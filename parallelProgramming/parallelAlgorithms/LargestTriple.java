/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelAlgorithms;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;



public class LargestTriple extends RecursiveTask<Integer> {

	int start;
	int end;
	int[] array;
	
	public static int largestTripleParallel(int[] array) {
		LargestTriple lt = new LargestTriple(array, 0, array.length);
		ForkJoinPool fjp = new ForkJoinPool();
		return fjp.invoke(lt);
	}
	
	LargestTriple(int[] array, int startIndex, int endIndex){
		this.array = array;
		this.start = startIndex;
		this.end = endIndex;
	}
	
	public static int largestTripleSequential(int[] array) {
		int largest = Integer.MIN_VALUE;
		for(int i = 1; i < array.length-1; i++) {
			int currentTriple = array[i-1] + array[i] + array[i+1];
			if(currentTriple > largest) {
				largest = currentTriple;
			}
		}
		return largest;
	}
	
	
	protected Integer compute() {
		if(start == end -1) {
			if(start == 0 || start == array.length-1)
				return 0;
			return array[start-1] + array[start] + array[start+1];
		}else {
			int midPoint = (start + end) / 2;
			LargestTriple left = new LargestTriple(array, start, midPoint);
			LargestTriple right = new LargestTriple(array, midPoint, end);
			left.fork();
			int rightVal = right.compute();
			int leftVal = left.join();
			int atCut =  0;//array[midPoint-1] + array[midPoint] + array[midPoint+1];
			return Math.max(leftVal, Math.max(rightVal, atCut));
		}
	}
	
}
