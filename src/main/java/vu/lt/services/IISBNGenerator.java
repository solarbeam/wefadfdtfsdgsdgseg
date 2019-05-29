package vu.lt.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface IISBNGenerator {

    Future<Integer> generateISBN() throws ExecutionException, InterruptedException;
}
