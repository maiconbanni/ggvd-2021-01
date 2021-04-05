/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uff.word_count;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final IntWritable ONE = new IntWritable(1);
    private final Text word = new Text();
    
    @SuppressWarnings("rawtypes")
	List wordList = new ArrayList();
    
    @SuppressWarnings("unchecked")
	@Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer strToken = new StringTokenizer(value.toString(), " ");
        while (strToken.hasMoreTokens()) wordList.add(strToken.nextToken());
    }

	@Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
		String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
		int nGrams = Integer.parseInt(context.getConfiguration().get("nGrams"));
		StringBuffer str = new StringBuffer("");
		for (int index = 0; index <= wordList.size() - nGrams; index++) {
			int pos = index;
			for(int n = 0; n < nGrams; n++) {
				str = n > 0 ? str.append(" ").append(wordList.get(pos)) : str.append("NGram: ").append(wordList.get(pos));
				pos++;
			}
			str = str.append(" - Filename: ").append(fileName).append(" - Total: ");
			word.set(str.toString());
			str = new StringBuffer("");
			context.write(word, ONE);
		}
    }
}
