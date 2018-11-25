package org.sopt.kclean.View;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.AdapterFinancialList;
import org.sopt.kclean.Controller.Get;
import org.sopt.kclean.Model.Group;
import org.sopt.kclean.Model.Trade;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

public class FinancialDetailActivity extends AppCompatActivity {

    private TextView financial_detail_account_balance_txt;
    private TextView financial_detail_bank_txt;
    private TextView financial_detail_account_number_txt;
    private TextView financial_detail_year_txt;
    private TextView financial_detail_month_txt;
    private ImageButton financial_detail_left_button;
    private ImageButton financial_detail_right_button;
    private RecyclerView fin_recycler1;
    private RecyclerView fin_recycler2;
    private Calendar oCalendar;
    private Integer search_year;
    private Integer search_month;

    // 탭
    private TabHost tabHost;
    private TabWidget tabs;
    private TabHost.TabSpec tab1, tab2;
    private User user;
    private Group group;

    FrameLayout frameLayout;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterFinancialList adapterFinancialList;


    ArrayList<Trade> tradeList = new ArrayList<Trade>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        group = intent.getParcelableExtra("selectedGroup");
        oCalendar = Calendar.getInstance( );
        search_year =oCalendar.get(Calendar.YEAR);
        search_month = oCalendar.get(oCalendar.get(Calendar.MONTH) + 1);
//
        setContentView(R.layout.activity_financial_detail);
        financial_detail_account_balance_txt = (TextView)findViewById(R.id.financial_detail_account_balance_txt);
        financial_detail_bank_txt = (TextView)findViewById(R.id.financial_detail_bank_txt);
        financial_detail_account_number_txt = (TextView)findViewById(R.id.financial_detail_account_number_txt);
        financial_detail_year_txt = (TextView)findViewById(R.id.financial_detail_year_txt);
        financial_detail_month_txt = (TextView)findViewById(R.id.financial_detail_month_txt);
        financial_detail_left_button = (ImageButton) findViewById(R.id.financial_detail_left_button);
        financial_detail_right_button = (ImageButton)findViewById(R.id.financial_detail_right_button);
//
        tradeList.add(new Trade("돈돈 삼겹살", "11/03 18:23", "-23,000", "결제"));
        tradeList.add(new Trade("컨퍼런스 회의", "11/01 12:10", "-240,000", "결제"));

        //동아리 재정 내역
        FinancialDetailTask financialDetailTask = new FinancialDetailTask();
        financialDetailTask.execute();

//                // 탭탭탭~~
        tabHost = (TabHost)findViewById(R.id.tabHost);

        tabHost.setup();

        //아이콘 변경 필요
        tab1 = tabHost.newTabSpec("1").setContent(R.id.fin_content1).setIndicator("전체");
//        FinancialDetailListTask financialDetailListTask = new FinancialDetailListTask();
//        financialDetailListTask.execute();
        tab2 = tabHost.newTabSpec("2").setContent(R.id.fin_content2).setIndicator("결제");

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
//        // 요까지 탭탭탭~~
//
////        init();
    }

    // tab 눌러질 때 처리
    void init(){
        frameLayout = (FrameLayout) findViewById(android.R.id.tabcontent);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String number) {
                switch (number) {
                    case "1":
                        break;

                    case "2":
                        break;
                }
            }
        });
    }

    private class FinancialDetailListTask extends AsyncTask<String, String, String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            Get get = new Get(user.getToken(),"club_id",group.getGroupId(),"search_year",search_year.toString(),"search_month",search_month.toString());
            String respone = null;
            try{
                respone = get.run("https://klean.apps.dev.clayon.io/api/finance", "application/x-www-form-urlencoded");
            }catch(IOException e){
                  e.printStackTrace();
            }
            return respone;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONObject jsonObject= null;
            JSONArray jsonArray =null;
            ArrayList<Trade> trades =null;
            trades = new ArrayList<>();
            Trade trade;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date d;
            //JSON 파싱
            try {
                jsonObject = new JSONObject(s);
                jsonArray = new JSONArray(jsonObject.get("data"));
                for(int i =  0 ; i < jsonArray.length()-1 ; i++) {
                    d = df.parse(jsonArray.getJSONObject(i).get("write_time").toString());
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(d);

                    if(i==0)
                    {
                        trade = new Trade(""+(calendar.get(Calendar.MONTH) + 1),""+calendar.get(Calendar.DATE),""+calendar.get(Calendar.DAY_OF_WEEK));
                        trades.add(trade);

                    }
                   else if(d.getMonth() != df.parse(jsonArray.getJSONObject(i+1).get("write_time").toString()).getMonth())
                    {
                        trade = new Trade(""+(calendar.get(Calendar.MONTH) + 1),""+calendar.get(Calendar.DATE),""+calendar.get(Calendar.DAY_OF_WEEK));
                        trades.add(trade);
                    }
                    trade = new Trade(jsonArray.getJSONObject(i).get("account_content").toString(),String.valueOf(d.getTime()),jsonArray.getJSONObject(i).get("price").toString(),jsonArray.getJSONObject(i).getInt("checking"),String.valueOf(calendar.get(Calendar.MONTH) + 1),jsonArray.getJSONObject(i).getString("image_uri"));
                    trades.add(trade);
                }
                d = df.parse(jsonArray.getJSONObject(jsonArray.length()-1).get("write_time").toString());
                Calendar ddCalendar = new GregorianCalendar();
                ddCalendar.setTime(d);

                trade = new Trade(jsonArray.getJSONObject(jsonArray.length()-1).get("account_content").toString(),String.valueOf(d.getTime()),jsonArray.getJSONObject(jsonArray.length()-1).get("price").toString(),jsonArray.getJSONObject(jsonArray.length()-1).getInt("checking"),String.valueOf(ddCalendar.get(Calendar.MONTH) + 1),jsonArray.getJSONObject(jsonArray.length()-1).getString("image_uri"));
                trades.add(trade);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (trades == null) {
                trades.add(new Trade("보쌈", "18:30", "24000", 0, "11", "https://kleanbucket.s3.ap-northeast-2.amazonaws.com/club/1541798852700.KakaoTalk_20181103_024853213.png"));
                trades.add(new Trade("삼겹살", "14:30", "30000", 0, "11", "https://kleanbucket.s3.ap-northeast-2.amazonaws.com/club/1541798852700.KakaoTalk_20181103_024853213.png"));
            }

            //recyclerView 구현
            recyclerView =  (RecyclerView)findViewById(R.id.financial_detail_list);
            layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager); // RecyclerView의 레이아웃 매니저 설정,,,
            adapterFinancialList = new AdapterFinancialList(getApplicationContext(),trades);
            recyclerView.setAdapter(adapterFinancialList);
        }
    }
    private  class FinancialDetailTask extends  AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Get get = new Get(user.getToken(), "club_id", group.getGroupId());
            String respone = null;
            Log.d("TAG","token :"+user.getToken()+"\n club_id:"+group.getGroupId());
            try {
                respone = get.run("https://klean.apps.dev.clayon.io/api/finance/info", "application/x-www-form-urlencoded");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return respone;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s == null)
                return ;
            JSONObject jsonObject = null;
            JSONObject data = null;
            try {
                jsonObject = new JSONObject(s);
            data = jsonObject.getJSONObject("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                financial_detail_account_balance_txt.setText(data.getString("price")+"");
                financial_detail_bank_txt .setText(data.getString("account_bank"));
                financial_detail_account_number_txt.setText(data.getString("account_number"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e){
                e.printStackTrace();
                return;
            }
        }
    }
}
