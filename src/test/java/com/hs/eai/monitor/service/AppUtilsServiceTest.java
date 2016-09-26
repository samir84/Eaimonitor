package com.hs.eai.monitor.service;

import org.junit.Assert;
import org.junit.Test;

public class AppUtilsServiceTest {

	@Test
	public void totatlSteps(){
		
		Assert.assertEquals(10, (new AppUtilsService().getMaxPages(199, 25)));
		
		Assert.assertEquals(5, (new AppUtilsService().getMaxPages(123, 25)));
	}
}
