package org.jetbrains.kotlin.psi.stubs.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.psi.KtProjectionKind;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinTypeArgumentBean;", "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "projectionKind", "Lorg/jetbrains/kotlin/psi/KtProjectionKind;", "type", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinTypeBean;", "<init>", "(Lorg/jetbrains/kotlin/psi/KtProjectionKind;Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinTypeBean;)V", "getProjectionKind", "()Lorg/jetbrains/kotlin/psi/KtProjectionKind;", "getType", "()Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinTypeBean;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class KotlinTypeArgumentBean implements TypeArgumentMarker {
    private final KtProjectionKind projectionKind;
    private final KotlinTypeBean type;

    public KotlinTypeArgumentBean(KtProjectionKind ktProjectionKind, KotlinTypeBean kotlinTypeBean) {
        ktProjectionKind.getClass();
        this.projectionKind = ktProjectionKind;
        this.type = kotlinTypeBean;
    }

    public static /* synthetic */ KotlinTypeArgumentBean copy$default(KotlinTypeArgumentBean kotlinTypeArgumentBean, KtProjectionKind ktProjectionKind, KotlinTypeBean kotlinTypeBean, int i, Object obj) {
        if ((i & 1) != 0) {
            ktProjectionKind = kotlinTypeArgumentBean.projectionKind;
        }
        if ((i & 2) != 0) {
            kotlinTypeBean = kotlinTypeArgumentBean.type;
        }
        return kotlinTypeArgumentBean.copy(ktProjectionKind, kotlinTypeBean);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final KtProjectionKind getProjectionKind() {
        return this.projectionKind;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final KotlinTypeBean getType() {
        return this.type;
    }

    public final KotlinTypeArgumentBean copy(KtProjectionKind projectionKind, KotlinTypeBean type) {
        projectionKind.getClass();
        return new KotlinTypeArgumentBean(projectionKind, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KotlinTypeArgumentBean)) {
            return false;
        }
        KotlinTypeArgumentBean kotlinTypeArgumentBean = (KotlinTypeArgumentBean) other;
        return this.projectionKind == kotlinTypeArgumentBean.projectionKind && Intrinsics.areEqual(this.type, kotlinTypeArgumentBean.type);
    }

    public final KtProjectionKind getProjectionKind() {
        return this.projectionKind;
    }

    public final KotlinTypeBean getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = this.projectionKind.hashCode() * 31;
        KotlinTypeBean kotlinTypeBean = this.type;
        return iHashCode + (kotlinTypeBean == null ? 0 : kotlinTypeBean.hashCode());
    }

    public String toString() {
        return "KotlinTypeArgumentBean(projectionKind=" + this.projectionKind + ", type=" + this.type + ')';
    }
}
