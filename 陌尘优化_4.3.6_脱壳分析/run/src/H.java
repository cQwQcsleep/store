import java.nio.file.*; import java.nio.ByteOrder; import java.util.*;
public class H {
  public static void main(String[] a) throws Exception {
    String mode = a.length>0? a[0] : "dump";
    System.out.println("[+] loading libjiagu.so");
    System.load("/workspace/run/libjiagu.so");
    System.out.println("[+] loaded, self-decryption done");
    if (mode.equals("dump")) { dumpLib(); }
    else {
      try { System.out.println("[+] gap()="+com.tianyu.util.DtcLoader.gap()); } catch(Throwable t){ System.out.println("[-] gap fail: "+t); }
      try { System.out.println("[+] gdp()="+com.tianyu.util.DtcLoader.gdp()); } catch(Throwable t){ System.out.println("[-] gdp fail: "+t); }
      try { com.tianyu.util.DtcLoader.ia(); System.out.println("[+] ia() OK"); } catch(Throwable t){ System.out.println("[-] ia fail: "+t); }
      carveDex();
    }
    System.out.println("[+] done");
  }
  static void dumpLib() throws Exception {
    List<String> lines=Files.readAllLines(Path.of("/proc/self/maps"));
    StringBuilder sb=new StringBuilder(); int n=0;
    try(var raf=new java.io.RandomAccessFile("/proc/self/mem","r")){
      for(String l: lines){
        if(!l.contains("libjiagu.so")) continue;
        String[] p=l.split("\\s+"); String[] rg=p[0].split("-");
        long s=Long.parseUnsignedLong(rg[0],16), e=Long.parseUnsignedLong(rg[1],16);
        if(!p[1].contains("r") || e<=s || (e-s)>(40L<<20)) continue;
        try{
          raf.seek(s); byte[] b=new byte[(int)(e-s)]; int got=raf.read(b);
          if(got>0){ Files.write(Path.of("/workspace/run/libdump_"+Long.toHexString(s)+".bin"), Arrays.copyOf(b,got)); sb.append(l).append('\n'); n++; }
        }catch(Exception ignore){}
      }
    }
    Files.writeString(Path.of("/workspace/run/libdump_maps.txt"), sb.toString());
    System.out.println("[+] dumped "+n+" regions");
  }
  static void carveDex() throws Exception {
    List<String> lines=Files.readAllLines(Path.of("/proc/self/maps"));
    int found=0;
    try(var raf=new java.io.RandomAccessFile("/proc/self/mem","r")){
      for(String l: lines){
        String[] p=l.split("\\s+");
        if(p.length<2||!p[1].startsWith("r")) continue;
        String[] rg=p[0].split("-");
        long s=Long.parseUnsignedLong(rg[0],16), e=Long.parseUnsignedLong(rg[1],16);
        long sz=e-s; if(sz==0||sz>(64L<<20)) continue;
        try{
          raf.seek(s); byte[] b=new byte[(int)sz]; int got=raf.read(b); if(got<=0) continue;
          for(int i=0;i+116<=got;i++){
            if(b[i]==0x64&&b[i+1]==0x65&&b[i+2]==0x78&&b[i+3]==0x0a){
              int fs=java.nio.ByteBuffer.wrap(b,i+32,4).order(ByteOrder.LITTLE_ENDIAN).getInt();
              if(fs>116&&fs<=got-i){
                Files.write(Path.of("/workspace/run/carved_"+Long.toHexString(s+i)+"_"+fs+".dex"), Arrays.copyOfRange(b,i,i+fs));
                found++; System.out.println("[+] dex @"+Long.toHexString(s+i)+" size="+fs);
              }
              i+=8;
            }
          }
        }catch(Exception ignore){}
      }
    }
    System.out.println("[+] carved dex count="+found);
  }
}
