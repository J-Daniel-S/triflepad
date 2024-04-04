package the.best.TriflePad.textview.components;

import javafx.scene.control.IndexedCell;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.StackPane;

public class LineNumberCell extends IndexedCell<Label> {
    private final Label lineNumberLabel;

    public LineNumberCell(long lineNumber) {
        this.lineNumberLabel = new Label(Long.toString(lineNumber));
        this.lineNumberLabel.getStyleClass().add("line-number");
        this.lineNumberLabel.setTextOverrun(OverrunStyle.LEADING_ELLIPSIS);
        this.setGraphic(new StackPane(this.lineNumberLabel));
    }

    public Label getLineNumberLabel() {
        return lineNumberLabel;
    }
}
