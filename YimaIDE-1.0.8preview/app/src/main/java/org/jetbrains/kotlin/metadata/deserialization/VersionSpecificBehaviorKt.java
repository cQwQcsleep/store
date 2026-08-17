package org.jetbrains.kotlin.metadata.deserialization;

import javax.xml.transform.OutputKeys;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0005"}, d2 = {"isVersionRequirementTableWrittenCorrectly", "", OutputKeys.VERSION, "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "isKotlin1Dot4OrLater", "org.jetbrains.kotlin:metadata"}, k = 2, mv = {2, 0, 0}, xi = 48)
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
