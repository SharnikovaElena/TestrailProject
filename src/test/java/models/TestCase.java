package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TestCase {
    String title;
    String section;
    String template;
    String type;
    String priority;
    String estimate;
    String references;
    String automationType;
    String preconditions;
    String steps;
    String expectedResult;

}
