package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumVariant extends Unknown implements IEnumVariant {
    public static final Guid.IID IID;
    public static final Guid.REFIID REFIID;

    static {
        Guid.IID iid = new Guid.IID("{00020404-0000-0000-C000-000000000046}");
        IID = iid;
        REFIID = new Guid.REFIID(iid);
    }

    public EnumVariant(Pointer pointer) {
        setPointer(pointer);
    }

    @Override // com.sun.jna.platform.win32.COM.IEnumVariant
    public EnumVariant Clone() {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC((WinNT.HRESULT) _invokeNativeObject(6, new Object[]{getPointer(), pointerByReference}, WinNT.HRESULT.class));
        return new EnumVariant(pointerByReference.getValue());
    }

    @Override // com.sun.jna.platform.win32.COM.IEnumVariant
    public Variant.VARIANT[] Next(int i) {
        Variant.VARIANT[] variantArr = new Variant.VARIANT[i];
        IntByReference intByReference = new IntByReference();
        COMUtils.checkRC((WinNT.HRESULT) _invokeNativeObject(3, new Object[]{getPointer(), Integer.valueOf(i), variantArr, intByReference}, WinNT.HRESULT.class));
        Variant.VARIANT[] variantArr2 = new Variant.VARIANT[intByReference.getValue()];
        System.arraycopy(variantArr, 0, variantArr2, 0, intByReference.getValue());
        return variantArr2;
    }

    @Override // com.sun.jna.platform.win32.COM.IEnumVariant
    public void Reset() {
        COMUtils.checkRC((WinNT.HRESULT) _invokeNativeObject(5, new Object[]{getPointer()}, WinNT.HRESULT.class));
    }

    @Override // com.sun.jna.platform.win32.COM.IEnumVariant
    public void Skip(int i) {
        COMUtils.checkRC((WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), Integer.valueOf(i)}, WinNT.HRESULT.class));
    }

    public EnumVariant() {
    }
}
