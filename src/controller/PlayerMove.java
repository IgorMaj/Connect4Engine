package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainWindow;

public class PlayerMove implements ActionListener  {
	
	int column;
	private MainWindow window;
	
	public PlayerMove(MainWindow w,int col) {
		window = w;
		column = col;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		window.setButtonsStatus(false);
		window.getGridView().performMove(column, window.getGridView().getTurn());
		
		
	}


}
