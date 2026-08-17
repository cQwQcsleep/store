package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import androidx.compose.compiler.plugins.kotlin.analysis.StabilityExternalClassNameMatchingKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class BinaryVersion {
    public static final Companion Companion = new Companion(null);
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
                throw new IllegalArgumentException("BinaryVersion with length more than 1024 are not supported. Provided length " + iArr.length + StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR);
            }
            listEmptyList = CollectionsKt.toList(ArraysKt.asList(iArr).subList(3, iArr.length));
        }
        this.rest = listEmptyList;
    }

    public boolean equals(Object obj) {
        if (obj == null || !Intrinsics.areEqual(getClass(), obj.getClass())) {
            return false;
        }
        BinaryVersion binaryVersion = (BinaryVersion) obj;
        return this.major == binaryVersion.major && this.minor == binaryVersion.minor && this.patch == binaryVersion.patch && Intrinsics.areEqual(this.rest, binaryVersion.rest);
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public int hashCode() {
        int i = this.major;
        int i2 = i + (i * 31) + this.minor;
        int i3 = i2 + (i2 * 31) + this.patch;
        return i3 + (i3 * 31) + this.rest.hashCode();
    }

    public final boolean isAtLeast(int i, int i2, int i3) {
        int i4 = this.major;
        if (i4 > i) {
            return true;
        }
        if (i4 < i) {
            return false;
        }
        int i5 = this.minor;
        if (i5 > i2) {
            return true;
        }
        return i5 >= i2 && this.patch >= i3;
    }

    public final boolean isAtMost(int i, int i2, int i3) {
        int i4 = this.major;
        if (i4 < i) {
            return true;
        }
        if (i4 > i) {
            return false;
        }
        int i5 = this.minor;
        if (i5 < i2) {
            return true;
        }
        return i5 <= i2 && this.patch <= i3;
    }

    public final boolean isCompatibleTo(BinaryVersion binaryVersion) {
        binaryVersion.getClass();
        int i = this.major;
        if (i == 0) {
            return binaryVersion.major == 0 && this.minor == binaryVersion.minor;
        }
        return i == binaryVersion.major && this.minor <= binaryVersion.minor;
    }

    public final int[] toArray() {
        return this.numbers;
    }

    public String toString() {
        int[] array = toArray();
        ArrayList arrayList = new ArrayList();
        for (int i : array) {
            if (i == -1) {
                break;
            }
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList.isEmpty() ? "unknown" : CollectionsKt.joinToString$default(arrayList, ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final boolean isAtLeast(BinaryVersion binaryVersion) {
        binaryVersion.getClass();
        return isAtLeast(binaryVersion.major, binaryVersion.minor, binaryVersion.patch);
    }
}
