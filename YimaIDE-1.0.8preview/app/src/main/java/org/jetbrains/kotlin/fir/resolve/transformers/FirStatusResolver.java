package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponentKt;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirStatusTransformerExtension;
import org.jetbrains.kotlin.fir.extensions.FirStatusTransformerExtensionKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.FirStatusResolver;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.DataClassResolver;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 >2\u00020\u0001:\u0001>B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J,\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u001d\u0010\u0012\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u0013¢\u0006\u0002\b\u0014H\u0082\bJ*\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\r2\u0006\u0010 \u001a\u00020\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ2\u0010\u0015\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\rJ\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\r2\u0006\u0010$\u001a\u00020#2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ2\u0010\u0015\u001a\u00020\u00162\u0006\u0010$\u001a\u00020#2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\rJ \u0010\u0015\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001eJ \u0010\u0015\u001a\u00020\u00162\u0006\u0010&\u001a\u00020'2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020+H\u0002J:\u0010\u0015\u001a\u00020\u00162\u0006\u0010,\u001a\u00020-2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00160\rJ \u0010\u0015\u001a\u00020\u00162\u0006\u0010.\u001a\u00020/2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001eJ \u0010\u0015\u001a\u00020\u00162\u0006\u00100\u001a\u0002012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001eJ\"\u0010\u0015\u001a\u00020\u00162\u0006\u00102\u001a\u0002032\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010\u0015\u001a\u00020\u00162\u0006\u00104\u001a\u0002052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001eJB\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00106\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00160\rH\u0002J:\u00107\u001a\u0002082\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00160\rH\u0002J2\u00109\u001a\u00020:2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00160\rH\u0002J\f\u0010;\u001a\u00020\u001e*\u00020\u001aH\u0002J$\u0010<\u001a\u00020=2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirStatusResolver;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "extensionStatusTransformers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirStatusTransformerExtension;", "applyExtensionTransformers", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "operation", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "resolveStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "containingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "containingProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "isLocal", Argument.Delimiters.none, "getOverriddenProperties", "property", "overriddenStatuses", "getOverriddenFunctions", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "function", "firClass", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "classLikeStatusValidation", Argument.Delimiters.none, "newStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "status", "computeMustUseReturnValue", "Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "resolveVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "hasPrivateConstructor", "resolveModality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirStatusResolver implements SessionAndScopeSessionHolder {
    private static final EnumSet<FirDeclarationStatusImpl.Modifier> MODIFIERS_FROM_OVERRIDDEN = EnumSet.of(FirDeclarationStatusImpl.Modifier.OPERATOR, FirDeclarationStatusImpl.Modifier.INFIX);
    private final List<FirStatusTransformerExtension> extensionStatusTransformers;
    private final ScopeSession scopeSession;
    private final FirSession session;

    public FirStatusResolver(FirSession firSession, ScopeSession scopeSession) {
        firSession.getClass();
        scopeSession.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.extensionStatusTransformers = FirStatusTransformerExtensionKt.getStatusTransformerExtensions(FirExtensionServiceKt.getExtensionService(getSession()));
    }

    public static int c(Function2 function2, Object obj, Object obj2) {
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void classLikeStatusValidation(FirDeclarationStatus newStatus, FirClassLikeDeclaration declaration) throws KotlinIllegalArgumentExceptionWithAttachments {
        Visibility visibility = declaration.getStatus().getVisibility();
        if (Intrinsics.areEqual(visibility, Visibilities.Unknown.INSTANCE)) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Visibility has to be provided for a class-like declaration (" + Reflection.getOrCreateKotlinClass(declaration.getClass()).getSimpleName() + ") during its initialization");
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "declaration", declaration);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        if (Intrinsics.areEqual(newStatus.getVisibility(), visibility)) {
            return;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments2 = new KotlinIllegalArgumentExceptionWithAttachments("Attempt to change visibility of a class-like declaration (" + Reflection.getOrCreateKotlinClass(declaration.getClass()).getSimpleName() + "), original visibility: " + visibility + ", new visibility: " + declaration.getStatus().getVisibility());
        ExceptionAttachmentBuilder exceptionAttachmentBuilder2 = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder2, "declaration", declaration);
        kotlinIllegalArgumentExceptionWithAttachments2.withAttachment("info.txt", exceptionAttachmentBuilder2.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments2;
    }

    private final ReturnValueStatus computeMustUseReturnValue(FirDeclaration declaration, boolean isLocal, FirClass containingClass, FirProperty containingProperty, List<? extends FirResolvedDeclarationStatus> overriddenStatuses) {
        if (declaration instanceof FirCallableDeclaration) {
            return FirMustUseReturnValueStatusComponentKt.getMustUseReturnValueStatusComponent(getSession()).computeMustUseReturnValueForCallable(getSession(), ((FirCallableDeclaration) declaration).getSymbol(), isLocal, containingClass != null ? containingClass.getSymbol() : null, containingProperty != null ? containingProperty.getSymbol() : null, overriddenStatuses);
        }
        return ReturnValueStatus.Unspecified;
    }

    public static int f(Visibility visibility, Visibility visibility2) {
        Visibilities visibilities = Visibilities.INSTANCE;
        visibility.getClass();
        visibility2.getClass();
        Integer numCompare = visibilities.compare(visibility, visibility2);
        if (numCompare != null) {
            return numCompare.intValue();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getOverriddenFunctions$lambda$0$0(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ProcessorAction getOverriddenFunctions$lambda$0$1(FirStatusResolver firStatusResolver, FirNamedFunction firNamedFunction, List list, FirNamedFunctionSymbol firNamedFunctionSymbol, FirTypeScope firTypeScope) {
        firNamedFunctionSymbol.getClass();
        firTypeScope.getClass();
        if (FirVisibilityCheckerKt.getVisibilityChecker(firStatusResolver.getSession()).isVisibleForOverriding(firNamedFunction, (FirCallableDeclaration) firNamedFunctionSymbol.getFir())) {
            list.add(firNamedFunctionSymbol.getFir());
        }
        return ProcessorAction.NEXT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getOverriddenProperties$lambda$0$0(FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ProcessorAction getOverriddenProperties$lambda$0$1(FirStatusResolver firStatusResolver, FirProperty firProperty, List list, FirPropertySymbol firPropertySymbol, FirTypeScope firTypeScope) {
        firPropertySymbol.getClass();
        firTypeScope.getClass();
        if (FirVisibilityCheckerKt.getVisibilityChecker(firStatusResolver.getSession()).isVisibleForOverriding(firProperty, (FirCallableDeclaration) firPropertySymbol.getFir())) {
            list.add(firPropertySymbol.getFir());
        }
        return ProcessorAction.NEXT;
    }

    private final boolean hasPrivateConstructor(FirClass firClass) {
        ClassKind classKind = firClass.getClassKind();
        return classKind == ClassKind.ENUM_CLASS || classKind == ClassKind.ENUM_ENTRY || FirStatusResolverKt.getModality(firClass) == Modality.SEALED || (firClass instanceof FirAnonymousObject);
    }

    private final Modality resolveModality(FirDeclaration declaration, FirProperty containingProperty, FirClass containingClass) {
        if (declaration instanceof FirRegularClass) {
            return ((FirRegularClass) declaration).getClassKind() == ClassKind.INTERFACE ? Modality.ABSTRACT : Modality.FINAL;
        }
        if (!(declaration instanceof FirCallableDeclaration)) {
            return Modality.FINAL;
        }
        Modality modality = containingProperty != null ? containingProperty.getStatus().getModality() : null;
        if (containingClass == null) {
            return Modality.FINAL;
        }
        if ((declaration instanceof FirPropertyAccessor) && modality != null) {
            return modality;
        }
        if (containingClass.getClassKind() != ClassKind.INTERFACE) {
            return ((FirMemberDeclaration) declaration).getStatus().isOverride() ? Modality.OPEN : Modality.FINAL;
        }
        if (Intrinsics.areEqual(((FirMemberDeclaration) declaration).getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
            return Modality.FINAL;
        }
        return !FirStatusResolverKt.hasOwnBodyOrAccessorBody(declaration) ? Modality.ABSTRACT : Modality.OPEN;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x005e  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b0  */
    private final FirResolvedDeclarationStatus resolveStatus(FirDeclaration declaration, FirDeclarationStatus status, FirClass containingClass, FirProperty containingProperty, boolean isLocal, List<? extends FirResolvedDeclarationStatus> overriddenStatuses) {
        Visibility visibility;
        EffectiveVisibility effectiveVisibility;
        Visibility visibility2;
        EffectiveVisibility effectiveVisibility2;
        boolean z;
        EffectiveVisibility publishedApiEffectiveVisibility;
        FirClassSymbol<FirClass> symbol;
        boolean z2;
        boolean z3;
        if (status instanceof FirResolvedDeclarationStatus) {
            return (FirResolvedDeclarationStatus) status;
        }
        if (!(status instanceof FirDeclarationStatusImpl)) {
            w01.a("Failed requirement.");
            return null;
        }
        FirDeclarationStatusImpl firDeclarationStatusImpl = (FirDeclarationStatusImpl) status;
        if (Intrinsics.areEqual(firDeclarationStatusImpl.getVisibility(), Visibilities.Unknown.INSTANCE)) {
            visibility = isLocal ? Visibilities.Local.INSTANCE : resolveVisibility(declaration, containingClass, containingProperty, overriddenStatuses);
        } else {
            visibility = firDeclarationStatusImpl.getVisibility();
        }
        Visibility visibility3 = visibility;
        Modality modality = firDeclarationStatusImpl.getModality();
        if (modality == null) {
            modality = resolveModality(declaration, containingProperty, containingClass);
        } else {
            if (modality == Modality.OPEN) {
                if ((containingClass != null ? containingClass.getClassKind() : null) == ClassKind.INTERFACE && !FirStatusResolverKt.hasOwnBodyOrAccessorBody(declaration) && !firDeclarationStatusImpl.isExpect()) {
                    modality = Modality.ABSTRACT;
                }
            }
            if (modality == null) {
                modality = resolveModality(declaration, containingProperty, containingClass);
            }
        }
        if (!overriddenStatuses.isEmpty()) {
            Iterator<FirDeclarationStatusImpl.Modifier> it = MODIFIERS_FROM_OVERRIDDEN.iterator();
            it.getClass();
            while (it.hasNext()) {
                FirDeclarationStatusImpl.Modifier next = it.next();
                next.getClass();
                if (firDeclarationStatusImpl.get(next)) {
                    z2 = true;
                } else {
                    Iterator<T> it2 = overriddenStatuses.iterator();
                    z2 = false;
                    while (true) {
                        z3 = false;
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            Object obj = (FirResolvedDeclarationStatus) it2.next();
                            if (!z3) {
                                obj.getClass();
                                if (((FirDeclarationStatusImpl) obj).get(next)) {
                                }
                            }
                            z3 = true;
                        }
                    }
                    if (z3) {
                        z2 = true;
                    }
                }
                firDeclarationStatusImpl.set(next, z2);
            }
        }
        if (containingProperty != null) {
            FirDeclarationStatus status2 = containingProperty.getStatus();
            FirResolvedDeclarationStatus firResolvedDeclarationStatus = status2 instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status2 : null;
            if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
                effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
            }
        } else if (containingClass instanceof FirRegularClass) {
            FirDeclarationStatus status3 = containingClass.getStatus();
            FirResolvedDeclarationStatus firResolvedDeclarationStatus2 = status3 instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status3 : null;
            if (firResolvedDeclarationStatus2 == null || (effectiveVisibility = firResolvedDeclarationStatus2.getEffectiveVisibility()) == null) {
                effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
            }
        } else {
            effectiveVisibility = containingClass instanceof FirAnonymousObject ? EffectiveVisibility.Local.INSTANCE : EffectiveVisibility.Public.INSTANCE;
        }
        EffectiveVisibility effectiveVisibility3 = effectiveVisibility;
        boolean z4 = declaration instanceof FirClass;
        EffectiveVisibility effectiveVisibility$default = EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(visibility3, (containingClass == null || (symbol = containingClass.getSymbol()) == null) ? null : symbol.getLookupTag(), z4, false, 4, (Object) null);
        EffectiveVisibility effectiveVisibilityLowerBound = effectiveVisibility3.lowerBound(effectiveVisibility$default, TypeComponentsKt.getTypeContext(getSession()));
        if (isLocal) {
            visibility2 = visibility3;
            effectiveVisibility2 = effectiveVisibilityLowerBound;
            z = true;
        } else {
            List<FirAnnotation> annotations = (containingProperty != null ? containingProperty : declaration).getAnnotations();
            if (containingProperty != null) {
                publishedApiEffectiveVisibility = PublishedApiEffectiveVisibilityKt.getPublishedApiEffectiveVisibility(containingProperty);
            } else {
                publishedApiEffectiveVisibility = containingClass instanceof FirRegularClass ? PublishedApiEffectiveVisibilityKt.getPublishedApiEffectiveVisibility(containingClass) : null;
            }
            visibility2 = visibility3;
            effectiveVisibility2 = effectiveVisibilityLowerBound;
            z = true;
            EffectiveVisibility effectiveVisibilityComputePublishedApiEffectiveVisibility = PublishedApiEffectiveVisibilityKt.computePublishedApiEffectiveVisibility(annotations, visibility2, effectiveVisibility$default, containingClass != null ? containingClass.getSymbol() : null, effectiveVisibility3, publishedApiEffectiveVisibility, z4, getSession());
            if (effectiveVisibilityComputePublishedApiEffectiveVisibility != null) {
                PublishedApiEffectiveVisibilityKt.setNonLazyPublishedApiEffectiveVisibility(declaration, effectiveVisibilityComputePublishedApiEffectiveVisibility);
            }
        }
        if ((containingClass instanceof FirRegularClass) && containingClass.getStatus().isExpect()) {
            firDeclarationStatusImpl.setExpect(z);
        }
        firDeclarationStatusImpl.setReturnValueStatus(computeMustUseReturnValue(declaration, isLocal, containingClass, containingProperty, overriddenStatuses));
        return firDeclarationStatusImpl.resolved(visibility2, modality, effectiveVisibility2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirResolvedDeclarationStatus resolveStatus$default(FirStatusResolver firStatusResolver, FirPropertyAccessor firPropertyAccessor, FirClass firClass, FirProperty firProperty, boolean z, List list, int i, Object obj) {
        if ((i & 16) != 0) {
            list = CollectionsKt.emptyList();
        }
        return firStatusResolver.resolveStatus(firPropertyAccessor, firClass, firProperty, z, list);
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0094  */
    /* JADX WARN: Code duplicated, block: B:44:0x00ab A[LOOP:0: B:42:0x00a5->B:44:0x00ab, LOOP_END] */
    /* JADX WARN: Multi-variable type inference failed */
    private final Visibility resolveVisibility(FirDeclaration declaration, FirClass containingClass, FirProperty containingProperty, List<? extends FirResolvedDeclarationStatus> overriddenStatuses) {
        ArrayList arrayList;
        Iterator<T> it;
        Visibility visibility;
        Visibility visibilityNormalize;
        FirDeclarationStatus status;
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny;
        FirConstructor firConstructor;
        Visibility visibility2;
        if ((declaration instanceof FirConstructor) && containingClass != null && hasPrivateConstructor(containingClass)) {
            return Visibilities.Private.INSTANCE;
        }
        Visibility visibility3 = (!(declaration instanceof FirPropertyAccessor) || containingProperty == null) ? Visibilities.Public.INSTANCE : containingProperty.getStatus().getVisibility();
        if (containingClass == null || (status = containingClass.getStatus()) == null || !status.isData() || !(declaration instanceof FirNamedFunction)) {
            List<? extends FirResolvedDeclarationStatus> list = overriddenStatuses;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirResolvedDeclarationStatus) it.next()).getVisibility());
            }
            final Function2 function2 = new Function2() { // from class: ge5
                public final Object invoke(Object obj, Object obj2) {
                    return Integer.valueOf(FirStatusResolver.f((Visibility) obj, (Visibility) obj2));
                }
            };
            visibility = (Visibility) CollectionsKt.maxWithOrNull(arrayList, new Comparator() { // from class: he5
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return FirStatusResolver.c(function2, obj, obj2);
                }
            });
            if (visibility == null && (visibilityNormalize = visibility.normalize()) != null) {
                return visibilityNormalize;
            }
        } else {
            FirNamedFunction firNamedFunction = (FirNamedFunction) declaration;
            if (Intrinsics.areEqual(firNamedFunction.getOrigin(), FirDeclarationOrigin.Synthetic.DataClassMember.INSTANCE) && DataClassResolver.INSTANCE.isCopy(firNamedFunction.getName())) {
                StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
                if (FirAnnotationUtilsKt.hasAnnotation((FirDeclaration) containingClass, standardClassIds$Annotations.getExposedCopyVisibility(), getSession())) {
                    return Visibilities.Public.INSTANCE;
                }
                if ((LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.DataClassCopyRespectsConstructorVisibility) || FirAnnotationUtilsKt.hasAnnotation((FirDeclaration) containingClass, standardClassIds$Annotations.getConsistentCopyVisibility(), getSession())) && (firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(containingClass, getSession())) != null && (firConstructor = (FirConstructor) firConstructorSymbolPrimaryConstructorIfAny.getFir()) != null && (visibility2 = firConstructor.getStatus().getVisibility()) != null) {
                    return visibility2;
                }
            } else {
                List<? extends FirResolvedDeclarationStatus> list2 = overriddenStatuses;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                it = list2.iterator();
                while (it.hasNext()) {
                    arrayList.add(((FirResolvedDeclarationStatus) it.next()).getVisibility());
                }
                final Function2 function3 = new Function2() { // from class: ge5
                    public final Object invoke(Object obj, Object obj2) {
                        return Integer.valueOf(FirStatusResolver.f((Visibility) obj, (Visibility) obj2));
                    }
                };
                visibility = (Visibility) CollectionsKt.maxWithOrNull(arrayList, new Comparator() { // from class: he5
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return FirStatusResolver.c(function3, obj, obj2);
                    }
                });
                if (visibility == null) {
                }
            }
        }
        return visibility3;
    }

    public final List<FirNamedFunction> getOverriddenFunctions(final FirNamedFunction function, FirClass containingClass) {
        function.getClass();
        if (containingClass == null) {
            return CollectionsKt.emptyList();
        }
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        FirTypeScope firTypeScopeUnsubstitutedScope = FirKotlinScopeProviderKt.unsubstitutedScope((SessionAndScopeSessionHolder) this, containingClass, false, (FirResolvePhase) null);
        FirNamedFunctionSymbol symbol = function.getSymbol();
        firTypeScopeUnsubstitutedScope.processFunctionsByName(function.getName(), new Function1() { // from class: ke5
            public final Object invoke(Object obj) {
                return FirStatusResolver.getOverriddenFunctions$lambda$0$0((FirNamedFunctionSymbol) obj);
            }
        });
        firTypeScopeUnsubstitutedScope.processDirectOverriddenFunctionsWithBaseScope(symbol, new Function2() { // from class: le5
            public final Object invoke(Object obj, Object obj2) {
                return FirStatusResolver.getOverriddenFunctions$lambda$0$1(this.b, function, listCreateListBuilder, (FirNamedFunctionSymbol) obj, (FirTypeScope) obj2);
            }
        });
        return CollectionsKt.build(listCreateListBuilder);
    }

    public final List<FirProperty> getOverriddenProperties(final FirProperty property, FirClass containingClass) {
        property.getClass();
        if (containingClass == null) {
            return CollectionsKt.emptyList();
        }
        FirTypeScope firTypeScopeUnsubstitutedScope = FirKotlinScopeProviderKt.unsubstitutedScope((SessionAndScopeSessionHolder) this, containingClass, false, (FirResolvePhase) null);
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        firTypeScopeUnsubstitutedScope.processPropertiesByName(property.getName(), new Function1() { // from class: ie5
            public final Object invoke(Object obj) {
                return FirStatusResolver.getOverriddenProperties$lambda$0$0((FirVariableSymbol) obj);
            }
        });
        firTypeScopeUnsubstitutedScope.processDirectOverriddenPropertiesWithBaseScope(property.getSymbol(), new Function2() { // from class: je5
            public final Object invoke(Object obj, Object obj2) {
                return FirStatusResolver.getOverriddenProperties$lambda$0$1(this.b, property, listCreateListBuilder, (FirPropertySymbol) obj, (FirTypeScope) obj2);
            }
        });
        return CollectionsKt.build(listCreateListBuilder);
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirResolvedDeclarationStatus resolveStatus$default(FirStatusResolver firStatusResolver, FirNamedFunction firNamedFunction, FirClass firClass, boolean z, List list, int i, Object obj) {
        if ((i & 8) != 0) {
            list = null;
        }
        return firStatusResolver.resolveStatus(firNamedFunction, firClass, z, (List<? extends FirResolvedDeclarationStatus>) list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirResolvedDeclarationStatus resolveStatus$default(FirStatusResolver firStatusResolver, FirProperty firProperty, FirClass firClass, boolean z, List list, int i, Object obj) {
        if ((i & 8) != 0) {
            list = null;
        }
        return firStatusResolver.resolveStatus(firProperty, firClass, z, (List<? extends FirResolvedDeclarationStatus>) list);
    }

    public final FirResolvedDeclarationStatus resolveStatus(FirProperty property, FirClass containingClass, boolean isLocal, List<? extends FirResolvedDeclarationStatus> overriddenStatuses) {
        List<? extends FirResolvedDeclarationStatus> list;
        FirDeclarationStatus status;
        property.getClass();
        if (overriddenStatuses == null) {
            List<FirProperty> overriddenProperties = getOverriddenProperties(property, containingClass);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenProperties, 10));
            Iterator<T> it = overriddenProperties.iterator();
            while (it.hasNext()) {
                FirDeclarationStatus status2 = ((FirProperty) it.next()).getStatus();
                status2.getClass();
                arrayList.add((FirResolvedDeclarationStatus) status2);
            }
            list = arrayList;
        } else {
            list = overriddenStatuses;
        }
        if (this.extensionStatusTransformers.isEmpty()) {
            status = property.getStatus();
        } else {
            List<FirStatusTransformerExtension> list2 = this.extensionStatusTransformers;
            FirDeclarationStatus status3 = property.getStatus();
            for (FirStatusTransformerExtension firStatusTransformerExtension : list2) {
                if (firStatusTransformerExtension.needTransformStatus(property)) {
                    status3 = firStatusTransformerExtension.transformStatus(status3, property, containingClass != null ? containingClass.getSymbol() : null, isLocal);
                }
            }
            status = status3;
        }
        return resolveStatus(property, status, containingClass, null, isLocal, list);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final FirResolvedDeclarationStatus resolveStatus(FirClass firClass, FirClass containingClass, boolean isLocal) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirDeclarationStatus status;
        FirDeclarationStatus firDeclarationStatus;
        firClass.getClass();
        if (firClass instanceof FirRegularClass) {
            if (this.extensionStatusTransformers.isEmpty()) {
                status = firClass.getStatus();
            } else {
                List<FirStatusTransformerExtension> list = this.extensionStatusTransformers;
                FirDeclarationStatus status2 = firClass.getStatus();
                for (FirStatusTransformerExtension firStatusTransformerExtension : list) {
                    if (firStatusTransformerExtension.needTransformStatus(firClass)) {
                        status2 = firStatusTransformerExtension.transformStatus(status2, (FirRegularClass) firClass, containingClass != null ? containingClass.getSymbol() : null, isLocal);
                    }
                }
                firDeclarationStatus = status2;
            }
            classLikeStatusValidation(firDeclarationStatus, firClass);
            return resolveStatus(firClass, firDeclarationStatus, containingClass, null, isLocal, CollectionsKt.emptyList());
        }
        status = firClass.getStatus();
        firDeclarationStatus = status;
        classLikeStatusValidation(firDeclarationStatus, firClass);
        return resolveStatus(firClass, firDeclarationStatus, containingClass, null, isLocal, CollectionsKt.emptyList());
    }

    public final FirResolvedDeclarationStatus resolveStatus(FirField field, FirClass containingClass, boolean isLocal) {
        field.getClass();
        return resolveStatus(field, field.getStatus(), containingClass, null, isLocal, CollectionsKt.emptyList());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final FirResolvedDeclarationStatus resolveStatus(FirDeclaration declaration, FirClass containingClass, FirProperty containingProperty, boolean isLocal) throws KotlinIllegalArgumentExceptionWithAttachments {
        declaration.getClass();
        if (declaration instanceof FirProperty) {
            return resolveStatus$default(this, (FirProperty) declaration, containingClass, isLocal, (List) null, 8, (Object) null);
        }
        if (declaration instanceof FirNamedFunction) {
            return resolveStatus$default(this, (FirNamedFunction) declaration, containingClass, isLocal, (List) null, 8, (Object) null);
        }
        if (declaration instanceof FirPropertyAccessor) {
            return resolveStatus$default(this, (FirPropertyAccessor) declaration, containingClass, containingProperty, isLocal, null, 16, null);
        }
        if (declaration instanceof FirRegularClass) {
            return resolveStatus((FirClass) declaration, containingClass, isLocal);
        }
        if (declaration instanceof FirTypeAlias) {
            return resolveStatus((FirTypeAlias) declaration, containingClass, isLocal);
        }
        if (declaration instanceof FirConstructor) {
            return resolveStatus((FirConstructor) declaration, containingClass, isLocal);
        }
        if (declaration instanceof FirField) {
            return resolveStatus((FirField) declaration, containingClass, isLocal);
        }
        if (declaration instanceof FirBackingField) {
            return resolveStatus((FirBackingField) declaration, containingClass, isLocal);
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unsupported declaration type: " + declaration.getClass(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "declaration", declaration);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public final FirResolvedDeclarationStatus resolveStatus(FirNamedFunction function, FirClass containingClass, boolean isLocal, List<? extends FirResolvedDeclarationStatus> overriddenStatuses) {
        FirDeclarationStatus status;
        List<? extends FirResolvedDeclarationStatus> list;
        function.getClass();
        if (this.extensionStatusTransformers.isEmpty()) {
            status = function.getStatus();
        } else {
            List<FirStatusTransformerExtension> list2 = this.extensionStatusTransformers;
            FirDeclarationStatus status2 = function.getStatus();
            for (FirStatusTransformerExtension firStatusTransformerExtension : list2) {
                if (firStatusTransformerExtension.needTransformStatus(function)) {
                    status2 = firStatusTransformerExtension.transformStatus(status2, function, containingClass != null ? containingClass.getSymbol() : null, isLocal);
                }
            }
            status = status2;
        }
        if (overriddenStatuses == null) {
            List<FirNamedFunction> overriddenFunctions = getOverriddenFunctions(function, containingClass);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenFunctions, 10));
            Iterator<T> it = overriddenFunctions.iterator();
            while (it.hasNext()) {
                FirDeclarationStatus status3 = ((FirNamedFunction) it.next()).getStatus();
                status3.getClass();
                arrayList.add((FirResolvedDeclarationStatus) status3);
            }
            list = arrayList;
        } else {
            list = overriddenStatuses;
        }
        return resolveStatus(function, status, containingClass, null, isLocal, list);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final FirResolvedDeclarationStatus resolveStatus(FirTypeAlias typeAlias, FirClass containingClass, boolean isLocal) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirDeclarationStatus status;
        typeAlias.getClass();
        if (this.extensionStatusTransformers.isEmpty()) {
            status = typeAlias.getStatus();
        } else {
            List<FirStatusTransformerExtension> list = this.extensionStatusTransformers;
            FirDeclarationStatus status2 = typeAlias.getStatus();
            for (FirStatusTransformerExtension firStatusTransformerExtension : list) {
                if (firStatusTransformerExtension.needTransformStatus(typeAlias)) {
                    status2 = firStatusTransformerExtension.transformStatus(status2, typeAlias, containingClass != null ? containingClass.getSymbol() : null, isLocal);
                }
            }
            status = status2;
        }
        classLikeStatusValidation(status, typeAlias);
        return resolveStatus(typeAlias, status, containingClass, null, isLocal, CollectionsKt.emptyList());
    }

    public final FirResolvedDeclarationStatus resolveStatus(FirPropertyAccessor propertyAccessor, FirClass containingClass, FirProperty containingProperty, boolean isLocal, List<? extends FirResolvedDeclarationStatus> overriddenStatuses) {
        FirDeclarationStatus status;
        FirPropertyAccessor firPropertyAccessor;
        FirProperty firProperty;
        boolean z;
        propertyAccessor.getClass();
        overriddenStatuses.getClass();
        if (this.extensionStatusTransformers.isEmpty()) {
            status = propertyAccessor.getStatus();
        } else {
            List<FirStatusTransformerExtension> list = this.extensionStatusTransformers;
            FirDeclarationStatus status2 = propertyAccessor.getStatus();
            status = status2;
            for (FirStatusTransformerExtension firStatusTransformerExtension : list) {
                if (firStatusTransformerExtension.needTransformStatus(propertyAccessor)) {
                    FirPropertyAccessor firPropertyAccessor2 = propertyAccessor;
                    FirProperty firProperty2 = containingProperty;
                    boolean z2 = isLocal;
                    FirDeclarationStatus firDeclarationStatusTransformStatus = firStatusTransformerExtension.transformStatus(status, firPropertyAccessor2, containingClass != null ? containingClass.getSymbol() : null, firProperty2, z2);
                    firPropertyAccessor = firPropertyAccessor2;
                    firProperty = firProperty2;
                    z = z2;
                    status = firDeclarationStatusTransformStatus;
                } else {
                    firPropertyAccessor = propertyAccessor;
                    firProperty = containingProperty;
                    z = isLocal;
                }
                propertyAccessor = firPropertyAccessor;
                containingProperty = firProperty;
                isLocal = z;
            }
        }
        return resolveStatus(propertyAccessor, status, containingClass, containingProperty, isLocal, overriddenStatuses);
    }

    public final FirResolvedDeclarationStatus resolveStatus(FirConstructor constructor, FirClass containingClass, boolean isLocal) {
        FirDeclarationStatus status;
        constructor.getClass();
        if (this.extensionStatusTransformers.isEmpty()) {
            status = constructor.getStatus();
        } else {
            List<FirStatusTransformerExtension> list = this.extensionStatusTransformers;
            FirDeclarationStatus status2 = constructor.getStatus();
            for (FirStatusTransformerExtension firStatusTransformerExtension : list) {
                if (firStatusTransformerExtension.needTransformStatus(constructor)) {
                    status2 = firStatusTransformerExtension.transformStatus(status2, constructor, containingClass != null ? containingClass.getSymbol() : null, isLocal);
                }
            }
            status = status2;
        }
        return resolveStatus(constructor, status, containingClass, null, isLocal, CollectionsKt.emptyList());
    }

    private final FirResolvedDeclarationStatus resolveStatus(FirBackingField backingField, FirClass containingClass, boolean isLocal) {
        FirDeclarationStatus status;
        if (this.extensionStatusTransformers.isEmpty()) {
            status = backingField.getStatus();
        } else {
            List<FirStatusTransformerExtension> list = this.extensionStatusTransformers;
            FirDeclarationStatus status2 = backingField.getStatus();
            for (FirStatusTransformerExtension firStatusTransformerExtension : list) {
                if (firStatusTransformerExtension.needTransformStatus(backingField)) {
                    status2 = firStatusTransformerExtension.transformStatus(status2, backingField, containingClass != null ? containingClass.getSymbol() : null, isLocal);
                }
            }
            status = status2;
        }
        return resolveStatus(backingField, status, containingClass, null, isLocal, CollectionsKt.emptyList());
    }

    public final FirResolvedDeclarationStatus resolveStatus(FirEnumEntry enumEntry, FirClass containingClass, boolean isLocal) {
        FirDeclarationStatus status;
        enumEntry.getClass();
        if (this.extensionStatusTransformers.isEmpty()) {
            status = enumEntry.getStatus();
        } else {
            List<FirStatusTransformerExtension> list = this.extensionStatusTransformers;
            FirDeclarationStatus status2 = enumEntry.getStatus();
            for (FirStatusTransformerExtension firStatusTransformerExtension : list) {
                if (firStatusTransformerExtension.needTransformStatus(enumEntry)) {
                    status2 = firStatusTransformerExtension.transformStatus(status2, enumEntry, containingClass != null ? containingClass.getSymbol() : null, isLocal);
                }
            }
            status = status2;
        }
        return resolveStatus(enumEntry, status, containingClass, null, isLocal, CollectionsKt.emptyList());
    }
}
