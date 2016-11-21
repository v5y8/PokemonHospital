package data_access_objects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dataObjects.Pokemon;

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
    public List<Pokemon> getPokemons(int tid) {
    	List<Pokemon> result = new ArrayList<>();;
		try {
			result = trainerDao.showPokemon(tid);
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
}