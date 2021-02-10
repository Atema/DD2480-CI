import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for the {@link BuildStatus} enum
 */
public class BuildStatusTests {
    /**
     * Tests conversion between {@link BuildStatus} and String using
     * {@link BuildStatus#fromString(String)} and {@link BuildStatus#toString()}
     */
    @Test
    @DisplayName("Conversion between BuildStatus and String")
    public void buildStatusToFromStringTest() {
        assertEquals(BuildStatus.SUCCESS, BuildStatus.fromString(BuildStatus.SUCCESS.toString()));
        assertEquals(BuildStatus.FAILURE, BuildStatus.fromString(BuildStatus.FAILURE.toString()));
        assertEquals(BuildStatus.ERROR, BuildStatus.fromString(BuildStatus.ERROR.toString()));
        assertEquals(BuildStatus.ERROR, BuildStatus.fromString("Nonsensical input"));
    }
}
