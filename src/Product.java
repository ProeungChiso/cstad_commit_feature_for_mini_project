import java.time.LocalDate;
public class Product {
    private String id;
    private String productName;
    private Integer qty;
    private Double price;
    private LocalDate date;
    private String status;
    Product(){}
    Product(String id, String productName, Integer qty, Double price, LocalDate date, String status){
        this.id = id;
        this.productName = productName;
        this.qty = qty;
        this.price = price;
        this.date = date;
        this.status = status;
    }
    public String getId(){
        return id;
    }
    public String getProductName(){
        return productName;
    }
    public Integer getQty(){
        return qty;
    }
    public Double getPrice(){
        return price;
    }
    public LocalDate getDate(){return date;}
    public String getStatus(){return status;}
    public void setId(String id){
        this.id = id;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public void setQty(Integer qty){
        this.qty = qty;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public void setStatus(String status) {this.status = status;}
}
