package vu.lt.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Decorator
public abstract class ISBNGeneratorDecorator implements IISBNGenerator, Serializable {
    @Inject
    @Delegate
    @Any
    ISBNGeneratorAlt isbnGeneratorAlt;

    public Future<Integer> generateISBN() throws ExecutionException, InterruptedException {
        Future<Integer> result = isbnGeneratorAlt.generateISBN();
        if (result != null && result.isDone()) {
            if (result.get() < 0) {
                System.out.println("Warning: property \"ISBN\" of entity \"Book\" is out of range.");
            }
        }
        return result;
    }
}
