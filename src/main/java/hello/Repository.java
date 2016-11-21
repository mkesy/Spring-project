package hello;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikolaj on 13.11.2016.
 */
public class Repository {

    private static Repository instance = null;
    private final List<Company> companies = new ArrayList<>();
    private final List<Material> materials = new ArrayList<>();
    private final List<MaterialDetails> materialDetails = new ArrayList<>();

    protected Repository() {

    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }


    public List<Company> getCompanies() {
        return companies;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<MaterialDetails> getMaterialDetails() {
        return materialDetails;
    }

    public static boolean containsCID(List<Company> list, int id) {
        for (Company company : list) {
            if (company.getCompanyID() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsMID(List<Material> list, int id) {
        for (Material material : list) {
            if (material.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsMDID(List<MaterialDetails> list, int id) {
        for (MaterialDetails materialDetails : list) {
            if (materialDetails.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static List<Material> findMID(List<Material> list, long id) {
        List<Material> foundedMaterial = new ArrayList<>();
        for (Material material : list) {
            if (material.getCompanyID() == id)
                foundedMaterial.add(material);
        }
        return foundedMaterial;
    }

    public static List<MaterialDetails> findMDID(List<MaterialDetails> list, long id) {
        List<MaterialDetails> foundedMaterialDetails = new ArrayList<>();
        for (MaterialDetails materialDetails : list) {
            if (materialDetails.getId() == id)
                foundedMaterialDetails.add(materialDetails);
        }
        return foundedMaterialDetails;
    }

    public static MaterialDetails getMDbyID(List<MaterialDetails> list, long id) {
        MaterialDetails foundedMaterialDetails = null;
        for (MaterialDetails materialDetails : list) {
            if (materialDetails.getId() == id)
                foundedMaterialDetails = materialDetails;
        }
        return foundedMaterialDetails;
    }
}
