import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ICalculator {

    int add(int x, int y);

    float divide(int x, int y) throws RuntimeException;
}


public class Calculator implements ICalculator{
    @Override
    public int add(int x, int y) {

        return x+y;
    }
    @Override
    public float divide(int x, int y) throws RuntimeException {
        if (y == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (float) x / y;
    }
    /* Implement your calculator class here*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        int First_number = scanner.nextInt();
        System.out.println("Enter the Operation: ");
        char operation = scanner.next().charAt(0);
        System.out.println("Enter the second number: ");
        int second_number = scanner.nextInt();

        ICalculator calculator = new Calculator();
        if(operation == '+'){
            int result = calculator.add(First_number, second_number);
            System.out.println("The result is "+result);
        }
        else if(operation == '/'){
                try{
                    float result2 = calculator.divide(First_number, second_number);
                    System.out.println("The result is "+result2);
                }
                catch (Exception e){
                    System.out.println("Error");
                }
        }else{
            System.out.println("Error");
        }

    }
}

