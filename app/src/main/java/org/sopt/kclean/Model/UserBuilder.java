package org.sopt.kclean.Model;

/**
 * Created by choisunpil on 05/11/2018.
 */

public class UserBuilder {

   private String id;
   private String password;
   private String name; //이름
   private String phoneNumber; //핸드폰번호
   private String gender; //성별
   private String birth; //생일
   private String token;//토큰
   private Account account;//계좌

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
    public UserBuilder setAccount(final Account account)
    {

        this.account = account;
        return this;
    }
   public User build()
   {
       User user;
       user = new User(id,password,name,phoneNumber,gender,birth,token,account);
       return user;
   }



}
