package seedu.jelphabot.model.productivity;

/**
 * Represents the overall productivity of the user in a day.
 */
public class Productivity {

    // TODO: productivity will list out time taken per task + overall productivity that day.
    //  i.e. how many hours spent on tasks so far -> need some way to know what time it is and calculated fast.
    //  List total time taken for all tasks at the top. e.g. 'You spent ___ hours and ___ minutes on work today!"
    //  List avg time spent per task (divide by incomplete tasks only)

    private static final String MESSAGE_COMPLIMENT = "Good work!";
    private static final String MESSAGE_ENCOURAGEMENT = "You can do better!";
    private static final String MESSAGE_CRITICISM = "Really? That's all you're going to do today? Nub";

    public Productivity() {

    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "noob";
    }
}
