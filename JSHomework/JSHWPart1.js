

//====================== fibonnacci function====================== 
function fibonacci(n){
    if (n == 0) {
        return 0;
    } else if(n == 1){
        return 1;
    }else {
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
  
//====================== sorting function====================== 
function sort(array){
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
function factorial(n){
    let ans = 1;
    for( let i = 1; i <= n; ++i) {
        ans *= i;
    }
    return ans;
}

//====================== factorial function using recursion====================== 
function factorialRecursive(n){
    if(n <= 1) {
        return 1;
    } else {
        return n * factorialRecursive(n - 1);
    }
}

//====================== rotate left function====================== 
function rotateLeft(arr,n){
    //int[] unOrderedArr = {1, 2, 3, 4, 5, 6, 7, 8};
    let RotateTimes = n;
    
    for (let i = 0; i < RotateTimes; i++) {
        for (let j = arr.length - 1; j > 0; j--) {
            let temp = arr[j];
            arr[j] = arr[j - 1];
            arr[j - 1] = temp;
        }
    }
    return arr
}