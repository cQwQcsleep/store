package org.jetbrains.kotlin.config.nativeBinaryOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\r\u001a\u0004\u0018\u00010\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/ListValueParser;", "T", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;", Argument.Delimiters.none, "elementValueParser", "<init>", "(Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;)V", "getElementValueParser", "()Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;", "parse", "value", Argument.Delimiters.none, "validValuesHint", "getValidValuesHint", "()Ljava/lang/String;", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class ListValueParser<T> implements BinaryOption.ValueParser<List<? extends T>> {
    private final BinaryOption.ValueParser<T> elementValueParser;

    public ListValueParser(BinaryOption.ValueParser<T> valueParser) {
        valueParser.getClass();
        this.elementValueParser = valueParser;
    }

    public final BinaryOption.ValueParser<T> getElementValueParser() {
        return this.elementValueParser;
    }

    @Override // org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOption.ValueParser
    public String getValidValuesHint() {
        return "semicolon-separated list of " + this.elementValueParser.getValidValuesHint();
    }

    @Override // org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOption.ValueParser
    public List<T> parse(String value) {
        value.getClass();
        if (Intrinsics.areEqual(value, Argument.Delimiters.none)) {
            return CollectionsKt.emptyList();
        }
        List listSplit$default = StringsKt.split$default(value, new String[]{Argument.Delimiters.semicolon}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator<T> it = listSplit$default.iterator();
        while (it.hasNext()) {
            T t = this.elementValueParser.parse((String) it.next());
            if (t == null) {
                return null;
            }
            arrayList.add(t);
        }
        return arrayList;
    }
}
