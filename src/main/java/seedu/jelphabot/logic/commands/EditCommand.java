package seedu.jelphabot.logic.commands;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.commons.util.CollectionUtil;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.*;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.logic.parser.CliSyntax.*;
import static seedu.jelphabot.model.Model.PREDICATE_SHOW_ALL_TASKS;

/**
 * Edits the details of an existing person in the address book.
 */
// TODO go through this manually
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) " 
            + "[" + PREFIX_NAME + "DESCRIPTION] " + "[" + PREFIX_MODULE_CODE + "MODULE_CODE] " 
            + "[" + PREFIX_DATETIME + "DATETIME]" + "[" + PREFIX_TAG + "TAG]...\n"  
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MODULE_CODE + "CS2105" + PREFIX_DATETIME + "23 01 2020 20 20";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedPerson(taskToEdit, editPersonDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Task createEditedPerson(Task taskToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert taskToEdit != null;

        Description updatedDescription = editPersonDescriptor.getDescription().orElse(taskToEdit.getDescription());
        ModuleCode updatedModuleCode = editPersonDescriptor.getModuleCode().orElse(taskToEdit.getModuleCode());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(taskToEdit.getTags());
        DateTime dateTime = editPersonDescriptor.getDateTime().orElse(taskToEdit.getDateTime());
        Status updatedStatus = editPersonDescriptor.getStatus();

        return new Task(updatedDescription, updatedStatus, dateTime, updatedModuleCode, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index) && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will
     * replace the corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Description description;
        private ModuleCode moduleCode;
        private Set<Tag> tags;
        private DateTime dateTime;
        private Status status;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setDescription(toCopy.description);
            setModuleCode(toCopy.moduleCode);
            setTags(toCopy.tags);
            setDateTime(toCopy.dateTime);
            setStatus(toCopy.status);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, moduleCode, tags);
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Status getStatus() {
            return status;
        }

        /**
         * Sets {@code status} to this object's {@code status}.
         * 
         * @param status sets the status to the specified status.
         */
        public void setStatus(Status status) {
            this.status = status;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of
         * {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted. Returns
         * {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getDescription().equals(e.getDescription()) && getModuleCode().equals(e.getModuleCode())
                    && getStatus().equals(e.getStatus()) && getDateTime().equals(e.getDateTime())
                    && getTags().equals(e.getTags());
        }
    }
}
