package com.sun.jna.platform.win32;

import com.sun.jna.IntegerType;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import com.sun.jna.ptr.ByReference;
import java.awt.Rectangle;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface WinDef {
    public static final int MAX_PATH = 260;

    public static class HINSTANCE extends WinNT.HANDLE {
    }

    public static class HMODULE extends HINSTANCE {
    }

    @Structure.FieldOrder({"left", "top", "right", "bottom"})
    public static class RECT extends Structure {
        public int bottom;
        public int left;
        public int right;
        public int top;

        public Rectangle toRectangle() {
            int i = this.left;
            int i2 = this.top;
            return new Rectangle(i, i2, this.right - i, this.bottom - i2);
        }

        @Override // com.sun.jna.Structure
        public String toString() {
            return "[(" + this.left + "," + this.top + ")(" + this.right + "," + this.bottom + ")]";
        }
    }

    @Structure.FieldOrder({"x", "y"})
    public static class POINT extends Structure {
        public int x;
        public int y;

        public POINT(int i, int i2) {
            this.x = i;
            this.y = i2;
        }

        public static class ByReference extends POINT implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }

            public ByReference(int i, int i2) {
                super(i, i2);
            }
        }

        public static class ByValue extends POINT implements Structure.ByValue {
            public ByValue() {
            }

            public ByValue(Pointer pointer) {
                super(pointer);
            }

            public ByValue(int i, int i2) {
                super(i, i2);
            }
        }

        public POINT(Pointer pointer) {
            super(pointer);
            read();
        }

        public POINT() {
        }
    }

    public static class HBITMAP extends WinNT.HANDLE {
        public HBITMAP() {
        }

        public HBITMAP(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HBRUSH extends WinNT.HANDLE {
        public HBRUSH() {
        }

        public HBRUSH(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HCURSOR extends HICON {
        public HCURSOR() {
        }

        public HCURSOR(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HDC extends WinNT.HANDLE {
        public HDC() {
        }

        public HDC(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HFONT extends WinNT.HANDLE {
        public HFONT() {
        }

        public HFONT(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HGLRC extends WinNT.HANDLE {
        public HGLRC() {
        }

        public HGLRC(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HGLRCByReference extends WinNT.HANDLEByReference {
        public HGLRCByReference() {
        }

        public HGLRCByReference(HGLRC hglrc) {
            super(hglrc);
        }
    }

    public static class HMENU extends WinNT.HANDLE {
        public HMENU() {
        }

        public HMENU(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HPALETTE extends WinNT.HANDLE {
        public HPALETTE() {
        }

        public HPALETTE(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HPEN extends WinNT.HANDLE {
        public HPEN() {
        }

        public HPEN(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HRGN extends WinNT.HANDLE {
        public HRGN() {
        }

        public HRGN(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HRSRC extends WinNT.HANDLE {
        public HRSRC() {
        }

        public HRSRC(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HWND extends WinNT.HANDLE {
        public HWND() {
        }

        public HWND(Pointer pointer) {
            super(pointer);
        }
    }

    public static class LPVOID extends PointerType {
        public LPVOID() {
        }

        public LPVOID(Pointer pointer) {
            super(pointer);
        }
    }

    public static class PVOID extends PointerType {
        public PVOID() {
        }

        public PVOID(Pointer pointer) {
            super(pointer);
        }
    }

    public static class ATOM extends WORD {
        public ATOM() {
            this(0L);
        }

        public ATOM(long j) {
            super(j);
        }
    }

    public static class BYTE extends UCHAR {
        public BYTE() {
            this(0L);
        }

        public BYTE(long j) {
            super(j);
        }
    }

    public static class DWORD extends IntegerType implements Comparable<DWORD> {
        public static final int SIZE = 4;

        public DWORD() {
            this(0L);
        }

        public WORD getHigh() {
            return new WORD((longValue() >> 16) & 65535);
        }

        public WORD getLow() {
            return new WORD(longValue() & 65535);
        }

        public DWORD(long j) {
            super(4, j, true);
        }

        @Override // java.lang.Comparable
        public int compareTo(DWORD dword) {
            return IntegerType.compare(this, dword);
        }
    }

    public static class INT_PTR extends IntegerType {
        public INT_PTR() {
            super(Native.POINTER_SIZE);
        }

        public Pointer toPointer() {
            return Pointer.createConstant(longValue());
        }

        public INT_PTR(long j) {
            super(Native.POINTER_SIZE, j);
        }
    }

    public static class LCID extends DWORD {
        public LCID() {
            super(0L);
        }

        public LCID(long j) {
            super(j);
        }
    }

    public static class LONG extends IntegerType implements Comparable<LONG> {
        public static final int SIZE = Native.LONG_SIZE;

        public LONG() {
            this(0L);
        }

        public LONG(long j) {
            super(SIZE, j);
        }

        @Override // java.lang.Comparable
        public int compareTo(LONG r1) {
            return IntegerType.compare(this, r1);
        }
    }

    public static class LPARAM extends BaseTSD.LONG_PTR {
        public LPARAM() {
            this(0L);
        }

        public LPARAM(long j) {
            super(j);
        }
    }

    public static class LRESULT extends BaseTSD.LONG_PTR {
        public LRESULT() {
            this(0L);
        }

        public LRESULT(long j) {
            super(j);
        }
    }

    public static class SCODE extends ULONG {
        public SCODE() {
            this(0L);
        }

        public SCODE(long j) {
            super(j);
        }
    }

    public static class SHORT extends IntegerType implements Comparable<SHORT> {
        public static final int SIZE = 2;

        public SHORT() {
            this(0L);
        }

        public SHORT(long j) {
            super(2, j, false);
        }

        @Override // java.lang.Comparable
        public int compareTo(SHORT r1) {
            return IntegerType.compare(this, r1);
        }
    }

    public static class UINT extends IntegerType implements Comparable<UINT> {
        public static final int SIZE = 4;

        public UINT() {
            this(0L);
        }

        public UINT(long j) {
            super(4, j, true);
        }

        @Override // java.lang.Comparable
        public int compareTo(UINT uint) {
            return IntegerType.compare(this, uint);
        }
    }

    public static class USHORT extends IntegerType implements Comparable<USHORT> {
        public static final int SIZE = 2;

        public USHORT() {
            this(0L);
        }

        public USHORT(long j) {
            super(2, j, true);
        }

        @Override // java.lang.Comparable
        public int compareTo(USHORT ushort) {
            return IntegerType.compare(this, ushort);
        }
    }

    public static class WORD extends IntegerType implements Comparable<WORD> {
        public static final int SIZE = 2;

        public WORD() {
            this(0L);
        }

        public WORD(long j) {
            super(2, j, true);
        }

        @Override // java.lang.Comparable
        public int compareTo(WORD word) {
            return IntegerType.compare(this, word);
        }
    }

    public static class WPARAM extends UINT_PTR {
        public WPARAM() {
            this(0L);
        }

        public WPARAM(long j) {
            super(j);
        }
    }

    public static class BOOL extends IntegerType implements Comparable<BOOL> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final int SIZE = 4;

        public BOOL(boolean z) {
            this(z ? 1L : 0L);
        }

        public static int compare(BOOL bool, BOOL bool2) {
            if (bool == bool2) {
                return 0;
            }
            if (bool == null) {
                return 1;
            }
            if (bool2 == null) {
                return -1;
            }
            return compare(bool.booleanValue(), bool2.booleanValue());
        }

        public boolean booleanValue() {
            return intValue() > 0;
        }

        @Override // com.sun.jna.IntegerType
        public String toString() {
            return Boolean.toString(booleanValue());
        }

        @Override // java.lang.Comparable
        public int compareTo(BOOL bool) {
            return compare(this, bool);
        }

        public BOOL() {
            this(0L);
        }

        public BOOL(long j) {
            super(4, j, false);
        }

        public static int compare(boolean z, boolean z2) {
            if (z == z2) {
                return 0;
            }
            return z ? 1 : -1;
        }

        public static int compare(BOOL bool, boolean z) {
            if (bool == null) {
                return 1;
            }
            return compare(bool.booleanValue(), z);
        }
    }

    public static class CHAR extends IntegerType implements Comparable<CHAR> {
        public static final int SIZE = 1;

        public CHAR(byte b) {
            this(b & 255);
        }

        public CHAR() {
            this(0L);
        }

        @Override // java.lang.Comparable
        public int compareTo(CHAR r1) {
            return IntegerType.compare(this, r1);
        }

        public CHAR(long j) {
            super(1, j, false);
        }
    }

    public static class DWORDLONG extends IntegerType implements Comparable<DWORDLONG> {
        public static final int SIZE = 8;

        public DWORDLONG(long j) {
            super(8, j, true);
        }

        public DWORDLONG() {
            this(0L);
        }

        @Override // java.lang.Comparable
        public int compareTo(DWORDLONG dwordlong) {
            return IntegerType.compare(this, dwordlong);
        }
    }

    public static class LONGLONG extends IntegerType implements Comparable<LONGLONG> {
        public static final int SIZE = Native.LONG_SIZE * 2;

        public LONGLONG(long j) {
            super(8, j, false);
        }

        public LONGLONG() {
            this(0L);
        }

        @Override // java.lang.Comparable
        public int compareTo(LONGLONG longlong) {
            return IntegerType.compare(this, longlong);
        }
    }

    public static class UCHAR extends IntegerType implements Comparable<UCHAR> {
        public static final int SIZE = 1;

        public UCHAR(char c) {
            this(c & 255);
        }

        public UCHAR() {
            this(0L);
        }

        @Override // java.lang.Comparable
        public int compareTo(UCHAR uchar) {
            return IntegerType.compare(this, uchar);
        }

        public UCHAR(long j) {
            super(1, j, true);
        }
    }

    public static class UINT_PTR extends IntegerType {
        public UINT_PTR(long j) {
            super(Native.POINTER_SIZE, j, true);
        }

        public Pointer toPointer() {
            return Pointer.createConstant(longValue());
        }

        public UINT_PTR() {
            super(Native.POINTER_SIZE);
        }
    }

    public static class ULONG extends IntegerType implements Comparable<ULONG> {
        public static final int SIZE = Native.LONG_SIZE;

        public ULONG(long j) {
            super(SIZE, j, true);
        }

        public ULONG() {
            this(0L);
        }

        @Override // java.lang.Comparable
        public int compareTo(ULONG ulong) {
            return IntegerType.compare(this, ulong);
        }
    }

    public static class ULONGLONG extends IntegerType implements Comparable<ULONGLONG> {
        public static final int SIZE = 8;

        public ULONGLONG(long j) {
            super(8, j, true);
        }

        public ULONGLONG() {
            this(0L);
        }

        @Override // java.lang.Comparable
        public int compareTo(ULONGLONG ulonglong) {
            return IntegerType.compare(this, ulonglong);
        }
    }

    public static class HICON extends WinNT.HANDLE {
        public HICON(WinNT.HANDLE handle) {
            this(handle.getPointer());
        }

        public HICON() {
        }

        public HICON(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HKL extends WinNT.HANDLE {
        public HKL(int i) {
            super(Pointer.createConstant(i));
        }

        public int getDeviceHandle() {
            return (int) ((Pointer.nativeValue(getPointer()) >> 16) & 65535);
        }

        public int getLanguageIdentifier() {
            return (int) (Pointer.nativeValue(getPointer()) & 65535);
        }

        @Override // com.sun.jna.platform.win32.WinNT.HANDLE, com.sun.jna.PointerType
        public String toString() {
            return String.format("%08x", Long.valueOf(Pointer.nativeValue(getPointer())));
        }

        public HKL(Pointer pointer) {
            super(pointer);
        }

        public HKL() {
        }
    }

    public static class BOOLByReference extends ByReference {
        public BOOLByReference() {
            this(new BOOL(0L));
        }

        public BOOL getValue() {
            return new BOOL(getPointer().getInt(0L));
        }

        public void setValue(BOOL bool) {
            getPointer().setInt(0L, bool.intValue());
        }

        public BOOLByReference(BOOL bool) {
            super(4);
            setValue(bool);
        }
    }

    public static class CHARByReference extends ByReference {
        public CHARByReference() {
            this(new CHAR(0L));
        }

        public CHAR getValue() {
            return new CHAR(getPointer().getByte(0L));
        }

        public void setValue(CHAR r3) {
            getPointer().setByte(0L, r3.byteValue());
        }

        public CHARByReference(CHAR r2) {
            super(1);
            setValue(r2);
        }
    }

    public static class DWORDByReference extends ByReference {
        public DWORDByReference() {
            this(new DWORD(0L));
        }

        public DWORD getValue() {
            return new DWORD(getPointer().getInt(0L));
        }

        public void setValue(DWORD dword) {
            getPointer().setInt(0L, dword.intValue());
        }

        public DWORDByReference(DWORD dword) {
            super(4);
            setValue(dword);
        }
    }

    public static class LONGByReference extends ByReference {
        public LONGByReference() {
            this(new LONG(0L));
        }

        public LONG getValue() {
            return new LONG(getPointer().getInt(0L));
        }

        public void setValue(LONG r3) {
            getPointer().setInt(0L, r3.intValue());
        }

        public LONGByReference(LONG r2) {
            super(LONG.SIZE);
            setValue(r2);
        }
    }

    public static class LONGLONGByReference extends ByReference {
        public LONGLONGByReference() {
            this(new LONGLONG(0L));
        }

        public LONGLONG getValue() {
            return new LONGLONG(getPointer().getLong(0L));
        }

        public void setValue(LONGLONG longlong) {
            getPointer().setLong(0L, longlong.longValue());
        }

        public LONGLONGByReference(LONGLONG longlong) {
            super(LONGLONG.SIZE);
            setValue(longlong);
        }
    }

    public static class SCODEByReference extends ByReference {
        public SCODEByReference() {
            this(new SCODE(0L));
        }

        public SCODE getValue() {
            return new SCODE(getPointer().getInt(0L));
        }

        public void setValue(SCODE scode) {
            getPointer().setInt(0L, scode.intValue());
        }

        public SCODEByReference(SCODE scode) {
            super(ULONG.SIZE);
            setValue(scode);
        }
    }

    public static class UINTByReference extends ByReference {
        public UINTByReference() {
            this(new UINT(0L));
        }

        public UINT getValue() {
            return new UINT(getPointer().getInt(0L));
        }

        public void setValue(UINT uint) {
            getPointer().setInt(0L, uint.intValue());
        }

        public UINTByReference(UINT uint) {
            super(4);
            setValue(uint);
        }
    }

    public static class ULONGByReference extends ByReference {
        public ULONGByReference() {
            this(new ULONG(0L));
        }

        public ULONG getValue() {
            return new ULONG(getPointer().getInt(0L));
        }

        public void setValue(ULONG ulong) {
            getPointer().setInt(0L, ulong.intValue());
        }

        public ULONGByReference(ULONG ulong) {
            super(ULONG.SIZE);
            setValue(ulong);
        }
    }

    public static class ULONGLONGByReference extends ByReference {
        public ULONGLONGByReference() {
            this(new ULONGLONG(0L));
        }

        public ULONGLONG getValue() {
            return new ULONGLONG(getPointer().getLong(0L));
        }

        public void setValue(ULONGLONG ulonglong) {
            getPointer().setLong(0L, ulonglong.longValue());
        }

        public ULONGLONGByReference(ULONGLONG ulonglong) {
            super(8);
            setValue(ulonglong);
        }
    }

    public static class WORDByReference extends ByReference {
        public WORDByReference() {
            this(new WORD(0L));
        }

        public WORD getValue() {
            return new WORD(getPointer().getShort(0L));
        }

        public void setValue(WORD word) {
            getPointer().setShort(0L, word.shortValue());
        }

        public WORDByReference(WORD word) {
            super(2);
            setValue(word);
        }
    }

    public static class USHORTByReference extends ByReference {
        public USHORTByReference(short s) {
            super(2);
            setValue(new USHORT(s));
        }

        public USHORT getValue() {
            return new USHORT(getPointer().getShort(0L));
        }

        public void setValue(USHORT ushort) {
            getPointer().setShort(0L, ushort.shortValue());
        }

        public USHORTByReference(USHORT ushort) {
            super(2);
            setValue(ushort);
        }

        public USHORTByReference() {
            this(new USHORT(0L));
        }
    }
}
