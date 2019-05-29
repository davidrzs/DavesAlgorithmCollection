import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;

public class JarvisWarp {

	
	public static void findConvexHull(List<Point> points, ConvexHull ch, Canvas canvas) {
		
		if(points.size() == 0) {
			return;
		}
		
		Point smallestX = points.get(0);
		for(Point p : points) {
			if(p.xPos < smallestX.xPos) {
				smallestX = p;
			}
		}
		
		ArrayList<Point> chp = new ArrayList<Point>();
		
		
		
		
	}
	
	
	
	
	// must be a three by three matrix
	public static double threeByThreeDeterminant(double[][] a) {
		double firstTerm = a[0][0]*(a[2][2]*a[1][1] - a[2][1]*a[1][2]);
		double secondTerm = a[0][1]*(a[2][2]*a[1][0] - a[2][0]*a[1][2]);
		double thirdTerm = a[0][2]*(a[2][1]*a[1][0] - a[2][0]*a[1][1]);
		return firstTerm - secondTerm + thirdTerm;
	}
}
