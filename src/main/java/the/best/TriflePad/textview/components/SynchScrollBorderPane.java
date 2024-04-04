package the.best.TriflePad.textview.components;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class SynchScrollBorderPane extends BorderPane{
	
	private ScrollPane textArea;
	private ScrollPane vBox;
	
	public SynchScrollBorderPane(ScrollPane centralScrollPane, ScrollPane leftScrollPane) {
		super();
		this.textArea = centralScrollPane;
		this.vBox = leftScrollPane;
		
		this.setCenter(centralScrollPane);
		this.setLeft(leftScrollPane);
		
		textArea.vvalueProperty().bindBidirectional(vBox.vvalueProperty());
	}

}
