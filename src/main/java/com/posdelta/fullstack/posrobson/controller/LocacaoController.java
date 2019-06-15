package com.posdelta.fullstack.posrobson.controller;

import java.math.BigDecimal;
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
import com.posdelta.fullstack.posrobson.model.Locacao;
import com.posdelta.fullstack.posrobson.model.Motorista;
import com.posdelta.fullstack.posrobson.repository.CarroRepository;
import com.posdelta.fullstack.posrobson.repository.LocacaoRepository;
import com.posdelta.fullstack.posrobson.repository.MotoristaRepository;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

    private static final String LOCACAO_CADASTRO = "locacaoCadastro";
    private static final String LOCACAO_LISTA = "locacaoLista";

    @Autowired
    private LocacaoRepository repository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @RequestMapping("/novo")
    public ModelAndView novo() {
        ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);
        modelAndView.addObject(new Locacao());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView salvar(@Valid Locacao locacao, Errors errors, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return new ModelAndView(LOCACAO_CADASTRO);
        }

        int dias = (int) ((locacao.getDataDevolucao().getTime() - locacao.getDataLocacao().getTime()) / 86400000);
        locacao.setValorTotal(locacao.getCarro().getValorDiaria().multiply(new BigDecimal(dias)));

        if (locacao.getId() == null) {
            repository.save(locacao);
            redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
        } else {
            repository.save(locacao);
            redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso!");
        }

        return new ModelAndView("redirect:/locacoes/novo");
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView(LOCACAO_LISTA);
        modelAndView.addObject("locacoes", repository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);

        modelAndView.addObject(repository
                .findById(id).orElseThrow(()
                        -> new EmptyResultDataAccessException(0)));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/locacoes");
        repository.deleteById(id);
        return modelAndView;
    }

    @ModelAttribute(name = "todosMotoristas")
    public List<Motorista> todosMotoristas(){
    	return motoristaRepository.findAll();
    }

    @ModelAttribute(name = "todosCarros")
    public List<Carro> todosCarros(){
        return carroRepository.findAll();
    }
}
