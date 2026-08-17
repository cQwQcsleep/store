package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u0002H\u0003\"\f\b\u0000\u0010\u0004*\u0006\u0012\u0002\b\u00030\u0005\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u0007\u001a\u0002H\u0003H¦\u0002¢\u0006\u0002\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueMapper;", Argument.Delimiters.none, "invoke", "T", "S", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;", "value", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;)Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ImplicitValueMapper {
    <S extends FirBasedSymbol<?>, T extends ImplicitValue<S>> T invoke(T value);
}
