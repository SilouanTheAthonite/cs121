import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Animated program with a ball bouncing off the program boundaries
 * @author CS121 Instructors
 * @author mvail
 * @author Bryce Younger
 */
@SuppressWarnings("serial")
public class BouncyBall extends JPanel
{
	private final int INIT_WIDTH = 600;
	private final int INIT_HEIGHT = 400;
	private final int DELAY = 60;       // milliseconds between Timer events

	private Random random;              // random number generator
	private Color color;                // initial ball color

	private int x, y;                   // ball anchor point coordinates
	private int xDelta, yDelta;         // change in x and y from one step to the next

	private int radius = 10;            // ball radius

	/**
	 * Draws a filled bouncy ball that stays within the bounds of the screen.
	 *
	 * @param g Graphics context
	 */
	public void paintComponent(Graphics g)
	{
		int width = getWidth();
		int height = getHeight();

		// Clear canvas
		g.setColor(getBackground());
		g.fillRect(0, 0, width, height);

		// Calculate new x anchor point value
		x += xDelta;
		// TODO: Add conditional statements to keep ball in bounds
		//       in the x axis (e.g. left and right edges)
        if  (x >= (width - radius)){ // @keyterm, conditional statement
			x = (width - radius);
            xDelta = -xDelta;
        }
		else if	(x <= (0 + radius)){
			x = (0 + radius);
			xDelta = -xDelta;
		}


		// Calculate new y anchor point value
		y += yDelta;
		// TODO: Add conditional statements to keep ball in bounds
		//       in the y axis (e.g. top and bottom edges)
		if	(y >= (height - radius)){ // @keyterm, relational operator
			y = (height - radius); // @keyterm, nested statement
			yDelta = -yDelta;
		}
		else if	(y <= (0 + radius)){ // @keyterm, equality and/or inequality
			y = (0 + radius);
			yDelta = -yDelta;
		}

		
		// Paint the ball at the calculated anchor point
		g.setColor(color);
		g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);

		// Makes the animation smoother
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins.
	 * This method also sets up a Timer that will call paintComponent() 
	 * with frequency specified by the DELAY constant.
	 */
	public BouncyBall()
	{
		setPreferredSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));
		setDoubleBuffered(true);
		setBackground(Color.black);

		 // Instantiate instance variable for reuse in paintComponent()
		random = new Random();

		float r = random.nextFloat();
		float g = random.nextFloat();
		float b = random.nextFloat();

		Color randomColor = new Color (r, g, b);
		// Initialize ball color
		// TODO: replace with random starting color.
		color = randomColor;

		// Initialize ball anchor point within panel bounds
		// TODO: replace centered starting point with a random
		// position anywhere in-bounds - the ball should never
		// extend out of bounds, so you'll need to take RADIUS
		// into account. Use INIT_WIDTH and INIT_HEIGHT as the
		// screen width and height.
		x = INIT_WIDTH/2;
		y = INIT_HEIGHT/2;

		// Initialize deltas for x and y
		xDelta = 5;
		yDelta = 5;

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically. DO NOT MODIFY.
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

	/**
	 * Starting point for the BouncyBall program. DO NOT MODIFY.
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Bouncy Ball");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BouncyBall());
		frame.pack();
		frame.setVisible(true);
	}
}
