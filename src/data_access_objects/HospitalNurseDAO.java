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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * @param nid
	 * @throws SQLException
	 */
	public void updateWorkPlace(int nid) throws SQLException {
		PreparedStatement ps = con.prepareStatement("UPDATE nurse SET hospital = ?");

		ps.setInt(1, nid);
		// AT THIS POINT THE TRIGGER SHOULD auto-update the hospital.

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
	 * puts pokemon into incubation under a nurse; as well as the relevant entries into incubator and healPokemon..
	 * @param pid
	 * @param nid
	 * @param iid
	 * @throws SQLException
	 */
	public void putInIncubator(int pid, int nid, int iid) throws SQLException {
		//insert into incubation table
		PreparedStatement ps1 = con.prepareStatement("INSERT into Incubation VALUES (?,?,?)");
		ps1.setInt(1, pid);
		ps1.setInt(2, nid);
		ps1.setInt(3, iid);
		
		//insert into Incubator table
		PreparedStatement ps2 = con.prepareStatement("INSERT into incubator VALUES (?,?)");
		ps2.setInt(1, iid);
		ps2.setInt(2, pid);
		
		//insert into healPokemon table
		PreparedStatement ps3 = con.prepareStatement("INSERT into healPokemon VALUES (?,?)");
		ps3.setInt(1, pid);
		ps3.setInt(2, nid);
		
		
		
		ps1.executeUpdate();
		ps2.executeUpdate();
		ps3.executeUpdate();
		con.commit();
		ps1.close();
		ps2.close();
		ps3.close();
	}
	/**
	 * takes the relevant pokemon out of incubator, as well as out of incubation and healPokemon.
	 * @param pid
	 * @throws SQLException
	 */
	public void removeFromIncubator(int pid) throws SQLException {
		//remove from incubation table
		PreparedStatement ps1 = con.prepareStatement("DELETE from Incubation where pid = ?");
		ps1.setInt(1, pid);
		
		//remove from Incubator table
		PreparedStatement ps2 = con.prepareStatement("DELETE from incubator where pid = ?");
		ps2.setInt(1, pid);
		
		//remove from healPokemon table
		PreparedStatement ps3 = con.prepareStatement("DELETE from healPokemon where pid = ?");
		ps3.setInt(1, pid);
		
		ps1.executeUpdate();
		ps2.executeUpdate();
		ps3.executeUpdate();
		con.commit();
		ps1.close();
		ps2.close();
		ps3.close();
	}
	/**
	 * adds a new incubator to the table.
	 * @param iid
	 * @throws SQLException
	 */
	public void addNewIncubator(int iid) throws SQLException{
		PreparedStatement ps = con.prepareStatement("INSERT INTO incubator VALUES (?,?)");

		ps.setInt(1, iid);
		ps.setNull(2, java.sql.Types.INTEGER);

		ps.executeUpdate();
		con.commit();
		ps.close();

	}
	/**
	 * removes an incubator from the table.
	 * @param iid
	 * @throws SQLException
	 */
	public void removeIncubator(int iid) throws SQLException{
		PreparedStatement ps = con.prepareStatement("DELETE FROM incubator WHERE iid = ?");

		ps.setInt(1, iid);
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
	public ResultSet showPokemons(int nid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM incubation WHERE nid = ?");
		ps.setInt(1, nid);

		ResultSet rs = ps.executeQuery();

		// // get info on ResultSet
		// TODO:naomi look at this code for controller manipulation
		// ResultSetMetaData rsmd = rs.getMetaData();
		//
		// // get number of columns
		// int numCols = rsmd.getColumnCount();

		ps.close();
		return rs;

	}

	/**
	 * shows the # of pokemon each nurse is caring for.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ResultSet showNurseLoad() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT nid, count(*) FROM incubation " + "group by nid");

		ResultSet rs = ps.executeQuery();

		// // get info on ResultSet
		// TODO:naomi look at this code for controller manipulation
		// ResultSetMetaData rsmd = rs.getMetaData();
		//
		// // get number of columns
		// int numCols = rsmd.getColumnCount();

		ps.close();
		return rs;
	}
	/**
	 * shows the # of pokemon under each incubator.
	 * @return
	 * @throws SQLException
	 */
	public ResultSet showIncubatorLoad() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT iid, count(*) FROM incubation " + "group by iid");

		ResultSet rs = ps.executeQuery();

		// // get info on ResultSet
		// TODO:naomi look at this code for controller manipulation
		// ResultSetMetaData rsmd = rs.getMetaData();
		//
		// // get number of columns
		// int numCols = rsmd.getColumnCount();

		ps.close();
		return rs;
	}
	
	/**
	 * show pokemon deposited by trainer from healPokemon table.
	 * 
	 * @param nid
	 * @param tid
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getTrainerPokemon(int nid, int tid, int pid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * from healPokemon where"
													+ "nid = ? AND pid in ("
															+ "SELECT pid from pokemonBel"
															+ "ongs"
															+ "where pid = ? AND trainer_id = ?)");
		ps.setInt(1, nid);
		ps.setInt(2, pid);
		ps.setInt(3, tid);
		
		ResultSet rs = ps.executeQuery();
		ps.close();
		return rs;
	}
	
	/**
	 * updates pokemon with nurse
	 * 
	 * @param nid
	 * @param pid
	 * @throws SQLException
	 */
	public void updatePokemon(int nid, int pid) throws SQLException {
		PreparedStatement ps = con.prepareStatement("UPDATE healPokemon SET nid = ? WHERE pid = ?");

		ps.setInt(1, nid);
		ps.setInt(2, pid);
		
		ps.executeUpdate();
		con.commit();

		ps.close();

	}
	
}
