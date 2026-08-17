package com.sun.jna.platform.linux;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.platform.unix.LibCAPI;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LibC extends Library, LibCAPI {
    public static final LibC INSTANCE = (LibC) Native.load("c", LibC.class);
    public static final String NAME = "c";

    @Structure.FieldOrder({"f_bsize", "f_frsize", "f_blocks", "f_bfree", "f_bavail", "f_files", "f_ffree", "f_favail", "f_fsid", "_f_unused", "f_flag", "f_namemax", "_f_spare"})
    public static class Statvfs extends Structure {
        public int[] _f_spare = new int[6];
        public int _f_unused;
        public NativeLong f_bavail;
        public NativeLong f_bfree;
        public NativeLong f_blocks;
        public NativeLong f_bsize;
        public NativeLong f_favail;
        public NativeLong f_ffree;
        public NativeLong f_files;
        public NativeLong f_flag;
        public NativeLong f_frsize;
        public NativeLong f_fsid;
        public NativeLong f_namemax;

        @Override // com.sun.jna.Structure
        public List<Field> getFieldList() {
            ArrayList arrayList = new ArrayList(super.getFieldList());
            if (NativeLong.SIZE > 4) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if ("_f_unused".equals(((Field) it.next()).getName())) {
                        it.remove();
                    }
                }
            }
            return arrayList;
        }

        @Override // com.sun.jna.Structure
        public List<String> getFieldOrder() {
            ArrayList arrayList = new ArrayList(super.getFieldOrder());
            if (NativeLong.SIZE > 4) {
                arrayList.remove("_f_unused");
            }
            return arrayList;
        }
    }

    @Structure.FieldOrder({"uptime", "loads", "totalram", "freeram", "sharedram", "bufferram", "totalswap", "freeswap", "procs", "totalhigh", "freehigh", "mem_unit", "_f"})
    public static class Sysinfo extends Structure {
        private static final int PADDING_SIZE = 16 - (NativeLong.SIZE * 2);
        public NativeLong bufferram;
        public NativeLong freehigh;
        public NativeLong freeram;
        public NativeLong freeswap;
        public int mem_unit;
        public short procs;
        public NativeLong sharedram;
        public NativeLong totalhigh;
        public NativeLong totalram;
        public NativeLong totalswap;
        public NativeLong uptime;
        public NativeLong[] loads = new NativeLong[3];
        public byte[] _f = new byte[PADDING_SIZE];

        @Override // com.sun.jna.Structure
        public List<Field> getFieldList() {
            ArrayList arrayList = new ArrayList(super.getFieldList());
            if (PADDING_SIZE == 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if ("_f".equals(((Field) it.next()).getName())) {
                        it.remove();
                    }
                }
            }
            return arrayList;
        }

        @Override // com.sun.jna.Structure
        public List<String> getFieldOrder() {
            ArrayList arrayList = new ArrayList(super.getFieldOrder());
            if (PADDING_SIZE == 0) {
                arrayList.remove("_f");
            }
            return arrayList;
        }
    }

    int statvfs(String str, Statvfs statvfs);

    int sysinfo(Sysinfo sysinfo);
}
