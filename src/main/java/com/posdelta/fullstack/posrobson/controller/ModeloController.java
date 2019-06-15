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

import com.posdelta.fullstack.posrobson.model.Fabricante;
import com.posdelta.fullstack.posrobson.model.Modelo;
import com.posdelta.fullstack.posrobson.service.FabricanteService;
import com.posdelta.fullstack.posrobson.service.ModeloService;
import com.posdelta.fullstack.posrobson.type.Categoria;

@Controller
@RequestMapping("/modelos")
public class ModeloController {

    private static final String MODELO_CADASTRO = "modeloCadastro";
    private static final String MODELO_LISTA = "modeloLista";

    @Autowired
    private ModeloService modeloService;

    @Autowired
    private FabricanteService fabricanteService;

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
        	modeloService.incluir(modelo);
            redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
        } else {
        	modeloService.alterar(modelo);
            redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso!");
        }

        return new ModelAndView("redirect:/modelos/novo");
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView(MODELO_LISTA);
        modelAndView.addObject("modelos", modeloService.listar());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(MODELO_CADASTRO);

        modelAndView.addObject(modeloService.pesquisaPorId(id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/modelos");
        modeloService.excluir(id);
        return modelAndView;
    }

    @ModelAttribute(name = "todasCategorias")
    public Categoria[] todasCategorias(){
        return Categoria.values();
    }

    @ModelAttribute(name = "todosFabricantes")
    public List<Fabricante> todosFabricantes(){
        return fabricanteService.listar();
    }
}
