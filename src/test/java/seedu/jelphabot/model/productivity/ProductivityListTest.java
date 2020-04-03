package seedu.jelphabot.model.productivity;

import org.junit.jupiter.api.Test;

import static seedu.jelphabot.testutil.Assert.assertThrows;

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
