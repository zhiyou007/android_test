package com.wzm.abc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.wzm.abc.bean.WxHotList;
import com.wzm.abc.utils.VolleyHelper;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private TextView tv_info ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_info = (TextView)findViewById(R.id.tv_info);

        String wxHot = "http://apis.baidu.com/antelope/wechat/article";
        try {
            wxHot = wxHot +"?keyword="+ URLEncoder.encode("美文", "utf-8") ;
            wxHot = wxHot +"&pageNo=1";
        } catch (UnsupportedEncodingException e) {

        }

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, wxHot, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        WxHotList whl = gson.fromJson(response.toString(), WxHotList.class);
                        tv_info.setText(response.toString());
                        Log.i("wzm",response.toString());
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
