import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements a SQLite database that contains the build history
 */
public class BuildDatabase {
    private final String dbUrl;

    /**
     * Constructs the database using an SQLite file in a subfolder of the user home,
     * creating databases, tables, and indexes where necessary
     */
    public BuildDatabase() {
        File directory = new File(System.getProperty("user.home"), ".ciserver");
        directory.mkdir();

        dbUrl = "jdbc:sqlite:" + (new File(directory, "history2.db")).getPath();

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            // Executes the query for creating the builds table, if non-existent
            conn.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS builds (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "time INTEGER," +
                    "repo TEXT," +
                    "branch TEXT," +
                    "sha TEXT," +
                    "url TEXT," +
                    "message TEXT," +
                    "name TEXT," +
                    "email TEXT," +
                    "status TEXT," +
                    "log TEXT" +
                ");"
            );

            // Executes the query for adding an index to the builds table on the time column
            conn.createStatement().execute(
                "CREATE INDEX IF NOT EXISTS builds_time ON builds (time);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a new build into the database
     *
     * @param time Timestamp of build completion
     * @param repo Repository linked to the build
     * @param branch Branch the build commit was in
     * @param sha SHA hash of the commit
     * @param url URL to the commit page
     * @param message Commit message
     * @param name Name of the pusher
     * @param email Email of the pusher
     * @param status Status of the build process
     * @param log Logs of the build process
     */
    public void insertBuild(long time, String repo, String branch, String sha, String url, String message, String name, String email, BuildStatus status, String log) {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            PreparedStatement query = conn.prepareStatement(
                "INSERT INTO builds (time, repo, branch, sha, url, message, name, email, status, log) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );

            query.setLong(1, time);
            query.setString(2, repo);
            query.setString(3, branch);
            query.setString(4, sha);
            query.setString(5, url);
            query.setString(6, message);
            query.setString(7, name);
            query.setString(8, email);
            query.setString(9, status.toString());
            query.setString(10, log);

            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all builds from the database, ordered descending by time
     *
     * @return List of all builds
     */
    public List<BuildDatabaseEntry> getAllBuilds() {
        List<BuildDatabaseEntry> builds = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            ResultSet rs = conn.createStatement().executeQuery(
                "SELECT * FROM builds ORDER BY time DESC;"
            );

            while (rs.next()) {
                builds.add(new BuildDatabaseEntry(
                    rs.getInt("id"),
                    rs.getLong("time"),
                    rs.getString("repo"),
                    rs.getString("branch"),
                    rs.getString("sha"),
                    rs.getString("url"),
                    rs.getString("message"),
                    rs.getString("name"),
                    rs.getString("email"),
                    BuildStatus.fromString(rs.getString("status")),
                    rs.getString("log")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return builds;
    }

    /**
     * Retrieves a single build from the database
     *
     * @param id Id of the build to retrieve
     * @return The build with specified id, or null if not found
     */
    public BuildDatabaseEntry getBuild(int id) {
        BuildDatabaseEntry build = null;

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            PreparedStatement query = conn.prepareStatement(
                "SELECT * FROM builds WHERE id = ?;"
            );

            query.setInt(1, id);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                build = new BuildDatabaseEntry(
                    rs.getInt("id"),
                    rs.getLong("time"),
                    rs.getString("repo"),
                    rs.getString("branch"),
                    rs.getString("sha"),
                    rs.getString("url"),
                    rs.getString("message"),
                    rs.getString("name"),
                    rs.getString("email"),
                    BuildStatus.fromString(rs.getString("status")),
                    rs.getString("log")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return build;
    }

    /**
     * Deletes a single build from the database
     *
     * @param id Id of the build to delete
     */
    public void deleteBuild(int id) {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            PreparedStatement query = conn.prepareStatement(
                "DELETE FROM builds WHERE id = ?;"
            );

            query.setInt(1, id);

            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
