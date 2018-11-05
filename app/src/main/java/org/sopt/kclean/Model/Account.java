package org.sopt.kclean.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choisunpil on 06/11/2018.
 */

public class Account implements Parcelable {
    private String account_number;//계좌번호
    private String balance; //잔고

    public Account(){

    }
    public Account(String account_number,String balance)
    {
        this.account_number = account_number;
        this.balance = balance;

    }
    public Account(Parcel parcel)
    {
        account_number = parcel.readString();
        balance = parcel.readString();
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    public static Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel parcel) {
            return new Account(parcel);
        }

        @Override
        public Account[] newArray(int i) {
            return new Account[i];
        }
    };
}
