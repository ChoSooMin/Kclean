package org.sopt.kclean.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sopt.kclean.Model.Board;
import org.sopt.kclean.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterBoardList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    Context context;
    ArrayList<Board> boardList;

    public AdapterBoardList(Context context, ArrayList<Board> boardList) {
        this.context = context;
        this.boardList = boardList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_card, parent, false);

        return new BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 임시
        ((BoardViewHolder)holder).board_card_memberImg_circleImg.setImageResource(R.drawable.sopt); // 작성자 이미지
        ((BoardViewHolder)holder).board_card_writer_text.setText("구설원"); // 작성자 이름
        ((BoardViewHolder)holder).board_card_date_text.setText("11/03"); // 작성 날짜
        ((BoardViewHolder)holder).board_card_time_text.setText("18:30"); // 작성 시간
        ((BoardViewHolder)holder).board_card_content_text.setText("컨퍼런스 후기 올립니다 ! 덕분에 즐겁고 보람찬 경험 많이 할수 있었습니다. 다음에도 또 이런 행사 열어주세요!"); // 게시글 내용
        ((BoardViewHolder)holder).board_commentNumber_text.setText("3"); // 댓글 개수
        ((BoardViewHolder)holder).board_likeNumber_text.setText("22"); // 좋아요 개수
        ((BoardViewHolder)holder).board_photoNumber_text.setText("2"); // 사진 개수

        // 사진이 있는 게시글일 경우,
//        ((BoardViewHolder)holder).board_card_linear.setVisibility(View.VISIBLE); // 사진 아이콘 & 사진 개수 보여주기

        // 사진이 없는 게시글의 경우
//        ((BoardViewHolder)holder).board_card_linear.setVisibility(View.GONE); // 사진 아이콘 & 사진 개수 보여주기
    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }

    class BoardViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout board_card_linear; // 게시판 LinearLayout
        public CircleImageView board_card_memberImg_circleImg; // 작성자 이미지
        public TextView board_card_writer_text; // 작성자 이름
        public TextView board_card_date_text; // 작성 날짜
        public TextView board_card_time_text; // 작성 시간
        public TextView board_card_content_text; // 게시글 내용
        public TextView board_commentNumber_text; // 댓글 개수
        public TextView board_likeNumber_text; // 좋아요 개수
        public LinearLayout board_photo_linear; // 사진 부분 LinearLayout
        public TextView board_photoNumber_text; // 사진 개수

        public BoardViewHolder(View itemView) {
            super(itemView);

            // xml
            board_card_linear = (LinearLayout) itemView.findViewById(R.id.board_card_linear); // 게시판 LinearLayout
            board_card_memberImg_circleImg = (CircleImageView) itemView.findViewById(R.id.board_card_memberImg_circleImg); // 작성자 이미지
            board_card_writer_text = (TextView) itemView.findViewById(R.id.board_card_writer_text); // 작성자 이름
            board_card_date_text = (TextView) itemView.findViewById(R.id.board_card_date_text); // 작성 날짜
            board_card_time_text = (TextView) itemView.findViewById(R.id.board_card_time_text); // 작성 시간
            board_card_content_text = (TextView) itemView.findViewById(R.id.board_card_content_text); // 게시글 내용
            board_commentNumber_text = (TextView) itemView.findViewById(R.id.board_commentNumber_text); // 댓글 개수
            board_likeNumber_text = (TextView) itemView.findViewById(R.id.board_likeNumber_text); // 좋아요 개수
            board_photo_linear = (LinearLayout) itemView.findViewById(R.id.board_photo_linear); // 사진 부분 LinearLayout
            board_photoNumber_text = (TextView) itemView.findViewById(R.id.board_photoNumber_text); // 사진 개수
        }
    }
}
