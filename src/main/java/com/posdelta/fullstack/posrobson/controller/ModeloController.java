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

import com.posdelta.fullstack.posrobson.model.Fabricante;
import com.posdelta.fullstack.posrobson.model.Modelo;
import com.posdelta.fullstack.posrobson.repository.FabricanteRepository;
import com.posdelta.fullstack.posrobson.repository.ModeloRepository;
import com.posdelta.fullstack.posrobson.type.Categoria;

@Controller
@RequestMapping("/modelos")
public class ModeloController {

    private static final String MODELO_CADASTRO = "modeloCadastro";
    private static final String MODELO_LISTA = "modeloLista";

    @Autowired
    private ModeloRepository repository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @RequestMapping("/novo")
    public ModelAndView novo() {
        ModelAndView modelAndView = new ModelAndView(MODELO_CADASTRO);
        modelAndView.addObject(new Modelo());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView salvar(@Valid Modelo modelo, Errors errors, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return new ModelAndView(MODELO_CADASTRO);
        }

        if (modelo.getId() == null) {
            repository.save(modelo);
            redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
        } else {
            repository.save(modelo);
            redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso!");
        }

        return new ModelAndView("redirect:/modelos/novo");
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView(MODELO_LISTA);
        modelAndView.addObject("modelos", repository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(MODELO_CADASTRO);

        modelAndView.addObject(repository
                .findById(id).orElseThrow(()
                        -> new EmptyResultDataAccessException(0)));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/modelos");
        repository.deleteById(id);
        return modelAndView;
    }

    @ModelAttribute(name = "todasCategorias")
    public Categoria[] todasCategorias(){
        return Categoria.values();
    }

    @ModelAttribute(name = "todosFabricantes")
    public List<Fabricante> todosFabricantes(){
        return fabricanteRepository.findAll();
    }
}
