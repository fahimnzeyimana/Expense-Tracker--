package com.studentapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExpenseServletTest {

    @Test
    public void testServletInstance() {
        ExpenseServlet servlet = new ExpenseServlet();
        assertNotNull("Servlet should be instantiated", servlet);
    }
}
