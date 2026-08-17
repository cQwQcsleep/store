package org.jetbrains.kotlin.config.nativeBinaryOptions;

import java.lang.Enum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\b\u0001\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B?\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0012\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0013\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\u0014R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017Ê\u0001\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/EnumValueParser;", "T", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;", "values", Argument.Delimiters.none, "shortcut", "Lkotlin/Function1;", Argument.Delimiters.none, "hideValue", Argument.Delimiters.none, "<init>", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getValues", "()Ljava/util/List;", "getShortcut", "()Lkotlin/jvm/functions/Function1;", "getHideValue", "parse", "value", "(Ljava/lang/String;)Ljava/lang/Enum;", "validValuesHint", "getValidValuesHint", "()Ljava/lang/String;", "org.jetbrains.kotlin:binary-options", "Lkotlin/PublishedApi;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EnumValueParser<T extends Enum<T>> implements BinaryOption.ValueParser<T> {
    private final Function1<T, Boolean> hideValue;
    private final Function1<T, String> shortcut;
    private final List<T> values;

    /* JADX WARN: Multi-variable type inference failed */
    public EnumValueParser(List<? extends T> list, Function1<? super T, String> function1, Function1<? super T, Boolean> function2) {
        list.getClass();
        function1.getClass();
        function2.getClass();
        this.values = list;
        this.shortcut = function1;
        this.hideValue = function2;
    }

    public final Function1<T, Boolean> getHideValue() {
        return this.hideValue;
    }

    public final Function1<T, String> getShortcut() {
        return this.shortcut;
    }

    @Override // org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOption.ValueParser
    public String getValidValuesHint() {
        List<T> list = this.values;
        ArrayList<Enum> arrayList = new ArrayList();
        for (Object obj : list) {
            if (!((Boolean) this.hideValue.invoke((Enum) obj)).booleanValue()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (Enum r1 : arrayList) {
            String lowerCase = String.valueOf(r1).toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            String str = (String) this.shortcut.invoke(r1);
            if (str != null) {
                lowerCase = lowerCase + " (or: " + str + ')';
            }
            arrayList2.add(lowerCase);
        }
        return CollectionsKt.joinToString$default(arrayList2, "|", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public final List<T> getValues() {
        return this.values;
    }

    @Override // org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOption.ValueParser
    public T parse(String value) {
        Object next;
        value.getClass();
        Iterator<T> it = this.values.iterator();
        while (it.hasNext()) {
            next = it.next();
            Enum r2 = (Enum) next;
            if (!StringsKt.equals(r2.name(), value, true)) {
                String str = (String) this.shortcut.invoke(r2);
                if (str != null ? StringsKt.equals(str, value, true) : false) {
                }
            }
            return (T) next;
        }
        next = null;
        return (T) next;
    }
}
