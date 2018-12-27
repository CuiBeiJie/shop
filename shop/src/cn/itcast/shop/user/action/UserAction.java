package cn.itcast.shop.user.action;

import java.io.IOException;


import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//������֤��
	private String checkcode;
	//ģ������ʹ�õĶ���
	private User user=new User();
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	//ע��UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/**
	 * ��ת��ע��ҳ���ִ�з���
	 */
  public String registPage(){
	  
	return "registPage";
	  
  }
  /**
   * AJAX�����첽У���û�����ִ�з���
 * @throws IOException 
   */
  public String findByName() throws IOException{
	  //����service��ѯ
	  System.out.println("�ҽ�����findByName");
	  System.out.println(user.getUsername());
	  User existUser=userService.findByUsername(user.getUsername());
	  //���response������ҳ�����
	  HttpServletResponse response= ServletActionContext.getResponse();
	  response.setContentType("text/html;charset=UTF-8");
	  //�ж�
	  if(existUser !=null){
		  //��ѯ���û����û����Ѿ�����
		  response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
	  }else{
		  //û�鵽���û����û�������ʹ��
		  response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
	  }
	  return NONE;
	  
  }
    /**
     * �û�ע��ķ���
     */
    public String regist(){
    	//�ж���֤�����
    	//��session�л����֤������ֵ
    	String code=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
    	if(!checkcode.equalsIgnoreCase(code)){
    		this.addActionError("��֤���������");
    		return "checkcodeFail";
    	}
    	userService.save(user);
    	this.addActionMessage("�Ѿ�ע��ɹ���ȥ���伤��");
		return "msg";
    	
    }
    /**
     * �û�����
     */
     public String active(){
    	 //���ݼ������ѯ�Ƿ���ڸ��û�
    	User existUser=userService.findByCode(user.getCode());
    	//�ж�
    	if(existUser ==null){
    		//����ʧ��
    		this.addActionMessage("����ʧ�ܣ����������");
    	}else{
    		//����ɹ�
    		//�޸��û�״̬
    		existUser.setState(1);
    		existUser.setCode(null);
    		userService.update(existUser);
    		this.addActionMessage("����ɹ�����ȥ��¼");
    	}
		return "msg";
    	 
     }
     
     //��ת����¼����ķ���
     public String loginPage(){
		return "loginPage";
    	 
     }
     
     //��¼�ķ���
     
     public String login(){
    	//�ж���֤�����
     	//��session�л����֤������ֵ
     	String code=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
     	if(!checkcode.equalsIgnoreCase(code)){
     		this.addActionError("��֤���������");
     		return "checkLogincodeFail";
     	}
    	 
    	 //ģ�������Զ������û���������
    	 User existUser=userService.login(user);
    	 
    	 
    	 if(existUser ==null){
    		 //��¼ʧ��
    		 this.addActionError("��¼ʧ�ܣ��û���������������������û�δ���");
    		 return "loginError";
    		 
    	 }else{
    		 //��¼�ɹ�
    		 //���û�����Ϣ���뵽session��
    		 ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
    		 //ҳ����ת
    		 return "loginSuccess";
    		 
    	 }
    	 
     }
     
     
     //�˳��ķ���
     
     public String quit(){
    	 //����session
    	 ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
    	 
     }
     
}