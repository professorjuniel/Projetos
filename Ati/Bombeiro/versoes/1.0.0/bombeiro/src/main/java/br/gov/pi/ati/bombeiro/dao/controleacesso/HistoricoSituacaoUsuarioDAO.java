package br.gov.pi.ati.bombeiro.dao.controleacesso;

import com.xpert.persistence.dao.BaseDAO;
import br.gov.pi.ati.bombeiro.modelo.controleacesso.HistoricoSituacaoUsuario;
import javax.ejb.Local;

/**
 *
 * @author ayslan
 */
@Local
public interface HistoricoSituacaoUsuarioDAO extends BaseDAO<HistoricoSituacaoUsuario> {
    
}
