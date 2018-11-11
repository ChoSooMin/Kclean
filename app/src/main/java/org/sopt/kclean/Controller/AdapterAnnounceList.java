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
import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterAnnounceList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    ArrayList<Notice> announceList;
    Context context;

    public AdapterAnnounceList(Context context, ArrayList<Notice> announceList) {
        this.announceList = announceList;
        this.context = context;
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
        ((AnnounceListViewHolder)holder).announce_card_type_image.setImageResource(R.drawable.ic_normal_notice);
        ((AnnounceListViewHolder)holder).announce_card_time_text.setText("11/03");
        ((AnnounceListViewHolder)holder).announce_card_date_text.setText("18:30");
        ((AnnounceListViewHolder)holder).announce_card_title_text.setText("컨퍼런스 기획단 모집");
        ((AnnounceListViewHolder)holder).announce_card_content_text.setText("<공지사항> 컨퍼런스 기획단 모집합니다. 기획/디자인 관심있는 분들 많은 지원 부탁드립니다 화이팅이에용...");

        // 항목 누르면?
        ((AnnounceListViewHolder)holder).announce_card_linear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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
