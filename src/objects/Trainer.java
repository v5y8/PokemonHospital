package objects;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
	private int trainer_id;
	private String trainer_name;
	
	
	public Trainer(int tid, String name) {
		this.trainer_id = tid;
		this.trainer_name =name;
		//pokemon = new ArrayList<>();
	}

	/**
	 * @return the trainer_id
	 */
	public int getTrainer_id() {
		return trainer_id;
	}

	/**
	 * @param trainer_id the trainer_id to set
	 */
	public void setTrainer_id(int trainer_id) {
		this.trainer_id = trainer_id;
	}

	/**
	 * @return the trainer_name
	 */
	public String getTrainer_name() {
		return trainer_name;
	}

	/**
	 * @param trainer_name the trainer_name to set
	 */
	public void setTrainer_name(String trainer_name) {
		this.trainer_name = trainer_name;
	}

	
	

}
