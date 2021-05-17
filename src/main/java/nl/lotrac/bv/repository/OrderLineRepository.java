package nl.lotrac.bv.repository;

import nl.lotrac.bv.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

   OrderLine getOrderLineByItemname(String itemname);

}
