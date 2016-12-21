package com.example.framgiamaidaidien.mvpexample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.framgiamaidaidien.mvpexample.R;

/**
 * Created by FRAMGIA\mai.dai.dien on 20/12/2016.
 */

public class User implements Parcelable{
    private static final int MALE = 1;
    private static final int FEMALE = 0;
    /**
     * name : Nguyen Thi B
     * gender : 0
     * projects : {"name":"Rola","role":"Leader"}
     */
    private String name;
    private int gender;

    private ProjectsEntity projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public ProjectsEntity getProjects() {
        return projects;
    }

    public void setProjects(ProjectsEntity projects) {
        this.projects = projects;
    }

    public int getAvatarResource() {
        if (gender == MALE) return R.drawable.male;
        if (gender == FEMALE) return R.drawable.female;
        return R.drawable.congchuabaothu;
    }

    public String getGenderString() {
        if (gender == MALE) return "Male";
        if (gender == FEMALE) return "Female";
        return "Undefine";
    }

    protected User(Parcel in) {
        name = in.readString();
        gender = in.readInt();
        projects = (ProjectsEntity) in.readValue(ProjectsEntity.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(gender);
        dest.writeValue(projects);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public static class ProjectsEntity implements Parcelable {
        private String name;
        private String role;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        protected ProjectsEntity(Parcel in) {
            name = in.readString();
            role = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeString(role);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<ProjectsEntity> CREATOR = new Parcelable.Creator<ProjectsEntity>() {
            @Override
            public ProjectsEntity createFromParcel(Parcel in) {
                return new ProjectsEntity(in);
            }

            @Override
            public ProjectsEntity[] newArray(int size) {
                return new ProjectsEntity[size];
            }
        };
    }
}
