package org.sopt.kclean.Model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choisunpil on 05/11/2018.
 */

public class Group implements Parcelable {


    private String groupName;
    private int totalMember;
    private String masterName;
    private String groupImage;
    private String  groupBackground;
    private String groupDetail;
    private String groupPassword;

    public Group(String groupName, int totalMember, String masterName, String groupImage, String groupBackground, String groupDetail,String password) {
        this.groupName = groupName;
        this.totalMember = totalMember;
        this.masterName = masterName;
        this.groupImage = groupImage;
        this.groupBackground = groupBackground;
        this.groupDetail = groupDetail;
        this.groupPassword = password;
    }

    public Group(Parcel source)
    {
        groupName = source.readString();
        totalMember = source.readInt();
        masterName = source.readString();
        groupImage = source.readString();
        groupBackground = source.readString();
        groupDetail = source.readString();
        groupPassword = source.readString();
    }

    public String getGroupPassword() {
        return groupPassword;
    }

    public void setGroupPassword(String groupPassword) {
        this.groupPassword = groupPassword;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(int totalMember) {
        this.totalMember = totalMember;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    public String getGroupBackground() {
        return groupBackground;
    }

    public void setGroupBackground(String groupBackground) {
        this.groupBackground = groupBackground;
    }

    public String getGroupDetail() {
        return groupDetail;
    }

    public void setGroupDetail(String groupDetail) {
        this.groupDetail = groupDetail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(groupName);
        parcel.writeInt(totalMember);
        parcel.writeString(masterName);
        parcel.writeValue(groupImage);
        parcel.writeValue(groupBackground);
        parcel.writeString(groupDetail);
    }

    public static Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel parcel) {
            return new Group(parcel);
        }

        @Override
        public Group[] newArray(int i) {
            return new Group[i];
        }
    };
}