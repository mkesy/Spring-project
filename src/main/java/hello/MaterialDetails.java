package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mikolaj on 13.11.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialDetails {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("supplier")
    private String supplier;
    @JsonProperty("price")
    private double price;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("ID")
    private int id;


    public MaterialDetails() {

    }

    @Autowired
    public MaterialDetails(String name, String description, String notes, String supplier, double price, String currency, int id) {
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.supplier = supplier;
        this.price = price;
        this.currency = currency;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
