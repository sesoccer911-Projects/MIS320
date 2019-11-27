/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrental;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author sgreen
 */
@ManagedBean
@Named(value = "filmController")
@SessionScoped
public class FilmController implements Serializable {

    int startId;
    int endId;
    DataModel filmTitles;
    FilmHelper helper;
    private int recordCount = 1000;
    private int pageSize = 10;
    int catId;
    private Film current;
    private int selectedItemIndex;

    /**
     * Creates a new instance of FilmController
     */
    public FilmController() {
        helper = new FilmHelper();
        startId = 1;
        endId = 10;
    }

    public FilmController(int startId, int endId) {
        helper = new FilmHelper();
        this.startId = startId;
        this.endId = endId;
    }

    public FilmController(int catId) {
        helper = new FilmHelper();
        startId = 1;
        endId = 10;
        this.catId = catId;
    }

    public Film getSelected() {
        if (current == null) {
            current = new Film();
            selectedItemIndex = -1;
        }
        return current;
    }

    public DataModel getFilmTitles() {
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitles(startId, endId));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByAction() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(1));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByAnimation() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(2));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByChildren() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(3));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByClassics() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(4));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByComedy() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(5));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByDocumentary() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(6));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByDrama() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(7));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByFamily() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(8));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByForeign() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(9));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByGames() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(10));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByHorror() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(11));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByMusic() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(12));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByNew() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(13));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByScifi() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(14));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesBySports() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(15));
        }
        return filmTitles;
    }

    public DataModel getFilmTitlesByTravel() {
        recreateModel();
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getFilmTitlesByCat(16));
        }
        return filmTitles;
    }

    void recreateModel() {
        filmTitles = null;
    }
//these methods are used to display the table and navigate the pages.
//The methods that return "index" or "browse" will prompt the JSF navigation handler to try to open a page named index.xhtml or browse.xhtml. 

    public boolean isHasNextPage() {
        if (endId + pageSize <= recordCount) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousPage() {
        if (startId - pageSize > 0) {
            return true;
        }
        return false;
    }

    public String next() {
        startId = endId + 1;
        endId = endId + pageSize;
        recreateModel();
        return "index";
    }

    public String previous() {
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return "index";
    }

    public int getPageSize() {
        return pageSize;
    }

    public String prepareView() {
        current = null;
        current = (Film) getFilmTitles().getRowData();
        return "browse";
    }
    
    public Film prepareFilm(){
        current = (Film) getFilmTitles().getRowData();
        return current;
    }

    //public void addToCart() {
        /*current = (Film) getFilmTitles().getRowData();
        boolean test = helper.addFilmToCart(current);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        try{
        externalContext.redirect("cart.jsp");
    }
        catch(Exception e){
            e.printStackTrace();
        }*/
    //}

    public String prepareList() {
        recreateModel();
        return "index";
    }
//the following methods access the helper class and retrieves additional film details

    public String getLanguage() {
        int langID = current.getLanguageByLanguageId().getLanguageId().intValue();
        String language = helper.getLangByID(langID);
        return language;
    }

    public String getActors() {
        List actors = helper.getActorsByID(current.getFilmId());
        StringBuffer totalCast = new StringBuffer();
        for (int i = 0; i < actors.size(); i++) {
            Actor actor = (Actor) actors.get(i);
            totalCast.append(actor.getFirstName());
            totalCast.append(" ");
            totalCast.append(actor.getLastName());
            totalCast.append("  ");
        }
        return totalCast.toString();
    }

    public String getCategory() {
        Category category = helper.getCategoryByID(current.getFilmId());
        return category.getName();
    }
}
