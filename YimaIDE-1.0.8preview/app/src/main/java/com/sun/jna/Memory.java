package com.sun.jna;

import defpackage.gz9;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Memory extends Pointer {
    private static LinkedReference HEAD;
    private static ReferenceQueue<Memory> QUEUE = new ReferenceQueue<>();
    private static final WeakMemoryHolder buffers = new WeakMemoryHolder();
    private final LinkedReference reference;
    protected long size;

    public static class LinkedReference extends WeakReference<Memory> {
        private LinkedReference next;
        private LinkedReference prev;

        private LinkedReference(Memory memory) {
            super(memory, Memory.QUEUE);
        }

        public static LinkedReference track(Memory memory) {
            synchronized (Memory.QUEUE) {
                while (true) {
                    try {
                        LinkedReference linkedReference = (LinkedReference) Memory.QUEUE.poll();
                        if (linkedReference == null) {
                            break;
                        }
                        linkedReference.unlink();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            LinkedReference linkedReference2 = new LinkedReference(memory);
            synchronized (LinkedReference.class) {
                try {
                    if (Memory.HEAD != null) {
                        linkedReference2.next = Memory.HEAD;
                        Memory.HEAD.prev = linkedReference2;
                        LinkedReference unused = Memory.HEAD = linkedReference2;
                    } else {
                        LinkedReference unused2 = Memory.HEAD = linkedReference2;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            return linkedReference2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void unlink() {
            LinkedReference linkedReference;
            synchronized (LinkedReference.class) {
                try {
                    if (Memory.HEAD != this) {
                        LinkedReference linkedReference2 = this.prev;
                        if (linkedReference2 == null) {
                            return;
                        }
                        linkedReference = this.next;
                        linkedReference2.next = linkedReference;
                    } else {
                        linkedReference = Memory.HEAD = Memory.HEAD.next;
                    }
                    if (linkedReference != null) {
                        linkedReference.prev = this.prev;
                    }
                    this.prev = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public class SharedMemory extends Memory {
        public SharedMemory(long j, long j2) {
            this.size = j2;
            this.peer = Memory.this.peer + j;
        }

        @Override // com.sun.jna.Memory
        public void boundsCheck(long j, long j2) {
            Memory memory = Memory.this;
            memory.boundsCheck((this.peer - memory.peer) + j, j2);
        }

        @Override // com.sun.jna.Memory
        public synchronized void dispose() {
            this.peer = 0L;
        }

        @Override // com.sun.jna.Memory, com.sun.jna.Pointer
        public String toString() {
            return super.toString() + " (shared from " + Memory.this.toString() + ")";
        }
    }

    public Memory(long j) {
        this.size = j;
        if (j <= 0) {
            w01.a("Allocation size must be greater than zero");
            throw null;
        }
        long jMalloc = malloc(j);
        this.peer = jMalloc;
        if (jMalloc != 0) {
            this.reference = LinkedReference.track(this);
            return;
        }
        throw new OutOfMemoryError("Cannot allocate " + j + " bytes");
    }

    public static void disposeAll() {
        LinkedReference linkedReference;
        synchronized (LinkedReference.class) {
            do {
                try {
                    linkedReference = HEAD;
                    if (linkedReference != null) {
                        Memory memory = linkedReference.get();
                        if (memory != null) {
                            memory.dispose();
                        } else {
                            HEAD.unlink();
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } while (HEAD != linkedReference);
            throw new IllegalStateException("the HEAD did not change");
        }
        synchronized (QUEUE) {
            while (true) {
                try {
                    LinkedReference linkedReference2 = (LinkedReference) QUEUE.poll();
                    if (linkedReference2 != null) {
                        linkedReference2.unlink();
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    public static void free(long j) {
        if (j != 0) {
            Native.free(j);
        }
    }

    public static int integrityCheck() {
        synchronized (LinkedReference.class) {
            try {
                if (HEAD == null) {
                    return 0;
                }
                ArrayList arrayList = new ArrayList();
                for (LinkedReference linkedReference = HEAD; linkedReference != null; linkedReference = linkedReference.next) {
                    arrayList.add(linkedReference);
                }
                int size = arrayList.size() - 1;
                LinkedReference linkedReference2 = (LinkedReference) arrayList.get(size);
                while (linkedReference2 != null) {
                    if (arrayList.get(size) != linkedReference2) {
                        throw new IllegalStateException(arrayList.get(size) + " vs. " + linkedReference2 + " at index " + size);
                    }
                    linkedReference2 = linkedReference2.prev;
                    size--;
                }
                return arrayList.size();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static long malloc(long j) {
        return Native.malloc(j);
    }

    public static void purge() {
        buffers.clean();
    }

    private Pointer shareReferenceIfInBounds(Pointer pointer) {
        if (pointer == null) {
            return null;
        }
        long j = pointer.peer - this.peer;
        return (j < 0 || j >= this.size) ? pointer : share(j);
    }

    public Memory align(int i) {
        if (i <= 0) {
            qf1.a("Byte boundary must be positive: ", i);
            return null;
        }
        for (int i2 = 0; i2 < 32; i2++) {
            if (i == (1 << i2)) {
                long j = i;
                long j2 = ~(j - 1);
                long j3 = this.peer;
                if ((j3 & j2) == j3) {
                    return this;
                }
                long j4 = ((j + j3) - 1) & j2;
                long j5 = (this.size + j3) - j4;
                if (j5 > 0) {
                    return (Memory) share(j4 - j3, j5);
                }
                w01.a("Insufficient memory to align to the requested boundary");
                return null;
            }
        }
        w01.a("Byte boundary must be a power of two");
        return null;
    }

    public void boundsCheck(long j, long j2) {
        if (j < 0) {
            gz9.a("Invalid offset: ", j);
            return;
        }
        long j3 = j + j2;
        if (j3 <= this.size) {
            return;
        }
        throw new IndexOutOfBoundsException("Bounds exceeds available space : size=" + this.size + ", offset=" + j3);
    }

    public void clear() {
        clear(this.size);
    }

    public synchronized void dispose() {
        long j = this.peer;
        if (j == 0) {
            return;
        }
        try {
            free(j);
            this.peer = 0L;
            this.reference.unlink();
        } catch (Throwable th) {
            this.peer = 0L;
            this.reference.unlink();
            throw th;
        }
    }

    public String dump() {
        return dump(0L, (int) size());
    }

    public void finalize() {
        dispose();
    }

    @Override // com.sun.jna.Pointer
    public byte getByte(long j) {
        boundsCheck(j, 1L);
        return super.getByte(j);
    }

    @Override // com.sun.jna.Pointer
    public ByteBuffer getByteBuffer(long j, long j2) {
        boundsCheck(j, j2);
        ByteBuffer byteBuffer = super.getByteBuffer(j, j2);
        buffers.put(byteBuffer, this);
        return byteBuffer;
    }

    @Override // com.sun.jna.Pointer
    public char getChar(long j) {
        boundsCheck(j, Native.WCHAR_SIZE);
        return super.getChar(j);
    }

    @Override // com.sun.jna.Pointer
    public double getDouble(long j) {
        boundsCheck(j, 8L);
        return super.getDouble(j);
    }

    @Override // com.sun.jna.Pointer
    public float getFloat(long j) {
        boundsCheck(j, 4L);
        return super.getFloat(j);
    }

    @Override // com.sun.jna.Pointer
    public int getInt(long j) {
        boundsCheck(j, 4L);
        return super.getInt(j);
    }

    @Override // com.sun.jna.Pointer
    public long getLong(long j) {
        boundsCheck(j, 8L);
        return super.getLong(j);
    }

    @Override // com.sun.jna.Pointer
    public Pointer getPointer(long j) {
        boundsCheck(j, Native.POINTER_SIZE);
        return shareReferenceIfInBounds(super.getPointer(j));
    }

    @Override // com.sun.jna.Pointer
    public short getShort(long j) {
        boundsCheck(j, 2L);
        return super.getShort(j);
    }

    @Override // com.sun.jna.Pointer
    public String getString(long j, String str) {
        boundsCheck(j, 0L);
        return super.getString(j, str);
    }

    @Override // com.sun.jna.Pointer
    public String getWideString(long j) {
        boundsCheck(j, 0L);
        return super.getWideString(j);
    }

    @Override // com.sun.jna.Pointer
    public void read(long j, short[] sArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 2);
        super.read(j, sArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void setByte(long j, byte b) {
        boundsCheck(j, 1L);
        super.setByte(j, b);
    }

    @Override // com.sun.jna.Pointer
    public void setChar(long j, char c) {
        boundsCheck(j, Native.WCHAR_SIZE);
        super.setChar(j, c);
    }

    @Override // com.sun.jna.Pointer
    public void setDouble(long j, double d) {
        boundsCheck(j, 8L);
        super.setDouble(j, d);
    }

    @Override // com.sun.jna.Pointer
    public void setFloat(long j, float f) {
        boundsCheck(j, 4L);
        super.setFloat(j, f);
    }

    @Override // com.sun.jna.Pointer
    public void setInt(long j, int i) {
        boundsCheck(j, 4L);
        super.setInt(j, i);
    }

    @Override // com.sun.jna.Pointer
    public void setLong(long j, long j2) {
        boundsCheck(j, 8L);
        super.setLong(j, j2);
    }

    @Override // com.sun.jna.Pointer
    public void setPointer(long j, Pointer pointer) {
        boundsCheck(j, Native.POINTER_SIZE);
        super.setPointer(j, pointer);
    }

    @Override // com.sun.jna.Pointer
    public void setShort(long j, short s) {
        boundsCheck(j, 2L);
        super.setShort(j, s);
    }

    @Override // com.sun.jna.Pointer
    public void setString(long j, String str, String str2) {
        boundsCheck(j, ((long) Native.getBytes(str, str2).length) + 1);
        super.setString(j, str, str2);
    }

    @Override // com.sun.jna.Pointer
    public void setWideString(long j, String str) {
        boundsCheck(j, (((long) str.length()) + 1) * ((long) Native.WCHAR_SIZE));
        super.setWideString(j, str);
    }

    @Override // com.sun.jna.Pointer
    public Pointer share(long j, long j2) {
        boundsCheck(j, j2);
        return new SharedMemory(j, j2);
    }

    public long size() {
        return this.size;
    }

    @Override // com.sun.jna.Pointer
    public String toString() {
        return "allocated@0x" + Long.toHexString(this.peer) + " (" + this.size + " bytes)";
    }

    public boolean valid() {
        return this.peer != 0;
    }

    @Override // com.sun.jna.Pointer
    public void write(long j, short[] sArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 2);
        super.write(j, sArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void read(long j, byte[] bArr, int i, int i2) {
        boundsCheck(j, i2);
        super.read(j, bArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void write(long j, byte[] bArr, int i, int i2) {
        boundsCheck(j, i2);
        super.write(j, bArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public Pointer share(long j) {
        return share(j, size() - j);
    }

    @Override // com.sun.jna.Pointer
    public void read(long j, char[] cArr, int i, int i2) {
        boundsCheck(j, Native.WCHAR_SIZE * i2);
        super.read(j, cArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void write(long j, char[] cArr, int i, int i2) {
        boundsCheck(j, Native.WCHAR_SIZE * i2);
        super.write(j, cArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void read(long j, int[] iArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 4);
        super.read(j, iArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void write(long j, int[] iArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 4);
        super.write(j, iArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void read(long j, long[] jArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 8);
        super.read(j, jArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void write(long j, long[] jArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 8);
        super.write(j, jArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void read(long j, float[] fArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 4);
        super.read(j, fArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void write(long j, float[] fArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 4);
        super.write(j, fArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void read(long j, double[] dArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 8);
        super.read(j, dArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void write(long j, double[] dArr, int i, int i2) {
        boundsCheck(j, ((long) i2) * 8);
        super.write(j, dArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void read(long j, Pointer[] pointerArr, int i, int i2) {
        boundsCheck(j, Native.POINTER_SIZE * i2);
        super.read(j, pointerArr, i, i2);
    }

    @Override // com.sun.jna.Pointer
    public void write(long j, Pointer[] pointerArr, int i, int i2) {
        boundsCheck(j, Native.POINTER_SIZE * i2);
        super.write(j, pointerArr, i, i2);
    }

    public Memory() {
        this.reference = null;
    }
}
