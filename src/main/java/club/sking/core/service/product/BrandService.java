package club.sking.core.service.product;

import club.sking.common.page.Pagination;
import club.sking.core.bean.product.Brand;
import club.sking.core.query.product.BrandQuery;

import java.util.List;

/**
 * 品牌
 * @author lx
 *
 */
public interface BrandService {

	public Pagination getBrandListWithPage(Brand brand);
	
	//查询集合
	public List<Brand> getBrandList(BrandQuery brandQuery);
	
	//添加品牌
	public void addBrand(Brand brand);
	
	//删除
	public void deleteBrandByKey(Integer id);
	//删除 批量
	public void deleteBrandByKeys(Integer[] ids);//List<Integer>  ids
	//修改
	public void updateBrandByKey(Brand brand);
	
	//
	public Brand getBrandByKey(Integer id);
}
