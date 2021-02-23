package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Locale;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.javawebdevtechjobsmvc.models.JobData.findAll;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("/search/results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm, @RequestParam String columns){
        ArrayList<Job> jobs;
        if(searchTerm.toLowerCase().equals("all") || searchTerm.equals(" ")){
            jobs = JobData.findAll();
            model.addAttribute("searchTerm", searchTerm);
            return "results";

        } else {
            jobs = JobData.findByColumnAndValue(columns, searchTerm);
            model.addAttribute("column", columns);

        }

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search";

    }
}
