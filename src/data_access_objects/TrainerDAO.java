package data_access_objects;

import java.sql.*;
import java.util.*;

import dataObjects.Egg;
import dataObjects.Pokemon;

//singleton pattern DAO for accessing data.
public class TrainerDAO {
	private Connection con;
	private static TrainerDAO instance;

	private TrainerDAO() throws SQLException {
		con = DB_connector.getConnection();
	};

	public static TrainerDAO getInstance() throws SQLException {
		if (instance == null) {
			instance = new TrainerDAO();
		}
		return instance;
	};

	/**
	 * inserts a trainer into table
	 * 
	 * @param tid
	 *            = trainer ID in int
	 * @param name
	 *            = trainer name in String
	 * 
	 */
	public void insertTrainer(int tid, String name) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO trainer VALUES (?,?)");

		ps.setInt(1, tid);
		ps.setString(2, name);

		ps.executeUpdate();

		con.commit();

		ps.close();

	}

	/**
	 * deletes a trainer from table
	 * 
	 * @param tid
	 *            = trainer ID in int Naomi: con.rollback(); may come in handy
	 *            for catching the SQLException.
	 */
	public void deletetrainer(int tid) throws SQLException {
		int trainer_id = tid;
		PreparedStatement ps;

		ps = con.prepareStatement("DELETE FROM trainer WHERE trainer_id = ?");
		ps.setInt(1, trainer_id);
		ps.executeUpdate();
		con.commit();
		ps.close();

	}

	/**
	 * updates the name of a trainer
	 * 
	 * @param tid
	 *            = trainer_Id in int
	 * @param name
	 *            = new name to be updated to.
	 */
	public void updateTrainer(int tid, String name) throws SQLException {
		PreparedStatement ps = con.prepareStatement("UPDATE trainer SET trainer_name = ? WHERE trainer_id = ?");
		ps.setInt(2, tid);
		ps.setString(1, name);

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
	 * returns the resultSet of all pokemon belonging to 1 trainer.
	 * @param tid
	 * @return
	 * @throws SQLException
	 */
	public List<Pokemon> showPokemon(int tid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM pokemonName WHERE pid in "
													+ "(SELECT pid from pokemonBelongs WHERE  trainer_id = ?)");
		ps.setInt(1, tid);

		ResultSet rs = ps.executeQuery();
		List<Pokemon> toReturn = new ArrayList<Pokemon>();
		while(rs.next()){
			int pid = rs.getInt("PID");
			String pname = rs.getString("PNAME");
			int cp = rs.getInt("CP");
			int hp = rs.getInt("HP");
			String pokeball = rs.getString("POKEBALL");
			Pokemon pokemon = new Pokemon(pid, pname, cp, hp, pokeball);
			toReturn.add(pokemon);
		}
		
		ps.close();
		return toReturn;

	}	
	/**
	 * an egg for a pokemon is obtained for the trainer!
	 * @param tid
	 * @param pid
	 * @throws SQLException
	 */
	public void obtainEgg(int tid, int pid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO eggDeposit VALUES (?,?,?)");
		
		//make a timestamp
		Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
		
		ps.setTimestamp(1, timestamp);
		ps.setInt(2, pid);
		ps.setInt(3, tid);
		

		ps.executeUpdate();

		con.commit();

		ps.close();

	}
	/**
	 * views the eggs a trainer has.
	 * @param tid the trainer id;
	 * @return
	 */
	public List<Egg> viewEgg(int tid) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM eggDeposit where trainer_id=?");
		ps.setInt(1, tid);
		ResultSet rs = ps.executeQuery();
		
		List<Egg> toReturn = new ArrayList<>();
		
		while(rs.next()){
			Timestamp timestamp = rs.getTimestamp("EDDATE");
			int pid = rs.getInt("PID");
			int trainer_id = rs.getInt("TRAINER_ID");
			Egg egg = new Egg(timestamp, pid, trainer_id);
			toReturn.add(egg);
		}
		ps.close();
		return toReturn;
	}
	/**
	 * discards the egg obtained at this timestamp.
	 * @param timestamp
	 * @throws SQLException
	 */
	public void discardEgg(Timestamp timestamp) throws SQLException {

		PreparedStatement ps = con.prepareStatement("DELETE FROM eggDeposit where eddate = ?");
		
		
		ps.setTimestamp(1, timestamp);
		
		ps.executeUpdate();

		con.commit();

		ps.close();

	}
}
