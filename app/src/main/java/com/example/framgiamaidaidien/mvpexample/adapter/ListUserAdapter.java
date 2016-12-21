package com.example.framgiamaidaidien.mvpexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framgiamaidaidien.mvpexample.R;
import com.example.framgiamaidaidien.mvpexample.model.User;

import java.util.List;

/**
 * Created by FRAMGIA\mai.dai.dien on 20/12/2016.
 */

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.UserViewHolder> {
    private List<User> mUserList;

    public ListUserAdapter(List<User> userList) {
        this.mUserList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        if (mUserList == null) return;
        holder.displayUser(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mUserList == null) return 0;
        return mUserList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, pj, role;
        ImageView iv;

        public UserViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            pj   = (TextView) view.findViewById(R.id.pj);
            role = (TextView) view.findViewById(R.id.role);
            iv   = (ImageView) view.findViewById(R.id.avatar);
        }

        public void displayUser(User user) {
            if (user == null) return;

            name.setText(user.getName());
            iv.setImageResource(user.getAvatarResource());
            User.ProjectsEntity projectsEntity = user.getProjects();
            if (projectsEntity != null) {
                pj.setText(projectsEntity.getName());
                role.setText(projectsEntity.getRole());
            } else {
                pj.setText("");
                role.setText("");
            }
        }
    }
}
