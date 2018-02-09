package club.sking.core.service.product;

import club.sking.common.page.Pagination;
import club.sking.core.bean.product.Feature;
import club.sking.core.query.product.FeatureQuery;

import java.util.List;

/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午01:50:28]
 */
public interface FeatureService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	public Integer addFeature(Feature feature);

	/**
	 * 根据主键查询
	 */
	public Feature getFeatureByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	public List<Feature> getFeaturesByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * 
	 * @return
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateFeatureByKey(Feature feature);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param featureQuery
	 *            查询条件
	 * @return
	 */
	public Pagination getFeatureListWithPage(FeatureQuery featureQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param featureQuery
	 *            查询条件
	 * @return
	 */
	public List<Feature> getFeatureList(FeatureQuery featureQuery);
}
