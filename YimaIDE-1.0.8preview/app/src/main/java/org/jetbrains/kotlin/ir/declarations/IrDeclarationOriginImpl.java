package org.jetbrains.kotlin.ir.declarations;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u000b\u001a\u00020\u0003H\u0096\u0080\u0004J\u0014\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0082\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOriginImpl;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "name", "", "isSynthetic", "", "<init>", "(Ljava/lang/String;Z)V", "getName", "()Ljava/lang/String;", "()Z", "toString", "equals", "other", "", "hashCode", "", "Regular", "Synthetic", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrDeclarationOriginImpl implements IrDeclarationOrigin {
    private final boolean isSynthetic;
    private final String name;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0086\u0002¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOriginImpl$Regular;", "", "<init>", "()V", "provideDelegate", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "thisRef", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Regular {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
        }

        public static IrDeclarationOriginImpl a(KProperty kProperty) {
            return new IrDeclarationOriginImpl(kProperty.getName(), false, 2, null);
        }

        public final Lazy<IrDeclarationOrigin> provideDelegate(Object thisRef, final KProperty<?> property) {
            property.getClass();
            return LazyKt.lazy(new Function0() { // from class: fz6
                public final Object invoke() {
                    return IrDeclarationOriginImpl.Regular.a(property);
                }
            });
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0086\u0002¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOriginImpl$Synthetic;", "", "<init>", "()V", "provideDelegate", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "thisRef", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Synthetic {
        public static final Synthetic INSTANCE = new Synthetic();

        private Synthetic() {
        }

        public static IrDeclarationOriginImpl a(KProperty kProperty) {
            return new IrDeclarationOriginImpl(kProperty.getName(), true);
        }

        public final Lazy<IrDeclarationOrigin> provideDelegate(Object thisRef, final KProperty<?> property) {
            property.getClass();
            return LazyKt.lazy(new Function0() { // from class: gz6
                public final Object invoke() {
                    return IrDeclarationOriginImpl.Synthetic.a(property);
                }
            });
        }
    }

    public IrDeclarationOriginImpl(String str, boolean z) {
        str.getClass();
        this.name = str;
        this.isSynthetic = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof IrDeclarationOriginImpl) && Intrinsics.areEqual(getName(), ((IrDeclarationOriginImpl) other).getName());
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    /* JADX INFO: renamed from: isSynthetic, reason: from getter */
    public boolean getIsSynthetic() {
        return this.isSynthetic;
    }

    public String toString() {
        return getName();
    }

    public /* synthetic */ IrDeclarationOriginImpl(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z);
    }
}
