package com.trilogy.cloudgamestorecustomerservice.service;


import com.trilogy.cloudgamestorecustomerservice.dao.CustomerDao;
import com.trilogy.cloudgamestorecustomerservice.dao.CustomerDaoJdbcTemplateImpl;
import com.trilogy.cloudgamestorecustomerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceLayerTest {
    CustomerDao customerDao;
    ServiceLayer service;



    @Before
    public void setUp() throws Exception{

        setUpCustomerDaoMock();
        service = new ServiceLayer(customerDao);

    }


    @Test
    public void addGetCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setStreet("1115 Broadway");
        customer.setCity("New York");
        customer.setZip("10010");
        customer.setEmail("JohnDoe@gmail.com");
        customer.setPhone("(212)222-2000");

        customer = service.addCustomer(customer);

        Customer fromService  = service.getCustomer(customer.getCustomerId());

        assertEquals(fromService, customer);

    }




    @Test
    public void getAllCustomers(){

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setStreet("1115 Broadway");
        customer.setCity("New York");
        customer.setZip("10010");
        customer.setEmail("JohnDoe@gmail.com");
        customer.setPhone("(212)222-2000");

        customer = service.addCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setStreet("1115 Broadway");
        customer2.setCity("New York");
        customer2.setZip("10010");
        customer2.setEmail("JohnDoe@gmail.com");
        customer2.setPhone("(212)222-2000");
        customer2 = service.addCustomer(customer2);


        Customer customer3 = new Customer();
        customer3.setFirstName("Johnny");
        customer3.setLastName("Quest");
        customer3.setStreet("1115 Broadway");
        customer3.setCity("New York");
        customer3.setZip("10010");
        customer3.setEmail("JohnnyQuest@cartoonnetwork.com");
        customer3.setPhone("(212)222-2000");
        customer3 = service.addCustomer(customer3);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer2);
        customers.add(customer3);

        List<Customer> customersFromService = service.getAllCustomers();
        assertEquals(customersFromService, customers);
    }


    private void setUpCustomerDaoMock() {
        customerDao = mock(CustomerDaoJdbcTemplateImpl.class);
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

        doReturn(testCustomerAdded).when(customerDao).insertCustomer(testCustomerNotAdded);
        doReturn(testCustomerAdded).when(customerDao).selectCustomer(1);
        doReturn(testCustomer2Added).when(customerDao).insertCustomer(testCustomer2NotAdded);
        doReturn(testCustomer2Added).when(customerDao).selectCustomer(2);
        doReturn(testCustomer3Added).when(customerDao).insertCustomer(testCustomer3NotAdded);
        doReturn(testCustomer3Added).when(customerDao).selectCustomer(3);
        doReturn(customerList).when(customerDao).selectAllCustomers();


    }


}
