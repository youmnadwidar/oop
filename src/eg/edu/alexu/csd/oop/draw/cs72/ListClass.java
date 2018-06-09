package eg.edu.alexu.csd.oop.draw.cs72;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eg.edu.alexu.csd.oop.draw.Shape;
@XmlRootElement
public class ListClass<T> implements Serializable{

	public ListClass() {
		super();
		list = new ArrayList<>();
	}

	public ListClass(ArrayList<T> list) {
		super();
		this.list = list;
	}
	
	private ArrayList<T> list = new ArrayList<T>();

	public ArrayList<T> getList() {
		return list;
	}

	public void setList(ArrayList<T> list) {
		this.list = list;
	}

}
