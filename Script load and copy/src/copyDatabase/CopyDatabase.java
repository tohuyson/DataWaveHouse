package copyDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.PreparedStatement;

public class CopyDatabase {
	public static void copyTableData() throws SQLException {
		Connection connSoure = MySQLConnectionSoure.connect();
		Connection connDest = MySQLConnectionDest.connect();

		String sqlSoure = "SELECT * FROM st";
		String sqlDest = "CREATE TABLE student(Number varchar(255),Name varchar(255), Gender varchar(255),Identitycard varchar(255), Email varchar(255),Phone varchar(255),Address varchar(255))";

		PreparedStatement preparedStatementSoure = (PreparedStatement) connSoure.prepareStatement(sqlSoure);
		PreparedStatement preparedStatementDest = (PreparedStatement) connDest.prepareStatement(sqlDest);

		ResultSet resultSetSoure = preparedStatementSoure.executeQuery();
		preparedStatementDest.execute();
		
		PreparedStatement preparedStatementIntoDest = (PreparedStatement) connDest.prepareStatement("INSERT INTO student(number,name, gender,identitycard, email,phone,address) VALUES (?,?,?,?,?,?,?)");
		while (resultSetSoure.next()) {
			preparedStatementIntoDest.setString(1, resultSetSoure.getString(1));
			preparedStatementIntoDest.setString(2, resultSetSoure.getString(2));
			preparedStatementIntoDest.setString(3, resultSetSoure.getString(3));
			preparedStatementIntoDest.setString(4, resultSetSoure.getString(4));
			preparedStatementIntoDest.setString(5, resultSetSoure.getString(5));
			preparedStatementIntoDest.setString(6, resultSetSoure.getString(6));
			preparedStatementIntoDest.setString(7, resultSetSoure.getString(7));
			preparedStatementIntoDest.addBatch();
		}
		preparedStatementIntoDest.executeBatch();
		
	}
	
	

	public static void main(String[] args) {
		try {
			copyTableData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
