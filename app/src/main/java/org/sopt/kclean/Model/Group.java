package org.sopt.kclean.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choisunpil on 05/11/2018.
 */

public class Group implements Parcelable {

    private String groupId;
    private String groupName;
    private int totalMember;
    private String club_manager;
    private String groupImage;
    private String  groupBackground;
    private String groupDetail;

    public Group(String groupId, String groupName, String groupDetail, String groupImage, String club_manager, int totalMember) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDetail = groupDetail;
        this.groupImage = groupImage;
        this.club_manager = club_manager;
        this.totalMember = totalMember;
    }

    public Group(String groupName,String groupImage,String groupDetail,String groupBackground)
    {
        this.groupName = groupName;
        this.groupImage = groupImage;
        this.groupDetail = groupDetail;
        this.groupBackground = groupBackground;
    }

    public Group(String groupId, String groupName , String groupImage, String club_manager, int totalMember )
    {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupImage = groupImage;
        this.club_manager = club_manager;
        this.totalMember = totalMember;
    }

    public Group(Parcel source)
    {
        groupId = source.readString();
        groupName = source.readString();
        totalMember = source.readInt();
        club_manager = source.readString();
        groupImage = source.readString();
        groupBackground = source.readString();
        groupDetail = source.readString();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public String getClub_manager() {
        return club_manager;
    }

    public void setClub_manager(String club_manager) {
        this.club_manager = club_manager;
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
        parcel.writeString(groupId);
        parcel.writeString(groupName);
        parcel.writeInt(totalMember);
        parcel.writeString(club_manager);
        parcel.writeString(groupImage);
        parcel.writeString(groupBackground);
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