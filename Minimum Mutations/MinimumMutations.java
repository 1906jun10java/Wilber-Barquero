import java.util.ArrayList;
import java.util.List;

public class MinimumMutations {
	
	
	//given 3 things: start, end, bank = must use a data structure in order to store a the bank strings
	//my method must be receiving 3 parameters
	
	//Validation to check if my string or bank is null using short circuit
	public int minMutation(String start, String end, String[] bank) {
		if(bank == null || start == null || end == null) {
			return 0;
		}
		
		//creating my bank of strings on an arraylist
		//augmented for loop passing my bank string one by one to be added on my ArrayList
		List<String> bankStr = new ArrayList<>(); 
	    for(String str : bank) {  
	    	bankStr.add(str);
	    }
	    
	    
	    //recursive function in order to return -1 if the string is invalid or return the count if is valid
	    int count = minMutation(start, end, bank, 0, bankStr); 
	    return count == 0 ? -1 : count;    
	}
	
	//checking the length
	 public int minMutation(String start, String end, String[] bank, int count, List<String> bankStrings) {
	        List<Integer> location = new ArrayList<>(); //list for indices of the char array
	        int length = start.length();   //getting the length of the start word 
	        for(int i = 0; i < length; ++i) {
	            if(start.charAt(i) != end.charAt(i)) { //if one of the char is not equal add the character to the list location
	                location.add(i);
	            }
	        }
	        
	        String strTemp = "";
	        int currentCount = 0;
	        
	        //passing location list indices one by one
	        //need to fill out our temp string which will compare with the bank of strings
	        //I will be passing the location list where my char that was different is store
	        //to reconstruct the string I have to get the substring ill pass three different strings to compare it with my bank
	        for(int i : location) {  //passing list indices one by one
	            strTemp = start.substring(0, i) + "" + end.charAt(i) + "" + start.substring(i+1, length); //constructing different types
	            if(bankStrings.contains(strTemp)) { // checks if my substring is contained in my bank
	                currentCount = 1 + minMutation(strTemp, end, bank, count, bankStrings); //recursive to keep count of mutations
	            }
	        }
	        return currentCount;
	    }
}
