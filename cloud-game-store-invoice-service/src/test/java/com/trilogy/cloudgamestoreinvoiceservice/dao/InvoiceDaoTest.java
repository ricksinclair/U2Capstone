package com.trilogy.cloudgamestoreinvoiceservice.dao;

import com.trilogy.cloudgamestoreinvoiceservice.model.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    protected InvoiceDao invoiceDao;

    @Before
    @After
    public void setUp() {
        //Cleans up test DB
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        invoices.stream()
                .forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));
    }

    @Test
    public void addGetDeleteInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.now());

        //Add invoice to database and return invoice with ID
        invoice = invoiceDao.addInvoice(invoice);

        //Asserts that getAllInvoices works and that invoice was created
        assertEquals(1, invoiceDao.getAllInvoices().size());

        //Gets one invoice (previously added to DB) and assigns to invoice1 - tests the GET
        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        //Asserts that invoice fetched is same as previously added to database
        assertEquals(invoice, invoice1);

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(2);
        invoice2.setPurchaseDate(LocalDate.of(2018, 10, 31));
        invoice2 = invoiceDao.addInvoice(invoice2);

        //Asserts second invoice is added to DB
        assertEquals(2, invoiceDao.getAllInvoices().size());

        //Deletes one of the invoices previously added
        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        //Asserts deletion has worked
        assertEquals(1, invoiceDao.getAllInvoices().size());
    }

    @Test
    public void getAllInvoicesAndByCustomerId() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.now());

        //Add invoice to database and return invoice with ID
        invoice = invoiceDao.addInvoice(invoice);

        //Add second invoice
        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(2);
        invoice2.setPurchaseDate(LocalDate.of(2018, 10, 31));
        invoice2 = invoiceDao.addInvoice(invoice2);

        //Asserts second invoice is added to DB and getAll is working properly
        assertEquals(2, invoiceDao.getAllInvoices().size());

        //Asserts get by customerID is working
        assertEquals(1, invoiceDao.getAllInvoiceByCustomerId(1).size());
        assertEquals(1, invoiceDao.getAllInvoiceByCustomerId(2).size());
    }

    @Test
    public void updateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.now());

        //Add invoice to database and return invoice with ID
        invoice = invoiceDao.addInvoice(invoice);

        //Gets one invoice (previously added to DB) and assigns to invoiceUPdate - tests the GET
        Invoice invoiceUpdate = invoiceDao.getInvoice(invoice.getInvoiceId());

        //Update fields
        invoiceUpdate.setCustomerId(2);
        invoiceUpdate.setPurchaseDate(LocalDate.of(2018, 10, 31));
        invoiceDao.updateInvoice(invoiceUpdate);

        //Set invoice with updated data from database
        invoice = invoiceDao.getInvoice(invoice.getInvoiceId());

        //Asserts update was successful
        assertEquals(invoiceUpdate, invoice);

    }
}