package eparking.utils;

import java.util.ArrayList;
import java.util.List;
import eparking.models.GroupMember;

public class RootData {
	public final static String AppTitle;
	public final static String sourcePath;
	public final static String version;
	public final static List<GroupMember> groupList = new ArrayList<>();
	
	static {
		AppTitle = "eParking";
		sourcePath = "/assets/";
		version = "1.0.0";
		
		// GroupMember		
		groupList.add(new GroupMember("i202501544 - ", "Luis Angel Salcedo Gavidia"));
		groupList.add(new GroupMember("i202502761 - ", "Jarumi EstelaCapcha Pariona"));
		groupList.add(new GroupMember("i202502813 - ", "Diana Miluska Gonzales Pariona"));
		groupList.add(new GroupMember("i202502899 - ", "Sara SabinaArias Gutierrez"));
		groupList.add(new GroupMember("i202221187 - ", "Jesus Rivera Melendez"));
	}
}
