package br.com.jsoft.centralfinanceira.dao.central.impl;

import br.com.jsoft.centralfinanceira.application.BaseDAOImpl;
import br.com.jsoft.centralfinanceira.dao.central.LojaDAO;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.GrupoLoja;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.Loja;
import com.xpert.persistence.query.QueryBuilder;
import com.xpert.persistence.query.Restrictions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Juniel
 */
@Stateless
public class LojaDAOImpl extends BaseDAOImpl<Loja> implements LojaDAO {

    @Override
    public List<Loja> getLojaPorNome(String nome) {
        String queryString = " SELECT p FROM " + Loja.class.getName();
        Query query = null;
        if (ehInteiro(nome)) {
            Long id = Long.parseLong(nome);
            queryString += " p WHERE p.id = ?1 ORDER BY p.id ";
            query = getEntityManager().createQuery(queryString);

            query.setParameter(1, id);
        } else {
            queryString += " p WHERE UPPER(p.nome) LIKE UPPER(?1) ORDER BY p.nome ";
            query = getEntityManager().createQuery(queryString);

            query.setParameter(1, "%" + nome + "%");
        }

        return query.getResultList();
    }

    @Override
    public List<Loja> getLojaPorNomeSemGrupo(String nome) {
        QueryBuilder queryBuilder = getQueryBuilder();
        Restrictions restrictions = new Restrictions();

        List<Loja> lojas;

        if (ehInteiro(nome)) {
            Long id = Long.parseLong(nome);
            restrictions.equals("id", id);
        } else {
            restrictions.like("nome", nome);
        }
        restrictions.isNull("grupoLoja");
        restrictions.equals("ativo", true);

        lojas = queryBuilder.from(Loja.class).add(restrictions).orderBy("id").getResultList();

        return lojas;
    }

    private boolean ehInteiro(String s) {

        // cria um array de char  
        char[] c = s.toCharArray();
        boolean d = true;

        for (int i = 0; i < c.length; i++) // verifica se o char não é um dígito  
        {
            if (!Character.isDigit(c[i])) {
                d = false;
                break;
            }
        }

        return d;

    }

}
