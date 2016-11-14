package data_access_objects;

import java.sql.*;

public class PokemonController {
	private PokemonController pokemonControllerObject;
	
    // input: trainerID, pokeball description, output: return pokemon to player
    public PokemonDAO getPokemon(Integer tid, String pokeball) {
        String SQL = "select * from Pokemon where tid = ?";
    	PokemonDAO pokemon = pokemonControllerObject.queryForObject(SQL, new Object[]{tid}, new PokemonDAO());
    	
    	return pokemon;
    }
    
}