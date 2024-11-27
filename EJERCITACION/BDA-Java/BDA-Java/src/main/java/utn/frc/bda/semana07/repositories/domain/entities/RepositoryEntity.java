package utn.frc.bda.semana07.repositories.domain.entities;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "REPOSITORY", schema = "PUBLIC", catalog = "REPOSITORIESDB")
@NamedQueries({
        @NamedQuery(name = "Repository.findAll", query = "select r from RepositoryEntity r")
})
public class RepositoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RID", nullable = false)
    private Integer rid;
    @Basic
    @Column(name = "UNAME", nullable = false, length = 64)
    private String uname;
    @Basic
    @Column(name = "RNAME", nullable = false, length = 64)
    private String rname;
    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 255)
    private String description;
    @Basic
    @Column(name = "LAST_UPDATE", nullable = true)
    private Date lastUpdate;
    @Basic
    @Column(name = "STARS", nullable = true, precision = 0)
    private Double stars;
    @Basic
    @Column(name = "URL", nullable = true, length = 255)
    private String url;
    @ManyToOne
    @JoinColumn(name = "UNAME", referencedColumnName = "UNAME", nullable = false, insertable = false, updatable = false)
    private UsrEntity usrByUname;
    @ManyToMany
    @JoinTable(
            name = "repository_languages",
            joinColumns = {
                    @JoinColumn(name = "rid", referencedColumnName = "rid", insertable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "language", referencedColumnName = "language", insertable = false, updatable = false)
            }
    )
    private Collection<LanguageEntity> languages;
    @ManyToMany
    @JoinTable(
            name = "repository_tags",
            joinColumns = {
                    @JoinColumn(name = "rid", referencedColumnName = "rid", insertable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tag", referencedColumnName = "tag", insertable = false, updatable = false)
            }
    )
    private Collection<TagEntity> tags;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RepositoryEntity{" +
                "rid=" + rid +
                ", uname='" + uname + '\'' +
                ", rname='" + rname + '\'' +
                ", description='" + description + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", stars=" + stars +
                ", url='" + url + '\'' +
                ", usrByUname=" + usrByUname +
                ", languages=" + languages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepositoryEntity that = (RepositoryEntity) o;

        if (!Objects.equals(rid, that.rid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid != null ? rid.hashCode() : 0;
        return result;
    }

    public UsrEntity getUsrByUname() {
        return usrByUname;
    }

    public void setUsrByUname(UsrEntity usrByUname) {
        this.usrByUname = usrByUname;
    }

    public Collection<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(Collection<LanguageEntity> languages) {
        this.languages = languages;
    }

    public Collection<TagEntity> getTags() {
        return tags;
    }

    public void setTags(Collection<TagEntity> tags) {
        this.tags = tags;
    }
}
