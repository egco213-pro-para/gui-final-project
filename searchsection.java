import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class searchsection extends JFrame
{
    private JPanel              contentpane;
    private JList		list;
    private DefaultListModel	listmodel; 
    private JButton		add_button, remove_button;
    private JTextField		text;

    public searchsection()
    {
	setTitle("This is a new Frame");
	setBounds(200, 200, 350, 250);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );

	AddComponents();
    }

    public void AddComponents()
    {
	text = new JTextField(15);
	text.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
	text.setEditable(false);

	// ----- (1) add empty model to list
	list = new JList( listmodel = new DefaultListModel() );
	list.setVisibleRowCount(10);
	list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	list.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged( ListSelectionEvent e )
            {
                // ----- (2) try with & without condition
		if ( !e.getValueIsAdjusting() )
		{
                    Object[] items = list.getSelectedValues();
                    if (items.length > 0) text.setText( " "+items[0]);
                    else                  text.setText("");
		}
            }
	});

	add_button  = new JButton("Add");
	add_button.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
		// ----- (3) add new item to the model
		String item = JOptionPane.showInputDialog("Enter name");
		listmodel.addElement(item);
            }
	});

	remove_button  = new JButton("Remove");
	remove_button.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
		// ----- (4) get selected items & remove them from the model
		Object x[] = list.getSelectedValues();
		for (int i=0; i < x.length; i++)
		{
                    listmodel.removeElement(x[i]);
		}
            }
	});

	JPanel mpanel = new JPanel();
	mpanel.add( new JScrollPane(list) );

	JPanel spanel = new JPanel();
	spanel.add(add_button);
	spanel.add(remove_button);

	contentpane.add( text, BorderLayout.NORTH );
	contentpane.add( mpanel, BorderLayout.CENTER );
	contentpane.add( spanel, BorderLayout.SOUTH );
	validate();
    }

    public static void main(String[] args) 
    {
	new searchsection();
    }
};
