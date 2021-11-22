package models;

import structures.Node;
import structures.Tree;

import java.util.ArrayList;
import java.util.Comparator;

public class Manager {

	private ArrayList<Scale> scales;
	private double maxGrade;
	private Tree treeParts;
	private ArrayList<Node> percentages;

	public Manager(double maxGrade) {
		scales = new ArrayList<>();
		this.maxGrade = maxGrade;
		treeParts = new Tree();
		percentages = null;
	}

	public void addPart(int search, String name, double percentage){
		Node actual = treeParts.search(search);
		treeParts.add(partComparator,actual, name, percentage);
	}

	private Comparator<Node> partComparator = (element, anotherElement) -> {
		return (element.getId() == anotherElement.getId())? 0: -1;
	};

	public void extractLeaves(){
		percentages = getTreeParts().print();
	}

	public ArrayList<Double> calculateGrades(ArrayList<String> scales, ArrayList<Integer> answers){
		ArrayList<Double> grades = new ArrayList<>();
		for (int i = 0; i < answers.size(); i++) {
			grades.add(this.calculateGrade(scales.get(i), answers.get(i)));
		}
		return grades;
	}

	private double calculateGrade(String nameScale, int answer) {
		Scale sc = this.searchScale(nameScale);
		double result = 0;
		if (sc != null) {
			result = this.maxGrade * sc.searchGradeAnswer(answer);
		}
		return result;
	}

	public ArrayList<Double> calculatePercentagesGrade(ArrayList<Double> grades){
		ArrayList<Double> result = new ArrayList<>();
		for (int i = 0; i < grades.size(); i++) {
			result.add(this.calculatePercentagesGrade(grades.get(i), i));
		}
		return result;
	}

	private double calculatePercentagesGrade(double grade, int position){
		return Math.round(grade * percentages.get(position).getPercentage() * 100.0)/100.0;
	}
	
	private Scale searchScale(String nameScale) {
		for (int i = 0; i < scales.size(); i++) {
			if (scales.get(i).getName().equals(nameScale)) {
				return scales.get(i);
			}
		}
		return null;
	}

	public double calculateAverage(ArrayList<Double> percentagesGrade){
		double result = 0;
		for (Double part: percentagesGrade) {
			result += part;
		}
		return Math.round(result);
	}

	public Tree getTreeParts() {
		return treeParts;
	}

	public void setScales(ArrayList<Scale> scales) {
		this.scales = scales;
	}
}
