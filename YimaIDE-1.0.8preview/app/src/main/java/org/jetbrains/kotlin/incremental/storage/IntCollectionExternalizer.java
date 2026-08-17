package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.incremental.storage.IntCollectionExternalizer;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J1\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0003 \u0007*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u00022\u0012\b\u0001\u0010\b\u001a\f0\t¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0096\u0001J9\u0010\f\u001a\u00020\r2\u0012\b\u0001\u0010\b\u001a\f0\u000e¢\u0006\u0002\b\n¢\u0006\u0002\b\u000b2\u001a\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0003 \u0007*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002H\u0096\u0001¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/IntCollectionExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "", "", "<init>", "()V", "read", JvmProtoBufUtil.PLATFORM_TYPE_ID, "p0", "Ljava/io/DataInput;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "save", "", "Ljava/io/DataOutput;", "p1", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IntCollectionExternalizer implements DataExternalizer<Collection<? extends Integer>> {
    public static final IntCollectionExternalizer INSTANCE = new IntCollectionExternalizer();
    private final /* synthetic */ CollectionExternalizerForPersistentHashMap<Integer> $$delegate_0 = new CollectionExternalizerForPersistentHashMap<>(IntExternalizer.INSTANCE, new Function0() { // from class: xr6
        public final Object invoke() {
            return IntCollectionExternalizer.a();
        }
    });

    private IntCollectionExternalizer() {
    }

    public static Collection a() {
        return new ArrayList();
    }

    public Collection<Integer> read(DataInput p0) {
        p0.getClass();
        return this.$$delegate_0.read(p0);
    }

    public void save(DataOutput p0, Collection<Integer> p1) {
        p0.getClass();
        this.$$delegate_0.save(p0, (Collection<? extends Integer>) p1);
    }
}
