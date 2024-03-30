package the.best.TriflePad.textview;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;

public class TextView {
	
	static String PATH = "src/main/resources/textFile.txt";
	static String doc = "";
	private TextArea field;
	
	
	public TextView() {
		field = new TextArea();
		initArea();
	}
	
	private void initArea() {
		field.setStyle("-fx-font-size: 14px;");
    	field.maxHeight(Long.MAX_VALUE);
    	field.maxWidth(Long.MAX_VALUE);
    	field.setPadding(new Insets(5));
    	pinScrollbarOnResize();
	}
	
	public TextArea get() {
		return field;
	}
	
	public void appendText(String text) {
		field.appendText(text);
	}
	
	private void pinScrollbarOnResize() {
		field.widthProperty().addListener((obs, oldVal, newVal) -> {
			field.setScrollLeft(0);
		});
		
		field.heightProperty().addListener((obs, oldVal, newVal) -> {
			field.setScrollTop(Long.MAX_VALUE);
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
