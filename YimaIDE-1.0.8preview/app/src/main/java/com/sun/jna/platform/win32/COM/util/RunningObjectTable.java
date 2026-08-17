package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.COMException;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.ptr.PointerByReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RunningObjectTable implements IRunningObjectTable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    ObjectFactory factory;
    com.sun.jna.platform.win32.COM.RunningObjectTable raw;

    public RunningObjectTable(com.sun.jna.platform.win32.COM.RunningObjectTable runningObjectTable, ObjectFactory objectFactory) {
        this.raw = runningObjectTable;
        this.factory = objectFactory;
    }

    @Override // com.sun.jna.platform.win32.COM.util.IRunningObjectTable
    public Iterable<IDispatch> enumRunning() {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(this.raw.EnumRunning(pointerByReference));
        return new EnumMoniker(new com.sun.jna.platform.win32.COM.EnumMoniker(pointerByReference.getValue()), this.raw, this.factory);
    }

    @Override // com.sun.jna.platform.win32.COM.util.IRunningObjectTable
    public <T> List<T> getActiveObjectsByInterface(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        Iterator<IDispatch> it = enumRunning().iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(it.next().queryInterface(cls));
            } catch (COMException unused) {
            }
        }
        return arrayList;
    }
}
