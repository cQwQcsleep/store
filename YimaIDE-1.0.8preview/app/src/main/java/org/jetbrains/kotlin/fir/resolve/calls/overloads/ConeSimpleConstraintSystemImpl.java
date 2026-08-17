package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.Collection;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.inference.ConeTypeParameterBasedTypeVariable;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemMarker;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.inference.model.SimpleConstraintSystemConstraintPosition;
import org.jetbrains.kotlin.resolve.calls.results.SimpleConstraintSystem;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker;
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContext;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0018\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeSimpleConstraintSystemImpl;", "Lorg/jetbrains/kotlin/resolve/calls/results/SimpleConstraintSystem;", "system", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSystem", "()Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "registerTypeVariables", "Lorg/jetbrains/kotlin/types/model/TypeSubstitutorMarker;", "typeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "addSubtypeConstraint", Argument.Delimiters.none, "subType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "superType", "hasContradiction", Argument.Delimiters.none, "captureFromArgument", "getCaptureFromArgument", "()Z", "context", "Lorg/jetbrains/kotlin/types/model/TypeSystemInferenceExtensionContext;", "getContext", "()Lorg/jetbrains/kotlin/types/model/TypeSystemInferenceExtensionContext;", "constraintSystemMarker", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemMarker;", "getConstraintSystemMarker", "()Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemMarker;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeSimpleConstraintSystemImpl implements SimpleConstraintSystem {
    private final FirSession session;
    private final NewConstraintSystemImpl system;

    public ConeSimpleConstraintSystemImpl(NewConstraintSystemImpl newConstraintSystemImpl, FirSession firSession) {
        newConstraintSystemImpl.getClass();
        firSession.getClass();
        this.system = newConstraintSystemImpl;
        this.session = firSession;
    }

    public void addSubtypeConstraint(KotlinTypeMarker subType, KotlinTypeMarker superType) {
        subType.getClass();
        superType.getClass();
        this.system.addSubtypeConstraint(subType, superType, SimpleConstraintSystemConstraintPosition.INSTANCE);
    }

    public boolean getCaptureFromArgument() {
        return true;
    }

    public ConstraintSystemMarker getConstraintSystemMarker() {
        return this.system;
    }

    public TypeSystemInferenceExtensionContext getContext() {
        return this.system;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final NewConstraintSystemImpl getSystem() {
        return this.system;
    }

    public boolean hasContradiction() {
        return this.system.getHasContradiction();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Type inference failed for: r0v5, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public TypeSubstitutorMarker registerTypeVariables(Collection<? extends TypeParameterMarker> typeParameters) throws KotlinIllegalArgumentExceptionWithAttachments {
        typeParameters.getClass();
        NewConstraintSystemImpl builder = this.system.getBuilder();
        Collection<? extends TypeParameterMarker> collection = typeParameters;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collection, 10)), 16));
        for (TypeParameterMarker typeParameterMarker : collection) {
            FirTypeParameterSymbol typeParameterSymbol = ((ConeTypeParameterLookupTag) typeParameterMarker).getTypeParameterSymbol();
            if (!(typeParameterMarker instanceof ConeTypeParameterLookupTag)) {
                w01.a("Failed requirement.");
                return null;
            }
            ConeTypeParameterBasedTypeVariable coneTypeParameterBasedTypeVariable = new ConeTypeParameterBasedTypeVariable(((ConeTypeParameterLookupTag) typeParameterMarker).getTypeParameterSymbol());
            builder.registerVariable(coneTypeParameterBasedTypeVariable);
            linkedHashMap.put(typeParameterSymbol, coneTypeParameterBasedTypeVariable.getDefaultType());
        }
        ConeSubstitutor coneSubstitutorSubstitutorByMap$default = ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, this.session, false, 4, null);
        for (TypeParameterMarker typeParameterMarker2 : typeParameters) {
            if (!(typeParameterMarker2 instanceof ConeTypeParameterLookupTag)) {
                w01.a("Failed requirement.");
                return null;
            }
            ConeTypeParameterLookupTag coneTypeParameterLookupTag = (ConeTypeParameterLookupTag) typeParameterMarker2;
            for (FirResolvedTypeRef firResolvedTypeRef : coneTypeParameterLookupTag.getSymbol().getResolvedBounds()) {
                ConeTypeVariableType coneTypeVariableType = (ConeTypeVariableType) linkedHashMap.get(coneTypeParameterLookupTag.getTypeParameterSymbol());
                if (coneTypeVariableType == null) {
                    KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("No " + coneTypeParameterLookupTag.getSymbol().getFir().getClass() + " in substitution map", (Throwable) null);
                    ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                    FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "typeParameter", coneTypeParameterLookupTag.getSymbol().getFir());
                    kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                    throw kotlinIllegalArgumentExceptionWithAttachments;
                }
                addSubtypeConstraint(coneTypeVariableType, coneSubstitutorSubstitutorByMap$default.substituteOrSelf(firResolvedTypeRef.getConeType()));
            }
        }
        return coneSubstitutorSubstitutorByMap$default;
    }
}
