package eparking.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import eparking.enums.Permission;
import eparking.models.User;

public class UserDAO_txt implements IUserDAO{
	
	private static final String filePath = "data/usuarios.txt";

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String txtOneLine = br.readLine(); 
	        while ((txtOneLine = br.readLine()) != null) {
	            String[] fields = txtOneLine.split(",");
	            if (fields.length >= 6) {
	            	
	                users.add(lineToUser(fields));
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error leyendo archivo: " + e.getMessage());
	    }
	    return users;
	}
	

	@Override
	public User findUserByUserName(String userName) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String txtOneLine = br.readLine(); // skip headers
	        while ((txtOneLine = br.readLine()) != null) {
	            String[] fields = txtOneLine.split(",");
	            if (fields.length >= 6 && fields[2].equals(userName)) {
	                return lineToUser(fields);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error buscando usuario por nombre de usuario: " + e.getMessage());
	    }
	    return null;
	}

	@Override
	public void insertUser(User user) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			user.setId(generarNuevoId());
			
			// userName must be unique
			if(findUserByUserName(user.getUserName()) == null) {		
				writer.write(userToLine(user));
				writer.newLine();
			} else System.out.println("el usuario " + user.getUserName() + " ya existe");
			
	    } catch (IOException e) {
	        System.out.println("Error guardando usuario: " + e.getMessage());
	    }
	}

	@Override
	public void updateUser(User user) {
	    try {
	        Path path = Paths.get(filePath);
	        List<String> originalLines = Files.readAllLines(path);
	        boolean wasFound = false;

	        if (originalLines.isEmpty()) return;

	        List<String> newLines = new ArrayList<>();
	        newLines.add(originalLines.get(0)); // add headers

	        for (int i = 1; i < originalLines.size(); i++) {
	            String txtOneLine = originalLines.get(i);
	            String[] fields = txtOneLine.split(",");

	            if (fields.length >= 6 && Integer.parseInt(fields[0]) == user.getId()) {
	                newLines.add(userToLine(user)); //add new line
	                wasFound = true;
	            } else {
	                newLines.add(txtOneLine); // add same line
	            }
	        }

	        if (!wasFound) {
	            System.out.println("No se encontró el usuario con ID: " + user.getId());
	        }
	        Files.write(path, newLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

	    } catch (IOException e) {
	        System.out.println("Error al actualizar usuario: " + e.getMessage());
	    }
	}


	private int generarNuevoId() {
	    List<User> users = getAllUsers();
	    if(!users.isEmpty()) {
	    	return users.get(users.size()-1).getId() + 1;
	    }
	    return 1;
	}
	
	private User lineToUser(String[] fields) {
		User user = new User();
        user.setId(Integer.parseInt(fields[0]));
        user.setName(fields[1]);
        user.setUserName(fields[2]);
        user.setPassword(fields[3]);
        user.setPermission(Permission.fromName(fields[4]));
        user.setLoginAttempt(Integer.parseInt(fields[5]));
        user.setUserLock(Boolean.parseBoolean(fields[6]));

        return user;
	}
	private String userToLine(User user) {
		return String.join(",",
                String.valueOf(user.getId()),
                user.getName(),
                user.getUserName(),
                user.getPassword(),
                user.getPermission().toString(),
                String.valueOf(user.getLoginAttempt()),
                String.valueOf(user.isUserLock())
        );
	}

}
