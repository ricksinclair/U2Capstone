package com.trilogy.cloudgamestoreinvoiceservice.dao;

import com.trilogy.cloudgamestoreinvoiceservice.model.Invoice;
import com.trilogy.cloudgamestoreinvoiceservice.model.InvoiceItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoTest {

    @Autowired
    protected InvoiceItemDao invoiceItemDao;

    @Autowired
    protected InvoiceDao invoiceDao;

    @Before
    @After
    public void setUp() {
        //Cleans up test DB
        List<InvoiceItem> invoicesItems = invoiceItemDao.getAllInvoiceItems();
        invoicesItems.stream()
                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        List<Invoice> invoices = invoiceDao.getAllInvoices();
        invoices.stream()
                .forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));
    }

    @Test
    public void addGetDeleteInvoiceItem() {
        //Loads two invoices into database
        List<Invoice> invoices = helperInvoiceDataLoad();

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoices.get(0).getInvoiceId());
        invoiceItem.setInventoryId(2);
        invoiceItem.setQuantity(3);
        invoiceItem.setUnitPrice(BigDecimal.valueOf(4.99));

        //Adds invoiceItem to database and returns with Invoice Item ID
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        //Asserts that invoice item was added to database and test get all invoice items
        assertEquals(1, invoiceItemDao.getAllInvoiceItems().size());

        //Test get one invoice item from database
        InvoiceItem invoiceItemRetrieved = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        //Asserts that invoice retrieved is same as invoice saved previously
        assertEquals(invoiceItem, invoiceItemRetrieved);

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(invoices.get(1).getInvoiceId());
        invoiceItem1.setInventoryId(3);
        invoiceItem1.setQuantity(4);
        invoiceItem1.setUnitPrice(BigDecimal.valueOf(15.23));

        //Adds second invoice item to database
        invoiceItem1 = invoiceItemDao.addInvoiceItem(invoiceItem1);

        //Asserts second invoice item was added successfully
        assertEquals(2, invoiceItemDao.getAllInvoiceItems().size());

        //Deletes one invoiceItem
        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());

        //Asserts deletion was successful
        assertEquals(1, invoiceItemDao.getAllInvoiceItems().size());
    }

    @Test
    public void getAllInvoiceItems() {
        //Loads two invoices into database
        List<Invoice> invoices = helperInvoiceDataLoad();

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoices.get(0).getInvoiceId());
        invoiceItem.setInventoryId(2);
        invoiceItem.setQuantity(3);
        invoiceItem.setUnitPrice(BigDecimal.valueOf(4.99));

        //Adds invoiceItem to database and returns with Invoice Item ID
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(invoices.get(1).getInvoiceId());
        invoiceItem1.setInventoryId(3);
        invoiceItem1.setQuantity(4);
        invoiceItem1.setUnitPrice(BigDecimal.valueOf(15.23));

        //Adds second invoice item to database
        invoiceItem1 = invoiceItemDao.addInvoiceItem(invoiceItem1);

        //Asserts both invoice items were added successfully
        assertEquals(2, invoiceItemDao.getAllInvoiceItems().size());
    }

    @Test
    public void updateInvoiceItem() {
        //Loads two invoices into database
        List<Invoice> invoices = helperInvoiceDataLoad();

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoices.get(0).getInvoiceId());
        invoiceItem.setInventoryId(2);
        invoiceItem.setQuantity(3);
        invoiceItem.setUnitPrice(BigDecimal.valueOf(4.99));

        //Adds invoiceItem to database and returns with Invoice Item ID
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem updatedInvoiceItem = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());
        updatedInvoiceItem.setInvoiceId(invoices.get(1).getInvoiceId());
        updatedInvoiceItem.setInventoryId(64);
        updatedInvoiceItem.setQuantity(48);
        updatedInvoiceItem.setUnitPrice(BigDecimal.valueOf(56.78));

        //Updates invoice item
        invoiceItemDao.updateInvoiceItem(updatedInvoiceItem);

        //Fetches updated invoice item from database
        invoiceItem = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        //Asserts fetched invoice item matches updated invoice item
        assertEquals(updatedInvoiceItem, invoiceItem);
    }

    private List<Invoice> helperInvoiceDataLoad() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.now());

        //Add invoice to database and return invoice with ID
        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(2);
        invoice2.setPurchaseDate(LocalDate.of(2018, 10, 31));
        invoice2 = invoiceDao.addInvoice(invoice2);

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);
        invoices.add(invoice2);

        return invoices;

    }
}