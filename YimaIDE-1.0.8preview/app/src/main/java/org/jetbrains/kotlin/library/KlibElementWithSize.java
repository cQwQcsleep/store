package org.jetbrains.kotlin.library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.library.abi.AbiQualifiedName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001d\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\b¢\u0006\u0002\u0010\tB%\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\b¢\u0006\u0002\u0010\nJ\u0018\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u00180\bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\"\u0010\u0012\u001a\u0004\u0018\u00010\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0000@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/library/KlibElementWithSize;", "", "name", "", "size", "", "(Ljava/lang/String;J)V", "children", "", "(Ljava/lang/String;Ljava/util/List;)V", "(Ljava/lang/String;JLjava/util/List;)V", "getChildren", "()Ljava/util/List;", "fullName", "getFullName", "()Ljava/lang/String;", "getName", "<set-?>", "parent", "getParent", "()Lorg/jetbrains/kotlin/library/KlibElementWithSize;", "getSize", "()J", "flatten", "Lkotlin/Pair;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KlibElementWithSize {
    private final List<KlibElementWithSize> children;
    private final String name;
    private KlibElementWithSize parent;
    private final long size;

    /* JADX WARN: Illegal instructions before constructor call */
    public KlibElementWithSize(String str, List<KlibElementWithSize> list) {
        str.getClass();
        list.getClass();
        Iterator<T> it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += ((KlibElementWithSize) it.next()).size;
        }
        this(str, j, list);
    }

    public final List<Pair<String, Long>> flatten() {
        List listListOf = CollectionsKt.listOf(TuplesKt.to(getFullName(), Long.valueOf(this.size)));
        List<KlibElementWithSize> list = this.children;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((KlibElementWithSize) it.next()).flatten());
        }
        return CollectionsKt.plus(listListOf, arrayList);
    }

    public final List<KlibElementWithSize> getChildren() {
        return this.children;
    }

    public final String getFullName() {
        KlibElementWithSize klibElementWithSize = this.parent;
        if (klibElementWithSize == null) {
            return this.name;
        }
        return klibElementWithSize.getFullName() + AbiQualifiedName.SEPARATOR + this.name;
    }

    public final String getName() {
        return this.name;
    }

    public final KlibElementWithSize getParent() {
        return this.parent;
    }

    public final long getSize() {
        return this.size;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KlibElementWithSize(String str, long j) {
        this(str, j, CollectionsKt.emptyList());
        str.getClass();
    }

    private KlibElementWithSize(String str, long j, List<KlibElementWithSize> list) {
        this.name = str;
        this.size = j;
        this.children = list;
        Iterator<KlibElementWithSize> it = list.iterator();
        while (it.hasNext()) {
            it.next().parent = this;
        }
    }
}
