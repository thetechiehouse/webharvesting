package com.jauntexample.sample;

import com.jaunt.*;
import com.jaunt.component.*;
import java.util.*;

public class SendPostRequest{
  public static void main(String[] args){
    try{
      UserAgent userAgent = new UserAgent();

      System.out.println("SENDING HEAD REQUEST...\n");
      userAgent.sendHEAD("http://jaunt-api.com/examples/hello.xml");   //send HTTP HEAD Request
      System.out.println("RESPONSE HEADERS:\n" + userAgent.response.getHeaders());
     
      System.out.println("SENDING POST REQUEST...\n");
      userAgent.sendPOST("http://tomcervenka.site90.com/handlePost.php",
        "username=tom&password=secret");                               //send HTTP POST Request with queryString
      System.out.println("DOCUMENT:\n" + userAgent.doc.innerXML());    //print retrieved Document
    }
    catch(JauntException e){
      System.err.println(e);
    }
  }
}
