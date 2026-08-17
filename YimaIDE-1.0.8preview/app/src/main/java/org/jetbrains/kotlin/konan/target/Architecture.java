package org.jetbrains.kotlin.konan.target;

import kotlin.Deprecated;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/Architecture;", "", "bitness", "", "(Ljava/lang/String;II)V", "getBitness$annotations", "()V", "getBitness", "()I", "X64", "X86", "ARM64", "ARM32", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public enum Architecture {
    X64(64),
    X86(32),
    ARM64(64),
    ARM32(32);

    private final int bitness;

    Architecture(int i) {
        this.bitness = i;
    }

    @Deprecated(message = "Compare Architecture entries instead.")
    public static /* synthetic */ void getBitness$annotations() {
    }

    public final int getBitness() {
        return this.bitness;
    }
}
