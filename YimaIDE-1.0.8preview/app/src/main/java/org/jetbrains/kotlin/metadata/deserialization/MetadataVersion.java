package org.jetbrains.kotlin.metadata.deserialization;

import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0015\b\u0016\u0012\n\u0010\b\u001a\u00020\u0003\"\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\r\u001a\u00020\u0005H\u0016J\u000e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0000J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0000H\u0002J\u0006\u0010\u0012\u001a\u00020\u0000J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0000H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "versionArray", "", "isStrictSemantics", "", "<init>", "([IZ)V", "numbers", "", "([I)V", "()Z", "lastSupportedVersionWithThisLanguageVersion", "isCompatibleWithCurrentCompilerVersion", "isCompatible", "metadataVersionFromLanguageVersion", "isCompatibleInternal", "limitVersion", "next", "newerThan", "other", "Companion", "org.jetbrains.kotlin:metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MetadataVersion extends BinaryVersion {
    public static final MetadataVersion INSTANCE;
    public static final MetadataVersion INSTANCE_NEXT;
    public static final MetadataVersion INVALID_VERSION;
    private final boolean isStrictSemantics;

    static {
        MetadataVersion metadataVersion = new MetadataVersion(2, 4, 0);
        INSTANCE = metadataVersion;
        INSTANCE_NEXT = metadataVersion.next();
        INVALID_VERSION = new MetadataVersion(new int[0]);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MetadataVersion(int[] iArr, boolean z) {
        super(Arrays.copyOf(iArr, iArr.length));
        iArr.getClass();
        this.isStrictSemantics = z;
    }

    private final boolean isCompatibleInternal(MetadataVersion limitVersion) {
        if ((getMajor() == 1 && getMinor() == 0) || getMajor() == 0) {
            return false;
        }
        return !newerThan(limitVersion);
    }

    private final boolean newerThan(MetadataVersion other) {
        if (getMajor() > other.getMajor()) {
            return true;
        }
        return getMajor() >= other.getMajor() && getMinor() > other.getMinor();
    }

    public final boolean isCompatible(MetadataVersion metadataVersionFromLanguageVersion) {
        metadataVersionFromLanguageVersion.getClass();
        return isCompatibleInternal(metadataVersionFromLanguageVersion.lastSupportedVersionWithThisLanguageVersion(this.isStrictSemantics));
    }

    @Override // org.jetbrains.kotlin.metadata.deserialization.BinaryVersion
    public boolean isCompatibleWithCurrentCompilerVersion() {
        return isCompatibleInternal(this.isStrictSemantics ? INSTANCE : INSTANCE_NEXT);
    }

    /* JADX INFO: renamed from: isStrictSemantics, reason: from getter */
    public final boolean getIsStrictSemantics() {
        return this.isStrictSemantics;
    }

    public final MetadataVersion lastSupportedVersionWithThisLanguageVersion(boolean isStrictSemantics) {
        MetadataVersion metadataVersion = isStrictSemantics ? INSTANCE : INSTANCE_NEXT;
        return metadataVersion.newerThan(this) ? metadataVersion : this;
    }

    public final MetadataVersion next() {
        return (getMajor() == 1 && getMinor() == 9) ? new MetadataVersion(2, 0, 0) : new MetadataVersion(getMajor(), getMinor() + 1, 0);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MetadataVersion(int... iArr) {
        this(iArr, false);
        iArr.getClass();
    }
}
