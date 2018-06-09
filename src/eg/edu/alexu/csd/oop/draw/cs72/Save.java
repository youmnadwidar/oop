package eg.edu.alexu.csd.oop.draw.cs72;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Save {

	DrawingEngine draw = new DrawingEngine();

	public void SaveJson(String path, ArrayList<Shape> list) {

		// File file = new File(path);
		// try {
		// FileWriter writer = new FileWriter(file);
		// Gson gson = new Gson();
		//
		// for (int i = 0; i < list.size(); i++) {
		//
		// Shape newShape = list.get(0);
		// writer.write(newShape.getClass() + "\n" + gson.toJson(newShape));
		//
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public void SaveXML(String path, ArrayList<Shape> list) throws IOException {

		File file = new File(path);
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
		e.writeObject(list);
		e.close();
	}
	// System.out.println(list.getList().size());
	//
	// Class[] array = new Class[draw.getSupportedShapes().size() + 1];
	// ArrayList<Class> listClass = new ArrayList<>();
	// listClass.addAll(draw.getSupportedShapes());
	// listClass.add(ListClass.class);
	// array = listClass.toArray(array);
	//
	// try {
	// JAXBContext jaxbMarshaller;
	//
	// jaxbMarshaller = JAXBContext.newInstance(array);
	// Marshaller m = jaxbMarshaller.createMarshaller();
	// m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	// m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
	// m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
	// m.marshal(list, file);
	//
	// } catch (JAXBException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
}
