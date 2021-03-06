/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cachorro;
import br.com.senac.entidade.Gato;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Artemisia Dutra
 */
public interface CachorroDao extends BaseDao<Cachorro, Long>{
    List<Cachorro> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
    List<Cachorro> pesquisarCachorroComp(String comp, Session sessao) throws HibernateException;
}