package DB;

import java.sql.*;

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
	public ResultSet showPokemon(int tid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM pokemonName WHERE pid in "
													+ "(SELECT pid from pokemonBelongs WHERE  trainer_id = ?)");
		ps.setInt(1, tid);

		ResultSet rs = ps.executeQuery();

		// // get info on ResultSet
		// TODO:naomi look at this code for controller manipulation.
		// ResultSetMetaData rsmd = rs.getMetaData();
		//
		// // get number of columns
		// int numCols = rsmd.getColumnCount();

		ps.close();
		return rs;

	}	

}
