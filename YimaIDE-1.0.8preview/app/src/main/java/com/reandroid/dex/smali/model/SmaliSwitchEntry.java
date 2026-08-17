package com.reandroid.dex.smali.model;

import com.reandroid.utils.ObjectsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SmaliSwitchEntry extends Smali {
    private final SmaliLabel label;

    public SmaliSwitchEntry() {
        SmaliLabel smaliLabel = new SmaliLabel();
        this.label = smaliLabel;
        smaliLabel.setParent(this);
    }

    public SmaliLabel getLabel() {
        return this.label;
    }

    public SmaliSwitchPayload<?> getPayload() {
        return (SmaliSwitchPayload) ObjectsUtil.cast(getParentInstance(SmaliSwitchPayload.class));
    }

    public Integer getRelativeOffset() {
        SmaliInstruction targetInstruction;
        SmaliInstruction smaliInstruction = getSwitch();
        if (smaliInstruction == null || (targetInstruction = getTargetInstruction()) == null) {
            return null;
        }
        return Integer.valueOf(targetInstruction.getAddress() - smaliInstruction.getAddress());
    }

    public SmaliInstruction getSwitch() {
        SmaliSwitchPayload<?> payload = getPayload();
        if (payload != null) {
            return payload.getSwitch();
        }
        return null;
    }

    public SmaliInstruction getTargetInstruction() {
        return getLabel().getTargetInstruction();
    }
}
