package com.algaworks.brewer.controller;

import com.algaworks.brewer.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by evandro on 07/09/16.
 */
@Controller
public class UsuarioController {
    @RequestMapping("/usuarios/novo")
    public String cadastro(Usuario usuario){
        return "/usuario/CadastroUsuario";
    }

    @RequestMapping(value = "/usuarios/novo", method = RequestMethod.POST)
    public String salvar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes){
        if (result.hasErrors()){
            return cadastro(usuario);
        }

        attributes.addFlashAttribute("mensagem", "Operação realizada com sucesso!");
        return "redirect:/usuarios/novo";
    }
}
