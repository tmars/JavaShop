package com.talipov;

import models.CarNotFoundException;
import models.Store;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.DOMConfiguration;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);

    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }
    public static void main(String[] args) {
        Store store = new Store();
//        logger.trace("store created");
        logger.trace(new MailObject("t.mars@mail.ru", "test subject", "test content"));
        logger.trace(new MailObject("t.mars@mail.ru", "test subject", "test content"));
        store.createCar(500000, "kia-rio","B146AA");
//        try {
//            store.sellCar("kia-rio","Jhon Konner","+79126241898");
//        } catch (CarNotFoundException e) {
//            e.printStackTrace();
//        }
        store.save();
    }
}
