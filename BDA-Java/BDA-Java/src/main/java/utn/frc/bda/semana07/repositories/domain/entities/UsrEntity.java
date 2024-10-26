package utn.frc.bda.semana07.repositories.domain.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "USR", schema = "PUBLIC", catalog = "REPOSITORIESDB")
public class UsrEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UNAME", nullable = false, length = 64)
    private String uname;
    //@OneToMany(mappedBy = "usrByUname")
    //private Collection<RepositoryEntity> repositoriesByUname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsrEntity usrEntity = (UsrEntity) o;

        if (!Objects.equals(uname, usrEntity.uname)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uname != null ? uname.hashCode() : 0;
    }

    //public Collection<RepositoryEntity> getRepositoriesByUname() {
    //    return repositoriesByUname;
    //}

    //public void setRepositoriesByUname(Collection<RepositoryEntity> repositoriesByUname) {
    //    this.repositoriesByUname = repositoriesByUname;
    //}
}
