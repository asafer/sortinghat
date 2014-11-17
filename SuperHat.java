/*
 * SuperHat.java
 * Code written by Amalia Safer (asafer@bu.edu)
 * Based on the Pottermore sorting hat quiz.
 * Questions credited to J.K. Rowling
 */ 

import java.util.*;
import java.io.*;

public class SuperHat {
    private static int raven = 0; //keeps track of ravenclaw points
    private static int gryff = 0; //keeps track of gryffindor points
    private static int slyth = 0; //keeps track of slytherin points
    private static int puff = 0; //keeps track of hufflepuff points
    
    //gets one question from the file
    private static Question importQ(Scanner qs) {
        int qNum = 0;
        int numAns = 0;
        if (qs.hasNextInt()) {
            qNum = qs.nextInt(); //imports number of question
        }
        if (qs.hasNextInt()) {
            numAns = qs.nextInt(); //imports number of answers
        }
        qs.nextLine();
        String qText = qs.nextLine(); //imports question text
        String[] ansText = new String[numAns];
        //int[] ansVals = new int[numAns];
        Answer[] answers = new Answer[numAns];
        for (int i = 0; i < numAns; i++) { //for each answer...
            int numNums = qs.nextInt(); //imports number of vals for the answer
            int[] ansVal = new int[numNums];
            for (int j = 0; j < numNums; j++) {
                ansVal[j] = qs.nextInt(); //imports all vals for the answer
            }
            //ansVals[i] = qs.nextInt();
            ansText[i] = qs.nextLine(); //imports answer text
            answers[i] = new Answer(ansText[i], ansVal); //puts all answer info into answer array
        }
        Question q = new Question(qNum, qText, numAns, /*ansText, ansVals*/answers); 
        return q; //returns all information as a Question
    }
    
    //fills one question array (for example, question 1)
    private static void qArrayFill(Question[] q, Scanner qs) {
        for (int i = 0; i < q.length; i++) {
            q[i] = importQ(qs);
        }
    }
    
    //asks the user a question, then increments house points accordingly
    private static void ask(Question q) {
        //int val = q.runQ();
        int[] vals = q.runQ();
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] == 0) {
                raven++;
            } else if (vals[i] == 1) { //change to val when using single val
                gryff++;
            } else if (vals[i] == 2) {
                slyth++;
            } else if (vals[i] == 3) {
                puff++;
            }
        }
    }
    
        //calculates which house you're in!
    private static String calcResult() {
        if (raven > gryff && raven > slyth && raven > puff) {
            return " Ravenclaw";
        } else if (gryff > raven && gryff > slyth && gryff > puff) {
            return " Gryffindor";
        } else if (slyth > raven && slyth > gryff && slyth > puff) {
            return " Slytherin";
        } else if (puff > raven && puff > gryff && puff > slyth) {
            return " Hufflepuff";
        } else { //if no one house has more points than all the others
            String stallChoice = hatstall();
            return " " + stallChoice;
        }
    }
    
    //if 2+ houses have the same number of points,
    //runs the hatstall method to determine the final house
    private static String hatstall() {
        System.out.println("HATSTALL!");
        if (raven == gryff) {
            return stallHelper("Ravenclaw", "Gryffindor");
        } else if (raven == slyth) {
            return stallHelper("Ravenclaw", "Slytherin");
        } else if (raven == puff) {
            return stallHelper("Ravenclaw", "Hufflepuff");
        } else if (gryff == slyth) {
            return stallHelper("Gryffindor", "Slytherin");
        } else if (gryff == puff) {
            return stallHelper("Gryffindor", "Hufflepuff");
        } else {
            return stallHelper("Slytherin", "Hufflepuff");
        }
    }
    
    //helper method for hatstall
    //prints 2 house options and gives the user
    //the choice
    private static String stallHelper(String h1, String h2) {
        Scanner stall = new Scanner(System.in);
        while(true) {
            System.out.println("Please print (exactly as you see it) the house of your choice:");
            System.out.println(h1);
            System.out.println(h2);
            String choice = stall.next();
            
            if (choice.equals(h1)) {
                return h1;
            } else if (choice.equals(h2)) {
                return h2;
            } else {
                System.out.println("That wasn't an option! Try again.");
            }
        }
    }
    
    //prints the welcome message
    private static void welcome() {
        System.out.println("Welcome to the Sorting Hat!\nI hope you're ready to be sorted!\n");
        System.out.println("Please type in the NUMBER of the answer you'd like to pick for each question.");
        System.out.println("Here we go...");
    }
    
    private static Question[] all = new Question[27];
    
    public static void main(String[] args) 
        throws FileNotFoundException {
        welcome();
        Scanner input = new Scanner(new File("all.txt"));
        
        //fills all question arrays from file
        qArrayFill(all, input);
        
        for (int i = 0; i < all.length; i++) {
            System.out.print("Question " + (i+1) + "/27: ");
            ask(all[i]);
        }
        
        //prints result!
        String result = "Congratulations! You are a" + calcResult() + "!";
        System.out.println(result); 
        //for debugging
        Scanner console = new Scanner(System.in);
        System.out.println("View Breakdown? (y/n)");
        String choice = console.next();
        if (choice.equals("y")) {
            System.out.println("Gryffindor final points: " + gryff);
            System.out.println("Ravenclaw final points: " + raven);
            System.out.println("Hufflepuff final points: " + puff);
            System.out.println("Slytherin final points: " + slyth);
        }
    }
}