package com.servicelib;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class UserWebServiceTester {

    private Client client;
    private String REST_SERVICE_URL = "http://localhost:8080/BlooDon/rest/UserService/users";
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";

    public UserWebServiceTester() {
        this.init();
    }

    void init() {
        this.client = ClientBuilder.newClient();
    }

    public static void main(String[] args) {
        UserWebServiceTester tester = new UserWebServiceTester();
        //initialize the tester
        tester.init();

        tester.testGetAllUsers();
     //   tester.testGetUser();
     //   tester.testUpdateUser();
       // boolean b =tester.testAddUser("u1", "b", "c", "d", "aa", "b", "c", "d");
       // System.out.println(b);
        //  tester.testDeleteUser();
    }
    //Test: Get list of all users
    //Test: Check if list is not empty

    private void testGetAllUsers() {
        GenericType<List<User>> list = new GenericType<List<User>>() {
        };
        List<User> users = client
                .target(REST_SERVICE_URL)
                .request(MediaType.APPLICATION_XML)
                .get(list);
        String result = PASS;
        if (users.isEmpty()) {
            result = FAIL;
        }
        System.out.println("Test case name: testGetAllUsers, Result: " + result);
    }
    //Test: Get User of id 1
    //Test: Check if user is same as sample user

    private void testGetUser() {
        User sampleUser = new User();
        sampleUser.setId("1");

        User user = client
                .target(REST_SERVICE_URL)
                .path("/{userid}")
                .resolveTemplate("userid", 1)
                .request(MediaType.APPLICATION_XML)
                .get(User.class);
        String result = FAIL;
        if (sampleUser != null && sampleUser.getId() == user.getId()) {
            result = PASS;
        }
        System.out.println("Test case name: testGetUser, Result: " + result);
    }
    //Test: Update User of id 1
    //Test: Check if result is success XML.

    private boolean testUpdateUser() {
        Form form = new Form();
        form.param("id", "u33");
        form.param("adress", "192/13A,maharagama");
        form.param("phone", "00000000000");
        form.param("email", "aaaaaaaaa");

        String callResult = client
                .target(REST_SERVICE_URL)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(form,
                                MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                        String.class);
        boolean result = true;
        if (!SUCCESS_RESULT.equals(callResult)) {
            result = false;
        }
        return result;
        // System.out.println("Test case name: testUpdateUser, Result: " + result);
    }
    //Test: Add User of id 2
    //Test: Check if result is success XML.

    public boolean testAddUser(String id, String name, String lname, String dob, String adress, String phone, String email, String bloodgroup) {
        Form form = new Form();
        form.param("id", id);
        form.param("name", name);
        form.param("lname", lname);
        form.param("dob", dob);
        form.param("adress", adress);
        form.param("phone", phone);
        form.param("email", email);
        form.param("bloodgroup", bloodgroup);

        String callResult = client
                .target(REST_SERVICE_URL)
                .request(MediaType.APPLICATION_XML)
                .put(Entity.entity(form,
                                MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                        String.class);

        boolean result = true;
        if (!SUCCESS_RESULT.equals(callResult)) {
            result = false;
        }
        return result;
        // System.out.println("Test case name: testAddUser, Result: " + result);
    }
    //Test: Delete User of id 2
    //Test: Check if result is success XML.

    private void testDeleteUser() {
        String callResult = client
                .target(REST_SERVICE_URL)
                .path("/{userid}")
                .resolveTemplate("userid", "u3")
                .request(MediaType.APPLICATION_XML)
                .delete(String.class);

        String result = PASS;
        if (!SUCCESS_RESULT.equals(callResult)) {
            result = FAIL;
        }

        System.out.println("Test case name: testDeleteUser, Result: " + result);
    }
}
