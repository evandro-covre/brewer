package com.algaworks.brewer.controller;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.Cidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by evandro on 08/09/16.
 */
@Controller
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private Cidades cidades;

    @RequestMapping("/novo")
    public ModelAndView cadastro(Cidade cidade){
        ModelAndView mv = new ModelAndView("/cidade/CadastroCidade");

        return mv;
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            return cadastro(cidade);
        }

        attributes.addFlashAttribute("mensagem", "Operação realizada com sucesso!");
        return new ModelAndView("redirect:/cidades/novo");
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Cidade> pesquisarPorCodigoEstado(
            @RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {	}
        return cidades.findByEstadoId(codigoEstado);
    }

}
