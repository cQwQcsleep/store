package com.sun.jna.platform.unix.solaris;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LibKstat extends Library {
    public static final int EAGAIN = 11;
    public static final LibKstat INSTANCE = (LibKstat) Native.load("kstat", LibKstat.class);
    public static final byte KSTAT_DATA_CHAR = 0;
    public static final byte KSTAT_DATA_INT32 = 1;
    public static final byte KSTAT_DATA_INT64 = 3;
    public static final byte KSTAT_DATA_STRING = 9;
    public static final byte KSTAT_DATA_UINT32 = 2;
    public static final byte KSTAT_DATA_UINT64 = 4;
    public static final int KSTAT_INTR_HARD = 0;
    public static final int KSTAT_INTR_MULTSVC = 4;
    public static final int KSTAT_INTR_SOFT = 1;
    public static final int KSTAT_INTR_SPURIOUS = 3;
    public static final int KSTAT_INTR_WATCHDOG = 2;
    public static final int KSTAT_NUM_INTRS = 5;
    public static final int KSTAT_STRLEN = 31;
    public static final byte KSTAT_TYPE_INTR = 2;
    public static final byte KSTAT_TYPE_IO = 3;
    public static final byte KSTAT_TYPE_NAMED = 1;
    public static final byte KSTAT_TYPE_RAW = 0;
    public static final byte KSTAT_TYPE_TIMER = 4;

    @Structure.FieldOrder({"ks_crtime", "ks_next", "ks_kid", "ks_module", "ks_resv", "ks_instance", "ks_name", "ks_type", "ks_class", "ks_flags", "ks_data", "ks_ndata", "ks_data_size", "ks_snaptime", "ks_update", "ks_private", "ks_snapshot", "ks_lock"})
    public static class Kstat extends Structure {
        public long ks_crtime;
        public Pointer ks_data;
        public long ks_data_size;
        public byte ks_flags;
        public int ks_instance;
        public int ks_kid;
        public Pointer ks_lock;
        public int ks_ndata;
        public Pointer ks_next;
        public Pointer ks_private;
        public byte ks_resv;
        public int ks_snapshot;
        public long ks_snaptime;
        public byte ks_type;
        public int ks_update;
        public byte[] ks_module = new byte[31];
        public byte[] ks_name = new byte[31];
        public byte[] ks_class = new byte[31];

        public Kstat next() {
            if (this.ks_next == null) {
                return null;
            }
            Kstat kstat = new Kstat();
            kstat.useMemory(this.ks_next);
            kstat.read();
            return kstat;
        }
    }

    @Structure.FieldOrder({"kc_chain_id", "kc_chain", "kc_kd"})
    public static class KstatCtl extends Structure {
        public Kstat kc_chain;
        public int kc_chain_id;
        public int kc_kd;
    }

    @Structure.FieldOrder({"intrs"})
    public static class KstatIntr extends Structure {
        public int[] intrs = new int[5];
    }

    @Structure.FieldOrder({"name", "resv", "num_events", "elapsed_time", "min_time", "max_time", "start_time", "stop_time"})
    public static class KstatTimer extends Structure {
        public long elapsed_time;
        public long max_time;
        public long min_time;
        public byte[] name = new byte[31];
        public long num_events;
        public byte resv;
        public long start_time;
        public long stop_time;
    }

    int kstat_chain_update(KstatCtl kstatCtl);

    int kstat_close(KstatCtl kstatCtl);

    Pointer kstat_data_lookup(Kstat kstat, String str);

    Kstat kstat_lookup(KstatCtl kstatCtl, String str, int i, String str2);

    KstatCtl kstat_open();

    int kstat_read(KstatCtl kstatCtl, Kstat kstat, Pointer pointer);

    int kstat_write(KstatCtl kstatCtl, Kstat kstat, Pointer pointer);

    @Structure.FieldOrder({"nread", "nwritten", "reads", "writes", "wtime", "wlentime", "wlastupdate", "rtime", "rlentime", "rlastupdate", "wcnt", "rcnt"})
    public static class KstatIO extends Structure {
        public long nread;
        public long nwritten;
        public int rcnt;
        public int reads;
        public long rlastupdate;
        public long rlentime;
        public long rtime;
        public int wcnt;
        public long wlastupdate;
        public long wlentime;
        public int writes;
        public long wtime;

        public KstatIO(Pointer pointer) {
            super(pointer);
            read();
        }

        public KstatIO() {
        }
    }

    @Structure.FieldOrder({"name", "data_type", "value"})
    public static class KstatNamed extends Structure {
        public byte data_type;
        public byte[] name;
        public UNION value;

        public static class UNION extends Union {
            public byte[] charc = new byte[16];
            public int i32;
            public long i64;
            public STR str;
            public int ui32;
            public long ui64;

            @Structure.FieldOrder({"addr", "len"})
            public static class STR extends Structure {
                public Pointer addr;
                public int len;
            }
        }

        public KstatNamed(Pointer pointer) {
            super(pointer);
            this.name = new byte[31];
            read();
        }

        @Override // com.sun.jna.Structure
        public void read() {
            super.read();
            byte b = this.data_type;
            if (b == 0) {
                this.value.setType(byte[].class);
            } else if (b == 1 || b == 2) {
                this.value.setType(Integer.TYPE);
            } else if (b == 3 || b == 4) {
                this.value.setType(Long.TYPE);
            } else if (b == 9) {
                this.value.setType(UNION.STR.class);
            }
            this.value.read();
        }

        public KstatNamed() {
            this.name = new byte[31];
        }
    }
}
