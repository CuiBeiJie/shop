package cn.itcast.shop.cart.vo;

import cn.itcast.shop.product.vo.Product;

/**
 * �������ʵ�����
 * @author Administrator
 *
 */
public class CartItem {
	private Product product; //����������Ʒ��Ϣ
	private int count;       //������Ʒ����
	private double subtatal; //С��
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
	//�Զ�����С��
	public double getSubtatal() {
		return count * product.getShop_price();
	}
	/*public void setSubtatal(double subtatal) {
		this.subtatal = subtatal;
	}
	*/
	

}
