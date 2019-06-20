package com.revature.thursday;

import java.util.Scanner;

public class Thursday {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Thursday method = new Thursday();
		
		System.out.println("Enter your String");
		String str = sc.nextLine();
		System.out.println("Enter your SubString");
		String substr = sc.nextLine();
		System.out.println("Is the substring "+substr+" contian in the string "+str+":\n"+method.containsSubstring(str, substr));
		System.out.println("");
		System.out.println("Enter the number of rows of your triangle");
		int numRows = sc.nextInt();
		System.out.println("Number of blocks of Triangle:\n"+method.triangle(numRows));
		sc.close();
	}
	
	
	public boolean containsSubstring(String str, String substr) {
		if(substr.length()>str.length()) {  //Validation if the subString is bigger than the string then we cannot compare it
    		return false;
    	}else if(str.contains(substr)) { // Returns true if and only if this string contains the specified sequence of char values.
    		return true;
    	}else {                          // last case if substring is not contained on string it will return false.
    		return false;
    	}
	}
	
	public int triangle(int numRows) {
		if(numRows==0) {   // base case
			 return 0;
		}else if(numRows == 1){ //base case
			return 1; 
		}else {
			return numRows = numRows + triangle(numRows-1);  // recursion call the same method until we reach one of our base cases
		}
	}
}
