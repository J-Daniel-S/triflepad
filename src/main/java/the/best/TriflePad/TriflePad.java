package the.best.TriflePad;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import the.best.TriflePad.textdocument.TextDocument;
import the.best.TriflePad.textview.TextView;


public class TriflePad extends Application {
	
    @Override
    public void start(Stage stage) {
    	TextView field = new TextView();
    	TextDocument doc = new TextDocument(field.get());
    	field.assignDoc(doc);
    	boolean noErrors = doc.setup();

        stage.setScene(field.initWindowContents());
        stage.setTitle("TriflePad");
        stage.getIcons().add(new Image("pie.png"));
        stage.show();
//        field.resetScroll();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}