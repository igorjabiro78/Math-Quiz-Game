package com.example.projecttwo;

import java.util.Random;

public class Question {
    private int firstnumber;
    private int secondnumber;
    private int answer;
    // there are 4 possible choice for user to pick from
    private int [] answerarray;

    // which of the 4 position is correct.0,1,2, or 3
    private int answerposition;

    // the maximum value of firstnumber or secondnumber
    private int upperlimit;

    // string output of the problem
    private String questionphrase;

    //generate a new random question
    public Question(int upperlimit){
        this.upperlimit=upperlimit;
        Random randomnumbermaker= new Random();
        //random operator
        String[] operators = {"+","-","/","*"};
        String op = operators[randomnumbermaker.nextInt(4)];

        this.firstnumber = randomnumbermaker.nextInt(upperlimit);
        this.secondnumber = randomnumbermaker.nextInt(upperlimit);
        switch(op){
            case "+":
                this.answer=this.firstnumber + this.secondnumber;
                break;
            case "-":
                this.answer=this.firstnumber - this.secondnumber;
                break;
            case "/":
                if(this.secondnumber!=0){
                    this.answer=this.firstnumber / this.secondnumber;}
                else if (this.secondnumber>this.firstnumber){
                    this.answer=this.secondnumber/this.firstnumber;
                }
                break;
            case "*":
                this.answer=this.firstnumber * this.secondnumber;
                break;
        }
        this.questionphrase ="Calculate : " + firstnumber + op + secondnumber + " =";

        this.answerposition = randomnumbermaker.nextInt(4);
        this.answerarray = new int[]{0,1,2,3};
// set different answers in different answerarray positions,
        this.answerarray[0]= answer +1;
        this.answerarray[1]= answer +10;
        this.answerarray[2]= answer -5;
        this.answerarray[3]= answer -2;

        this.answerarray = shuffleArray(this.answerarray );
        answerarray[answerposition]= answer;
    }

    private int[] shuffleArray(int[] array) {
        int index,temp;
        Random randomnumbergenerator =  new Random();

        for (int i=array.length-1; i>0; i--){
            index = randomnumbergenerator.nextInt(i+1);
            temp= array[index];
            array[index]= array[i];
            array[i]= temp;
        }
        return array;
    }

    public Question(int firstnumber, int secondnumber, int answer, int[] answerarray, int answerposition, int upperlimit, String questionphrase) {
        this.firstnumber = firstnumber;
        this.secondnumber = secondnumber;
        this.answer = answer;
        this.answerarray = answerarray;
        this.answerposition = answerposition;
        this.upperlimit = upperlimit;
        this.questionphrase = questionphrase;
    }

    public int getFirstnumber() {
        return firstnumber;
    }

    public void setFirstnumber(int firstnumber) {
        this.firstnumber = firstnumber;
    }

    public int getSecondnumber() {
        return secondnumber;
    }

    public void setSecondnumber(int secondnumber) {
        this.secondnumber = secondnumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerarray() {
        return answerarray;
    }

    public void setAnswerarray(int[] answerarray) {
        this.answerarray = answerarray;
    }

    public int getAnswerposition() {
        return answerposition;
    }

    public void setAnswerposition(int answerposition) {
        this.answerposition = answerposition;
    }

    public int getUpperlimit() {
        return upperlimit;
    }

    public void setUpperlimit(int upperlimit) {
        this.upperlimit = upperlimit;
    }

    public String getQuestionphrase() {
        return questionphrase;
    }

    public void setQuestionphrase(String questionphrase) {
        this.questionphrase = questionphrase;
    }
}
