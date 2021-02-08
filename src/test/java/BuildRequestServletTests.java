import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Contains tests for the {@link BuildRequestServlet} class
 */
public class BuildRequestServletTests {
    Build b,c;

    final String correctJson = "{\n" +
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
            "  \"repository\": {\n" +
            "    \"html_url\": \"https://github.com/Test/testRepoName.git\",\n" +
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

    @BeforeEach
    void init(){
        b = BuildRequestServlet.JsonParsing(correctJson);
    }

    
    @Test
    @DisplayName("Test getRef correct")
    void getRefTest(){
        assertEquals("refs/heads/testBranchName", b.branchRef);
    }

    @Test
    @DisplayName("Test getUrl correct")
    void getUrlTest(){
        assertEquals("https://github.com/Test/testRepoName.git", b.url);
    }
    
    @Test
    @DisplayName("Test getidSHA correct")
    void getidSHATest(){
        assertEquals("abc123", b.idSHA);
    }

    @Test
    @DisplayName("Test getAuthorsName correct")
    void getAuthorNamneTest(){
        assertEquals("AuthorName", b.nameAuthor);
    }

    @Test
    @DisplayName("Test getAuthorsName correct")
    void getAuthorEmailTest(){
        assertEquals("AuthorName@mail.com", b.emailAuthor);
    }
    

    @Test
    @DisplayName("Test getTimestamp correct")
    void getTimeStampTest(){
        assertEquals("20210207", b.timeStamp);
    }

    @Test
    @DisplayName("Test incorrect JSON")
    void getincorrectTest(){
        assertThrows(JSONException.class, () -> BuildRequestServlet.JsonParsing(inCorrectJson));
    }

    @Test
    @DisplayName("Test empty JSON string")
    void getemptyJsonTest(){
        assertThrows(JSONException.class, () -> BuildRequestServlet.JsonParsing(emptyJson));
    }
}
