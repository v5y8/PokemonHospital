package data_access_objects;

import java.util.List;
import java.util.Set;
import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import dataObjects.*;

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
	 * return List of Nurse
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Nurse> showNurse() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM nurse");

		ResultSet rs = ps.executeQuery();

		List<Nurse> toReturn = new ArrayList<Nurse>();

		while(rs.next()){
			int nid = rs.getInt("NID");
			String hname = rs.getString("HNAME");
			Nurse nurse = new Nurse(nid, hname);
			toReturn.add(nurse);
		}

		ps.close();
		return toReturn;
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
	 * return List of Incubator IDs
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Incubator> showIncubators() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT iid FROM incubator");

		ResultSet rs = ps.executeQuery();

		List<Incubator> toReturn = new ArrayList<Incubator>();

		while(rs.next()){
			int iid = rs.getInt("IID");
			Incubator incubator = new Incubator(iid);
			toReturn.add(incubator);
		}

		ps.close();
		return toReturn;
	}

	/**
	 * puts pokemon into incubation under a nurse
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

		ps1.executeUpdate();
		ps2.executeUpdate();
		con.commit();
		ps1.close();
		ps2.close();
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

		ps1.executeUpdate();
		ps2.executeUpdate();
		con.commit();
		ps1.close();
		ps2.close();
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
	 * deposit pokemon of pid to the care of nurse of nid
	 * 
	 * @param pid
	 * @param nid
	 * @throws SQLException
	 */
	public void depositPokemons(int pid, int nid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT into healPokemon VALUES (?,?,?)");
		ps.setInt(1, pid);
		ps.setInt(2, nid);
		Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
		ps.setTimestamp(3, timestamp);

		ps.executeUpdate();
		con.commit();
		ps.close();
	}

	/**
	 * removes healed Pokemon from healPokemon
	 * 
	 * @param pid
	 * @throws SQLException
	 */
	public void removePokemon(int pid) throws SQLException{
		PreparedStatement ps = con.prepareStatement("DELETE from healPokemon where pid = ?");
		
		ps.setInt(1, pid);
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
	public List<Pokemon> showPokemons(int nid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM pokemonName WHERE pid in (SELECT PID from healPokemon WHERE nid = ?)");
		ps.setInt(1, nid);

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
	 * shows the # of pokemon each nurse is caring for.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dictionary<Integer, Integer> showNurseLoad() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT nid, count(*) FROM healPokemon " + "group by nid");

		ResultSet rs = ps.executeQuery();
		Dictionary<Integer, Integer> nurseLoad = new Hashtable<>();

		while(rs.next()){
			int nid = rs.getInt("NID");
			int load = rs.getInt("COUNT(*)");
			nurseLoad.put(nid, load);

		}
		ps.close();
		return nurseLoad;
	}
	/**
	 * shows all available nurses with less than 6 pokemon to care for.
	 * @return
	 * @throws SQLException
	 */
	public List<Nurse> showAvailableNurses() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * from nurse "
													+ "where nid in"
													+ "(select nid from healPokemon"
													+ "group by nid"
													+ "having count(nid)<6");

		ResultSet rs = ps.executeQuery();
		List<Nurse> toReturn = new ArrayList<>();;

		while(rs.next()){
			Nurse toAdd = new Nurse(rs.getInt("NID"), rs.getString("HNAME"));
			toReturn.add(toAdd);

		}
		ps.close();
		return toReturn;
	}
	
	/**
	 * shows the # of pokemon under each incubator.
	 * @return
	 * @throws SQLException
	 */
	public Dictionary<Integer, Integer>  showIncubatorLoad() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT iid, count(*) FROM incubation " + "group by iid");

		ResultSet rs = ps.executeQuery();

		Dictionary<Integer, Integer> incubatorLoad = new Hashtable<>();

		while(rs.next()){
			int iid = rs.getInt("IID");
			int load = rs.getInt("COUNT(*)");
			incubatorLoad.put(iid, load);

		}
		ps.close();
		return incubatorLoad;
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
	public List<Pokemon> getTrainerPokemon(int nid, int tid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * from pokemonName WHERE pid in (SELECT pid from healPokemon where nid = ? AND pid in(SELECT pid from pokemonBelongs WHERE trainer_id = ?))");
		ps.setInt(1, nid);
		ps.setInt(2, tid);

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

	/**
	 * get healtime of pid under nid
	 * 
	 * @param nid
	 * @param pid
	 */
	public Timestamp getHealTime(int nid, int pid) throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT healdate from healPokemon WHERE nid = ? AND pid = ?");

		ps.setInt(1, nid);
		ps.setInt(2, pid);

		ResultSet rs = ps.executeQuery();
		
		Timestamp toReturn = rs.getTimestamp(0);

		ps.close();
		return toReturn;
	}

	/**
	 * get hatchtime of pid deposited by tid
	 * 
	 * @param pid
	 * @param tid
	 * @return
	 * @throws SQLException
	 */
	public Timestamp getHatchTime(int pid, int tid) throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT eddate from eggDeposits WHERE tid = ? AND pid = ?");

		ps.setInt(1, tid);
		ps.setInt(2, pid);

		ResultSet rs = ps.executeQuery();
		Timestamp toReturn = rs.getTimestamp(0);

		ps.close();
		return toReturn;
	}

}
