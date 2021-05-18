package nl.lotrac.bv.service;

import nl.lotrac.bv.controller.model.CreateOrderLine;
import nl.lotrac.bv.model.OrderLine;

import java.util.List;

public interface OrderLineService {

  OrderLine createNewOrderLine(CreateOrderLine orderLine);

   public abstract List<OrderLine> getAllOrderLines();

   public abstract OrderLine getOneOrderLineByID(Long id);



   //    In repository staat getOrderLineByItemName
   public abstract OrderLine getOneOrderLineByName(String itemname);


}
