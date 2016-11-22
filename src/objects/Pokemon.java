package objects;

public class Pokemon {
	private int pid;
	private String pname;
	private int cp;
	private int HP;
	private String pokeball;
	
	
	public Pokemon(int pid, String pname, int cp, int hP, String pokeball) {
		this.pid = pid;
		this.pname = pname;
		this.cp = cp;
		HP = hP;
		this.pokeball = pokeball;
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
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the cp
	 */
	public int getCp() {
		return cp;
	}
	/**
	 * @param cp the cp to set
	 */
	public void setCp(int cp) {
		this.cp = cp;
	}
	/**
	 * @return the hP
	 */
	public int getHP() {
		return HP;
	}
	/**
	 * @param hP the hP to set
	 */
	public void setHP(int hP) {
		HP = hP;
	}
	/**
	 * @return the pokeball
	 */
	public String getPokeball() {
		return pokeball;
	}
	/**
	 * @param pokeball the pokeball to set
	 */
	public void setPokeball(String pokeball) {
		this.pokeball = pokeball;
	}

}
