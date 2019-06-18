import java.util.Scanner;

public class FizzBuzzRunner {
	public static void main(String[] args) {
		
		//Instantiating object for scanner
		Scanner sc = new Scanner(System.in);
		
		//Instantiating object fizzbuzz
		FizzBuzz fizzbuzz = new FizzBuzz();
		
		//calling method fizzBuzzBasic
		fizzbuzz.fizzBuzzBasic();
		
		System.out.println("Please enter the number from where you want to start counting");
		int m = sc.nextInt();
		System.out.println("Please enter the number where you want to stop counting counting");
		int n = sc.nextInt();
		System.out.println("Please enter the number of the multiples you will use");
		
		//capturing the size of the array to validate the condition that the arry must the same size
		int size = sc.nextInt();
		int [] numbers = new int[size];
		for (int i = 0; i < numbers.length; i++)
	    {
	        System.out.println("Please enter the "+ i +" multiple");
	        numbers[i] = sc.nextInt();
	    }
		
		String [] terms = new String[size];
		for (int i = 0; i < terms.length; i++)
	    {
	        System.out.println("Please enter "+i+ "word");
	        terms[i] = sc.nextLine();
	    }
		
		fizzbuzz.fizzBuzzAdvanced(m, n, numbers, terms);
	}
}
