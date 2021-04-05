package br.com.uff.word_count;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCount {

     public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
    	 if(args.length < 4) {
    		 String msg = "Favor informar os seguintes parâmetros:\n";
    		 msg += "args[0]: Valor N do ngram (e.g, 2 para um bigram)\n";
    		 msg += "args[1]: A contagem mínina para ser incluído na saída (e.g., só desejo mostrar ngrams que apareçam mais de 5 vezes)\n";
    		 msg += "args[2]: O diretório onde os arquivos se encontram\n";
    		 msg += "args[3]: O diretório onde os arquivos de saída serão gravados\n";
    		 throw new IllegalArgumentException(msg);
    	 }
    	 
        // Captura o parâmetros passados após o nome da Classe driver.
    	String nGrams    = args[0];
        String nMinGrams = args[1];
        Path inputPath   = new Path(args[2]);
        Path outputDir   = new Path(args[3]);
 
        // Criar uma configuração e Registra os parâmetros que serão importantes para o Mapper e Reducer
        Configuration conf = new Configuration(true);
        conf.set("nGrams", nGrams);
        conf.set("nMinGrams", nMinGrams);
        conf.set("fs.defaultFS", "file:///");
        
        // Criar o job para submissão da task
        Job job = new Job(conf, "Count - N° Grams");
        job.setJarByClass(WordCount.class);
 
        // Definir as classes para Mapper e Reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        job.setNumReduceTasks(1);
 
        // Definir as chaves e os valores
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
 
        // Entradas
        FileInputFormat.addInputPath(job, inputPath);
        job.setInputFormatClass(TextInputFormat.class);
 
        // Saídas
        FileOutputFormat.setOutputPath(job, outputDir);
        job.setOutputFormatClass(TextOutputFormat.class);
 
        // Excluir saída se existir
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(outputDir)) fs.delete(outputDir, true);
 
        // Executar job
        int code = job.waitForCompletion(true) ? 0 : 1;
        System.exit(code);
 
    }
}