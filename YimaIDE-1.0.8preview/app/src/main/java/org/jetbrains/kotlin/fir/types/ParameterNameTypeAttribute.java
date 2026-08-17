package org.jetbrains.kotlin.fir.types;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.renderer.FirRenderer;
import org.jetbrains.kotlin.fir.types.ParameterNameTypeAttribute;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001#B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\r\u001a\u0004\u0018\u00010\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0000H\u0016J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u0010\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u001c\u001a\u00020\u001dH\u0096\u0080\u0004J\b\u0010\u001e\u001a\u00020\u001dH\u0016J\u0014\u0010\u001f\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010 H\u0096\u0082\u0004J\n\u0010!\u001a\u00020\"H\u0096\u0080\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0013\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0015¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ParameterNameTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "name", "Lorg/jetbrains/kotlin/name/Name;", "annotations", "", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Ljava/util/List;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getAnnotations", "()Ljava/util/List;", "union", "other", "intersect", "add", "isSubtypeOf", "", "implementsEquality", "getImplementsEquality", "()Z", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "toString", "", "renderForReadability", "equals", "", "hashCode", "", "Companion", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ParameterNameTypeAttribute extends ConeAttribute<ParameterNameTypeAttribute> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KClass<? extends ParameterNameTypeAttribute> KEY = Reflection.getOrCreateKotlinClass(ParameterNameTypeAttribute.class);
    private final List<FirAnnotation> annotations;
    private final Name name;

    public ParameterNameTypeAttribute(Name name, List<? extends FirAnnotation> list) {
        list.getClass();
        this.name = name;
        this.annotations = list;
        if (list.isEmpty()) {
            w01.a("Failed requirement.");
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence renderForReadability$lambda$0$0(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        return FirRenderer.Companion.forReadability().renderElementAsString(firAnnotation, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence toString$lambda$0$0(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        return UtilsKt.render(firAnnotation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ParameterNameTypeAttribute.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        ParameterNameTypeAttribute parameterNameTypeAttribute = (ParameterNameTypeAttribute) other;
        Name name = this.name;
        return name != null ? Intrinsics.areEqual(name, parameterNameTypeAttribute.name) : Intrinsics.areEqual(CollectionsKt.first(this.annotations), CollectionsKt.first(parameterNameTypeAttribute.annotations));
    }

    public final List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public boolean getImplementsEquality() {
        return true;
    }

    public boolean getKeepInInferredDeclarationType() {
        return true;
    }

    public KClass<? extends ParameterNameTypeAttribute> getKey() {
        return KEY;
    }

    public final Name getName() {
        return this.name;
    }

    public int hashCode() {
        Name name = this.name;
        return name != null ? name.hashCode() : ((FirAnnotation) CollectionsKt.first(this.annotations)).hashCode();
    }

    public String renderForReadability() {
        StringBuilder sb = new StringBuilder();
        CollectionsKt.joinTo$default(this.annotations, sb, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: bya
            public final Object invoke(Object obj) {
                return ParameterNameTypeAttribute.renderForReadability$lambda$0$0((FirAnnotation) obj);
            }
        }, 60, (Object) null);
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CollectionsKt.joinTo$default(this.annotations, sb, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: aya
            public final Object invoke(Object obj) {
                return ParameterNameTypeAttribute.toString$lambda$0$0((FirAnnotation) obj);
            }
        }, 60, (Object) null);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ParameterNameTypeAttribute$Companion;", "", "<init>", "()V", "KEY", "Lkotlin/reflect/KClass;", "Lorg/jetbrains/kotlin/fir/types/ParameterNameTypeAttribute;", "getKEY", "()Lkotlin/reflect/KClass;", "ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ClassId getANNOTATION_CLASS_ID() {
            return StandardNames.FqNames.parameterNameClassId;
        }

        public final KClass<? extends ParameterNameTypeAttribute> getKEY() {
            return ParameterNameTypeAttribute.KEY;
        }

        private Companion() {
        }
    }

    public ParameterNameTypeAttribute add(ParameterNameTypeAttribute other) {
        return this;
    }

    public ParameterNameTypeAttribute intersect(ParameterNameTypeAttribute other) {
        return null;
    }

    public boolean isSubtypeOf(ParameterNameTypeAttribute other) {
        return true;
    }

    public ParameterNameTypeAttribute union(ParameterNameTypeAttribute other) {
        return null;
    }
}
