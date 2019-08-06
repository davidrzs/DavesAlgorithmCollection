/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */

import java.util.Arrays;

public class NormalQuickSelect {
	public static void main(String[] args) {
	    int[] toSort = new int[] {3,1,4,6,1,4,84,2,1,6};
	    quickSort(toSort, 0, toSort.length-1);
	    System.out.println(Arrays.toString(toSort));
	    int[] toSelect = new int[] {3,1,4,6,1,4,84,2,1,6};

	    System.out.println(quickSelect(toSelect, 5, 0, toSelect.length-1));
	  }

	  public static int quickSelect(int[] array, int kSmallest, int left, int right){
	    int pivot = partition(array, left, right);
	    if(pivot == kSmallest-1){
	      return array[pivot];
	    }
	    if(pivot > kSmallest-1){
	      return quickSelect(array, kSmallest, pivot+1, right);
	    }else {
	      return quickSelect(array, kSmallest, left, pivot-1);
	    }

	  }

	  public static int[] quickSort(int[] array, int left, int right){
	    if(left<right){
	      int pivot = partition(array, left, right);
	      quickSort(array, left, pivot-1);
	      quickSort(array, pivot+1, right);
	    }

	    return array;
	  }

	  public static int partition(int[] array, int left, int right){
	    int pivot = array[right];
	    int leftRunner = left;
	    int rightRunner = right-1;
	    do{

	      while(array[leftRunner] <= pivot && leftRunner < right){
	        leftRunner++;
	      }
	      
	      while(array[rightRunner] >= pivot && rightRunner > left){
	        rightRunner--;
	      }
	      if(leftRunner >= rightRunner){
	        break;
	      }

	      if(array[rightRunner] < array[leftRunner] && leftRunner < rightRunner){
	        int temp = array[rightRunner];
	        array[rightRunner] = array[leftRunner];
	        array[leftRunner] = temp;
	      }
	    
	    }while(leftRunner < rightRunner);

	    array[right] = array[leftRunner];
	    array[leftRunner] = pivot;
	    return leftRunner;
	  }
}
