package cn.itcast.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ����
 * @author Administrator
 *
 */
public class Cart {
     //������ϣ�Map��key��Ψһ������Ʒ��id��value�ŵ��ǹ�����
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer, CartItem>();
	//Cart��������һ����cartItems����
	  public Collection<CartItem> getCartItems(){
		return map.values();
		  
	  }
	//�����ܼ�
	private double total;
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Map<Integer, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}
	//���ﳵ����
	//1.����������ӵ����ﳵ
	public void addCart(CartItem cartItem){
		//�жϹ��ﳵ���Ƿ��Ѿ����ڸù�����
		/**
		 * ������ڣ�
		 *        *��������
		 *        *�ܼƵ���=�ܼ�+������С��
		 * ��������ڣ�
		 *        *��map����ӹ�����
		 *        *�ܼ�=�ܼ�+������С��
		 */
		//�����Ʒ��id
		Integer pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//����
			CartItem cartItemPre=  map.get(pid);//���ԭ���Ĺ�����
			cartItemPre.setCount(cartItemPre.getCount()+cartItem.getCount());
		}
		else{
			//������
			map.put(pid, cartItem);
		}
		//����ܼ�ֵ
		total+=cartItem.getSubtatal();
	}
	//2.�ӹ��ﳵ�Ƴ�������
	public void removeCart(Integer pid){
		//���������Ƴ����ﳵ
		 CartItem cartItem=map.remove(pid);
		//�ܼ�=�ܼ�-�Ƴ����ﳵ��С��
		total-=cartItem.getSubtatal();
	}
	//3.��չ��ﳵ
	public void clearCart(){
		//�����й��������
		map.clear();
		//���ܼ�����Ϊ0
		total=0;
		
	}
}
