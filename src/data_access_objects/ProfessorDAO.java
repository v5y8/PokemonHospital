package data_access_objects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dataObjects.Pokemon;
import dataObjects.Professor;
import dataObjects.Trainer;

public class ProfessorDAO {
	private Connection con;
	private static ProfessorDAO instance;

	private ProfessorDAO() throws SQLException {
		con = DB_connector.getConnection();
	};

	public static ProfessorDAO getInstance() throws SQLException {
		if (instance == null) {
			instance = new ProfessorDAO();
		}
		return instance;
	};

	/**
	 * inserts a professor, with no trainers and pokemon in table.
	 * 
	 * @param professor
	 * @throws SQLException
	 */
	public void insertProfessor(String professor) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO professorTrades VALUES (?,?,?)");

		ps.setString(1, professor);
		// didn't input trainer ID or pokemon ID; set as null.
		ps.setNull(2, java.sql.Types.INTEGER);
		ps.setNull(3, java.sql.Types.INTEGER);

		ps.executeUpdate();

		con.commit();

		ps.close();

	}

	/**
	 * return List of Professors
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Professor> showProfessors() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT professor_name FROM professorTrades");

		ResultSet rs = ps.executeQuery();

		List<Professor> toReturn = new ArrayList<Professor>();

		while(rs.next()){
			String profname = rs.getString("NAME");
			Professor professor = new Professor(profname);
			toReturn.add(professor);
		}

		ps.close();
		return toReturn;
	}

	/**
	 * inserts professor/trainer/pokemon entry into table.
	 * 
	 * @param professor
	 * @param tid
	 * @param pid
	 * @throws SQLException
	 */
	public void insertProfessor(String professor, int tid, int pid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO professorTrades VALUES (?,?,?)");

		ps.setString(1, professor);
		ps.setInt(2, pid);
		ps.setInt(3, tid);
		ps.executeUpdate();
		con.commit();
		ps.close();

	}

	/**
	 * deletes a professor from a table.
	 * 
	 * @param tid
	 * @throws SQLException
	 */
	public void deleteProfessor(String professor) throws SQLException {

		PreparedStatement ps = con.prepareStatement("DELETE FROM professorTrades WHERE professor_name = ?");
		ps.setString(1, professor);
		ps.executeUpdate();
		con.commit();
		ps.close();

	}

	/**
	 * updates an entry in the professorTrades table
	 * 
	 * @param professor
	 * @param tid
	 * @param pid
	 * @throws SQLException
	 */
	public void updateProfessor(String professor, int tid, int pid) throws SQLException {
		PreparedStatement ps = con
				.prepareStatement("UPDATE professorTrades SET pid = ? trainer_id = ? WHERE professor = ?");
		ps.setInt(1, pid);
		ps.setInt(2, tid);
		ps.setString(3, professor);

		// TODO: naomi look at this code; may help with controller.
		// int rowCount = ps.executeUpdate();
		// if (rowCount == 0)
		// {
		// System.out.println("\ntrainer " + trainer_id + " does not exist!");
		// }
		ps.executeUpdate();
		con.commit();

		ps.close();

	}

	/**
	 * returns the resultSet from all the pokemon managed by 1 professor.
	 * 
	 * @param professor
	 * @return
	 * @throws SQLException
	 */
	public List<Pokemon> showPokemons(String professor) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM PokemonName WHERE pid in "
				+ "(SELECT pid from professorTrades WHERE professor_name = ?)");
		ps.setString(1, professor);

		ResultSet rs = ps.executeQuery();

		List<Pokemon> toReturn = new ArrayList<Pokemon>();

		while(rs.next()){
			int poke_id = rs.getInt("PID");
			String pname = rs.getString("PNAME");
			int cp = rs.getInt("CP");
			int hp = rs.getInt("HP");
			String pokeball = rs.getString("POKEBALL");
			Pokemon pokemon = new Pokemon(poke_id, pname, cp, hp, pokeball);
			toReturn.add(pokemon);
		}

		ps.close();
		return toReturn;

	}
	/**
	 * returns the resultSet from all the trainers managed by 1 professor.
	 * @param professor
	 * @return
	 * @throws SQLException
	 */
	public List<Trainer> showTrainers(String professor) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM trainer WHERE trainer_id in "
				+ "(SELECT Trainer_id from professorTrades WHERE professor_name = ?)");
		ps.setString(1, professor);

		ResultSet rs = ps.executeQuery();
		List<Trainer> toReturn = new ArrayList<>();

		while(rs.next()){
			int trainer_id = rs.getInt("TRAINER_ID");
			String trainer_name = rs.getString("TRAINER_NAME");
			Trainer trainer = new Trainer(trainer_id, trainer_name);
			toReturn.add(trainer);
		}

		ps.close();
		return toReturn;

	}

}
