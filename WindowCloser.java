import java.awt.*;
import java.awt.event.*;

public class WindowCloser extends WindowAdapter
{
	Window w;
	public void windowClosing(WindowEvent we1)
	{
		 w=we1.getWindow();
		w.setVisible(false);
		w.dispose();//destroy's frame

		//System.exit(1);

	}
	public void oo()
	{
			w.setVisible(false);
		w.dispose();
	}
}