package org.jetbrains.kotlin.fir.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.impl.FirLazyDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PlatformSupertypeUpdater;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/JvmSupertypeUpdater;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/PlatformSupertypeUpdater;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "jvmRecordUpdater", "Lorg/jetbrains/kotlin/fir/java/JvmSupertypeUpdater$DelegatedConstructorCallTransformer;", "updateSupertypesIfNeeded", Argument.Delimiters.none, "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "hasAnnotationUltraSafe", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "DelegatedConstructorCallTransformer", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmSupertypeUpdater extends PlatformSupertypeUpdater {
    private final DelegatedConstructorCallTransformer jvmRecordUpdater;
    private final FirSession session;

    public JvmSupertypeUpdater(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.jvmRecordUpdater = new DelegatedConstructorCallTransformer(firSession);
    }

    private final boolean hasAnnotationUltraSafe(FirDeclaration firDeclaration, ClassId classId) {
        List<FirAnnotation> annotations = firDeclaration.getAnnotations();
        if ((annotations instanceof Collection) && annotations.isEmpty()) {
            return false;
        }
        Iterator<T> it = annotations.iterator();
        while (it.hasNext()) {
            FirResolvedTypeRef annotationTypeRef = ((FirAnnotation) it.next()).getAnnotationTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (!(coneType instanceof ConeClassLikeType)) {
                coneType = null;
            }
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
            if (Intrinsics.areEqual(coneClassLikeType != null ? ConeTypeUtilsKt.getClassId(coneClassLikeType) : null, classId)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.PlatformSupertypeUpdater
    public void updateSupertypesIfNeeded(FirClass firClass, ScopeSession scopeSession) {
        firClass.getClass();
        scopeSession.getClass();
        if (firClass instanceof FirRegularClass) {
            FirRegularClass firRegularClass = (FirRegularClass) firClass;
            if (firRegularClass.getStatus().isData() && hasAnnotationUltraSafe(firClass, JvmStandardClassIds.Annotations.INSTANCE.getJvmRecord())) {
                if (FirLanguageSettingsComponentKt.isMetadataCompilation(this.session) && ToSymbolUtilsKt.toRegularClassSymbol(DelegatedConstructorCallTransformer.INSTANCE.getRecordType().getLookupTag(), this.session) == null) {
                    return;
                }
                List<FirTypeRef> superTypeRefs = firRegularClass.getSuperTypeRefs();
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = superTypeRefs.iterator();
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    FirResolvedTypeRef firResolvedTypeRefWithReplacedSourceAndType = (FirTypeRef) it.next();
                    if ((firResolvedTypeRefWithReplacedSourceAndType instanceof FirImplicitBuiltinTypeRef) && Intrinsics.areEqual(((FirImplicitBuiltinTypeRef) firResolvedTypeRefWithReplacedSourceAndType).getId(), StandardClassIds.INSTANCE.getAny())) {
                        FirResolvedTypeRef firResolvedTypeRef = firResolvedTypeRefWithReplacedSourceAndType;
                        KtSourceElement source = firRegularClass.getSource();
                        firResolvedTypeRefWithReplacedSourceAndType = TypeUtilsKt.withReplacedSourceAndType(firResolvedTypeRef, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.RecordSuperTypeRef.INSTANCE, null, 2, null) : null, DelegatedConstructorCallTransformer.INSTANCE.getRecordType());
                        z = true;
                    } else {
                        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(FirTypeUtilsKt.getConeType(firResolvedTypeRefWithReplacedSourceAndType), this.session);
                        if ((regularClassSymbol != null ? regularClassSymbol.getClassKind() : null) == ClassKind.CLASS) {
                            z2 = true;
                        }
                    }
                    arrayList.add(firResolvedTypeRefWithReplacedSourceAndType);
                }
                if (!z && !z2) {
                    ConeClassLikeType recordType = DelegatedConstructorCallTransformer.INSTANCE.getRecordType();
                    KtSourceElement source2 = firRegularClass.getSource();
                    arrayList.add(UtilsKt.toFirResolvedTypeRef$default(recordType, source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.RecordSuperTypeRef.INSTANCE, null, 2, null) : null, null, 2, null));
                }
                if (z || !z2) {
                    firRegularClass.replaceSuperTypeRefs(arrayList);
                    firRegularClass.transformDeclarations((FirTransformer<? super ScopeSession>) this.jvmRecordUpdater, scopeSession);
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\u0006\u0010\n\u001a\u0002H\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/JvmSupertypeUpdater$DelegatedConstructorCallTransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "transformConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "transformErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "transformDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "Companion", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DelegatedConstructorCallTransformer extends FirTransformer<ScopeSession> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final ConeClassLikeType recordType = TypeConstructionUtilsKt.constructClassLikeType$default(JvmStandardClassIds.Java.INSTANCE.getRecord(), null, false, null, 7, null);
        private final FirSession session;

        public DelegatedConstructorCallTransformer(FirSession firSession) {
            firSession.getClass();
            this.session = firSession;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformConstructor(FirConstructor constructor, ScopeSession data) {
            constructor.getClass();
            data.getClass();
            return constructor.transformDelegatedConstructor(this, data);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x0045  */
        /* JADX WARN: Code duplicated, block: B:27:0x004d  */
        /* JADX WARN: Code duplicated, block: B:28:0x0055  */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformDelegatedConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall, ScopeSession data) {
            KtSourceElement source;
            KtSourceElement ktSourceElementFakeElement$default;
            FirTypeScope firTypeScopeUnsubstitutedScope;
            List<FirConstructorSymbol> declaredConstructors;
            delegatedConstructorCall.getClass();
            data.getClass();
            if (!(delegatedConstructorCall instanceof FirLazyDelegatedConstructorCall)) {
                KtSourceElement source2 = delegatedConstructorCall.getSource();
                FirConstructorSymbol firConstructorSymbol = null;
                Object obj = null;
                firConstructorSymbol = null;
                firConstructorSymbol = null;
                if (!Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE)) {
                    return delegatedConstructorCall;
                }
                FirResolvedTypeRef constructedTypeRef = delegatedConstructorCall.getConstructedTypeRef();
                if (constructedTypeRef instanceof FirImplicitTypeRef) {
                    ConeClassLikeType coneClassLikeType = recordType;
                    source = delegatedConstructorCall.getSource();
                    if (source != null) {
                        ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.RecordSuperTypeRef.INSTANCE, null, 2, null);
                    } else {
                        ktSourceElementFakeElement$default = null;
                    }
                    delegatedConstructorCall.replaceConstructedTypeRef(CopyUtilsKt.resolvedTypeFromPrototype(constructedTypeRef, coneClassLikeType, ktSourceElementFakeElement$default));
                } else {
                    FirResolvedTypeRef firResolvedTypeRef = constructedTypeRef instanceof FirResolvedTypeRef ? constructedTypeRef : null;
                    ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                    if (coneType == null) {
                        coneType = null;
                    }
                    if (coneType != null && ConeBuiltinTypeUtilsKt.isAny(coneType)) {
                        ConeClassLikeType coneClassLikeType2 = recordType;
                        source = delegatedConstructorCall.getSource();
                        if (source != null) {
                            ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.RecordSuperTypeRef.INSTANCE, null, 2, null);
                        } else {
                            ktSourceElementFakeElement$default = null;
                        }
                        delegatedConstructorCall.replaceConstructedTypeRef(CopyUtilsKt.resolvedTypeFromPrototype(constructedTypeRef, coneClassLikeType2, ktSourceElementFakeElement$default));
                    }
                }
                FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(recordType.getLookupTag(), this.session);
                if (regularClassSymbol != null && (firTypeScopeUnsubstitutedScope = FirKotlinScopeProviderKt.unsubstitutedScope((FirClassSymbol<?>) regularClassSymbol, this.session, data, false, (FirResolvePhase) null)) != null && (declaredConstructors = FirScopeKt.getDeclaredConstructors(firTypeScopeUnsubstitutedScope)) != null) {
                    for (Object obj2 : declaredConstructors) {
                        if (((FirConstructor) ((FirConstructorSymbol) obj2).getFir()).getValueParameters().isEmpty()) {
                            obj = obj2;
                            break;
                        }
                    }
                    firConstructorSymbol = (FirConstructorSymbol) obj;
                }
                if (firConstructorSymbol != null) {
                    FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
                    firResolvedNamedReferenceBuilder.setName(JvmStandardClassIds.Java.INSTANCE.getRecord().getShortClassName());
                    firResolvedNamedReferenceBuilder.setResolvedSymbol(firConstructorSymbol);
                    delegatedConstructorCall.replaceCalleeReference(firResolvedNamedReferenceBuilder.build());
                }
            }
            return delegatedConstructorCall;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public <E extends FirElement> E transformElement(E element, ScopeSession data) {
            element.getClass();
            data.getClass();
            return element;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, ScopeSession data) {
            errorPrimaryConstructor.getClass();
            data.getClass();
            return transformConstructor((FirConstructor) errorPrimaryConstructor, data);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformRegularClass(FirRegularClass regularClass, ScopeSession data) {
            regularClass.getClass();
            data.getClass();
            return regularClass;
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/JvmSupertypeUpdater$DelegatedConstructorCallTransformer$Companion;", Argument.Delimiters.none, "<init>", "()V", "recordType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "getRecordType", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final ConeClassLikeType getRecordType() {
                return DelegatedConstructorCallTransformer.recordType;
            }

            private Companion() {
            }
        }
    }
}
