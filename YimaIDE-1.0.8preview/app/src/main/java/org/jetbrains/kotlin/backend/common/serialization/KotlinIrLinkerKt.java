package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.library.KotlinLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "kotlinLibrary", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "getKotlinLibrary", "(Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;)Lorg/jetbrains/kotlin/library/KotlinLibrary;", "setKotlinLibrary", "(Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;Lorg/jetbrains/kotlin/library/KotlinLibrary;)V", "kotlinLibrary$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class KotlinIrLinkerKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute kotlinLibrary$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(KotlinIrLinkerKt.class, "kotlinLibrary", "getKotlinLibrary(Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;)Lorg/jetbrains/kotlin/library/KotlinLibrary;", 1)};
        $$delegatedProperties = kPropertyArr;
        kotlinLibrary$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[0]);
    }

    public static final KotlinLibrary getKotlinLibrary(IrModuleFragment irModuleFragment) {
        irModuleFragment.getClass();
        return (KotlinLibrary) IrAttributeKt.get(irModuleFragment, kotlinLibrary$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setKotlinLibrary(IrModuleFragment irModuleFragment, KotlinLibrary kotlinLibrary) {
        IrAttributeKt.set(irModuleFragment, kotlinLibrary$delegate, kotlinLibrary);
    }
}
