package vu.lt.usecases;

import vu.lt.interceptors.LoggedInvocation;
import vu.lt.services.IISBNGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateISBN implements Serializable {
    @Inject
    IISBNGenerator isbnGenerator;

    private Future<Integer> isbnGenerationTask = null;

    @LoggedInvocation
    public String generateISBN() throws ExecutionException, InterruptedException {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        isbnGenerationTask = isbnGenerator.generateISBN();
        return  "/bookDetails.xhtml?faces-redirect=true&bookId=" + requestParameters.get("bookId");
    }

    public boolean isISBNGenerationRunning(){
        return isbnGenerationTask != null && !isbnGenerationTask.isDone();
    }

    public String getISBNGenerationStatus() throws ExecutionException, InterruptedException{
        if(isbnGenerationTask == null){
            return null;
        } else if (isISBNGenerationRunning()){
            return "Task in progress, please wait";
        }
        return "Generated ISBN: " + isbnGenerationTask.get();
    }
}
