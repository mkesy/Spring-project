package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikolaj on 07.11.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Material {

    @JsonProperty("name")
    private String name;
    @JsonProperty("ID")
    private int id;
    @JsonProperty("companyID")
    private int companyID;


    public Material() {

    }

    @Autowired
    public Material(String name, int id, int companyID) {
        this.name = name;
        this.id = id;
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }


}
