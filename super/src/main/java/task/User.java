package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class User {

	private String name;
	private int timestamp;
	private HashMap<String, String> values = new HashMap<String, String>();
	private HashMap<String, User> friends = new HashMap<String, User>();

	public User(String name) {
		super();
		this.name = name;
		this.timestamp = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setValues(String id, String value) {
		values.put(id, value);
	}

	public void addFriend(String name,User user) {
		if (friends.get(name) == null) {
			friends.put(name,user);
		}
	}
	
	public void removeFriend(String name) {
		if (friends.get(name) != null) {
			friends.remove(name);
		}
	}
	public Set<String> getFriends() {
		return friends.keySet();
		
	}

}
