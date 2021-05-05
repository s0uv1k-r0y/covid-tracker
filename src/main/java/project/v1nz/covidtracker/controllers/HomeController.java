package project.v1nz.covidtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.v1nz.covidtracker.models.LocationStat;
import project.v1nz.covidtracker.services.DeathDataService;
import project.v1nz.covidtracker.services.InfectedDataService;
import project.v1nz.covidtracker.services.RecoveredDataService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    InfectedDataService infectedDataService;

    @Autowired
    DeathDataService deathDataService;

    @Autowired
    RecoveredDataService recoveredDataService;

    @GetMapping("/")
    public String home(Model theModel){
        List<LocationStat> allInfectedStats = infectedDataService.getAllInfectedStats();
        int totalInfectedCases = allInfectedStats.stream().mapToInt(stat -> (int) stat.getLatestTotalInfectedCases()).sum();
        int totalNewInfectedCases = allInfectedStats.stream().mapToInt(stat -> stat.getInfectedDiffFromPrevDay()).sum();
        theModel.addAttribute("totalInfectedCases", totalInfectedCases);
        theModel.addAttribute("totalNewInfectedCases", totalNewInfectedCases);


        List<LocationStat> allDeathStats = deathDataService.getAllDeathStats();
        int totalDeathCases = allDeathStats.stream().mapToInt(stat -> (int) stat.getLatestTotalDeathCases()).sum();
        int totalNewDeathCases = allDeathStats.stream().mapToInt(stat -> stat.getDeathDiffFromPrevDay()).sum();
        theModel.addAttribute("totalDeathCases", totalDeathCases);
        theModel.addAttribute("totalNewDeathCases", totalNewDeathCases);


        List<LocationStat> allRecoveredStats = recoveredDataService.getAllRecoveredStats();
        int totalRecoveredCases = allRecoveredStats.stream().mapToInt(stat -> (int) stat.getLatestTotalRecoveredCases()).sum();
        int totalNewRecoveredCases = allRecoveredStats.stream().mapToInt(stat -> stat.getRecoveredDiffFromPrevDay()).sum();
        theModel.addAttribute("totalRecoveredCases", totalRecoveredCases);
        theModel.addAttribute("totalNewRecoveredCases", totalNewRecoveredCases);


        int activeCases = totalInfectedCases - totalDeathCases - totalRecoveredCases;
        theModel.addAttribute("activeCases", activeCases);


        double deaths = totalDeathCases;
        double cases = totalInfectedCases;
        double caseFatality = deaths / cases * 100;
        theModel.addAttribute("caseFatality", caseFatality);

        return "home";
    }

    @GetMapping("/infected")
    public String infectedStats(Model theModel){
        List<LocationStat> allInfectedStats = infectedDataService.getAllInfectedStats();
        int totalInfectedCases = allInfectedStats.stream().mapToInt(stat -> (int) stat.getLatestTotalInfectedCases()).sum();
        int totalNewInfectedCases = allInfectedStats.stream().mapToInt(stat -> stat.getInfectedDiffFromPrevDay()).sum();
        theModel.addAttribute("locationStat", allInfectedStats);
        theModel.addAttribute("totalInfectedCases", totalInfectedCases);
        theModel.addAttribute("totalNewInfectedCases", totalNewInfectedCases);

        return "infected";
    }

    @GetMapping("/death")
    public String deathStats(Model theModel){

        List<LocationStat> allDeathStats = deathDataService.getAllDeathStats();
        int totalDeathCases = allDeathStats.stream().mapToInt(stat -> (int) stat.getLatestTotalDeathCases()).sum();
        int totalNewDeathCases = allDeathStats.stream().mapToInt(stat -> stat.getDeathDiffFromPrevDay()).sum();
        theModel.addAttribute("locationStat", allDeathStats);
        theModel.addAttribute("totalDeathCases", totalDeathCases);
        theModel.addAttribute("totalNewDeathCases", totalNewDeathCases);

        return "death";
    }

    @GetMapping("/recovered")
    public String recoveredStats(Model theModel){

        List<LocationStat> allRecoveredStats = recoveredDataService.getAllRecoveredStats();
        int totalRecoveredCases = allRecoveredStats.stream().mapToInt(stat -> (int) stat.getLatestTotalRecoveredCases()).sum();
        int totalNewRecoveredCases = allRecoveredStats.stream().mapToInt(stat -> stat.getRecoveredDiffFromPrevDay()).sum();
        theModel.addAttribute("locationStat", allRecoveredStats);
        theModel.addAttribute("totalRecoveredCases", totalRecoveredCases);
        theModel.addAttribute("totalNewRecoveredCases", totalNewRecoveredCases);

        return "recovered";
    }
}
