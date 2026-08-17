package org.jetbrains.kotlin.resolve;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/UseSiteTargetsList;", "", "<init>", "()V", "T_CONSTRUCTOR_PARAMETER", "", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "getT_CONSTRUCTOR_PARAMETER", "()Ljava/util/List;", "T_PROPERTY", "getT_PROPERTY", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class UseSiteTargetsList {
    public static final UseSiteTargetsList INSTANCE = new UseSiteTargetsList();
    private static final List<AnnotationUseSiteTarget> T_CONSTRUCTOR_PARAMETER;
    private static final List<AnnotationUseSiteTarget> T_PROPERTY;

    static {
        AnnotationUseSiteTarget annotationUseSiteTarget = AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER;
        AnnotationUseSiteTarget annotationUseSiteTarget2 = AnnotationUseSiteTarget.PROPERTY;
        AnnotationUseSiteTarget annotationUseSiteTarget3 = AnnotationUseSiteTarget.FIELD;
        T_CONSTRUCTOR_PARAMETER = CollectionsKt.listOf(new AnnotationUseSiteTarget[]{annotationUseSiteTarget, annotationUseSiteTarget2, annotationUseSiteTarget3});
        T_PROPERTY = CollectionsKt.listOf(new AnnotationUseSiteTarget[]{annotationUseSiteTarget2, annotationUseSiteTarget3});
    }

    private UseSiteTargetsList() {
    }

    public final List<AnnotationUseSiteTarget> getT_CONSTRUCTOR_PARAMETER() {
        return T_CONSTRUCTOR_PARAMETER;
    }

    public final List<AnnotationUseSiteTarget> getT_PROPERTY() {
        return T_PROPERTY;
    }
}
