package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class VersionSpecificBehaviorKt {
    public static final boolean isKotlin1Dot4OrLater(BinaryVersion binaryVersion) {
        binaryVersion.getClass();
        return (binaryVersion.getMajor() == 1 && binaryVersion.getMinor() >= 4) || binaryVersion.getMajor() > 1;
    }

    public static final boolean isVersionRequirementTableWrittenCorrectly(BinaryVersion binaryVersion) {
        binaryVersion.getClass();
        return isKotlin1Dot4OrLater(binaryVersion);
    }
}
