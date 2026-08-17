package com.sun.jna.platform.win32.COM;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IEnumIDList {
    public static final Guid.IID IID_IEnumIDList = new Guid.IID("{000214F2-0000-0000-C000-000000000046}");

    public static class Converter {
        public static IEnumIDList PointerToIEnumIDList(PointerByReference pointerByReference) {
            final Pointer value = pointerByReference.getValue();
            final Pointer[] pointerArr = new Pointer[7];
            value.getPointer(0L).read(0L, pointerArr, 0, 7);
            return new IEnumIDList() { // from class: com.sun.jna.platform.win32.COM.IEnumIDList.Converter.1
                @Override // com.sun.jna.platform.win32.COM.IEnumIDList
                public int AddRef() {
                    return Function.getFunction(pointerArr[1], 63).invokeInt(new Object[]{value});
                }

                @Override // com.sun.jna.platform.win32.COM.IEnumIDList
                public WinNT.HRESULT Clone(PointerByReference pointerByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[6], 63).invokeInt(new Object[]{value, pointerByReference2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IEnumIDList
                public WinNT.HRESULT Next(int i, PointerByReference pointerByReference2, IntByReference intByReference) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[3], 63).invokeInt(new Object[]{value, Integer.valueOf(i), pointerByReference2, intByReference}));
                }

                @Override // com.sun.jna.platform.win32.COM.IEnumIDList
                public WinNT.HRESULT QueryInterface(Guid.REFIID refiid, PointerByReference pointerByReference2) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[0], 63).invokeInt(new Object[]{value, refiid, pointerByReference2}));
                }

                @Override // com.sun.jna.platform.win32.COM.IEnumIDList
                public int Release() {
                    return Function.getFunction(pointerArr[2], 63).invokeInt(new Object[]{value});
                }

                @Override // com.sun.jna.platform.win32.COM.IEnumIDList
                public WinNT.HRESULT Reset() {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[5], 63).invokeInt(new Object[]{value}));
                }

                @Override // com.sun.jna.platform.win32.COM.IEnumIDList
                public WinNT.HRESULT Skip(int i) {
                    return new WinNT.HRESULT(Function.getFunction(pointerArr[4], 63).invokeInt(new Object[]{value, Integer.valueOf(i)}));
                }
            };
        }
    }

    int AddRef();

    WinNT.HRESULT Clone(PointerByReference pointerByReference);

    WinNT.HRESULT Next(int i, PointerByReference pointerByReference, IntByReference intByReference);

    WinNT.HRESULT QueryInterface(Guid.REFIID refiid, PointerByReference pointerByReference);

    int Release();

    WinNT.HRESULT Reset();

    WinNT.HRESULT Skip(int i);
}
