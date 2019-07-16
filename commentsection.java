import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class commentsection extends JFrame
{
    private JPanel          contentpane;
    private JComboBox     check;
    private JTextArea       text;
    private JButton         pot_button,close_button;
    private String [] check1 = {"Public", "All friends", "my friends", "private", "only me", "send one"};
        
        

    // ----- (1) message from previous frame
    private Object [] items   = null;
    private String    message = "";

    public commentsection()
    {
	setTitle("Comment Feat");
	setBounds(200, 200, 400, 400);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );
    }

    public void setCheckMessage( Object [] m )	{ items = m; }
    public void setTextMessage( String m )		{ message = m; }


    public void AddComponents()
    {
        
   

        
        
        pot_button = new JButton("Post");
        close_button = new JButton("Close");
        check = new JComboBox(check1);
        
	JPanel cpanel = new JPanel();
	cpanel.add( check );
        
        JPanel dpanel = new JPanel();
        dpanel.add(pot_button);
        dpanel.add(close_button);

	text = new JTextArea(message, 10, 40);
	text.setFont( new Font("SanSerif", Font.BOLD | Font.ITALIC, 20) );

       
	contentpane.add(cpanel, BorderLayout.NORTH);
        
        contentpane.add(dpanel);
	contentpane.add(new JScrollPane(text), BorderLayout.SOUTH);

	validate();
    }


    public static void main(String[] args) 
    {
	// ----- (2) test this frame independently
	commentsection frame = new commentsection();

	// ----- (3) suppose that messages are passed from previous frame
	frame.setTextMessage( "Type anything here" );
	

	frame.AddComponents();
    }
};
