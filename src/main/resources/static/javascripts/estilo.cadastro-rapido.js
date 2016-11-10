/**
 * Created by evandro on 21/09/16.
 */
var Brewer = Brewer || {};

Brewer.EstiloCadastroRapido=(function () {

    function EstiloCadastroRapido(){
        // No construtor é colocado a inicialização dos objetos
        this.modalCadastro = $('#modalCadastroRapidoEstilo');
        this.botaoSalvar = this.modalCadastro.find('.js-modal-cadastro-estilo-salvar-btn');


        // Com a função abaixo pegamos o formulário relacionado ao Modal e trocamos o evento de submit.
        // com, ao clicar em Enter, o formulário não é recarregado
        this.form = this.modalCadastro.find('form');

        // Interceptando a URL de Action
        this.url = this.form.attr('action');

        // buscando o elemento nomeEstilo para possibilitar trabalhar com ele
        this.inputNomeEstlo = $('#nomeEstilo');

        // Div contendo as mensagens de alerta
        this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');

    }

    EstiloCadastroRapido.prototype.iniciar = function () {
        // Trocamos o evento de submit.
        // com, ao clicar em Enter, o formulário não é recarregado
        this.form.on('submit', function (event) { event.preventDefault(); });

        // Eventos bs são do BootStrap que lançam quando trabalhamos com o Modal
        // pesquisar por mais alguns
        // Evento Disparado ao mostar o Modal onShow
        // Lembran que o bind(this) faz com que a função onModalShow seja chamada no contexto do objeto atual
        this.modalCadastro.on('shown.bs.modal', onModalShow.bind(this));
        // Evento Disparado ao fechar o modal onClose
        this.modalCadastro.on('hide.bs.modal', onModalClose.bind(this));
        // Evento de OnClick
        this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
    }

    // Faz com que o controle nomeEstilo tenha o foco quando iniciar o modal
    function  onModalShow() {
        this.inputNomeEstlo.focus();
    }

    // Implementação do Evento de Close, inicializa o input para branco
    function onModalClose() {
        this.inputNomeEstlo.val('');

        // Ajuste para remover as formatações que foram inseridas pelas validações e mensagens.
        this.containerMensagemErro.addClass('hidden');// Adiciona a classe hidden do Bootstrap
        this.form.find('.form-group').removeClass('has-error');
    }

    // Evento OnClick
    function onBotaoSalvarClick() {
        var nomeEstilo = this.inputNomeEstlo.val().trim();
        console.log('nome estilo', nomeEstilo);

        // função ajax para submeter os dados ao servidor. JQuery
        $.ajax({
            url:this.url,
            method:'POST',
            contentType: 'application/json',
            data: JSON.stringify({ 'nome': nomeEstilo }), // Método que converte uma string em formato JSON
            error: onErroSalvarEstilo.bind(this),
            success: onEstiloSalvo.bind(this)
        })
    }

    function onErroSalvarEstilo(obj) {
        // Recebe como parâmetro obj para podermos acessar as propriedades do objeto de retorno
        // nesse caso os erros devolvidos pelo nosso metodo salvar do controller serão acessados
        // pelo responseText
        var mensagemErro = obj.responseText;
        this.containerMensagemErro.removeClass('hidden');
        this.containerMensagemErro.html('<span>'+mensagemErro+'</span>');
        this.form.find('.form-group').addClass('has-error');

    }

    function onEstiloSalvo(estilo){
        var comboEstilo = $('#estilo'); // nome do combo da página CadastroCerveja.html
        comboEstilo.append('<option value='+estilo.codigo+'>'+estilo.nome+'</option>');
        comboEstilo.val(estilo.codigo);

        this.modalCadastro.modal('hide');
    }

    return EstiloCadastroRapido;

})();

$(function () {
    var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
    estiloCadastroRapido.iniciar();
})