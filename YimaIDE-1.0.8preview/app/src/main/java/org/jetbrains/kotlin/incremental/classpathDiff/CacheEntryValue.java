package org.jetbrains.kotlin.incremental.classpathDiff;

import java.lang.ref.SoftReference;
import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B+\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\n\u001a\u00028\u0000\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\fJ\r\u0010\u000e\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0002\u0010\fJ\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014J\u0006\u0010\u0006\u001a\u00020\u0007R\u0012\u0010\u0003\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000Ê\u0001\u0002\b\u0017¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/CacheEntryValue;", "VALUE", "", "strongRef", "softRef", "Ljava/lang/ref/SoftReference;", "lastUsed", "", "<init>", "(Ljava/lang/Object;Ljava/lang/ref/SoftReference;I)V", "value", "currentTimePeriod", "(Ljava/lang/Object;I)V", "Ljava/lang/Object;", "get", "()Ljava/lang/Object;", "setStrongReference", "", "updateToSoftReference", "valueIsAStrongReference", "", "valueWasGarbageCollected", "org.jetbrains.kotlin:incremental-compilation-impl", "Ljavax/annotation/concurrent/NotThreadSafe;"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CacheEntryValue<VALUE> {
    private int lastUsed;
    private SoftReference<VALUE> softRef;
    private VALUE strongRef;

    private CacheEntryValue(VALUE value, SoftReference<VALUE> softReference, int i) {
        this.strongRef = value;
        this.softRef = softReference;
        this.lastUsed = i;
    }

    public final VALUE get() {
        VALUE value = this.strongRef;
        if (value != null) {
            return value;
        }
        SoftReference<VALUE> softReference = this.softRef;
        softReference.getClass();
        return softReference.get();
    }

    /* JADX INFO: renamed from: lastUsed, reason: from getter */
    public final int getLastUsed() {
        return this.lastUsed;
    }

    public final void setStrongReference(VALUE value, int currentTimePeriod) {
        this.strongRef = value;
        this.softRef = null;
        this.lastUsed = currentTimePeriod;
    }

    public final void updateToSoftReference() {
        if (this.strongRef != null) {
            this.softRef = new SoftReference<>(this.strongRef);
            this.strongRef = null;
        }
    }

    public final boolean valueIsAStrongReference() {
        return this.strongRef != null;
    }

    public final boolean valueWasGarbageCollected() {
        return get() == null;
    }

    public CacheEntryValue(VALUE value, int i) {
        this(value, null, i);
    }
}
