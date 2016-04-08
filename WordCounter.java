import java.util.*;
import java.io.*;

//this class has the method that counts the words as well as the main method//
public class WordCounter{
  
  public static String wordCount(String input_file, String output_file) throws Exception{
    
    //hash table that stores all the words in the input file//
    HashTable table = new HashTable();
    File outputFile = new File(output_file);
    File inputFile = new File(input_file);
    
    //if specified input file exists, tells user so//
    if(!inputFile.exists()){
      return "Input file does not exist";
    }
    //creates an output file of appropriate name if file does not already exist//
    if(!outputFile.exists()) {
      outputFile.createNewFile();
    }

    //creates writer and reader to read and write input and output//
    FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
    BufferedWriter bw = new BufferedWriter(fw);
    BufferedReader br = new BufferedReader(new FileReader(inputFile));
    
    //stores the current line being read//
    String currentLine = br.readLine();
    
    //while the input has not been read completely//
    while(currentLine!= null){
      //makes the input lowercase//
      currentLine = currentLine.toLowerCase();
      //splits into an array of all the words in the line by splitting at all delimiters//
      String [] words  = currentLine.split("[^0-9a-zA-Z]");
      
      //adds all the words in the line to hashtable//
      for(int i = 0; i < words.length; i++){
        table.addWord(words[i]);
      }
      
      //advances to next line//
      currentLine = br.readLine();
    }
    
    //writes all unique words present in hashtable to output along with their frequencies//
    for(int i = 0; i < table.getSize(); i++){
      if(table.getTable()[i] != null){
        Bucket currentBucket = table.getTable()[i];
        for(BucketNode b : currentBucket){
          bw.write("(" + b.getWord() + " " + String.valueOf(b.getFreq()) + ") ");
        }
      }
    }
    
    //calculates average collision length of hashtable//
    double averageCollisionLength = (double) table.getNumWords() / table.getSize();
    
    //closes reader and writer//
    bw.close();
    br.close();
    //return total number of words, hash table size, and average length of collision list//
    return ("OK; Total Words: " + (int)table.getNumWords() +", Hash table size: " + table.getSize() + ", Average length of collision list: " + String.valueOf(averageCollisionLength));
  }
  
  
  //main method that calls wordCount on the input arguments which are the names of the input and output files.
  public static void main(String[] args) throws Exception{
    
    //if there are 1 or no arguments, prompts user to enter the inputfile and output file name//
    if(args.length <2){
      System.out.print("Please enter input file name followed by output file name");
      return;
    }
    
    // if there are too many argyments, prompts user to only enter input and output file names as arguments//
    if(args.length > 2){
      System.out.print("Please enter only input file name followed by output file name");
      return;
    }
    
    //prints out the string returned by wordCount method//
    System.out.print( WordCounter.wordCount(args[0]+".txt",args[1]+".txt"));
  }
}
    
  
    
  
  
  
  