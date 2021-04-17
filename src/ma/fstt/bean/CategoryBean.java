package ma.fstt.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.google.protobuf.ByteString.Output;

import ma.fstt.persistance.Category;
import ma.fstt.persistance.DatabaseOperation;

@ManagedBean(name = "CategoryBean")
@ViewScoped
public class CategoryBean {
	
	private int id_cat;
	private String name_cat;
	private Part image;
	private StreamedContent img;
	
	
	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public String getName_cat() {
		return name_cat;
	}
	public void setName_cat(String name_cat) {
		this.name_cat = name_cat;
	}
	
	
	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

	public StreamedContent getImg() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            this.img = new DefaultStreamedContent();
            return this.img;
        } 
        else {
            String imageId = context.getExternalContext().getRequestParameterMap().get("idCat");
            System.out.println("IdCat : " + imageId);
            byte[] myImage = null;
            for(Category cat : listCategory()) {
            	if(cat.getIdCat() == Integer.valueOf(imageId)) {
            		myImage = cat.getImageCat();
            		break;
            	}
            }
            this.img = new DefaultStreamedContent(new ByteArrayInputStream(myImage));
            return this.img;
        }
    }

	public void setImg(StreamedContent img) {
		this.img = img;
	}

	
	

	//---------------------------------------
	public String redirectUpdate(int idCat) {
		
		return "updateCategory.xhtml?faces-redirect=true&idCat="+idCat;

	}

	
	//---------------------------------------
	public String redirectProduct(int idCat)
	{
		return "showListProd.xhtml?faces-redirect=true&idCat="+idCat;
	}
	
	//---------------------------------------
	public String redirectDelete(int idCat)
	{
		
		return DatabaseOperation.deleteCategory(idCat);
	}
	
	

	//---------------------------------------
	public List<Category> listCategory() 
	{
		return DatabaseOperation.getAllCategory();
	}
	public int setCategory(int id)
	{
		this.id_cat =DatabaseOperation.getCategoryById(id).getIdCat();
		this.name_cat = DatabaseOperation.getCategoryById(id).getNameCat();
		return this.id_cat;
	}
	
	public String save(CategoryBean bean) throws IOException {

		InputStream input = image.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[10240];
		for (int length = 0; (length = input.read(buffer)) > 0;) 
			output.write(buffer, 0, length);
		
		return DatabaseOperation.createCategory(bean.getName_cat(), output.toByteArray());

	}

	public String updateCategory(CategoryBean bean) throws IOException
	{
		InputStream input = image.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[10240];
		for (int length = 0; (length = input.read(buffer)) > 0;) 
			output.write(buffer, 0, length);
		
		Category cat = new Category(bean.id_cat, bean.name_cat, output.toByteArray());
		System.out.println("$$$$$$$$$$$$$$$$$$******************$"+cat.getIdCat());
		return DatabaseOperation.updateCategory(cat);
	}

}
