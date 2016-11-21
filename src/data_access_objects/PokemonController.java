package data_access_objects;

import java.sql.*;

public class PokemonController {
	private PokemonController pokemonControllerObject;
	private PokemonDAO pokemonDao;
	
	
	private PokemonController(){
		try {
			//get instances of all the DAO objects
			pokemonDao = PokemonDAO.getInstance();
			//do the rest
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PokemonController getInstance(){
		if (pokemonControllerObject ==null){
			pokemonControllerObject = new PokemonController();
		}
		return pokemonControllerObject;
	};
	
    /**
     * 
     * @param tid
     * @param pid
     * @return
     */
    public String getPokemon(int tid, int pid) {
        //ask the Dao for what you want
    	ResultSet result;
		try {
			result = pokemonDao.retrievePokemons(tid, pid);
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
     * @param pid
     * @param name
     * @param cp
     * @param hp
     * @param pokeball
     */
    public void editPokemon(int pid, String name, int cp, int hp, String pokeball) {
    	try {
			pokemonDao.updatePokemon(pid, name, cp, hp, pokeball);
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
    public void updateTrainers(int tid, int pid) {
		try {
			pokemonDao.updateTrainer(tid, pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
    }
    
    /**
     * 
     * @param pid
     * @param tid
     */
    public void removePokemon(int pid, int tid) {
    	try {
			pokemonDao.freePokemon(pid, tid);
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
    public void newTrainer(int tid, int pid) {
		try {
			pokemonDao.trainerCaught(tid, pid);
			//do something that converts ResultSet to string
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
    }
    
    /**
     * 
     * @return
     */
    public String uncaughtPokemon() {
        //ask the Dao for what you want
    	ResultSet result;
		try {
			result = pokemonDao.wildPokemon();
			//do something that converts ResultSet to string
	    	String pokemon = result.getNString(0);
	    	return pokemon;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
    }
    
    /**
     * transfer pokemon of pid from tid1 to tid2
     * 
     * @param tid1
     * @param tid2
     * @param pid
     * @param professor
     */
    public void transferPokemon(int tid1, int tid2, int pid) {
    	pokemonControllerObject.newTrainer(tid2, pid);
		pokemonControllerObject.removePokemon(pid, tid1);
    }
    
    /**
     * tid1 trades pid1 for pid2 from tid2
     * 
     * @param tid1
     * @param tid2
     * @param pid1
     * @param pid2
     */
    public void tradePokemon(int tid1, int tid2, int pid1, int pid2) {
    	pokemonControllerObject.newTrainer(tid1, pid2);
		pokemonControllerObject.removePokemon(pid2, tid2);
		pokemonControllerObject.newTrainer(tid2, pid1);
		pokemonControllerObject.removePokemon(pid1, tid1);
    }
    
}