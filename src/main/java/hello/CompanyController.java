package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class CompanyController {

    private final Repository repository = Repository.getInstance();

    @RequestMapping("/companies")
    public String returnCompanies(Model model) {
        final RestTemplate restTemplate = new RestTemplate();
        final Company[] companiesList = restTemplate.getForObject("http://193.142.112.220:8337/companyList", Company[].class);

        for (Company company : companiesList) {
            if (!Repository.containsCID(repository.getCompanies(), company.getCompanyID())) {
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
            if (!Repository.containsMID(repository.getMaterials(), material.getId()))
                repository.getMaterials().add(material);
        }

        model.addAttribute("material", Repository.findMID(repository.getMaterials(), id));

        return "material";
    }

    @RequestMapping("materialDetails/{id}")
    public String returnMaterialDetailsById(@PathVariable Long id, Model model) {
        final RestTemplate restTemplate = new RestTemplate();
        final MaterialDetails materialDetails = restTemplate.getForObject("http://193.142.112.220:8337/materialDetails?ID=" + id, MaterialDetails.class);

        if (!Repository.containsMDID(repository.getMaterialDetails(), materialDetails.getId()))
            repository.getMaterialDetails().add(materialDetails);

        model.addAttribute("materialDetails", repository.findMDID(repository.getMaterialDetails(), id));
        return "materialDetails";
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public String editDetails(@RequestParam String name, @RequestParam String description, @RequestParam String notes,
                              @RequestParam String supplier, @RequestParam double price, @RequestParam String currency, @RequestParam int id) {

        MaterialDetails materialDetails = repository.getMDbyID(repository.getMaterialDetails(), id);
        materialDetails.setName(name);
        materialDetails.setDescription(description);
        materialDetails.setNotes(notes);
        materialDetails.setSupplier(supplier);
        materialDetails.setPrice(price);
        materialDetails.setCurrency(currency);

        return "redirect:/materialDetails/" + id;
    }

    @RequestMapping("details/{id}")
    public String returnMaterialDetailsModel(@PathVariable Long id, Model model) {
        model.addAttribute("details", repository.findMDID(repository.getMaterialDetails(), id));
        return "editDetails";
    }

}