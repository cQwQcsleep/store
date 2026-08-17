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
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.util.ArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\"%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005\"3\u0010\n\u001a\u0004\u0018\u00010\t*\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\"\u0015\u0010\n\u001a\u00020\t*\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0013¨\u0006\u0014"}, d2 = {"expressionResolutionExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExpressionResolutionExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "getExpressionResolutionExtensions", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", "expressionResolutionExtensions$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "<set-?>", Argument.Delimiters.none, "captureValueInAnalyze", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "getCaptureValueInAnalyze", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;)Ljava/lang/Boolean;", "setCaptureValueInAnalyze", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;Ljava/lang/Boolean;)V", "captureValueInAnalyze$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;)Z", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpressionResolutionExtensionKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirExpressionResolutionExtensionKt.class, "expressionResolutionExtensions", "getExpressionResolutionExtensions(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", 1), new MutablePropertyReference1Impl<>(FirExpressionResolutionExtensionKt.class, "captureValueInAnalyze", "getCaptureValueInAnalyze(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;)Ljava/lang/Boolean;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor captureValueInAnalyze$delegate;
    private static final ArrayMapAccessor expressionResolutionExtensions$delegate;

    static {
        FirExtensionService.Companion companion = FirExtensionService.INSTANCE;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FirExpressionResolutionExtension.class);
        List listEmptyList = CollectionsKt.emptyList();
        listEmptyList.getClass();
        expressionResolutionExtensions$delegate = companion.generateAccessor(orCreateKotlinClass, listEmptyList);
        captureValueInAnalyze$delegate = FirDeclarationDataRegistry.INSTANCE.data(CaptureValueInAnalyzeKey.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getCaptureValueInAnalyze(FirReceiverParameterSymbol firReceiverParameterSymbol) {
        firReceiverParameterSymbol.getClass();
        Boolean captureValueInAnalyze = getCaptureValueInAnalyze((FirReceiverParameter) firReceiverParameterSymbol.getFir());
        if (captureValueInAnalyze != null) {
            return captureValueInAnalyze.booleanValue();
        }
        return true;
    }

    public static final List<FirExpressionResolutionExtension> getExpressionResolutionExtensions(FirExtensionService firExtensionService) {
        firExtensionService.getClass();
        return (List) expressionResolutionExtensions$delegate.getValue(firExtensionService, $$delegatedProperties[0]);
    }

    public static final void setCaptureValueInAnalyze(FirReceiverParameter firReceiverParameter, Boolean bool) {
        firReceiverParameter.getClass();
        captureValueInAnalyze$delegate.setValue(firReceiverParameter, $$delegatedProperties[1], bool);
    }

    public static final Boolean getCaptureValueInAnalyze(FirReceiverParameter firReceiverParameter) {
        firReceiverParameter.getClass();
        return (Boolean) captureValueInAnalyze$delegate.getValue(firReceiverParameter, $$delegatedProperties[1]);
    }
}
