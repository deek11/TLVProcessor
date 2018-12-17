package com.dino.processor;

import com.google.inject.Inject;

import javax.inject.Named;

/**
 * TLV Processor
 */
public class TLVProcessor implements Processor {

    private final int lengthProp;
    private final String replaceString;
    private String output;

    @Inject
    TLVProcessor(@Named("TLVPROCESSOR_LENGTH") int lengthProp, @Named("TLVPROCESSOR_REPLACE_STR") String replaceString){
        this.lengthProp = lengthProp;
        this.replaceString = replaceString;
    }

    /**
     * Processes input
     * @param input
     * @return
     */
    public String process(String...input){

        String iType = input[0];
        String iLength = input[1];
        String iValue = input[2];

        StringBuilder sb = new StringBuilder();


        //Validation
        if(!validate(iType,iLength,iValue))
            return output;

        //Processing
        ProcessType processType = ProcessType.valueOf(iType);

        sb.append(processType);
        sb.append("-");

        switch (processType){
            case REPLCE:
                sb.append(replaceString);
                break;

            case UPPRCS:
                sb.append(iValue.toUpperCase());
                break;
        }

        output = sb.toString();
        return output;

    }


    /**
     * Validates the input before processing
     * @param iType
     * @param iLength
     * @param iValue
     * @return
     */
    private boolean validate(String iType, String iLength, String iValue){
        return validateType(iType);
    }

    /**
     * Validates Process Type
     * @param iType
     * @return
     */

    private boolean validateType(String iType){
        try{
            ProcessType processType = ProcessType.valueOf(iType);
            return true;
        } catch (IllegalArgumentException ex){
            output = "Type not valid";
            return false;
        }
    }



}
