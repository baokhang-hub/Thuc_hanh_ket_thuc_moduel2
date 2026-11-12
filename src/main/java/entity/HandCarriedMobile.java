package entity;

public class HandCarriedMobile extends Mobiles{
    private String country;
    private String status;

    public HandCarriedMobile(String country, String status) {
        this.country = country;
        this.status = status;
    }

    public HandCarriedMobile(int id, String name, double price, int quantity, String manufacturer, String country, String status) {
        super(id, name, price, quantity, manufacturer);
        this.country = country;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + ", Ten dien thoai: " + getName()
                + ", Gia ban: " + getPrice()
                + ", So luong: " + getQuantity()
                + ", Nha san xuat: " + getManufacturer()
                + ", Quoc gia xach tay: " + this.country
                + ", Trang thai: " + this.status;
    }
}
