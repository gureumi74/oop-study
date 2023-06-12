package com.example.oopstudy.opgame.model;

import javax.xml.namespace.QName;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBMain extends BaseDAO {
    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();
        personDAO.initPerson();
        System.out.println(personDAO.insertPerson("leo"));
        System.out.println(personDAO.insertPerson("yui"));
        List<Person> personList = personDAO.findAllPerson();
        System.out.println(personList.toString());
        System.out.println(personDAO.findByNamePerson("leo"));
    }
}
