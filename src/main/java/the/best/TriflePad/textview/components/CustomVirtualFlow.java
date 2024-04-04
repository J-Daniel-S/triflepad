package the.best.TriflePad.textview.components;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.skin.VirtualFlow;

public class CustomVirtualFlow extends VirtualFlow<LineNumberCell> {
    public void setLineNumberCells(List<Label> lineNumberCells) {
        this.getChildren().setAll(lineNumberCells);
    }
}