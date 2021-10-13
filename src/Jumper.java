import java.awt.Color;
import javax.swing.JLabel;

public class Jumper {
	public JLabel picture;
	public int X, Y;
	public final int Width, Height;
	public int Speed = 10;
	public Jumper(int X, int Y, int Width, int Height)
	{
		this.X = X;
		this.Y = Y;
		this.Width = Width;
		this.Height = Height;
		picture = new JLabel();
		picture.setOpaque(true);
		picture.setBackground(Color.GRAY);
		picture.setBounds(X,Y,Width,Height);
	}
	public void Jumper_Movement(int increase_X, int increase_Y)
	{
		X += increase_X;
		Y += increase_Y;
		picture.setLocation(X,Y);
	}
}
