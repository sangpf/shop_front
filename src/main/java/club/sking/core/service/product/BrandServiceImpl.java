package club.sking.core.service.product;

import club.sking.common.page.Pagination;
import club.sking.core.bean.product.Brand;
import club.sking.core.dao.product.BrandDao;
import club.sking.core.query.product.BrandQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌事务
 * @author lx
 *
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService{
	
	@Resource
	private BrandDao brandDao;

	@Transactional(readOnly = true)
	public Pagination getBrandListWithPage(Brand brand){
		//1:起始页  startRow = (pageNo - 1)*pageSize
		//2:每页数
		//3:总记录数
		Pagination pagination = new Pagination(brand.getPageNo(),brand.getPageSize(),brandDao.getBrandCount(brand));
		//Brand集合
		pagination.setList(brandDao.getBrandListWithPage(brand));
		
		return pagination;
	}

	public void addBrand(Brand brand) {
		brandDao.addBrand(brand);
	}

	public void deleteBrandByKey(Integer id) {
		brandDao.deleteBrandByKey(id);
	}

	public void deleteBrandByKeys(Integer[] ids) {
		brandDao.deleteBrandByKeys(ids);
		
	}

	public void updateBrandByKey(Brand brand) {
		brandDao.updateBrandByKey(brand);
		
	}

	public Brand getBrandByKey(Integer id) {
		// TODO Auto-generated method stub
		return brandDao.getBrandByKey(id);
	}

	public List<Brand> getBrandList(BrandQuery brandQuery) {
		// TODO Auto-generated method stub
		return brandDao.getBrandList(brandQuery);
	}
}
