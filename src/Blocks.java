import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Blocks{
	public JLabel picture;
	public boolean Destroyable;
	public static int Frequency = 3;
	int Width, Height;
	public Blocks(int X, int Y, int Width, int Height, boolean Destroyable)
	{
		this.Destroyable = Destroyable;
		this.Width = Width;
		this.Height = Height;
		picture = new JLabel();
		picture.setBounds(X, Y, Width, Height);
		picture.setHorizontalTextPosition(SwingConstants.CENTER);
		picture.setVerticalTextPosition(SwingConstants.BOTTOM);	
		
		if(Destroyable == true)
			Weak_Block_Builder();
		else
			Strong_Block_Builder();
	}
	public void Weak_Block_Builder()
	{
		picture.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Weak_Block.gif")).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT)));
	}
	public void Strong_Block_Builder()
	{
		picture.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Strong_Block.gif")).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT)));
	}
}
