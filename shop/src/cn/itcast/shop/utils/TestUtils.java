package cn.itcast.shop.utils;

import org.junit.Test;

public class TestUtils {
	@Test
	public void TestCeil(){
		int totalCount=1;
		int totalPage=0;
		int limit=8;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit +1;
		}
		System.out.println("totalPage="+totalPage);
	}

}
