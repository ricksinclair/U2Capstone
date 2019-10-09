package com.trilogy.cloudgamestorecustomerservice.dao;


import com.trilogy.cloudgamestorecustomerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoJdbcTemplateImplTest {


    @Autowired
    CustomerDao customerDao;




    @Before
    public void setUp() throws Exception{

        List<Customer> customers = customerDao.selectAllCustomers();

        customers.forEach(customer -> {
            customerDao.deleteCustomer(customer.getCustomerId());
        });
    }


    @Test

    public void insertSelectDeleteCustomer(){
        Customer testCustomer = new Customer();
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setStreet("1115 Broadway");
        testCustomer.setCity("New York");
        testCustomer.setZip("10010");
        testCustomer.setEmail("JohnDoe@gmail.com");
        testCustomer.setPhone("(212)222-2000");
        testCustomer = customerDao.insertCustomer(testCustomer);
        Customer fromRepo = customerDao.selectCustomer(testCustomer.getCustomerId());
        assertEquals(fromRepo, testCustomer);
        customerDao.deleteCustomer(testCustomer.getCustomerId());
        fromRepo = customerDao.selectCustomer(testCustomer.getCustomerId());
        assertNull(fromRepo);
    }


    @Test
    public void updateCustomer(){
        Customer testCustomer = new Customer();
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setStreet("1115 Broadway");
        testCustomer.setCity("New York");
        testCustomer.setZip("10010");
        testCustomer.setEmail("JohnDoe@gmail.com");
        testCustomer.setPhone("(212)222-2000");
        testCustomer = customerDao.insertCustomer(testCustomer);
        Customer fromRepo = customerDao.selectCustomer(testCustomer.getCustomerId());
        assertEquals( fromRepo, testCustomer);
        testCustomer.setEmail("JohnDoe@Outlook.com");
        customerDao.updateCustomer(testCustomer);
        fromRepo = customerDao.selectCustomer(testCustomer.getCustomerId());
        assertEquals(fromRepo, testCustomer);
    }

    @Test
    public void selectAllCustomers(){
        Customer testCustomer = new Customer();
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setStreet("1115 Broadway");
        testCustomer.setCity("New York");
        testCustomer.setZip("10010");
        testCustomer.setEmail("JohnDoe@gmail.com");
        testCustomer.setPhone("(212)222-2000");
        testCustomer = customerDao.insertCustomer(testCustomer);

        Customer testCustomer2 = new Customer();
        testCustomer2.setFirstName("Jane");
        testCustomer2.setLastName("Doe");
        testCustomer2.setStreet("1115 Broadway");
        testCustomer2.setCity("New York");
        testCustomer2.setZip("10010");
        testCustomer2.setEmail("JohnDoe@gmail.com");
        testCustomer2.setPhone("(212)222-2000");
        testCustomer2 = customerDao.insertCustomer(testCustomer2);

        List<Customer> customers = customerDao.selectAllCustomers();

        assertEquals(customers.size(), 2);
    }
}
