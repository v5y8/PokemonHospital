package DB;

public class ProfessorDAO {

	public ProfessorDAO() {
		// TODO Auto-generated constructor stub
	}


	/*
	 * display information about pokemons
	 */
	// private void showpokemon(int tid, )
	// {
	// String pokemon_id;
	// String pokemon_name;
	// Statement stmt;
	// ResultSet rs;
	//
	// try
	// {
	// stmt = con.createStatement();
	//
	// rs = stmt.executeQuery("SELECT * FROM pokemon");
	//
	// // get info on ResultSet
	// ResultSetMetaData rsmd = rs.getMetaData();
	//
	// // get number of columns
	// int numCols = rsmd.getColumnCount();
	//
	// System.out.println(" ");
	//
	// // display column names;
	// for (int i = 0; i < numCols; i++)
	// {
	// // get column name and print it
	//
	// System.out.printf("%-15s", rsmd.getColumnName(i+1));
	// }
	//
	// System.out.println(" ");
	//
	// while(rs.next())
	// {
	// // for display purposes get everything from Oracle
	// // as a string
	//
	// // simplified output formatting; truncation may occur
	//
	// pokemon_id = rs.getString("pokemon_id");
	// System.out.printf("%-10.10s", pokemon_id);
	//
	// pokemon_name = rs.getString("pokemon_name");
	// System.out.printf("%-20.20s", pokemon_name +'\n');
	//
	// // baddr = rs.getString("pokemon_addr");
	// // if (rs.wasNull())
	// // {
	// // System.out.printf("%-20.20s", " ");
	// // }
	// // else
	// // {
	// // System.out.printf("%-20.20s", baddr);
	// // }
	// //
	// // bcity = rs.getString("pokemon_city");
	// // System.out.printf("%-15.15s", bcity);
	// //
	// // bphone = rs.getString("pokemon_phone");
	// // if (rs.wasNull())
	// // {
	// // System.out.printf("%-15.15s\n", " ");
	// // }
	// // else
	// // {
	// // System.out.printf("%-15.15s\n", bphone);
	// // }
	// }
	// //
	// // close the statement;
	// // the ResultSet will also be closed
	// stmt.close();
	// }
	// catch (SQLException ex)
	// {
	// System.out.println("Message: " + ex.getMessage());
	// }
	// }
	// System.out.print("\nPokemon ID: ");
	// pokemon_id = Integer.parseInt(in.readLine());
	// ps.setInt(1, pokemon_id);
	//
	// System.out.print("\nPokemon Name: ");
	// pokemon_name = in.readLine();
	// ps.setString(2, pokemon_name);
	//
	// // System.out.print("\npokemon Address: ");
	// // baddr = in.readLine();
	// //
	// // if (baddr.length() == 0)
	// // {
	// // ps.setString(3, null);
	// // }
	// // else
	// // {
	// // ps.setString(3, baddr);
	// // }
	// //
	// // System.out.print("\npokemon City: ");
	// // bcity = in.readLine();
	// // ps.setString(4, bcity);
	// //
	// // System.out.print("\npokemon Phone: ");
	// // String phoneTemp = in.readLine();
	// // if (phoneTemp.length() == 0)
	// // {
	// // ps.setNull(5, java.sql.Types.INTEGER);
	// // }
	// // else
	// // {
	// // bphone = Integer.parseInt(phoneTemp);
	// // ps.setInt(5, bphone);
	// // }
	//
	// ps.executeUpdate();
	//
	// // commit work
	// con.commit();
	//
	// ps.close();
	// }
	// catch (IOException e)
	// {
	// System.out.println("IOException!");
	// }
	// catch (SQLException ex)
	// {
	// System.out.println("Message: " + ex.getMessage());
	// try
	// {
	// // undo the insert
	// con.rollback();
	// }
	// catch (SQLException ex2)
	// {
	// System.out.println("Message: " + ex2.getMessage());
	// System.exit(-1);
	// }
	// }
	// }

	/*
	 * deletes a pokemon
	 */

}
