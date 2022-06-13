/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Comportamento;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Artemisia Dutra
 */
public interface ComportamentoDao extends BaseDao<Comportamento, Long> {

    List<Comportamento> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
//    List<Comportamento> pesquisarPorDescricao(String descricao, Session sessao) throws HibernateException;

}
