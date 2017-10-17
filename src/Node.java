/**
 * Created by logan on 10/16/2017.
 */

import java.util.ArrayList;
import java.util.Stack;

public class Node {
    public String value;
    public ArrayList<Node> nodes;

    public Node(){
        this.nodes = new ArrayList<Node>();
        this.value = null;
    }

    public Node(String value){
        this.nodes = new ArrayList<Node>();
        this.value = value;
    }

    public boolean isEmpty() {
        return (nodes.isEmpty());
    }

    private boolean isOperator(char c){
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNumber(char c){
        if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5'
                || c == '6' || c == '7' || c == '8' || c == '9' || c == '0') {
            return true;
        }
        else{
            return false;
        }
    }

    public void infixPrint(){
        System.out.println(this.value);
        for(Node node : this.nodes){
            node.infixPrint();
        }
    }

    public Node CreateTree(String postfix){
        Stack<Node> st = new Stack();
        Node root = new Node();

        // Traverse through every character of input expression
        for (int i = 0; i < postfix.length(); i++) {
            if(postfix.charAt(i) == ' ' || postfix.charAt(i) == ')'){
                continue;
            }

            if(isOperator(postfix.charAt(i))){
                if(postfix.charAt(i+1) == ' '){ //if character is an operand and not a negative number
                    root.value = Character.toString(postfix.charAt(i));
                }
            }

            //if its a negative number, create a node with the number
            if(postfix.charAt(i) == '-' && isNumber(postfix.charAt(i+1))){
                i++;
                StringBuilder sb = new StringBuilder();
                sb.append('-');
                while(isNumber(postfix.charAt(i))){
                    sb.append(postfix.charAt(i));
                    i++;
                    if(i >= postfix.length()){break;}
                }
                //append the number onto the current nodes list
                root.nodes.add(new Node(sb.toString()));
                if(i >= postfix.length()){break;}
            }

            //if its a positive number, create a node with the number
            if(isNumber(postfix.charAt(i))){
                StringBuilder sb = new StringBuilder();
                while(isNumber(postfix.charAt(i))){
                    sb.append(postfix.charAt(i));
                    i++;
                    if(i >= postfix.length()){break;}
                }
                //append the number onto the current nodes list
                root.nodes.add(new Node(sb.toString()));
                if(i >= postfix.length()){break;}
            }

            //if bracket encounters, create a new node and create new subtree for it
            if(postfix.charAt(i) == '('){
                i++;
                //find where the expression ends
                int start = i;
                int end;
                int paracount = 1;
                while(paracount != 0){
                    if(postfix.charAt(i) == '('){
                        paracount++;
                    }
                    else if(postfix.charAt(i) == ')'){
                        paracount--;
                    }
                    i++;
                }
                end = i;
                root.nodes.add(CreateTree(postfix.substring(start,end)));
                if(i >= postfix.length()){break;}
            }
        }

        return root;
    }
}
