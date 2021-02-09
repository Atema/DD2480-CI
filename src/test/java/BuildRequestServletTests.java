import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Contains tests for the {@link BuildRequestServlet} class
 */
public class BuildRequestServletTests {
    Build b;

    final String correctJson = "{\n" +
            "  \"ref\": \"refs/heads/testBranchName\",\n" +
            "  \"head_commit\": {\n" +
            "    \"id\": \"abc123\",\n" +
            " },\n" +
            "  \"repository\": {\n" +
            "    \"html_url\": \"https://github.com/Test/testRepoName.git\",\n" +
            "    \"clone_url\": \"https://github.com/Test/testRepoName.git\",\n" +
            "    \"statuses_url\": \"https://github.com/Test/testRepoName.git/statuses/{sha}\",\n" +
            "    \"pushed_at\": \"20210207\",\n" +
            "    \"pusher\": {\n" +
            "      \"name\": \"AuthorName\",\n" +
            "       \"email\": \"AuthorName@mail.com\",\n" +
            "    },\n" +
            "  },\n" +
            "}";

        final String inCorrectJson = "{\n" +
        "  \"ref\": \"refs/heads/testBranchName\",\n" +
        "  \"commits\": [\n" +
        "   {\n" +
        "    \"id\": \"abc123\",\n" +
        "    \"timestamp\": \"20210207\",\n" +
        "    \"author\": {\n" +
        "      \"name\": \"AuthorName\",\n" +
        "       \"email\": \"AuthorName@mail.com\",\n" +
        "    },\n" +
        " },\n" +
        "  ],\n" +
        "}";

        final String emptyJson = "{\n" +
        "}";

    /**
     * Before each test initialize build object
     */
    @BeforeEach
    void init(){
        b = BuildRequestServlet.JsonParsing(correctJson);
    }

    /**
     * Tests that the JSON for ref is translated correctly
     * 
     * @result true when branchRef is correctly read in
     */
    @Test
    @DisplayName("Test getRef correct")
    void getRefTest(){
        assertEquals("refs/heads/testBranchName", b.branchRef);
    }

    /**
     * Tests that the JSON for url is translated correctly
     * 
     * @result true when url is correctly read in
     */
    @Test
    @DisplayName("Test getUrl correct")
    void getUrlTest(){
        assertEquals("https://github.com/Test/testRepoName.git", b.url);
    }

    /**
     * Tests that the JSON for Clone url is translated correctly
     * 
     * @result true when clone url is correctly read in
     */
    @Test
    @DisplayName("Test getCloneUrl correct")
    void getCloneUrlTest(){
        assertEquals("https://github.com/Test/testRepoName.git", b.cloneURL);
    }

    /**
     * Tests that the JSON for status url is translated correctly
     * 
     * @result true when status url is correctly read in
     */
    @Test
    @DisplayName("Test getStatusUrl correct")
    void getStatusUrlTest(){
        assertEquals("https://github.com/Test/testRepoName.git/statuses/{sha}", b.statusURL);
    }
    
    /**
     * Tests that the JSON for idSHA is translated correctly
     * 
     * @result true when idSHA is correctly read in
     */
    @Test
    @DisplayName("Test getidSHA correct")
    void getidSHATest(){
        assertEquals("abc123", b.idSHA);
    }

    /**
     * Tests that the JSON for name is translated correctly
     * 
     * @result true when name is correctly read in
     */
    @Test
    @DisplayName("Test getAuthorsName correct")
    void getAuthorNamneTest(){
        assertEquals("AuthorName", b.nameAuthor);
    }

    /**
     * Tests that the JSON for email is translated correctly
     * 
     * @result true when email is correctly read in
     */
    @Test
    @DisplayName("Test getAuthorsName correct")
    void getAuthorEmailTest(){
        assertEquals("AuthorName@mail.com", b.emailAuthor);
    }
    
    /**
     * Tests that the JSON for timestamp is translated correctly
     * 
     * @result true when timestamp is correctly read in
     */
    @Test
    @DisplayName("Test getTimestamp correct")
    void getTimeStampTest(){
        assertEquals("20210207", b.timeStamp);
    }

    /**
     * Tests that incorrect JSON throws error
     * 
     * @result throws JSONException
     */
    @Test
    @DisplayName("Test incorrect JSON")
    void getincorrectTest(){
        assertThrows(JSONException.class, () -> BuildRequestServlet.JsonParsing(inCorrectJson));
    }

    /**
     * Tests that empty/incorrect JSON throws error
     * 
     * @result throws JSONException
     */
    @Test
    @DisplayName("Test empty JSON string")
    void getemptyJsonTest(){
        assertThrows(JSONException.class, () -> BuildRequestServlet.JsonParsing(emptyJson));
    }
}
