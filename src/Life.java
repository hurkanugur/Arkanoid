import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Life {
	public JLabel picture;
	public static int Health = 3;
	public Life(int X, int Y, int Width, int Height)
	{
		picture = new JLabel();
		picture.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Life.png")).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT)));
		picture.setBounds(X, Y, Width, Height);
		picture.setHorizontalTextPosition(SwingConstants.CENTER);
		picture.setVerticalTextPosition(SwingConstants.BOTTOM);
	}
}
