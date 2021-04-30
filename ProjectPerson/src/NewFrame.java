import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class NewFrame extends JFrame{
	
	JTabbedPane tab=new JTabbedPane();
	
	JPanel personP=new JPanel();
	JPanel carsP=new JPanel();
	JPanel rentaP=new JPanel();
	JPanel sprP=new JPanel();
	
	public NewFrame() {
		this.setSize(500, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tab.add(personP,"Клиенти");
		tab.add(carsP,"Коли");
		tab.add(rentaP,"Наем");
		tab.add(sprP,"Справка за клиенти");
		
		this.add(tab);
		
		this.setVisible(true);
	}

}
