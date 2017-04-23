package Artaud.Cauchy.Fenoll.TwitMiner.Phase4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;;

public class MainWindow extends JFrame implements ActionListener {
	private JComboBox<String> sortCombo = null;
	private JButton fcButton = null;
	
	private JTable resultTab = null;
	
	private JFileChooser fc = new JFileChooser();
	
	private void initNorthPane() {
		JPanel north = new JPanel(new BorderLayout());
		north.setBorder(new EmptyBorder(0, 150, 0, 150));
		JLabel sortLabel = new JLabel("Méthode de tri :", JLabel.CENTER);
		sortLabel.setLabelFor(sortCombo);
		north.add(sortLabel, BorderLayout.NORTH);
		
		sortCombo = new JComboBox<String>();
		sortCombo.addItem("Lift");
		sortCombo.addItem("Confiance");
		sortCombo.addItem("Fréquence");
		sortCombo.addActionListener(this);
		north.add(sortCombo, BorderLayout.CENTER);
		
		this.add(north, BorderLayout.NORTH);
	}
	
	private void initCenterPane() {
		String[] columnNames = {"Association", "Lift", "Confiance", "Fréquence"};
		DefaultTableModel dataTableModel = new DefaultTableModel(null, columnNames);
		resultTab = new JTable(dataTableModel);
	    
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
		if (resultTab == null)
			return;
		((DefaultTableModel) resultTab.getModel()).addRow(new String[] {assoc, freq, conf, lift});
	}
	
	private void sortTable(String mode) {
		int colKey = 1;
		if (mode == "Lift")
			colKey = 1;
		else if (mode == "Confiance")
			colKey = 2;
		else if (mode == "Fréquence")
			colKey = 3;
			
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(resultTab.getModel());
		Comparator<String> comp = new Comparator<String>() {
	        public int compare(String s1, String s2)
	        {
	            return new Double(s1).compareTo(new Double(s2));
	        }
	    };
	    sorter.setComparator(1, comp);
	    sorter.setComparator(2, comp);
	    sorter.setComparator(3, comp);
		resultTab.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(colKey, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
	}
	
	public MainWindow(String title) {
		super(title);
		
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null); // vertical and horizontal center
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		initComponents();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sortCombo) {
			sortTable(sortCombo.getSelectedItem().toString());
		}
		else if (e.getSource() == fcButton) {
			System.out.println("choose a file !");
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedReader fileReader = new BufferedReader(new FileReader(fc.getSelectedFile()));
					String line;
				    while ((line = fileReader.readLine()) != null) {
				    	String assoc = line.split(" \\(")[0];
				    	String score = line.split(" \\(")[1];
				    	String lift = score.split("lift:")[1].split(" ")[0];
				    	String conf = score.split("conf:")[1].split(" ")[0];
				    	String freq = score.split("freq:")[1].split("\\)")[0];
				    	this.addRow(assoc, lift, conf, freq);
				    }
				    fileReader.close();
				    sortTable("Lift");
				} catch (FileNotFoundException err) {
					err.printStackTrace();
					System.exit(1);
				} catch (IOException err) {
					err.printStackTrace();
					System.exit(1);
				}
			}
		}
	}
}