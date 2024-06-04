package com.riwi.Surveys_Riwi.utils.messages;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ErrorMessages {
    public static final String RequiredName= "Name is required";
    public static final String RequiredEmail= "Email is required";
    public static final String RequiredPassword= "Password is required";
    public static final String RequiredActive= "Active is required to be TRUE";
    public static final String RequiredTitle= "The title already exists, enter a new one";
    public static final String RequiredDate= "Creation date is required";
    public static final String RequiredText = "Text is required";            
    public static final String RequiredType= "Type is required";    
           
    public static String idNotFound(String entity){
       final String message = "There are no records with entity %s with the supplied id";
       return String.format(message, entity);
    }
}
