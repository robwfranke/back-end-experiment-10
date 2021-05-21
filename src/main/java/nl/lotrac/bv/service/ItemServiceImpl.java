package nl.lotrac.bv.service;


import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.AddJob;
import nl.lotrac.bv.controller.model.CreateItem;
import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.exceptions.NameNotFoundException;
import nl.lotrac.bv.model.Job;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.model.Item;
import nl.lotrac.bv.repository.JobRepository;
import nl.lotrac.bv.repository.ItemRepository;
import nl.lotrac.bv.repository.OrderRepository;

import nl.lotrac.bv.utils.ExtractUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
   private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JobRepository jobRepository;


    @Override

    public Item addJob(AddJob addJob) {

        Item Item = itemRepository.getOrderLineByItemname(addJob.getOrderLineName());
        Job job = jobRepository.getJobByJobname(addJob.getJobName());



        if (Item.getJobsFromItem() == null) {
            List<Job> jobs = List.of(job);
            Item.setJobsFromItem(jobs);
        } else {
            Item.getJobsFromItem().add(job);
        }


        return itemRepository.save(Item);

    }


    public Item createNewItem(CreateItem createItem) {
        log.debug(createItem.toString());

        Order order = orderRepository.getOrderByOrdername(createItem.getOrderName());

//        check of deze order bestaat
        if (order == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);


        String username = ExtractUserName.ExtractUserNameFromJwt();
        log.debug("!!! username:  " + username);


        String ordername1 = order.getOrdername();
        log.debug("!!! ordername: " + ordername1);

// via de methode order.getUser kun je met getUsername() haal je de username op die bij deze order hoort.
//        check of deze order bij de user hoort
        if (!order.getUser().getUsername().equals(username))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

//        check of Item bij deze user bestaat

        if (itemRepository.getOrderLineByItemname(createItem.getItemName()) != null)
            throw new NameExistsException("Item exists");


//    order heeft de velden:
//      private long id;
//      private String ordername;
//      private String status;
//
//     hier is dus een copy uit de repositoryOrder gemaakt mbv de createOrdeLine, CreateItem createItem
//
//        log.debug("ordername:" + order.getOrdername());


        log.debug("order Id: " + String.valueOf(order.getId()));
        log.debug("ordername: " + order.getOrdername());
        log.debug("orderStatus: " + order.getStatus());

        Item newItem = new Item();


        newItem.setItemname(createItem.getItemName());
        newItem.setQuantity(createItem.getQuantity());
        log.debug("newOrderline ItemName: " + newItem.getItemname());
        log.debug("newOrderline Quantity: " + newItem.getQuantity());


//  wanneer je nu de order teruggeeft dmv de setter weet JPA via @ManyToOne dat hij dat in de foreign Key moet zetten
        newItem.setOrder(order);


// pas na save wordt een nieuwe id aangemaakt
        return itemRepository.save(newItem);
    }


    @Override
    public List<Item> getAllItems() {

        return itemRepository.findAll();
    }

    @Override
    public Item getOneItemByID(Long id) {

        Optional<Item> Item = itemRepository.findById(id);
        if (Item.isEmpty()) {
            throw new NameNotFoundException("Item does not exists");
        } else {
            return Item.get();
        }
    }


    //    In repository staat getOrderLineByItemName
    @Override
    public Item getOneItemByName(String itemname) {

        Item item = itemRepository.getOrderLineByItemname(itemname);
        if (item == null)
            throw new NameNotFoundException("item does not exists");

        return item;
    }


}
