import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Score {
	public JLabel picture;
	public JLabel scoreBoard;
	public int points = 0;
	public Score(int X, int Y, int Width, int Height)
	{
		picture = new JLabel();
		picture.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Score.png")).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT)));
		picture.setBounds(X, Y, Width, Height);
		picture.setHorizontalTextPosition(SwingConstants.CENTER);
		picture.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		scoreBoard = new JLabel("" + points);
		scoreBoard.setForeground(Color.YELLOW);
		scoreBoard.setFont(new Font("TimesRoman",Font.BOLD,28));
		scoreBoard.setBounds(X + Width + 10, Y, Width + 30, Height);
		scoreBoard.setHorizontalTextPosition(SwingConstants.CENTER);
		scoreBoard.setVerticalTextPosition(SwingConstants.BOTTOM);
	}
}
