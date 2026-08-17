package kotlin.reflect.jvm.internal.types;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.KTypeParameterOwnerImpl;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0082\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\bH\u0096\u0080\u0004J\"\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0097\u0081\u0004b\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018R\u0015\u0010\u0007\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u000b\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0019\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001f\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020 0\u001fX\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b!\u0010\"R#\u0010#\u001a\u00020\u000e8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\b#\u0010$R#\u0010%\u001a\u00020\u000e8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\b%\u0010$R#\u0010&\u001a\u00020\u000e8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\b&\u0010$R#\u0010'\u001a\u00020\u000e8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\b'\u0010$R#\u0010(\u001a\u00020\u000e8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b()¢\u0006\u0006\u001a\u0004\b(\u0010$R#\u0010*\u001a\u00020\u000e8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\b*\u0010$R#\u0010+\u001a\u00020\u000e8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\b+\u0010$R#\u0010,\u001a\u00020\u000e8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\b,\u0010$R#\u0010-\u001a\u00020\u000e8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(.¢\u0006\u0006\u001a\u0004\b-\u0010$R\u001d\u0010/\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003000\u001fX\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b1\u0010\"R\u001d\u00102\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u001fX\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b3\u0010\"R\u0015\u00104\u001a\u0004\u0018\u00010\u0002X\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b5\u00106R1\u00107\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\u001a8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(9¢\u0006\u0006\u001a\u0004\b8\u0010\u001dR)\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u001a8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\b<\u0010\u001dR)\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u001a8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\b?\u0010\u001dR%\u0010@\u001a\u0004\u0018\u00010A8\u0016X\u0097\u0085\br\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018¢\u0006\u0006\u001a\u0004\bB\u0010C¨\u0006D"}, d2 = {"Lkotlin/reflect/jvm/internal/types/NothingKClass;", "Lkotlin/reflect/KClass;", "Ljava/lang/Void;", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeConstructorMarker;", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "<init>", "()V", "simpleName", HttpUrl.FRAGMENT_ENCODE_SET, "getSimpleName", "()Ljava/lang/String;", "qualifiedName", "getQualifiedName", "equals", HttpUrl.FRAGMENT_ENCODE_SET, "other", HttpUrl.FRAGMENT_ENCODE_SET, "hashCode", HttpUrl.FRAGMENT_ENCODE_SET, "toString", "isInstance", "value", "Lkotlin/SinceKotlin;", "version", "1.1", "annotations", HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, "getAnnotations", "()Ljava/util/List;", "constructors", HttpUrl.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "()Z", "isCompanion", "isData", "isFinal", "isFun", "1.4", "isInner", "isOpen", "isSealed", "isValue", "1.5", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Void;", "sealedSubclasses", "getSealedSubclasses", "1.3", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "kotlin-reflection"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NothingKClass implements KClass<Void>, KTypeParameterOwnerImpl, TypeConstructorMarker {
    public static final NothingKClass INSTANCE = new NothingKClass();
    private final /* synthetic */ KClass<Void> $$delegate_0 = Reflection.getOrCreateKotlinClass(Void.class);

    private NothingKClass() {
    }

    public boolean equals(Object other) {
        return this == other;
    }

    public List<Annotation> getAnnotations() {
        return this.$$delegate_0.getAnnotations();
    }

    public Collection<KFunction<Void>> getConstructors() {
        return this.$$delegate_0.getConstructors();
    }

    public Collection<KCallable<?>> getMembers() {
        return this.$$delegate_0.getMembers();
    }

    public Collection<KClass<?>> getNestedClasses() {
        return this.$$delegate_0.getNestedClasses();
    }

    public Void getObjectInstance() {
        return (Void) this.$$delegate_0.getObjectInstance();
    }

    public String getQualifiedName() {
        return "kotlin.Nothing";
    }

    public List<KClass<? extends Void>> getSealedSubclasses() {
        return this.$$delegate_0.getSealedSubclasses();
    }

    public String getSimpleName() {
        return "Nothing";
    }

    public List<KType> getSupertypes() {
        return this.$$delegate_0.getSupertypes();
    }

    @Override // kotlin.reflect.jvm.internal.KTypeParameterOwnerImpl
    public List<KTypeParameter> getTypeParameters() {
        return this.$$delegate_0.getTypeParameters();
    }

    public KVisibility getVisibility() {
        return this.$$delegate_0.getVisibility();
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public boolean isAbstract() {
        return this.$$delegate_0.isAbstract();
    }

    public boolean isCompanion() {
        return this.$$delegate_0.isCompanion();
    }

    public boolean isData() {
        return this.$$delegate_0.isData();
    }

    public boolean isFinal() {
        return this.$$delegate_0.isFinal();
    }

    public boolean isFun() {
        return this.$$delegate_0.isFun();
    }

    public boolean isInner() {
        return this.$$delegate_0.isInner();
    }

    public boolean isInstance(Object value) {
        return this.$$delegate_0.isInstance(value);
    }

    public boolean isOpen() {
        return this.$$delegate_0.isOpen();
    }

    public boolean isSealed() {
        return this.$$delegate_0.isSealed();
    }

    public boolean isValue() {
        return this.$$delegate_0.isValue();
    }

    public String toString() {
        return "NothingKClass";
    }
}
