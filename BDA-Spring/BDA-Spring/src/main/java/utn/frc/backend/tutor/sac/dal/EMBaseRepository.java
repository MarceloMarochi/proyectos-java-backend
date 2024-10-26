package utn.frc.backend.tutor.sac.dal;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public class EMBaseRepository<ET, KT extends Serializable>
        extends SimpleJpaRepository<ET, KT>
        implements EMRepository<ET, KT> {

    private final EntityManager entityManager;

    public EMBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void refresh(ET entity) {
        entityManager.refresh(entity);
    }

    @Override
    @Transactional
    public ET saveAndRefresh(ET entity) {
        entity = saveAndFlush(entity);
        entityManager.refresh(entity);
        return entity;
    }
}
