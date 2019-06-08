import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ConvexHull {

	List<Point> pointsOnConvexHull;
	
	ConvexHull(){
		pointsOnConvexHull = new ArrayList<Point>();
	}
	
	
	@SuppressWarnings("restriction")
	public void drawOnCanvas(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);

		double[] xs = new double[pointsOnConvexHull.size()];
		double[] ys = new double[pointsOnConvexHull.size()];
		int counter = 0;
		
		for(Point p : pointsOnConvexHull) {
			xs[counter] = p.xPos;
			ys[counter] = p.yPos;
			counter++;
		}
		gc.strokePolygon(xs, ys, pointsOnConvexHull.size());
	}
	
	
}
