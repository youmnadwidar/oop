package eg.edu.alexu.csd.oop.jdbc.cs72;

import java.util.ArrayList;

public class ResultSetPool {
	ArrayList<ResultSetAdapter> available = new ArrayList<ResultSetAdapter>();
	ArrayList<ResultSetAdapter> inUse = new ArrayList<ResultSetAdapter>();

	public ResultSetAdapter getConnection(Object[][] r, StatementAdapter state) {
		if(available.isEmpty()) {
			return  new ResultSetAdapter(r, new ResultSetPool(), state, new ArrayList<String>() , null );
		}
		else
		{
			final ResultSetAdapter resultSet = available.remove(available.size()-1);
			inUse.add(resultSet);
			resultSet.setObject(r);
			return resultSet;
		}
	}

	public void retrieve (ResultSetAdapter resultSet) {
		resultSet.setWithNull(null);
		available.add(resultSet);
		inUse.remove(resultSet);
	}

}

