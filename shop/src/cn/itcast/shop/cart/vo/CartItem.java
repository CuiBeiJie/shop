package cn.itcast.shop.cart.vo;

import cn.itcast.shop.product.vo.Product;

/**
 * 购物项的实体对象
 * @author Administrator
 *
 */
public class CartItem {
	private Product product; //购物项中商品信息
	private int count;       //购买商品数量
	private double subtatal; //小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//自动计算小计
	public double getSubtatal() {
		return count * product.getShop_price();
	}
	/*public void setSubtatal(double subtatal) {
		this.subtatal = subtatal;
	}
	*/
	

}
