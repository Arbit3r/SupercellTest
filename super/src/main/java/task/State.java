package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class State extends Thread {

	private final HashMap<String, User> players = new HashMap<String, User>();
	private String user1;
	private String user2;
	private JsonObject json = null;
	private BufferedReader reader = null;
	private Path path;
	private JsonParser parser = new JsonParser();
	private String argument = "";
	
	
	

	public State(String argument) {
		super();
		this.argument = argument;
	}

	@Override
	public void run() {
		
		synchronized(this) {
			path = Paths.get(argument);
	    }

		String line = "";
		try {
			reader = Files.newBufferedReader(path);
			while (reader.ready()) {
				line = reader.readLine();
				json = parser.parse(line).getAsJsonObject();
				;
				String type = json.get("type").getAsString();

				switch (type) {

				case "make_friends":
					make_friends();

					break;

				case "del_friends":
					del_friends();

					break;

				case "update":
					update();

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getPlayerValues();

	}

	private synchronized void make_friends() {
		user1 = json.get("user1").getAsString();
		user2 = json.get("user2").getAsString();

		if (!players.containsKey(user1)) {
			players.put(user1, new User(user1));
		}

		if (!players.containsKey(user2)) {
			players.put(user2, new User(user2));
		}

		players.get(user1).addFriend(user2, players.get(user2));
		players.get(user2).addFriend(user1, players.get(user1));
	}

	private synchronized void del_friends() {
		user1 = json.get("user1").getAsString();
		user2 = json.get("user2").getAsString();

		if (players.containsKey(user1) && players.containsKey(user2)) {
			players.get(user1).removeFriend(user2);
			players.get(user2).removeFriend(user1);
		}
	}

	private synchronized void update() {
		user1 = json.get("user").getAsString();
		int time = json.get("timestamp").getAsInt();
		JsonObject values = json.get("values").getAsJsonObject();
		List<String> keys = new ArrayList<>(values.keySet());

		if (players.get(user1) == null) {
			players.put(user1, new User(user1));
		}

		if (players.get(user1) != null && players.get(user1).getTimestamp() < time) {
			players.get(user1).setTimestamp(time);
			for (int i = 0; i < values.size(); i++) {
				players.get(user1).setValues(keys.get(i), values.get(keys.get(i)).getAsString());
			}

			if (!players.get(user1).getFriends().isEmpty()) {

				User u = players.get(user1);

				BroadCast broadcast = new BroadCast();
				broadcast.setBroadcast(u.getFriends());
				broadcast.setUser(u.getName());
				broadcast.setTimestamp(u.getTimestamp());
				broadcast.setValues(u.getValues());

				String g = new Gson().toJson(broadcast);
				System.out.println(g);

			}

		}
	}
	public void getPlayerValues() {
		Iterator<String> iterator = players.keySet().iterator();
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<HashMap> arr = new ArrayList<HashMap>();
		while (iterator.hasNext()) {
			String key = iterator.next();
			arr.add(players.get(key).getValues());
		}
		String s = g.toJson(arr);
		System.out.println(s);
	}

	

}
