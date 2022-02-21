package models;

import com.github.javafaker.Faker;

public class TestCaseFactory {
    static Faker faker = new Faker();


    public static TestCase get() {
        return TestCase.builder().title("Test Case")
                .section("Test Cases")
                .template("Test Case (Text)")
                .type("Functional")
                .priority("High")
                .estimate(faker.number().digit())
                .references(faker.internet().url())
                .automationType("None")
                .preconditions(faker.pokemon().name())
                .steps(faker.animal().name())
                .expectedResult(faker.book().publisher())
                .build();
    }
}

