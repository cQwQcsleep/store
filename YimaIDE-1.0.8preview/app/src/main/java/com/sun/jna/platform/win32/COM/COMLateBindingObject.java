package com.sun.jna.platform.win32.COM;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import java.util.Date;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class COMLateBindingObject extends COMBindingBaseObject {
    public COMLateBindingObject(IDispatch iDispatch) {
        super(iDispatch);
    }

    public IDispatch getAutomationProperty(String str) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str);
        return (IDispatch) byReference.getValue();
    }

    public boolean getBooleanProperty(String str) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str);
        return byReference.booleanValue();
    }

    public Date getDateProperty(String str) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str);
        return byReference.dateValue();
    }

    public int getIntProperty(String str) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str);
        return byReference.intValue();
    }

    public short getShortProperty(String str) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str);
        return byReference.shortValue();
    }

    public String getStringProperty(String str) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str);
        String strStringValue = byReference.stringValue();
        OleAuto.INSTANCE.VariantClear(byReference);
        return strStringValue;
    }

    public Variant.VARIANT invoke(String str) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(1, byReference, str);
        return byReference;
    }

    @Deprecated
    public void invokeNoReply(String str, COMLateBindingObject cOMLateBindingObject, Variant.VARIANT variant, Variant.VARIANT variant2) {
        oleMethod(1, (Variant.VARIANT.ByReference) null, cOMLateBindingObject.getIDispatch(), str, new Variant.VARIANT[]{variant, variant2});
    }

    public void setProperty(String str, String str2) {
        Variant.VARIANT variant = new Variant.VARIANT(str2);
        try {
            oleMethod(4, (Variant.VARIANT.ByReference) null, str, variant);
        } finally {
            OleAuto.INSTANCE.VariantClear(variant);
        }
    }

    public Variant.VARIANT toVariant() {
        return new Variant.VARIANT(getIDispatch());
    }

    public COMLateBindingObject(Guid.CLSID clsid, boolean z) {
        super(clsid, z);
    }

    public COMLateBindingObject(String str, boolean z) throws COMException {
        super(str, z);
    }

    public Variant.VARIANT invoke(String str, Variant.VARIANT variant) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(1, byReference, str, variant);
        return byReference;
    }

    public Variant.VARIANT invoke(String str, Variant.VARIANT[] variantArr) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(1, byReference, str, variantArr);
        return byReference;
    }

    public Variant.VARIANT invoke(String str, Variant.VARIANT variant, Variant.VARIANT variant2) {
        return invoke(str, new Variant.VARIANT[]{variant, variant2});
    }

    public Variant.VARIANT invoke(String str, Variant.VARIANT variant, Variant.VARIANT variant2, Variant.VARIANT variant3) {
        return invoke(str, new Variant.VARIANT[]{variant, variant2, variant3});
    }

    public IDispatch getAutomationProperty(String str, Variant.VARIANT variant) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str, variant);
        return (IDispatch) byReference.getValue();
    }

    public Variant.VARIANT invoke(String str, Variant.VARIANT variant, Variant.VARIANT variant2, Variant.VARIANT variant3, Variant.VARIANT variant4) {
        return invoke(str, new Variant.VARIANT[]{variant, variant2, variant3, variant4});
    }

    @Deprecated
    public void invokeNoReply(String str, COMLateBindingObject cOMLateBindingObject) {
        oleMethod(1, (Variant.VARIANT.ByReference) null, cOMLateBindingObject.getIDispatch(), str);
    }

    public void invokeNoReply(String str, Variant.VARIANT variant) {
        oleMethod(1, (Variant.VARIANT.ByReference) null, str, variant);
    }

    @Deprecated
    public void invokeNoReply(String str, IDispatch iDispatch, Variant.VARIANT variant) {
        oleMethod(1, (Variant.VARIANT.ByReference) null, iDispatch, str, variant);
    }

    @Deprecated
    public IDispatch getAutomationProperty(String str, COMLateBindingObject cOMLateBindingObject) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str);
        return (IDispatch) byReference.getValue();
    }

    @Deprecated
    public void invokeNoReply(String str, IDispatch iDispatch, Variant.VARIANT variant, Variant.VARIANT variant2) {
        oleMethod(1, (Variant.VARIANT.ByReference) null, iDispatch, str, new Variant.VARIANT[]{variant, variant2});
    }

    @Deprecated
    public void invokeNoReply(String str, IDispatch iDispatch) {
        oleMethod(1, (Variant.VARIANT.ByReference) null, iDispatch, str);
    }

    @Deprecated
    public void invokeNoReply(String str, COMLateBindingObject cOMLateBindingObject, Variant.VARIANT variant) {
        oleMethod(1, (Variant.VARIANT.ByReference) null, cOMLateBindingObject.getIDispatch(), str, variant);
    }

    @Deprecated
    public IDispatch getAutomationProperty(String str, COMLateBindingObject cOMLateBindingObject, Variant.VARIANT variant) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str, variant);
        return (IDispatch) byReference.getValue();
    }

    @Deprecated
    public void invokeNoReply(String str, IDispatch iDispatch, Variant.VARIANT[] variantArr) {
        oleMethod(1, (Variant.VARIANT.ByReference) null, iDispatch, str, variantArr);
    }

    public void invokeNoReply(String str) {
        oleMethod(1, new Variant.VARIANT.ByReference(), str);
    }

    public void setProperty(String str, Date date) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, str, new Variant.VARIANT(date));
    }

    public void setProperty(String str, Dispatch dispatch) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, str, new Variant.VARIANT(dispatch));
    }

    @Deprecated
    public IDispatch getAutomationProperty(String str, IDispatch iDispatch) {
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        oleMethod(2, byReference, str);
        return (IDispatch) byReference.getValue();
    }

    public void invokeNoReply(String str, Variant.VARIANT[] variantArr) {
        oleMethod(1, new Variant.VARIANT.ByReference(), str, variantArr);
    }

    @Deprecated
    public void setProperty(String str, IDispatch iDispatch) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, str, new Variant.VARIANT(iDispatch));
    }

    public void setProperty(String str, int i) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, str, new Variant.VARIANT(i));
    }

    public void invokeNoReply(String str, Variant.VARIANT variant, Variant.VARIANT variant2) {
        invokeNoReply(str, new Variant.VARIANT[]{variant, variant2});
    }

    public void setProperty(String str, short s) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, str, new Variant.VARIANT(s));
    }

    public void invokeNoReply(String str, Variant.VARIANT variant, Variant.VARIANT variant2, Variant.VARIANT variant3) {
        invokeNoReply(str, new Variant.VARIANT[]{variant, variant2, variant3});
    }

    public void setProperty(String str, boolean z) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, str, new Variant.VARIANT(z));
    }

    public void invokeNoReply(String str, Variant.VARIANT variant, Variant.VARIANT variant2, Variant.VARIANT variant3, Variant.VARIANT variant4) {
        invokeNoReply(str, new Variant.VARIANT[]{variant, variant2, variant3, variant4});
    }

    public void setProperty(String str, Variant.VARIANT variant) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, str, variant);
    }

    @Deprecated
    public void setProperty(String str, IDispatch iDispatch, Variant.VARIANT variant) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, iDispatch, str, variant);
    }

    @Deprecated
    public void setProperty(String str, COMLateBindingObject cOMLateBindingObject, Variant.VARIANT variant) {
        oleMethod(4, (Variant.VARIANT.ByReference) null, cOMLateBindingObject.getIDispatch(), str, variant);
    }
}
