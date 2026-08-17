package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\t\u001a\u00028\u0000H&¢\u0006\u0002\u0010\nJ\r\u0010\u000b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\nJ\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\rH&J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H&J(\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0000\"\u0004\b\u0001\u0010\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00140\u0016H&R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;", "T", Argument.Delimiters.none, "<init>", "()V", "size", Argument.Delimiters.none, "getSize", "()I", "top", "()Ljava/lang/Object;", "pop", "push", Argument.Delimiters.none, "value", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "reset", "all", Argument.Delimiters.none, "createSnapshot", "R", "transform", "Lkotlin/Function1;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class Stack<T> {
    public abstract List<T> all();

    public abstract <R> Stack<R> createSnapshot(Function1<? super T, ? extends R> transform);

    public abstract int getSize();

    public abstract T pop();

    public abstract void push(T value);

    public abstract void reset();

    public abstract T top();
}
