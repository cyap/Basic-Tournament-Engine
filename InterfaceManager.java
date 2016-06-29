import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
Purpose: Draws / redraws user interface
*/

public class InterfaceManager
{
	/**
	Constructs the user interface
	@param
	*/

	public InterfaceManager() 
	{
	
	}

	public static void updateWindow(final JFrame frame)
	{	

		frame.pack();
		frame.setVisible(true);

	}
	
	
}
	