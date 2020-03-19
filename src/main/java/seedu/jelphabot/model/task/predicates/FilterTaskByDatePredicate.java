package seedu.jelphabot.model.task.predicates;

import java.util.function.Predicate;

import seedu.jelphabot.model.task.Task;

/**
 * Interface class representing all predicates which filter tasks by date.
 * TODO add methods for composing predicates, maybe as a abstract class.
 */
public interface FilterTaskByDatePredicate extends Predicate<Task> {
}
