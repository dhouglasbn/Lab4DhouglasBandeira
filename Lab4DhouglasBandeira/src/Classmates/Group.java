package Classmates;

import java.util.HashSet;

public class Group {
	
	private String name;

	private HashSet<Classmate> classmatesList;
	
	private int membersLimit;

	public Group(String name) {
		this.name = name;
		this.classmatesList = new HashSet<>();
		this.membersLimit = -1;
	}
	
	public Group(String name, int membersLimit) {
		this.name = name;
		this.classmatesList = new HashSet<>();
		this.membersLimit = membersLimit;
	}
	
	public String getName() {
		return name;
	}
	
	public void addClassmateOnList(Classmate classmate) {
		this.classmatesList.add(classmate);
	}
	
	public boolean isClassmateOnGroup(Classmate classmate) {
		return this.classmatesList.contains(classmate);
	}
}
