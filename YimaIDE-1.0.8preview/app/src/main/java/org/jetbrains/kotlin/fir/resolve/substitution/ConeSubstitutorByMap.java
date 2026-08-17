package org.jetbrains.kotlin.fir.resolve.substitution;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMap;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB%\b\u0002\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0016J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutorByMap;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor;", "substitution", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Ljava/util/Map;Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSubstitution", "()Ljava/util/Map;", "hashCode", Argument.Delimiters.none, "getHashCode", "()I", "hashCode$delegate", "Lkotlin/Lazy;", "substituteType", ModuleXmlParser.TYPE, "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeSubstitutorByMap extends AbstractConeSubstitutor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: hashCode$delegate, reason: from kotlin metadata */
    private final Lazy hashCode;
    private final Map<FirTypeParameterSymbol, ConeKotlinType> substitution;
    private final FirSession useSiteSession;

    private ConeSubstitutorByMap(Map<FirTypeParameterSymbol, ? extends ConeKotlinType> map, FirSession firSession) {
        super(TypeComponentsKt.getTypeContext(firSession));
        this.substitution = map;
        this.useSiteSession = firSession;
        this.hashCode = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: dq2
            public final Object invoke() {
                return Integer.valueOf(ConeSubstitutorByMap.c(this.b));
            }
        });
    }

    public static CharSequence b(Map.Entry entry) {
        entry.getClass();
        return ((FirTypeParameterSymbol) entry.getKey()).getName() + " -> " + ConeTypeUtilsKt.renderForDebugging((ConeKotlinType) entry.getValue());
    }

    public static int c(ConeSubstitutorByMap coneSubstitutorByMap) {
        return coneSubstitutorByMap.substitution.hashCode();
    }

    private final int getHashCode() {
        return ((Number) this.hashCode.getValue()).intValue();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConeSubstitutorByMap)) {
            return false;
        }
        ConeSubstitutorByMap coneSubstitutorByMap = (ConeSubstitutorByMap) other;
        return getHashCode() == coneSubstitutorByMap.getHashCode() && Intrinsics.areEqual(this.substitution, coneSubstitutorByMap.substitution) && Intrinsics.areEqual(this.useSiteSession, coneSubstitutorByMap.useSiteSession);
    }

    public final Map<FirTypeParameterSymbol, ConeKotlinType> getSubstitution() {
        return this.substitution;
    }

    public int hashCode() {
        return getHashCode();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
    public ConeKotlinType substituteType(ConeKotlinType type) {
        ConeKotlinType coneKotlinType;
        ConeKotlinType coneKotlinTypeUpdateNullabilityIfNeeded;
        type.getClass();
        if (!(type instanceof ConeTypeParameterType) || (coneKotlinType = this.substitution.get(((ConeTypeParameterType) type).getLookupTag().getSymbol())) == null || (coneKotlinTypeUpdateNullabilityIfNeeded = updateNullabilityIfNeeded(coneKotlinType, type)) == null) {
            return null;
        }
        return LookupTagUtilsKt.withCombinedAttributesFrom(coneKotlinTypeUpdateNullabilityIfNeeded, type);
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.substitution.entrySet(), " | ", "{", "}", 0, (CharSequence) null, new Function1() { // from class: eq2
            public final Object invoke(Object obj) {
                return ConeSubstitutorByMap.b((Map.Entry) obj);
            }
        }, 24, (Object) null);
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutorByMap$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "substitution", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "allowIdenticalSubstitution", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConeSubstitutor create(Map<FirTypeParameterSymbol, ? extends ConeKotlinType> substitution, FirSession useSiteSession, boolean allowIdenticalSubstitution) {
            ConeTypeParameterLookupTag lookupTag;
            substitution.getClass();
            useSiteSession.getClass();
            if (substitution.isEmpty()) {
                return ConeSubstitutor.Empty.INSTANCE;
            }
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (!allowIdenticalSubstitution) {
                if (!substitution.isEmpty()) {
                    for (Map.Entry<FirTypeParameterSymbol, ? extends ConeKotlinType> entry : substitution.entrySet()) {
                        FirTypeParameterSymbol key = entry.getKey();
                        ConeKotlinType value = entry.getValue();
                        ConeTypeParameterType coneTypeParameterType = value instanceof ConeTypeParameterType ? (ConeTypeParameterType) value : null;
                        if (!Intrinsics.areEqual((coneTypeParameterType == null || (lookupTag = coneTypeParameterType.getLookupTag()) == null) ? null : lookupTag.getTypeParameterSymbol(), key) || ((ConeTypeParameterType) value).getIsMarkedNullable()) {
                        }
                    }
                }
                return ConeSubstitutor.Empty.INSTANCE;
            }
            return new ConeSubstitutorByMap(substitution, useSiteSession, defaultConstructorMarker);
        }

        private Companion() {
        }
    }

    public /* synthetic */ ConeSubstitutorByMap(Map map, FirSession firSession, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, firSession);
    }
}
