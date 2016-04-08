import java.util.*;


//this class is the linked list if bucketnodes that stores all the collisions at that particular index in the array//
//extends Java's default linked list to inherit methods but override some methods and add extra methods to make it appropriate for this implementation//
public class Bucket extends LinkedList<BucketNode>{
  
  
  //override default contains method to check if word is present in that particular bucket//
  public BucketNode contains(String word){
    
    //for each BucketNode in the Bucket//
    for(BucketNode b : this){
      //if the word stored in the BucketNode is the word being checked for, return the node containing the word//
      if(b.getWord().equals(word)){
        return b;
      }
    }
    
    //if word is not found, return null//
    return null;
  }
  
  
  //Overrides default toString method to return all words in the bucket with a space in between them//
  public String toString(){
    
    //string containing the string representation//
    String s = "";
    
    //for each bucket node in the bucket, prints out the word contained in the bucket//
    for(BucketNode b : this){
      s+= b.getWord() + " ";
    }
    
    return s;
  }
  
  //method to increase frequency of bucketnode//
  public void increaseFreq(BucketNode toIncrease){
    toIncrease.setFreq(toIncrease.getFreq() + 1);
  }
  
  //prints each word in bucket followed by frequency//
  public void printBucket(){
    
    for(BucketNode b : this){
      System.out.println(b.getWord() + "" + b.getFreq());
    }
  }
  
  
  
}
  
  
      