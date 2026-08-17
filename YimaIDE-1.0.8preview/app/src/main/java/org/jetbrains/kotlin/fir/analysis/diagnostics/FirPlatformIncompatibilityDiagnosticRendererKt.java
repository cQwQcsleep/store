package org.jetbrains.kotlin.fir.analysis.diagnostics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.IntRange;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirPlatformIncompatibilityDiagnosticRendererKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a@\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00032\u001e\u0010\u0004\u001a\u001a\u0012\u0006\b\u0001\u0012\u00020\u0006\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00070\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001aV\u0010\r\u001a\u00020\u0001*\u00060\u0002j\u0002`\u000324\u0010\u000e\u001a0\u0012,\u0012*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u001c\u0012\u001a\u0012\u0006\b\u0001\u0012\u00020\u0006\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00070\u00050\u00100\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\"\u000e\u0010\u0011\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"renderIncompatibilityInformation", Argument.Delimiters.none, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "map", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "indent", Argument.Delimiters.none, "mode", "Lorg/jetbrains/kotlin/fir/analysis/diagnostics/MultiplatformDiagnosticRenderingMode;", "renderIncompatibleClassScopes", "unfulfilled", Argument.Delimiters.none, "Lkotlin/Pair;", "INDENTATION_UNIT", "org.jetbrains.kotlin:diagnostic-renderers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPlatformIncompatibilityDiagnosticRendererKt {
    public static final String INDENTATION_UNIT = "    ";

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderIncompatibilityInformation(final StringBuilder sb, Map<? extends ExpectActualMatchingCompatibility, ? extends Collection<? extends FirBasedSymbol<?>>> map, final String str, final MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode) {
        String reason;
        for (Map.Entry<? extends ExpectActualMatchingCompatibility, ? extends Collection<? extends FirBasedSymbol<?>>> entry : map.entrySet()) {
            ExpectActualMatchingCompatibility.Mismatch mismatch = (ExpectActualMatchingCompatibility) entry.getKey();
            Collection<? extends FirBasedSymbol<?>> value = entry.getValue();
            sb.append(str);
            sb.append("The following declaration");
            sb.append(value.size() == 1 ? " is" : "s are");
            sb.append(" incompatible");
            ExpectActualMatchingCompatibility.Mismatch mismatch2 = mismatch instanceof ExpectActualMatchingCompatibility.Mismatch ? mismatch : null;
            if (mismatch2 != null && (reason = mismatch2.getReason()) != null) {
                sb.append(" because ".concat(reason));
            }
            sb.append(":");
            Collection<? extends FirBasedSymbol<?>> collection = value;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                final FirBasedSymbol firBasedSymbol = (FirBasedSymbol) it.next();
                arrayList.add(new Function0() { // from class: xb5
                    public final Object invoke() {
                        return FirPlatformIncompatibilityDiagnosticRendererKt.renderIncompatibilityInformation$lambda$1$0(multiplatformDiagnosticRenderingMode, sb, firBasedSymbol, str);
                    }
                });
            }
            multiplatformDiagnosticRenderingMode.renderList(sb, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderIncompatibilityInformation$lambda$1$0(MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode, StringBuilder sb, FirBasedSymbol firBasedSymbol, String str) {
        multiplatformDiagnosticRenderingMode.renderSymbol(sb, firBasedSymbol, str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderIncompatibleClassScopes(final StringBuilder sb, final List<? extends Pair<? extends FirBasedSymbol<?>, ? extends Map<? extends ExpectActualMatchingCompatibility, ? extends Collection<? extends FirBasedSymbol<?>>>>> list, final String str, final MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode) {
        IntRange indices = CollectionsKt.getIndices(list);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(indices, 10));
        IntIterator it = indices.iterator();
        while (it.hasNext()) {
            final int iNextInt = it.nextInt();
            arrayList.add(new Function0() { // from class: yb5
                public final Object invoke() {
                    return FirPlatformIncompatibilityDiagnosticRendererKt.renderIncompatibleClassScopes$lambda$0$0(list, iNextInt, multiplatformDiagnosticRenderingMode, sb, str);
                }
            });
        }
        multiplatformDiagnosticRenderingMode.renderList(sb, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderIncompatibleClassScopes$lambda$0$0(List list, int i, MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode, StringBuilder sb, String str) {
        Pair pair = (Pair) list.get(i);
        FirBasedSymbol<?> firBasedSymbol = (FirBasedSymbol) pair.component1();
        Map map = (Map) pair.component2();
        multiplatformDiagnosticRenderingMode.renderSymbol(sb, firBasedSymbol, str);
        if (!map.isEmpty()) {
            multiplatformDiagnosticRenderingMode.newLine(sb);
            renderIncompatibilityInformation(sb, map, str + INDENTATION_UNIT, multiplatformDiagnosticRenderingMode);
        }
        if (i != CollectionsKt.getLastIndex(list)) {
            multiplatformDiagnosticRenderingMode.newLine(sb);
        }
        return Unit.INSTANCE;
    }
}
