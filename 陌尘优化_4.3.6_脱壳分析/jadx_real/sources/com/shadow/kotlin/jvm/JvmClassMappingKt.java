package com.shadow.kotlin.jvm;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.ClassBasedDeclarationContainer;
import com.swift.sandhook.annotation.MethodReflectParams;
import kotlin.reflect.KClass;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class JvmClassMappingKt {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public static final <T> Class<T> getJavaObjectType(KClass<T> kClass) {
        CloseableKt.checkNotNullParameter(kClass, "<this>");
        Class<T> cls = (Class<T>) ((ClassBasedDeclarationContainer) kClass).getJClass();
        if (!cls.isPrimitive()) {
            return cls;
        }
        String name = cls.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (!name.equals(MethodReflectParams.DOUBLE)) {
                }
                break;
            case 104431:
                if (!name.equals(MethodReflectParams.INT)) {
                }
                break;
            case 3039496:
                if (!name.equals(MethodReflectParams.BYTE)) {
                }
                break;
            case 3052374:
                if (!name.equals(MethodReflectParams.CHAR)) {
                }
                break;
            case 3327612:
                if (!name.equals(MethodReflectParams.LONG)) {
                }
                break;
            case 3625364:
                if (!name.equals("void")) {
                }
                break;
            case 64711720:
                if (!name.equals(MethodReflectParams.BOOLEAN)) {
                }
                break;
            case 97526364:
                if (!name.equals(MethodReflectParams.FLOAT)) {
                }
                break;
            case 109413500:
                if (!name.equals(MethodReflectParams.SHORT)) {
                }
                break;
        }
        return cls;
    }
}
