package hello.controller;

import hello.model.Material;
import hello.model.MaterialDetails;
import hello.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mkesy on 2016-12-07.
 */

@Controller
public class MaterialController {

    @Autowired
    private final Repository repository;

    public MaterialController(Repository repository) {
        this.repository = repository;
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

    @RequestMapping("details/{id}")
    public String returnMaterialDetailsModel(@PathVariable Long id, Model model) {
        model.addAttribute("details", repository.filterMaterialDetailsByID(repository.getMaterialDetails(), id));
        return "editDetails";
    }

}
