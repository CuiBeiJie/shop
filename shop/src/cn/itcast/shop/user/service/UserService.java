package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.MailUitls;
import cn.itcast.shop.utils.UUIDUtils;

/**
 * 用户模块业务层代码
 * @author Administrator
 *
 */
@Transactional
public class UserService {
     //注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//按用户名查询用户的方法
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	/**
	 * 用户注册的方法
	 */
	public void save(User user) {
		// TODO Auto-generated method stub
		user.setState(0);//0:代表用户未激活  1代表用户已激活
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailUitls.sendMail(user.getEmail(), code);
	}
	/**
	 * 根据激活码查询用户
	 * @return 
	 */
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}
	//业务层修改用户状态
	public void update(User existUser) {
		// TODO Auto-generated method stub
		userDao.update(existUser);
	}
	public User login(User user) {
		// TODO Auto-generated method stub
		
		return userDao.login(user);
	}
	
}
