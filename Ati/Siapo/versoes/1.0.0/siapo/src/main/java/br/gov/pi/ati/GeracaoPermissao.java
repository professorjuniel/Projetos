package br.gov.pi.ati;

import br.gov.pi.ati.dao.controleacesso.PermissaoDAO;
import br.gov.pi.ati.modelo.controleacesso.Permissao;
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

        create(new Permissao("cadastro", "Cadastro", true), null);

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

        //Classificacao Da Despesa
        create(new Permissao("classificacaoDaDespesa", "Classificacao Da Despesa", false), "cadastro");
        create(new Permissao("classificacaoDaDespesa.create", "Cadastro de Classificacao Da Despesa", "/view/cadastro/classificacaoDaDespesa/createClassificacaoDaDespesa.jsf", false), "classificacaoDaDespesa");
        create(new Permissao("classificacaoDaDespesa.list", "Classificacões da Despesa", "/view/cadastro/classificacaoDaDespesa/listClassificacaoDaDespesa.jsf", true), "cadastro");
        create(new Permissao("classificacaoDaDespesa.audit", "Auditoria de Classificacao Da Despesa"), "classificacaoDaDespesa");
        create(new Permissao("classificacaoDaDespesa.delete", "Exclusão de Classificacao Da Despesa"), "classificacaoDaDespesa");

        create(new Permissao("fontes", "Fontes", true), "cadastro");
//Fonte De Recurso
        create(new Permissao("fonteDeRecurso", "Fonte De Recurso", false), "fontes");
        create(new Permissao("fonteDeRecurso.create", "Cadastro de Fonte De Recurso", "/view/cadastro/fonteDeRecurso/createFonteDeRecurso.jsf", false), "fonteDeRecurso");
        create(new Permissao("fonteDeRecurso.list", "Fontes de Recurso", "/view/cadastro/fonteDeRecurso/listFonteDeRecurso.jsf", true), "fontes");
        create(new Permissao("fonteDeRecurso.audit", "Auditoria de Fonte De Recurso"), "fonteDeRecurso");
        create(new Permissao("fonteDeRecurso.delete", "Exclusão de Fonte De Recurso"), "fonteDeRecurso");

        //Grupo De Fonte
        create(new Permissao("grupoDeFonte", "Grupo de Fonte", false), "fontes");
        create(new Permissao("grupoDeFonte.create", "Cadastro de Grupo De Fonte", "/view/cadastro/grupoDeFonte/createGrupoDeFonte.jsf", false), "grupoDeFonte");
        create(new Permissao("grupoDeFonte.list", "Grupos de Fontes", "/view/cadastro/grupoDeFonte/listGrupoDeFonte.jsf", true), "fontes");
        create(new Permissao("grupoDeFonte.audit", "Auditoria de Grupo De Fonte"), "grupoDeFonte");
        create(new Permissao("grupoDeFonte.delete", "Exclusão de Grupo De Fonte"), "grupoDeFonte");

        create(new Permissao("funcoes", "Funções de Governo", true), "cadastro");

//Funcao
        create(new Permissao("funcao", "Função", false), "funcoes");
        create(new Permissao("funcao.create", "Cadastro de Função", "/view/cadastro/funcao/createFuncao.jsf", false), "funcao");
        create(new Permissao("funcao.list", "Funcões de Governos", "/view/cadastro/funcao/listFuncao.jsf", true), "funcoes");
        create(new Permissao("funcao.audit", "Auditoria de Função"), "funcao");
        create(new Permissao("funcao.delete", "Exclusão de Função"), "funcao");

        //Sub Funcao
        create(new Permissao("subFuncao", "Sub Função", false), "funcoes");
        create(new Permissao("subFuncao.create", "Cadastro de Sub Função", "/view/cadastro/subFuncao/createSubFuncao.jsf", false), "subFuncao");
        create(new Permissao("subFuncao.list", "Subfuncões", "/view/cadastro/subFuncao/listSubFuncao.jsf", true), "funcoes");
        create(new Permissao("subFuncao.audit", "Auditoria de Sub Função"), "subFuncao");
        create(new Permissao("subFuncao.delete", "Exclusão de Sub Função"), "subFuncao");

//Programa De Governo
        create(new Permissao("programaDeGoverno", "Programa De Governo", false), "cadastro");
        create(new Permissao("programaDeGoverno.create", "Cadastro de Programa De Governo", "/view/cadastro/programaDeGoverno/createProgramaDeGoverno.jsf", false), "programaDeGoverno");
        create(new Permissao("programaDeGoverno.list", "Programas de Governo", "/view/cadastro/programaDeGoverno/listProgramaDeGoverno.jsf", true), "cadastro");
        create(new Permissao("programaDeGoverno.audit", "Auditoria de Programa De Governo"), "programaDeGoverno");
        create(new Permissao("programaDeGoverno.delete", "Exclusão de Programa De Governo"), "programaDeGoverno");

        //Orgao
        create(new Permissao("orgao", "Orgao", false), "cadastro");
        create(new Permissao("orgao.create", "Cadastro de Orgao", "/view/cadastro/orgao/createOrgao.jsf", false), "orgao");
        create(new Permissao("orgao.list", "Órgãos", "/view/cadastro/orgao/listOrgao.jsf", true), "cadastro");
        create(new Permissao("orgao.audit", "Auditoria de Orgao"), "orgao");
        create(new Permissao("orgao.delete", "Exclusão de Orgao"), "orgao");

        create(new Permissao("cadastro.unidade", "Unidades", true), "cadastro");

//Unidade Gestora
        create(new Permissao("unidadeGestora", "Unidade Gestora", false), "cadastro.unidade");
        create(new Permissao("unidadeGestora.create", "Cadastro de Unidade Gestora", "/view/cadastro/unidadeGestora/createUnidadeGestora.jsf", false), "unidadeGestora");
        create(new Permissao("unidadeGestora.list", "Unidades Gestoras", "/view/cadastro/unidadeGestora/listUnidadeGestora.jsf", true), "cadastro.unidade");
        create(new Permissao("unidadeGestora.audit", "Auditoria de Unidade Gestora"), "unidadeGestora");
        create(new Permissao("unidadeGestora.delete", "Exclusão de Unidade Gestora"), "unidadeGestora");

//Unidade Orcamentaria
        create(new Permissao("unidadeOrcamentaria", "Unidade Orcamentaria", false), "cadastro.unidade");
        create(new Permissao("unidadeOrcamentaria.create", "Cadastro de Unidade Orcamentaria", "/view/cadastro/unidadeOrcamentaria/createUnidadeOrcamentaria.jsf", false), "unidadeOrcamentaria");
        create(new Permissao("unidadeOrcamentaria.list", "Unidades Orcamentárias", "/view/cadastro/unidadeOrcamentaria/listUnidadeOrcamentaria.jsf", true), "cadastro.unidade");
        create(new Permissao("unidadeOrcamentaria.audit", "Auditoria de Unidade Orcamentaria"), "unidadeOrcamentaria");
        create(new Permissao("unidadeOrcamentaria.delete", "Exclusão de Unidade Orcamentaria"), "unidadeOrcamentaria");

//Unidade De Medida
        create(new Permissao("unidadeDeMedida", "Unidade De Medida", false), "cadastro.unidade");
        create(new Permissao("unidadeDeMedida.create", "Cadastro de Unidade De Medida", "/view/cadastro/unidadeDeMedida/createUnidadeDeMedida.jsf", false), "unidadeDeMedida");
        create(new Permissao("unidadeDeMedida.list", "Unidades de Medidas", "/view/cadastro/unidadeDeMedida/listUnidadeDeMedida.jsf", true), "cadastro.unidade");
        create(new Permissao("unidadeDeMedida.audit", "Auditoria de Unidade De Medida"), "unidadeDeMedida");
        create(new Permissao("unidadeDeMedida.delete", "Exclusão de Unidade De Medida"), "unidadeDeMedida");

        //Categoria De Despesa
        create(new Permissao("categoriaDeDespesa", "Categoria De Despesa", false), "cadastro");
        create(new Permissao("categoriaDeDespesa.create", "Cadastro de Categoria De Despesa", "/view/cadastro/categoriaDeDespesa/createCategoriaDeDespesa.jsf", false), "categoriaDeDespesa");
        create(new Permissao("categoriaDeDespesa.list", "Categorias de Despesas", "/view/cadastro/categoriaDeDespesa/listCategoriaDeDespesa.jsf", true), "cadastro");
        create(new Permissao("categoriaDeDespesa.audit", "Auditoria de Categoria De Despesa"), "categoriaDeDespesa");
        create(new Permissao("categoriaDeDespesa.delete", "Exclusão de Categoria De Despesa"), "categoriaDeDespesa");

//Diretrize De Governo
        create(new Permissao("diretrizeDeGoverno", "Diretrize De Governo", false), "cadastro");
        create(new Permissao("diretrizeDeGoverno.create", "Cadastro de Diretrize De Governo", "/view/cadastro/diretrizeDeGoverno/createDiretrizeDeGoverno.jsf", false), "diretrizeDeGoverno");
        create(new Permissao("diretrizeDeGoverno.list", "Diretrizes de Governos", "/view/cadastro/diretrizeDeGoverno/listDiretrizeDeGoverno.jsf", true), "cadastro");
        create(new Permissao("diretrizeDeGoverno.audit", "Auditoria de Diretrize De Governo"), "diretrizeDeGoverno");
        create(new Permissao("diretrizeDeGoverno.delete", "Exclusão de Diretrize De Governo"), "diretrizeDeGoverno");

//Elemento De Despesa
        create(new Permissao("elementoDeDespesa", "Elemento De Despesa", false), "cadastro");
        create(new Permissao("elementoDeDespesa.create", "Cadastro de Elemento De Despesa", "/view/cadastro/elementoDeDespesa/createElementoDeDespesa.jsf", false), "elementoDeDespesa");
        create(new Permissao("elementoDeDespesa.list", "Elementos de Despesas", "/view/cadastro/elementoDeDespesa/listElementoDeDespesa.jsf", true), "cadastro");
        create(new Permissao("elementoDeDespesa.audit", "Auditoria de Elemento De Despesa"), "elementoDeDespesa");
        create(new Permissao("elementoDeDespesa.delete", "Exclusão de Elemento De Despesa"), "elementoDeDespesa");

//Grupo De Despesa
        create(new Permissao("grupoDeDespesa", "Grupo De Despesa", false), "cadastro");
        create(new Permissao("grupoDeDespesa.create", "Cadastro de Grupo De Despesa", "/view/cadastro/grupoDeDespesa/createGrupoDeDespesa.jsf", false), "grupoDeDespesa");
        create(new Permissao("grupoDeDespesa.list", "Grupos de Despesas", "/view/cadastro/grupoDeDespesa/listGrupoDeDespesa.jsf", true), "cadastro");
        create(new Permissao("grupoDeDespesa.audit", "Auditoria de Grupo De Despesa"), "grupoDeDespesa");
        create(new Permissao("grupoDeDespesa.delete", "Exclusão de Grupo De Despesa"), "grupoDeDespesa");

//Modalidade De Aplicacao
        create(new Permissao("modalidadeDeAplicacao", "Modalidade De Aplicacao", false), "cadastro");
        create(new Permissao("modalidadeDeAplicacao.create", "Cadastro de Modalidade De Aplicacao", "/view/cadastro/modalidadeDeAplicacao/createModalidadeDeAplicacao.jsf", false), "modalidadeDeAplicacao");
        create(new Permissao("modalidadeDeAplicacao.list", "Modalidades de Aplicações", "/view/cadastro/modalidadeDeAplicacao/listModalidadeDeAplicacao.jsf", true), "cadastro");
        create(new Permissao("modalidadeDeAplicacao.audit", "Auditoria de Modalidade De Aplicacao"), "modalidadeDeAplicacao");
        create(new Permissao("modalidadeDeAplicacao.delete", "Exclusão de Modalidade De Aplicacao"), "modalidadeDeAplicacao");

        create(new Permissao("naturezas", "Naturezas", true), "cadastro");

        //Natureza De Despesa
        create(new Permissao("naturezaDeDespesa", "Natureza De Despesa", false), "naturezas");
        create(new Permissao("naturezaDeDespesa.create", "Cadastro de Natureza De Despesa", "/view/cadastro/naturezaDeDespesa/createNaturezaDeDespesa.jsf", false), "naturezaDeDespesa");
        create(new Permissao("naturezaDeDespesa.list", "Despesas", "/view/cadastro/naturezaDeDespesa/listNaturezaDeDespesa.jsf", true), "naturezas");
        create(new Permissao("naturezaDeDespesa.audit", "Auditoria de Natureza De Despesa"), "naturezaDeDespesa");
        create(new Permissao("naturezaDeDespesa.delete", "Exclusão de Natureza De Despesa"), "naturezaDeDespesa");

//Natureza De Receita
        create(new Permissao("naturezaDeReceita", "Natureza De Receita", false), "naturezas");
        create(new Permissao("naturezaDeReceita.create", "Cadastro de Natureza De Receita", "/view/cadastro/naturezaDeReceita/createNaturezaDeReceita.jsf", false), "naturezaDeReceita");
        create(new Permissao("naturezaDeReceita.list", "Receitas", "/view/cadastro/naturezaDeReceita/listNaturezaDeReceita.jsf", true), "naturezas");
        create(new Permissao("naturezaDeReceita.audit", "Auditoria de Natureza De Receita"), "naturezaDeReceita");
        create(new Permissao("naturezaDeReceita.delete", "Exclusão de Natureza De Receita"), "naturezaDeReceita");

//Territorio
        create(new Permissao("territorio", "Territorio", false), "cadastro");
        create(new Permissao("territorio.create", "Cadastro de Territorio", "/view/cadastro/territorio/createTerritorio.jsf", false), "territorio");
        create(new Permissao("territorio.list", "Territórios", "/view/cadastro/territorio/listTerritorio.jsf", true), "cadastro");
        create(new Permissao("territorio.audit", "Auditoria de Territorio"), "territorio");
        create(new Permissao("territorio.delete", "Exclusão de Territorio"), "territorio");

        //Acao Estrategica
        create(new Permissao("acaoEstrategica", "Acao Estrategica", false), "cadastro");
        create(new Permissao("acaoEstrategica.create", "Cadastro de Acao Estrategica", "/view/cadastro/acaoEstrategica/createAcaoEstrategica.jsf", false), "acaoEstrategica");
        create(new Permissao("acaoEstrategica.list", "Acões Estrategicas", "/view/cadastro/acaoEstrategica/listAcaoEstrategica.jsf", true), "cadastro");
        create(new Permissao("acaoEstrategica.audit", "Auditoria de Acao Estrategica"), "acaoEstrategica");
        create(new Permissao("acaoEstrategica.delete", "Exclusão de Acao Estrategica"), "acaoEstrategica");

//Produto
        create(new Permissao("produto", "Produto", false), "cadastro");
        create(new Permissao("produto.create", "Cadastro de Produto", "/view/cadastro/produto/createProduto.jsf", false), "produto");
        create(new Permissao("produto.list", "Produtos", "/view/cadastro/produto/listProduto.jsf", true), "cadastro");
        create(new Permissao("produto.audit", "Auditoria de Produto"), "produto");
        create(new Permissao("produto.delete", "Exclusão de Produto"), "produto");


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
