package com.talipov;

import models.Store;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        store.createCar(500000, "kia-rio","B146AA");
        store.sellCar("kia-rio","Jhon Konner","+79126241898");
        store.save();
    }
}
