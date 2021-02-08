/**
 * Simple wrapper to consistently access environment variables
 */
public class EnvVars {
    /**
     * Getter for the CI_TOKEN environment variable
     *
     * @return Value of CI_TOKEN, or null if not set
     */
    public static String getToken() {
        return System.getenv("CI_TOKEN");
    }
}
