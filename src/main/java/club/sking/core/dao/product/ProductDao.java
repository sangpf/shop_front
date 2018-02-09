package club.sking.core.dao.product;

import club.sking.core.bean.product.Product;
import club.sking.core.query.product.ProductQuery;

import java.util.List;

public interface ProductDao {
	/**
	 * 添加
	 */
	public Integer addProduct(Product product);

	/**
	 * 根据主键查找
	 */
	public Product getProductByKey(Integer id);

	/**
	 * 根据主键批量查找
	 */
	public List<Product> getProductsByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 */
	public Integer updateProductByKey(Product product);

	/**
	 * 分页查询
	 */
	public List<Product> getProductListWithPage(ProductQuery productQuery);

	/**
	 * 集合查询
	 */
	public List<Product> getProductList(ProductQuery productQuery);
	
	/**
	 * 总条数
	 */
	public int getProductListCount(ProductQuery productQuery);
}
