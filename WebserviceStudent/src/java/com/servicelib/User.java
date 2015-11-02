package com.servicelib;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String lname;
    private String dob;
    private String adress;
    private String phone;
    private String email;
    private String bloodgroup;
    
    public User() {
    }

    public User(String id, String name, String lname, String dob,String adress,String phone,String email,String bloodgroup) {
        this.id = id;
        this.name = name;
        this.lname = lname;
        this.dob = dob;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.bloodgroup = bloodgroup;
        
    }
    
    public String getbloodgroup() {
        return email;
    }

    @XmlElement
    public void setbloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }
    
    public String getemail() {
        return email;
    }

    @XmlElement
    public void setemail(String email) {
        this.email = email;
    }

    public String getphone() {
        return phone;
    }

    @XmlElement
    public void setphone(String phone) {
        this.phone = phone;
    }
    
    public String getadress() {
        return adress;
    }

    @XmlElement
    public void setadress(String adress) {
        this.dob = adress;
    }
    
    public String getdob() {
        return dob;
    }

    @XmlElement
    public void setdob(String dob) {
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    @XmlElement
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getlname() {
        return lname;
    }

    @XmlElement
    public void setlname(String lname) {
        this.lname = lname;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (!(object instanceof User)) {
            return false;
        } else {
            User user = (User) object;
            if (id == user.getId()
                    && name.equals(user.getName())
                    && lname.equals(user.getlname())
                    && dob.equals(user.getdob())
                    && adress.equals(user.getadress())
                    && phone.equals(user.getphone())
                    && email.equals(user.getemail())
                    && bloodgroup.equals(user.getbloodgroup())
                    ) {
                return true;
            }
        }
        return false;
    }
}
