//bucketnode stores the word and the frequency that it has appeared//
public class BucketNode{
  
  //word contained in the node//
  private String word = "";
  
  //frequency that word is present in document//
  private int frequency = 0;
  
  //empty constructor for BucketNode//
  public BucketNode(){
  }
  
  //constrcutor that creates new node with input word and frequency//
  public BucketNode(String word, int frequency){
    this.word = word;
    this.frequency = frequency;
  }
  
  //getter method for word//
  public String getWord(){
    return word;
  }
  
  //getter method for frequency//
  public int getFreq(){
    return frequency;
  }
  
  //setter method for frequency//
  public void setFreq(int freq){
    frequency = freq;
  }
  
}