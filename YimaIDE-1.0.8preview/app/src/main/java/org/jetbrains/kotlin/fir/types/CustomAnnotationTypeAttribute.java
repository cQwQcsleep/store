package org.jetbrains.kotlin.fir.types;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.renderer.FirRenderer;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\t\u001a\u0004\u0018\u00010\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0000H\u0016J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\f\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\b\u0010\u0011\u001a\u00020\u0010H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CustomAnnotationTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "annotations", "", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "(Ljava/util/List;)V", "getAnnotations", "()Ljava/util/List;", "union", "other", "intersect", "add", "isSubtypeOf", "", "toString", "", "renderForReadability", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CustomAnnotationTypeAttribute extends ConeAttribute<CustomAnnotationTypeAttribute> {
    private final List<FirAnnotation> annotations;

    public CustomAnnotationTypeAttribute(List<? extends FirAnnotation> list) {
        list.getClass();
        this.annotations = list;
    }

    public static CharSequence a(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        return FirRenderer.Companion.forReadability().renderElementAsString(firAnnotation, true);
    }

    public static CharSequence b(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        return UtilsKt.render(firAnnotation);
    }

    public CustomAnnotationTypeAttribute add(CustomAnnotationTypeAttribute other) {
        return (other == null || other == this) ? this : new CustomAnnotationTypeAttribute(CollectionsKt.plus(this.annotations, other.annotations));
    }

    public final List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public boolean getKeepInInferredDeclarationType() {
        return true;
    }

    public KClass<? extends CustomAnnotationTypeAttribute> getKey() {
        return Reflection.getOrCreateKotlinClass(CustomAnnotationTypeAttribute.class);
    }

    public String renderForReadability() {
        return CollectionsKt.joinToString$default(this.annotations, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: j33
            public final Object invoke(Object obj) {
                return CustomAnnotationTypeAttribute.a((FirAnnotation) obj);
            }
        }, 30, (Object) null);
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.annotations, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: i33
            public final Object invoke(Object obj) {
                return CustomAnnotationTypeAttribute.b((FirAnnotation) obj);
            }
        }, 30, (Object) null);
    }

    public CustomAnnotationTypeAttribute intersect(CustomAnnotationTypeAttribute other) {
        return null;
    }

    public boolean isSubtypeOf(CustomAnnotationTypeAttribute other) {
        return true;
    }

    public CustomAnnotationTypeAttribute union(CustomAnnotationTypeAttribute other) {
        return null;
    }
}
