package org.jml.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Console;
import java.util.Enumeration;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class KeyStoreShowContent {
	public static void main(String[] args) throws IOException, GeneralSecurityException {
		String keyStoreFile = System.getProperty("user.home") + System.getProperty("file.separator") + ".keystore";
		String storeType = KeyStore.getDefaultType(); // JKS
		char[] storePassword = null;
		boolean showSubject = false;
		boolean showIssuer = false;
		boolean showStartDate = false;
		boolean showEndDate = false;
		boolean showPubKey = false;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-keystore")) {
				keyStoreFile = args[++i];
			} else if (args[i].equals("-storetype")) {
				storeType = args[++i];
			} else if (args[i].equals("-storepass")) {
				storePassword = args[++i].toCharArray();
			} else if (args[i].equals("-subject")) {
				showSubject = true;
			} else if (args[i].equals("-issuer")) {
				showIssuer = true;
			} else if (args[i].equals("-startdate")) {
				showStartDate = true;
			} else if (args[i].equals("-enddate")) {
				showEndDate = true;
			} else if (args[i].equals("-pubKey")) {
				showPubKey = true;
			} else {
				System.out.println("Illegal option " + args[i]);
				System.out.println("-keystore <keystore>\tkeystore name");
				System.out.println("-storetype <storetype>\tkeystore type");
				System.out.println("-storepass <arg>\tkeystore password");
				System.out.println("-subject\tprint subject DN");
				System.out.println("-issuer\tprint issuer DN");
				System.out.println("-startdate\tnotBefore field");
				System.out.println("-enddate\tnotAfter field");
				System.out.println("-pubkey\toutput the public key");
				System.exit(0);
			}
		}

		if (!showSubject && !showIssuer && !showStartDate && !showEndDate && !showPubKey) {
			showSubject = true; // make sure something shows up; default is
								// subject
		}

		if (storePassword == null) {
			System.out.print("Password: ");
			storePassword = System.console().readPassword();
		}

		KeyStore ks = KeyStore.getInstance(storeType);

		FileInputStream in = new FileInputStream(keyStoreFile);
		try {
			ks.load(in, storePassword);
		} finally {
			in.close();
		}

		Enumeration<String> aliases = ks.aliases(); // [1]
		while (aliases.hasMoreElements()) {
			String alias = aliases.nextElement();
			System.out.print(alias + ": ");
			if (ks.isCertificateEntry(alias)) {
				System.out.println("Certificate Entry");
			} else {
				System.out.println("Private Key Entry");
			}
			Certificate cert = ks.getCertificate(alias);
			if (cert != null) {
				if ("X.509".equals(cert.getType())) {
					X509Certificate x509 = (X509Certificate) cert;
					if (showSubject) {
						System.out.println("Subject: " + x509.getSubjectX500Principal().toString());
					}
					if (showIssuer) {
						System.out.println("Issuer: " + x509.getIssuerX500Principal().toString());
					}
					if (showStartDate) {
						System.out.println("Start Date: " + x509.getNotBefore().toString());
					}
					if (showEndDate) {
						System.out.println("End Date: " + x509.getNotAfter().toString());
					}
					if (showPubKey) {
						PublicKey key = x509.getPublicKey();
						System.out.println(key.toString());
					}
				} else {
					System.out.println("Unrecognized certificate type '" + cert.getType() + "'");
				}
			}
		}
	}
}
