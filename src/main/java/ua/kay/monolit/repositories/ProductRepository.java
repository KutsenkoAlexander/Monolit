package ua.kay.monolit.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.models.FullProduct;
import ua.kay.monolit.models.Product;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Async
    @Query("select p from FullProduct p where p.productByProductId.categoryId = ?1")
    Page<FullProduct> findByCategoryId(Integer categoryId, Pageable pageable);

    @Async
    @Query("select p from FullProduct p where p.productId = ?1")
    FullProduct findByIdProduct(Long productId);

    @Async
    @Query("select p.sprConsumerByConsumerId from Product p where p.categoryId = ?1 group by p.sprConsumerByConsumerId")
    List<Product> findConsumerByCategoryId(Integer categoryId);

    @Async
    @Query("select p.sprTypeByTypeId from Product p where p.categoryId = ?1 group by p.sprTypeByTypeId")
    List<Product> findTypeProductByCategoryId(Integer categoryId);

    @Async
    @Query("select p.sprSizeBySizeId from Product p where p.categoryId = ?1 group by p.sprSizeBySizeId")
    List<Product> findSizeProductByCategoryId(Integer categoryId);

    @Async
    @Query("select p.sprColorByColorId from Product p where p.categoryId = ?1 group by p.sprColorByColorId")
    List<Product> findColorProductByCategoryId(Integer categoryId);

    @Async
    @Query("select p.price from Product p where p.categoryId = ?1")
    List<Product> findPricesProductByCategoryId(Integer categoryId);

    @Async
    @Query("select p.exist from Product p where p.categoryId = ?1")
    List<Product> findExistProductByCategoryId(Integer categoryId);

    @Async
    @Query("select p from FullProduct p where (p.productByProductId.categoryId = ?1 or ?1 is null) " +
                                    "and (p.productByProductId.consumerId = ?2  or ?2 is null) " +
                                    "and (p.productByProductId.typeId = ?3 or ?3 is null) " +
                                    "and (p.productByProductId.sizeId = ?4 or ?4 is null) " +
                                    "and (p.productByProductId.colorId = ?5 or ?5 is null) " +
                                    "and (p.productByProductId.exist = ?6 or ?6 is null)")
    Page<FullProduct> sortProduct(Integer categoryId, Long consumerId, Integer typeId, Integer sizeId, Integer colorId, Byte exist, Pageable pageable);

    @Async
    @Query("select p from Product p where p.title like %?1%")
    List<Product> findTitleLikeName(String name);

}
