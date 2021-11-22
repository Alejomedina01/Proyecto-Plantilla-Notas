package structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tree {

    protected Node root;

    public Tree(){
        root = new Node("Uno", 1);
    }

    public Node getRoot(){
        return root;
    }

    public Node add(Comparator<Node> comparator, Node father, String name, double percentage){
        Node newChild = new Node(name, percentage);
        int index = Collections.binarySearch(father.getChildren(), newChild, comparator);
        if(index >= 0){
            return father.getChildren().get(index);
        }
        father.addChild(newChild);
        return newChild;
    }
    
    public Node search(int id){
        return search(root, id);
    }

    private Node search(Node actual, int id) {
        if(actual.getId() == id){
           return actual;
        }
        for (Node child: actual.getChildren()){
            Node result = search(child, id);
            if(result != null){
                return result;
            }
        }
        return null;
    }

    public ArrayList<Node> searchAll(int id){
        ArrayList<Node> results = new ArrayList<>();
        search(root, id, results);
        return  results;
    }

    private void search(Node actual, int data, ArrayList<Node> results) {
        if(actual.getId() == data){
             results.add(actual);
        }
        for (Node child: actual.getChildren()){
           search(child, data, results);
        }
    }

    public Node searchFather(int data){
        return getFather(root, data);
    }

    private Node getFather(Node base, int data) {
        for (Node child : base.getChildren()) {
            if(child.getId() == data){
                return base;
            }else{
                Node father = getFather(child,data);
                if(father != null){
                    return father;
                }
            }
        }
        return null;
    }

    public ArrayList<Node> print() {
        ArrayList<Node> result = new ArrayList<>();
        print(root, result);
        return result;
    }

    private ArrayList<Node> print(Node actual, ArrayList<Node> result) {
        for (Node child: actual.getChildren()){
            print(child, result);
            if (child.getChildren().size() == 0) {
                result.add(child);
            }
        }
        return result;
    }



}
