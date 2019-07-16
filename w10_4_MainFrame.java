import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


class w10_4_MainFrame extends JFrame
{
    
    
    private JComboBox        combo1;
    private JToggleButton [] tb;
    private JPanel	contentpane;
    private JList	list,list2;
    private JTextArea	text;
    private JButton	print_button, draw_button, clear_button, more_button,button01, button02;;

    private String [] items = {"January", "February", "March", "April", "May", "June", 
		               "July", "August", "September", "October", "November", "December"};
    
    private String [] choice={"Romantic", "Fantasy", "Sci-fi","Detective","Horror","Adventure"};
    
    
    private String [] tag={"Long story","Short story","one-shot","special case"};
    
    
    private ButtonGroup      bgroup;
    private ButtonGroup      cgroup;

    // ----- (1) message to be passed to next frames
    private Object [] messageFromList;
    private String    messageFromText;

    // ----- (2) next frames
    giftsection sframe;
    commentsection pframe;
    searchsection lframe;


    public w10_4_MainFrame()
    {
	setTitle("This is the Frame");
	setBounds(500, 500, 1100, 950);
        setVisible(true);
	
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
       
        
	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );

	AddComponents();
        
    }
    
    

  
    public void AddComponents()
    {
        
        String [] item = new String[10];
	for (int i=0; i < 10; i++) item[i] = " --- item " + i + " ---";
        
        
	combo1 = new JComboBox( choice );
        
        button01 = new JButton("Click First");
	button02 = new JButton("Click Second");
        
	// ----- (3) TextArea + CaretEvent
	text = new JTextArea(10, 42);
	text.addCaretListener( new CaretListener() {
            public void caretUpdate( CaretEvent e )
            {
		messageFromText = text.getText();
            }
	});


	// ----- (4) List + ListSelectionEvent
	list = new JList( items );
        list2 = new JList( tag );
        list2.setVisibleRowCount(4);
	list.setVisibleRowCount(5);
	list.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged( ListSelectionEvent e )
            {
		if( !e.getValueIsAdjusting() )
		{
                    messageFromList = list.getSelectedValues();
		}
            }
	});


	// ---- (5) Button + ActionEvent : clearup TextArea and List
	clear_button = new JButton("Clear");
	clear_button.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) 
            {
		text.setText("");
		list.clearSelection();
            }
	});


	// ----- (6) Button + ActionEvent -> open SemanticEvent frame
	draw_button  = new JButton("Gift Feat");
	draw_button.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
		if (sframe == null) sframe = new giftsection();
		else                sframe.setVisible(true);
            }
        });


	// ----- (7) commentsection
	print_button = new JButton("Comment Feat");
	print_button.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
		pframe = new commentsection();
                
		messageFromText = text.getText();
		messageFromList = list.getSelectedValues();

		// ----- (7.1) pass messages to the next frame
		pframe.setTextMessage( messageFromText );
		pframe.setCheckMessage( messageFromList );
		pframe.AddComponents();
            }
	});
        
        // ------- (8) ListModel --->
        more_button = new JButton("Search");
        more_button.addActionListener( new ActionListener() {
        
        public void actionPerformed (ActionEvent e){
            if (lframe == null) lframe = new searchsection ();
		else                lframe.setVisible(true);
        }
        }
        );

        // ---------- (9) Dialog ----?
        
        ImageIcon image = new ImageIcon("fff.png");
        JLabel imageLabel = new JLabel(image); 
        imageLabel.setBounds(10, 10, 400, 400);
        imageLabel.setVisible(true);
        add(imageLabel);
        
        
        //************** " nPanel "*******************
        JPanel npanel = new JPanel();
        npanel.add(imageLabel);
	
        //**********************************
        
        
        //************** " oPanel "*******************
        JPanel opanel = new JPanel();
        opanel.setBounds(20, 900, 100, 200);
        opanel.setBackground( new Color(75, 147, 200) );
        opanel.add( new JScrollPane(list2) );
        
        BoxLayout boxlayout1 = new BoxLayout(opanel, BoxLayout.Y_AXIS);
        opanel.setLayout(boxlayout1);
        opanel.add(button01);
        opanel.add(button02);
        //**********************************
        
        //************** " mPanel "*******************
	JPanel mpanel = new JPanel();
        //---------" Button group in mpanel"-------
        bgroup  = new ButtonGroup();
	tb      = new JToggleButton[5];
        for (int i=0; i < 5; i++) 
	{ 
            tb[i] = new JRadioButton( choice[i] );
            bgroup.add( tb[i] );
            mpanel.add( tb[i] );
        }
        //---------------------------------------
	mpanel.add( new JScrollPane(text) );
	mpanel.add( new JScrollPane(list) );
        mpanel.setBackground( new Color(0, 120, 140) );
        //**********************************
        
        
        //************** " sPanel "*******************
	JPanel spanel = new JPanel();
        BoxLayout boxlayout2 = new BoxLayout(spanel, BoxLayout.Y_AXIS);
        spanel.setLayout(boxlayout2);
        
       //draw_button.setBounds(250, 250, 120, 35);
        spanel.setBorder(new EmptyBorder(new Insets(10,5,500,40)));
	spanel.add(draw_button);
	spanel.add(print_button);
	spanel.add(clear_button);
        spanel.add(more_button);
        spanel.add(combo1);
        spanel.setBackground( new Color(45, 220, 120) );
        spanel.setBounds(15, 150, 350, 400);
        //**********************************
               
	contentpane.add( spanel, BorderLayout.WEST);
        contentpane.add( opanel, BorderLayout.EAST);
	contentpane.add( mpanel, BorderLayout.CENTER );
        contentpane.add( npanel, BorderLayout.NORTH);
	validate();
    }


    public static void main(String[] args) 
    {
	// ----- (8) check look & feel
	UIManager.LookAndFeelInfo [] laf = UIManager.getInstalledLookAndFeels();
	System.out.println("===== available look and feel =====");
	for (int i=0; i < laf.length; i++)
	{
            System.out.println( laf[i].getClassName() );
	}

	// ----- (9) set look and feel
	try
	{
            String look1 = "javax.swing.plaf.metal.MetalLookAndFeel";
            String look2 = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            String look3 = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            String look4 = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";

            UIManager.setLookAndFeel(look1);
	}
	catch (Exception e) { System.out.println(e); }

	new w10_4_MainFrame();
    }
};
