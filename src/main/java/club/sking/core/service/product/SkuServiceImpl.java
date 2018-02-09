package club.sking.core.service.product;

import club.sking.common.page.Pagination;
import club.sking.core.bean.product.Product;
import club.sking.core.bean.product.Sku;
import club.sking.core.dao.product.SkuDao;
import club.sking.core.query.product.SkuQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 最小销售单元事务层
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class SkuServiceImpl implements SkuService {

	@Resource
	SkuDao skuDao;
	@Resource
	ColorService colorService;
	@Resource
	ProductService productService;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addSku(Sku sku) {
		return skuDao.addSku(sku);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Sku getSkuByKey(Integer id) {
		Sku sku = skuDao.getSkuByKey(id);
		//通过商品ID
		Product product = productService.getProductByKey(sku.getProductId());
		
		sku.setProduct(product);
		//颜色加载
		sku.setColor(colorService.getColorByKey(sku.getColorId()));
		
		return sku;
	}
	
	@Transactional(readOnly = true)
	public List<Sku> getSkusByKeys(List<Integer> idList) {
		return skuDao.getSkusByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return skuDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return skuDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateSkuByKey(Sku sku) {
		return skuDao.updateSkuByKey(sku);
	}
	
	@Transactional(readOnly = true)
	public Pagination getSkuListWithPage(SkuQuery skuQuery) {
		Pagination p = new Pagination(skuQuery.getPageNo(),skuQuery.getPageSize(),skuDao.getSkuListCount(skuQuery));
		p.setList(skuDao.getSkuListWithPage(skuQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Sku> getSkuList(SkuQuery skuQuery) {
		List<Sku> skus = skuDao.getSkuList(skuQuery);
		//颜色加载完结
		for(Sku sku : skus){
			sku.setColor(colorService.getColorByKey(sku.getColorId()));
		}
		return skus;
	}

	public List<Sku> getStock(Integer productId) {
		// TODO Auto-generated method stub
		List<Sku> skus = skuDao.getStock(productId);
		//颜色加载完结
		for(Sku sku : skus){
			sku.setColor(colorService.getColorByKey(sku.getColorId()));
		}
		return skus;
	}
}
