package ma.fstt.persistance;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false, unique=true)
	private Integer idCat;
	
	@Column(nullable=true, length=45)
	private String nameCat;
	
	@Column(nullable = true, length=52528800)
	@Lob
	private byte[] imageCat;
	
	@OneToMany(mappedBy = "cat")
	@JoinColumn(name="id_cat")
	private List<Product> listProd;
	
	public List<Product> getListProd() {
		return listProd;
	}

	public void setListProd(List<Product> listProd) {
		this.listProd = listProd;
	}

	public Category() {
		super();
	}
	
	public Category(Integer idCat, String nameCat, byte[] image) {
		super();
		this.idCat = idCat;
		this.nameCat = nameCat;
		this.imageCat = image;
	}
	
	public Integer getIdCat() {
		return idCat;
	}
	public void setIdCat(Integer idCat) {
		this.idCat = idCat;
	}
	public String getNameCat() {
		return nameCat;
	}
	public void setNameCat(String nomCat) {
		this.nameCat = nomCat;
	}
	
	
	public byte[] getImageCat() {
		return imageCat;
	}

	public void setImageCat(byte[] imageCat) {
		this.imageCat = imageCat;
	}

	@Override
	public String toString() {
		return "Category [idCat=" + idCat + ", nameCat=" + nameCat + "]";
	}

	
}