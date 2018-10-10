package com.zhixun.mvptest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.zhixun.mvptest.R;
import com.zhixun.mvptest.ui.activity.DropmenuActivity;
import com.zhixun.mvptest.ui.bean.ChildBean;
import com.zhixun.mvptest.ui.bean.GroupBean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/7.
 */

public class ExpandAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<GroupBean> groupList;
    private final List<List<ChildBean>> childList;

    public ExpandAdapter(Context context, List<GroupBean> groupList, List<List<ChildBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList == null ? 0 : groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int position, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder=null;
        if (view == null) {
            view= LayoutInflater.from(context).inflate(R.layout.expand_group,null);
            groupViewHolder=new GroupViewHolder();
            groupViewHolder.tv_group_name=view.findViewById(R.id.tv_group_name);
            view.setTag(groupViewHolder);
        }else{
            groupViewHolder= (GroupViewHolder) view.getTag();
        }
        groupViewHolder.tv_group_name.setText(groupList.get(position).getTitle());
        return view;
    }

    @Override
    public View getChildView(int position, int position1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder=null;
        if (view == null) {
            view= LayoutInflater.from(context).inflate(R.layout.expand_child,null);
            childViewHolder=new ChildViewHolder();
            childViewHolder.tv_child_name=view.findViewById(R.id.tv_child_name);
            view.setTag(childViewHolder);
        }else{
            childViewHolder= (ChildViewHolder) view.getTag();
        }
        childViewHolder.tv_child_name.setText(childList.get(position).get(position1).getChild());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


    private static class GroupViewHolder {
        TextView tv_group_name;
    }

    private static class ChildViewHolder {
        TextView tv_child_name;
    }
}
