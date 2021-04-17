package ma.fstt.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ma.fstt.bean.CategoryBean;

public class DatabaseOperation {
	private static final String PERSISTENCE_UNIT_NAME = "unit";
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();

	// ------------------------- Product ------------------------
	public static String createProduct(String ref, String name, int qty, float price, String des, Category cat) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Product prod = new Product(0, ref, name, qty, price, des, cat);
		entityMgrObj.persist(prod);
		transactionObj.commit();
		return "product.xhtml?faces-redirect=true";

	}

	//----------------List of products---------------
	@SuppressWarnings("unchecked")
	public static List getAllProducts(Integer idCat) {
		
		Category cat = entityMgrObj.find(Category.class, idCat);

		Query queryObj = entityMgrObj.createQuery("SELECT p FROM Product p WHERE p.cat.idCat =:idCat" );
		queryObj.setParameter("idCat", idCat);
		List<Product> pList = queryObj.getResultList();
		if (pList != null && pList.size() > 0) {
			return pList ;
		} else {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static List getAllProducts() {
		
		Query queryObj = entityMgrObj.createQuery("SELECT p FROM Product p" );
		List<Product> pList = queryObj.getResultList();
		if (pList != null && pList.size() > 0) {
			return pList ;
		} else {
			return null;
		}
	}
	
	
	//--------------get product---------------
	public static Product getProductById(int id)
	{
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Product prod = entityMgrObj.find(Product.class, id);
		return prod;
	}
	
	//------------- delete Product ------------
	public static String deleteProduct(int id) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Product prod = entityMgrObj.find(Product.class, id);
		int idCat = prod.getCat().getIdCat();
		entityMgrObj.remove(prod);
		transactionObj.commit();
	
		return "product.xhtml?faces-redirect=true&";
	}
	
	

	//---------updateProduct
	
	public static String updateProduct(Product prod )
	{
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
		
		Query queryObj = entityMgrObj.createQuery("update Product p SET p.ref_Product = '"+prod.getRefProduct()+"', p.name_Product = '"+prod.getNameProduct()+
				"' , p.quantity = '"+prod.getQuantity()+"' , p.price = '"+prod.getPrice()+"' , p.description_Prod = '"+prod.getDescriptionProd()+
				"' where p.id_Product = "+prod.getIdProduct());

		queryObj.executeUpdate();

		
		transactionObj.commit();
		return "product.xhtml?faces-redirect=true";	
	}
	

	// ------------------------- Category ------------------------
	// ---------- Create -----------
	public static String createCategory(String name, byte[] image) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Category cat = new Category(0, name, image);
		

		entityMgrObj.persist(cat);
		transactionObj.commit();
		return "index.xhtml?faces-redirect=true";
	}

	// list all category
	@SuppressWarnings("unchecked")	
		public static List<Category> getAllCategory() {
			Query queryObj = entityMgrObj.createQuery("SELECT c FROM Category c");
			
			List<Category> lList = queryObj.getResultList();
			if (lList != null && lList.size() > 0) {
				return lList;
			} else {
				return null;
			}
		}
	
	//------- getById ---------
	public static String getById(int idCat)
	{		
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Category cat = entityMgrObj.find(Category.class, idCat);
		transactionObj.commit();
		return "updateCategory.xhtml?faces-redirect=true&Cat=" + cat;
	}

	/*----------------get category by id -------------*/
	public static Category getCategoryById(int id) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Category cat = entityMgrObj.find(Category.class, id);
		return cat;
	}

	// --------- Delete Category ----------
	public static String deleteCategory(int id) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Category cat = entityMgrObj.find(Category.class, id);
		entityMgrObj.remove(cat);
		transactionObj.commit();

		return "index.xhtml?faces-redirect=true";
	}
	/*----------------update category -------------*/
	public static String updateCategory(Category cat )
	{
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
		
		Query queryObj = entityMgrObj.createQuery("update Category c SET c.nameCat = '"+cat.getNameCat()+"' , c.imageCat = '"+cat.getImageCat()+"' where c.idCat = "+cat.getIdCat());

		queryObj.executeUpdate();

		
		transactionObj.commit();
		return "index.xhtml?faces-redirect=true";	
	}
}
