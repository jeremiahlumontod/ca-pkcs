package org.jml.utils;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.*;

public class ExportPrivateKey {
	
    final static String keystoreName = "/Users/jeremiahlumontod/Desktop/folders/projs/refs/ca/clientcert/jmlclient.jks";
    final static String exportFileName = "/Users/jeremiahlumontod/Desktop/folders/projs/refs/ca/clientcert/jmlclientkey.pem";
    final static String keystorePassword = "123456";
    final static String aliasforkey = "jmlclientkey";
	final static String type = "jks";
	
	private File keystoreFile;
	private String keyStoreType;
	private char[] keyStorePassword;
	private char[] keyPassword;
	private String alias;
	private File exportedFile;

	public void export() throws Exception {
		KeyStore keystore = KeyStore.getInstance(keyStoreType);
		BASE64Encoder encoder = new BASE64Encoder();
		keystore.load(new FileInputStream(keystoreFile), keyStorePassword);
		Key key = keystore.getKey(alias, keyPassword);
		String encoded = encoder.encode(key.getEncoded());
		FileWriter fw = new FileWriter(exportedFile);
		fw.write("---BEGIN PRIVATE KEY---\n");
		fw.write(encoded);
		fw.write("\n");
		fw.write("---END PRIVATE KEY---");
		fw.close();
	}

	public static void main(String args[]) throws Exception {
		ExportPrivateKey export = new ExportPrivateKey();
		export.keystoreFile = new File(keystoreName);
		export.keyStoreType = type;
		export.keyStorePassword = keystorePassword.toCharArray();
		export.alias = aliasforkey;
		export.keyPassword = keystorePassword.toCharArray();
		export.exportedFile = new File(exportFileName);
		export.export();
	}
}