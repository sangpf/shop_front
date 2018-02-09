package club.sking.core.service.product;

import club.sking.common.page.Pagination;
import club.sking.core.bean.product.Color;
import club.sking.core.dao.product.ColorDao;
import club.sking.core.query.product.ColorQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 颜色
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class ColorServiceImpl implements ColorService {

	@Resource
	ColorDao colorDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addColor(Color color) {
		return colorDao.addColor(color);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Color getColorByKey(Integer id) {
		return colorDao.getColorByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Color> getColorsByKeys(List<Integer> idList) {
		return colorDao.getColorsByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return colorDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return colorDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateColorByKey(Color color) {
		return colorDao.updateColorByKey(color);
	}
	
	@Transactional(readOnly = true)
	public Pagination getColorListWithPage(ColorQuery colorQuery) {
		Pagination p = new Pagination(colorQuery.getPageNo(),colorQuery.getPageSize(),colorDao.getColorListCount(colorQuery));
		p.setList(colorDao.getColorListWithPage(colorQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Color> getColorList(ColorQuery colorQuery) {
		return colorDao.getColorList(colorQuery);
	}
}