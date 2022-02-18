package models;

import com.github.javafaker.Faker;

public class TestRunFactory {
    static Faker faker = new Faker();


    public static TestRun get() {
        return TestRun.builder()
                .name("TestRunNew ")
                .references(faker.internet().url())
                .milestone("")
                .assignTo("Me")
                .description(faker.pokemon().name())
                .build();
    }
}

