package data_access_objects;

import java.sql.*;
import java.util.List;

import dataObjects.Pokemon;

public class PokemonController {
	private static PokemonController pokemonControllerObject;
	private static PokemonDAO pokemonDao;
	
	
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
	
	public static PokemonController getInstance(){
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
    public List<Pokemon> getPokemon(int tid, int pid) {
        //ask the Dao for what you want
    	List<Pokemon> result;
		try {
			result = pokemonDao.retrievePokemons(tid, pid);
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
    public List<Pokemon> uncaughtPokemon() {
        //ask the Dao for what you want
    	List<Pokemon> result;
		try {
			result = pokemonDao.wildPokemon();
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
    	pokemonControllerObject.updateTrainers(tid2, pid);
    }
    
    /**
     * tid1 trades pid1 for pid2 with tid2
     * 
     * @param tid1
     * @param tid2
     * @param pid1
     * @param pid2
     */
    public void tradePokemon(int tid1, int tid2, int pid1, int pid2) {
    	pokemonControllerObject.updateTrainers(tid1, pid2);
		pokemonControllerObject.updateTrainers(tid2, pid1);
    }
    
}