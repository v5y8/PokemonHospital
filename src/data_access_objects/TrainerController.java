package data_access_objects;

import dataObjects.Egg;
import dataObjects.Nurse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dataObjects.Pokemon;
import dataObjects.Professor;
import dataObjects.Trainer;

public class TrainerController {
	private static TrainerController trainerControllerObject;
	private static TrainerDAO trainerDao;
        private static HospitalNurseController hospitalNurseControllerObject;
	
	
	private TrainerController(){
		try {
			//get instances of all the DAO objects
			trainerDao = TrainerDAO.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TrainerController getInstance(){
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
     * @return
     */
    public List<Trainer> getTrainers() {
    	List<Trainer> result;
    	try {
			result = trainerDao.showTrainer();
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
    
    public List<Egg> viewEgg(int tid){
            	List<Egg> result = new ArrayList<>();
		try {
			result = trainerDao.viewEgg(tid);
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public void getEgg(int tid) {
        		try {
                         List<Trainer> tList= trainerControllerObject.getTrainers();
                         List<Pokemon> pms = new ArrayList<Pokemon>();
                         for(Trainer t:tList) {
                             pms =trainerControllerObject.getPokemons(t.getTrainer_id());
                             for(int i=0;i<pms.size();i++) {
                                  pms.add(getPokemons(t.getTrainer_id()).get(i));
                             }
                         }
                         int index = (int)Math.random() *( pms.size()-1) ;
                         
			trainerDao.obtainEgg(tid, pms.get(index).getPid());
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}