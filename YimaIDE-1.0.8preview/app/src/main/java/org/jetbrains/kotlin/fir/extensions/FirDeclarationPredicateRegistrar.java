package org.jetbrains.kotlin.fir.extensions;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u00052\u001a\u0010\u0006\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\b0\u0007\"\u0006\u0012\u0002\b\u00030\bH&¢\u0006\u0002\u0010\tJ\u001a\u0010\u0004\u001a\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\nH&¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationPredicateRegistrar;", Argument.Delimiters.none, "<init>", "()V", "register", Argument.Delimiters.none, "predicates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "([Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;)V", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDeclarationPredicateRegistrar {
    public abstract void register(Collection<? extends AbstractPredicate<?>> predicates);

    public abstract void register(AbstractPredicate<?>... predicates);
}
