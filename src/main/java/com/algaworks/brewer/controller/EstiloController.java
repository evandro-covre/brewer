package com.algaworks.brewer.controller;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.service.CadastroEstiloService;
import com.algaworks.brewer.service.exception.NomeEstiloJaCadastradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by evandro on 08/09/16.
 */
@Controller
@RequestMapping("/estilos")
public class EstiloController {

    private static final Logger logger = LoggerFactory.getLogger(EstiloController.class);

    @Autowired
    private CadastroEstiloService cadastroEstiloService;

    @RequestMapping("/novo")
    public ModelAndView cadastro(Estilo estilo){
        System.out.println(">> EstiloController: cadastro");
        logger.debug("ModelAndView cadastro(Estilo estilo)");
        ModelAndView mv = new ModelAndView("estilo/CadastroEstilo");

        return mv;
    }

    /*
     * Exemplos de métodos utilizados com retorno não Ajax
     */
    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes){
        // primeiramente valida os erros relacionados ao modelo
        if (result.hasErrors()){
            return cadastro(estilo);
        }

        // Caso tenha validado os erros de modelo, entramos nos erros de regra de negócio
        try{
            cadastroEstiloService.salvar(estilo);
        } catch (NomeEstiloJaCadastradoException e){
            result.rejectValue("nome", e.getMessage(), e.getMessage());
            return cadastro(estilo);
        }

        attributes.addFlashAttribute("mensagem", "Operação realizada com sucesso!");
        return new ModelAndView("redirect:/estilos/novo");
    }

    /**
     *
     * @param estilo - indica ao Spring para pegar o corpo da Requisição (@RequestBody) e o converter para Estilo
     * @ResponseBody - indica que será devolvido ao chamador uma resposta de qqr tipo ResponseEntity<?>
     *
     * Exemplos de métodos utilizados com retorno em AJAX
     */
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result){
        System.out.println(">>> salvar:: estilo: "+estilo.getNome());

        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
        }

        /**
         * Todo o tratamento de excessão comentado foi substituido pelo ControllerAdviceExceptionHandler
         * que faz a interceptação de todas as excessões e retorna o formato padrão definido na excessão.
         * try{
         */

            estilo = cadastroEstiloService.salvar(estilo);
        /*} catch (NomeEstiloJaCadastradoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }*/

        return ResponseEntity.ok(estilo);
    }
}
