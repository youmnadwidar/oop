package eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Ellipse extends Shape {



	public Ellipse() {
		super();
		map = new HashMap<>();
		map.put("xDiameter", ZeroDEFAULT);
		map.put("yDiameter", ZeroDEFAULT);
	}

	/* pt is the Top left of the rectangle */
	/* redraw the shape on the canvas */
	@Override
	public void draw(Graphics canvas) {
		((Graphics2D) canvas).setColor(clrFill);
		((Graphics2D) canvas).fillRoundRect((int) pt.getX() - (map.get("xDiameter").intValue()/2),
		    (int) pt.getY() - (map.get("yDiameter").intValue()/2), super.map.get("xDiameter").intValue(),
				map.get("yDiameter").intValue(), map.get("xDiameter").intValue(),
				map.get("yDiameter").intValue());
		((Graphics2D) canvas).setStroke(new java.awt.BasicStroke(2));
		((Graphics2D) canvas).setColor(clrBorder);
    ((Graphics2D) canvas).drawRoundRect((int) pt.getX() - (map.get("xDiameter").intValue()/2),
        (int) pt.getY() - (map.get("yDiameter").intValue()/2), super.map.get("xDiameter").intValue(),
        map.get("yDiameter").intValue(), map.get("xDiameter").intValue(),
        map.get("yDiameter").intValue());
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clonedShape = new Ellipse();
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

