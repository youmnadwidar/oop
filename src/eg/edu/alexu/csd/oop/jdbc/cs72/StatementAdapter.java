package eg.edu.alexu.csd.oop.jdbc.cs72;


import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.cs72.DataBaseImpl;


/**
 * check this one
 * https://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html
 */

public class StatementAdapter extends StatementAbstract {

	private final Connection connection;
	private int queryTimeOut;
	private final ResultSetPool resultPool;
	private final StatementPool statePool;
	private ArrayList<String> arr;
	private String path;
	private boolean isclosed;
	private DataBaseImpl dbms;

	public StatementAdapter(String path, StatementPool pool, Connection connect) {
		this.path = path;
		this.resultPool = new ResultSetPool();
		this.isclosed = false;
		this.arr = new ArrayList<>();
		this.statePool = pool;
		this.connection = connect;
		this.dbms = new DataBaseImpl();
		final String[] strings = this.path.split(File.separator);
		dbms.createDatabase(strings[strings.length -1 ], false);
	}

	/**
	 * Adds the given SQL command to the current list of commands for this
	 * Statement object. The commands in this list can be executed as a batch by
	 * calling the method executeBatch.
	 *
	 * Note:This method cannot be called on a PreparedStatement or
	 * CallableStatement.
	 *
	 * Parameters: sql - typically this is a SQL INSERT or UPDATE statement
	 * Throws: SQLException - if a database access error occurs, this method is
	 * called on a closed Statement, the driver does not support batch updates,
	 * the method is called on a PreparedStatement or CallableStatement
	 */
	@Override
	public void addBatch(String arg0) throws SQLException {
		arr.add(arg0);
	}

	/**
	 * Empties this Statement object's current list of SQL commands.
	 *
	 * Throws: SQLException - if a database access error occurs, this method is
	 * called on a closed Statement or the driver does not support batch updates
	 */
	@Override
	public void clearBatch() throws SQLException {
		arr.clear();
	}

	/**
	 * Releases this Statement object's database and JDBC resources immediately
	 * instead of waiting for this to happen when it is automatically closed. It
	 * is generally good practice to release resources as soon as you are
	 * finished with them to avoid tying up database resources.
	 *
	 * Calling the method close on a Statement object that is already closed has
	 * no effect.
	 *
	 * Note:When a Statement object is closed, its current ResultSet object, if
	 * one exists, is also closed.
	 *
	 * Specified by: close in interface AutoCloseable Throws: SQLException - if
	 * a database access error occurs
	 */
	@Override
	public void close() throws SQLException {
		statePool.retrieve(this);
		this.isclosed = true;
	}


	@Override
	public boolean execute(String query) throws SQLException {
		// throw new RuntimeException(query);




		final String[] operation = query.trim().split("\\W+");
		
		if (operation[0].equalsIgnoreCase("Select")) {

		executeQuery(query);

			return true;
		} else if (operation[0].equalsIgnoreCase("Create") || operation[0].equalsIgnoreCase("drop")) {

		 dbms.executeStructureQuery(query);

		 return false ;
		}else if(operation[0].equalsIgnoreCase("update") || operation[0].equalsIgnoreCase("insert")
				|| operation[0].equalsIgnoreCase("delete")){
			//			throw new RuntimeException(query);
			executeUpdate(query);
			return false ;
		}

		return false ;
	}

	
	@Override
	public int[] executeBatch() throws SQLException {
		final int[] answer = new int[arr.size()];

		for (int i = 0; i < arr.size(); i++) {

			answer[i] = dbms.executeUpdateQuery(arr.get(i));

		}

		arr.clear();
		return answer;
	}

	@Override
	public ResultSet executeQuery(String arg0) throws SQLException {

		final Object[][] ans = dbms.executeQuery(arg0);

		final ResultSet result = new ResultSetAdapter(ans, resultPool, this , new ArrayList<String>(dbms.getColumnsNames()) , dbms.getTableNameForSelect());
		return result;
	}

	
	@Override
	public int executeUpdate(String arg0) throws SQLException {

		final int ans = dbms.executeUpdateQuery(arg0);


		return ans;
	}

	/**
	 * Retrieves the Connection object that produced this Statement object.
	 *
	 * Returns: the connection that produced this statement Throws: SQLException
	 * - if a database access error occurs or this method is called on a closed
	 * Statement
	 */

	@Override
	public Connection getConnection() throws SQLException {
		return connection;
	}

	/**
	 *
	 * Retrieves the number of seconds the driver will wait for a Statement
	 * object to execute. If the limit is exceeded, a SQLException is thrown.
	 *
	 * Returns: the current query timeout limit in seconds; zero means there is
	 * no limit Throws: SQLException - if a database access error occurs or this
	 * method is called on a closed Statement
	 *
	 * threads
	 */
	@Override
	public int getQueryTimeout() throws SQLException {
		return queryTimeOut;
	}

	/**
	 * Sets the number of seconds the driver will wait for a Statement object to
	 * execute to the given number of seconds. By default there is no limit on
	 * the amount of time allowed for a running statement to complete. If the
	 * limit is exceeded, an SQLTimeoutException is thrown. A JDBC driver must
	 * apply this limit to the execute, executeQuery and executeUpdate methods.
	 *
	 * Note: JDBC driver implementations may also apply this limit to ResultSet
	 * methods (consult your driver vendor documentation for details).
	 *
	 * Note: In the case of Statement batching, it is implementation defined as
	 * to whether the time-out is applied to individual SQL commands added via
	 * the addBatch method or to the entire batch of SQL commands invoked by the
	 * executeBatch method (consult your driver vendor documentation for
	 * details).
	 *
	 * Parameters: seconds - the new query timeout limit in seconds; zero means
	 * there is no limit Throws: SQLException - if a database access error
	 * occurs, this method is called on a closed Statement or the condition
	 * seconds >= 0 is not satisfied
	 */

	@Override
	public void setQueryTimeout(int arg0) throws SQLException {
		queryTimeOut = arg0;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setWithNull() {
		this.arr = new ArrayList<>();
		this.dbms = new DataBaseImpl();
	}

}
