package data_access_objects;

import java.sql.*;

public class ProfessorController {
	private ProfessorController professorControllerObject;
	private ProfessorDAO professorDao;
	
	
	private ProfessorController(){
		try {
			//get instances of all the DAO objects
			professorDao = ProfessorDAO.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ProfessorController getInstance(){
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
    public String getPokemons(String professor) {
    	ResultSet result;
		try {
			result = professorDao.showPokemons(professor);
			//do something that converts ResultSet to string
	    	String pokemons = result.getNString(0);
	    	return pokemons;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
    }
    
    /**
     * 
     * @param professor
     * @return
     */
    public String getTrainers(String professor) {
    	ResultSet result;
		try {
			result = professorDao.showTrainers(professor);
			//do something that converts ResultSet to string
	    	String trainers = result.getNString(0);
	    	return trainers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
    }
}