
/* Relevantes Code-Fragment um die vom Server erhaltene Liste auszugeben */(
	namelist nl;
	result_1 = readdir_1(&dirname, clnt);
	if (result_1 == (readdir_res *) NULL) {
		clnt_perror (clnt, "call failed");
	} else {
	  for (nl = result_1->readdir_res_u.list;
	       nl != NULL;
	       nl = nl->next) {
	    printf("%s\n", nl->name);
	  }
	}



/* Relevantes Code-Fragment um ein Verzeichnis zu lesen und eine Liste zu erzeugen */(

        #include <dirent.h>
       
        ....

        namelist nl; namelist *nlp;
        DIR *dirp;
        struct dirent *d;

        xdr_free((xdrproc_t) xdr_readdir_res, (char*) &result);

        dirp= opendir(*argp);
        nlp = &result.readdir_res_u.list;
        while (d = readdir(dirp)) {
	    nl = *nlp = malloc(sizeof(namenode));
	    nl->name = strdup(d->d_name);
	    nlp = &nl->next;
        }
	*nlp = NULL;
	/* Return the result */
	result.errno = 0;
	closedir(dirp);
