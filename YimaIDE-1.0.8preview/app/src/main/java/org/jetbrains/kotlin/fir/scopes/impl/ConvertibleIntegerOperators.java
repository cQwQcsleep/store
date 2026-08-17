package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/ConvertibleIntegerOperators;", Argument.Delimiters.none, "<init>", "()V", "binaryOperatorsNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "getBinaryOperatorsNames", "()Ljava/util/Set;", "unaryOperatorNames", "getUnaryOperatorNames", "binaryOperatorsWithSignedArgument", "getBinaryOperatorsWithSignedArgument", "toNameSet", Argument.Delimiters.none, Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConvertibleIntegerOperators {
    public static final ConvertibleIntegerOperators INSTANCE;
    private static final Set<Name> binaryOperatorsNames;
    private static final Set<Name> binaryOperatorsWithSignedArgument;
    private static final Set<Name> unaryOperatorNames;

    static {
        ConvertibleIntegerOperators convertibleIntegerOperators = new ConvertibleIntegerOperators();
        INSTANCE = convertibleIntegerOperators;
        binaryOperatorsNames = convertibleIntegerOperators.toNameSet(CollectionsKt.listOf(new String[]{"plus", "minus", "times", "div", "rem", "and", "or", "xor", "shl", "shr", "ushr"}));
        unaryOperatorNames = convertibleIntegerOperators.toNameSet(CollectionsKt.listOf(new String[]{"inv", "unaryPlus", "unaryMinus"}));
        binaryOperatorsWithSignedArgument = convertibleIntegerOperators.toNameSet(CollectionsKt.listOf(new String[]{"shl", "shr", "ushr"}));
    }

    private ConvertibleIntegerOperators() {
    }

    private final Set<Name> toNameSet(List<String> list) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(Name.identifier((String) it.next()));
        }
        return linkedHashSet;
    }

    public final Set<Name> getBinaryOperatorsNames() {
        return binaryOperatorsNames;
    }

    public final Set<Name> getBinaryOperatorsWithSignedArgument() {
        return binaryOperatorsWithSignedArgument;
    }

    public final Set<Name> getUnaryOperatorNames() {
        return unaryOperatorNames;
    }
}
