package com.shadow.kotlin.internal.jdk7;

import com.shadow.kotlin.internal.ProgressionUtilKt;
import com.shadow.kotlin.io.CloseableKt;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class JDK7PlatformImplementations extends ProgressionUtilKt {

    abstract class ReflectSdkVersion {
        public static final Integer sdkVersion;

        static {
            Object obj;
            Integer num = null;
            try {
                obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Throwable unused) {
            }
            Integer num2 = obj instanceof Integer ? (Integer) obj : null;
            if (num2 != null && num2.intValue() > 0) {
                num = num2;
            }
            sdkVersion = num;
        }
    }

    @Override // com.shadow.kotlin.internal.ProgressionUtilKt
    public final void addSuppressed(Throwable th, Throwable th2) {
        CloseableKt.checkNotNullParameter(th, "cause");
        CloseableKt.checkNotNullParameter(th2, "exception");
        Integer num = ReflectSdkVersion.sdkVersion;
        if (num == null || num.intValue() >= 19) {
            th.addSuppressed(th2);
        } else {
            super.addSuppressed(th, th2);
        }
    }
}
