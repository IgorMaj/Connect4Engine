package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import model.GameField;

public class GameFieldView extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2045540166223030451L;
	private static LinkedHashMap<GameField,Image> tilePaths;
	
	static {
		tilePaths = new LinkedHashMap<GameField,Image>();
		try {
			tilePaths.put(GameField.FREE, ImageIO.read(new File("res/free.png")));
			tilePaths.put(GameField.PLAYER_1, ImageIO.read(new File("res/player_1.png")));
			tilePaths.put(GameField.PLAYER_2, ImageIO.read(new File("res/Player_2.png")));
		}
		catch(IOException ex) {
			
		}
		
	}
	
	private GameField currentValue;

	public GameFieldView(GameField gameField) {
		super();
		currentValue = gameField;
		setGameField(gameField);
		
	}
	
	public void setGameField(GameField gameField) {
		currentValue = gameField;
		StretchIcon icon = new StretchIcon(tilePaths.get(gameField),false);
		this.setIcon(icon);
		
	}
	
	public GameField getCurrentValue() {
		return currentValue;
	}
	
	public void resetGameField() {
		setGameField(GameField.FREE);
	}

}
