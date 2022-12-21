import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int capacity = getUserInputForCapacity(); //call getUserInputForCapacity method to get the capacity of array1.

        int[] array1 = new int[capacity]; //declare an array with a capacity of (capacity) elements
        int[] array2 = new int[capacity];
        fillArray(array1); //call fillArray function on array1
        fillArray(array2); //call fillArray function on array2
        System.out.println("Array 1");
        printArray(array1); //call printArray function on array1
        System.out.println();
        System.out.println("Array 2");
        printArray(array2); //call printArray function on array1

        System.out.println("\n");//two empty nl

        long mergeSortStartTime = System.nanoTime(); //start time for mergeSort method call
        mergeSort(array1); //call mergeSort function on array1
        long mergeSortEndTime = System.nanoTime(); // end time for mergeSort method call
        long mergeSortDuration = mergeSortEndTime - mergeSortStartTime;
        System.out.println("Merge sort elapsed time: " + mergeSortDuration + " nanoseconds, which is "
                + TimeUnit.NANOSECONDS.toMillis(mergeSortDuration) + " milliseconds."); // print elapsed time in nanoseconds and milliseconds
        printArray(array1); // call printArray function on array1

        System.out.println("\n");//two empty nl

        long insertionSortStartTime = System.nanoTime(); //start time for insertionSort method call
        insertionSort(array1); //call insertionSort function on array1
        long insertionSortEndTime = System.nanoTime(); // end time for insertionSort method call
        long insertionSortDuration = insertionSortEndTime - insertionSortStartTime;
        System.out.println("Insertion sort sort elapsed time: " + insertionSortDuration + " nanoseconds, which is "
                + TimeUnit.NANOSECONDS.toMillis(insertionSortDuration) + " milliseconds."); // print elapsed time in nanoseconds and milliseconds
        printArray(array1); // call printArray function on array1
    }

    static int getUserInputForCapacity(){ //this method simply gets the user input used for the capacity of the array.
        int userInput;
        Scanner in = new Scanner(System.in);
        while(true) { // start while loop
            System.out.print("Input an integer that will be used for the capacity of an array:"); //tell user to input an integer
            try { //start try block
                if (in.hasNextInt()) { //if next user input is an integer
                    userInput = in.nextInt(); //userInput = int that user inputs
                    if(userInput < 0){ //if userInput is less than 0,
                        throw new IndexOutOfBoundsException("Out of bounds."); //throw IndexOutOfBoundsException
                    }
                    return userInput; //otherwise, return user input
                } else { //otherwise, throw InputMismatchException, meaning the user input something aside from an int
                    throw new InputMismatchException("Incorrect input.");
                }
            } catch (InputMismatchException er){ //catch InputMismatchException
                System.out.println(er.getMessage());
                in.next(); //prevent infinite loop
            } catch (IndexOutOfBoundsException er) { //catch IndexOutOfBoundsException
                System.out.println(er.getMessage());
            }
        }

    }

    static void fillArray(int[] array){ // utility method used for filling array with random ints
        Random rand = new Random(); //create new random object
        for (int i = 0; i < array.length; i++){ //iterate as long as i < array capacity
            array[i] = rand.nextInt(100001); //random int between 0 and length of array + 1 (inclusive)
        }
    }

    static void printArray(int[] array){ // utility method used for printing the array contents
        for (int j : array) { //enhanced for loop to iterate through entire array
            System.out.print(j + " "); //print j
        }
    }

    //NEXT TWO METHODS ARE FOR MERGE SORT
    static void merge(int[] arr, int[] leftArr, int[] rightArr){
        int i = 0, indexLeft = 0, indexRight = 0;
        int leftSize = arr.length / 2;
        int rightSize = arr.length - leftSize;

        //while the index of left array < leftSize and index of right array is < rightSize.
        //while there are elements in left and right
         while(indexLeft < leftSize && indexRight < rightSize){
            if(leftArr[indexLeft] < rightArr[indexRight]){ //if left array element at indexLeft is < right array element at indexRight
                arr[i] = leftArr[indexLeft]; //set arr element at i = to left array element at indexRight
                i++; //increment i
                indexLeft++; //increment indexLeft
            } else { //if above condition is not true
                arr[i] = rightArr[indexRight]; //set arr element at i = to right array element at indexRight
                i++; //increment i
                indexRight++; //increment indexRight
            }
        }
        //in case there is an element remaining that cannot be compared to another element since remainder is one
        while (indexLeft < leftArr.length){
            arr[i] = leftArr[indexLeft]; //set arr[i] = to element in the left array at indexLeft
            i++; //increment i
            indexLeft++; //increment indexLeft
        }
        while (indexRight < rightArr.length){
            arr[i] = rightArr[indexRight]; //set arr[i] = to element in right array at indexRight
            i++; //increment i
            indexRight++; //increment indexRight
        }
    }

    static void mergeSort(int[] arr){ //O(nlogn)
        //this method handles the division of arrays by two. it will recursively make divisions until arr.length <= 1.
        if (arr.length <= 1){ //if array length is <= 1
            return; //base case
        }

        int midpoint = arr.length/2; //mid-point is half the capacity of array
        int[] left = new int[midpoint]; //left sub-array will go from arr[0] to midpoint (arr.length / 2)
        int[] right = new int[arr.length - midpoint]; //right sub-array will go from mid-point to array.length

        int j = 0;
        //use for right array
        for(int i = 0; i < arr.length; i++){ //iterate as long as i is less than arr.length
            if(i < midpoint){ // if i is less than the midpoint
                left[i] = arr[i]; //set left[i] = arr[i]. add this value to the left sub-array
            } else {
                right[j] = arr[i]; //set right[j] = arr[i]. add this value to right sub-array
                j++; //increment j
            }
        }
        mergeSort(left); //recursively call mergeSort on left subArray to once again make another division
        mergeSort(right); //recursively call mergeSort on right subArray to once again make another division
        merge(arr, left, right); //call merge on arr, left, and right arrays
    }

    //NEXT METHOD IS INSERTION SORT METHOD
    static void insertionSort(int[] arr){ //O(n^2)
        int length = arr.length;
        for (int i = 1; i < length; i++){ //loop between element at index 1 and the last element
            int temporary = arr[i];
            int j = i - 1; //set j = to i - 1
            while (j >= 0 && arr[j] > temporary){ //while j >= 0 and arr at index j is > temporary
                arr[j + 1] = arr[j]; //set arr [j+1] = to arr[j]
                j--; //decrement j
            }
            arr[j+1] = temporary;

        }
    }

}