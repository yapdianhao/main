package seedu.jelphabot.testutil;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code JelphaBot ab = new JelphaBotBuilder().withPerson("John", "Doe").build();}
 */
public class JelphaBotBuilder {

    private JelphaBot jelphaBot;

    public JelphaBotBuilder() {
        jelphaBot = new JelphaBot();
    }

    public JelphaBotBuilder(JelphaBot jelphaBot) {
        this.jelphaBot = jelphaBot;
    }

    /**
     * Adds a new {@code Person} to the {@code JelphaBot} that we are building.
     */
    public JelphaBotBuilder withPerson(Task task) {
        jelphaBot.addPerson(task);
        return this;
    }

    public JelphaBot build() {
        return jelphaBot;
    }
}
