package club.sking.core.service.product;

import club.sking.common.page.Pagination;
import club.sking.core.bean.product.Img;
import club.sking.core.dao.product.ImgDao;
import club.sking.core.query.product.ImgQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class ImgServiceImpl implements ImgService {

	@Resource
	ImgDao imgDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addImg(Img img) {
		return imgDao.addImg(img);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Img getImgByKey(Integer id) {
		return imgDao.getImgByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Img> getImgsByKeys(List<Integer> idList) {
		return imgDao.getImgsByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return imgDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return imgDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateImgByKey(Img img) {
		return imgDao.updateImgByKey(img);
	}
	
	@Transactional(readOnly = true)
	public Pagination getImgListWithPage(ImgQuery imgQuery) {
		Pagination p = new Pagination(imgQuery.getPageNo(),imgQuery.getPageSize(),imgDao.getImgListCount(imgQuery));
		p.setList(imgDao.getImgListWithPage(imgQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Img> getImgList(ImgQuery imgQuery) {
		return imgDao.getImgList(imgQuery);
	}
}