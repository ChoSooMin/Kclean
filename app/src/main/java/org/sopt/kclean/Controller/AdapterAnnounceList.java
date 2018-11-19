package org.sopt.kclean.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sopt.kclean.Model.Notice;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;
import org.sopt.kclean.View.AnnounceDetailActivity;
import org.sopt.kclean.View.GroupDetailActivity;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterAnnounceList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    ArrayList<Notice> announceList;
    Context context;
    User user;
    int user_position;

    public AdapterAnnounceList(Context context, ArrayList<Notice> announceList, User user, int user_position) {
        this.announceList = announceList;
        this.context = context;
        this.user = user;
        this.user_position = user_position;
        Log.v("adapterUser", "get user_position || " + user_position);
        Log.v("adapterUser", "this user_position || " + user_position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announce_card, parent, false);

        return new AnnounceListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 임시로,,, 해놨ㄷ,,,ㅏ,,,
        Notice notice = announceList.get(position);

        final String notice_id = notice.getNotice_id();

        int category = notice.getNotice_category();
        if (category == 0) {
            ((AnnounceListViewHolder)holder).announce_card_type_image.setImageResource(R.drawable.ic_normal_notice);
        }
        else if (category == 1) {
            ((AnnounceListViewHolder)holder).announce_card_type_image.setImageResource(R.drawable.ic_special_notice);
        }

        // 날짜
        Date formatDate = new Date();
        String time = notice.getWrite_time(); // 다가오는 일정 날짜 (2018-10-02 이런식,,,)
        int month = 0;
        int date = 0;
        int hour = -1;
        int minute = -1;

        String monthStr = "";
        String dateStr = "";
        String hourStr = "";
        String minuteStr = "";

        String write_time = "";
        String write_date = "";
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
        try {
            formatDate = transFormat.parse(time);
            month = formatDate.getMonth() + 1;
            date = formatDate.getDate() + 1;
            hour = formatDate.getHours();
            minute = formatDate.getMinutes();

            monthStr = month + "";
            dateStr = date + "";
            hourStr = hour + "";
            minuteStr = minute + "";

            if (month == 0) {
                monthStr = month + "0";
            }
            if (date == 0){
                dateStr = date + "0";
            }
            if (hour == 0) {
                hourStr = hour + "0";
            }
            if (minute == 0) {
                minuteStr = minute + "0";
            }

            write_date = monthStr + "/" + dateStr + " ";
            write_time = hourStr + ":" + minuteStr;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        ((AnnounceListViewHolder)holder).announce_card_date_text.setText(write_date);
        ((AnnounceListViewHolder)holder).announce_card_time_text.setText(write_time);
        ((AnnounceListViewHolder)holder).announce_card_title_text.setText(notice.getNotice_title());
        ((AnnounceListViewHolder)holder).announce_card_content_text.setText(notice.getNotice_content());

        // 항목 누르면?
        ((AnnounceListViewHolder)holder).announce_card_linear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnnounceDetailActivity.class);


                intent.putExtra("notice_id", notice_id);
                intent.putExtra("user", user);
                Log.v("adapterUser(어댑터 항목 눌렀을 때)", "user_position" + user_position);
                intent.putExtra("user_position", user_position);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return announceList.size();
    }

    class AnnounceListViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout announce_card_linear; // 공지 LinearLayout
        public ImageView announce_card_type_image; // 공지 타입 이미지
        public TextView announce_card_date_text; // 공지 날짜 텍스트
        public TextView announce_card_time_text; // 공지 시간 텍스트
        public TextView announce_card_title_text; // 공지 제목 텍스트
        public TextView announce_card_content_text; // 공지 내용 텍스트

        public AnnounceListViewHolder(View itemView) {
            super(itemView);

            // xml 연결
            announce_card_linear = (LinearLayout) itemView.findViewById(R.id.announce_card_linear);
            announce_card_type_image = (ImageView) itemView.findViewById(R.id.announce_card_type_image);
            announce_card_date_text = (TextView) itemView.findViewById(R.id.announce_card_date_text);
            announce_card_time_text = (TextView) itemView.findViewById(R.id.announce_card_time_text);
            announce_card_title_text = (TextView) itemView.findViewById(R.id.announce_card_title_text);
            announce_card_content_text = (TextView) itemView.findViewById(R.id.announce_card_content_text);
        }
    }
}
