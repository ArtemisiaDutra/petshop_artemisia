/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Comportamento;
import br.com.senac.entidade.Gato;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Artemisia Dutra
 */
public class GatoDaoImpl extends BaseDaoImpl<Gato, Long> implements GatoDao, Serializable {

    @Override
    public Gato pesquisarPorId(Long id, Session sessao) throws HibernateException {

        return sessao.find(Gato.class, id);
    }

    @Override
    public List<Gato> pesquisarPorNome(String nome, Session sessao) throws HibernateException {

        Query<Gato> consulta = sessao.createQuery("from Gato g where g.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }

    @Override
    public Gato pesquisarGatoTipoComp(String tipo, Session sessao) throws HibernateException {
        Query<Gato> consulta = sessao.createQuery("from Gato g where g.tipo like :tipo");
        consulta.setParameter("tipo", tipo);
        return consulta.getSingleResult();
    }

}
