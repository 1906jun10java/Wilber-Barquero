//========Object=========
let homework = () => {};

//====================== fibonnacci function====================== 
homework.fibonacci = (n) =>{
    if (n == 0) {
        return 0;
    } else if(n == 1){
        return 1;
    }else {
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
  
//====================== sorting function====================== 
homework.sort = (array) =>{
    let size=array.length;
    //Bubble sorting begins 
	//outer loop till last element-1
    for(let i=0;i<size-1;i++){
        for(let j=0;j<size-i-1;j++){

            //comparing elements for sorting
            if(array[j]>array[j+1]){
                
                // swap temp and arr[i]
                let temp = array[j];
                array[j] = array[j+1];
                array[j+1] = temp;
            }
        }
    }
    return array
}

//====================== factorial function====================== 
homework.factorial = (n) =>{
    let ans = 1;
    for( let i = 1; i <= n; ++i) {
        ans *= i;
    }
    return ans;
}

//====================== factorial function using recursion====================== 
homework.factorialRecursive = (n) =>{
    if(n <= 1) {
        return 1;
    } else {
        return n * factorialRecursive(n - 1);
    }
}

//====================== rotate left function====================== 
homework.rotateLeft = (array,n) =>{
    //int[] unOrderedArr = {1, 2, 3, 4, 5, 6, 7, 8};
    let RotateTimes = n;
    
    for (let i = 0; i < RotateTimes; i++) {
        for (let j = array.length - 1; j > 0; j--) {
            let temp = array[j];
            array[j] = array[j - 1];
            array[j - 1] = temp;
        }
    }
    return array
}

//====================== balance brackets function ====================== 
homework.balancedBrackets = (str) =>{
    //object contening properties, in this case my brackets
        let openBrackets = {
            curly: '{',
            square: '[',
            parentesi: '('
        };
        
        let closeBrakets = {
            curly: '}',
            square: ']',
            parentesi: ')'
        };
    
        //assigning the length of the string on a variable
        let end = str.length-1;
    
        //variables to store temp valus to evaluate each case
        let balBracket;
    
        //boolean variable that will return my answer
        let ans;
    
        //check which type of bracket and we set the closing one on temp variable in order to make the check 
        //on the next if statement otherwise returns false, it will also return flase if any of the brackets are 
        //closing brackets at the beginning
        for(let i=0; i <= end; i++){
            if(str.charAt(i) === openBrackets.curly){
                balBracket = closeBrakets.curly;
            }else if(str.charAt(i) === openBrackets.square){
                balBracket = closeBrakets.square;
            }else if(str.charAt(i) === openBrackets.parentesi){
                balBracket = closeBrakets.parentesi;
            }else{
                return false;
            }
    
            //checks the end of the char array to see if is the opposite bracket
            if(str.charAt(end) === balBracket){
                ans = true;
            }else{
                ans = false;
                break;
            }
            //reduces the lenght of the string to check the next one on the end
            end -= 1;
        }
        return ans;
}