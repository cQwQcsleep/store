package org.jetbrains.kotlin.fir.backend.p002native;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.p002native.interop.FirObjCInteropKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.impl.FirOverrideUtilsKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirStandardOverrideChecker;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.native.interop.ObjCMethodInfo;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000fH\u0016J$\u0010\u0010\u001a\u00020\u00112\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J#\u0010\u0018\u001a\u0004\u0018\u00010\t*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\u0019J\u0018\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/native/FirNativeOverrideChecker;", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "standardOverrideChecker", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirStandardOverrideChecker;", "isOverriddenFunction", Argument.Delimiters.none, "overrideCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "baseDeclaration", "isOverriddenProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "chooseIntersectionVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "overrides", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "dispatchClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "isObjCClassPropertyOrAccessor", "isPlatformOverriddenFunction", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)Ljava/lang/Boolean;", "parameterNamesMatch", "first", "second", "org.jetbrains.kotlin:fir-native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeOverrideChecker implements FirOverrideChecker {
    private final FirSession session;
    private final FirStandardOverrideChecker standardOverrideChecker;

    public FirNativeOverrideChecker(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.standardOverrideChecker = new FirStandardOverrideChecker(firSession);
    }

    private final boolean isObjCClassPropertyOrAccessor(FirCallableSymbol<?> firCallableSymbol, FirSession firSession) {
        FirClassSymbol<?> classSymbol;
        if ((firCallableSymbol instanceof FirPropertySymbol) || (firCallableSymbol instanceof FirPropertyAccessorSymbol)) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableSymbol);
            if ((coneClassLikeLookupTagContainingClassLookupTag == null || (classSymbol = ToSymbolUtilsKt.toClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession)) == null) ? false : FirObjCInteropKt.isObjCClass(classSymbol, firSession)) {
                return true;
            }
        }
        return false;
    }

    private final Boolean isPlatformOverriddenFunction(FirNamedFunction firNamedFunction, FirSession firSession, FirNamedFunction firNamedFunction2) {
        ObjCMethodInfo objCMethodInfoDecodeObjCMethodAnnotation;
        if (!Intrinsics.areEqual(firNamedFunction.getName(), firNamedFunction2.getName()) || (objCMethodInfoDecodeObjCMethodAnnotation = FirObjCInteropKt.decodeObjCMethodAnnotation(firNamedFunction2.getSymbol(), firSession)) == null) {
            return null;
        }
        ObjCMethodInfo objCMethodInfoDecodeObjCMethodAnnotation2 = FirObjCInteropKt.decodeObjCMethodAnnotation(firNamedFunction.getSymbol(), firSession);
        if (objCMethodInfoDecodeObjCMethodAnnotation2 != null) {
            return Boolean.valueOf(Intrinsics.areEqual(objCMethodInfoDecodeObjCMethodAnnotation.getSelector(), objCMethodInfoDecodeObjCMethodAnnotation2.getSelector()));
        }
        if (parameterNamesMatch(firNamedFunction, firNamedFunction2)) {
            return null;
        }
        return Boolean.FALSE;
    }

    private final boolean parameterNamesMatch(FirNamedFunction first, FirNamedFunction second) {
        if (first.getValueParameters().size() != second.getValueParameters().size()) {
            return false;
        }
        int i = 0;
        for (Object obj : first.getValueParameters()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirValueParameter firValueParameter = (FirValueParameter) obj;
            if (i > 0 && !Intrinsics.areEqual(firValueParameter.getName(), second.getValueParameters().get(i).getName())) {
                return false;
            }
            i = i2;
        }
        return true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public Visibility chooseIntersectionVisibility(Collection<? extends FirCallableSymbol<?>> overrides, FirRegularClassSymbol dispatchClassSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        Visibility visibility;
        overrides.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : overrides) {
            FirCallableSymbol<?> firCallableSymbol = (FirCallableSymbol) obj;
            if (!FirOverrideUtilsKt.isAbstractAccordingToRawStatus(firCallableSymbol) && !isObjCClassPropertyOrAccessor(firCallableSymbol, this.session) && !Intrinsics.areEqual(firCallableSymbol.getCallableId(), StandardClassIds.Callables.INSTANCE.getClone())) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            visibility = Visibilities.Private.INSTANCE;
            Iterator<? extends FirCallableSymbol<?>> it = overrides.iterator();
            while (it.hasNext()) {
                Object fir = it.next().getFir();
                fir.getClass();
                Visibility visibility2 = ((FirMemberDeclaration) fir).getStatus().getVisibility();
                Integer numCompare = Visibilities.INSTANCE.compare(visibility2, visibility);
                if (numCompare == null) {
                    visibility = null;
                    break;
                }
                if (numCompare.intValue() > 0) {
                    visibility = visibility2;
                }
            }
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                linkedHashSet.add(((FirCallableSymbol) it2.next()).getRawStatus().getVisibility());
            }
            visibility = (Visibility) CollectionsKt.singleOrNull(linkedHashSet);
        }
        return visibility == null ? Visibilities.Unknown.INSTANCE : visibility;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public boolean isOverriddenFunction(FirNamedFunction overrideCandidate, FirNamedFunction baseDeclaration) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        Boolean boolIsPlatformOverriddenFunction = isPlatformOverriddenFunction(overrideCandidate, this.session, baseDeclaration);
        return boolIsPlatformOverriddenFunction != null ? boolIsPlatformOverriddenFunction.booleanValue() : this.standardOverrideChecker.isOverriddenFunction(overrideCandidate, baseDeclaration);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public boolean isOverriddenProperty(FirCallableDeclaration overrideCandidate, FirProperty baseDeclaration) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        return this.standardOverrideChecker.isOverriddenProperty(overrideCandidate, baseDeclaration);
    }
}
