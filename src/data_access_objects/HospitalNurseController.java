package data_access_objects;

import java.sql.*;

public class HospitalNurseController {
	private HospitalNurseController hospitalNurseControllerObject;
	private HospitalNurseDAO hospitalnurseDao;
	private PokemonDAO pokemonDao;
	private ProfessorDAO professorDao;
	private TrainerDAO trainerDao;


	private HospitalNurseController(){
		try {
			//get instances of all the DAO objects
			hospitalnurseDao = HospitalNurseDAO.getInstance();
			pokemonDao = PokemonDAO.getInstance();
			professorDao = ProfessorDAO.getInstance();
			trainerDao = TrainerDAO.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HospitalNurseController getInstance(){
		if (hospitalNurseControllerObject == null){
			hospitalNurseControllerObject = new HospitalNurseController();
		}
		return hospitalNurseControllerObject;
	};

	/**
	 * 
	 * @param nid
	 */
	public void editNurse(int nid) {
		try {
			hospitalnurseDao.updateWorkPlace(nid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

	/**
	 * 
	 * @param nid
	 * @return
	 */
	public String getPokemons(int nid) {
		ResultSet result;
		try {
			result = hospitalnurseDao.showPokemons(nid);
			//do something that converts ResultSet to string
			String pokemon = result.getNString(0);
			return pokemon;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getNurseLoad() {
		ResultSet result;
		try {
			result = hospitalnurseDao.showNurseLoad();
			int pokemon = result.getInt(0);
			// TODO: not sure about getInt(0)
			return pokemon;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getEggNum() {
		ResultSet result;
		try {
			result = hospitalnurseDao.showIncubatorLoad();
			int pokemon = result.getInt(0);
			// TODO: not sure about getInt(0)
			return pokemon;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	/**
	 * 
	 * @param nid
	 * @param tid
	 * @param pid
	 * @return
	 */
	public String getDepositPokemon(int nid, int tid, int pid) {
		ResultSet result;
		try {
			result = hospitalnurseDao.getTrainerPokemon(nid, tid, pid);
			//do something that converts ResultSet to string
			String pokemon = result.getNString(0);
			return pokemon;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	/**
	 * nid1 trades pid1 for pid2 with nid2
	 * 
	 * @param tid1
	 * @param tid2
	 * @param pid1
	 * @param pid2
	 */
	public void tradeNurse(int nid1, int nid2, int pid1, int pid2) {
		try {
			hospitalnurseDao.updatePokemon(nid1, pid2);
			hospitalnurseDao.updatePokemon(nid2, pid1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

	/**
	 * 
	 * @param tid
	 * @param nid
	 * @param pid
	 */
	public void pickupPokemon(int tid, int nid, int pid) {
		try{
			if (hospitalnurseDao.getHealTime(nid, pid) > 2 &&
					hospitalnurseDao.getTrainerPokemon(nid, tid, pid) != null) {
				hospitalnurseDao.removeFromIncubator(pid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
}