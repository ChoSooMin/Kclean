package org.sopt.kclean.Controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.FormatException;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.kclean.Model.Notice;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;
import org.sopt.kclean.View.AnnounceDetailActivity;
import org.sopt.kclean.View.GroupDetailActivity;
import org.sopt.kclean.View.SendMoneyActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterMainNoticeList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    ArrayList<Notice> noticeList;
    Context context;
    User user;

    //

    LinearLayout linear;

    public AdapterMainNoticeList(Context context, ArrayList<Notice> noticeList, User user) {
        this.noticeList = noticeList;
        this.context = context;
        this.user = user;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_notice_card, parent, false);

        return new NoticeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Notice notice = noticeList.get(position);
        final String notice_id = noticeList.get(position).getNotice_id();

        Log.v("noticeListnoticeList", "notice_id || " + notice_id);

        String club_logo = noticeList.get(position).getClub_logo();

        // 동아리 사진 설정
        if (club_logo == null) {
            ((NoticeListViewHolder)holder).main_notice_card_group_circleImage.setImageResource(R.drawable.sopt);
        }
        else {
            Glide.with(context).load(club_logo).asBitmap().centerCrop().into(((NoticeListViewHolder)holder).main_notice_card_group_circleImage);
        }

        ((NoticeListViewHolder)holder).main_notice_card_groupName_text.setText(noticeList.get(position).getClub_name());
        ((NoticeListViewHolder)holder).main_notice_card_managerName_text.setText(noticeList.get(position).getClub_manager()); // 이건 api 고치고

        // 시간 설정
        String write_time = noticeList.get(position).getWrite_time();
        try {
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
            Date date = transFormat.parse(write_time);

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            int month = calendar.get(Calendar.MONTH) + 1;
            int dateInt = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            ((NoticeListViewHolder)holder).main_notice_card_noticeDate_text.setText(month + "/" + dateInt);
            ((NoticeListViewHolder)holder).main_notice_card_noticeTime_text.setText(hour + ":" + minute);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ((NoticeListViewHolder)holder).main_notice_card_noticeTitle_text.setText(noticeList.get(position).getNotice_title());
        ((NoticeListViewHolder)holder).main_notice_card_noticeContent_text.setText(noticeList.get(position).getNotice_content());

        linear = ((NoticeListViewHolder)holder).main_notice_card_linear;

        // 송금하기 버튼 리스너
        ((NoticeListViewHolder)holder).main_notice_card_send_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v("noticenotice", "송금하기 눌렀당");

                linear.setBackgroundColor(Color.parseColor("#FFFFFF"));

                Intent intent = new Intent(context, SendMoneyActivity.class);
                intent.putExtra("notice", notice);
                intent.putExtra("user", user);
                intent.putExtra("notice_id", notice_id);

                Log.v("noticenotice(송금하기 가기 전)", "notice_id || " + notice_id);

                context.startActivity(intent);
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
