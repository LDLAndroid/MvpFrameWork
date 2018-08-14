package com.zhixun.mvptest.ui.bean;


import com.zhixun.mvptest.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Created by Administrator on 2018/4/4.
 */
@Data @EqualsAndHashCode(callSuper=true)
public class  HomeOrderData extends BaseBean{
    private List<HomeOrderDataList> data;
}
