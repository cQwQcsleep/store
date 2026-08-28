#define _GNU_SOURCE
#include <dlfcn.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
int main(){
  void* h=dlopen("/workspace/run/libjiagu.so", RTLD_NOW);
  if(!h){ printf("dlopen fail: %s\n", dlerror()); return 1; }
  printf("loaded ok, self-decrypt done\n");
  FILE* maps=fopen("/proc/self/maps","r");
  char line[512]; int n=0;
  int fd=open("/proc/self/mem",O_RDONLY);
  while(fgets(line,sizeof line,maps)){
    unsigned long s,e; char perms[8]; char name[256]={0};
    int k=sscanf(line,"%lx-%lx %4s %*s %*s %*s %255s",&s,&e,perms,name);
    if(k>=3 && strstr(name,"libjiagu.so") && perms[0]=='r' && e>s && (e-s)<(40UL<<20)){
      unsigned char* buf=malloc(e-s);
      ssize_t got=pread(fd,buf,e-s,(off_t)s);
      if(got>0){
        char out[128]; snprintf(out,sizeof out,"/workspace/run/libdump_%lx.bin",s);
        int w=open(out,O_WRONLY|O_CREAT|O_TRUNC,0644);
        if(w>0){ ssize_t wr=write(w,buf,got); (void)wr; close(w); n++; printf("dumped %lx-%lx (%ld bytes)\n",s,e,(long)got); }
      }
      free(buf);
    }
  }
  close(fd); fclose(maps);
  printf("regions dumped: %d\n",n);
  return 0;
}
