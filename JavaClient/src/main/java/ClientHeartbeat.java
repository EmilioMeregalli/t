import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class ClientHeartbeat extends Thread{

	
	
	public void run() {
		
		String name = Client.frame.textField.getText();
		Client.name = name;
		UUID uuid = UUID.nameUUIDFromBytes(name.getBytes(StandardCharsets.UTF_8));
		
		try(ZContext context = new ZContext()){
			Socket req = context.createSocket(SocketType.REQ);
			req.connect("tcp://ds.iit.his.se:5556");
			String temp = "{\r\n"
					+ "    \"clientId\": \""+ uuid +"\",\r\n"
					+ "    \"name\": \""+ name +"\",\r\n"
					+ "    \"enterQueue\": true\r\n"
					+ "}";
			req.send(temp.getBytes(ZMQ.CHARSET), 0);
			req.recv();
			while(!Thread.currentThread().isInterrupted()) {
				temp = "{\r\n"
						+ "    \"clientId\": \""+ uuid +"\"\r\n"
						+ "}";
				req.send(temp.getBytes(ZMQ.CHARSET), 0);
				req.recv();
				Thread.sleep(200);
				System.out.println("a");
			}
			req.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
