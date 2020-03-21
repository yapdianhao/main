package seedu.jelphabot.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.reminder.exceptions.DuplicateReminderException;
import seedu.jelphabot.model.reminder.exceptions.ReminderNotFoundException;

public class UniqueReminderList implements Iterable<Reminder> {

    private final ObservableList<Reminder> internalList = FXCollections.observableArrayList();
    private final ObservableList<Reminder> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Reminder toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameReminder);
    }

    public void add(Reminder toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateReminderException();
        }
        internalList.add(toAdd);
    }

    public ObservableList<Reminder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public void remove(Reminder toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ReminderNotFoundException();
        }
    }

    public void setReminders(UniqueReminderList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setReminders(List<Reminder> reminders) {
        requireAllNonNull(reminders);
        if (!remindersAreUnique(reminders)) {
            throw new DuplicateReminderException();
        }

        internalList.setAll(reminders);
    }

    @Override
    public Iterator<Reminder> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof UniqueReminderList // instanceof handles nulls
                           && internalList.equals(((UniqueReminderList) other).internalList));
    }

    public boolean remindersAreUnique(List<Reminder> reminders) {
        HashSet<Reminder> seen = new HashSet<>();
        for (int i = 0; i < reminders.size(); i++) {
            if (seen.contains(reminders.get(i))) {
                return false;
            }
            seen.add(reminders.get(i));
        }
        return true;
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }


}