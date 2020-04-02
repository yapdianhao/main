package seedu.jelphabot.model.summary;

import static seedu.jelphabot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SummaryListTest {
    @Test
    public void addsummary_nullargument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SummaryList().addSummary(null));
    }

    @Test
    public void setsummary_nullargument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SummaryList().setSummary(null));
    }
}
