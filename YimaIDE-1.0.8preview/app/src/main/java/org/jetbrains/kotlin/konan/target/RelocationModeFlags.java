package org.jetbrains.kotlin.konan.target;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.konan.properties.TargetableExternalStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\fJ\u000e\u0010\n\u001a\u00020\u0003*\u0004\u0018\u00010\u000bH\u0002R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/RelocationModeFlags;", "Lorg/jetbrains/kotlin/konan/properties/TargetableExternalStorage;", "dynamicLibraryRelocationMode", "Lorg/jetbrains/kotlin/konan/target/RelocationModeFlags$Mode;", "getDynamicLibraryRelocationMode", "()Lorg/jetbrains/kotlin/konan/target/RelocationModeFlags$Mode;", "executableRelocationMode", "getExecutableRelocationMode", "staticLibraryRelocationMode", "getStaticLibraryRelocationMode", "mode", "", "Mode", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface RelocationModeFlags extends TargetableExternalStorage {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/RelocationModeFlags$Mode;", "", "(Ljava/lang/String;I)V", "PIC", "STATIC", "DEFAULT", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum Mode {
        PIC,
        STATIC,
        DEFAULT
    }

    private default Mode mode(String str) {
        String lowerCase;
        if (str != null) {
            lowerCase = str.toLowerCase();
            lowerCase.getClass();
        } else {
            lowerCase = null;
        }
        if (lowerCase == null) {
            return Mode.DEFAULT;
        }
        if (Intrinsics.areEqual(lowerCase, "pic")) {
            return Mode.PIC;
        }
        if (Intrinsics.areEqual(lowerCase, "static")) {
            return Mode.STATIC;
        }
        w04.a("Unknown relocation mode: ", str);
        return null;
    }

    default Mode getDynamicLibraryRelocationMode() {
        return mode(targetString("dynamicLibraryRelocationMode"));
    }

    default Mode getExecutableRelocationMode() {
        return mode(targetString("executableRelocationMode"));
    }

    default Mode getStaticLibraryRelocationMode() {
        return mode(targetString("staticLibraryRelocationMode"));
    }
}
