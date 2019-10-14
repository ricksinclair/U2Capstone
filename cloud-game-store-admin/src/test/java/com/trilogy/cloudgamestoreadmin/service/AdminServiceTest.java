package com.trilogy.cloudgamestoreadmin.service;


import com.trilogy.cloudgamestoreadmin.model.Customer;
import com.trilogy.cloudgamestoreadmin.model.Product;
import com.trilogy.cloudgamestoreadmin.util.feign.CustomerFeignClient;
import com.trilogy.cloudgamestoreadmin.util.feign.ProductFeignClient;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class AdminServiceTest {

    private CustomerFeignClient customerClient;

    private ProductFeignClient productClient;

    private void setUpProductClientMock(){
        productClient = mock(ProductFeignClient.class);
        Product testProductNotAdded = new Product();
        testProductNotAdded.setProductName("Call of Duty: Modern Warfare");
        testProductNotAdded.setProductDescription("A modern reboot of a classic franchise.");
        testProductNotAdded.setListPrice(new BigDecimal("59.99"));
        testProductNotAdded.setUnitCost(new BigDecimal("39.99"));

        Product testProductAdded = new Product();
        testProductAdded.setProductId(1);
        testProductAdded.setProductName("Call of Duty: Modern Warfare");
        testProductAdded.setProductDescription("A modern reboot of a classic franchise.");
        testProductAdded.setListPrice(new BigDecimal("59.99"));
        testProductAdded.setUnitCost(new BigDecimal("39.99"));


        Product testProduct2NotAdded = new Product();
        testProduct2NotAdded.setProductName("BattleField 1");
        testProduct2NotAdded.setProductDescription("A WWI FPS");
        testProduct2NotAdded.setListPrice(new BigDecimal("59.99"));
        testProduct2NotAdded.setUnitCost(new BigDecimal("39.99"));

        Product testProduct2Added = new Product();
        testProduct2Added.setProductId(2);
        testProduct2Added.setProductName("BattleField 1");
        testProduct2Added.setProductDescription("A WWI FPS");
        testProduct2Added.setListPrice(new BigDecimal("59.99"));
        testProduct2Added.setUnitCost(new BigDecimal("39.99"));

        List<Product> allProducts = new ArrayList<>();

        allProducts.add(testProductAdded);
        allProducts.add(testProduct2Added);

        doReturn(testProductAdded).when(productClient).addProduct(testProductNotAdded);
        doReturn(testProductAdded).when(productClient).getProduct(1);
        doReturn(testProduct2Added).when(productClient).addProduct(testProduct2NotAdded);
        doReturn(testProduct2Added).when(productClient).getProduct(2);
        doReturn(allProducts).when(productClient).getAllProducts();
        doReturn(allProducts).when(productClient).getProductsByInvoiceId(1);





    }

    private void setUpCustomerClientMock() {
        customerClient = mock(CustomerFeignClient.class);
        Customer testCustomerNotAdded = new Customer();
        testCustomerNotAdded.setFirstName("John");
        testCustomerNotAdded.setLastName("Doe");
        testCustomerNotAdded.setStreet("1115 Broadway");
        testCustomerNotAdded.setCity("New York");
        testCustomerNotAdded.setZip("10010");
        testCustomerNotAdded.setEmail("JohnDoe@gmail.com");
        testCustomerNotAdded.setPhone("(212)222-2000");

        Customer testCustomerAdded = new Customer();
        testCustomerAdded.setCustomerId(1);
        testCustomerAdded.setFirstName("John");
        testCustomerAdded.setLastName("Doe");
        testCustomerAdded.setStreet("1115 Broadway");
        testCustomerAdded.setCity("New York");
        testCustomerAdded.setZip("10010");
        testCustomerAdded.setEmail("JohnDoe@gmail.com");
        testCustomerAdded.setPhone("(212)222-2000");


        Customer testCustomer2NotAdded = new Customer();
        testCustomer2NotAdded.setFirstName("Jane");
        testCustomer2NotAdded.setLastName("Doe");
        testCustomer2NotAdded.setStreet("1115 Broadway");
        testCustomer2NotAdded.setCity("New York");
        testCustomer2NotAdded.setZip("10010");
        testCustomer2NotAdded.setEmail("JohnDoe@gmail.com");
        testCustomer2NotAdded.setPhone("(212)222-2000");

        Customer testCustomer2Added = new Customer();
        testCustomer2Added.setCustomerId(2);
        testCustomer2Added.setFirstName("Jane");
        testCustomer2Added.setLastName("Doe");
        testCustomer2Added.setStreet("1115 Broadway");
        testCustomer2Added.setCity("New York");
        testCustomer2Added.setZip("10010");
        testCustomer2Added.setEmail("JohnDoe@gmail.com");
        testCustomer2Added.setPhone("(212)222-2000");


        Customer testCustomer3NotAdded = new Customer();
        testCustomer3NotAdded.setFirstName("Johnny");
        testCustomer3NotAdded.setLastName("Quest");
        testCustomer3NotAdded.setStreet("1115 Broadway");
        testCustomer3NotAdded.setCity("New York");
        testCustomer3NotAdded.setZip("10010");
        testCustomer3NotAdded.setEmail("JohnnyQuest@cartoonnetwork.com");
        testCustomer3NotAdded.setPhone("(212)222-2000");


        Customer testCustomer3Added = new Customer();
        testCustomer3Added.setCustomerId(3);
        testCustomer3Added.setFirstName("Johnny");
        testCustomer3Added.setLastName("Quest");
        testCustomer3Added.setStreet("1115 Broadway");
        testCustomer3Added.setCity("New York");
        testCustomer3Added.setZip("10010");
        testCustomer3Added.setEmail("JohnnyQuest@cartoonnetwork.com");
        testCustomer3Added.setPhone("(212)222-2000");


        List<Customer> customerList = new ArrayList<>();
        customerList.add(testCustomerAdded);
        customerList.add(testCustomer2Added);
        customerList.add(testCustomer3Added);

        doReturn(testCustomerAdded).when(customerClient).addCustomer(testCustomerNotAdded);
        doReturn(testCustomerAdded).when(customerClient).getCustomer(1);
        doReturn(testCustomer2Added).when(customerClient).addCustomer(testCustomer2NotAdded);
        doReturn(testCustomer2Added).when(customerClient).getCustomer(2);
        doReturn(testCustomer3Added).when(customerClient).addCustomer(testCustomer3NotAdded);
        doReturn(testCustomer3Added).when(customerClient).getCustomer(3);
        doReturn(customerList).when(customerClient).getAllCustomers();


    }
}
