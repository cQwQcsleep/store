package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"3\u0010\u000b\u001a\u0004\u0018\u00010\n*\u00020\f2\b\u0010\u0000\u001a\u0004\u0018\u00010\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\t\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/name/Name;", "inventedNameForLocalFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getInventedNameForLocalFunction", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/name/Name;", "setInventedNameForLocalFunction", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/name/Name;)V", "inventedNameForLocalFunction$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "Lorg/jetbrains/kotlin/backend/common/lower/ScopeWithCounter;", "scopeWithCounter", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "getScopeWithCounter", "(Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;)Lorg/jetbrains/kotlin/backend/common/lower/ScopeWithCounter;", "setScopeWithCounter", "(Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;Lorg/jetbrains/kotlin/backend/common/lower/ScopeWithCounter;)V", "scopeWithCounter$delegate", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class InventNamesForLocalFunctionsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute inventedNameForLocalFunction$delegate;
    private static final IrAttribute scopeWithCounter$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(InventNamesForLocalFunctionsKt.class, "inventedNameForLocalFunction", "getInventedNameForLocalFunction(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/name/Name;", 1), new MutablePropertyReference1Impl<>(InventNamesForLocalFunctionsKt.class, "scopeWithCounter", "getScopeWithCounter(Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;)Lorg/jetbrains/kotlin/backend/common/lower/ScopeWithCounter;", 1)};
        $$delegatedProperties = kPropertyArr;
        inventedNameForLocalFunction$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[0]);
        scopeWithCounter$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[1]);
    }

    public static final Name getInventedNameForLocalFunction(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        return (Name) IrAttributeKt.get(irSimpleFunction, inventedNameForLocalFunction$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ScopeWithCounter getScopeWithCounter(IrSymbolOwner irSymbolOwner) {
        return (ScopeWithCounter) IrAttributeKt.get((IrElement) irSymbolOwner, scopeWithCounter$delegate);
    }

    public static final void setInventedNameForLocalFunction(IrSimpleFunction irSimpleFunction, Name name) {
        irSimpleFunction.getClass();
        IrAttributeKt.set(irSimpleFunction, inventedNameForLocalFunction$delegate, name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setScopeWithCounter(IrSymbolOwner irSymbolOwner, ScopeWithCounter scopeWithCounter) {
        IrAttributeKt.set((IrElement) irSymbolOwner, scopeWithCounter$delegate, scopeWithCounter);
    }
}
