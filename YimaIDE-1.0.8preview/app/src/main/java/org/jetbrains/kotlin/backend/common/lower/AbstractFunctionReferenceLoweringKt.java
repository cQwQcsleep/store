package org.jetbrains.kotlin.backend.common.lower;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"?\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00042\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<set-?>", "", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "declarationsAtFunctionReferenceLowering", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getDeclarationsAtFunctionReferenceLowering", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/List;", "setDeclarationsAtFunctionReferenceLowering", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Ljava/util/List;)V", "declarationsAtFunctionReferenceLowering$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class AbstractFunctionReferenceLoweringKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute declarationsAtFunctionReferenceLowering$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(AbstractFunctionReferenceLoweringKt.class, "declarationsAtFunctionReferenceLowering", "getDeclarationsAtFunctionReferenceLowering(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/List;", 1)};
        $$delegatedProperties = kPropertyArr;
        declarationsAtFunctionReferenceLowering$delegate = IrAttributeKt.irAttribute(true).provideDelegate((Object) null, kPropertyArr[0]);
    }

    public static final List<IrDeclaration> getDeclarationsAtFunctionReferenceLowering(IrClass irClass) {
        irClass.getClass();
        return (List) IrAttributeKt.get(irClass, declarationsAtFunctionReferenceLowering$delegate);
    }

    public static final void setDeclarationsAtFunctionReferenceLowering(IrClass irClass, List<? extends IrDeclaration> list) {
        irClass.getClass();
        IrAttributeKt.set(irClass, declarationsAtFunctionReferenceLowering$delegate, list);
    }
}
