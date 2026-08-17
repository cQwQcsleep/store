package com.sun.jna.platform.unix.aix;

import com.intellij.psi.PsiKeyword;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Perfstat extends Library {
    public static final int IDENTIFIER_LENGTH = 64;
    public static final Perfstat INSTANCE = SharedObjectLoader.getPerfstatInstance();

    @Structure.FieldOrder({"name", "user", "sys", "idle", "wait", "pswitch", "syscall", "sysread", "syswrite", "sysfork", "sysexec", "readch", "writech", "bread", "bwrite", "lread", "lwrite", "phread", "phwrite", "iget", "namei", "dirblk", "msg", "sema", "minfaults", "majfaults", "puser", "psys", "pidle", "pwait", "redisp_sd0", "redisp_sd1", "redisp_sd2", "redisp_sd3", "redisp_sd4", "redisp_sd5", "migration_push", "migration_S3grq", "migration_S3pul", "invol_cswitch", "vol_cswitch", "runque", "bound", "decrintrs", "mpcrintrs", "mpcsintrs", "devintrs", "softintrs", "phantintrs", "idle_donated_purr", "idle_donated_spurr", "busy_donated_purr", "busy_donated_spurr", "idle_stolen_purr", "idle_stolen_spurr", "busy_stolen_purr", "busy_stolen_spurr", "hpi", "hpit", "puser_spurr", "psys_spurr", "pidle_spurr", "pwait_spurr", "spurrflag", "localdispatch", "neardispatch", "fardispatch", "cswitches", "version", "tb_last"})
    public static class perfstat_cpu_t extends Structure {
        public long bound;
        public long bread;
        public long busy_donated_purr;
        public long busy_donated_spurr;
        public long busy_stolen_purr;
        public long busy_stolen_spurr;
        public long bwrite;
        public long cswitches;
        public long decrintrs;
        public long devintrs;
        public long dirblk;
        public long fardispatch;
        public long hpi;
        public long hpit;
        public long idle;
        public long idle_donated_purr;
        public long idle_donated_spurr;
        public long idle_stolen_purr;
        public long idle_stolen_spurr;
        public long iget;
        public long invol_cswitch;
        public long localdispatch;
        public long lread;
        public long lwrite;
        public long majfaults;
        public long migration_S3grq;
        public long migration_S3pul;
        public long migration_push;
        public long minfaults;
        public long mpcrintrs;
        public long mpcsintrs;
        public long msg;
        public byte[] name = new byte[64];
        public long namei;
        public long neardispatch;
        public long phantintrs;
        public long phread;
        public long phwrite;
        public long pidle;
        public long pidle_spurr;
        public long pswitch;
        public long psys;
        public long psys_spurr;
        public long puser;
        public long puser_spurr;
        public long pwait;
        public long pwait_spurr;
        public long readch;
        public long redisp_sd0;
        public long redisp_sd1;
        public long redisp_sd2;
        public long redisp_sd3;
        public long redisp_sd4;
        public long redisp_sd5;
        public long runque;
        public long sema;
        public long softintrs;
        public int spurrflag;
        public long sys;
        public long syscall;
        public long sysexec;
        public long sysfork;
        public long sysread;
        public long syswrite;
        public long tb_last;
        public long user;
        public long version;
        public long vol_cswitch;
        public long wait;
        public long writech;
    }

    @Structure.FieldOrder({"ncpus", "ncpus_cfg", "description", "processorHZ", "user", "sys", "idle", "wait", "pswitch", "syscall", "sysread", "syswrite", "sysfork", "sysexec", "readch", "writech", "devintrs", "softintrs", "lbolt", "loadavg", "runque", "swpque", "bread", "bwrite", "lread", "lwrite", "phread", "phwrite", "runocc", "swpocc", "iget", "namei", "dirblk", "msg", "sema", "rcvint", "xmtint", "mdmint", "tty_rawinch", "tty_caninch", "tty_rawoutch", "ksched", "koverf", "kexit", "rbread", "rcread", "rbwrt", "rcwrt", "traps", "ncpus_high", "puser", "psys", "pidle", "pwait", "decrintrs", "mpcrintrs", "mpcsintrs", "phantintrs", "idle_donated_purr", "idle_donated_spurr", "busy_donated_purr", "busy_donated_spurr", "idle_stolen_purr", "idle_stolen_spurr", "busy_stolen_purr", "busy_stolen_spurr", "iowait", "physio", "twait", "hpi", "hpit", "puser_spurr", "psys_spurr", "pidle_spurr", "pwait_spurr", "spurrflag", "version", "tb_last", "purr_coalescing", "spurr_coalescing"})
    public static class perfstat_cpu_total_t extends Structure {
        public long bread;
        public long busy_donated_purr;
        public long busy_donated_spurr;
        public long busy_stolen_purr;
        public long busy_stolen_spurr;
        public long bwrite;
        public long decrintrs;
        public long devintrs;
        public long dirblk;
        public long hpi;
        public long hpit;
        public long idle;
        public long idle_donated_purr;
        public long idle_donated_spurr;
        public long idle_stolen_purr;
        public long idle_stolen_spurr;
        public long iget;
        public short iowait;
        public long kexit;
        public long koverf;
        public long ksched;
        public NativeLong lbolt;
        public long lread;
        public long lwrite;
        public long mdmint;
        public long mpcrintrs;
        public long mpcsintrs;
        public long msg;
        public long namei;
        public int ncpus;
        public int ncpus_cfg;
        public int ncpus_high;
        public long phantintrs;
        public long phread;
        public long phwrite;
        public short physio;
        public long pidle;
        public long pidle_spurr;
        public long processorHZ;
        public long pswitch;
        public long psys;
        public long psys_spurr;
        public long purr_coalescing;
        public long puser;
        public long puser_spurr;
        public long pwait;
        public long pwait_spurr;
        public long rbread;
        public long rbwrt;
        public long rcread;
        public long rcvint;
        public long rcwrt;
        public long readch;
        public long runocc;
        public long runque;
        public long sema;
        public long softintrs;
        public long spurr_coalescing;
        public int spurrflag;
        public long swpocc;
        public long swpque;
        public long sys;
        public long syscall;
        public long sysexec;
        public long sysfork;
        public long sysread;
        public long syswrite;
        public long tb_last;
        public long traps;
        public long tty_caninch;
        public long tty_rawinch;
        public long tty_rawoutch;
        public long twait;
        public long user;
        public long version;
        public long wait;
        public long writech;
        public long xmtint;
        public byte[] description = new byte[64];
        public long[] loadavg = new long[3];
    }

    @Structure.FieldOrder({"name", "description", "vgname", "size", "free", "bsize", "xrate", "xfers", "wblks", "rblks", "qdepth", SchemaSymbols.ATTVAL_TIME, "adapter", "paths_count", "q_full", "rserv", "rtimeout", "rfailed", "min_rserv", "max_rserv", "wserv", "wtimeout", "wfailed", "min_wserv", "max_wserv", "wq_depth", "wq_sampled", "wq_time", "wq_min_time", "wq_max_time", "q_sampled", "wpar_id", "version", "dk_type"})
    public static class perfstat_disk_t extends Structure {
        public long bsize;
        public int dk_type;
        public long free;
        public long max_rserv;
        public long max_wserv;
        public long min_rserv;
        public long min_wserv;
        public int paths_count;
        public long q_full;
        public long q_sampled;
        public long qdepth;
        public long rblks;
        public long rfailed;
        public long rserv;
        public long rtimeout;
        public long size;
        public long time;
        public long version;
        public long wblks;
        public long wfailed;
        public short wpar_id;
        public long wq_depth;
        public long wq_max_time;
        public long wq_min_time;
        public long wq_sampled;
        public long wq_time;
        public long wserv;
        public long wtimeout;
        public long xfers;
        public long xrate;
        public byte[] name = new byte[64];
        public byte[] description = new byte[64];
        public byte[] vgname = new byte[64];
        public byte[] adapter = new byte[64];
    }

    @Structure.FieldOrder({"name"})
    public static class perfstat_id_t extends Structure {
        public byte[] name = new byte[64];
    }

    @Structure.FieldOrder({"virt_total", "real_total", "real_free", "real_pinned", "real_inuse", "pgbad", "pgexct", "pgins", "pgouts", "pgspins", "pgspouts", "scans", "cycles", "pgsteals", "numperm", "pgsp_total", "pgsp_free", "pgsp_rsvd", "real_system", "real_user", "real_process", "virt_active", "iome", "iomu", "iohwm", "pmem", "comprsd_total", "comprsd_wseg_pgs", "cpgins", "cpgouts", "true_size", "expanded_memory", "comprsd_wseg_size", "target_cpool_size", "max_cpool_size", "min_ucpool_size", "cpool_size", "ucpool_size", "cpool_inuse", "ucpool_inuse", "version", "real_avail", "bytes_coalesced", "bytes_coalesced_mempool"})
    public static class perfstat_memory_total_t extends Structure {
        public long bytes_coalesced;
        public long bytes_coalesced_mempool;
        public long comprsd_total;
        public long comprsd_wseg_pgs;
        public long comprsd_wseg_size;
        public long cpgins;
        public long cpgouts;
        public long cpool_inuse;
        public long cpool_size;
        public long cycles;
        public long expanded_memory;
        public long iohwm;
        public long iome;
        public long iomu;
        public long max_cpool_size;
        public long min_ucpool_size;
        public long numperm;
        public long pgbad;
        public long pgexct;
        public long pgins;
        public long pgouts;
        public long pgsp_free;
        public long pgsp_rsvd;
        public long pgsp_total;
        public long pgspins;
        public long pgspouts;
        public long pgsteals;
        public long pmem;
        public long real_avail;
        public long real_free;
        public long real_inuse;
        public long real_pinned;
        public long real_process;
        public long real_system;
        public long real_total;
        public long real_user;
        public long scans;
        public long target_cpool_size;
        public long true_size;
        public long ucpool_inuse;
        public long ucpool_size;
        public long version;
        public long virt_active;
        public long virt_total;
    }

    @Structure.FieldOrder({"name", "description", "type", "mtu", "ipackets", "ibytes", "ierrors", "opackets", "obytes", "oerrors", "collisions", "bitrate", "xmitdrops", "version", "if_iqdrops", "if_arpdrops"})
    public static class perfstat_netinterface_t extends Structure {
        public long bitrate;
        public long collisions;
        public long ibytes;
        public long ierrors;
        public long if_arpdrops;
        public long if_iqdrops;
        public long ipackets;
        public long mtu;
        public long obytes;
        public long oerrors;
        public long opackets;
        public byte type;
        public long version;
        public long xmitdrops;
        public byte[] name = new byte[64];
        public byte[] description = new byte[64];
    }

    @Structure.FieldOrder({"version", "partitionname", "nodename", "conf", "partitionnum", "groupid", "processorFamily", "processorModel", "machineID", "processorMHz", "numProcessors", "OSName", "OSVersion", "OSBuild", "lcpus", "smtthreads", "drives", "nw_adapters", "cpucap", "cpucap_weightage", "entitled_proc_capacity", "vcpus", "processor_poolid", "activecpusinpool", "cpupool_weightage", "sharedpcpu", "maxpoolcap", "entpoolcap", "mem", "mem_weightage", "totiomement", "mempoolid", "hyperpgsize", "exp_mem", "targetmemexpfactor", "targetmemexpsize"})
    public static class perfstat_partition_config_t extends Structure {
        public int activecpusinpool;
        public int conf;
        public perfstat_value_t cpucap;
        public int cpucap_weightage;
        public int cpupool_weightage;
        public int drives;
        public int entitled_proc_capacity;
        public int entpoolcap;
        public perfstat_value_t exp_mem;
        public int groupid;
        public long hyperpgsize;
        public int lcpus;
        public int maxpoolcap;
        public perfstat_value_t mem;
        public int mem_weightage;
        public int mempoolid;
        public perfstat_value_t numProcessors;
        public int nw_adapters;
        public int partitionnum;
        public double processorMHz;
        public int processor_poolid;
        public int sharedpcpu;
        public int smtthreads;
        public long targetmemexpfactor;
        public long targetmemexpsize;
        public long totiomement;
        public perfstat_value_t vcpus;
        public long version;
        public byte[] partitionname = new byte[64];
        public byte[] nodename = new byte[64];
        public byte[] processorFamily = new byte[64];
        public byte[] processorModel = new byte[64];
        public byte[] machineID = new byte[64];
        public byte[] OSName = new byte[64];
        public byte[] OSVersion = new byte[64];
        public byte[] OSBuild = new byte[64];
    }

    @Structure.FieldOrder({"version", "pid", "proc_name", "proc_priority", "num_threads", "proc_uid", "proc_classid", "proc_size", "proc_real_mem_data", "proc_real_mem_text", "proc_virt_mem_data", "proc_virt_mem_text", "shared_lib_data_size", "heap_size", "real_inuse", "virt_inuse", "pinned", "pgsp_inuse", "filepages", "real_inuse_map", "virt_inuse_map", "pinned_inuse_map", "ucpu_time", "scpu_time", "last_timebase", "inBytes", "outBytes", "inOps", "outOps"})
    public static class perfstat_process_t extends Structure {
        public long filepages;
        public long heap_size;
        public long inBytes;
        public long inOps;
        public long last_timebase;
        public long num_threads;
        public long outBytes;
        public long outOps;
        public long pgsp_inuse;
        public long pid;
        public long pinned;
        public long pinned_inuse_map;
        public long proc_classid;
        public byte[] proc_name = new byte[64];
        public int proc_priority;
        public long proc_real_mem_data;
        public long proc_real_mem_text;
        public long proc_size;
        public long proc_uid;
        public long proc_virt_mem_data;
        public long proc_virt_mem_text;
        public long real_inuse;
        public long real_inuse_map;
        public double scpu_time;
        public long shared_lib_data_size;
        public double ucpu_time;
        public long version;
        public long virt_inuse;
        public long virt_inuse_map;
    }

    @Structure.FieldOrder({"name", "u", "version"})
    public static class perfstat_protocol_t extends Structure {
        public byte[] name = new byte[64];
        public AnonymousUnionPayload u;
        public long version;

        @Structure.FieldOrder({"received", "sent", "errors"})
        public static class AnonymousStructICMP extends Structure {
            public long errors;
            public long received;
            public long sent;
        }

        @Structure.FieldOrder({"received", "sent", "errors"})
        public static class AnonymousStructICMPv6 extends Structure {
            public long errors;
            public long received;
            public long sent;
        }

        @Structure.FieldOrder({"ipackets", "ierrors", "iqueueoverflow", "opackets", "oerrors"})
        public static class AnonymousStructIP extends Structure {
            public long ierrors;
            public long ipackets;
            public long iqueueoverflow;
            public long oerrors;
            public long opackets;
        }

        @Structure.FieldOrder({"ipackets", "ierrors", "iqueueoverflow", "opackets", "oerrors"})
        public static class AnonymousStructIPv6 extends Structure {
            public long ierrors;
            public long ipackets;
            public long iqueueoverflow;
            public long oerrors;
            public long opackets;
        }

        @Structure.FieldOrder({"client", "server"})
        public static class AnonymousStructNFS extends Structure {
            public AnonymousStructNFSclient client;
            public AnonymousStructNFSserver server;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "badcalls", "clgets", "cltoomany"})
        public static class AnonymousStructNFSclient extends Structure {
            public long badcalls;
            public long calls;
            public long clgets;
            public long cltoomany;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "badcalls", "public_v2", "public_v3"})
        public static class AnonymousStructNFSserver extends Structure {
            public long badcalls;
            public long calls;
            public long public_v2;
            public long public_v3;
        }

        @Structure.FieldOrder({"client", "server"})
        public static class AnonymousStructNFSv2 extends Structure {
            public AnonymousStructNFSv2client client;
            public AnonymousStructNFSv2server server;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "nullreq", "getattr", "setattr", Constants.ELEMNAME_ROOT_STRING, "lookup", "readlink", "read", "writecache", "write", "create", "remove", "rename", "link", "symlink", "mkdir", "rmdir", "readdir", "statfs"})
        public static class AnonymousStructNFSv2client extends Structure {
            public long calls;
            public long create;
            public long getattr;
            public long link;
            public long lookup;
            public long mkdir;
            public long nullreq;
            public long read;
            public long readdir;
            public long readlink;
            public long remove;
            public long rename;
            public long rmdir;
            public long root;
            public long setattr;
            public long statfs;
            public long symlink;
            public long write;
            public long writecache;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "nullreq", "getattr", "setattr", Constants.ELEMNAME_ROOT_STRING, "lookup", "readlink", "read", "writecache", "write", "create", "remove", "rename", "link", "symlink", "mkdir", "rmdir", "readdir", "statfs"})
        public static class AnonymousStructNFSv2server extends Structure {
            public long calls;
            public long create;
            public long getattr;
            public long link;
            public long lookup;
            public long mkdir;
            public long nullreq;
            public long read;
            public long readdir;
            public long readlink;
            public long remove;
            public long rename;
            public long rmdir;
            public long root;
            public long setattr;
            public long statfs;
            public long symlink;
            public long write;
            public long writecache;
        }

        @Structure.FieldOrder({"client", "server"})
        public static class AnonymousStructNFSv3 extends Structure {
            public AnonymousStructNFSv3client client;
            public AnonymousStructNFSv3server server;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "nullreq", "getattr", "setattr", "lookup", "access", "readlink", "read", "write", "create", "mkdir", "symlink", "mknod", "remove", "rmdir", "rename", "link", "readdir", "readdirplus", "fsstat", "fsinfo", "pathconf", "commit"})
        public static class AnonymousStructNFSv3client extends Structure {
            public long access;
            public long calls;
            public long commit;
            public long create;
            public long fsinfo;
            public long fsstat;
            public long getattr;
            public long link;
            public long lookup;
            public long mkdir;
            public long mknod;
            public long nullreq;
            public long pathconf;
            public long read;
            public long readdir;
            public long readdirplus;
            public long readlink;
            public long remove;
            public long rename;
            public long rmdir;
            public long setattr;
            public long symlink;
            public long write;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "nullreq", "getattr", "setattr", "lookup", "access", "readlink", "read", "write", "create", "mkdir", "symlink", "mknod", "remove", "rmdir", "rename", "link", "readdir", "readdirplus", "fsstat", "fsinfo", "pathconf", "commit"})
        public static class AnonymousStructNFSv3server extends Structure {
            public long access;
            public long calls;
            public long commit;
            public long create;
            public long fsinfo;
            public long fsstat;
            public long getattr;
            public long link;
            public long lookup;
            public long mkdir;
            public long mknod;
            public long nullreq;
            public long pathconf;
            public long read;
            public long readdir;
            public long readdirplus;
            public long readlink;
            public long remove;
            public long rename;
            public long rmdir;
            public long setattr;
            public long symlink;
            public long write;
        }

        @Structure.FieldOrder({"client", "server"})
        public static class AnonymousStructNFSv4 extends Structure {
            public AnonymousStructNFSv4client client;
            public AnonymousStructNFSv4server server;
        }

        @Structure.FieldOrder({"operations", "nullreq", "getattr", "setattr", "lookup", "access", "readlink", "read", "write", "create", "mkdir", "symlink", "mknod", "remove", "rmdir", "rename", "link", "readdir", "statfs", "finfo", "commit", PsiKeyword.OPEN, "open_confirm", "open_downgrade", "close", "lock", "unlock", "lock_test", "set_clientid", "renew", "client_confirm", "secinfo", "release_lock", "replicate", "pcl_stat", "acl_stat_l", "pcl_stat_l", "acl_read", "pcl_read", "acl_write", "pcl_write", "delegreturn"})
        public static class AnonymousStructNFSv4client extends Structure {
            public long access;
            public long acl_read;
            public long acl_stat_l;
            public long acl_write;
            public long client_confirm;
            public long close;
            public long commit;
            public long create;
            public long delegreturn;
            public long finfo;
            public long getattr;
            public long link;
            public long lock;
            public long lock_test;
            public long lookup;
            public long mkdir;
            public long mknod;
            public long nullreq;
            public long open;
            public long open_confirm;
            public long open_downgrade;
            public long operations;
            public long pcl_read;
            public long pcl_stat;
            public long pcl_stat_l;
            public long pcl_write;
            public long read;
            public long readdir;
            public long readlink;
            public long release_lock;
            public long remove;
            public long rename;
            public long renew;
            public long replicate;
            public long rmdir;
            public long secinfo;
            public long set_clientid;
            public long setattr;
            public long statfs;
            public long symlink;
            public long unlock;
            public long write;
        }

        @Structure.FieldOrder({"nullreq", "compound", "operations", "access", "close", "commit", "create", "delegpurge", "delegreturn", "getattr", "getfh", "link", "lock", "lockt", "locku", "lookup", "lookupp", "nverify", PsiKeyword.OPEN, "openattr", "open_confirm", "open_downgrade", "putfh", "putpubfh", "putrootfh", "read", "readdir", "readlink", "remove", "rename", "renew", "restorefh", "savefh", "secinfo", "setattr", "set_clientid", "clientid_confirm", "verify", "write", "release_lock"})
        public static class AnonymousStructNFSv4server extends Structure {
            public long access;
            public long clientid_confirm;
            public long close;
            public long commit;
            public long compound;
            public long create;
            public long delegpurge;
            public long delegreturn;
            public long getattr;
            public long getfh;
            public long link;
            public long lock;
            public long lockt;
            public long locku;
            public long lookup;
            public long lookupp;
            public long nullreq;
            public long nverify;
            public long open;
            public long open_confirm;
            public long open_downgrade;
            public long openattr;
            public long operations;
            public long putfh;
            public long putpubfh;
            public long putrootfh;
            public long read;
            public long readdir;
            public long readlink;
            public long release_lock;
            public long remove;
            public long rename;
            public long renew;
            public long restorefh;
            public long savefh;
            public long secinfo;
            public long set_clientid;
            public long setattr;
            public long verify;
            public long write;
        }

        @Structure.FieldOrder({"client", "server"})
        public static class AnonymousStructRPC extends Structure {
            public AnonymousStructRPCclient client;
            public AnonymousStructRPCserver server;
        }

        @Structure.FieldOrder({"stream", "dgram"})
        public static class AnonymousStructRPCclient extends Structure {
            public AnonymousStructRPCclientdgram dgram;
            public AnonymousStructRPCclientstream stream;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "badcalls", "retrans", "badxids", "timeouts", "newcreds", "badverfs", "timers", "nomem", "cantsend"})
        public static class AnonymousStructRPCclientdgram extends Structure {
            public long badcalls;
            public long badverfs;
            public long badxids;
            public long calls;
            public long cantsend;
            public long newcreds;
            public long nomem;
            public long retrans;
            public long timeouts;
            public long timers;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "badcalls", "badxids", "timeouts", "newcreds", "badverfs", "timers", "nomem", "cantconn", "interrupts"})
        public static class AnonymousStructRPCclientstream extends Structure {
            public long badcalls;
            public long badverfs;
            public long badxids;
            public long calls;
            public long cantconn;
            public long interrupts;
            public long newcreds;
            public long nomem;
            public long timeouts;
            public long timers;
        }

        @Structure.FieldOrder({"stream", "dgram"})
        public static class AnonymousStructRPCserver extends Structure {
            public AnonymousStructRPCserverdgram dgram;
            public AnonymousStructRPCserverstream stream;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "badcalls", "nullrecv", "badlen", "xdrcall", "dupchecks", "dupreqs"})
        public static class AnonymousStructRPCserverdgram extends Structure {
            public long badcalls;
            public long badlen;
            public long calls;
            public long dupchecks;
            public long dupreqs;
            public long nullrecv;
            public long xdrcall;
        }

        @Structure.FieldOrder({Constants.ATTRNAME_METHOD, "badcalls", "nullrecv", "badlen", "xdrcall", "dupchecks", "dupreqs"})
        public static class AnonymousStructRPCserverstream extends Structure {
            public long badcalls;
            public long badlen;
            public long calls;
            public long dupchecks;
            public long dupreqs;
            public long nullrecv;
            public long xdrcall;
        }

        @Structure.FieldOrder({"ipackets", "ierrors", "opackets", "initiated", "accepted", "established", "dropped"})
        public static class AnonymousStructTCP extends Structure {
            public long accepted;
            public long dropped;
            public long established;
            public long ierrors;
            public long initiated;
            public long ipackets;
            public long opackets;
        }

        @Structure.FieldOrder({"ipackets", "ierrors", "opackets", "no_socket"})
        public static class AnonymousStructUDP extends Structure {
            public long ierrors;
            public long ipackets;
            public long no_socket;
            public long opackets;
        }

        public static class AnonymousUnionPayload extends Union {
            public AnonymousStructICMP icmp;
            public AnonymousStructICMPv6 icmpv6;
            public AnonymousStructIP ip;
            public AnonymousStructIPv6 ipv6;
            public AnonymousStructNFS nfs;
            public AnonymousStructNFSv2 nfsv2;
            public AnonymousStructNFSv3 nfsv3;
            public AnonymousStructNFSv4 nfsv4;
            public AnonymousStructRPC rpc;
            public AnonymousStructTCP tcp;
            public AnonymousStructUDP udp;
        }

        @Override // com.sun.jna.Structure
        public void read() {
            super.read();
            String string = Native.toString(this.name);
            if (!string.isEmpty()) {
                this.u.setType(string);
            }
            this.u.read();
        }
    }

    @Structure.FieldOrder({"online", "max", "min", "desired"})
    public static class perfstat_value_t extends Structure {
        public long desired;
        public long max;
        public long min;
        public long online;
    }

    int perfstat_cpu(perfstat_id_t perfstat_id_tVar, perfstat_cpu_t[] perfstat_cpu_tVarArr, int i, int i2);

    int perfstat_cpu_total(perfstat_id_t perfstat_id_tVar, perfstat_cpu_total_t perfstat_cpu_total_tVar, int i, int i2);

    int perfstat_disk(perfstat_id_t perfstat_id_tVar, perfstat_disk_t[] perfstat_disk_tVarArr, int i, int i2);

    int perfstat_memory_total(perfstat_id_t perfstat_id_tVar, perfstat_memory_total_t perfstat_memory_total_tVar, int i, int i2);

    int perfstat_netinterface(perfstat_id_t perfstat_id_tVar, perfstat_netinterface_t[] perfstat_netinterface_tVarArr, int i, int i2);

    int perfstat_partition_config(perfstat_id_t perfstat_id_tVar, perfstat_partition_config_t perfstat_partition_config_tVar, int i, int i2);

    int perfstat_process(perfstat_id_t perfstat_id_tVar, perfstat_process_t[] perfstat_process_tVarArr, int i, int i2);

    int perfstat_protocol(perfstat_id_t perfstat_id_tVar, perfstat_protocol_t[] perfstat_protocol_tVarArr, int i, int i2);
}
