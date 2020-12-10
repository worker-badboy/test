package com.unluckyworker.appointment;

import com.unluckyworker.appointment.controller.WXController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class AppointmentApplicationTests {

    @Autowired
    private WXController wxController;

    @Test
    void contextLoads() {
        System.out.println(wxController.getAppid());
    }

}
