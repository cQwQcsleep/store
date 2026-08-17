package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorProperty;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.resolve.dfa.RealVariable;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.types.SmartcastStability;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 (2\u00020\u0001:\u0001(B7\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0000\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0018\u0010!\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\"\u001a\u00020#8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b$\u0010%¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isImplicit", Argument.Delimiters.none, "dispatchReceiver", "extensionReceiver", "originalType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;ZLorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "()Z", "getDispatchReceiver", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", "getExtensionReceiver", "getOriginalType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "getStability", "Lorg/jetbrains/kotlin/types/SmartcastStability;", "flow", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Flow;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "hasFinalType", "propertyStability", "Lorg/jetbrains/kotlin/fir/resolve/dfa/PropertyStability;", "getPropertyStability", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/PropertyStability;", "propertyStability$delegate", "Lkotlin/Lazy;", "Companion", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RealVariable extends DataFlowVariable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final RealVariable dispatchReceiver;
    private final RealVariable extensionReceiver;
    private final boolean isImplicit;
    private final ConeKotlinType originalType;

    /* JADX INFO: renamed from: propertyStability$delegate, reason: from kotlin metadata */
    private final Lazy propertyStability;
    private final FirBasedSymbol<?> symbol;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealVariable(FirBasedSymbol<?> firBasedSymbol, boolean z, RealVariable realVariable, RealVariable realVariable2, ConeKotlinType coneKotlinType) {
        super(null);
        firBasedSymbol.getClass();
        coneKotlinType.getClass();
        this.symbol = firBasedSymbol;
        this.isImplicit = z;
        this.dispatchReceiver = realVariable;
        this.extensionReceiver = realVariable2;
        this.originalType = coneKotlinType;
        this.propertyStability = LazyKt.lazy(new Function0() { // from class: j7c
            public final Object invoke() {
                return RealVariable.a(this.b);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static PropertyStability a(RealVariable realVariable) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirDeclaration fir = realVariable.symbol.getFir();
        if ((fir instanceof FirVariable) && !(fir instanceof FirEnumEntry) && !(fir instanceof FirErrorProperty) && !(fir instanceof FirValueParameter)) {
            if (fir instanceof FirBackingField) {
                return ((FirBackingField) fir).isVal() ? PropertyStability.PRIVATE_OR_CONST_VAL : PropertyStability.MUTABLE_PROPERTY;
            }
            if (fir instanceof FirField) {
                return ((FirField) fir).isVal() ? PropertyStability.PUBLIC_FINAL_VAL : PropertyStability.MUTABLE_PROPERTY;
            }
            if (!(fir instanceof FirProperty)) {
                bu8.a();
                return null;
            }
            FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) fir;
            if (firMemberDeclaration.getStatus().isExpect()) {
                return PropertyStability.EXPECT_PROPERTY;
            }
            FirProperty firProperty = (FirProperty) fir;
            if (firProperty.getDelegate() != null) {
                return PropertyStability.DELEGATED_PROPERTY;
            }
            if (firProperty.getSymbol() instanceof FirLocalPropertySymbol) {
                return firProperty.isVal() ? PropertyStability.PRIVATE_OR_CONST_VAL : PropertyStability.CAPTURED_VARIABLE;
            }
            if (firProperty.getIsVar()) {
                return PropertyStability.MUTABLE_PROPERTY;
            }
            if (firProperty.getReceiverParameter() != null) {
                return PropertyStability.PROPERTY_WITH_GETTER;
            }
            FirPropertyAccessor getter = firProperty.getGetter();
            if (!(getter == null ? true : getter instanceof FirDefaultPropertyAccessor)) {
                return PropertyStability.PROPERTY_WITH_GETTER;
            }
            if (!Intrinsics.areEqual(firMemberDeclaration.getStatus().getVisibility(), Visibilities.Private.INSTANCE) && !Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(fir), Boolean.TRUE)) {
                Modality modality = firMemberDeclaration.getStatus().getModality();
                return (modality == null || modality == Modality.FINAL) ? PropertyStability.PUBLIC_FINAL_VAL : PropertyStability.PUBLIC_OPEN_VAL;
            }
            return PropertyStability.PRIVATE_OR_CONST_VAL;
        }
        return PropertyStability.PRIVATE_OR_CONST_VAL;
    }

    private final PropertyStability getPropertyStability() {
        return (PropertyStability) this.propertyStability.getValue();
    }

    private final boolean hasFinalType(Flow flow, FirSession session) {
        Set<ConeKotlinType> upperTypes;
        if (DfaVariablesKt.isFinal(getOriginalType(), session)) {
            return true;
        }
        TypeStatement typeStatement = flow.getTypeStatement(this);
        if (typeStatement == null || (upperTypes = typeStatement.getUpperTypes()) == null) {
            return false;
        }
        Set<ConeKotlinType> set = upperTypes;
        if ((set instanceof Collection) && set.isEmpty()) {
            return false;
        }
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            if (DfaVariablesKt.isFinal((ConeKotlinType) it.next(), session)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object other) {
        if (!(other instanceof RealVariable)) {
            return false;
        }
        RealVariable realVariable = (RealVariable) other;
        return Intrinsics.areEqual(this.symbol, realVariable.symbol) && this.isImplicit == realVariable.isImplicit && Intrinsics.areEqual(this.dispatchReceiver, realVariable.dispatchReceiver) && Intrinsics.areEqual(this.extensionReceiver, realVariable.extensionReceiver);
    }

    public final RealVariable getDispatchReceiver() {
        return this.dispatchReceiver;
    }

    public final RealVariable getExtensionReceiver() {
        return this.extensionReceiver;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.DataFlowVariable
    public ConeKotlinType getOriginalType() {
        return this.originalType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final SmartcastStability getStability(Flow flow, FirSession session) {
        SmartcastStability stability;
        RealVariable realVariable;
        flow.getClass();
        session.getClass();
        if (!this.isImplicit) {
            PropertyStability propertyStability = getPropertyStability();
            if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).supportsFeature(LanguageFeature.UnstableSmartcastOnDelegatedProperties)) {
                FirDeclaration fir = this.symbol.getFir();
                FirProperty firProperty = fir instanceof FirProperty ? (FirProperty) fir : null;
                if (firProperty != null && ClassMembersKt.isDelegated(firProperty)) {
                    return SmartcastStability.DELEGATED_PROPERTY;
                }
            }
            SmartcastStability inherentInstability = propertyStability.getInherentInstability();
            if (inherentInstability != null) {
                return inherentInstability;
            }
            FirBasedSymbol<?> firBasedSymbol = this.symbol;
            if ((firBasedSymbol instanceof FirPropertySymbol) && FirExpressionUtilKt.isImplicitWhenSubjectVariable((FirVariable) ((FirPropertySymbol) firBasedSymbol).getFir())) {
                RealVariable realVariableUnwrapVariable = flow.unwrapVariable(this);
                if (Intrinsics.areEqual(realVariableUnwrapVariable, this)) {
                    realVariableUnwrapVariable = null;
                }
                if (realVariableUnwrapVariable != null) {
                    return realVariableUnwrapVariable.getStability(flow, session);
                }
            }
            if (propertyStability.getCheckReceiver() && (realVariable = this.dispatchReceiver) != null && !realVariable.hasFinalType(flow, session)) {
                return SmartcastStability.PROPERTY_WITH_GETTER;
            }
            if (propertyStability.getCheckModule()) {
                FirDeclaration fir2 = this.symbol.getFir();
                fir2.getClass();
                if (!DfaVariablesKt.isInCurrentOrFriendModule((FirVariable) fir2, session)) {
                    return SmartcastStability.ALIEN_PUBLIC_PROPERTY;
                }
            }
            RealVariable realVariable2 = this.dispatchReceiver;
            if (realVariable2 != null && (stability = realVariable2.getStability(flow, session)) != null) {
                SmartcastStability smartcastStability = stability != SmartcastStability.STABLE_VALUE ? stability : null;
                if (smartcastStability != null) {
                    return smartcastStability;
                }
            }
        }
        return SmartcastStability.STABLE_VALUE;
    }

    public final FirBasedSymbol<?> getSymbol() {
        return this.symbol;
    }

    public int hashCode() {
        return Objects.hash(this.symbol, Boolean.valueOf(this.isImplicit), this.dispatchReceiver, this.extensionReceiver);
    }

    /* JADX INFO: renamed from: isImplicit, reason: from getter */
    public final boolean getIsImplicit() {
        return this.isImplicit;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.isImplicit) {
            sb.append(this.symbol instanceof FirValueParameterSymbol ? "context@" : "this@");
        }
        ClassId callableId = this.symbol;
        if (callableId instanceof FirClassSymbol) {
            callableId = ((FirClassSymbol) callableId).getClassId();
        } else if ((callableId instanceof FirCallableSymbol) && (callableId = ((FirCallableSymbol) callableId).getCallableId()) == null) {
            callableId = ((FirCallableSymbol) this.symbol).getName();
        }
        sb.append(callableId);
        RealVariable realVariable = this.dispatchReceiver;
        if (realVariable != null && this.extensionReceiver != null) {
            sb.append("(" + this.dispatchReceiver + ", " + this.extensionReceiver + ')');
        } else if (realVariable != null || this.extensionReceiver != null) {
            StringBuilder sb2 = new StringBuilder("(");
            RealVariable realVariable2 = this.dispatchReceiver;
            if (realVariable2 == null) {
                realVariable2 = this.extensionReceiver;
            }
            sb2.append(realVariable2);
            sb2.append(')');
            sb.append(sb2.toString());
        }
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable$Companion;", Argument.Delimiters.none, "<init>", "()V", "implicit", "Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RealVariable implicit(FirBasedSymbol<?> symbol, ConeKotlinType type) {
            symbol.getClass();
            type.getClass();
            return new RealVariable(symbol, true, null, null, type);
        }

        private Companion() {
        }
    }
}
