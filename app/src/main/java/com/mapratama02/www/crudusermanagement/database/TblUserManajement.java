package com.mapratama02.www.crudusermanagement.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TblUserManajement {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String email;
    @Generated(hash = 1301491530)
    public TblUserManajement(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    @Generated(hash = 1507012086)
    public TblUserManajement() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
