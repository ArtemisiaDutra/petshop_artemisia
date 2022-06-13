/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Comportamento;
import br.com.senac.util.GeradorUtil;
import static br.com.senac.util.GeradorUtil.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Artemisia Dutra
 */
public class ComportamentoDaoImplTest {

    private Comportamento comportamento;
    private ComportamentoDao comportamentoDao;
    private Session sessao;

    public ComportamentoDaoImplTest() {
        comportamentoDao = new ComportamentoDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("Salvar");
//        ComportamentoDaoImplTest cdit = new ComportamentoDaoImplTest();

        sessao = HibernateUtil.abrirConexao();
        comportamento = new Comportamento(gerarTipoCompAnimal(), gerarDescricaoCompAnimal(), false);

        comportamentoDao.salvarOuAlterar(comportamento, sessao);
        sessao.close();
        assertNotNull(comportamento.getId());

    }
//    @Test

    public void testAlterar() {
        System.out.println("alterar");
        buscarComportamentoBd();
        comportamento.setTipo(gerarTipoCompAnimal());
        comportamento.setDescricao(gerarDescricaoCompAnimal());

        sessao = HibernateUtil.abrirConexao();
        comportamentoDao.salvarOuAlterar(comportamento, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirConexao();
        Comportamento compAlt = comportamentoDao.pesquisarPorId(comportamento.getId(), sessao);
        sessao.close();
        assertEquals(comportamento.getTipo(), compAlt.getTipo());
        assertEquals(comportamento.getDescricao(), compAlt.getDescricao());
        assertEquals(comportamento.getAtivo(), compAlt.getAtivo());
    }

//    @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarComportamentoBd();
        sessao = HibernateUtil.abrirConexao();
        comportamentoDao.excluir(comportamento, sessao);

        Comportamento compExc = comportamentoDao.pesquisarPorId(comportamento.getId(), sessao);
        sessao.close();
        assertNull(compExc);
    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarComportamentoBd();
        sessao = HibernateUtil.abrirConexao();
        Comportamento comport = comportamentoDao.pesquisarPorId(comportamento.getId(), sessao);
        sessao.close();
        assertNotNull(comport);
    }

//    @Test 
    public void testPesquisarPorDescricao() {
        String hql = "from Comportamento c";
        sessao = HibernateUtil.abrirConexao();
        Query<Comportamento> consulta = sessao.createQuery(hql);
        List<Comportamento> comportamentos = consulta.getResultList();
        sessao.close();
        if (comportamentos.isEmpty()) {
            testSalvar();
        }
        assertTrue(!comportamentos.isEmpty());
//        List<Comportamento> comportamentos = comportamentoDao.pesquisarPorDescricao(comportamento.getDescricao(), sessao);
    }

    public Comportamento buscarComportamentoBd() {
        String hql = "from Comportamento c";
        sessao = HibernateUtil.abrirConexao();
        Query<Comportamento> consulta = sessao.createQuery(hql);
        List<Comportamento> comportamentos = consulta.getResultList();
        sessao.close();
  
        if (comportamentos.isEmpty()) {
            testSalvar();
        } else {
            comportamento = comportamentos.get(0);
        }
        return comportamento;
    }
}
