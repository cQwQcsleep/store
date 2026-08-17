package org.jetbrains.kotlin.konan;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.library.KlibConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/konan/ForeignExceptionMode;", "", "()V", "Companion", "Mode", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ForeignExceptionMode {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String manifestKey = "foreignExceptionMode";

    /* JADX INFO: renamed from: default, reason: not valid java name */
    private static final Mode f2default = Mode.TERMINATE;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/konan/ForeignExceptionMode$Mode;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "TERMINATE", "OBJC_WRAP", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum Mode {
        TERMINATE("terminate"),
        OBJC_WRAP("objc-wrap");

        private final String value;

        Mode(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/konan/ForeignExceptionMode$Companion;", "", "()V", KlibConstants.KLIB_DEFAULT_COMPONENT_NAME, "Lorg/jetbrains/kotlin/konan/ForeignExceptionMode$Mode;", "getDefault", "()Lorg/jetbrains/kotlin/konan/ForeignExceptionMode$Mode;", "manifestKey", "", "getManifestKey", "()Ljava/lang/String;", "byValue", "value", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Mode byValue(String value) {
            Mode mode;
            if (value == null) {
                return getDefault();
            }
            Mode[] modeArrValues = Mode.values();
            int length = modeArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    mode = null;
                    break;
                }
                mode = modeArrValues[i];
                if (Intrinsics.areEqual(mode.getValue(), value)) {
                    break;
                }
                i++;
            }
            if (mode != null) {
                return mode;
            }
            w01.a("Illegal ForeignExceptionMode ".concat(value));
            return null;
        }

        public final Mode getDefault() {
            return ForeignExceptionMode.f2default;
        }

        public final String getManifestKey() {
            return ForeignExceptionMode.manifestKey;
        }

        private Companion() {
        }
    }
}
