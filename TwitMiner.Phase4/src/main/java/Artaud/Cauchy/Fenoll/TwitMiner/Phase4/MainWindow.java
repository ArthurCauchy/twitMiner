package Artaud.Cauchy.Fenoll.TwitMiner.Phase4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;;

public class MainWindow extends JFrame implements ActionListener {
	private JComboBox<String> sortCombo;
	
	private int nbTableRows = 0;
	
	private void initComponents() {
		JLabel sortLabel = new JLabel("Méthode de tri :");
		sortLabel.setLabelFor(sortCombo); // ???????? effet à vérifier
		
		sortCombo = new JComboBox<String>();
		sortCombo.addItem("Fréquence");
		sortCombo.addItem("Confiance");
		sortCombo.addItem("Lift");
		sortCombo.addActionListener(this);
		
		JTable resultTab = new JTable(nbTableRows, 3);
		resultTab.setAutoscrolls(true); // ???????? effet à vérifier
		resultTab.setEnabled(false); // enabled est peut-être pas la meilleur prop à toucher, empêche aussi le copier coller
		
		this.add(sortLabel);
		this.add(sortCombo);
		this.add(resultTab);
	}
	
	public MainWindow(String title, int entries) { // -------- A terme changer, non plus le nombre d'entrées mais la liste des assocs
		super(title);
		this.nbTableRows = entries;
		
		this.setSize(500, 500);
		this.setLocationRelativeTo(null); // vertical and horizontal center
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // mouai bof
		
		initComponents();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sortCombo) {
			System.out.println("action performed");
		}
	}
}