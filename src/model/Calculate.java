
package model;

public class Calculate {
    private double tax;
    private double subTotal;

    public double getTax() {
        return calTax(subTotal);
    }

    public double getTotal(){
        return subTotal + getTax();
    }
    
    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    private double calTax(double t){
        if(t < 10000.0){
            tax = 0.0;
        }else if(t >= 10000.0 && t <= 20000.0){
            tax = t * 0.05;
        } else if(t > 20000.0 && t <= 40000.0){
            tax = t * 0.1;
        } else if(t > 40000.0 && t <= 60000.0){
            tax = t * 0.15;
        }else if(t > 60000.0 && t <= 80000.0){
            tax = t * 0.2;
        }else if(t > 80000.0 && t <= 100000.0){
            tax = t * 0.25;
        } else if(t > 100000.0 && t <= 150000.0){
            tax = t * 0.3; 
        } else if(t > 150000.0 && t <= 200000.0){
            tax = t * 0.35;
        }else if(t > 200000.0){
            tax = t * 0.5;
        }
        return tax;
    }
}
