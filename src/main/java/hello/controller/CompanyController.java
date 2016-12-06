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

    @RequestMapping("/material/{id}")
    public String returnMaterialsById(@PathVariable Long id, Model model) {
        final RestTemplate restTemplate = new RestTemplate();
        final Material[] materialsList = restTemplate.getForObject("http://193.142.112.220:8337/materialList?companyID=" + id, Material[].class);

        for (Material material : materialsList) {
            if (!repository.containsMaterialByID(repository.getMaterials(), material.getId()))
                repository.getMaterials().add(material);
        }

        model.addAttribute("material", repository.filterMaterialsByID(repository.getMaterials(), id));

        return "material";
    }

    @RequestMapping("materialDetails/{id}")
    public String returnMaterialDetailsById(@PathVariable Long id, Model model) {
        final RestTemplate restTemplate = new RestTemplate();
        final MaterialDetails materialDetails = restTemplate.getForObject("http://193.142.112.220:8337/materialDetails?ID=" + id, MaterialDetails.class);

        if (!repository.containsMaterialDetailByID(repository.getMaterialDetails(), materialDetails.getId()))
            repository.getMaterialDetails().add(materialDetails);

        model.addAttribute("materialDetails", repository.filterMaterialDetailsByID(repository.getMaterialDetails(), id));
        return "materialDetails";
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public String editDetails(@ModelAttribute("retrievedMaterialDetails") MaterialDetails retrievedMaterialDetails) {

        MaterialDetails materialDetails = repository.getMaterialDetailByID(repository.getMaterialDetails(), retrievedMaterialDetails.getId());
        materialDetails.setName(retrievedMaterialDetails.getName());
        materialDetails.setDescription(retrievedMaterialDetails.getDescription());
        materialDetails.setNotes(retrievedMaterialDetails.getNotes());
        materialDetails.setSupplier(retrievedMaterialDetails.getSupplier());
        materialDetails.setPrice(retrievedMaterialDetails.getPrice());
        materialDetails.setCurrency(retrievedMaterialDetails.getCurrency());

        return "redirect:/materialDetails/" + retrievedMaterialDetails.getId();
    }

    @RequestMapping("details/{id}")
    public String returnMaterialDetailsModel(@PathVariable Long id, Model model) {
        model.addAttribute("details", repository.filterMaterialDetailsByID(repository.getMaterialDetails(), id));
        return "editDetails";
    }

    @ModelAttribute(value = "retrievedMaterialDetails")
    public MaterialDetails newMaterialDetails() {
        return new MaterialDetails();
    }
}