package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.ImplicitReceiverUtilsKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.resolve.FirTowerDataMode;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.ImportingScopesKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirMemberTypeParameterScope;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001e\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\f0\nH\u0016J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00120\nH\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u00102\u001a\u0002032\u0006\u00104\u001a\u0002032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u00105\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u00106\u001a\u0002072\u0006\u00108\u001a\u0002072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u00069"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirDeclarationsResolveTransformerForAnnotationArguments;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;)V", "withFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "action", "Lkotlin/Function0;", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "regularClass", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "forRegularClassBody", "withScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "script", "transformAnonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "anonymousInitializer", "transformNamedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "namedFunction", "transformConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "constructor", "transformErrorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "errorPrimaryConstructor", "transformValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "valueParameter", "transformProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "property", "transformPropertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "propertyAccessor", "transformDeclarationStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "declarationStatus", "transformEnumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "enumEntry", "transformField", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "field", "transformBackingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "backingField", "transformScript", "transformReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "replSnippet", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirDeclarationsResolveTransformerForAnnotationArguments extends FirDeclarationsResolveTransformer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDeclarationsResolveTransformerForAnnotationArguments(FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher) {
        super(firAbstractBodyResolveTransformerDispatcher);
        firAbstractBodyResolveTransformerDispatcher.getClass();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirRegularClass forRegularClassBody$lambda$0$0(Function0 function0) {
        return (FirRegularClass) function0.invoke();
    }

    public static FirScript q(Function0 function0) {
        return (FirScript) function0.invoke();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer
    public FirRegularClass forRegularClassBody(FirRegularClass regularClass, final Function0<? extends FirRegularClass> action) {
        regularClass.getClass();
        action.getClass();
        BodyResolveContext context = getTransformer().getContext();
        context.getContainingClassDeclarations().add(regularClass);
        try {
            return (FirRegularClass) getTransformer().getContext().forRegularClassBody(regularClass, getTransformer().getComponents(), new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.plugin.c
                public final Object invoke() {
                    return FirDeclarationsResolveTransformerForAnnotationArguments.forRegularClassBody$lambda$0$0(action);
                }
            });
        } finally {
            context.getContainingClassDeclarations().removeLast();
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirAnonymousInitializer transformAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, ResolutionMode data) {
        anonymousInitializer.getClass();
        data.getClass();
        BodyResolveContext context = getTransformer().getContext();
        context.getContainers().add(anonymousInitializer);
        try {
            anonymousInitializer.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) this, ResolutionMode.ContextIndependent.INSTANCE);
            return anonymousInitializer;
        } finally {
            context.getContainers().removeLast();
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirBackingField transformBackingField(FirBackingField backingField, ResolutionMode data) {
        backingField.getClass();
        data.getClass();
        backingField.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data);
        return backingField;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirConstructor transformConstructor(FirConstructor constructor, ResolutionMode data) {
        constructor.getClass();
        data.getClass();
        FirDeclaration containerIfAny = getTransformer().getContext().getContainerIfAny();
        FirRegularClass firRegularClass = containerIfAny instanceof FirRegularClass ? (FirRegularClass) containerIfAny : null;
        BodyResolveContext context = getTransformer().getContext();
        FirTowerDataMode towerDataMode = FirTowerDataMode.CONSTRUCTOR_HEADER;
        FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = context.getTowerDataMode();
            } catch (Throwable th) {
                context.setTowerDataMode(towerDataMode2);
                throw th;
            }
        }
        context.setTowerDataMode(towerDataMode);
        context.getContainers().add(constructor);
        try {
            Iterator<T> it = constructor.transformTypeParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReceiverParameter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data).getContextParameters().iterator();
            while (it.hasNext()) {
                FirTransformerUtilKt.transformSingle((FirValueParameter) it.next(), getTransformer(), data);
            }
            BodyResolveContext context2 = getTransformer().getContext();
            FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components = getTransformer().getComponents();
            if (context2.getTowerDataMode() != FirTowerDataMode.CONSTRUCTOR_HEADER) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            FirTowerDataContext towerDataContext = context2.getTowerDataContext();
            try {
                if (!constructor.getIsPrimary()) {
                    context2.addInaccessibleImplicitReceiverValue(firRegularClass, components);
                }
                context2.addLocalScope(context2.buildConstructorParametersScope(constructor, components.getSession()));
                constructor.transformValueParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data);
                context2.replaceTowerDataContext(towerDataContext);
                context.getContainers().removeLast();
                context.setTowerDataMode(towerDataMode2);
                return constructor;
            } catch (Throwable th2) {
                context2.replaceTowerDataContext(towerDataContext);
                throw th2;
            }
        } catch (Throwable th3) {
            context.getContainers().removeLast();
            throw th3;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDeclarationStatus transformDeclarationStatus(FirDeclarationStatus declarationStatus, ResolutionMode data) {
        declarationStatus.getClass();
        data.getClass();
        return declarationStatus;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirEnumEntry transformEnumEntry(FirEnumEntry enumEntry, ResolutionMode data) {
        enumEntry.getClass();
        data.getClass();
        BodyResolveContext context = getTransformer().getContext();
        FirTowerDataMode towerDataMode = FirTowerDataMode.ENUM_ENTRY;
        FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = context.getTowerDataMode();
            } catch (Throwable th) {
                context.setTowerDataMode(towerDataMode2);
                throw th;
            }
        }
        context.setTowerDataMode(towerDataMode);
        context.getContainers().add(enumEntry);
        try {
            enumEntry.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReceiverParameter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformTypeParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data);
            context.getContainers().removeLast();
            context.setTowerDataMode(towerDataMode2);
            return enumEntry;
        } catch (Throwable th2) {
            context.getContainers().removeLast();
            throw th2;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirErrorPrimaryConstructor transformErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, ResolutionMode data) {
        errorPrimaryConstructor.getClass();
        data.getClass();
        FirConstructor firConstructorTransformConstructor = transformConstructor((FirConstructor) errorPrimaryConstructor, data);
        firConstructorTransformConstructor.getClass();
        return (FirErrorPrimaryConstructor) firConstructorTransformConstructor;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirField transformField(FirField field, ResolutionMode data) {
        field.getClass();
        data.getClass();
        BodyResolveContext context = getTransformer().getContext();
        FirTowerDataMode towerDataMode = FirTowerDataMode.CONSTRUCTOR_HEADER;
        FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = context.getTowerDataMode();
            } catch (Throwable th) {
                context.setTowerDataMode(towerDataMode2);
                throw th;
            }
        }
        context.setTowerDataMode(towerDataMode);
        context.getContainers().add(field);
        try {
            FirTowerDataContext towerDataContext = context.getTowerDataContext();
            try {
                FirLocalScope primaryConstructorAllParametersScope = context.getPrimaryConstructorAllParametersScope();
                if (primaryConstructorAllParametersScope != null) {
                    context.addLocalScope(primaryConstructorAllParametersScope);
                }
                field.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformBackingField((FirTransformer<? super ResolutionMode>) getTransformer(), data);
                context.replaceTowerDataContext(towerDataContext);
                context.getContainers().removeLast();
                context.setTowerDataMode(towerDataMode2);
                return field;
            } catch (Throwable th2) {
                context.replaceTowerDataContext(towerDataContext);
                throw th2;
            }
        } catch (Throwable th3) {
            context.getContainers().removeLast();
            throw th3;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirNamedFunction transformNamedFunction(FirNamedFunction namedFunction, ResolutionMode data) {
        FirFunction publicApiInlineFunction;
        namedFunction.getClass();
        data.getClass();
        BodyResolveContext context = getTransformer().getContext();
        FirSession session = getSession();
        if (!(context.getContainerIfAny() instanceof FirClass)) {
            context.storeFunction(namedFunction, session);
        }
        FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(namedFunction) ? FirTowerDataMode.COMPANION_BLOCK : null;
        FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = context.getTowerDataMode();
            } catch (Throwable th) {
                context.setTowerDataMode(towerDataMode2);
                throw th;
            }
        }
        context.setTowerDataMode(towerDataMode);
        if (namedFunction.getTypeParameters().isEmpty()) {
            publicApiInlineFunction = context.isPublicInline(namedFunction) ? namedFunction : null;
            if (publicApiInlineFunction == null) {
                publicApiInlineFunction = context.getPublicApiInlineFunction();
            }
            FirFunction publicApiInlineFunction2 = context.getPublicApiInlineFunction();
            try {
                context.setPublicApiInlineFunction(publicApiInlineFunction);
                context.getContainers().add(namedFunction);
                try {
                    Iterator<T> it = namedFunction.transformTypeParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReceiverParameter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformValueParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).getContextParameters().iterator();
                    while (it.hasNext()) {
                        FirTransformerUtilKt.transformSingle((FirValueParameter) it.next(), getTransformer(), data);
                    }
                    Unit unit = Unit.INSTANCE;
                    context.getContainers().removeLast();
                    context.setPublicApiInlineFunction(publicApiInlineFunction2);
                } catch (Throwable th2) {
                    context.getContainers().removeLast();
                    throw th2;
                }
            } catch (Throwable th3) {
                context.setPublicApiInlineFunction(publicApiInlineFunction2);
                throw th3;
            }
        } else {
            FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(namedFunction);
            FirTowerDataContext towerDataContext = context.getTowerDataContext();
            try {
                context.addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                publicApiInlineFunction = context.isPublicInline(namedFunction) ? namedFunction : null;
                if (publicApiInlineFunction == null) {
                    publicApiInlineFunction = context.getPublicApiInlineFunction();
                }
                FirFunction publicApiInlineFunction3 = context.getPublicApiInlineFunction();
                try {
                    context.setPublicApiInlineFunction(publicApiInlineFunction);
                    context.getContainers().add(namedFunction);
                    try {
                        Iterator<T> it2 = namedFunction.transformTypeParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReceiverParameter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformValueParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).getContextParameters().iterator();
                        while (it2.hasNext()) {
                            FirTransformerUtilKt.transformSingle((FirValueParameter) it2.next(), getTransformer(), data);
                        }
                        Unit unit2 = Unit.INSTANCE;
                        context.getContainers().removeLast();
                        context.setPublicApiInlineFunction(publicApiInlineFunction3);
                        context.replaceTowerDataContext(towerDataContext);
                    } catch (Throwable th4) {
                        context.getContainers().removeLast();
                        throw th4;
                    }
                } catch (Throwable th5) {
                    context.setPublicApiInlineFunction(publicApiInlineFunction3);
                    throw th5;
                }
            } catch (Throwable th6) {
                context.replaceTowerDataContext(towerDataContext);
                throw th6;
            }
        }
        context.setTowerDataMode(towerDataMode2);
        return namedFunction;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirProperty transformProperty(FirProperty property, ResolutionMode data) {
        property.getClass();
        data.getClass();
        BodyResolveContext context = getTransformer().getContext();
        FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(property) ? FirTowerDataMode.COMPANION_BLOCK : null;
        FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = context.getTowerDataMode();
            } catch (Throwable th) {
                context.setTowerDataMode(towerDataMode2);
                throw th;
            }
        }
        context.setTowerDataMode(towerDataMode);
        if (property.getTypeParameters().isEmpty()) {
            context.getContainers().add(property);
            try {
                property.transformTypeParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReceiverParameter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformGetter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformSetter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformBackingField((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformContextParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data);
                context.getContainers().removeLast();
            } catch (Throwable th2) {
                context.getContainers().removeLast();
                throw th2;
            }
        } else {
            FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(property);
            FirTowerDataContext towerDataContext = context.getTowerDataContext();
            try {
                context.addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                context.getContainers().add(property);
                try {
                    property.transformTypeParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReceiverParameter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformGetter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformSetter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformBackingField((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformContextParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data);
                    context.getContainers().removeLast();
                    context.replaceTowerDataContext(towerDataContext);
                } catch (Throwable th3) {
                    context.getContainers().removeLast();
                    throw th3;
                }
            } catch (Throwable th4) {
                context.replaceTowerDataContext(towerDataContext);
                throw th4;
            }
        }
        context.setTowerDataMode(towerDataMode2);
        return property;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirPropertyAccessor transformPropertyAccessor(FirPropertyAccessor propertyAccessor, ResolutionMode data) {
        propertyAccessor.getClass();
        data.getClass();
        propertyAccessor.transformValueParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReceiverParameter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data);
        return propertyAccessor;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirRegularClass transformRegularClass(FirRegularClass regularClass, ResolutionMode data) {
        regularClass.getClass();
        data.getClass();
        BodyResolveContext context = getTransformer().getContext();
        FirTowerDataMode towerDataMode = context.getTowerDataMode();
        try {
            if (!regularClass.getStatus().isInner() && (context.getContainerIfAny() instanceof FirRegularClass)) {
                context.setTowerDataMode(regularClass.getStatus().isCompanion() ? FirTowerDataMode.COMPANION_OBJECT : FirTowerDataMode.NESTED_CLASS);
            }
            context.getContainers().add(regularClass);
            try {
                ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
                regularClass.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) this, contextIndependent);
                regularClass.transformTypeParameters((FirTransformer<? super ResolutionMode.ContextIndependent>) this, contextIndependent);
                regularClass.transformSuperTypeRefs((FirTransformer<? super ResolutionMode.ContextIndependent>) this, contextIndependent);
                Iterator<T> it = regularClass.getContextParameters().iterator();
                while (it.hasNext()) {
                    FirTransformerUtilKt.transformSingle((FirValueParameter) it.next(), this, ResolutionMode.ContextIndependent.INSTANCE);
                }
                Unit unit = Unit.INSTANCE;
                context.getContainers().removeLast();
                context.setTowerDataMode(towerDataMode);
                doTransformRegularClassContent(regularClass, data);
                return regularClass;
            } catch (Throwable th) {
                context.getContainers().removeLast();
                throw th;
            }
        } catch (Throwable th2) {
            context.setTowerDataMode(towerDataMode);
            throw th2;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReplSnippet transformReplSnippet(FirReplSnippet replSnippet, ResolutionMode data) {
        replSnippet.getClass();
        data.getClass();
        return replSnippet;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirScript transformScript(FirScript script, ResolutionMode data) {
        script.getClass();
        data.getClass();
        return script;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirValueParameter transformValueParameter(FirValueParameter valueParameter, ResolutionMode data) {
        valueParameter.getClass();
        data.getClass();
        BodyResolveContext context = getTransformer().getContext();
        context.storeValueParameterIfNeeded(valueParameter, getSession());
        context.getContainers().add(valueParameter);
        try {
            valueParameter.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data);
            return valueParameter;
        } finally {
            context.getContainers().removeLast();
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer
    public FirFile withFile(FirFile file, Function0<? extends FirFile> action) {
        file.getClass();
        action.getClass();
        BodyResolveContext context = getTransformer().getContext();
        FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components = getTransformer().getComponents();
        context.clear();
        context.setFile(file);
        List<FirScope> fileImportsScope = context.getFileImportsScope();
        int size = fileImportsScope.size();
        int i = 0;
        try {
            FirTowerDataContext towerDataContext = context.getTowerDataContext();
            try {
                List listCreateImportingScopes$default = ImportingScopesKt.createImportingScopes$default(file, components.getSession(), components.getScopeSession(), false, 8, null);
                CollectionsKt.addAll(context.getFileImportsScope(), listCreateImportingScopes$default);
                List list = listCreateImportingScopes$default;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(ImplicitReceiverUtilsKt.asTowerDataElement((FirScope) it.next(), false));
                }
                context.addNonLocalTowerDataElements(arrayList);
                context.getContainers().add(file);
                try {
                    FirFile firFile = (FirFile) action.invoke();
                    context.getContainers().removeLast();
                    context.replaceTowerDataContext(towerDataContext);
                    int size2 = fileImportsScope.size() - size;
                    while (i < size2) {
                        fileImportsScope.remove(fileImportsScope.size() - 1);
                        i++;
                    }
                    return firFile;
                } catch (Throwable th) {
                    context.getContainers().removeLast();
                    throw th;
                }
            } catch (Throwable th2) {
                context.replaceTowerDataContext(towerDataContext);
                throw th2;
            }
        } catch (Throwable th3) {
            int size3 = fileImportsScope.size() - size;
            while (i < size3) {
                fileImportsScope.remove(fileImportsScope.size() - 1);
                i++;
            }
            throw th3;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer
    public FirScript withScript(FirScript script, final Function0<? extends FirScript> action) {
        script.getClass();
        action.getClass();
        return (FirScript) getTransformer().getContext().withScript(script, getTransformer().getComponents(), new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.plugin.d
            public final Object invoke() {
                return FirDeclarationsResolveTransformerForAnnotationArguments.q(action);
            }
        });
    }
}
