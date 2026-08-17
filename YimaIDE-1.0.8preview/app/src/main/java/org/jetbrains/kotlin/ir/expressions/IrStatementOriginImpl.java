package org.jetbrains.kotlin.ir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.PropertyDelegateProvider;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u0014B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\n\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u0005H\u0096\u0080\u0004J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0096\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "Lkotlin/properties/ReadOnlyProperty;", "", "debugName", "", "<init>", "(Ljava/lang/String;)V", "getDebugName", "()Ljava/lang/String;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "toString", "equals", "", "other", "hashCode", "", "Companion", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrStatementOriginImpl implements ReadOnlyProperty<Object, IrStatementOriginImpl>, IrStatementOrigin {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String debugName;

    public IrStatementOriginImpl(String str) {
        str.getClass();
        this.debugName = str;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof IrStatementOriginImpl) && Intrinsics.areEqual(getDebugName(), ((IrStatementOriginImpl) other).getDebugName());
    }

    @Override // org.jetbrains.kotlin.ir.expressions.IrStatementOrigin
    public String getDebugName() {
        return this.debugName;
    }

    public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
        return m388getValue(obj, (KProperty<?>) kProperty);
    }

    public int hashCode() {
        return getDebugName().hashCode();
    }

    public String toString() {
        return getDebugName();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J.\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u00022\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0096\u0082\u0004¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl$Companion;", "Lkotlin/properties/PropertyDelegateProvider;", "", "Lkotlin/properties/ReadOnlyProperty;", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "<init>", "()V", "provideDelegate", "thisRef", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion implements PropertyDelegateProvider<Object, ReadOnlyProperty<? super Object, ? extends IrStatementOriginImpl>> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: provideDelegate, reason: collision with other method in class */
        public ReadOnlyProperty<Object, IrStatementOriginImpl> m389provideDelegate(Object thisRef, KProperty<?> property) {
            property.getClass();
            return new IrStatementOriginImpl(property.getName());
        }

        private Companion() {
        }

        public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
            return m389provideDelegate(obj, (KProperty<?>) kProperty);
        }
    }

    /* JADX INFO: renamed from: getValue, reason: collision with other method in class */
    public IrStatementOriginImpl m388getValue(Object thisRef, KProperty<?> property) {
        property.getClass();
        return this;
    }
}
