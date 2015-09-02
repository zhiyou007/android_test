package com.wzm.abc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzm.abc.adapter.CommonAdapter;
import com.wzm.abc.adapter.ViewHolder;
import com.wzm.abc.bean.WxHotItem;
import com.wzm.abc.bean.WxHotList;
import com.wzm.abc.ui.activity.WebActivity;
import com.wzm.abc.utils.VolleyHelper;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    private ListView mListView = null;
    private TextView tv_info ;
    private CommonAdapter<WxHotItem> mAdapter = null;
    private Context mContext = null;
    private ArrayList<WxHotItem> mList = new ArrayList<WxHotItem>();
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        tv_info = (TextView)findViewById(R.id.tv_info);
        mListView = (ListView)findViewById(R.id.listView);

        mAdapter = new CommonAdapter<WxHotItem>(mContext,mList,R.layout.cell_wxhot_item) {
            @Override
            public void convert(ViewHolder helper, WxHotItem item, int pos) {
                TextView tv_title = (TextView)helper.getView(R.id.textView);
                tv_title.setText(Html.fromHtml(item.getTitle()));

                TextView tv_content = (TextView)helper.getView(R.id.tv_intro);
                tv_content.setText(Html.fromHtml(item.getWxcontent()));

                ImageView iv_img = (ImageView)helper.getView(R.id.imageView);

                if (!TextUtils.isEmpty(item.getSrc())) {
                    ImageLoader.getInstance().displayImage(item.getSrc(), iv_img);
                }
            }
        };

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WxHotItem whi = mList.get(position);

                Intent intent = new Intent(mContext, WebActivity.class);
                Bundle bd = new Bundle();
                bd.putString("BUNDLE_KEY_URL",whi.getWxurl());
                bd.putString("BUNDLE_KEY_TITLE",whi.getTitle());
                bd.putBoolean("BUNDLE_KEY_SHOW_BOTTOM_BAR", true);
                intent.putExtras(bd);
                startActivity(intent);
            }
        });


        getdata();
    }

    public void getdata()
    {
        page = 1;
        geturl();
    }

    public void loadmore()
    {
        page++;
        geturl();
    }


    public void geturl()
    {
        String wxHot = "http://apis.baidu.com/antelope/wechat/article";
        try {
            wxHot = wxHot +"?keyword="+ URLEncoder.encode("美文", "utf-8") ;
            wxHot = wxHot +"&pageNo="+page;
        } catch (UnsupportedEncodingException e) {

        }

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, wxHot, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("wzm",response.toString());
                        Gson gson = new Gson();
                        WxHotList whl = gson.fromJson(response.toString(), WxHotList.class);
                        if(null!=whl)
                        {
                            if(whl.getStatus().equals("200"))
                            {
                                mList.addAll(whl.getList());
                                mAdapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(mContext,whl.getMesg(),Toast.LENGTH_SHORT).show();
                            }

                            Log.i("wzm","size:"+mList.size());
                        }
                        tv_info.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("apikey", "d42482be87d57dee08e5dca2998c0c53");
                return headers;
            }
        };
        jsonObjRequest.setShouldCache(true);
        VolleyHelper.getInstance().getRequestQueue().add(jsonObjRequest);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
