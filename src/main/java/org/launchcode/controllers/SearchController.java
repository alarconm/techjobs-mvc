package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

//     Initial page load(GET)
    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

//     Page load with info(POST)
    @RequestMapping(value = "results")
        public String search(Model model, @RequestParam String searchType, String searchTerm) {

            model.addAttribute("columns", ListController.columnChoices);

        // TODO: 8/28/2017 Refactor into an if/else to remove redundancy. Count can come from template
//      Search all job fields by the value given
        if (searchType.equals("all")) {
            List<HashMap<String, String>> results = JobData.findByValue(searchTerm);
            model.addAttribute("results", results);
            model.addAttribute("count", results.size());
            return "search";
        }
//      Search specified field by field given and value given
            List<HashMap<String, String>> results = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("results", results);
            model.addAttribute("count", results.size());
            model.addAttribute("checked", searchType); //Keeps same field checked when displaying results
            return "search";
    }


}
