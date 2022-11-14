package fmi.bachelors.bigData.carsProject;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CarsProjectMapper extends MapReduceBase 
implements Mapper<LongWritable,Text,Text,DoubleWritable>{

	String byBrandName;
	String fromHP;
	String toHP;
	String consumeMPG;
	String comboBox;
	
	
	@Override
	public void configure(JobConf job) {
		byBrandName = job.get("byBrandName", "all").toLowerCase();
		fromHP= job.get("fromHP", "all");
		toHP= job.get("toHP", "all");
		consumeMPG= job.get("consumeMPG", "all");
		comboBox= job.get("comboBox", "all");
	}
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter)
			throws IOException {
				
		String[] array = value.toString().toLowerCase().split(";");
		
		if(byBrandName.equalsIgnoreCase("all")|| array[0].contains(byBrandName)) {
			try {
				
				String brandName = array[0];
				double mpg=Double.parseDouble(array[2]);
				double HP=Double.parseDouble(array[5]);
				double filterMpg=0;
				double filterFromHp=0;
				double filterToHp=0;
				
				if(!consumeMPG.equals("all")) {
					filterMpg=Double.parseDouble(consumeMPG);
				}
				if(!fromHP.equals("all")) {
					filterFromHp=Double.parseDouble(fromHP);
				}
				if(!toHP.equals("all")) {
					filterToHp=Double.parseDouble(toHP);
				}
				
				if(comboBox.equals("Avg Consumption/Brands")) {
					if(byBrandName.equals("all")||brandName.contains(byBrandName)) {
					
					output.collect(new Text(brandName), new DoubleWritable(mpg));
						}
					
				}else if(comboBox.equals("Car List")) {
					if(byBrandName.equals("all")||brandName.contains(byBrandName)
							&& (fromHP.equals("all")||filterFromHp<HP) 
							&& (toHP.equals("all")||filterToHp>HP)
							&& (consumeMPG.equals("all")||filterMpg>mpg)) {
						
						output.collect(new Text(brandName+" "+array[5]), new DoubleWritable(mpg));
							}
				}
				
			}catch(NumberFormatException e) {
				System.err.println(value.toString());
			}
		}
	}
}
