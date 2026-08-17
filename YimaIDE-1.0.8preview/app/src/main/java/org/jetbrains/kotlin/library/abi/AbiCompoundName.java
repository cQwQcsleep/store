package org.jetbrains.kotlin.library.abi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.ir.BuiltInOperatorNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0087@\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001$B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0000H\u0096\u0082\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0003H\u0096\u0080\u0004¢\u0006\u0004\b\u0018\u0010\u0005J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0000H\u0086\u0004¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001e\u001a\u00020\u001a2\b\u0010\u0014\u001a\u0004\u0018\u00010\u001fHÖ\u0083\u0004¢\u0006\u0004\b \u0010!J\u0011\u0010\"\u001a\u00020\u000eHÖ\u0081\u0004¢\u0006\u0004\b#\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0005\u0088\u0001\u0002Ê\u0001\u0002\b&Ê\u0001\u0002\b'¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiCompoundName;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "nameSegments", "", "Lorg/jetbrains/kotlin/library/abi/AbiSimpleName;", "getNameSegments-impl", "(Ljava/lang/String;)Ljava/util/List;", "nameSegmentsCount", "", "getNameSegmentsCount-impl", "(Ljava/lang/String;)I", "simpleName", "getSimpleName-t-3xSXQ", BuiltInOperatorNames.COMPARE_TO, "other", "compareTo-6OaPNJE", "(Ljava/lang/String;Ljava/lang/String;)I", "toString", "toString-impl", "isContainerOf", "", "member", "isContainerOf-6OaPNJE", "(Ljava/lang/String;Ljava/lang/String;)Z", "equals", "", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "hashCode-impl", "Companion", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
@JvmInline
public final class AbiCompoundName implements Comparable<AbiCompoundName> {
    public static final char SEPARATOR = '.';
    private final String value;

    private /* synthetic */ AbiCompoundName(String str) {
        this.value = str;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AbiCompoundName m593boximpl(String str) {
        return new AbiCompoundName(str);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static String m595constructorimpl(String str) {
        str.getClass();
        if (!StringsKt.contains$default(str, AbiQualifiedName.SEPARATOR, false, 2, (Object) null)) {
            return str;
        }
        dt1.a("Compound name contains illegal characters: ", str);
        return null;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m596equalsimpl(String str, Object obj) {
        return (obj instanceof AbiCompoundName) && Intrinsics.areEqual(str, ((AbiCompoundName) obj).m605unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m597equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual(str, str2);
    }

    /* JADX INFO: renamed from: getNameSegments-impl, reason: not valid java name */
    public static final List<AbiSimpleName> m598getNameSegmentsimpl(String str) {
        List listSplit$default = StringsKt.split$default(str, new char[]{SEPARATOR}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            arrayList.add(AbiSimpleName.m613boximpl(AbiSimpleName.m615constructorimpl((String) it.next())));
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: getNameSegmentsCount-impl, reason: not valid java name */
    public static final int m599getNameSegmentsCountimpl(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '.') {
                i++;
            }
        }
        return i + 1;
    }

    /* JADX INFO: renamed from: getSimpleName-t-3xSXQ, reason: not valid java name */
    public static final String m600getSimpleNamet3xSXQ(String str) {
        return AbiSimpleName.m615constructorimpl(StringsKt.substringAfterLast$default(str, SEPARATOR, (String) null, 2, (Object) null));
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m601hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* JADX INFO: renamed from: isContainerOf-6OaPNJE, reason: not valid java name */
    public static final boolean m602isContainerOf6OaPNJE(String str, String str2) {
        str2.getClass();
        int length = str.length();
        if (length == 0) {
            return true;
        }
        return str2.length() > length + 1 && StringsKt.startsWith$default(str2, str, false, 2, (Object) null) && str2.charAt(length) == '.';
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m603toStringimpl(String str) {
        return str;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(AbiCompoundName abiCompoundName) {
        return m604compareTo6OaPNJE(abiCompoundName.m605unboximpl());
    }

    /* JADX INFO: renamed from: compareTo-6OaPNJE, reason: not valid java name */
    public int m604compareTo6OaPNJE(String str) {
        str.getClass();
        return m594compareTo6OaPNJE(this.value, str);
    }

    public boolean equals(Object obj) {
        return m596equalsimpl(this.value, obj);
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return m601hashCodeimpl(this.value);
    }

    public String toString() {
        return m603toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m605unboximpl() {
        return this.value;
    }

    /* JADX INFO: renamed from: compareTo-6OaPNJE, reason: not valid java name */
    public static int m594compareTo6OaPNJE(String str, String str2) {
        str2.getClass();
        return str.compareTo(str2);
    }
}
