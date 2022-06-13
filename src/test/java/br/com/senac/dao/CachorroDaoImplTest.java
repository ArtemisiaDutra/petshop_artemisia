/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cachorro;
import br.com.senac.entidade.Comportamento;
import br.com.senac.entidade.Gato;
import br.com.senac.util.GeradorUtil;
import static br.com.senac.util.GeradorUtil.gerarComportamento;
import static br.com.senac.util.GeradorUtil.gerarNomeAnimal;
import static br.com.senac.util.GeradorUtil.gerarObsevAnimal;
import static br.com.senac.util.GeradorUtil.gerarSexoAnimal;
import static br.com.senac.util.GeradorUtil.gerarTreinado;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Artemisia Dutra
 */
public class CachorroDaoImplTest {

    private Cachorro cachorro;
    private CachorroDao cachorroDao;
    private Session sessao;

    public CachorroDaoImplTest() {
        cachorroDao = new CachorroDaoImpl();
    }

    //@Test 
    public void testSalvar() {
        System.out.println("Salvar");
        cachorro = new Cachorro(true, gerarNomeAnimal(), "2013-15-15", 3.0, gerarSexoAnimal(), gerarObsevAnimal(), gerarComportamento());
        sessao = HibernateUtil.abrirConexao();
        cachorroDao.salvarOuAlterar(cachorro, sessao);
        sessao.close();
        assertNotNull(cachorro.getId());

    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarCachorrobd();
        sessao = HibernateUtil.abrirConexao();
        Cachorro idCachorro = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
        sessao.close();
        assertNotNull(idCachorro);
    }

//    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarCachorrobd();
        sessao = HibernateUtil.abrirConexao();
        List<Cachorro> cachorros = cachorroDao.pesquisarPorNome(cachorro.getNome(), sessao);
        sessao.close();
        assertTrue(!cachorros.isEmpty());

    }

//    @Test
    public void testPesquisarCachorroComp() {
        System.out.println("pesquisarCachorroComp");

    }

//    @Test
    public void testTreinamento() {
        String hql = "from Cachorro c where treinado = true";
        sessao = HibernateUtil.abrirConexao();
        Query<Cachorro> consulta = sessao.createQuery(hql);
        List<Cachorro> cachorros = consulta.getResultList();
        sessao.close();
        if (cachorros.isEmpty()) {
            testSalvar();
        }
        assertTrue(!cachorros.isEmpty());
    }

    private Comportamento comp() {
        Comportamento comp = new Comportamento("Agressivo", "bla bla ", false);
        return comp;
    }
    
    //    @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarCachorrobd();
        cachorro.setNome(gerarNomeAnimal());
        sessao = HibernateUtil.abrirConexao();
        cachorroDao.salvarOuAlterar(cachorro, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirConexao();
        Cachorro cachorroAlt = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
        sessao.close();
        assertEquals(cachorro.getNome(), cachorroAlt.getNome());
    }

    //    @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarCachorrobd();
        sessao = HibernateUtil.abrirConexao();
        cachorroDao.excluir(cachorro, sessao);

        Cachorro cachorroExc = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
        sessao.close();
        assertNull(cachorroExc);
    }

    public Cachorro buscarCachorrobd() {
        String hql = "from Cachorro c";
        sessao = HibernateUtil.abrirConexao();
        Query<Cachorro> consulta = sessao.createQuery(hql);
        List<Cachorro> cachorros = consulta.getResultList();
        sessao.close();
        if (cachorros.isEmpty()) {
            testSalvar();
        } else {
            cachorro = cachorros.get(0);
        }
        return cachorro;
    }
}
