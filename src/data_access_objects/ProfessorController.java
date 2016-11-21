package data_access_objects;

import java.sql.*;
import java.util.List;

import dataObjects.Pokemon;
import dataObjects.Trainer;

public class ProfessorController {
	private static ProfessorController professorControllerObject;
	private static ProfessorDAO professorDao;
	
	
	private ProfessorController(){
		try {
			//get instances of all the DAO objects
			professorDao = ProfessorDAO.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ProfessorController getInstance(){
		if (professorControllerObject == null){
			professorControllerObject = new ProfessorController();
		}
		return professorControllerObject;
	};
	
    /**
     * 
     * @param professor
     * @param tid
     * @param pid
     */
    public void editProfessors(String professor, int tid, int pid) {
		try {
			professorDao.updateProfessor(professor, tid, pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
    }
    
    /**
     * 
     * @param professors
     * @return
     */
    public List<Pokemon> getPokemons(String professor) {
    	List<Pokemon> result;
		try {
			result = professorDao.showPokemons(professor);
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 
     * @param professor
     * @return
     */
    public List<Trainer> getTrainers(String professor) {
    	List<Trainer> result;
		try {
			result = professorDao.showTrainers(professor);
			//do something that converts ResultSet to string
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
}