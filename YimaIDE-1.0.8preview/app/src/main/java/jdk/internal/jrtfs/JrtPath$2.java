package jdk.internal.jrtfs;

import java.nio.file.AccessMode;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public /* synthetic */ class JrtPath$2 {
    static final /* synthetic */ int[] $SwitchMap$java$nio$file$AccessMode;

    static {
        int[] iArr = new int[AccessMode.values().length];
        $SwitchMap$java$nio$file$AccessMode = iArr;
        try {
            iArr[AccessMode.READ.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            $SwitchMap$java$nio$file$AccessMode[AccessMode.WRITE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            $SwitchMap$java$nio$file$AccessMode[AccessMode.EXECUTE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
