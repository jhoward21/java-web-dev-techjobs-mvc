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

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam(required = false) String searchTerm, @RequestParam String searchType){
        ArrayList<Job> jobs;
        if(searchType.toLowerCase().equals("all") || searchType.equals(" ")){
            jobs = JobData.findAll();
//            model.addAttribute("searchTerm", searchTerm);
//            return "results";

            model.addAttribute("searchType", searchType);
            model.addAttribute("columns", columnChoices);
            model.addAttribute("jobs", jobs);

        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//            model.addAttribute("column", columns);

            model.addAttribute("searchType", searchType);
            model.addAttribute("columns", columnChoices);
            model.addAttribute("jobs", jobs);
        }


        return "search";

    }
}
