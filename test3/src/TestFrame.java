

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class TestFrame extends JFrame implements WindowListener{
	
	public TestFrame(){
		super();
		addWindowListener(this);
		
		getContentPane().add(new JScrollPane(new GraphicsAlgoPanel()));
		
		setSize(new Dimension(800, 600));
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestFrame frame = new TestFrame();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("....exiting application...!");
		System.exit(0);
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("....exiting application...!");
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
