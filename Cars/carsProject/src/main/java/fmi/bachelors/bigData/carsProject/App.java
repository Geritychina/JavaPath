package fmi.bachelors.bigData.carsProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class App extends JFrame
{
	public App() {
		initialize();
	}
	public void initialize() {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);

		String[] types = {"Not Selected","Avg Consumption/Brands","Car List"};
		final JLabel comboBoxLabel = new JLabel("Please, choose search type:");
		final JLabel byBrandLabel = new JLabel("Find Brand Name:");
		final JLabel byHPLabelfrom = new JLabel("HP From: ");
		final JLabel byHPLabelto = new JLabel("HP To:");
		final JLabel byMPGLabel = new JLabel("MPG Consumption:");

	    final JComboBox<String> comboBox = new JComboBox<String>(types);
	    
		final JTextField byBrand = new JTextField();
		final JTextField byHPfrom = new JTextField();
		final JTextField byHPto = new JTextField();
		final JTextField byMPG = new JTextField();
		JButton btn = new JButton("Find");
		
		add(jpanel);
		
		jpanel.add(comboBoxLabel);
		jpanel.add(comboBox);
		jpanel.add(byBrandLabel);
		jpanel.add(byBrand);
		jpanel.add(byHPLabelfrom);
		jpanel.add(byHPfrom);
		jpanel.add(byHPLabelto);
		jpanel.add(byHPto);
		jpanel.add(byMPGLabel);
		jpanel.add(byMPG);
		jpanel.add(btn);
		
		comboBoxLabel.setBounds(10,25,200,30);
		comboBox.setBounds(10,50,200,30);
		
		byBrandLabel.setBounds(10,75,200,30);
		byBrand.setBounds(10,100,200,30);
		
		byHPLabelfrom.setBounds(10,125,200,30);
		byHPfrom.setBounds(10,150,200,30);
		
		byHPLabelto.setBounds(10,175,200,30);
		byHPto.setBounds(10,200,200,30);
		
		byMPGLabel.setBounds(10,225,200,30);
		byMPG.setBounds(10,250,200,30);
		
		btn.setBounds(75,300,80,30);
		
		setSize(240,390);
		setVisible(true);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String byBrandName = byBrand.getText();
				String fromHP = byHPfrom.getText();
				String toHP = byHPto.getText();
				String consumeMPG = byMPG.getText();
				
				if(byBrandName.isEmpty()) {
					byBrandName = "all";
				}
				
				try {
					runHadoop(comboBox.getSelectedItem().toString(),byBrandName, fromHP, toHP, consumeMPG);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
				{
						if(comboBox.getSelectedIndex()==1) 
						{
							byHPfrom.setEnabled(false);
							byHPto.setEnabled(false);
							byMPG.setEnabled(false);
						}
					else {
						byHPfrom.setEnabled(true);
						byHPto.setEnabled(true);
						byMPG.setEnabled(true);
					}
				}
				});
	}
	
    public static void main( String[] args )
    {
        App form = new App();
    }
    
    
    public void runHadoop(String comboBox, String byBrandName, String fromHP, String toHP, String consumeMPG) throws IOException {
    	
    	
    	Configuration conf = new Configuration();
    	
    	JobConf job = new JobConf(conf, App.class);
    	
    	job.set("byBrandName", byBrandName);
    	job.set("fromHP", fromHP);
    	job.set("toHP", toHP);
    	job.set("consumeMPG", consumeMPG);
    	job.set("comboBox", comboBox);
    	
    	job.setMapperClass(CarsProjectMapper.class);
    	job.setReducerClass(CarsProjectReducer.class);
    	
    	job.setOutputKeyClass(Text.class);
    	job.setOutputValueClass(DoubleWritable.class);
    	
    	Path inputPath = new Path("hdfs://127.0.0.1:9000/input/cars.csv");
    	Path outputPath = new Path("hdfs://127.0.0.1:9000/output/result");
    	
    	FileInputFormat.setInputPaths(job, inputPath);
    	FileOutputFormat.setOutputPath(job, outputPath);
    	
    	FileSystem fs = FileSystem.get(URI.create("hdfs://127.0.0.1:9000"), conf);
    	
    	if(fs.exists(outputPath)) {
    		fs.delete(outputPath,true);
    	}
    	
    	JobClient.runJob(job);
    }
}