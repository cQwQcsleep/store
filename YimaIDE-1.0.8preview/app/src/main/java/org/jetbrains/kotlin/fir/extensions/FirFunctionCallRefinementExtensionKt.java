package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.util.ArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"+\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038FX\u0086\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"3\u0010\f\u001a\u0004\u0018\u00010\u000b*\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000b8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"callRefinementExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionCallRefinementExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "getCallRefinementExtensions$annotations", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)V", "getCallRefinementExtensions", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", "callRefinementExtensions$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "<set-?>", "Lorg/jetbrains/kotlin/fir/extensions/OriginalCallData;", "originalCallDataForPluginRefinedCall", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getOriginalCallDataForPluginRefinedCall", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lorg/jetbrains/kotlin/fir/extensions/OriginalCallData;", "setOriginalCallDataForPluginRefinedCall", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/fir/extensions/OriginalCallData;)V", "originalCallDataForPluginRefinedCall$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFunctionCallRefinementExtensionKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirFunctionCallRefinementExtensionKt.class, "callRefinementExtensions", "getCallRefinementExtensions(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", 1), new MutablePropertyReference1Impl<>(FirFunctionCallRefinementExtensionKt.class, "originalCallDataForPluginRefinedCall", "getOriginalCallDataForPluginRefinedCall(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lorg/jetbrains/kotlin/fir/extensions/OriginalCallData;", 1)};
    private static final ArrayMapAccessor callRefinementExtensions$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor originalCallDataForPluginRefinedCall$delegate;

    static {
        FirExtensionService.Companion companion = FirExtensionService.INSTANCE;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FirFunctionCallRefinementExtension.class);
        List listEmptyList = CollectionsKt.emptyList();
        listEmptyList.getClass();
        callRefinementExtensions$delegate = companion.generateAccessor(orCreateKotlinClass, listEmptyList);
        originalCallDataForPluginRefinedCall$delegate = FirDeclarationDataRegistry.INSTANCE.data(OriginalCallDataKey.INSTANCE);
    }

    public static final List<FirFunctionCallRefinementExtension> getCallRefinementExtensions(FirExtensionService firExtensionService) {
        firExtensionService.getClass();
        return (List) callRefinementExtensions$delegate.getValue(firExtensionService, $$delegatedProperties[0]);
    }

    public static /* synthetic */ void getCallRefinementExtensions$annotations(FirExtensionService firExtensionService) {
    }

    public static final OriginalCallData getOriginalCallDataForPluginRefinedCall(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (OriginalCallData) originalCallDataForPluginRefinedCall$delegate.getValue(firDeclaration, $$delegatedProperties[1]);
    }

    public static final void setOriginalCallDataForPluginRefinedCall(FirDeclaration firDeclaration, OriginalCallData originalCallData) {
        firDeclaration.getClass();
        originalCallDataForPluginRefinedCall$delegate.setValue(firDeclaration, $$delegatedProperties[1], originalCallData);
    }
}
