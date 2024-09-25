import org.json.JSONArray;
import org.json.JSONObject;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class ListenerThread extends Thread{
	private String tempName = "";
	@Override
	public void run() {
		try(ZContext context = new ZContext()){
			Socket sub = context.createSocket(SocketType.SUB);
			sub.connect("tcp://ds.iit.his.se:5555");
			sub.subscribe("queue");
			sub.subscribe("supervisors");
			
			String msg;
			
			
			while(!Thread.currentThread().isInterrupted()) {
				msg = new String(sub.recv(), ZMQ.CHARSET);
				if(msg.contains("queue")) {
					msg = new String(sub.recv(), ZMQ.CHARSET);
					System.out.println(msg);
					//System.out.println(msg);
					JSONArray jsonArr = new JSONArray(msg);
					String temp = "";
					for (int i = 0; i < jsonArr.length(); i++) {
						int j = i + 1;
			            JSONObject jsonObject = jsonArr.getJSONObject(i);
			            String name = jsonObject.getString("name");
			            if(Client.name.equals(name)) {
			            	sub.unsubscribe(tempName);
			            	tempName = name;
			            	temp += "*" + j + " - " + name + "*\n"; 
			            	sub.subscribe(name);
			            } else {
			            	temp += j + " - " + name + "\n";
			            }
					}
					Client.frame.queueTextArea.setText(temp);
				}else if(msg.contains("supervisors")) {
					msg = new String(sub.recv(), ZMQ.CHARSET);
					System.out.println(msg);
					JSONArray jsonArr = new JSONArray(msg);
					String temp = "";
					if(jsonArr.length() == 0) {
						temp = "no supervisors online";
					}else {
						for (int i = 0; i < jsonArr.length(); i++) {
				            JSONObject jsonObject = jsonArr.getJSONObject(i);
				            String name = jsonObject.getString("name");
				            String status = jsonObject.getString("status");
				            String client = jsonObject.getJSONObject("client").getString("name"); //probably wrong
				            
				            temp += i+1 + " - " + name + " - " + status +"\n";
						}
					}
					Client.frame.supervisorsTextArea.setText(temp);
				}
			 }
			
			
			
			
			
		
		}
	}

}
