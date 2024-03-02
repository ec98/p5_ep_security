package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Usuario;

@Transactional
@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

	@PersistenceContext
	private EntityManager entitymanager;

	@Override
	public Usuario consultarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		TypedQuery<Usuario> typedQuery = this.entitymanager.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
		typedQuery.setParameter("nombre", nombre);
		return typedQuery.getSingleResult();
	}

}
