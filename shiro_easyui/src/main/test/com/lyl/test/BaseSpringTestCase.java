package com.lyl.test;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.lyl.base.dao.IBaseDao;

/**
 * Spring集成测试通用父类
 */
@ContextConfiguration(locations = { "classpath:/spring-xml/spring-context.xml"})
public class BaseSpringTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	protected IBaseDao	baseDao;

	protected Logger	logger	= Logger.getLogger(getClass());

	public BaseSpringTestCase() {

	}

	//	protected void bindSecurityPrincipal(String principalName) {
	//		Subject subjectUnderTest = Mockito.mock(Subject.class);
	//		Mockito.when(subjectUnderTest.isAuthenticated()).thenReturn(true);
	//		Mockito.when(subjectUnderTest.getPrincipal()).thenReturn(principalName);
	//		new SubjectThreadState(subjectUnderTest).bind();
	//	}

}