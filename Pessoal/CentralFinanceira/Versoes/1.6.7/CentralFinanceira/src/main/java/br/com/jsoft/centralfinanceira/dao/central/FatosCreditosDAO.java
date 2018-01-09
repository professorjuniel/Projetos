package br.com.jsoft.centralfinanceira.dao.central;

import com.xpert.persistence.dao.BaseDAO;
import br.com.jsoft.centralfinanceira.modelo.central.FatosCreditos;
import br.com.jsoft.centralfinanceira.vo.ComissaoConvenioCredVO;
import br.com.jsoft.centralfinanceira.vo.ComissaoLojaCredVO;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juniel
 */
@Local
public interface FatosCreditosDAO extends BaseDAO<FatosCreditos> {
    public void updateUnitarioConvenio(Long id, BigDecimal valor, Integer periodo);
    public void updateUnitarioLoja(Long id, BigDecimal valor, Integer periodo);
    public List<ComissaoConvenioCredVO> listBoletoConvenio();
    public List<ComissaoLojaCredVO> listBoletoLoja(); 
}
