package hello.repository;

import hello.model.Company;
import hello.model.Material;
import hello.model.MaterialDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikolaj on 13.11.2016.
 */

@Component
public class Repository {

    private final List<Company> companies = new ArrayList<>();
    private final List<Material> materials = new ArrayList<>();
    private final List<MaterialDetails> materialDetails = new ArrayList<>();

    public List<Company> getCompanies() {
        return companies;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<MaterialDetails> getMaterialDetails() {
        return materialDetails;
    }

    public boolean containsCompanyByID(List<Company> list, int id) {
        for (Company company : list) {
            if (company.getCompanyID() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean containsMaterialByID(List<Material> list, int id) {
        for (Material material : list) {
            if (material.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean containsMaterialDetailByID(List<MaterialDetails> list, int id) {
        for (MaterialDetails materialDetails : list) {
            if (materialDetails.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public List<Material> filterMaterialsByID(List<Material> list, long id) {
        List<Material> foundedMaterial = new ArrayList<>();
        for (Material material : list) {
            if (material.getCompanyID() == id)
                foundedMaterial.add(material);
        }
        return foundedMaterial;
    }

    public List<MaterialDetails> filterMaterialDetailsByID(List<MaterialDetails> list, long id) {
        List<MaterialDetails> foundedMaterialDetails = new ArrayList<>();
        for (MaterialDetails materialDetails : list) {
            if (materialDetails.getId() == id)
                foundedMaterialDetails.add(materialDetails);
        }
        return foundedMaterialDetails;
    }

    public MaterialDetails getMaterialDetailByID(List<MaterialDetails> list, long id) {
        MaterialDetails foundedMaterialDetails = null;
        for (MaterialDetails materialDetails : list) {
            if (materialDetails.getId() == id)
                foundedMaterialDetails = materialDetails;
        }
        return foundedMaterialDetails;
    }
}
