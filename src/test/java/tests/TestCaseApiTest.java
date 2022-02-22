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


    @BeforeClass (description = "Creating a new test case.")
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
    }

    @Test(description = "Negative test for Create new TestCase. 'Title' field is required")
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


    @Test(description = "Positive test for Create new TestCase and Delete it")
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


    @Test(description = "Positive test for Get TestCase", priority = 1)
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

//    @Test
//    public void updateTestCasePositiveTest(int priority_id, String estimate) {
//
//        log.info("Run test  getTestCasePositiveTest");
//        TestCaseForApi contentOfChanges = TestCaseForApi.builder()
//                .priority_id(priority_id)
//                .estimate(estimate)
//                .build();
//        ResponseStatusPositive actualUpdateCaseResponse = new CaseAdapter().postUpdateTestCaseByCorrectCode(contentOfChanges,200,case_id);
//        ResponseStatusPositive expectedUpdateCaseResponse = ResponseStatusPositive.builder()
//                .created_by(1)
//                .title(caseTitle)
//                .priority_id(3)
//                .estimate("4h")
//                .build();
//
//        Assert.assertEquals(actualUpdateCaseResponse.getTitle(),expectedUpdateCaseResponse.getTitle(), "Test Case names do not match");
//        Assert.assertEquals(actualUpdateCaseResponse.getPriority_id(), expectedUpdateCaseResponse.getPriority_id());
//        Assert.assertEquals(actualUpdateCaseResponse.getEstimate(), expectedUpdateCaseResponse.getEstimate());
//    }
}
