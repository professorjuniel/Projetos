package br.gov.pi.ati.sccd;

import br.gov.pi.ati.sccd.dao.controleacesso.PermissaoDAO;
import br.gov.pi.ati.sccd.modelo.controleacesso.Permissao;
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
        create(new Permissao("permissao.ativacao", "Ativação de Permissão"), "permissao");
        create(new Permissao("permissao.inativacao", "Inativação de Permissão"), "permissao");

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

        //Menu Cadastro
        create(new Permissao("cadastro", "Cadastro", true), null);

        //Cliente
        create(new Permissao("cliente", "Cliente", false), "cadastro");
        create(new Permissao("cliente.create", "Cadastro de Cliente", "/view/cadastro/cliente/createCliente.jsf", false), "cliente");
        create(new Permissao("cliente.list", "Clientes", "/view/cadastro/cliente/listCliente.jsf", true), "cadastro");
        create(new Permissao("cliente.audit", "Auditoria de Cliente"), "cliente");
        create(new Permissao("cliente.delete", "Exclusão de Cliente"), "cliente");

//Fornecedor
        create(new Permissao("fornecedor", "Fornecedor", false), "cadastro");
        create(new Permissao("fornecedor.create", "Cadastro de Fornecedor", "/view/cadastro/fornecedor/createFornecedor.jsf", false), "fornecedor");
        create(new Permissao("fornecedor.list", "Fornecedores", "/view/cadastro/fornecedor/listFornecedor.jsf", true), "cadastro");
        create(new Permissao("fornecedor.audit", "Auditoria de Fornecedor"), "fornecedor");
        create(new Permissao("fornecedor.delete", "Exclusão de Fornecedor"), "fornecedor");

//Tipo Certificado
        create(new Permissao("tipoCertificado", "Tipo Certificado", false), "cadastro");
        create(new Permissao("tipoCertificado.create", "Cadastro de Tipo Certificado", "/view/cadastro/tipoCertificado/createTipoCertificado.jsf", false), "tipoCertificado");
        create(new Permissao("tipoCertificado.list", "Tipos Certificados", "/view/cadastro/tipoCertificado/listTipoCertificado.jsf", true), "cadastro");
        create(new Permissao("tipoCertificado.audit", "Auditoria de Tipo Certificado"), "tipoCertificado");
        create(new Permissao("tipoCertificado.delete", "Exclusão de Tipo Certificado"), "tipoCertificado");

        //Menu Cadastro
        create(new Permissao("contrato", "Contratos", true), null);

        //Contrato Cliente
        create(new Permissao("contratoCliente", "Contrato Cliente", false), "contrato");
        create(new Permissao("contratoCliente.create", "Cadastro de Contrato Cliente", "/view/certificado/contratoCliente/createContratoCliente.jsf", false), "contratoCliente");
        create(new Permissao("contratoCliente.list", "Clientes", "/view/certificado/contratoCliente/listContratoCliente.jsf", true), "contrato");
        create(new Permissao("contratoCliente.audit", "Auditoria de Contrato Cliente"), "contratoCliente");
        create(new Permissao("contratoCliente.delete", "Exclusão de Contrato Cliente"), "contratoCliente");

//Contrato Fornecedor
        create(new Permissao("contratoFornecedor", "Contrato Fornecedor", false), "contrato");
        create(new Permissao("contratoFornecedor.create", "Cadastro de Contrato Fornecedor", "/view/certificado/contratoFornecedor/createContratoFornecedor.jsf", false), "contratoFornecedor");
        create(new Permissao("contratoFornecedor.list", "Fornecedores", "/view/certificado/contratoFornecedor/listContratoFornecedor.jsf", true), "contrato");
        create(new Permissao("contratoFornecedor.audit", "Auditoria de Contrato Fornecedor"), "contratoFornecedor");
        create(new Permissao("contratoFornecedor.delete", "Exclusão de Contrato Fornecedor"), "contratoFornecedor");

        //Autoridade Certificadora
        create(new Permissao("autoridadeCertificadora", "Autoridade Certificadora", false), "cadastro");
        create(new Permissao("autoridadeCertificadora.create", "Cadastro de Autoridade Certificadora", "/view/cadastro/autoridadeCertificadora/createAutoridadeCertificadora.jsf", false), "autoridadeCertificadora");
        create(new Permissao("autoridadeCertificadora.list", "Autoridades Certificadoras", "/view/cadastro/autoridadeCertificadora/listAutoridadeCertificadora.jsf", true), "cadastro");
        create(new Permissao("autoridadeCertificadora.audit", "Auditoria de Autoridade Certificadora"), "autoridadeCertificadora");
        create(new Permissao("autoridadeCertificadora.delete", "Exclusão de Autoridade Certificadora"), "autoridadeCertificadora");

        //Configuracao Sistema
        create(new Permissao("configuracaoSistema", "Configuração Sistema", false), "cadastro");
        create(new Permissao("configuracaoSistema.create", "Cadastro de Configuração Sistema", "/view/cadastro/configuracaoSistema/createConfiguracaoSistema.jsf", false), "configuracaoSistema");
        create(new Permissao("configuracaoSistema.list", "Configurações do Sistema", "/view/cadastro/configuracaoSistema/listConfiguracaoSistema.jsf", true), "cadastro");
        create(new Permissao("configuracaoSistema.audit", "Auditoria de Configuração Sistema"), "configuracaoSistema");
        create(new Permissao("configuracaoSistema.delete", "Exclusão de Configuração Sistema"), "configuracaoSistema");

        //Menu certificados
        create(new Permissao("certificado", "Certificado", true), null);

        //Pedido
        create(new Permissao("pedido", "Pedido", false), "certificado");
        create(new Permissao("pedido.create", "Cadastro de Pedido", "/view/certificado/pedido/createPedido.jsf", false), "pedido");
        create(new Permissao("pedido.list", "Pedidos", "/view/certificado/pedido/listPedido.jsf", true), "certificado");
        create(new Permissao("pedido.audit", "Auditoria de Pedido"), "pedido");
        create(new Permissao("pedido.delete", "Exclusão de Pedido"), "pedido");

        //Certificado
        create(new Permissao("certificadoEmitidos", "Certificado", false), "certificado");
        create(new Permissao("certificadoEmitidos.create", "Cadastro de Certificado", "/view/certificado/certificado/createCertificado.jsf", false), "certificadoEmitidos");
        create(new Permissao("certificadoEmitidos.list", "Emitidos", "/view/certificado/certificado/listCertificado.jsf", true), "certificado");
        create(new Permissao("certificadoEmitidos.audit", "Auditoria de Certificado"), "certificadoEmitidos");
        create(new Permissao("certificadoEmitidos.delete", "Exclusão de Certificado"), "certificadoEmitidos");

        //Agendamento
        create(new Permissao("agendamento", "Agendamento", true), "certificado");
        create(new Permissao("agendamento.create", "Agenda", "/view/certificado/agendamento/createAgendamento.jsf", true), "agendamento");
        create(new Permissao("agendamento.list", "Consultar", "/view/certificado/agendamento/listAgendamento.jsf", true), "agendamento");
        create(new Permissao("agendamento.editar", "Editar Agendamento"), "agendamento");
        create(new Permissao("agendamento.audit", "Auditoria de Agendamento"), "agendamento");
        create(new Permissao("agendamento.delete", "Exclusão de Agendamento"), "agendamento");

        //Feriado
        create(new Permissao("feriado", "Feriado", false), "cadastro");
        create(new Permissao("feriado.create", "Cadastro de Feriado", "/view/cadastro/feriado/createFeriado.jsf", false), "feriado");
        create(new Permissao("feriado.list", "Feriados", "/view/cadastro/feriado/listFeriado.jsf", true), "cadastro");
        create(new Permissao("feriado.audit", "Auditoria de Feriado"), "feriado");
        create(new Permissao("feriado.delete", "Exclusão de Feriado"), "feriado");

        /*
         * Configuracao
         */
        create(new Permissao("configuracaoSistema", "Configuração", true), "administracaoSistema");

        //Erro sistema
        create(new Permissao("erroSistema.list", "Relatório de Erros", "/view/configuracao/erroSistema/listErroSistema.jsf", true), "configuracaoSistema");

        /**
         * Permissoes Globais (essas permissoes todos terao acessos)
         */
        //Alterar Senha
        createGlobal(new Permissao("usuario.alterarSenha", "Alterar Senha", "/view/controleAcesso/password/alterPassword.jsf", true), "controleAcesso");
        createGlobal(new Permissao("usuario.ultimosAcessos", "Últimos Acessos", "/view/controleAcesso/acessoSistema/ultimosAcessoSistema.jsf", true), "controleAcesso");

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
            if (permissaoPai == null) {
                logger.log(Level.WARNING, "Permissao ''{0}'' pai nao encontrada.", pai);
            }
            permissao.setPermissaoPai(permissaoPai);
        }

        if (permissaoDB != null) {
            permissaoDB.setUrl(permissao.getUrl());
            permissaoDB.setDescricao(permissao.getDescricao());
            permissaoDB.setPermissaoPai(permissao.getPermissaoPai());
            permissaoDB.setPossuiMenu(permissao.isPossuiMenu());
            permissaoDB.setGlobal(permissao.isGlobal());
            permissao = permissaoDB;
        }

        permissaoDAO.merge(permissao, false);
    }
}
