package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnexpectedTypeArgumentsError;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.resolve.FirTypeResolutionResult;
import org.jetbrains.kotlin.fir.resolve.FirTypeResolver;
import org.jetbrains.kotlin.fir.resolve.FirTypeResolverKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeSupplier;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeResolutionConfiguration;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeVisibilityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedTypeQualifierError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnsupportedDefaultValueInFunctionType;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirUserTypeRefBuilder;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ/\u0010\u0019\u001a\u0002H\u001a\"\u0004\b\u0000\u0010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001eJ%\u0010!\u001a\u0002H\u001a\"\u0004\b\u0000\u0010\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\"J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0002H\u0016J\u0018\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020*2\u0006\u0010'\u001a\u00020\u0002H\u0016J&\u0010+\u001a\u00020,*\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010-\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u0006H\u0002J4\u0010.\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u0010-\u001a\u00020\u00022\b\u00103\u001a\u0004\u0018\u000104H\u0002J(\u00105\u001a\u0002062\u0006\u0010%\u001a\u00020&2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u0010-\u001a\u00020\u0002H\u0002J\u0014\u00107\u001a\u000208*\u0002082\u0006\u00109\u001a\u000200H\u0002J&\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020>0=2\b\u0010@\u001a\u0004\u0018\u00010$H\u0002J\u001a\u0010A\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0002H\u0002J\u0016\u0010B\u001a\u00020C2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020>0=H\u0002J\u000e\u0010D\u001a\u0004\u0018\u000100*\u000200H\u0002J\u0018\u0010E\u001a\u00020&2\u0006\u0010F\u001a\u00020$2\u0006\u0010'\u001a\u00020\u0002H\u0016J\u0018\u0010G\u001a\u00020&2\u0006\u0010H\u001a\u0002062\u0006\u0010'\u001a\u00020\u0002H\u0016J\u0018\u0010I\u001a\u00020&2\u0006\u0010J\u001a\u00020K2\u0006\u0010'\u001a\u00020\u0002H\u0016J\u0018\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020O2\u0006\u0010'\u001a\u00020\u0002H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0013\u001a\u00020\u0006@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u0018¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R!\u0010\u001f\u001a\u00020\u0006@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u0018¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\u0018\u0010:\u001a\u00020\u0006*\u0002008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006P"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirAbstractTreeTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "errorTypeAsResolved", Argument.Delimiters.none, "resolveDeprecations", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "expandTypeAliases", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;ZZLorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;Z)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "typeResolver", "Lorg/jetbrains/kotlin/fir/resolve/FirTypeResolver;", "getTypeResolver", "()Lorg/jetbrains/kotlin/fir/resolve/FirTypeResolver;", "areBareTypesAllowed", "getAreBareTypesAllowed", "()Z", "setAreBareTypesAllowed", "(Z)V", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "withBareTypes", "R", "allowed", "block", "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isOperandOfIsOperator", "setOperandOfIsOperator", "withIsOperandOfIsOperator", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "transformTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "data", "transformFunctionTypeRef", "functionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "resolveType", "Lorg/jetbrains/kotlin/fir/resolve/FirTypeResolutionResult;", "configuration", "transformType", "resolvedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "buildErrorType", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "fakeIfAbbreviated", "Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.TYPE, "isTypealiasWithErrorInExpansion", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "smallestUnresolvablePrefix", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "qualifiers", "partiallyResolvedTypeRef", "tryCalculatingPartiallyResolvedTypeRef", "calculatePartiallyResolvablePackageSegments", Argument.Delimiters.none, "takeIfAcceptable", "transformResolvedTypeRef", "resolvedTypeRef", "transformErrorTypeRef", "errorTypeRef", "transformImplicitTypeRef", "implicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "transformValueParameter", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSpecificTypeResolverTransformer extends FirAbstractTreeTransformer<TypeResolutionConfiguration> {
    private boolean areBareTypesAllowed;
    private final boolean errorTypeAsResolved;
    private final boolean expandTypeAliases;
    private boolean isOperandOfIsOperator;
    private final boolean resolveDeprecations;
    private final FirSession session;
    private final SupertypeSupplier supertypeSupplier;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirSpecificTypeResolverTransformer(FirSession firSession, boolean z, boolean z2, SupertypeSupplier supertypeSupplier, boolean z3) {
        super(FirResolvePhase.SUPER_TYPES);
        firSession.getClass();
        supertypeSupplier.getClass();
        this.session = firSession;
        this.errorTypeAsResolved = z;
        this.resolveDeprecations = z2;
        this.supertypeSupplier = supertypeSupplier;
        this.expandTypeAliases = z3;
    }

    private final FirErrorTypeRef buildErrorType(FirTypeRef typeRef, ConeKotlinType resolvedType, ConeDiagnostic diagnostic, TypeResolutionConfiguration configuration) {
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        KtSourceElement source = typeRef.getSource();
        KtSourceElementKind kind = source != null ? source.getKind() : null;
        ConeUnexpectedTypeArgumentsError coneUnexpectedTypeArgumentsError = diagnostic instanceof ConeUnexpectedTypeArgumentsError ? (ConeUnexpectedTypeArgumentsError) diagnostic : null;
        KtSourceElement source2 = coneUnexpectedTypeArgumentsError != null ? coneUnexpectedTypeArgumentsError.getSource() : null;
        if (source2 == null) {
            source2 = typeRef.getSource();
        } else if (kind instanceof KtFakeSourceElementKind) {
            source2 = KtSourceElementKt.fakeElement$default(source2, (KtFakeSourceElementKind) kind, null, 2, null);
        }
        firErrorTypeRefBuilder.setSource(source2 != null ? fakeIfAbbreviated(source2, resolvedType) : null);
        firErrorTypeRefBuilder.setDelegatedTypeRef(typeRef);
        firErrorTypeRefBuilder.setConeType(resolvedType);
        CollectionsKt.addAll(firErrorTypeRefBuilder.getAnnotations(), typeRef.getAnnotations());
        FirResolvedTypeRef firResolvedTypeRefTryCalculatingPartiallyResolvedTypeRef = tryCalculatingPartiallyResolvedTypeRef(typeRef, configuration);
        firErrorTypeRefBuilder.setPartiallyResolvedTypeRef(firResolvedTypeRefTryCalculatingPartiallyResolvedTypeRef);
        if (diagnostic instanceof ConeUnresolvedTypeQualifierError) {
            diagnostic = new ConeUnresolvedTypeQualifierError(smallestUnresolvablePrefix(((ConeUnresolvedTypeQualifierError) diagnostic).getQualifiers(), firResolvedTypeRefTryCalculatingPartiallyResolvedTypeRef));
        } else if ((diagnostic instanceof ConeVisibilityError) && (typeRef instanceof FirUserTypeRef)) {
            diagnostic = new ConeTypeVisibilityError(((ConeVisibilityError) diagnostic).getSymbol(), smallestUnresolvablePrefix(((FirUserTypeRef) typeRef).getQualifier(), firResolvedTypeRefTryCalculatingPartiallyResolvedTypeRef));
        }
        firErrorTypeRefBuilder.setDiagnostic(diagnostic);
        return firErrorTypeRefBuilder.build();
    }

    private final int calculatePartiallyResolvablePackageSegments(List<? extends FirQualifierPart> qualifiers) {
        if (qualifiers.size() <= 1) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = qualifiers.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirQualifierPart) it.next()).getName().asString());
        }
        while (arrayList.size() > 1) {
            CollectionsKt.removeLast(arrayList);
            if (FirSymbolProviderKt.getSymbolProvider(getSession()).hasPackage(FqName.Companion.fromSegments(arrayList))) {
                return arrayList.size();
            }
        }
        return 0;
    }

    private final KtSourceElement fakeIfAbbreviated(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType) {
        ConeKotlinType abbreviatedType;
        KtSourceElement ktSourceElement2 = ((ktSourceElement.getKind() instanceof KtRealSourceElementKind) && (abbreviatedType = AbbreviatedTypeAttributeKt.getAbbreviatedType(coneKotlinType)) != null && isTypealiasWithErrorInExpansion(abbreviatedType)) ? null : ktSourceElement;
        return ktSourceElement2 == null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ErroneousTypealiasExpansion.INSTANCE, null, 2, null) : ktSourceElement2;
    }

    private final FirTypeResolver getTypeResolver() {
        return FirTypeResolverKt.getTypeResolver(getSession());
    }

    private final boolean isTypealiasWithErrorInExpansion(ConeKotlinType coneKotlinType) {
        FirTypeAliasSymbol typeAliasSymbol = ToSymbolUtilsKt.toTypeAliasSymbol(this, coneKotlinType);
        return (typeAliasSymbol != null ? typeAliasSymbol.getResolvedExpandedTypeRef() : null) instanceof FirErrorTypeRef;
    }

    private final FirTypeResolutionResult resolveType(FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer, FirTypeRef firTypeRef, TypeResolutionConfiguration typeResolutionConfiguration, boolean z) {
        return firSpecificTypeResolverTransformer.getTypeResolver().resolveType(firTypeRef, typeResolutionConfiguration, firSpecificTypeResolverTransformer.areBareTypesAllowed, firSpecificTypeResolverTransformer.isOperandOfIsOperator, firSpecificTypeResolverTransformer.resolveDeprecations, firSpecificTypeResolverTransformer.supertypeSupplier, z);
    }

    public static /* synthetic */ FirTypeResolutionResult resolveType$default(FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer, FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer2, FirTypeRef firTypeRef, TypeResolutionConfiguration typeResolutionConfiguration, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return firSpecificTypeResolverTransformer.resolveType(firSpecificTypeResolverTransformer2, firTypeRef, typeResolutionConfiguration, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<FirQualifierPart> smallestUnresolvablePrefix(List<? extends FirQualifierPart> qualifiers, FirResolvedTypeRef partiallyResolvedTypeRef) {
        List qualifier;
        int size = qualifiers.size();
        FirTypeRef delegatedTypeRef = partiallyResolvedTypeRef != null ? partiallyResolvedTypeRef.getDelegatedTypeRef() : null;
        FirUserTypeRef firUserTypeRef = delegatedTypeRef instanceof FirUserTypeRef ? (FirUserTypeRef) delegatedTypeRef : null;
        int iCalculatePartiallyResolvablePackageSegments = size - ((firUserTypeRef == null || (qualifier = firUserTypeRef.getQualifier()) == null) ? calculatePartiallyResolvablePackageSegments(qualifiers) : qualifier.size());
        return iCalculatePartiallyResolvablePackageSegments > 1 ? CollectionsKt.dropLast(qualifiers, iCalculatePartiallyResolvablePackageSegments - 1) : qualifiers;
    }

    private final ConeKotlinType takeIfAcceptable(ConeKotlinType coneKotlinType) {
        if (this.errorTypeAsResolved || !(coneKotlinType instanceof ConeErrorType)) {
            return coneKotlinType;
        }
        return null;
    }

    private final FirResolvedTypeRef transformType(FirTypeRef typeRef, ConeKotlinType resolvedType, ConeDiagnostic diagnostic, TypeResolutionConfiguration configuration, FirResolvedSymbolOrigin resolvedSymbolOrigin) {
        if (resolvedType instanceof ConeErrorType) {
            return buildErrorType(typeRef, resolvedType, ((ConeErrorType) resolvedType).getDiagnostic(), configuration);
        }
        if (diagnostic != null) {
            return buildErrorType(typeRef, resolvedType, diagnostic, configuration);
        }
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setSource(typeRef.getSource());
        firResolvedTypeRefBuilder.setConeType(resolvedType);
        CollectionsKt.addAll(firResolvedTypeRefBuilder.getAnnotations(), typeRef.getAnnotations());
        firResolvedTypeRefBuilder.setDelegatedTypeRef(typeRef);
        firResolvedTypeRefBuilder.setResolvedSymbolOrigin(resolvedSymbolOrigin);
        return firResolvedTypeRefBuilder.build();
    }

    private final FirResolvedTypeRef tryCalculatingPartiallyResolvedTypeRef(FirTypeRef typeRef, TypeResolutionConfiguration data) {
        if (!(typeRef instanceof FirUserTypeRef)) {
            return null;
        }
        FirUserTypeRef firUserTypeRef = (FirUserTypeRef) typeRef;
        List qualifier = firUserTypeRef.getQualifier();
        if (qualifier.size() <= 1) {
            return null;
        }
        List mutableList = CollectionsKt.toMutableList(qualifier);
        while (mutableList.size() > 1) {
            CollectionsKt.removeLast(mutableList);
            FirUserTypeRefBuilder firUserTypeRefBuilder = new FirUserTypeRefBuilder();
            CollectionsKt.addAll(firUserTypeRefBuilder.getQualifier(), mutableList);
            firUserTypeRefBuilder.setMarkedNullable(false);
            firUserTypeRefBuilder.setSource(firUserTypeRef.getSource());
            FirUserTypeRef firUserTypeRefBuild = firUserTypeRefBuilder.build();
            boolean areBareTypesAllowed = this.getAreBareTypesAllowed();
            this.setAreBareTypesAllowed(true);
            FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer = this;
            TypeResolutionConfiguration typeResolutionConfiguration = data;
            try {
                FirTypeResolutionResult firTypeResolutionResultResolveType$default = resolveType$default(firSpecificTypeResolverTransformer, this, firUserTypeRefBuild, typeResolutionConfiguration, false, 4, null);
                firSpecificTypeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
                ConeKotlinType type = firTypeResolutionResultResolveType$default.getType();
                ConeDiagnostic diagnostic = firTypeResolutionResultResolveType$default.getDiagnostic();
                if (!(type instanceof ConeErrorType) && diagnostic == null) {
                    FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                    firResolvedTypeRefBuilder.setSource(((FirQualifierPart) CollectionsKt.last(mutableList)).getSource());
                    firResolvedTypeRefBuilder.setConeType(type);
                    firResolvedTypeRefBuilder.setDelegatedTypeRef(firUserTypeRefBuild);
                    return firResolvedTypeRefBuilder.build();
                }
                this = firSpecificTypeResolverTransformer;
                data = typeResolutionConfiguration;
            } catch (Throwable th) {
                firSpecificTypeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
                throw th;
            }
        }
        return null;
    }

    public static /* synthetic */ Object withBareTypes$default(FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        function0.getClass();
        boolean areBareTypesAllowed = firSpecificTypeResolverTransformer.getAreBareTypesAllowed();
        firSpecificTypeResolverTransformer.setAreBareTypesAllowed(z);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            firSpecificTypeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
            InlineMarker.finallyEnd(1);
        }
    }

    public final boolean getAreBareTypesAllowed() {
        return this.areBareTypesAllowed;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    /* JADX INFO: renamed from: isOperandOfIsOperator, reason: from getter */
    public final boolean getIsOperandOfIsOperator() {
        return this.isOperandOfIsOperator;
    }

    @PrivateForInline
    public final void setAreBareTypesAllowed(boolean z) {
        this.areBareTypesAllowed = z;
    }

    @PrivateForInline
    public final void setOperandOfIsOperator(boolean z) {
        this.isOperandOfIsOperator = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformErrorTypeRef(FirErrorTypeRef errorTypeRef, TypeResolutionConfiguration data) {
        errorTypeRef.getClass();
        data.getClass();
        errorTypeRef.transformPartiallyResolvedTypeRef(this, data);
        return (errorTypeRef.getAnnotations().isEmpty() || errorTypeRef.getConeType().getAttributes().isNotEmpty()) ? errorTypeRef : TypeUtilsKt.withReplacedConeType$default(errorTypeRef, TypeUtilsKt.withAttributes(errorTypeRef.getConeType(), CopyUtilsKt.computeTypeAttributes$default(errorTypeRef.getAnnotations(), getSession(), null, false, true, 6, null)), null, 2, null);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirResolvedTypeRef transformFunctionTypeRef(FirFunctionTypeRef functionTypeRef, TypeResolutionConfiguration data) {
        functionTypeRef.getClass();
        data.getClass();
        functionTypeRef.transformChildren(this, data);
        FirTypeResolutionResult firTypeResolutionResultResolveType$default = resolveType$default(this, this, functionTypeRef, data, false, 4, null);
        ConeKotlinType coneKotlinTypeTakeIfAcceptable = takeIfAcceptable(firTypeResolutionResultResolveType$default.getType());
        ConeDiagnostic diagnostic = firTypeResolutionResultResolveType$default.getDiagnostic();
        if (coneKotlinTypeTakeIfAcceptable != null && !(coneKotlinTypeTakeIfAcceptable instanceof ConeErrorType) && diagnostic == null) {
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setSource(functionTypeRef.getSource());
            firResolvedTypeRefBuilder.setConeType(coneKotlinTypeTakeIfAcceptable);
            CollectionsKt.addAll(firResolvedTypeRefBuilder.getAnnotations(), functionTypeRef.getAnnotations());
            firResolvedTypeRefBuilder.setDelegatedTypeRef(functionTypeRef);
            return firResolvedTypeRefBuilder.build();
        }
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder.setSource(functionTypeRef.getSource());
        if (coneKotlinTypeTakeIfAcceptable != null) {
            firErrorTypeRefBuilder.setConeType(coneKotlinTypeTakeIfAcceptable);
        }
        CollectionsKt.addAll(firErrorTypeRefBuilder.getAnnotations(), functionTypeRef.getAnnotations());
        if (diagnostic == null) {
            ConeErrorType coneErrorType = coneKotlinTypeTakeIfAcceptable instanceof ConeErrorType ? (ConeErrorType) coneKotlinTypeTakeIfAcceptable : null;
            diagnostic = coneErrorType != null ? coneErrorType.getDiagnostic() : null;
            if (diagnostic == null) {
                diagnostic = new ConeSimpleDiagnostic("Unresolved function type: " + UtilsKt.render(functionTypeRef), null, 2, null);
            }
        }
        firErrorTypeRefBuilder.setDiagnostic(diagnostic);
        return firErrorTypeRefBuilder.build();
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformImplicitTypeRef(FirImplicitTypeRef implicitTypeRef, TypeResolutionConfiguration data) {
        implicitTypeRef.getClass();
        data.getClass();
        return implicitTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef, TypeResolutionConfiguration data) {
        resolvedTypeRef.getClass();
        data.getClass();
        return resolvedTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    /* JADX INFO: renamed from: transformTypeRef, reason: merged with bridge method [inline-methods] */
    public FirResolvedTypeRef mo600transformTypeRef(FirTypeRef typeRef, TypeResolutionConfiguration data) {
        typeRef.getClass();
        data.getClass();
        boolean areBareTypesAllowed = getAreBareTypesAllowed();
        setAreBareTypesAllowed(false);
        try {
            typeRef.transformChildren(this, data);
            FirTypeResolutionResult firTypeResolutionResultResolveType = resolveType(this, typeRef, data, this.expandTypeAliases);
            return transformType(typeRef, firTypeResolutionResultResolveType.getType(), firTypeResolutionResultResolveType.getDiagnostic(), data, firTypeResolutionResultResolveType.getResolvedSymbolOrigin());
        } finally {
            setAreBareTypesAllowed(areBareTypesAllowed);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformValueParameter(FirValueParameter valueParameter, TypeResolutionConfiguration data) {
        valueParameter.getClass();
        data.getClass();
        FirValueParameter firValueParameter = (FirValueParameter) transformElement(valueParameter, data);
        FirExpression defaultValue = firValueParameter.getDefaultValue();
        if (defaultValue != null) {
            defaultValue.replaceConeTypeOrNull(new ConeErrorType(new ConeUnsupportedDefaultValueInFunctionType(defaultValue.getSource()), false, null, null, null, null, null, 126, null));
        }
        return firValueParameter;
    }

    public final <R> R withBareTypes(boolean allowed, Function0<? extends R> block) {
        block.getClass();
        boolean areBareTypesAllowed = getAreBareTypesAllowed();
        setAreBareTypesAllowed(allowed);
        try {
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setAreBareTypesAllowed(areBareTypesAllowed);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <R> R withIsOperandOfIsOperator(Function0<? extends R> block) {
        block.getClass();
        boolean isOperandOfIsOperator = getIsOperandOfIsOperator();
        setOperandOfIsOperator(true);
        try {
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setOperandOfIsOperator(isOperandOfIsOperator);
            InlineMarker.finallyEnd(1);
        }
    }

    public /* synthetic */ FirSpecificTypeResolverTransformer(FirSession firSession, boolean z, boolean z2, SupertypeSupplier supertypeSupplier, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, (i & 2) != 0 ? true : z, (i & 4) != 0 ? true : z2, (i & 8) != 0 ? SupertypeSupplier.Default.INSTANCE : supertypeSupplier, z3);
    }
}
