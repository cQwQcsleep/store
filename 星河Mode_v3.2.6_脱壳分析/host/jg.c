#define _GNU_SOURCE
#include <dlfcn.h>
#include <stdio.h>
#include <string.h>
#include <signal.h>
#include <setjmp.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <ucontext.h>
extern int JNI_CreateJavaVM(void** vm, void** env, void* args);
static sigjmp_buf jb; static volatile int crashed=0; static unsigned long g_rip=0;
static void handler(int sig, siginfo_t* si, void* uc){ crashed=sig; g_rip=((ucontext_t*)uc)->uc_mcontext.gregs[REG_RIP]; siglongjmp(jb,1); }
int main(int argc, char** argv){
  setbuf(stdout,NULL);
  void* jvmh=dlopen(argv[1], RTLD_NOW|RTLD_GLOBAL);
  if(!jvmh){printf("no jvm\n");return 1;}
  int (*CreateVM)(void**,void**,void*)=dlsym(jvmh,"JNI_CreateJavaVM");
  void* vm=NULL; void* env=NULL;
  typedef struct { int version; int nOptions; void* options; int ignoreUnrecognized; } MyArgs;
  MyArgs a; a.version=0x00010006; a.nOptions=0; a.options=NULL; a.ignoreUnrecognized=1;
  if(CreateVM(&vm,&env,&a)){printf("vm fail\n");return 2;}
  printf("[+] vm ok env=%p\n",env);
  void* yj=dlopen("/workspace/xh/host/libyj-vmp-lib.so", RTLD_NOW|RTLD_GLOBAL);
  void* tb=dlopen("/workspace/xh/host/libcom.Mode.toolbox.so", RTLD_NOW);
  if(!tb){printf("tb: %s\n",dlerror());return 3;}
  unsigned long base=0;
  { FILE* f=fopen("/proc/self/maps","r"); char line[512];
    while(fgets(line,sizeof line,f)){ unsigned long s; char nm[256]={0};
      if(sscanf(line,"%lx-%*lx %*s %*s %*s %*s %255s",&s,nm)>=1 && strstr(nm,"libcom.Mode")){ base=s; break; } }
    fclose(f); }
  printf("[+] tb base=%lx\n",base);
  struct sigaction sa; memset(&sa,0,sizeof sa);
  sa.sa_sigaction=handler; sa.sa_flags=SA_SIGINFO|SA_NODEFER;
  sigaction(SIGSEGV,&sa,NULL); sigaction(SIGBUS,&sa,NULL); sigaction(SIGABRT,&sa,NULL);
  // try JNI_OnLoad(vm, NULL)
  typedef int (*onload_t)(void*, void*);
  onload_t ol=(onload_t)dlsym(tb,"JNI_OnLoad");
  printf("[+] JNI_OnLoad @%p, calling with vm=%p...\n",ol,vm);
  if(sigsetjmp(jb,1)==0){ int r=ol(vm, NULL); printf("[+] JNI_OnLoad ret=%d\n",r); }
  else printf("[!] crash sig=%d rip_off=%lx — dumping\n",crashed,g_rip-base);
  // dump all rw anon memory looking for readable chinese/commands
  int n=0; FILE* f=fopen("/proc/self/maps","r");
  int mfd=open("/proc/self/mem",O_RDONLY);
  char line[512];
  while(fgets(line,sizeof line,f)){
    unsigned long s,e; char perms[8]={0}; char nm[256]={0};
    int k=sscanf(line,"%lx-%lx %4s %*s %*s %*s %255s",&s,&e,perms,nm);
    if(k<3||perms[0]!='r'||perms[1]!='w'||e<=s||(e-s)>(200UL<<20)) continue;
    if(nm[0]) continue; // anon only
    char* buf=malloc(e-s); if(!buf) continue;
    ssize_t g=pread(mfd,buf,e-s,(off_t)s);
    if(g>0){
      int hits=0;
      for(long i=0;i+16<g;i++){
        unsigned char c=buf[i];
        if(c>=0xE4 && c<=0xE9 && i+2<g){ // utf8 chinese lead
          hits++;
        }
        if(!strncmp(buf+i,"settings ",9)||!strncmp(buf+i,"setprop ",8)||!strncmp(buf+i,"echo ",5)){hits+=10;}
      }
      if(hits>20){
        char out[128]; snprintf(out,sizeof out,"/workspace/xh/host/jgdump_%lx.bin",s);
        int w=open(out,O_WRONLY|O_CREAT|O_TRUNC,0644);
        if(w>0){ ssize_t wr=write(w,buf,g); (void)wr; close(w); n++;
          printf("    [jg] region %lx (%ld B) hits=%d -> saved\n",s,(long)g,hits); }
      }
    }
    free(buf);
  }
  close(mfd); fclose(f);
  printf("[+] dumped %d regions\n",n);
  return 0;
}
