package com.posdelta.fullstack.posrobson.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.posdelta.fullstack.posrobson.repository.CarroRepository;
import com.posdelta.fullstack.posrobson.repository.ModeloRepository;

@Controller
@RequestMapping("/carros")
public class CarroController {

    private static final String CARRO_CADASTRO = "carroCadastro";
    private static final String CARRO_LISTA = "carroLista";

    @Autowired
    private CarroRepository repository;

    @Autowired
    private ModeloRepository modeloRepository;

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
            repository.save(carro);
            redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
        } else {
            repository.save(carro);
            redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso!");
        }

        return new ModelAndView("redirect:/carros/novo");
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView(CARRO_LISTA);
        modelAndView.addObject("carros", repository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);

        modelAndView.addObject(repository
                .findById(id).orElseThrow(()
                        -> new EmptyResultDataAccessException(0)));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/carros");
        repository.deleteById(id);
        return modelAndView;
    }

    @ModelAttribute(name = "todosModelos")
    public List<Modelo> todosModelos(){
        return modeloRepository.findAll();
    }
}