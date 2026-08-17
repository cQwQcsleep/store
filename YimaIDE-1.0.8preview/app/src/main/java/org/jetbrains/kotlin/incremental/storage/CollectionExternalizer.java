package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ1\u0010\n\u001a\u0016\u0012\u0004\u0012\u00028\u0000 \u000b*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00030\u00032\u0012\b\u0001\u0010\f\u001a\f0\r¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0096\u0001J9\u0010\u0010\u001a\u00020\u00112\u0012\b\u0001\u0010\f\u001a\f0\u0012¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000f2\u001a\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00028\u0000 \u000b*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00030\u0003H\u0096\u0001R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/CollectionExternalizer;", "T", "Lcom/intellij/util/io/DataExternalizer;", "", "elementExternalizer", "newCollection", "Lkotlin/Function0;", "", "<init>", "(Lcom/intellij/util/io/DataExternalizer;Lkotlin/jvm/functions/Function0;)V", "read", JvmProtoBufUtil.PLATFORM_TYPE_ID, "p0", "Ljava/io/DataInput;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "save", "", "Ljava/io/DataOutput;", "p1", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CollectionExternalizer<T> implements DataExternalizer<Collection<? extends T>> {
    private final /* synthetic */ CollectionExternalizerForPersistentHashMap<T> $$delegate_0;
    private final DataExternalizer<T> elementExternalizer;
    private final Function0<Collection<T>> newCollection;

    /* JADX WARN: Multi-variable type inference failed */
    public CollectionExternalizer(DataExternalizer<T> dataExternalizer, Function0<? extends Collection<T>> function0) {
        dataExternalizer.getClass();
        function0.getClass();
        this.$$delegate_0 = new CollectionExternalizerForPersistentHashMap<>(dataExternalizer, function0);
        this.elementExternalizer = dataExternalizer;
        this.newCollection = function0;
    }

    public Collection<T> read(DataInput p0) {
        p0.getClass();
        return this.$$delegate_0.read(p0);
    }

    public void save(DataOutput p0, Collection<? extends T> p1) {
        p0.getClass();
        this.$$delegate_0.save(p0, (Collection) p1);
    }
}
