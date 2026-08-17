package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Wdm {

    public static abstract class KEY_INFORMATION_CLASS {
        public static final int KeyBasicInformation = 0;
        public static final int KeyCachedInformation = 4;
        public static final int KeyFullInformation = 2;
        public static final int KeyNameInformation = 3;
        public static final int KeyNodeInformation = 1;
        public static final int KeyVirtualizationInformation = 5;
    }

    @Structure.FieldOrder({"LastWriteTime", "TitleIndex", "NameLength", SchemaSymbols.ATTVAL_NAME})
    public static class KEY_BASIC_INFORMATION extends Structure {
        public long LastWriteTime;
        public char[] Name;
        public int NameLength;
        public int TitleIndex;

        public KEY_BASIC_INFORMATION(int i) {
            int i2 = i - 16;
            this.NameLength = i2;
            this.Name = new char[i2];
            allocateMemory();
        }

        public String getName() {
            return Native.toString(this.Name);
        }

        @Override // com.sun.jna.Structure
        public void read() {
            super.read();
            this.Name = new char[this.NameLength / 2];
            readField(SchemaSymbols.ATTVAL_NAME);
        }

        public KEY_BASIC_INFORMATION() {
        }

        public KEY_BASIC_INFORMATION(Pointer pointer) {
            super(pointer);
            read();
        }
    }
}
