import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = selectStackType();

        // Leer la expresión infix del archivo
        String infixExpression = readExpressionFromFile("Datos.txt");

        // Convertir la expresión infix a postfix
        String postfixExpression = infixToPostfix(infixExpression);

        // Evaluar la expresión postfix y mostrar el resultado
        int result = evaluatePostfix(postfixExpression);
        System.out.println("El resultado de la expresión es: " + result);
    }

    private static Stack<Integer> selectStackType() {
        System.out.println("Ingrese el tipo de pila a utilizar (ArrayList, Vector o LinkedList): ");
        Scanner scanner = new Scanner(System.in);
        String stackType = scanner.nextLine();
    
        if (stackType.equalsIgnoreCase("ArrayList")) {
            return StackFactory.createStack("ArrayList");
        } else if (stackType.equalsIgnoreCase("Vector")) {
            return StackFactory.createStack("Vector");
        } else if (stackType.equalsIgnoreCase("LinkedList")) {
            // Seleccionar el tipo de implementación de lista
            return (Stack<Integer>) selectListImplementation();
        } else {
            throw new IllegalArgumentException("Tipo de pila inválido: " + stackType);
        }
    }
    
   private static Stack<Integer> selectListImplementation() {
    System.out.println("Ingrese el tipo de implementación de lista a utilizar (Simples, Dobles): ");
    Scanner scanner = new Scanner(System.in);
    String listType = scanner.nextLine();

    if (listType.equalsIgnoreCase("Simples")) {
        return new ArrayListStack<>();
    } else if (listType.equalsIgnoreCase("Dobles")) {
        return new DoublyLinkedListStack<>();
    } else {
        throw new IllegalArgumentException("Tipo de implementación de lista inválido: " + listType);
    }
}

    private static String readExpressionFromFile(String fileName) {
        StringBuilder expression = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int character;
            while ((character = br.read()) != -1) {
                char c = (char) character;
                if (Character.isWhitespace(c)) {
                    continue; // Ignorar espacios en blanco
                }
                expression.append(c);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return expression.toString();
    }
    public static String infixToPostfix(String infixExpression) {
        Stack<Character> operatorStack = StackFactory.createStack("Vector");
        StringBuilder postfix = new StringBuilder();
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
    
        for (char c : infixExpression.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
                postfix.append(' '); // Agregar un espacio después de los operandos
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                    postfix.append(' '); // Agregar un espacio después de los operadores
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // Descartar el paréntesis izquierdo
                } else {
                    throw new IllegalArgumentException("Error: paréntesis no balanceados");
                }
            } else { // c es un operador
                while (!operatorStack.isEmpty() && precedence.getOrDefault(operatorStack.peek(), 0) >= precedence.getOrDefault(c, 0)) {
                    postfix.append(operatorStack.pop());
                    postfix.append(' '); // Agregar un espacio después de los operadores
                }
                operatorStack.push(c);
            }
        }
    
        // Agregar los operadores restantes en la pila al resultado
        while (!operatorStack.isEmpty()) {
            char op = operatorStack.pop();
            if (op == '(') {
                throw new IllegalArgumentException("Error: paréntesis no balanceados");
            }
            postfix.append(op);
            postfix.append(' '); // Agregar un espacio después de los operadores
        }
    
        return postfix.toString().trim(); // Eliminar espacios en blanco al final
    }
    
    
    public static int evaluatePostfix(String postfixExpression) {
        Stack<Integer> operandStack = StackFactory.createStack("Vector");

        String[] tokens = postfixExpression.trim().split("\\s+");
        for (String token : tokens) {
            if (token.isEmpty()) {
                continue; // Ignorar espacios en blanco
            }

            if (Character.isDigit(token.charAt(0))) {
                operandStack.push(Integer.parseInt(token)); // Convertir el token a entero y agregarlo a la pila
            } else {
                if (operandStack.size() < 2) {
                    throw new IllegalStateException("Error: No hay suficientes operandos para la operación");
                }
                int operand2 = operandStack.pop();
                int operand1 = operandStack.pop();
                int result;
                switch (token) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        result = operand1 / operand2;
                        break;
                    default:
                        throw new IllegalArgumentException("Operador inválido: " + token);
                }
                operandStack.push(result);
            }
        }

        if (operandStack.size() != 1) {
            throw new IllegalStateException("Error: Operand stack contiene más de un valor al final de la evaluación");
        }

        return operandStack.pop();
    }
}