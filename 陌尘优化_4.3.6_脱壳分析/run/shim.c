#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
int *__errno(void){ return &errno; }
void __assert2(const char* f,int l,const char* fn,const char* m){ fprintf(stderr,"[shim] assert2: %s\n",m); abort(); }
void android_set_abort_message(const char* m){ fprintf(stderr,"[shim] abort_msg: %s\n",m); }
char __sF[0x600];
__attribute__((constructor)) static void init_sF(void){
  memset(__sF,0,sizeof(__sF));
  memcpy(__sF+0x000, stdin, 0x1d8);
  memcpy(__sF+0x200, stdout, 0x1d8);
  memcpy(__sF+0x400, stderr, 0x1d8);
}
size_t __strlen_chk(const char* s, size_t n){ return strlen(s); }
