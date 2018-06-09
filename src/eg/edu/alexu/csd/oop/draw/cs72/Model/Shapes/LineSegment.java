package eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class LineSegment extends Shape {

	private Point pt;

	 public LineSegment() {
		super();
		map = new HashMap<>();
		map.put("length", ZeroDEFAULT);
		map.put("pointx", ZeroDEFAULT);
		map.put("pointy", ZeroDEFAULT);
	}
	/* pt is the Top left of the rectangle */
	/* redraw the shape on the canvas */
	@Override
	public void draw(Graphics canvas) {

		((Graphics2D) canvas).setColor(clrFill);
		((Graphics2D) canvas).drawLine(pt.x, pt.y, map.get("pointx").intValue(), map.get("pointy").intValue());
		//((Graphics2D) canvas).setStroke(new java.awt.BasicStroke(2));

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clonedShape = new LineSegment();
		clonedShape.setPosition(this.pt);
		clonedShape.setColor(clrBorder);
		clonedShape.setFillColor(clrFill);
		Map<String, Double> newMap = new HashMap<>();
		for (Map.Entry<String, Double> element : super.map.entrySet()) {
			newMap.put(element.getKey(), element.getValue());
		}
		clonedShape.setProperties(newMap);
		return clonedShape;
	}

}
