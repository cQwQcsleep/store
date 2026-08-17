package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantUtf8 extends Constant {
    private static final String SYS_PROP_CACHE_MAX_ENTRIES = "bcel.maxcached";
    private static final String SYS_PROP_CACHE_MAX_ENTRY_SIZE = "bcel.maxcached.size";
    private static final String SYS_PROP_STATISTICS = "bcel.statistics";
    private static volatile int considered;
    private static volatile int created;
    private static volatile int hits;
    private static volatile int skipped;
    private final String value;

    public static class Cache {
        private static final boolean BCEL_STATISTICS = false;
        private static final int MAX_ENTRIES = 20000;
        private static final int MAX_ENTRY_SIZE = 200;
        private static final int INITIAL_CAPACITY = 26666;
        private static final HashMap<String, ConstantUtf8> CACHE = new LinkedHashMap<String, ConstantUtf8>(INITIAL_CAPACITY, 0.75f, true) { // from class: com.sun.org.apache.bcel.internal.classfile.ConstantUtf8.Cache.1
            private static final long serialVersionUID = -8506975356158971766L;

            @Override // java.util.LinkedHashMap
            public boolean removeEldestEntry(Map.Entry<String, ConstantUtf8> entry) {
                return size() > Cache.MAX_ENTRIES;
            }
        };

        private Cache() {
        }

        public static boolean isEnabled() {
            return true;
        }
    }

    public ConstantUtf8(String str) {
        super((byte) 1);
        Objects.requireNonNull(str, "value");
        this.value = str;
        created++;
    }

    public static synchronized void clearCache() {
        Cache.CACHE.clear();
    }

    public static synchronized void clearStats() {
        created = 0;
        skipped = 0;
        considered = 0;
        hits = 0;
    }

    public static ConstantUtf8 getCachedInstance(String str) {
        if (str.length() > 200) {
            skipped++;
            return new ConstantUtf8(str);
        }
        considered++;
        synchronized (ConstantUtf8.class) {
            try {
                ConstantUtf8 constantUtf8 = (ConstantUtf8) Cache.CACHE.get(str);
                if (constantUtf8 != null) {
                    hits++;
                    return constantUtf8;
                }
                ConstantUtf8 constantUtf9 = new ConstantUtf8(str);
                Cache.CACHE.put(str, constantUtf9);
                return constantUtf9;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static ConstantUtf8 getInstance(String str) {
        return Cache.isEnabled() ? getCachedInstance(str) : new ConstantUtf8(str);
    }

    public static void printStats() {
        System.err.printf("%s Cache hit %,d/%,d, %d skipped.%n", "[Apache Commons BCEL]", Integer.valueOf(hits), Integer.valueOf(considered), Integer.valueOf(skipped));
        System.err.printf("%s Total of %,d ConstantUtf8 objects created.%n", "[Apache Commons BCEL]", Integer.valueOf(created));
        System.err.printf("%s Configuration: %s=%,d, %s=%,d.%n", "[Apache Commons BCEL]", SYS_PROP_CACHE_MAX_ENTRIES, 20000, SYS_PROP_CACHE_MAX_ENTRY_SIZE, 200);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantUtf8(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeUTF(this.value);
    }

    public String getBytes() {
        return this.value;
    }

    @java.lang.Deprecated
    public void setBytes(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(\"" + Utility.replace(this.value, "\n", "\\n") + "\")";
    }

    public static ConstantUtf8 getInstance(DataInput dataInput) throws IOException {
        return getInstance(dataInput.readUTF());
    }

    public ConstantUtf8(DataInput dataInput) throws IOException {
        super((byte) 1);
        this.value = dataInput.readUTF();
        created++;
    }

    public ConstantUtf8(ConstantUtf8 constantUtf8) {
        this(constantUtf8.getBytes());
    }
}
