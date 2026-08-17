package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0011HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FutureApiDeprecationInfo;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "deprecationLevel", "Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;", "propagatesToOverrides", Argument.Delimiters.none, "sinceVersion", "Lorg/jetbrains/kotlin/config/ApiVersion;", "<init>", "(Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;ZLorg/jetbrains/kotlin/config/ApiVersion;)V", "getDeprecationLevel", "()Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;", "getPropagatesToOverrides", "()Z", "getSinceVersion", "()Lorg/jetbrains/kotlin/config/ApiVersion;", "getMessage", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "component1", "component2", "component3", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class FutureApiDeprecationInfo extends FirDeprecationInfo {
    private final DeprecationLevelValue deprecationLevel;
    private final boolean propagatesToOverrides;
    private final ApiVersion sinceVersion;

    public FutureApiDeprecationInfo(DeprecationLevelValue deprecationLevelValue, boolean z, ApiVersion apiVersion) {
        deprecationLevelValue.getClass();
        apiVersion.getClass();
        this.deprecationLevel = deprecationLevelValue;
        this.propagatesToOverrides = z;
        this.sinceVersion = apiVersion;
    }

    public static /* synthetic */ FutureApiDeprecationInfo copy$default(FutureApiDeprecationInfo futureApiDeprecationInfo, DeprecationLevelValue deprecationLevelValue, boolean z, ApiVersion apiVersion, int i, Object obj) {
        if ((i & 1) != 0) {
            deprecationLevelValue = futureApiDeprecationInfo.deprecationLevel;
        }
        if ((i & 2) != 0) {
            z = futureApiDeprecationInfo.propagatesToOverrides;
        }
        if ((i & 4) != 0) {
            apiVersion = futureApiDeprecationInfo.sinceVersion;
        }
        return futureApiDeprecationInfo.copy(deprecationLevelValue, z, apiVersion);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DeprecationLevelValue getDeprecationLevel() {
        return this.deprecationLevel;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getPropagatesToOverrides() {
        return this.propagatesToOverrides;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ApiVersion getSinceVersion() {
        return this.sinceVersion;
    }

    public final FutureApiDeprecationInfo copy(DeprecationLevelValue deprecationLevel, boolean propagatesToOverrides, ApiVersion sinceVersion) {
        deprecationLevel.getClass();
        sinceVersion.getClass();
        return new FutureApiDeprecationInfo(deprecationLevel, propagatesToOverrides, sinceVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FutureApiDeprecationInfo)) {
            return false;
        }
        FutureApiDeprecationInfo futureApiDeprecationInfo = (FutureApiDeprecationInfo) other;
        return this.deprecationLevel == futureApiDeprecationInfo.deprecationLevel && this.propagatesToOverrides == futureApiDeprecationInfo.propagatesToOverrides && Intrinsics.areEqual(this.sinceVersion, futureApiDeprecationInfo.sinceVersion);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
    public DeprecationLevelValue getDeprecationLevel() {
        return this.deprecationLevel;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
    public String getMessage(FirSession session) {
        session.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
    public boolean getPropagatesToOverrides() {
        return this.propagatesToOverrides;
    }

    public final ApiVersion getSinceVersion() {
        return this.sinceVersion;
    }

    public int hashCode() {
        return (((this.deprecationLevel.hashCode() * 31) + Boolean.hashCode(this.propagatesToOverrides)) * 31) + this.sinceVersion.hashCode();
    }

    public String toString() {
        return "FutureApiDeprecationInfo(deprecationLevel=" + this.deprecationLevel + ", propagatesToOverrides=" + this.propagatesToOverrides + ", sinceVersion=" + this.sinceVersion + ')';
    }
}
