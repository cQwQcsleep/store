package org.jetbrains.kotlin.fir.plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirEmptyArgumentList;
import org.jetbrains.kotlin.fir.expressions.builder.FirDelegatedConstructorCallBuilder;
import org.jetbrains.kotlin.fir.extensions.FirExtension;
import org.jetbrains.kotlin.fir.plugin.ConstructorBuildingContext;
import org.jetbrains.kotlin.fir.plugin.ConstructorBuildingContextKt;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001aM\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000e\u001a(\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b\u001a\u0014\u0010\u0010\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0013"}, d2 = {"createConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "isPrimary", Argument.Delimiters.none, "generateDelegatedNoArgConstructorCall", "config", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/plugin/ConstructorBuildingContext;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "createDefaultPrivateConstructor", "generateNoArgDelegatingConstructorCall", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:plugin-utils"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstructorBuildingContextKt {
    public static Unit a(ConstructorBuildingContext constructorBuildingContext) {
        constructorBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static Unit c(ConstructorBuildingContext constructorBuildingContext) {
        constructorBuildingContext.getClass();
        constructorBuildingContext.setVisibility(Visibilities.Private.INSTANCE);
        return Unit.INSTANCE;
    }

    public static final FirConstructor createConstructor(FirExtension firExtension, final FirClassSymbol<?> firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, boolean z, boolean z2, Function1<? super ConstructorBuildingContext, Unit> function1) {
        firExtension.getClass();
        firClassSymbol.getClass();
        generatedDeclarationKey.getClass();
        function1.getClass();
        ConstructorBuildingContext constructorBuildingContext = new ConstructorBuildingContext(firExtension.getSession(), generatedDeclarationKey, firClassSymbol, z);
        function1.invoke(constructorBuildingContext);
        constructorBuildingContext.status(new Function1() { // from class: gt2
            public final Object invoke(Object obj) {
                return ConstructorBuildingContextKt.createConstructor$lambda$1$0(firClassSymbol, (FirResolvedDeclarationStatusImpl) obj);
            }
        });
        FirConstructor firConstructorBuild = constructorBuildingContext.build();
        if (z2) {
            generateNoArgDelegatingConstructorCall(firConstructorBuild, firExtension.getSession());
        }
        return firConstructorBuild;
    }

    public static /* synthetic */ FirConstructor createConstructor$default(FirExtension firExtension, FirClassSymbol firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, boolean z, boolean z2, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: ft2
                public final Object invoke(Object obj2) {
                    return ConstructorBuildingContextKt.a((ConstructorBuildingContext) obj2);
                }
            };
        }
        return createConstructor(firExtension, firClassSymbol, generatedDeclarationKey, z, z2, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createConstructor$lambda$1$0(FirClassSymbol firClassSymbol, FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl) {
        firResolvedDeclarationStatusImpl.getClass();
        firResolvedDeclarationStatusImpl.setExpect(firClassSymbol.getRawStatus().isExpect());
        return Unit.INSTANCE;
    }

    public static final FirConstructor createDefaultPrivateConstructor(FirExtension firExtension, FirClassSymbol<?> firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, boolean z) {
        firExtension.getClass();
        firClassSymbol.getClass();
        generatedDeclarationKey.getClass();
        return createConstructor(firExtension, firClassSymbol, generatedDeclarationKey, true, z, new Function1() { // from class: ht2
            public final Object invoke(Object obj) {
                return ConstructorBuildingContextKt.c((ConstructorBuildingContext) obj);
            }
        });
    }

    public static /* synthetic */ FirConstructor createDefaultPrivateConstructor$default(FirExtension firExtension, FirClassSymbol firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return createDefaultPrivateConstructor(firExtension, firClassSymbol, generatedDeclarationKey, z);
    }

    private static final void generateNoArgDelegatingConstructorCall(FirConstructor firConstructor, FirSession firSession) {
        Object obj;
        ConeKotlinType coneType;
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(FirTypeUtilsKt.getConeType(firConstructor.getReturnTypeRef()), firSession);
        if (classSymbol == null) {
            w01.a("Required value was null.");
            return;
        }
        FirDelegatedConstructorCallBuilder firDelegatedConstructorCallBuilder = new FirDelegatedConstructorCallBuilder();
        List<ConeKotlinType> resolvedSuperTypes = classSymbol.getResolvedSuperTypes();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = resolvedSuperTypes.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((ConeKotlinType) next, firSession);
            if ((regularClassSymbol != null ? regularClassSymbol.getClassKind() : null) == ClassKind.CLASS) {
                arrayList.add(next);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            coneType = firSession.getBuiltinTypes().getAnyType().getConeType();
        } else {
            if (size != 1) {
                a11.a("Object ", classSymbol, " has more than one class supertypes: ", arrayList);
                return;
            }
            coneType = (ConeKotlinType) CollectionsKt.first(arrayList);
        }
        firDelegatedConstructorCallBuilder.setConstructedTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneType, null, null, 3, null));
        FirRegularClassSymbol regularClassSymbol2 = ToSymbolUtilsKt.toRegularClassSymbol(coneType, firSession);
        if (regularClassSymbol2 == null) {
            x04.a("Symbol for supertype ", coneType, " not found");
            return;
        }
        for (Object obj2 : FirScopeKt.getDeclaredConstructors(FirDeclaredMemberScopeProviderKt.declaredMemberScope(regularClassSymbol2, firSession, (FirResolvePhase) null))) {
            if (((FirConstructorSymbol) obj2).getValueParameterSymbols().isEmpty()) {
                obj = obj2;
                break;
            }
        }
        FirConstructorSymbol firConstructorSymbol = (FirConstructorSymbol) obj;
        if (firConstructorSymbol == null) {
            x04.a("No arguments constructor for class ", coneType, " not found");
            return;
        }
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder.setName(firConstructorSymbol.getName());
        firResolvedNamedReferenceBuilder.setResolvedSymbol(firConstructorSymbol);
        firDelegatedConstructorCallBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
        firDelegatedConstructorCallBuilder.setArgumentList(FirEmptyArgumentList.INSTANCE);
        firDelegatedConstructorCallBuilder.setThis(false);
        firConstructor.replaceDelegatedConstructor(firDelegatedConstructorCallBuilder.mo288build());
    }
}
