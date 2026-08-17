package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.incremental.storage.CollectionExternalizerV2;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004B@\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012)\b\u0002\u0010\u0006\u001a#\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001a\u00028\u00012\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u0006\u001a#\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/CollectionExternalizerV2;", "T", "C", "", "Lcom/intellij/util/io/DataExternalizer;", "elementExternalizer", "newCollection", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "size", "", "<init>", "(Lcom/intellij/util/io/DataExternalizer;Lkotlin/jvm/functions/Function1;)V", "save", "", "output", "Ljava/io/DataOutput;", "collection", "(Ljava/io/DataOutput;Ljava/util/Collection;)V", "read", "input", "Ljava/io/DataInput;", "(Ljava/io/DataInput;)Ljava/util/Collection;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class CollectionExternalizerV2<T, C extends Collection<? extends T>> implements DataExternalizer<C> {
    private final DataExternalizer<T> elementExternalizer;
    private final Function1<Integer, Collection<T>> newCollection;

    /* JADX WARN: Multi-variable type inference failed */
    public CollectionExternalizerV2(DataExternalizer<T> dataExternalizer, Function1<? super Integer, ? extends Collection<T>> function1) {
        dataExternalizer.getClass();
        function1.getClass();
        this.elementExternalizer = dataExternalizer;
        this.newCollection = function1;
    }

    public static ArrayList a(int i) {
        return new ArrayList(i);
    }

    public C read(DataInput input) throws IOException {
        input.getClass();
        int i = input.readInt();
        C c = (C) this.newCollection.invoke(Integer.valueOf(i));
        for (int i2 = 0; i2 < i; i2++) {
            c.add(this.elementExternalizer.read(input));
        }
        c.getClass();
        return c;
    }

    public void save(DataOutput output, C collection) throws IOException {
        output.getClass();
        collection.getClass();
        output.writeInt(collection.size());
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            this.elementExternalizer.save(output, it.next());
        }
    }

    public /* synthetic */ CollectionExternalizerV2(DataExternalizer dataExternalizer, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(dataExternalizer, (i & 2) != 0 ? new Function1() { // from class: q52
            public final Object invoke(Object obj) {
                return CollectionExternalizerV2.a(((Integer) obj).intValue());
            }
        } : function1);
    }
}
