import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array1 = new int[100]; //declare an array with a capacity of 100 elements
        fillArray(array1); //call fillArray function on array1
        printArray(array1); //call printArray function on array1
    }

    static void fillArray(int[] array){ // method used for filling array with random ints
        Random rand = new Random(); //create new random object
        for (int i = 0; i < array.length; i++){ //iterate as long as i < array capacity
            array[i] = rand.nextInt(100); //random int between 0 and 99
        }
    }

    static void printArray(int[] array){ // method used for printing the array contents
        for (int j : array) { //enhanced for loop to iterate through entire array
            System.out.println(j); //print j
        }
    }

    static void merge(int arr[], int[] left, int right[]){
        int i = 0, indexLeft = 0, indexRight = 0;

        //while the index of left array < length of left array and index of right array is < length of right array.
        // while there are elements in left and right
        while(indexLeft < left.length && indexRight < right.length){
            if(left[indexLeft] < right[indexRight]){

            }
        }


    }

    static void mergeSort(int[] arr){
        //this method handles the division of arrays by two. it will recursively make divisions until arr.length <= 1.
        if (arr.length <= 1){ //if array length is <= 1
            return; //base case
        }

        int midpoint = arr.length/2; //mid-point is half the capacity of array
        int[] left = new int[midpoint]; //left subarray will go from arr[0] to midpoint (arr.length / 2)
        int[] right = new int[arr.length - midpoint]; //right subarray will go from mid-point to array.length

        int j = 0; //use for right array
        for(int i = 0; i < arr.length; i++){ //iterate as long as i is less than arr.length
            if(i < midpoint){ // if i is less than the midpoint
                left[i] = arr[i]; //set left[i] = arr[i]. add this value to the left subarray
            } else {
                right[i] = arr[i]; //set right[i] = arr[i]. add this value to right subarray
                j++; //add 1 to j
            }
        }
        mergeSort(left); //recursively call mergeSort on left subArray to once again make another division
        mergeSort(right); //recursively call mergeSort on right subArray to once again make another division
        merge(arr, left, right); //call merge on arr, left, and right arrays
    }

}