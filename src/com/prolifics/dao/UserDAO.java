package com.prolifics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.prolifics.dto.UserDTO;
import com.prolifics.util.DBConnectionUtil;

/**
 * 
 * User Data Access Object contains database
 * operations related to User entity.
 */
public class UserDAO {
	/**
	 * getUserDetails is intended to return the User Profile
	 * corresponding to the specified user name.
	 * 
	 * @param userName
	 * 	<p>User Name to query for.</p>
	 * @return
	 * 	<p>The returned profile.</p>
	 */
	public UserDTO getUserDetails(String userName) {
		UserDTO dto = null;
		String sql = "select id,first_name, last_name, gender,phone, status from painscriptdb.user where username = ?";
		Connection con = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				System.out.println("====User Found=====");
				dto = new UserDTO();
				dto.setId(resultSet.getInt("id"));
				dto.setFirstName(resultSet.getString("first_name"));
				dto.setLastName(resultSet.getString("last_name"));
				dto.setGender(resultSet.getString("gender"));
				dto.setPhone(resultSet.getString("phone"));
				dto.setStatus(resultSet.getString("status"));
			}else {
				System.out.println("====User Missing=====");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.closeResultSet(resultSet);
			DBConnectionUtil.closePreparedStatement(preparedStatement);
			DBConnectionUtil.closeConnection(con);
		}
		return dto;
	}

}
