package utn.frc.bda.semana07.repositories.domain.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TAG", schema = "PUBLIC", catalog = "REPOSITORIESDB")
public class TagEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TAG", nullable = false, length = 32)
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagEntity tagEntity = (TagEntity) o;

        if (!Objects.equals(tag, tagEntity.tag)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tag != null ? tag.hashCode() : 0;
    }
}
