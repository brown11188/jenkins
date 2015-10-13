/**
 *
 */
package com.enclaveit.dto.model;

/**
 * @author varick
 *
 */
public class CustomerRegisterDTO {

    private String username;
    private String password;
    private String matchpassword;
    private String firstname;
    private String lastname;
    private String email;
    private String address;

    public CustomerRegisterDTO() {
        super();
    }

    public CustomerRegisterDTO(String username, String password,
            String matchpassword, String firstname, String lastname,
            String email, String address) {
        super();
        this.username = username;
        this.password = password;
        this.matchpassword = matchpassword;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchpassword() {
        return matchpassword;
    }

    public void setMatchpassword(String matchpassword) {
        this.matchpassword = matchpassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
