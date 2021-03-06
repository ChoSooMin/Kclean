package org.sopt.kclean.Controller;

//import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.kclean.Model.Group;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;
import org.sopt.kclean.View.GroupDetailActivity;

import java.util.ArrayList;

/**
 * Created by choisunpil on 06/11/2018.
 * 최효진 최고
 */

public class AdapterGroupList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Group> groupList;
    private Context context;
    private User user;

    public AdapterGroupList(Context context, ArrayList<Group> groupList, User user) {
        this.context = context;
        this.groupList = groupList;
        this.user = user;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_card, parent, false);

        return new GroupListViewHolder(view); // 이제되이이이이이이이이이이이
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // 이건 임시
        Glide.with(context).load(groupList.get(position).getGroupImage()).asBitmap().centerCrop().into(((GroupListViewHolder) holder).group_card_image);

        ((GroupListViewHolder)holder).group_card_managerName_text.setText(groupList.get(position).getClub_manager());
        ((GroupListViewHolder)holder).group_card_totalnumber_text.setText("" + groupList.get(position).getTotalMember());
        ((GroupListViewHolder)holder).group_card_group_name_text.setText(groupList.get(position).getGroupName());

        ((GroupListViewHolder) holder).group_card_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GroupDetailActivity.class);
                intent.putExtra("selectedGroup", groupList.get(position));
                intent.putExtra("user",user);
                intent.putExtra("totalNumber", groupList.get(position).getTotalMember() + "");

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    class GroupListViewHolder extends RecyclerView.ViewHolder {
        // 홈화면 동아리 리스트에 들어갈 뷰 속성
        public LinearLayout group_card_linear;
        public ImageView group_card_image;
        public TextView group_card_group_name_text;
        public TextView group_card_totalnumber_text;
        public TextView group_card_managerName_text;

        public GroupListViewHolder(View itemView) {
            super(itemView);

            group_card_linear = (LinearLayout)itemView.findViewById(R.id.group_card_linear);
            group_card_image = (ImageView)itemView.findViewById(R.id.group_card_image);
            group_card_group_name_text = (TextView)itemView.findViewById(R.id.group_card_group_name_text);
            group_card_totalnumber_text = (TextView)itemView.findViewById(R.id.group_card_totalnumber_text);
            group_card_managerName_text = (TextView)itemView.findViewById(R.id.group_card_managerName_text);
        }
    }
}