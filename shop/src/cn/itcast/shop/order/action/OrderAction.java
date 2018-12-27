package cn.itcast.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ���������Action
 * @author Administrator
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
    //ģ������ʹ�õĶ���
	private Order order=new Order();
	//ע��orderService
	//����ҳ��
	private Integer page;
	//����֧��ͨ������
	private String pd_FrpId;
	//���ո���ɹ������Ӧ����
	private String r6_Order;
	private String r3_Amt;
	
	public String getR6_Order() {
		return r6_Order;
	}
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public String getR3_Amt() {
		return r3_Amt;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	public String getPd_FrpId() {
		return pd_FrpId;
	}
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	private OrderService orderService;
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
   //���ɶ�����Action
	public String saveOrder(){
		//�������ݵ����ݿ�
		//�������ݵĲ�ȫ
		order.setOrdertime(new Date());
		order.setState(1);//1.δ���� 2.�Ѿ������δ���� 3.�Ѿ�������û��ȷ���ջ� 4.�������
		//�ܼƵĽ���ǹ��ﳵ�����Ϣ
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			this.addActionError("�ף��㻹û�й�����ȥ����");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//���ö����еĶ�����
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtatal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		//���ö��������û�
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user==null){
			this.addActionError("�ף��㻹û�е�¼�����ȥ��¼");
			return "login";
		}
		order.setUser(user);
		orderService.save(order);
		//������������ʾ��ҳ����
		//ͨ��ֵջ�ķ�ʽ��������ʾ����ΪOrder��ʾ�Ķ������ģ�������Ķ��󣬾���ջ��
		//��չ��ﳵ
		cart.clearCart();
		return "saveOrder";
		
	}
	//�ҵĶ����Ĳ�ѯ
	public String myOrder(){
		//�����û���id��ѯ
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//����Service
		PageBean<Order> pageBean=orderService.findOrderByUid(user.getUid(),page);
		//����ҳ������ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "myOrder";
		
	}
	
	//���ݶ����������ѯ����
	public String findByOid(){
		order=orderService.findByOid(order.getOid());
		return "findByOidSuccess";
		
	}
	
	//Ϊ��������ķ���
	public String payOrder(){
		//�޸Ķ���
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		//Ϊ��������
		String p0_Cmd ="Buy"; //ҵ������
		String p1_MerId ="10001126856" ; //�̻����
		String p2_Order=order.getOid().toString(); //�������
		String p3_Amt="0.01";//֧�����
		String p4_Cur="CNY"; //���ױ���
		String p5_Pid="";       //��Ʒ����
		String p6_Pcat="";//��Ʒ����
		String p7_Pdesc="";//��Ʒ����
		String p8_Url="http://localhost:8080/shop/order_callBack.action";// �̻�����֧���ɹ����ݵĵ�ַ
		String p9_SAF="";//�ͻ���ַ
		String pa_MP="";//�̻���չ��Ϣ
		String pd_FrpId=this.pd_FrpId;//֧��ͨ������
		String pr_NeedResponse="1"; //Ӧ�����
		String keyValue= "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		//���ױ���ת
		StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("keyValue=").append(keyValue).append("&");
		stringBuffer.append("hmac=").append(hmac);
		//�ض����ױ�
		try {
			ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
		
	}
	//����ɹ���ת��
	public String callBack(){
		//�޸Ķ���״̬���޸�Ϊ�Ѿ�����
		Order currOrder=orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);
		orderService.update(currOrder);
		//��ҳ������ʾ������Ϣ
		this.addActionMessage("��������ɹ� �� ������ţ�"+r6_Order+" �����"+r3_Amt);
		return "msg";
		
	}
}
