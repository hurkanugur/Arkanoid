import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;

public class Screen extends JFrame implements KeyListener{
	private final int Block_Numbers = 15;
	private final int Life_Numbers = 3;
	private Blocks[] gameBlocks = new Blocks[Block_Numbers];
	private Life[] gameLife = new Life[Life_Numbers];
	private Jumper jumper;
	private Ball ball;
	private Score score;
	private Player player;
	private Options options;
	public static boolean Restart = false;
	private boolean Reverse = false;
	private boolean Go_Down = true;
	private boolean Go_Up = false;
	private boolean Go_Left = false;
	private boolean Go_Right = false;
	private int Screen_Width, Screen_Height;
	public Screen()
	{
		//MENU BAR SETTINGS
		options = new Options();
		setJMenuBar(options.menuBar);
		//OBJECT CREATING
		jumper = new Jumper(165,500,150, 20);
		ball = new Ball(50,300,70,70);
		score = new Score(47,579,55,55);
		//Screen Settings
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(485, 700);
		Screen_Width = this.getWidth();
		Screen_Height = this.getHeight();
		this.setContentPane(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("Screen.gif")).getImage().getScaledInstance(Screen_Width, Screen_Height, Image.SCALE_DEFAULT))));
		Create_Life();
		Create_Blocks();
		this.setTitle("Hurkanix Arkanoid");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		//ADDING MEMBERS OF JFRAME
		this.add(jumper.picture); 
		this.add(ball.picture);
		this.add(score.picture);
		this.add(score.scoreBoard);
		//EVENT OPERATIONS
		this.addKeyListener(this);
		//TIMER SETTINGS
		Game_Timer();
		Game_Music_Timer();
	}
	//BLOCKS ARE CREATED HERE
	private void Create_Blocks()
	{
		int X = 40, Y = 40;
		int Width = 80, Height = 40;
		int New_X = 0, New_Y = 0;
	    int Line_Number = 1;
	    int Push_Rate = 0;
		for(int i = 0; i < Block_Numbers; i++)
		{
			try
			{
				New_X = X * (Line_Number) + Push_Rate;
				New_Y = Y * Line_Number;
			
				if(New_X <= (this.getSize().width - (X * Line_Number + Width)))
				{
					if(i % Blocks.Frequency == 0)
						gameBlocks[i] = new Blocks(New_X, New_Y, Width, Height, false);
					else
						gameBlocks[i] = new Blocks(New_X, New_Y, Width, Height, true);
					
					this.add(gameBlocks[i].picture);
					Push_Rate = Push_Rate + Width;
				}	
				else
				{
					Line_Number++;
					Push_Rate = 0;
					i--;
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Block[" + i + "]: " + e.getClass().getCanonicalName());
			}
		}
	}
	//LIFE BOXES ARE CREATED HERE
	private void Create_Life()
	{
		int Width = 50, Height = 50;
		int X = 280, Y = 580;
		int New_X = 0;
	    int Push_Rate = 0;
		for(int i = 0; i < Life_Numbers; i++)
		{
			try
			{
				New_X = X + Push_Rate;
			
				if(New_X <= this.getSize().width)
				{
					gameLife[i] = new Life(New_X, Y, Width, Height);
					this.add(gameLife[i].picture);
					Push_Rate = Push_Rate + Width;
				}	
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Life[" + i + "]: " + e.getClass().getCanonicalName());
			}
		}
	}
	//GAME MUSIC PLAYS FOR EVER
	private void Game_Music_Timer()
	{
		Timer musicTimer = new Timer();
		TimerTask Task = new TimerTask()
		{
			@Override
			public void run() 
			{
				try
				{
				   player = new Player(new FileInputStream("Game.mp3"));
				   player.play();
				}
				catch(Exception e) { }
			}
		};
		musicTimer.schedule(Task, 0, 1000);
	}
	//HITTING BLOCKS AND GAME MOTIONS ARE CALCULATED HERE
	private void Game_Timer()
	{
		Timer gameTimer = new Timer();
		TimerTask Task = new TimerTask()
		{
			@Override
			public void run() 
			{
				//BALL SPEED SETTINGS
				if(score.points < 300)
					ball.Speed = 2;
				else if(score.points < 600)
					ball.Speed = 4;
				else if(score.points < 900)
					ball.Speed = 6;
				else
					ball.Speed = 8;
				
				// CHECK IF THE BALL HITS BLOCKS AND REBOUNDS
				for(Blocks detectBlocks : gameBlocks)
				{
					if(ball.picture.getBounds().intersects(detectBlocks.picture.getBounds()))
					{
						if(detectBlocks.picture.isVisible() == true)
						{
							if(detectBlocks.Destroyable == true)
							{
								score.points += 100; 
								score.scoreBoard.setText("" + score.points);
								detectBlocks.picture.setVisible(false);
							}
							else
							{
								detectBlocks.Destroyable = true;
								detectBlocks.Weak_Block_Builder();
							}
							
							if(Go_Up == true && Reverse == false)
							{
								Go_Up = false;
								Go_Right = true;
								Reverse = true;
							}		
							else if(Go_Up == true && Reverse == true)
							{
								Go_Up = false;
								Go_Left = true;
								Reverse = false;
							}
							else if(Go_Left == true && Reverse == false)
							{
								Go_Left = false;
								Go_Up = true;
								Reverse = true;
							}
							else if(Go_Right == true && Reverse == true)
							{
								Go_Right = false;
								Go_Up = true;
								Reverse = false;
							}
							break;
						}	
					}
				}
				//BALL GOES DOWN
				if(Go_Down == true)
				{
					if(Reverse == true)
						ball.Ball_Movement(-ball.Speed, ball.Speed);
					else
						ball.Ball_Movement(ball.Speed, ball.Speed);
					
					if(ball.picture.getBounds().intersects(jumper.picture.getBounds()))
					{
						if(Reverse == true)
						{
							Go_Up = false;
							Go_Down = false;
							Go_Left = true;
							Go_Right = false;
						}
						else
						{
							Go_Up = false;
							Go_Down = false;
							Go_Left = false;
							Go_Right = true;
						}
					}
				}
				//BALL GOES RIGHT
				if(Go_Right == true)
				{
					if(Reverse == true)
						ball.Ball_Movement(ball.Speed, ball.Speed);
					else
						ball.Ball_Movement(ball.Speed, -ball.Speed);
					if((ball.X + ball.Width) >= Screen_Width)
					{
						if(Reverse == true)
						{
							Go_Up = false;
							Go_Down = true;
							Go_Left = false;
							Go_Right = false;
						}
						else
						{
							Go_Up = true;
							Go_Down = false;
							Go_Left = false;
							Go_Right = false;
						}
					}
				}
				//BALL GOES UP
				if(Go_Up == true)
				{
					if(Reverse == true)
						ball.Ball_Movement(ball.Speed, -ball.Speed);
					else
						ball.Ball_Movement(-ball.Speed, -ball.Speed);
					if(ball.Y <= 0)
					{
						if(Reverse == true)
						{
							Go_Up = false;
							Go_Down = false;
							Go_Left = false;
							Go_Right = true;
						}
						else
						{
							Go_Up = false;
							Go_Down = false;
							Go_Left = true;
							Go_Right = false;
						}
					}
				}
				//BALL GOES LEFT
				if(Go_Left == true)
				{
					if(Reverse == true)
						ball.Ball_Movement(-ball.Speed, -ball.Speed);
					else
						ball.Ball_Movement(-ball.Speed, ball.Speed);
					if(ball.X <= 0)
					{
						if(Reverse == true)
						{
							Go_Up = true;
							Go_Down = false;
							Go_Left = false;
							Go_Right = false;
						}
						else
						{
							Go_Up = false;
							Go_Down = true;
							Go_Left = false;
							Go_Right = false;
						}
					}
				}
				// CHECK  <G A M E   O V E R>  OR  NOT
				if(((ball.X > Screen_Width) || (ball.Y > Screen_Height)) || ((score.points == (Block_Numbers * 100))) || Restart == true)
				{
					Life.Health --;
					if(Life.Health == 0 || (score.points == (Block_Numbers * 100)) || Restart == true)
					{
						gameLife[(Life_Numbers - 1) - Life.Health].picture.setVisible(false);
						if(Restart != true)
							JOptionPane.showMessageDialog(null, "G A M E   O V E R");
						Restart = false;
						Life.Health = Life_Numbers;
						score.points = 0;
						score.scoreBoard.setText("" + score.points);
						player.close();
						
						for(Blocks myBlock : gameBlocks)
							myBlock.picture.setVisible(false);
						
						Create_Blocks();
						
						for(Life myLife : gameLife)
							myLife.picture.setVisible(true);
					}
					else
					{
						gameLife[(Life_Numbers - 1) - Life.Health].picture.setVisible(false);
					}

					ball.X = 50;
					ball.Y = 300; 
					jumper.X = 165;
					jumper.Y = 500;
					ball.picture.setLocation(ball.X, ball.Y);
					jumper.picture.setLocation(jumper.X, jumper.Y);
					
					Reverse = false;
					Go_Up = false;
					Go_Down = true;
					Go_Left = false;
					Go_Right = false;
				}
			}
		};
		gameTimer.schedule(Task, 0, 10);
	}
	// KEY PRESSING EVENTS
	@Override
    public void keyPressed(KeyEvent e) 
    { 
          if((e.getKeyChar() == 'A' || e.getKeyChar() == 'a') && (jumper.X > 0))
               jumper.Jumper_Movement(-jumper.Speed, 0);
          if((e.getKeyChar() == 'D' || e.getKeyChar() == 'd') && ((jumper.X + jumper.Width) < Screen_Width - 6))
        	  jumper.Jumper_Movement(jumper.Speed, 0);
    }
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
}
