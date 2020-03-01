package seedu.jelphabot.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.jelphabot.logic.commands.EditCommand;
import seedu.jelphabot.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Task;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
// TODO rewrite required
public class EditPersonDescriptorBuilder {

    private EditCommand.EditTaskDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditCommand.EditTaskDescriptor();
    }

    public EditPersonDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditCommand.EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setDescription(task.getDescription());
        descriptor.setModuleCode(task.getModuleCode());
        descriptor.setTags(task.getTags());
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDescription(String name) {
        descriptor.setDescription(new Description(name));
        return this;
    }

    //   /**
    //     * Sets the {@code Phone} of the {@code EditTaskDescriptor} that we are building.
    //     */
    //    public EditPersonDescriptorBuilder withPhone(String phone) {
    //        descriptor.setPhone(new Phone(phone));
    //        return this;
    //    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModuleCode(new ModuleCode(moduleCode));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
