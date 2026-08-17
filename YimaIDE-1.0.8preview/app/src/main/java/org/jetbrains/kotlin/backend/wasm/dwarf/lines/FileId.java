package org.jetbrains.kotlin.backend.wasm.dwarf.lines;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import org.eclipse.jdt.internal.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004¢\u0006\u0004\b\u000e\u0010\u0005J\u0011\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004¢\u0006\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002Ê\u0001\u0002\b\u0014¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/dwarf/lines/FileId;", "", "index", "", "constructor-impl", "(I)I", "getIndex", "()I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "org.jetbrains.kotlin:backend.wasm", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@JvmInline
public final class FileId {
    private final int index;

    private /* synthetic */ FileId(int i) {
        this.index = i;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FileId m2102boximpl(int i) {
        return new FileId(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2103constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2104equalsimpl(int i, Object obj) {
        return (obj instanceof FileId) && i == ((FileId) obj).m2108unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2105equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2106hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2107toStringimpl(int i) {
        return "FileId(index=" + i + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m2104equalsimpl(this.index, obj);
    }

    public final int getIndex() {
        return this.index;
    }

    public int hashCode() {
        return m2106hashCodeimpl(this.index);
    }

    public String toString() {
        return m2107toStringimpl(this.index);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2108unboximpl() {
        return this.index;
    }
}
