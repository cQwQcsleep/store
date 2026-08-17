package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirErrorNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguouslyResolvedAnnotationArgument;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bH\u0016J(\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001a\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirExpressionTransformerForAnnotationArguments;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "annotationArgumentsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirAnnotationArgumentsTransformer;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirAnnotationArgumentsTransformer;)V", "transformAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "transformErrorAnnotationCall", "errorAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorAnnotationCall;", "transformQualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "resolveSpecialPropertyAccess", "originalAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "originalCalleeReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "originalResolvedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "generatePropertyAccessExpression", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "accessSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirExpressionTransformerForAnnotationArguments extends FirExpressionsResolveTransformer {
    private final FirAnnotationArgumentsTransformer annotationArgumentsTransformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirExpressionTransformerForAnnotationArguments(FirAnnotationArgumentsTransformer firAnnotationArgumentsTransformer) {
        super(firAnnotationArgumentsTransformer);
        firAnnotationArgumentsTransformer.getClass();
        this.annotationArgumentsTransformer = firAnnotationArgumentsTransformer;
    }

    private final FirPropertyAccessExpression generatePropertyAccessExpression(FqName fqName, KtSourceElement accessSource) {
        List<Name> listPathSegments = fqName.pathSegments();
        int i = 0;
        FirPropertyAccessExpression firPropertyAccessExpressionMo288build = null;
        for (Name name : listPathSegments) {
            int i2 = i + 1;
            FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder.setName(name);
            firPropertyAccessExpressionBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            firPropertyAccessExpressionBuilder.setExplicitReceiver(firPropertyAccessExpressionMo288build);
            if (i == CollectionsKt.getLastIndex(listPathSegments)) {
                firPropertyAccessExpressionBuilder.setSource(accessSource);
            }
            firPropertyAccessExpressionMo288build = firPropertyAccessExpressionBuilder.mo288build();
            i = i2;
        }
        if (firPropertyAccessExpressionMo288build != null) {
            return firPropertyAccessExpressionMo288build;
        }
        k2d.a("Got an empty ClassId");
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0044  */
    private final FirExpression resolveSpecialPropertyAccess(FirPropertyAccessExpression originalAccess, FirResolvedNamedReference originalCalleeReference, FirEnumEntrySymbol originalResolvedSymbol, ResolutionMode data) {
        FqName packageFqName;
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        firPropertyAccessExpressionBuilder.setSource(originalAccess.getSource());
        firPropertyAccessExpressionBuilder.getTypeArguments().addAll(originalAccess.getTypeArguments());
        FirExpression explicitReceiver = originalAccess.getExplicitReceiver();
        FirExpression firExpressionUnwrapSmartcastExpression = explicitReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver) : null;
        if (firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier) {
            FirResolvedQualifier firResolvedQualifier = (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression;
            ClassId classId = firResolvedQualifier.getClassId();
            if (classId == null) {
                packageFqName = firResolvedQualifier.getPackageFqName();
            } else {
                packageFqName = firResolvedQualifier.getIsFullyQualified() ? classId.asSingleFqName() : classId.getRelativeClassName();
                if (packageFqName == null) {
                    packageFqName = firResolvedQualifier.getPackageFqName();
                }
            }
            firPropertyAccessExpressionBuilder.setExplicitReceiver(generatePropertyAccessExpression(packageFqName, firResolvedQualifier.getSource()));
        }
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(originalCalleeReference.getSource());
        firSimpleNamedReferenceBuilder.setName(originalCalleeReference.getName());
        firPropertyAccessExpressionBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        FirPropertyAccessExpression firPropertyAccessExpressionMo288build = firPropertyAccessExpressionBuilder.mo288build();
        FirExpression firExpressionTransformQualifiedAccessExpression = super.transformQualifiedAccessExpression((FirQualifiedAccessExpression) firPropertyAccessExpressionMo288build, data);
        if (firExpressionTransformQualifiedAccessExpression instanceof FirQualifiedAccessExpression) {
            FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) firExpressionTransformQualifiedAccessExpression;
            FirBasedSymbol resolvedBaseSymbol$default = FirReferenceUtilsKt.toResolvedBaseSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
            if (!Intrinsics.areEqual(originalResolvedSymbol, resolvedBaseSymbol$default)) {
                FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
                firErrorNamedReferenceBuilder.setSource(firQualifiedAccessExpression.getCalleeReference().getSource());
                firErrorNamedReferenceBuilder.setDiagnostic(new ConeAmbiguouslyResolvedAnnotationArgument(originalResolvedSymbol, resolvedBaseSymbol$default));
                firErrorNamedReferenceBuilder.setName(firPropertyAccessExpressionMo288build.getCalleeReference().getName());
                firQualifiedAccessExpression.replaceCalleeReference(firErrorNamedReferenceBuilder.build());
            }
        }
        return firExpressionTransformQualifiedAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotationCall(FirAnnotationCall annotationCall, ResolutionMode data) {
        annotationCall.getClass();
        data.getClass();
        FirAnnotationArgumentsTransformer firAnnotationArgumentsTransformer = this.annotationArgumentsTransformer;
        boolean isInsideAnnotationArgument = firAnnotationArgumentsTransformer.getIsInsideAnnotationArgument();
        firAnnotationArgumentsTransformer.setInsideAnnotationArgument(true);
        try {
            return super.transformAnnotationCall(annotationCall, data);
        } finally {
            firAnnotationArgumentsTransformer.setInsideAnnotationArgument(isInsideAnnotationArgument);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorAnnotationCall(FirErrorAnnotationCall errorAnnotationCall, ResolutionMode data) {
        errorAnnotationCall.getClass();
        data.getClass();
        FirAnnotationArgumentsTransformer firAnnotationArgumentsTransformer = this.annotationArgumentsTransformer;
        boolean isInsideAnnotationArgument = firAnnotationArgumentsTransformer.getIsInsideAnnotationArgument();
        firAnnotationArgumentsTransformer.setInsideAnnotationArgument(true);
        try {
            return super.transformErrorAnnotationCall(errorAnnotationCall, data);
        } finally {
            firAnnotationArgumentsTransformer.setInsideAnnotationArgument(isInsideAnnotationArgument);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirExpression transformQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, ResolutionMode data) {
        qualifiedAccessExpression.getClass();
        data.getClass();
        if (qualifiedAccessExpression instanceof FirPropertyAccessExpression) {
            FirPropertyAccessExpression firPropertyAccessExpression = (FirPropertyAccessExpression) qualifiedAccessExpression;
            FirNamedReference calleeReference = firPropertyAccessExpression.getCalleeReference();
            if (calleeReference instanceof FirResolvedNamedReference) {
                FirResolvedNamedReference firResolvedNamedReference = (FirResolvedNamedReference) calleeReference;
                FirBasedSymbol<?> resolvedSymbol = firResolvedNamedReference.getResolvedSymbol();
                if (resolvedSymbol instanceof FirEnumEntrySymbol) {
                    Set set = FirAnnotationArgumentsTransformerKt.classIdsToCheck;
                    ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) resolvedSymbol);
                    if (CollectionsKt.contains(set, coneClassLikeLookupTagContainingClassLookupTag != null ? coneClassLikeLookupTagContainingClassLookupTag.getClassId() : null)) {
                        return resolveSpecialPropertyAccess(firPropertyAccessExpression, firResolvedNamedReference, (FirEnumEntrySymbol) resolvedSymbol, data);
                    }
                }
            }
        }
        return super.transformQualifiedAccessExpression(qualifiedAccessExpression, data);
    }
}
