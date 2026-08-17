package org.jetbrains.kotlin.konan.target;

import java.util.List;
import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.ir.BuiltInOperatorNames;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0000H\u0096\u0002J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/XcodeVersion;", "", "major", "", "minor", "(II)V", "getMajor", "()I", "getMinor", BuiltInOperatorNames.COMPARE_TO, "other", "component1", "component2", "copy", "equals", "", "", "hashCode", "toString", "", "Companion", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class XcodeVersion implements Comparable<XcodeVersion> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final XcodeVersion maxTested = new XcodeVersion(26, 4);
    private final int major;
    private final int minor;

    public XcodeVersion(int i, int i2) {
        this.major = i;
        this.minor = i2;
    }

    public static /* synthetic */ XcodeVersion copy$default(XcodeVersion xcodeVersion, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = xcodeVersion.major;
        }
        if ((i3 & 2) != 0) {
            i2 = xcodeVersion.minor;
        }
        return xcodeVersion.copy(i, i2);
    }

    @Override // java.lang.Comparable
    public int compareTo(XcodeVersion other) {
        other.getClass();
        int iCompare = Intrinsics.compare(this.major, other.major);
        return iCompare == 0 ? Intrinsics.compare(this.minor, other.minor) : iCompare;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getMajor() {
        return this.major;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMinor() {
        return this.minor;
    }

    public final XcodeVersion copy(int major, int minor) {
        return new XcodeVersion(major, minor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof XcodeVersion)) {
            return false;
        }
        XcodeVersion xcodeVersion = (XcodeVersion) other;
        return this.major == xcodeVersion.major && this.minor == xcodeVersion.minor;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public int hashCode() {
        return (Integer.hashCode(this.major) * 31) + Integer.hashCode(this.minor);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.major);
        sb.append(AbiCompoundName.SEPARATOR);
        sb.append(this.minor);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/XcodeVersion$Companion;", "", "()V", "maxTested", "Lorg/jetbrains/kotlin/konan/target/XcodeVersion;", "getMaxTested", "()Lorg/jetbrains/kotlin/konan/target/XcodeVersion;", "parse", OutputKeys.VERSION, "", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final XcodeVersion getMaxTested() {
            return XcodeVersion.maxTested;
        }

        public final XcodeVersion parse(String version) {
            Integer intOrNull;
            version.getClass();
            List listSplit = new Regex("(\\s+|\\.|-)").split(version, 0);
            Integer intOrNull2 = StringsKt.toIntOrNull((String) listSplit.get(0));
            if (intOrNull2 != null) {
                int iIntValue = intOrNull2.intValue();
                String str = (String) CollectionsKt.getOrNull(listSplit, 1);
                if (str != null && (intOrNull = StringsKt.toIntOrNull(str)) != null) {
                    return new XcodeVersion(iIntValue, intOrNull.intValue());
                }
            }
            return null;
        }

        private Companion() {
        }
    }
}
