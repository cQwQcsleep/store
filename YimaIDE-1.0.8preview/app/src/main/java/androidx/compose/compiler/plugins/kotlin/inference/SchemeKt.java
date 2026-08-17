package androidx.compose.compiler.plugins.kotlin.inference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001a\u0010\b\u001a\u00020\u0003*\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"schemeParseError", "", "deserializeScheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "value", "", "eos", "", "mergeWith", "schemes", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SchemeKt {
    private static final char eos = 0;

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ItemKind.values().length];
            try {
                iArr[ItemKind.Token.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ItemKind.Number.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static Scheme c(final SchemeStringSerializationReader schemeStringSerializationReader) throws SchemeParseError {
        Item itemDeserializeScheme$item = deserializeScheme$item(schemeStringSerializationReader);
        boolean zDeserializeScheme$isItem = deserializeScheme$isItem(schemeStringSerializationReader, ItemKind.AnyParameters);
        return new Scheme(itemDeserializeScheme$item, zDeserializeScheme$isItem ? CollectionsKt.emptyList() : deserializeScheme$list(schemeStringSerializationReader, new Function0() { // from class: androidx.compose.compiler.plugins.kotlin.inference.a
            public final Object invoke() {
                return SchemeKt.deserializeScheme$scheme(schemeStringSerializationReader);
            }
        }), (Scheme) deserializeScheme$optional$default(schemeStringSerializationReader, ItemKind.ResultPrefix, null, new Function0() { // from class: androidx.compose.compiler.plugins.kotlin.inference.b
            public final Object invoke() {
                return SchemeKt.deserializeScheme$scheme(schemeStringSerializationReader);
            }
        }, 4, null), zDeserializeScheme$isItem);
    }

    public static final Scheme deserializeScheme(String str) {
        str.getClass();
        SchemeStringSerializationReader schemeStringSerializationReader = new SchemeStringSerializationReader(str);
        try {
            Scheme schemeDeserializeScheme$scheme = deserializeScheme$scheme(schemeStringSerializationReader);
            schemeStringSerializationReader.end();
            return schemeDeserializeScheme$scheme;
        } catch (SchemeParseError unused) {
            return null;
        }
    }

    private static final <T> T deserializeScheme$delimited(SchemeStringSerializationReader schemeStringSerializationReader, ItemKind itemKind, ItemKind itemKind2, Function0<? extends T> function0) {
        schemeStringSerializationReader.expect(itemKind);
        T t = (T) function0.invoke();
        schemeStringSerializationReader.expect(itemKind2);
        return t;
    }

    private static final boolean deserializeScheme$isItem(SchemeStringSerializationReader schemeStringSerializationReader, ItemKind itemKind) {
        if (schemeStringSerializationReader.getKind() != itemKind) {
            return false;
        }
        schemeStringSerializationReader.expect(itemKind);
        return true;
    }

    private static final Item deserializeScheme$item(SchemeStringSerializationReader schemeStringSerializationReader) throws SchemeParseError {
        int i = WhenMappings.$EnumSwitchMapping$0[schemeStringSerializationReader.getKind().ordinal()];
        if (i == 1) {
            return new Token(schemeStringSerializationReader.token());
        }
        if (i == 2) {
            return new Open(schemeStringSerializationReader.number(), false, 2, null);
        }
        schemeParseError();
        wq6.a();
        return null;
    }

    private static final <T> List<T> deserializeScheme$list(SchemeStringSerializationReader schemeStringSerializationReader, Function0<? extends T> function0) {
        if (schemeStringSerializationReader.getKind() != ItemKind.Open) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (schemeStringSerializationReader.getKind() == ItemKind.Open) {
            arrayList.add(function0.invoke());
        }
        return arrayList;
    }

    private static final <T> T deserializeScheme$optional(SchemeStringSerializationReader schemeStringSerializationReader, ItemKind itemKind, ItemKind itemKind2, Function0<? extends T> function0) {
        if (schemeStringSerializationReader.getKind() == itemKind) {
            return (T) deserializeScheme$delimited(schemeStringSerializationReader, itemKind, itemKind2, function0);
        }
        return null;
    }

    public static /* synthetic */ Object deserializeScheme$optional$default(SchemeStringSerializationReader schemeStringSerializationReader, ItemKind itemKind, ItemKind itemKind2, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            itemKind2 = ItemKind.Invalid;
        }
        return deserializeScheme$optional(schemeStringSerializationReader, itemKind, itemKind2, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Scheme deserializeScheme$scheme(final SchemeStringSerializationReader schemeStringSerializationReader) {
        return (Scheme) deserializeScheme$delimited(schemeStringSerializationReader, ItemKind.Open, ItemKind.Close, new Function0() { // from class: androidx.compose.compiler.plugins.kotlin.inference.c
            public final Object invoke() {
                return SchemeKt.c(schemeStringSerializationReader);
            }
        });
    }

    public static final Scheme mergeWith(Scheme scheme, List<Scheme> list) {
        scheme.getClass();
        list.getClass();
        if (list.isEmpty()) {
            return scheme;
        }
        LazyScheme lazyScheme = new LazyScheme(scheme, null, null, 6, null);
        Bindings bindings = lazyScheme.getBindings();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            mergeWith$unifySchemes(bindings, lazyScheme, new LazyScheme((Scheme) it.next(), null, lazyScheme.getBindings(), 2, null));
        }
        return lazyScheme.toScheme();
    }

    private static final void mergeWith$unifySchemes(Bindings bindings, LazyScheme lazyScheme, LazyScheme lazyScheme2) {
        bindings.unify(lazyScheme.getTarget(), lazyScheme2.getTarget());
        for (Pair pair : CollectionsKt.zip(lazyScheme.getParameters(), lazyScheme2.getParameters())) {
            mergeWith$unifySchemes(bindings, (LazyScheme) pair.component1(), (LazyScheme) pair.component2());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void schemeParseError() throws SchemeParseError {
        throw new SchemeParseError();
    }
}
