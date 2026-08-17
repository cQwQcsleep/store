package org.jetbrains.kotlin.ir.backend.js.tsexport;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u0011\u001a\u00020\u0003HÖ\u0081\u0004¢\u0006\u0004\b\u0012\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002Ê\u0001\u0002\b\u0014¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/TypeScriptFragment;", "", "raw", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getRaw", "()Ljava/lang/String;", "equals", "", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "org.jetbrains.kotlin:typescript-printer", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@JvmInline
public final class TypeScriptFragment {
    private final String raw;

    private /* synthetic */ TypeScriptFragment(String str) {
        this.raw = str;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TypeScriptFragment m2183boximpl(String str) {
        return new TypeScriptFragment(str);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static String m2184constructorimpl(String str) {
        str.getClass();
        return str;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2185equalsimpl(String str, Object obj) {
        return (obj instanceof TypeScriptFragment) && Intrinsics.areEqual(str, ((TypeScriptFragment) obj).m2189unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2186equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual(str, str2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2187hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2188toStringimpl(String str) {
        return "TypeScriptFragment(raw=" + str + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m2185equalsimpl(this.raw, obj);
    }

    public final String getRaw() {
        return this.raw;
    }

    public int hashCode() {
        return m2187hashCodeimpl(this.raw);
    }

    public String toString() {
        return m2188toStringimpl(this.raw);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m2189unboximpl() {
        return this.raw;
    }
}
