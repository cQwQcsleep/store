package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.WrongNumberOfTypeArguments;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallKind;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeIntersector;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirTypeProjectionWithVarianceBuilder;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ!\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/MapTypeArguments;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeDefaultMappingForRawTypeMember", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping$Mapped;", "owner", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;)Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping$Mapped;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MapTypeArguments extends ResolutionStage {
    public static final MapTypeArguments INSTANCE = new MapTypeArguments();

    private MapTypeArguments() {
    }

    private final TypeArgumentMapping.Mapped computeDefaultMappingForRawTypeMember(ResolutionContext resolutionContext, FirTypeParameterRefsOwner firTypeParameterRefsOwner) {
        List<FirTypeParameterRef> typeParameters = firTypeParameterRefsOwner.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        for (FirTypeParameterRef firTypeParameterRef : typeParameters) {
            FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
            ConeTypeIntersector coneTypeIntersector = ConeTypeIntersector.INSTANCE;
            ConeInferenceContext typeContext = resolutionContext.getTypeContext();
            List<FirResolvedTypeRef> resolvedBounds = firTypeParameterRef.getSymbol().getResolvedBounds();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
            Iterator<T> it = resolvedBounds.iterator();
            while (it.hasNext()) {
                arrayList2.add(((FirResolvedTypeRef) it.next()).getConeType());
            }
            firTypeProjectionWithVarianceBuilder.setTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneTypeIntersector.intersectTypes(typeContext, arrayList2), null, null, 3, null));
            firTypeProjectionWithVarianceBuilder.setVariance(Variance.INVARIANT);
            arrayList.add(firTypeProjectionWithVarianceBuilder.build());
        }
        return new TypeArgumentMapping.Mapped(arrayList);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeSimpleKotlinType dispatchReceiverType;
        List<FirTypeProjection> typeArguments = candidate.getCallInfo().getTypeArguments();
        FirAnnotationContainer fir = candidate.getSymbol().getFir();
        fir.getClass();
        FirTypeParameterRefsOwner firTypeParameterRefsOwner = (FirTypeParameterRefsOwner) fir;
        if (typeArguments.isEmpty()) {
            if ((firTypeParameterRefsOwner instanceof FirCallableDeclaration) && (dispatchReceiverType = ((FirCallableDeclaration) firTypeParameterRefsOwner).getDispatchReceiverType()) != null && TypeUtilsKt.isRaw(dispatchReceiverType)) {
                candidate.setTypeArgumentMapping(computeDefaultMappingForRawTypeMember(resolutionContext, firTypeParameterRefsOwner));
            } else {
                candidate.setTypeArgumentMapping(TypeArgumentMapping.NoExplicitArguments.INSTANCE);
            }
            return Unit.INSTANCE;
        }
        int size = firTypeParameterRefsOwner.getTypeParameters().size();
        if (typeArguments.size() == size || Intrinsics.areEqual(candidate.getCallInfo().getCallKind(), CallKind.DelegatingConstructorCall.INSTANCE) || (((FirDeclaration) firTypeParameterRefsOwner).getOrigin() instanceof FirDeclarationOrigin.DynamicScope)) {
            candidate.setTypeArgumentMapping(new TypeArgumentMapping.Mapped(typeArguments));
            return Unit.INSTANCE;
        }
        if (typeArguments.size() > size) {
            typeArguments = CollectionsKt.take(typeArguments, size);
        }
        candidate.setTypeArgumentMapping(new TypeArgumentMapping.Mapped(typeArguments));
        checkerSink.reportDiagnostic(new WrongNumberOfTypeArguments(size, candidate.getSymbol()));
        return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
    }
}
