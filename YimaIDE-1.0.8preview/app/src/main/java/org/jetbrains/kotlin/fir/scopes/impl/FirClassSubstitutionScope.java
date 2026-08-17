package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCacheWithPostComputeKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutorKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.ConeSubstitutionScopeKey;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassSubstitutionScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002QRBQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001b0\u001fH\u0016J*\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020 2\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\"0$H\u0016J\u008d\u0001\u0010%\u001a\u00020\"\"\u000e\b\u0000\u0010&\u0018\u0001*\u0006\u0012\u0002\b\u00030'2\u0006\u0010(\u001a\u0002H&2\u001a\b\b\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\"0$25\u0010)\u001a1\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H&\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\"0$\u0012\u0004\u0012\u00020\"0*¢\u0006\u0002\b+2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u00020\f0\u001fH\u0082\b¢\u0006\u0002\u0010-J(\u0010.\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0016\u0010\u001e\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030/\u0012\u0004\u0012\u00020\u001b0\u001fH\u0016J*\u00100\u001a\u00020\"2\u0006\u00101\u001a\u0002022\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\"0$H\u0016J.\u00103\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u001c\u0010\u001e\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u000304\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001b0$H\u0016J\u000e\u00105\u001a\u0004\u0018\u000106*\u000206H\u0002J\u0016\u00105\u001a\u0004\u0018\u000106*\u0002062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u00107\u001a\u0004\u0018\u000108*\u0002082\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020 J\u000e\u0010;\u001a\u00020<2\u0006\u0010:\u001a\u00020<J\u000e\u0010=\u001a\u0002022\u0006\u0010:\u001a\u000202J\u001c\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020A2\n\u0010B\u001a\u0006\u0012\u0002\b\u00030CH\u0002J\u000e\u0010D\u001a\u00020E2\u0006\u0010:\u001a\u00020EJ\u001c\u0010F\u001a\u00020\u001b2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u001b0\u001fH\u0016J\u000e\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001d0HH\u0016J\u000e\u0010I\u001a\b\u0012\u0004\u0012\u00020\u001d0HH\u0016J\n\u0010J\u001a\u00020KH\u0096\u0080\u0004J\u001c\u0010L\u001a\u00020\u00002\u0006\u0010M\u001a\u00020\u00032\u0006\u0010N\u001a\u00020OH\u0017b\u0002\bPR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "useSiteMemberScope", "key", "Lorg/jetbrains/kotlin/fir/scopes/ConeSubstitutionScopeKey;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "dispatchReceiverTypeForSubstitutedMembers", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "skipPrivateMembers", Argument.Delimiters.none, "makeExpect", "derivedClassLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/scopes/ConeSubstitutionScopeKey;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;ZZLorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride;)V", "getSubstitutor", "()Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "substitutionOverrideCache", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirSubstitutionOverrideStorage$SubstitutionOverrideCache;", "newOwnerClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processDirectOverriddenFunctionsWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "functionSymbol", "Lkotlin/Function2;", "processDirectOverriddenWithBaseScope", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "callableSymbol", "processDirectOverriddenCallablesWithBaseScope", "Lkotlin/Function3;", "Lkotlin/ExtensionFunctionType;", "originalInCache", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "processClassifiersByNameWithSubstitution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "substitute", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "substituteDispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "createSubstitutionOverrideFunction", "original", "createSubstitutionOverrideConstructor", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "createSubstitutionOverrideProperty", "createSubstitutedData", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope$SubstitutedData;", "member", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "symbolForOverride", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "createSubstitutionOverrideField", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "processDeclaredConstructors", "getCallableNames", Argument.Delimiters.none, "getClassifierNames", "toString", Argument.Delimiters.none, "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "SubstitutedData", "ReturnTypeData", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirClassSubstitutionScope extends FirTypeScope {
    private final ConeClassLikeLookupTag derivedClassLookupTag;
    private final ConeClassLikeType dispatchReceiverTypeForSubstitutedMembers;
    private final ConeSubstitutionScopeKey key;
    private final boolean makeExpect;
    private final ClassId newOwnerClassId;
    private final FirDeclarationOrigin.SubstitutionOverride origin;
    private final FirSession session;
    private final boolean skipPrivateMembers;
    private final FirSubstitutionOverrideStorage.SubstitutionOverrideCache substitutionOverrideCache;
    private final ConeSubstitutor substitutor;
    private final FirTypeScope useSiteMemberScope;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope$ReturnTypeData;", Argument.Delimiters.none, "newReturnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "deferredReturnTypeOfSubstitution", "Lorg/jetbrains/kotlin/fir/scopes/impl/DeferredReturnTypeOfSubstitution;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/scopes/impl/DeferredReturnTypeOfSubstitution;)V", "getNewReturnType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getDeferredReturnTypeOfSubstitution", "()Lorg/jetbrains/kotlin/fir/scopes/impl/DeferredReturnTypeOfSubstitution;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ReturnTypeData {
        private final DeferredReturnTypeOfSubstitution deferredReturnTypeOfSubstitution;
        private final ConeKotlinType newReturnType;

        public ReturnTypeData(ConeKotlinType coneKotlinType, DeferredReturnTypeOfSubstitution deferredReturnTypeOfSubstitution) {
            this.newReturnType = coneKotlinType;
            this.deferredReturnTypeOfSubstitution = deferredReturnTypeOfSubstitution;
        }

        public static /* synthetic */ ReturnTypeData copy$default(ReturnTypeData returnTypeData, ConeKotlinType coneKotlinType, DeferredReturnTypeOfSubstitution deferredReturnTypeOfSubstitution, int i, Object obj) {
            if ((i & 1) != 0) {
                coneKotlinType = returnTypeData.newReturnType;
            }
            if ((i & 2) != 0) {
                deferredReturnTypeOfSubstitution = returnTypeData.deferredReturnTypeOfSubstitution;
            }
            return returnTypeData.copy(coneKotlinType, deferredReturnTypeOfSubstitution);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ConeKotlinType getNewReturnType() {
            return this.newReturnType;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final DeferredReturnTypeOfSubstitution getDeferredReturnTypeOfSubstitution() {
            return this.deferredReturnTypeOfSubstitution;
        }

        public final ReturnTypeData copy(ConeKotlinType newReturnType, DeferredReturnTypeOfSubstitution deferredReturnTypeOfSubstitution) {
            return new ReturnTypeData(newReturnType, deferredReturnTypeOfSubstitution);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ReturnTypeData)) {
                return false;
            }
            ReturnTypeData returnTypeData = (ReturnTypeData) other;
            return Intrinsics.areEqual(this.newReturnType, returnTypeData.newReturnType) && Intrinsics.areEqual(this.deferredReturnTypeOfSubstitution, returnTypeData.deferredReturnTypeOfSubstitution);
        }

        public final DeferredReturnTypeOfSubstitution getDeferredReturnTypeOfSubstitution() {
            return this.deferredReturnTypeOfSubstitution;
        }

        public final ConeKotlinType getNewReturnType() {
            return this.newReturnType;
        }

        public int hashCode() {
            ConeKotlinType coneKotlinType = this.newReturnType;
            int iHashCode = (coneKotlinType == null ? 0 : coneKotlinType.hashCode()) * 31;
            DeferredReturnTypeOfSubstitution deferredReturnTypeOfSubstitution = this.deferredReturnTypeOfSubstitution;
            return iHashCode + (deferredReturnTypeOfSubstitution != null ? deferredReturnTypeOfSubstitution.hashCode() : 0);
        }

        public String toString() {
            return "ReturnTypeData(newReturnType=" + this.newReturnType + ", deferredReturnTypeOfSubstitution=" + this.deferredReturnTypeOfSubstitution + ')';
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BC\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\t\u0010\u001f\u001a\u00020\fHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fHÆ\u0003JQ\u0010!\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020&HÖ\u0081\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope$SubstitutedData;", Argument.Delimiters.none, "typeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "receiverType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "returnTypeData", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope$ReturnTypeData;", "explicitBackingFieldReturnTypeData", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope$ReturnTypeData;Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope$ReturnTypeData;)V", "getTypeParameters", "()Ljava/util/List;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getSubstitutor", "()Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "getReturnTypeData", "()Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope$ReturnTypeData;", "getExplicitBackingFieldReturnTypeData", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class SubstitutedData {
        private final ConeSimpleKotlinType dispatchReceiverType;
        private final ReturnTypeData explicitBackingFieldReturnTypeData;
        private final ConeKotlinType receiverType;
        private final ReturnTypeData returnTypeData;
        private final ConeSubstitutor substitutor;
        private final List<FirTypeParameterRef> typeParameters;

        /* JADX WARN: Multi-variable type inference failed */
        public SubstitutedData(List<? extends FirTypeParameterRef> list, ConeSimpleKotlinType coneSimpleKotlinType, ConeKotlinType coneKotlinType, ConeSubstitutor coneSubstitutor, ReturnTypeData returnTypeData, ReturnTypeData returnTypeData2) {
            list.getClass();
            coneSubstitutor.getClass();
            returnTypeData.getClass();
            this.typeParameters = list;
            this.dispatchReceiverType = coneSimpleKotlinType;
            this.receiverType = coneKotlinType;
            this.substitutor = coneSubstitutor;
            this.returnTypeData = returnTypeData;
            this.explicitBackingFieldReturnTypeData = returnTypeData2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SubstitutedData copy$default(SubstitutedData substitutedData, List list, ConeSimpleKotlinType coneSimpleKotlinType, ConeKotlinType coneKotlinType, ConeSubstitutor coneSubstitutor, ReturnTypeData returnTypeData, ReturnTypeData returnTypeData2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = substitutedData.typeParameters;
            }
            if ((i & 2) != 0) {
                coneSimpleKotlinType = substitutedData.dispatchReceiverType;
            }
            if ((i & 4) != 0) {
                coneKotlinType = substitutedData.receiverType;
            }
            if ((i & 8) != 0) {
                coneSubstitutor = substitutedData.substitutor;
            }
            if ((i & 16) != 0) {
                returnTypeData = substitutedData.returnTypeData;
            }
            if ((i & 32) != 0) {
                returnTypeData2 = substitutedData.explicitBackingFieldReturnTypeData;
            }
            ReturnTypeData returnTypeData3 = returnTypeData;
            ReturnTypeData returnTypeData4 = returnTypeData2;
            return substitutedData.copy(list, coneSimpleKotlinType, coneKotlinType, coneSubstitutor, returnTypeData3, returnTypeData4);
        }

        public final List<FirTypeParameterRef> component1() {
            return this.typeParameters;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ConeSimpleKotlinType getDispatchReceiverType() {
            return this.dispatchReceiverType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final ConeKotlinType getReceiverType() {
            return this.receiverType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final ConeSubstitutor getSubstitutor() {
            return this.substitutor;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final ReturnTypeData getReturnTypeData() {
            return this.returnTypeData;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final ReturnTypeData getExplicitBackingFieldReturnTypeData() {
            return this.explicitBackingFieldReturnTypeData;
        }

        public final SubstitutedData copy(List<? extends FirTypeParameterRef> typeParameters, ConeSimpleKotlinType dispatchReceiverType, ConeKotlinType receiverType, ConeSubstitutor substitutor, ReturnTypeData returnTypeData, ReturnTypeData explicitBackingFieldReturnTypeData) {
            typeParameters.getClass();
            substitutor.getClass();
            returnTypeData.getClass();
            return new SubstitutedData(typeParameters, dispatchReceiverType, receiverType, substitutor, returnTypeData, explicitBackingFieldReturnTypeData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubstitutedData)) {
                return false;
            }
            SubstitutedData substitutedData = (SubstitutedData) other;
            return Intrinsics.areEqual(this.typeParameters, substitutedData.typeParameters) && Intrinsics.areEqual(this.dispatchReceiverType, substitutedData.dispatchReceiverType) && Intrinsics.areEqual(this.receiverType, substitutedData.receiverType) && Intrinsics.areEqual(this.substitutor, substitutedData.substitutor) && Intrinsics.areEqual(this.returnTypeData, substitutedData.returnTypeData) && Intrinsics.areEqual(this.explicitBackingFieldReturnTypeData, substitutedData.explicitBackingFieldReturnTypeData);
        }

        public final ConeSimpleKotlinType getDispatchReceiverType() {
            return this.dispatchReceiverType;
        }

        public final ReturnTypeData getExplicitBackingFieldReturnTypeData() {
            return this.explicitBackingFieldReturnTypeData;
        }

        public final ConeKotlinType getReceiverType() {
            return this.receiverType;
        }

        public final ReturnTypeData getReturnTypeData() {
            return this.returnTypeData;
        }

        public final ConeSubstitutor getSubstitutor() {
            return this.substitutor;
        }

        public final List<FirTypeParameterRef> getTypeParameters() {
            return this.typeParameters;
        }

        public int hashCode() {
            int iHashCode = this.typeParameters.hashCode() * 31;
            ConeSimpleKotlinType coneSimpleKotlinType = this.dispatchReceiverType;
            int iHashCode2 = (iHashCode + (coneSimpleKotlinType == null ? 0 : coneSimpleKotlinType.hashCode())) * 31;
            ConeKotlinType coneKotlinType = this.receiverType;
            int iHashCode3 = (((((iHashCode2 + (coneKotlinType == null ? 0 : coneKotlinType.hashCode())) * 31) + this.substitutor.hashCode()) * 31) + this.returnTypeData.hashCode()) * 31;
            ReturnTypeData returnTypeData = this.explicitBackingFieldReturnTypeData;
            return iHashCode3 + (returnTypeData != null ? returnTypeData.hashCode() : 0);
        }

        public String toString() {
            return "SubstitutedData(typeParameters=" + this.typeParameters + ", dispatchReceiverType=" + this.dispatchReceiverType + ", receiverType=" + this.receiverType + ", substitutor=" + this.substitutor + ", returnTypeData=" + this.returnTypeData + ", explicitBackingFieldReturnTypeData=" + this.explicitBackingFieldReturnTypeData + ')';
        }
    }

    public FirClassSubstitutionScope(FirSession firSession, FirTypeScope firTypeScope, ConeSubstitutionScopeKey coneSubstitutionScopeKey, ConeSubstitutor coneSubstitutor, ConeClassLikeType coneClassLikeType, boolean z, boolean z2, ConeClassLikeLookupTag coneClassLikeLookupTag, FirDeclarationOrigin.SubstitutionOverride substitutionOverride) {
        firSession.getClass();
        firTypeScope.getClass();
        coneSubstitutionScopeKey.getClass();
        coneSubstitutor.getClass();
        coneClassLikeType.getClass();
        coneClassLikeLookupTag.getClass();
        substitutionOverride.getClass();
        this.session = firSession;
        this.useSiteMemberScope = firTypeScope;
        this.key = coneSubstitutionScopeKey;
        this.substitutor = coneSubstitutor;
        this.dispatchReceiverTypeForSubstitutedMembers = coneClassLikeType;
        this.skipPrivateMembers = z;
        this.makeExpect = z2;
        this.derivedClassLookupTag = coneClassLikeLookupTag;
        this.origin = substitutionOverride;
        this.substitutionOverrideCache = (FirSubstitutionOverrideStorage.SubstitutionOverrideCache) FirClassSubstitutionScopeKt.getSubstitutionOverrideStorage(firSession).getSubstitutionOverrideCacheByScope().getValue(coneSubstitutionScopeKey, null);
        this.newOwnerClassId = coneClassLikeType.getLookupTag().getClassId();
    }

    public static Unit b(FirClassSubstitutionScope firClassSubstitutionScope, Function1 function1, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        function1.invoke(firClassSubstitutionScope.substitutionOverrideCache.getOverridesForConstructors().getValue(firConstructorSymbol, firClassSubstitutionScope));
        return Unit.INSTANCE;
    }

    public static Unit c(FirClassSubstitutionScope firClassSubstitutionScope, Function1 function1, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if ((firVariableSymbol instanceof FirPropertySymbol) || (firVariableSymbol instanceof FirFieldSymbol)) {
            firVariableSymbol = (FirVariableSymbol) firClassSubstitutionScope.substitutionOverrideCache.getOverridesForVariables().getValue(firVariableSymbol, firClassSubstitutionScope);
        }
        function1.invoke(firVariableSymbol);
        return Unit.INSTANCE;
    }

    private final SubstitutedData createSubstitutedData(FirCallableDeclaration member, FirBasedSymbol<?> symbolForOverride) {
        FirBackingField explicitBackingField;
        FirTypeRef typeRef;
        ConeClassLikeLookupTag classLikeLookupTagIfAny = member instanceof FirConstructor ? ConeTypeUtilsKt.getClassLikeLookupTagIfAny(FirTypeUtilsKt.getConeType(((FirConstructor) member).getReturnTypeRef())) : ClassMembersKt.dispatchReceiverClassLookupTagOrNull(member);
        FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
        FirSession firSession = this.session;
        member.getClass();
        Pair<List<FirTypeParameterRef>, ConeSubstitutor> pairCreateNewTypeParametersAndSubstitutor = firFakeOverrideGenerator.createNewTypeParametersAndSubstitutor(firSession, member, symbolForOverride, this.substitutor, this.origin, !Intrinsics.areEqual(this.dispatchReceiverTypeForSubstitutedMembers.getLookupTag(), classLikeLookupTagIfAny));
        List list = (List) pairCreateNewTypeParametersAndSubstitutor.component1();
        ConeSubstitutor coneSubstitutor = (ConeSubstitutor) pairCreateNewTypeParametersAndSubstitutor.component2();
        FirReceiverParameter receiverParameter = member.getReceiverParameter();
        ReturnTypeData returnTypeDataCreateSubstitutedData$substituteOrPrepareDeferredSubstitutionForReturnType = null;
        ConeKotlinType coneType = (receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null) ? null : FirTypeUtilsKt.getConeType(typeRef);
        ConeKotlinType coneKotlinTypeSubstitute = coneType != null ? substitute(coneType, coneSubstitutor) : null;
        ConeSimpleKotlinType coneSimpleKotlinTypeSubstituteDispatchReceiverType = substituteDispatchReceiverType(this.dispatchReceiverTypeForSubstitutedMembers, coneSubstitutor);
        ReturnTypeData returnTypeDataCreateSubstitutedData$substituteOrPrepareDeferredSubstitutionForReturnType2 = createSubstitutedData$substituteOrPrepareDeferredSubstitutionForReturnType(member, this, coneSubstitutor);
        FirProperty firProperty = member instanceof FirProperty ? (FirProperty) member : null;
        if (firProperty != null && (explicitBackingField = DeclarationAttributesKt.getExplicitBackingField(firProperty)) != null) {
            returnTypeDataCreateSubstitutedData$substituteOrPrepareDeferredSubstitutionForReturnType = createSubstitutedData$substituteOrPrepareDeferredSubstitutionForReturnType(explicitBackingField, this, coneSubstitutor);
        }
        return new SubstitutedData(list, coneSimpleKotlinTypeSubstituteDispatchReceiverType, coneKotlinTypeSubstitute, coneSubstitutor, returnTypeDataCreateSubstitutedData$substituteOrPrepareDeferredSubstitutionForReturnType2, returnTypeDataCreateSubstitutedData$substituteOrPrepareDeferredSubstitutionForReturnType);
    }

    private static final ReturnTypeData createSubstitutedData$substituteOrPrepareDeferredSubstitutionForReturnType(FirCallableDeclaration firCallableDeclaration, FirClassSubstitutionScope firClassSubstitutionScope, ConeSubstitutor coneSubstitutor) {
        FirResolvedTypeRef returnTypeRef = firCallableDeclaration.getReturnTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (coneType == null) {
            coneType = null;
        }
        return new ReturnTypeData(coneType != null ? firClassSubstitutionScope.substitute(coneType, coneSubstitutor) : null, coneType == null ? new DeferredReturnTypeOfSubstitution(coneSubstitutor, firCallableDeclaration.getSymbol()) : null);
    }

    public static Unit d(FirClassSubstitutionScope firClassSubstitutionScope, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        function1.invoke(firClassSubstitutionScope.substitutionOverrideCache.getOverridesForFunctions().getValue(firNamedFunctionSymbol, firClassSubstitutionScope));
        return Unit.INSTANCE;
    }

    public static Unit e(Function2 function2, FirClassSubstitutionScope firClassSubstitutionScope, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        function2.invoke(firClassifierSymbol, ChainedSubstitutorKt.chain(coneSubstitutor, firClassSubstitutionScope.substitutor));
        return Unit.INSTANCE;
    }

    private final ConeKotlinType substitute(ConeKotlinType coneKotlinType) {
        return this.substitutor.substituteOrNull(coneKotlinType);
    }

    private final ConeSimpleKotlinType substituteDispatchReceiverType(ConeSimpleKotlinType coneSimpleKotlinType, ConeSubstitutor coneSubstitutor) {
        ConeKotlinType coneKotlinTypeSubstituteOrNull = coneSubstitutor.substituteOrNull(coneSimpleKotlinType);
        return (ConeSimpleKotlinType) (coneKotlinTypeSubstituteOrNull != null ? ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeSubstituteOrNull) : null);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:32:0x00d3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:33:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:36:0x00de  */
    /* JADX WARN: Code duplicated, block: B:51:0x00e8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:52:? A[LOOP:3: B:34:0x00d8->B:52:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirConstructorSymbol createSubstitutionOverrideConstructor(FirConstructorSymbol original) {
        Iterator it;
        original.getClass();
        if (Intrinsics.areEqual(this.substitutor, ConeSubstitutor.Empty.INSTANCE)) {
            return original;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(original, FirResolvePhase.TYPES);
        FirConstructor firConstructor = (FirConstructor) original.getFir();
        FirConstructorSymbol firConstructorSymbol = new FirConstructorSymbol(original.getCallableId());
        SubstitutedData substitutedDataCreateSubstitutedData = createSubstitutedData(firConstructor, firConstructorSymbol);
        List<FirTypeParameterRef> listComponent1 = substitutedDataCreateSubstitutedData.component1();
        ConeSubstitutor substitutor = substitutedDataCreateSubstitutedData.getSubstitutor();
        ReturnTypeData returnTypeData = substitutedDataCreateSubstitutedData.getReturnTypeData();
        ConeSimpleKotlinType dispatchReceiverType = original.getDispatchReceiverType();
        ConeSimpleKotlinType coneSimpleKotlinTypeSubstituteDispatchReceiverType = dispatchReceiverType != null ? substituteDispatchReceiverType(dispatchReceiverType, this.substitutor) : null;
        List<FirValueParameter> valueParameters = firConstructor.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it2 = valueParameters.iterator();
        while (it2.hasNext()) {
            arrayList.add(substitute(FirTypeUtilsKt.getConeType(((FirValueParameter) it2.next()).getReturnTypeRef()), substitutor));
        }
        List<FirValueParameter> contextParameters = firConstructor.getContextParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
        Iterator<T> it3 = contextParameters.iterator();
        while (it3.hasNext()) {
            arrayList2.add(substitute(FirTypeUtilsKt.getConeType(((FirValueParameter) it3.next()).getReturnTypeRef()), substitutor));
        }
        if (returnTypeData.getNewReturnType() == null) {
            if (!arrayList.isEmpty()) {
                Iterator it4 = arrayList.iterator();
                while (true) {
                    if (it4.hasNext()) {
                        if (((ConeKotlinType) it4.next()) == null) {
                        }
                    } else if (listComponent1 == firConstructor.getTypeParameters()) {
                        if (arrayList2.isEmpty()) {
                            return original;
                        }
                        it = arrayList2.iterator();
                        while (it.hasNext()) {
                            if (((ConeKotlinType) it.next()) == null) {
                            }
                        }
                        return original;
                    }
                }
            } else if (listComponent1 == firConstructor.getTypeParameters()) {
                if (arrayList2.isEmpty()) {
                    return original;
                }
                it = arrayList2.iterator();
                while (it.hasNext()) {
                    if (((ConeKotlinType) it.next()) == null) {
                    }
                }
                return original;
            }
        }
        FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
        FirSession firSession = this.session;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = null;
        ConeClassLikeLookupTag coneClassLikeLookupTag = this.derivedClassLookupTag;
        FirDeclarationOrigin.SubstitutionOverride substitutionOverride = this.origin;
        ConeKotlinType newReturnType = returnTypeData.getNewReturnType();
        if (newReturnType != null) {
            coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(newReturnType);
        }
        return firFakeOverrideGenerator.createCopyForFirConstructor(firConstructorSymbol, firSession, firConstructor, coneClassLikeLookupTag, substitutionOverride, coneSimpleKotlinTypeSubstituteDispatchReceiverType, coneRigidTypeLowerBoundIfFlexible, arrayList, arrayList2, listComponent1, this.makeExpect, returnTypeData.getDeferredReturnTypeOfSubstitution(), (4096 & 4096) != 0 ? null : null).getSymbol();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirFieldSymbol createSubstitutionOverrideField(FirFieldSymbol original) {
        ConeKotlinType coneKotlinTypeSubstitute;
        original.getClass();
        if (Intrinsics.areEqual(this.substitutor, ConeSubstitutor.Empty.INSTANCE)) {
            return original;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(original, FirResolvePhase.TYPES);
        FirField firField = (FirField) original.getFir();
        if (!this.skipPrivateMembers || !Intrinsics.areEqual(firField.getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
            FirResolvedTypeRef returnTypeRef = firField.getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            ConeKotlinType coneKotlinType = coneType != null ? coneType : null;
            if (coneKotlinType != null && (coneKotlinTypeSubstitute = substitute(coneKotlinType)) != null) {
                return FirFakeOverrideGenerator.INSTANCE.createSubstitutionOverrideField(this.session, firField, this.derivedClassLookupTag, coneKotlinTypeSubstitute, this.dispatchReceiverTypeForSubstitutedMembers, this.origin);
            }
        }
        return original;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:41:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:46:0x0104  */
    /* JADX WARN: Code duplicated, block: B:47:0x010b  */
    /* JADX WARN: Code duplicated, block: B:49:0x010e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0118  */
    /* JADX WARN: Code duplicated, block: B:65:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00d6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:? A[LOOP:3: B:39:0x00ef->B:67:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirNamedFunctionSymbol createSubstitutionOverrideFunction(FirNamedFunctionSymbol original) {
        ConeSimpleKotlinType dispatchReceiverType;
        ConeSimpleKotlinType coneSimpleKotlinTypeSubstituteDispatchReceiverType;
        ConeSimpleKotlinType coneSimpleKotlinType;
        Iterator it;
        original.getClass();
        if (Intrinsics.areEqual(this.substitutor, ConeSubstitutor.Empty.INSTANCE)) {
            return original;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(original, FirResolvePhase.TYPES);
        FirNamedFunction firNamedFunction = (FirNamedFunction) original.getFir();
        if (!this.skipPrivateMembers || !Intrinsics.areEqual(firNamedFunction.getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
            FirNamedFunctionSymbol firNamedFunctionSymbolCreateSymbolForSubstitutionOverride = FirFakeOverrideGenerator.INSTANCE.createSymbolForSubstitutionOverride(original, this.newOwnerClassId);
            SubstitutedData substitutedDataCreateSubstitutedData = createSubstitutedData(firNamedFunction, firNamedFunctionSymbolCreateSymbolForSubstitutionOverride);
            List<FirTypeParameterRef> listComponent1 = substitutedDataCreateSubstitutedData.component1();
            ConeSimpleKotlinType dispatchReceiverType2 = substitutedDataCreateSubstitutedData.getDispatchReceiverType();
            ConeKotlinType receiverType = substitutedDataCreateSubstitutedData.getReceiverType();
            ConeSubstitutor substitutor = substitutedDataCreateSubstitutedData.getSubstitutor();
            ReturnTypeData returnTypeData = substitutedDataCreateSubstitutedData.getReturnTypeData();
            List<FirValueParameter> valueParameters = firNamedFunction.getValueParameters();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
            Iterator<T> it2 = valueParameters.iterator();
            while (it2.hasNext()) {
                arrayList.add(substitute(FirTypeUtilsKt.getConeType(((FirValueParameter) it2.next()).getReturnTypeRef()), substitutor));
            }
            List<FirValueParameter> contextParameters = firNamedFunction.getContextParameters();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
            Iterator<T> it3 = contextParameters.iterator();
            while (it3.hasNext()) {
                arrayList2.add(substitute(FirTypeUtilsKt.getConeType(((FirValueParameter) it3.next()).getReturnTypeRef()), substitutor));
            }
            if (receiverType == null && returnTypeData.getNewReturnType() == null) {
                if (!arrayList.isEmpty()) {
                    Iterator it4 = arrayList.iterator();
                    while (true) {
                        if (it4.hasNext()) {
                            if (((ConeKotlinType) it4.next()) == null) {
                            }
                        } else if (listComponent1 == firNamedFunction.getTypeParameters()) {
                            if (!arrayList2.isEmpty()) {
                                it = arrayList2.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        if (((ConeKotlinType) it.next()) == null) {
                                        }
                                    }
                                }
                            }
                            dispatchReceiverType = original.getDispatchReceiverType();
                            if (dispatchReceiverType != null) {
                                coneSimpleKotlinTypeSubstituteDispatchReceiverType = substituteDispatchReceiverType(dispatchReceiverType, this.substitutor);
                            } else {
                                coneSimpleKotlinTypeSubstituteDispatchReceiverType = null;
                            }
                            if (coneSimpleKotlinTypeSubstituteDispatchReceiverType != null) {
                                FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
                                coneSimpleKotlinType = dispatchReceiverType2;
                                FirSession firSession = this.session;
                                ConeClassLikeLookupTag coneClassLikeLookupTag = this.derivedClassLookupTag;
                                if (coneSimpleKotlinType == null) {
                                    coneSimpleKotlinType = this.dispatchReceiverTypeForSubstitutedMembers;
                                }
                                return firFakeOverrideGenerator.createSubstitutionOverrideFunction(firSession, firNamedFunctionSymbolCreateSymbolForSubstitutionOverride, firNamedFunction, coneClassLikeLookupTag, coneSimpleKotlinType, this.origin, (6080 & 64) != 0 ? null : null, (List<? extends ConeKotlinType>) ((6080 & 128) != 0 ? null : null), (6080 & 256) != 0 ? null : null, (List<? extends ConeKotlinType>) ((6080 & 512) != 0 ? null : null), (List<? extends FirTypeParameter>) ((6080 & 1024) != 0 ? null : null), (6080 & 2048) != 0 ? firNamedFunction.getStatus().isExpect() : this.makeExpect, (6080 & 4096) != 0 ? null : null);
                            }
                        }
                    }
                } else if (listComponent1 == firNamedFunction.getTypeParameters() && returnTypeData.getDeferredReturnTypeOfSubstitution() == null) {
                    if (!arrayList2.isEmpty()) {
                        it = arrayList2.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (((ConeKotlinType) it.next()) == null) {
                                }
                            }
                        }
                    }
                    dispatchReceiverType = original.getDispatchReceiverType();
                    if (dispatchReceiverType != null) {
                        coneSimpleKotlinTypeSubstituteDispatchReceiverType = substituteDispatchReceiverType(dispatchReceiverType, this.substitutor);
                    } else {
                        coneSimpleKotlinTypeSubstituteDispatchReceiverType = null;
                    }
                    if (coneSimpleKotlinTypeSubstituteDispatchReceiverType != null) {
                        FirFakeOverrideGenerator firFakeOverrideGenerator2 = FirFakeOverrideGenerator.INSTANCE;
                        coneSimpleKotlinType = dispatchReceiverType2;
                        FirSession firSession2 = this.session;
                        ConeClassLikeLookupTag coneClassLikeLookupTag2 = this.derivedClassLookupTag;
                        if (coneSimpleKotlinType == null) {
                            coneSimpleKotlinType = this.dispatchReceiverTypeForSubstitutedMembers;
                        }
                        return firFakeOverrideGenerator2.createSubstitutionOverrideFunction(firSession2, firNamedFunctionSymbolCreateSymbolForSubstitutionOverride, firNamedFunction, coneClassLikeLookupTag2, coneSimpleKotlinType, this.origin, (6080 & 64) != 0 ? null : null, (List<? extends ConeKotlinType>) ((6080 & 128) != 0 ? null : null), (6080 & 256) != 0 ? null : null, (List<? extends ConeKotlinType>) ((6080 & 512) != 0 ? null : null), (List<? extends FirTypeParameter>) ((6080 & 1024) != 0 ? null : null), (6080 & 2048) != 0 ? firNamedFunction.getStatus().isExpect() : this.makeExpect, (6080 & 4096) != 0 ? null : null);
                    }
                }
            }
            ConeSimpleKotlinType coneSimpleKotlinType2 = dispatchReceiverType2;
            FirFakeOverrideGenerator firFakeOverrideGenerator3 = FirFakeOverrideGenerator.INSTANCE;
            FirSession firSession3 = this.session;
            ConeClassLikeLookupTag coneClassLikeLookupTag3 = this.derivedClassLookupTag;
            if (coneSimpleKotlinType2 == null) {
                coneSimpleKotlinType2 = this.dispatchReceiverTypeForSubstitutedMembers;
            }
            FirDeclarationOrigin.SubstitutionOverride substitutionOverride = this.origin;
            ConeKotlinType newReturnType = returnTypeData.getNewReturnType();
            listComponent1.getClass();
            return firFakeOverrideGenerator3.createSubstitutionOverrideFunction(firSession3, firNamedFunctionSymbolCreateSymbolForSubstitutionOverride, firNamedFunction, coneClassLikeLookupTag3, coneSimpleKotlinType2, substitutionOverride, receiverType, arrayList2, newReturnType, arrayList, listComponent1, this.makeExpect, returnTypeData.getDeferredReturnTypeOfSubstitution());
        }
        return original;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:34:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:36:0x00c4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:37:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:38:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:40:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:42:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:44:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:46:0x00e5  */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirPropertySymbol createSubstitutionOverrideProperty(FirPropertySymbol original) {
        ConeKotlinType newReturnType;
        DeferredReturnTypeOfSubstitution deferredReturnTypeOfSubstitution;
        ConeSimpleKotlinType dispatchReceiverType;
        ConeSimpleKotlinType coneSimpleKotlinType;
        original.getClass();
        if (Intrinsics.areEqual(this.substitutor, ConeSubstitutor.Empty.INSTANCE)) {
            return original;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(original, FirResolvePhase.TYPES);
        FirProperty firProperty = (FirProperty) original.getFir();
        if (!this.skipPrivateMembers || !Intrinsics.areEqual(firProperty.getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
            FirRegularPropertySymbol firRegularPropertySymbolCreateSymbolForSubstitutionOverride = FirFakeOverrideGenerator.INSTANCE.createSymbolForSubstitutionOverride(original, this.newOwnerClassId);
            SubstitutedData substitutedDataCreateSubstitutedData = createSubstitutedData(firProperty, firRegularPropertySymbolCreateSymbolForSubstitutionOverride);
            List<FirTypeParameterRef> listComponent1 = substitutedDataCreateSubstitutedData.component1();
            ConeSimpleKotlinType dispatchReceiverType2 = substitutedDataCreateSubstitutedData.getDispatchReceiverType();
            ConeKotlinType receiverType = substitutedDataCreateSubstitutedData.getReceiverType();
            ConeSubstitutor substitutor = substitutedDataCreateSubstitutedData.getSubstitutor();
            ReturnTypeData returnTypeData = substitutedDataCreateSubstitutedData.getReturnTypeData();
            ReturnTypeData explicitBackingFieldReturnTypeData = substitutedDataCreateSubstitutedData.getExplicitBackingFieldReturnTypeData();
            List<FirValueParameter> contextParameters = firProperty.getContextParameters();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
            Iterator<T> it = contextParameters.iterator();
            while (it.hasNext()) {
                arrayList.add(substitute(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef()), substitutor));
            }
            if (receiverType == null && returnTypeData.getNewReturnType() == null && listComponent1 == firProperty.getTypeParameters() && returnTypeData.getDeferredReturnTypeOfSubstitution() == null) {
                if (arrayList.isEmpty()) {
                    if (explicitBackingFieldReturnTypeData != null) {
                        newReturnType = explicitBackingFieldReturnTypeData.getNewReturnType();
                    } else {
                        newReturnType = null;
                    }
                    if (newReturnType == null) {
                        if (explicitBackingFieldReturnTypeData != null) {
                            deferredReturnTypeOfSubstitution = explicitBackingFieldReturnTypeData.getDeferredReturnTypeOfSubstitution();
                        } else {
                            deferredReturnTypeOfSubstitution = null;
                        }
                        if (deferredReturnTypeOfSubstitution == null) {
                            dispatchReceiverType = original.getDispatchReceiverType();
                            if ((dispatchReceiverType != null ? substituteDispatchReceiverType(dispatchReceiverType, this.substitutor) : null) != null) {
                                coneSimpleKotlinType = dispatchReceiverType2;
                                FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
                                FirSession firSession = this.session;
                                ConeClassLikeLookupTag coneClassLikeLookupTag = this.derivedClassLookupTag;
                                if (coneSimpleKotlinType == null) {
                                    coneSimpleKotlinType = this.dispatchReceiverTypeForSubstitutedMembers;
                                }
                                return firFakeOverrideGenerator.createSubstitutionOverrideProperty(firSession, firRegularPropertySymbolCreateSymbolForSubstitutionOverride, firProperty, coneClassLikeLookupTag, coneSimpleKotlinType, this.origin, (15296 & 64) != 0 ? null : null, (15296 & 128) != 0 ? null : null, (15296 & 256) != 0 ? null : null, (15296 & 512) != 0 ? null : null, (15296 & 1024) != 0 ? firProperty.getStatus().isExpect() : this.makeExpect, (15296 & 2048) != 0 ? null : null, (15296 & 4096) != 0 ? null : null, (15296 & 8192) != 0 ? null : null);
                            }
                        }
                    }
                } else {
                    Iterator it2 = arrayList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            if (explicitBackingFieldReturnTypeData != null) {
                                newReturnType = explicitBackingFieldReturnTypeData.getNewReturnType();
                            } else {
                                newReturnType = null;
                            }
                            if (newReturnType == null) {
                                if (explicitBackingFieldReturnTypeData != null) {
                                    deferredReturnTypeOfSubstitution = explicitBackingFieldReturnTypeData.getDeferredReturnTypeOfSubstitution();
                                } else {
                                    deferredReturnTypeOfSubstitution = null;
                                }
                                if (deferredReturnTypeOfSubstitution == null) {
                                    dispatchReceiverType = original.getDispatchReceiverType();
                                    if ((dispatchReceiverType != null ? substituteDispatchReceiverType(dispatchReceiverType, this.substitutor) : null) != null) {
                                        coneSimpleKotlinType = dispatchReceiverType2;
                                        FirFakeOverrideGenerator firFakeOverrideGenerator2 = FirFakeOverrideGenerator.INSTANCE;
                                        FirSession firSession2 = this.session;
                                        ConeClassLikeLookupTag coneClassLikeLookupTag2 = this.derivedClassLookupTag;
                                        if (coneSimpleKotlinType == null) {
                                            coneSimpleKotlinType = this.dispatchReceiverTypeForSubstitutedMembers;
                                        }
                                        return firFakeOverrideGenerator2.createSubstitutionOverrideProperty(firSession2, firRegularPropertySymbolCreateSymbolForSubstitutionOverride, firProperty, coneClassLikeLookupTag2, coneSimpleKotlinType, this.origin, (15296 & 64) != 0 ? null : null, (15296 & 128) != 0 ? null : null, (15296 & 256) != 0 ? null : null, (15296 & 512) != 0 ? null : null, (15296 & 1024) != 0 ? firProperty.getStatus().isExpect() : this.makeExpect, (15296 & 2048) != 0 ? null : null, (15296 & 4096) != 0 ? null : null, (15296 & 8192) != 0 ? null : null);
                                    }
                                }
                            }
                        } else if (((ConeKotlinType) it2.next()) == null) {
                        }
                    }
                }
            }
            ConeSimpleKotlinType coneSimpleKotlinType2 = dispatchReceiverType2;
            FirFakeOverrideGenerator firFakeOverrideGenerator3 = FirFakeOverrideGenerator.INSTANCE;
            DeferredReturnTypeOfSubstitution deferredReturnTypeOfSubstitution2 = null;
            FirSession firSession3 = this.session;
            ConeClassLikeLookupTag coneClassLikeLookupTag3 = this.derivedClassLookupTag;
            if (coneSimpleKotlinType2 == null) {
                coneSimpleKotlinType2 = this.dispatchReceiverTypeForSubstitutedMembers;
            }
            FirDeclarationOrigin.SubstitutionOverride substitutionOverride = this.origin;
            ConeKotlinType newReturnType2 = returnTypeData.getNewReturnType();
            listComponent1.getClass();
            boolean z = this.makeExpect;
            DeferredReturnTypeOfSubstitution deferredReturnTypeOfSubstitution3 = returnTypeData.getDeferredReturnTypeOfSubstitution();
            ConeKotlinType newReturnType3 = explicitBackingFieldReturnTypeData != null ? explicitBackingFieldReturnTypeData.getNewReturnType() : null;
            if (explicitBackingFieldReturnTypeData != null) {
                deferredReturnTypeOfSubstitution2 = explicitBackingFieldReturnTypeData.getDeferredReturnTypeOfSubstitution();
            }
            return firFakeOverrideGenerator3.createSubstitutionOverrideProperty(firSession3, firRegularPropertySymbolCreateSymbolForSubstitutionOverride, firProperty, coneClassLikeLookupTag3, coneSimpleKotlinType2, substitutionOverride, receiverType, arrayList, newReturnType2, listComponent1, z, deferredReturnTypeOfSubstitution3, newReturnType3, deferredReturnTypeOfSubstitution2);
        }
        return original;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return this.useSiteMemberScope.getCallableNames();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return this.useSiteMemberScope.getClassifierNames();
    }

    public final ConeSubstitutor getSubstitutor() {
        return this.substitutor;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, final Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.useSiteMemberScope.processClassifiersByNameWithSubstitution(name, new Function2() { // from class: mz4
            public final Object invoke(Object obj, Object obj2) {
                return FirClassSubstitutionScope.e(processor, this, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(final Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.useSiteMemberScope.processDeclaredConstructors(new Function1() { // from class: lz4
            public final Object invoke(Object obj) {
                return FirClassSubstitutionScope.b(this.b, processor, (FirConstructorSymbol) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) functionSymbol.getFir();
        FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
        FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null);
        if (firNamedFunctionSymbol == null || !FirCacheWithPostComputeKt.contains(this.substitutionOverrideCache.getOverridesForFunctions(), firNamedFunctionSymbol)) {
            return this.useSiteMemberScope.processDirectOverriddenFunctionsWithBaseScope(functionSymbol, processor);
        }
        return ((ProcessorAction) processor.invoke(firNamedFunctionSymbol, this.useSiteMemberScope)).not() ? ProcessorAction.STOP : ProcessorAction.NONE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) propertySymbol.getFir();
        FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
        FirPropertySymbol firPropertySymbol = (FirPropertySymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null);
        if (firPropertySymbol == null || !FirCacheWithPostComputeKt.contains(this.substitutionOverrideCache.getOverridesForVariables(), firPropertySymbol)) {
            return this.useSiteMemberScope.processDirectOverriddenPropertiesWithBaseScope(propertySymbol, processor);
        }
        return ((ProcessorAction) processor.invoke(firPropertySymbol, this.useSiteMemberScope)).not() ? ProcessorAction.STOP : ProcessorAction.NONE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, final Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.useSiteMemberScope.processFunctionsByName(name, new Function1() { // from class: kz4
            public final Object invoke(Object obj) {
                return FirClassSubstitutionScope.d(this.b, processor, (FirNamedFunctionSymbol) obj);
            }
        });
        super.processFunctionsByName(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, final Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.useSiteMemberScope.processPropertiesByName(name, new Function1() { // from class: jz4
            public final Object invoke(Object obj) {
                return FirClassSubstitutionScope.c(this.b, processor, (FirVariableSymbol) obj);
            }
        });
    }

    public String toString() {
        return "Substitution scope for [" + this.useSiteMemberScope + "] for type " + this.dispatchReceiverTypeForSubstitutedMembers;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirClassSubstitutionScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirTypeScope firTypeScopeWithReplacedSessionOrNull = this.useSiteMemberScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firTypeScopeWithReplacedSessionOrNull == null) {
            firTypeScopeWithReplacedSessionOrNull = this.useSiteMemberScope;
        }
        return new FirClassSubstitutionScope(newSession, firTypeScopeWithReplacedSessionOrNull, this.key, this.substitutor, this.dispatchReceiverTypeForSubstitutedMembers, this.skipPrivateMembers, this.makeExpect, this.derivedClassLookupTag, this.origin);
    }

    private final ConeKotlinType substitute(ConeKotlinType coneKotlinType, ConeSubstitutor coneSubstitutor) {
        return coneSubstitutor.substituteOrNull(coneKotlinType);
    }

    public /* synthetic */ FirClassSubstitutionScope(FirSession firSession, FirTypeScope firTypeScope, ConeSubstitutionScopeKey coneSubstitutionScopeKey, ConeSubstitutor coneSubstitutor, ConeClassLikeType coneClassLikeType, boolean z, boolean z2, ConeClassLikeLookupTag coneClassLikeLookupTag, FirDeclarationOrigin.SubstitutionOverride substitutionOverride, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firTypeScope, coneSubstitutionScopeKey, coneSubstitutor, coneClassLikeType, z, (i & 64) != 0 ? false : z2, coneClassLikeLookupTag, substitutionOverride);
    }
}
