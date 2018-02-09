package club.sking.core.dao.country;

import club.sking.core.bean.country.City;
import club.sking.core.query.country.CityQuery;

import java.util.List;

public interface CityDao {

	/**
	 * 添加
	 * @param city
	 */
	public Integer addCity(City city);

	/**
	 * 根据主键查找
	 * @param cityQuery
	 */
	public City getCityByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param cityQuery
	 */
	public List<City> getCitysByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * @param cityQuery
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * @param cityQuery
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * @param cityQuery
	 */
	public Integer updateCityByKey(City city);

	/**
	 * 分页查询
	 * @param cityQuery
	 */
	public List<City> getCityListWithPage(CityQuery cityQuery);

	/**
	 * 集合查询
	 * @param cityQuery
	 */
	public List<City> getCityList(CityQuery cityQuery);
	
	/**
	 * 总条数
	 * @param cityQuery
	 */
	public int getCityListCount(CityQuery cityQuery);
}
