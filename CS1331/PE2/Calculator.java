import java.util.Scanner;
public class Calculator{
  public static void main(String[] args){
    int firstNumber;
    int secondNumber;
    Scanner myScanner=new Scanner(System.in);
    System.out.print("Enter your first number: ");
    firstNumber=myScanner.nextInt();
    myScanner.nextLine();
    System.out.print("Enter your second number: ");
    secondNumber=myScanner.nextInt();
    myScanner.nextLine();
    System.out.println("Sum is "+(firstNumber+secondNumber));
    System.out.println("Difference is "+(firstNumber-secondNumber));
    System.out.println("Product is "+(firstNumber*secondNumber));
    System.out.println("Quotient is "+((double)firstNumber/(double)secondNumber));
  }
}
