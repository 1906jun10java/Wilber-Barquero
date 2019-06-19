package com.revature.strings;

public class StringRunner {
	
	public static void main(String[] args) {
		String str = "barquero";
		
		StringMethods strmethods = new StringMethods();
		
		System.out.println("String before reverse: "+str);
		strmethods.reverse(str);
		System.out.println("The String is a Palindrome: "+strmethods.isPalindrome(str));
	}
}
