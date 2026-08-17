package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStubTypeForTypeVariableInSubtyping;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nH\u0002¨\u0006\u000b"}, d2 = {"getParentChainForContextSensitiveResolutionOfExpressions", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getParentChainForContextSensitiveResolutionOfTypes", "getClassRepresentativeForContextSensitiveResolution", "getParentChainForContextSensitiveResolution", "onlySealed", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContextSensitiveResolutionKt {

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.ContextSensitiveResolutionKt$getParentChainForContextSensitiveResolution$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none, "Lkotlin/sequences/SequenceScope;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;"}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super FirRegularClassSymbol>, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $onlySealed;
        final /* synthetic */ FirSession $session;
        final /* synthetic */ FirRegularClassSymbol $this_getParentChainForContextSensitiveResolution;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FirRegularClassSymbol firRegularClassSymbol, boolean z, FirSession firSession, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_getParentChainForContextSensitiveResolution = firRegularClassSymbol;
            this.$onlySealed = z;
            this.$session = firSession;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_getParentChainForContextSensitiveResolution, this.$onlySealed, this.$session, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(SequenceScope<? super FirRegularClassSymbol> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x002a  */
        /* JADX WARN: Code duplicated, block: B:11:0x002c  */
        /* JADX WARN: Code duplicated, block: B:13:0x0038  */
        /* JADX WARN: Code duplicated, block: B:15:0x0046 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x0051  */
        /* JADX WARN: Code duplicated, block: B:19:0x0054  */
        /* JADX WARN: Code duplicated, block: B:21:0x0057  */
        /* JADX WARN: Code duplicated, block: B:23:0x0065  */
        /* JADX WARN: Code duplicated, block: B:24:0x0067  */
        /* JADX WARN: Code duplicated, block: B:26:0x006a  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r9v2 */
        /* JADX WARN: Type inference failed for: r9v3, types: [int] */
        /* JADX WARN: Type inference failed for: r9v9 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0036 -> B:16:0x0047). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0044 -> B:16:0x0047). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:21:0x0057
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = r8.L$0
                kotlin.sequences.SequenceScope r0 = (kotlin.sequences.SequenceScope) r0
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r8.label
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L1e
                if (r2 != r4) goto L18
                java.lang.Object r2 = r8.L$1
                org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol r2 = (org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol) r2
                kotlin.ResultKt.throwOnFailure(r9)
                goto L47
            L18:
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                k2d.a(r8)
                return r3
            L1e:
                kotlin.ResultKt.throwOnFailure(r9)
                org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol r9 = r8.$this_getParentChainForContextSensitiveResolution
                boolean r2 = r8.$onlySealed
                r7 = r2
                r2 = r9
                r9 = r7
            L28:
                if (r2 == 0) goto L6a
                if (r9 == 0) goto L38
                org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus r5 = r2.getResolvedStatus()
                org.jetbrains.kotlin.descriptors.Modality r5 = r5.getModality()
                org.jetbrains.kotlin.descriptors.Modality r6 = org.jetbrains.kotlin.descriptors.Modality.SEALED
                if (r5 != r6) goto L47
            L38:
                r8.L$0 = r0
                r8.L$1 = r2
                r8.I$0 = r9
                r8.label = r4
                java.lang.Object r9 = r0.yield(r2, r8)
                if (r9 != r1) goto L47
                return r1
            L47:
                org.jetbrains.kotlin.fir.FirSession r9 = r8.$session
                org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol r9 = org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt.getContainingDeclaration(r2, r9)
                boolean r2 = r9 instanceof org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
                if (r2 == 0) goto L54
                org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol r9 = (org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol) r9
                goto L55
            L54:
                r9 = r3
            L55:
                if (r9 == 0) goto L67
                org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol r2 = r8.$this_getParentChainForContextSensitiveResolution
                org.jetbrains.kotlin.fir.FirSession r5 = r8.$session
                org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag r6 = r9.getLookupTag()
                boolean r2 = org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt.isSubclassOf(r2, r6, r5, r4, r4)
                if (r2 == 0) goto L67
                r2 = r9
                goto L68
            L67:
                r2 = r3
            L68:
                r9 = r4
                goto L28
            L6a:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.resolve.ContextSensitiveResolutionKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirRegularClassSymbol getClassRepresentativeForContextSensitiveResolution(ConeKotlinType coneKotlinType, FirSession firSession) {
        ConeKotlinType coneType;
        Object obj = null;
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            FirRegularClassSymbol classRepresentativeForContextSensitiveResolution = getClassRepresentativeForContextSensitiveResolution(coneFlexibleType.getLowerBound(), firSession);
            if (classRepresentativeForContextSensitiveResolution == null || !Intrinsics.areEqual(classRepresentativeForContextSensitiveResolution, getClassRepresentativeForContextSensitiveResolution(coneFlexibleType.getUpperBound(), firSession))) {
                return null;
            }
            return classRepresentativeForContextSensitiveResolution;
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return getClassRepresentativeForContextSensitiveResolution(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), firSession);
        }
        if (coneKotlinType instanceof ConeIntegerLiteralType) {
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) CollectionsKt.singleOrNull(((ConeIntegerLiteralType) coneKotlinType).getPossibleTypes());
            if (coneClassLikeType != null) {
                return getClassRepresentativeForContextSensitiveResolution(coneClassLikeType, firSession);
            }
            return null;
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
            Iterator<T> it = intersectedTypes.iterator();
            while (it.hasNext()) {
                FirRegularClassSymbol classRepresentativeForContextSensitiveResolution2 = getClassRepresentativeForContextSensitiveResolution((ConeKotlinType) it.next(), firSession);
                if (classRepresentativeForContextSensitiveResolution2 == null) {
                    return null;
                }
                arrayList.add(classRepresentativeForContextSensitiveResolution2);
            }
            loop1: for (Object obj2 : arrayList) {
                FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) obj2;
                if (!arrayList.isEmpty()) {
                    Iterator it2 = arrayList.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (!SupertypeUtilsKt.isSubclassOf$default((FirClass) firRegularClassSymbol.getFir(), ((FirRegularClassSymbol) it2.next()).getLookupTag(), firSession, false, null, false, 24, null)) {
                            }
                        }
                    }
                }
                obj = obj2;
            }
            return (FirRegularClassSymbol) obj;
        }
        if (!(coneKotlinType instanceof ConeLookupTagBasedType)) {
            if (!(coneKotlinType instanceof ConeCapturedType) && !(coneKotlinType instanceof ConeStubTypeForTypeVariableInSubtyping) && !(coneKotlinType instanceof ConeTypeVariableType)) {
                bu8.a();
            }
            return null;
        }
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(((ConeLookupTagBasedType) coneKotlinType).getLookupTag(), firSession);
        if (symbol instanceof FirRegularClassSymbol) {
            return (FirRegularClassSymbol) symbol;
        }
        if (symbol instanceof FirTypeParameterSymbol) {
            FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) CollectionsKt.singleOrNull(((FirTypeParameterSymbol) symbol).getResolvedBounds());
            if (firResolvedTypeRef == null || (coneType = firResolvedTypeRef.getConeType()) == null) {
                return null;
            }
            return getClassRepresentativeForContextSensitiveResolution(coneType, firSession);
        }
        if (symbol instanceof FirAnonymousObjectSymbol) {
            return null;
        }
        if (!(symbol instanceof FirTypeAliasSymbol)) {
            if (symbol == null) {
                return null;
            }
            bu8.a();
            return null;
        }
        ConeSimpleKotlinType coneSimpleKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default((ConeSimpleKotlinType) coneKotlinType, firSession, (Function1) null, 2, (Object) null);
        if (coneSimpleKotlinTypeFullyExpandedType$default == coneKotlinType) {
            coneSimpleKotlinTypeFullyExpandedType$default = null;
        }
        if (coneSimpleKotlinTypeFullyExpandedType$default != null) {
            return getClassRepresentativeForContextSensitiveResolution(coneSimpleKotlinTypeFullyExpandedType$default, firSession);
        }
        return null;
    }

    private static final Sequence<FirRegularClassSymbol> getParentChainForContextSensitiveResolution(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession, boolean z) {
        return SequencesKt.sequence(new AnonymousClass1(firRegularClassSymbol, z, firSession, null));
    }

    public static final Sequence<FirRegularClassSymbol> getParentChainForContextSensitiveResolutionOfExpressions(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FirRegularClassSymbol classRepresentativeForContextSensitiveResolution = getClassRepresentativeForContextSensitiveResolution(coneKotlinType, firSession);
        Sequence<FirRegularClassSymbol> parentChainForContextSensitiveResolution = classRepresentativeForContextSensitiveResolution != null ? getParentChainForContextSensitiveResolution(classRepresentativeForContextSensitiveResolution, firSession, false) : null;
        return parentChainForContextSensitiveResolution == null ? SequencesKt.emptySequence() : parentChainForContextSensitiveResolution;
    }

    public static final Sequence<FirRegularClassSymbol> getParentChainForContextSensitiveResolutionOfTypes(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FirRegularClassSymbol classRepresentativeForContextSensitiveResolution = getClassRepresentativeForContextSensitiveResolution(coneKotlinType, firSession);
        Sequence<FirRegularClassSymbol> parentChainForContextSensitiveResolution = classRepresentativeForContextSensitiveResolution != null ? getParentChainForContextSensitiveResolution(classRepresentativeForContextSensitiveResolution, firSession, true) : null;
        return parentChainForContextSensitiveResolution == null ? SequencesKt.emptySequence() : parentChainForContextSensitiveResolution;
    }
}
