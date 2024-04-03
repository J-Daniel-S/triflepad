package the.best.TriflePad.textview;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import the.best.TriflePad.textdocument.TextDocument;

public class TextView {
	
	private TextArea field;
	private TextDocument doc;
	private VBox numberBox;
	
	private static final String fontStyle = "-fx-font-family: monospace;";
	private static final String fontSize = "-fx-font-size: 14px;";
	private static final String padding = "-fx-padding: 0 5 0 5;";
	
	
	
	public TextView() {
		field = new TextArea();
		initArea();
		initNumBox();
	}
	
	private void initNumBox() {
		numberBox = new VBox();
		numberBox.getStyleClass().add("line-number-box");
//		numberBox.setStyle(fontStyle);
//		numberBox.setStyle(fontSize);
		setLineBoxStyles();
		field.textProperty().addListener((o, old, nV) -> updateLineNumbers());
	}
	
	private void updateLineNumbers() {
		numberBox.getChildren().clear();
		for (long i = 0; i <= doc.lineCount(); i++) {
			Label lineNo = new Label(Long.toString(i));
			lineNo.getStyleClass().add("line-number");
			numberBox.getChildren().add(lineNo);
		}
	}
	
	private void setLineBoxStyles() {
//		numberBox.setStyle(padding);
//		numberBox.setStyle("-fx-text-alignment: CENTER-RIGHT;");
	}
	
	private void initArea() {
//		field.setStyle(fontSize);
//		field.setStyle(fontStyle);
    	field.maxHeight(Long.MAX_VALUE);
    	field.maxWidth(Long.MAX_VALUE);
//    	field.setPadding(new Insets(0, 5, 0, 5));
    	pinScrollbarOnResize();
		field.setOnMouseClicked(c -> {
			
			// TODO init
			
		});
		dynamicallyEditText();
	}
	
	private void dynamicallyEditText() {
		field.textProperty().addListener((observable, oldValue, newValue) -> doc.updateBuffer(newValue));
	}
	
	public void assignDoc(TextDocument doc) {
		this.doc = doc;
		updateLineNumbers();
	}
	
	@SuppressWarnings("unused")
	private int getLineNumber(int caretPosition) {
		
		int lineNumber = 0;
		int currentPosition = 0;
		while (currentPosition <= caretPosition) {
			int nextNewLine = field.getText().indexOf('\n', currentPosition);
			if (nextNewLine == -1 || nextNewLine > caretPosition) {
				break;
			}
			lineNumber++;
			currentPosition = nextNewLine +1;
				
		}
		
		return lineNumber;
	}
	
	public Scene initWindowContents() {
    	MenuBar bar = new MenuBar();
    	Menu menu = new Menu("New");
    	Menu format = new Menu("Format");
    	
    	bar.getMenus().add(menu);
    	bar.getMenus().add(format);
    	
    	BorderPane bp = new BorderPane();
    	bp.setTop(bar);
    	bp.setCenter(field);
    	bp.setLeft(numberBox);
    	Scene scene = new Scene(new StackPane(bp), 800, 600);
    	scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    	return scene;
	}
	
	public TextArea get() {
		return field;
	}
	
	public VBox getNumberBox() {
		return numberBox;
	}

	private void pinScrollbarOnResize() {
		field.widthProperty().addListener((obs, oldVal, newVal) -> {
			field.setScrollLeft(0);
		});
	}
	
//	private static void writeToFile(String input) {
//		try (BufferedWriter w = new BufferedWriter(new FileWriter(PATH, false))) {
//			w.write(input);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
