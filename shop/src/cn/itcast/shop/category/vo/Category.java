package cn.itcast.shop.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.categorysecond.vo.CategorySecond;

/**
 * һ�������ʵ��
 * @author Administrator
 *
 */
public class Category implements Serializable{
    private Integer cid;
    private String cname;
    //һ�������Ŷ�������ļ���
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
