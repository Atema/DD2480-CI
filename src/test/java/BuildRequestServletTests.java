import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Contains tests for the {@link BuildRequestServlet} class
 */
public class BuildRequestServletTests {


    final String correctJson = "{\n" +
            "  \"ref\": \"refs/heads/testBranchName\",\n" +
            "  \"commits\": [\n" +
            "   {\n" +
            "    \"id\": \"abc123\",\n" +
            "    \"timestamp\": \"20210207\",\n" +
            "    \"author\": {\n" +
            "      \"name\": \"testOwnerName\",\n" +
            "       \"email\": \"testOwnerName@mail.com\",\n" +
            "    },\n" +
            " },\n" +
            "  ],\n" +
            "  \"repository\": {\n" +
            "    \"html_url\": \"https://github.com/Test/testRepoName.git\",\n" +
            "  },\n" +
            "}";

    @Test
    @DisplayName("Test getRef correct")
    void getRef(){
        Build b = BuildRequestServlet.JsonParsing(correctJson);
        assertEquals("refs/heads/testBranchName", b.branchRef);
    }

    @Test
    @DisplayName("Test test")
    void truetest() {
        assertTrue(true);
    }

}
