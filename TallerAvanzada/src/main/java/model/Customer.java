package model;

public class Customer {
    private int customerID;
    private String name;
    private String country;
    private String email;
    private String lastName;
    private String phone;
    private String registrationdate;

    // Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastName() { 
        return lastName; 
    }
    
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public void setPhone(String phone){
        this.phone= phone;
    }
    
    public String getRegistrationDate(){
        return registrationdate;
    }
    
    public void setRegistrationDate(String registrationdate){
        this.registrationdate= registrationdate;
    }
}
