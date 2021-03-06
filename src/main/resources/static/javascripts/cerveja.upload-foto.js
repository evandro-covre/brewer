/**
 * Created by evandro on 26/09/16.
 */
var Brewer = Brewer || [];

Brewer.UploadFoto = (function () {
    function UploadFoto(){
        this.inputNomeFoto = $('input[name=foto]');
        this.inputContentType = $('input[name=contentType]');

        this.htmlFotoCervejaTemplate = $('#foto-cerveja').html();
        this.template = Handlebars.compile(this.htmlFotoCervejaTemplate);

        this.containerFotoCerveja = $('.js-container-foto-cerveja');
        this.uploadDrop = $('#upload-drop');
    }

    UploadFoto.prototype.iniciar = function () {
        var settings = {
            type : 'json',
            filelimit: 1,
            allow : '*.(jpg|jpeg|png)',
            action: this.containerFotoCerveja.data('url-fotos'), // Contexto
            complete: onUploadCompleto.bind(this)
        }

        UIKit.uploadSelect($('#upload-select'), settings);
        UIKit.uploadDrop(this.uploadDrop, settings);

        if (this.inputNomeFoto.val()){
            onUploadCompleto.call(this, {nome:this.inputNomeFoto.val(), contentType:this.inputContentType.val()})
        }
    }

    function onUploadCompleto(resposta){
        this.inputNomeFoto.val(resposta.nome);
        this.inputContentType.val(resposta.contentType);

        this.uploadDrop.addClass('hidden');
        var htmlFotoCerveja = this.template({nomeFoto: resposta.nome});
        this.containerFotoCerveja.append(htmlFotoCerveja);

        $('.js-remove-foto').on('click', onRemoverFoto.bind(this));
    }

    return UploadFoto;
})();

$(funcion(){
   var uploadFoto = new Brewer.UploadFoto();
    uploadFoto.iniciar();
});