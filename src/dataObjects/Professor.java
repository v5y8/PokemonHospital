package dataObjects;

import java.util.ArrayList;
import java.util.List;

public class Professor {
	private String professor_name;
	
	
	public Professor(String name) {
		this.professor_name = name;
	}

	/**
	 * @return the professor_name
	 */
	public String getProfessor_name() {
		return professor_name;
	}

	/**
	 * @param professor_name the professor_name to set
	 */
	public void setProfessor_name(String professor_name) {
		this.professor_name = professor_name;
	}

}
