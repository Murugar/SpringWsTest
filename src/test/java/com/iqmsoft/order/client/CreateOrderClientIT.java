package com.iqmsoft.order.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.iqmsoft.types.order.CustomerType;
import com.iqmsoft.types.order.LineItemType;
import com.iqmsoft.types.order.LineItemsType;
import com.iqmsoft.types.order.ObjectFactory;
import com.iqmsoft.types.order.ProductType;
import com.iqmsoft.client.CreateOrderClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CreateOrderClientIT {

    @Autowired
    private CreateOrderClient createOrderClient;

    private CustomerType customer;
    private LineItemsType lineItems;

    @Before
    public void setUp() throws Exception {
        ObjectFactory factory = new ObjectFactory();

        customer = factory.createCustomerType();
        customer.setFirstName("Test1");
        customer.setLastName("Test2");

        ProductType product1 = factory.createProductType();
        product1.setId("1");
        
        ProductType product2 = factory.createProductType();
        product1.setId("2");
        
        ProductType product3 = factory.createProductType();
        product1.setId("3");

        LineItemType lineItem1 = factory.createLineItemType();
        lineItem1.setProduct(product1);
        lineItem1.setQuantity(BigInteger.valueOf(2));
        
        LineItemType lineItem2 = factory.createLineItemType();
        lineItem1.setProduct(product2);
        lineItem1.setQuantity(BigInteger.valueOf(10));
        
        LineItemType lineItem3 = factory.createLineItemType();
        lineItem1.setProduct(product3);
        lineItem1.setQuantity(BigInteger.valueOf(100));

        lineItems = factory.createLineItemsType();
        lineItems.getLineItem().add(lineItem1);
        lineItems.getLineItem().add(lineItem2);
        lineItems.getLineItem().add(lineItem3);
    }

    @Test
    public void testCreateOrder() {
        assertThat(createOrderClient.createOrder(customer, lineItems)
                .getConfirmationId()).isEqualTo("test test");
    }
}
