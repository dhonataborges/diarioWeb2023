//package com.borges.diario_eletronico.domain.repositoryImpl;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Repository;
//
//import com.borges.diario_eletronico.domain.model.Aluno;
//import com.borges.diario_eletronico.domain.repository.query.AlunoRepositoryQuery;
//
//@Repository
//public class AlunoRepositoryImpl implements AlunoRepositoryQuery{
//	
//	@PersistenceContext
//	private EntityManager manager;
//	
//	@Autowired @Lazy
//	private AlunoRepositoryQuery alunoRepositoryQuery;
//	
//	@Override
//	public Aluno findByMatricula(String matricula) {
//		
//		String jpql = "SELECT obj FROM Aluno obj WHERE obj.matricula =: matricula";
//		TypedQuery<Aluno> typedQuerySing = manager.createQuery(jpql, Aluno.class);
//		Aluno obj = (Aluno) typedQuerySing.setParameter("matricula", matricula);
//
//		if (obj != null) {
//			return obj;
//		}
//		return null;
//		
//	}
//	
//}
