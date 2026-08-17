package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0016\u0012\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0007\"\u00028\u0000¢\u0006\u0004\b\u0005\u0010\bJ\r\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\r\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0012J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016J(\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0002\"\u0004\b\u0001\u0010\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u001a0\u001cH\u0016R.\u0010\u0003\u001a\"\u0012\f\u0012\n \n*\u0004\u0018\u00018\u00008\u00000\tj\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00018\u00008\u0000`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/StackImpl;", "T", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;", "values", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", Argument.Delimiters.none, "([Ljava/lang/Object;)V", "Ljava/util/ArrayList;", "kotlin.jvm.PlatformType", "Lkotlin/collections/ArrayList;", "top", "()Ljava/lang/Object;", "pop", "push", Argument.Delimiters.none, "value", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "size", Argument.Delimiters.none, "getSize", "()I", "reset", "all", "createSnapshot", "R", "transform", "Lkotlin/Function1;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class StackImpl<T> extends Stack<T> {
    private final ArrayList<T> values;

    public StackImpl(List<? extends T> list) {
        list.getClass();
        this.values = new ArrayList<>(list);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Stack
    public List<T> all() {
        return CollectionsKt.asReversedMutable(this.values);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Stack
    public <R> Stack<R> createSnapshot(Function1<? super T, ? extends R> transform) {
        transform.getClass();
        ArrayList<T> arrayList = this.values;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(transform.invoke(it.next()));
        }
        return new StackImpl(arrayList2);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Stack
    public int getSize() {
        return this.values.size();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Stack
    public T pop() {
        ArrayList<T> arrayList = this.values;
        return arrayList.remove(arrayList.size() - 1);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Stack
    public void push(T value) {
        this.values.add(value);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Stack
    public void reset() {
        this.values.clear();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Stack
    public T top() {
        ArrayList<T> arrayList = this.values;
        return arrayList.get(arrayList.size() - 1);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StackImpl(T... tArr) {
        this(ArraysKt.asList(tArr));
        tArr.getClass();
    }
}
