package dataObjects;

import java.util.ArrayList;
import java.util.List;

public class Incubator {
	private int iid;
	
	
	public Incubator(int iid) {
		this.iid = iid;
	}

	/**
	 * @return the iid
	 */
	public int getIncubator_id() {
		return iid;
	}

	/**
	 * @param iid the iid to set
	 */
	public void setIncubator_id(int iid) {
		this.iid = iid;
	}
}
