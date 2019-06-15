package com.posdelta.fullstack.posrobson.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.posdelta.fullstack.posrobson.model.Carro;
import com.posdelta.fullstack.posrobson.model.Modelo;
import com.posdelta.fullstack.posrobson.service.CarroService;
import com.posdelta.fullstack.posrobson.service.ModeloService;

@Controller
@RequestMapping("/carros")
public class CarroController {

    private static final String CARRO_CADASTRO = "carroCadastro";
    private static final String CARRO_LISTA = "carroLista";

    @Autowired
    private CarroService carroService;

    @Autowired
    private ModeloService modeloService;

    @RequestMapping("/novo")
    public ModelAndView novo() {
        ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);
        modelAndView.addObject(new Carro());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView salvar(@Valid Carro carro, Errors errors, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return new ModelAndView(CARRO_CADASTRO);
        }

        if (carro.getId() == null) {
        	carroService.incluir(carro);
            redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
        } else {
        	carroService.alterar(carro);
            redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso!");
        }

        return new ModelAndView("redirect:/carros/novo");
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView(CARRO_LISTA);
        modelAndView.addObject("carros", carroService.listar());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);

        modelAndView.addObject(carroService.pesquisaPorId(id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/carros");
        carroService.excluir(id);
        return modelAndView;
    }

    @ModelAttribute(name = "todosModelos")
    public List<Modelo> todosModelos(){
        return modeloService.listar();
    }
}