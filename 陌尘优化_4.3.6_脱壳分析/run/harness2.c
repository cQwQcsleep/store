#define _GNU_SOURCE
#include <dlfcn.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <setjmp.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <ucontext.h>
extern int JNI_CreateJavaVM(void** vm, void** env, void* args);
static sigjmp_buf jb; static volatile int crashed=0;
static unsigned long g_rip=0,g_addr=0;
static void handler(int sig, siginfo_t* si, void* uc){
  crashed=sig; ucontext_t* u=(ucontext_t*)uc;
  g_rip=u->uc_mcontext.gregs[REG_RIP]; g_addr=(unsigned long)si->si_addr;
  siglongjmp(jb,1);
}
int main(int argc, char** argv){
  setbuf(stdout,NULL);
  void* jvmh=dlopen(argv[1], RTLD_NOW|RTLD_GLOBAL);
  if(!jvmh){printf("no libjvm: %s\n",dlerror());return 1;}
  int (*CreateVM)(void**,void**,void*)=dlsym(jvmh,"JNI_CreateJavaVM");
  void* vm=NULL; void* env=NULL;
  typedef struct { int version; int nOptions; void* options; int ignoreUnrecognized; } MyArgs;
  MyArgs a; a.version=0x00010006; a.nOptions=0; a.options=NULL; a.ignoreUnrecognized=1;
  int r=CreateVM(&vm,&env,&a);
  printf("[+] vm create=%d vm=%p env=%p\n",r,vm,env);
  if(r) return 2;
  void* h=dlopen("/workspace/run/libjiagu.so", RTLD_NOW);
  if(!h){printf("[-] shell: %s\n",dlerror());return 3;}
  printf("[+] shell loaded\n");
  unsigned long base=0;
  { FILE* f=fopen("/proc/self/maps","r"); char line[512];
    while(fgets(line,sizeof line,f)){ unsigned long s; char nm[256]={0};
      if(sscanf(line,"%lx-%*lx %*s %*s %*s %*s %255s",&s,nm)>=1 && strstr(nm,"libjiagu.so")){ base=s; break; } }
    fclose(f); }
  printf("[+] base=%lx\n",base);
  if(!base) return 4;
  int fd=open("/workspace/mochen.apk",O_RDONLY); struct stat st; fstat(fd,&st);
  char* apk=malloc(st.st_size); ssize_t rd=read(fd,apk,st.st_size); close(fd);
  printf("[+] apk=%zd bytes\n",rd);
  struct sigaction sa; memset(&sa,0,sizeof sa);
  sa.sa_sigaction=handler; sa.sa_flags=SA_SIGINFO|SA_NODEFER;
  sigaction(SIGSEGV,&sa,NULL); sigaction(SIGBUS,&sa,NULL); sigaction(SIGABRT,&sa,NULL);
  void (*pp)(void*,void*,unsigned long)=(void(*)(void*,void*,unsigned long))(base+0x63d10);
  printf("[+] calling processPayload @%lx ...\n",(unsigned long)pp); fflush(stdout);
  if(sigsetjmp(jb,1)==0){ pp(env, apk, rd); printf("[+] processPayload returned\n"); }
  else { Dl_info di; if(dladdr((void*)g_rip,&di)) printf("[!] crash sig=%d rip=%lx in %s+%lx fault=%lx\n",crashed,g_rip,di.dli_fname,(unsigned long)g_rip-(unsigned long)di.dli_fbase,g_addr); else printf("[!] crash sig=%d rip=%lx (dladdr miss) fault=%lx\n",crashed,g_rip,g_addr); }
  fflush(stdout);
  int n=0,dexn=0; FILE* f=fopen("/proc/self/maps","r");
  int mfd=open("/proc/self/mem",O_RDONLY);
  char line[512];
  while(fgets(line,sizeof line,f)){
    unsigned long s,e; char perms[8]={0}; char nm[256]={0};
    int k=sscanf(line,"%lx-%lx %4s %*s %*s %*s %255s",&s,&e,perms,nm);
    if(k<3||perms[0]!='r'||e<=s||(e-s)>(400UL<<20)) continue;
    if(strstr(nm,".so") && !strstr(nm,"libjiagu")) continue;
    char* buf=malloc(e-s); if(!buf) continue;
    ssize_t g=pread(mfd,buf,e-s,(off_t)s);
    if(g>0){
      void* hit=memmem(buf,g,"dex\n",4);
      if(hit){ char out[128]; snprintf(out,sizeof out,"/workspace/run/mem_%lx.bin",s);
        int w=open(out,O_WRONLY|O_CREAT|O_TRUNC,0644);
        if(w>0){ ssize_t wr=write(w,buf,g); (void)wr; close(w); n++; 
          char* p=buf; int c=0;
          while((p=memmem(p,g-(p-buf)-4,"dex\n035",6))){ c++; dexn++; p+=4; }
          printf("    region %lx (%ld B) has dex magic x%d\n",s,(long)g,c);
        } }
    }
    free(buf);
  }
  close(mfd); fclose(f);
  printf("[+] dumped %d regions, dex magic hits=%d\n",n,dexn);
  return 0;
}
