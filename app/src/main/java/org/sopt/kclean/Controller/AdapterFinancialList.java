package org.sopt.kclean.Controller;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.kclean.Model.Trade;
import org.sopt.kclean.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by choisunpil on 17/11/2018.
 */

public class AdapterFinancialList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    final static  int TRADE = 0;
    final static  int DATE = 1;
    Context context;
    ArrayList<Trade> tradesList;



    public AdapterFinancialList(Context context, ArrayList<Trade>tradesList ){
        this.context = context;
        this.tradesList =  tradesList;

    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TRADE)
        {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trade_info_card, parent, false);
            return new  trade_info_card_ViewHolder(view);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.financial_detail_date_card, parent, false);
            return new financial_detail_card_ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == DATE)
        {
            Glide.with(context).load(tradesList.get(position).getImageUri()).asBitmap().centerCrop().into(((trade_info_card_ViewHolder) holder).trade_info_memberCircle_image);

            ((trade_info_card_ViewHolder) holder).trade_info_tradeName_txt.setText(tradesList.get(position).getTradeLocation());

            if(tradesList.get(position).getIsIncoming() == 1) {
                ((trade_info_card_ViewHolder) holder).trade_info_isIncome_txt.setText("입금");
                ((trade_info_card_ViewHolder) holder).trade_info_tradePrice_txt.setText("+"+tradesList.get(position).getMoney());
                ((trade_info_card_ViewHolder) holder).trade_info_tradePrice_txt.setTextColor(Color.parseColor("#000000"));
            }else {
                ((trade_info_card_ViewHolder) holder).trade_info_isIncome_txt.setText("출금");
                ((trade_info_card_ViewHolder) holder).trade_info_tradePrice_txt.setText("-"+tradesList.get(position).getMoney());
                ((trade_info_card_ViewHolder) holder).trade_info_tradePrice_txt.setTextColor(Color.parseColor("#ff5050"));

            }
            ((trade_info_card_ViewHolder) holder).trade_info_tradeTime_txt.setText(tradesList.get(position).getTime());

        }
        else
        {
            ((financial_detail_card_ViewHolder)holder).date_card_day_text.setText(tradesList.get(position).getDay());
            ((financial_detail_card_ViewHolder)holder).date_card_date_text.setText(tradesList.get(position).getDate());
            ((financial_detail_card_ViewHolder)holder).date_card_month_text.setText(tradesList.get(position).getMonth());

        }
    }

    @Override
    public int getItemCount()
    {
        return tradesList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(tradesList.get(position).getType() == TRADE)
         return TRADE;
        else
            return DATE;
    }

    class trade_info_card_ViewHolder extends RecyclerView.ViewHolder //결제 정보
    {
        private CircleImageView trade_info_memberCircle_image;
        private TextView trade_info_tradeName_txt;
        private  TextView trade_info_tradeTime_txt;
        private  TextView trade_info_tradePrice_txt;
        private  TextView  trade_info_isIncome_txt;

        public trade_info_card_ViewHolder(View itemView) {
            super(itemView);
            trade_info_memberCircle_image = itemView.findViewById(R.id.trade_info_memberCircle_image);
            trade_info_tradeName_txt = itemView.findViewById(R.id.trade_info_tradeName_txt);
            trade_info_tradeTime_txt = itemView.findViewById(R.id.trade_info_tradeTime_txt);
            trade_info_tradePrice_txt = itemView.findViewById(R.id.trade_info_tradePrice_txt);
            trade_info_isIncome_txt = itemView.findViewById(R.id.trade_info_isIncome_txt);


        }
    }
    class financial_detail_card_ViewHolder extends RecyclerView.ViewHolder //날짜 정보
    {
        private TextView date_card_month_text;
        private  TextView date_card_date_text;
        private  TextView date_card_day_text;
        public financial_detail_card_ViewHolder(View itemView) {
            super(itemView);
            date_card_date_text = itemView.findViewById(R.id.date_card_date_text);
            date_card_month_text = itemView.findViewById(R.id.date_card_month_text);
            date_card_day_text = itemView.findViewById(R.id.date_card_day_text);

        }
    }
}
