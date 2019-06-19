package com.revature.strings;

import java.util.Arrays;

public class StringMethods {

	public void reverse(String str) {
		char[] phrase = str.toCharArray();
		for(int i = str.length()-1; i>=0; i--) {
			System.out.println(phrase[i]);
		}
		//return phrase;	
	}

	public boolean isPalindrome(String str) {
		int length = str.length();  //capturing the length of my string 
	    char[] phrase = str.toCharArray();  //converting my string into an array of characters
	    int c = 0;                          //counter for my loop
	    	while(c <= length/2) {       //keep looping until my counter is bigger than my length/2
	    		if(phrase[c] != phrase[length - 1 - c]) {  // if the character is not equal to the other one at the end return false
	    			return false;
	    		}
	    			c = c + 1;   //increment the counter to check the next character in the array
	    	}
	    	return true;  //when the loop is done checking return true if it is 
	}
}
