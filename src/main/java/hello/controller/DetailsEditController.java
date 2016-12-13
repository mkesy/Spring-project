package hello.controller;

import hello.model.MaterialDetails;
import hello.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mkesy on 2016-12-07.
 */

@Controller
public class DetailsEditController {

    @Autowired
    private final Repository repository;

    public DetailsEditController(Repository repository) {
        this.repository = repository;
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

    @ModelAttribute(value = "retrievedMaterialDetails")
    public MaterialDetails newMaterialDetails() {
        return new MaterialDetails();
    }
}
