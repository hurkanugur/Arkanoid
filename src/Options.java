import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class Options {
	JMenuBar menuBar;
	JMenu settings;
	JMenuItem newGame;
	JMenuItem exitGame;
	HurkanListener handle = new HurkanListener();
	public Options()
	{
		menuBar = new JMenuBar();
		settings = new JMenu("S E T T I N G S");
		settings.setOpaque(true);
		settings.setBackground(Color.BLACK);
		settings.setForeground(Color.YELLOW);
		newGame = new JMenuItem(" R E S T A R T",new ImageIcon(new ImageIcon(getClass().getResource("Ball.gif")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		newGame.setBackground(Color.BLACK);
		newGame.setForeground(Color.YELLOW);
		newGame.setHorizontalTextPosition(SwingConstants.RIGHT);
		newGame.setVerticalTextPosition(SwingConstants.CENTER);
		exitGame = new JMenuItem("      E X I T",new ImageIcon(new ImageIcon(getClass().getResource("Ball.gif")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		exitGame.setBackground(Color.RED);
		exitGame.setForeground(Color.BLACK);
		exitGame.setHorizontalTextPosition(SwingConstants.RIGHT);
		exitGame.setVerticalTextPosition(SwingConstants.CENTER);
		settings.add(newGame);
		settings.add(exitGame);
		menuBar.add(settings);
		newGame.addActionListener(handle);
		exitGame.addActionListener(handle);
	}
	private class HurkanListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == Options.this.newGame)
			{
				Screen.Restart = true;
			}
			else if(e.getSource() == Options.this.exitGame)
			{
				System.exit(0);
			}
		}
	}
}
