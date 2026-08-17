package org.jetbrains.kotlin.konan.target;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/Ar;", "", "ar", "", "(Ljava/lang/String;)V", "getAr", "()Ljava/lang/String;", "GnuAr", "LlvmAr", "Lorg/jetbrains/kotlin/konan/target/Ar$GnuAr;", "Lorg/jetbrains/kotlin/konan/target/Ar$LlvmAr;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
abstract class Ar {
    private final String ar;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/Ar$GnuAr;", "Lorg/jetbrains/kotlin/konan/target/Ar;", "ar", "", "(Ljava/lang/String;)V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class GnuAr extends Ar {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GnuAr(String str) {
            super(str, null);
            str.getClass();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/Ar$LlvmAr;", "Lorg/jetbrains/kotlin/konan/target/Ar;", "ar", "", "(Ljava/lang/String;)V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class LlvmAr extends Ar {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LlvmAr(String str) {
            super(str, null);
            str.getClass();
        }
    }

    private Ar(String str) {
        this.ar = str;
    }

    public final String getAr() {
        return this.ar;
    }

    public /* synthetic */ Ar(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
