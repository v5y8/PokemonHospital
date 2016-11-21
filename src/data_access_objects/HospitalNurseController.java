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
}