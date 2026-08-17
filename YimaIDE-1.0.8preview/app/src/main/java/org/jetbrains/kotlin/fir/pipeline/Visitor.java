package org.jetbrains.kotlin.fir.pipeline;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagWithFixedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitorVoid;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/Visitor;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitorVoid;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "visitResolvedNamedReference", "resolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "visitResolvedQualifier", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "visitResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "lookupInType", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class Visitor extends FirDefaultVisitorVoid {
    private final FirSession session;

    public Visitor(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    private final void lookupInType(ConeKotlinType type) {
        List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{type});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf);
            ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(coneKotlinType);
            if (classLikeLookupTagIfAny != null && !(classLikeLookupTagIfAny instanceof ConeClassLikeLookupTagWithFixedSymbol)) {
                FirSymbolProviderKt.getSymbolProvider(this.session).getClassLikeSymbolByClassId(classLikeLookupTagIfAny.getClassId());
            }
            if (coneKotlinType instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
            } else if (coneKotlinType instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType).getIntersectedTypes());
            } else {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
        }
    }

    public final FirSession getSession() {
        return this.session;
    }

    public void visitElement(FirElement element) {
        element.getClass();
        if (element instanceof FirExpression) {
            lookupInType(FirTypeUtilsKt.getResolvedType((FirExpression) element));
        }
        element.acceptChildren(this);
    }

    public void visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression) {
        qualifiedAccessExpression.getClass();
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(qualifiedAccessExpression);
        CallableId callableId = resolvedCallableSymbol != null ? resolvedCallableSymbol.getCallableId() : null;
        if (callableId != null && callableId.getClassName() == null) {
            FirSymbolProviderKt.getSymbolProvider(this.session).getTopLevelCallableSymbols(callableId.getPackageName(), callableId.getCallableName());
        }
        super/*org.jetbrains.kotlin.fir.visitors.FirVisitorVoid*/.visitQualifiedAccessExpression(qualifiedAccessExpression);
    }

    public void visitResolvedNamedReference(FirResolvedNamedReference resolvedNamedReference) {
        resolvedNamedReference.getClass();
        FirBasedSymbol<?> resolvedSymbol = resolvedNamedReference.getResolvedSymbol();
        FirCallableSymbol firCallableSymbol = resolvedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) resolvedSymbol : null;
        if (firCallableSymbol != null) {
            if (firCallableSymbol.isLocal()) {
                firCallableSymbol = null;
            }
            if (firCallableSymbol == null) {
                return;
            }
            CallableId callableId = firCallableSymbol.getCallableId();
            CallableId callableId2 = (callableId != null ? callableId.getClassId() : null) == null ? callableId : null;
            if (callableId2 == null) {
                return;
            }
            FirSymbolProviderKt.getSymbolProvider(this.session).getTopLevelCallableSymbols(callableId2.getPackageName(), callableId2.getCallableName());
        }
    }

    public void visitResolvedQualifier(FirResolvedQualifier resolvedQualifier) {
        resolvedQualifier.getClass();
        lookupInType(FirTypeUtilsKt.getResolvedType(resolvedQualifier));
        visitElement(resolvedQualifier);
    }

    public void visitResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef) {
        resolvedTypeRef.getClass();
        lookupInType(resolvedTypeRef.getConeType());
    }
}
