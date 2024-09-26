package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Customer;
import fpt.swp.WorkSpace.models.User;

public interface ICustomerService {

    Customer getCustomerProfile(String token);

    Customer customerChangePassword(String username, String newPassword);

    Customer customerEditProfile(String username, Customer newCustomer);




}
