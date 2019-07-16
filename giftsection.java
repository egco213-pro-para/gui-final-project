import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class giftsection extends JFrame
{
    private JPanel        contentpane;
    private MyPane	  drawpane;
    private JButton	  add_button, rem_button ,sendheart_button;
    private JComboBox	  combo,combow;
    private JRadioButton  [] radio;
     private String [] writername = {"Name A", "Name B", "Name C", "Name D", "Name E", "Name F"};
    
    
    private Integer [] items = {1, 2, 3, 4};

    public giftsection()
    {
	setTitle("This is a Pet's Frame");
	setBounds(300, 200, 700, 400);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new FlowLayout() );
	AddComponents();
    }

    public void AddComponents()
    {
	// ----- (1) anonymous listener classes (implicitly implement listeners)
        String local = "local";
        final String finlocal = "final local";
        
	add_button = new JButton("Add");
	add_button.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                java.util.Date date = new java.util.Date();
                date.setTime(e.getWhen());
                System.out.println(e.getActionCommand() + " >>> " + date.toString());

                System.out.printf("%s %s \n", local, finlocal);
                
                drawpane.doAdd();
            }
        });

        // local = "new value";
        sendheart_button = new JButton("Send");
        
	rem_button = new JButton("Remove");
	rem_button.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
		drawpane.doRemove();
            }
	});


	combo = new JComboBox(items);
        combow = new JComboBox(writername);
	combo.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e )
            {
		int index = combo.getSelectedIndex();
		drawpane.setCount( items[index] );
            }
	});


	drawpane = new MyPane();
		
        contentpane.add(combow);
	contentpane.add(add_button);
	contentpane.add(rem_button);
	contentpane.add(combo);			
        contentpane.add(sendheart_button);
	contentpane.add(drawpane);

	validate();
    }


    public static void main(String[] args) 
    {
	new giftsection();
    }
};

////////////////////////////////////////////////////////////////////////////////////

// ----- (1) a pane for drawing birds

class MyPane extends JPanel
{
    private java.util.Random  random;
    private int		      count = 1;

    private String[] heart = {"red-heart.png", "blue-heart.png", "green-heart.png", 
		              "purple-heart.png", "orange-heart.png", "yellow-heart.png"};

    public MyPane()
    {
	setPreferredSize( new Dimension(1100, 766) );
	random = new java.util.Random();	
    }

    public void setCount( int c )	{ count = c; }

    public void doAdd()
    {
	for (int i = 0; i < count; i++)
	{
            int r = random.nextInt(6);
            JLabel label = new JLabel( new ImageIcon(heart[r]) );
            add(label);
            validate();
	}
    }

    public void doRemove()
    {
        for (int i = 0; i < count; i++)
        {
            if (getComponentCount() > 0)
            {
                Component clabel = getComponent(0);
                remove( clabel );

                // ----- (4) try with and without validate
                		
                repaint();	
            }
        }
    }
};
