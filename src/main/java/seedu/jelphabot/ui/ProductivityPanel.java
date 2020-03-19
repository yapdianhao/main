package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.productivity.Productivity;

/**
 * The Productivity Panel. Provides the basic application layout of productivity of tasks.
 */
public class ProductivityPanel extends UiPart<Stage> {
    private static final String FXML = "ProductivityPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProductivityPanel.class);

    @FXML
    private ListView<Productivity> productivityListView;

    // TODO: insert piechart or progress bar.
    public ProductivityPanel(ObservableList<Productivity> productivityList) {
        super(FXML);
        logger.info("Initialising productivity panel stage");
        productivityListView.setItems(productivityList);
        productivityListView.setCellFactory(productivityView -> new ProductivityListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Productivity} using a {@code ProductivityCard}.
     */
    class ProductivityListViewCell extends ListCell<Productivity> {
        @Override
        protected void updateItem(Productivity productivity, boolean empty) {
            super.updateItem(productivity, empty);

            if (empty || productivity == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProductivityCard(productivity, getIndex() + 1).getRoot());
            }
        }
    }
}
