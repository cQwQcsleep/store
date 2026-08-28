package gTBLD.dev.XSSTG.free;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import core.pro.android.notify.h;
import core.pro.android.notify.k2;
import core.pro.android.notify.l2;
import core.pro.android.notify.z0;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: /workspace/unpacked/classes2.dex */
public class SignatureSpoof {
    private static Field findField(Class<?> cls, String str) throws NoSuchFieldException, SecurityException {
        for (Class<?> superclass = cls; superclass != null && !superclass.equals(Object.class); superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        StringBuilder sb = new StringBuilder();
        h.f("0EIFXN+z\n", "litgMLuTskY=\n", sb, str);
        sb.append(l2.decrypt("/pkDuwSHEGawk0ymSsEcf7+EH+8=\n", "3vdszyThfxM=\n"));
        sb.append(cls.getName());
        throw new NoSuchFieldException(sb.toString());
    }

    public static void killPM(Context context, final String str, String str2) {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                try {
                    PiQ2N.media.btkBu.pPhDR.HiddenApiBypass.addHiddenApiExemptions("");
                    System.out.println(l2.decrypt("obqBJa183MdAw+cTyCzsoqdyZdAoqBNuEvWtUN1gpo1zt4Mqfe+quWq6mTYFlArKY8LrMfI=\n", "+lIOuUTEQyM=\n"));
                } catch (Throwable th) {
                    System.out.println(l2.decrypt("p++MW7kjs91Glupt3HODuKEn62DzcrWdFZ6TItgtyZ1N77divye2\n", "/AcDx1CbLDk=\n") + th.getMessage());
                }
            }
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            if (signatureArr != null && signatureArr.length > 0) {
                String strEncodeToString = Base64.encodeToString(signatureArr[0].toByteArray(), 2);
                System.out.println(l2.decrypt("c4iC19fyi9aS8eThsqK7s3VA6Patr52/wOeno4Th85+WhZ3GFgh1QU1WOWIEag==\n", "KGANSz5KFDI=\n") + strEncodeToString);
            }
            System.out.println(l2.decrypt("Suw0fXRzbF2rlVJLESNcOEwkXmwuLkM/9bgRCB1rFBSv4StstYmSynQyj8in6w==\n", "EQS74Z3L87k=\n") + str2);
            final Signature signature = new Signature(Base64.decode(str2, 0));
            Field fieldFindField = findField(PackageInfo.class, l2.decrypt("ZM6bpixE2g==\n", "J5ze53gLiJ4=\n"));
            final Parcelable.Creator creator = (Parcelable.Creator) fieldFindField.get(null);
            Parcelable.Creator<PackageInfo> creator2 = new Parcelable.Creator<PackageInfo>() { // from class: gTBLD.dev.XSSTG.free.SignatureSpoof.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PackageInfo createFromParcel(Parcel parcel) {
                    PackageInfo packageInfo = (PackageInfo) creator.createFromParcel(parcel);
                    String str3 = packageInfo.packageName;
                    if (str3 != null && str3.equals(str)) {
                        Signature[] signatureArr2 = packageInfo.signatures;
                        if (signatureArr2 != null && signatureArr2.length > 0) {
                            signatureArr2[0] = signature;
                        }
                        if (Build.VERSION.SDK_INT >= 28 && z0.e(packageInfo) != null) {
                            try {
                                Signature[] signatureArr3 = (Signature[]) z0.e(packageInfo).getClass().getMethod(l2.decrypt("si5Tp+1gZ4y7P0KI6Xh3irIlQpTu\n", "1Usn5p0LJOM=\n"), null).invoke(z0.e(packageInfo), null);
                                if (signatureArr3 != null && signatureArr3.length > 0) {
                                    signatureArr3[0] = signature;
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    }
                    return packageInfo;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PackageInfo[] newArray(int i) {
                    return (PackageInfo[]) creator.newArray(i);
                }
            };
            fieldFindField.set(null, creator2);
            try {
                Object obj = findField(Class.forName(l2.decrypt("XsqQWYqKw9pcy5pfgI3T2k/J2nuEgMyVWMG5SouCwJFN\n", "P6T0K+Xjp/Q=\n")), l2.decrypt("JY1LUxBJn9Qfs0xfOEmb2TM=\n", "Vt0qMHso+LE=\n")).get(null);
                if (obj instanceof Map) {
                    ((Map) obj).clear();
                }
            } catch (Throwable unused) {
            }
            try {
                Object obj2 = findField(Parcel.class, l2.decrypt("pzOxtifnqz65\n", "ynDD00aTxEw=\n")).get(null);
                if (obj2 instanceof Map) {
                    ((Map) obj2).clear();
                }
            } catch (Throwable unused2) {
            }
            try {
                Object obj3 = findField(Parcel.class, l2.decrypt("F0uKxLmKj2QWforZpJ2Y\n", "ZBvrrcvv6yc=\n")).get(null);
                if (obj3 instanceof Map) {
                    ((Map) obj3).clear();
                }
            } catch (Throwable unused3) {
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 134217792);
            Signature[] signatureArr2 = (Build.VERSION.SDK_INT < 28 || z0.e(packageInfo) == null) ? packageInfo.signatures : (Signature[]) z0.e(packageInfo).getClass().getMethod(l2.decrypt("jjGPhX6V9TGHIJ6qeo3lN446nrZ9\n", "6VT7xA7+tl4=\n"), null).invoke(z0.e(packageInfo), null);
            if (signatureArr2 == null || signatureArr2.length <= 0) {
                k2.logToFloatingWindow(l2.decrypt("q2lojKjb0BjZeQAQ5Z/uXaxQVUT704FBxRxLF6r5+BnxTgMF9Q==\n", "SfTkrE92bv0=\n"), l2.decrypt("UJt3FsE=\n", "NekFebOoMq4=\n"));
            } else {
                String strEncodeToString2 = Base64.encodeToString(signatureArr2[0].toByteArray(), 2);
                boolean zEquals = strEncodeToString2.equals(str2);
                PrintStream printStream = System.out;
                printStream.println(l2.decrypt("wMMcKGlgUqQhunoeDDBiwcYLdgkTPUTNc5QHURtGKu0lzgM5qJqsM/4dp51vZFc=\n", "myuTtIDYzUA=\n") + strEncodeToString2);
                printStream.println(l2.decrypt("FooTisPcvGf383W8poyMAhBCe42EgoMEqd42/6rExC7zhwybAiZC8ChUqD/F2Lk=\n", "TWKcFipkI4M=\n") + str2);
                if (zEquals) {
                    k2.logToFloatingWindow(l2.decrypt("2yxq2n4EK/SpPQtGM0AVsd84fx8TNnqtow==\n", "ObDv+pmplRE=\n") + str, l2.decrypt("BuM=\n", "aYj7mlSiw3o=\n"));
                } else {
                    k2.logToFloatingWindow(l2.decrypt("WS13SDO0PG0rPR/UfvACKF4USoBgvG00N1dW1jGJD2wDPR/QVPEFPA==\n", "u7D7aNQZgog=\n"), l2.decrypt("iW/2lYU=\n", "7B2E+vc/44Q=\n"));
                }
            }
            if (fieldFindField.get(null) == creator2) {
                k2.logToFloatingWindow(l2.decrypt("q3XGJaJlyh4ojiZMnGLGWwq7BkSmS/tVr3L843+mT/3ZDMma\n", "SelDBfIEqXU=\n"), l2.decrypt("oJ0=\n", "z/aoqujLFPE=\n"));
            } else {
                k2.logToFloatingWindow(l2.decrypt("tlAR4xlXt5cAgs/jvJ5NMNlveGfr7UZzu3ERJ+GIFm7uKBNcv6J5M/t0dXL7\n", "VM2dw1oF8tY=\n"), l2.decrypt("s+5bJUk=\n", "1pwpSjsqUe4=\n"));
            }
        } catch (Throwable th2) {
            k2.logToFloatingWindow(l2.decrypt("lwM/0vH0dsH1PlRfq61MpZA6Ahqh7TOU7w==\n", "dZ6z8hVI3Cg=\n") + th2.getMessage(), l2.decrypt("73wbgAA=\n", "ig5p73Iqg5k=\n"));
            throw new RuntimeException(l2.decrypt("fE7GjZ39Pip2TsaEqQ==\n", "Fyeq4c2wHkw=\n"), th2);
        }
    }

    public static void killPM(final String str, String str2) {
        final Signature signature = new Signature(Base64.decode(str2, 0));
        final Parcelable.Creator creator = PackageInfo.CREATOR;
        try {
            findField(PackageInfo.class, l2.decrypt("ZEqL9RfAsg==\n", "JxjOtEOP4Ko=\n")).set(null, new Parcelable.Creator<PackageInfo>() { // from class: gTBLD.dev.XSSTG.free.SignatureSpoof.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PackageInfo createFromParcel(Parcel parcel) {
                    Signature[] signatureArrG;
                    PackageInfo packageInfo = (PackageInfo) creator.createFromParcel(parcel);
                    if (packageInfo.packageName.equals(str)) {
                        Signature[] signatureArr = packageInfo.signatures;
                        if (signatureArr != null && signatureArr.length > 0) {
                            signatureArr[0] = signature;
                        }
                        if (Build.VERSION.SDK_INT >= 28 && z0.e(packageInfo) != null && (signatureArrG = z0.g(z0.e(packageInfo))) != null && signatureArrG.length > 0) {
                            signatureArrG[0] = signature;
                        }
                    }
                    return packageInfo;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PackageInfo[] newArray(int i) {
                    return (PackageInfo[]) creator.newArray(i);
                }
            });
            if (Build.VERSION.SDK_INT >= 28) {
                PiQ2N.media.btkBu.pPhDR.HiddenApiBypass.addHiddenApiExemptions(l2.decrypt("+inuM2ShZOmZJ/N4Rq9/7tMkuw==\n", "tkiAVxbODY0=\n"), l2.decrypt("toxZaE6n4KvVjlhiSK3nu9WdWg==\n", "+u03DDzIic8=\n"), l2.decrypt("6dR7AYxz4kCK1GUV\n", "pbUVZf4ciyQ=\n"));
            }
            try {
                Object obj = findField(PackageManager.class, l2.decrypt("wrQ5wp70/KX4ij7OtvT4qNQ=\n", "seRYofWVm8A=\n")).get(null);
                obj.getClass().getMethod(l2.decrypt("YX9NwRw=\n", "AhMooG5TBmo=\n"), null).invoke(obj, null);
            } catch (Throwable unused) {
            }
            try {
                ((Map) findField(Parcel.class, l2.decrypt("VQV7l0Lq60lL\n", "OEYJ8iOehDs=\n")).get(null)).clear();
            } catch (Throwable unused2) {
            }
            try {
                ((Map) findField(Parcel.class, l2.decrypt("8XuyoCzijvTwTrK9MfWZ\n", "givTyV6H6rc=\n")).get(null)).clear();
            } catch (Throwable unused3) {
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
