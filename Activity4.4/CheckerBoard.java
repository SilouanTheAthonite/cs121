import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Lesson 11: Activity - for Loops
 * 
 * @author CS121 Instructors
 * @version Spring 2023 
 * @author Bryce Younger 
 */
@SuppressWarnings("serial")
public class CheckerBoard extends JPanel
{
	private static final int NUM_ROWS = 8;
	private static final int NUM_COLS = 8;

	/**
	 * Sets the initial dimensions of the panel. 
	 */
	public CheckerBoard()
	{
		setPreferredSize(new Dimension(500, 500));
	}

	/**
	 *  Draws the checker board.
	 *  @param page Graphics context
	 */
	public void paintComponent(Graphics page)
	{
		int width = getWidth();
		int height = getHeight();

		int boxWidth = (int) Math.ceil((double) width/NUM_COLS);
		int boxHeight = (int) Math.ceil((double) height/NUM_ROWS);
        
        for (int j = 0; j <= NUM_COLS; j++)
        // @keyterm, initial conditions; terminating conditions
        {
            for (int i = 0; i <= NUM_ROWS; i++) // @keyterm, nested loops
            {
                if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 == 1) && (j % 2 == 1))) // @keyterm, conditional
                {
                    page.setColor(Color.RED);
		            page.fillRect((j * boxWidth), (i * boxHeight), boxWidth, boxHeight);
                }   
                else if (((i % 2 == 1) && (j % 2 == 0)) || ((i % 2 == 0) && (j % 2 == 1)))
                {
                    page.setColor(Color.black);
                    page.fillRect((j * boxWidth), (i * boxHeight), boxWidth, boxHeight);
                }
            
            }
        }
        
		
	}

	/**
	 * Creates the main frame of the program.
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Checker Board");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CheckerBoard panel = new CheckerBoard();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
