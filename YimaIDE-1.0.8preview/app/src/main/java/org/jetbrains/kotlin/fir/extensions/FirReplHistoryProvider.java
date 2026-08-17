package org.jetbrains.kotlin.fir.extensions;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0006H&J\b\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirReplHistoryProvider;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "getSnippets", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "putSnippet", Argument.Delimiters.none, "symbol", "isFirstSnippet", Argument.Delimiters.none, "getSnippetCount", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirReplHistoryProvider implements FirSessionComponent {
    public abstract int getSnippetCount();

    public abstract Iterable<FirReplSnippetSymbol> getSnippets();

    public abstract boolean isFirstSnippet(FirReplSnippetSymbol symbol);

    public abstract void putSnippet(FirReplSnippetSymbol symbol);
}
