import java.awt.EventQueue;

public class Client {
	static MainFrame frame;
	public static String name="";
	public static String connection;

	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		ListenerThread l = new ListenerThread();
		l.start();
		
	}
	
}
	
	

