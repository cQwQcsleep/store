package org.jetbrains.kotlin.fir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeSupplier;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCodeFragmentSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.CallableIdKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\u0006\u0012\u0002\b\u00030\u0014\u001a\u000e\u0010\u0015\u001a\u00020\u0016*\u0006\u0012\u0002\b\u00030\u0014\u001a<\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\b\b\u0002\u0010!\u001a\u00020\"\u001a6\u0010#\u001a\u0004\u0018\u00010\u0019*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"H\u0002\u001a\u0016\u0010#\u001a\u0004\u0018\u00010\u0019*\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0002H\u0002\u001aL\u0010%\u001a\u00020\u0016*\u00020\r2\n\u0010&\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010'\u001a\u00020(2\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010)\u001a\u00020\u0016\u001a8\u0010*\u001a\u00020\u0016*\u00020\r2\n\u0010&\u001a\u0006\u0012\u0002\b\u00030+2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010'\u001a\u00020(2\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u001f\u001a\u0012\u0010,\u001a\u00020\u0016*\u00020-2\u0006\u0010.\u001a\u00020/\u001a\u001a\u0010,\u001a\u00020\u0016*\u0006\u0012\u0002\b\u00030\u00142\n\u00100\u001a\u0006\u0012\u0002\b\u000301\u001a\"\u0010,\u001a\u00020\u0016*\u0006\u0012\u0002\b\u00030\u00142\n\u00102\u001a\u0006\u0012\u0002\b\u0003012\u0006\u00103\u001a\u000204\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"!\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\t\u0010\n\"\u001f\u0010\f\u001a\u00020\r*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u00065"}, d2 = {"moduleVisibilityChecker", "Lorg/jetbrains/kotlin/fir/FirModuleVisibilityChecker;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getModuleVisibilityChecker", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirModuleVisibilityChecker;", "moduleVisibilityChecker$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "privateVisibleFromDifferentModulesExtension", "Lorg/jetbrains/kotlin/fir/FirPrivateVisibleFromDifferentModuleExtension;", "getPrivateVisibleFromDifferentModulesExtension", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirPrivateVisibleFromDifferentModuleExtension;", "privateVisibleFromDifferentModulesExtension$delegate", "visibilityChecker", "Lorg/jetbrains/kotlin/fir/FirVisibilityChecker;", "getVisibilityChecker", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirVisibilityChecker;", "visibilityChecker$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "getOwnerLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isVariableOrNamedFunction", Argument.Delimiters.none, "parentDeclarationSequence", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "session", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "containingNonLocalClass", "containingUseSiteDeclarations", "isVisible", "symbol", "useSiteFileSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "skipCheckForContainingClassVisibility", "isClassLikeVisible", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "isVisibleInClass", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "parentClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "parentClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "classSymbol", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirVisibilityCheckerKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirVisibilityCheckerKt.class, "moduleVisibilityChecker", "getModuleVisibilityChecker(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirModuleVisibilityChecker;", 1), new PropertyReference1Impl<>(FirVisibilityCheckerKt.class, "privateVisibleFromDifferentModulesExtension", "getPrivateVisibleFromDifferentModulesExtension(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirPrivateVisibleFromDifferentModuleExtension;", 1), new PropertyReference1Impl<>(FirVisibilityCheckerKt.class, "visibilityChecker", "getVisibilityChecker(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirVisibilityChecker;", 1)};
    private static final NullableArrayMapAccessor moduleVisibilityChecker$delegate;
    private static final NullableArrayMapAccessor privateVisibleFromDifferentModulesExtension$delegate;
    private static final ArrayMapAccessor visibilityChecker$delegate;

    static {
        FirSession.Companion companion = FirSession.INSTANCE;
        moduleVisibilityChecker$delegate = companion.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirModuleVisibilityChecker.class));
        privateVisibleFromDifferentModulesExtension$delegate = companion.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirPrivateVisibleFromDifferentModuleExtension.class));
        visibilityChecker$delegate = companion.generateAccessor(Reflection.getOrCreateKotlinClass(FirVisibilityChecker.class), FirVisibilityChecker.Default.INSTANCE);
    }

    public static FirClassLikeDeclaration a(FirSession firSession, FirClassLikeDeclaration firClassLikeDeclaration) {
        firClassLikeDeclaration.getClass();
        return containingNonLocalClass(firClassLikeDeclaration, firSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirClassLikeDeclaration containingNonLocalClass(FirMemberDeclaration firMemberDeclaration, FirSession firSession, FirExpression firExpression, List<? extends FirDeclaration> list, SupertypeSupplier supertypeSupplier) {
        FirSession firSession2;
        SupertypeSupplier supertypeSupplier2;
        FirClass firClass;
        FirClassLikeSymbol<?> symbol;
        ConeClassLikeType coneClassLikeTypeDispatchReceiverClassTypeOrNull;
        ConeClassLikeLookupTag coneClassLikeLookupTagFindClassRepresentation;
        FirClassLikeSymbol<?> symbol2;
        FirClassLikeDeclaration firClassLikeDeclaration;
        FirClass firClass2 = null;
        if (!(firMemberDeclaration instanceof FirCallableDeclaration)) {
            if (firMemberDeclaration instanceof FirClassLikeDeclaration) {
                return containingNonLocalClass((FirClassLikeDeclaration) firMemberDeclaration, firSession);
            }
            bu8.a();
            return null;
        }
        if (firExpression != null && (coneClassLikeTypeDispatchReceiverClassTypeOrNull = ClassMembersKt.dispatchReceiverClassTypeOrNull((FirCallableDeclaration) firMemberDeclaration)) != null && (coneClassLikeLookupTagFindClassRepresentation = LookupTagUtilsKt.findClassRepresentation(FirTypeUtilsKt.getResolvedType(firExpression), coneClassLikeTypeDispatchReceiverClassTypeOrNull, firSession)) != null && (symbol2 = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTagFindClassRepresentation, firSession)) != null && (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol2.getFir()) != null) {
            return firClassLikeDeclaration;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableDeclaration) firMemberDeclaration);
        FirClassLikeDeclaration firClassLikeDeclaration2 = (coneClassLikeLookupTagContainingClassLookupTag == null || (symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession)) == null) ? null : (FirClassLikeDeclaration) symbol.getFir();
        if (firMemberDeclaration.getStatus().isStatic() && firClassLikeDeclaration2 != null) {
            for (FirDeclaration firDeclaration : list) {
                if (firDeclaration instanceof FirClass) {
                    firSession2 = firSession;
                    supertypeSupplier2 = supertypeSupplier;
                    if (!SupertypeUtilsKt.isSubclassOf$default((FirClass) firDeclaration, coneClassLikeLookupTagContainingClassLookupTag, firSession2, false, supertypeSupplier2, false, 16, null)) {
                        firDeclaration = null;
                    }
                    firClass = (FirClass) firDeclaration;
                } else {
                    firSession2 = firSession;
                    supertypeSupplier2 = supertypeSupplier;
                    firClass = null;
                }
                if (firClass != null) {
                    firClass2 = firClass;
                    break;
                }
                firSession = firSession2;
                supertypeSupplier = supertypeSupplier2;
            }
            if (firClass2 != null) {
                return firClass2;
            }
        }
        return firClassLikeDeclaration2;
    }

    public static final FirModuleVisibilityChecker getModuleVisibilityChecker(FirSession firSession) {
        firSession.getClass();
        return (FirModuleVisibilityChecker) moduleVisibilityChecker$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeClassLikeLookupTag getOwnerLookupTag(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirBackingFieldSymbol) {
            return getOwnerLookupTag(((FirBackingField) ((FirBackingFieldSymbol) firBasedSymbol).getFir()).getPropertySymbol());
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            return ClassMembersKt.getContainingClassLookupTag((FirClassLikeSymbol) firBasedSymbol);
        }
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firBasedSymbol);
        }
        if ((firBasedSymbol instanceof FirScriptSymbol) || (firBasedSymbol instanceof FirCodeFragmentSymbol)) {
            return null;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unsupported owner search for " + firBasedSymbol.getFir().getClass(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "ownerDeclaration", firBasedSymbol.getFir());
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirPrivateVisibleFromDifferentModuleExtension getPrivateVisibleFromDifferentModulesExtension(FirSession firSession) {
        return (FirPrivateVisibleFromDifferentModuleExtension) privateVisibleFromDifferentModulesExtension$delegate.getValue(firSession, $$delegatedProperties[1]);
    }

    public static final FirVisibilityChecker getVisibilityChecker(FirSession firSession) {
        firSession.getClass();
        return (FirVisibilityChecker) visibilityChecker$delegate.getValue(firSession, $$delegatedProperties[2]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isClassLikeVisible(FirVisibilityChecker firVisibilityChecker, FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession, FirFileSymbol firFileSymbol, List<? extends FirBasedSymbol<?>> list) throws KotlinIllegalArgumentExceptionWithAttachments {
        firVisibilityChecker.getClass();
        firClassLikeSymbol.getClass();
        firSession.getClass();
        firFileSymbol.getClass();
        list.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firClassLikeSymbol, FirResolvePhase.STATUS);
        FirClassLikeDeclaration firClassLikeDeclaration = (FirClassLikeDeclaration) firClassLikeSymbol.getFir();
        FirFile fir = firFileSymbol.getFir();
        List<? extends FirBasedSymbol<?>> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirBasedSymbol) it.next()).getFir());
        }
        return firVisibilityChecker.isClassLikeVisible(firClassLikeDeclaration, firSession, fir, arrayList);
    }

    public static final boolean isVariableOrNamedFunction(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        return (firBasedSymbol instanceof FirVariableSymbol) || (firBasedSymbol instanceof FirNamedFunctionSymbol) || (firBasedSymbol instanceof FirPropertyAccessorSymbol);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final boolean isVisible(FirVisibilityChecker firVisibilityChecker, FirBasedSymbol<?> firBasedSymbol, FirSession firSession, FirFileSymbol firFileSymbol, List<? extends FirBasedSymbol<?>> list, FirExpression firExpression, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        firVisibilityChecker.getClass();
        firBasedSymbol.getClass();
        firSession.getClass();
        firFileSymbol.getClass();
        list.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firBasedSymbol, FirResolvePhase.STATUS);
        FirDeclaration fir = firBasedSymbol.getFir();
        FirMemberDeclaration firMemberDeclaration = fir instanceof FirMemberDeclaration ? (FirMemberDeclaration) fir : null;
        if (firMemberDeclaration == null) {
            w04.a("Not a member declaration: ", firBasedSymbol);
            return false;
        }
        FirFile fir2 = firFileSymbol.getFir();
        List<? extends FirBasedSymbol<?>> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirBasedSymbol) it.next()).getFir());
        }
        return FirVisibilityChecker.isVisible$default(firVisibilityChecker, firMemberDeclaration, firSession, fir2, arrayList, firExpression, false, null, z, null, 352, null);
    }

    public static /* synthetic */ boolean isVisible$default(FirVisibilityChecker firVisibilityChecker, FirBasedSymbol firBasedSymbol, FirSession firSession, FirFileSymbol firFileSymbol, List list, FirExpression firExpression, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            z = false;
        }
        return isVisible(firVisibilityChecker, firBasedSymbol, firSession, firFileSymbol, list, firExpression, z);
    }

    public static final boolean isVisibleInClass(FirBasedSymbol<?> firBasedSymbol, FirClassSymbol<?> firClassSymbol, FirDeclarationStatus firDeclarationStatus) {
        FqName packageFqName;
        firBasedSymbol.getClass();
        firClassSymbol.getClass();
        firDeclarationStatus.getClass();
        FqName packageFqName2 = firClassSymbol.getClassId().getPackageFqName();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            packageFqName = CallableIdKt.getPackageName(((FirCallableSymbol) firBasedSymbol).getCallableId());
        } else {
            if (!(firBasedSymbol instanceof FirClassLikeSymbol)) {
                return true;
            }
            packageFqName = ((FirClassLikeSymbol) firBasedSymbol).getClassId().getPackageFqName();
        }
        Visibility visibility = firDeclarationStatus.getVisibility();
        if (Intrinsics.areEqual(visibility, Visibilities.Private.INSTANCE) || !visibility.visibleFromPackage(packageFqName2, packageFqName)) {
            return false;
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Internal.INSTANCE)) {
            return FirModuleDataKt.canSeeInternalsOf(firClassSymbol.getModuleData(), firBasedSymbol.getModuleData());
        }
        return true;
    }

    public static final Sequence<FirClassLikeDeclaration> parentDeclarationSequence(FirMemberDeclaration firMemberDeclaration, final FirSession firSession, FirExpression firExpression, List<? extends FirDeclaration> list, SupertypeSupplier supertypeSupplier) {
        firMemberDeclaration.getClass();
        firSession.getClass();
        list.getClass();
        supertypeSupplier.getClass();
        FirClassLikeDeclaration firClassLikeDeclarationContainingNonLocalClass = containingNonLocalClass(firMemberDeclaration, firSession, firExpression, list, supertypeSupplier);
        if (firClassLikeDeclarationContainingNonLocalClass == null) {
            return null;
        }
        return SequencesKt.generateSequence(firClassLikeDeclarationContainingNonLocalClass, new Function1() { // from class: og5
            public final Object invoke(Object obj) {
                return FirVisibilityCheckerKt.a(firSession, (FirClassLikeDeclaration) obj);
            }
        });
    }

    public static /* synthetic */ Sequence parentDeclarationSequence$default(FirMemberDeclaration firMemberDeclaration, FirSession firSession, FirExpression firExpression, List list, SupertypeSupplier supertypeSupplier, int i, Object obj) {
        if ((i & 8) != 0) {
            supertypeSupplier = SupertypeSupplier.Default.INSTANCE;
        }
        return parentDeclarationSequence(firMemberDeclaration, firSession, firExpression, list, supertypeSupplier);
    }

    public static final boolean isVisibleInClass(FirBasedSymbol<?> firBasedSymbol, FirClassSymbol<?> firClassSymbol) {
        FirResolvedDeclarationStatus resolvedStatus;
        firBasedSymbol.getClass();
        firClassSymbol.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            resolvedStatus = ((FirCallableSymbol) firBasedSymbol).getResolvedStatus();
        } else {
            if (!(firBasedSymbol instanceof FirClassLikeSymbol)) {
                return true;
            }
            resolvedStatus = ((FirClassLikeSymbol) firBasedSymbol).getResolvedStatus();
        }
        return isVisibleInClass(firBasedSymbol, firClassSymbol, resolvedStatus);
    }

    public static final boolean isVisibleInClass(FirCallableDeclaration firCallableDeclaration, FirClass firClass) {
        firCallableDeclaration.getClass();
        firClass.getClass();
        return isVisibleInClass(firCallableDeclaration.getSymbol(), firClass.getSymbol(), firCallableDeclaration.getSymbol().getResolvedStatus());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirClassLikeDeclaration containingNonLocalClass(FirClassLikeDeclaration firClassLikeDeclaration, FirSession firSession) {
        ClassId outerClassId;
        FirClassLikeSymbol<?> classLikeSymbolByClassId;
        if (firClassLikeDeclaration instanceof FirClass) {
            if (((FirClass) firClassLikeDeclaration).getIsLocal() || (outerClassId = FirDeclarationUtilKt.getClassId(firClassLikeDeclaration).getOuterClassId()) == null || (classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(firSession).getClassLikeSymbolByClassId(outerClassId)) == null) {
                return null;
            }
            return (FirClassLikeDeclaration) classLikeSymbolByClassId.getFir();
        }
        if (firClassLikeDeclaration instanceof FirTypeAlias) {
            return null;
        }
        bu8.a();
        return null;
    }
}
