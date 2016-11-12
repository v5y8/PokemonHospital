package DB;

import java.io.IOException;
import java.sql.*;


//singleton pattern DAO for accessing data.
public class TrainerDAO {
	private Connection con;
	private static TrainerDAO instance;

	private TrainerDAO() throws SQLException{
		con = DB_connector.getConnection();
	};

	public static TrainerDAO getInstance()throws SQLException{
		if (instance ==null){
			instance = new TrainerDAO();
		}
		return instance;
	};



	/**
	 * inserts a trainer into table
	 * @param tid = trainer ID in int
	 * @param name = trainer name in String
	 * 
	 */ 
	private void insertTrainer(int tid, String name) throws SQLException
	{

		int trainer_id = tid;
		String trainer_name = name;
		PreparedStatement ps = con.prepareStatement("INSERT INTO trainer VALUES (?,?)");

		ps.setInt(1, trainer_id);
		ps.setString(2, trainer_name);

		ps.executeUpdate();

		// commit work 
		con.commit();

		ps.close();

	}


	/**
	 * deletes a trainer from table
	 * @param tid = trainer ID in int
	 * Naomi: con.rollback(); may come in handy for catching the SQLException.
	 */ 
	private void deletetrainer(int tid) throws SQLException
	{
		int trainer_id = tid;
		PreparedStatement  ps;

			ps = con.prepareStatement("DELETE FROM trainer WHERE trainer_id = ?");
			ps.setInt(1, trainer_id);
			con.commit();
			ps.close();
		
			
	}


	/**
	 * updates the name of a trainer
	 * @param tid = trainer_Id in int
	 * @param name = new name to be updated to.
	 */ 
	private void updateTrainer(int tid, String name) throws SQLException
	{
		int trainer_id = tid;
		String trainer_name = name;
		PreparedStatement  ps;

			ps = con.prepareStatement("UPDATE trainer SET trainer_name = ? WHERE trainer_id = ?");
			ps.setInt(2, trainer_id);
			ps.setString(1, trainer_name);

//			TODO: naomi look at this code; may help with controller.
			//int rowCount = ps.executeUpdate();
//			if (rowCount == 0)
//			{
//				System.out.println("\ntrainer " + trainer_id + " does not exist!");
//			}

			con.commit();

			ps.close();
		
	}


	/*
	 * display information about trainers
	 */ 
//	private void showtrainer(int tid, )
//	{
//		String     trainer_id;
//		String     trainer_name;
//		Statement  stmt;
//		ResultSet  rs;
//
//		try
//		{
//			stmt = con.createStatement();
//
//			rs = stmt.executeQuery("SELECT * FROM trainer");
//
//			// get info on ResultSet
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			// get number of columns
//			int numCols = rsmd.getColumnCount();
//
//			System.out.println(" ");
//
//			// display column names;
//			for (int i = 0; i < numCols; i++)
//			{
//				// get column name and print it
//
//				System.out.printf("%-15s", rsmd.getColumnName(i+1));    
//			}
//
//			System.out.println(" ");
//
//			while(rs.next())
//			{
//				// for display purposes get everything from Oracle 
//				// as a string
//
//				// simplified output formatting; truncation may occur
//
//				trainer_id = rs.getString("trainer_id");
//				System.out.printf("%-10.10s", trainer_id);
//
//				trainer_name = rs.getString("trainer_name");
//				System.out.printf("%-20.20s", trainer_name +'\n');
//
//				//		      baddr = rs.getString("trainer_addr");
//				//		      if (rs.wasNull())
//				//		      {
//				//		    	  System.out.printf("%-20.20s", " ");
//				//	              }
//				//		      else
//				//		      {
//				//		    	  System.out.printf("%-20.20s", baddr);
//				//		      }
//				//
//				//		      bcity = rs.getString("trainer_city");
//				//		      System.out.printf("%-15.15s", bcity);
//				//
//				//		      bphone = rs.getString("trainer_phone");
//				//		      if (rs.wasNull())
//				//		      {
//				//		    	  System.out.printf("%-15.15s\n", " ");
//				//	              }
//				//		      else
//				//		      {
//				//		    	  System.out.printf("%-15.15s\n", bphone);
//				//		      }      
//			}
//			// 
//			// close the statement; 
//			// the ResultSet will also be closed
//			stmt.close();
//		}
//		catch (SQLException ex)
//		{
//			System.out.println("Message: " + ex.getMessage());
//		}	
//	}
//	System.out.print("\nTrainer ID: ");
//	trainer_id = Integer.parseInt(in.readLine());
//	ps.setInt(1, trainer_id);
//
//	System.out.print("\nTrainer Name: ");
//	trainer_name = in.readLine();
//	ps.setString(2, trainer_name);
//
//	//	  System.out.print("\ntrainer Address: ");
//	//	  baddr = in.readLine();
//	//	  
//	//	  if (baddr.length() == 0)
//	//          {
//	//	      ps.setString(3, null);
//	//	  }
//	//	  else
//	//	  {
//	//	      ps.setString(3, baddr);
//	//	  }
//	//	 
//	//	  System.out.print("\ntrainer City: ");
//	//	  bcity = in.readLine();
//	//	  ps.setString(4, bcity);
//	//
//	//	  System.out.print("\ntrainer Phone: ");
//	//	  String phoneTemp = in.readLine();
//	//	  if (phoneTemp.length() == 0)
//	//	  {
//	//	      ps.setNull(5, java.sql.Types.INTEGER);
//	//	  }
//	//	  else
//	//	  {
//	//	      bphone = Integer.parseInt(phoneTemp);
//	//	      ps.setInt(5, bphone);
//	//	  }
//
//	ps.executeUpdate();
//
//	// commit work 
//	con.commit();
//
//	ps.close();
//}
//catch (IOException e)
//{
//	System.out.println("IOException!");
//}
//catch (SQLException ex)
//{
//	System.out.println("Message: " + ex.getMessage());
//	try 
//	{
//		// undo the insert
//		con.rollback();	
//	}
//	catch (SQLException ex2)
//	{
//		System.out.println("Message: " + ex2.getMessage());
//		System.exit(-1);
//	}
//}
//}


/*
 * deletes a trainer
 */ 

}
