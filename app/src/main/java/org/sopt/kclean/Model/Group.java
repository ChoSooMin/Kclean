package org.sopt.kclean.Model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choisunpil on 05/11/2018.
 */

public class Group implements Parcelable {

    String groupName;
    int totalMember;
    String masterName;
    Drawable groupImage;
    Drawable groupBackground;
    String groupDetail;

    public Group(String groupName, int totalMember, String masterName, Drawable groupImage, Drawable groupBackground, String groupDetail) {
        this.groupName = groupName;
        this.totalMember = totalMember;
        this.masterName = masterName;
        this.groupImage = groupImage;
        this.groupBackground = groupBackground;
        this.groupDetail = groupDetail;
    }

    public Group(Parcel source)
    {
        groupName = source.readString();
        totalMember = source.readInt();
        masterName = source.readString();
        groupImage = source.readParcelable(Drawable.class.getClassLoader());
        groupBackground = source.readParcelable(Drawable.class.getClassLoader());
        groupDetail = source.readString();
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

    public Drawable getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(Drawable groupImage) {
        this.groupImage = groupImage;
    }

    public Drawable getGroupBackground() {
        return groupBackground;
    }

    public void setGroupBackground(Drawable groupBackground) {
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