package eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Triangle extends Shape {

	private static final int NumOfPoints = 3;
	/*
	 * delwa2ty 3andy len, hight, w doul pramater ll shape add param in map set
	 * propoerties mo5talefa fei kol shape
	 */
	private int[] xPoints;
	private int[] yPoints;

	/*
	 * EXTRA FEATURES Area
	 */

	/*
	 * pt is calculated to get the center
	 */
	public Triangle() {
		// map param to points
		// tests???
		map = new HashMap<>();
		map.put("point0x", ZeroDEFAULT);
		map.put("point0y", ZeroDEFAULT);
		map.put("point1x", ZeroDEFAULT);
		map.put("point1y", ZeroDEFAULT);
		map.put("point2x", ZeroDEFAULT);
		map.put("point2y", ZeroDEFAULT);
		xPoints = new int[3];
		yPoints = new int[3];

	}

	/* pt is the center of the rectangle */
	/* redraw the shape on the canvas */
	@Override
	public void draw(Graphics canvas) {
		((Graphics2D) canvas).setColor(clrFill);
		for (int i = 0; i < xPoints.length; i++) {
			xPoints[i] = map.get("point" + i + "x").intValue();
		}
		((Graphics2D) canvas).setColor(clrFill);
		for (int i = 0; i < yPoints.length; i++) {
			yPoints[i] = map.get("point" + i + "y").intValue();
		}
		canvas.fillPolygon(xPoints, yPoints, NumOfPoints);
		((Graphics2D) canvas).setStroke(new java.awt.BasicStroke(2));
    ((Graphics2D) canvas).setColor(clrBorder);
    ((Graphics2D) canvas).drawPolygon(xPoints, yPoints, NumOfPoints);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		Shape clonedShape = new Triangle();
		clonedShape.setColor(clrBorder);
		clonedShape.setFillColor(clrFill);
		clonedShape.setPosition(pt);
		Map<String, Double> newMap = new HashMap<>();
		for (Map.Entry<String, Double> element : newMap.entrySet()) {
			newMap.put(element.getKey(), element.getValue());
		}
		clonedShape.setProperties(newMap);
		return clonedShape;
	}
}
