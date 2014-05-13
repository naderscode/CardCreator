//Nader K
//Project 6
//November, 2004



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Locale;
import java.text.NumberFormat;



public class Project6 extends JFrame implements ActionListener, /*ItemListener,*/ ListSelectionListener, ChangeListener
{
	private JLabel titleLabel,sayLabel,avlblLabel,sizesLabel,checkLabel,fontLabel,cardLabel,prevLabel;
	private JButton compButton, clearButton, exitButton,clickButton;
	private JRadioButton siz1JButton, siz2JButton, siz3JButton,siz4JButton,siz5JButton;
	private String[] sizes= {"3x5","4x6","6x8","8x10","16x20"};
	private float[] sicosts= {0.5f,1.0f,1.5f,3.0f,5.0f};
	private float[] costs ={2.0f,3.0f,3.5f,4.0f,5.0f,6.0f,7.5f,8.5f,9.5f};
	private float sfactor;
	private String[] sayings ={"Happy Birthday!","You're the Greatest!!","Have a Great Trip","Just Saying Hi!","Friends Forever","Congratulations","Put 'em up","You Did It!","Happy Days!"};
	private Icon[] imageicon = new ImageIcon[9];
	private String cardpics[] = {"Boxed","Waving","Pop up Wave","Fighting","Painting","magnify.gif","Snoozing","Surfing","Thumbs Up"};
	private String cardpics2[] = {"box.gif","duke.gif","boxwave.gif","fight.gif","painting.gif","magnify.gif","snooze.gif","urfing","yhumbsup.gif"};

	private JList sayList, imageList;
	private int[] sayindex;
	private int cardindex;
	private JSlider fontSlider;
	private ButtonGroup radioGroup;
	private JPanel northPanel, southPanel, eastPanel, westPanel, centerPanel;
	private Font plainFont, boldFont, italicFont, titleFont;
	private Color custcolor, pinkColor, yellowColor,peachColor,greenColor, blueColor, purpleColor;
	private Container c;
	private BorderLayout layout;
	private boolean order, sizeSelected, fontSelected,imageSelected, saySelected;
	private int fontsize;
	private float price;
	NumberFormat moneyfmt = NumberFormat.getCurrencyInstance(Locale.US);

	public static void main(String[]args)
		{
			Project6 p6 = new Project6();
			p6.setSize(800,600);
			p6.setVisible(true);
			p6.addWindowListener(new WindowAdapter()
				{
				  	 public void windowClosing(WindowEvent e)
				  	 {
						System.exit(0);
				  	 }
				});


		}

	public Project6()
	{

	c= getContentPane();
	setTitle("Project6 by Nader Kishek");
	layout = new BorderLayout();
	c.setLayout(layout);
	plainFont = new Font("TimesRoman", Font.PLAIN, 18);
	boldFont = new Font("TimesRoman", Font.BOLD, 14);
	italicFont = new Font("TimesRoman", Font.ITALIC,24);
	titleFont = new Font("Serif", Font.BOLD, 40);
	pinkColor = new Color(250,138,170);
	yellowColor = new Color(239,213,21);
	peachColor = new Color(210,127,94);
	greenColor = new Color(137,193,133);
	blueColor = new Color(170,143,245);
	purpleColor = new Color(206,144,188);
	imageicon[0] = new ImageIcon("box.gif");
	imageicon[1] = new ImageIcon("duke.gif");
	imageicon[2] = new ImageIcon("boxwave.gif");
	imageicon[3] = new ImageIcon("fight.gif");
	imageicon[4] = new ImageIcon("painting.gif");
	imageicon[5] = new ImageIcon("magnify.gif");
	imageicon[6] = new ImageIcon("snooze.gif");
	imageicon[7] = new ImageIcon("surfing.gif");
	imageicon[8] = new ImageIcon("thumbsup.gif");


	northPanel  = new JPanel();
	northPanel.setBackground(blueColor);
	titleLabel = new JLabel(" Duke's Great Greetings! ");
	northPanel.add(titleLabel);
	titleLabel.setFont(titleFont);
	c.add(northPanel, BorderLayout.NORTH);

	southPanel  = new JPanel();
	southPanel.setBackground(greenColor);
	setVisible(true);
	c.add(southPanel, BorderLayout.SOUTH);
	fontLabel = new JLabel("  Select Font Size");
	southPanel.add(fontLabel);
	fontLabel.setFont(italicFont);
	fontSlider = new JSlider(SwingConstants.HORIZONTAL,5,19,5);
	fontSlider.setMajorTickSpacing(2);
	fontSlider.setPaintTicks(true);
	fontSlider.setPaintLabels(true);
	fontSlider.addChangeListener(this);
	southPanel.add(fontSlider);

	eastPanel   = new JPanel();
	eastPanel.setBackground(peachColor);
	eastPanel.setLayout(new GridLayout(12,1));
	c.add(eastPanel, BorderLayout.EAST);
	avlblLabel = new JLabel("  Available  ");
	eastPanel.add(avlblLabel);
	avlblLabel.setFont(italicFont);
	sizesLabel = new JLabel("  Card Sizes  ");
	eastPanel.add(sizesLabel);
	sizesLabel.setFont(italicFont);
	checkLabel = new JLabel("  Check Selections ");
	checkLabel.setFont(italicFont);
	eastPanel.add(checkLabel);
	siz1JButton = new JRadioButton(sizes[0],false);
	eastPanel.add(siz1JButton);
	siz2JButton = new JRadioButton(sizes[1],false);
	eastPanel.add(siz2JButton);
	siz3JButton = new JRadioButton(sizes[2],true);
	eastPanel.add(siz3JButton);
	siz4JButton = new JRadioButton(sizes[3],false);
	eastPanel.add(siz4JButton);
	siz5JButton = new JRadioButton(sizes[4],false);
	eastPanel.add(siz5JButton);
	siz1JButton.addActionListener(this);
	siz2JButton.addActionListener(this);
	siz3JButton.addActionListener(this);
	siz4JButton.addActionListener(this);
	siz5JButton.addActionListener(this);


	radioGroup = new ButtonGroup();
	radioGroup.add(siz1JButton);
	radioGroup.add(siz2JButton);
	radioGroup.add(siz3JButton);
	radioGroup.add(siz4JButton);
	radioGroup.add(siz5JButton);

	compButton = new JButton(" Complete Order ");
	compButton.setToolTipText( "Complete Order" );
	compButton.setMnemonic(KeyEvent.VK_C);
	eastPanel.add(compButton);
	compButton.addActionListener(this);

	clearButton = new JButton(" Clear ");
	clearButton.setToolTipText( "Clear" );
	clearButton.setMnemonic(KeyEvent.VK_L);
	eastPanel.add(clearButton);
	clearButton.addActionListener(this);

	exitButton = new JButton(" Exit ");
	exitButton.setToolTipText( "Exit" );
	exitButton.setMnemonic(KeyEvent.VK_E);
	eastPanel.add(exitButton);
	exitButton.addActionListener(this);

	westPanel   = new JPanel();
	westPanel.setBackground(pinkColor);
	westPanel.setLayout(new GridLayout(4,1));
	c.add(westPanel, BorderLayout.WEST);
	sayLabel = new JLabel("  Sayings  ");
	westPanel.add(sayLabel);
	sayLabel.setFont(italicFont);
	sayList = new JList(sayings);
	westPanel.add(sayList);
	JScrollPane scroller = new JScrollPane(sayList);
	scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	westPanel.add(scroller);
	sayList.setVisibleRowCount(5);
	sayList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	sayList.addListSelectionListener(this);

	centerPanel = new JPanel();
	centerPanel.setBackground(yellowColor);
	centerPanel.setLayout(new GridLayout(2,2));
	cardLabel = new JLabel("  Card Selection");
	cardLabel.setFont(italicFont);
	cardLabel.setForeground(blueColor);
	centerPanel.add(cardLabel);
	imageList = new JList(imageicon);
	centerPanel.add(imageList);
	JScrollPane scroller2 = new JScrollPane(imageList);
	scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	centerPanel.add(scroller2);
	imageList.setVisibleRowCount(2);
	imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	imageList.addListSelectionListener(this);

	clickButton = new JButton("  Click to select mat color and Preview Card ");
	centerPanel.add(clickButton);
	clickButton.addActionListener(this);
	prevLabel = new JLabel("");
	prevLabel.setVisible(false);
	centerPanel.add(prevLabel);
	c.add(centerPanel, BorderLayout.CENTER);

	}

public void calcPrice()
{	if(!fontSelected)
		{
		JOptionPane.showMessageDialog(null,
		"Choose Font Size", "alert", JOptionPane.ERROR_MESSAGE);
		}

	if(!imageSelected)
		{
		JOptionPane.showMessageDialog(null,
		"Choose an Image", "alert", JOptionPane.ERROR_MESSAGE);
		}

	if(!saySelected)
		{
			JOptionPane.showMessageDialog(null,
			"Choose Saying(s)", "alert", JOptionPane.ERROR_MESSAGE);
		}
	if(!sizeSelected)
		{
			JOptionPane.showMessageDialog(null,
			"Choose Card Size", "alert", JOptionPane.ERROR_MESSAGE);
		}




			else

		//if((sizeSelected==true) && (imageSelected== true) && (saySelected==true))
		{
			price = (sfactor * costs [cardindex]);
			prevLabel.setVisible(true);
			prevLabel.setText(""+ cardpics[cardindex]+"  " +sizes[cardindex]+"  "+"Price is: "+ moneyfmt.format(price));
			prevLabel.setIcon(imageicon[cardindex]);
			prevLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			prevLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
			prevLabel.setFont(new Font("Serif", Font.PLAIN,fontsize));

		//

		}
}



public void actionPerformed(ActionEvent e)
{

	if ( e.getSource() == siz1JButton )
	{
			sizeSelected = true;
	        sfactor=sicosts[0] ;
	}
	if ( e.getSource() == siz2JButton )
	{
			sizeSelected = true;
			sfactor=sicosts[1] ;
	}
	if ( e.getSource() == siz3JButton )
	{
			sizeSelected = true;
			sfactor=sicosts[2] ;
	}
	if ( e.getSource() == siz4JButton )
	{
			sizeSelected = true;
			sfactor=sicosts[3] ;
	}
	if ( e.getSource() == siz5JButton )
	{
			sizeSelected = true;
			sfactor=sicosts[4] ;
	}

	if(e.getSource() == compButton)

		calcPrice();
	if (e.getSource() == clickButton)
		{
			if(!fontSelected)
			{
					JOptionPane.showMessageDialog(null,
					"Choose Font Size", "alert", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			DeskTopJDialog jd = new DeskTopJDialog(cardindex,sayindex, fontsize,
        			sayings, cardpics2);
			//jd.setVisible(true);
			//jd.setSize(640,460);
			jd.show();

			
			}//else
		}


	if(e.getSource() == clearButton)
	{
		sayList.clearSelection();
		imageList.clearSelection();
		siz1JButton.setSelected(false);
		siz2JButton.setSelected(false);
		siz3JButton.setSelected(true);
		siz4JButton.setSelected(false);
		siz5JButton.setSelected(false);
		prevLabel.setVisible(false);
	}


	if(e.getSource() == exitButton)
		System.exit(0);
}

public void valueChanged(ListSelectionEvent lse)
{
	 sayindex = sayList.getSelectedIndices();
	 saySelected = true;
	 cardindex = imageList.getSelectedIndex();
	 imageSelected = true;
}

public void stateChanged(ChangeEvent e)
{
	JSlider fontSlider = (JSlider)e.getSource();
	fontSelected=true;
	fontsize = fontSlider.getValue();
}





}//end class


class DeskTopJDialog extends JDialog implements ActionListener
{
        Container c = getContentPane();
        private JDesktopPane theDesktop;	//declare globally
        private Color color = Color.lightGray;
        private int index;
        private int[] sayindex;
        private int size;
        private String[]sayings;
        private String[]cardpics2;
		private JButton clrBtn;
       //now set up the JDialog constructor passing in the appropriate arguments


	public DeskTopJDialog(int index,int[] sayindex, int size,
        			String[] sayings, String[] cardpics2)
    {
		//To create in the JDialog constructor:
		theDesktop = new JDesktopPane();

		this.index = index;
		this.sayindex = sayindex;    //and so on
		this.size = size;
		this.sayings = sayings;
		this.cardpics2= cardpics2;


		//BorderLayout layout = new BorderLayout();
		//c.setLayout(layout);

		clrBtn = new JButton("Choose Color");
		c.add(clrBtn,BorderLayout.NORTH);
		clrBtn.addActionListener(this);
		c.add( theDesktop );

		setSize(800,600);
		setVisible(true);

	}


	public void actionPerformed( ActionEvent event )
 	{
          if (event.getSource() == clrBtn)
          {

		  //show a JColorChooser
		color = JColorChooser.showDialog(null,"Choose a color", color);
		if (color==null)
		{
			color = Color.lightGray;
		}
			// now, create an internal frame to show the sample card in
			    JInternalFrame frame = new JInternalFrame("Internal Frame", true, true, true, true );
				// attach panel to internal frame content pane
			   Container container = frame.getContentPane();

			   MyJPanel panel = new MyJPanel(index,color,sayindex,size,sayings,cardpics2);
			   container.add( panel, BorderLayout.CENTER );

			   // set size internal frame to size of its contents
			   frame.pack();

			   // attach internal frame to desktop and show it
			   theDesktop.add( frame );
			   frame.setVisible( true );
				//frame.setSize(640,480);
		}
	} //end actionPerformed

}//jdial

class MyJPanel extends JPanel
{
	  //globally declare local names for each parameter and any other local variables you want
	private int index, size;
	private int[] sayindex;
	String[] sayings;
	String[] cardpics2;
	private ImageIcon imageIcon;
	private Color color;
	Graphics g = getGraphics();

//write the constructor
  public MyJPanel(int index, Color color,int[]sayindex,int size, String[]sayings, String[]cardpics2)
 {
	this.index = index;
	System.out.println("pic index" +index);
	this.sayindex = sayindex;
	this.size = size;
	this.sayings = sayings;
	this.cardpics2= cardpics2;
	this.color= color;
	imageIcon = new ImageIcon( cardpics2[ index ] );

}

public void paintComponent(Graphics g)
{
    	super.paintComponent(g);
    	setBackground(color);

        //create Font object with correct size (not shown)
        Font bFont = new Font("TimesRoman", Font.BOLD, size);
       	g.setFont(bFont);

        //call paintICon method:
       	imageIcon.paintIcon( this, g, imageIcon.getIconWidth(), imageIcon. getIconHeight());
       //use loop to print all sayings, print just below the locations above (not shown)
       for(int i=0; i< sayindex.length;i++)
       		g.drawString(sayings[sayindex[i]],imageIcon.getIconWidth(), imageIcon. getIconHeight()*2+(i+1)*20);
}

//method to get icon sizes
public Dimension getPreferredSize()
   {
       return new Dimension( imageIcon.getIconWidth()*3, imageIcon.getIconHeight()*3 );
   }



}//jpanel




