package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CfgInternals;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u0002H\u0003\"\f\b\u0000\u0010\u0003*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u0002H\u0003H&¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\b2\u0006\u0010\t\u001a\u0002H\u0003H&¢\u0006\u0002\u0010\nÊ\u0001\u0002\b\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/SnapshotFirMapper;", Argument.Delimiters.none, "mapSymbol", "T", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "symbol", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "mapElement", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "(Lorg/jetbrains/kotlin/fir/FirElement;)Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:resolve", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@CfgInternals
public interface SnapshotFirMapper {
    <T extends FirElement> T mapElement(T element);

    <T extends FirBasedSymbol<?>> T mapSymbol(T symbol);
}
