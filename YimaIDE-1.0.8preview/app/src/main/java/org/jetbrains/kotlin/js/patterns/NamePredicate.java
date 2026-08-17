package org.jetbrains.kotlin.js.patterns;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.js.patterns.NamePredicate;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public final class NamePredicate implements Predicate<Name> {
    public static final NamePredicate BOOLEAN;
    public static final NamePredicate CHAR;
    public static final NamePredicate LONG;
    public static final NamePredicate NUMBER;
    public static final NamePredicate PRIMITIVE_NUMBERS;
    public static final NamePredicate PRIMITIVE_NUMBERS_MAPPED_TO_PRIMITIVE_JS;
    public static final NamePredicate STRING;
    private final Set<Name> validNames;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "validNames", "org/jetbrains/kotlin/js/patterns/NamePredicate", "<init>"));
    }

    static {
        Set set = PrimitiveType.NUMBER_TYPES;
        PRIMITIVE_NUMBERS = new NamePredicate((List<String>) CollectionsKt.map(set, new Function1() { // from class: kba
            public final Object invoke(Object obj) {
                return ((PrimitiveType) obj).getTypeName().asString();
            }
        }));
        PRIMITIVE_NUMBERS_MAPPED_TO_PRIMITIVE_JS = new NamePredicate((List<String>) CollectionsKt.mapNotNull(set, new Function1() { // from class: lba
            public final Object invoke(Object obj) {
                return NamePredicate.a((PrimitiveType) obj);
            }
        }));
        STRING = new NamePredicate("String");
        NUMBER = new NamePredicate("Number");
        BOOLEAN = new NamePredicate("Boolean");
        CHAR = new NamePredicate(PrimitiveType.CHAR.getTypeName());
        LONG = new NamePredicate(PrimitiveType.LONG.getTypeName());
    }

    private NamePredicate(List<String> list) {
        if (list == null) {
            $$$reportNull$$$0(1);
        }
        this.validNames = new HashSet();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.validNames.add(Name.guessByFirstCharacter(it.next()));
        }
    }

    public static /* synthetic */ String a(PrimitiveType primitiveType) {
        if (primitiveType != PrimitiveType.LONG) {
            return primitiveType.getTypeName().asString();
        }
        return null;
    }

    @Override // java.util.function.Predicate
    public boolean test(Name name) {
        return this.validNames.contains(name);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NamePredicate(String... strArr) {
        this((List<String>) Arrays.asList(strArr));
        if (strArr == null) {
            $$$reportNull$$$0(0);
        }
    }

    public NamePredicate(Collection<Name> collection) {
        if (collection == null) {
            $$$reportNull$$$0(2);
        }
        HashSet hashSet = new HashSet();
        this.validNames = hashSet;
        hashSet.addAll(collection);
    }

    public NamePredicate(Name... nameArr) {
        if (nameArr == null) {
            $$$reportNull$$$0(3);
        }
        HashSet hashSet = new HashSet();
        this.validNames = hashSet;
        hashSet.addAll(Lists.newArrayList(nameArr));
    }
}
