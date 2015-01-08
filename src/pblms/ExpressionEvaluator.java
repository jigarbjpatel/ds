import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import java.util.Scanner;
public class ExpressionEvaluator{

	private static Map<Character,Integer> operatorToPrecedenceMap;
	public static void main(String[] args){
		//Given a expression consisting of + - / * and brackets, evaluate its value
		operatorToPrecedenceMap = new HashMap<Character,Integer>();
		operatorToPrecedenceMap.put('-', 1);
		operatorToPrecedenceMap.put('+', 1);
		operatorToPrecedenceMap.put('*', 2);
		operatorToPrecedenceMap.put('/', 2);		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		System.out.println(evaluate(input));
	}

	public static int evaluate(String input){
		int n = input.length();
		if(n == 0)
			return 0;
		int res = 0;
		
		//Combine Shunting yard and RPN logic to evaluate
		//One stack is for storing operators like in Shunting Yard algo
		LinkedList<Character> operators = new LinkedList<Character>();
		//Another stack is for storing numbers like in RPN evaluation algo
		LinkedList<Integer> operands = new LinkedList<Integer>();
		for(int i=0; i<n; i++){
			char currentChar = input.charAt(i);
			if(currentChar == '('){
				//( has highest priority so no need to check
				operators.push(currentChar);
			}else if(currentChar == ')'){
				//Pop all the operaots until we get (\
				Character operator = operators.pop();
				while(operator != '('){
					//Perfomr the operation on 2 operands - no validation check done
					Integer operand1 = operands.pop();
					Integer operand2 = operands.pop();
					Integer value = calculate(operand1,operand2,operator);
					operands.push(value);
					operator = operators.pop();
				}
			}else if(operatorToPrecedenceMap.get(currentChar) != null){
				//If the operator is of higher precedence than one in stack then push
				//Else pop the operator and perform operation
				while(operators.size() > 0 && operators.peek() != '(' && 
					operatorToPrecedenceMap.get(currentChar) <= operatorToPrecedenceMap.get(operators.peek())){
						Character operator = operators.pop();
						Integer operand1 = operands.pop();
						Integer operand2 = operands.pop();
						Integer value = calculate(operand1,operand2,operator);
						operands.push(value);
				}
				operators.push(currentChar);
			}else{
				//Get entire number (consisting of multipe digits)
				StringBuilder sb = new StringBuilder();
				sb.append(currentChar);
				i++;
				while(i < n && input.charAt(i) >= '0' && input.charAt(i) <= '9'){
					sb.append(input.charAt(i));		
					i++;			
				}
				i--; // i would be at operator and for loop will again increment i
				operands.push(Integer.parseInt(sb.toString())); 
			}			
		}
		while(operators.size() != 0){
			Integer value = calculate(operands.pop(), operands.pop(), operators.pop());
			operands.push(value);
		}
		res = operands.pop();
		return res;
	}
	private static Integer calculate(Integer operand1, Integer operand2, Character operator){
		//NOTE : We need to perform the operation in reverse order for left associative operators
		switch(operator){
			case '+':
				return operand2 + operand1;
			case '-':
				return operand2 - operand1;
			case '*':
				return operand2 * operand1;
			case '/':
				return operand2 / operand1;
			default:
				return 0;
		}
	}
}