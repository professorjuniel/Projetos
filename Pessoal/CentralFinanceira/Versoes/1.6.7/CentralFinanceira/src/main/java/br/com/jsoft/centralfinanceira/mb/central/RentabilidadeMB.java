package br.com.jsoft.centralfinanceira.mb.central;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.jsoft.centralfinanceira.bo.central.FatosBoletosBO;
import br.com.jsoft.centralfinanceira.modelo.central.FatosBoletos;
import br.com.jsoft.centralfinanceira.vo.ConvenioCampConsultVO;
import br.com.jsoft.centralfinanceira.vo.ReceitaEDespesaVO;
import br.com.jsoft.centralfinanceira.vo.RentabilidadeVO;
import com.xpert.core.exception.BusinessException;
import com.xpert.faces.utils.FacesJasper;
import com.xpert.faces.utils.FacesMessageUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class RentabilidadeMB extends AbstractBaseBean<FatosBoletos> implements Serializable {

    @EJB
    private FatosBoletosBO fatosBoletosBO;

    private List<RentabilidadeVO> boletos; //1

    private List<RentabilidadeVO> boletosSite; //2

    private List<RentabilidadeVO> boletosCreditos; //3

    private List<RentabilidadeVO> boletosRecargas; //4

    private List<RentabilidadeVO> boletosValeGas; //5

    private List<RentabilidadeVO> boletosOps; //6

    private List<RentabilidadeVO> boletosBB; //7

    private List<RentabilidadeVO> boletosBP; //7

    private List<ReceitaEDespesaVO> receitaEDespesaVO;

    private List<RentabilidadeVO> boletosFiltrados;

    private List<RentabilidadeVO> boletosSiteFiltrados;

    private List<RentabilidadeVO> boletosCreditosFiltrados;

    private List<RentabilidadeVO> boletosRecargasFiltrados;

    private List<RentabilidadeVO> boletosValeGasFiltrados;

    private List<RentabilidadeVO> boletosOpsFiltrados;

    private List<RentabilidadeVO> boletosBBFiltrados;

    private List<RentabilidadeVO> boletosBPFiltrados;

    private List<ReceitaEDespesaVO> receitaEDespesaVOFiltrados;

    private ConvenioCampConsultVO camposConsulta;

    private LineChartModel animatedModel1;

    private BarChartModel animatedModel2;

    @Override
    public void init() {
        camposConsulta = new ConvenioCampConsultVO();
        boletosOps = new ArrayList<RentabilidadeVO>();
        boletosValeGas = new ArrayList<RentabilidadeVO>();
        boletosRecargas = new ArrayList<RentabilidadeVO>();
        boletosCreditos = new ArrayList<RentabilidadeVO>();
        boletosSite = new ArrayList<RentabilidadeVO>();
        boletos = new ArrayList<RentabilidadeVO>();
        boletosBB = new ArrayList<RentabilidadeVO>();
        boletosBP = new ArrayList<RentabilidadeVO>();
        receitaEDespesaVO = new ArrayList<ReceitaEDespesaVO>();
        animatedModel1 = new LineChartModel();
        animatedModel2 = new BarChartModel();
    }

    public void getListasConvenioLoja() throws BusinessException {

        if (validarDataFinalMenorInicial(convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()))
                && validarDataInicialMaiorAtual(convertPeriodDate(camposConsulta.getDataInicial()))) {

            boletos = fatosBoletosBO.listRentabilidade(camposConsulta.getLoja() != null ? camposConsulta.getLoja().getId() : null, convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()), 1);
            boletosSite = fatosBoletosBO.listRentabilidade(camposConsulta.getLoja() != null ? camposConsulta.getLoja().getId() : null, convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()), 2);
            boletosCreditos = fatosBoletosBO.listRentabilidade(camposConsulta.getLoja() != null ? camposConsulta.getLoja().getId() : null, convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()), 3);
            boletosRecargas = fatosBoletosBO.listRentabilidade(camposConsulta.getLoja() != null ? camposConsulta.getLoja().getId() : null, convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()), 4);
            boletosValeGas = fatosBoletosBO.listRentabilidade(camposConsulta.getLoja() != null ? camposConsulta.getLoja().getId() : null, convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()), 5);
            boletosOps = fatosBoletosBO.listRentabilidade(camposConsulta.getLoja() != null ? camposConsulta.getLoja().getId() : null, convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()), 6);
            boletosBB = fatosBoletosBO.listRentabilidade(camposConsulta.getLoja() != null ? camposConsulta.getLoja().getId() : null, convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()), 7);
            boletosBP = fatosBoletosBO.listRentabilidade(camposConsulta.getLoja() != null ? camposConsulta.getLoja().getId() : null, convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()), 8);
            receitaEDespesaVO = fatosBoletosBO.listRentablidadeRecDesp(camposConsulta.getLoja() != null ? camposConsulta.getLoja().getId() : null, convertPeriodDate(camposConsulta.getDataInicial()), convertPeriodDate(camposConsulta.getDataFinal()));
        }

    }

    private boolean validarDataFinalMenorInicial(Date data1, Date data2) throws BusinessException {
        if (data1 != null && data2 != null) {
            if (data1.after(data2)) {
                FacesMessageUtils.error("O Periodo Inicial não pode ser maior que o Periodo Final!");
                return false;
            }
        }

        return true;
    }

    private boolean validarDataInicialMaiorAtual(Date data1) throws BusinessException {
        if (data1 != null) {
            if (data1.after(new Date())) {
                FacesMessageUtils.error("O Periodo Inicial não pode ser maior que o Periodo Atual!");
                return false;
            }
        }

        return true;
    }

    private Date convertPeriodDate(String data) {
        Calendar agora = Calendar.getInstance();
        if (!"".equals(data)) {
            String[] temp = data.split("/");
            Integer periodoTemp = Integer.valueOf(temp[1] + temp[0]);
            agora.set(Integer.valueOf(temp[1]), Integer.valueOf(temp[0]) - 1, 1);

            return agora.getTime();
        }
        return null;
    }

    public Integer totalGuiasBoletos() {

        Integer valor = 0;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getGuias() != null) {
                valor += boleto.getGuias();
            }
        }
        return valor;
    }

    public Integer totalGuiasBoletosSite() {

        Integer valor = 0;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getGuias() != null) {
                valor += boleto.getGuias();
            }
        }
        return valor;
    }

    public Integer totalGuiasBoletosCreditos() {

        Integer valor = 0;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getGuias() != null) {
                valor += boleto.getGuias();
            }
        }
        return valor;
    }

    public Integer totalGuiasBoletosRecargas() {

        Integer valor = 0;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getGuias() != null) {
                valor += boleto.getGuias();
            }
        }
        return valor;
    }

    public Integer totalGuiasBoletosValeGas() {

        Integer valor = 0;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getGuias() != null) {
                valor += boleto.getGuias();
            }
        }
        return valor;
    }

    public Integer totalGuiasBoletosOps() {

        Integer valor = 0;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getGuias() != null) {
                valor += boleto.getGuias();
            }
        }
        return valor;
    }

    public Integer totalGuiasBoletosBB() {

        Integer valor = 0;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getGuias() != null) {
                valor += boleto.getGuias();
            }
        }
        return valor;
    }

    public Integer totalGuiasBoletosBP() {

        Integer valor = 0;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getGuias() != null) {
                valor += boleto.getGuias();
            }
        }
        return valor;
    }

    public Integer totalGuias() {

        return totalGuiasBoletos() + totalGuiasBoletosSite() + totalGuiasBoletosCreditos()
                + totalGuiasBoletosRecargas() + totalGuiasBoletosValeGas() + totalGuiasBoletosOps() + totalGuiasBoletosBB() + totalGuiasBoletosBP();
    }

    public BigDecimal totalValorBoletos() {

        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getValor() != null) {
                valor = valor.add(boleto.getValor());
            }
        }
        return valor;
    }

    public BigDecimal totalValorBoletosSite() {

        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getValor() != null) {
                valor = valor.add(boleto.getValor());
            }
        }
        return valor;
    }

    public BigDecimal totalValorBoletosCreditos() {

        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getValor() != null) {
                valor = valor.add(boleto.getValor());
            }
        }
        return valor;
    }

    public BigDecimal totalValorBoletosRecargas() {

        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getValor() != null) {
                valor = valor.add(boleto.getValor());
            }
        }
        return valor;
    }

    public BigDecimal totalValorBoletosValeGas() {

        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getValor() != null) {
                valor = valor.add(boleto.getValor());
            }
        }
        return valor;
    }

    public BigDecimal totalValorBoletosOps() {

        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getValor() != null) {
                valor = valor.add(boleto.getValor());
            }
        }
        return valor;
    }

    public BigDecimal totalValorBoletosBB() {

        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getValor() != null) {
                valor = valor.add(boleto.getValor());
            }
        }
        return valor;
    }

    public BigDecimal totalValorBoletosBP() {

        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }

        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getValor() != null) {
                valor = valor.add(boleto.getValor());
            }
        }
        return valor;
    }

    public BigDecimal totalValor() {
        return totalValorBoletos().add(totalValorBoletosSite()).add(totalValorBoletosCreditos()).add(totalValorBoletosRecargas())
                .add(totalValorBoletosValeGas()).add(totalValorBoletosOps()).add(totalValorBoletosBB()).add(totalValorBoletosBP());
    }

    public BigDecimal totalTarifaConvenioBoletos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaConvenio() != null) {
                valor = valor.add(boleto.getTarifaConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaConvenioBoletosSite() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaConvenio() != null) {
                valor = valor.add(boleto.getTarifaConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaConvenioBoletosCreditos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaConvenio() != null) {
                valor = valor.add(boleto.getTarifaConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaConvenioBoletosRecargas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaConvenio() != null) {
                valor = valor.add(boleto.getTarifaConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaConvenioBoletosValeGas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaConvenio() != null) {
                valor = valor.add(boleto.getTarifaConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaConvenioBoletosOps() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaConvenio() != null) {
                valor = valor.add(boleto.getTarifaConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaConvenioBoletosBB() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaConvenio() != null) {
                valor = valor.add(boleto.getTarifaConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaConvenioBoletosBP() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaConvenio() != null) {
                valor = valor.add(boleto.getTarifaConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaConvenio() {
        return totalTarifaConvenioBoletos().add(totalTarifaConvenioBoletosSite()).add(totalTarifaConvenioBoletosCreditos())
                .add(totalTarifaConvenioBoletosRecargas()).add(totalTarifaConvenioBoletosValeGas()).add(totalTarifaConvenioBoletosOps()).
                add(totalTarifaConvenioBoletosBB()).add(totalTarifaConvenioBoletosBP());
    }

    public BigDecimal totalComissaoConvenioBoletos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoConvenio() != null) {
                valor = valor.add(boleto.getComissaoConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoConvenioBoletosSite() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoConvenio() != null) {
                valor = valor.add(boleto.getComissaoConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoConvenioBoletosCreditos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoConvenio() != null) {
                valor = valor.add(boleto.getComissaoConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoConvenioBoletosRecargas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoConvenio() != null) {
                valor = valor.add(boleto.getComissaoConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoConvenioBoletosValeGas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoConvenio() != null) {
                valor = valor.add(boleto.getComissaoConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoConvenioBoletosOps() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoConvenio() != null) {
                valor = valor.add(boleto.getComissaoConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoConvenioBoletosBB() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoConvenio() != null) {
                valor = valor.add(boleto.getComissaoConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoConvenioBoletosBP() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoConvenio() != null) {
                valor = valor.add(boleto.getComissaoConvenio());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoConvenio() {
        return totalComissaoConvenioBoletos().add(totalComissaoConvenioBoletosSite()).add(totalComissaoConvenioBoletosCreditos())
                .add(totalComissaoConvenioBoletosRecargas()).add(totalComissaoConvenioBoletosValeGas()).add(totalComissaoConvenioBoletosOps())
                .add(totalComissaoConvenioBoletosBB()).add(totalComissaoConvenioBoletosBP());
    }

    public BigDecimal totalTarifaLojaBoletos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaLojaBoletosSite() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaLojaBoletosCreditos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaLojaBoletosRecargas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaLojaBoletosValeGas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaLojaBoletosOps() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaLojaBoletosBB() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaLojaBoletosBP() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalTarifaLoja() {
        return totalTarifaLojaBoletos().add(totalTarifaLojaBoletosSite()).add(totalTarifaLojaBoletosCreditos()).add(totalTarifaLojaBoletosRecargas())
                .add(totalTarifaLojaBoletosValeGas()).add(totalTarifaLojaBoletosOps()).add(totalTarifaLojaBoletosBB())
                .add(totalTarifaLojaBoletosBP());

    }

    public BigDecimal totalComissaoLojaBoletos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoLojaBoletosSite() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoLojaBoletosCreditos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoLojaBoletosRecargas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoLojaBoletosValeGas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoLojaBoletosOps() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoLojaBoletosBB() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoLojaBoletosBP() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoLoja() {
        return totalComissaoLojaBoletos().add(totalComissaoLojaBoletosSite()).add(totalComissaoLojaBoletosCreditos()).
                add(totalComissaoLojaBoletosRecargas()).add(totalComissaoLojaBoletosValeGas()).add(totalComissaoLojaBoletosOps())
                .add(totalComissaoLojaBoletosBB());
    }

    public BigDecimal totalComissaoW7Boletos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoW7BoletosSite() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoW7BoletosCreditos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoW7BoletosRecargas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoW7BoletosValeGas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoW7BoletosOps() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoW7BoletosBB() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoW7BoletosBP() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoW7() {
        return totalComissaoW7Boletos().add(totalComissaoW7BoletosSite()).add(totalComissaoW7BoletosCreditos()).add(totalComissaoW7BoletosRecargas())
                .add(totalComissaoW7BoletosValeGas()).add(totalComissaoW7BoletosOps()).add(totalComissaoW7BoletosBB())
                .add(totalComissaoW7BoletosBP());
    }

    public BigDecimal totalComissaoSistemaBoletos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoSistemaBoletosSite() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoSistemaBoletosCreditos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoSistemaBoletosRecargas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoSistemaBoletosValeGas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoSistemaBoletosOps() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoSistemaBoletosBB() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoSistemaBoletosBP() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
        }
        return valor;
    }

    public BigDecimal totalComissaoSistema() {
        return totalComissaoSistemaBoletos().add(totalComissaoSistemaBoletosSite()).add(totalComissaoSistemaBoletosCreditos())
                .add(totalComissaoSistemaBoletosRecargas()).add(totalComissaoSistemaBoletosValeGas()).add(totalComissaoSistemaBoletosOps())
                .add(totalComissaoSistemaBoletosBB()).add(totalComissaoSistemaBoletosBP());
    }

    public BigDecimal totalDescontosBoletos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }

            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalDescontosBoletosSite() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }

            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalDescontosBoletosCreditos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }

            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalDescontosBoletosRecargas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }

            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalDescontosBoletosValeGas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }

            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalDescontosBoletosOps() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }

            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalDescontosBoletosBB() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }

            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalDescontosBoletosBP() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getComissao() != null) {
                valor = valor.add(boleto.getComissao());
            }
            if (boleto.getComissaoLoja() != null) {
                valor = valor.add(boleto.getComissaoLoja());
            }
            if (boleto.getTarifaLoja() != null) {
                valor = valor.add(boleto.getTarifaLoja());
            }

            if (boleto.getComissaoW7() != null) {
                valor = valor.add(boleto.getComissaoW7());
            }
        }
        return valor;
    }

    public BigDecimal totalImpostoBoletos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getImposto() != null) {
                valor = valor.add(boleto.getImposto());
            }
        }
        return valor;
    }

    public BigDecimal totalImpostoBoletosSite() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getImposto() != null) {
                valor = valor.add(boleto.getImposto());
            }
        }
        return valor;
    }

    public BigDecimal totalImpostoBoletosCreditos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getImposto() != null) {
                valor = valor.add(boleto.getImposto());
            }
        }
        return valor;
    }

    public BigDecimal totalImpostoBoletosRecargas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getImposto() != null) {
                valor = valor.add(boleto.getImposto());
            }
        }
        return valor;
    }

    public BigDecimal totalImpostoBoletosValeGas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getImposto() != null) {
                valor = valor.add(boleto.getImposto());
            }
        }
        return valor;
    }

    public BigDecimal totalImpostoBoletosOps() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getImposto() != null) {
                valor = valor.add(boleto.getImposto());
            }
        }
        return valor;
    }

    public BigDecimal totalImpostoBoletosBB() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getImposto() != null) {
                valor = valor.add(boleto.getImposto());
            }
        }
        return valor;
    }

    public BigDecimal totalImpostoBoletosBP() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getImposto() != null) {
                valor = valor.add(boleto.getImposto());
            }
        }
        return valor;
    }

    public BigDecimal totalImposto() {
        return totalImpostoBoletos().add(totalImpostoBoletosSite()).add(totalImpostoBoletosCreditos()).add(totalImpostoBoletosRecargas())
                .add(totalImpostoBoletosValeGas()).add(totalImpostoBoletosOps()).add(totalImpostoBoletosBB()).add(totalImpostoBoletosBP());
    }

    public BigDecimal totalLiquidoBoletos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getLiquido() != null) {
                valor = valor.add(boleto.getLiquido());
            }
        }
        return valor;
    }

    public BigDecimal totalLiquidoBoletosSite() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosSiteFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosSiteFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosSite;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getLiquido() != null) {
                valor = valor.add(boleto.getLiquido());
            }
        }
        return valor;
    }

    public BigDecimal totalLiquidoBoletosCreditos() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosCreditosFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditosFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosCreditos;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getLiquido() != null) {
                valor = valor.add(boleto.getLiquido());
            }
        }
        return valor;
    }

    public BigDecimal totalLiquidoBoletosRecargas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosRecargasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosRecargas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getLiquido() != null) {
                valor = valor.add(boleto.getLiquido());
            }
        }
        return valor;
    }

    public BigDecimal totalLiquidoBoletosValeGas() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosValeGasFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGasFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosValeGas;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getLiquido() != null) {
                valor = valor.add(boleto.getLiquido());
            }
        }
        return valor;
    }

    public BigDecimal totalLiquidoBoletosOps() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosOpsFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosOpsFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosOps;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getLiquido() != null) {
                valor = valor.add(boleto.getLiquido());
            }
        }
        return valor;
    }

    public BigDecimal totalLiquidoBoletosBB() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBBFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBBFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBB;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getLiquido() != null) {
                valor = valor.add(boleto.getLiquido());
            }
        }
        return valor;
    }

    public BigDecimal totalLiquidoBoletosBP() {
        BigDecimal valor = BigDecimal.ZERO;

        List<RentabilidadeVO> boletosTemp;

        if (boletosBPFiltrados != null) {
            boletosTemp = (List<RentabilidadeVO>) boletosBPFiltrados;
        } else {
            boletosTemp = (List<RentabilidadeVO>) boletosBP;
        }
        for (RentabilidadeVO boleto : boletosTemp) {
            if (boleto.getLiquido() != null) {
                valor = valor.add(boleto.getLiquido());
            }
        }
        return valor;
    }

    public BigDecimal totalReceitaDespesas() {
        BigDecimal valor = BigDecimal.ZERO;
        List<ReceitaEDespesaVO> receitaDespesaTemp;

        if (receitaEDespesaVOFiltrados != null) {
            receitaDespesaTemp = (List<ReceitaEDespesaVO>) receitaEDespesaVOFiltrados;
        } else {
            receitaDespesaTemp = (List<ReceitaEDespesaVO>) receitaEDespesaVO;
        }

        for (ReceitaEDespesaVO item : receitaDespesaTemp) {
            if (item.getValor() != null) {
                valor = valor.add(item.getValor());
            }
        }

        return valor;

    }

    public BigDecimal totalLiquido() {
        return totalLiquidoBoletos().add(totalLiquidoBoletosSite()).add(totalLiquidoBoletosCreditos()).add(totalLiquidoBoletosRecargas())
                .add(totalLiquidoBoletosValeGas()).add(totalLiquidoBoletosOps()).add(totalLiquidoBoletosBB()).add(totalLiquidoBoletosBP());
    }

    public BigDecimal totalLiquidoComDespesas() {
        return totalLiquido().add(totalReceitaDespesas());
    }

    @Override
    public FatosBoletosBO getBO() {
        return fatosBoletosBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    public List<RentabilidadeVO> getBoletosBB() {
        return boletosBB;
    }

    public void setBoletosBB(List<RentabilidadeVO> boletosBB) {
        this.boletosBB = boletosBB;
    }

    public List<RentabilidadeVO> getBoletosBBFiltrados() {
        return boletosBBFiltrados;
    }

    public void setBoletosBBFiltrados(List<RentabilidadeVO> boletosBBFiltrados) {
        this.boletosBBFiltrados = boletosBBFiltrados;
    }

    public List<RentabilidadeVO> getBoletos() {
        return boletos;
    }

    public List<RentabilidadeVO> getBoletosSite() {
        return boletosSite;
    }

    public List<RentabilidadeVO> getBoletosCreditos() {
        return boletosCreditos;
    }

    public List<RentabilidadeVO> getBoletosRecargas() {
        return boletosRecargas;
    }

    public List<RentabilidadeVO> getBoletosValeGas() {
        return boletosValeGas;
    }

    public List<RentabilidadeVO> getBoletosOps() {
        return boletosOps;
    }

    public ConvenioCampConsultVO getCamposConsulta() {
        return camposConsulta;
    }

    public void setCamposConsulta(ConvenioCampConsultVO camposConsulta) {
        this.camposConsulta = camposConsulta;
    }

    public List<RentabilidadeVO> getBoletosFiltrados() {
        return boletosFiltrados;
    }

    public void setBoletosFiltrados(List<RentabilidadeVO> boletosFiltrados) {
        this.boletosFiltrados = boletosFiltrados;
    }

    public List<ReceitaEDespesaVO> getReceitaEDespesaVO() {
        return receitaEDespesaVO;
    }

    public void setReceitaEDespesaVO(List<ReceitaEDespesaVO> receitaEDespesaVO) {
        this.receitaEDespesaVO = receitaEDespesaVO;
    }

    public List<ReceitaEDespesaVO> getReceitaEDespesaVOFiltrados() {
        return receitaEDespesaVOFiltrados;
    }

    public void setReceitaEDespesaVOFiltrados(List<ReceitaEDespesaVO> receitaEDespesaVOFiltrados) {
        this.receitaEDespesaVOFiltrados = receitaEDespesaVOFiltrados;
    }

    public List<RentabilidadeVO> getBoletosSiteFiltrados() {
        return boletosSiteFiltrados;
    }

    public void setBoletosSiteFiltrados(List<RentabilidadeVO> boletosSiteFiltrados) {
        this.boletosSiteFiltrados = boletosSiteFiltrados;
    }

    public List<RentabilidadeVO> getBoletosCreditosFiltrados() {
        return boletosCreditosFiltrados;
    }

    public void setBoletosCreditosFiltrados(List<RentabilidadeVO> boletosCreditosFiltrados) {
        this.boletosCreditosFiltrados = boletosCreditosFiltrados;
    }

    public List<RentabilidadeVO> getBoletosRecargasFiltrados() {
        return boletosRecargasFiltrados;
    }

    public void setBoletosRecargasFiltrados(List<RentabilidadeVO> boletosRecargasFiltrados) {
        this.boletosRecargasFiltrados = boletosRecargasFiltrados;
    }

    public List<RentabilidadeVO> getBoletosValeGasFiltrados() {
        return boletosValeGasFiltrados;
    }

    public void setBoletosValeGasFiltrados(List<RentabilidadeVO> boletosValeGasFiltrados) {
        this.boletosValeGasFiltrados = boletosValeGasFiltrados;
    }

    public List<RentabilidadeVO> getBoletosOpsFiltrados() {
        return boletosOpsFiltrados;
    }

    public void setBoletosOpsFiltrados(List<RentabilidadeVO> boletosOpsFiltrados) {
        this.boletosOpsFiltrados = boletosOpsFiltrados;
    }

    public BigDecimal porcentagemComissao(BigDecimal total, BigDecimal totalPorcentagem) {
        if (total != null && totalPorcentagem != null && total.compareTo(BigDecimal.ZERO) > 0 && totalPorcentagem.compareTo(BigDecimal.ZERO) > 0) {
            return totalPorcentagem.multiply(BigDecimal.valueOf(100)).divide(total, 2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal porcentagemLiquida(BigDecimal total, BigDecimal totalPorcentagem) {
        if (total != null && totalPorcentagem != null && total.compareTo(BigDecimal.ZERO) > 0 && totalPorcentagem.compareTo(BigDecimal.ZERO) > 0) {
            return totalPorcentagem.multiply(BigDecimal.valueOf(100)).divide(total, 2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    public List<RentabilidadeVO> getBoletosBP() {
        return boletosBP;
    }

    public void setBoletosBP(List<RentabilidadeVO> boletosBP) {
        this.boletosBP = boletosBP;
    }

    public List<RentabilidadeVO> getBoletosBPFiltrados() {
        return boletosBPFiltrados;
    }

    public void setBoletosBPFiltrados(List<RentabilidadeVO> boletosBPFiltrados) {
        this.boletosBPFiltrados = boletosBPFiltrados;
    }

    /*------------------------------------------------Parte dos Gráficos --------------------------------------------------*/
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle(getCamposConsulta().getLoja().getNome());
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle(getCamposConsulta().getLoja().getNome());
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    public void exportCSV() {

    }

    /* 
     * @Method: Gerador de Arquivos Excel. 
     * 
     */
    public void gerarXLS() {

        HashMap params = new HashMap();
        params.put("totalGuias", totalGuias());
        params.put("totalValor", totalValor());
        params.put("totalComissaoConvenio", totalComissaoConvenio());
        params.put("totalComissaoLoja", totalComissaoLoja());
        params.put("totalComissaoW7", totalComissaoW7());
        params.put("totalCustoOperacional", totalComissaoSistema());
        params.put("totalImposto", totalImposto());
        params.put("totalLiquido", totalLiquido());
        params.put("porcentagemTotal", porcentagemLiquida(totalLiquido(), totalLiquido()));
        params.put("totalGuiasBoleto", totalGuiasBoletos());
        params.put("totalValorBoleto", totalValorBoletos());
        params.put("totalComissaoConvenioBoleto", totalComissaoConvenioBoletos());
        params.put("totalComissaoLojaBoleto", totalComissaoLojaBoletos());
        params.put("totalComissaoW7Boleto", totalComissaoW7Boletos());
        params.put("totalCustoOperacionalBoleto", totalComissaoSistemaBoletos());
        params.put("totalImpostoBoleto", totalImpostoBoletos());
        params.put("totalLiquidoBoleto", totalLiquidoBoletos());
        params.put("porcentagemTotalBoleto", porcentagemLiquida(totalLiquido(), totalLiquidoBoletos()));
        params.put("totalGuiasSite", totalGuiasBoletosSite());
        params.put("totalValorSite", totalValorBoletosSite());
        params.put("totalComissaoConvenioSite", totalComissaoConvenioBoletosSite());
        params.put("totalComissaoLojaSite", totalComissaoLojaBoletosSite());
        params.put("totalComissaoW7Site", totalComissaoW7BoletosSite());
        params.put("totalCustoOperacionalSite", totalComissaoSistemaBoletosSite());
        params.put("totalImpostoSite", totalImpostoBoletosSite());
        params.put("totalLiquidoSite", totalLiquidoBoletosSite());
        params.put("porcentagemTotalSite", porcentagemLiquida(totalLiquido(), totalLiquidoBoletosSite()));
        params.put("totalGuiasCreditos", totalGuiasBoletosCreditos());
        params.put("totalValorCreditos", totalValorBoletosCreditos());
        params.put("totalComissaoConvenioCreditos", totalComissaoConvenioBoletosCreditos());
        params.put("totalComissaoLojaCreditos", totalComissaoLojaBoletosCreditos());
        params.put("totalComissaoW7Creditos", totalComissaoW7BoletosCreditos());
        params.put("totalCustoOperacionalCreditos", totalComissaoSistemaBoletosCreditos());
        params.put("totalImpostoCreditos", totalImpostoBoletosCreditos());
        params.put("totalLiquidoCreditos", totalLiquidoBoletosCreditos());
        params.put("porcentagemTotalCreditos", porcentagemLiquida(totalLiquido(), totalLiquidoBoletosCreditos()));
        params.put("totalGuiasRecarga", totalGuiasBoletosRecargas());
        params.put("totalValorRecarga", totalValorBoletosRecargas());
        params.put("totalComissaoConvenioRecarga", totalComissaoConvenioBoletosRecargas());
        params.put("totalComissaoLojaRecarga", totalComissaoLojaBoletosRecargas());
        params.put("totalComissaoW7Recarga", totalComissaoW7BoletosRecargas());
        params.put("totalCustoOperacionalRecarga", totalComissaoSistemaBoletosRecargas());
        params.put("totalImpostoRecarga", totalImpostoBoletosRecargas());
        params.put("totalLiquidoRecarga", totalLiquidoBoletosRecargas());
        params.put("porcentagemTotalRecarga", porcentagemLiquida(totalLiquido(), totalLiquidoBoletosRecargas()));
        params.put("totalGuiasGas", totalGuiasBoletosValeGas());
        params.put("totalValorGas", totalValorBoletosValeGas());
        params.put("totalComissaoConvenioGas", totalComissaoConvenioBoletosValeGas());
        params.put("totalComissaoLojaGas", totalComissaoLojaBoletosValeGas());
        params.put("totalComissaoW7Gas", totalComissaoW7BoletosValeGas());
        params.put("totalCustoOperacionalGas", totalComissaoSistemaBoletosValeGas());
        params.put("totalImpostoGas", totalImpostoBoletosValeGas());
        params.put("totalLiquidoGas", totalLiquidoBoletosValeGas());
        params.put("porcentagemTotalGas", porcentagemLiquida(totalLiquido(), totalLiquidoBoletosValeGas()));
        params.put("totalGuiasOP", totalGuiasBoletosOps());
        params.put("totalValorOP", totalValorBoletosOps());
        params.put("totalComissaoConvenioOP", totalComissaoConvenioBoletosOps());
        params.put("totalComissaoLojaOP", totalComissaoLojaBoletosOps());
        params.put("totalComissaoW7OP", totalComissaoW7BoletosOps());
        params.put("totalCustoOperacionalOP", totalComissaoSistemaBoletosOps());
        params.put("totalImpostoOP", totalImpostoBoletosOps());
        params.put("totalLiquidoOP", totalLiquidoBoletosOps());
        params.put("porcentagemTotalOP", porcentagemLiquida(totalLiquido(), totalLiquidoBoletosOps()));
        params.put("totalGuiasBB", totalGuiasBoletosBB());
        params.put("totalValorBB", totalValorBoletosBB());
        params.put("totalComissaoConvenioBB", totalComissaoConvenioBoletosBB());
        params.put("totalComissaoLojaBB", totalComissaoLojaBoletosBB());
        params.put("totalComissaoW7BB", totalComissaoW7BoletosBB());
        params.put("totalCustoOperacionalBB", totalComissaoSistemaBoletosBB());
        params.put("totalImpostoBB", totalImpostoBoletosBB());
        params.put("totalLiquidoBB", totalLiquidoBoletosBB());
        params.put("porcentagemTotalBB", porcentagemLiquida(totalLiquido(), totalLiquidoBoletosBB()));
        params.put("totalGuiasBP", totalGuiasBoletosBP());
        params.put("totalValorBP", totalValorBoletosBP());
        params.put("totalComissaoConvenioBP", totalComissaoConvenioBoletosBP());
        params.put("totalComissaoLojaBP", totalComissaoLojaBoletosBP());
        params.put("totalComissaoW7BP", totalComissaoW7BoletosBP());
        params.put("totalCustoOperacionalBP", totalComissaoSistemaBoletosBP());
        params.put("totalImpostoBP", totalImpostoBoletosBP());
        params.put("totalLiquidoBP", totalLiquidoBoletosBP());
        params.put("porcentagemTotalBP", porcentagemLiquida(totalLiquido(), totalLiquidoBoletosBP()));
        params.put("totalReceitaDespesa", totalReceitaDespesas());
        params.put("totalLiquidoTotal", totalLiquidoComDespesas());
        params.put("porcentagemRecDesp", porcentagemLiquida(totalLiquido(), totalReceitaDespesas().multiply(BigDecimal.ONE.negate())));
        params.put("totalLiqueidoComDesp", porcentagemLiquida(totalLiquido(), totalLiquidoComDespesas()));

        
        FacesJasper.createJasperExcel(null, params,
                "/WEB-INF/report/template/totaisRentabilidade.jasper", "Total Rentabilidade", null);

    }

}
