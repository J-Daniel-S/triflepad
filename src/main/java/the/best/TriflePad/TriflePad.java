package the.best.TriflePad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import the.best.TriflePad.textdocument.TextDocument;
import the.best.TriflePad.textview.TextView;


public class TriflePad extends Application {
	
	public static TextView field;

    @Override
    public void start(Stage stage) {
    	field = new TextView();
    	TextDocument doc = new TextDocument();
    	boolean noErrors = doc.setup();
    	MenuBar bar = new MenuBar();
    	Menu menu = new Menu("New");
    	
    	bar.getMenus().add(menu);
    	
    	BorderPane bp = new BorderPane();
    	bp.setTop(bar);
    	bp.setCenter(field.get());
    	Scene scene = new Scene(new StackPane(bp), 800, 600);
        stage.setScene(scene);
        stage.setTitle("TriflePad");
        stage.getIcons().add(new Image("pie.png"));
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}