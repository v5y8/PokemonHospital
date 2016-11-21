package data_access_objects;

import java.sql.*;

//singleton pattern DAO for accessing data.
public class PokemonDAO {
	private Connection con;
	private static PokemonDAO instance;

	private PokemonDAO() throws SQLException {
		con = DB_connector.getConnection();
	};

	public static PokemonDAO getInstance() throws SQLException {
		if (instance == null) {
			instance = new PokemonDAO();
		}
		return instance;
	};

	/**
	 * inserts a new pokemon into the list of known pokemons.
	 * @param pid
	 * @param name
	 * @param cp
	 * @param hp
	 * @param pokeball
	 * @throws SQLException
	 */
	public void insertPokemon(int pid, String name, int cp, int hp, String pokeball) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO pokemonName VALUES (?,?,?,?,?)");

		ps.setInt(1, pid);
		ps.setString(2, name);
		ps.setInt(3, cp);
		ps.setInt(4, hp);
		ps.setString(5, pokeball);

		ps.executeUpdate();
		con.commit();

		ps.close();

	}

	
	/**
	 * deletes a pokemon from the list of all pokemon.
	 * @param pid
	 * @throws SQLException
	 */
	public void deletepokemon(int pid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("DELETE FROM pokemonName WHERE pid = ?");
		ps.setInt(1, pid);
		ps.executeUpdate();
		con.commit();
		ps.close();

	}

	/**
	 * updates information about pokemon. NOT for updating trainer/pokemon info.
	 * @param pid
	 * @param name
	 * @param cp
	 * @param hp
	 * @param pokeball
	 * @throws SQLException
	 */
	public void updatePokemon(int pid, String name, int cp, int hp, String pokeball) throws SQLException {

		PreparedStatement ps = con
				.prepareStatement("UPDATE pokemonName SET pname = ? cp = ? hp = ? pokeball = ? WHERE pid = ?");
		ps.setInt(5, pid);
		ps.setString(1, name);
		ps.setInt(2, cp);
		ps.setInt(3, hp);
		ps.setString(4, pokeball);
		// TODO: naomi look at this code; may help with controller.
		// int rowCount = ps.executeUpdate();
		// if (rowCount == 0)
		// {
		// System.out.println("\npokemon " + pokemon_id + " does not exist!");
		// }
		ps.executeUpdate();
		con.commit();

		ps.close();

	}

	/**
	 * enters pokemon and coresponding trainer into pokemonBelongs table.
	 * 
	 * @param tid
	 * @param pid
	 * @throws SQLException
	 */
	public void trainerCaught(int tid, int pid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO pokemonBelongs VALUES(?,?)");
		ps.setInt(1, pid);
		ps.setInt(2, tid);

		// TODO: naomi look at this code; may help with controller.
		// int rowCount = ps.executeUpdate();
		// if (rowCount == 0)
		// {
		// System.out.println("\npokemon " + pokemon_id + " does not exist!");
		// }
		ps.executeUpdate();
		con.commit();

		ps.close();

	}
	
	/**
	 * retrieve pokemon and corresponding trainer into pokemonBelongs table.
	 * 
	 * @param tid
	 * @param pid
	 * @throws SQLException
	 */
	public ResultSet retrievePokemons(int tid, int pid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * from pokemonName where"
													+ "pid in ("
															+ "SELECT pid from pokemonBelongs"
															+ "where pid = ? AND tid = ?)");
		ps.setInt(1, pid);
		ps.setInt(2, tid);
		
		ResultSet rs = ps.executeQuery();

		// TODO: naomi look at this code; may help with controller.
		// int rowCount = ps.executeUpdate();
		// if (rowCount == 0)
		// {
		// System.out.println("\npokemon " + pokemon_id + " does not exist!");
		// }
		ps.close();
		return rs;
	}

	/**
	 * updates the pokemon belonging to trainers.
	 * 
	 * @param tid
	 * @param pid
	 * @throws SQLException
	 */
	public void updateTrainer(int tid, int pid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("UPDATE pokemonBelongs SET trainer_id = ? WHERE pid = ?");
		ps.setInt(2, pid);
		ps.setInt(1, tid);

		// TODO: naomi look at this code; may help with controller.
		// int rowCount = ps.executeUpdate();
		// if (rowCount == 0)
		// {
		// System.out.println("\npokemon " + pokemon_id + " does not exist!");
		// }
		ps.executeUpdate();
		con.commit();

		ps.close();

	}

	/**
	 * releases a pokemon back into the wild.
	 * @param pid
	 * @param tid
	 * @throws SQLException
	 */
	public void freePokemon(int pid, int tid) throws SQLException {

		PreparedStatement ps = con.prepareStatement("DELETE from pokemonBelongs  WHERE pid = ? AND tid = ?");
		ps.setInt(1, pid);
		ps.setInt(2, tid);

		// TODO: naomi look at this code; may help with controller.
		// int rowCount = ps.executeUpdate();
		// if (rowCount == 0)
		// {
		// System.out.println("\npokemon " + pokemon_id + " does not exist!");
		// }
		ps.executeUpdate();
		con.commit();

		ps.close();

	}
	/**
	 * returns a list of pokemon which no trainer has.
	 * @return
	 * @throws SQLException
	 */
	public ResultSet wildPokemon() throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM pokemonName "
													+ "WHERE pid not in("
													+ "SELECT pid FROM pokemonBelongs);");

		ResultSet rs = ps.executeQuery();

		// // get info on ResultSet
		// TODO:naomi look at this code for controller manipulation
		// ResultSetMetaData rsmd = rs.getMetaData();
		//
		// // get number of columns
		// int numCols = rsmd.getColumnCount();

		ps.close();
		return rs;
	}

}
