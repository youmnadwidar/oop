package eg.edu.alexu.csd.oop.db.cs72;

import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {
		DataBaseImpl db = new DataBaseImpl();
		db.createDatabase("c", false);
		//db.executeStructureQuery("Create table table_13 (id int , name varchar );");
		db.executeUpdateQuery("insert into table_13 values(3 , 'name')");

	}

}
