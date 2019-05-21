import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


	public class Circle {
		
		double xPos;
		double yPos;
		double radius;

		public Circle(double xPos, double yPos, double radius) {
			this.radius = radius;
			this.xPos = xPos;
			this.yPos = yPos;
		}
		
		public double getXPos() {
			return xPos;
		}
		
		public double getYPos() {
			return yPos;
		}
		
		public String toString() {
			return xPos + " " + yPos + " " + radius;
		}
		
		
		public boolean containsPoint(Point p) {
			// add +1 as error margin
			return Math.pow(p.xPos - xPos, 2) + Math.pow(p.yPos - yPos, 2)-1 <= radius * radius;
		}
		
		/**
		 * Draw this Point on a given canvas
		 */
		
		@SuppressWarnings("restriction")
		public void drawOnCanvas(Canvas canvas) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(4);
			gc.strokeOval(xPos-radius, yPos-radius, 2*radius, 2*radius);
		}
		
	}

