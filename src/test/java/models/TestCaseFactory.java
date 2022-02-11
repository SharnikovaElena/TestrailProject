package models;

import com.github.javafaker.Faker;

public class TestCaseFactory {
    static Faker faker = new Faker();


    public static TestCase get() {
        return TestCase.builder().title(faker.pokemon().name())
                .section("Test Case")
                .template("Exploratory Session")
                .type("Functional")
                .priority("High")
                .estimate(String.valueOf(faker.number().randomNumber()))
                .references(faker.internet().url())
                .automationType("None").build();

    }
}

