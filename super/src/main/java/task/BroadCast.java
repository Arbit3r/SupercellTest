package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BroadCast {

	private ArrayList<String> broadcast = new ArrayList<String>();
	private String user;
	private int timestamp;
	private HashMap<String, String> values = new HashMap<String, String>();
	
	public BroadCast(ArrayList<String> broadcast, String user, int timestamp, HashMap<String, String> values) {
		super();
		this.broadcast = broadcast;
		this.user = user;
		this.timestamp = timestamp;
		this.values = values;
	}
	public BroadCast() {
		super();
	}
	public ArrayList<String> getBroadcast() {
		return broadcast;
	}
	public void setBroadcast(Set<String> broadcast) {
		this.broadcast = new ArrayList<>(broadcast);
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	public HashMap<String, String> getValues() {
		return values;
	}
	public void setValues(HashMap<String, String> values) {
		this.values = values;
	}

	

}
