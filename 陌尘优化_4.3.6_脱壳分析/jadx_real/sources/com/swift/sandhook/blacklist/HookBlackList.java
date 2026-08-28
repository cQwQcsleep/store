package com.swift.sandhook.blacklist;

import com.swift.sandhook.SandHookConfig;
import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class HookBlackList {
    public static Set<String> methodBlackList = new HashSet();
    public static Set<Class> classBlackList = new HashSet();
    public static Set<String> methodUseInHookBridge = new HashSet();
    public static Set<String> methodUseInHookStub = new HashSet();

    static {
        methodBlackList.add(NPStringFog.decode("04111B00400D060B1540020807020404115C231519090105490C1C181F0604"));
        methodBlackList.add(NPStringFog.decode("04111B00400D060B1540020807020404115C2F130E041D120E071E0B3F0F0B0B02134B010B042C020D0414161B0C1C08"));
        methodUseInHookBridge.add(NPStringFog.decode("04111B00400D060B15403301001D124902171A34080202001500162819080D0A"));
        methodUseInHookBridge.add(NPStringFog.decode("04111B00400D060B1540020807020404115C271E1B0E0D00130C1D00240C13090413200A0D151D15070E094B150B042E001B1202"));
        methodUseInHookStub.add(NPStringFog.decode("04111B00400D060B15403F0F0B0B02134B171F050C0D1D"));
        methodUseInHookStub.add(NPStringFog.decode("04111B00400D060B15403301001D12490C013E02040C07150E1317"));
    }

    public static final boolean canNotHook(Member member) {
        if (classBlackList.contains(member.getDeclaringClass())) {
            return true;
        }
        return methodBlackList.contains(member.getDeclaringClass().getName() + NPStringFog.decode("40") + member.getName());
    }

    public static final boolean canNotHookByBridge(Member member) {
        return methodUseInHookBridge.contains(member.getDeclaringClass().getName() + NPStringFog.decode("40") + member.getName());
    }

    public static final boolean canNotHookByStub(Member member) {
        if (SandHookConfig.SDK_INT >= 29 && Thread.class.equals(member.getDeclaringClass())) {
            return true;
        }
        return methodUseInHookStub.contains(member.getDeclaringClass().getName() + NPStringFog.decode("40") + member.getName());
    }
}
