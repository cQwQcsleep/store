package org.jetbrains.kotlin.p000native.interop;

import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/native/interop/ObjCMethodInfo;", "", "selector", "", OutputKeys.ENCODING, "isStret", "", "directSymbol", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getSelector", "()Ljava/lang/String;", "getEncoding", "()Z", "getDirectSymbol", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:compiler.common.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class ObjCMethodInfo {
    private final String directSymbol;
    private final String encoding;
    private final boolean isStret;
    private final String selector;

    public ObjCMethodInfo(String str, String str2, boolean z, String str3) {
        str.getClass();
        str2.getClass();
        this.selector = str;
        this.encoding = str2;
        this.isStret = z;
        this.directSymbol = str3;
    }

    public static /* synthetic */ ObjCMethodInfo copy$default(ObjCMethodInfo objCMethodInfo, String str, String str2, boolean z, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = objCMethodInfo.selector;
        }
        if ((i & 2) != 0) {
            str2 = objCMethodInfo.encoding;
        }
        if ((i & 4) != 0) {
            z = objCMethodInfo.isStret;
        }
        if ((i & 8) != 0) {
            str3 = objCMethodInfo.directSymbol;
        }
        return objCMethodInfo.copy(str, str2, z, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSelector() {
        return this.selector;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEncoding() {
        return this.encoding;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsStret() {
        return this.isStret;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDirectSymbol() {
        return this.directSymbol;
    }

    public final ObjCMethodInfo copy(String selector, String encoding, boolean isStret, String directSymbol) {
        selector.getClass();
        encoding.getClass();
        return new ObjCMethodInfo(selector, encoding, isStret, directSymbol);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ObjCMethodInfo)) {
            return false;
        }
        ObjCMethodInfo objCMethodInfo = (ObjCMethodInfo) other;
        return Intrinsics.areEqual(this.selector, objCMethodInfo.selector) && Intrinsics.areEqual(this.encoding, objCMethodInfo.encoding) && this.isStret == objCMethodInfo.isStret && Intrinsics.areEqual(this.directSymbol, objCMethodInfo.directSymbol);
    }

    public final String getDirectSymbol() {
        return this.directSymbol;
    }

    public final String getEncoding() {
        return this.encoding;
    }

    public final String getSelector() {
        return this.selector;
    }

    public int hashCode() {
        int iHashCode = ((((this.selector.hashCode() * 31) + this.encoding.hashCode()) * 31) + Boolean.hashCode(this.isStret)) * 31;
        String str = this.directSymbol;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isStret() {
        return this.isStret;
    }

    public String toString() {
        return "ObjCMethodInfo(selector=" + this.selector + ", encoding=" + this.encoding + ", isStret=" + this.isStret + ", directSymbol=" + this.directSymbol + ')';
    }
}
