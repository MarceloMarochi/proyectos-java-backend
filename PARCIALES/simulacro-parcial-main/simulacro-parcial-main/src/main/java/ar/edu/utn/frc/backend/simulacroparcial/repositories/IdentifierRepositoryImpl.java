package ar.edu.utn.frc.backend.simulacroparcial.repositories;


import static java.lang.String.format;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IdentifierRepositoryImpl implements IdentifierRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override public int nextValue(final String tableName) {

		Integer result = (Integer) entityManager.createNativeQuery(format("SELECT count(*) FROM %s;", tableName)).getSingleResult();

		return result + 1;
	}
}
