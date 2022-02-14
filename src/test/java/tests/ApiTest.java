package tests;

import adapters.CaseAdapter;
import com.github.javafaker.Faker;
import models.ResponseStatusNegative;
import models.ResponseStatusPositive;
import models.Result;
import models.TestCase;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ApiTest {

    static Faker faker = new Faker();

    @Test(description = "Negative test for Create new TestCase. 'Title' field is required")
    public void createTestCaseNegativeTest() {
        TestCase testCase = TestCase.builder().title("")
                .template("3")
                .type("8").priority("1")
                .estimate("2hours")
                .references("https://jira.elsharnikova.com/")
                .preconditions(faker.animal().name())
                .build();
        ResponseStatusNegative actual = new CaseAdapter().postCreateTestCaseNegative(testCase, 400);
        ResponseStatusNegative expected = ResponseStatusNegative.builder()
                .status(false)
                .errorMessage("Field :title is a required field.")
                .build();
        assertEquals(actual, expected);

    }

    @Test(description = "Positive test for Create new TestCase")
    public void createTestCasePositiveTest(){
        TestCase testCase = TestCase.builder().title("TesT")
                .section("2767")
                .template("3")
                .type("8")
                .priority("1")
                .estimate("2hours")
                .references("https://jira.elsharnikova.com/")
                .preconditions(faker.animal().name())
                .build();
        ResponseStatusPositive actual = new CaseAdapter().postCreateTestCasePositive(testCase,200);
        ResponseStatusPositive expected = ResponseStatusPositive.builder()
                .status(true)
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

