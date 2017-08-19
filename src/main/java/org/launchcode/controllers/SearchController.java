package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
        public String search(Model model, @RequestParam String searchType, String searchTerm) {

            ArrayList<HashMap<String,String>> results;
            model.addAttribute("columns", ListController.columnChoices);

            results = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("results", results);
            return "search";

    }

    // TODO #1 - Create handler to process search request and display results

}
