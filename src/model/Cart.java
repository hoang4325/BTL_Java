
package model;

public class Cart {
    private int id;
    private int pid;
    private String pName;
    private int qty;
    private double price;
    private double total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cart(int id, int pid, String pName, int qty, double price, double total) {
        this.id = id;
        this.pid = pid;
        this.pName = pName;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public Cart() {
    }
}
