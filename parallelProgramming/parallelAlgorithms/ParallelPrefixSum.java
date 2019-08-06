/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelAlgorithms;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;



/*
 * IMPORTANT NOTICE - THIS CODE IS FAR FROM GOOD, I HAVE WRITTEN IN IN HALF AN HOUR AS A PROOF OF CONCEPT TO TEST SOMETHING NOT TO HAVE A GOOD IMPLEMENTATION
 * 
 */

public class ParallelPrefixSum{

	int[] array;
	static ForkJoinPool fjp;

	
	ParallelPrefixSum(int[] array){
		this.array = array;
		if(fjp == null) {
			fjp = new ForkJoinPool(4);
		}
	}
	
	
	int[] calculate() {
		// this is a pseudoparent - a very ugly solution but it works
		PrefixSumNode prefixTree = new PrefixSumNode(null);
		fjp.invoke(new TopDown(array, 0, array.length-1,prefixTree, Direction.LEFT));
		int[] prefixSum = new int[array.length];
		PropagateValues pv = new PropagateValues(prefixTree.leftChild, prefixSum, 0,array.length-1, Direction.LEFT);
		fjp.invoke(pv);
		return prefixSum;
	}
	

}

class TopDown extends RecursiveAction{

	int[] array;
	int leftBound;
	int rightBound;
	PrefixSumNode fatherNode;
	Direction direction;
	
	
	TopDown(int[] array, int leftBound, int rightBound, PrefixSumNode fatherNode, Direction direction){
		this.array = array;
		this.leftBound = leftBound;
		this.rightBound = rightBound;
		this.fatherNode = fatherNode;
		this.direction = direction;
	}
	
	@Override
	protected void compute() {
		if(rightBound == leftBound) {
			PrefixSumNode child = new PrefixSumNode(fatherNode);
			child.totalSum = array[leftBound];
			if(direction == Direction.LEFT) {
				fatherNode.leftChild = child;
			}else {
				fatherNode.rightChild = child;
			}
		}else {
			PrefixSumNode thisLevel = new PrefixSumNode(fatherNode);
			if(direction == Direction.LEFT) {
				fatherNode.leftChild = thisLevel;
			}else {
				fatherNode.rightChild = thisLevel;
			}
			int midPoint = (rightBound + leftBound) / 2;
			TopDown left = new TopDown(array, leftBound, midPoint,thisLevel, Direction.LEFT);
			TopDown right = new TopDown(array, midPoint+1, rightBound,thisLevel, Direction.RIGHT);
			left.fork();
			right.compute();
			left.join();
			thisLevel.leftSum = thisLevel.leftChild.totalSum;
			thisLevel.totalSum = thisLevel.leftChild.totalSum + thisLevel.rightChild.totalSum;
		}
	}
	
}


class PropagateValues extends RecursiveAction{
	
	PrefixSumNode node;
	int[] arrayToFill;
	int leftIndex;
	int rightIndex;
	Direction direction;
	
	PropagateValues(PrefixSumNode node, int[] arrayToFill, int leftIndex, int rightIndex, Direction direction){
		this.node = node;
		this.arrayToFill = arrayToFill;
		this.leftIndex = leftIndex;
		this.rightIndex = rightIndex;
		this.direction = direction;
	}
	
	@Override
	public void compute() {
		if(leftIndex == rightIndex) {
			if(direction == Direction.LEFT) {
				arrayToFill[leftIndex] = node.totalSum + node.leftSumFromFather;
			}else {
				arrayToFill[leftIndex] = node.totalSum + node.leftSumFromFather;
			}
		}else {
			
			node.rightChild.leftSumFromFather = node.leftSumFromFather + node.leftSum;
			node.leftChild.leftSumFromFather = node.leftSumFromFather; 
			
			int midPoint = (leftIndex + rightIndex) / 2;
			PropagateValues left = new PropagateValues(node.leftChild, arrayToFill, leftIndex, midPoint, Direction.LEFT);
			PropagateValues right = new PropagateValues(node.rightChild, arrayToFill, midPoint+1, rightIndex,Direction.RIGHT);
			left.fork();
			right.compute();
			left.join();
		}
	}
}


enum Direction{
	LEFT, RIGHT
}

class PrefixSumNode{
	PrefixSumNode leftChild;
	PrefixSumNode rightChild;
	PrefixSumNode father;
	int leftSum;
	int rightSum;
	int totalSum;
	int leftSumFromFather;
	
	PrefixSumNode(PrefixSumNode father){
		this.father = father;
	}
	
}

