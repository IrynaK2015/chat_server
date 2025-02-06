package academy.prog;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
	private static final MessageList msgList = new MessageList();

    private final Gson gson;
	private final List<Message> list = new LinkedList<>();
	
	public static MessageList getInstance() {
		return msgList;
	}
  
  	private MessageList() {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}
	
	public synchronized void add(Message m) {
		list.add(m);
	}


	public synchronized String toJSON(String user, int n) {
		List<Message> userList = list.stream()
			.filter(msg ->  msg.getTo() == null || msg.getTo().equals(user) || !msg.getFrom().equals(user))
			.collect(Collectors.toList());
		if (n < 0 || n >= userList.size()) return null;

		return gson.toJson(new JsonMessages(userList, n));
	}
}
