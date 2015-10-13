/**
 *
 */
package com.enclaveit.service;

import com.enclaveit.dto.model.AdminUserRegisterDTO;
import com.enclaveit.dto.model.CustomerRegisterDTO;

/**
 * @author varick
 *
 */
public interface UserService {

    public boolean addUser(AdminUserRegisterDTO user);
    public boolean customerRegister(CustomerRegisterDTO customer);
}
