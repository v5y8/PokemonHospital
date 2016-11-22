package dataObjects;

import java.util.ArrayList;
import java.util.List;

public class Nurse {
	private int nid;
	private String hname;
	
	
	public Nurse(int nid, String hname) {
		this.nid = nid;
		this.hname = hname;
	}

	/**
	 * @return the nid
	 */
	public int getNurse_id() {
		return nid;
	}

	/**
	 * @param nid the nid to set
	 */
	public void setNurse_id(int nid) {
		this.nid = nid;
	}

	/**
	 * @return the hospital name
	 */
	public String getNurseHospital_name() {
		return hname;
	}

	/**
	 * @param hname the hname to set
	 */
	public void setNurseHospital_name(String hname) {
		this.hname = hname;
	}
}
