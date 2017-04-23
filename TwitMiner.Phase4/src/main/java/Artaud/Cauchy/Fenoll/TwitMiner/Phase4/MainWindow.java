package Artaud.Cauchy.Fenoll.TwitMiner.Phase4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;;

public class MainWindow extends JFrame implements ActionListener {
	private JComboBox<String> sortCombo = null;
	private JButton fcButton = null;
	
	private DefaultTableModel dataTableModel = null;
	
	private JFileChooser fc = new JFileChooser();
	
	private void initNorthPane() {
		JPanel north = new JPanel(new BorderLayout());
		north.setBorder(new EmptyBorder(0, 150, 0, 150));
		JLabel sortLabel = new JLabel("Méthode de tri :", JLabel.CENTER);
		sortLabel.setLabelFor(sortCombo);
		north.add(sortLabel, BorderLayout.NORTH);
		
		sortCombo = new JComboBox<String>();
		sortCombo.addItem("Fréquence");
		sortCombo.addItem("Confiance");
		sortCombo.addItem("Lift");
		sortCombo.addActionListener(this);
		north.add(sortCombo, BorderLayout.CENTER);
		
		this.add(north, BorderLayout.NORTH);
	}
	
	private void initCenterPane() {
		String[] columnNames = {"Association", "Fréquence", "Confiance", "Lift"};
		dataTableModel = new DefaultTableModel(null, columnNames);
		JTable resultTab = new JTable(dataTableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
	    
		JScrollPane centerScroll = new JScrollPane(resultTab);
		
		this.add(centerScroll, BorderLayout.CENTER);
	}
	
	private void initSouthPane() {
		JPanel south = new JPanel(new BorderLayout());
		fcButton = new JButton("Pick a file");
		fcButton.addActionListener(this);
		south.add(fcButton, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
	}
	
	private void initComponents() {
		initNorthPane();
		
		initCenterPane();
		
		initSouthPane();
	}
	
	private void addRow(String assoc, String freq, String conf, String lift) {
		if (dataTableModel == null)
			return;
		dataTableModel.addRow(new String[] {assoc, freq, conf, lift});
	}
	
	public MainWindow(String title) {
		super(title);
		
		this.setSize(500, 500);
		this.setLocationRelativeTo(null); // vertical and horizontal center
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		initComponents();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sortCombo) {
			System.out.println("action performed");
		}
		else if (e.getSource() == fcButton) {
			System.out.println("choose a file !");
			addRow("ceci", "est", "un", "test");
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				
			}
		}
	}
}