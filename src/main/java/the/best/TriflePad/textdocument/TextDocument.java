package the.best.TriflePad.textdocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextDocument {
	
	private long length;
	private StringBuffer buffer;
	private long[] lineBuffer;
	
	public TextDocument(String filePath) {
		
	}
	
	public long lineCount() {
		return 0;
	}
	
	public long getLine(int lineNum, StringBuffer buf, int len) {
		
		
		long lineLength;
		
		// find the start of the specified line
		long lineStart =  lineBuffer[lineNum];
		
		// work out how long it is by looking at the next line's starting point
		lineLength = lineBuffer[lineNum+1] - lineBuffer[lineNum];
		
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
		int capacity = (int) Math.min(length, Integer.MAX_VALUE);
		
		if ((length = file.length()) == 0) 
			return false;
		
		try {
			buffer = new StringBuffer(capacity);
		} catch (OutOfMemoryError | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
		
		try (BufferedReader r = new BufferedReader(new FileReader(file.getPath()))) {
			String line;
			while((line = r.readLine()) != null) {
				buffer.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
//		initializeLineBuffer(capacity);
		
		return true;

	}
	
	private boolean initializeLineBuffer(int length) {
		int i = 0;
		long lineStart = 0;
		lineBuffer = new long[length];
		
		if (lineBuffer.length == 0)
			return false;
		
		int numLines = 0;
		
		for (i = 0; i < buffer.length(); i++) {
			if (buffer.charAt(i) == '\r') {
				// carriage return / line-feed combination
				if (buffer.charAt(++i) == '\n')
					i++;
				
				//record where the line starts
				lineBuffer[numLines++] = lineStart;
				lineStart = i;
				
			}
		}
		
		lineBuffer[numLines] = length;
		return true;
		
	}
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	
}
