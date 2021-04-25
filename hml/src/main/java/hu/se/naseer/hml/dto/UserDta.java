package hu.se.naseer.hml.dto;

import java.util.Set;

public class UserDta {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer roleId;
    private Set<Integer> ordersIds;
    private Set<Integer> reservationsId;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Set<Integer> getOrdersIds() {
        return ordersIds;
    }
    public void setRoomsIds(Set<Integer> ordersIds) {
        this.ordersIds = ordersIds;
    }
    public Set<Integer> getReservationsId() {
        return reservationsId;
    }
    public void setReservationsId(Set<Integer> reservationsId) {
        this.reservationsId = reservationsId;
    }


}
