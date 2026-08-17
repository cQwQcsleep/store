package com.intellij.util.io;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0004\t\n\u000b\fB\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0004\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/intellij/util/io/WalEvent;", "K", "V", "", "<init>", "()V", "key", "getKey", "()Ljava/lang/Object;", "PutEvent", "RemoveEvent", "AppendEvent", "CorruptionEvent", "Lcom/intellij/util/io/WalEvent$AppendEvent;", "Lcom/intellij/util/io/WalEvent$CorruptionEvent;", "Lcom/intellij/util/io/WalEvent$PutEvent;", "Lcom/intellij/util/io/WalEvent$RemoveEvent;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class WalEvent<K, V> {

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u000e\u0010\u0014\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J.\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u0017J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0016\u0010\u0004\u001a\u00028\u0002X\u0096\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/intellij/util/io/WalEvent$AppendEvent;", "K", "V", "Lcom/intellij/util/io/WalEvent;", "key", "data", "", "<init>", "(Ljava/lang/Object;[B)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getData", "()[B", "equals", "", "other", "", "hashCode", "", "component1", "component2", "copy", "(Ljava/lang/Object;[B)Lcom/intellij/util/io/WalEvent$AppendEvent;", "toString", "", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class AppendEvent<K, V> extends WalEvent<K, V> {
        private final byte[] data;
        private final K key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AppendEvent(K k, byte[] bArr) {
            super(null);
            bArr.getClass();
            this.key = k;
            this.data = bArr;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(AppendEvent.class, other != null ? other.getClass() : null)) {
                return false;
            }
            other.getClass();
            AppendEvent appendEvent = (AppendEvent) other;
            return Intrinsics.areEqual(getKey(), appendEvent.getKey()) && Arrays.equals(this.data, appendEvent.data);
        }

        public final byte[] getData() {
            return this.data;
        }

        @Override // com.intellij.util.io.WalEvent
        public K getKey() {
            return this.key;
        }

        public int hashCode() {
            K key = getKey();
            return ((key != null ? key.hashCode() : 0) * 31) + Arrays.hashCode(this.data);
        }

        public String toString() {
            return "AppendEvent(key=" + this.key + ", data=" + Arrays.toString(this.data) + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/intellij/util/io/WalEvent$CorruptionEvent;", "Lcom/intellij/util/io/WalEvent;", "", "<init>", "()V", "key", "getKey", "()Ljava/lang/Void;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class CorruptionEvent extends WalEvent {
        public static final CorruptionEvent INSTANCE = new CorruptionEvent();

        private CorruptionEvent() {
            super(null);
        }

        @Override // com.intellij.util.io.WalEvent
        public Void getKey() {
            throw new UnsupportedOperationException();
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0002\u0012\u0006\u0010\u0005\u001a\u00028\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\f\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\tJ\u000e\u0010\r\u001a\u00028\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ.\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00022\b\b\u0002\u0010\u0005\u001a\u00028\u0003HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0016\u0010\u0004\u001a\u00028\u0002X\u0096\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u00028\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/intellij/util/io/WalEvent$PutEvent;", "K", "V", "Lcom/intellij/util/io/WalEvent;", "key", "value", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/util/io/WalEvent$PutEvent;", "equals", "", "other", "", "hashCode", "", "toString", "", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class PutEvent<K, V> extends WalEvent<K, V> {
        private final K key;
        private final V value;

        public PutEvent(K k, V v) {
            super(null);
            this.key = k;
            this.value = v;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PutEvent)) {
                return false;
            }
            PutEvent putEvent = (PutEvent) other;
            return Intrinsics.areEqual(this.key, putEvent.key) && Intrinsics.areEqual(this.value, putEvent.value);
        }

        @Override // com.intellij.util.io.WalEvent
        public K getKey() {
            return this.key;
        }

        public final V getValue() {
            return this.value;
        }

        public int hashCode() {
            K k = this.key;
            int iHashCode = (k == null ? 0 : k.hashCode()) * 31;
            V v = this.value;
            return iHashCode + (v != null ? v.hashCode() : 0);
        }

        public String toString() {
            return "PutEvent(key=" + this.key + ", value=" + this.value + ')';
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\bJ$\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0002HÆ\u0001¢\u0006\u0002\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0016\u0010\u0004\u001a\u00028\u0002X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/intellij/util/io/WalEvent$RemoveEvent;", "K", "V", "Lcom/intellij/util/io/WalEvent;", "key", "<init>", "(Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/intellij/util/io/WalEvent$RemoveEvent;", "equals", "", "other", "", "hashCode", "", "toString", "", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class RemoveEvent<K, V> extends WalEvent<K, V> {
        private final K key;

        public RemoveEvent(K k) {
            super(null);
            this.key = k;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof RemoveEvent) && Intrinsics.areEqual(this.key, ((RemoveEvent) other).key);
        }

        @Override // com.intellij.util.io.WalEvent
        public K getKey() {
            return this.key;
        }

        public int hashCode() {
            K k = this.key;
            if (k == null) {
                return 0;
            }
            return k.hashCode();
        }

        public String toString() {
            return "RemoveEvent(key=" + this.key + ')';
        }
    }

    public /* synthetic */ WalEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract K getKey();

    private WalEvent() {
    }
}
