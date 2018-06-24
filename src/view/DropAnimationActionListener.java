package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

import model.Constants;
import model.GameField;

public class DropAnimationActionListener extends Observable implements ActionListener {
	
	private int row;
	private GameFieldView[][] view;
	private int column;
	private GameField field;
	
	
	public DropAnimationActionListener(int row, GameFieldView[][] view, int column, GameField field) {
		super();
		this.row = row;
		this.view = view;
		this.column = column;
		this.field = field;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		setChanged();
		if(row < Constants.NUM_ROWS && view[row][column-1].getCurrentValue()==GameField.FREE) {
			if(row -1 !=-1) {
				view[row-1][column-1].resetGameField();
			}
			view[row][column-1].setGameField(field);
			row++;
		}
		else {
			((Timer)arg0.getSource()).stop();
			notifyObservers();
		}
		
	}

}
