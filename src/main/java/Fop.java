public class Fop {
    private String name;
    private String address;
    private String kved;
    private String kvedDescription;
    private String status;

    String getName() {
        return name;
    }

    void setName(String name) {

        if (this.name != null) {
            throw new RuntimeException("The parameter is not null. " + this.toString());
        }

        this.name = name;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {

        if (this.address != null) {
            throw new RuntimeException("The parameter is not null. " + this.toString());
        }

        this.address = address;
    }

    String getKved() {
        return kved;
    }

    void setKved(String kved) {

        if (this.kved != null) {
            throw new RuntimeException("The parameter is not null. " + this.toString());
        }

        this.kved = kved;
    }

    String getKvedDescription() {
        return kvedDescription;
    }

    void setKvedDescription(String kvedDescription) {

        if (this.kvedDescription != null) {
            throw new RuntimeException("The parameter is not null. " + this.toString());
        }

        this.kvedDescription = kvedDescription;
    }

    String getStatus() {
        return status;
    }

    void setStatus(String status) {

        if (this.status != null) {
            throw new RuntimeException("The parameter is not null. " + this.toString());
        }

        this.status = status;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\nAddress: " + getAddress() + "\nKved: " + getKved() + "\nDescription: " + getKvedDescription() + "\nStatus: " + getStatus();
    }

}
