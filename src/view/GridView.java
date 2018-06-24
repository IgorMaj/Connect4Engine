package view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import exception.IllegalMove;
import model.Constants;
import model.GameField;
import model.GameGrid;
import model.Turn;

public class GridView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3751842034064001042L;
	
	private GameGrid gameGrid;
	private GameFieldView[][] view;
	private GridLayout gLayout;
	
	private Turn turn;
	
	private MainWindow parent;
	
	
	public GridView(MainWindow p) {
		gameGrid = new GameGrid();
		turn = Turn.PLAYER_1;
		parent = p;
		setUI();
	}
	
	public GridView(GameGrid g,MainWindow p) {
		gameGrid = g;
		turn = Turn.PLAYER_1;
		parent = p;
		setUI();
	}
	
	public void nextTurn() {
		if(turn==Turn.PLAYER_1) {
			turn = Turn.PLAYER_2;
			return;
		}
		turn = Turn.PLAYER_1;
	}
	
	private void setUI() {
		
		view = new GameFieldView[Constants.NUM_ROWS][Constants.NUM_COLS];
		gLayout = new GridLayout(Constants.NUM_ROWS,Constants.NUM_COLS);
		gLayout.setHgap(0);
		gLayout.setVgap(0);
		setOpaque(false);
		
		this.setLayout(gLayout);
		for(int i=0;i<Constants.NUM_ROWS;i++) {
			
			for(int j=0;j<Constants.NUM_COLS;j++) {
				view[i][j] = new GameFieldView(gameGrid.getGrid()[i][j]);
				this.add(view[i][j]);
			}
		}
		
	}
	
	public void performMove(int column,Turn player) {
		
		try {
			
			gameGrid.performMove(column, player);
		} catch (IllegalMove e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
		}
		performMoveAnimation(column,player);
	}
	
	

	private void performMoveAnimation(int column, Turn player) {
		int row = 0;
		GameField field = GameGrid.turnToGameField(player);
		DropAnimationActionListener listener = new DropAnimationActionListener(row, view,column,field);
		listener.addObserver(this);
		Timer t = new Timer(Constants.WAIT_TIME,listener);
		t.start();
		
	}

	public GameGrid getGrid() {
		
		return gameGrid;
	}
	
	public Turn getTurn() {
		return turn;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(this.getGrid().checkVictory(this.getTurn())) {
			JOptionPane.showMessageDialog(this, Constants.victoryMessage(this.getTurn()));
			return;
		}
		if(this.getGrid().gridFilled()) {
			JOptionPane.showMessageDialog(this, Constants.boardFilledMessage());
			return;
		}
		
		nextTurn();
		parent.setButtonsStatus(true);
		
	}

}
