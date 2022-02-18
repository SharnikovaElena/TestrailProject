package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TestRun {
    String name;
    String references;
    String milestone;
    String assignTo;
    String description;
}
