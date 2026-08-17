package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\rHÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/RequireKotlinDeprecationInfo;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "deprecationLevel", "Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;", "versionRequirement", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement;", "<init>", "(Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement;)V", "getDeprecationLevel", "()Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;", "getVersionRequirement", "()Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement;", "getMessage", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "propagatesToOverrides", Argument.Delimiters.none, "getPropagatesToOverrides", "()Z", "component1", "component2", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class RequireKotlinDeprecationInfo extends FirDeprecationInfo {
    private final DeprecationLevelValue deprecationLevel;
    private final VersionRequirement versionRequirement;

    public RequireKotlinDeprecationInfo(DeprecationLevelValue deprecationLevelValue, VersionRequirement versionRequirement) {
        deprecationLevelValue.getClass();
        versionRequirement.getClass();
        this.deprecationLevel = deprecationLevelValue;
        this.versionRequirement = versionRequirement;
    }

    public static /* synthetic */ RequireKotlinDeprecationInfo copy$default(RequireKotlinDeprecationInfo requireKotlinDeprecationInfo, DeprecationLevelValue deprecationLevelValue, VersionRequirement versionRequirement, int i, Object obj) {
        if ((i & 1) != 0) {
            deprecationLevelValue = requireKotlinDeprecationInfo.deprecationLevel;
        }
        if ((i & 2) != 0) {
            versionRequirement = requireKotlinDeprecationInfo.versionRequirement;
        }
        return requireKotlinDeprecationInfo.copy(deprecationLevelValue, versionRequirement);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DeprecationLevelValue getDeprecationLevel() {
        return this.deprecationLevel;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final VersionRequirement getVersionRequirement() {
        return this.versionRequirement;
    }

    public final RequireKotlinDeprecationInfo copy(DeprecationLevelValue deprecationLevel, VersionRequirement versionRequirement) {
        deprecationLevel.getClass();
        versionRequirement.getClass();
        return new RequireKotlinDeprecationInfo(deprecationLevel, versionRequirement);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RequireKotlinDeprecationInfo)) {
            return false;
        }
        RequireKotlinDeprecationInfo requireKotlinDeprecationInfo = (RequireKotlinDeprecationInfo) other;
        return this.deprecationLevel == requireKotlinDeprecationInfo.deprecationLevel && Intrinsics.areEqual(this.versionRequirement, requireKotlinDeprecationInfo.versionRequirement);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
    public DeprecationLevelValue getDeprecationLevel() {
        return this.deprecationLevel;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
    public String getMessage(FirSession session) {
        session.getClass();
        return this.versionRequirement.getMessage();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
    public boolean getPropagatesToOverrides() {
        return false;
    }

    public final VersionRequirement getVersionRequirement() {
        return this.versionRequirement;
    }

    public int hashCode() {
        return (this.deprecationLevel.hashCode() * 31) + this.versionRequirement.hashCode();
    }

    public String toString() {
        return "RequireKotlinDeprecationInfo(deprecationLevel=" + this.deprecationLevel + ", versionRequirement=" + this.versionRequirement + ')';
    }
}
