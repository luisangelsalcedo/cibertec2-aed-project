package eparking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import eparking.enums.Permission;
import eparking.interfaces.IUserDAO;
import eparking.models.User;

public class UserDAO implements IUserDAO {
	
	private String jdbcURL = "jdbc:sqlite:eparkeing.db";
	
	private Connection connect() throws SQLException {
        return DriverManager.getConnection(jdbcURL);
    }
    
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "userName TEXT UNIQUE NOT NULL, "
                + "password TEXT NOT NULL, "
                + "permission TEXT NOT NULL, "
                + "loginAttempt INTEGER NOT NULL DEFAULT 0,"
                + "userLock INTEGER NOT NULL DEFAULT 0"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada o ya existente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
    	
        String sql = "SELECT * FROM users";        

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String permStr = rs.getString("permission");
                int loginAttempt = rs.getInt("loginAttempt");
                boolean lock = rs.getInt("userLock") == 1;

                User user = new User();
                user.setName(name);
                user.setUserName(userName);
                user.setPassword(password);
                user.setPermission(Permission.fromName(permStr));
                user.setUserLock(lock);
                user.loginAttempt = loginAttempt;
                userList.add(user);
            }             
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }        
        return userList;
	}

	@Override
	public User findUserByUserName(String userName) {
		String sql = "SELECT * FROM users WHERE userName = ?"; 
		
		try (Connection conn = connect();
			 PreparedStatement pstmt = conn.prepareStatement(sql) ) {
			
			pstmt.setString(1, userName);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				User userFound;
				int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String permissionName = rs.getString("permission");
                int loginAttempt = rs.getInt("loginAttempt");
                boolean userLock = rs.getInt("userLock") == 1 ? true : false;
                
                Permission permission = Permission.fromName(permissionName);
                
                if(permission.equals(Permission.ADMIN)) {
                	userFound = User.createAdmin(userName, password);
                } else {
                	userFound = User.createUser(userName, password);
                }
                userFound.setId(id);
                userFound.setName(name);
                userFound.setUserLock(userLock);
                userFound.loginAttempt = loginAttempt;                
                return userFound;
            }			
		} catch (SQLException e) {
            System.out.println("Error al buscar usuario por nombre de usuario: " + e.getMessage());
		}
		
		return null;
	}
	
	@Override
	public User findUserById(int id) {
		String sql = "SELECT * FROM users WHERE id = ?"; 
		
		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(sql) ) {
				
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					User userFound;					
	                String name = rs.getString("name");
	                String userName = rs.getString("userName");
	                String password = rs.getString("password");
	                String permissionName = rs.getString("permission");
	                int loginAttempt = rs.getInt("loginAttempt");
	                boolean userLock = rs.getInt("userLock") == 1 ? true : false;
	                
	                Permission permission = Permission.fromName(permissionName);
	                
	                if(permission.equals(Permission.ADMIN)) {
	                	userFound = User.createAdmin(userName, password);
	                } else {
	                	userFound = User.createUser(userName, password);
	                }
	                userFound.setId(id);
	                userFound.setName(name);
	                userFound.setUserLock(userLock);
	                userFound.loginAttempt = loginAttempt;                
	                return userFound;
	            }			
			} catch (SQLException e) {
	            System.out.println("Error al buscar usuario por nombre de usuario: " + e.getMessage());
			}
		
		return null;
	}

	@Override
	public void insertUser(User user) {
		String sql = "INSERT INTO users(name, userName, password, permission) VALUES(?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getPermission().toString());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
        }
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE users SET name = ?, password = ?, permission = ?, loginAttempt = ?, userLock = ? " 
					+ "WHERE userName = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getPermission().toString());
            pstmt.setInt(4, user.loginAttempt);
            pstmt.setInt(5, user.isUserLock() ? 1 : 0);
            pstmt.setString(6, user.getUserName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
	}
}
