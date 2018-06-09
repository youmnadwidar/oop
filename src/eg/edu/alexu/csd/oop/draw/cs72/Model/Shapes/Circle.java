package eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement
public class Circle extends Shape {


	 public Circle() {
		super();
		map = new HashMap<>();
		map.put("Diameter", ZeroDEFAULT);
	}
	/* pt is the Top left of the rectangle */
	/* redraw the shape on the canvas */
	@Override
	public void draw(Graphics canvas) {
		int diameter = map.get("Diameter").intValue();
		((Graphics2D) canvas).setColor(clrFill);
		((Graphics2D) canvas).fillRoundRect((int) pt.getX() - (map.get("Diameter").intValue()/2),
				(int) pt.getY() - (map.get("Diameter").intValue()/2),
				diameter, diameter, diameter, diameter);
		((Graphics2D) canvas).setStroke(new java.awt.BasicStroke(2));
		((Graphics2D) canvas).setColor(clrBorder);
    ((Graphics2D) canvas).drawRoundRect((int) pt.getX() - (map.get("Diameter").intValue()/2),
        (int) pt.getY() - (map.get("Diameter").intValue()/2),
        diameter, diameter, diameter, diameter);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
//		Shape clonedShape = new circle();
//		clonedShape.setPosition(this.pt);
//		clonedShape.setColor(clrBorder);
//		clonedShape.setFillColor(clrFill);
//		Map<String, Double> newMap = new HashMap<>();
//		for (Map.Entry<String, Double> element : super.map.entrySet()) {
//			newMap.put(element.getKey(), element.getValue());
//		}
//		clonedShape.setProperties(newMap);
	  
	  Shape clonedShape = null;
	  clonedShape = (Circle) super.clone();
		return clonedShape;
	}
	public void printFun () {
	  System.out.println("Hello from circle");
	}

}
