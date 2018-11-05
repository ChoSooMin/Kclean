package org.sopt.kclean.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by choisunpil on 06/11/2018.
 */

public class AdapterGroupList extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class GroupListViewHolder extends RecyclerView.ViewHolder {
        //홈화면 동아리 리스트에 들어갈 뷰 속성
        public LinearLayout group_linearLayout;
        public TextView group_name;
        public GroupListViewHolder(View itemView) {
            super(itemView);
        }
    }



}
