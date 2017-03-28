package org.jml.utils;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.*;

public class ExportPrivateKey {
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
		export.keystoreFile = new File("/Users/jeremiahlumontod/Desktop/folders/projs/refs/ca/clientcert/jmlclient.jks");
		export.keyStoreType = "jks";
		export.keyStorePassword = "123456".toCharArray();
		export.alias = "jmlclientkey";
		export.keyPassword = "123456".toCharArray();
		export.exportedFile = new File("/Users/jeremiahlumontod/Desktop/folders/projs/refs/ca/clientcert/jmlclientkey.pem");
		export.export();
	}
}