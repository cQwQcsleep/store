package org.jetbrains.kotlin.container;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/container/IterableDescriptor;", "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "descriptors", Argument.Delimiters.none, "<init>", "(Ljava/lang/Iterable;)V", "getDescriptors", "()Ljava/lang/Iterable;", "getValue", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IterableDescriptor implements ValueDescriptor {
    private final Iterable<ValueDescriptor> descriptors;

    /* JADX WARN: Multi-variable type inference failed */
    public IterableDescriptor(Iterable<? extends ValueDescriptor> iterable) {
        iterable.getClass();
        this.descriptors = iterable;
    }

    public final Iterable<ValueDescriptor> getDescriptors() {
        return this.descriptors;
    }

    @Override // org.jetbrains.kotlin.container.ValueDescriptor
    public Object getValue() {
        Iterable<ValueDescriptor> iterable = this.descriptors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator<ValueDescriptor> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        return arrayList;
    }

    public String toString() {
        return "Iterable: " + this.descriptors;
    }
}
