package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\r\u001a\u00020\u00072\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0002\b\u000fJ1\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u00122\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0014H\u0014¢\u0006\u0002\u0010\u0015J3\u0010\u0016\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u00122\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0014H\u0000¢\u0006\u0004\b\u0017\u0010\u0015J\u0014\u0010\u0018\u001a\u00020\u00192\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J\u001c\u0010\u001a\u001a\u00020\u00192\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u001b\u001a\u00020\u0012H\u0002J\u0012\u0010\u001c\u001a\u00020\u00192\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006J\u0012\u0010\u001d\u001a\u00020\u001e2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006R2\u0010\u0004\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationSession;", Argument.Delimiters.none, "<init>", "()V", "implicitBodyResolveStatusMap", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus;", "Lkotlin/collections/HashMap;", "computingSymbolsStack", Argument.Delimiters.none, "nonTrivialLoops", Argument.Delimiters.none, "getStatus", "symbol", "getStatus$org_jetbrains_kotlin_resolve", "executeTransformation", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "transformation", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function0;)Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "compute", "compute$org_jetbrains_kotlin_resolve", "startComputing", Argument.Delimiters.none, "storeResult", "transformedDeclaration", "calculateAndStoreNonTrivialLoop", "belongToSomeNonTrivialLoop", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ImplicitBodyResolveComputationSession {
    private final HashMap<FirCallableSymbol<?>, ImplicitBodyResolveComputationStatus> implicitBodyResolveStatusMap = new HashMap<>();
    private final List<FirCallableSymbol<?>> computingSymbolsStack = new ArrayList();
    private final Set<FirCallableSymbol<?>> nonTrivialLoops = new LinkedHashSet();

    private final void startComputing(FirCallableSymbol<?> symbol) {
        if (this.implicitBodyResolveStatusMap.get(symbol) == null) {
            this.implicitBodyResolveStatusMap.put(symbol, ImplicitBodyResolveComputationStatus.Computing.INSTANCE);
            this.computingSymbolsStack.add(symbol);
        } else {
            StringBuilder sb = new StringBuilder("Unexpected static in startComputing for ");
            sb.append(symbol);
            ywd.a(sb, ": ", this.implicitBodyResolveStatusMap.get(symbol));
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    private final void storeResult(FirCallableSymbol<?> symbol, FirCallableDeclaration transformedDeclaration) {
        if (!Intrinsics.areEqual(this.implicitBodyResolveStatusMap.get(symbol), ImplicitBodyResolveComputationStatus.Computing.INSTANCE)) {
            StringBuilder sb = new StringBuilder("Unexpected static in storeResult for ");
            sb.append(symbol);
            ywd.a(sb, ": ", this.implicitBodyResolveStatusMap.get(symbol));
        } else {
            FirResolvedTypeRef returnTypeRef = transformedDeclaration.getReturnTypeRef();
            if (!(returnTypeRef instanceof FirResolvedTypeRef)) {
                rza.a("Not FirResolvedTypeRef (", UtilsKt.render(transformedDeclaration.getReturnTypeRef()), ") in storeResult for: ", UtilsKt.render(symbol.getFir()));
            } else {
                CollectionsKt.removeLast(this.computingSymbolsStack);
                this.implicitBodyResolveStatusMap.put(symbol, new ImplicitBodyResolveComputationStatus.Computed(returnTypeRef, transformedDeclaration));
            }
        }
    }

    public final boolean belongToSomeNonTrivialLoop(FirCallableSymbol<?> symbol) {
        symbol.getClass();
        return this.nonTrivialLoops.contains(symbol);
    }

    public final void calculateAndStoreNonTrivialLoop(FirCallableSymbol<?> symbol) {
        List list;
        symbol.getClass();
        List<FirCallableSymbol<?>> list2 = this.computingSymbolsStack;
        if (!list2.isEmpty()) {
            ListIterator<FirCallableSymbol<?>> listIterator = list2.listIterator(list2.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    list = CollectionsKt.toList(list2);
                    break;
                }
                if (Intrinsics.areEqual(listIterator.previous(), symbol)) {
                    listIterator.next();
                    int size = list2.size() - listIterator.nextIndex();
                    if (size != 0) {
                        ArrayList arrayList = new ArrayList(size);
                        while (listIterator.hasNext()) {
                            arrayList.add(listIterator.next());
                        }
                        list = arrayList;
                        break;
                    }
                    list = CollectionsKt.emptyList();
                    break;
                }
            }
        } else {
            list = CollectionsKt.emptyList();
        }
        if (list.isEmpty()) {
            list = null;
        }
        if (list == null) {
            return;
        }
        Set<FirCallableSymbol<?>> set = this.nonTrivialLoops;
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        setCreateSetBuilder.add(symbol);
        setCreateSetBuilder.addAll(list);
        set.addAll(SetsKt.build(setCreateSetBuilder));
    }

    public final <D extends FirCallableDeclaration> D compute$org_jetbrains_kotlin_resolve(FirCallableSymbol<?> symbol, Function0<? extends D> transformation) {
        symbol.getClass();
        transformation.getClass();
        startComputing(symbol);
        D d = (D) executeTransformation(symbol, transformation);
        storeResult(symbol, d);
        return d;
    }

    public <D extends FirCallableDeclaration> D executeTransformation(FirCallableSymbol<?> symbol, Function0<? extends D> transformation) {
        symbol.getClass();
        transformation.getClass();
        return (D) transformation.invoke();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ImplicitBodyResolveComputationStatus getStatus$org_jetbrains_kotlin_resolve(FirCallableSymbol<?> symbol) {
        symbol.getClass();
        if (symbol instanceof FirSyntheticPropertySymbol) {
            FirProperty firProperty = (FirProperty) ((FirSyntheticPropertySymbol) symbol).getFir();
            if (firProperty instanceof FirSyntheticProperty) {
                return getStatus$org_jetbrains_kotlin_resolve(((FirSyntheticProperty) firProperty).getGetter().getDelegate().getSymbol());
            }
        }
        ImplicitBodyResolveComputationStatus implicitBodyResolveComputationStatus = this.implicitBodyResolveStatusMap.get(symbol);
        return implicitBodyResolveComputationStatus == null ? ImplicitBodyResolveComputationStatus.NotComputed.INSTANCE : implicitBodyResolveComputationStatus;
    }
}
