package club.sking.core.dao.user;

import club.sking.core.bean.user.Buyer;
import club.sking.core.query.user.BuyerQuery;

import java.util.List;

public interface BuyerDao {

	/**
	 * 添加
	 * @param buyer
	 */
	public Integer addBuyer(Buyer buyer);

	/**
	 * 根据主键查找
	 * @param buyerQuery
	 */
	public Buyer getBuyerByKey(String id);

	/**
	 * 根据主键批量查找
	 * @param buyerQuery
	 */
	public List<Buyer> getBuyersByKeys(List<String> idList);

	/**
	 * 根据主键删除
	 * @param buyerQuery
	 */
	public Integer deleteByKey(String id);

	/**
	 * 根据主键批量删除
	 * @param buyerQuery
	 */
	public Integer deleteByKeys(List<String> idList);

	/**
	 * 根据主键更新
	 * @param buyerQuery
	 */
	public Integer updateBuyerByKey(Buyer buyer);

	/**
	 * 分页查询
	 * @param buyerQuery
	 */
	public List<Buyer> getBuyerListWithPage(BuyerQuery buyerQuery);

	/**
	 * 集合查询
	 * @param buyerQuery
	 */
	public List<Buyer> getBuyerList(BuyerQuery buyerQuery);
	
	/**
	 * 总条数
	 * @param buyerQuery
	 */
	public int getBuyerListCount(BuyerQuery buyerQuery);
}
