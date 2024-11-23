package com.mobilebg.web;

import com.mobilebg.model.dto.AddOfferDTO;
import com.mobilebg.model.dto.UserRegisterDTO;
import com.mobilebg.service.BrandService;
import com.mobilebg.service.OfferService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
@Slf4j
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @ModelAttribute("addOfferModel")
    public void initUserModel(Model model) {
        model.addAttribute("addOfferModel", new AddOfferDTO());
    }

    @ModelAttribute("brands")
    public void initBrandModel(Model model) {
        model.addAttribute("brands", this.brandService.getAllBrands());
    }

    @GetMapping("/all")
    public String allOffers() {
        return "offers";
    }

    @GetMapping("/add")
    public String addOffer() {
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDTO addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);
            bindingResult.getAllErrors().stream().forEach(
                    error -> {
                        log.error("{}", error);
                    }

            );

            return "redirect:/offers/add";
        }

        this.offerService.addOffer(addOfferModel);

        return "redirect:/offers/all";
    }


}
