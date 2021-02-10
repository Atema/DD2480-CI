import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for the {@link BuildDatabase} class
 */
public class BuildDatabaseTests {
    /**
     * Tests retrieval ({@link BuildDatabase#getBuild(int)} and {@link BuildDatabase#getAllBuilds()}), insertion
     * ({@link BuildDatabase#insertBuild(long, String, String, String, String, String, String, String, BuildStatus, String)}) and deletion
     * ({@link BuildDatabase#deleteBuild(int)}) of entries in the database.
     */
    @Test
    @DisplayName("Build database retrieval, insertion, and deletion")
    public void databaseTest() {
        final BuildDatabase db = new BuildDatabase();

        List<BuildDatabaseEntry> beforeInsert = db.getAllBuilds();

        db.insertBuild(-1, "Test/Repo", "testBranch", "commit_sha", "http://github.com/commit_sha", "A commit message!", "A name", "An email", BuildStatus.SUCCESS, "");

        List<BuildDatabaseEntry> afterInsert = db.getAllBuilds();

        assertEquals(beforeInsert.size() + 1, afterInsert.size(), "Database does not contain an extra build after insertion");

        BuildDatabaseEntry insertedBuild = null;

        for (BuildDatabaseEntry build : afterInsert) {
            if (build.getTime() == -1) {
                insertedBuild = build;
                break;
            }
        }

        assertNotNull(insertedBuild, "Inserted build does not exist in the database");

        assertEquals(insertedBuild, db.getBuild(insertedBuild.getId()), "Build is not correctly selected from database");

        db.deleteBuild(insertedBuild.getId());

        List<BuildDatabaseEntry> afterDelete = db.getAllBuilds();

        assertEquals(beforeInsert.size(), afterDelete.size(), "Database does not contain the same number of builds after insertion and deletion");

        assertNull(db.getBuild(insertedBuild.getId()), "Build is not correctly deleted from database");
    }
}
