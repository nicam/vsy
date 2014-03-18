all:
	# rpcgen -Ca versand.x
	gcc -o versand_client.o -c versand_client.c
	gcc -o versand_server.o -c versand_server.c
	gcc -o versand_clnt.o -c versand_clnt.c
	gcc -o versand_svc.o -c versand_svc.c
	gcc -o versand_xdr.o -c versand_xdr.c
	gcc -o versand_client versand_client.o versand_clnt.o versand_xdr.o
	gcc -o versand_server versand_server.o versand_svc.o versand_xdr.o

clean:
	rm *.o