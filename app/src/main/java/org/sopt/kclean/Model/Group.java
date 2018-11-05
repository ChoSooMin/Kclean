package org.sopt.kclean.Model;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;

/**
 * Created by choisunpil on 05/11/2018.
 */

public class Group {

    String groupName;
    int totalMember;
    String masterName;
    Drawable groupImage;

    public Group(String groupName, int totalMember, String masterName, Drawable groupImage) {
        this.groupName = groupName;
        this.totalMember = totalMember;
        this.masterName = masterName;
        this.groupImage = groupImage;
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
}