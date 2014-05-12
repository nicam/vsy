# Generate server certificate
keytool -genkeypair -alias serverkey -keyalg RSA -dname "CN=Web Server,OU=Application Development,O=ZHAW,L=Winterthur,S=ZH,C=CH" -keypass password -storepass password -keystore server.jks
keytool -importcert -keystore server.jks -alias servercert -storepass password -noprompt

# generate client certificate
keytool -genkey -v -alias clientKey -keyalg RSA -storetype PKCS12 -keystore client.p12 -dname "CN=Client,OU=Application Development,O=ZHAW,L=Winterthur,S=ZH,C=CH" -storepass password -keypass password
# export client certificate 
keytool -export -alias clientKey -keystore client.p12 -storetype PKCS12 -storepass password -rfc -file client.cer
# import client certificate into server keystore
keytool -import -v -file client.cer -keystore server.jks -storepass password