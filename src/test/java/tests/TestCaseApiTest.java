package tests;
import adapters.CaseAdapter;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import models.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class TestCaseApiTest {

    static Faker faker = new Faker();

    int case_id;
    String caseTitle;
    int section_id;
    int template_id;
    int type_id;

    @BeforeClass(description = "Creating a new test case.")
    public void createNewTestCase() {
        log.info("Create new Test Case");
        TestCaseForApi testCaseForApi = TestCaseForApi.builder().title(faker.artist().name())
                .section_id(3)
                .template_id(3)
                .type_id(8)
                .priority_id(1)
                .estimate("2h")
                .refs("https://jira.elsharnikova.com/")
                .build();
        ResponseStatusPositive actual = new CaseAdapter().postCreateTestCasePositive(testCaseForApi, 200);
        case_id = actual.getId();
        caseTitle = actual.getTitle();
        section_id = actual.getSection_id();
        template_id = actual.getTemplate_id();
        type_id = actual.getType_id();
    }


    @Test(description = "Negative test for Create new TestCase. 'Title' field is required", priority = 1)
    public void createTestCaseNegativeTest() {
        TestCaseForApi testCaseForApi = TestCaseForApi.builder()
                .section_id(3)
                .title("")
                .template_id(3)
                .type_id(8)
                .priority_id(1)
                .estimate("2hours")
                .refs("https://jira.elsharnikova.com/").build();

        ResponseStatusNegative actual = new CaseAdapter().postCreateTestCaseNegative(testCaseForApi, 400);
        ResponseStatusNegative expected = ResponseStatusNegative.builder()
                .error("Field :title is a required field.")
                .build();
        assertEquals(actual, expected);
    }


    @Test(description = "Positive test for Create new TestCase and Delete it", priority = 2)
    public void createAndDeleteTestCasePositiveTest() {

        log.info("Run test createAndDeleteTestCasePositiveTest");
        log.info("Create new Test Case");
        String caseTitle = faker.artist().name();
        TestCaseForApi testCaseForApi = TestCaseForApi.builder().title(caseTitle)
                .section_id(3)
                .template_id(3)
                .type_id(8)
                .priority_id(1)
                .estimate("2h")
                .refs("https://jira.elsharnikova.com/")
                .build();
        ResponseStatusPositive actual = new CaseAdapter().postCreateTestCasePositive(testCaseForApi, 200);
        ResponseStatusPositive expected = ResponseStatusPositive.builder()
                .created_by(1)
                .title(caseTitle)
                .template_id(3)
                .type_id(8)
                .priority_id(1)
                .estimate("2h")
                .refs("https://jira.elsharnikova.com/")
                .build();
        int id = actual.getId();
        assertEquals(actual.getCreated_by(), expected.getCreated_by());
        assertEquals(actual.getTitle(), expected.getTitle());
        assertEquals(actual.getTemplate_id(), expected.getTemplate_id());
        assertEquals(actual.getType_id(), expected.getType_id());
        assertEquals(actual.getPriority_id(), expected.getPriority_id());
        assertEquals(actual.getEstimate(), expected.getEstimate());
        assertEquals(actual.getRefs(), expected.getRefs());

        log.info("Removing TestCase by case_id");

        int actualStatusDelete = new CaseAdapter().postDeleteTestCaseByCorrectCode(200, id);
        Assert.assertEquals(actualStatusDelete, 200, "Failed to uninstall TestCase");
        log.info("Completion test createAndDeleteTestCasePositiveTest");
    }


    @Test(description = "Positive test for Get TestCase", priority = 3)
    public void getTestCasePositiveTest() {

        log.info("Run test  getTestCasePositiveTest");
        log.info("We send a get request via the API to get a Test Case by its case_id");

        ResponseStatusPositive actualGetCaseResponse = new CaseAdapter().getTestCasePositive(200, case_id);
        ResponseStatusPositive expectedGetCaseResponse = ResponseStatusPositive.builder()
                .created_by(1)
                .title(caseTitle)
                .section_id(3)
                .template_id(3)
                .type_id(8)
                .priority_id(1)
                .estimate("2h")
                .refs("https://jira.elsharnikova.com/")
                .build();
        Assert.assertEquals(actualGetCaseResponse.getTitle(), expectedGetCaseResponse.getTitle(), "Failed to get the required test case.");
        log.info("Completion test  getTestCasePositiveTest");
    }


    @Test(description = "Negative test for Get TestCase. Using an invalid case_id", priority = 4)
    public void getTestCaseNegativeTest() {

        log.info("Run test  getTestCaseNegativeTest");
        log.info("We send a request via the API to get a Test Case using a non-existent case_id.");

        ResponseStatusNegative actualGetCaseResponse = new CaseAdapter().getTestCaseNegative(400, 0);
        ResponseStatusNegative expectedGetCaseResponse = ResponseStatusNegative.builder()
                .error("Field :case_id is not a valid test case.")
                .build();
        assertEquals(actualGetCaseResponse, expectedGetCaseResponse);
        log.info("Completion test  getTestCaseNegativeTest");
    }


    @Test(description = "Checking to save changes to an existing test case. Changing the values of priority_id and estimate", priority = 5)
    public void updateTestCasePositiveTest() {
        log.info("Run test updateTestCasePositiveTest");

        TestCaseForApi testCaseForApi = TestCaseForApi.builder()
                .id(case_id)
                .type_id(type_id)
                .section_id(section_id)
                .template_id(template_id)
                .priority_id(3)
                .estimate("5h")
                .build();
        ResponseStatusPositive actualUpdateCaseResponse = new CaseAdapter().postUpdateTestCasePositive(testCaseForApi, 200, case_id);
        ResponseStatusPositive expectedUpdateCaseResponse = ResponseStatusPositive.builder()
                .title(caseTitle)
                .section_id(section_id)
                .type_id(type_id)
                .template_id(template_id)
                .priority_id(3)
                .estimate("5h")
                .build();

        Assert.assertEquals(actualUpdateCaseResponse.getTitle(), expectedUpdateCaseResponse.getTitle(), "Test Case names do not match");
        Assert.assertEquals(actualUpdateCaseResponse.getPriority_id(), expectedUpdateCaseResponse.getPriority_id());
        Assert.assertEquals(actualUpdateCaseResponse.getEstimate(), expectedUpdateCaseResponse.getEstimate());

        log.info("Completion test updateTestCasePositiveTest");
    }


    @Test(description = "Checking if changes are saved in an existing Test Case when using invalid data: type_id.", priority = 6)
    public void updateTestCaseNegativeTest() {
        log.info("Run test updateTestCaseNegativeTest");

        TestCaseForApi testCaseForApi = TestCaseForApi.builder()
                .id(case_id)
                .type_id(13)
                .section_id(section_id)
                .template_id(template_id)
                .build();
        ResponseStatusNegative actualUpdateCaseResponse = new CaseAdapter().postUpdateTestCaseNegative(testCaseForApi, 400, case_id);
        ResponseStatusNegative expectedUpdateCaseResponse = ResponseStatusNegative.builder()
                .error("Field :type_id is not a valid case type.")
                .build();

        assertEquals(actualUpdateCaseResponse, expectedUpdateCaseResponse);
        log.info("Completion test updateTestCaseNegativeTest");
    }
}