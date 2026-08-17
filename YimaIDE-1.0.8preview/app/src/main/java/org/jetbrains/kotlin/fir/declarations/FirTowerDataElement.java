package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataElement;
import org.jetbrains.kotlin.fir.resolve.ImplicitValueMapper;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitContextParameterValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStubType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BK\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\u0004\u0018\u0001`\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ-\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u001f\b\u0002\u0010\u001f\u001a\u0019\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020!0 ¢\u0006\u0002\b#J/\u0010$\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00052\u001d\u0010\u001f\u001a\u0019\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020!0 ¢\u0006\u0002\b#H\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\u0004\u0018\u0001`\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataElement;", Argument.Delimiters.none, "scope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "implicitReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "contextParameterGroup", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitContextParameterValue;", "Lorg/jetbrains/kotlin/fir/declarations/ContextParameterGroup;", "isLocal", Argument.Delimiters.none, "staticScopeOwnerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/FirScope;Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;Ljava/util/List;ZLorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "getScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "getImplicitReceiver", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "getContextParameterGroup", "()Ljava/util/List;", "()Z", "getStaticScopeOwnerSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "createSnapshot", "keepMutable", "mapper", "Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueMapper;", "createSnapshot$org_jetbrains_kotlin_semantics", "getAvailableScopes", "processTypeScope", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lkotlin/ExtensionFunctionType;", "getImplicitScope", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTowerDataElement {
    private final List<ImplicitContextParameterValue> contextParameterGroup;
    private final ImplicitReceiverValue<?> implicitReceiver;
    private final boolean isLocal;
    private final FirScope scope;
    private final FirRegularClassSymbol staticScopeOwnerSymbol;

    public /* synthetic */ FirTowerDataElement(FirScope firScope, ImplicitReceiverValue implicitReceiverValue, List list, boolean z, FirRegularClassSymbol firRegularClassSymbol, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firScope, implicitReceiverValue, (i & 4) != 0 ? null : list, z, (i & 16) != 0 ? null : firRegularClassSymbol);
    }

    public static FirTypeScope a(FirTypeScope firTypeScope, ConeKotlinType coneKotlinType) {
        firTypeScope.getClass();
        coneKotlinType.getClass();
        return firTypeScope;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List getAvailableScopes$default(FirTowerDataElement firTowerDataElement, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = new Function2() { // from class: hf5
                public final Object invoke(Object obj2, Object obj3) {
                    return FirTowerDataElement.a((FirTypeScope) obj2, (ConeKotlinType) obj3);
                }
            };
        }
        return firTowerDataElement.getAvailableScopes(function2);
    }

    private final FirScope getImplicitScope(ImplicitReceiverValue<?> implicitReceiverValue, Function2<? super FirTypeScope, ? super ConeKotlinType, ? extends FirTypeScope> function2) {
        FirTypeScope implicitScope = implicitReceiverValue.getImplicitScope();
        if (implicitScope == null) {
            return FirTypeScope.Empty.INSTANCE;
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(implicitReceiverValue, implicitReceiverValue.getType());
        return ((coneKotlinTypeFullyExpandedType instanceof ConeErrorType) || (coneKotlinTypeFullyExpandedType instanceof ConeStubType)) ? FirTypeScope.Empty.INSTANCE : (FirScope) function2.invoke(implicitScope, coneKotlinTypeFullyExpandedType);
    }

    public final FirTowerDataElement createSnapshot$org_jetbrains_kotlin_semantics(boolean keepMutable, ImplicitValueMapper mapper) {
        mapper.getClass();
        FirScope firScope = this.scope;
        ImplicitReceiverValue<?> implicitReceiverValue = this.implicitReceiver;
        ArrayList arrayList = null;
        ImplicitReceiverValue implicitReceiverValue2 = implicitReceiverValue != null ? (ImplicitReceiverValue) mapper.invoke(implicitReceiverValue) : null;
        List<ImplicitContextParameterValue> list = this.contextParameterGroup;
        if (list != null) {
            List<ImplicitContextParameterValue> list2 = list;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(((ImplicitContextParameterValue) it.next()).createSnapshot(keepMutable));
            }
        }
        return new FirTowerDataElement(firScope, implicitReceiverValue2, arrayList, this.isLocal, this.staticScopeOwnerSymbol);
    }

    public final List<FirScope> getAvailableScopes(Function2<? super FirTypeScope, ? super ConeKotlinType, ? extends FirTypeScope> processTypeScope) {
        processTypeScope.getClass();
        FirScope firScope = this.scope;
        if (firScope != null) {
            return CollectionsKt.listOf(firScope);
        }
        ImplicitReceiverValue<?> implicitReceiverValue = this.implicitReceiver;
        if (implicitReceiverValue != null) {
            return CollectionsKt.listOf(getImplicitScope(implicitReceiverValue, processTypeScope));
        }
        if (this.contextParameterGroup != null || this.staticScopeOwnerSymbol != null) {
            return CollectionsKt.emptyList();
        }
        k2d.a("Tower data element is expected to have either scope or implicit receivers.");
        return null;
    }

    public final List<ImplicitContextParameterValue> getContextParameterGroup() {
        return this.contextParameterGroup;
    }

    public final ImplicitReceiverValue<?> getImplicitReceiver() {
        return this.implicitReceiver;
    }

    public final FirScope getScope() {
        return this.scope;
    }

    public final FirRegularClassSymbol getStaticScopeOwnerSymbol() {
        return this.staticScopeOwnerSymbol;
    }

    /* JADX INFO: renamed from: isLocal, reason: from getter */
    public final boolean getIsLocal() {
        return this.isLocal;
    }

    public FirTowerDataElement(FirScope firScope, ImplicitReceiverValue<?> implicitReceiverValue, List<ImplicitContextParameterValue> list, boolean z, FirRegularClassSymbol firRegularClassSymbol) {
        this.scope = firScope;
        this.implicitReceiver = implicitReceiverValue;
        this.contextParameterGroup = list;
        this.isLocal = z;
        this.staticScopeOwnerSymbol = firRegularClassSymbol;
    }
}
