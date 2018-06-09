package eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public abstract class Shape implements eg.edu.alexu.csd.oop.draw.Shape {

	protected static final double ZeroDEFAULT = 0.0;
	protected Point pt;
	protected Map<String, Double> map;
	protected Color clrFill;
	protected Color clrBorder;

	public Shape() {
		pt = new Point();
		map = new HashMap<>();
		clrFill = new Color(0, 0, 0);
		clrBorder = new Color(0, 0, 0);
	}

	/* update shape specific properties (e.g., radius) */
	@Override
	public void setPosition(Point position) {
		this.pt = position;
	}

	@Override
	@XmlJavaTypeAdapter(MyPointAdapter.class)
	public Point getPosition() {
		return pt;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.map = properties;
	}

	@Override
	public Map<String, Double> getProperties() {
		return this.map;
	}

	@Override
	public void setColor(Color color) {
		this.clrBorder = color;
	}

	@Override
	@XmlJavaTypeAdapter(ColorAdapter.class)
	public Color getColor() {
		return this.clrBorder;
	}

	@Override
	public void setFillColor(Color color) {
		this.clrFill = color;
	}

	@Override
	@XmlJavaTypeAdapter(ColorAdapter.class)
	public Color getFillColor() {
		return this.clrFill;
	}

	// missing function draw

	/* create a deep clone of the shape */
	public Object clone() throws CloneNotSupportedException {
		return null;
	}

	public void printFun() {
	}

	private static class ColorAdapter extends XmlAdapter<ColorAdapter.ColorValueType, Color> {

		@Override
		public Color unmarshal(ColorValueType v) throws Exception {
			return new Color(v.red, v.green, v.blue);
		}

		@Override
		public ColorValueType marshal(Color v) throws Exception {
			return new ColorValueType(v.getRed(), v.getGreen(), v.getBlue());
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		public static class ColorValueType {
			private int red;
			private int green;
			private int blue;

			public ColorValueType() {
			}

			public ColorValueType(int red, int green, int blue) {
				this.red = red;
				this.green = green;
				this.blue = blue;
			}
		}
	}

	private static class MyPointAdapter extends XmlAdapter<MyPointAdapter.MyPoint, Point> {

		@Override
		public MyPoint marshal(Point v) throws Exception {
			return new MyPoint((int) v.getX(), (int) v.getY());
		}

		@Override
		public Point unmarshal(MyPoint v) throws Exception {

			return new Point(v.x, v.y);
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		public static class MyPoint {
			private int x, y;

			public MyPoint() {

			}

			public MyPoint(int x, int y) {
				this.x = x;
				this.y = y;
			}

		}

	}
}
