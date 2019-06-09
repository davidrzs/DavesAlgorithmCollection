import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

	/**
	 * Datastructure for a point that sits in a two dimensional plane
	 * @author Sven Pfiffner
	 */
	public class Point implements Comparable{
		
		final double xPos;
		final double yPos;

		public Point(double xPos, double yPos) {
			this.xPos = xPos;
			this.yPos = yPos;
		}
		
		public double getXPos() {
			return xPos;
		}
		
		public double getYPos() {
			return yPos;
		}
		
		
		@Override
		public String toString() {
			return "(" +this.xPos + "," + this.yPos + ")";
		}
		
	    public int compareTo(Object o) {
	    	// we first cast the object to the other type
	    	Point theOther = (Point) o;
	    	if(this.xPos > theOther.xPos) {
	    		return 1;
	    	} else if(theOther.xPos == this.xPos) {
	    		return 0;
	    	}else { 
	    		return -1;
	    	}
	    }
	    
		
		/**
		 * Draw this Point on a given canvas
		 */
		@SuppressWarnings("restriction")
		public void drawOnCanvas(Canvas canvas) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(4);
			gc.strokeOval(xPos, yPos, 4, 4);
		}
		
	}

