package br.gov.pi.salvemaria.mb.padrao;

import br.gov.pi.salvemaria.constante.Constantes;
import javax.faces.bean.ManagedBean;

/**
 * class que retorna o valor das constantes
 *
 * @author Ayslan
 */
@ManagedBean
public class ConstantesMB {
    
    public int getMinutosRecuperacaoSenha(){
        return Constantes.MINUTOS_VALIDADE_RECUPERACAO_SENHA;
    }
    
    public String getChaveApiGoogleMaps(){
        return Constantes.CHAVE_API_GOOGLE_MAPS;
    }
    
}
