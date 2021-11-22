package models;

import java.util.ArrayList;

public class Scale {
	
	private String name;
	private ArrayList<Double> answers;
	
	public Scale(String name) {
		answers = new ArrayList<Double>();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<Double> getAnswers() {
		return answers;
	}

	public double searchGradeAnswer(int answer) {
		return answers.get(answer-1);
	}
}
