package test;

import app.pagesFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

public class BaseRunner {

    private  static ThreadLocal<pagesFactory> threadLocalPagesFactory = new ThreadLocal<>();
    static pagesFactory pagesFactory;

    @Before
    public void start() {
        if (threadLocalPagesFactory.get() != null) {
            pagesFactory = threadLocalPagesFactory.get();
            return;
        }
        pagesFactory = new pagesFactory();
        threadLocalPagesFactory.set(pagesFactory);
    }

    //Для единичных прогонов тестов
    /*@After
    public void close(){
        pagesFactory.quit();
        threadLocalPagesFactory.remove();
    }*/

    @AfterClass
    public static void tearDown() {
        pagesFactory.quit();
    }

}