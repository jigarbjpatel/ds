//Converts the ternary expression given in string format to tree format
//Assumptions: valid input and each operand is represented by a single character in the string
public class TernaryExpressionParser{
    Integer position = 0;
    public static void main(String[] args){
        TernaryExpressionParser obj = new TernaryExpressionParser();
        System.out.println(obj.parse("a?b:c?d:e"));
    }
    
    Expression parse(String input){
        Node root = new Node(input.charAt(0));
        position = 1;
        helper(root, input);
        return new Expression(root);
    }    
    void helper(Node node, String input){

        if(position >= input.length() )
            return;
        
        if(input.charAt(position) == '?'){
            Node leftChild = new Node(input.charAt(position+1));
            node.left= leftChild;

            position += 2;
            if(position >= input.length() )
                return;
            if(input.charAt(position) == '?')
                helper(leftChild, input);            
        }
        if(input.charAt(position) == ':'){
            Node rightChild = new Node(input.charAt(position+1));
            node.right= rightChild;
            position += 2;
            if(position >= input.length() )
                return;
            if(input.charAt(position) == '?')
                helper(rightChild, input);            
        }   
    }  
}
class Node {
	String variableName;
	Node left, right;
    public Node(char variableName){
        this.variableName = String.valueOf(variableName);        
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{" + variableName);
        if(left != null)
            sb.append(" Left: " + left);
        if(right != null)
            sb.append(" Right: " + right);
        sb.append("}");
        return sb.toString();
    }
}

class Expression {
	Node root;
    public Expression(Node root){
        this.root = root;
    }
    @Override
    public String toString(){
        return root.toString();
    }
}
