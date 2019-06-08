import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.canvas.Canvas;

/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */

/**
 * @author David
 *
 */
public class GrahamScan {

	
	@SuppressWarnings("unchecked")
	public static void findConvexHull(List<Point> points, ConvexHull ch, Canvas canvas) {
		
		if(points.size() < 3) {
			// in this case it doesnt make any sense to calculate a convex hull
			return;
		}
		
		ArrayList<Point> chp = new ArrayList<Point>();
		
		Collections.sort(points);
		
		chp.add(points.get(0));
		
		int h = 0;
		
		for(int i = 2; i < points.size(); i++) {
			while(h > 0 && isLeftOf(chp.get(h), chp.get(h-1), points.get(i))) {
				h--;
			}	
			h++;
			if(chp.size() <= h) {
				chp.add(points.get(i));
			} else {
				chp.set(h, points.get(i));				
			}
		}
		
		// we can now recycle h
		
		
		

		// set the points of the convex hull
		ch.pointsOnConvexHull = chp;
		
		
		
		
	}
	
	public static boolean isLeftOf(Point p, Point q, Point r) {
		return (q.xPos-p.xPos)*(r.yPos-p.yPos) < (q.yPos-p.yPos)*(r.xPos-p.xPos);
	}
}
