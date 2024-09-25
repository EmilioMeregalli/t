import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textField;
	public JTextArea queueTextArea;
	public JTextArea supervisorsTextArea;
	public int clientId = 98467;
	private ClientHeartbeat c = new ClientHeartbeat();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Your name");
		lblNewLabel.setBounds(68, 11, 80, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(60, 28, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enter queue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.interrupt();
					c = new ClientHeartbeat();
					try {
						c.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clientId++;
				
			}
		});
		btnNewButton.setBounds(156, 27, 103, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Queue");
		lblNewLabel_1.setBounds(10, 88, 154, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Supervisors");
		lblNewLabel_2.setBounds(243, 88, 154, 14);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(243, 116, 154, 120);
		contentPane.add(scrollPane);
		
		supervisorsTextArea = new JTextArea();
		scrollPane.setViewportView(supervisorsTextArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 116, 154, 120);
		contentPane.add(scrollPane_1);
		
		queueTextArea = new JTextArea();
		scrollPane_1.setViewportView(queueTextArea);
		
		
	}
}
