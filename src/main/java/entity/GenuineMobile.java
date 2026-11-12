package entity;

public class GenuineMobile extends Mobiles{
    private int warrantyPeriod;
    private String warrantyScope;

    public GenuineMobile(int id, String name, double price, int quantity, String manufacturer, int warrantyPeriod, String warrantyScope) {
        super(id, name, price, quantity, manufacturer);
        this.warrantyPeriod = warrantyPeriod;
        this.warrantyScope = warrantyScope;
    }

    public GenuineMobile(int warrantyPeriod, String warrantyScope) {
        this.warrantyPeriod = warrantyPeriod;
        this.warrantyScope = warrantyScope;
    }

    public String getWarrantyScope() {
        return warrantyScope;
    }

    public void setWarrantyScope(String warrantyScope) {
        this.warrantyScope = warrantyScope;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + ", Ten dien thoai: " + getName()
                + ", Gia ban: " + getPrice()
                + ", So luong: " + getQuantity()
                + ", Nha san xuat: " + getManufacturer()
                + ", Thoi gian bao hanh: " + warrantyPeriod
                + ", Pham vi bao hanh: " + warrantyScope;
    }
}
