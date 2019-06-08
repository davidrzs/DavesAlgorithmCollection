import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;

public class JarvisWarp {

	
	public static void findConvexHull(List<Point> points, ConvexHull ch, Canvas canvas) {
		
		if(points.size() < 3) {
			return;
		}
		
		Point smallestX = points.get(0);
		for(Point p : points) {
			if(p.xPos < smallestX.xPos) {
				smallestX = p;
			}
		}
		
		ArrayList<Point> chp = new ArrayList<Point>();
		
		chp.add(smallestX);
		Point nextPoint = smallestX;
		
		do {
			nextPoint = findNext(nextPoint, points);
			chp.add(nextPoint);
//		ch.pointsOnConvexHull = chp;
//		ch.drawOnCanvas(canvas);
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}while(nextPoint != smallestX);
		
		
		
		
		// set the points of the convex hull
		ch.pointsOnConvexHull = chp;
		
		
		
		
	}
	
	
	public static boolean isRightOf(Point q, Point r, Point p) {
		return (q.xPos-p.xPos)*(r.yPos-p.yPos) < (q.yPos-p.yPos)*(r.xPos-p.xPos);
	}
	
	
	public static Point findNext(Point current, List<Point> ps) {
		Point nextCandidate = (current == ps.get(1)) ? ps.get(0) : ps.get(1);
		
		for(Point p : ps) {
			if(isRightOf(current, nextCandidate, p)) {
				nextCandidate = p;
			}
		}
		
		return nextCandidate;
	}
	
	
	
	// must be a three by three matrix
	public static double threeByThreeDeterminant(double[][] a) {
		double firstTerm = a[0][0]*(a[2][2]*a[1][1] - a[2][1]*a[1][2]);
		double secondTerm = a[0][1]*(a[2][2]*a[1][0] - a[2][0]*a[1][2]);
		double thirdTerm = a[0][2]*(a[2][1]*a[1][0] - a[2][0]*a[1][1]);
		return firstTerm - secondTerm + thirdTerm;
	}
}
