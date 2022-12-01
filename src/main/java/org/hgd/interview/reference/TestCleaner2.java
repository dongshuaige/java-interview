package org.hgd.interview.reference;

import jdk.internal.ref.Cleaner;
import org.hgd.interview.utils.LoggerUtils;

import java.io.IOException;

public class TestCleaner2 {
    public static void main(String[] args) throws IOException {
        Cleaner cleaner1 = Cleaner.create(new MyResource(), ()-> LoggerUtils.get().debug("clean 1"));
        Cleaner cleaner2 = Cleaner.create(new MyResource(), ()-> LoggerUtils.get().debug("clean 2"));
        Cleaner cleaner3 = Cleaner.create(new MyResource(), ()-> LoggerUtils.get().debug("clean 3"));
        Cleaner cleaner4 = Cleaner.create(new MyResource(), ()-> LoggerUtils.get().debug("clean 4"));

        System.gc();
        System.in.read();
    }

    static class MyResource {

    }
}
