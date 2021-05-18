package nl.lotrac.bv.controller.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@ToString



public class
CreateOrderLine {
    private String orderName;

    private String itemName;

    private Integer quantity;

}
