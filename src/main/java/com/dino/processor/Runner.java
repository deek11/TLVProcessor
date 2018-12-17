package com.dino.processor;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Runner {

    private static final String SPLITTER = "-";
    private static final String TLV_INPUT_REGEX = "[A-Z]{6}-\\d{4}-\\w+";

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new BasicModule());
        Processor processor = injector.getInstance(Processor.class);

        if(checkArgs(args)){

            String[] arr = args[0].split(SPLITTER);
            String iType = arr[0];
            String iLength = arr[1];
            String iValue = arr[2];

            System.out.println(processor.process(iType, iLength, iValue));
        }

    }

    /**
     * Validate runtime args
     * @param args
     * @return
     */
    private static boolean checkArgs(String[] args){
        if(args==null || !args[0].matches(TLV_INPUT_REGEX)){
            System.out.println("Please enter valid input - Format: TYPE(6)-LENGTH(4)-VALUE");
            return false;
        }
        return true;
    }
}
