package com.algaworks.brewer.controller;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.repository.Cervejas;
import com.algaworks.brewer.repository.Estilos;
import com.algaworks.brewer.service.CadastroCervejaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.jws.Oneway;
import javax.validation.Valid;

/**
 * Created by evandro on 31/08/16.
 */
@Controller
@RequestMapping("/cervejas")
public class CervejasController {

    private static final Logger logger = LoggerFactory.getLogger(CervejasController.class);
    /** Indica que, ao digitar /cervejas/novo a resposta será dada através da página cerveja/CadastroCereja
     * Por padrão, quando o método de chamada for um Get, será chamado o metodo novo*/

    @Autowired
    private Estilos estilos;

    @Autowired
    private CadastroCervejaService cadastroCervejaService;

    @Autowired
    private Cervejas cervejas;

//    @RequestMapping("/cervejas/novo")
//    public String novo(Model model){
        /* Usando o Model, temos que definir qual é o objeto que será interpretado na minha view.
        No caso a view não sabe quem é o ojebto (cerveja) que estamos indicando como referência no formulário
        * */

//        model.addAttribute(new Cerveja());// Esse código é uma simplificação da parte abaixo
        //model.addAttribute("cerveja", new Cerveja()); Somente usa essa declaração se quiser trocar o nome do objeto
//        return "cerveja/CadastroCerveja";// Retorna o nome do Template que processará a requisição
//    }

/*    @RequestMapping("/cervejas/novo")
    public String novo(Cerveja cerveja){
        logger.error("Log de nivel Error!");
        logger.info("Log de nivel Info!");
        // Ver método anterior explicações
        //    Quando passamos o objeto como parâmetro, Spring entede que esse objeto será usado no model
        //    não sendo necessário incluir o objeto no model;
        //

//        cervejas.findAll();

        return "cerveja/CadastroCerveja";
    }
*/
    // Usando o acesso a Model dentro da
    @RequestMapping("/novo")
    public ModelAndView novo(Cerveja cerveja){
        // Deixamos de devolver a URL e devolvemos um ModelAndView.
        // Acarreta na necessidade de modificar o método cadastrar
        logger.debug("ModelAndView novo(Cerveja cerveja)");
        ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
        mv.addObject("sabores", Sabor.values());
        mv.addObject("estilos", estilos.findAll());
        mv.addObject("origens", Origem.values());

        return mv;
    }


    /** É possível utilizar a mesma URL para métodos distintos contudo, é necessário informar o método de acesso
     * para fazer o mapeamento correto. Pelo metodo GET será chamado o novo() e o método POST chamará o cadastrar()
     * A anotação @Valid funciona juntamente com a anotação da classe Cerveja, repassando os erros apra BindindResult
     * */
/*    @RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
    public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes){
        // O Spring faz o mapeamento automático do parâmetro da função cadastrar com o name do objeto input do formulário
        //do arquivo CadastroCerveja.html

        if (result.hasErrors()){
            // o Model é responsável por fazer a ponte com o Thymeleaf, através do atributo mensagem. Essa "mensagem"
            //será lida pelo Thymeleaf no browser e processado.
//            model.addAttribute(cerveja);
//            return "cerveja/CadastroCerveja";
            return novo(cerveja);
        }

        // No redirect o Thymeleaf perde a mensagem pois ela é encaminhada no header da nova página e perde a referência.
        //    Com o objeto RedirectAttributes, o Thymeleaf monta um objeto em memória para armazenar a mensagem e utilizar
        //    na nova página.
        //

        attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
        System.out.println(">>>>> sku: " + cerveja.getSku());
        return "redirect:/cervejas/novo";
        // O uso do redirect nesse ponto faz com que, se não houver erros, o sistema chame novamente o evento novo() e,
        //com isso,prepara novamente o ambiente para uma nova inserção.

        //Quando o browser faz um post, ele chama o método cadastrar, por não ter nenhum erro,
        //o browser encontra um return redirecionando para a chamada /cervejas/novo. Esse redirect poderia ser
        //repassado para qualquer página.

    }
*/

    // Método Refatorado com o ModelAndView
    @RequestMapping(value="/novo", method = RequestMethod.POST)
    public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            return novo(cerveja);
        }

        cadastroCervejaService.salvar(cerveja);
        attributes.addFlashAttribute("mensagem", "Operação realizada com sucesso!");

        return new ModelAndView("redirect:/cervejas/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(){
        ModelAndView mv = new ModelAndView("cerveja/PesquisaCerveja");
        mv.addObject("estilos", estilos.findAll());
        mv.addObject("sabores", Sabor.values());
        mv.addObject("origens", Origem.values());

        mv.addObject("cervejas", cervejas.findAll());

        return mv;
    }

}