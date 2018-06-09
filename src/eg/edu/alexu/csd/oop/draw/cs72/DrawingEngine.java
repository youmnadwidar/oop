package eg.edu.alexu.csd.oop.draw.cs72;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes.Ellipse;
import eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes.LineSegment;
import eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes.Square;
import eg.edu.alexu.csd.oop.draw.cs72.Model.Shapes.Triangle;
import eg.edu.alexu.csd.oop.draw.cs72.Model.momento.CareTaker;
import eg.edu.alexu.csd.oop.draw.cs72.Model.momento.Originator;
import eg.edu.alexu.csd.oop.draw.cs72.Model.momento.TwoShapes;

public class DrawingEngine implements eg.edu.alexu.csd.oop.draw.DrawingEngine {

	private static final Exception IOException = null;
	public ArrayList<Shape> listOfShapes;
	private List<Class<? extends Shape>> list;
	Originator originator;
	CareTaker careTaker;
	int currentIndexSaved = -1;
	int indexRedoStackRemover;
	Graphics canvas;

	public DrawingEngine() {
		listOfShapes = new ArrayList<>();
		originator = new Originator();
		careTaker = new CareTaker();
		list = new ArrayList<>();
		list.add(Circle.class);
		list.add(LineSegment.class);
		list.add(Ellipse.class);
		list.add(Rectangle.class);
		list.add(Triangle.class);
		list.add(Square.class);
	}

	@Override
	public void refresh(Graphics canvas) {
		// check if it's working
		for (int i = 0; i < listOfShapes.size(); i++) {
			listOfShapes.get(i).draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		listOfShapes.add(shape);
		originator.set(new TwoShapes(null, shape));
		if (currentIndexSaved != 19) {
			currentIndexSaved++;
		}
		careTaker.addMemento(currentIndexSaved, originator.storeInMemento());
		indexRedoStackRemover = currentIndexSaved;
	}

	@Override
	public void removeShape(Shape shape) {
		listOfShapes.remove(shape);
		originator.set(new TwoShapes(shape, null));
		if (currentIndexSaved != 19) {
			currentIndexSaved++;
		}
		careTaker.addMemento(currentIndexSaved, originator.storeInMemento());
		indexRedoStackRemover = currentIndexSaved;
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		listOfShapes.set(listOfShapes.indexOf(oldShape), newShape);
		originator.set(new TwoShapes(oldShape, newShape));
		if (currentIndexSaved != 19) {
			currentIndexSaved++;
		}
		careTaker.addMemento(currentIndexSaved, originator.storeInMemento());
		indexRedoStackRemover = currentIndexSaved;
	}

	@Override
	public Shape[] getShapes() {
		Shape[] shapes = listOfShapes.toArray(new Shape[0]);
		return shapes;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() { // dynamic
																// list.add
		return list;

	}

	@Override
	public void undo() {
		if (currentIndexSaved == -1) {
			return;
		}

		TwoShapes tS = originator.restoreFromMemento(careTaker.getMemento(currentIndexSaved--));

		if (tS.newShape == null) {
			listOfShapes.add(tS.oldShape);
		} else if (tS.oldShape == null) {
			listOfShapes.remove(tS.newShape);
		} else {
			listOfShapes.set(listOfShapes.indexOf(tS.newShape), tS.oldShape);
		}
	}

	@Override
	public void redo() {
		if (currentIndexSaved == indexRedoStackRemover) {
			return;
		}

		TwoShapes tS = originator.restoreFromMemento(careTaker.getMemento(++currentIndexSaved));

		if (tS.oldShape == null) {
			listOfShapes.add(tS.newShape);
		} else if (tS.newShape == null) {
			listOfShapes.remove(tS.oldShape);
		} else {
			listOfShapes.set(listOfShapes.indexOf(tS.oldShape), tS.newShape);
		}
	}

	public void addSupportedShapes(Class<? extends Shape> clss) {
		list.add(clss);
	}

	// Jason, Xml
	@Override
	public void save(String path) {

		Save s = new Save();
		try {
			s.SaveXML(path, listOfShapes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void load(String path) {

		Load load = new Load();

		listOfShapes.clear();
		listOfShapes = new ArrayList<>(load.LoadXML(path));

		currentIndexSaved = 0;
		indexRedoStackRemover = currentIndexSaved;

	}

}
