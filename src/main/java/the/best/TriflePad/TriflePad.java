package the.best.TriflePad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import the.best.TriflePad.textview.TextView;


public class TriflePad extends Application {
	
	public static TextArea field;

    @Override
    public void start(Stage stage) {
    	MenuBar bar = new MenuBar();
    	Menu menu = new Menu("New");
    	field = new TextArea();
    	field.maxHeight(Long.MAX_VALUE);
    	field.maxWidth(Long.MAX_VALUE);
    	
    	bar.getMenus().add(menu);
    	
    	BorderPane bp = new BorderPane();
    	bp.setTop(bar);
    	bp.setCenter(field);
    	var scene = new Scene(new StackPane(bp), 640, 480);
        stage.setScene(scene);
        stage.setTitle("TriflePad");
        stage.getIcons().add(new Image("pie.png"));
        stage.show();
        
        TextView.setup();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}