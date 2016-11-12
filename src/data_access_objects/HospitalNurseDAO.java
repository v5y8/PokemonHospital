package data_access_objects;

import java.sql.*;

public class HospitalNurseDAO {
	private Connection con;
	private static HospitalNurseDAO instance;

	private HospitalNurseDAO() throws SQLException {
		con = DB_connector.getConnection();
	};

	public static HospitalNurseDAO getInstance() throws SQLException {
		if (instance == null) {
			instance = new HospitalNurseDAO();
		}
		return instance;
	};

	/**
	 * inserts an unemployed nurse into the table.
	 * @param nurse
	 * @throws SQLException
	 */
	public void insertNurse(String nurse) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO nurse VALUES (?,?)");

		ps.setString(1, nurse);
		// didn't input hospital; set as null.
		ps.setNull(2, java.sql.Types.VARCHAR);
		
		ps.executeUpdate();

		con.commit();

		ps.close();

	}
	/**
	 * builds a new hospital, with at least 1 nurse staffing it.
	 * @param hospital
	 * @param nid
	 * @throws SQLException
	 */
	public void buildHospital(String hospital, int nid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO hospital VALUES (?,?)");

		ps.setString(1, hospital);
		// didn't input hospital; set as null.
		ps.setInt(2, nid);
		
		ps.executeUpdate();

		con.commit();

		ps.close();

	}

	/**
	 * inserts a working nurse at a hospital into the table.
	 * @param nurse
	 * @param hospital
	 * @throws SQLException
	 */
	public void insertNurse(String nurse, String hospital) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO nurse VALUES (?,?)");

		ps.setString(1, nurse);
		ps.setString(2, hospital);
		
		ps.executeUpdate();
		con.commit();
		ps.close();

	}

	/**
	 * deletes a nurse from a table.
	 * 
	 * @param tid
	 * @throws SQLException
	 */
	public void deleteNurse(int nid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("DELETE FROM nurse WHERE nid = ?");
		ps.setInt(1, nid);
		ps.executeUpdate();
		con.commit();
		ps.close();

	}
	/**
	 * demolishes a hospital.
	 * @param nid
	 * @throws SQLException
	 */
	public void demolishHospital(String name) throws SQLException {

		PreparedStatement ps = con.prepareStatement("DELETE FROM hospital WHERE hname = ?");
		ps.setString(1, name);
		ps.executeUpdate();
		con.commit();
		ps.close();

	}
	/**
	 * transfers a nurse to a new hospital.
	 * @param nid
	 * @throws SQLException
	 */
	public void updateWorkPlace(int nid) throws SQLException {
		PreparedStatement ps = con
				.prepareStatement("UPDATE nurse SET hospital = ?");
		
		ps.setInt(1, nid);
		//AT THIS POINT THE TRIGGER SHOULD auto-update the hospital.
		
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
	 * returns the resultSet from all the pokemon managed by 1 nurse.
	 * 
	 * @param nurse
	 * @return
	 * @throws SQLException
	 */
	public ResultSet showPokemons(String nurse) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM PokemonName WHERE pid in "
													+ "(SELECT pid from nurseTrades WHERE nurse_name = ?)");
		ps.setString(1, nurse);

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
	/**
	 * returns the resultSet from all the trainers managed by 1 nurse.
	 * @param nurse
	 * @return
	 * @throws SQLException
	 */
	public ResultSet showTrainers(String nurse) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM trainer WHERE trainer_id in "
													+ "(SELECT Trainer_id from nurseTrades WHERE nurse_name = ?)");
		ps.setString(1, nurse);

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
