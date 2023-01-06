package vttp2022.paf.assessment.eshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderStatusRepository;

@Controller
@RequestMapping(path="/api", produces= MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	@Autowired
	private CustomerRepository custRepo;
	//TODO: Task 3
	
	// Check if customer is valid
	@GetMapping(path="{cname}")
	public ResponseEntity<String> getCustomerName (@PathVariable String cname) {
		
		JsonObject result = null;
        try {
       
            List<Customer> customer = custRepo.findCustomerByName(cname);
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            objBuilder.add("cname", ((Customer) customer).toJSON());
            result = objBuilder.build();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"record not found\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

	// Dispatch Order
	@Autowired
	private OrderRepository orderRepo;


	@PostMapping(path="/server/dispatch/{orderId}")
	public ResponseEntity<String> saveOrder (@RequestBody Order o){
		JsonObject result = null;
		
		try {
			Order ord = orderRepo.saveOrder(o);
			JsonObjectBuilder builder = Json.createObjectBuilder();
			builder.add("order", ord.toJSON());
			builder.add("createdBy", "Daryl Goh Da Hui");
			result = builder.build();
		} catch (Exception e){
			return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\": \"record not found\"}");

		}
		return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

	}
	
	
	// Order Status
	@Autowired
	private OrderStatusRepository orderStatusRepo;

	@GetMapping(path="/api/order/{name}/status")
	public ResponseEntity<String> getOrderStatus (@PathVariable String name) {
		
		JsonObject result = null;
     
       
		List<OrderStatus> os = orderStatusRepo.getOrderStatus(name);
		JsonObjectBuilder objBuilder = Json.createObjectBuilder();
		objBuilder.add("name", (((OrderStatus) os).toJSON()));
		result = objBuilder.build();
	
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

}
