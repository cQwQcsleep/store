package org.jetbrains.kotlin.incremental.impl;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.incremental.storage.DelegateDataExternalizer;
import org.jetbrains.kotlin.incremental.storage.DoubleExternalizer;
import org.jetbrains.kotlin.incremental.storage.FloatExternalizer;
import org.jetbrains.kotlin.incremental.storage.IntExternalizer;
import org.jetbrains.kotlin.incremental.storage.LongExternalizer;
import org.jetbrains.kotlin.incremental.storage.StringExternalizer;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J%\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00020\u00022\u0012\b\u0001\u0010\u0007\u001a\f0\b¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0096\u0001J-\u0010\u000b\u001a\u00020\f2\u0012\b\u0001\u0010\u0007\u001a\f0\r¢\u0006\u0002\b\t¢\u0006\u0002\b\n2\u000e\u0010\u000e\u001a\n \u0006*\u0004\u0018\u00010\u00020\u0002H\u0096\u0001¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/impl/ConstantValueExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "", "<init>", "()V", "read", JvmProtoBufUtil.PLATFORM_TYPE_ID, "p0", "Ljava/io/DataInput;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "save", "", "Ljava/io/DataOutput;", "p1", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class ConstantValueExternalizer implements DataExternalizer<Object> {
    public static final ConstantValueExternalizer INSTANCE = new ConstantValueExternalizer();
    private final /* synthetic */ DelegateDataExternalizer<Object> $$delegate_0 = new DelegateDataExternalizer<>(CollectionsKt.listOf(new Class[]{Integer.class, Long.class, Float.class, Double.class, String.class}), CollectionsKt.listOf(new DataExternalizer[]{IntExternalizer.INSTANCE, LongExternalizer.INSTANCE, FloatExternalizer.INSTANCE, DoubleExternalizer.INSTANCE, StringExternalizer.INSTANCE}));

    private ConstantValueExternalizer() {
    }

    public Object read(DataInput p0) {
        p0.getClass();
        return this.$$delegate_0.read(p0);
    }

    public void save(DataOutput p0, Object p1) throws IOException {
        p0.getClass();
        this.$$delegate_0.save(p0, p1);
    }
}
