package hello.controller;

import hello.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mikolaj on 13.12.2016.
 */

@Controller
public class DataController {

    @Autowired
    private final Repository repository;

    public DataController(Repository repository) {
        this.repository = repository;
    }

    @RequestMapping("/cleanData/{id}")
    public String cleanData(@PathVariable Long id) {

        repository.getCompanies().clear();
        repository.getMaterialDetails().clear();
        repository.getMaterials().clear();

        return "redirect:/materialDetails/" + id;

    }
}
