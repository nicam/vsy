all:
	#rpcgen -Ca dir.x
	gcc -o dir_client.o -c dir_client.c
	gcc -o dir_server.o -c dir_server.c
	gcc -o dir_clnt.o -c dir_clnt.c
	gcc -o dir_svc.o -c dir_svc.c
	gcc -o dir_xdr.o -c dir_xdr.c
	gcc -o dir_client dir_client.o dir_clnt.o dir_xdr.o
	gcc -o dir_server dir_server.o dir_svc.o dir_xdr.o

clean:
	rm *.o