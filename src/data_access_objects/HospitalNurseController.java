package data_access_objects;

import java.sql.*;
import java.util.Dictionary;
import java.util.List;

import dataObjects.Incubator;
import dataObjects.Nurse;
import dataObjects.Pokemon;
import dataObjects.Professor;

public class HospitalNurseController {
	private static HospitalNurseController hospitalNurseControllerObject;
	private static HospitalNurseDAO hospitalnurseDao;
	private static PokemonDAO pokemonDao;
        private static PokemonController pController;
	private static ProfessorDAO professorDao;
	private static TrainerDAO trainerDao;


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

	public static HospitalNurseController getInstance(){
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
	 * @return
	 */
	public List<Nurse> getNurses() {
		List<Nurse> result;
		try {
			result = hospitalnurseDao.showNurse();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param nid
	 * @return
	 */
	public List<Pokemon> getPokemons(int nid) {
		List<Pokemon> result;
		try {
			result = hospitalnurseDao.showPokemons(nid);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public Dictionary<Integer, Integer> getNurseLoad() {
		Dictionary<Integer, Integer> result;
		try {
			result = hospitalnurseDao.showNurseLoad();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public List<Nurse> getAvailableNurses(){
		
		List<Nurse> result;
		try {
			result = hospitalnurseDao.showAvailableNurses();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * 
	 * @return
	 */
	public Dictionary<Integer, Integer> getEggNum() {
		Dictionary<Integer, Integer> result;
		try {
			result = hospitalnurseDao.showIncubatorLoad();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param pid
	 * @param nid
	 */
	public void healPokemon(int pid, int nid) {
		try {
			int nurseLoad = hospitalnurseDao.showIncubatorLoad().get(nid);
			if (nurseLoad < 6) {
				hospitalnurseDao.depositPokemons(pid, nid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

	/**
	 * 
	 * @param nid
	 * @param tid
	 * @param pid
	 * @return
	 */
	public List<Pokemon> getDepositPokemon(int nid, int tid) {
		List<Pokemon> result;
		try {
			result = hospitalnurseDao.getTrainerPokemon(nid, tid);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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

	public void deposit(int pid, int nid) {
		try {		
			hospitalnurseDao.depositPokemons(pid, nid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

	/**
	 * 
	 * @param tid
	 * @param pid
	 * @param nid
	 */
	public void pickupPokemon(int tid, int pid, int nid) {
		try {
			Long currentTime = new java.util.Date().getTime();
			Long healTime = hospitalnurseDao.getHealTime(nid, pid).getTime();
			double differenceInHours = (healTime - currentTime)/(7.2*1000000);
			if (differenceInHours >= 2) {
				hospitalnurseDao.removePokemon(pid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	/**
	 * 
	 * @param tid
	 * @param pid
	 */
	public void pickupHatchedPokemon(int tid, int pid) {
		try {
			Long currentTime = new java.util.Date().getTime();
			Long incubateTime = hospitalnurseDao.getHatchTime(pid, tid).getTime();
			double differenceInHours = (incubateTime - currentTime)/(7.2*1000000);
			if (differenceInHours >= 2) {
				hospitalnurseDao.removeFromIncubator(pid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
        
        	/**
	 * 
	 * @param tid
	 * @param pid
	 */
	public void pickupHatchedEgg(int tid, int eid) {
		try {
			Long currentTime = new java.util.Date().getTime();
			Long incubateTime = hospitalnurseDao.getHatchTime(eid, tid).getTime();
			double differenceInHours = (incubateTime - currentTime)/(7.2*1000000);
			if (differenceInHours >= 24) {
				hospitalnurseDao.removeFromIncubator(eid);
                                pController.getPokemon(tid, eid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Incubator> getIncubators() {
		List<Incubator> result;
    	try {
			result = hospitalnurseDao.showIncubators();
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}