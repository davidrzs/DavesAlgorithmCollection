
public class Recursive2MergeSort {
	public static int binarySearch(int[] arr, int value) {
		int middle = 0;
		int left = 0;
		int right = arr.length-1;
		while(left<=right) {
			middle =  left + (right-left)/2;
			if(value == arr[middle]) {
				return middle+1;
			}
			//System.out.println(middle);
			if(value < arr[middle]) {
				right = middle-1;
			} else {
				left = middle+1;
			}
		}
		return middle;
	}
	
	public static int[] mergeSort(int[] arrToBeSorted) {
		if(arrToBeSorted.length > 1) {
			int middle = arrToBeSorted.length / 2;
			//System.out.println(middle);
			 /* Copy data to temp arrays L[] and R[] */
			int[] leftSide = new int[middle];
			int[] rightSide = new int[arrToBeSorted.length-middle];
			//System.out.println("ll " + leftSide.length + " rl " + rightSide.length);
		    for (int i = 0; i < leftSide.length; i++) {
		    	leftSide[i] = arrToBeSorted[i];
		    }

		    for (int i = 0; i < rightSide.length; i++) {
		    	rightSide[i] = arrToBeSorted[middle + i];
		    }
			
			int[] leftArr = mergeSort(leftSide);
			int[] rightArr = mergeSort(rightSide);
			return merge(leftArr,rightArr);
		} 
		return arrToBeSorted;
	}
	
	public static int[] merge(int[] arr1, int[] arr2) {
		int[] resArr = new int[arr1.length + arr2.length];
		int leftPointer = 0;
		int rightPointer = 0; 
		int loopCounter = 0;
		
		while(leftPointer < arr1.length && rightPointer < arr2.length) {
			if(arr1[leftPointer] < arr2[rightPointer]) {
				resArr[loopCounter] = arr1[leftPointer];
				leftPointer++;
			} else {
				resArr[loopCounter] = arr2[rightPointer];
				rightPointer++;
			}
			loopCounter++;
		}
		//fill the rest of the resArray:
		
		while(leftPointer < arr1.length) {
			//System.out.println("lp: " + leftPointer + " arr1.l" + arr1.length);
			resArr[loopCounter] = arr1[leftPointer];
			leftPointer++;
			loopCounter++;
		}
		
		while(rightPointer < arr2.length) {
			resArr[loopCounter] = arr2[rightPointer];
			rightPointer++;
			loopCounter++;
		}
		
		return resArr;
	}
}
