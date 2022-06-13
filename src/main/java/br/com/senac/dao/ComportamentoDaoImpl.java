/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Comportamento;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Artemisia Dutra
 */
public class ComportamentoDaoImpl extends BaseDaoImpl<Comportamento, Long> implements ComportamentoDao, Serializable {

    @Override
    public Comportamento pesquisarPorId(Long id, Session sessao) throws HibernateException {

        return sessao.find(Comportamento.class, id);
    }
  
    @Override
    public List<Comportamento> pesquisarPorNome(String nome, Session sessao) throws HibernateException {

        Query<Comportamento> consulta = sessao.createQuery("from Comportamento c where c.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
       return consulta.getResultList();
    }




    


}
