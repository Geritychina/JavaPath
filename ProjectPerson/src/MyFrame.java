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

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyFrame extends JFrame{
	
	Connection conn=null;
	PreparedStatement state=null;
	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);
	int id = -1;
	ResultSet result = null;
	
	JPanel upPanel=new JPanel();
	JPanel midPanel=new JPanel();
	JPanel downPanel=new JPanel();
	
	JLabel fnameL=new JLabel("Име:");
	JLabel lnameL=new JLabel("Фамилия:");
	JLabel sexL=new JLabel("Пол:");
	JLabel ageL=new JLabel("Години:");
	JLabel salaryL=new JLabel("Заплата:");
	
	JTextField fnameTF=new JTextField();
	JTextField lnameTF=new JTextField();
	JTextField ageTF=new JTextField();
	JTextField salaryTF=new JTextField();
	
	String[] item= {"Мъж", "Жена"};
	JComboBox<String> sexCombo=new JComboBox<String>(item);
	
	JButton addBtn=new JButton("Добавяне");
	JButton deleteBtn=new JButton("Изтриване");
	JButton editBtn=new JButton("Редактиране");
	JButton searchBtn = new JButton("Търси");
	JComboBox<String> searchCombo = new JComboBox<String>();
	
	public MyFrame() {
		this.setSize(500, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(3, 1));
		
		//upPanel
		upPanel.setLayout(new GridLayout(5,2));
		
		upPanel.add(fnameL);
		upPanel.add(fnameTF);
		upPanel.add(lnameL);
		upPanel.add(lnameTF);
		upPanel.add(sexL);
		upPanel.add(sexCombo);
		upPanel.add(ageL);
		upPanel.add(ageTF);
		upPanel.add(salaryL);
		upPanel.add(salaryTF);
		
		this.add(upPanel);
		
		//midPanel
		midPanel.add(addBtn);
		midPanel.add(deleteBtn);
		midPanel.add(editBtn);
		midPanel.add(searchCombo);
		midPanel.add(searchBtn); 

		
		this.add(midPanel);
		addBtn.addActionListener(new AddAction());
		deleteBtn.addActionListener(new DeleteAction());
		DBHelper.fillCombo(searchCombo);
		searchBtn.addActionListener(new SearchAction());

		//downPanel
		this.add(downPanel);
		downPanel.add(scroller);
		scroller.setPreferredSize(new Dimension(450,160));
		table.setModel(DBHelper.getAllData("person"));
		table.addMouseListener(new TableListener());
		
		this.setVisible(true);
		
	}//end constructor
	
	class SearchAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedItem = searchCombo.getSelectedItem().toString();
			String[] items = selectedItem.split(" ");
			int itemID = Integer.parseInt(items[0]);
			
			conn = DBHelper.getConnection();
			String sql = "select * from person where id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, itemID);
				result = state.executeQuery();
				table.setModel(new MyModel(result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}//end actionPerformed
		
	}//end SearchAction 
	
		
	class DeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "delete from person where id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				table.setModel(DBHelper.getAllData("person"));
				DBHelper.fillCombo(searchCombo);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}//end DeleteAction
	
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			
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
		
	}//end TableListener
	
	public void clearForm() {
		fnameTF.setText("");
		lnameTF.setText("");
		ageTF.setText("");
		salaryTF.setText("");
	}// end clearForm
	
	class AddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBHelper.getConnection();
			String sql="insert into person values(null,?,?,?,?,?)";
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, fnameTF.getText());
				state.setString(2, lnameTF.getText());
				state.setString(3, sexCombo.getSelectedItem().toString());
				state.setByte(4, Byte.parseByte(ageTF.getText()));
				state.setFloat(5, Float.parseFloat(salaryTF.getText()));
				
				state.execute();
				table.setModel(DBHelper.getAllData("person"));
				DBHelper.fillCombo(searchCombo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			clearForm();
		}
		
	}// end class AddAction

}// end class MyFrame
