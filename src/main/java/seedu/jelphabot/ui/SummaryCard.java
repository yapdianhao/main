package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.summary.Summary;

public class SummaryCard extends UiPart<Region> {

    private static final String FXML = "SummaryCard.fxml";

    private static final String BREAKLINE_STRING = "--------------------------------------------------";

    private Summary summary;

    @FXML
    private Label breakline;

    @FXML
    private Label tasksDueToday;

    @FXML
    private Label tasksCompletedToday;

    public SummaryCard(Summary summary) {
        super(FXML);
        this.summary = summary;
        breakline.setText(BREAKLINE_STRING);
    }
}
