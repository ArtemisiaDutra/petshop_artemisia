/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cachorro;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Artemisia Dutra
 */
public class CachorroDaoImpl extends BaseDaoImpl<Cachorro, Long> implements CachorroDao, Serializable {

    @Override
    public Cachorro pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(Cachorro.class, id);
    }

    @Override
    public List<Cachorro> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query<Cachorro> consulta = sessao.createQuery("from Cachorro c where c.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }

    @Override
    public List<Cachorro> pesquisarCachorroComp(String comp, Session sessao) throws HibernateException {
       
        Query<Cachorro> consulta = sessao.createQuery("from Cachorro c where c.comp like :comp");
        consulta.setParameter("comportamento", "%" + comp + "%");
        return consulta.getResultList();
    }

}
