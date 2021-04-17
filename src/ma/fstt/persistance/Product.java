package ma.fstt.persistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false, unique=true)
	private Integer id_Product ;
	
	@Column(nullable=true, length=45)
	private String ref_Product;
	
	@Column(nullable=true, length=45)
	private String name_Product;
	
	@Column(nullable=true)
	private Integer quantity;
	
	@Column(nullable=true)
	private Float price;
	
	@Column(nullable=true, length=500)
	private String description_Prod;
	
	@ManyToOne
	@JoinColumn(name="id_cat")
	private Category cat;

	public void setId_product(Integer idProduct) {
		this.id_Product = idProduct;
	}


	public Product() {
		super();
	}
	
	

	
	public Product(Integer idProduct, String refProduct, String nameProduct, Integer quantity, Float price,
			String descriptionProd, Category cat) {
		super();
		this.id_Product = idProduct;
		this.ref_Product = refProduct;
		this.name_Product = nameProduct;
		this.quantity = quantity;
		this.price = price;
		this.description_Prod = descriptionProd;
		this.cat = cat;
	}



	public Integer getIdProduct() {
		return id_Product;
	}

	public String getRefProduct() {
		return ref_Product;
	}



	public void setRefProduct(String refProduct) {
		this.ref_Product = refProduct;
	}



	public String getNameProduct() {
		return name_Product;
	}



	public void setNameProduct(String nameProduct) {
		this.name_Product = nameProduct;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public Float getPrice() {
		return price;
	}



	public void setPrice(Float price) {
		this.price = price;
	}



	public String getDescriptionProd() {
		return description_Prod;
	}



	public void setDescriptionProd(String descriptionProd) {
		this.description_Prod = descriptionProd;
	}


	public Category getCat() {
		return cat;
	}


	public void setCat(Category cat) {
		this.cat = cat;
	}


	public void setIdProduct(Integer idProduct) {
		this.id_Product = idProduct;
	}



	
	
}