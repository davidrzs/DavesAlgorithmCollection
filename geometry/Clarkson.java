import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import javafx.scene.canvas.Canvas;

public class Clarkson {
	
	static Random rand;
	
	
	
	public static void findSmallestEnclosingCircle(ArrayList<Point> points, Circle circ, Canvas canvas) {
		if(points.size() < 3) {
			return;
		}
	
		while(!allPointsInCircle(points, circ)) {
			// randomly shuffle the points
			Collections.shuffle(points);
			getSmallestEnclosingCircle(points.get(0), points.get(1), points.get(2), circ);
			circ.drawOnCanvas(canvas);
			try {
				Thread.sleep(10);
				System.out.println("all points in circle");
			}catch(Exception ex) {
				
			}
		}
		System.out.println("all points in circle");
	}
	
	public static void getSmallestEnclosingCircle(Point p1, Point p2, Point p3, Circle circ) {
		ArrayList<Circle> cList = new ArrayList<Circle>(4);

		cList.add(makeCircle3Points(p1,p2,p3));
		cList.add(makeCircle2Points(p1,p2));
		cList.add(makeCircle2Points(p2,p3));
		cList.add(makeCircle2Points(p3,p1));
		Iterator<Circle> itC = cList.iterator();
		while(itC.hasNext()) {
			Circle c = itC.next();
			// if the circle doesnt contains all three we will remove it
			if(!c.containsPoint(p1) || !c.containsPoint(p2) || !c.containsPoint(p3)) {
				itC.remove();
			}
		}
		System.out.println(cList);		
		Circle minCircle = new Circle(0,0,Double.MAX_VALUE);
		for(Circle tempCirc : cList) {
			if(tempCirc.radius <= minCircle.radius) {
				minCircle = tempCirc;
			}
		}
		circ.xPos = minCircle.xPos;
		circ.yPos = minCircle.yPos;
		circ.radius = minCircle.radius;
		
	}
	
	
	// http://www.cs.princeton.edu/courses/archive/spring09/cos226/assignments/circle.html
	public static Circle makeCircle2Points(Point p1, Point p2) {
		Circle circ = new Circle(0,0,0);
		circ.xPos = (p1.getXPos() + p2.getXPos())/2;
		circ.yPos = (p1.getYPos() + p2.getYPos())/2;
		circ.radius =  Math.sqrt((Math.pow(p1.getXPos() - p2.getXPos(), 2) + Math.pow(p1.getYPos() - p2.getYPos(), 2)) / 4);
		return circ;
	}
	
	
	// http://www.cs.princeton.edu/courses/archive/spring09/cos226/assignments/circle.html
	public static Circle makeCircle3Points(Point p1, Point p2, Point p3) {
		Circle circ = new Circle(0,0,0);
		double[][] a = {{p1.xPos, p1.yPos, 1},
						{p2.xPos, p2.yPos, 1},
						{p3.xPos, p3.yPos, 1}};
		
		double[][] d = {{p1.xPos*p1.xPos + p1.yPos*p1.yPos, p1.yPos, 1},
						{p2.xPos*p2.xPos + p2.yPos*p2.yPos, p2.yPos, 1},
						{p3.xPos*p3.xPos + p3.yPos*p3.yPos, p3.yPos, 1}};
		
		double[][] e = {{p1.xPos*p1.xPos + p1.yPos*p1.yPos, p1.xPos, 1},
						{p2.xPos*p2.xPos + p2.yPos*p2.yPos, p2.xPos, 1},
						{p3.xPos*p3.xPos + p3.yPos*p3.yPos, p3.xPos, 1}};

		double[][] f = {{p1.xPos*p1.xPos + p1.yPos*p1.yPos, p1.xPos, p1.yPos},
						{p2.xPos*p2.xPos + p2.yPos*p2.yPos, p2.xPos, p2.yPos},
						{p3.xPos*p3.xPos + p3.yPos*p3.yPos, p3.xPos, p3.yPos}};
		
		double detA = threeByThreeDeterminant(a);
		double detD = threeByThreeDeterminant(d);
		double detE = threeByThreeDeterminant(e);
		double detF = threeByThreeDeterminant(f);
		
		// in case the circle are all one one line
		if(detA==0) {
			return null;
		}
		
		circ.xPos = detD/(2*detA);
		circ.yPos = -detE/(2*detA);
		circ.radius =  Math.sqrt( ((detD*detD + detE*detE) / (4*detA*detA))  + (detF/detA)  );
		
		return circ;
	}
	
	

	
	public static boolean allPointsInCircle(ArrayList<Point> points, Circle circ) {
		boolean allInside = true;
		for(Point p : points) {
			if(!circ.containsPoint(p)) {
				allInside = false;
			}
		}
		return allInside;
	}
	
	// a must be a three by three matrix
	public static double threeByThreeDeterminant(double[][] a) {
		double firstTerm = a[0][0]*(a[2][2]*a[1][1] - a[2][1]*a[1][2]);
		double secondTerm = a[0][1]*(a[2][2]*a[1][0] - a[2][0]*a[1][2]);
		double thirdTerm = a[0][2]*(a[2][1]*a[1][0] - a[2][0]*a[1][1]);
		return firstTerm - secondTerm + thirdTerm;
	}
	
	
	
	
}
