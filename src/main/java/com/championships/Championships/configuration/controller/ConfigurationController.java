package com.championships.Championships.configuration.controller;

import com.championships.Championships.configuration.dto.Configuration;
import com.championships.Championships.configuration.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ConfigurationController {

    private ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationService configService) {
        this.configurationService = configService;
    }
    @GetMapping("/allConfigPage")
    public String displayAllConfigurations(Model model) {
        setupModel(model);
        return "allConfigPage";
    }

    @PostMapping("/addConfigAction")
    public RedirectView addNewConfig(@ModelAttribute("newConfig") Configuration config, Model model) {
        configurationService.saveAddon(config);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allConfigPage");
        return redirectView;
    }
    @DeleteMapping("/deleteAddonAction/{deleteAddonId}")
    public RedirectView deleteAddon(@PathVariable String deleteAddonId, Model model) {
        configurationService.deleteById(deleteAddonId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allConfigPage");
        return redirectView;
    }

    private void setupModel(Model model) {
        List<Configuration> allConfig = configurationService.getAllConfigurations();
        model.addAttribute("allConfig", allConfig);
        model.addAttribute("newConfig", new Configuration());
        model.addAttribute("deleteConfig", "");
    }
}
