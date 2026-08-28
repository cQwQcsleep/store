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
        sb.append(" not found in class ");
        sb.append(cls.getName());
        throw new NoSuchFieldException(sb.toString());
    }

    public static void killPM(Context context, final String str, String str2) {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                try {
                    PiQ2N.media.btkBu.pPhDR.HiddenApiBypass.addHiddenApiExemptions("");
                    System.out.println("[菜鸟云验证] killPM解除安卓9+隐藏API限制");
                } catch (Throwable th) {
                    System.out.println("[菜鸟云验证] 解除限制失败：" + th.getMessage());
                }
            }
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            if (signatureArr != null && signatureArr.length > 0) {
                String strEncodeToString = Base64.encodeToString(signatureArr[0].toByteArray(), 2);
                System.out.println("[菜鸟云验证] 当前自身签名(Base64): " + strEncodeToString);
            }
            System.out.println("[菜鸟云验证] 即将伪造签名(Base64): " + str2);
            final Signature signature = new Signature(Base64.decode(str2, 0));
            Field fieldFindField = findField(PackageInfo.class, "CREATOR");
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
                                Signature[] signatureArr3 = (Signature[]) z0.e(packageInfo).getClass().getMethod("getApkContentsSigners", null).invoke(z0.e(packageInfo), null);
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
                Object obj = findField(Class.forName("android.content.pm.PackageManager"), "sPackageInfoCache").get(null);
                if (obj instanceof Map) {
                    ((Map) obj).clear();
                }
            } catch (Throwable unused) {
            }
            try {
                Object obj2 = findField(Parcel.class, "mCreators").get(null);
                if (obj2 instanceof Map) {
                    ((Map) obj2).clear();
                }
            } catch (Throwable unused2) {
            }
            try {
                Object obj3 = findField(Parcel.class, "sPairedCreators").get(null);
                if (obj3 instanceof Map) {
                    ((Map) obj3).clear();
                }
            } catch (Throwable unused3) {
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 134217792);
            Signature[] signatureArr2 = (Build.VERSION.SDK_INT < 28 || z0.e(packageInfo) == null) ? packageInfo.signatures : (Signature[]) z0.e(packageInfo).getClass().getMethod("getApkContentsSigners", null).invoke(z0.e(packageInfo), null);
            if (signatureArr2 == null || signatureArr2.length <= 0) {
                k2.logToFloatingWindow("❌ 签名伪造失败，读取为空", "error");
            } else {
                String strEncodeToString2 = Base64.encodeToString(signatureArr2[0].toByteArray(), 2);
                boolean zEquals = strEncodeToString2.equals(str2);
                PrintStream printStream = System.out;
                printStream.println("[菜鸟云验证] 当前返回签名(Base64)：" + strEncodeToString2);
                printStream.println("[菜鸟云验证] 目标伪造签名(Base64)：" + str2);
                if (zEquals) {
                    k2.logToFloatingWindow("✅ 签名伪造成功：" + str, "ok");
                } else {
                    k2.logToFloatingWindow("❌ 签名伪造失败，签名不一致", "error");
                }
            }
            if (fieldFindField.get(null) == creator2) {
                k2.logToFloatingWindow("✅ PackageInfo.CREATOR 替换成功", "ok");
            } else {
                k2.logToFloatingWindow("❌ CREATOR 替换失败，仍为原始对象", "error");
            }
        } catch (Throwable th2) {
            k2.logToFloatingWindow("❌ 伪造签名失败：" + th2.getMessage(), "error");
            throw new RuntimeException("killPM failed", th2);
        }
    }

    public static void killPM(final String str, String str2) {
        final Signature signature = new Signature(Base64.decode(str2, 0));
        final Parcelable.Creator creator = PackageInfo.CREATOR;
        try {
            findField(PackageInfo.class, "CREATOR").set(null, new Parcelable.Creator<PackageInfo>() { // from class: gTBLD.dev.XSSTG.free.SignatureSpoof.2
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
                PiQ2N.media.btkBu.pPhDR.HiddenApiBypass.addHiddenApiExemptions("Landroid/os/Parcel;", "Landroid/content/pm", "Landroid/app");
            }
            try {
                Object obj = findField(PackageManager.class, "sPackageInfoCache").get(null);
                obj.getClass().getMethod("clear", null).invoke(obj, null);
            } catch (Throwable unused) {
            }
            try {
                ((Map) findField(Parcel.class, "mCreators").get(null)).clear();
            } catch (Throwable unused2) {
            }
            try {
                ((Map) findField(Parcel.class, "sPairedCreators").get(null)).clear();
            } catch (Throwable unused3) {
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
