package org.sopt.kclean.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.kclean.Model.Group;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterMemberList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<User> memberList;
    private Context context;
    private User user;
    private int count;

    public AdapterMemberList(Context context, ArrayList<User> memberList, User user) {
        this.context = context;
        this.memberList = memberList;
        this.user = user;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_card, parent, false);

        return new MemberListViewHolder(view); // 이제되이이이이이이이이이이이
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String img = memberList.get(position).getUserImg();

        if (img == null) {
            Glide.with(context).load(R.drawable.ic_profile_48).asBitmap().centerCrop().into(((MemberListViewHolder) holder).member_card_member_image);
        }
        else {
            Glide.with(context).load(img).asBitmap().centerCrop().into(((MemberListViewHolder) holder).member_card_member_image);
        }

//        Glide.with(context).load(memberList.get(position).getUserImg()).asBitmap().centerCrop().into(((MemberListViewHolder) holder).member_card_member_image);
        ((MemberListViewHolder) holder).member_card_memberName_text.setText(memberList.get(position).getName());

        String time = memberList.get(position).getCurrent_time();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
        try {
            Date timeDate = transFormat.parse(time);

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(timeDate);

            int month = calendar.get(Calendar.MONTH) + 1;
            int date = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            ((MemberListViewHolder) holder).member_card_memberTime_text.setText(month + "/" + date + " " + hour + ":" + minute);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        int account_check = memberList.get(position).getAccount_check();

        Log.v("checkcheck", account_check + "");

        if (account_check == 0) { // 입금 안함
            ((MemberListViewHolder) holder).member_card_sendCheck_text.setText("입금 X");
        }
        else if (account_check == 1) { // 입금 함
            ((MemberListViewHolder) holder).member_card_sendCheck_text.setText("입금 O");
        }
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    class MemberListViewHolder extends RecyclerView.ViewHolder {
        // 홈화면 동아리 리스트에 들어갈 뷰 속성
        public LinearLayout member_card_relative;
        public CircleImageView member_card_member_image;
        public TextView member_card_memberName_text;
        public TextView member_card_memberTime_text;
        public TextView member_card_sendCheck_text;

        public MemberListViewHolder(View itemView) {
            super(itemView);

            member_card_relative = (LinearLayout) itemView.findViewById(R.id.member_card_relative);
            member_card_member_image = (CircleImageView) itemView.findViewById(R.id.member_card_member_image);
            member_card_memberName_text = (TextView)itemView.findViewById(R.id.member_card_memberName_text);
            member_card_memberTime_text = (TextView)itemView.findViewById(R.id.member_card_memberTime_text);
            member_card_sendCheck_text = (TextView) itemView.findViewById(R.id.member_card_sendCheck_text);
        }
    }
}
