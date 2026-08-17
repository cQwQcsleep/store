package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.util.ArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001aA\u0010\b\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0019\b\u0002\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012H\u0086\bø\u0001\u0000\"%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"statusTransformerExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirStatusTransformerExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "getStatusTransformerExtensions", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", "statusTransformerExtensions$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "transform", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirStatusTransformerExtensionKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirStatusTransformerExtensionKt.class, "statusTransformerExtensions", "getStatusTransformerExtensions(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", 1)};
    private static final ArrayMapAccessor statusTransformerExtensions$delegate;

    static {
        FirExtensionService.Companion companion = FirExtensionService.INSTANCE;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FirStatusTransformerExtension.class);
        List listEmptyList = CollectionsKt.emptyList();
        listEmptyList.getClass();
        statusTransformerExtensions$delegate = companion.generateAccessor(orCreateKotlinClass, listEmptyList);
    }

    public static final List<FirStatusTransformerExtension> getStatusTransformerExtensions(FirExtensionService firExtensionService) {
        firExtensionService.getClass();
        return (List) statusTransformerExtensions$delegate.getValue(firExtensionService, $$delegatedProperties[0]);
    }

    public static final FirDeclarationStatus transform(FirDeclarationStatus firDeclarationStatus, Visibility visibility, Modality modality, Function1<? super FirDeclarationStatusImpl, Unit> function1) {
        firDeclarationStatus.getClass();
        visibility.getClass();
        function1.getClass();
        FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility, modality);
        firDeclarationStatusImpl.setExpect(firDeclarationStatus.isExpect());
        firDeclarationStatusImpl.setActual(firDeclarationStatus.isActual());
        firDeclarationStatusImpl.setOverride(firDeclarationStatus.isOverride());
        firDeclarationStatusImpl.setOperator(firDeclarationStatus.isOperator());
        firDeclarationStatusImpl.setInfix(firDeclarationStatus.isInfix());
        firDeclarationStatusImpl.setInline(firDeclarationStatus.isInline());
        firDeclarationStatusImpl.setValue(firDeclarationStatus.isValue());
        firDeclarationStatusImpl.setTailRec(firDeclarationStatus.isTailRec());
        firDeclarationStatusImpl.setExternal(firDeclarationStatus.isExternal());
        firDeclarationStatusImpl.setConst(firDeclarationStatus.isConst());
        firDeclarationStatusImpl.setLateInit(firDeclarationStatus.isLateInit());
        firDeclarationStatusImpl.setInner(firDeclarationStatus.isInner());
        firDeclarationStatusImpl.setCompanion(firDeclarationStatus.isCompanion());
        firDeclarationStatusImpl.setData(firDeclarationStatus.isData());
        firDeclarationStatusImpl.setSuspend(firDeclarationStatus.isSuspend());
        firDeclarationStatusImpl.setStatic(firDeclarationStatus.isStatic());
        firDeclarationStatusImpl.setFromSealedClass(firDeclarationStatus.isFromSealedClass());
        firDeclarationStatusImpl.setFromEnumClass(firDeclarationStatus.isFromEnumClass());
        function1.invoke(firDeclarationStatusImpl);
        return firDeclarationStatusImpl;
    }

    public static /* synthetic */ FirDeclarationStatus transform$default(FirDeclarationStatus firDeclarationStatus, Visibility visibility, Modality modality, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            visibility = firDeclarationStatus.getVisibility();
        }
        if ((i & 2) != 0) {
            modality = firDeclarationStatus.getModality();
        }
        if ((i & 4) != 0) {
            function1 = new Function1<FirDeclarationStatusImpl, Unit>() { // from class: org.jetbrains.kotlin.fir.extensions.FirStatusTransformerExtensionKt.transform.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirDeclarationStatusImpl) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(FirDeclarationStatusImpl firDeclarationStatusImpl) {
                    firDeclarationStatusImpl.getClass();
                }
            };
        }
        firDeclarationStatus.getClass();
        visibility.getClass();
        function1.getClass();
        FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility, modality);
        firDeclarationStatusImpl.setExpect(firDeclarationStatus.isExpect());
        firDeclarationStatusImpl.setActual(firDeclarationStatus.isActual());
        firDeclarationStatusImpl.setOverride(firDeclarationStatus.isOverride());
        firDeclarationStatusImpl.setOperator(firDeclarationStatus.isOperator());
        firDeclarationStatusImpl.setInfix(firDeclarationStatus.isInfix());
        firDeclarationStatusImpl.setInline(firDeclarationStatus.isInline());
        firDeclarationStatusImpl.setValue(firDeclarationStatus.isValue());
        firDeclarationStatusImpl.setTailRec(firDeclarationStatus.isTailRec());
        firDeclarationStatusImpl.setExternal(firDeclarationStatus.isExternal());
        firDeclarationStatusImpl.setConst(firDeclarationStatus.isConst());
        firDeclarationStatusImpl.setLateInit(firDeclarationStatus.isLateInit());
        firDeclarationStatusImpl.setInner(firDeclarationStatus.isInner());
        firDeclarationStatusImpl.setCompanion(firDeclarationStatus.isCompanion());
        firDeclarationStatusImpl.setData(firDeclarationStatus.isData());
        firDeclarationStatusImpl.setSuspend(firDeclarationStatus.isSuspend());
        firDeclarationStatusImpl.setStatic(firDeclarationStatus.isStatic());
        firDeclarationStatusImpl.setFromSealedClass(firDeclarationStatus.isFromSealedClass());
        firDeclarationStatusImpl.setFromEnumClass(firDeclarationStatus.isFromEnumClass());
        function1.invoke(firDeclarationStatusImpl);
        return firDeclarationStatusImpl;
    }
}
