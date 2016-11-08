import java.util.*;
public class Order {
    CustomerOrder A= new CustomerOrder(1,2,001);
    CustomerOrder B= new CustomerOrder(2,3,002);
    CustomerOrder C= new CustomerOrder(3,4,003);
    ArrayList<CustomerOrder> L = new ArrayList<CustomerOrder>();
    CustomerOrder generated =null;
    Boolean inList = true;
   
    public Order(){
        L.add(A);
        L.add(B);
        L.add(C);
    }
    //given the basic properties of the order and create a new Customer Object
    public void Generate(int id,int quatity,int address){
        generated = new CustomerOrder(id,quatity,address);
    }
  
    //return true if the order is in list, else return false
    public Boolean GetStatus(int id){
        CustomerOrder matched = null;
        for (CustomerOrder A:L){
            if (A.id == id){
                return inList;
            }
        }
        return false;
        
    }
    /**
     * @param args the command line arguments
     */
    
}
//create a CustomerOrder
class CustomerOrder{
    int id;
    int quatity;
    int address;
    CustomerOrder(int id,int quatity,int address){
        this.id = id;
        this.quatity = quatity;
        this.address = address;
    }
}