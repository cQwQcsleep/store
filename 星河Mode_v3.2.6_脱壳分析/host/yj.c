#define _GNU_SOURCE
#include <dlfcn.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
typedef char* (*dec_t)(const char*, void*, size_t);
typedef char* (*dec1_t)(const char*);
int main(){
  void* yj=dlopen("/workspace/xh/host/libyj-vmp-lib.so", RTLD_NOW|RTLD_GLOBAL);
  void* tb=dlopen("/workspace/xh/host/libcom.Mode.toolbox.so", RTLD_NOW);
  if(!yj||!tb){printf("load fail\n");return 1;}
  dec1_t d=(dec1_t)dlsym(yj,"YjStr_decode");
  dec_t d2=(dec_t)dlsym(yj,"YjStr_decode");
  const char* tests[]={"dza/{r{]d2{reiz59oYJ","dz1_!z$%ls^(!2m59oYJ","d1*gis:s#%zd","d2~umr17{%a8{bv59oYJ"};
  for(int i=0;i<4;i++){
    size_t L=strlen(tests[i]);
    char* r=d2(tests[i], NULL, L);
    printf("[%d] 1arg? out=%s\n", i, r?r:"(null)");
    if(!r){
      r=d(tests[i]);
      printf("[%d] plain1arg out=%s\n", i, r?r:"(null)");
    }
  }
  return 0;
}
