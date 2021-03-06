package br.gov.pi.ati.ati_guard.dao.controleacesso.impl;

import br.gov.pi.ati.ati_guard.application.BaseDAOImpl;
import br.gov.pi.ati.ati_guard.dao.controleacesso.PerfilDAO;
import br.gov.pi.ati.ati_guard.modelo.controleacesso.Perfil;
import br.gov.pi.ati.ati_guard.modelo.controleacesso.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Ayslan
 */
@Stateless
public class PerfilDAOImpl extends BaseDAOImpl<Perfil> implements PerfilDAO {

    @Override
    public Class getEntityClass() {
        return Perfil.class;
    }

    @Override
    public List<Perfil> getPerfis(Usuario usuario) {

        String queryString = "SELECT perfis FROM " + Usuario.class.getName() + " u WHERE u =?1 ";
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter(1, usuario);

        return query.getResultList();

    }
}
