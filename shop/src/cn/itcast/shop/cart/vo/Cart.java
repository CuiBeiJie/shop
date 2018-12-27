package cn.itcast.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 * @author Administrator
 *
 */
public class Cart {
     //购物项集合：Map的key是唯一的用商品的id，value放的是购物项
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer, CartItem>();
	//Cart对象中有一个叫cartItems属性
	  public Collection<CartItem> getCartItems(){
		return map.values();
		  
	  }
	//购物总计
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
	//购物车功能
	//1.将购物项添加到购物车
	public void addCart(CartItem cartItem){
		//判断购物车中是否已经存在该购物项
		/**
		 * 如果存在：
		 *        *数量增加
		 *        *总计等于=总计+购物项小计
		 * 如果不存在：
		 *        *向map中添加购物项
		 *        *总计=总计+购物项小计
		 */
		//获得商品的id
		Integer pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//存在
			CartItem cartItemPre=  map.get(pid);//获得原来的购物项
			cartItemPre.setCount(cartItemPre.getCount()+cartItem.getCount());
		}
		else{
			//不存在
			map.put(pid, cartItem);
		}
		//设计总计值
		total+=cartItem.getSubtatal();
	}
	//2.从购物车移除购物项
	public void removeCart(Integer pid){
		//将购物项移除购物车
		 CartItem cartItem=map.remove(pid);
		//总计=总计-移除购物车的小计
		total-=cartItem.getSubtatal();
	}
	//3.清空购物车
	public void clearCart(){
		//将所有购物项清空
		map.clear();
		//将总计设置为0
		total=0;
		
	}
}
