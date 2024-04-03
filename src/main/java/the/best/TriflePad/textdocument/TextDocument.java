package the.best.TriflePad.textdocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.control.TextArea;

public class TextDocument {

	private long length;
	private int lines;
	private StringBuilder buffer;
	private long[] lineBuffer;
	private String filePath;
	private TextArea field;

	public TextDocument(String filePath, TextArea area) {
		this.filePath = filePath;
		this.field = area;
	}

	public TextDocument(TextArea area) {
		this.filePath = "src/main/resources/textFile.txt";
		this.field = area;
	}
	
	public boolean setup() {
		return init(filePath);
	}

	public long lineCount() {
		return lines;
	}
	
	// perhaps this ought not be
	public String[] getLines() {
		return buffer.toString().split("\n");
	}

	// do I need this?
	public long getLine(int lineNum, StringBuilder buf, int len) {

		long lineLength;

		// find the start of the specified line
		long lineStart = lineBuffer[lineNum];

		// work out how long it is by looking at the next line's starting point
		try {
			
			lineLength = lineBuffer[lineNum + 1] - lineBuffer[lineNum];
		} catch (ArrayIndexOutOfBoundsException e) {
			// this should never happen but just in case
			lineLength = lineBuffer[lineBuffer.length-1] - lineBuffer[lineBuffer.length - 2];
		}

		// make sure we don't overflow the caller's buffer
		lineLength = Math.min(len, lineLength);

		// copy the chars from the buffer to the caller's buffer
		System.arraycopy(buffer, (int) lineStart, buf, 0, (int) lineLength);

		return lineLength;
	}

	private boolean init(String path) {
		File file = new File(path);
		try {
			return file.exists() ? init(file) : (file.createNewFile() ? init(file) : false);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean init(File file) {
		length = countChars(file);

		if (length == 0)
			return false;
		
		if ((lineBuffer = countLines(file)).length == 0)
			return false;

		try {
			buffer = new StringBuilder((int) Math.min(length, Integer.MAX_VALUE));
		} catch (OutOfMemoryError | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
		
		try (BufferedReader r = new BufferedReader(new FileReader(file.getPath()))) {


			// populate buffer and store values in lineBuffer: each line (index) begins with char# value
			String line;
			int counter = 0;
			int totalChars = 0;
			while ((line = r.readLine()) != null) {
				lineBuffer[counter++] = totalChars;
				totalChars += line.chars().count() + 1;
				buffer.append(line).append('\n');
			}
			
			lineBuffer[lines -1] = length;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		field.appendText(buffer.toString());
		return true;
		
	}
	
	public void updateBuffer(String newValue) {
		//TODO add write to file
		buffer.delete(0, buffer.length());
		buffer.append(newValue);
		lines = getLines().length;
		this.length = newValue.length();
	}

	private long[] countLines(File file) {
		int lines = 0;
		try (BufferedReader r = new BufferedReader(new FileReader(file.getPath()))) {
			
			while (r.readLine() != null) {
				lines++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.lines = lines;
		return new long[lines];
	}

	private long countChars(File file) {
		long length = 0;
		try (BufferedReader r = new BufferedReader(new FileReader(file.getPath()))) {
			int data;
			while ((data = r.read()) != -1) {
				@SuppressWarnings("unused")
				char character = (char) data;
				length++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return length;
	}

}
