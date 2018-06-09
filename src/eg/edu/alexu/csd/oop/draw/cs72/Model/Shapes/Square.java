package eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Square extends Shape {


	 public Square() {
		super();
		map = new HashMap<>();
		map.put("length", ZeroDEFAULT);
	}
	/* pt is the Top left of the rectangle */
	/* redraw the shape on the canvas */
	@Override
	public void draw(Graphics canvas) {

		((Graphics2D) canvas).setColor(clrFill);
		((Graphics2D) canvas).fillRect((int) pt.getX(), (int) pt.getY(),map.get("length").intValue(),
				map.get("length").intValue());
		((Graphics2D) canvas).setStroke(new java.awt.BasicStroke(2));
		((Graphics2D) canvas).setColor(clrBorder);
    ((Graphics2D) canvas).drawRect((int) pt.getX(), (int) pt.getY(),map.get("length").intValue(),
        map.get("length").intValue());

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clonedShape = new Square();
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
