package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Moniker extends Unknown implements IMoniker {
    static final int vTableIdStart = 7;

    public static class ByReference extends Moniker implements Structure.ByReference {
    }

    public Moniker() {
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void BindToObject() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void BindToStorage() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void CommonPrefixWith() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void ComposeWith() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void Enum() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IPersist
    public Guid.CLSID GetClassID() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public String GetDisplayName(Pointer pointer, Pointer pointer2) {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC((WinNT.HRESULT) _invokeNativeObject(20, new Object[]{getPointer(), pointer, pointer2, pointerByReference}, WinNT.HRESULT.class));
        Pointer value = pointerByReference.getValue();
        if (value == null) {
            return null;
        }
        String value2 = new WTypes.LPOLESTR(value).getValue();
        Ole32.INSTANCE.CoTaskMemFree(value);
        return value2;
    }

    @Override // com.sun.jna.platform.win32.COM.IPersistStream
    public void GetSizeMax() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void GetTimeOfLastChange() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void Hash() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void Inverse() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IPersistStream
    public boolean IsDirty() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void IsEqual() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void IsRunning() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void IsSystemMoniker() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IPersistStream
    public void Load(IStream iStream) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void ParseDisplayName() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void Reduce() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IMoniker
    public void RelativePathTo() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.jna.platform.win32.COM.IPersistStream
    public void Save(IStream iStream) {
        throw new UnsupportedOperationException();
    }

    public Moniker(Pointer pointer) {
        super(pointer);
    }
}
