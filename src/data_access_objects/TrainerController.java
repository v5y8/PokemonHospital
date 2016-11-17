package data_access_objects;

import java.sql.*;

public class TrainerController {
	private TrainerController trainerControllerObject;
	private TrainerDAO trainerDao;
	
	
	private TrainerController(){
		try {
			//get instances of all the DAO objects
			trainerDao = TrainerDAO.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TrainerController getInstance(){
		if (trainerControllerObject == null){
			trainerControllerObject = new TrainerController();
		}
		return trainerControllerObject;
	};
	
    /**
     * 
     * @param tid
     * @param name
     */
    public void editTrainers(int tid, String name) {
		try {
			trainerDao.updateTrainer(tid, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
    }
    
    /**
     * 
     * @param tid
     * @return
     */
    public String getPokemons(int tid) {
    	ResultSet result;
		try {
			result = trainerDao.showPokemon(tid);
			//do something that converts ResultSet to string
	    	String pokemons = result.getNString(0);
	    	return pokemons;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
    }
    
}