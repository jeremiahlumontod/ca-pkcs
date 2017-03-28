/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jml.utils;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author t-jmlumontod
 */
public class DumpCAPrivateKey {

    public static void main(String[] args) throws Exception {
        /**
        final String keystoreName = args[0];
        final String keystorePassword = args[1];
        final String alias = args[2];
        */
    	
    	String encodedText;
    	
        try {
        	
	        //final String keystoreName = "/Users/jeremiahlumontod/Desktop/folders/projs/refs/ca/clientcert/jmlclient.jks";
        	final String keystoreName = "/Users/jeremiahlumontod/Desktop/folders/projs/refs/ca/servercert/jmlserver.jks";
	        final String keystorePassword = "123456";
	        //final String alias = "jmlclientkey";
	        final String alias = "jmlserverkey";
	        java.security.KeyStore ks = java.security.KeyStore.getInstance("jks");
	        ks.load(new java.io.FileInputStream(keystoreName), keystorePassword.toCharArray());
	        System.out.println("-----BEGIN PRIVATE KEY-----");
	        System.out.println(new sun.misc.BASE64Encoder().encode(ks.getKey(alias, keystorePassword.toCharArray()).getEncoded()));
	        //encodedText = new String(Base64.encodeBase64(ks.getKey(alias, keystorePassword.toCharArray()).getEncoded()));
	        //System.out.println(encodedText);
	        System.out.println("-----END PRIVATE KEY-----");
        
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}

