import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ball {
	public JLabel picture;
	public int X, Y;
	public final int Width, Height;
	public int Speed = 2;
	public Ball(int X, int Y, int Width, int Height)
	{
		this.X = X;
		this.Y = Y;
		this.Width = Width;
		this.Height = Height;
		picture = new JLabel();
		picture.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Ball.gif")).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT)));
		picture.setBounds(X, Y, Width, Height);
		picture.setHorizontalTextPosition(SwingConstants.CENTER);
		picture.setVerticalTextPosition(SwingConstants.BOTTOM);
	}
	public void Ball_Movement(int increase_X, int increase_Y)
	{
		X += increase_X; 
		Y += increase_Y;
		picture.setLocation(X,Y);
	}
}
