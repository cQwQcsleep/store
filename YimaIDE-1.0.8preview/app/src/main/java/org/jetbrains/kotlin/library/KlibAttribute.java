package org.jetbrains.kotlin.library;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002\u0013\u0014B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J$\u0010\b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0086\n¢\u0006\u0002\u0010\rJ,\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\b\u0010\u0010\u001a\u0004\u0018\u00018\u0000H\u0086\n¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/library/KlibAttribute;", "T", "", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getValue", "thisRef", "Lorg/jetbrains/kotlin/library/Klib;", "property", "Lkotlin/reflect/KProperty;", "(Lorg/jetbrains/kotlin/library/Klib;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Lorg/jetbrains/kotlin/library/Klib;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "toString", "Delegate", "Flag", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KlibAttribute<T> {
    private final String name;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0002B\u0007\b\u0000¢\u0006\u0002\u0010\u0003J%\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0086\u0002¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/library/KlibAttribute$Delegate;", "T", "", "()V", "provideDelegate", "Lorg/jetbrains/kotlin/library/KlibAttribute;", "thisRef", "property", "Lkotlin/reflect/KProperty;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Delegate<T> {
        public final KlibAttribute<T> provideDelegate(Object thisRef, KProperty<?> property) {
            property.getClass();
            return new KlibAttribute<>(property.getName(), null);
        }
    }

    private KlibAttribute(String str) {
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }

    public final T getValue(Klib thisRef, KProperty<?> property) {
        thisRef.getClass();
        property.getClass();
        return (T) thisRef.getAttributes().get(this);
    }

    public final void setValue(Klib thisRef, KProperty<?> property, T value) {
        thisRef.getClass();
        property.getClass();
        thisRef.getAttributes().set(this, value);
    }

    public String toString() {
        return this.name;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0010B\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u001d\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0086\nJ%\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u000f\u001a\u00020\u0004H\u0086\nR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/library/KlibAttribute$Flag;", "", "wrappedAttribute", "Lorg/jetbrains/kotlin/library/KlibAttribute;", "", "(Lorg/jetbrains/kotlin/library/KlibAttribute;)V", "getWrappedAttribute", "()Lorg/jetbrains/kotlin/library/KlibAttribute;", "getValue", "thisRef", "Lorg/jetbrains/kotlin/library/Klib;", "property", "Lkotlin/reflect/KProperty;", "setValue", "", "value", "Delegate", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Flag {
        private final KlibAttribute<Boolean> wrappedAttribute;

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0086\u0002¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/library/KlibAttribute$Flag$Delegate;", "", "()V", "provideDelegate", "Lorg/jetbrains/kotlin/library/KlibAttribute$Flag;", "thisRef", "property", "Lkotlin/reflect/KProperty;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Delegate {
            public final Flag provideDelegate(Object thisRef, KProperty<?> property) {
                property.getClass();
                DefaultConstructorMarker defaultConstructorMarker = null;
                return new Flag(new KlibAttribute(property.getName(), defaultConstructorMarker), defaultConstructorMarker);
            }
        }

        private Flag(KlibAttribute<Boolean> klibAttribute) {
            this.wrappedAttribute = klibAttribute;
        }

        public final boolean getValue(Klib thisRef, KProperty<?> property) {
            thisRef.getClass();
            property.getClass();
            return Intrinsics.areEqual(thisRef.getAttributes().get(getWrappedAttribute()), Boolean.TRUE);
        }

        public final KlibAttribute<Boolean> getWrappedAttribute() {
            return this.wrappedAttribute;
        }

        public final void setValue(Klib thisRef, KProperty<?> property, boolean value) {
            thisRef.getClass();
            property.getClass();
            thisRef.getAttributes().set(getWrappedAttribute(), value ? Boolean.TRUE : null);
        }

        public /* synthetic */ Flag(KlibAttribute klibAttribute, DefaultConstructorMarker defaultConstructorMarker) {
            this(klibAttribute);
        }
    }

    public /* synthetic */ KlibAttribute(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
