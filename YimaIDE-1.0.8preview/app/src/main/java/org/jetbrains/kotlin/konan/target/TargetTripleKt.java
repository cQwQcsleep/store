package org.jetbrains.kotlin.konan.target;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0012\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003¨\u0006\t"}, d2 = {"isMacabi", "", "Lorg/jetbrains/kotlin/konan/target/TargetTriple;", "(Lorg/jetbrains/kotlin/konan/target/TargetTriple;)Z", "isSimulator", "withOSVersion", "osVersion", "", "withoutVendor", "kotlin-native-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TargetTripleKt {
    public static final boolean isMacabi(TargetTriple targetTriple) {
        targetTriple.getClass();
        return Intrinsics.areEqual(targetTriple.getEnvironment(), "macabi");
    }

    public static final boolean isSimulator(TargetTriple targetTriple) {
        targetTriple.getClass();
        return Intrinsics.areEqual(targetTriple.getEnvironment(), "simulator");
    }

    public static final TargetTriple withOSVersion(TargetTriple targetTriple, String str) {
        targetTriple.getClass();
        str.getClass();
        return TargetTriple.copy$default(targetTriple, null, null, targetTriple.getOs() + str, null, 11, null);
    }

    public static final String withoutVendor(TargetTriple targetTriple) {
        String str;
        targetTriple.getClass();
        if (targetTriple.getEnvironment() != null) {
            str = "-" + targetTriple.getEnvironment();
        } else {
            str = "";
        }
        return targetTriple.getArchitecture() + '-' + targetTriple.getOs() + str;
    }
}
