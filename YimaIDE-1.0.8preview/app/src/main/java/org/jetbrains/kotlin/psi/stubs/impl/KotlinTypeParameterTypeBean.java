package org.jetbrains.kotlin.psi.stubs.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0004HÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinTypeParameterTypeBean;", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinTypeBean;", "Lorg/jetbrains/kotlin/types/model/SimpleTypeMarker;", "typeParameterName", "", "nullable", "", "definitelyNotNull", "<init>", "(Ljava/lang/String;ZZ)V", "getTypeParameterName", "()Ljava/lang/String;", "getNullable", "()Z", "getDefinitelyNotNull", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class KotlinTypeParameterTypeBean implements KotlinTypeBean, SimpleTypeMarker {
    private final boolean definitelyNotNull;
    private final boolean nullable;
    private final String typeParameterName;

    public KotlinTypeParameterTypeBean(String str, boolean z, boolean z2) {
        str.getClass();
        this.typeParameterName = str;
        this.nullable = z;
        this.definitelyNotNull = z2;
    }

    public static /* synthetic */ KotlinTypeParameterTypeBean copy$default(KotlinTypeParameterTypeBean kotlinTypeParameterTypeBean, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = kotlinTypeParameterTypeBean.typeParameterName;
        }
        if ((i & 2) != 0) {
            z = kotlinTypeParameterTypeBean.nullable;
        }
        if ((i & 4) != 0) {
            z2 = kotlinTypeParameterTypeBean.definitelyNotNull;
        }
        return kotlinTypeParameterTypeBean.copy(str, z, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTypeParameterName() {
        return this.typeParameterName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getNullable() {
        return this.nullable;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getDefinitelyNotNull() {
        return this.definitelyNotNull;
    }

    public final KotlinTypeParameterTypeBean copy(String typeParameterName, boolean nullable, boolean definitelyNotNull) {
        typeParameterName.getClass();
        return new KotlinTypeParameterTypeBean(typeParameterName, nullable, definitelyNotNull);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KotlinTypeParameterTypeBean)) {
            return false;
        }
        KotlinTypeParameterTypeBean kotlinTypeParameterTypeBean = (KotlinTypeParameterTypeBean) other;
        return Intrinsics.areEqual(this.typeParameterName, kotlinTypeParameterTypeBean.typeParameterName) && this.nullable == kotlinTypeParameterTypeBean.nullable && this.definitelyNotNull == kotlinTypeParameterTypeBean.definitelyNotNull;
    }

    public final boolean getDefinitelyNotNull() {
        return this.definitelyNotNull;
    }

    @Override // org.jetbrains.kotlin.psi.stubs.impl.KotlinTypeBean
    public boolean getNullable() {
        return this.nullable;
    }

    public final String getTypeParameterName() {
        return this.typeParameterName;
    }

    public int hashCode() {
        return (((this.typeParameterName.hashCode() * 31) + Boolean.hashCode(this.nullable)) * 31) + Boolean.hashCode(this.definitelyNotNull);
    }

    public String toString() {
        return "KotlinTypeParameterTypeBean(typeParameterName=" + this.typeParameterName + ", nullable=" + this.nullable + ", definitelyNotNull=" + this.definitelyNotNull + ')';
    }
}
