package org.sopt.kclean.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sopt.kclean.Model.Notice;
import org.sopt.kclean.R;

import java.util.ArrayList;

public class AdapterNoticeList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    ArrayList<Notice> noticeList;
    Context context;

    public AdapterNoticeList(ArrayList<Notice> noticeList, Context context) {
        this.noticeList = noticeList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_card, parent, false);

        return new NoticeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 흐음,,
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    class NoticeListViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout notice_card_linear;
        public ImageView notice_card_group_image;
        public TextView notice_card_groupName_text;
        public TextView notice_card_noticeContent_text;
        public TextView notice_card_noticeTime_text;

        public NoticeListViewHolder(View itemView) {
            super(itemView);

            notice_card_linear = (LinearLayout) itemView.findViewById(R.id.notice_card_linear);
            notice_card_group_image = (ImageView) itemView.findViewById(R.id.notice_card_group_image);
            notice_card_groupName_text = (TextView) itemView.findViewById(R.id.notice_card_groupName_text);
            //notice_card_noticeContent_text = (TextView) itemView.findViewById(R.id.notice_card_noticeContent_text);
            notice_card_noticeTime_text = (TextView) itemView.findViewById(R.id.notice_card_noticeTime_text);
        }
    }
}
