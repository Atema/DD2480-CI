import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for the {@link BuildDatabaseEntry} class
 */
public class BuildDatabaseEntryTests {
    /**
     * Test equality checking ({@link BuildDatabaseEntry#equals(Object)}) of database entries
     */
    @Test
    @DisplayName("Database entry equality")
    public void equalityTest() {
        BuildDatabaseEntry base = new BuildDatabaseEntry(0, 0, "Repo", "Branch", "SHA", "URL", "Message", "Name", "Email", BuildStatus.SUCCESS, "Log");

        assertNotEquals(base, new Object(), "Base entry is equal to empty object");

        BuildDatabaseEntry same = new BuildDatabaseEntry(0, 0, "Repo", "Branch", "SHA", "URL", "Message", "Name", "Email", BuildStatus.SUCCESS, "Log");
        assertEquals(base, same, "Base entry is not equal to entry with same values");

        BuildDatabaseEntry diffId = new BuildDatabaseEntry(1, 0, "Repo", "Branch", "SHA", "URL", "Message", "Name", "Email", BuildStatus.SUCCESS, "Log");
        assertNotEquals(base, diffId, "Base entry is equal to entry with different id");

        BuildDatabaseEntry diffTime = new BuildDatabaseEntry(0, 1, "Repo", "Branch", "SHA", "URL", "Message", "Name", "Email", BuildStatus.SUCCESS, "Log");
        assertNotEquals(base, diffTime, "Base entry is equal to entry with different time");

        BuildDatabaseEntry diffRepo = new BuildDatabaseEntry(0, 0, "OtherRepo", "Branch", "SHA", "URL", "Message", "Name", "Email", BuildStatus.SUCCESS, "Log");
        assertNotEquals(base, diffRepo, "Base entry is equal to entry with different repo");

        BuildDatabaseEntry diffBranch = new BuildDatabaseEntry(0, 0, "Repo", "OtherBranch", "SHA", "URL", "Message", "Name", "Email", BuildStatus.SUCCESS, "Log");
        assertNotEquals(base, diffBranch, "Base entry is equal to entry with different branch");

        BuildDatabaseEntry diffSHA = new BuildDatabaseEntry(0, 0, "Repo", "Branch", "OtherSHA", "URL", "Message", "Name", "Email", BuildStatus.SUCCESS, "Log");
        assertNotEquals(base, diffSHA, "Base entry is equal to entry with different SHA");

        BuildDatabaseEntry diffURL = new BuildDatabaseEntry(0, 0, "Repo", "Branch", "SHA", "OtherURL", "Message", "Name", "Email", BuildStatus.SUCCESS, "Log");
        assertNotEquals(base, diffURL, "Base entry is equal to entry with different URL");

        BuildDatabaseEntry diffMessage = new BuildDatabaseEntry(0, 0, "Repo", "Branch", "SHA", "URL", "OtherMessage", "Name", "Email", BuildStatus.SUCCESS, "Log");
        assertNotEquals(base, diffMessage, "Base entry is equal to entry with different Message");

        BuildDatabaseEntry diffName = new BuildDatabaseEntry(0, 0, "Repo", "Branch", "SHA", "URL", "Message", "OtherName", "Email", BuildStatus.SUCCESS, "Log");
        assertNotEquals(base, diffName, "Base entry is equal to entry with different Name");

        BuildDatabaseEntry diffEmail = new BuildDatabaseEntry(0, 0, "Repo", "Branch", "SHA", "URL", "Message", "Name", "OtherEmail", BuildStatus.SUCCESS, "Log");
        assertNotEquals(base, diffEmail, "Base entry is equal to entry with different Email");

        BuildDatabaseEntry diffStatus = new BuildDatabaseEntry(0, 0, "Repo", "Branch", "SHA", "URL", "Message", "Name", "Email", BuildStatus.FAILURE, "Log");
        assertNotEquals(base, diffStatus, "Base entry is equal to entry with different status");

        BuildDatabaseEntry diffLog = new BuildDatabaseEntry(0, 0, "Repo", "Branch", "SHA", "URL", "Message", "Name", "Email", BuildStatus.SUCCESS, "OtherLog");
        assertNotEquals(base, diffLog, "Base entry is equal to entry with different log");
    }
}
