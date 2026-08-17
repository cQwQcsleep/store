package org.jetbrains.kotlin.fir.analysis.wasm.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.FirAbstractWebCheckerUtils;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/wasm/checkers/FirWasmWebCheckerUtils;", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/FirAbstractWebCheckerUtils;", "<init>", "()V", "isNativeOrExternalInterface", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmWebCheckerUtils extends FirAbstractWebCheckerUtils {
    public static final FirWasmWebCheckerUtils INSTANCE = new FirWasmWebCheckerUtils();

    private FirWasmWebCheckerUtils() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.FirAbstractWebCheckerUtils
    public boolean isNativeOrExternalInterface(FirBasedSymbol<?> symbol, FirSession session) {
        symbol.getClass();
        session.getClass();
        if (!FirWebCommonHelpersKt.isEffectivelyExternal(symbol, session)) {
            return false;
        }
        FirClassSymbol firClassSymbol = symbol instanceof FirClassSymbol ? (FirClassSymbol) symbol : null;
        return firClassSymbol != null && firClassSymbol.getClassKind() == ClassKind.INTERFACE;
    }
}
