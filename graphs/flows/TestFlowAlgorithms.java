package flows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */

/**
 * @author david
 *
 */
class TestFlowAlgorithms {

	@Test
	void test() throws FileNotFoundException {
		Scanner scn = new Scanner(new File("./graphs/flows/testCases/test1_input.txt"));
		Scanner solutionScanner = new Scanner(new File("./graphs/flows/testCases/test1_output.txt"));

        // Read the number of testcases to follow
        int p = scn.nextInt();

        // Iterate over the testcases and solve the problem
        for (int i = 0; i < p; i++) {
        }
		
        // Read the input
        int nrOfKids = scn.nextInt();
        int nrOfToys = scn.nextInt();
        
        int sumOfDeserve = 0;
        
        int numVer = nrOfKids + nrOfToys + 2; // since we have s and t too.

        // Don't forget to initialize numVer
        // Initialize the graph
        double[][] capacity = new double[numVer][numVer];
        double[][] flow = new double[numVer][numVer];
        
        ArrayList<Integer>[] graph = (ArrayList<Integer>[])new ArrayList[numVer];
        for(int i = 0; i < numVer; i++)
            graph[i] = new ArrayList<Integer>();
        
      
        int t = nrOfToys+nrOfKids + 1;
        for(int i = nrOfToys+1; i <= nrOfToys+nrOfKids; i++){
          int nrOfToysDeserve = scn.nextInt();
          //System.out.println("toyNr: "+ nrOfToysDeserve);
          sumOfDeserve += nrOfToysDeserve;
          // add the connection to s
          graph[i].add(t); //<-- adding an edge from vertex 0 to vertex 1
          graph[t].add(i); //<-- add the reverse edge!
          capacity[i][t] = nrOfToysDeserve; // <- replace 1 with your desired capacity
          capacity[t][i] = 0; // <- the reverse edge should have capacity 0
        }
        
        for(int i = 1; i <= nrOfToys; i++){
          int nrOfCopies = scn.nextInt();
          
          // add the connection to s
          graph[0].add(i); //<-- adding an edge from vertex 0 to vertex 1
          graph[i].add(0); //<-- add the reverse edge!
          capacity[0][i] = nrOfCopies; // <- replace 1 with your desired capacity
          capacity[i][0] = 0; // <- the reverse edge should have capacity 0
        }
        
        // now we need to connect all the toys with the kids
        
        for(int i = 1; i <= nrOfToys; i++){
                for(int k = nrOfToys+1; k <= nrOfToys+nrOfKids; k++){
                    graph[i].add(k); //<-- adding an edge from vertex 0 to vertex 1
                    graph[k].add(i); //<-- add the reverse edge!
                    capacity[i][k] = 1; // <- replace 1 with your desired capacity
                    capacity[k][i] = 0; // <- the reverse edge should have capacity 0
                }
          
        }
        

        
        // Compute maximum flow
        double  maxFlow = FordFulkerson.calculateMaximalflow(capacity, flow, graph);
        //System.out.println(maxFlow+" " + sumOfDeserve);
        String outputString;
        
        if(maxFlow == sumOfDeserve){
        	outputString = ("yes");
        }else {
        	outputString = ("no");
        }
        
        assertEquals(solutionScanner.next(), outputString);
        
	}



}
