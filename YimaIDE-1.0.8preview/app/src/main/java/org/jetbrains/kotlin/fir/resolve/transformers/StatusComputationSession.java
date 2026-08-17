package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.StatusComputationSession;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001*B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0013H\u0086\u0002J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0013J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0013J\u000e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0013J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0013H\u0016J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\u001c\u001a\u00020\u0013H\u0014J\u001a\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"0!2\u0006\u0010#\u001a\u00020\u001fH\u0014J\u0014\u0010$\u001a\u00020\u00192\n\u0010%\u001a\u0006\u0012\u0002\b\u00030\"H\u0002J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u001c\u001a\u00020'H\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession;", Argument.Delimiters.none, "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "useSiteScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "designationMapForLocalClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Ljava/util/Map;)V", "getUseSiteSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getUseSiteScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getDesignationMapForLocalClasses", "()Ljava/util/Map;", "statusMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession$StatusComputationStatus;", "get", "klass", "startComputing", "endComputing", Argument.Delimiters.none, "computeOnlyClassStatus", "forceResolveStatusesOfSupertypes", "regularClass", "additionalSuperTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "superTypeToSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "typeRef", "forceResolveStatusOfCorrespondingClass", "superClassSymbol", "forceResolveStatusesOfClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "resolveClassForSuperType", Argument.Delimiters.none, "StatusComputationStatus", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class StatusComputationSession {
    private final Map<FirClassLikeDeclaration, FirClassLikeDeclaration> designationMapForLocalClasses;
    private final Map<FirClass, StatusComputationStatus> statusMap;
    private final ScopeSession useSiteScopeSession;
    private final FirSession useSiteSession;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession$StatusComputationStatus;", Argument.Delimiters.none, "requiresComputation", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;IZ)V", "getRequiresComputation", "()Z", "NotComputed", "Computing", "ComputedOnlyClassStatus", "Computed", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum StatusComputationStatus {
        NotComputed(true),
        Computing(false),
        ComputedOnlyClassStatus(true),
        Computed(false);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final boolean requiresComputation;

        StatusComputationStatus(boolean z) {
            this.requiresComputation = z;
        }

        public static EnumEntries<StatusComputationStatus> getEntries() {
            return $ENTRIES;
        }

        public final boolean getRequiresComputation() {
            return this.requiresComputation;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StatusComputationSession(FirSession firSession, ScopeSession scopeSession, Map<FirClassLikeDeclaration, ? extends FirClassLikeDeclaration> map) {
        firSession.getClass();
        scopeSession.getClass();
        map.getClass();
        this.useSiteSession = firSession;
        this.useSiteScopeSession = scopeSession;
        this.designationMapForLocalClasses = map;
        this.statusMap = MapsKt.withDefaultMutable(new HashMap(), new Function1() { // from class: ind
            public final Object invoke(Object obj) {
                return StatusComputationSession.a((FirClass) obj);
            }
        });
    }

    public static StatusComputationStatus a(FirClass firClass) {
        firClass.getClass();
        return StatusComputationStatus.NotComputed;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void forceResolveStatusOfCorrespondingClass(FirClassifierSymbol<?> superClassSymbol) {
        if (superClassSymbol instanceof FirRegularClassSymbol) {
            forceResolveStatusesOfClass((FirRegularClass) ((FirRegularClassSymbol) superClassSymbol).getFir());
            return;
        }
        if (superClassSymbol instanceof FirTypeAliasSymbol) {
            Iterator<FirClassifierSymbol<?>> it = superTypeToSymbols(((FirTypeAlias) ((FirTypeAliasSymbol) superClassSymbol).getFir()).getExpandedTypeRef()).iterator();
            while (it.hasNext()) {
                forceResolveStatusOfCorrespondingClass(it.next());
            }
        } else {
            if ((superClassSymbol instanceof FirTypeParameterSymbol) || (superClassSymbol instanceof FirAnonymousObjectSymbol)) {
                return;
            }
            bu8.a();
        }
    }

    private final void forceResolveStatusesOfClass(FirRegularClass regularClass) {
        if (Intrinsics.areEqual(regularClass.getOrigin(), FirDeclarationOrigin.Source.INSTANCE)) {
            if (get(regularClass).getRequiresComputation() && resolveClassForSuperType(regularClass)) {
                endComputing(regularClass);
                return;
            }
            return;
        }
        if (get(regularClass).getRequiresComputation()) {
            startComputing(regularClass);
            forceResolveStatusesOfSupertypes(regularClass);
            endComputing(regularClass);
        }
    }

    public List<FirTypeRef> additionalSuperTypes(FirClass regularClass) {
        regularClass.getClass();
        return CollectionsKt.emptyList();
    }

    public final void computeOnlyClassStatus(FirClass klass) {
        klass.getClass();
        StatusComputationStatus statusComputationStatus = (StatusComputationStatus) MapsKt.getValue(this.statusMap, klass);
        StatusComputationStatus statusComputationStatus2 = StatusComputationStatus.ComputedOnlyClassStatus;
        if (statusComputationStatus.compareTo(statusComputationStatus2) < 0) {
            this.statusMap.put(klass, statusComputationStatus2);
        }
    }

    public final void endComputing(FirClass klass) {
        klass.getClass();
        this.statusMap.put(klass, StatusComputationStatus.Computed);
    }

    public void forceResolveStatusesOfSupertypes(FirClass regularClass) {
        regularClass.getClass();
        Iterator it = CollectionsKt.plus(regularClass.getSuperTypeRefs(), additionalSuperTypes(regularClass)).iterator();
        while (it.hasNext()) {
            Iterator<FirClassifierSymbol<?>> it2 = superTypeToSymbols((FirTypeRef) it.next()).iterator();
            while (it2.hasNext()) {
                forceResolveStatusOfCorrespondingClass(it2.next());
            }
        }
    }

    public final StatusComputationStatus get(FirClass klass) {
        klass.getClass();
        return (StatusComputationStatus) MapsKt.getValue(this.statusMap, klass);
    }

    public final Map<FirClassLikeDeclaration, FirClassLikeDeclaration> getDesignationMapForLocalClasses() {
        return this.designationMapForLocalClasses;
    }

    public final ScopeSession getUseSiteScopeSession() {
        return this.useSiteScopeSession;
    }

    public final FirSession getUseSiteSession() {
        return this.useSiteSession;
    }

    public boolean resolveClassForSuperType(FirRegularClass regularClass) {
        regularClass.getClass();
        DesignationState designationStateCreate = DesignationState.INSTANCE.create(regularClass.getSymbol(), this.designationMapForLocalClasses, false);
        if (designationStateCreate == null) {
            return false;
        }
        FirTransformerUtilKt.transformSingle(designationStateCreate.getFirstDeclaration(), new FirDesignatedStatusResolveTransformer(designationStateCreate, this), null);
        return true;
    }

    public final StatusComputationStatus startComputing(FirClass klass) {
        klass.getClass();
        Map<FirClass, StatusComputationStatus> map = this.statusMap;
        StatusComputationStatus statusComputationStatus = map.get(klass);
        if (statusComputationStatus == null) {
            statusComputationStatus = StatusComputationStatus.Computing;
            map.put(klass, statusComputationStatus);
        }
        return statusComputationStatus;
    }

    public Collection<FirClassifierSymbol<?>> superTypeToSymbols(FirTypeRef typeRef) {
        typeRef.getClass();
        return CollectionsKt.listOfNotNull(ToSymbolUtilsKt.toSymbol(FirTypeUtilsKt.getConeType(typeRef), this.useSiteSession));
    }

    public /* synthetic */ StatusComputationSession(FirSession firSession, ScopeSession scopeSession, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, (i & 4) != 0 ? MapsKt.emptyMap() : map);
    }
}
