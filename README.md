
creating an ssl key in full cycle that involves certificate authority, applicant, and the ssl key users 


step by step tutorial on how to create an ssl keys.
ssl keys has 3 components; 
1) the user. a good example of ssl user is a browser
2) the server, a good example is the web applicant hosted somewhere
3) the authority that guarantees the ssl key is legit

getting an ssl key works like the following:
an organization will contact the authority to acquire an ssl key. the organization will be given 2 keys,
public and private. the public key will be given by the organization to any third party that wishes to
have access to an application the organization owns.

in a web application scenario, the public key will be embedded to the browser by the third party. this public key
will be use by the browser to encrypt the information before passing or making a GET request to the web application

on the side of web application, it will decrypt the encrypted information using its private key

by the way, the ssl explained above is for 2 way authentication which is much secured than one way
which require that only the server side or the web application is having an ssl key


the following directory structure is required:

1) root directory
2) cacert under root
3) clientcert under root
4) servercert under root
5) openssl.cfg under root

openssl.cfg can be found at src/main/resources


the following tools are required:
1) java 1.8
2) openssl 0.9.8
3) java IDE (Spring Tools Suite 3.8.3-RELEASE is fine) 


see the instructions in src/main/resources/how to create certs signed by ca using keytool and openssl.txt
