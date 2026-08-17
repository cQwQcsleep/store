package com.sun.nio.zipfs;

import java.nio.file.Paths;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ZipInfo {
    public static long locoff(byte[] bArr, int i) {
        long jCENOFF = ZipConstants.CENOFF(bArr, i);
        if (jCENOFF == 4294967295L) {
            int iCENNAM = i + 46 + ZipConstants.CENNAM(bArr, i);
            int iCENEXT = ZipConstants.CENEXT(bArr, i) + iCENNAM;
            while (true) {
                int i2 = iCENNAM + 4;
                if (i2 >= iCENEXT) {
                    break;
                }
                int iSH = ZipConstants.SH(bArr, iCENNAM);
                int iSH2 = ZipConstants.SH(bArr, iCENNAM + 2);
                if (iSH == 1) {
                    if (ZipConstants.CENLEN(bArr, i) == 4294967295L) {
                        i2 = iCENNAM + 12;
                    }
                    if (ZipConstants.CENSIZ(bArr, i) == 4294967295L) {
                        i2 += 8;
                    }
                    return ZipConstants.LL(bArr, i2);
                }
                iCENNAM += iSH2 + 4;
            }
        }
        return jCENOFF;
    }

    public static void main(String[] strArr) throws Throwable {
        int iCENNAM = 0;
        int i = 1;
        if (strArr.length < 1) {
            print("Usage: java ZipInfo zfname", new Object[0]);
            return;
        }
        ZipFileSystem zipFileSystem = (ZipFileSystem) new ZipFileSystemProvider().newFileSystem(Paths.get(strArr[0], new String[0]), Collections.EMPTY_MAP);
        byte[] bArr = zipFileSystem.cen;
        if (bArr == null) {
            print("zip file is empty%n", new Object[0]);
            return;
        }
        byte[] bArr2 = new byte[1024];
        while (iCENNAM + 46 < bArr.length) {
            int i2 = i + 1;
            print("----------------#%d--------------------%n", Integer.valueOf(i));
            printCEN(bArr, iCENNAM);
            long jCENNAM = ZipConstants.CENNAM(bArr, iCENNAM) + 30 + ZipConstants.CENEXT(bArr, iCENNAM) + 46;
            if (zipFileSystem.readFullyAt(bArr2, 0, jCENNAM, locoff(bArr, iCENNAM)) != jCENNAM) {
                ZipFileSystem.zerror("read loc header failed");
            }
            if (ZipConstants.LOCEXT(bArr2) > ZipConstants.CENEXT(bArr, iCENNAM) + 46) {
                long jLOCNAM = ZipConstants.LOCNAM(bArr2) + 30 + ZipConstants.LOCEXT(bArr2);
                if (zipFileSystem.readFullyAt(bArr2, 0, jLOCNAM, locoff(bArr, iCENNAM)) != jLOCNAM) {
                    ZipFileSystem.zerror("read loc header failed");
                }
            }
            printLOC(bArr2);
            iCENNAM += ZipConstants.CENNAM(bArr, iCENNAM) + 46 + ZipConstants.CENEXT(bArr, iCENNAM) + ZipConstants.CENCOM(bArr, iCENNAM);
            i = i2;
        }
        zipFileSystem.close();
    }

    public static void print(String str, Object... objArr) {
        System.out.printf(str, objArr);
    }

    public static void printCEN(byte[] bArr, int i) {
        print("[Central Directory Header]%n", new Object[0]);
        print("    Signature   :   %#010x%n", Long.valueOf(ZipConstants.CENSIG(bArr, i)));
        if (ZipConstants.CENSIG(bArr, i) != ZipConstants.CENSIG) {
            print("    Wrong signature!", new Object[0]);
            return;
        }
        print("    VerMadeby   :       %#6x    [%d, %d.%d]%n", Integer.valueOf(ZipConstants.CENVEM(bArr, i)), Integer.valueOf(ZipConstants.CENVEM(bArr, i) >> 8), Integer.valueOf((ZipConstants.CENVEM(bArr, i) & 255) / 10), Integer.valueOf((ZipConstants.CENVEM(bArr, i) & 255) % 10));
        print("    VerExtract  :       %#6x    [%d.%d]%n", Integer.valueOf(ZipConstants.CENVER(bArr, i)), Integer.valueOf(ZipConstants.CENVER(bArr, i) / 10), Integer.valueOf(ZipConstants.CENVER(bArr, i) % 10));
        print("    Flag        :       %#6x%n", Integer.valueOf(ZipConstants.CENFLG(bArr, i)));
        print("    Method      :       %#6x%n", Integer.valueOf(ZipConstants.CENHOW(bArr, i)));
        print("    LastMTime   :   %#10x    [%tc]%n", Long.valueOf(ZipConstants.CENTIM(bArr, i)), Long.valueOf(ZipUtils.dosToJavaTime(ZipConstants.CENTIM(bArr, i))));
        print("    CRC         :   %#10x%n", Long.valueOf(ZipConstants.CENCRC(bArr, i)));
        print("    CSize       :   %#10x%n", Long.valueOf(ZipConstants.CENSIZ(bArr, i)));
        print("    Size        :   %#10x%n", Long.valueOf(ZipConstants.CENLEN(bArr, i)));
        int i2 = i + 46;
        print("    NameLen     :       %#6x    [%s]%n", Integer.valueOf(ZipConstants.CENNAM(bArr, i)), new String(bArr, i2, ZipConstants.CENNAM(bArr, i)));
        print("    ExtraLen    :       %#6x%n", Integer.valueOf(ZipConstants.CENEXT(bArr, i)));
        if (ZipConstants.CENEXT(bArr, i) != 0) {
            printExtra(bArr, i2 + ZipConstants.CENNAM(bArr, i), ZipConstants.CENEXT(bArr, i));
        }
        print("    CommentLen  :       %#6x%n", Integer.valueOf(ZipConstants.CENCOM(bArr, i)));
        print("    DiskStart   :       %#6x%n", Integer.valueOf(ZipConstants.CENDSK(bArr, i)));
        print("    Attrs       :       %#6x%n", Integer.valueOf(ZipConstants.CENATT(bArr, i)));
        print("    AttrsEx     :   %#10x%n", Long.valueOf(ZipConstants.CENATX(bArr, i)));
        print("    LocOff      :   %#10x%n", Long.valueOf(ZipConstants.CENOFF(bArr, i)));
    }

    public static void printExtra(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (true) {
            int i4 = i + 4;
            if (i4 > i3) {
                return;
            }
            int iSH = ZipConstants.SH(bArr, i);
            int iSH2 = ZipConstants.SH(bArr, i + 2);
            print("        [tag=0x%04x, sz=%d, data= ", Integer.valueOf(iSH), Integer.valueOf(iSH2));
            if (i + iSH2 > i3) {
                print("    Error: Invalid extra data, beyond extra length", new Object[0]);
                return;
            }
            for (int i5 = 0; i5 < iSH2; i5++) {
                print("%02x ", Byte.valueOf(bArr[i4 + i5]));
            }
            print("]%n", new Object[0]);
            if (iSH == 1) {
                print("         ->ZIP64: ", new Object[0]);
                int i6 = i4;
                while (true) {
                    int i7 = i6 + 8;
                    if (i7 > i4 + iSH2) {
                        break;
                    }
                    print(" *0x%x ", Long.valueOf(ZipConstants.LL(bArr, i6)));
                    i6 = i7;
                }
                print("%n", new Object[0]);
            } else if (iSH == 10) {
                print("         ->PKWare NTFS%n", new Object[0]);
                if (ZipConstants.SH(bArr, i + 8) != 1 || ZipConstants.SH(bArr, i + 10) != 24) {
                    print("    Error: Invalid NTFS sub-tag or subsz", new Object[0]);
                }
                print("            mtime:%tc%n", Long.valueOf(ZipUtils.winToJavaTime(ZipConstants.LL(bArr, i + 12))));
                print("            atime:%tc%n", Long.valueOf(ZipUtils.winToJavaTime(ZipConstants.LL(bArr, i + 20))));
                print("            ctime:%tc%n", Long.valueOf(ZipUtils.winToJavaTime(ZipConstants.LL(bArr, i + 28))));
            } else if (iSH != 21589) {
                print("         ->[tag=%x, size=%d]%n", Integer.valueOf(iSH), Integer.valueOf(iSH2));
            } else {
                print("         ->Info-ZIP Extended Timestamp: flag=%x%n", Byte.valueOf(bArr[i4]));
                int i8 = i + 5;
                while (true) {
                    int i9 = i8 + 4;
                    if (i9 <= i4 + iSH2) {
                        print("            *%tc%n", Long.valueOf(ZipUtils.unixToJavaTime(ZipConstants.LG(bArr, i8))));
                        i8 = i9;
                    }
                }
            }
            i = i4 + iSH2;
        }
    }

    public static void printLOC(byte[] bArr) {
        print("%n", new Object[0]);
        print("[Local File Header]%n", new Object[0]);
        print("    Signature   :   %#010x%n", Long.valueOf(ZipConstants.LOCSIG(bArr)));
        if (ZipConstants.LOCSIG(bArr) != ZipConstants.LOCSIG) {
            print("    Wrong signature!", new Object[0]);
            return;
        }
        print("    Version     :       %#6x    [%d.%d]%n", Integer.valueOf(ZipConstants.LOCVER(bArr)), Integer.valueOf(ZipConstants.LOCVER(bArr) / 10), Integer.valueOf(ZipConstants.LOCVER(bArr) % 10));
        print("    Flag        :       %#6x%n", Integer.valueOf(ZipConstants.LOCFLG(bArr)));
        print("    Method      :       %#6x%n", Integer.valueOf(ZipConstants.LOCHOW(bArr)));
        print("    LastMTime   :   %#10x    [%tc]%n", Long.valueOf(ZipConstants.LOCTIM(bArr)), Long.valueOf(ZipUtils.dosToJavaTime(ZipConstants.LOCTIM(bArr))));
        print("    CRC         :   %#10x%n", Long.valueOf(ZipConstants.LOCCRC(bArr)));
        print("    CSize       :   %#10x%n", Long.valueOf(ZipConstants.LOCSIZ(bArr)));
        print("    Size        :   %#10x%n", Long.valueOf(ZipConstants.LOCLEN(bArr)));
        print("    NameLength  :       %#6x    [%s]%n", Integer.valueOf(ZipConstants.LOCNAM(bArr)), new String(bArr, 30, ZipConstants.LOCNAM(bArr)));
        print("    ExtraLength :       %#6x%n", Integer.valueOf(ZipConstants.LOCEXT(bArr)));
        if (ZipConstants.LOCEXT(bArr) != 0) {
            printExtra(bArr, ZipConstants.LOCNAM(bArr) + 30, ZipConstants.LOCEXT(bArr));
        }
    }
}
