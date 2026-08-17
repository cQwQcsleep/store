package org.jetbrains.kotlin.fir;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001aR\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u001a\"\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004¨\u0006\u0019"}, d2 = {"toQualifiedAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "fakeSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "generateTemporaryVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "source", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "initializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "extractedAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "generateExplicitReceiverTemporaryVariable", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "expression", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirGenerationKt {
    public static final FirProperty generateExplicitReceiverTemporaryVariable(FirSession firSession, FirExpression firExpression, KtSourceElement ktSourceElement) {
        FirExpression explicitReceiver;
        firSession.getClass();
        firExpression.getClass();
        FirQualifiedAccessExpression firQualifiedAccessExpression = firExpression instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firExpression : null;
        if (firQualifiedAccessExpression != null && (explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver()) != null) {
            FirExpression firExpression2 = ((explicitReceiver instanceof FirResolvedQualifier) || (explicitReceiver instanceof FirThisReceiverExpression) || (explicitReceiver instanceof FirSuperReceiverExpression)) ? null : explicitReceiver;
            if (firExpression2 != null) {
                FirModuleData moduleData = FirModuleDataKt.getModuleData(firSession);
                Name name = SpecialNames.RECEIVER;
                ConeKotlinType coneTypeOrNull = firExpression2.getConeTypeOrNull();
                FirProperty firPropertyGenerateTemporaryVariable$default = generateTemporaryVariable$default(moduleData, ktSourceElement, name, firExpression2, coneTypeOrNull != null ? UtilsKt.toFirResolvedTypeRef$default(coneTypeOrNull, ktSourceElement, null, 2, null) : null, null, null, 96, null);
                FirQualifiedAccessExpression qualifiedAccess$default = toQualifiedAccess$default(firPropertyGenerateTemporaryVariable$default, firExpression2.getSource(), null, 2, null);
                FirQualifiedAccessExpression firQualifiedAccessExpression2 = (FirQualifiedAccessExpression) firExpression;
                if (Intrinsics.areEqual(firQualifiedAccessExpression2.getExplicitReceiver(), firQualifiedAccessExpression2.getDispatchReceiver())) {
                    firQualifiedAccessExpression2.replaceDispatchReceiver(qualifiedAccess$default);
                } else {
                    firQualifiedAccessExpression2.replaceExtensionReceiver(qualifiedAccess$default);
                }
                firQualifiedAccessExpression2.replaceExplicitReceiver(qualifiedAccess$default);
                return firPropertyGenerateTemporaryVariable$default;
            }
        }
        return null;
    }

    public static final FirProperty generateTemporaryVariable(FirModuleData firModuleData, KtSourceElement ktSourceElement, Name name, FirExpression firExpression, FirTypeRef firTypeRef, Collection<? extends FirAnnotation> collection, FirDeclarationOrigin firDeclarationOrigin) {
        firModuleData.getClass();
        name.getClass();
        firExpression.getClass();
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        firPropertyBuilder.setSource(ktSourceElement);
        firPropertyBuilder.setModuleData(firModuleData);
        if (firDeclarationOrigin == null) {
            firDeclarationOrigin = FirDeclarationOrigin.Source.INSTANCE;
        }
        firPropertyBuilder.setOrigin(firDeclarationOrigin);
        if (firTypeRef == null) {
            firTypeRef = FirImplicitTypeRefImplWithoutSource.INSTANCE;
        }
        firPropertyBuilder.setReturnTypeRef(firTypeRef);
        firPropertyBuilder.setName(name);
        firPropertyBuilder.setInitializer(firExpression);
        firPropertyBuilder.setSymbol(new FirLocalPropertySymbol());
        firPropertyBuilder.setVar(false);
        firPropertyBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL, EffectiveVisibility.Local.INSTANCE));
        firPropertyBuilder.setLocal(true);
        if (collection != null) {
            firPropertyBuilder.getAnnotations().addAll(collection);
        }
        return firPropertyBuilder.mo288build();
    }

    public static /* synthetic */ FirProperty generateTemporaryVariable$default(FirModuleData firModuleData, KtSourceElement ktSourceElement, Name name, FirExpression firExpression, FirTypeRef firTypeRef, Collection collection, FirDeclarationOrigin firDeclarationOrigin, int i, Object obj) {
        if ((i & 16) != 0) {
            firTypeRef = null;
        }
        if ((i & 32) != 0) {
            collection = null;
        }
        if ((i & 64) != 0) {
            firDeclarationOrigin = null;
        }
        return generateTemporaryVariable(firModuleData, ktSourceElement, name, firExpression, firTypeRef, collection, firDeclarationOrigin);
    }

    public static final FirQualifiedAccessExpression toQualifiedAccess(FirVariable firVariable, KtSourceElement ktSourceElement, FirTypeRef firTypeRef) {
        firVariable.getClass();
        firTypeRef.getClass();
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        firPropertyAccessExpressionBuilder.setSource(ktSourceElement);
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder.setSource(ktSourceElement);
        firResolvedNamedReferenceBuilder.setName(firVariable.getName());
        firResolvedNamedReferenceBuilder.setResolvedSymbol(firVariable.getSymbol());
        firPropertyAccessExpressionBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
        firPropertyAccessExpressionBuilder.setConeTypeOrNull(FirTypeUtilsKt.getConeTypeOrNull(firTypeRef));
        return firPropertyAccessExpressionBuilder.mo288build();
    }

    public static /* synthetic */ FirQualifiedAccessExpression toQualifiedAccess$default(FirVariable firVariable, KtSourceElement ktSourceElement, FirTypeRef firTypeRef, int i, Object obj) {
        if ((i & 1) != 0) {
            KtSourceElement source = firVariable.getSource();
            ktSourceElement = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess.INSTANCE, null, 2, null) : null;
        }
        if ((i & 2) != 0) {
            firTypeRef = firVariable.getReturnTypeRef();
        }
        return toQualifiedAccess(firVariable, ktSourceElement, firTypeRef);
    }
}
