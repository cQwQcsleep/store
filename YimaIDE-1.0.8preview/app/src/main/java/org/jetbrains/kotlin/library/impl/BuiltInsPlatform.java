package org.jetbrains.kotlin.library.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/library/impl/BuiltInsPlatform;", "", "(Ljava/lang/String;I)V", "JVM", "JS", "NATIVE", "WASM", "JKLIB", "COMMON", "Companion", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public enum BuiltInsPlatform {
    JVM,
    JS,
    NATIVE,
    WASM,
    JKLIB,
    COMMON;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/library/impl/BuiltInsPlatform$Companion;", "", "()V", "parseFromString", "Lorg/jetbrains/kotlin/library/impl/BuiltInsPlatform;", "name", "", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BuiltInsPlatform parseFromString(String name) {
            name.getClass();
            for (BuiltInsPlatform builtInsPlatform : BuiltInsPlatform.values()) {
                if (Intrinsics.areEqual(builtInsPlatform.name(), name)) {
                    return builtInsPlatform;
                }
            }
            return null;
        }

        private Companion() {
        }
    }
}
