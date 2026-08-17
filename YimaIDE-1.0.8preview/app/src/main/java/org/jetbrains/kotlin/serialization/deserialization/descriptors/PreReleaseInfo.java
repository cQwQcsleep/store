package org.jetbrains.kotlin.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/PreReleaseInfo;", "", "isInvisible", "", "poisoningFeatures", "", "", "<init>", "(ZLjava/util/List;)V", "()Z", "getPoisoningFeatures", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final /* data */ class PreReleaseInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final PreReleaseInfo DEFAULT_VISIBLE;
    private final boolean isInvisible;
    private final List<String> poisoningFeatures;

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        INSTANCE = new Companion(defaultConstructorMarker);
        DEFAULT_VISIBLE = new PreReleaseInfo(false, defaultConstructorMarker, 2, defaultConstructorMarker);
    }

    public /* synthetic */ PreReleaseInfo(boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PreReleaseInfo copy$default(PreReleaseInfo preReleaseInfo, boolean z, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            z = preReleaseInfo.isInvisible;
        }
        if ((i & 2) != 0) {
            list = preReleaseInfo.poisoningFeatures;
        }
        return preReleaseInfo.copy(z, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsInvisible() {
        return this.isInvisible;
    }

    public final List<String> component2() {
        return this.poisoningFeatures;
    }

    public final PreReleaseInfo copy(boolean isInvisible, List<String> poisoningFeatures) {
        poisoningFeatures.getClass();
        return new PreReleaseInfo(isInvisible, poisoningFeatures);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PreReleaseInfo)) {
            return false;
        }
        PreReleaseInfo preReleaseInfo = (PreReleaseInfo) other;
        return this.isInvisible == preReleaseInfo.isInvisible && Intrinsics.areEqual(this.poisoningFeatures, preReleaseInfo.poisoningFeatures);
    }

    public final List<String> getPoisoningFeatures() {
        return this.poisoningFeatures;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.isInvisible) * 31) + this.poisoningFeatures.hashCode();
    }

    public final boolean isInvisible() {
        return this.isInvisible;
    }

    public String toString() {
        return "PreReleaseInfo(isInvisible=" + this.isInvisible + ", poisoningFeatures=" + this.poisoningFeatures + ')';
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/PreReleaseInfo$Companion;", "", "<init>", "()V", "DEFAULT_VISIBLE", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/PreReleaseInfo;", "getDEFAULT_VISIBLE", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/PreReleaseInfo;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PreReleaseInfo getDEFAULT_VISIBLE() {
            return PreReleaseInfo.DEFAULT_VISIBLE;
        }

        private Companion() {
        }
    }

    public PreReleaseInfo(boolean z, List<String> list) {
        list.getClass();
        this.isInvisible = z;
        this.poisoningFeatures = list;
    }
}
