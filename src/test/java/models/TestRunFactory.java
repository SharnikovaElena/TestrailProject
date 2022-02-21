package models;

import com.github.javafaker.Faker;

public class TestRunFactory {
    static Faker faker = new Faker();


    public static TestRun get() {
        return TestRun.builder()
                .name("TestRun " + faker.date().birthday())
                .references(faker.internet().url())
                .milestone("")
                .assignTo("Me")
                .description(faker.pokemon().name())
                .build();
    }
}

