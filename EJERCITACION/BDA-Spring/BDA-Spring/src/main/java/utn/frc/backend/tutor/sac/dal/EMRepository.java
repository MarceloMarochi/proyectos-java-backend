package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface EMRepository<ET, KT extends Serializable> extends JpaRepository<ET, KT> {
    void refresh(ET entity);

    ET saveAndRefresh(ET entity);
}
