/**
 *
 */
package com.enclaveit.dto.model;

/**
 * @author varick
 *
 */
public class AdminUserRegisterDTO {

    private String username;
    private String password;
    private String matchPassword;

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

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }
}
