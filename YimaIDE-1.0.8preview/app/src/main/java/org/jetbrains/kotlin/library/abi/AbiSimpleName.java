package org.jetbrains.kotlin.library.abi;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.ir.BuiltInOperatorNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0096\u0082\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u0005J\u001b\u0010\u000f\u001a\u00020\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\tHÖ\u0081\u0004¢\u0006\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002Ê\u0001\u0002\b\u0018Ê\u0001\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiSimpleName;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", BuiltInOperatorNames.COMPARE_TO, "", "other", "compareTo-C8x1a7g", "(Ljava/lang/String;Ljava/lang/String;)I", "toString", "toString-impl", "equals", "", "", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "hashCode-impl", "(Ljava/lang/String;)I", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@JvmInline
@ExperimentalLibraryAbiReader
public final class AbiSimpleName implements Comparable<AbiSimpleName> {
    private final String value;

    private /* synthetic */ AbiSimpleName(String str) {
        this.value = str;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AbiSimpleName m613boximpl(String str) {
        return new AbiSimpleName(str);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static String m615constructorimpl(String str) {
        str.getClass();
        if (!StringsKt.contains$default(str, AbiCompoundName.SEPARATOR, false, 2, (Object) null) && !StringsKt.contains$default(str, AbiQualifiedName.SEPARATOR, false, 2, (Object) null)) {
            return str;
        }
        dt1.a("Simple name contains illegal characters: ", str);
        return null;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m616equalsimpl(String str, Object obj) {
        return (obj instanceof AbiSimpleName) && Intrinsics.areEqual(str, ((AbiSimpleName) obj).m621unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m617equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual(str, str2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m618hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m619toStringimpl(String str) {
        return str;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(AbiSimpleName abiSimpleName) {
        return m620compareToC8x1a7g(abiSimpleName.m621unboximpl());
    }

    /* JADX INFO: renamed from: compareTo-C8x1a7g, reason: not valid java name */
    public int m620compareToC8x1a7g(String str) {
        str.getClass();
        return m614compareToC8x1a7g(this.value, str);
    }

    public boolean equals(Object obj) {
        return m616equalsimpl(this.value, obj);
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return m618hashCodeimpl(this.value);
    }

    public String toString() {
        return m619toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m621unboximpl() {
        return this.value;
    }

    /* JADX INFO: renamed from: compareTo-C8x1a7g, reason: not valid java name */
    public static int m614compareToC8x1a7g(String str, String str2) {
        str2.getClass();
        return str.compareTo(str2);
    }
}
