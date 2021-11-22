package structures;

import java.util.ArrayList;

public class Node{

    private static int count = 0;
    private int id;
    private String name;
    private double percentage;
    private ArrayList<Node> children;

    public Node(){
        children = new ArrayList<Node>();
    }

    public Node(String name, double percentage){
        this.id = count++;
        this.name = name;
        this.percentage = percentage;
        children = new ArrayList<Node>();
    }

    public void addChild(Node child){
        children.add(child);
    }

    public ArrayList<Node> getChildren(){
        return children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.name + " - " + this.percentage;
    }
}
