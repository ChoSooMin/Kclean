package org.sopt.kclean.Model;

/**
 * Created by choisunpil on 05/11/2018.
 */

public class UserBuilder {

    String id;
    String password;
    String name;
    String phoneNumber;
    String gender;
    String birth;
    String token;
    String university;
    String major;
    Account account; //추후 객체로 변경

    public UserBuilder setToken(final String token)
    {
        this.token = token;
        return this;
    }

   public UserBuilder setId(final String id)
   {
       this.id  = id;
       return this;
   }
   public UserBuilder setPassword(final String password)
   {
       this.password = password;
       return this;
   }
   public UserBuilder setName(final String name)
   {
       this.name =  name;
       return this;
   }

   public UserBuilder setPhoneNumber(final String phoneNumber)
   {
       this.phoneNumber = phoneNumber;
       return this;
   }

   public UserBuilder setGender(final String gender)
   {
       this.gender = gender;
       return this;
   }
   public UserBuilder setBirth(final String birth)
   {

       this.birth = birth;
       return this;
   }
   public UserBuilder setUniversity(final String University)
   {

       this.university = university;
       return this;

   }

   public UserBuilder setMajor(final String Major)
   {
       this.major = major;
       return this;


   }

    public UserBuilder setAccount(final Account account)
    {

        this.account = account;
        return this;
    }
   public User build()
   {
       User user;
       user = new User(id,password,name,phoneNumber,gender,birth,university,major);
       return user;
   }



}
