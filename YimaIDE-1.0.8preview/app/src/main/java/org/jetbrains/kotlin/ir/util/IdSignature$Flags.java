package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003J\u000e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/IdSignature$Flags;", "", "recursive", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;IZ)V", "getRecursive", "()Z", "IS_EXPECT", "IS_JAVA_FOR_KOTLIN_OVERRIDE_PROPERTY", "IS_NATIVE_INTEROP_LIBRARY", "IS_SYNTHETIC_JAVA_PROPERTY", "encode", "", "isSet", "decode", "flags", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum IdSignature$Flags {
    IS_EXPECT(true),
    IS_JAVA_FOR_KOTLIN_OVERRIDE_PROPERTY(false),
    IS_NATIVE_INTEROP_LIBRARY(true),
    IS_SYNTHETIC_JAVA_PROPERTY(false);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final boolean recursive;

    IdSignature$Flags(boolean z) {
        this.recursive = z;
    }

    public static EnumEntries<IdSignature$Flags> getEntries() {
        return $ENTRIES;
    }

    public final boolean decode(long flags) {
        return (flags & (1 << ordinal())) != 0;
    }

    public final long encode(boolean isSet) {
        if (isSet) {
            return 1 << ordinal();
        }
        return 0L;
    }

    public final boolean getRecursive() {
        return this.recursive;
    }
}
