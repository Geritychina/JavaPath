package fmi.bachelors.bigData.carsProject;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class CarsProjectReducer extends MapReduceBase
implements Reducer<Text,DoubleWritable,Text,DoubleWritable>{

	@Override
	public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output,
			Reporter reporter) throws IOException {
		double sumMpg = 0;
		int count=0;
		
		while(values.hasNext()) {
			sumMpg += values.next().get();
			count++;
		}
		output.collect(key, new DoubleWritable(sumMpg/count));
		
	}

}
