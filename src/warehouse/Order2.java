package warehouse;
import java.util.*;
//*@author Rui Zhao
//create a Order class to intiate the order from customer and return the list of initial orders
public class Order {
//create a customer object and a list to store orders
    int id;
    CustomerOrder[] item;
    ArrayList<CustomerOrder> L = new ArrayList<CustomerOrder>();
   
    public Order(int id){
        this.id = id;
    }
    //check if the order is in stock and then add it to the list
    public void Generate(CustomerOrder item){
        this.item = item;
        if(inStock){
            L.add(item);
        }
    }
  
    //return true if the order is in list, else return false
    public int GetID(){
        return id;
        
    }
    public GetOrderList(){
        return L;
        
    }
    /**
     * @param args the command line arguments
     */
    
}
//create a CustomerOrder
class CustomerOrder extands Item{
    //create basic identity of the order
    int id;
    String name;
    double price;
    int weight;
    Boolean inStock = false;
    CustomerOrder(int id,String name,double price,int weight){
        //create a Item object
        Item A = new Item(id,name,price,weight);
        //use the method in Item to check how many in stock
        if (getNumStocked(A)>0){
            instock = true;
        }
      
    }
}