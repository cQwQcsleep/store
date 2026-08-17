package org.jetbrains.kotlin.fir.symbols.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.declarations.DirectDeclarationsAccess;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR(\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\t8FX\u0087\u0004r\u0002\b\u0015¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\u0082\u0001\u0002\u001a\u001b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "C", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "resolvedSuperTypeRefs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "getResolvedSuperTypeRefs", "()Ljava/util/List;", "resolvedSuperTypes", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getResolvedSuperTypes", "declarationSymbols", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getDeclarationSymbols$annotations", "()V", "getDeclarationSymbols", "Lorg/jetbrains/kotlin/fir/declarations/DirectDeclarationsAccess;", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "getClassKind", "()Lorg/jetbrains/kotlin/descriptors/ClassKind;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousObjectSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirClassSymbol<C extends FirClass> extends FirClassLikeSymbol<C> {
    private FirClassSymbol(ClassId classId) {
        super(classId, null);
    }

    @DirectDeclarationsAccess
    public static /* synthetic */ void getDeclarationSymbols$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ClassKind getClassKind() {
        return ((FirClass) getFir()).getClassKind();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<FirBasedSymbol<?>> getDeclarationSymbols() {
        List<FirDeclaration> declarations = ((FirClass) getFir()).getDeclarations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(declarations, 10));
        Iterator<T> it = declarations.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirDeclaration) it.next()).getSymbol());
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<FirResolvedTypeRef> getResolvedSuperTypeRefs() {
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.SUPER_TYPES);
        List<FirTypeRef> superTypeRefs = ((FirClass) getFir()).getSuperTypeRefs();
        superTypeRefs.getClass();
        return superTypeRefs;
    }

    public final List<ConeKotlinType> getResolvedSuperTypes() {
        List<FirResolvedTypeRef> resolvedSuperTypeRefs = getResolvedSuperTypeRefs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedSuperTypeRefs, 10));
        Iterator<T> it = resolvedSuperTypeRefs.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
        }
        return arrayList;
    }

    public /* synthetic */ FirClassSymbol(ClassId classId, DefaultConstructorMarker defaultConstructorMarker) {
        this(classId);
    }
}
