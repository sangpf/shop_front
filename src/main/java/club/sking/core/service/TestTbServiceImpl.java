package club.sking.core.service;

import club.sking.core.bean.TestTb;
import club.sking.core.dao.TestTbDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 
 * @author lx
 *
 */
@Service
@Transactional
public class TestTbServiceImpl implements TestTbService{

	@Resource
	private TestTbDao testTbDao;
	
	public void addTestTb(TestTb testTb) {
		testTbDao.addTestTb(testTb);
		
		throw new RuntimeException();
	}

}
