package com.bid.common.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;

/**
 * 分页实体类 (结合jquery easyui)
 */
public class PageInfo {
	
	//-- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	private final static int PAGESIZE = 10; //默认显示的记录数 

    //-- 返回结果 --//
    private long total = -1; // 总记录数
    private List<Object> rows; //结果记录
    
    //-- 分页参数 --//
    @JsonIgnore
    private int nowpage; // 当前查询页数 
    @JsonIgnore
    private int pagesize; // 每页显示的记录数 
    @JsonIgnore
    private String sort = "";// 排序字段
    @JsonIgnore
    private String order = ASC;// asc，desc -- Order 关键字
    
    //@JsonIgnore
    //private int from;	//limit第1个参数--开始的记录
    //@JsonIgnore
    //private int size;	//limit第2个参数--结束的记录

    
    //构造方法
    public PageInfo() {}
    public PageInfo(int nowpage, int pagesize) {
        //计算当前页  
        if (nowpage < 0) {
            this.nowpage = 1;
        } else {
            //当前页
            this.nowpage = nowpage;
        }
        //记录每页显示的记录数  
        if (pagesize < 0) {
            this.pagesize = PAGESIZE;
        } else {
            this.pagesize = pagesize;
        }
        //计算开始的记录和结束的记录  
        //this.from = (this.nowpage - 1) * this.pagesize;
        //this.size = this.pagesize;
    }

    // 构造方法
    public PageInfo(int nowpage, int pagesize, String sort, String order) {
    	this(nowpage, pagesize);
        // 排序字段，正序还是反序
        this.sort = sort;
        this.order = order;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
    	String lowcaseOrder = StringUtils.lowerCase(order);

		//检查order字符串的合法值
		String[] orders = StringUtils.split(lowcaseOrder, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.order = lowcaseOrder;
    }

	@SuppressWarnings("unchecked")
	public void setPageResult(List pageResultList) {
		if (pageResultList instanceof Page){
			 Page page = (Page) pageResultList; 
			 rows = page.getResult();
			 total = page.getTotal();
		} else {
			rows = pageResultList;
		}
		
	}
}
