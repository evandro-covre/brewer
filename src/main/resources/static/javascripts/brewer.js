/**
 * Created by evandro on 05/09/16.
  $(function(){
    var decimal = $('.js-decimal');
    decimal.maskMoney({decimal: ',', thousands:'.'});

    var plain = $('.js-plain');
    plain.maskMoney({precision: 0, thousands: '.'});
})
 */
/**
 * Modularizando o JS
 */
// Caso o objeto Brewer não esteja instanciado, será criado uma nova instância
var Brewer = Brewer || {};//*

Brewer.MaskMoney=(function () {//*

    function MaskMoney() {//* Função Construtora
        this.decimal = $('.js-decimal');
        this.plain = $('.js-plain');
    }//*

    MaskMoney.prototype.enable = function () {
        this.decimal.maskMoney({decimal: ',', thousands:'.'});
        this.plain.maskMoney({precision: 0, thousands: '.'});
    }

    return MaskMoney;//* retorno da função
})();//*

$(function () {
    var maskMoney = new Brewer.MaskMoney();
    maskMoney.enable();
})