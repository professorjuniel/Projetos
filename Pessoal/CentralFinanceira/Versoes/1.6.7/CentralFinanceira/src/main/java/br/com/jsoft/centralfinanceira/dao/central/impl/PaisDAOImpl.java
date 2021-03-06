package br.com.jsoft.centralfinanceira.dao.central.impl;

import br.com.jsoft.centralfinanceira.application.BaseDAOImpl;
import br.com.jsoft.centralfinanceira.dao.central.PaisDAO;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.Pais;
import javax.ejb.Stateless;

/**
 *
 * @author KillerBeeTwo
 */
@Stateless
public class PaisDAOImpl extends BaseDAOImpl<Pais> implements PaisDAO {

    @Override
    public Class getEntityClass() {
        return Pais.class;
    }

}
