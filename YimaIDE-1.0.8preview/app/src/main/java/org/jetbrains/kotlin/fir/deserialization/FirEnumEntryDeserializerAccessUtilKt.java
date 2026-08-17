package org.jetbrains.kotlin.fir.deserialization;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirEnumEntryDeserializedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirResolvedQualifierBuilder;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirErrorNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\b"}, d2 = {"toQualifiedPropertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "toResolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:fir-deserialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEnumEntryDeserializerAccessUtilKt {
    public static final FirPropertyAccessExpression toQualifiedPropertyAccessExpression(FirEnumEntryDeserializedAccessExpression firEnumEntryDeserializedAccessExpression, FirSession firSession) {
        Object next;
        FirNamedReference firNamedReferenceBuild;
        firEnumEntryDeserializedAccessExpression.getClass();
        firSession.getClass();
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        Iterator<T> it = FirSymbolProviderKt.getClassDeclaredPropertySymbols(firSession, firEnumEntryDeserializedAccessExpression.getEnumClassId(), firEnumEntryDeserializedAccessExpression.getEnumEntryName()).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((FirVariableSymbol) next).getRawStatus().isStatic());
        FirVariableSymbol firVariableSymbol = (FirVariableSymbol) next;
        if (firVariableSymbol != null) {
            FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
            firResolvedNamedReferenceBuilder.setName(firEnumEntryDeserializedAccessExpression.getEnumEntryName());
            firResolvedNamedReferenceBuilder.setResolvedSymbol(firVariableSymbol);
            firNamedReferenceBuild = firResolvedNamedReferenceBuilder.build();
        } else {
            FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
            firErrorNamedReferenceBuilder.setDiagnostic(new ConeSimpleDiagnostic("Strange deserialized enum value: " + firEnumEntryDeserializedAccessExpression.getEnumClassId() + '.' + firEnumEntryDeserializedAccessExpression.getEnumEntryName(), DiagnosticKind.Java));
            firErrorNamedReferenceBuilder.setName(firEnumEntryDeserializedAccessExpression.getEnumEntryName());
            firNamedReferenceBuild = firErrorNamedReferenceBuilder.build();
        }
        firPropertyAccessExpressionBuilder.setCalleeReference(firNamedReferenceBuild);
        FirResolvedQualifier resolvedQualifier = toResolvedQualifier(firEnumEntryDeserializedAccessExpression.getEnumClassId(), firSession);
        firPropertyAccessExpressionBuilder.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(resolvedQualifier));
        firPropertyAccessExpressionBuilder.setDispatchReceiver(resolvedQualifier);
        firPropertyAccessExpressionBuilder.setExplicitReceiver(resolvedQualifier);
        return firPropertyAccessExpressionBuilder.mo288build();
    }

    public static final FirResolvedQualifier toResolvedQualifier(ClassId classId, FirSession firSession) {
        classId.getClass();
        firSession.getClass();
        ConeClassLikeLookupTagImpl lookupTag = TypeConstructionUtilsKt.toLookupTag(classId);
        FirResolvedQualifierBuilder firResolvedQualifierBuilder = new FirResolvedQualifierBuilder();
        firResolvedQualifierBuilder.setConeTypeOrNull(TypeConstructionUtilsKt.constructClassType$default(lookupTag, null, false, null, 7, null));
        firResolvedQualifierBuilder.setPackageFqName(classId.getPackageFqName());
        firResolvedQualifierBuilder.setRelativeClassFqName(classId.getRelativeClassName());
        firResolvedQualifierBuilder.setSymbol(ToSymbolUtilsKt.toSymbol((ConeClassLikeLookupTag) lookupTag, firSession));
        firResolvedQualifierBuilder.setResolvedToCompanionObject(false);
        return firResolvedQualifierBuilder.mo288build();
    }
}
