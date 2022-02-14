package tests;

import adapters.CaseAdapter;
import com.github.javafaker.Faker;
import models.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ApiTest {

    @Test(description = "Negative test for Create new TestCase. 'Title' field is required")
    public void createTestCaseNegativeTest() {
        TestCaseForApi testCaseForApi = TestCaseForApi.builder()
                .section_id(2767)
                .title("")
                .template_id(3)
                .type_id(8)
                .priority_id(1)
                .estimate("2hours")
                .refs("https://jira.elsharnikova.com/").build();

        ResponseStatusNegative actual = new CaseAdapter().postCreateTestCaseNegative(testCaseForApi, 400);
        ResponseStatusNegative expected = ResponseStatusNegative.builder()
                .errorMessage("Field :title is a required field.")
                .build();
        assertEquals(actual, expected);

    }

    @Test(description = "Positive test for Create new TestCase")
    public void createTestCasePositiveTest() {
        TestCaseForApi testCaseForApi = TestCaseForApi.builder().title("TesTExample")
                .section_id(2767)
                .template_id(3)
                .type_id(8)
                .priority_id(1)
                .estimate("2hours")
                .refs("https://jira.elsharnikova.com/")
                .build();
        ResponseStatusPositive actual = new CaseAdapter().postCreateTestCasePositive(testCaseForApi, 200);
        ResponseStatusPositive expected = ResponseStatusPositive.builder()
                .result(Result.builder()
                        .created_by(1)
                        .id(33987)
                        .title("TesT")
                        .template_id(3)
                        .type_id(8)
                        .priority_id(1)
                        .estimate("2hours")
                        .refs("https://jira.elsharnikova.com/")
                        .build())
                .build();
        assertEquals(actual, expected);
    }
}

