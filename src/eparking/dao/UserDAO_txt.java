package eparking.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import eparking.enums.Permission;
import eparking.interfaces.IUserDAO;
import eparking.models.User;

public class UserDAO_txt implements IUserDAO {
	
	private static final String filePath = "data/usuarios.txt";
	private final String headers = "Id,Name,UserName,Password,Permission,LoginAttempt,isUserLock";
	private List<User> userList;
	
	public UserDAO_txt() {
		userList = new ArrayList<>();
		loadDataFromFile();		
	}

	@Override
	public List<User> getAllUsers() {	    
	    return new ArrayList<>(userList);
	}
	
	@Override
	public User findUserByUserName(String userName) {
		return userList.stream().filter(user -> user.getUserName().equals(userName)).findFirst().orElse(null);
	}
	
	@Override
	public User findUserById(int id) {
		return userList.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
	}

	@Override
	public void insertUser(User user) {			
		user.setId(generateNewId());
		
		// userName must be unique
		if(findUserByUserName(user.getUserName()) == null) {		
			userList.add(user);
			writeDataToFile();
		} else System.out.println("el usuario " + user.getUserName() + " ya existe");
	}

	@Override
	public void updateUser(User user) {
		userList.replaceAll(current -> current.getId() == user.getId() ? user : current);
		writeDataToFile();
	}

	private int generateNewId() {	
	    return userList.stream().mapToInt(User::getId).max().orElse(0) + 1;
	}

	private void loadDataFromFile() {
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String txtOneLine = br.readLine(); 
	        while ((txtOneLine = br.readLine()) != null) {
	            String[] fields = txtOneLine.split(",");
	            
	            User user = new User();
	            user.setId(Integer.parseInt(fields[0]));
	            user.setName(fields[1]);
	            user.setUserName(fields[2]);
	            user.setPassword(fields[3]);
	            user.setPermission(Permission.fromName(fields[4]));
	            user.setLoginAttempt(Integer.parseInt(fields[5]));
	            user.setUserLock(Boolean.parseBoolean(fields[6]));
	            
	            userList.add(user);
	        }
	    } catch (IOException e) {
	        System.out.println("Error leyendo archivo: " + e.getMessage());
	    }		
	}

	private void writeDataToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(headers);
			for(User user:userList) {
				writer.newLine();
				writer.write(String.join(",",
		                String.valueOf(user.getId()),
		                user.getName(),
		                user.getUserName(),
		                user.getPassword(),
		                user.getPermission().toString(),
		                String.valueOf(user.getLoginAttempt()),
		                String.valueOf(user.isUserLock())
		        ));
			}			
	    } catch (IOException e) {
	        System.out.println("Error guardando usuario: " + e.getMessage());
	    }		
	}
}
