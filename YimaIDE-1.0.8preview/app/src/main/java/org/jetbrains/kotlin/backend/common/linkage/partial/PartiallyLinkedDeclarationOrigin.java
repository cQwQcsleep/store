package org.jetbrains.kotlin.backend.common.linkage.partial;

import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\"\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000e\u0010\u0007R\u001b\u0010\u0010\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0011\u0010\u0007R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartiallyLinkedDeclarationOrigin;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "MISSING_DECLARATION", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getMISSING_DECLARATION", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "MISSING_DECLARATION$delegate", "Lkotlin/Lazy;", "UNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER", "getUNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER", "UNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER$delegate", "AMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER", "getAMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER", "AMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER$delegate", "AUXILIARY_GENERATED_DECLARATION", "getAUXILIARY_GENERATED_DECLARATION", "AUXILIARY_GENERATED_DECLARATION$delegate", "entries", "", "getEntries", "()Ljava/util/Set;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PartiallyLinkedDeclarationOrigin {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

    /* JADX INFO: renamed from: AMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER$delegate, reason: from kotlin metadata */
    private static final Lazy AMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER;

    /* JADX INFO: renamed from: AUXILIARY_GENERATED_DECLARATION$delegate, reason: from kotlin metadata */
    private static final Lazy AUXILIARY_GENERATED_DECLARATION;
    public static final PartiallyLinkedDeclarationOrigin INSTANCE;

    /* JADX INFO: renamed from: MISSING_DECLARATION$delegate, reason: from kotlin metadata */
    private static final Lazy MISSING_DECLARATION;

    /* JADX INFO: renamed from: UNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER$delegate, reason: from kotlin metadata */
    private static final Lazy UNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER;
    private static final Set<IrDeclarationOrigin> entries;

    static {
        KProperty<Object>[] kPropertyArr = {new PropertyReference1Impl<>(PartiallyLinkedDeclarationOrigin.class, "MISSING_DECLARATION", "getMISSING_DECLARATION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(PartiallyLinkedDeclarationOrigin.class, "UNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER", "getUNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(PartiallyLinkedDeclarationOrigin.class, "AMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER", "getAMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(PartiallyLinkedDeclarationOrigin.class, "AUXILIARY_GENERATED_DECLARATION", "getAUXILIARY_GENERATED_DECLARATION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0)};
        $$delegatedProperties = kPropertyArr;
        PartiallyLinkedDeclarationOrigin partiallyLinkedDeclarationOrigin = new PartiallyLinkedDeclarationOrigin();
        INSTANCE = partiallyLinkedDeclarationOrigin;
        IrDeclarationOriginImpl.Regular regular = IrDeclarationOriginImpl.Regular.INSTANCE;
        MISSING_DECLARATION = regular.provideDelegate(partiallyLinkedDeclarationOrigin, kPropertyArr[0]);
        UNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER = regular.provideDelegate(partiallyLinkedDeclarationOrigin, kPropertyArr[1]);
        AMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER = regular.provideDelegate(partiallyLinkedDeclarationOrigin, kPropertyArr[2]);
        AUXILIARY_GENERATED_DECLARATION = regular.provideDelegate(partiallyLinkedDeclarationOrigin, kPropertyArr[3]);
        entries = SetsKt.setOf(new IrDeclarationOrigin[]{partiallyLinkedDeclarationOrigin.getMISSING_DECLARATION(), partiallyLinkedDeclarationOrigin.getUNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER(), partiallyLinkedDeclarationOrigin.getAMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER(), partiallyLinkedDeclarationOrigin.getAUXILIARY_GENERATED_DECLARATION()});
    }

    private PartiallyLinkedDeclarationOrigin() {
    }

    public final IrDeclarationOrigin getAMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER() {
        return (IrDeclarationOrigin) AMBIGUOUS_NON_OVERRIDDEN_CALLABLE_MEMBER.getValue();
    }

    public final IrDeclarationOrigin getAUXILIARY_GENERATED_DECLARATION() {
        return (IrDeclarationOrigin) AUXILIARY_GENERATED_DECLARATION.getValue();
    }

    public final Set<IrDeclarationOrigin> getEntries() {
        return entries;
    }

    public final IrDeclarationOrigin getMISSING_DECLARATION() {
        return (IrDeclarationOrigin) MISSING_DECLARATION.getValue();
    }

    public final IrDeclarationOrigin getUNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER() {
        return (IrDeclarationOrigin) UNIMPLEMENTED_ABSTRACT_CALLABLE_MEMBER.getValue();
    }
}
