/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsoft.centralfinanceira.mb.regente;

import br.com.jsoft.centralfinanceira.bo.central.LojaBO;
import br.com.jsoft.centralfinanceira.modelo.auxiliar.ColumnModel;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.GrupoLoja;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.ListaLoja;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.ListaLoja_Loja;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.Loja;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.TipoLoja;
import br.com.jsoft.centralfinanceira.modelo.enums.Ativo;
import br.com.jsoft.centralfinanceira.modelo.enums.Situacao;
import br.com.jsoft.centralfinanceira.util.SessaoUtils;
import br.com.jsoft.centralfinanceira.vo.DiaDiaVO;
import com.xpert.faces.utils.FacesMessageUtils;
import com.xpert.persistence.query.QueryBuilder;
import com.xpert.persistence.query.Restrictions;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.component.tabview.TabView;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class DiaADiaMB implements Serializable {

    @EJB
    private LojaBO lojaBO;

    private String menu = "";

    private String periodo;

    private List<ListaLoja_Loja> listaLojas_Loja;

    String menuTemp = "";

    String periodoTemp = "";

    private String codNome;

    private Ativo ativo;

    private TipoLoja tipoLoja;

    private Situacao situacao = Situacao.ATIVO;

    private TabView tabView;

    private List<SelectItem> menus;

    List<Loja> lojas;

    List<Loja> lojasSelecionadas;

    List<Loja> lojasSelecionadasTemp;

    private String columnTemplate = "pos loja";

    private List<ColumnModel> columns;

    private List<DiaDiaVO> recebimentos;

    private List<DiaDiaVO> recebimentosFilter;

    private int tabAtual;

    private GrupoLoja grupo;

    private ListaLoja listaLoja;

    private boolean dia28 = true;

    private boolean dia29 = true;

    private boolean dia30 = true;

    private boolean dia31 = true;

    private BigDecimal totalQuantidadeAnt = BigDecimal.ZERO;

    private BigDecimal totalQuantidade = BigDecimal.ZERO;

    private BigDecimal totalValores = BigDecimal.ZERO;

    private BigDecimal totalValoresAnterior = BigDecimal.ZERO;

    private BigDecimal previsao = BigDecimal.ZERO;

    @PostConstruct
    public void init() {
        lojasSelecionadas = new ArrayList<Loja>();
        lojasSelecionadasTemp = new ArrayList<Loja>();
        SelectItemGroup g1 = new SelectItemGroup("Boletos");
        g1.setSelectItems(new SelectItem[]{new SelectItem("Boletos - Todos", "Boletos - Todos"), new SelectItem("Próprias", "Próprias"), new SelectItem("Site", "Site"),
            new SelectItem("Banco do Brasil", "Banco do Brasil"), new SelectItem("Banco Popular", "Banco Popular")});

        SelectItemGroup g2 = new SelectItemGroup("Crédito e Recarga");
        g2.setSelectItems(new SelectItem[]{new SelectItem("Crédito e Recarga - Todos", "Crédito e Recarga - Todos"), new SelectItem("Crédito", "Crédito"), new SelectItem("Recarga", "Recarga")});

        SelectItemGroup g3 = new SelectItemGroup("Vale Gás");
        g3.setSelectItems(new SelectItem[]{new SelectItem("Vale Gás - Todos")});

        SelectItemGroup g4 = new SelectItemGroup("Op");
        g4.setSelectItems(new SelectItem[]{new SelectItem("Op - Todos")});

        menus = new ArrayList<SelectItem>();
        menus.add(g1);
        menus.add(g2);
        menus.add(g3);
        menus.add(g4);

        menu = "";

        periodo = periodoAtual();

        menuTemp = "";

        periodoTemp = "";

        lojas = lojaBO.listarLoja(codNome, tipoLoja, Ativo.SIM, null);

        recebimentos = new ArrayList<DiaDiaVO>();
        QueryBuilder queryBuilder = lojaBO.getDAO().getQueryBuilder();
        Restrictions restrictions = new Restrictions();;
        restrictions.equals("l.usuario", SessaoUtils.getUser());

        listaLojas_Loja = queryBuilder.from(ListaLoja_Loja.class, "ll").leftJoinFetch("ll.listaLoja", "l").add(restrictions).getResultList();

        for (ListaLoja_Loja listloja : listaLojas_Loja) {
            Loja lojaTemp = new Loja();
            lojaTemp = lojaBO.getDAO().getInitialized(listloja.getLoja());
            lojasSelecionadas.add(lojaTemp);
        }
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public List<SelectItem> getMenus() {
        return menus;
    }

    public void setMenus(List<SelectItem> menus) {
        this.menus = menus;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getColumnTemplate() {
        return columnTemplate;
    }

    public void setColumnTemplate(String columnTemplate) {
        this.columnTemplate = columnTemplate;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public List<DiaDiaVO> getRecebimentos() {
        return recebimentos;
    }

    public void setRecebimentos(List<DiaDiaVO> recebimentos) {
        this.recebimentos = recebimentos;
    }

    public List<DiaDiaVO> getRecebimentosFilter() {
        return recebimentosFilter;
    }

    public void setRecebimentosFilter(List<DiaDiaVO> recebimentosFilter) {
        this.recebimentosFilter = recebimentosFilter;
    }

    public List<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(List<Loja> lojas) {
        this.lojas = lojas;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public TabView getTabView() {
        return tabView;
    }

    public void setTabView(TabView tabView) {
        this.tabView = tabView;
    }

    public List<Loja> getLojasSelecionadas() {
        return lojasSelecionadas;
    }

    public void setLojasSelecionadas(List<Loja> lojasSelecionadas) {
        this.lojasSelecionadas = lojasSelecionadas;
    }

    public String getCodNome() {
        return codNome;
    }

    public void setCodNome(String codNome) {
        this.codNome = codNome;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public TipoLoja getTipoLoja() {
        return tipoLoja;
    }

    public void setTipoLoja(TipoLoja tipoLoja) {
        this.tipoLoja = tipoLoja;
    }

    public int getTabAtual() {
        return tabAtual;
    }

    public void setTabAtual(int tabAtual) {
        this.tabAtual = tabAtual;
    }

    public GrupoLoja getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoLoja grupo) {
        this.grupo = grupo;
    }

    public ListaLoja getListaLoja() {
        return listaLoja;
    }

    public void setListaLoja(ListaLoja listaLoja) {
        this.listaLoja = listaLoja;
    }

    public boolean isDia28() {
        return dia28;
    }

    public void setDia28(boolean dia28) {
        this.dia28 = dia28;
    }

    public boolean isDia29() {
        return dia29;
    }

    public void setDia29(boolean dia29) {
        this.dia29 = dia29;
    }

    public boolean isDia30() {
        return dia30;
    }

    public void setDia30(boolean dia30) {
        this.dia30 = dia30;
    }

    public boolean isDia31() {
        return dia31;
    }

    public void setDia31(boolean dia31) {
        this.dia31 = dia31;
    }

    public BigDecimal getTotalQuantidadeAnt() {
        return totalQuantidadeAnt;
    }

    public void setTotalQuantidadeAnt(BigDecimal totalQuantidadeAnt) {
        this.totalQuantidadeAnt = totalQuantidadeAnt;
    }

    public BigDecimal getTotalQuantidade() {
        return totalQuantidade;
    }

    public void setTotalQuantidade(BigDecimal totalQuantidade) {
        this.totalQuantidade = totalQuantidade;
    }

    public BigDecimal getTotalValores() {
        return totalValores;
    }

    public void setTotalValores(BigDecimal totalValores) {
        this.totalValores = totalValores;
    }

    public BigDecimal getTotalValoresAnterior() {
        return totalValoresAnterior;
    }

    public void setTotalValoresAnterior(BigDecimal totalValoresAnterior) {
        this.totalValoresAnterior = totalValoresAnterior;
    }

    private int getUltimoDiaMes() {
        Calendar c = Calendar.getInstance();
        String[] temp;
        int mes, ano;

        if (periodo == null || periodo.equals("")) {
            c.setTime(new Date());
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
        } else {
            temp = periodo.split("/");
            mes = Integer.parseInt(temp[0]);
            ano = Integer.parseInt(temp[1]);
            c.set(Calendar.MONTH, mes - 1);
            c.set(Calendar.YEAR, ano);
        }

        int ultimodia = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        return ultimodia;
    }

    public String getMesAno() {
        Calendar c = Calendar.getInstance();
        String[] temp;
        int mes, ano;

        if (periodo == null || periodo.equals("")) {
            c.setTime(new Date());
            mes = c.get(Calendar.MONTH) + 1;
            ano = c.get(Calendar.YEAR);
        } else {
            temp = periodo.split("/");
            mes = Integer.parseInt(temp[0]);
            ano = Integer.parseInt(temp[1]);
            c.set(Calendar.MONTH, mes - 1);
            c.set(Calendar.YEAR, ano);
        }

        return convertMes(mes) + "/" + ano;
    }

    private String periodoAtual() {
        Calendar c = Calendar.getInstance();

        int mes, ano;

        c.setTime(new Date());
        mes = c.get(Calendar.MONTH) + 1;
        ano = c.get(Calendar.YEAR);

        return mes + "/" + ano;
    }

    public void buscar() {

        menu = menu != null ? menu : "";
        periodo = periodo != null ? periodo : "";

        if (!menuTemp.equals(menu) || !periodoTemp.equals(periodo) || !lojasSelecionadasTemp.equals(lojasSelecionadas)) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            int mes, mesAtual = c.get(Calendar.MONTH) + 1;
            int ano, anoAtual = c.get(Calendar.YEAR);
            if (periodo != null) {
                if (!periodo.equals("")) {
                    String[] temp = periodo.split("/");
                    mes = Integer.parseInt(temp[0]);
                    ano = Integer.parseInt(temp[1]);

                    if (!(mes >= 1 && mes <= 12)) {
                        FacesMessageUtils.error("O mes do perído deve está no intervalo de 01 a 12!");
                    } else {
                        if (ano > anoAtual) {
                            FacesMessageUtils.error("O ano do período não dever ser maior que o ano atual!");
                        } else {
                            dia28 = false;
                            dia29 = false;
                            dia30 = false;
                            dia31 = false;

                            if (getUltimoDiaMes() > 27) {
                                dia28 = true;
                            }
                            if (getUltimoDiaMes() > 28) {
                                dia29 = true;
                            }
                            if (getUltimoDiaMes() > 29) {
                                dia30 = true;
                            }
                            if (getUltimoDiaMes() > 30) {
                                dia31 = true;
                            }

                            recebimentos = lojaBO.listaRecebimentoLoja(lojasSelecionadas, periodo, menu);
                        }
                    }
                } else {
                    recebimentos = lojaBO.listaRecebimentoLoja(lojasSelecionadas, periodo, menu);
                }

            } else {
                recebimentos = lojaBO.listaRecebimentoLoja(lojasSelecionadas, periodo, menu);
            }
        }
        menuTemp = menu;
        periodoTemp = periodo;
        lojasSelecionadasTemp = lojasSelecionadas;
    }

    public void buscarLoja() {
        lojas = lojaBO.listarLoja(codNome, tipoLoja, ativo, grupo);
        lojasSelecionadas = lojas;
    }

    public void buscarLojaPorSituacao() {
        if (situacao == Situacao.ATIVO) {
            lojas = lojaBO.listarLoja(codNome, tipoLoja, Ativo.SIM, null);
        } else {
            if (situacao == Situacao.INATIVO) {
                lojas = lojaBO.listarLoja(codNome, tipoLoja, Ativo.NAO, null);
            } else {
                lojas = lojaBO.listarLoja(codNome, tipoLoja, null, null);
            }
        }

    }

    public BigDecimal totalDia01() {
        BigDecimal valor = null;
        BigDecimal valorTemp = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_01() != null) {
                valor = valorTemp.add(dia.getDia_01());
            }
        }
        return valor;
    }

    public BigDecimal totalDia02() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_02() != null) {
                valor = valor.add(dia.getDia_02());
            }
        }
        return valor;
    }

    public BigDecimal totalDia03() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_03() != null) {
                valor = valor.add(dia.getDia_03());
            }
        }
        return valor;
    }

    public BigDecimal totalDia04() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_04() != null) {
                valor = valor.add(dia.getDia_04());
            }
        }
        return valor;
    }

    public BigDecimal totalDia05() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_05() != null) {
                valor = valor.add(dia.getDia_05());
            }
        }
        return valor;
    }

    public BigDecimal totalDia06() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_06() != null) {
                valor = valor.add(dia.getDia_06());
            }
        }
        return valor;
    }

    public BigDecimal totalDia07() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_07() != null) {
                valor = valor.add(dia.getDia_07());
            }
        }
        return valor;
    }

    public BigDecimal totalDia08() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_08() != null) {
                valor = valor.add(dia.getDia_08());
            }
        }
        return valor;
    }

    public BigDecimal totalDia09() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_09() != null) {
                valor = valor.add(dia.getDia_09());
            }
        }
        return valor;
    }

    public BigDecimal totalDia10() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_10() != null) {
                valor = valor.add(dia.getDia_10());
            }
        }
        return valor;
    }

    public BigDecimal totalDia11() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_11() != null) {
                valor = valor.add(dia.getDia_11());
            }
        }
        return valor;
    }

    public BigDecimal totalDia12() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_12() != null) {
                valor = valor.add(dia.getDia_12());
            }
        }
        return valor;
    }

    public BigDecimal totalDia13() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_13() != null) {
                valor = valor.add(dia.getDia_13());
            }
        }
        return valor;
    }

    public BigDecimal totalDia14() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_14() != null) {
                valor = valor.add(dia.getDia_14());
            }
        }
        return valor;
    }

    public BigDecimal totalDia15() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_15() != null) {
                valor = valor.add(dia.getDia_15());
            }
        }
        return valor;
    }

    public BigDecimal totalDia16() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_16() != null) {
                valor = valor.add(dia.getDia_16());
            }
        }
        return valor;
    }

    public BigDecimal totalDia17() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_17() != null) {
                valor = valor.add(dia.getDia_17());
            }
        }
        return valor;
    }

    public BigDecimal totalDia18() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_18() != null) {
                valor = valor.add(dia.getDia_18());
            }
        }
        return valor;
    }

    public BigDecimal totalDia19() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_19() != null) {
                valor = valor.add(dia.getDia_19());
            }
        }
        return valor;
    }

    public BigDecimal totalDia20() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_20() != null) {
                valor = valor.add(dia.getDia_20());
            }
        }
        return valor;
    }

    public BigDecimal totalDia21() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_21() != null) {
                valor = valor.add(dia.getDia_21());
            }
        }
        return valor;
    }

    public BigDecimal totalDia22() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_22() != null) {
                valor = valor.add(dia.getDia_22());
            }
        }
        return valor;
    }

    public BigDecimal totalDia23() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_23() != null) {
                valor = valor.add(dia.getDia_23());
            }
        }
        return valor;
    }

    public BigDecimal totalDia24() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_24() != null) {
                valor = valor.add(dia.getDia_24());
            }
        }
        return valor;
    }

    public BigDecimal totalDia25() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_25() != null) {
                valor = valor.add(dia.getDia_25());
            }
        }
        return valor;
    }

    public BigDecimal totalDia26() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_26() != null) {
                valor = valor.add(dia.getDia_26());
            }
        }
        return valor;
    }

    public BigDecimal totalDia27() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_27() != null) {
                valor = valor.add(dia.getDia_27());
            }
        }
        return valor;
    }

    public BigDecimal totalDia28() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_28() != null) {
                valor = valor.add(dia.getDia_28());
            }
        }
        return valor;
    }

    public BigDecimal totalDia29() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_29() != null) {
                valor = valor.add(dia.getDia_29());
            }
        }
        return valor;
    }

    public BigDecimal totalDia30() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_30() != null) {
                valor = valor.add(dia.getDia_30());
            }
        }
        return valor;
    }

    public BigDecimal totalDia31() {
        BigDecimal valor = BigDecimal.ZERO;
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDia_31() != null) {
                valor = valor.add(dia.getDia_31());
            }
        }
        return valor;
    }

    public BigDecimal totalQtd() {
        BigDecimal valor = BigDecimal.ZERO;
        
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getTotal() != null) {
                valor = valor.add(dia.getTotal());
            }
        }

        return valor;
    }
    
    public BigDecimal totalPrevisaoQtd() {
        BigDecimal valor = BigDecimal.ZERO;
        
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getPrevisaoQtd() != null) {
                valor = valor.add(dia.getPrevisaoQtd());
            }
        }

        return valor;
    }
    
    public BigDecimal totalDiferencaQtd() {
        BigDecimal valor = BigDecimal.ZERO;
        
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDiferencaQtd()!= null) {
                valor = valor.add(dia.getDiferencaQtd());
            }
        }

        return valor;
    }

    public BigDecimal totalQtdAnt() {
        BigDecimal valor = BigDecimal.ZERO;
        
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getTotalAnt() != null) {
                valor = valor.add(dia.getTotalAnt());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia01() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV01() != null) {
                valor = valor.add(dia.getV01());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia02() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV02() != null) {
                valor = valor.add(dia.getV02());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia03() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV03() != null) {
                valor = valor.add(dia.getV03());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia04() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV04() != null) {
                valor = valor.add(dia.getV04());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia05() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV05() != null) {
                valor = valor.add(dia.getV05());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia06() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV06() != null) {
                valor = valor.add(dia.getV06());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia07() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV07() != null) {
                valor = valor.add(dia.getV07());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia08() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV08() != null) {
                valor = valor.add(dia.getV08());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia09() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV09() != null) {
                valor = valor.add(dia.getV09());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia10() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV10() != null) {
                valor = valor.add(dia.getV10());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia11() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV11() != null) {
                valor = valor.add(dia.getV11());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia12() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV12() != null) {
                valor = valor.add(dia.getV12());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia13() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV13() != null) {
                valor = valor.add(dia.getV13());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia14() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV14() != null) {
                valor = valor.add(dia.getV14());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia15() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV15() != null) {
                valor = valor.add(dia.getV15());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia16() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV16() != null) {
                valor = valor.add(dia.getV16());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia17() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV17() != null) {
                valor = valor.add(dia.getV17());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia18() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV18() != null) {
                valor = valor.add(dia.getV18());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia19() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV19() != null) {
                valor = valor.add(dia.getV19());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia20() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV20() != null) {
                valor = valor.add(dia.getV20());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia21() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV21() != null) {
                valor = valor.add(dia.getV21());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia22() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV22() != null) {
                valor = valor.add(dia.getV22());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia23() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV23() != null) {
                valor = valor.add(dia.getV23());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia24() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV24() != null) {
                valor = valor.add(dia.getV24());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia25() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV25() != null) {
                valor = valor.add(dia.getV25());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia26() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV26() != null) {
                valor = valor.add(dia.getV26());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia27() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV27() != null) {
                valor = valor.add(dia.getV27());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia28() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV28() != null) {
                valor = valor.add(dia.getV28());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia29() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV29() != null) {
                valor = valor.add(dia.getV29());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia30() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV30() != null) {
                valor = valor.add(dia.getV30());
            }
        }

        return valor;
    }

    public BigDecimal totalValorDia31() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getV31() != null) {
                valor = valor.add(dia.getV31());
            }
        }

        return valor;
    }

    public BigDecimal totalValorTotal() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getValorTotal() != null) {
                valor = valor.add(dia.getValorTotal());
            }
        }

        return valor;
    }
    
    public BigDecimal totalPrevisaoValor() {
        BigDecimal valor = BigDecimal.ZERO;
        
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getPrevisaoValor()!= null) {
                valor = valor.add(dia.getPrevisaoValor());
            }
        }

        return valor;
    }
    
    public BigDecimal totalDiferencaValor() {
        BigDecimal valor = BigDecimal.ZERO;
        
        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }
        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getDiferencaValor()!= null) {
                valor = valor.add(dia.getDiferencaValor());
            }
        }

        return valor;
    }

    public BigDecimal totalValorTotalAnt() {

        BigDecimal valor = BigDecimal.ZERO;

        List<DiaDiaVO> recebimentosTemp;

        if (recebimentosFilter != null) {
            recebimentosTemp = recebimentosFilter;
        } else {
            recebimentosTemp = recebimentos;
        }

        for (DiaDiaVO dia : recebimentosTemp) {
            if (dia.getValorTotalAnt() != null) {
                valor = valor.add(dia.getValorTotalAnt());
            }
        }

        return valor;
    }

    public void selecionarListaLojas() {
        QueryBuilder queryBuilder = lojaBO.getDAO().getQueryBuilder();
        Restrictions restrictions = new Restrictions();

        if (listaLoja != null) {
            restrictions.equals("l", listaLoja);
            listaLojas_Loja = queryBuilder.from(ListaLoja_Loja.class, "ll").leftJoinFetch("ll.listaLoja", "l").add(restrictions).getResultList();
        }
        for (ListaLoja_Loja listloja : listaLojas_Loja) {
            Loja lojaTemp = new Loja();
            lojaTemp = lojaBO.getDAO().getInitialized(listloja.getLoja());
            lojasSelecionadas.add(lojaTemp);
        }
    }

    private String convertMes(int mes) {
        String mesRetorno;
        switch (mes) {
            case 1:
                mesRetorno = "Janeiro";
                break;
            case 2:
                mesRetorno = "Fevereiro";
                break;
            case 3:
                mesRetorno = "Março";
                break;
            case 4:
                mesRetorno = "Abril";
                break;
            case 5:
                mesRetorno = "Maio";
                break;
            case 6:
                mesRetorno = "Junho";
                break;
            case 7:
                mesRetorno = "Julho";
                break;
            case 8:
                mesRetorno = "Agosto";
                break;
            case 9:
                mesRetorno = "Setembro";
                break;
            case 10:
                mesRetorno = "Outubro";
                break;
            case 11:
                mesRetorno = "Novembro";
                break;
            case 12:
                mesRetorno = "Dezembro";
                break;
            default:
                mesRetorno = "Nao";
        }
        return mesRetorno;
    }

    public int diasUteis(boolean totalDiasMes) {
        int workingDays = 0;

        Calendar c = Calendar.getInstance();
        String[] temp;
        int dia, mes, ano;

        if (periodo == null || periodo.equals("")) {
            c.setTime(new Date());
            mes = c.get(Calendar.MONTH) + 1;
            ano = c.get(Calendar.YEAR);
        } else {
            temp = periodo.split("/");
            mes = Integer.parseInt(temp[0]);
            ano = Integer.parseInt(temp[1]);
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.MONTH, mes - 1);
            c.set(Calendar.YEAR, ano);
        }

        if (totalDiasMes) {
            for (int i = 0; i < c.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                if (!(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) && !(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                    workingDays++;
                }
                c.add(Calendar.DATE, 1);
            }
        } else {
            for (int i = 0; i < c.get(Calendar.DAY_OF_MONTH); i++) {
                if (!(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) && !(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                    workingDays++;
                }
                c.add(Calendar.DATE, 1);
            }
        }

        return (workingDays);
    }

    public BigDecimal previsao(BigDecimal total) {

        BigDecimal diaUteis = BigDecimal.ONE;

        int diasUlteisTotal = diasUteis(true);

        int diasUlteisDecorridos = diasUteis(false);

        if (diasUlteisDecorridos < 2) {
            return BigDecimal.ZERO;
        } else {
            diaUteis = new BigDecimal(diasUlteisDecorridos);
            diaUteis = diaUteis.subtract(BigDecimal.ONE);
        }

        BigDecimal mediaDiaria = total.divide(diaUteis, 2, RoundingMode.HALF_UP);

        return mediaDiaria.multiply(new BigDecimal(diasUlteisTotal));
    }

    public BigDecimal porcentagemDiferenca(BigDecimal previsao, BigDecimal totalAnterior) {

        if (totalAnterior.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return previsao.divide(totalAnterior, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
    }
}
