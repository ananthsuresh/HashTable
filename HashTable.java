import java.io.*;
import java.util.*;


//This class is the actual HashTable//
public class HashTable{
  
  //number of slots in the table, start with two to the power of 10//
  private int tableSize = (int)Math.pow(2,10);
  
  //this is the array that represents the hashtable//
  private Bucket[] table = new Bucket[tableSize];
  
  //total number of words in the hash table//
  private double numWords;
  
  //initialises an empty hash table//
  public HashTable(){
    numWords = 0;
  }
  
  //getter and setter methods for private fields to ensure proper encapsulation//
  public double getNumWords(){
    return numWords;
  }
  
  public void setNumWords(double num){
    numWords = num;
  }
  
  public Bucket[] getTable(){
    return table;
  }
  
  public void setTable(Bucket[] table){
    this.table = table;
  }
  
  public int getSize(){
    return tableSize;
  }
  
  public void setSize(int size){
    tableSize = size;
  }
  
  
  //this method doubles the size of the hash table and rehashes all the elements accordingly//
  public void expandAndRehash(){
    //resets count of number of words to 0 when table is rehashed//
    this.setNumWords(0);
    //increases table size by a factor of 2//
    this.setSize(this.getSize() *2);
    //stores the old hash table//
    Bucket[] oldTable = this.getTable();
    //creates new table of appropriate size//
    Bucket[] newTable = new Bucket[this.getSize()];
    
    this.setTable(newTable);
    
    //loops throught all the slots in the old table//
    for(int i = 0; i < oldTable.length; i++){
      //this is the bucket in the current slot//
      Bucket bucket = oldTable[i];
      //loops through the buckets to add all collisions to the new list//
      if(bucket!=null){
        for(BucketNode b : bucket){
          this.addWord(b.getWord());
        }
      }
    }
  }
  
  
  //this method adds a word to the hash table//
  public void addWord(String word){
    

    //if word is empty string, do not add to hash table//
    if(word.equals("")){
      return;
    }
    
    //key stores the value of the word's hash after using the hash function on it, namely java's hashcode mod size of the table//
    int key = Math.abs(word.hashCode() % tableSize);
    
    //expands the table if key is bigger than array//
    while(key >= tableSize){
      this.expandAndRehash();
    }
    
    
    //expands the array and rehashes elements if load factor of hash table is 1 or more//
    //I chose a load factor of 1 as this means that the length of the collision list, on average is 1//
    //This leads to an average time complexity of O(1) for searching, insertion, and deletion, which  I believe is good as it is a good balance between time and space efficiency//
    if(getNumWords()/(getTable().length)>=1){
      this.expandAndRehash();
    }
    
    //bucket at position of key//
    Bucket bucket = table[key];
    //if the bucket is not empty//
    if(bucket != null){
      
      BucketNode toAdd = bucket.contains(word);
      //if the word to be added is already present, increases frequency//
      if(toAdd != null){
        bucket.increaseFreq(toAdd);
      }
      //else, adds the new word to the bucket//
      else{
        bucket.add(new BucketNode(word,1));
        //increments the total number of words in the hashtable//
        setNumWords(getNumWords()+1);
      }
    }
    
    //there is no word at that key value in the array, creates and adds a new bucket containing the word//
    else{
      table[key] = new Bucket();
      table[key].add(new BucketNode(word,1));
      setNumWords(getNumWords()+1);
    } 
  }
}















