package the.best.TriflePad.textview;

import java.util.Set;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import the.best.TriflePad.textdocument.TextDocument;

public class TextView {

	private TextArea field;
	private TextDocument doc;
	private VBox numberBox;
	private ScrollPane textAreaScrollPane;
	private ScrollPane numberBoxScrollPane;

	public TextView() {
		field = new TextArea();
//		initArea();
		initNumBox();
	}

	private void initNumBox() {
		numberBox = new VBox();
		numberBox.getStyleClass().add("line-number-box");
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
	
    private String getLineNumbers(String text) {
        StringBuilder numbers = new StringBuilder();
        String[] lines = text.split("\n");
        for (int i = 1; i <= lines.length; i++) {
            numbers.append(i).append("\n");
        }
        return numbers.toString();
    }

	private void initArea() {

		field.setOnMouseClicked(c -> {

			// TODO init

		});
		
	}
	
//	public void resetScroll() {
//		textAreaScrollPane.setVvalue(0);
//	}

	public void assignDoc(TextDocument doc) {
		this.doc = doc;
//		updateLineNumbers();
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
			currentPosition = nextNewLine + 1;

		}

		return lineNumber;
	}

	public Scene initWindowContents() {
		MenuBar bar = new MenuBar();
		Menu menu = new Menu("New");
		Menu format = new Menu("Format");
		bar.getMenus().addAll(menu, format);

		BorderPane bp = new BorderPane();
		bp.setTop(bar);
		
//		textAreaScrollPane = new ScrollPane(field);
//	    textAreaScrollPane.setFitToWidth(true);
//	    textAreaScrollPane.setFitToHeight(true);
//	    textAreaScrollPane.setPrefSize(600, 400);
//		numberBoxScrollPane = new ScrollPane(numberBox);
//	    numberBoxScrollPane.setFitToWidth(true);
//	    numberBoxScrollPane.setFitToHeight(true);
//	    numberBoxScrollPane.setPrefSize(50, 400);
		
		field.setPrefRowCount(100);
	    
		Set<Node> scrollPanes = field.lookupAll(".scroll-pane");
	    if (!scrollPanes.isEmpty()) {
	        Node scrollPaneNode = scrollPanes.iterator().next();
	        if (scrollPaneNode instanceof ScrollPane) {
	            ScrollPane pane = (ScrollPane) scrollPaneNode;
	            pane.vvalueProperty().addListener((observable, oldValue, newValue) -> {
	                numberBox.setLayoutY(-newValue.doubleValue() * numberBox.getHeight());
	            });
	        } else {
	            System.out.println("The node found is not a ScrollPane");
	        }
	    } else {
	        System.out.println("No ScrollPane found in the TextArea");
	    }
	    
//        ChangeListener<Number> scrollListener = (observable, oldValue, newValue) -> {
//            textAreaScrollPane.setVvalue(newValue.doubleValue());
//            numberBoxScrollPane.setVvalue(newValue.doubleValue());
//        };
        
//        textAreaScrollPane.vvalueProperty().addListener(scrollListener);
//        numberBoxScrollPane.vvalueProperty().addListener(scrollListener);
	    
//	    SynchScrollBorderPane pane = new SynchScrollBorderPane(textAreaScrollPane, numberBoxScrollPane);
//	    pane.setPrefSize(650, 400);
	    
//	    field.textProperty().addListener((observable, oldValue, newValue) ->  updateLineNumbers());
		
//		textAreaScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//		numberBoxScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
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

//	private static void writeToFile(String input) {
//		try (BufferedWriter w = new BufferedWriter(new FileWriter(PATH, false))) {
//			w.write(input);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
