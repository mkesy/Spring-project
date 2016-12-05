package hello.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mikolaj on 30.10.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company implements Serializable {

    private int companyID;
    private String companyName;

    public Company() {

    }

    @Autowired
    public Company(int companyID, String companyName) {
        this.companyID = companyID;
        this.companyName = companyName;
    }

    public void setID(int companyID) {
        this.companyID = companyID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Company id=" + companyID + ", Company Name =" + companyName;
    }


}
