package eg.edu.alexu.csd.oop.draw.cs72;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Load {

	DrawingEngine draw = new DrawingEngine();

	public void LoadJson(String path) {

		// try {
		// FileReader reader = new FileReader(path);
		// BufferedReader bf = new BufferedReader(reader);
		// Gson gson = new Gson();
		//
		//
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		//
	}

	public ArrayList<Shape> LoadXML(String path) {

		File file = new File(path);
		XMLDecoder d;
		try {

			d = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)), null, null,
					this.getClass().getClassLoader());
			ArrayList<Shape> result = ((ArrayList<Shape>) d.readObject());

			d.close();
			return result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
	}
	//
	// Class<?>[] array = new Class[draw.getSupportedShapes().size() + 1];
	// ArrayList<Class<?>> listClass = new ArrayList<>();
	// listClass.addAll(draw.getSupportedShapes());
	// listClass.add(ListClass.class);
	// array = listClass.toArray(array);
	// try {
	// JAXBContext jaxbContext = JAXBContext.newInstance(array);
	// Unmarshaller um = jaxbContext.createUnmarshaller();
	// ListClass<Shape> list = (ListClass<Shape>) um.unmarshal(file);
	// return list;
	// } catch (JAXBException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return null;
	//
	// }
}
