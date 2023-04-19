package YTJ;

public class Item {

    public String getBusinessId() {
        return businessId;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getCompanyForm() {
        return companyForm;
    }

    public String businessId;
    public String name;
    public String registrationDate;
    public String companyForm;

    public Item() {
        this.businessId = "";
        this.name = "";
        this.registrationDate = "";
        this.companyForm = "";
    }
}

