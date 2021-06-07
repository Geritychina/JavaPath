import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;



public class MainFrame extends JFrame {
	
	Connection con=null;
	PreparedStatement state=null;
	//IMP For Db!
	
	JTable tableEmp = new JTable();
	JTable tablePos = new JTable();
	JTable tableContr = new JTable();
	JTable tableQuery = new JTable();
	//Tables
	JScrollPane scrollerEmp = new JScrollPane(tableEmp);//For Employee
	JScrollPane scrollerPos = new JScrollPane(tablePos);//For Position
	JScrollPane scrollerContr = new JScrollPane(tableContr);//For Contract
	JScrollPane scrollerQuery = new JScrollPane(tableQuery); //For Query
	//Scrollers
	
	int id = -1;
	ResultSet result = null;
	//Properties
    JTabbedPane tab=new JTabbedPane();//Tabbedpane
    
	//Employee
	JPanel employeeP=new JPanel();
	JPanel topPanelEmp = new JPanel();
	JPanel midPanelEmp = new JPanel();
	JPanel downPanelEmp = new JPanel();
	//Emp Panels
	JLabel nameL = new JLabel("Name");
	JLabel birthDayL = new JLabel("Birthday");
	JLabel emailL = new JLabel("E-mail");
	JLabel townL = new JLabel("Town");
	JLabel positionL = new JLabel("Position");
	//JLabels for Employee
	JTextField nameTF=new JTextField();
	JTextField birthDayTF=new JTextField("yyyy-mm-dd");
	JTextField emailTF=new JTextField();
	JTextField townTF=new JTextField();
	JComboBox<String> positionCombo = new JComboBox<String>();
	//Text Fields for Employee
	//Employee Buttons
    JButton addBtnEmp=new JButton("Insert");
	JButton deleteBtnEmp=new JButton("Delete");
	JButton editBtnEmp=new JButton("Edit");
	JButton searchBtnEmp = new JButton("Search By Name");
	JButton refreshBtnEmp = new JButton("Refresh");
	//JComboBox<String> searchComboEmp = new JComboBox<String>();
	
	JPanel positionP=new JPanel();
	JPanel topPanelPos = new JPanel();
	JPanel midPanelPos = new JPanel();
	JPanel downPanelPos = new JPanel();
	//Positon panels
	JLabel positionNameL = new JLabel("Position");
	JTextField positionNameTF = new JTextField();
	//Text Fields for Positions
	JButton addBtnPos = new JButton("Insert");
	JButton deleteBtnPos = new JButton("Delete");
	JButton editBtnPos = new JButton("Edit");
	JButton searchBtnPos = new JButton("Search by Position");
	JButton refreshBtnPos = new JButton("Refresh");
	// Contract Panels
	JPanel contractP=new JPanel();
	JPanel topPanelContr = new JPanel();
	JPanel midPanelContr = new JPanel();
	JPanel downPanelContr = new JPanel();
	//Contr Labels
	JLabel TypeContrL = new JLabel("Type Contract");
	JLabel dateContrL = new JLabel("Date of Contract");
	JLabel EmploContrL = new JLabel("Employee");
	JTextField TypeContrTF = new JTextField();
	JTextField dateContrTF = new JTextField("yyyy-mm-dd");
	
	JComboBox<String> EmpContrCombo = new JComboBox<String>();
	//Text Fields Contr
	JButton addBtnContr = new JButton("Insert");
	JButton deleteBtnContr = new JButton("Delete");
	JButton editBtnContr = new JButton("Edit");
	JButton searchBtnContr = new JButton("Search By TypeContract");
	JButton refreshBtnContr = new JButton("Refresh");
	//Contract Buttons
	
	JPanel queryP = new JPanel();
	JPanel topPanelQuery = new JPanel();
	JPanel midPanelQ = new JPanel();
	JPanel downPanelQ = new JPanel();
	//Query Panel
	//Query Labels & Text Fields
	JLabel nameEmpQueryL = new JLabel("Name");
	JLabel typeContractQueryL = new JLabel("Type Contract");
	JTextField nameEmpQueryTF = new JTextField();
	JTextField typeContQueryTF = new JTextField();
	JButton searchQuerryBtn = new JButton("Seach");
	
	public MainFrame() {
		this.setSize(600, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tab.add(employeeP,"Employees");
		tab.add(positionP,"Positions");
		tab.add(contractP,"Contracts");
		tab.add(queryP,"Queries");
		//Tabs 4 
		this.add(tab);
		
		//Employee 
		employeeP.setLayout(new GridLayout(3,1));
		topPanelEmp.setLayout(new GridLayout(6,2));
		topPanelEmp.add(nameL);
		topPanelEmp.add(nameTF);
		topPanelEmp.add(birthDayL);
		topPanelEmp.add(birthDayTF);
		topPanelEmp.add(emailL);
		topPanelEmp.add(emailTF);
		topPanelEmp.add(townL);
		topPanelEmp.add(townTF);
		topPanelEmp.add(positionL);
		topPanelEmp.add(positionCombo);
		//midPanelEmp Buttons
		midPanelEmp.add(addBtnEmp);
		midPanelEmp.add(deleteBtnEmp);
		midPanelEmp.add(editBtnEmp);
		midPanelEmp.add(searchBtnEmp);
		midPanelEmp.add(refreshBtnEmp);

		addBtnEmp.addActionListener(new AddActionEmployee());
		deleteBtnEmp.addActionListener(new DeleteActionEmployee());
		editBtnEmp.addActionListener(new EditActionEmployee());
		searchBtnEmp.addActionListener(new SearchActionEmployee());
		refreshBtnEmp.addActionListener(new RefreshActionEmp());
		
		DBHelper.fillCombo(EmpContrCombo);
		
		//downPanelEmp with table
		downPanelEmp.add(scrollerEmp);
		scrollerEmp.setPreferredSize(new Dimension(550,160));
		tableEmp.setModel(DBHelper.getAllDataEmployee());
		tableEmp.addMouseListener(new TableListenerEmp());
		employeeP.add(topPanelEmp);
		employeeP.add(midPanelEmp);
		employeeP.add(downPanelEmp);
		refreshComboPos();
		
		//Position
		positionP.setLayout(new GridLayout(3,1));
		topPanelPos.setLayout(new GridLayout(1,2));
		
		topPanelPos.add(positionNameL);
		topPanelPos.add(positionNameTF);
		//midPanelPos
		midPanelPos.add(addBtnPos);
		midPanelPos.add(deleteBtnPos);
		midPanelPos.add(editBtnPos);
		midPanelPos.add(searchBtnPos);
		midPanelPos.add(refreshBtnPos);
		
		addBtnPos.addActionListener(new AddActionPosition());
		deleteBtnPos.addActionListener(new DeleteActionPosition());
		editBtnPos.addActionListener(new EditActionPosition());
		searchBtnPos.addActionListener(new SearchActionPosition());
		refreshBtnPos.addActionListener(new RefreshActionPosition());
		//downPanelPos
		downPanelPos.add(scrollerPos);
		scrollerPos.setPreferredSize(new Dimension(450,160));
		tablePos.setModel(DBHelper.getAllData("positions"));
		tablePos.addMouseListener(new TableListenerPos());
		positionP.add(topPanelPos);
		positionP.add(midPanelPos);
		positionP.add(downPanelPos);
		
		//Contract
		contractP.setLayout(new GridLayout(3,1));
		topPanelContr.setLayout(new GridLayout(3,2));
		topPanelContr.add(dateContrL);
		topPanelContr.add(dateContrTF);
		topPanelContr.add(TypeContrL);
		topPanelContr.add(TypeContrTF);
		topPanelContr.add(EmploContrL);
		topPanelContr.add(EmpContrCombo);
		//MidPanelContr
		midPanelContr.add(addBtnContr);
		midPanelContr.add(deleteBtnContr);
		midPanelContr.add(editBtnContr);
		midPanelContr.add(searchBtnContr);
		midPanelContr.add(refreshBtnContr);
	
		//DownPaneContr
		downPanelContr.add(scrollerContr);
		scrollerContr.setPreferredSize(new Dimension(450,160));
		tableContr.setModel(DBHelper.getAllDataContract());
		tableContr.addMouseListener(new TableListenerContr());
		contractP.add(topPanelContr);
		contractP.add(midPanelContr);
		contractP.add(downPanelContr);
		addBtnContr.addActionListener(new AddActionContract());
		deleteBtnContr.addActionListener(new DeleteActionContract());
		editBtnContr.addActionListener(new EditActionContract());
		searchBtnContr.addActionListener(new SearchActionContract());
		refreshBtnContr.addActionListener(new RefreshActionContract());
		refreshEmployeeCombo();
		
		//Query - Справка 
		queryP.setLayout(new GridLayout(3,1));
		topPanelQuery.setLayout(new GridLayout(2,2));
		topPanelQuery.add(nameEmpQueryL);
		topPanelQuery.add(nameEmpQueryTF);
		topPanelQuery.add(typeContractQueryL);
		topPanelQuery.add(typeContQueryTF);
		
		//Mid
		midPanelQ.add(searchQuerryBtn);
		scrollerQuery.setPreferredSize(new Dimension(450,160));
		downPanelQ.add(scrollerQuery);
		queryP.add(topPanelQuery);
		queryP.add(midPanelQ);
		queryP.add(downPanelQ);
		searchQuerryBtn.addActionListener(new QueryAction());
		
		this.setVisible(true);
		
	}//end of constructor
	
	//ClassActions for Employee
	
	class SearchActionEmployee implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			con = DBHelper.getConnection();
			String sql = "SELECT id, fname, birthday, email, town, typeposition FROM Employee E JOIN Positions P ON E.position_id = P.position_id WHERE Fname=?";
					
			try {
				state = con.prepareStatement(sql);
				state.setString(1, nameTF.getText());
				result = state.executeQuery();
				tableEmp.setModel(new MyModel(result));
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}//end actionPerformed
		
	}//end Търсене по айди, име,рд,мейл,град и тип договор(показва и имена при комбобокса)
    
	class RefreshActionEmp implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			tableEmp.setModel(DBHelper.getAllDataEmployee());
		}
		
	}// Рефреш за емп
	public void refreshComboPos() {
		positionCombo.removeAllItems();
		String sql = "select position_id, typeposition from positions";
		con = DBHelper.getConnection();
		try {
			state = con.prepareStatement(sql);
			result = state.executeQuery();
			String item = "";
			while(result.next()) {
				item = result.getObject(1).toString() + "." + result.getObject(2).toString();
				positionCombo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//Рефреш за комбобокс-позиции
	
	class DeleteActionEmployee implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			con = DBHelper.getConnection();
			String sql = "delete from employee where id=?";
			try {
				state = con.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				tableEmp.setModel(DBHelper.getAllDataEmployee());
				tableContr.setModel(DBHelper.getAllDataContract());
				DBHelper.fillCombo(EmpContrCombo);
				clearFormEmp();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}//end Изтриване емп
	
	class TableListenerEmp implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tableEmp.getSelectedRow();
			id = Integer.parseInt(tableEmp.getValueAt(row, 0).toString());
			nameTF.setText(tableEmp.getValueAt(row, 1).toString());
			birthDayTF.setText(tableEmp.getValueAt(row, 2).toString().substring(0,10));
			emailTF.setText(tableEmp.getValueAt(row, 3).toString());
			townTF.setText(tableEmp.getValueAt(row, 4).toString());
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}//end Емп таблица
	
	public void clearFormEmp() {
		nameTF.setText("");
		birthDayTF.setText("");
		emailTF.setText("");
		townTF.setText("");
	}// end clearFormEmp
	
	class AddActionEmployee implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String selectItem = positionCombo.getSelectedItem().toString();
			//String[] item = selectItem.split(".");
			//int position_ID = Integer.parseInt(item[0]); 
			//System.out.println(position_ID);
			con=DBHelper.getConnection();
			String sql="insert into employee values(null,?,?,?,?,?)";
			try {
				state=con.prepareStatement(sql);
				state.setString(1, nameTF.getText());
				state.setDate(2, java.sql.Date.valueOf(birthDayTF.getText()));
				state.setString(3, emailTF.getText());
				state.setString(4, townTF.getText());
				state.setInt(5, Integer.parseInt(selectItem.substring(0, selectItem.indexOf('.'))));
				
				state.execute();
				tableEmp.setModel(DBHelper.getAllDataEmployee());
				DBHelper.fillCombo(EmpContrCombo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					state.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			clearFormEmp();
		}
	}// end class AddActionEmployee

	class EditActionEmployee implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectItem = positionCombo.getSelectedItem().toString();
			con=DBHelper.getConnection();
			String sql="update employee set fname=?,birthday=?,email=?,town=?,position_id=? where id=?";
			try {
				state=con.prepareStatement(sql);
				state.setString(1, nameTF.getText());
				state.setDate(2, java.sql.Date.valueOf(birthDayTF.getText()));
				state.setString(3, emailTF.getText());
				state.setString(4, townTF.getText());
				state.setInt(5, Integer.parseInt(selectItem.substring(0, selectItem.indexOf('.'))));
		        state.setInt(6, id);
				
				state.execute();
				id = -1;
				tableEmp.setModel(DBHelper.getAllDataEmployee());
				tableContr.setModel(DBHelper.getAllDataContract());
				DBHelper.fillCombo(EmpContrCombo);
				clearFormEmp();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	//End Emp
	
	//Класове за Позиции
	
	class AddActionPosition implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			con=DBHelper.getConnection();
			String sql="insert into positions values(null,?)";
			try {
				state=con.prepareStatement(sql);
				state.setString(1, positionNameTF.getText());
		
				state.execute();
				
				tablePos.setModel(DBHelper.getAllData("positions"));
				clearFormPos();
				refreshComboPos();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		}
		
	}
	
	class DeleteActionPosition implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			con = DBHelper.getConnection();
			String sql = "delete from positions where position_id=?";
			try {
				state = con.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				tablePos.setModel(DBHelper.getAllData("positions"));
				tableEmp.setModel(DBHelper.getAllDataEmployee());
				clearFormPos();
				refreshComboPos();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	class EditActionPosition implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			con=DBHelper.getConnection();
			String sql="update positions set typeposition=? where position_id=?";
			try {
				state=con.prepareStatement(sql);
				state.setString(1,positionNameTF.getText());
				state.setInt(2, id);
		
				state.execute();
				
				tablePos.setModel(DBHelper.getAllData("positions"));
				tableEmp.setModel(DBHelper.getAllDataEmployee());
				clearFormPos();
				refreshComboPos();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	class SearchActionPosition implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			con = DBHelper.getConnection();
			String sql = "select * from positions where typeposition=? ";
			try {
				state = con.prepareStatement(sql);
				state.setString(1, positionNameTF.getText());
				result = state.executeQuery();
				tablePos.setModel(new MyModel(result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

	class RefreshActionPosition implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			tablePos.setModel(DBHelper.getAllData("positions"));
			
		}
		
	}
	class TableListenerPos implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tablePos.getSelectedRow();
			id = Integer.parseInt(tablePos.getValueAt(row, 0).toString());
			positionNameTF.setText(tablePos.getValueAt(row,1).toString());
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void clearFormPos() {
		positionNameTF.setText("");
	}
	//End Pos
	
	
	//Класове за договори
	class TableListenerContr implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tableContr.getSelectedRow();
			id = Integer.parseInt(tableContr.getValueAt(row, 0).toString());
			dateContrTF.setText(tableContr.getValueAt(row, 1).toString());
			TypeContrTF.setText(tableContr.getValueAt(row, 2).toString());
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	class AddActionContract implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectItem = EmpContrCombo.getSelectedItem().toString();
			con=DBHelper.getConnection();
			String sql="insert into contracts values(null,?,?,?)";
			try {
				state=con.prepareStatement(sql);
				state.setDate(1, java.sql.Date.valueOf(dateContrTF.getText()));
				state.setString(2, TypeContrTF.getText());
				state.setInt(3, Integer.parseInt(selectItem.substring(0, selectItem.indexOf('.'))));
				
				state.execute();
				tableContr.setModel(DBHelper.getAllDataContract());
			    clearFormContr();
			    refreshEmployeeCombo();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}finally {
				try {
					state.close();
					con.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		}
		
	}
}
	
	class DeleteActionContract implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			con = DBHelper.getConnection();
			String sql = "delete from contracts where contract_id=?";
			try {
				state = con.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				tableContr.setModel(DBHelper.getAllDataContract());
				tableEmp.setModel(DBHelper.getAllDataEmployee());
			    clearFormContr();
			    refreshEmployeeCombo();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	class EditActionContract implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String selectItem = EmpContrCombo.getSelectedItem().toString();
			con=DBHelper.getConnection();
			String sql="update contracts set datecontract=?,typecontract=?,employee_id=? where contract_id=?";
			try {
				state=con.prepareStatement(sql);
				state.setDate(1, java.sql.Date.valueOf(dateContrTF.getText()));
				state.setString(2, TypeContrTF.getText());
				state.setInt(3, Integer.parseInt(selectItem.substring(0, selectItem.indexOf('.'))));
		        state.setInt(4, id);
				
				state.execute();
				id = -1;
				tableContr.setModel(DBHelper.getAllDataContract());
				tableEmp.setModel(DBHelper.getAllDataEmployee());
			    clearFormContr();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	class SearchActionContract implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			con = DBHelper.getConnection();
			String sql = "SELECT contract_id, datecontract, typecontract, fname FROM Contracts C JOIN Employee E ON C.employee_id = E.ID WHERE typecontract=?";
					
			try {
				state = con.prepareStatement(sql);
				state.setString(1, TypeContrTF.getText());
				result = state.executeQuery();
				tableContr.setModel(new MyModel(result));
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}//Търсене по айди, дата и тип на договор(да показва имената на работника)

	class RefreshActionContract implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			tableContr.setModel(DBHelper.getAllDataContract());
		}
		
	}
	public void clearFormContr() {
		TypeContrTF.setText("");
		dateContrTF.setText("");
	}
	
	public void refreshEmployeeCombo() {
		EmpContrCombo.removeAllItems(); //Small e
		String sql = "select id, fname from employee";
		con = DBHelper.getConnection();
		try {
			state = con.prepareStatement(sql);
			result = state.executeQuery();
			String item = "";
			while(result.next()) {
				item = result.getObject(1).toString() + "." + result.getObject(2).toString();
				EmpContrCombo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//Рефреша за договори на работниците(комбобокса)
	
	
	//Query Търсене
	class QueryAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			con = DBHelper.getConnection();
			String sql="SELECT FName,email,town,typecontract,datecontract FROM Contracts C JOIN Employee E ON C.employee_id = E.ID WHERE FName = ? And typecontract = ?;";
			
			try {
				state=con.prepareStatement(sql);
				state.setString(1, nameEmpQueryTF.getText());
				state.setString(2, typeContQueryTF.getText());
				result=state.executeQuery();
				tableQuery.setModel(new MyModel(result));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			nameEmpQueryTF.setText("");
			typeContQueryTF.setText("");
		}
		
	}
	
	
}
