package com.wileyedge;

import com.wileyedge.config.AppConfig;
import com.wileyedge.controller.VendingMachineController;
import com.wileyedge.view.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        VendingMachineController vendingMachineController = context.getBean(VendingMachineController.class);

        vendingMachineController.run();

        ((AnnotationConfigApplicationContext) context).close();
    }
}
