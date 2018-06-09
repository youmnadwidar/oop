package eg.edu.alexu.csd.oop.jdbc.cs72;

import java.sql.SQLException;
import java.sql.Types;
/**
 *
 * check this one https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSetMetaData.html
 *
 */

public class ResultSetMetaDataAdapter extends ResultMetaData {

	// link it using function getMetaData in ResultSet
	// get table name men el url elly fei ResultSet(sent in getMetaData)

	private final ResultSetAdapter resultSet ;

	public ResultSetMetaDataAdapter(ResultSetAdapter result) {

		this.resultSet = result ;
	}

	@Override
	public int getColumnCount() throws SQLException {
		// TODO Auto-generated method stub
		return resultSet.getColumnsName().size();
	}

	@Override
	public String getColumnLabel(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return resultSet.getColumnsName().get(arg0);
	}

	@Override
	public String getColumnName(int arg0) throws SQLException {

		return resultSet.getColumnsName().get(arg0);
	}

	@Override
	public int getColumnType(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		final String className = resultSet.result[0][arg0].getClass().getSimpleName();
		if(className.equalsIgnoreCase("string")){
			return Types.VARCHAR;
		}else {
			return Types.INTEGER;
		}

	}

	@Override
	public String getTableName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return resultSet.getTableName();
	}
}
