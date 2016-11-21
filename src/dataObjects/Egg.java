package dataObjects;

import java.sql.Timestamp;

public class Egg {
	
	private Timestamp obtained;
	private int pid;
	private int tid;
	public Egg(Timestamp obtained, int pid, int tid) {
		super();
		this.obtained = obtained;
		this.pid = pid;
		this.tid = tid;
	}
	/**
	 * @return the obtained
	 */
	public Timestamp getObtained() {
		return obtained;
	}
	/**
	 * @param obtained the obtained to set
	 */
	public void setObtained(Timestamp obtained) {
		this.obtained = obtained;
	}
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}
	/**
	 * @return the tid
	 */
	public int getTid() {
		return tid;
	}
	/**
	 * @param tid the tid to set
	 */
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	
}
