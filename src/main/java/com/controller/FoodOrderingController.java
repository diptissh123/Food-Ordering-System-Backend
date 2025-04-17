package com.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Repository.CustomerRepository;
import com.Repository.OrderRepository;
import com.model.Admin;
import com.model.Customer;
import com.model.FoodItem;
import com.model.Order;
import com.model.OrderItem;
import com.services.CustomerServices;
import com.services.FoodServices;
import com.services.OrderService;
import com.services.AdminService;

@RestController
@RequestMapping("/food-ordering")
@CrossOrigin("*")
public class FoodOrderingController {
	@Autowired 
	private CustomerRepository customerRepository;
	@Autowired 
	private OrderRepository orderRepository;
    @Autowired
    private CustomerServices custService;

    @Autowired
    private FoodServices foodService;
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private AdminService adminService;
    @PostMapping(value = "/food-items", consumes = "multipart/form-data")
    public ResponseEntity<String> addFoodItem(
            @RequestParam("foodName") String foodName,
            @RequestParam("foodPrice") double foodPrice,
            @RequestParam("foodType") String foodType,
            @RequestParam("foodDescription") String foodDescription,
            @RequestParam("image") MultipartFile image) {

        FoodItem foodItem = foodService.addFoodItem(foodName, foodPrice, foodType, foodDescription, image);
        if (foodItem != null) {
            return ResponseEntity.ok("Food item added successfully! ID: " + foodItem.getId());
        } else {
            return ResponseEntity.status(500).body("Error saving food item.");
        }
    }

    @GetMapping("/food-items")
    public ResponseEntity<List<FoodItem>> GetAllData() {
        return foodService.GetAllData();
    }

    @GetMapping("/foodType/{foodType}")
    public List<FoodItem> GetByFoodType(@PathVariable String foodType) { 
        return foodService.GetByFoodType(foodType); 
    }

    
    @PostMapping("/customer")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return custService.saveCustomer(customer);
    }

    @PostMapping("/Validate-customer")
    public Customer validateCustomer(@RequestBody Customer customer) {
        return custService.ValidateCustomer(customer);
    }

    @PostMapping("/Validate-admin")
    public Admin validateAdmin(@RequestBody Admin admin) {
        return adminService.validateAdmin(admin);
    }
    
	@GetMapping("/custId/{custId}")
	public ResponseEntity<Customer> GetCustomerById(@PathVariable Long custId){
		return custService.GetCustomerById(custId);
		
		
	}
	
	
	@PostMapping("/placeOrder")
	public String placeOrder(@RequestBody Order orderData) {
	    System.out.println("Received Order Data: " + orderData);

	    for (OrderItem item : orderData.getItems()) {
	        item.setOrder(orderData); // ✅ Set parent order reference

	        if (item.getImage() != null && !item.getImage().isEmpty()) {
	            try {
	                String base64Image = item.getImage();
	                if (base64Image.startsWith("data:image")) {
	                    base64Image = base64Image.substring(base64Image.indexOf(",") + 1);
	                }

	                byte[] imageBytes = Base64.getDecoder().decode(base64Image);
	                item.setImage(imageBytes); // ✅ Set imageBytes

	                System.out.println("Saving Item: " + item.getName() + " | Image Bytes Length: " + imageBytes.length);
	            } catch (IllegalArgumentException e) {
	                System.out.println("Error decoding Base64 image: " + e.getMessage());
	            }
	        } else {
	            System.out.println("⚠ No Image Found for Item: " + item.getName());
	        }
	    }

	    orderRepository.save(orderData); // ✅ Ensure the order is saved

	    return "Order placed successfully!";
	}


//    @PostMapping("/placeOrder")
//    public String placeOrder(@RequestBody Order orderData) {
//
//        // Find customer by custId
//        Customer customer = customerRepository.findById(orderData.getCustomer().getCustId())
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        // Assign the found customer to order
//        orderData.setCustomer(customer);
//
//        // Set order reference in items
//        for (OrderItem item : orderData.getItems()) {
//            item.setOrder(orderData);
//            if (item.getImage() != null && item.getImage().length > 0) {
//                try {
//                    // Convert Base64 to byte array
//                    String base64Image = new String(item.getImage());
//                    byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);
//                    item.setImage(imageBytes);  // ✅ Store as byte[] in DB
//                } catch (IllegalArgumentException e) {
//                   
//                }
//            }
//        }
//
//        // Save order
//        orderRepository.save(orderData);
//        System.out.println(orderData);
//
//        return "Order placed successfully!";
//    }

	 @GetMapping("/search")
	    public List<FoodItem> searchFood(@RequestParam String foodName) {
	        return foodService.searchFoodByName(foodName);
	    }


    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return custService.getAllCustomer();
    }
    
    @GetMapping("/orders/{custId}")
    public List<Order> getCustomerOrders(@PathVariable Long custId) {
        return orderService.getOrdersByCustomerId(custId);
    }
 // Get all orders (For Admin)
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get order by ID
    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Update order status (For Admin)
    @PutMapping("/orders/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status); // Call service method
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder); // Return updated order
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }

    // Delete an order (Optional)
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "Order deleted successfully.";
    }
    
}

