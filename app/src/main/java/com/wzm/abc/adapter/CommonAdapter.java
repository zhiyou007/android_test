package com.wzm.abc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
/**
 * 通用adapter抽象类
 * @author zhiyou007
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected ArrayList<T> mList;
	protected final int mItemLayoutId;  
	
	public CommonAdapter(Context context,ArrayList<T> datas,int itemLayoutId)
	{
		mInflater = LayoutInflater.from(context);  
        this.mContext = context;  
        this.mList = datas;  
        mItemLayoutId = itemLayoutId;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public T getItem(int arg0) {
		// TODO Auto-generated method stub
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	@Override  
    public View getView(int position, View convertView, ViewGroup parent)  
    {  
        final ViewHolder viewHolder = getViewHolder(position, convertView,parent);  
        convert(viewHolder, getItem(position),position);  
        return viewHolder.getConvertView();    
    } 
	
	
	
	
	/**
	 * 对外方法
	 * @param helper  ViewHolder 
	 * @param item    Bean
	 */
    public abstract void convert(ViewHolder helper, T item,int pos);  
  
    private ViewHolder getViewHolder(int position, View convertView,  
            ViewGroup parent)  
    {  
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,  
                position);  
    }  
}
