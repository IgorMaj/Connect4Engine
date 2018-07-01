package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Agent;
import controller.AlphaBetaAgent;
import controller.DummyAgent;
import controller.MinimaxAgent;
import controller.PlayerAgent;
import controller.PlayerMove;
import model.Constants;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3172688540921699213L;
	private GridView gridView;
	
	private LinkedList<JButton> buttons = new LinkedList<JButton>();
	
	public MainWindow() {
		
		BorderLayout bLayout = new BorderLayout();
		this.setLayout(bLayout);
		setControlPanel();
		setGridView(new GridView(this));
		this.add(getGridView(),BorderLayout.CENTER);
		this.setSize(Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private void setControlPanel() {
		JPanel buttonPanel = new JPanel();
		GridLayout gLayout = new GridLayout(0,Constants.NUM_COLS);
		gLayout.setHgap(0);
		buttonPanel.setLayout(gLayout);
		JButton button;
		for(int i=1;i<=Constants.NUM_COLS;i++) {
			button = new JButton(""+i);
			int column = i;
			buttons.add(button);
			button.setEnabled(false);
			button.addActionListener(new PlayerMove(this,column));
			buttonPanel.add(button);
		}
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.add(new JLabel("Agent 1: "));
		JComboBox<Agent> box1 = new JComboBox<Agent>();
		setupComboxBox(box1);
		controlPanel.add(box1);
		
		JComboBox<Agent> box2 = new JComboBox<Agent>();
		setupComboxBox(box2);
		controlPanel.add(new JLabel("Agent 2: "));
		controlPanel.add(box2);
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridView.setAgents((Agent)box1.getSelectedItem(), (Agent)box2.getSelectedItem());
				startButton.setText("Restart");
				
			}});
		
		controlPanel.add(startButton);
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		
		containerPanel.add(controlPanel);
		containerPanel.add(buttonPanel);
		add(containerPanel,BorderLayout.NORTH);
	}
	
	private void setupComboxBox(JComboBox<Agent> box1) {
		box1.setEditable(false);
		box1.addItem(new DummyAgent());
		box1.addItem(new MinimaxAgent(4));
		box1.addItem(new AlphaBetaAgent(10));
		box1.addItem(new PlayerAgent());
		
	}


	public void setButtonsStatus(boolean enabled) {
		for(JButton b:buttons) {
			b.setEnabled(enabled);
		}
	}


	public GridView getGridView() {
		return gridView;
	}

	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}


}
