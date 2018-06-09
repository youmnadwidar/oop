package eg.edu.alexu.csd.oop.draw.cs72.Model.momento;

import eg.edu.alexu.csd.oop.draw.Shape;
/**
 * Stores two shapes to be passed as a moment.
 */
public class TwoShapes {
  /** shapes variables.
   */
	public Shape oldShape, newShape;
	/**
	 * @param oldShape stores old shape
	 * @param newShape stores new shape
	 */
	public TwoShapes(final Shape oldShape, final Shape newShape) {
		this.oldShape = oldShape;
		this.newShape = newShape;
	}

}
