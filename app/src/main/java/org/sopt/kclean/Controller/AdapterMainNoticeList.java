package org.sopt.kclean.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sopt.kclean.Model.Notice;
import org.sopt.kclean.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterMainNoticeList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    ArrayList<Notice> noticeList;
    Context context;

    public AdapterMainNoticeList(Context context, ArrayList<Notice> noticeList) {
        this.noticeList = noticeList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_notice_card, parent, false);

        return new NoticeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 임시
        ((NoticeListViewHolder)holder).main_notice_card_group_circleImage.setImageResource(R.drawable.sopt);
        ((NoticeListViewHolder)holder).main_notice_card_noticeTitle_text.setText("컨퍼런스 기획단 모집");
        ((NoticeListViewHolder)holder).main_notice_card_noticeContent_text.setText("컨퍼런스 기획단 모집합니다. 기획/디자인 관심있는 분들 많은 지원 부탁드립니다...");
        ((NoticeListViewHolder)holder).main_notice_card_noticeDate_text.setText("11/05");
        ((NoticeListViewHolder)holder).main_notice_card_noticeTime_text.setText("18:30");
        ((NoticeListViewHolder)holder).main_notice_card_groupName_text.setText("S.O.P.T 23기");
        ((NoticeListViewHolder)holder).main_notice_card_managerName_text.setText("강지희");

        // 알림 누르면
        ((NoticeListViewHolder)holder).main_notice_card_linear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 알림 상세보기로 이동
            }
        });

        // 송금하기 버튼 리스너
        ((NoticeListViewHolder)holder).main_notice_card_send_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    class NoticeListViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout main_notice_card_linear; // (메인) 알림 LinearLayout
        public CircleImageView main_notice_card_group_circleImage; // 알림 동아리 이미지
        public TextView main_notice_card_noticeTitle_text; // 알림 제목
        public TextView main_notice_card_noticeContent_text; // 알림 내용
        public TextView main_notice_card_noticeDate_text; // 알림 날짜
        public TextView main_notice_card_noticeTime_text; // 알림 시간
        public TextView main_notice_card_groupName_text; // 알림 동아리 이름
        public TextView main_notice_card_managerName_text; // 동아리 총무 이름
        public ImageButton main_notice_card_send_button; // 송금하기 버튼

        public NoticeListViewHolder(View itemView) {
            super(itemView);

            // xml
            main_notice_card_linear = (LinearLayout) itemView.findViewById(R.id.main_notice_card_linear); // (메인) 알림 LinearLayout
            main_notice_card_group_circleImage = (CircleImageView) itemView.findViewById(R.id.main_notice_card_group_circleImage); // 알림 동아리 이미지
            main_notice_card_noticeTitle_text = (TextView) itemView.findViewById(R.id.main_notice_card_noticeTitle_text); // 알림 제목
            main_notice_card_noticeContent_text = (TextView) itemView.findViewById(R.id.main_notice_card_noticeContent_text); // 알림 내용
            main_notice_card_noticeDate_text = (TextView) itemView.findViewById(R.id.main_notice_card_noticeDate_text); // 알림 날짜
            main_notice_card_noticeTime_text = (TextView) itemView.findViewById(R.id.main_notice_card_noticeTime_text); // 알림 시간
            main_notice_card_groupName_text = (TextView) itemView.findViewById(R.id.main_notice_card_groupName_text); // 알림 동아리 이름
            main_notice_card_managerName_text = (TextView) itemView.findViewById(R.id.main_notice_card_managerName_text); // 동아리 총무 이름
            main_notice_card_send_button = (ImageButton) itemView.findViewById(R.id.main_notice_card_send_button); // 송금하기 버튼
        }
    }
}
