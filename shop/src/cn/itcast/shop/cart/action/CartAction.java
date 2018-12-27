package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
	//��������
	private Integer count;
	//������Ʒid
	private Integer pid;
	//ע����Ʒ��Service
	private ProductService productService;
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

		//����������ӵ����ﳵ�еķ���
	     public String addCart(){
	    	//��װCartItem����
	    	 CartItem cartItem=new CartItem();
	    	 //��������
	    	 cartItem.setCount(count);
	    	 //������Ʒ
	    	 Product product=productService.findByPid(pid);
	    	 cartItem.setProduct(product);
	    	 //����������ӵ����ﳵ
	    	 //���ﳵ�浽session��
	    	 Cart cart=getCart();
	    	 cart.addCart(cartItem);
			return "addCart";
	    	 
	     }
	     //��ù��ﳵ��������session�л�ù��ﳵ

		private Cart getCart() {
			// TODO Auto-generated method stub
			Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
			if(cart==null){
				cart=new Cart();
				ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
			}
			return cart;
		}
		//��չ��ﳵ
		public String clearCart(){
			//���session��Ĺ��ﳵ����
			Cart cart=getCart();
			//��ն���
			cart.clearCart();
			return "clearCart";
			
		}
		//�Ƴ�������
		public String deleteCart(){
			//��session�л�ù��ﳵ����
			Cart cart=getCart();
			cart.removeCart(pid);
			return "removeCart";
			
		}
       //�ҵĹ��ﳵ
		public String myCart(){
			return "myCart";
			
		}
}
