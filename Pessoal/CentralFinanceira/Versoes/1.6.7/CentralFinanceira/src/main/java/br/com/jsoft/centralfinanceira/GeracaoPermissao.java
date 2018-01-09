package br.com.jsoft.centralfinanceira;

import br.com.jsoft.centralfinanceira.dao.controleacesso.PermissaoDAO;
import br.com.jsoft.centralfinanceira.modelo.controleacesso.Permissao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Classe para geracao das permissoes iniciais do sistema
 *
 * @author Ayslan
 */
@Stateless
public class GeracaoPermissao {

    private static final Logger logger = Logger.getLogger(GeracaoPermissao.class.getName());
    @EJB
    private PermissaoDAO permissaoDAO;

    public void generate() {

        /**
         * Administracao Sistema
         */
        create(new Permissao("administracaoSistema", "Sistema", true), null);

//        create(new Permissao("excluir", "Pai excluir", false), null);
//        create(new Permissao("cadastrar", "Pai cadastrar", false), null);
//        create(new Permissao("auditar", "Pai auditar", false), null);

        /*
         * Controle de Acesso
         */
        create(new Permissao("controleAcesso", "Controle de Acesso", true), "administracaoSistema");

        //Permissao
        create(new Permissao("permissao", "Permissão", true), "controleAcesso");
        create(new Permissao("permissao.create", "Cadastro de Permissão", "/view/controleAcesso/permissao/createPermissao.jsf", true), "permissao");
        create(new Permissao("permissao.list", "Consulta de Permissão", "/view/controleAcesso/permissao/listPermissao.jsf", true), "permissao");
        create(new Permissao("permissao.audit", "Auditoria de Permissão"), "permissao");
        create(new Permissao("permissao.delete", "Exclusão de Permissão"), "permissao");

        //Usuario
        create(new Permissao("usuario", "Usuário", true), "controleAcesso");
        create(new Permissao("usuario.create", "Cadastro de Usuário", "/view/controleAcesso/usuario/createUsuario.jsf", true), "usuario");
        create(new Permissao("usuario.list", "Consulta de Usuário", "/view/controleAcesso/usuario/listUsuario.jsf", true), "usuario");
        create(new Permissao("usuario.audit", "Auditoria de Usuário"), "usuario");
        create(new Permissao("usuario.delete", "Exclusão de Usuário"), "usuario");

        //Perfil
        create(new Permissao("perfil", "Perfil", true), "controleAcesso");
        create(new Permissao("perfil.create", "Cadastro de Perfil", "/view/controleAcesso/perfil/createPerfil.jsf", true), "perfil");
        create(new Permissao("perfil.list", "Consulta de Perfil", "/view/controleAcesso/perfil/listPerfil.jsf", true), "perfil");
        create(new Permissao("perfil.audit", "Auditoria de Perfil"), "perfil");
        create(new Permissao("perfil.delete", "Exclusão de Perfil"), "perfil");

        //Acessos do Sistema
        create(new Permissao("acessoSistema.list", "Relatório de Acessos", "/view/controleAcesso/acessoSistema/listAcessoSistema.jsf", true), "controleAcesso");

        //Solicitacao recuperacao senha
        create(new Permissao("solicitacaoRecuperacaoSenha", "Recuperação de Senha", true), "controleAcesso");
        create(new Permissao("solicitacaoRecuperacaoSenha.list", "Consulta Recuperação de Senha", "/view/controleAcesso/solicitacaoRecuperacaoSenha/listSolicitacaoRecuperacaoSenha.jsf", true), "solicitacaoRecuperacaoSenha");
        create(new Permissao("solicitacaoRecuperacaoSenha.audit", "Auditoria de Recuperação de Senha"), "solicitacaoRecuperacaoSenha");

        /*
         * Email
         */
        create(new Permissao("email", "Email", true), "administracaoSistema");

        //Modelo email
        create(new Permissao("modeloEmail", "Modelo de Email", true), "email");
        create(new Permissao("modeloEmail.create", "Cadastro de Modelo de Email", "/view/email/modeloEmail/createModeloEmail.jsf", true), "modeloEmail");
        create(new Permissao("modeloEmail.list", "Consulta de Modelo de Email", "/view/email/modeloEmail/listModeloEmail.jsf", true), "modeloEmail");
        create(new Permissao("modeloEmail.audit", "Auditoria de Modelo de Email"), "modeloEmail");
        create(new Permissao("modeloEmail.delete", "Exclusão de Modelo de Email"), "modeloEmail");

        //Configuracao email
        create(new Permissao("configuracaoEmail", "Configuração de Email", true), "email");
        create(new Permissao("configuracaoEmail.create", "Cadastro de Configuração de Email", "/view/email/configuracaoEmail/createConfiguracaoEmail.jsf", true), "configuracaoEmail");
        create(new Permissao("configuracaoEmail.list", "Consulta de Configuração de Email", "/view/email/configuracaoEmail/listConfiguracaoEmail.jsf", true), "configuracaoEmail");
        create(new Permissao("configuracaoEmail.audit", "Auditoria de Configuração de Email"), "configuracaoEmail");
        create(new Permissao("configuracaoEmail.delete", "Exclusão de Configuração de Email"), "configuracaoEmail");

        /*
         * Configuracao
         */
        create(new Permissao("configuracaoSistema", "Configuração", true), "administracaoSistema");

        //Erro sistema
        create(new Permissao("erroSistema.list", "Relatório de Erros", "/view/configuracao/erroSistema/listErroSistema.jsf", true), "configuracaoSistema");

        /**
         * Permissoes Globais
         */
        //Alterar Senha
        createGlobal(new Permissao("usuario.alterarSenha", "Alterar Senha", "/view/controleAcesso/password/alterPassword.jsf", true), "controleAcesso");
        createGlobal(new Permissao("usuario.ultimosAcessos", "Últimos Acessos", "/view/controleAcesso/acessoSistema/ultimosAcessoSistema.jsf", true), "controleAcesso");

        // Modificado por Juniel em 14/Dez/2015
        // Cadastros Básicos
//        create(new Permissao("cadastrosbasicos", "Cadastros Básicos", true), null);
        // Menu Fatos - Creditos e Debitos
//        create(new Permissao("fatos", "Convênios", true), null);
//        create(new Permissao("fatosLojas", "Lojas", true), null);
//        create(new Permissao("convenios", "Convênios", true), "cadastrosbasicos");
//        create(new Permissao("cep", "Cep", true), "cadastrosbasicos");
//        create(new Permissao("tipos", "Tipos", true), "fatosLojas");
//        create(new Permissao("tiposCadastro", "Tipos", true), "cadastrosbasicos");
//        create(new Permissao("grupos", "Grupos", true), "cadastrosbasicos");
//        create(new Permissao("complementar", "Cadastro Complementar", true), "cadastrosbasicos");
        //Tipo Float
        //create(new Permissao("tipoFloat", "Tipo Float", true), "tipos");
        //create(new Permissao("tipoFloat.create", "Cadastro de Tipo de Float", "/view/central/cadastrosbasicos/tipoFloat/createTipoFloat.jsf", true), "tipoFloat");
        //create(new Permissao("tipoFloat.list", "Consulta de Tipo de Float", "/view/central/cadastrosbasicos/tipoFloat/listTipoFloat.jsf", true), "tipoFloat");
        //create(new Permissao("tipoFloat.audit", "Auditoria de Tipo de Float"), "tipoFloat");
        //create(new Permissao("tipoFloat.delete", "Exclusão de Tipo de Float"), "tipoFloat");
        //Loja
//        create(new Permissao("loja", "Loja - Pai", false), "fatosLojas");
//        create(new Permissao("loja.create", "Cadastro de Loja", "/view/central/cadastrosbasicos/loja/createLoja.jsf", true), "loja");
//        create(new Permissao("loja.list", "Relação de Loja", "/view/central/cadastrosbasicos/loja/listLoja.jsf", true), "fatosLojas");
//        create(new Permissao("loja.audit", "Auditoria de Loja"), "loja");
//        create(new Permissao("loja.delete", "Exclusão de Loja"), "loja");
        //Tipo Loja
//        create(new Permissao("tipoLoja", "Tipo Lojas - Pai", false), "tipos");
//        create(new Permissao("tipoLoja.create", "Cadastro de Tipo Loja", "/view/central/cadastrosbasicos/tipoLoja/createTipoLoja.jsf", true), "tipoLoja");
//        create(new Permissao("tipoLoja.list", "Relação dos Tipos de Loja", "/view/central/cadastrosbasicos/tipoLoja/listTipoLoja.jsf", true), "tipos");
//        create(new Permissao("tipoLoja.audit", "Auditoria de Tipo Loja"), "tipoLoja");
//        create(new Permissao("tipoLoja.delete", "Exclusão de Tipo Loja"), "tipoLoja");
        //Estado
//        create(new Permissao("uf", "Estado - Pai", false), "cep");
        //create(new Permissao("cidade.create", "Cadastro de Cidade", "/view/central/cadastrosbasicos/cidade/createCidade.jsf", true), "cidade");
//        create(new Permissao("uf.list", "Estado", "/view/central/cadastrosbasicos/uf/listUf.jsf", true), "cep");
        //create(new Permissao("cidade.audit", "Auditoria de Cidade"), "cidade");
        //create(new Permissao("cidade.delete", "Exclusão de Cidade"), "cidade");
        //Cidade
//        create(new Permissao("cidade", "Cidade - Pai ", false), "cep");
        //create(new Permissao("cidade.create", "Cadastro de Cidade", "/view/central/cadastrosbasicos/cidade/createCidade.jsf", true), "cidade");
//        create(new Permissao("cidade.list", "Cidade", "/view/central/cadastrosbasicos/cidade/listCidade.jsf", true), "cep");
        //create(new Permissao("cidade.audit", "Auditoria de Cidade"), "cidade");
        //create(new Permissao("cidade.delete", "Exclusão de Cidade"), "cidade");
        //Fatos Boletos
//        create(new Permissao("fatosBoletos", "Boletos", true), "fatos");
//        create(new Permissao("fatosBoletos.create", "Cadastro de Boletos", "/view/central/fatos/fatosBoletos/createFatosBoletos.jsf", true), "fatosBoletos");
//        create(new Permissao("fatosBoletos.list", "Recebimento Mensal", "/view/central/fatos/fatosBoletos/listFatosBoletos.jsf", true), "fatosBoletos");
//        create(new Permissao("fatosBoletos.relatorio", "Relatório de remuneração", "/view/central/fatos/fatosBoletos/consultaFatoBoletos.jsf", true), "fatosBoletos");
//        create(new Permissao("fatosBoletos.audit", "Auditoria de Boletos"), "fatosBoletos");
//        create(new Permissao("fatosBoletos.delete", "Exclusão de Boletos"), "fatosBoletos");
//        create(new Permissao("fatosBoletos.listConvenio", "Comissão Mensal dos Convênios", "/view/central/fatos/fatosBoletos/listFatosBoletosConvenio.jsf", true), "fatosBoletos");
//        create(new Permissao("fatosBoletos.listLoja", "Comissão Mensal das Lojas", "/view/central/fatos/fatosBoletos/listFatosBoletosLoja.jsf", true), "fatosBoletos");
        //Convenio Boleto
//        create(new Permissao("convenioBoleto", "Boleto", true), "convenios");
        //create(new Permissao("convenioBoleto.create", "Cadastro de Convenio Boleto", "/view/central/cadastrosbasicos/convenioBoleto/createConvenioBoleto.jsf", true), "convenioBoleto");
//        create(new Permissao("convenioBoleto.list", "Relação de Convênios", "/view/central/cadastrosbasicos/convenioBoleto/listConvenioBoleto.jsf", true), "fatosBoletos");
        //create(new Permissao("convenioBoleto.audit", "Auditoria de Convenio Boleto"), "convenioBoleto");
        //create(new Permissao("convenioBoleto.delete", "Exclusão de Convenio Boleto"), "convenioBoleto");
        //Fatos Boletos Site
//        create(new Permissao("fatosBoletosSite", "Boletos Site", true), "fatos");
//        create(new Permissao("fatosBoletoSite.create", "Cadastro de Boletos Site", "/view/central/fatos/fatosBoletoSite/fatosBoletoSite/createFatosBoletoSite.jsf", true), "fatosBoletosSite");
        //       create(new Permissao("fatosBoletoSite.list", "Recebimento Mensal", "/view/central/fatos/fatosBoletoSite/fatosBoletoSite/listFatosBoletoSite.jsf", true), "fatosBoletosSite");
//        create(new Permissao("fatosBoletoSite.audit", "Auditoria de Boletos Site"), "fatosBoletosSite");
//        create(new Permissao("fatosBoletoSite.delete", "Exclusão de Boletos Site"), "fatosBoletosSite");
        //     create(new Permissao("fatosBoletoSite.listConvenio", "Comissão Mensal Convênios", "/view/central/fatos/fatosBoletoSite/fatosBoletoSite/listFatosBoletosConvenio.jsf", true), "fatosBoletosSite");
        //      create(new Permissao("fatosBoletoSite.listLoja", "Comissão Mensal Loja", "/view/central/fatos/fatosBoletoSite/fatosBoletoSite/listFatosBoletosLoja.jsf", true), "fatosBoletosSite");
        //Convenio Site
//        create(new Permissao("convenioSite", "Site", true), "convenios");
        //create(new Permissao("convenioSite.create", "Cadastro de Convenio Site", "/view/central/cadastrosbasicos/convenioSite/convenioSite/createConvenioSite.jsf", true), "convenioSite");
        //     create(new Permissao("convenioSite.list", "Relação de Convênios", "/view/central/cadastrosbasicos/convenioSite/convenioSite/listConvenioSite.jsf", true), "fatosBoletosSite");
        //create(new Permissao("convenioSite.audit", "Auditoria de Convenio Site"), "convenioSite");
        //create(new Permissao("convenioSite.delete", "Exclusão de Convenio Site"), "convenioSite");
        //Fatosops
        //   create(new Permissao("fatosops", "Ordem de Pagamento", true), "fatos");
//        create(new Permissao("fatosops.create", "Cadastro de Ordem de Pagamento", "/view/central/fatos/fatosops/createFatosops.jsf", true), "fatosops");
        //   create(new Permissao("fatosops.list", "Recebimento Mensal", "/view/central/fatos/fatosops/listFatosops.jsf", true), "fatosops");
//        create(new Permissao("fatosops.audit", "Auditoria de OP"), "fatosops");
//        create(new Permissao("fatosops.delete", "Exclusão de Ordem de Pagamento"), "fatosops");
        //   create(new Permissao("fatosops.listConvenio", "Comissão Mensal dos Convênios", "/view/central/fatos/fatosops/listFatosBoletosConvenio.jsf", true), "fatosops");
        //   create(new Permissao("fatosops.listLoja", "Comissão Mensal das Lojas", "/view/central/fatos/fatosops/listFatosBoletosLoja.jsf", true), "fatosops");
        //Conveniop
//        create(new Permissao("conveniop", "Ordem de Pagamento", true), "convenios");
        //create(new Permissao("conveniop.create", "Cadastro de Convenio Ordem de Pagamento", "/view/central/fatos/conveniop/createConveniop.jsf", true), "conveniop");
        //  create(new Permissao("conveniop.list", "Relação de Convênios", "/view/central/fatos/conveniop/listConveniop.jsf", true), "fatosops");
        //create(new Permissao("conveniop.audit", "Auditoria de Convenio Ordem de Pagamento"), "conveniop");
        //create(new Permissao("conveniop.delete", "Exclusão de Convenio Ordem de Pagamento"), "conveniop");
        //Fatos Creditos
        //  create(new Permissao("fatosCreditos", "Créditos Digitais", true), "fatos");
//        create(new Permissao("fatosCreditos.create", "Cadastro de Créditos Digitais", "/view/central/fatos/fatosCreditos/createFatosCreditos.jsf", true), "fatosCreditos");
        //  create(new Permissao("fatosCreditos.list", "Recebimento Mensal", "/view/central/fatos/fatosCreditos/listFatosCreditos.jsf", true), "fatosCreditos");
//        create(new Permissao("fatosCreditos.audit", "Auditoria de Créd. Digitais"), "fatosCreditos");
//        create(new Permissao("fatosCreditos.delete", "Exclusão de Crédito Digitais"), "fatosCreditos");
        //  create(new Permissao("fatosCreditos.listConvenio", "Comissão Mensal dos Convênios", "/view/central/fatos/fatosCreditos/listFatosBoletosConvenio.jsf", true), "fatosCreditos");
        //  create(new Permissao("fatosCreditos.listLoja", "Comissão Mensal das Lojas", "/view/central/fatos/fatosCreditos/listFatosBoletosLoja.jsf", true), "fatosCreditos");
        //Conveniocredigi
//        create(new Permissao("conveniocredigi", "Créditos Digitais", true), "convenios");
//        create(new Permissao("conveniocredigi.create", "Cadastro de Convênio Créditos Digitais", "/view/central/fatos/conveniocredigi/createConveniocredigi.jsf", true), "conveniocredigi");
        //      create(new Permissao("conveniocredigi.list", "Relação de Convênios", "/view/central/fatos/conveniocredigi/listConveniocredigi.jsf", true), "fatosCreditos");
//        create(new Permissao("conveniocredigi.audit", "Auditoria de Convênio Créditos Digitais"), "conveniocredigi");
//        create(new Permissao("conveniocredigi.delete", "Exclusão de Convênio Créditos Digitais"), "conveniocredigi");
        //Fatos Vale Gas
        // create(new Permissao("fatosValeGas", "Vale Gás", true), "fatos");
//        create(new Permissao("fatosValeGas.create", "Cadastro de Vale Gás", "/view/central/fatos/fatosValeGas/createFatosValeGas.jsf", true), "fatosValeGas");
        // create(new Permissao("fatosValeGas.list", "Recebimento Mensal", "/view/central/fatos/fatosValeGas/listFatosValeGas.jsf", true), "fatosValeGas");
//        create(new Permissao("fatosValeGas.audit", "Auditoria de Vale Gás"), "fatosValeGas");
//        create(new Permissao("fatosValeGas.delete", "Exclusão de Vale Gás"), "fatosValeGas");
        //  create(new Permissao("fatosValeGas.listConvenio", "Comissão Mensal dos Convênios", "/view/central/fatos/fatosValeGas/listFatosBoletosConvenio.jsf", true), "fatosValeGas");
//        create(new Permissao("fatosValeGas.listLoja", "Comissão Mensal das Lojas", "/view/central/fatos/fatosValeGas/listFatosBoletosLoja.jsf", true), "fatosValeGas");
        //Convenio Gas
//        create(new Permissao("convenioGas", "Vale Gás", true), "convenios");
        //create(new Permissao("convenioGas.create", "Cadastro de Convenio Gas", "/view/central/fatos/convenioGas/createConvenioGas.jsf", true), "convenioGas");
        //     create(new Permissao("convenioGas.list", "Relação de Convênios", "/view/central/fatos/convenioGas/listConvenioGas.jsf", true), "fatosValeGas");
        //create(new Permissao("convenioGas.audit", "Auditoria de Convenio Gas"), "convenioGas");
        //create(new Permissao("convenioGas.delete", "Exclusão de Convenio Gas"), "convenioGas");
        //Fatos Recarga
        //  create(new Permissao("fatosRecarga", "Recarga", true), "fatos");
//        create(new Permissao("fatosRecarga.create", "Cadastro de Recarga", "/view/central/fatos/fatosRecarga/createFatosRecarga.jsf", true), "fatosRecarga");
        //    create(new Permissao("fatosRecarga.list", "Recebimento Mensal", "/view/central/fatos/fatosRecarga/listFatosRecarga.jsf", true), "fatosRecarga");
//        create(new Permissao("fatosRecarga.audit", "Auditoria de Recarga"), "fatosRecarga");
//        create(new Permissao("fatosRecarga.delete", "Exclusão de Recarga"), "fatosRecarga");
        //    create(new Permissao("fatosRecarga.listConvenio", "Comissão Mensal dos Convênios", "/view/central/fatos/fatosRecarga/listFatosBoletosConvenio.jsf", true), "fatosRecarga");
        //    create(new Permissao("fatosRecarga.listLoja", "Comissão Mensal das Lojas", "/view/central/fatos/fatosRecarga/listFatosBoletosLoja.jsf", true), "fatosRecarga");
        //Convenio Recarga
//        create(new Permissao("convenioRecarga", "Recarga", true), "convenios");
//        create(new Permissao("convenioRecarga.create", "Cadastro de Convenio Recarga", "/view/central/fatos/convenioRecarga/createConvenioRecarga.jsf", true), "convenioRecarga");
        //      create(new Permissao("convenioRecarga.list", "Relação de Convênios", "/view/central/fatos/convenioRecarga/listConvenioRecarga.jsf", true), "fatosRecarga");
//        create(new Permissao("convenioRecarga.audit", "Auditoria de Convenio Recarga"), "convenioRecarga");
//        create(new Permissao("convenioRecarga.delete", "Exclusão de Convenio Recarga"), "convenioRecarga");
//Fatos Ban Pop
//        create(new Permissao("fatosBanPop", "Banco Popular", true), "fatos");
//        create(new Permissao("fatosBanPop.create", "Cadastro de Banco Popular", "/view/central/fatos/fatosBanPop/createFatosBanPop.jsf", true), "fatosBanPop");
        //       create(new Permissao("fatosBanPop.list", "Recebimento Mensal", "/view/central/fatos/fatosBanPop/listFatosBanPop.jsf", true), "fatosBanPop");
//        create(new Permissao("fatosBanPop.audit", "Auditoria de Banco Popular"), "fatosBanPop");
//        create(new Permissao("fatosBanPop.delete", "Exclusão de Banco Popular"), "fatosBanPop");
        //      create(new Permissao("fatosBanPop.listConvenios", "Comissão Mensal dos Convênios", "/view/central/fatos/fatosBanPop/listFatosBoletosConvenio.jsf", true), "fatosBanPop");
        //      create(new Permissao("fatosBanPop.listLojas", "Comissão Mensal das Lojas", "/view/central/fatos/fatosBanPop/listFatosBoletosLoja.jsf", true), "fatosBanPop");
        //Convenio Ban Pop
//        create(new Permissao("convenioBanPop", "Banco Popular", true), "convenios");
//        create(new Permissao("convenioBanPop.create", "Cadastro de Convênio Banco Popular", "/view/central/fatos/convenioBanPop/createConvenioBanPop.jsf", true), "convenioBanPop");
        //       create(new Permissao("convenioBanPop.list", "Relação de Convênios", "/view/central/fatos/convenioBanPop/listConvenioBanPop.jsf", true), "fatosBanPop");
//        create(new Permissao("convenioBanPop.audit", "Auditoria de Convênio Banco Popular"), "convenioBanPop");
//        create(new Permissao("convenioBanPop.delete", "Exclusão de Convênio Banco Popular"), "convenioBanPop");
        //Fatosbb
        //      create(new Permissao("fatosbb", "Banco do Brasil", true), "fatos");
//        create(new Permissao("fatosbb.create", "Cadastro de Fatosbb", "/view/central/fatos/fatosbb/createFatosbb.jsf", true), "fatosbb");
        //      create(new Permissao("fatosbb.list", "Recebimento Mensal", "/view/central/fatos/fatosbb/listFatosbb.jsf", true), "fatosbb");
//        create(new Permissao("fatosbb.audit", "Auditoria de Boletos BB"), "fatosbb");
//        create(new Permissao("fatosbb.delete", "Exclusão de Fatosbb"), "fatosbb");
        //       create(new Permissao("fatosbb.listConvenios", "Comissão Mensal dos Convênios", "/view/central/fatos/fatosbb/listFatosBoletosConvenio.jsf", true), "fatosbb");
        //       create(new Permissao("fatosbb.listLojas", "Comissão Mensal das Lojas", "/view/central/fatos/fatosbb/listFatosBoletosLoja.jsf", true), "fatosbb");
        //Conveniobb
//        create(new Permissao("conveniobb", "Banco do Brasil", true), "convenios");
//        create(new Permissao("conveniobb.create", "Cadastro de Conveniobb", "/view/central/fatos/conveniobb/createConveniobb.jsf", true), "conveniobb");
        //      create(new Permissao("conveniobb.list", "Relação de Convênios", "/view/central/fatos/conveniobb/listConveniobb.jsf", true), "fatosbb");
//        create(new Permissao("conveniobb.audit", "Auditoria de Conveniobb"), "conveniobb");
//       create(new Permissao("conveniobb.delete", "Exclusão de Conveniobb"), "conveniobb");
        //Retabilidades
//        create(new Permissao("retabilidades", "Rentabilidades", true), "fatos");
        //       create(new Permissao("retabilidades.listRentabilidadesLoja", "Rentabilidade", "/view/central/fatos/fatosBoletos/listRentabilidadeLoja.jsf", true), "fatosLojas");
        //      create(new Permissao("retabilidades.listRentabilidadesConvenio", "Rentabilidade", "/view/central/fatos/fatosBoletos/listRentabilidadeConvenio.jsf", true), "fatos");
//Contrato
//        create(new Permissao("contrato", "Contrato", true), null);
//        create(new Permissao("contrato.create", "Cadastro de Contrato", "/view/contrato/contrato/createContrato.jsf", true), "contrato");
//        create(new Permissao("contrato.list", "Consulta de Contrato", "/view/contrato/contrato/listContrato.jsf", true), "contrato");
//        create(new Permissao("contrato.audit", "Auditoria de Contrato"), "contrato");
//        create(new Permissao("contrato.delete", "Exclusão de Contrato"), "contrato");
//Pag Contas
//        create(new Permissao("pagContas", "Dados PagContas - Pai", false), "complementar");
//        create(new Permissao("pagContas.create", "Cadastro de Dados PagContas", "/view/contrato/pagContas/createPagContas.jsf", false), "pagContas");
//        create(new Permissao("pagContas.list", "Dados PagContas", "/view/contrato/pagContas/listPagContas.jsf", true), "complementar");
//        create(new Permissao("pagContas.audit", "Auditoria de Dados PagContas"), "pagContas");
//        create(new Permissao("pagContas.delete", "Exclusão de Dados PagContas"), "pagContas");
        //Despesa Loja
//        create(new Permissao("despesaLoja", "Despesa Loja - Pai", false), "fatosLojas");
//        create(new Permissao("despesaLoja.create", "Cadastro de Despesa Loja", "/view/central/fatos/despesaLoja/createDespesaLoja.jsf", false), "despesaLoja");
//        create(new Permissao("despesaLoja.list", "Despesa Loja", "/view/central/fatos/despesaLoja/listDespesaLoja.jsf", true), "fatosLojas");
//        create(new Permissao("despesaLoja.audit", "Auditoria de Despesa Loja"), "despesaLoja");
//        create(new Permissao("despesaLoja.delete", "Exclusão de Despesa Loja"), "despesaLoja");
//Receita Loja
//        create(new Permissao("receitaLoja", "Receita Loja - Pai", false), "fatosLojas");
//        create(new Permissao("receitaLoja.create", "Cadastro de Receita Loja", "/view/central/fatos/receitaLoja/createReceitaLoja.jsf", false), "receitaLoja");
//        create(new Permissao("receitaLoja.list", "Tipo Receita", "/view/central/fatos/receitaLoja/listReceitaLoja.jsf", true), "fatosLojas");
//        create(new Permissao("receitaLoja.audit", "Auditoria de Receita Loja"), "receitaLoja");
//        create(new Permissao("receitaLoja.delete", "Exclusão de Receita Loja"), "receitaLoja");
//Tipo Despesa
//        create(new Permissao("tipoDespesa", "Tipo Despesa - Pai", false), "tipos");
//        create(new Permissao("tipoDespesa.create", "Cadastro de Tipo Despesa", "/view/central/fatos/tipoDespesa/createTipoDespesa.jsf", false), "tipoDespesa");
//        create(new Permissao("tipoDespesa.list", "Tipo Despesa", "/view/central/fatos/tipoDespesa/listTipoDespesa.jsf", true), "tipos");
//        create(new Permissao("tipoDespesa.audit", "Auditoria de Tipo Despesa"), "tipoDespesa");
//        create(new Permissao("tipoDespesa.delete", "Exclusão de Tipo Despesa"), "tipoDespesa");
//Tipo Receita
//        create(new Permissao("tipoReceita", "Tipo Receita - Pai", false), "tipos");
//        create(new Permissao("tipoReceita.create", "Cadastro de Tipo Receita", "/view/central/fatos/tipoReceita/createTipoReceita.jsf", false), "tipoReceita");
//        create(new Permissao("tipoReceita.list", "Receita Loja", "/view/central/fatos/tipoReceita/listTipoReceita.jsf", true), "tipos");
//        create(new Permissao("tipoReceita.audit", "Auditoria de Tipo Receita"), "tipoReceita");
//        create(new Permissao("tipoReceita.delete", "Exclusão de Tipo Receita"), "tipoReceita");
        //Menu Graficos
//        create(new Permissao("graficos", "Gráficos - Pai", false), null);
        //Rentabilidade
//        create(new Permissao("graficos.rentabilidade", "Rentabilidade - Pai", false), "graficos");
//        create(new Permissao("graficos.arrecadacao", "Gráfico de Rentabilidade", "/view/central/fatos/graficos/graficoRentabilidadeLoja.jsf", true), "fatosLojas");
//        create(new Permissao("graficos.arrecadacaoConvenio", "Gráfico de Rentabilidade", "/view/central/fatos/graficos/graficoRentabilidadeConvenio.jsf", true), "fatos");
        //Menu Graficos
//        create(new Permissao("maps", "Localização - Pai", false), "fatosLojas");
//        create(new Permissao("maps.geral", "Localização", "/view/maps/addMarkers.jsf", true), "fatosLojas");
        //Arquivos
//        create(new Permissao("menuArquivo", "Arquivos", true), null);
//
//        create(new Permissao("comercial", "Comercial", true), "menuArquivo");
//        create(new Permissao("comercial.contratos", "Contratos/Doc Postos", "/view/arquivos/listContratosPos.jsf", true), "comercial");
//
//        create(new Permissao("financeiro", "Financeiro", true), "menuArquivo");
//
//        create(new Permissao("financeiro.comprovantes", "Repasse a Convenio", "/view/arquivos/listComprovantesRepasse.jsf", true), "financeiro");
//        create(new Permissao("financeiro.despesas", "Despesas", "/view/arquivos/listComprovantesDespesas.jsf", true), "financeiro");
//-------------------------------------- Inicio MENU AG -----------------------------------------------
//        create(new Permissao("bancarios", "Dados Bancários", true), "cadastrosbasicos");
//
//        create(new Permissao("clienteFornecedor", "Cliente e Fornecedor", true), "cadastrosbasicos");
//
//        create(new Permissao("empresaEstabelecimento", "Empresa e Estabelecimento", true), "cadastrosbasicos");
//Agente Cobrador
//        create(new Permissao("agenteCobrador", "Agente Cobradores/Pagadores - Pai", false), "bancarios");
//        create(new Permissao("agenteCobrador.create", "Cadastro", "/view/central/cadastrosbasicos/agenteCobrador/createAgenteCobrador.jsf", false), "agenteCobrador");
//        create(new Permissao("agenteCobrador.list", "Agente Cobradores/Pagadores", "/view/central/cadastrosbasicos/agenteCobrador/listAgenteCobrador.jsf", true), "bancarios");
//        create(new Permissao("agenteCobrador.audit", "Auditoria"), "agenteCobrador");
//        create(new Permissao("agenteCobrador.delete", "Exclusão"), "agenteCobrador");
//Banco
//        create(new Permissao("banco", "Banco - Pai", false), "bancarios");
//        create(new Permissao("banco.create", "Cadastro de Banco", "/view/central/cadastrosbasicos/banco/createBanco.jsf", false), "banco");
//        create(new Permissao("banco.list", "Bancos", "/view/central/cadastrosbasicos/banco/listBanco.jsf", true), "bancarios");
//        create(new Permissao("banco.audit", "Auditoria de Banco"), "banco");
//        create(new Permissao("banco.delete", "Exclusão de Banco"), "banco");
//Cliente
//        create(new Permissao("cliente", "Cliente - Pai", false), "clienteFornecedor");
//        create(new Permissao("cliente.create", "Cadastro de Cliente", "/view/central/cadastrosbasicos/cliente/createCliente.jsf", false), "cliente");
//        create(new Permissao("cliente.list", "Clientes", "/view/central/cadastrosbasicos/cliente/listCliente.jsf", true), "clienteFornecedor");
//        create(new Permissao("cliente.audit", "Auditoria de Cliente"), "cliente");
//        create(new Permissao("cliente.delete", "Exclusão de Cliente"), "cliente");
//Conta Financeira
//        create(new Permissao("contaFinanceira", "Conta Financeira - Pai", false), "bancarios");
//        create(new Permissao("contaFinanceira.create", "Cadastro de Conta Financeira", "/view/central/cadastrosbasicos/contaFinanceira/createContaFinanceira.jsf", false), "contaFinanceira");
//        create(new Permissao("contaFinanceira.list", "Contas Financeira", "/view/central/cadastrosbasicos/contaFinanceira/listContaFinanceira.jsf", true), "bancarios");
//        create(new Permissao("contaFinanceira.audit", "Auditoria de Conta Financeira"), "contaFinanceira");
//        create(new Permissao("contaFinanceira.delete", "Exclusão de Conta Financeira"), "contaFinanceira");
//Empresa
//        create(new Permissao("empresa", "Empresa - Pai", false), "empresaEstabelecimento");
//        create(new Permissao("empresa.create", "Cadastro de Empresa", "/view/central/cadastrosbasicos/empresa/createEmpresa.jsf", false), "empresa");
//        create(new Permissao("empresa.list", "Empresas", "/view/central/cadastrosbasicos/empresa/listEmpresa.jsf", true), "empresaEstabelecimento");
//        create(new Permissao("empresa.audit", "Auditoria de Empresa"), "empresa");
//        create(new Permissao("empresa.delete", "Exclusão de Empresa"), "empresa");
//Estabelecimento
//        create(new Permissao("estabelecimento", "Estabelecimento - Pai", false), "empresaEstabelecimento");
//        create(new Permissao("estabelecimento.create", "Cadastro de Estabelecimento", "/view/central/cadastrosbasicos/estabelecimento/createEstabelecimento.jsf", false), "estabelecimento");
//        create(new Permissao("estabelecimento.list", "Estabelecimentos", "/view/central/cadastrosbasicos/estabelecimento/listEstabelecimento.jsf", true), "empresaEstabelecimento");
//        create(new Permissao("estabelecimento.audit", "Auditoria de Estabelecimento"), "estabelecimento");
//        create(new Permissao("estabelecimento.delete", "Exclusão de Estabelecimento"), "estabelecimento");
//Fornecedor
//        create(new Permissao("fornecedor", "Fornecedor - Pai", false), "clienteFornecedor");
//        create(new Permissao("fornecedor.create", "Cadastro de Fornecedor", "/view/central/cadastrosbasicos/fornecedor/createFornecedor.jsf", false), "fornecedor");
//        create(new Permissao("fornecedor.list", "Fornecedores", "/view/central/cadastrosbasicos/fornecedor/listFornecedor.jsf", true), "clienteFornecedor");
//        create(new Permissao("fornecedor.audit", "Auditoria de Fornecedor"), "fornecedor");
//        create(new Permissao("fornecedor.delete", "Exclusão de Fornecedor"), "fornecedor");
//Grupo Cliente
//        create(new Permissao("grupoCliente", "Grupo Cliente - Pai", false), "grupos");
//        create(new Permissao("grupoCliente.create", "Cadastro de Grupo Cliente", "/view/central/cadastrosbasicos/grupoCliente/createGrupoCliente.jsf", false), "grupoCliente");
//        create(new Permissao("grupoCliente.list", "Grupos de Clientes", "/view/central/cadastrosbasicos/grupoCliente/listGrupoCliente.jsf", true), "grupos");
//        create(new Permissao("grupoCliente.audit", "Auditoria de Grupo Cliente"), "grupoCliente");
//        create(new Permissao("grupoCliente.delete", "Exclusão de Grupo Cliente"), "grupoCliente");
//Grupo Conta Financeira
//        create(new Permissao("grupoContaFinanceira", "Grupo Conta Financeira - Pai", false), "grupos");
//        create(new Permissao("grupoContaFinanceira.create", "Cadastro de Grupo Conta Financeira", "/view/central/cadastrosbasicos/grupoContaFinanceira/createGrupoContaFinanceira.jsf", false), "grupoContaFinanceira");
//        create(new Permissao("grupoContaFinanceira.list", "Grupos Contas Financeiras", "/view/central/cadastrosbasicos/grupoContaFinanceira/listGrupoContaFinanceira.jsf", true), "grupos");
//        create(new Permissao("grupoContaFinanceira.audit", "Auditoria de Grupo Conta Financeira"), "grupoContaFinanceira");
//        create(new Permissao("grupoContaFinanceira.delete", "Exclusão de Grupo Conta Financeira"), "grupoContaFinanceira");
//Grupo Fornecedor
//        create(new Permissao("grupoFornecedor", "Grupo Fornecedor - Pai", false), "grupos");
//        create(new Permissao("grupoFornecedor.create", "Cadastro de Grupo Fornecedor", "/view/central/cadastrosbasicos/grupoFornecedor/createGrupoFornecedor.jsf", false), "grupoFornecedor");
//        create(new Permissao("grupoFornecedor.list", "Grupos de Fornecedores", "/view/central/cadastrosbasicos/grupoFornecedor/listGrupoFornecedor.jsf", true), "grupos");
//        create(new Permissao("grupoFornecedor.audit", "Auditoria de Grupo Fornecedor"), "grupoFornecedor");
//        create(new Permissao("grupoFornecedor.delete", "Exclusão de Grupo Fornecedor"), "grupoFornecedor");
//Grupo Receita Despesa
//        create(new Permissao("grupoReceitaDespesa", "Grupo Receita Despesa - Pai", false), "grupos");
//        create(new Permissao("grupoReceitaDespesa.create", "Cadastro de Grupo Receita Despesa", "/view/central/cadastrosbasicos/grupoReceitaDespesa/createGrupoReceitaDespesa.jsf", false), "grupoReceitaDespesa");
//        create(new Permissao("grupoReceitaDespesa.list", "Consulta de Grupo Receita Despesa", "/view/central/cadastrosbasicos/grupoReceitaDespesa/listGrupoReceitaDespesa.jsf", true), "grupos");
//        create(new Permissao("grupoReceitaDespesa.audit", "Auditoria de Grupo Receita Despesa"), "grupoReceitaDespesa");
//        create(new Permissao("grupoReceitaDespesa.delete", "Exclusão de Grupo Receita Despesa"), "grupoReceitaDespesa");
//Historico Padrao
//        create(new Permissao("historicoPadrao", "Historico Padrao - Pai", false), "complementar");
//        create(new Permissao("historicoPadrao.create", "Cadastro de Historico Padrao", "/view/central/cadastrosbasicos/historicoPadrao/createHistoricoPadrao.jsf", false), "historicoPadrao");
//        create(new Permissao("historicoPadrao.list", "Historico Padrão", "/view/central/cadastrosbasicos/historicoPadrao/listHistoricoPadrao.jsf", true), "complementar");
//        create(new Permissao("historicoPadrao.audit", "Auditoria de Historico Padrao"), "historicoPadrao");
//        create(new Permissao("historicoPadrao.delete", "Exclusão de Historico Padrao"), "historicoPadrao");
//Instrucao Cobranca
//        create(new Permissao("instrucaoCobranca", "Instrução Cobranca - Pai", false), "bancarios");
//        create(new Permissao("instrucaoCobranca.create", "Cadastro de Instrução Cobranca", "/view/central/cadastrosbasicos/instrucaoCobranca/createInstrucaoCobranca.jsf", false), "instrucaoCobranca");
//        create(new Permissao("instrucaoCobranca.list", "Consulta de Instrução Cobranca", "/view/central/cadastrosbasicos/instrucaoCobranca/listInstrucaoCobranca.jsf", true), "bancarios");
//        create(new Permissao("instrucaoCobranca.audit", "Auditoria de Instrução Cobranca"), "instrucaoCobranca");
//        create(new Permissao("instrucaoCobranca.delete", "Exclusão de Instrução Cobranca"), "instrucaoCobranca");
//Pais
//        create(new Permissao("pais", "Pais - Pai", false), "cep");
//        create(new Permissao("pais.create", "Cadastro de Pais", "/view/central/cadastrosbasicos/pais/createPais.jsf", false), "pais");
//        create(new Permissao("pais.list", "Pais", "/view/central/cadastrosbasicos/pais/listPais.jsf", true), "cep");
//        create(new Permissao("pais.audit", "Auditoria de Pais"), "pais");
//        create(new Permissao("pais.delete", "Exclusão de Pais"), "pais");
//Representante
//        create(new Permissao("representante", "Representante - Pai", false), "complementar");
//        create(new Permissao("representante.create", "Cadastro de Representante", "/view/central/cadastrosbasicos/representante/createRepresentante.jsf", false), "representante");
//        create(new Permissao("representante.list", "Representantes", "/view/central/cadastrosbasicos/representante/listRepresentante.jsf", true), "complementar");
//        create(new Permissao("representante.audit", "Auditoria de Representante"), "representante");
//        create(new Permissao("representante.delete", "Exclusão de Representante"), "representante");
//Tabela De Comissao
//        create(new Permissao("tabelaDeComissao", "Tabela De Comissao - Pai", false), "complementar");
//        create(new Permissao("tabelaDeComissao.create", "Cadastro de Tabela De Comissao", "/view/central/cadastrosbasicos/tabelaDeComissao/createTabelaDeComissao.jsf", false), "tabelaDeComissao");
//        create(new Permissao("tabelaDeComissao.list", "Tabela De Comissao", "/view/central/cadastrosbasicos/tabelaDeComissao/listTabelaDeComissao.jsf", true), "complementar");
//        create(new Permissao("tabelaDeComissao.audit", "Auditoria de Tabela De Comissao"), "tabelaDeComissao");
//        create(new Permissao("tabelaDeComissao.delete", "Exclusão de Tabela De Comissao"), "tabelaDeComissao");
//Tipo Documento
//        create(new Permissao("tipoDocumento", "Tipo Documento - Pai", false), "tiposCadastro");
//        create(new Permissao("tipoDocumento.create", "Cadastro de Tipo Documento", "/view/central/cadastrosbasicos/tipoDocumento/createTipoDocumento.jsf", false), "tipoDocumento");
//        create(new Permissao("tipoDocumento.list", "Tipo Documento", "/view/central/cadastrosbasicos/tipoDocumento/listTipoDocumento.jsf", true), "tiposCadastro");
//        create(new Permissao("tipoDocumento.audit", "Auditoria de Tipo Documento"), "tipoDocumento");
//        create(new Permissao("tipoDocumento.delete", "Exclusão de Tipo Documento"), "tipoDocumento");
//Transportadora
//        create(new Permissao("transportadora", "Transportadora - Pai", false), "complementar");
//        create(new Permissao("transportadora.create", "Cadastro de Transportadora", "/view/central/cadastrosbasicos/transportadora/createTransportadora.jsf", false), "transportadora");
//        create(new Permissao("transportadora.list", "Transportadoras", "/view/central/cadastrosbasicos/transportadora/listTransportadora.jsf", true), "complementar");
//        create(new Permissao("transportadora.audit", "Auditoria de Transportadora"), "transportadora");
//        create(new Permissao("transportadora.delete", "Exclusão de Transportadora"), "transportadora");
        //Centro Resultados
//        create(new Permissao("centroResultados", "Centro Resultados - Pai", false), "cadastrosbasicos");
//        create(new Permissao("centroResultados.create", "Cadastro de Centro Resultados", "/view/financeiro/centroResultados/createCentroResultados.jsf", false), "centroResultados");
//        create(new Permissao("centroResultados.list", "Centro Resultados", "/view/financeiro/centroResultados/listCentroResultados.jsf", true), "cadastrosbasicos");
//        create(new Permissao("centroResultados.audit", "Auditoria de Centro Resultados"), "centroResultados");
//        create(new Permissao("centroResultados.delete", "Exclusão de Centro Resultados"), "centroResultados");
//Receita Despesa
//        create(new Permissao("receitaDespesa", "Receita Despesa - Pai", false), "cadastrosbasicos");
//        create(new Permissao("receitaDespesa.create", "Cadastro de Receita Despesa", "/view/financeiro/receitaDespesa/createReceitaDespesa.jsf", false), "receitaDespesa");
//        create(new Permissao("receitaDespesa.list", "Receita Despesa", "/view/financeiro/receitaDespesa/listReceitaDespesa.jsf", true), "cadastrosbasicos");
//        create(new Permissao("receitaDespesa.audit", "Auditoria de Receita Despesa"), "receitaDespesa");
//        create(new Permissao("receitaDespesa.delete", "Exclusão de Receita Despesa"), "receitaDespesa");
//        create(new Permissao("movimentos", "Movimentos", true), null);
        //Contas A Pagar
//        create(new Permissao("contasAPagar", "Contas a Pagar - Pai", false), "movimentos");
//        create(new Permissao("contasAPagar.create", "Cadastro de Contas a Pagar", "/view/financeiro/contasAPagar/createContasAPagar.jsf", false), "contasAPagar");
//        create(new Permissao("contasAPagar.list", "Contas a Pagar", "/view/financeiro/contasAPagar/listContasAPagar.jsf", true), "movimentos");
//        create(new Permissao("contasAPagar.audit", "Auditoria de Contas a Pagar"), "contasAPagar");
//        create(new Permissao("contasAPagar.delete", "Exclusão de Contas a Pagar"), "contasAPagar");
        //Lancamentos
//        create(new Permissao("lancamentos", "Lançamentos - Pai", false), "movimentos");
//        create(new Permissao("lancamentos.create", "Cadastro de Lançamentos", "/view/financeiro/lancamentos/createLancamentos.jsf", false), "lancamentos");
//        create(new Permissao("lancamentos.list", "Lançamentos", "/view/financeiro/lancamentos/listLancamentos.jsf", true), "movimentos");
//        create(new Permissao("lancamentos.audit", "Auditoria de Lançamentos"), "lancamentos");
//        create(new Permissao("lancamentos.delete", "Exclusão de Lançamentos"), "lancamentos");
//-------------------------------------- FIM MENU AG -----------------------------------------------
//        create(new Permissao("remuneracao", "Remuneração", true), "fatosLojas");
//        create(new Permissao("diaADia.consultar", "Dia a Dia", "/view/regente/diadia.jsf", true), "remuneracao");
//        
//        create(new Permissao("relatorio", "Relatórios", true), null);
//        create(new Permissao("relatorio.contasApagar", "Contas a Pagar", true), "relatorio");
//        create(new Permissao("relatorio.contasemaberto", "Em Aberto", "/view/relatorios/contasApagarEmAberto.jsf", true), "relatorio.contasApagar");
        //Cal Guia
//        create(new Permissao("calGuia", "Cal Guia menu", false), null);
//        create(new Permissao("calGuia.create", "Cadastro de Cal Guia", "/view/central/cadastrosbasicos/calGuia/createCalGuia.jsf", false), "calGuia");
//        create(new Permissao("calGuia.list", "Cálculos de Guias", "/view/central/cadastrosbasicos/calGuia/listCalGuia.jsf", true), "cadastrosbasicos");
//        create(new Permissao("calGuia.audit", "Auditoria de Cal Guia"), "calGuia");
//        create(new Permissao("calGuia.delete", "Exclusão de Cal Guia"), "calGuia");
//Cal Guia Item
//        create(new Permissao("calGuiaItem", "Cal Guia Item - Menu", false), null);
//        create(new Permissao("calGuiaItem.create", "Cadastro de Cal Guia Item", "/view/central/cadastrosbasicos/calGuiaItem/createCalGuiaItem.jsf", false), "calGuiaItem");
//        create(new Permissao("calGuiaItem.list", "Cálculos de Guias - Itens", "/view/central/cadastrosbasicos/calGuiaItem/listCalGuiaItem.jsf", true), "cadastrosbasicos");
//        create(new Permissao("calGuiaItem.audit", "Auditoria de Cal Guia Item"), "calGuiaItem");
//        create(new Permissao("calGuiaItem.delete", "Exclusão de Cal Guia Item"), "calGuiaItem");
//Grupo Loja
//        create(new Permissao("grupoLoja", "Grupos de Loja - menu", false), null);
//        create(new Permissao("grupoLoja.create", "Cadastro de Grupo Loja", "/view/central/cadastrosbasicos/grupoLoja/createGrupoLoja.jsf", false), "grupoLoja");
//        create(new Permissao("grupoLoja.list", "Grupos de Loja", "/view/central/cadastrosbasicos/grupoLoja/listGrupoLoja.jsf", true), "fatosLojas");
//        create(new Permissao("grupoLoja.audit", "Auditoria de Grupo Loja"), "grupoLoja");
//        create(new Permissao("grupoLoja.delete", "Exclusão de Grupo Loja"), "grupoLoja");
//        create(new Permissao("relatorio.contasentradas", "Entradas", "/view/relatorios/contasApagarEntrada.jsf", true), "relatorio.contasApagar");
//        create(new Permissao("relatorio.contascanceladas", "Canceladas", "/view/relatorios/contasApagarCanceladas.jsf", true), "relatorio.contasApagar");
//        create(new Permissao("relatorio.contaspagas", "Pagas", "/view/relatorios/contasApagarPagas.jsf", true), "relatorio.contasApagar");
//Lista Loja
//        create(new Permissao("listaLoja", "Grupo Usuários x Lojas Menu", false), null);
//        create(new Permissao("listaLoja.create", "Cadastro de Grupo Usuários x Lojas", "/view/central/cadastrosbasicos/listaLoja/createListaLoja.jsf", false), "listaLoja");
//        create(new Permissao("listaLoja.list", "Grupo Usuários x Lojas", "/view/central/cadastrosbasicos/listaLoja/listListaLoja.jsf", true), "fatosLojas");
//        create(new Permissao("listaLoja.audit", "Auditoria de Grupo Usuários x Lojas"), "listaLoja");
//        create(new Permissao("listaLoja.delete", "Exclusão de Grupo Usuários x Lojas"), "listaLoja");
    }

    private void createGlobal(Permissao permissao, String pai) {
        permissao.setGlobal(true);
        create(permissao, pai);
    }

    private void create(Permissao permissao, String pai) {

        if (pai != null && pai.equals(permissao.getKey())) {
            logger.log(Level.WARNING, "Permissao ''{0}'' pai dela mesmo. Esta permissao nao sera salva.", permissao.getKey());
            return;
        }

        Permissao permissaoDB = permissaoDAO.unique("key", permissao.getKey());

        if (pai != null && !pai.isEmpty()) {
            Permissao permissaoPai = permissaoDAO.unique("key", pai);
            permissao.setPermissaoPai(permissaoPai);
        }

        if (permissaoDB != null) {
            permissaoDB.setUrl(permissao.getUrl());
            permissaoDB.setDescricao(permissao.getDescricao());
//            permissaoDB.setPermissaoPai(permissao.getPermissaoPai());
            permissaoDB.setPossuiMenu(permissao.isPossuiMenu());
            permissaoDB.setGlobal(permissao.isGlobal());
            permissao = permissaoDB;
        }

        permissaoDAO.merge(permissao, false);
    }
}
