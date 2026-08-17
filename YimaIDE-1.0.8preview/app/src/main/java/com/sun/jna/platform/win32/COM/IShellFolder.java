package com.sun.jna.platform.win32.COM;

import com.sun.jna.Function;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.ShTypes;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IShellFolder {
    public static final Guid.IID IID_ISHELLFOLDER = new Guid.IID("{000214E6-0000-0000-C000-000000000046}");

    public static class Converter {
        public static IShellFolder PointerToIShellFolder(PointerByReference pointerByReference) {
            final Pointer value = pointerByReference.getValue();
            final Pointer[] pointerArr = new Pointer[13];
            value.getPointer(0L).read(0L, pointerArr, 0, 13);
            return new IShellFolder() { // from class: com.sun.jna.platform.win32.COM.IShellFolder.Converter.1
                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public int AddRef() {
                    return Function.getFunction(pointerArr[1], 63).invokeInt(new Object[]{value});
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT BindToObject(Pointer pointer, Pointer pointer2, Guid.REFIID refiid, PointerByReference pointerByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[5], 63).invokeInt(new Object[]{value, pointer, pointer2, refiid, pointerByReference2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT BindToStorage(Pointer pointer, Pointer pointer2, Guid.REFIID refiid, PointerByReference pointerByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[6], 63).invokeInt(new Object[]{value, pointer, pointer2, refiid, pointerByReference2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT CompareIDs(WinDef.LPARAM lparam, Pointer pointer, Pointer pointer2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[7], 63).invokeInt(new Object[]{value, lparam, pointer, pointer2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT CreateViewObject(WinDef.HWND hwnd, Guid.REFIID refiid, PointerByReference pointerByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[8], 63).invokeInt(new Object[]{value, hwnd, refiid, pointerByReference2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT EnumObjects(WinDef.HWND hwnd, int i, PointerByReference pointerByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[4], 63).invokeInt(new Object[]{value, hwnd, Integer.valueOf(i), pointerByReference2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT GetAttributesOf(int i, Pointer pointer, IntByReference intByReference) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[9], 63).invokeInt(new Object[]{value, Integer.valueOf(i), pointer, intByReference}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT GetDisplayNameOf(Pointer pointer, int i, ShTypes.STRRET strret) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[11], 63).invokeInt(new Object[]{value, pointer, Integer.valueOf(i), strret}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT GetUIObjectOf(WinDef.HWND hwnd, int i, Pointer pointer, Guid.REFIID refiid, IntByReference intByReference, PointerByReference pointerByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[10], 63).invokeInt(new Object[]{value, hwnd, Integer.valueOf(i), pointer, refiid, intByReference, pointerByReference2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT ParseDisplayName(WinDef.HWND hwnd, Pointer pointer, String str, IntByReference intByReference, PointerByReference pointerByReference2, IntByReference intByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[3], 63).invokeInt(new Object[]{value, hwnd, pointer, Native.toCharArray(str), intByReference, pointerByReference2, intByReference2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT QueryInterface(Guid.REFIID refiid, PointerByReference pointerByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[0], 63).invokeInt(new Object[]{value, refiid, pointerByReference2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public int Release() {
                    return Function.getFunction(pointerArr[2], 63).invokeInt(new Object[]{value});
                }

                @Override // com.sun.jna.platform.win32.COM.IShellFolder
                public WinNT.HRESULT SetNameOf(WinDef.HWND hwnd, Pointer pointer, String str, int i, PointerByReference pointerByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[12], 63).invokeInt(new Object[]{value, hwnd, pointer, str, Integer.valueOf(i), pointerByReference2}));
                }
            };
        }
    }

    int AddRef();

    WinNT.HRESULT BindToObject(Pointer pointer, Pointer pointer2, Guid.REFIID refiid, PointerByReference pointerByReference);

    WinNT.HRESULT BindToStorage(Pointer pointer, Pointer pointer2, Guid.REFIID refiid, PointerByReference pointerByReference);

    WinNT.HRESULT CompareIDs(WinDef.LPARAM lparam, Pointer pointer, Pointer pointer2);

    WinNT.HRESULT CreateViewObject(WinDef.HWND hwnd, Guid.REFIID refiid, PointerByReference pointerByReference);

    WinNT.HRESULT EnumObjects(WinDef.HWND hwnd, int i, PointerByReference pointerByReference);

    WinNT.HRESULT GetAttributesOf(int i, Pointer pointer, IntByReference intByReference);

    WinNT.HRESULT GetDisplayNameOf(Pointer pointer, int i, ShTypes.STRRET strret);

    WinNT.HRESULT GetUIObjectOf(WinDef.HWND hwnd, int i, Pointer pointer, Guid.REFIID refiid, IntByReference intByReference, PointerByReference pointerByReference);

    WinNT.HRESULT ParseDisplayName(WinDef.HWND hwnd, Pointer pointer, String str, IntByReference intByReference, PointerByReference pointerByReference, IntByReference intByReference2);

    WinNT.HRESULT QueryInterface(Guid.REFIID refiid, PointerByReference pointerByReference);

    int Release();

    WinNT.HRESULT SetNameOf(WinDef.HWND hwnd, Pointer pointer, String str, int i, PointerByReference pointerByReference);
}
