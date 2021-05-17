package nl.lotrac.bv.service;

import nl.lotrac.bv.model.OrderLine;

import java.util.List;

public interface OrderLineService {

   String createNewOrderLine(OrderLine orderLine);

   public abstract List<OrderLine> getAllOrderLines();

   public abstract OrderLine getOneOrderLineByID(Long id);



   //    In repository staat getOrderLineByItemName
   public abstract OrderLine getOneOrderLineByName(String itemname);


}
