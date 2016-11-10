package com.algaworks.brewer.controller;

/**
 * Created by evandro on 07/09/16.
 */

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.model.TipoPessoa;
import com.algaworks.brewer.repository.Estados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private Estados estados;

    @RequestMapping("/novo") // URL de acesso a funcionalidade
    public ModelAndView cadastro(Cliente cliente){
        ModelAndView mv = new ModelAndView("cliente/CadastroCliente"); // Arquivo Fisico da funcionalidade
        mv.addObject("estados", estados.findAll());
        mv.addObject("tiposPessoa", TipoPessoa.values());

        return mv;
    }

    @RequestMapping(value="/novo", method = RequestMethod.POST)
    public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return cadastro(cliente);
        }

        attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
        return new ModelAndView("redirect:/clientes/novo");
    }
}
