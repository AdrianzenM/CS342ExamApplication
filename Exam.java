/*
Exam Class

Methods:

Exam(text: String)
Exam(Scanner)
print(): void
addQuestion(question: Question)
reorderQuestions(): void
getAnswerFromStudent(position: int): void
getValue(): double
reportQuestionValue(): void
save(PrintWriter): void
restoreStudentAnswers(Scanner): void

*/

//Edward Nava
//enava
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.io.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Exam {
	//Name of the exam
	private String text;
	private ArrayList<Question> questions;

	//CONSTRUCTOR
	public Exam(String input){
		text = input;
		questions = new ArrayList<Question>();
	}
	//Constructs Exam via Input File
	//First Line is the Exam Title
	//Blank Lines in between Quesitons
	//Type of question first
	//MaxValue second
	//Question Type Differentiations

	//Creates an exam based on the scanner input
	public Exam(Scanner sc){
		if(sc.hasNextLine()){
			String line;
			line = sc.nextLine();
			text = line;
		}
		questions = new ArrayList<Question>();
	}




	//Adds a question to the question list
	public void addQuestion(Question q){
		questions.add(q);
	}


	//Goes throught the list and prints out Questions
	//Since each kind of question has a print method,
	//Multiple choice questions will print all choices
	//while short answer questions only print out the question
	public void print(){
		int i = 1;
		for(Question q: questions){
			System.out.println("\n*****************QUESTION "+ i +"*********************\n");
			q.print();
			i++;
		}
	}

	//This method produces a table of the values of each Question on the Exam and the total.
	public void reportQuestionValue(){
		double total = 0.0;
		int i = 0;

		for(i = 0; i < questions.size(); i++){
			Question q = questions.get(i);
			System.out.println("\n\n------ Question "+ (i+1) + " is Worth " + q.getQuestionValue() + " points ----------\n");
			total += q.getQuestionValue();
		}
		System.out.println("TOTAL POINTS POSSIBLE:" + total);
	}

	//If the position parameter is negative, then all MC questions get their answers reordered,
	//provided that Question is a MC type question
	public void reorderMCAnswers(int position){
		//If the position is greater than 0
		if(position > 0){
			//Make a shallow copy of the question in the position
			Question quest = questions.get(position);
			if(quest instanceof MCQuestion){
				MCQuestion q = (MCQuestion)quest;
				//Reorder the answers
				q.reorderAnswers();
				//Sets the reordered answers to the question
				questions.set(position, q);
			}
		}
		else{
			position = 0;
			//Go through the array of questions
			for(Question q: questions){
				//Find each instance of a Multiple Choice Question
				Question quest = questions.get(position);
				if(q instanceof MCQuestion){
					//Make a shallow copy of the question in the position
					MCQuestion q1 = (MCQuestion)quest;
					//Reorder the answers
					q1.reorderAnswers();
					//Sets the reordered answers to the question
					questions.set(position, q1);
					position++;
				}
			}
		}
	}
	public void reorderQuestions(){
		Collections.shuffle(questions);
	}

	public void getAnswerFromStudent(int position){

	}

	public double getValue(){
		//Goes through the array of Questions and tally's up
		//the score
		double value = 0.0;
		for(Question q: questions){
			value += q.getValue();
		}
		return value;
	}


	//This will save the entire test with the answers and Questions
	//into the print writer passed through
	public void save(PrintWriter p){
		//First, save the exam name in the first Line
		p.println(this.text);
		//Go through all the questions and save them
		//Should work like this since all questions are specified in
		//their type
		for(Question q: questions){
			q.save(p);
		}

	}






}
