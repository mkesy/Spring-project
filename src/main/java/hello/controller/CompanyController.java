package hello.controller;

import hello.model.Company;
import hello.model.Material;
import hello.model.MaterialDetails;
import hello.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class CompanyController {

    @Autowired
    private final Repository repository;

    public CompanyController(Repository repository) {
        this.repository = repository;
    }

    @RequestMapping("/companies")
    public String returnCompanies(Model model) {
        final RestTemplate restTemplate = new RestTemplate();
        final Company[] companiesList = restTemplate.getForObject("http://193.142.112.220:8337/companyList", Company[].class);

        for (Company company : companiesList) {
            if (!repository.containsCompanyByID(repository.getCompanies(), company.getCompanyID())) {
                repository.getCompanies().add(company);
            }
        }

        model.addAttribute("companies", repository.getCompanies());

        return "companies";
    }
}