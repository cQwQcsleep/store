package org.jetbrains.kotlin.fir.analysis.checkers.context;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u000e\b\u0000\u0010\u0001\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\u0005H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"findClosest", "T", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "check", "Lkotlin/Function1;", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckerContextKt {
    public static final /* synthetic */ <T extends FirBasedSymbol<?>> T findClosest(CheckerContext checkerContext, Function1<? super T, Boolean> function1) {
        T t;
        checkerContext.getClass();
        function1.getClass();
        Iterator it = CollectionsKt.asReversed(checkerContext.getContainingDeclarations()).iterator();
        while (true) {
            t = null;
            if (!it.hasNext()) {
                break;
            }
            FirBasedSymbol firBasedSymbol = (FirBasedSymbol) it.next();
            Intrinsics.reifiedOperationMarker(2, "T");
            if (firBasedSymbol != null) {
                if (((Boolean) function1.invoke(firBasedSymbol)).booleanValue()) {
                    t = (T) firBasedSymbol;
                }
                if (t != null) {
                    break;
                }
            }
        }
        return t;
    }

    public static /* synthetic */ FirBasedSymbol findClosest$default(CheckerContext checkerContext, Function1 function1, int i, Object obj) {
        FirBasedSymbol firBasedSymbol;
        if ((i & 1) != 0) {
            Intrinsics.needClassReification();
            function1 = AnonymousClass1.INSTANCE;
        }
        checkerContext.getClass();
        function1.getClass();
        Iterator it = CollectionsKt.asReversed(checkerContext.getContainingDeclarations()).iterator();
        while (true) {
            firBasedSymbol = null;
            if (!it.hasNext()) {
                break;
            }
            FirBasedSymbol firBasedSymbol2 = (FirBasedSymbol) it.next();
            Intrinsics.reifiedOperationMarker(2, "T");
            if (firBasedSymbol2 != null) {
                firBasedSymbol = ((Boolean) function1.invoke(firBasedSymbol2)).booleanValue() ? firBasedSymbol2 : null;
                if (firBasedSymbol != null) {
                    break;
                }
            }
        }
        return firBasedSymbol;
    }
}
