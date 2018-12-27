package cn.itcast.shop.utils;

import java.util.UUID;

/**
 * 生成字符串的工具类
 * @author Administrator
 *
 */
public class UUIDUtils {
	/**
	 * @return
	 * 获得随机的字符串
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
