package cn.itcast.shop.categorysecond.vo;

import java.io.Serializable;
import java.util.Set;

import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.vo.Product;

/**
 * ���������ʵ�����
 * @author Administrator
 *
 */
public class CategorySecond implements Serializable{
   private Integer csid;
   private String csname;
   //������һ�����࣬�����һ���������
   private Category category;
   //������Ʒ����
   private Set<Product> products;
public Set<Product> getProducts() {
	return products;
}
public void setProducts(Set<Product> products) {
	this.products = products;
}
public Integer getCsid() {
	return csid;
}
public void setCsid(Integer csid) {
	this.csid = csid;
}

public String getCsname() {
	return csname;
}
public void setCsname(String csname) {
	this.csname = csname;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
   
}
