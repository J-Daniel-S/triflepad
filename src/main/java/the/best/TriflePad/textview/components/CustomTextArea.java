package the.best.TriflePad.textview.components;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class CustomTextArea extends TextArea {
	
	private ScrollPane scrollPane;

	public CustomTextArea() {
		super();
		initializeScrollPane();
	}

	public CustomTextArea(String text) {
		super(text);
		initializeScrollPane();
	}
	
    private void initializeScrollPane() {
        // Access the ScrollPane inside the TextArea
        scrollPane = (ScrollPane) this.lookup(".scroll-pane");
        if (scrollPane != null) {
            // Customize the ScrollPane behavior or perform any other initialization
            // For example:
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

	public double getVerticalScrollBarPosition() {
		// Access the ScrollPane inside the TextArea
		ScrollPane scrollPane = (ScrollPane) this.lookup(".scroll-pane");
		if (scrollPane != null) {
			// Retrieve the position of the vertical scrollbar
			return scrollPane.getVvalue();
		}
		return 0.0; // Default position if the ScrollPane is not found
	}
	
    public ScrollPane getScrollPane() {
        return scrollPane;
    }
}
