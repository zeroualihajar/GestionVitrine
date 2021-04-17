package ma.fstt.bean;


import java.io.IOException;
import java.util.List;
import java.util.Map;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ma.fstt.persistance.Category;
import ma.fstt.persistance.DatabaseOperation;
import ma.fstt.persistance.Product;

@ManagedBean(name = "ProductBean")
@SessionScoped
public class ProductBean {

	private Integer id_product = 0;
	private String ref_product;
	private String name_product;
	private Integer quantity;
	private float price;
	private String description_prod;
	private Category cat;
	
	private int idc;

	public int getIdc() {
		return idc;
	}

	public void setIdc(int idc) {
		this.idc = idc;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public Integer getId_product() {
		return id_product;
	}

	public void setId_product(Integer id_product) {
		this.id_product = id_product;
	}

	public String getRef_product() {
		return ref_product;
	}

	public void setRef_product(String ref_product) {
		this.ref_product = ref_product;
	}

	public String getName_product() {
		return name_product;
	}

	public void setName_product(String name_product) {
		this.name_product = name_product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription_prod() {
		return description_prod;
	}

	public void setDescription_prod(String description_prod) {
		this.description_prod = description_prod;
	}

	public Category getCat() {
		return cat;
	}

	public int getIdCat(int idCat) {
		
		System.out.println("GetIdCat : "+idCat);
		
		return DatabaseOperation.getCategoryById(idCat).getIdCat();
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	

	public String redirectUpdate(int idProd){
	
		System.out.println("idProd:  "+idProd);
		Product p =DatabaseOperation.getProductById(idProd);
		
		this.name_product = p.getNameProduct();
		this.ref_product = p.getRefProduct();
		this.quantity = p.getQuantity();
		this.price = p.getPrice();
		this.description_prod = p.getNameProduct();
		this.cat = p.getCat();

		return "updateProduct.xhtml?faces-redirect=true&idProd="+idProd;
	}
	
	
	public String redirectDelete(int idProd)
	{
		System.out.println("idProd : "+idProd);
		Product p =DatabaseOperation.getProductById(idProd);
		return  DatabaseOperation.deleteProduct(p.getIdProduct());
	}


	public String save(ProductBean bean) throws IOException {
		
		
		
		bean.cat =DatabaseOperation.getCategoryById(getIdc());
		
		return DatabaseOperation.createProduct(bean.getRef_product(), bean.getName_product(), bean.getQuantity(),

				bean.getPrice(), bean.getDescription_prod(), bean.getCat());


	}

	@SuppressWarnings("unchecked")
	public List<Product> listProducts(int id) {

		this.cat =DatabaseOperation.getCategoryById(id);
		System.out.println("ListProducts");
		return DatabaseOperation.getAllProducts(id);

	}
	
	@SuppressWarnings("unchecked")
	public List<Product> listProducts() {

		return DatabaseOperation.getAllProducts();

	}
	
	public String redirectAddProd()
	{
		return "addProd.xhtml?faces-redirect=true";
	}
	
	public List listCategory()
	{
		return DatabaseOperation.getAllCategory();
	}
	
	public String updateProduct(ProductBean bean) throws IOException
	{
		Map<String, String> params =FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
		
		Product prod = new Product(Integer.parseInt(params.get("idProd")), bean.ref_product, bean.name_product , bean.quantity, bean.price, bean.description_prod, bean.cat);
		return DatabaseOperation.updateProduct(prod);
	}

}
