package org.jetbrains.kotlin.konan;

import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/konan/KonanAbiVersion;", "", OutputKeys.VERSION, "", "(I)V", "getVersion", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class KonanAbiVersion {
    private final int version;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KonanAbiVersion CURRENT = new KonanAbiVersion(10);

    public KonanAbiVersion(int i) {
        this.version = i;
    }

    public static /* synthetic */ KonanAbiVersion copy$default(KonanAbiVersion konanAbiVersion, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = konanAbiVersion.version;
        }
        return konanAbiVersion.copy(i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    public final KonanAbiVersion copy(int version) {
        return new KonanAbiVersion(version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof KonanAbiVersion) && this.version == ((KonanAbiVersion) other).version;
    }

    public final int getVersion() {
        return this.version;
    }

    public int hashCode() {
        return Integer.hashCode(this.version);
    }

    public String toString() {
        return String.valueOf(this.version);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/konan/KonanAbiVersion$Companion;", "", "()V", "CURRENT", "Lorg/jetbrains/kotlin/konan/KonanAbiVersion;", "getCURRENT", "()Lorg/jetbrains/kotlin/konan/KonanAbiVersion;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KonanAbiVersion getCURRENT() {
            return KonanAbiVersion.CURRENT;
        }

        private Companion() {
        }
    }
}
