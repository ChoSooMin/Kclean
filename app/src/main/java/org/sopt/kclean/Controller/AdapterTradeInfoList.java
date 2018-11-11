package org.sopt.kclean.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.sopt.kclean.Model.Trade;
import org.sopt.kclean.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterTradeInfoList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Trade> tradeInfoList;
    Context context;

    public AdapterTradeInfoList(Context context, ArrayList<Trade> tradeInfoList) {
        this.tradeInfoList = tradeInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trade_info_card, parent, false);

        return new TradeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tradeInfoList.size();
    }

    class TradeListViewHolder extends RecyclerView.ViewHolder {
        // 홈화면 동아리 리스트에 들어갈 뷰 속성
        public RelativeLayout trade_info_card_relative;
        public CircleImageView trade_info_memberCircle_image;
        public TextView trade_info_tradeName_txt;
        public TextView trade_info_tradeTime_txt;
        public TextView trade_info_isPlus_txt;
        public TextView trade_info_tradePrice_txt;
        public TextView trade_info_isIncome_txt;

        public TradeListViewHolder(View itemView) {
            super(itemView);

            trade_info_card_relative = (RelativeLayout)itemView.findViewById(R.id.trade_info_card_relative);
            trade_info_memberCircle_image = (CircleImageView) itemView.findViewById(R.id.trade_info_memberCircle_image);
            trade_info_tradeName_txt = (TextView) itemView.findViewById(R.id.trade_info_tradeName_txt);
            trade_info_tradeTime_txt = (TextView) itemView.findViewById(R.id.trade_info_tradeTime_txt);
            trade_info_isPlus_txt = (TextView) itemView.findViewById(R.id.trade_info_isPlus_txt);
            trade_info_tradePrice_txt = (TextView) itemView.findViewById(R.id.trade_info_tradePrice_txt);
            trade_info_isIncome_txt = (TextView) itemView.findViewById(R.id.trade_info_isIncome_txt);
        }
    }
}
