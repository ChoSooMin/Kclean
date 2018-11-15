package org.sopt.kclean.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.kclean.Model.Group;
import org.sopt.kclean.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterSearchGroupList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Group> searchGroupList;
    private Context context;

    public AdapterSearchGroupList(Context context, ArrayList<Group> searchGroupList) {
        this.context = context;
        this.searchGroupList = searchGroupList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_group_card, parent, false);

        return new SearchGroupListViewHolder(view); // 이제되이이이이이이이이이이이
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context).load(searchGroupList.get(position).getGroupImage()).asBitmap().centerCrop().into(((SearchGroupListViewHolder) holder).search_group_group_circleImage);
        ((SearchGroupListViewHolder)holder).search_group_groupName_text.setText(searchGroupList.get(position).getGroupName());
        ((SearchGroupListViewHolder)holder).search_group_totalMember_text.setText("" + searchGroupList.get(position).getTotalMember());
        ((SearchGroupListViewHolder)holder).search_group_managerName_text.setText(searchGroupList.get(position).getMasterName());
        ((SearchGroupListViewHolder)holder).search_group_groupDetail_text.setText(searchGroupList.get(position).getGroupDetail());
    }

    @Override
    public int getItemCount() {
        return searchGroupList.size();
    }

    class SearchGroupListViewHolder extends RecyclerView.ViewHolder {
        // 홈화면 동아리 리스트에 들어갈 뷰 속성
        public CircleImageView search_group_group_circleImage;
        public TextView search_group_groupName_text;
        public TextView search_group_totalMember_text;
        public TextView search_group_managerName_text;
        public TextView search_group_groupDetail_text;

        public SearchGroupListViewHolder(View itemView) {
            super(itemView);

            search_group_group_circleImage = (CircleImageView) itemView.findViewById(R.id.search_group_group_circleImage);
            search_group_groupName_text = (TextView) itemView.findViewById(R.id.search_group_groupName_text);
            search_group_totalMember_text = (TextView) itemView.findViewById(R.id.search_group_totalMember_text);
            search_group_managerName_text = (TextView) itemView.findViewById(R.id.search_group_managerName_text);
            search_group_groupDetail_text = (TextView) itemView.findViewById(R.id.search_group_groupDetail_text);
        }
    }
}
