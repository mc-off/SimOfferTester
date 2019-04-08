package test;

import app.PagesFactory;
import org.junit.AfterClass;
import org.junit.Before;

public class BaseRunner {

    private  static ThreadLocal<PagesFactory> threadLocalPagesFactory = new ThreadLocal<>();
    static PagesFactory pagesFactory;

    @Before
    public void start() {
        if (threadLocalPagesFactory.get() != null) {
            pagesFactory = threadLocalPagesFactory.get();
            return;
        }
        pagesFactory = new PagesFactory();
        threadLocalPagesFactory.set(pagesFactory);
    }

    //Для единичных прогонов тестов
    /*@After
    public void close(){
        PagesFactory.quit();

    }*/

    @AfterClass
    public static void tearDown() {
        pagesFactory.quit();
    }

}