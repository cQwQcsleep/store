package org.jetbrains.kotlin.incremental.classpathDiff;

import com.intellij.util.containers.Interner;
import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.incremental.storage.JvmClassNameExternalizer;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J1\u0010\u0010\u001a\u00020\u00112\u0016\b\u0001\u0010\u0012\u001a\f0\u0013¢\u0006\u0002\b\b¢\u0006\u0002\b\u0014:\u0002\b\b2\u000e\u0010\u0015\u001a\n \u0007*\u0004\u0018\u00010\u00020\u0002H\u0096\u0001R.\u0010\u0005\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00020\u00020\u0006¢\u0006\u0002\b\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/JvmClassNameExternalizerWithInterning;", "Lcom/intellij/util/io/DataExternalizer;", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "<init>", "()V", "jvmClassNameInterner", "Lcom/intellij/util/containers/Interner;", JvmProtoBufUtil.PLATFORM_TYPE_ID, "Lorg/jetbrains/annotations/NotNull;", "getJvmClassNameInterner", "()Lcom/intellij/util/containers/Interner;", "jvmClassNameInterner$delegate", "Lkotlin/Lazy;", "read", "input", "Ljava/io/DataInput;", "save", "", "p0", "Ljava/io/DataOutput;", "Lkotlin/jvm/internal/EnhancedNullability;", "p1", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class JvmClassNameExternalizerWithInterning implements DataExternalizer<JvmClassName> {
    public static final JvmClassNameExternalizerWithInterning INSTANCE = new JvmClassNameExternalizerWithInterning();

    /* JADX INFO: renamed from: jvmClassNameInterner$delegate, reason: from kotlin metadata */
    private static final Lazy jvmClassNameInterner = LazyKt.lazy(new Function0() { // from class: org.jetbrains.kotlin.incremental.classpathDiff.g
        public final Object invoke() {
            return JvmClassNameExternalizerWithInterning.a();
        }
    });
    private final /* synthetic */ JvmClassNameExternalizer $$delegate_0 = JvmClassNameExternalizer.INSTANCE;

    private JvmClassNameExternalizerWithInterning() {
    }

    public static Interner a() {
        return Interner.createWeakInterner();
    }

    private final Interner<JvmClassName> getJvmClassNameInterner() {
        return (Interner) jvmClassNameInterner.getValue();
    }

    public JvmClassName read(DataInput input) {
        input.getClass();
        Object objIntern = getJvmClassNameInterner().intern(JvmClassNameExternalizer.INSTANCE.read(input));
        objIntern.getClass();
        return (JvmClassName) objIntern;
    }

    public void save(DataOutput p0, JvmClassName p1) {
        p0.getClass();
        this.$$delegate_0.save(p0, p1);
    }
}
