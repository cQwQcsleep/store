#define _GNU_SOURCE
#include <dlfcn.h>
#include <stdio.h>
int main(){
  void* yj=dlopen("/workspace/xh/host/libyj-vmp-lib.so", RTLD_NOW|RTLD_GLOBAL);
  if(!yj){printf("yj: %s\n",dlerror());return 1;}
  void* tb=dlopen("/workspace/xh/host/libcom.Mode.toolbox.so", RTLD_NOW);
  if(!tb){printf("tb: %s\n",dlerror());return 2;}
  printf("both loaded\n");
  const char* names[]={"YjStr_decode","YjStr_decode_inplace","StrParse","vmInterpret","cacheInitial","getJNIWrapper","getCacheClass"};
  for(int i=0;i<7;i++){
    void* s=dlsym(tb,names[i]); if(!s) s=dlsym(yj,names[i]);
    printf("  %-20s %p %s\n", names[i], s, s?"":"(missing)");
  }
  return 0;
}
