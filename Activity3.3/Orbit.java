import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

/**
 * Animates and object orbiting around the Earth.
 *
 * @author CS121 Instructors
 * @author <you>
 */
@SuppressWarnings("serial")
public class Orbit extends JPanel
{
	private final int INIT_WIDTH = 600;
	private final int INIT_HEIGHT = 600;
	private final int DELAY = 100; //milliseconds
	Random generator = new Random();
	
	private final ImageIcon EARTH_ICON = new ImageIcon("earth.png");
	
	private int orbitTheta;
	private int orbitTDelta;
	
	/**
	 * Constructor. Sets the initial dimensions and starts the animation.
	 */
	public Orbit()
	{
		setBackground(Color.white);
		setPreferredSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));
		
		startAnimation();
		
		orbitTheta = 0;
		
		// TODO: Generate random theta delta value from -10 to 20.
		orbitTDelta = generator.nextInt(31) - 10; // @keyterm, pseudo-random
	}

	/**
	 * Display the square at a new location.
	 * @param page Graphics context
	 */
	public void paintComponent(Graphics page)
	{
		int width = getWidth();
		int height = getHeight();
		
		// Define earth radius and position.
		int earthRadius = Math.min(width/5, height/5);
		int earthX = width / 2;
		int earthY = height / 2;

		// HINT: If you end up with a solid circle of objects surrounding your earth instead of a 
		// single object smoothly circling it, try drawing (below) a filled rectangle to blank the 
		// window each time paintComponent() is called.
		
		// Draw earth
		page.fillRect(0, 0, width, height);
		page.drawImage(EARTH_ICON.getImage(), earthX - earthRadius, earthY - earthRadius, 
				    earthRadius * 2, earthRadius * 2, null);
		
		// TODO: Define orbit radius
		// HINT: What is the difference between radius and diameter?
		int orbitRadius = Math.min(width / 3, height / 3);
		
		// TODO: Draw orbit path
		page.setColor(Color.WHITE);
		page.drawOval(earthX - orbitRadius, earthY - orbitRadius, orbitRadius * 2, orbitRadius * 2);
		
		// TODO: Define the radius of your object
		int objectRadius = earthRadius / 5;
		
		// TODO: Calculate x and y using Math.sin and Math.cos.
		// HINT: The Math.sin and Math.cos methods use radians for the parameter units. orbitTheta is in degrees.  
		//       Try using the Math.toRadians() method to convert orbitTheta from degrees to radians. This will 
		//       smooth out the orbit of your object.
		double objectX = earthX + orbitRadius * Math.cos(Math.toRadians(orbitTheta)); // @keyterm, static method: Math
		double objectY = earthY + orbitRadius * Math.sin(Math.toRadians(orbitTheta));
		// TODO: Create a random color and draw your object as an oval with that random color.
		Color randomColor = new Color(generator.nextInt(256), generator.nextInt(256), generator.nextInt(256));
		page.setColor(randomColor); // @keyterm, dot operator
		page.fillOval((int)objectX - objectRadius, (int)objectY - objectRadius, objectRadius * 2, objectRadius * 2);
		// Add to theta (for animation)
		orbitTheta = orbitTheta + orbitTDelta;
		
		// Make the animation smoother
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * sets up a JFrame and the RandomMovement panel
	 * @param args unused
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Geocentric Orbit");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Orbit());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Create an animation thread that runs periodically
	 */
	private void startAnimation()
	{
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				repaint();
			}
		};
		new Timer(DELAY, taskPerformer).start();
	}
}
