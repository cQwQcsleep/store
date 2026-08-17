package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeCollectionLiteralAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionContext;
import org.jetbrains.kotlin.resolve.calls.inference.components.TypeVariableDependencyInformationProvider;
import org.jetbrains.kotlin.resolve.calls.inference.model.Constraint;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintKind;
import org.jetbrains.kotlin.resolve.calls.inference.model.VariableWithConstraints;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContextContextualKt;
import org.jetbrains.kotlin.types.model.TypeVariableTypeConstructorMarker;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\rR\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBoundsCollector;", Argument.Delimiters.none, "dependencyInformationProvider", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/TypeVariableDependencyInformationProvider;", "<init>", "(Lorg/jetbrains/kotlin/resolve/calls/inference/components/TypeVariableDependencyInformationProvider;)V", "collectBoundsForCollectionLiteral", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionContext;", "c", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "resolutionContext", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "(Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;)Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CollectionLiteralBoundsCollector {
    private final TypeVariableDependencyInformationProvider dependencyInformationProvider;

    public CollectionLiteralBoundsCollector(TypeVariableDependencyInformationProvider typeVariableDependencyInformationProvider) {
        typeVariableDependencyInformationProvider.getClass();
        this.dependencyInformationProvider = typeVariableDependencyInformationProvider;
    }

    private static final void collectBoundsForCollectionLiteral$processConstraintsOfShallowlyDependentVariable(ConstraintSystemCompletionContext constraintSystemCompletionContext, ResolutionContext resolutionContext, Set<FirRegularClassSymbol> set, CollectionLiteralBoundsCollector collectionLiteralBoundsCollector, TypeConstructorMarker typeConstructorMarker, Set<TypeConstructorMarker> set2) {
        List<Constraint> listEmptyList;
        FirRegularClassSymbol classRepresentativeForCollectionLiteralResolution;
        FirRegularClassSymbol classRepresentativeForCollectionLiteralResolution2;
        set2.add(typeConstructorMarker);
        VariableWithConstraints variableWithConstraints = (VariableWithConstraints) constraintSystemCompletionContext.getNotFixedTypeVariables().get(typeConstructorMarker);
        if (variableWithConstraints == null || (listEmptyList = variableWithConstraints.getConstraints()) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        SmartSet.Companion companion = SmartSet.Companion;
        SmartSet smartSetCreate = companion.create();
        SmartSet smartSetCreate2 = companion.create();
        for (Constraint constraint : listEmptyList) {
            if (constraint.getKind() != ConstraintKind.LOWER && (classRepresentativeForCollectionLiteralResolution2 = CollectionLiteralResolutionUtilsKt.getClassRepresentativeForCollectionLiteralResolution(resolutionContext, constraint.getType())) != null && CollectionLiteralResolutionUtilsKt.declaresOperatorOf(resolutionContext, classRepresentativeForCollectionLiteralResolution2)) {
                smartSetCreate2.add(classRepresentativeForCollectionLiteralResolution2);
            }
            if (constraint.getKind() != ConstraintKind.UPPER && (classRepresentativeForCollectionLiteralResolution = CollectionLiteralResolutionUtilsKt.getClassRepresentativeForCollectionLiteralResolution(resolutionContext, constraint.getType())) != null && CollectionLiteralResolutionUtilsKt.declaresOperatorOf(resolutionContext, classRepresentativeForCollectionLiteralResolution)) {
                smartSetCreate.add(classRepresentativeForCollectionLiteralResolution);
            }
        }
        set.addAll(smartSetCreate);
        FirRegularClassSymbol firRegularClassSymbolChooseSingleClassFromIntersectionComponents = CollectionLiteralResolutionUtilsKt.chooseSingleClassFromIntersectionComponents(resolutionContext, smartSetCreate2);
        if (firRegularClassSymbolChooseSingleClassFromIntersectionComponents == null) {
            CollectionsKt.addAll(set, smartSetCreate2);
        } else {
            set.add(firRegularClassSymbolChooseSingleClassFromIntersectionComponents);
        }
        Set<TypeConstructorMarker> shallowlyDependentVariables = collectionLiteralBoundsCollector.dependencyInformationProvider.getShallowlyDependentVariables(typeConstructorMarker);
        if (shallowlyDependentVariables == null) {
            shallowlyDependentVariables = SetsKt.emptySet();
        }
        for (TypeConstructorMarker typeConstructorMarker2 : shallowlyDependentVariables) {
            if (!set2.contains(typeConstructorMarker2)) {
                collectBoundsForCollectionLiteral$processConstraintsOfShallowlyDependentVariable(constraintSystemCompletionContext, resolutionContext, set, collectionLiteralBoundsCollector, typeConstructorMarker2, set2);
            }
        }
    }

    public static /* synthetic */ void collectBoundsForCollectionLiteral$processConstraintsOfShallowlyDependentVariable$default(ConstraintSystemCompletionContext constraintSystemCompletionContext, ResolutionContext resolutionContext, Set set, CollectionLiteralBoundsCollector collectionLiteralBoundsCollector, TypeConstructorMarker typeConstructorMarker, Set set2, int i, Object obj) {
        if ((i & 32) != 0) {
            set2 = SmartSet.Companion.create();
        }
        collectBoundsForCollectionLiteral$processConstraintsOfShallowlyDependentVariable(constraintSystemCompletionContext, resolutionContext, set, collectionLiteralBoundsCollector, typeConstructorMarker, set2);
    }

    public final CollectionLiteralBounds collectBoundsForCollectionLiteral(ConstraintSystemCompletionContext constraintSystemCompletionContext, ResolutionContext resolutionContext, ConeCollectionLiteralAtom coneCollectionLiteralAtom) {
        TypeConstructorMarker typeConstructorMarkerTypeConstructor;
        constraintSystemCompletionContext.getClass();
        resolutionContext.getClass();
        coneCollectionLiteralAtom.getClass();
        if (coneCollectionLiteralAtom.getAnalyzed()) {
            return null;
        }
        ConeKotlinType coneKotlinTypeMo581getExpectedType = coneCollectionLiteralAtom.mo581getExpectedType();
        if (coneKotlinTypeMo581getExpectedType == null || (typeConstructorMarkerTypeConstructor = TypeSystemContextContextualKt.typeConstructor(constraintSystemCompletionContext, coneKotlinTypeMo581getExpectedType)) == null) {
            return new CollectionLiteralBounds.FallbackOnly(coneCollectionLiteralAtom);
        }
        if (!constraintSystemCompletionContext.getNotFixedTypeVariables().containsKey(typeConstructorMarkerTypeConstructor)) {
            if (!(typeConstructorMarkerTypeConstructor instanceof TypeVariableTypeConstructorMarker)) {
                return new CollectionLiteralBounds.NonTvExpected(coneCollectionLiteralAtom, CollectionLiteralResolutionUtilsKt.getClassRepresentativeForCollectionLiteralResolution(resolutionContext, coneCollectionLiteralAtom.mo581getExpectedType()));
            }
            w01.a("CL-expected type variable must not be fixed before its CL.");
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectBoundsForCollectionLiteral$processConstraintsOfShallowlyDependentVariable$default(constraintSystemCompletionContext, resolutionContext, linkedHashSet, this, typeConstructorMarkerTypeConstructor, null, 32, null);
        int size = linkedHashSet.size();
        if (size != 0) {
            return size != 1 ? new CollectionLiteralBounds.Ambiguity(coneCollectionLiteralAtom, linkedHashSet) : new CollectionLiteralBounds.SingleBound(coneCollectionLiteralAtom, (FirRegularClassSymbol) CollectionsKt.single(linkedHashSet));
        }
        return new CollectionLiteralBounds.FallbackOnly(coneCollectionLiteralAtom);
    }
}
