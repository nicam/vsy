keytool -genkeypair -alias serverkey -keyalg RSA -dname "CN=Web Server,OU=Application Development,O=ZHAW,L=Winterthur,S=ZH,C=CH" -keypass password -storepass password -keystore server.jks
keytool -genkeypair -alias clientkey -keyalg RSA -dname "CN=Client,OU=Application Development,O=ZHAw,L=Winterthur,S=ZH,C=CH" -keypass password -storepass password -keystore client.jks

keytool -exportcert -alias clientkey -file client-public.cer -keystore client.jks -storepass password
keytool -importcert -keystore server.jks -alias clientcert -file client-public.cer -storepass password -noprompt
 
# view the contents of the keystore (use -v for verbose output)
keytool -list -keystore server.jks -storepass password

keytool -exportcert -alias serverkey -file server-public.cer -keystore server.jks -storepass password
keytool -importcert -keystore client.jks -alias servercert -file server-public.cer -storepass password -noprompt
 
# view the contents of the keystore (use -v for verbose output)
keytool -list -keystore client.jks -storepass password