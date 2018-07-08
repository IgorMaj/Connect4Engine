package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

import controller.Heuristic;
import model.Options;

public class OptionsView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Options currentOptions;
	
	private JButton okButton = new JButton("Ok");
	private JButton cancelButton = new JButton("Cancel");
	
	private JFormattedTextField agent1Field;
	private JFormattedTextField agent2Field;
	
	private JComboBox<Heuristic> agent1ComboBox = new JComboBox<Heuristic>();
	private JComboBox<Heuristic> agent2ComboBox = new JComboBox<Heuristic>();
		
	public OptionsView(Options opt) {
		super();
		setTitle("Game options");
		setFixedSize(700, 250);
		currentOptions = opt;
		setElements();
		setUI();
		this.setLocationRelativeTo(getParent());
	}
	
	private void setElements() {
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    //formatter.setAllowsInvalid(false);
	    
	    agent1Field = new JFormattedTextField(formatter);
	    agent2Field = new JFormattedTextField(formatter);
	    agent1Field.setValue(currentOptions.getAgent1Depth());
	    agent2Field.setValue(currentOptions.getAgent2Depth());
	    
	    for(Heuristic o:Options.allHeuristics) {
	    	agent1ComboBox.addItem(o);
	    	agent2ComboBox.addItem(o);
	    }
	    agent1ComboBox.setSelectedItem(currentOptions.getAgent1Heuristic());
	    agent2ComboBox.setSelectedItem(currentOptions.getAgent2Heuristic());
	    okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					currentOptions.setAgent1Depth((int)agent1Field.getValue());
					currentOptions.setAgent2Depth((int)agent2Field.getValue());
					currentOptions.setAgent1Heuristic((Heuristic)agent1ComboBox.getSelectedItem());
					currentOptions.setAgent2Heuristic((Heuristic)agent2ComboBox.getSelectedItem());
					
					setVisible(false);
					setEnabled(false);
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Check your input values. Depths must be numbers.");
				}				
			}
	    	
	    });
	    
	    cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					setEnabled(false);
			}
	    	
	    });
	}
	
	private void setFixedSize(int i, int j) {
		this.setMinimumSize(new Dimension(i,j));
		this.setMaximumSize(new Dimension(i,j));
		
	}

	private void setUI() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20, 0, 20, 0);
		this.setLayout(layout);
		JPanel mainContainer = new JPanel();
		GridLayout gLayout = new GridLayout(2,4);
		gLayout.setHgap(30);
		gLayout.setVgap(10);
		mainContainer.setLayout(gLayout);

		mainContainer.add(new JLabel("Depth (Agent 1): "));
		mainContainer.add(agent1Field,c);
		mainContainer.add(new JLabel("Heuristic (Agent 1): "));
		mainContainer.add(agent1ComboBox);
		mainContainer.add(new JLabel("Depth (Agent 2): "));
		
		mainContainer.add(agent2Field,c);
		mainContainer.add(new JLabel("Heuristic (Agent2): "));
		mainContainer.add(agent2ComboBox);
		
		this.add(mainContainer, c);
		c.gridx = 2;
		this.add(Box.createVerticalGlue(),c);
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridx = GridBagConstraints.LAST_LINE_END;
		this.add(createConfirmPanel(),c);
	}
	
	public JPanel createConfirmPanel() {
		JPanel confirmPanel = new JPanel();
		BoxLayout layout = new BoxLayout(confirmPanel,BoxLayout.X_AXIS);
		confirmPanel.setLayout(layout);
		confirmPanel.add(okButton);
		confirmPanel.add(Box.createRigidArea(new Dimension(10,0)));
		confirmPanel.add(cancelButton);
		return confirmPanel;
	}

	public Options getCurrentOptions() {
		return currentOptions;
	}
	
	
}
