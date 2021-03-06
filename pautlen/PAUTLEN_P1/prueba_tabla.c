#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hash.h"
#include "tabsim.h"

void main(int argc, char** argv) {
    hash_table_t * t_global = NULL;
    hash_table_t * t_local = NULL;
    FILE * fl = NULL;
	FILE * fs=NULL;
    char str[32];
    char *str1,*str2;
    char * pch;
    int *simb, simbret;
    int busqueda = 1;
    t_global = hash_create_t_local();
    if (t_global == NULL) {
        printf("no se reserva bien\n");
        return;
    }
    if (argc < 3) {
        printf("no se especifico el archivo\n");
        return;
    }
    fl = fopen(argv[1], "r");
    if (fl == NULL) {
        printf("fallo al abrir el fichero %s\n", argv[1]);
        return;
    }
    fs = fopen(argv[2], "w");
	if (fs == NULL) {
        printf("fallo al abrir el fichero %s\n", argv[2]);
        return;
    }

    while ((fgets(str, 32, fl)) != NULL) {
        busqueda = 1;
        pch = strtok(str, " \r\t\n");
        str1 = (char*) malloc(sizeof (char)*strlen(str));
        strcpy(str1, str);
        if (pch != NULL) {
            pch = strtok(NULL, " \t\n");
            if (pch != NULL) {
        		simb=(int*)malloc(sizeof(int));
                *simb = atoi(pch);
                busqueda = 0;
            }
        }
        if ((strncmp(str1, "cierre", 6) == 0)&&(*simb == -999)) {/*cierre*/
            hash_destroy_table(t_local);
            t_local=NULL;
            fprintf(fs,"cierre\n");

        } else if ((busqueda == 0)&&(*simb >= 0)) { /*añadir simbolo*/
            if (-1 == hash_insert_table(t_local, t_global, str1, simb)) {
                fprintf(fs,"-1\t%s\n", str1);
            } else
                fprintf(fs,"%s\n", str1);
        } else if ((busqueda == 0)&&(*simb<-1)) { /*añadir funcion*/
			if (-1 == hash_insert_table_global(t_global, str1, simb)) {
	            fprintf(fs,"-1\t%s\n", str1);
				continue;
	        }
            if (t_local == NULL) {
                
                t_local = hash_create_t_local();
                if (t_local == NULL) {
                    printf("ERROR AL RESERVAR MEMORIA\n");
                    return;
                }
                str2 =(char*)malloc(sizeof(char)*strlen(str1));
                strcpy(str2,str1);
                hash_insert_table(t_local, t_global, str2, simb);
                fprintf(fs,"%s\n",str1);
            } else {
                fprintf(fs,"%s\n", str1);

            }
        } else if (busqueda == 1) {/*busqueda de variables y funciones*/
			simbret = hash_find_table(t_local, t_global, (void*) str1);
			
			printf("%s\t%d\n", str1, simbret);
            fprintf(fs,"%s\t%d\n", str1, simbret);
        }
    }
	hash_destroy_table(t_global);
    fclose(fl);
    
}
