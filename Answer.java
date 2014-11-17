/*
 * Answer.java
 * Written by Amalia Safer (asafer@bu.edu)
 * This program represents an answer to a sorting hat
 * quiz question. Each answer has some values.
 * (Can be weighted)
 */ 

public class Answer {
    private String text; //text of the answer
    //private int value;
    private int[] values; //how many points the answer awards to each house
    
    public Answer(String text/*, int value*/,  int[] values) {
        this.text = text;
        //this.value = value;
        this.values = values;
    }
    
    //public int getVal() {
    //    return value;
    //}
    
    //returns values of the answer
    public int[] getVals() {
        return values;
    } 
    
    //returns the text of the answer
    public String getAns() {
        return text;
    }
}