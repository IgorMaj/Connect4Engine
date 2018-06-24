package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
		setGridView(new GridView(this));
		BorderLayout bLayout = new BorderLayout();
		this.setLayout(bLayout);
		setControlButtons();
		this.add(getGridView(),BorderLayout.CENTER);
		this.setSize(Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private void setControlButtons() {
		JPanel buttonPanel = new JPanel();
		GridLayout gLayout = new GridLayout(0,Constants.NUM_COLS);
		gLayout.setHgap(0);
		buttonPanel.setLayout(gLayout);
		JButton button;
		for(int i=1;i<=Constants.NUM_COLS;i++) {
			button = new JButton(""+i);
			int column = i;
			buttons.add(button);
			button.addActionListener(new PlayerMove(this,column));
			buttonPanel.add(button);
		}
		add(buttonPanel,BorderLayout.NORTH);
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
