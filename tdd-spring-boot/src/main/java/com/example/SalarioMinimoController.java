package com.example;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SalarioMinimoController {
	
	@Autowired
	private SalarioMinimoService salarioMinimoService;

	@GetMapping("/salarios")
	@ResponseBody
	public String index() {
		return "index";
	}
	
	@GetMapping("/salarios/novo")
	public String save(Model model, @ModelAttribute("salarioMinimo") SalarioMinimo salarioMinimo) {
		return "save";
	}
	
	@PostMapping("/salarios")
	public String save(@Valid @ModelAttribute("salarioMinimo") SalarioMinimo salarioMinimo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		try {
			salarioMinimoService.save(salarioMinimo);
			redirectAttributes.addFlashAttribute("sucesso", "Salário salvo com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "Problema ao salvar salário.");
		}
		return "redirect:/salarios/novo";
	}
	
	@PutMapping("/salarios/{salario_id}/editar")
	public String save(Model model, @PathVariable("salario_id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				SalarioMinimo salarioMinimo = (SalarioMinimo) salarioMinimoService.findOne(id);
				salarioMinimoService.save(salarioMinimo);
				redirectAttributes.addFlashAttribute("sucesso", "Salário Editado com sucesso.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "Problema ao editar salário.");
		}
		return "redirect:/salarios";
	}
	
}
