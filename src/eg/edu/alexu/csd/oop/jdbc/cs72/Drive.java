package eg.edu.alexu.csd.oop.jdbc.cs72;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class Drive implements Driver {

	// 2D array to add properties and save them;
	ConnectionPool pool;

	public Drive() {
		this.pool = new ConnectionPool();
	}

	/**
	 * Retrieves whether the driver thinks that it can open a connection to the given URL. Typically
	 * drivers will return true if they understand the sub-protocol specified in the URL and false if
	 * they do not. Parameters: url - the URL of the database Returns: true if this driver understands
	 * the given URL; false otherwise Throws: SQLException - if a database access error occurs or the
	 * url is null
	 */
	@Override
	public boolean acceptsURL(String arg0) throws SQLException {
		return true;
		// validates URL;
		// checking path handeled in DBMS

	}

	/**
	 * Attempts to make a database connection to the given URL. The driver should return "null" if it
	 * realizes it is the wrong kind of driver to connect to the given URL. This will be common, as
	 * when the JDBC driver manager is asked to connect to a given URL it passes the URL to each
	 * loaded driver in turn. //don't care
	 *
	 * The driver should throw an SQLException if it is the right driver to connect to the given URL
	 * but has trouble connecting to the database. // must handle
	 *
	 * The Properties argument can be used to pass arbitrary string tag/value pairs as connection
	 * arguments. Normally at least "user" and "password" properties should be included in the
	 * Properties object. // Ahmed Hamdy asked for path
	 *
	 * Note: If a property is specified as part of the url and is also specified in the Properties
	 * object, it is implementation-defined as to which value will take precedence. For maximum
	 * portability, an application should only specify a property once.
	 *
	 * Parameters: url - the URL of the database to which to connect info - a list of arbitrary string
	 * tag/value pairs as connection arguments. Normally at least a "user" and "password" property
	 * should be included. Returns: a Connection object that represents a connection to the URL
	 * Throws: SQLException - if a database access error occurs or the url is null // must handle
	 */

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		//      if (!acceptsURL(url)) {
		//        throw new SQLException();
		//      }
		final File dir = (File) info.get("path");
		final String path = dir.getAbsolutePath();
		//      String path = "/home/saraheldafrawy/git/oop/databases/sarah";
		return new ConnectAdapter(path, pool);
		//	return pool.getConnection(path);
	}

	/**
	 * Gets information about the possible properties for this driver.
	 *
	 * The getPropertyInfo method is intended to allow a generic GUI tool to discover what properties
	 * it should prompt a human for in order to get enough information to connect to a database. Note
	 * that depending on the values the human has supplied so far, additional values may become
	 * necessary, so it may be necessary to iterate though several calls to the getPropertyInfo
	 * method.
	 *
	 * Parameters: url - the URL of the database to which to connect info - a proposed list of
	 * tag/value pairs that will be sent on connect open Returns: an array of DriverPropertyInfo
	 * objects describing possible properties. This array may be an empty array if no properties are
	 * required. Throws: SQLException - if a database access error occurs
	 *
	 * DriverPropertyInfo(String name, String value) Constructs a DriverPropertyInfo object with a
	 * given name and value.
	 */

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {

		final Set<Object> set = info.keySet();
		final Object[] obj = set.toArray();
		final DriverPropertyInfo[] arr = new DriverPropertyInfo [info.size()];
		int i = 0;
		while(i < info.size()) {
			arr[i] = new DriverPropertyInfo((String)obj[i], (String)info.get(obj[i]));
			i++;
		}
		return arr;
	}

	@Override
	public int getMajorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMinorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException();
	}


	@Override
	public boolean jdbcCompliant() {
		throw new UnsupportedOperationException();
	}

}
