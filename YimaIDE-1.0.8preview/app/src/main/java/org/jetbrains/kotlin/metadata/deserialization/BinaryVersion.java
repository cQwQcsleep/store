package org.jetbrains.kotlin.metadata.deserialization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0013\u0012\n\u0010\u0002\u001a\u00020\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0013H&J\u0006\u0010\u0014\u001a\u00020\u0003J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0000H\u0004J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0000J\u001e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0000J\u001e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004J\n\u0010\u001a\u001a\u00020\u001bH\u0096\u0080\u0004J\u0014\u0010\u001c\u001a\u00020\u00132\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u001e\u001a\u00020\u0004H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "", "numbers", "", "", "<init>", "([I)V", "major", "getMajor", "()I", "minor", "getMinor", "patch", "getPatch", "rest", "", "getRest", "()Ljava/util/List;", "isCompatibleWithCurrentCompilerVersion", "", "toArray", "isCompatibleTo", "ourVersion", "isAtLeast", OutputKeys.VERSION, "isAtMost", "toString", "", "equals", "other", "hashCode", "Companion", "org.jetbrains.kotlin:metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class BinaryVersion {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int MAX_LENGTH = 1024;
    private final int major;
    private final int minor;
    private final int[] numbers;
    private final int patch;
    private final List<Integer> rest;

    public BinaryVersion(int... iArr) {
        List<Integer> listEmptyList;
        iArr.getClass();
        this.numbers = iArr;
        Integer orNull = ArraysKt.getOrNull(iArr, 0);
        this.major = orNull != null ? orNull.intValue() : -1;
        Integer orNull2 = ArraysKt.getOrNull(iArr, 1);
        this.minor = orNull2 != null ? orNull2.intValue() : -1;
        Integer orNull3 = ArraysKt.getOrNull(iArr, 2);
        this.patch = orNull3 != null ? orNull3.intValue() : -1;
        if (iArr.length <= 3) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            if (iArr.length > 1024) {
                throw new IllegalArgumentException("BinaryVersion with length more than 1024 are not supported. Provided length " + iArr.length + AbiCompoundName.SEPARATOR);
            }
            listEmptyList = CollectionsKt.toList(ArraysKt.asList(iArr).subList(3, iArr.length));
        }
        this.rest = listEmptyList;
    }

    @JvmStatic
    public static final int[] parseVersionArray(String str) {
        return INSTANCE.parseVersionArray(str);
    }

    public boolean equals(Object other) {
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        BinaryVersion binaryVersion = (BinaryVersion) other;
        return this.major == binaryVersion.major && this.minor == binaryVersion.minor && this.patch == binaryVersion.patch && Intrinsics.areEqual(this.rest, binaryVersion.rest);
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    public final List<Integer> getRest() {
        return this.rest;
    }

    public int hashCode() {
        int i = this.major;
        int i2 = i + (i * 31) + this.minor;
        int i3 = i2 + (i2 * 31) + this.patch;
        return i3 + (i3 * 31) + this.rest.hashCode();
    }

    public final boolean isAtLeast(int major, int minor, int patch) {
        int i = this.major;
        if (i > major) {
            return true;
        }
        if (i < major) {
            return false;
        }
        int i2 = this.minor;
        if (i2 > minor) {
            return true;
        }
        return i2 >= minor && this.patch >= patch;
    }

    public final boolean isAtMost(int major, int minor, int patch) {
        int i = this.major;
        if (i < major) {
            return true;
        }
        if (i > major) {
            return false;
        }
        int i2 = this.minor;
        if (i2 < minor) {
            return true;
        }
        return i2 <= minor && this.patch <= patch;
    }

    public final boolean isCompatibleTo(BinaryVersion ourVersion) {
        ourVersion.getClass();
        int i = this.major;
        if (i == 0) {
            return ourVersion.major == 0 && this.minor == ourVersion.minor;
        }
        return i == ourVersion.major && this.minor <= ourVersion.minor;
    }

    public abstract boolean isCompatibleWithCurrentCompilerVersion();

    /* JADX INFO: renamed from: toArray, reason: from getter */
    public final int[] getNumbers() {
        return this.numbers;
    }

    public String toString() {
        int[] numbers = getNumbers();
        ArrayList arrayList = new ArrayList();
        for (int i : numbers) {
            if (i == -1) {
                break;
            }
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList.isEmpty() ? "unknown" : CollectionsKt.joinToString$default(arrayList, ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion$Companion;", "", "<init>", "()V", "MAX_LENGTH", "", "UNKNOWN", "parseVersionArray", "", "string", "", "org.jetbrains.kotlin:metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final int[] parseVersionArray(String string) {
            string.getClass();
            List listSplit$default = StringsKt.split$default(string, new String[]{"."}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                Integer intOrNull = StringsKt.toIntOrNull((String) it.next());
                if (intOrNull == null) {
                    return null;
                }
                arrayList.add(intOrNull);
            }
            return ArraysKt.toIntArray((Integer[]) arrayList.toArray(new Integer[0]));
        }

        private Companion() {
        }
    }

    public final boolean isAtLeast(BinaryVersion version) {
        version.getClass();
        return isAtLeast(version.major, version.minor, version.patch);
    }

    public final boolean isAtMost(BinaryVersion version) {
        version.getClass();
        return isAtMost(version.major, version.minor, version.patch);
    }
}
