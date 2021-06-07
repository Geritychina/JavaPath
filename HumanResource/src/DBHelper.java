
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComboBox;



public class DBHelper {
	
	static Connection con=null;
	static PreparedStatement state = null;
	static MyModel model = null;
	static ResultSet result = null;

	
static void fillCombo(JComboBox<String> combo) {
		
		con = getConnection();
		String sql = "select id,fname from employee";
		try {
			state = con.prepareStatement(sql);
			result = state.executeQuery();   //combo.setModel(aModel);
			combo.removeAllItems();
			while(result.next()) {
				String item = result.getObject(1).toString() + " " + result.getObject(2);
				combo.addItem(item);
			}//end while
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end fillCombo


static MyModel getAllData(String tableName) {
	
	con = getConnection();
	String sql = "select * from " + tableName;
	try {
		state = con.prepareStatement(sql);
		result = state.executeQuery();
		model = new MyModel(result);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return model;
	
}//end
	

static MyModel getAllDataContract() {
	
	con = getConnection();
	String sql = "SELECT contract_id, datecontract, typecontract, fname FROM Contracts C JOIN Employee E ON C.employee_id = E.ID";
			
	try {
		state = con.prepareStatement(sql);
		result = state.executeQuery();
		model = new MyModel(result);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return model;
	
}//end


static MyModel getAllDataEmployee() {
	
	con = getConnection();
	String sql = "SELECT id, fname, birthday, email, town, typeposition FROM Employee E JOIN Positions P ON E.position_id = P.position_id";
			
	try {
		state = con.prepareStatement(sql);
		result = state.executeQuery();
		model = new MyModel(result);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return model;
	
}//end



	static Connection getConnection() {
		
		try {
			Class.forName("org.h2.Driver");
			String url = "";
			String user = "";
			String pass = "";
			File read = new File("C:\\Users\\USER\\Desktop\\Config.txt");
			Scanner scan = new Scanner(read);
			while(scan.hasNextLine()) {
				url = scan.nextLine().trim();
				user = scan.nextLine().trim();
				pass = scan.nextLine().trim();
				break;
			}
			
			con=DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return con;
	}//end getConnection

}
