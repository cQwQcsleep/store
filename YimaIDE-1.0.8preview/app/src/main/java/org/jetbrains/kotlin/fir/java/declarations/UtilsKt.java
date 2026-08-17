package org.jetbrains.kotlin.fir.java.declarations;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirStatusTransformerExtension;
import org.jetbrains.kotlin.fir.extensions.FirStatusTransformerExtensionKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a;\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u001d\u0010\n\u001a\u0019\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b¢\u0006\u0002\b\rH\u0080\bø\u0001\u0000\u001a$\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"javaOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "isFromSource", Argument.Delimiters.none, "applyStatusTransformerExtensions", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "originalStatus", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;", "operation", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/extensions/FirStatusTransformerExtension;", "Lkotlin/ExtensionFunctionType;", "shouldNotBeCalled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "mutator", "Lkotlin/reflect/KCallable;", "reader", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UtilsKt {
    public static final FirDeclarationStatus applyStatusTransformerExtensions(FirMemberDeclaration firMemberDeclaration, FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl, Function2<? super FirStatusTransformerExtension, ? super FirDeclarationStatus, ? extends FirDeclarationStatus> function2) {
        FirClassLikeSymbol<FirClassLikeDeclaration> symbol;
        firMemberDeclaration.getClass();
        firResolvedDeclarationStatusImpl.getClass();
        function2.getClass();
        List<FirStatusTransformerExtension> statusTransformerExtensions = FirStatusTransformerExtensionKt.getStatusTransformerExtensions(FirExtensionServiceKt.getExtensionService(firMemberDeclaration.getModuleData().getSession()));
        if (!statusTransformerExtensions.isEmpty()) {
            FirDeclarationStatus firDeclarationStatus = firResolvedDeclarationStatusImpl;
            for (FirStatusTransformerExtension firStatusTransformerExtension : statusTransformerExtensions) {
                if (firStatusTransformerExtension.needTransformStatus(firMemberDeclaration)) {
                    firDeclarationStatus = (FirDeclarationStatus) function2.invoke(firStatusTransformerExtension, firDeclarationStatus);
                }
            }
            firDeclarationStatus.getClass();
            FirDeclarationStatusImpl firDeclarationStatusImpl = (FirDeclarationStatusImpl) firDeclarationStatus;
            if (firDeclarationStatusImpl != firResolvedDeclarationStatusImpl) {
                if (Intrinsics.areEqual(firDeclarationStatusImpl.getVisibility(), firResolvedDeclarationStatusImpl.getVisibility()) || !(firMemberDeclaration instanceof FirClassLikeDeclaration)) {
                    Visibility visibility = firDeclarationStatusImpl.getVisibility();
                    Modality modality = firDeclarationStatusImpl.getModality();
                    if (modality == null) {
                        modality = firResolvedDeclarationStatusImpl.getModality();
                    }
                    return firDeclarationStatusImpl.resolved(visibility, modality, firResolvedDeclarationStatusImpl.getEffectiveVisibility());
                }
                StringBuilder sb = new StringBuilder("Attempt to change visibility of a class-like: ");
                FirClassLikeDeclaration firClassLikeDeclaration = firMemberDeclaration instanceof FirClassLikeDeclaration ? (FirClassLikeDeclaration) firMemberDeclaration : null;
                sb.append((firClassLikeDeclaration == null || (symbol = firClassLikeDeclaration.getSymbol()) == null) ? null : symbol.getClassId());
                sb.append(", original visibility: ");
                sb.append(firResolvedDeclarationStatusImpl.getVisibility());
                ywd.a(sb, ", new visibility: ", firDeclarationStatusImpl.getVisibility());
                return null;
            }
        }
        return firResolvedDeclarationStatusImpl;
    }

    public static final FirDeclarationOrigin.Java javaOrigin(boolean z) {
        return z ? FirDeclarationOrigin.Java.Source.INSTANCE : FirDeclarationOrigin.Java.Library.INSTANCE;
    }

    public static final Void shouldNotBeCalled(FirDeclaration firDeclaration, KCallable<?> kCallable, KCallable<?> kCallable2) {
        firDeclaration.getClass();
        kCallable.getClass();
        kCallable2.getClass();
        throw new IllegalStateException((kCallable.getName() + " should not be called for " + Reflection.getOrCreateKotlinClass(firDeclaration.getClass()).getSimpleName() + ", " + kCallable2.getName() + " is lazily calculated").toString());
    }
}
