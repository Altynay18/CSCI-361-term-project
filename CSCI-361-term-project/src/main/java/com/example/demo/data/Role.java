package com.example.demo.data;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name")
    private String roleName;
    
    @OneToMany(mappedBy="role")
    private Set<Guest> users;

    /*@Column(name = "role_desc")
    private String desc;*/

//    public Set<Guest> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<Guest> users) {
//		this.users = users;
//	}

	public int getId() {
        return roleId;
    }

    public void setId(int role_id) {
        this.roleId = role_id;
    }

    public String getRole() {
        return roleName;
    }

    public void setRole(String roleName) {
        this.roleName = roleName;
    }

   /* public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }*/


}