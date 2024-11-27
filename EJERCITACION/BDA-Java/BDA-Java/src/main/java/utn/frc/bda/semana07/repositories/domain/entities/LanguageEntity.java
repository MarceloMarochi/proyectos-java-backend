package utn.frc.bda.semana07.repositories.domain.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "LANGUAGE", schema = "PUBLIC", catalog = "REPOSITORIESDB")
public class LanguageEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "LANGUAGE", nullable = false, length = 32)
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguageEntity that = (LanguageEntity) o;

        if (!Objects.equals(language, that.language)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return language != null ? language.hashCode() : 0;
    }
}
