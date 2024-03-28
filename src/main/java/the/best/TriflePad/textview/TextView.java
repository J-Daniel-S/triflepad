package the.best.TriflePad.textview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.TextArea;
import the.best.TriflePad.TriflePad;

public class TextView {
	
	static TextArea field = TriflePad.field;
	static String PATH = "src/main/resources/textFile.txt";
	static String doc = "";
	
	private TextView() {}
	
	private static void listenForInput() {
		StringBuilder fileText = new StringBuilder();
		fileText.append(getText());
		
		field.setOnKeyPressed(e -> {
			StringBuilder text = new StringBuilder();
			
			text.append(doc).append(e.getText());
			field.setText(text.toString());
			doc = text.toString();
			writeToFile(text.toString());
			
		});
	}
	
	private static void writeToFile(String input) {
		try (BufferedWriter w = new BufferedWriter(new FileWriter(PATH, false))) {
			w.write(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getText() {
		StringBuilder text = new StringBuilder();

		try (BufferedReader r = new BufferedReader(new FileReader(PATH))) {
			String line;
			while((line = r.readLine()) != null) {
				text.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text.toString();
	}
	
	private static void createFile() {
		File file = new File(PATH);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void setText() {
		doc = getText();
		field.setText(doc);
	}
	
	public static void setup() {
		createFile();
		setText();
		listenForInput();
	}

}
