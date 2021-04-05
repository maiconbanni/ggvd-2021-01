package br.com.uff.word_count;

import java.io.IOException;
 
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
 
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text text, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
    	int nMinGrams = Integer.parseInt(context.getConfiguration().get("nMinGrams"));
    	int sum = 0;        
    	for (IntWritable value : values) sum += value.get();        
    	if(sum >= nMinGrams) context.write(text, new IntWritable(sum));
    }
}