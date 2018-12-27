package cn.itcast.shop.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.categorysecond.vo.CategorySecond;

/**
 * 一级分类的实体
 * @author Administrator
 *
 */
public class Category implements Serializable{
    private Integer cid;
    private String cname;
    //一级分类存放二级分类的集合
    private Set<CategorySecond> categorysecond=new HashSet<CategorySecond>();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorysecond() {
		return categorysecond;
	}
	public void setCategorysecond(Set<CategorySecond> categorysecond) {
		this.categorysecond = categorysecond;
	}
    
}
