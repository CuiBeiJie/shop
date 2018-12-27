package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.MailUitls;
import cn.itcast.shop.utils.UUIDUtils;

/**
 * �û�ģ��ҵ������
 * @author Administrator
 *
 */
@Transactional
public class UserService {
     //ע��UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//���û�����ѯ�û��ķ���
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	/**
	 * �û�ע��ķ���
	 */
	public void save(User user) {
		// TODO Auto-generated method stub
		user.setState(0);//0:�����û�δ����  1�����û��Ѽ���
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//���ͼ����ʼ�
		MailUitls.sendMail(user.getEmail(), code);
	}
	/**
	 * ���ݼ������ѯ�û�
	 * @return 
	 */
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}
	//ҵ����޸��û�״̬
	public void update(User existUser) {
		// TODO Auto-generated method stub
		userDao.update(existUser);
	}
	public User login(User user) {
		// TODO Auto-generated method stub
		
		return userDao.login(user);
	}
	
}
