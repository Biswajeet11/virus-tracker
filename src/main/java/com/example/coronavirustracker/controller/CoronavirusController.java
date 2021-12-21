package com.example.coronavirustracker.controller;

import com.example.coronavirustracker.model.Location;
import com.example.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CoronavirusController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String  home(Model model){
        List<Location> allStatuses =  coronaVirusDataService.getAllStatus();
        int totalReportedCases = allStatuses.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStatuses.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",allStatuses);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
