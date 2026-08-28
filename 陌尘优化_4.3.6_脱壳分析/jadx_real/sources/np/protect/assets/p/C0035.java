package np.protect.assets.p;

import com.shadow.okio.Segment;
import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import np.protect.assets.C0043;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: np.protect.assets.p.۟۟۟ۡ۟, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0035 {
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static void m436(InputStream inputStream, OutputStream outputStream, byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[1024000];
        while (true) {
            int i = inputStream.read(bArr2);
            if (i == -1) {
                return;
            }
            for (int i2 = 0; i2 < 1024000; i2++) {
                bArr2[i2] = (byte) (bArr2[i2] ^ bArr[i2 % length]);
            }
            outputStream.write(bArr2, 0, i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x00ce A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00c7 A[SYNTHETIC] */
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m437(File file) {
        int i;
        int i2;
        String strDecode;
        int i3;
        int i4;
        int i5;
        String strDecode2;
        String strDecode3 = NPStringFog.decode("B5D7B6C4B5C4");
        int iM503 = C0052.m503(strDecode3);
        File[] fileArrM558 = null;
        int i6 = 0;
        int length = 0;
        boolean zM200 = false;
        int i7 = 0;
        while (true) {
            String strDecode4 = NPStringFog.decode("B5EFB6FE");
            String strDecode5 = "ۣ۟ۡ";
            String strDecode6 = NPStringFog.decode("B5D1B6C1B5C2");
            switch (iM503) {
                case 56288:
                    if (i6 >= length) {
                        i = C0053.f92 % C0001.f1;
                        i2 = 1752776;
                        iM503 = i + i2;
                    } else if (C0002.f2 >= 0) {
                        C0052.m520();
                        iM503 = C0043.m455(strDecode4);
                    } else {
                        iM503 = C0043.m455(NPStringFog.decode("B5D6B6C4B5C7"));
                    }
                case 56323:
                    length = fileArrM558.length;
                    if ((C0053.f92 ^ (C0001.f1 / (-5815))) >= 0) {
                        C0043.m456();
                        iM503 = C0002.m230(strDecode6);
                    } else {
                        i = C0043.f73 | C0001.f1;
                        i2 = 1748994;
                        iM503 = i + i2;
                    }
                case 56354:
                    if (!C0001.m185(file)) {
                        i = C0053.f92 % C0001.f1;
                        i2 = 1752776;
                        iM503 = i + i2;
                    } else if (C0052.f91 * (C0052.f91 % (-6789)) <= 0) {
                        int i8 = i7;
                        strDecode = NPStringFog.decode("B5D1B6C4B5C4");
                        i3 = i8;
                        int iM562 = C0053.m562(strDecode);
                        i7 = i3;
                        iM503 = iM562;
                    } else {
                        i4 = C0043.f73 | C0002.f2;
                        i5 = -1747866;
                        iM503 = i4 ^ i5;
                    }
                case 56358:
                case 1748677:
                    if (C0001.m192() >= 0) {
                        C0001.f1 = 91;
                        strDecode2 = NPStringFog.decode("B5D7B6C9B5C1");
                        iM503 = C0001.m190(strDecode2);
                    } else {
                        iM503 = C0002.m230(strDecode4);
                    }
                case 56449:
                    if (!zM200) {
                        iM503 = C0002.m230(NPStringFog.decode("B5D7B6FEB5C2"));
                    } else if (C0043.m456() > 0) {
                        strDecode4 = NPStringFog.decode("B5EFB6C0B5C0");
                        iM503 = C0002.m230(strDecode4);
                    } else {
                        strDecode2 = NPStringFog.decode("B5D1B6C2");
                        iM503 = C0001.m190(strDecode2);
                    }
                case 1746753:
                    if (C0001.f1 <= 0) {
                        C0001.f1 = 44;
                        iM503 = C0043.m455("ۣ۟ۡ");
                    } else {
                        i4 = C0053.f92 | C0002.f2;
                        i5 = -56457;
                        iM503 = i4 ^ i5;
                    }
                case 1747807:
                    i3 = (C0001.f1 ^ 354) + i6;
                    strDecode = NPStringFog.decode("B5D2B6C3B5C9");
                    int iM5622 = C0053.m562(strDecode);
                    i7 = i3;
                    iM503 = iM5622;
                case 1747867:
                    fileArrM558 = C0053.m558(file);
                    if (C0053.f92 >= 0) {
                        iM503 = C0002.m230(NPStringFog.decode("B5D7B6FEB5C2"));
                    } else {
                        i = C0001.f1 ^ C0001.f1;
                        i2 = 56323;
                        iM503 = i + i2;
                    }
                case 1748644:
                    i = C0002.f2 | C0043.f73;
                    i2 = 56361;
                    iM503 = i + i2;
                case 1748801:
                    i = C0053.f92 % C0001.f1;
                    i2 = 1752776;
                    iM503 = i + i2;
                case 1748861:
                    if (C0052.f91 >= 0) {
                        C0053.m574();
                        iM503 = C0002.m230(NPStringFog.decode("B5D1B6C6"));
                    } else {
                        iM503 = (C0002.f2 ^ C0001.f1) ^ (-56009);
                    }
                    i6 = 0;
                case 1749672:
                    i6 = i7;
                    strDecode4 = strDecode6;
                    iM503 = C0002.m230(strDecode4);
                case 1749761:
                    if (C0043.m456() > 0) {
                    }
                    break;
                case 1751557:
                    if (C0052.f91 >= 0) {
                        C0001.m192();
                        strDecode5 = NPStringFog.decode("B5D3B6C5B5C9");
                    } else {
                        strDecode5 = strDecode3;
                    }
                    iM503 = C0001.m190(strDecode5);
                case 1752672:
                    return C0052.m508(file);
                case 1753607:
                    C0002.m218(fileArrM558[i6]);
                    i = C0001.f1 % C0002.f2;
                    i2 = 1747756;
                    iM503 = i + i2;
                case 1754379:
                    return false;
                case 1754567:
                    zM200 = C0001.m200(file);
                    iM503 = C0001.m190(strDecode5);
            }
        }
    }

    /* renamed from: ۟۟۟, reason: not valid java name and contains not printable characters */
    public static void m438(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[Segment.SIZE];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }
}
