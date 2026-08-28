package com.Mode.toolbox;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException: Cannot invoke "java.util.List.forEach(java.util.function.Consumer)" because "blocks" is null
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:1029)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:245)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:160)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:65)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:58)
    */
/* loaded from: /workspace/xh/fix_3950620.dex */
public class OutputPageActivity extends Activity {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f66short = null;
    private String command;
    public ImageButton copyBtn;
    protected MyHandler mHandler;
    public Process p;
    public TextView t1;

    /* renamed from: com.Mode.toolbox.OutputPageActivity$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f68short = null;
        final /* synthetic */ OutputPageActivity this$0;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0000: NEW_ARRAY r11, r1
            java.lang.IllegalArgumentException: newPosition > limit: (188564 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:144)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:130)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:128)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:113)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:101)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.buildUsageData(UsageInfoVisitor.java:81)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:65)
            	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:356)
            */
        /*  JADX ERROR: Dependency scan failed at insn: 0x0002: SPUT r195
            java.lang.IllegalArgumentException: newPosition > limit: (213104 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: NEW_ARRAY r11, r1
            java.lang.IllegalArgumentException: newPosition > limit: (188564 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.instructions.InsnDecoder.makeNewArray(InsnDecoder.java:524)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:476)
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
        /*  JADX ERROR: Failed to decode insn: 0x0002: SPUT r195
            java.lang.IllegalArgumentException: newPosition > limit: (213104 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
            	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:390)
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
        AnonymousClass2(com.Mode.toolbox.OutputPageActivity r52) {
            /*
                r51 = this;
                // decode failed: newPosition > limit: (188564 > 104176)
                // decode failed: newPosition > limit: (213104 > 104176)
                r166[r0] = r67
                int r58 = r237 << r8
                monitor-enter(r238)
                r27 = 52
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.AnonymousClass2.<init>(com.Mode.toolbox.OutputPageActivity):void");
        }

        /* renamed from: ۟۠ۥۢۢ, reason: not valid java name and contains not printable characters */
        public static native MyHandler m310(Object obj);

        /* renamed from: ۣ۟ۥۤ, reason: not valid java name and contains not printable characters */
        public static native short[] m311();

        /* renamed from: ۠۟ۢۡ, reason: not valid java name and contains not printable characters */
        public static native boolean m312(Object obj, Object obj2);

        /* renamed from: ۣۧۥ, reason: not valid java name and contains not printable characters */
        public static native OutputPageActivity m313(Object obj);

        @Override // java.lang.Runnable
        public native void run();
    }

    /* renamed from: com.Mode.toolbox.OutputPageActivity$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f69short = null;
        final /* synthetic */ OutputPageActivity this$0;
        final /* synthetic */ float val$elapsedTime;
        final /* synthetic */ boolean val$isFinished;

        /*  JADX ERROR: Dependency scan failed at insn: 0x000A: IGET r14, r6
            java.lang.IllegalArgumentException: newPosition > limit: (526728 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x000E: INVOKE_POLYMORPHIC_RANGE r33265, r33266, r33267, r33268, r33269, r33270, r33271, r33272, r33273, r33274, r33275, r33276, r33277, r33278, r33279, r33280, r33281, r33282, r33283, r33284, r33285, r33286, r33287, r33288, r33289, r33290, r33291, r33292, r33293, r33294, r33295, r33296, r33297, r33298, r33299, r33300, r33301, r33302, r33303, r33304, r33305, r33306, r33307, r33308, r33309, r33310, r33311, r33312, r33313, r33314, r33315, r33316, r33317, r33318, r33319, r33320, r33321, r33322, r33323, r33324, r33325, r33326, r33327, r33328, r33329, r33330, r33331, r33332, r33333, r33334, r33335, r33336, r33337, r33338, r33339, r33340, r33341, r33342, r33343, r33344, r33345, r33346, r33347, r33348, r33349, r33350, r33351, r33352, r33353, r33354, r33355, r33356, r33357, r33358, r33359, r33360, r33361, r33362, r33363, r33364, r33365, r33366, r33367, r33368, r33369, r33370, r33371, r33372, r33373, r33374, r33375, r33376, r33377, r33378, r33379, r33380, r33381, r33382, r33383, r33384, r33385, r33386, r33387, r33388, r33389, r33390, r33391, r33392, r33393, r33394, r33395, r33396, r33397, r33398, r33399, r33400, r33401, r33402, r33403, r33404, r33405, r33406, r33407, r33408, r33409, r33410, r33411, r33412, r33413, r33414, r33415, r33416, r33417, r33418, r33419, r33420, r33421, r33422, r33423, r33424, r33425, r33426, r33427, r33428, r33429, r33430, r33431, r33432, r33433, r33434, r33435, r33436, r33437, r33438, r33439, r33440, r33441, r33442
            java.lang.IllegalArgumentException: newPosition > limit: (296064 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
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
        /*  JADX ERROR: Failed to decode insn: 0x0008: CONST_STRING r90
            java.lang.IllegalArgumentException: newPosition > limit: (144968 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:81)
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
        /*  JADX ERROR: Failed to decode insn: 0x000A: IGET r14, r6
            java.lang.IllegalArgumentException: newPosition > limit: (526728 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x000E: INVOKE_POLYMORPHIC_RANGE r33265, r33266, r33267, r33268, r33269, r33270, r33271, r33272, r33273, r33274, r33275, r33276, r33277, r33278, r33279, r33280, r33281, r33282, r33283, r33284, r33285, r33286, r33287, r33288, r33289, r33290, r33291, r33292, r33293, r33294, r33295, r33296, r33297, r33298, r33299, r33300, r33301, r33302, r33303, r33304, r33305, r33306, r33307, r33308, r33309, r33310, r33311, r33312, r33313, r33314, r33315, r33316, r33317, r33318, r33319, r33320, r33321, r33322, r33323, r33324, r33325, r33326, r33327, r33328, r33329, r33330, r33331, r33332, r33333, r33334, r33335, r33336, r33337, r33338, r33339, r33340, r33341, r33342, r33343, r33344, r33345, r33346, r33347, r33348, r33349, r33350, r33351, r33352, r33353, r33354, r33355, r33356, r33357, r33358, r33359, r33360, r33361, r33362, r33363, r33364, r33365, r33366, r33367, r33368, r33369, r33370, r33371, r33372, r33373, r33374, r33375, r33376, r33377, r33378, r33379, r33380, r33381, r33382, r33383, r33384, r33385, r33386, r33387, r33388, r33389, r33390, r33391, r33392, r33393, r33394, r33395, r33396, r33397, r33398, r33399, r33400, r33401, r33402, r33403, r33404, r33405, r33406, r33407, r33408, r33409, r33410, r33411, r33412, r33413, r33414, r33415, r33416, r33417, r33418, r33419, r33420, r33421, r33422, r33423, r33424, r33425, r33426, r33427, r33428, r33429, r33430, r33431, r33432, r33433, r33434, r33435, r33436, r33437, r33438, r33439, r33440, r33441, r33442
            java.lang.IllegalArgumentException: newPosition > limit: (296064 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
            	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
            	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invokePolymorphic(InsnDecoder.java:605)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:467)
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
        AnonymousClass3(com.Mode.toolbox.OutputPageActivity r52, float r53, boolean r54) {
            /*
                r51 = this;
                r19013 = r65412
                if (r133 > 0) goto LB_36f3
                double r3 = r3 + r5
                if (r5 != r2) goto L4e59
                // decode failed: newPosition > limit: (144968 > 104176)
                // decode failed: newPosition > limit: (526728 > 104176)
                r121[r240] = r251
                // decode failed: newPosition > limit: (296064 > 104176)
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.AnonymousClass3.<init>(com.Mode.toolbox.OutputPageActivity, float, boolean):void");
        }

        /* renamed from: ۟ۦ۟ۡۤ, reason: not valid java name and contains not printable characters */
        public static native OutputPageActivity m314(Object obj);

        /* renamed from: ۦۤ۟ۡ, reason: contains not printable characters */
        public static native int m315(Object obj);

        /* renamed from: ۦۧ, reason: contains not printable characters */
        public static native float m316(Object obj);

        /* renamed from: ۧۦۣۦ, reason: not valid java name and contains not printable characters */
        public static native boolean m317(Object obj);

        /* renamed from: ۨ۠ۡ۠, reason: not valid java name and contains not printable characters */
        public static native short[] m318();

        @Override // java.lang.Runnable
        public native void run();
    }

    /* renamed from: com.Mode.toolbox.OutputPageActivity$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f70short = null;
        final /* synthetic */ OutputPageActivity this$0;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0005: SGET r197
            java.lang.IllegalArgumentException: newPosition > limit: (255144 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0007: FILLED_NEW_ARRAY_RANGE r8418, r8419, r8420, r8421, r8422, r8423, r8424, r8425, r8426, r8427, r8428, r8429, r8430, r8431, r8432, r8433, r8434, r8435, r8436, r8437, r8438, r8439, r8440, r8441, r8442, r8443, r8444, r8445, r8446, r8447, r8448, r8449, r8450, r8451, r8452, r8453, r8454, r8455, r8456, r8457, r8458, r8459, r8460, r8461, r8462, r8463, r8464, r8465, r8466, r8467, r8468, r8469, r8470, r8471, r8472, r8473, r8474, r8475, r8476, r8477, r8478, r8479, r8480, r8481, r8482, r8483, r8484, r8485, r8486, r8487, r8488, r8489, r8490, r8491, r8492, r8493, r8494, r8495, r8496, r8497, r8498, r8499, r8500, r8501, r8502, r8503, r8504, r8505, r8506, r8507, r8508, r8509, r8510, r8511, r8512, r8513, r8514, r8515, r8516, r8517, r8518, r8519, r8520, r8521, r8522, r8523, r8524, r8525, r8526, r8527, r8528, r8529, r8530, r8531, r8532, r8533, r8534, r8535, r8536, r8537, r8538, r8539, r8540, r8541, r8542, r8543, r8544, r8545, r8546, r8547, r8548, r8549, r8550, r8551, r8552, r8553, r8554, r8555, r8556, r8557, r8558, r8559, r8560, r8561, r8562, r8563, r8564, r8565, r8566, r8567, r8568, r8569, r8570, r8571, r8572, r8573, r8574, r8575, r8576, r8577, r8578, r8579, r8580, r8581, r8582, r8583, r8584, r8585
            java.lang.IllegalArgumentException: newPosition > limit: (1074037424 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:144)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:130)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:128)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:113)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:101)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.buildUsageData(UsageInfoVisitor.java:81)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:65)
            	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:356)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x93E6)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x93E6)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        /*  JADX ERROR: Failed to decode insn: 0x0005: SGET r197
            java.lang.IllegalArgumentException: newPosition > limit: (255144 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
            	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:384)
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
        /*  JADX ERROR: Failed to decode insn: 0x0007: FILLED_NEW_ARRAY_RANGE r8418, r8419, r8420, r8421, r8422, r8423, r8424, r8425, r8426, r8427, r8428, r8429, r8430, r8431, r8432, r8433, r8434, r8435, r8436, r8437, r8438, r8439, r8440, r8441, r8442, r8443, r8444, r8445, r8446, r8447, r8448, r8449, r8450, r8451, r8452, r8453, r8454, r8455, r8456, r8457, r8458, r8459, r8460, r8461, r8462, r8463, r8464, r8465, r8466, r8467, r8468, r8469, r8470, r8471, r8472, r8473, r8474, r8475, r8476, r8477, r8478, r8479, r8480, r8481, r8482, r8483, r8484, r8485, r8486, r8487, r8488, r8489, r8490, r8491, r8492, r8493, r8494, r8495, r8496, r8497, r8498, r8499, r8500, r8501, r8502, r8503, r8504, r8505, r8506, r8507, r8508, r8509, r8510, r8511, r8512, r8513, r8514, r8515, r8516, r8517, r8518, r8519, r8520, r8521, r8522, r8523, r8524, r8525, r8526, r8527, r8528, r8529, r8530, r8531, r8532, r8533, r8534, r8535, r8536, r8537, r8538, r8539, r8540, r8541, r8542, r8543, r8544, r8545, r8546, r8547, r8548, r8549, r8550, r8551, r8552, r8553, r8554, r8555, r8556, r8557, r8558, r8559, r8560, r8561, r8562, r8563, r8564, r8565, r8566, r8567, r8568, r8569, r8570, r8571, r8572, r8573, r8574, r8575, r8576, r8577, r8578, r8579, r8580, r8581, r8582, r8583, r8584, r8585
            java.lang.IllegalArgumentException: newPosition > limit: (1074037424 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:486)
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
        AnonymousClass4(com.Mode.toolbox.OutputPageActivity r52) {
            /*
                r51 = this;
                // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x93E6)'
                long r150 = r90 << r91
                int r135 = r164 % (-93)
                // decode failed: newPosition > limit: (255144 > 104176)
                // decode failed: newPosition > limit: (1074037424 > 104176)
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.AnonymousClass4.<init>(com.Mode.toolbox.OutputPageActivity):void");
        }

        /* renamed from: ۟ۢۤۡ۟, reason: not valid java name and contains not printable characters */
        public static native short[] m319();

        /* renamed from: ۟ۥۢۥۢ, reason: not valid java name and contains not printable characters */
        public static native OutputPageActivity m320(Object obj);

        /* renamed from: ۟ۦۣۧۥ, reason: not valid java name and contains not printable characters */
        public static native int m321(Object obj);

        @Override // java.lang.Runnable
        public native void run();
    }

    class MyHandler extends Handler {
        private final WeakReference mOuter;

        /* renamed from: com.Mode.toolbox.OutputPageActivity$MyHandler$1, reason: invalid class name */
        class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ MyHandler this$0;
            final /* synthetic */ TextView val$textView;

            /*  JADX ERROR: Failed to decode insn: 0x0005: CONST_METHOD_HANDLE r247
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0005: CONST_METHOD_HANDLE r247'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            static {
                /*
                    long r13 = r13 >>> r4
                    if (r7 < 0) goto LB_7b66
                    r33[r61] = r144
                    // decode failed: Unknown instruction: '0x0005: CONST_METHOD_HANDLE r247'
                    if (r4 >= r11) goto L3d
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.MyHandler.AnonymousClass1.<clinit>():void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0006: INVOKE_SUPER_RANGE r47594, r47595, r47596, r47597, r47598, r47599, r47600, r47601, r47602, r47603, r47604, r47605, r47606
                java.lang.IllegalArgumentException: newPosition > limit: (281072 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0009: INVOKE_VIRTUAL r0, r0, r2, r14, r11, r47599, r47600, r47601, r47602, r47603
                java.lang.IllegalArgumentException: newPosition > limit: (344504 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
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
            /*  JADX ERROR: Failed to decode insn: 0x0001: UNKNOWN(0x3EF9)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0001: UNKNOWN(0x3EF9)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0002: UNKNOWN(0xAA40)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0002: UNKNOWN(0xAA40)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0006: INVOKE_SUPER_RANGE r47594, r47595, r47596, r47597, r47598, r47599, r47600, r47601, r47602, r47603, r47604, r47605, r47606
                java.lang.IllegalArgumentException: newPosition > limit: (281072 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:461)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0009: INVOKE_VIRTUAL r0, r0, r2, r14, r11, r47599, r47600, r47601, r47602, r47603
                java.lang.IllegalArgumentException: newPosition > limit: (344504 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:448)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            AnonymousClass1(com.Mode.toolbox.OutputPageActivity.MyHandler r52, android.widget.TextView r53) {
                /*
                    r51 = this;
                    r1 = r14
                    // decode failed: Unknown instruction: '0x0001: UNKNOWN(0x3EF9)'
                    // decode failed: Unknown instruction: '0x0002: UNKNOWN(0xAA40)'
                    return
                    int r6 = r6 >> r3
                    int r10 = r10 >>> r13
                    // decode failed: newPosition > limit: (281072 > 104176)
                    // decode failed: newPosition > limit: (344504 > 104176)
                    int r87 = r227 - r196
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.MyHandler.AnonymousClass1.<init>(com.Mode.toolbox.OutputPageActivity$MyHandler, android.widget.TextView):void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0000: IGET r11, r1
                java.lang.IllegalArgumentException: newPosition > limit: (336920 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0011: IPUT r3, r4
                java.lang.IllegalArgumentException: newPosition > limit: (523048 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0014: SPUT r199
                java.lang.IllegalArgumentException: newPosition > limit: (205824 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0025: FILLED_NEW_ARRAY_RANGE r40745, r40746, r40747, r40748, r40749, r40750, r40751, r40752, r40753, r40754, r40755, r40756, r40757, r40758, r40759, r40760, r40761
                java.lang.IllegalArgumentException: newPosition > limit: (151916 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:144)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:130)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:128)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:113)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:101)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.buildUsageData(UsageInfoVisitor.java:81)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:65)
                	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:356)
                */
            /*  JADX ERROR: Dependency scan failed at insn: 0x002B: SGET r141
                java.lang.IllegalArgumentException: newPosition > limit: (219336 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0038: SPUT r156
                java.lang.IllegalArgumentException: newPosition > limit: (395280 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x003E: INVOKE_DIRECT_RANGE r28038, r28039, r28040, r28041, r28042
                java.lang.IllegalArgumentException: newPosition > limit: (366616 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0041: FILLED_NEW_ARRAY_RANGE r20487, r20488, r20489, r20490, r20491, r20492, r20493, r20494, r20495, r20496, r20497, r20498, r20499, r20500, r20501, r20502, r20503, r20504, r20505, r20506, r20507, r20508, r20509, r20510, r20511, r20512, r20513, r20514, r20515, r20516, r20517, r20518, r20519, r20520, r20521, r20522, r20523, r20524, r20525, r20526, r20527, r20528, r20529, r20530, r20531, r20532, r20533, r20534, r20535, r20536, r20537, r20538, r20539, r20540, r20541, r20542, r20543, r20544, r20545, r20546, r20547, r20548, r20549, r20550, r20551, r20552, r20553, r20554, r20555, r20556, r20557, r20558, r20559, r20560, r20561, r20562
                java.lang.IllegalArgumentException: newPosition > limit: (187308 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:144)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:130)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:128)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:113)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:101)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.buildUsageData(UsageInfoVisitor.java:81)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:65)
                	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:356)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0000: IGET r11, r1
                java.lang.IllegalArgumentException: newPosition > limit: (336920 > 104176)
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
                */
            /*  JADX ERROR: Failed to decode insn: 0x0004: UNKNOWN(0xDAF3)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0004: UNKNOWN(0xDAF3)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0009: UNKNOWN(0x257A)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0009: UNKNOWN(0x257A)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0011: IPUT r3, r4
                java.lang.IllegalArgumentException: newPosition > limit: (523048 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
                	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:377)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0014: SPUT r199
                java.lang.IllegalArgumentException: newPosition > limit: (205824 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
                	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:390)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0025: FILLED_NEW_ARRAY_RANGE r40745, r40746, r40747, r40748, r40749, r40750, r40751, r40752, r40753, r40754, r40755, r40756, r40757, r40758, r40759, r40760, r40761
                java.lang.IllegalArgumentException: newPosition > limit: (151916 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:486)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x002B: SGET r141
                java.lang.IllegalArgumentException: newPosition > limit: (219336 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
                	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:384)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0032: UNKNOWN(0x6B73)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0032: UNKNOWN(0x6B73)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0038: SPUT r156
                java.lang.IllegalArgumentException: newPosition > limit: (395280 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
                	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:390)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x003E: INVOKE_DIRECT_RANGE r28038, r28039, r28040, r28041, r28042
                java.lang.IllegalArgumentException: newPosition > limit: (366616 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:457)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0041: FILLED_NEW_ARRAY_RANGE r20487, r20488, r20489, r20490, r20491, r20492, r20493, r20494, r20495, r20496, r20497, r20498, r20499, r20500, r20501, r20502, r20503, r20504, r20505, r20506, r20507, r20508, r20509, r20510, r20511, r20512, r20513, r20514, r20515, r20516, r20517, r20518, r20519, r20520, r20521, r20522, r20523, r20524, r20525, r20526, r20527, r20528, r20529, r20530, r20531, r20532, r20533, r20534, r20535, r20536, r20537, r20538, r20539, r20540, r20541, r20542, r20543, r20544, r20545, r20546, r20547, r20548, r20549, r20550, r20551, r20552, r20553, r20554, r20555, r20556, r20557, r20558, r20559, r20560, r20561, r20562
                java.lang.IllegalArgumentException: newPosition > limit: (187308 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:486)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            private static /* synthetic */ void lmYmk() {
                /*
                    // decode failed: newPosition > limit: (336920 > 104176)
                    int r1 = r0 * (-28112)
                    // decode failed: Unknown instruction: '0x0004: UNKNOWN(0xDAF3)'
                    long r62 = r141 >> r145
                    long r199 = r145 << r157
                    // decode failed: Unknown instruction: '0x0009: UNKNOWN(0x257A)'
                    float r34 = r152 + r223
                    long r90 = r179 >>> r68
                    int r4 = r8 * (-31800)
                    double r9 = (double) r14
                    // decode failed: newPosition > limit: (523048 > 104176)
                    float r14 = (float) r15
                    // decode failed: newPosition > limit: (205824 > 104176)
                    return r17
                    char r1 = (char) r8
                    r252 = 360065093(0x15762845, float:4.971107E-26)
                    long r6 = r6 ^ r7
                    int r62 = r221 >> r80
                    goto L24
                    r123[r239] = r122
                    r106 = r155 | r146
                    int r5 = r5 / r10
                    r5 = r3
                    // decode failed: newPosition > limit: (151916 > 104176)
                    long r43 = r4 | r55
                    r189 = move-exception
                    // decode failed: newPosition > limit: (219336 > 104176)
                    int r14 = r14 << r10
                    if (r0 >= r13) goto L4e48
                    if (r7 != r6) goto LB_3c8
                    // decode failed: Unknown instruction: '0x0032: UNKNOWN(0x6B73)'
                    short r64 = r6[r121]
                    r18 = r19 & r82
                    long r9 = (long) r5
                    // decode failed: newPosition > limit: (395280 > 104176)
                    int r2 = r2 >> r15
                    goto L91
                    r3 = r7 ^ (-112(0xffffffffffffff90, float:NaN))
                    // decode failed: newPosition > limit: (366616 > 104176)
                    // decode failed: newPosition > limit: (187308 > 104176)
                    int r14 = r119 * r180
                    goto L679c6b7a
                    long r3 = -r6
                    r0[r0] = r59
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.MyHandler.AnonymousClass1.lmYmk():void");
            }

            /* renamed from: ۠ۨۨۨ, reason: not valid java name and contains not printable characters */
            public static native TextView m323(Object obj);

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public native boolean onPreDraw();
        }

        /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0xE2E4)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0xE2E4)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        static {
            /*
                // decode failed: Unknown instruction: '0x0000: UNKNOWN(0xE2E4)'
                long r12 = r12 % r15
                long r56 = r107 & r51
                switch(r180) {
                // error: 0x0004: SWITCH (r180 I:??)no payload
                long r13 = r13 % r13
                com.Mode.toolbox.OutputPageActivity.super.<init>()
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.MyHandler.<clinit>():void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0004: SPUT r69
            java.lang.IllegalArgumentException: newPosition > limit: (169324 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x61F5)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x61F5)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        /*  JADX ERROR: Failed to decode insn: 0x0004: SPUT r69
            java.lang.IllegalArgumentException: newPosition > limit: (169324 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
            	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:390)
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
        /*  JADX ERROR: Failed to decode insn: 0x0006: UNKNOWN(0x7B43)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0006: UNKNOWN(0x7B43)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        /*  JADX ERROR: Failed to decode insn: 0x0008: UNKNOWN(0xAFEA)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0008: UNKNOWN(0xAFEA)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        public MyHandler(com.Mode.toolbox.OutputPageActivity r53) {
            /*
                r52 = this;
                // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x61F5)'
                short r228 = r39[r158]
                double r4 = (double) r1
                // decode failed: newPosition > limit: (169324 > 104176)
                // decode failed: Unknown instruction: '0x0006: UNKNOWN(0x7B43)'
                int r6 = ~r11
                // decode failed: Unknown instruction: '0x0008: UNKNOWN(0xAFEA)'
                float r2 = r2 / r8
                char r14 = (char) r15
                if (r60 > 0) goto L22ff
                double r176 = r204 * r129
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.MyHandler.<init>(com.Mode.toolbox.OutputPageActivity):void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0002: SGET r46
            java.lang.IllegalArgumentException: newPosition < 0: (-1114820356 < 0)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:195)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x001D: INVOKE_CUSTOM_RANGE r31245, r31246, r31247, r31248, r31249, r31250, r31251, r31252, r31253, r31254, r31255, r31256, r31257, r31258, r31259, r31260, r31261, r31262, r31263, r31264, r31265, r31266, r31267, r31268, r31269, r31270, r31271, r31272, r31273, r31274, r31275, r31276, r31277, r31278, r31279, r31280, r31281, r31282, r31283, r31284, r31285, r31286, r31287, r31288, r31289, r31290, r31291, r31292, r31293, r31294, r31295, r31296, r31297, r31298, r31299, r31300, r31301, r31302, r31303, r31304, r31305, r31306, r31307, r31308, r31309, r31310, r31311, r31312, r31313, r31314, r31315, r31316, r31317, r31318, r31319, r31320, r31321, r31322, r31323, r31324, r31325, r31326, r31327, r31328, r31329, r31330, r31331, r31332, r31333, r31334, r31335, r31336, r31337, r31338, r31339, r31340, r31341, r31342, r31343, r31344, r31345, r31346, r31347, r31348, r31349, r31350, r31351, r31352, r31353, r31354, r31355, r31356, r31357, r31358, r31359, r31360, r31361, r31362, r31363, r31364, r31365, r31366, r31367, r31368, r31369, r31370, r31371, r31372, r31373, r31374, r31375, r31376, r31377, r31378, r31379, r31380, r31381, r31382, r31383, r31384, r31385, r31386, r31387, r31388, r31389, r31390, r31391, r31392, r31393, r31394, r31395, r31396, r31397, r31398, r31399, r31400, r31401, r31402, r31403, r31404, r31405, r31406, r31407, r31408, r31409, r31410, r31411, r31412, r31413, r31414, r31415, r31416, r31417, r31418, r31419, r31420, r31421, r31422, r31423, r31424, r31425, r31426, r31427
            java.lang.IllegalArgumentException: newPosition > limit: (462591101 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:208)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:174)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:130)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:128)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:113)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:101)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.buildUsageData(UsageInfoVisitor.java:81)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:65)
            	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:356)
            */
        /*  JADX ERROR: Dependency scan failed at insn: 0x0025: IPUT r11, r12
            java.lang.IllegalArgumentException: newPosition > limit: (367248 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0033: INVOKE_CUSTOM_RANGE r4981, r4982, r4983, r4984, r4985, r4986, r4987, r4988, r4989, r4990, r4991, r4992, r4993, r4994, r4995, r4996, r4997, r4998, r4999, r5000, r5001, r5002, r5003, r5004, r5005, r5006, r5007, r5008, r5009, r5010, r5011, r5012, r5013, r5014, r5015, r5016, r5017, r5018, r5019, r5020, r5021, r5022, r5023, r5024, r5025, r5026, r5027, r5028, r5029, r5030, r5031, r5032, r5033, r5034, r5035, r5036, r5037, r5038, r5039, r5040, r5041, r5042, r5043, r5044, r5045, r5046, r5047, r5048, r5049, r5050, r5051, r5052, r5053, r5054, r5055, r5056, r5057, r5058, r5059, r5060, r5061, r5062, r5063, r5064, r5065, r5066, r5067, r5068, r5069, r5070, r5071, r5072, r5073, r5074, r5075, r5076, r5077, r5078, r5079, r5080, r5081, r5082, r5083, r5084, r5085, r5086, r5087, r5088, r5089, r5090, r5091, r5092, r5093, r5094, r5095, r5096, r5097, r5098, r5099, r5100, r5101, r5102, r5103, r5104, r5105, r5106, r5107, r5108, r5109, r5110, r5111, r5112, r5113, r5114, r5115, r5116, r5117, r5118, r5119, r5120, r5121, r5122, r5123, r5124, r5125, r5126, r5127, r5128, r5129, r5130, r5131, r5132, r5133, r5134, r5135, r5136, r5137, r5138, r5139, r5140, r5141, r5142, r5143, r5144, r5145, r5146, r5147, r5148, r5149, r5150, r5151, r5152, r5153, r5154, r5155, r5156, r5157, r5158, r5159, r5160, r5161, r5162, r5163, r5164, r5165, r5166, r5167, r5168, r5169, r5170, r5171, r5172, r5173, r5174, r5175
            java.lang.IllegalArgumentException: newPosition < 0: (-802162664 < 0)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:208)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:174)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:130)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:128)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:113)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:101)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.buildUsageData(UsageInfoVisitor.java:81)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:65)
            	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:356)
            */
        /*  JADX ERROR: Dependency scan failed at insn: 0x0036: IGET r14, r6
            java.lang.IllegalArgumentException: newPosition > limit: (177048 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x003F: NEW_INSTANCE r227
            java.lang.IllegalArgumentException: newPosition > limit: (67111540 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:144)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:130)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:128)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:113)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:101)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.buildUsageData(UsageInfoVisitor.java:81)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:65)
            	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:356)
            */
        /*  JADX ERROR: Dependency scan failed at insn: 0x0041: SGET r189
            java.lang.IllegalArgumentException: newPosition > limit: (208824 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0002: SGET r46
            java.lang.IllegalArgumentException: newPosition < 0: (-1114820356 < 0)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:195)
            	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:384)
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
        /*  JADX ERROR: Failed to decode insn: 0x000B: UNKNOWN(0x92E3)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000B: UNKNOWN(0x92E3)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        /*  JADX ERROR: Failed to decode insn: 0x0016: UNKNOWN(0xBFF7)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0016: UNKNOWN(0xBFF7)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        /*  JADX ERROR: Failed to decode insn: 0x001D: INVOKE_CUSTOM_RANGE r31245, r31246, r31247, r31248, r31249, r31250, r31251, r31252, r31253, r31254, r31255, r31256, r31257, r31258, r31259, r31260, r31261, r31262, r31263, r31264, r31265, r31266, r31267, r31268, r31269, r31270, r31271, r31272, r31273, r31274, r31275, r31276, r31277, r31278, r31279, r31280, r31281, r31282, r31283, r31284, r31285, r31286, r31287, r31288, r31289, r31290, r31291, r31292, r31293, r31294, r31295, r31296, r31297, r31298, r31299, r31300, r31301, r31302, r31303, r31304, r31305, r31306, r31307, r31308, r31309, r31310, r31311, r31312, r31313, r31314, r31315, r31316, r31317, r31318, r31319, r31320, r31321, r31322, r31323, r31324, r31325, r31326, r31327, r31328, r31329, r31330, r31331, r31332, r31333, r31334, r31335, r31336, r31337, r31338, r31339, r31340, r31341, r31342, r31343, r31344, r31345, r31346, r31347, r31348, r31349, r31350, r31351, r31352, r31353, r31354, r31355, r31356, r31357, r31358, r31359, r31360, r31361, r31362, r31363, r31364, r31365, r31366, r31367, r31368, r31369, r31370, r31371, r31372, r31373, r31374, r31375, r31376, r31377, r31378, r31379, r31380, r31381, r31382, r31383, r31384, r31385, r31386, r31387, r31388, r31389, r31390, r31391, r31392, r31393, r31394, r31395, r31396, r31397, r31398, r31399, r31400, r31401, r31402, r31403, r31404, r31405, r31406, r31407, r31408, r31409, r31410, r31411, r31412, r31413, r31414, r31415, r31416, r31417, r31418, r31419, r31420, r31421, r31422, r31423, r31424, r31425, r31426, r31427
            jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: newPosition > limit: (462591101 > 104176)
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:597)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:465)
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
            Caused by: java.lang.IllegalArgumentException: newPosition > limit: (462591101 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:208)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
            	... 13 more
            */
        /*  JADX ERROR: Failed to decode insn: 0x0020: UNKNOWN(0x1BE5)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0020: UNKNOWN(0x1BE5)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        /*  JADX ERROR: Failed to decode insn: 0x0022: UNKNOWN(0xBAE4)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0022: UNKNOWN(0xBAE4)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        /*  JADX ERROR: Failed to decode insn: 0x0025: IPUT r11, r12
            java.lang.IllegalArgumentException: newPosition > limit: (367248 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
            	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:377)
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
        /*  JADX ERROR: Failed to decode insn: 0x002A: UNKNOWN(0xA2ED)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x002A: UNKNOWN(0xA2ED)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        /*  JADX ERROR: Failed to decode insn: 0x0033: INVOKE_CUSTOM_RANGE r4981, r4982, r4983, r4984, r4985, r4986, r4987, r4988, r4989, r4990, r4991, r4992, r4993, r4994, r4995, r4996, r4997, r4998, r4999, r5000, r5001, r5002, r5003, r5004, r5005, r5006, r5007, r5008, r5009, r5010, r5011, r5012, r5013, r5014, r5015, r5016, r5017, r5018, r5019, r5020, r5021, r5022, r5023, r5024, r5025, r5026, r5027, r5028, r5029, r5030, r5031, r5032, r5033, r5034, r5035, r5036, r5037, r5038, r5039, r5040, r5041, r5042, r5043, r5044, r5045, r5046, r5047, r5048, r5049, r5050, r5051, r5052, r5053, r5054, r5055, r5056, r5057, r5058, r5059, r5060, r5061, r5062, r5063, r5064, r5065, r5066, r5067, r5068, r5069, r5070, r5071, r5072, r5073, r5074, r5075, r5076, r5077, r5078, r5079, r5080, r5081, r5082, r5083, r5084, r5085, r5086, r5087, r5088, r5089, r5090, r5091, r5092, r5093, r5094, r5095, r5096, r5097, r5098, r5099, r5100, r5101, r5102, r5103, r5104, r5105, r5106, r5107, r5108, r5109, r5110, r5111, r5112, r5113, r5114, r5115, r5116, r5117, r5118, r5119, r5120, r5121, r5122, r5123, r5124, r5125, r5126, r5127, r5128, r5129, r5130, r5131, r5132, r5133, r5134, r5135, r5136, r5137, r5138, r5139, r5140, r5141, r5142, r5143, r5144, r5145, r5146, r5147, r5148, r5149, r5150, r5151, r5152, r5153, r5154, r5155, r5156, r5157, r5158, r5159, r5160, r5161, r5162, r5163, r5164, r5165, r5166, r5167, r5168, r5169, r5170, r5171, r5172, r5173, r5174, r5175
            jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: newPosition < 0: (-802162664 < 0)
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:597)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:465)
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
            Caused by: java.lang.IllegalArgumentException: newPosition < 0: (-802162664 < 0)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:208)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
            	... 13 more
            */
        /*  JADX ERROR: Failed to decode insn: 0x0036: IGET r14, r6
            java.lang.IllegalArgumentException: newPosition > limit: (177048 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x003F: NEW_INSTANCE r227
            java.lang.IllegalArgumentException: newPosition > limit: (67111540 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:470)
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
        /*  JADX ERROR: Failed to decode insn: 0x0041: SGET r189
            java.lang.IllegalArgumentException: newPosition > limit: (208824 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
            	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:384)
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
        /*  JADX ERROR: Failed to decode insn: 0x0043: CONST_METHOD_TYPE r226
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0043: CONST_METHOD_TYPE r226'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        /*  JADX ERROR: Failed to decode insn: 0x0048: UNKNOWN(0xDCF5)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0048: UNKNOWN(0xDCF5)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
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
        private static /* synthetic */ void KipGrN() {
            /*
                r32 = -7536773976404525056(0x9768000000000000, double:-6.421316452173056E-196)
                // decode failed: newPosition < 0: (-1114820356 < 0)
                int r246 = r104 >>> r23
                long r10 = r10 * r15
                double r7 = r7 * r3
                double r8 = (double) r0
                int r28 = r55 >> 120
                // decode failed: Unknown instruction: '0x000B: UNKNOWN(0x92E3)'
                long r1 = r1 & r11
                float r5 = -r12
                long r9 = r9 >>> r8
                if (r5 <= r8) goto LB_6483
                long r225 = r118 >> r14
                int r127 = r19 << r126
                float r8 = r8 + r1
                // decode failed: Unknown instruction: '0x0016: UNKNOWN(0xBFF7)'
                int r106 = r103 / r225
                double r7 = (double) r9
                r231 = r90 & (-46)
                long r2 = r2 % r8
                // decode failed: 'invoke-custom' instruction processing error: newPosition > limit: (462591101 > 104176)
                // decode failed: Unknown instruction: '0x0020: UNKNOWN(0x1BE5)'
                double r4 = r4 + r12
                // decode failed: Unknown instruction: '0x0022: UNKNOWN(0xBAE4)'
                double r152 = r226 % r77
                // decode failed: newPosition > limit: (367248 > 104176)
                double r8 = (double) r8
                r0 = r14
                monitor-exit(r123)
                // decode failed: Unknown instruction: '0x002A: UNKNOWN(0xA2ED)'
                com.Mode.toolbox.LandscapeHelper r222 = new com.Mode.toolbox.LandscapeHelper
                r1920 = r25153
                double r12 = r12 / r13
                r9 = r15 & 27275(0x6a8b, float:3.822E-41)
                // decode failed: 'invoke-custom' instruction processing error: newPosition < 0: (-802162664 < 0)
                // decode failed: newPosition > limit: (177048 > 104176)
                r6547 = r62408
                return r221
                r6 = r6 & r6
                float r5 = r5 / r1
                int r6 = r6 - r7
                // decode failed: newPosition > limit: (67111540 > 104176)
                // decode failed: newPosition > limit: (208824 > 104176)
                // decode failed: Unknown instruction: '0x0043: CONST_METHOD_TYPE r226'
                double r1 = r1 % r6
                r2 = -7
                long r0 = r0 >>> r12
                // decode failed: Unknown instruction: '0x0048: UNKNOWN(0xDCF5)'
                long r11 = r11 ^ r9
                int r13 = r13 % r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.MyHandler.KipGrN():void");
        }

        /* renamed from: ۦ۠ۦ۠, reason: contains not printable characters */
        public static native WeakReference m322(Object obj);

        @Override // android.os.Handler
        public native void handleMessage(Message message);
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0001: IGET r0, r13
        java.lang.IllegalArgumentException: newPosition > limit: (180728 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x000B: IGET r1, r6
        java.lang.IllegalArgumentException: newPosition > limit: (526632 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0012: INVOKE_DIRECT_RANGE r53857, r53858, r53859, r53860, r53861, r53862, r53863, r53864, r53865, r53866, r53867, r53868, r53869, r53870, r53871, r53872, r53873, r53874, r53875, r53876, r53877, r53878, r53879, r53880, r53881, r53882, r53883, r53884, r53885, r53886, r53887, r53888, r53889, r53890, r53891, r53892, r53893, r53894, r53895, r53896, r53897, r53898, r53899, r53900, r53901, r53902, r53903, r53904, r53905, r53906, r53907, r53908, r53909, r53910, r53911, r53912, r53913, r53914, r53915, r53916, r53917, r53918, r53919, r53920, r53921, r53922, r53923, r53924, r53925, r53926, r53927, r53928, r53929, r53930, r53931, r53932, r53933, r53934, r53935, r53936, r53937, r53938, r53939, r53940, r53941, r53942, r53943, r53944, r53945, r53946, r53947, r53948, r53949, r53950, r53951, r53952, r53953, r53954, r53955, r53956, r53957, r53958, r53959, r53960, r53961, r53962, r53963, r53964, r53965, r53966, r53967, r53968, r53969, r53970, r53971, r53972, r53973, r53974, r53975, r53976, r53977, r53978, r53979, r53980, r53981, r53982, r53983, r53984, r53985, r53986, r53987, r53988, r53989, r53990, r53991, r53992, r53993, r53994, r53995, r53996, r53997, r53998, r53999, r54000, r54001, r54002, r54003, r54004, r54005, r54006, r54007, r54008, r54009, r54010, r54011, r54012, r54013, r54014, r54015, r54016, r54017, r54018, r54019, r54020, r54021, r54022, r54023, r54024, r54025, r54026, r54027, r54028, r54029, r54030, r54031, r54032, r54033, r54034, r54035, r54036, r54037, r54038, r54039, r54040, r54041, r54042, r54043, r54044, r54045, r54046, r54047, r54048, r54049, r54050, r54051, r54052, r54053, r54054, r54055, r54056, r54057, r54058, r54059, r54060, r54061, r54062, r54063, r54064, r54065, r54066, r54067, r54068, r54069, r54070, r54071, r54072, r54073, r54074, r54075, r54076, r54077, r54078, r54079, r54080, r54081, r54082, r54083
        java.lang.IllegalArgumentException: newPosition > limit: (500080 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0015: IPUT r9, r12
        java.lang.IllegalArgumentException: newPosition < 0: (-1001293540 < 0)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x002C: IGET r15, r14
        java.lang.IllegalArgumentException: newPosition > limit: (33555024 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0030: IGET r1, r1
        java.lang.IllegalArgumentException: newPosition > limit: (200264 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0034: SGET r66
        java.lang.IllegalArgumentException: newPosition > limit: (451784 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0036: IGET r2, r11
        java.lang.IllegalArgumentException: newPosition > limit: (176492 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
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
    /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0xC043)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0xC043)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0001: IGET r0, r13
        java.lang.IllegalArgumentException: newPosition > limit: (180728 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
        	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:370)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0007: UNKNOWN(0x8DF7)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0007: UNKNOWN(0x8DF7)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x000B: IGET r1, r6
        java.lang.IllegalArgumentException: newPosition > limit: (526632 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x000F: UNKNOWN(0xFE3E)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000F: UNKNOWN(0xFE3E)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0012: INVOKE_DIRECT_RANGE r53857, r53858, r53859, r53860, r53861, r53862, r53863, r53864, r53865, r53866, r53867, r53868, r53869, r53870, r53871, r53872, r53873, r53874, r53875, r53876, r53877, r53878, r53879, r53880, r53881, r53882, r53883, r53884, r53885, r53886, r53887, r53888, r53889, r53890, r53891, r53892, r53893, r53894, r53895, r53896, r53897, r53898, r53899, r53900, r53901, r53902, r53903, r53904, r53905, r53906, r53907, r53908, r53909, r53910, r53911, r53912, r53913, r53914, r53915, r53916, r53917, r53918, r53919, r53920, r53921, r53922, r53923, r53924, r53925, r53926, r53927, r53928, r53929, r53930, r53931, r53932, r53933, r53934, r53935, r53936, r53937, r53938, r53939, r53940, r53941, r53942, r53943, r53944, r53945, r53946, r53947, r53948, r53949, r53950, r53951, r53952, r53953, r53954, r53955, r53956, r53957, r53958, r53959, r53960, r53961, r53962, r53963, r53964, r53965, r53966, r53967, r53968, r53969, r53970, r53971, r53972, r53973, r53974, r53975, r53976, r53977, r53978, r53979, r53980, r53981, r53982, r53983, r53984, r53985, r53986, r53987, r53988, r53989, r53990, r53991, r53992, r53993, r53994, r53995, r53996, r53997, r53998, r53999, r54000, r54001, r54002, r54003, r54004, r54005, r54006, r54007, r54008, r54009, r54010, r54011, r54012, r54013, r54014, r54015, r54016, r54017, r54018, r54019, r54020, r54021, r54022, r54023, r54024, r54025, r54026, r54027, r54028, r54029, r54030, r54031, r54032, r54033, r54034, r54035, r54036, r54037, r54038, r54039, r54040, r54041, r54042, r54043, r54044, r54045, r54046, r54047, r54048, r54049, r54050, r54051, r54052, r54053, r54054, r54055, r54056, r54057, r54058, r54059, r54060, r54061, r54062, r54063, r54064, r54065, r54066, r54067, r54068, r54069, r54070, r54071, r54072, r54073, r54074, r54075, r54076, r54077, r54078, r54079, r54080, r54081, r54082, r54083
        java.lang.IllegalArgumentException: newPosition > limit: (500080 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:457)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0015: IPUT r9, r12
        java.lang.IllegalArgumentException: newPosition < 0: (-1001293540 < 0)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
        	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:377)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x001A: CONST_STRING r59
        java.lang.IllegalArgumentException: newPosition > limit: (2060877392 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:81)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x001D: UNKNOWN(0x3DF1)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001D: UNKNOWN(0x3DF1)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0029: UNKNOWN(0x28E5)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0029: UNKNOWN(0x28E5)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x002C: IGET r15, r14
        java.lang.IllegalArgumentException: newPosition > limit: (33555024 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
        	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:370)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0030: IGET r1, r1
        java.lang.IllegalArgumentException: newPosition > limit: (200264 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0034: SGET r66
        java.lang.IllegalArgumentException: newPosition > limit: (451784 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:190)
        	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:384)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0036: IGET r2, r11
        java.lang.IllegalArgumentException: newPosition > limit: (176492 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
        	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:370)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    static {
        /*
            // decode failed: Unknown instruction: '0x0000: UNKNOWN(0xC043)'
            // decode failed: newPosition > limit: (180728 > 104176)
            int r129 = r22 >>> r148
            int r6 = r10 / r135
            // decode failed: Unknown instruction: '0x0007: UNKNOWN(0x8DF7)'
            if (r85 > 0) goto LB_353f
            r223 = move-result
            // decode failed: newPosition > limit: (526632 > 104176)
            double r191 = r71 - r239
            // decode failed: Unknown instruction: '0x000F: UNKNOWN(0xFE3E)'
            r105[r172] = r55
            // decode failed: newPosition > limit: (500080 > 104176)
            // decode failed: newPosition < 0: (-1001293540 < 0)
            r155 = move-result
            long r66 = r195 * r186
            // decode failed: newPosition > limit: (2060877392 > 104176)
            // decode failed: Unknown instruction: '0x001D: UNKNOWN(0x3DF1)'
            float r139 = r12 - r169
            boolean r9 = r248[r154]
            double r4 = r4 % r12
            r211 = r83 | r224
            int r246 = r131 >>> r110
            double r165 = r194 / r109
            // decode failed: Unknown instruction: '0x0029: UNKNOWN(0x28E5)'
            long r53 = r50 / r204
            // decode failed: newPosition > limit: (33555024 > 104176)
            if (r211 == 0) goto L664e
            // decode failed: newPosition > limit: (200264 > 104176)
            r41 = r206 & 51
            // decode failed: newPosition > limit: (451784 > 104176)
            // decode failed: newPosition > limit: (176492 > 104176)
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.<clinit>():void");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0009: SGET r167
        java.lang.IllegalArgumentException: newPosition > limit: (164204 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
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
    /*  JADX ERROR: Failed to decode insn: 0x0009: SGET r167
        java.lang.IllegalArgumentException: newPosition > limit: (164204 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
        	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:384)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    public OutputPageActivity() {
        /*
            r52 = this;
            r190 = r119 | (-81)
            long r12 = r12 / r7
            r14 = r2
            long r5 = r5 | r0
            int r92 = (r205 > r70 ? 1 : (r205 == r70 ? 0 : -1))
            double r8 = r8 * r7
            long r2 = (long) r13
            // decode failed: newPosition > limit: (164204 > 104176)
            int r14 = r5.length
            r53.<init>()
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.<init>():void");
    }

    /* renamed from: ۟ۤۨۧ۠, reason: not valid java name and contains not printable characters */
    public static native int m301(Object obj);

    /* renamed from: ۦ۠ۥۧ, reason: contains not printable characters */
    public static native String m302(Object obj);

    /* renamed from: ۨۤۢۤ, reason: not valid java name and contains not printable characters */
    public static native short[] m303();

    public native void Back(View view);

    public native void CopyOutput(View view);

    public native void ShizukuExec(String str);

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.OutputPageActivity.lambda$exec$0$com-Mode-toolbox-OutputActivity(java.lang.String):void, file: /workspace/xh/fix_3950620.dex
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
        	at jadx.plugins.input.dex.insns.DexInsnData.toString(DexInsnData.java:251)
        	at java.base/java.lang.String.valueOf(String.java:4530)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	... 6 more
        */
    /* renamed from: lambda$exec$0$com-Mode-toolbox-OutputActivity */
    /* synthetic */ void m304lambda$exec$0$comModetoolboxOutputActivity(java.lang.String r1) {
        /*
        // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.OutputPageActivity.lambda$exec$0$com-Mode-toolbox-OutputActivity(java.lang.String):void, file: /workspace/xh/fix_3950620.dex
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity.m304lambda$exec$0$comModetoolboxOutputActivity(java.lang.String):void");
    }

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    @Override // android.app.Activity
    protected native void onDestroy();
}
