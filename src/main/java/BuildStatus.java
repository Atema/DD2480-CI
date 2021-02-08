/**
 * Contains possible states that a (completed) build can have
 */
public enum BuildStatus {
    /**
     * The build procsses completed successfully
     */
    SUCCESS("success"),

    /**
     * The build process completed without success
     */
    FAILURE("failure"),

    /**
     * The build process did not complete due to an error
     */
    ERROR("error");

    public final String label;

    private BuildStatus(String label) {
        this.label = label;
    }

    /**
     * Converts a BuildStatus into a string that can be easily stored or transferred
     *
     * @return String corresponding to this BuildStatus
     */
    @Override
    public String toString() {
        return label;
    }

    /**
     * Returns the BuildStatus from a string created by {@link #toString()}
     *
     * @param label String created by {@link #toString()}
     * @return BuildString corresponding to the String (or ERROR)
     */
    public static BuildStatus fromString(String label) {
        if (label.equals(SUCCESS.label)) {
            return SUCCESS;
        } else if (label.equals(FAILURE.label)) {
            return FAILURE;
        } else {
            return ERROR;
        }
    }
}
