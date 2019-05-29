package vu.lt.rest;


import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Book;
import vu.lt.entities.Library;
import vu.lt.persistence.BooksDAO;
import vu.lt.persistence.LibrariesDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.stream.JsonParsingException;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/books")
public class BooksController {

    @Inject
    @Getter @Setter
    private BooksDAO booksDAO;

    @Inject
    @Getter @Setter
    private LibrariesDAO librariesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Book book = booksDAO.findOne(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setISBN(book.getISBN());
        bookDto.setLibraryName(book.getLibrary().getName());

        return Response.ok(bookDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer bookId, BookDto bookDto) {
        try{
            Book existingBook = booksDAO.findOne(bookId);
            if(existingBook == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingBook.setName(bookDto.getName());
            existingBook.setISBN(bookDto.getISBN());
            booksDAO.update(existingBook);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(BookDto bookDto) {
        try{
            Book newBook = new Book();
            newBook.setName(bookDto.getName());
            newBook.setISBN(bookDto.getISBN());
            final List<Library> libraries = librariesDAO.loadAll();
            for(Library l : libraries){
                if(l.getName().equals(bookDto.getLibraryName())){
                    newBook.setLibrary(l);
                    break;
                }
            }
            booksDAO.persist(newBook);
            return Response.ok().build();
        } catch (JsonParsingException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("This is an invalid request. The field " + e.getCause() + " is not recognized by the system.")
                    .type( MediaType.TEXT_PLAIN)
                    .build();
        }
    }
}
