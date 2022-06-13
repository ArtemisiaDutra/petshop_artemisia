/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Comportamento;
import br.com.senac.entidade.Gato;
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
public class GatoDaoImplTest {

    private Gato gato;
    private GatoDao gatoDao;
    private Session sessao;

    public GatoDaoImplTest() {
        gatoDao = new GatoDaoImpl();

    }

    //@Test 
    public void testSalvar() {
        System.out.println("Salvar");
        gato = new Gato(true, false, gerarNomeAnimal(), "2013-08-20", 3.0, gerarSexoAnimal(), gerarObsevAnimal(), gerarComportamento());
        sessao = HibernateUtil.abrirConexao();
        gatoDao.salvarOuAlterar(gato, sessao);
        sessao.close();
        assertNotNull(gato.getId());

    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarGatobd();
        sessao = HibernateUtil.abrirConexao();
        Gato idGato = gatoDao.pesquisarPorId(gato.getId(), sessao);
        sessao.close();
        assertNotNull(idGato);

    }

//    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarGatobd();
        sessao = HibernateUtil.abrirConexao();
        List<Gato> gatos = gatoDao.pesquisarPorNome(gato.getNome(), sessao);
        sessao.close();
        assertTrue(!gatos.isEmpty());
    }

//    @Test
    public void testPesquisarGatoTipoComp() {
        System.out.println("pesquisarGatoTipoComp");
   
    }


       public void testAlterar() {
        System.out.println("alterar");
        buscarGatobd();
        gato.setNome(gerarNomeAnimal());
        sessao = HibernateUtil.abrirConexao();
        gatoDao.salvarOuAlterar(gato, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirConexao();
        Gato gatoAlt = gatoDao.pesquisarPorId(gato.getId(), sessao);
        sessao.close();
        assertEquals(gato.getNome(), gatoAlt.getNome());
    }
    //    @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarGatobd();
        sessao = HibernateUtil.abrirConexao();
        gatoDao.excluir(gato, sessao);

        Gato gatoExc = gatoDao.pesquisarPorId(gato.getId(), sessao);
        sessao.close();
        assertNull(gatoExc);
    }
    public Gato buscarGatobd() {
        String hql = "from Gato g";
        sessao = HibernateUtil.abrirConexao();
        Query<Gato> consulta = sessao.createQuery(hql);
        List<Gato> gatos = consulta.getResultList();
        sessao.close();
        if (gatos.isEmpty()) {
            testSalvar();
        } else {
            gato = gatos.get(0);
        }
        return gato;
    }
}
