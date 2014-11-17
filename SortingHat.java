/*
 * SortingHat.java
 * Code written by Amalia Safer (asafer@bu.edu)
 * Based on the Pottermore sorting hat quiz.
 * Questions credited to J.K. Rowling
 */ 

import java.util.*;
import java.io.*;

public class SortingHat {
    private static int raven = 0; //keeps track of ravenclaw points
    private static int gryff = 0; //keeps track of gryffindor points
    private static int slyth = 0; //keeps track of slytherin points
    private static int puff = 0; //keeps track of hufflepuff points
    
    //these arrays of questions represent all possible sorting hat
    //questions, organized by which number question they are
    private static Question[] q1 = new Question[4];
    private static Question[] q2 = new Question[5];
    private static Question[] q3 = new Question[3];
    private static Question[] q4 = new Question[3];
    private static Question[] q5 = new Question[6];
    private static Question[] q6 = new Question[3];
    private static Question[] q7 = new Question[3];
    
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
        Scanner stall = new Scanner(System.in);
        System.out.println("Sorry, you're a muggle.");
        System.out.println("Press enter.");
        String nothing = stall.nextLine();
        System.out.println("Just kidding.");
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
    
    //chooses a random question within a question number's options
    private static int chooseQ(Question[] questions) {
        Random rand = new Random();
        int qNum = rand.nextInt(questions.length);
        return qNum;
    }
    
    //prints the welcome message
    private static void welcome() {
        System.out.println("Welcome to the Sorting Hat!\nI hope you're ready to be sorted!\n");
        System.out.println("Please type in the NUMBER of the answer you'd like to pick for each question.");
        System.out.println("Here we go...");
    }
    
    public static void main(String[] args) 
        throws FileNotFoundException {
        welcome();
        Scanner input = new Scanner(new File("all.txt"));
        
        //fills all question arrays from file
        qArrayFill(q1, input);
        qArrayFill(q2, input);
        qArrayFill(q3, input);
        qArrayFill(q4, input);
        qArrayFill(q5, input);
        qArrayFill(q6, input);
        qArrayFill(q7, input);
        
        //runs through all questions
        System.out.print("Question 1/7: ");
        ask(q1[chooseQ(q1)]);
        System.out.print("Question 2/7: ");
        ask(q2[chooseQ(q2)]);
        System.out.print("Question 3/7: ");
        ask(q3[chooseQ(q3)]);
        System.out.print("Question 4/7: ");
        ask(q4[chooseQ(q4)]);
        System.out.print("Question 5/7: ");
        ask(q5[chooseQ(q5)]);
        System.out.print("Question 6/7: ");
        ask(q6[chooseQ(q6)]);
        System.out.print("Question 7/7: ");
        ask(q7[chooseQ(q7)]);
        
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
    
    
    