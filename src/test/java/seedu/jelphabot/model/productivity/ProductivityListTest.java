//@@author Clouddoggo
package seedu.jelphabot.model.productivity;

import static seedu.jelphabot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ProductivityListTest {

    @Test
    void addProductivity_nullProductivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProductivityList().addProductivity(null));
    }

    @Test
    void setProductivity_nullProductivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProductivityList().setProductivity(null));
    }
}
