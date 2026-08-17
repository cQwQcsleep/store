package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Dispatch;
import com.sun.jna.platform.win32.COM.IEnumMoniker;
import com.sun.jna.platform.win32.COM.Moniker;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumMoniker implements Iterable<IDispatch> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    ObjectFactory factory;
    IEnumMoniker raw;
    Moniker rawNext;
    com.sun.jna.platform.win32.COM.IRunningObjectTable rawRot;

    public EnumMoniker(IEnumMoniker iEnumMoniker, com.sun.jna.platform.win32.COM.IRunningObjectTable iRunningObjectTable, ObjectFactory objectFactory) {
        this.rawRot = iRunningObjectTable;
        this.raw = iEnumMoniker;
        this.factory = objectFactory;
        COMUtils.checkRC(iEnumMoniker.Reset());
        cacheNext();
    }

    public void cacheNext() {
        PointerByReference pointerByReference = new PointerByReference();
        WinDef.ULONGByReference uLONGByReference = new WinDef.ULONGByReference();
        WinNT.HRESULT hresultNext = this.raw.Next(new WinDef.ULONG(1L), pointerByReference, uLONGByReference);
        if (WinError.S_OK.equals(hresultNext) && uLONGByReference.getValue().intValue() > 0) {
            this.rawNext = new Moniker(pointerByReference.getValue());
            return;
        }
        if (!WinError.S_FALSE.equals(hresultNext)) {
            COMUtils.checkRC(hresultNext);
        }
        this.rawNext = null;
    }

    @Override // java.lang.Iterable
    public Iterator<IDispatch> iterator() {
        return new Iterator<IDispatch>() { // from class: com.sun.jna.platform.win32.COM.util.EnumMoniker.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return EnumMoniker.this.rawNext != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public IDispatch next() {
                Moniker moniker = EnumMoniker.this.rawNext;
                PointerByReference pointerByReference = new PointerByReference();
                COMUtils.checkRC(EnumMoniker.this.rawRot.GetObject(moniker.getPointer(), pointerByReference));
                Dispatch dispatch = new Dispatch(pointerByReference.getValue());
                EnumMoniker.this.cacheNext();
                IDispatch iDispatch = (IDispatch) EnumMoniker.this.factory.createProxy(IDispatch.class, dispatch);
                dispatch.Release();
                return iDispatch;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }
}
