package com.Mode.toolbox;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/* loaded from: /workspace/xh/fix_3950620.dex */
public class BatterytemperaturefloatingwindowService extends Service implements View.OnTouchListener {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f20short = null;
    private BroadcastReceiver batteryReceiver;
    private float currentTemp;
    private View floatView;
    private WindowManager.LayoutParams params;
    private float startTouchX;
    private float startTouchY;
    private int startX;
    private int startY;
    private WindowManager wm;

    /* renamed from: com.Mode.toolbox.BatterytemperaturefloatingwindowService$1, reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f21short = null;
        final /* synthetic */ BatterytemperaturefloatingwindowService this$0;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0000: IGET r12, r3
            java.lang.IllegalArgumentException: newPosition > limit: (235568 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
            	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:150)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:130)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:128)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:113)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:101)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.buildUsageData(UsageInfoVisitor.java:81)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:65)
            	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:356)
            */
        /*  JADX ERROR: Dependency scan failed at insn: 0x0004: INVOKE_DIRECT r4, r1, r6, r4, r9
            java.lang.IllegalArgumentException: newPosition > limit: (151044 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
            	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
            	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:165)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:130)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:128)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:113)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:101)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.buildUsageData(UsageInfoVisitor.java:81)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:65)
            	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:356)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0000: IGET r12, r3
            java.lang.IllegalArgumentException: newPosition > limit: (235568 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
            	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:370)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0004: INVOKE_DIRECT r4, r1, r6, r4, r9
            java.lang.IllegalArgumentException: newPosition > limit: (151044 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
            	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
            	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:442)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass1(com.Mode.toolbox.BatterytemperaturefloatingwindowService r52) {
            /*
                r51 = this;
                // decode failed: newPosition > limit: (235568 > 104176)
                if (r21 >= 0) goto LB_75d1
                // decode failed: newPosition > limit: (151044 > 104176)
                float r207 = r230 / r154
                double r156 = r52 - r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.BatterytemperaturefloatingwindowService.AnonymousClass1.<init>(com.Mode.toolbox.BatterytemperaturefloatingwindowService):void");
        }

        /* renamed from: ۟۟ۡۡۥ, reason: not valid java name and contains not printable characters */
        public static native BatterytemperaturefloatingwindowService m82(Object obj);

        /* renamed from: ۢ۟ۡۤ, reason: not valid java name and contains not printable characters */
        public static native short[] m83();

        @Override // android.content.BroadcastReceiver
        public native void onReceive(Context context, Intent intent);
    }

    /* renamed from: ۟۟۟ۡ۠, reason: not valid java name and contains not printable characters */
    public static native int m73(Object obj);

    /* renamed from: ۣ۟ۤۢۥ, reason: not valid java name and contains not printable characters */
    public static native View m74(Object obj);

    /* renamed from: ۟ۥۧۧۧ, reason: not valid java name and contains not printable characters */
    public static native float m75(Object obj);

    /* renamed from: ۟ۧۡ۟, reason: not valid java name and contains not printable characters */
    public static native short[] m76();

    /* renamed from: ۣ۠ۢ, reason: not valid java name and contains not printable characters */
    public static native WindowManager.LayoutParams m77(Object obj);

    /* renamed from: ۣۤ۟ۧ, reason: not valid java name and contains not printable characters */
    public static native BroadcastReceiver m78(Object obj);

    /* renamed from: ۤۢۧ, reason: not valid java name and contains not printable characters */
    public static native WindowManager m79(Object obj);

    /* renamed from: ۤۥ۟ۥ, reason: not valid java name and contains not printable characters */
    public static native float m80(Object obj);

    /* renamed from: ۦۦۣۤ, reason: contains not printable characters */
    public static native int m81(Object obj);

    @Override // android.app.Service
    public native IBinder onBind(Intent intent);

    @Override // android.app.Service
    public native void onCreate();

    @Override // android.app.Service
    public native void onDestroy();

    @Override // android.app.Service
    public native int onStartCommand(Intent intent, int i, int i2);

    @Override // android.view.View.OnTouchListener
    public native boolean onTouch(View view, MotionEvent motionEvent);

    public native void updateTemperature(float f);

    public native void updateViewLayout();
}
