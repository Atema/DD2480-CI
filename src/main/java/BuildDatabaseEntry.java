import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Build entry that is retrieved from the database
 */
public class BuildDatabaseEntry {
    private final int id;
    private final long time;
    private final String repo;
    private final String branch;
    private final String sha;
    private final String url;
    private final String message;
    private final String name;
    private final String email;
    private final BuildStatus status;
    private final String log;

    /**
     * Constructs the entry (called by {@link BuildDatabase} with results from a query)
     *
     * @param id Id of the build
     * @param time Timestamp of build completion
     * @param repo Repository linked to the build
     * @param branch Branch the build commit was in
     * @param sha SHA hash of the commit
     * @param url URL of the commit page
     * @param message Commit message
     * @param name Name of the pusher
     * @param email Email of the pusher
     * @param status Status of the build process
     * @param log Logs of the build process
     */
    public BuildDatabaseEntry(int id, long time, String repo, String branch, String sha, String url, String message,
            String name, String email, BuildStatus status, String log) {
        this.id = id;
        this.time = time;
        this.repo = repo;
        this.branch = branch;
        this.sha = sha;
        this.url = url;
        this.message = message;
        this.name = name;
        this.email = email;
        this.status = status;
        this.log = log;
    }

    /**
     * Gets id of the build
     *
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets timestamp of build completion
     *
     * @return Timestamp (in milliseconds)
     */
    public long getTime() {
        return time;
    }

    /**
     * Gets timestamp of build completion as human-readable String
     *
     * @return Date-time string
     */
    public String getTimeString() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date(time));
    }

    /**
     * Gets repository linked to the build
     *
     * @return Repository
     */
    public String getRepo() {
        return repo;
    }

    /**
     * Gets branch the build commit was in
     *
     * @return Branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Gets SHA hash of the commit
     *
     * @return SHA hash
     */
    public String getSha() {
        return sha;
    }

    /**
     * Gets the URL of the commit page
     *
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the commit message
     *
     * @return Commit message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the pusher name
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the pusher email
     *
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets status of the build process
     *
     * @return Status
     */
    public BuildStatus getStatus() {
        return status;
    }

    /**
     * Gets logs of the build process
     *
     * @return Build logs
     */
    public String getLog() {
        return log;
    }

    /**
     * Converts the entry to a String
     *
     * @return Generated String
     */
    @Override
    public String toString() {
        return String.valueOf(id) + ", " + String.valueOf(time) + ", " + repo + ", " + branch + ", " + sha + ", " + status.toString();
    }

    /**
     * Compares the current entry to another object
     *
     * @return Whether the two are equal
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BuildDatabaseEntry)) {
            return false;
        }

        BuildDatabaseEntry entry = (BuildDatabaseEntry) other;

        return id == entry.getId() && time == entry.getTime() && repo.equals(entry.getRepo()) && branch.equals(entry.getBranch()) && sha.equals(entry.getSha()) &&
                url.equals(entry.getUrl()) && message.equals(entry.getMessage()) && name.equals(entry.getName()) && email.equals(entry.getEmail()) &&
                status == entry.getStatus() && log.equals(entry.getLog());
    }
}
