package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageStatistics {
    private String languageName;
    private Long repositoryCount;
    private Double totalStars;

    @Override
    public String toString() {
        return languageName + "," + repositoryCount + "," + totalStars;
    }
}
