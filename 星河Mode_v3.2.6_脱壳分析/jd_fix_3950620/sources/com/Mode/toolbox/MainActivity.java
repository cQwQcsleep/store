package com.Mode.toolbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import rikka.shizuku.Shizuku;

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
public class MainActivity extends Activity {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f26short = null;
    private final Shizuku.OnRequestPermissionResultListener RL;
    private TextView ShizukuStatusDetection;
    boolean b;
    boolean br;
    boolean c;
    protected MyHandler mHandler;
    public Process p;
    private SharedPreferences prefs;
    public TextView t1;

    /* renamed from: com.Mode.toolbox.MainActivity$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f28short = null;
        final /* synthetic */ MainActivity this$0;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.1.<init>(com.Mode.toolbox.MainActivity):void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
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
            	... 7 more
            */
        AnonymousClass1(com.Mode.toolbox.MainActivity r1) {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.1.<init>(com.Mode.toolbox.MainActivity):void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass1.<init>(com.Mode.toolbox.MainActivity):void");
        }

        /* renamed from: ۟ۡۡۧۢ, reason: not valid java name and contains not printable characters */
        public static native MainActivity m140(Object obj);

        /* renamed from: ۟ۦۡۥۡ, reason: not valid java name and contains not printable characters */
        public static native short[] m141();

        /* renamed from: ۨ۠ۡۧ, reason: not valid java name and contains not printable characters */
        public static native MyHandler m142(Object obj);

        @Override // java.lang.Runnable
        public native void run();
    }

    /* renamed from: com.Mode.toolbox.MainActivity$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f29short = null;
        final /* synthetic */ MainActivity this$0;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.2.<init>(com.Mode.toolbox.MainActivity):void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
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
            	... 7 more
            */
        AnonymousClass2(com.Mode.toolbox.MainActivity r1) {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.2.<init>(com.Mode.toolbox.MainActivity):void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass2.<init>(com.Mode.toolbox.MainActivity):void");
        }

        /* renamed from: ۟ۤۨۧۤ, reason: not valid java name and contains not printable characters */
        public static native short[] m143();

        /* renamed from: ۟ۦ۟۠۠, reason: not valid java name and contains not printable characters */
        public static native MyHandler m144(Object obj);

        /* renamed from: ۟ۧ۠ۧۥ, reason: not valid java name and contains not printable characters */
        public static native MainActivity m145(Object obj);

        @Override // java.lang.Runnable
        public native void run();
    }

    /* renamed from: com.Mode.toolbox.MainActivity$3, reason: invalid class name */
    class AnonymousClass3 implements DialogInterface.OnClickListener {
        final /* synthetic */ MainActivity this$0;

        /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
            jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x0000: ARITH (r217 I:??[int, boolean]) = (r233 I:??[int, boolean, short, byte, char]) ^ (127(0x7f, float:1.78E-43) ??[int, float, short, byte, char]), expected to be less than 52
            	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:79)
            	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
            */
        static {
            /*
                r217 = r233 ^ 127(0x7f, float:1.78E-43)
                int r44 = r90 >>> (-16)
                r195 = r231 ^ (-10)
                if (r45 == 0) goto L2b79
                int r60 = r0 * r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass3.<clinit>():void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0005: SGET r81
            java.lang.IllegalArgumentException: newPosition > limit: (157152 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0004: UNKNOWN(0x17F7)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0004: UNKNOWN(0x17F7)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0005: SGET r81
            java.lang.IllegalArgumentException: newPosition > limit: (157152 > 104176)
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
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0007: UNKNOWN(0xA7E7)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0007: UNKNOWN(0xA7E7)'
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
        AnonymousClass3(com.Mode.toolbox.MainActivity r52) {
            /*
                r51 = this;
                if (r5 >= r5) goto L2264
                float r191 = r24 * r78
                // decode failed: Unknown instruction: '0x0004: UNKNOWN(0x17F7)'
                // decode failed: newPosition > limit: (157152 > 104176)
                // decode failed: Unknown instruction: '0x0007: UNKNOWN(0xA7E7)'
                long r10 = r10 >>> r10
                r7 = r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass3.<init>(com.Mode.toolbox.MainActivity):void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0000: SGET r108
            java.lang.IllegalArgumentException: newPosition > limit: (382304 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0002: IPUT r8, r8
            java.lang.IllegalArgumentException: newPosition > limit: (478368 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x000F: IPUT r4, r8
            java.lang.IllegalArgumentException: newPosition > limit: (145400 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0017: INSTANCE_OF r0, r0
            java.lang.IllegalArgumentException: newPosition > limit: (154080 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0020: INVOKE_SUPER_RANGE r38162, r38163, r38164, r38165, r38166, r38167, r38168, r38169, r38170, r38171, r38172, r38173, r38174, r38175, r38176, r38177, r38178, r38179, r38180, r38181, r38182, r38183, r38184, r38185, r38186, r38187, r38188, r38189, r38190, r38191, r38192, r38193, r38194, r38195, r38196, r38197, r38198, r38199, r38200, r38201, r38202, r38203, r38204, r38205, r38206, r38207, r38208, r38209, r38210, r38211, r38212, r38213, r38214, r38215, r38216, r38217, r38218, r38219, r38220, r38221, r38222, r38223, r38224, r38225, r38226, r38227, r38228, r38229, r38230, r38231, r38232, r38233, r38234, r38235, r38236, r38237, r38238, r38239, r38240, r38241, r38242, r38243, r38244, r38245, r38246, r38247, r38248, r38249, r38250, r38251, r38252, r38253, r38254, r38255, r38256, r38257, r38258, r38259, r38260, r38261, r38262, r38263, r38264, r38265, r38266, r38267, r38268, r38269, r38270, r38271, r38272, r38273, r38274, r38275, r38276, r38277, r38278, r38279, r38280, r38281, r38282, r38283, r38284, r38285, r38286, r38287, r38288, r38289, r38290, r38291, r38292, r38293, r38294, r38295, r38296, r38297, r38298, r38299, r38300, r38301, r38302, r38303, r38304, r38305, r38306, r38307, r38308, r38309, r38310, r38311, r38312, r38313, r38314, r38315, r38316, r38317, r38318, r38319, r38320, r38321, r38322, r38323, r38324, r38325, r38326, r38327, r38328, r38329, r38330, r38331, r38332, r38333, r38334, r38335, r38336, r38337, r38338, r38339, r38340, r38341, r38342, r38343, r38344, r38345, r38346, r38347, r38348, r38349, r38350, r38351
            java.lang.IllegalArgumentException: newPosition > limit: (503796 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0023: IGET r11, r13
            java.lang.IllegalArgumentException: newPosition > limit: (156288 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0036: SGET r221
            java.lang.IllegalArgumentException: newPosition > limit: (305936 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0047: INVOKE_STATIC_RANGE r2208, r2209, r2210, r2211, r2212, r2213, r2214, r2215, r2216, r2217, r2218, r2219, r2220, r2221, r2222, r2223, r2224, r2225, r2226, r2227, r2228, r2229, r2230, r2231, r2232, r2233, r2234, r2235
            java.lang.IllegalArgumentException: newPosition > limit: (457160 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: SGET r108
            java.lang.IllegalArgumentException: newPosition > limit: (382304 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0002: IPUT r8, r8
            java.lang.IllegalArgumentException: newPosition > limit: (478368 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0008: UNKNOWN(0x7CF4)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0008: UNKNOWN(0x7CF4)'
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
        /*  JADX ERROR: Failed to decode insn: 0x000E: UNKNOWN(0x08EE)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000E: UNKNOWN(0x08EE)'
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
        /*  JADX ERROR: Failed to decode insn: 0x000F: IPUT r4, r8
            java.lang.IllegalArgumentException: newPosition > limit: (145400 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0011: UNKNOWN(0x45EC)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0011: UNKNOWN(0x45EC)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0014: CONST_STRING r116
            java.lang.IllegalArgumentException: newPosition < 0: (-522137308 < 0)
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
        /*  JADX ERROR: Failed to decode insn: 0x0017: INSTANCE_OF r0, r0
            java.lang.IllegalArgumentException: newPosition > limit: (154080 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:357)
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
        /*  JADX ERROR: Failed to decode insn: 0x0019: UNKNOWN(0xF8EC)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0019: UNKNOWN(0xF8EC)'
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
        /*  JADX ERROR: Failed to decode insn: 0x001F: UNKNOWN(0xC2F5)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001F: UNKNOWN(0xC2F5)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0020: INVOKE_SUPER_RANGE r38162, r38163, r38164, r38165, r38166, r38167, r38168, r38169, r38170, r38171, r38172, r38173, r38174, r38175, r38176, r38177, r38178, r38179, r38180, r38181, r38182, r38183, r38184, r38185, r38186, r38187, r38188, r38189, r38190, r38191, r38192, r38193, r38194, r38195, r38196, r38197, r38198, r38199, r38200, r38201, r38202, r38203, r38204, r38205, r38206, r38207, r38208, r38209, r38210, r38211, r38212, r38213, r38214, r38215, r38216, r38217, r38218, r38219, r38220, r38221, r38222, r38223, r38224, r38225, r38226, r38227, r38228, r38229, r38230, r38231, r38232, r38233, r38234, r38235, r38236, r38237, r38238, r38239, r38240, r38241, r38242, r38243, r38244, r38245, r38246, r38247, r38248, r38249, r38250, r38251, r38252, r38253, r38254, r38255, r38256, r38257, r38258, r38259, r38260, r38261, r38262, r38263, r38264, r38265, r38266, r38267, r38268, r38269, r38270, r38271, r38272, r38273, r38274, r38275, r38276, r38277, r38278, r38279, r38280, r38281, r38282, r38283, r38284, r38285, r38286, r38287, r38288, r38289, r38290, r38291, r38292, r38293, r38294, r38295, r38296, r38297, r38298, r38299, r38300, r38301, r38302, r38303, r38304, r38305, r38306, r38307, r38308, r38309, r38310, r38311, r38312, r38313, r38314, r38315, r38316, r38317, r38318, r38319, r38320, r38321, r38322, r38323, r38324, r38325, r38326, r38327, r38328, r38329, r38330, r38331, r38332, r38333, r38334, r38335, r38336, r38337, r38338, r38339, r38340, r38341, r38342, r38343, r38344, r38345, r38346, r38347, r38348, r38349, r38350, r38351
            java.lang.IllegalArgumentException: newPosition > limit: (503796 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
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
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0023: IGET r11, r13
            java.lang.IllegalArgumentException: newPosition > limit: (156288 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0036: SGET r221
            java.lang.IllegalArgumentException: newPosition > limit: (305936 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x003C: CONST_METHOD_HANDLE r136
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x003C: CONST_METHOD_HANDLE r136'
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
        /*  JADX ERROR: Failed to decode insn: 0x0047: INVOKE_STATIC_RANGE r2208, r2209, r2210, r2211, r2212, r2213, r2214, r2215, r2216, r2217, r2218, r2219, r2220, r2221, r2222, r2223, r2224, r2225, r2226, r2227, r2228, r2229, r2230, r2231, r2232, r2233, r2234, r2235
            java.lang.IllegalArgumentException: newPosition > limit: (457160 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
            	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
            	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:439)
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
        private static /* synthetic */ void LAoHFpj() {
            /*
                // decode failed: newPosition > limit: (382304 > 104176)
                // decode failed: newPosition > limit: (478368 > 104176)
                monitor-enter(r33)
                long r7 = r7 * r8
                r9 = r2 ^ (-6422(0xffffffffffffe6ea, float:NaN))
                // decode failed: Unknown instruction: '0x0008: UNKNOWN(0x7CF4)'
                float r91 = r135 / r195
                r34534 = r31691
                // decode failed: Unknown instruction: '0x000E: UNKNOWN(0x08EE)'
                // decode failed: newPosition > limit: (145400 > 104176)
                // decode failed: Unknown instruction: '0x0011: UNKNOWN(0x45EC)'
                int r12 = r1 * 752
                // decode failed: newPosition < 0: (-522137308 < 0)
                // decode failed: newPosition > limit: (154080 > 104176)
                // decode failed: Unknown instruction: '0x0019: UNKNOWN(0xF8EC)'
                r541 = r25809
                long r108 = r200 & r220
                // decode failed: Unknown instruction: '0x001F: UNKNOWN(0xC2F5)'
                // decode failed: newPosition > limit: (503796 > 104176)
                // decode failed: newPosition > limit: (156288 > 104176)
                return r40
                r8879 = r38201
                int r4 = (int) r1
                r48 = 1220627033(0x48c14a59, float:395858.78)
                r88 = -691036387381517965(0xf668f21123416173, double:-2.4547074587386626E262)
                r187[r13] = r10
                if (r136 > 0) goto LB_6b39
                // decode failed: newPosition > limit: (305936 > 104176)
                int r35 = r127 << r108
                double r178 = r208 + r38
                // decode failed: Unknown instruction: '0x003C: CONST_METHOD_HANDLE r136'
                monitor-exit(r126)
                int r109 = (r103 > r71 ? 1 : (r103 == r71 ? 0 : -1))
                r40[r88] = r107
                return r178
                r14 = r14 ^ r7
                r116[r236] = r42
                // decode failed: newPosition > limit: (457160 > 104176)
                long r56 = r0 ^ r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass3.LAoHFpj():void");
        }

        /* renamed from: ۟ۢۥ۟ۥ, reason: not valid java name and contains not printable characters */
        public static native void m146(Object obj, int i);

        /* renamed from: ۦ۠۟, reason: contains not printable characters */
        public static native MainActivity m147(Object obj);

        @Override // android.content.DialogInterface.OnClickListener
        public native void onClick(DialogInterface dialogInterface, int i);
    }

    /* renamed from: com.Mode.toolbox.MainActivity$4, reason: invalid class name */
    class AnonymousClass4 implements View.OnClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f30short = null;
        final /* synthetic */ MainActivity this$0;
        final /* synthetic */ EditText val$input;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0017: SGET r103
            java.lang.IllegalArgumentException: newPosition > limit: (500616 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0008: UNKNOWN(0x08F7)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0008: UNKNOWN(0x08F7)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0016: UNKNOWN(0x9CF5)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0016: UNKNOWN(0x9CF5)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0017: SGET r103
            java.lang.IllegalArgumentException: newPosition > limit: (500616 > 104176)
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
        static {
            /*
                r223 = 7331655592940492251(0x65bf45eac0b955db, double:1.2976827050035154E182)
                int r5 = r5 >>> r12
                float r209 = r70 * r242
                // decode failed: Unknown instruction: '0x0008: UNKNOWN(0x08F7)'
                r9 = r9 ^ r13
                int r164 = r39 / r230
                r159 = r37 | 19
                r168[r31] = r248
                r91 = -737193112654285989(0xf5c4f6c51560735b, double:-2.0145557510501806E259)
                r11 = r11 | r2
                // decode failed: Unknown instruction: '0x0016: UNKNOWN(0x9CF5)'
                // decode failed: newPosition > limit: (500616 > 104176)
                double r13 = r13 - r12
                long r255 = r0 / r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass4.<clinit>():void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0002: SPUT r75
            java.lang.IllegalArgumentException: newPosition > limit: (518728 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x7AE6)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x7AE6)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0002: SPUT r75
            java.lang.IllegalArgumentException: newPosition > limit: (518728 > 104176)
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
        AnonymousClass4(com.Mode.toolbox.MainActivity r52, android.widget.EditText r53) {
            /*
                r51 = this;
                // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x7AE6)'
                float r12 = (float) r13
                // decode failed: newPosition > limit: (518728 > 104176)
                r133 = r31[r145]
                long r188 = r95 + r171
                r191 = r190 & r12
                r127 = 14748761656420176(0x3465ebab686750, double:1.1346834944493779E-307)
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass4.<init>(com.Mode.toolbox.MainActivity, android.widget.EditText):void");
        }

        /* renamed from: ۟۟۟ۧۧ, reason: not valid java name and contains not printable characters */
        public static native EditText m148(Object obj);

        /* renamed from: ۣ۟ۤۡۧ, reason: not valid java name and contains not printable characters */
        public static native short[] m149();

        /* renamed from: ۦۢۤ, reason: contains not printable characters */
        public static native MainActivity m150(Object obj);

        @Override // android.view.View.OnClickListener
        public native void onClick(View view);
    }

    /* renamed from: com.Mode.toolbox.MainActivity$5, reason: invalid class name */
    class AnonymousClass5 implements View.OnClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f31short = null;
        final /* synthetic */ MainActivity this$0;

        /* renamed from: com.Mode.toolbox.MainActivity$5$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f32short = null;
            final /* synthetic */ AnonymousClass5 this$1;
            final /* synthetic */ ProgressDialog val$dialog;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0010: IGET r3, r2
                java.lang.IllegalArgumentException: newPosition > limit: (419040 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0004: UNKNOWN(0x01EC)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0004: UNKNOWN(0x01EC)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x000E: CONST_METHOD_TYPE r24
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000E: CONST_METHOD_TYPE r24'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0010: IGET r3, r2
                java.lang.IllegalArgumentException: newPosition > limit: (419040 > 104176)
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
            static {
                /*
                    int r6 = r3 / 7524
                    int r234 = r86 >> r54
                    // decode failed: Unknown instruction: '0x0004: UNKNOWN(0x01EC)'
                    switch(r230) {
                    // error: 0x0005: SWITCH (r230 I:??)no payload
                    if (r117 != 0) goto L451a
                    r78 = r44 & r21
                    if (r189 <= 0) goto LB_529b
                    // decode failed: Unknown instruction: '0x000E: CONST_METHOD_TYPE r24'
                    // decode failed: newPosition > limit: (419040 > 104176)
                    long r2 = r2 << r13
                    r4 = r14
                    int r12 = r9 % (-12489)
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass5.AnonymousClass1.<clinit>():void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0000: SGET r121
                java.lang.IllegalArgumentException: newPosition > limit: (203592 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0000: SGET r121
                java.lang.IllegalArgumentException: newPosition > limit: (203592 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x000B: UNKNOWN(0xB040)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000B: UNKNOWN(0xB040)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            AnonymousClass1(com.Mode.toolbox.MainActivity.AnonymousClass5 r52, android.app.ProgressDialog r53) {
                /*
                    r51 = this;
                    // decode failed: newPosition > limit: (203592 > 104176)
                    
                    // error: 0x0002: CHECK_CAST (r222 I:null) = () (r222 I:??[OBJECT, ARRAY])
                    long r0 = (long) r3
                    int r159 = (r140 > r54 ? 1 : (r140 == r54 ? 0 : -1))
                    r164 = 6146850541407305728(0x554e000000000000, double:8.399042783166766E102)
                    if (r194 <= 0) goto L2c1d
                    // decode failed: Unknown instruction: '0x000B: UNKNOWN(0xB040)'
                    r205 = r51[r8]
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass5.AnonymousClass1.<init>(com.Mode.toolbox.MainActivity$5, android.app.ProgressDialog):void");
            }

            /* renamed from: ۟ۡۦ۟ۦ, reason: not valid java name and contains not printable characters */
            public static native MainActivity m154(Object obj);

            /* renamed from: ۣ۟ۢۧۤ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass5 m155(Object obj);

            /* renamed from: ۠ۤۦ۟, reason: not valid java name and contains not printable characters */
            public static native ProgressDialog m156(Object obj);

            /* renamed from: ۦ۠ۢ, reason: contains not printable characters */
            public static native short[] m157();

            @Override // java.lang.Runnable
            public native void run();
        }

        /* renamed from: com.Mode.toolbox.MainActivity$5$2, reason: invalid class name */
        class AnonymousClass2 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f33short = null;
            final /* synthetic */ AnonymousClass1 this$2;
            final /* synthetic */ boolean val$success;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0000: IGET r5, r8
                java.lang.IllegalArgumentException: newPosition > limit: (143776 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:184)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0005: IPUT r10, r5
                java.lang.IllegalArgumentException: newPosition > limit: (115428 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0007: INVOKE_POLYMORPHIC_RANGE r3562, r3563, r3564, r3565
                java.lang.IllegalArgumentException: newPosition > limit: (304536 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x000B: INVOKE_SUPER_RANGE r18718, r18719, r18720, r18721, r18722, r18723, r18724, r18725, r18726, r18727, r18728, r18729, r18730, r18731, r18732, r18733, r18734, r18735, r18736, r18737, r18738, r18739, r18740, r18741, r18742, r18743, r18744, r18745, r18746, r18747, r18748, r18749, r18750, r18751, r18752, r18753, r18754, r18755, r18756, r18757, r18758, r18759, r18760, r18761, r18762, r18763, r18764, r18765, r18766, r18767, r18768, r18769, r18770, r18771, r18772, r18773, r18774, r18775, r18776, r18777, r18778, r18779, r18780, r18781, r18782, r18783, r18784, r18785, r18786, r18787, r18788, r18789, r18790, r18791, r18792, r18793, r18794, r18795, r18796, r18797, r18798, r18799, r18800, r18801, r18802, r18803, r18804, r18805, r18806, r18807, r18808, r18809, r18810, r18811, r18812, r18813, r18814, r18815, r18816, r18817, r18818, r18819, r18820, r18821, r18822, r18823, r18824, r18825, r18826, r18827, r18828, r18829, r18830, r18831, r18832, r18833, r18834, r18835, r18836, r18837, r18838, r18839, r18840, r18841, r18842, r18843, r18844, r18845, r18846, r18847, r18848, r18849, r18850
                java.lang.IllegalArgumentException: newPosition > limit: (505644 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0010: INVOKE_POLYMORPHIC r2
                java.lang.IllegalArgumentException: newPosition > limit: (215800 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0000: IGET r5, r8
                java.lang.IllegalArgumentException: newPosition > limit: (143776 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:184)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:370)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0004: UNKNOWN(0x7EF5)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0004: UNKNOWN(0x7EF5)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0005: IPUT r10, r5
                java.lang.IllegalArgumentException: newPosition > limit: (115428 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
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
            /*  JADX ERROR: Failed to decode insn: 0x0007: INVOKE_POLYMORPHIC_RANGE r3562, r3563, r3564, r3565
                java.lang.IllegalArgumentException: newPosition > limit: (304536 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
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
                */
            /*  JADX ERROR: Failed to decode insn: 0x000B: INVOKE_SUPER_RANGE r18718, r18719, r18720, r18721, r18722, r18723, r18724, r18725, r18726, r18727, r18728, r18729, r18730, r18731, r18732, r18733, r18734, r18735, r18736, r18737, r18738, r18739, r18740, r18741, r18742, r18743, r18744, r18745, r18746, r18747, r18748, r18749, r18750, r18751, r18752, r18753, r18754, r18755, r18756, r18757, r18758, r18759, r18760, r18761, r18762, r18763, r18764, r18765, r18766, r18767, r18768, r18769, r18770, r18771, r18772, r18773, r18774, r18775, r18776, r18777, r18778, r18779, r18780, r18781, r18782, r18783, r18784, r18785, r18786, r18787, r18788, r18789, r18790, r18791, r18792, r18793, r18794, r18795, r18796, r18797, r18798, r18799, r18800, r18801, r18802, r18803, r18804, r18805, r18806, r18807, r18808, r18809, r18810, r18811, r18812, r18813, r18814, r18815, r18816, r18817, r18818, r18819, r18820, r18821, r18822, r18823, r18824, r18825, r18826, r18827, r18828, r18829, r18830, r18831, r18832, r18833, r18834, r18835, r18836, r18837, r18838, r18839, r18840, r18841, r18842, r18843, r18844, r18845, r18846, r18847, r18848, r18849, r18850
                java.lang.IllegalArgumentException: newPosition > limit: (505644 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
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
            /*  JADX ERROR: Failed to decode insn: 0x000E: UNKNOWN(0x3EE3)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000E: UNKNOWN(0x3EE3)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0010: INVOKE_POLYMORPHIC r2
                java.lang.IllegalArgumentException: newPosition > limit: (215800 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invokePolymorphic(InsnDecoder.java:605)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:454)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0018: UNKNOWN(0x49E6)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0018: UNKNOWN(0x49E6)'
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
                    // decode failed: newPosition > limit: (143776 > 104176)
                    if (r2 <= r6) goto L68ea
                    // decode failed: Unknown instruction: '0x0004: UNKNOWN(0x7EF5)'
                    // decode failed: newPosition > limit: (115428 > 104176)
                    // decode failed: newPosition > limit: (304536 > 104176)
                    // decode failed: newPosition > limit: (505644 > 104176)
                    // decode failed: Unknown instruction: '0x000E: UNKNOWN(0x3EE3)'
                    int r4 = r4 + r8
                    // decode failed: newPosition > limit: (215800 > 104176)
                    long r3 = r3 | r1
                    if (r4 > r1) goto L142e
                    return r116
                    // decode failed: Unknown instruction: '0x0018: UNKNOWN(0x49E6)'
                    r12 = r11
                    double r2 = r2 + r4
                    long r22 = r54 / r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass5.AnonymousClass2.<clinit>():void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0001: IPUT r15, r8
                java.lang.IllegalArgumentException: newPosition > limit: (365304 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0003: IGET r5, r1
                java.lang.IllegalArgumentException: newPosition > limit: (113128 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x000C: IGET r15, r11
                java.lang.IllegalArgumentException: newPosition > limit: (282576 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0001: IPUT r15, r8
                java.lang.IllegalArgumentException: newPosition > limit: (365304 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0003: IGET r5, r1
                java.lang.IllegalArgumentException: newPosition > limit: (113128 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x000A: UNKNOWN(0x22EB)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000A: UNKNOWN(0x22EB)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x000C: IGET r15, r11
                java.lang.IllegalArgumentException: newPosition > limit: (282576 > 104176)
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
            AnonymousClass2(com.Mode.toolbox.MainActivity.AnonymousClass5.AnonymousClass1 r52, boolean r53) {
                /*
                    r51 = this;
                    float r2 = r2 / r10
                    // decode failed: newPosition > limit: (365304 > 104176)
                    // decode failed: newPosition > limit: (113128 > 104176)
                    long r132 = r207 >>> r42
                    r14 = r10 & 3816(0xee8, float:5.347E-42)
                    double r8 = r8 - r7
                    // decode failed: Unknown instruction: '0x000A: UNKNOWN(0x22EB)'
                    int r1 = -r15
                    // decode failed: newPosition > limit: (282576 > 104176)
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass5.AnonymousClass2.<init>(com.Mode.toolbox.MainActivity$5$1, boolean):void");
            }

            /* renamed from: ۣ۟۟ۤ۠, reason: not valid java name and contains not printable characters */
            public static native boolean m158(Object obj);

            /* renamed from: ۟ۦۣۥۧ, reason: not valid java name and contains not printable characters */
            public static native short[] m159();

            /* renamed from: ۟ۧۥۥۨ, reason: not valid java name and contains not printable characters */
            public static native MainActivity m160(Object obj);

            /* renamed from: ۠ۥ۠ۧ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass1 m161(Object obj);

            /* renamed from: ۦۢۥۢ, reason: contains not printable characters */
            public static native AnonymousClass5 m162(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0000: INVOKE_INTERFACE_RANGE r56818, r56819, r56820, r56821, r56822, r56823, r56824, r56825, r56826, r56827, r56828, r56829, r56830, r56831, r56832, r56833, r56834, r56835, r56836, r56837, r56838, r56839, r56840, r56841, r56842, r56843, r56844, r56845, r56846, r56847, r56848, r56849, r56850, r56851, r56852, r56853, r56854, r56855, r56856, r56857, r56858, r56859, r56860, r56861, r56862, r56863, r56864, r56865, r56866, r56867, r56868, r56869, r56870, r56871, r56872, r56873, r56874, r56875, r56876, r56877, r56878, r56879, r56880, r56881, r56882, r56883, r56884, r56885, r56886, r56887, r56888, r56889, r56890, r56891, r56892, r56893, r56894, r56895
            java.lang.IllegalArgumentException: newPosition > limit: (365808 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0004: FILLED_NEW_ARRAY r9, r11, r12, r4, r12, r56823, r56824, r56825, r56826, r56827, r56828, r56829, r56830, r56831, r56832
            java.lang.IllegalArgumentException: newPosition < 0: (-2132758756 < 0)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x000B: NEW_ARRAY r8, r15
            java.lang.IllegalArgumentException: newPosition < 0: (-1493548976 < 0)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0012: INVOKE_POLYMORPHIC_RANGE r31148, r31149, r31150, r31151, r31152, r31153, r31154, r31155, r31156, r31157, r31158, r31159, r31160, r31161, r31162, r31163, r31164, r31165, r31166, r31167, r31168, r31169, r31170, r31171, r31172, r31173, r31174, r31175, r31176, r31177, r31178, r31179, r31180, r31181, r31182, r31183, r31184, r31185, r31186, r31187, r31188, r31189, r31190, r31191, r31192, r31193, r31194, r31195, r31196, r31197, r31198, r31199, r31200, r31201, r31202, r31203, r31204, r31205, r31206, r31207, r31208, r31209, r31210, r31211, r31212, r31213, r31214, r31215, r31216, r31217, r31218, r31219, r31220, r31221, r31222, r31223, r31224, r31225, r31226, r31227, r31228, r31229, r31230, r31231
            java.lang.IllegalArgumentException: newPosition > limit: (500184 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0019: INVOKE_STATIC_RANGE r57479, r57480, r57481, r57482, r57483, r57484, r57485, r57486, r57487, r57488, r57489, r57490, r57491, r57492, r57493, r57494, r57495, r57496, r57497, r57498, r57499, r57500, r57501, r57502, r57503, r57504, r57505, r57506, r57507, r57508, r57509, r57510, r57511, r57512, r57513, r57514, r57515, r57516, r57517, r57518, r57519, r57520, r57521, r57522, r57523, r57524, r57525, r57526, r57527, r57528, r57529, r57530, r57531, r57532, r57533, r57534, r57535, r57536, r57537, r57538, r57539, r57540, r57541, r57542, r57543, r57544, r57545, r57546, r57547, r57548, r57549, r57550, r57551, r57552, r57553, r57554, r57555, r57556, r57557, r57558, r57559, r57560, r57561, r57562, r57563, r57564, r57565, r57566, r57567, r57568, r57569, r57570, r57571, r57572, r57573, r57574, r57575, r57576, r57577, r57578, r57579, r57580, r57581, r57582, r57583, r57584, r57585, r57586, r57587, r57588, r57589, r57590, r57591, r57592, r57593, r57594, r57595, r57596, r57597, r57598, r57599, r57600, r57601, r57602, r57603, r57604, r57605
            java.lang.IllegalArgumentException: newPosition > limit: (206172 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: INVOKE_INTERFACE_RANGE r56818, r56819, r56820, r56821, r56822, r56823, r56824, r56825, r56826, r56827, r56828, r56829, r56830, r56831, r56832, r56833, r56834, r56835, r56836, r56837, r56838, r56839, r56840, r56841, r56842, r56843, r56844, r56845, r56846, r56847, r56848, r56849, r56850, r56851, r56852, r56853, r56854, r56855, r56856, r56857, r56858, r56859, r56860, r56861, r56862, r56863, r56864, r56865, r56866, r56867, r56868, r56869, r56870, r56871, r56872, r56873, r56874, r56875, r56876, r56877, r56878, r56879, r56880, r56881, r56882, r56883, r56884, r56885, r56886, r56887, r56888, r56889, r56890, r56891, r56892, r56893, r56894, r56895
            java.lang.IllegalArgumentException: newPosition > limit: (365808 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
            	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
            	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:459)
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
        /*  JADX ERROR: Failed to decode insn: 0x0004: FILLED_NEW_ARRAY r9, r11, r12, r4, r12, r56823, r56824, r56825, r56826, r56827, r56828, r56829, r56830, r56831, r56832
            java.lang.IllegalArgumentException: newPosition < 0: (-2132758756 < 0)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:484)
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
        /*  JADX ERROR: Failed to decode insn: 0x000A: UNKNOWN(0x1AEC)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000A: UNKNOWN(0x1AEC)'
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
        /*  JADX ERROR: Failed to decode insn: 0x000B: NEW_ARRAY r8, r15
            java.lang.IllegalArgumentException: newPosition < 0: (-1493548976 < 0)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
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
        /*  JADX ERROR: Failed to decode insn: 0x0012: INVOKE_POLYMORPHIC_RANGE r31148, r31149, r31150, r31151, r31152, r31153, r31154, r31155, r31156, r31157, r31158, r31159, r31160, r31161, r31162, r31163, r31164, r31165, r31166, r31167, r31168, r31169, r31170, r31171, r31172, r31173, r31174, r31175, r31176, r31177, r31178, r31179, r31180, r31181, r31182, r31183, r31184, r31185, r31186, r31187, r31188, r31189, r31190, r31191, r31192, r31193, r31194, r31195, r31196, r31197, r31198, r31199, r31200, r31201, r31202, r31203, r31204, r31205, r31206, r31207, r31208, r31209, r31210, r31211, r31212, r31213, r31214, r31215, r31216, r31217, r31218, r31219, r31220, r31221, r31222, r31223, r31224, r31225, r31226, r31227, r31228, r31229, r31230, r31231
            java.lang.IllegalArgumentException: newPosition > limit: (500184 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0019: INVOKE_STATIC_RANGE r57479, r57480, r57481, r57482, r57483, r57484, r57485, r57486, r57487, r57488, r57489, r57490, r57491, r57492, r57493, r57494, r57495, r57496, r57497, r57498, r57499, r57500, r57501, r57502, r57503, r57504, r57505, r57506, r57507, r57508, r57509, r57510, r57511, r57512, r57513, r57514, r57515, r57516, r57517, r57518, r57519, r57520, r57521, r57522, r57523, r57524, r57525, r57526, r57527, r57528, r57529, r57530, r57531, r57532, r57533, r57534, r57535, r57536, r57537, r57538, r57539, r57540, r57541, r57542, r57543, r57544, r57545, r57546, r57547, r57548, r57549, r57550, r57551, r57552, r57553, r57554, r57555, r57556, r57557, r57558, r57559, r57560, r57561, r57562, r57563, r57564, r57565, r57566, r57567, r57568, r57569, r57570, r57571, r57572, r57573, r57574, r57575, r57576, r57577, r57578, r57579, r57580, r57581, r57582, r57583, r57584, r57585, r57586, r57587, r57588, r57589, r57590, r57591, r57592, r57593, r57594, r57595, r57596, r57597, r57598, r57599, r57600, r57601, r57602, r57603, r57604, r57605
            java.lang.IllegalArgumentException: newPosition > limit: (206172 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
            	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
            	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:439)
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
        /*  JADX ERROR: Failed to decode insn: 0x001D: UNKNOWN(0xC3ED)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001D: UNKNOWN(0xC3ED)'
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
                // decode failed: newPosition > limit: (365808 > 104176)
                float r3 = (float) r8
                // decode failed: newPosition < 0: (-2132758756 < 0)
                double r2 = r2 + r2
                long r35 = r17 | r37
                // decode failed: Unknown instruction: '0x000A: UNKNOWN(0x1AEC)'
                // decode failed: newPosition < 0: (-1493548976 < 0)
                goto L52b2
                int r35 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
                int r13 = r13 / r4
                // decode failed: newPosition > limit: (500184 > 104176)
                r81 = r141 ^ r121
                return r169
                // decode failed: newPosition > limit: (206172 > 104176)
                float r10 = r10 - r14
                // decode failed: Unknown instruction: '0x001D: UNKNOWN(0xC3ED)'
                float r0 = r0 - r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass5.<clinit>():void");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0006: UNKNOWN(0x4173)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0006: UNKNOWN(0x4173)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0007: UNKNOWN(0xA3F2)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0007: UNKNOWN(0xA3F2)'
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
        AnonymousClass5(com.Mode.toolbox.MainActivity r52) {
            /*
                r51 = this;
                r65350 = r27897
                int r15 = r15 / r2
                int r186 = r38 / 19
                // decode failed: Unknown instruction: '0x0006: UNKNOWN(0x4173)'
                // decode failed: Unknown instruction: '0x0007: UNKNOWN(0xA3F2)'
                if (r7 == 0) goto LB_6771
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass5.<init>(com.Mode.toolbox.MainActivity):void");
        }

        /* renamed from: ۟۟۠ۤۧ, reason: not valid java name and contains not printable characters */
        public static native int m151(Object obj);

        /* renamed from: ۟۟ۤۡۧ, reason: not valid java name and contains not printable characters */
        public static native short[] m152();

        /* renamed from: ۣ۟۟ۧ۟, reason: not valid java name and contains not printable characters */
        public static native MainActivity m153(Object obj);

        @Override // android.view.View.OnClickListener
        public native void onClick(View view);
    }

    /* renamed from: com.Mode.toolbox.MainActivity$6, reason: invalid class name */
    class AnonymousClass6 implements View.OnClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f34short = null;
        final /* synthetic */ MainActivity this$0;

        /* renamed from: com.Mode.toolbox.MainActivity$6$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f35short = null;
            final /* synthetic */ AnonymousClass6 this$1;
            final /* synthetic */ ProgressDialog val$dialog;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0003: IPUT r2, r6
                java.lang.IllegalArgumentException: newPosition > limit: (511080 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0006: SGET r216
                java.lang.IllegalArgumentException: newPosition > limit: (178936 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x000D: INVOKE_SUPER_RANGE r16295, r16296, r16297, r16298, r16299, r16300, r16301, r16302, r16303, r16304, r16305, r16306, r16307, r16308, r16309, r16310, r16311, r16312, r16313, r16314, r16315, r16316, r16317, r16318, r16319, r16320, r16321, r16322, r16323, r16324, r16325, r16326, r16327, r16328, r16329, r16330, r16331, r16332, r16333, r16334, r16335, r16336, r16337, r16338, r16339, r16340, r16341, r16342, r16343, r16344, r16345, r16346, r16347, r16348, r16349, r16350, r16351, r16352, r16353, r16354, r16355, r16356, r16357, r16358, r16359, r16360, r16361, r16362, r16363, r16364, r16365, r16366, r16367, r16368, r16369, r16370, r16371, r16372, r16373, r16374, r16375, r16376, r16377, r16378, r16379, r16380, r16381, r16382, r16383, r16384, r16385, r16386, r16387, r16388, r16389, r16390, r16391, r16392, r16393, r16394, r16395, r16396, r16397, r16398, r16399, r16400, r16401, r16402, r16403, r16404, r16405, r16406, r16407, r16408, r16409, r16410, r16411, r16412, r16413, r16414, r16415, r16416, r16417, r16418, r16419, r16420, r16421, r16422, r16423, r16424, r16425, r16426, r16427, r16428
                java.lang.IllegalArgumentException: newPosition > limit: (571776 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0010: SPUT r197
                java.lang.IllegalArgumentException: newPosition > limit: (199000 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0015: IGET r12, r11
                java.lang.IllegalArgumentException: newPosition > limit: (385664 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0019: IGET r4, r10
                java.lang.IllegalArgumentException: newPosition > limit: (353544 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x001D: IGET r1, r4
                java.lang.IllegalArgumentException: newPosition > limit: (126208 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0028: IPUT r1, r6
                java.lang.IllegalArgumentException: newPosition < 0: (-706107888 < 0)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x002A: SGET r131
                java.lang.IllegalArgumentException: newPosition > limit: (265560 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0035: INVOKE_STATIC r14, r11, r0, r5, r0, r16300, r16301, r16302, r16303, r16304, r16305
                java.lang.IllegalArgumentException: newPosition > limit: (381396 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x003D: FILLED_NEW_ARRAY_RANGE r43110, r43111, r43112, r43113
                java.lang.IllegalArgumentException: newPosition < 0: (-623488548 < 0)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0044: SGET r4
                java.lang.IllegalArgumentException: newPosition > limit: (151736 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0046: INVOKE_POLYMORPHIC_RANGE r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140, r141, r142, r143, r144, r145, r146, r147, r148, r149, r150, r151, r152, r153, r154, r155, r156, r157, r158, r159, r160, r161, r162, r163, r164, r165, r166, r167, r168, r169, r170, r171
                java.lang.IllegalArgumentException: newPosition > limit: (441616 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0003: IPUT r2, r6
                java.lang.IllegalArgumentException: newPosition > limit: (511080 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0005: UNKNOWN(0x7500)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0005: UNKNOWN(0x7500)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0006: SGET r216
                java.lang.IllegalArgumentException: newPosition > limit: (178936 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x000D: INVOKE_SUPER_RANGE r16295, r16296, r16297, r16298, r16299, r16300, r16301, r16302, r16303, r16304, r16305, r16306, r16307, r16308, r16309, r16310, r16311, r16312, r16313, r16314, r16315, r16316, r16317, r16318, r16319, r16320, r16321, r16322, r16323, r16324, r16325, r16326, r16327, r16328, r16329, r16330, r16331, r16332, r16333, r16334, r16335, r16336, r16337, r16338, r16339, r16340, r16341, r16342, r16343, r16344, r16345, r16346, r16347, r16348, r16349, r16350, r16351, r16352, r16353, r16354, r16355, r16356, r16357, r16358, r16359, r16360, r16361, r16362, r16363, r16364, r16365, r16366, r16367, r16368, r16369, r16370, r16371, r16372, r16373, r16374, r16375, r16376, r16377, r16378, r16379, r16380, r16381, r16382, r16383, r16384, r16385, r16386, r16387, r16388, r16389, r16390, r16391, r16392, r16393, r16394, r16395, r16396, r16397, r16398, r16399, r16400, r16401, r16402, r16403, r16404, r16405, r16406, r16407, r16408, r16409, r16410, r16411, r16412, r16413, r16414, r16415, r16416, r16417, r16418, r16419, r16420, r16421, r16422, r16423, r16424, r16425, r16426, r16427, r16428
                java.lang.IllegalArgumentException: newPosition > limit: (571776 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
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
            /*  JADX ERROR: Failed to decode insn: 0x0010: SPUT r197
                java.lang.IllegalArgumentException: newPosition > limit: (199000 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0012: UNKNOWN(0x2579)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0012: UNKNOWN(0x2579)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0013: UNKNOWN(0xBCF0)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0013: UNKNOWN(0xBCF0)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0015: IGET r12, r11
                java.lang.IllegalArgumentException: newPosition > limit: (385664 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0017: UNKNOWN(0x7FE7)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0017: UNKNOWN(0x7FE7)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0018: UNKNOWN(0x4EE4)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0018: UNKNOWN(0x4EE4)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0019: IGET r4, r10
                java.lang.IllegalArgumentException: newPosition > limit: (353544 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x001D: IGET r1, r4
                java.lang.IllegalArgumentException: newPosition > limit: (126208 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0028: IPUT r1, r6
                java.lang.IllegalArgumentException: newPosition < 0: (-706107888 < 0)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
                	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:195)
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
            /*  JADX ERROR: Failed to decode insn: 0x002A: SGET r131
                java.lang.IllegalArgumentException: newPosition > limit: (265560 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0031: UNKNOWN(0x1D7A)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0031: UNKNOWN(0x1D7A)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0032: UNKNOWN(0x9142)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0032: UNKNOWN(0x9142)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0035: INVOKE_STATIC r14, r11, r0, r5, r0, r16300, r16301, r16302, r16303, r16304, r16305
                java.lang.IllegalArgumentException: newPosition > limit: (381396 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:436)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x003D: FILLED_NEW_ARRAY_RANGE r43110, r43111, r43112, r43113
                java.lang.IllegalArgumentException: newPosition < 0: (-623488548 < 0)
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
                */
            /*  JADX ERROR: Failed to decode insn: 0x0042: UNKNOWN(0x24F0)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0042: UNKNOWN(0x24F0)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0044: SGET r4
                java.lang.IllegalArgumentException: newPosition > limit: (151736 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0046: INVOKE_POLYMORPHIC_RANGE r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140, r141, r142, r143, r144, r145, r146, r147, r148, r149, r150, r151, r152, r153, r154, r155, r156, r157, r158, r159, r160, r161, r162, r163, r164, r165, r166, r167, r168, r169, r170, r171
                java.lang.IllegalArgumentException: newPosition > limit: (441616 > 104176)
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
                */
            static {
                /*
                    int r70 = r132 >>> (-70)
                    int r13 = r13 / r8
                    // decode failed: newPosition > limit: (511080 > 104176)
                    // decode failed: Unknown instruction: '0x0005: UNKNOWN(0x7500)'
                    // decode failed: newPosition > limit: (178936 > 104176)
                    double r215 = r199 * r170
                    int r3 = (-8189) - r0
                    int r14 = r14 + r10
                    // decode failed: newPosition > limit: (571776 > 104176)
                    // decode failed: newPosition > limit: (199000 > 104176)
                    // decode failed: Unknown instruction: '0x0012: UNKNOWN(0x2579)'
                    // decode failed: Unknown instruction: '0x0013: UNKNOWN(0xBCF0)'
                    float r9 = (float) r3
                    // decode failed: newPosition > limit: (385664 > 104176)
                    // decode failed: Unknown instruction: '0x0017: UNKNOWN(0x7FE7)'
                    // decode failed: Unknown instruction: '0x0018: UNKNOWN(0x4EE4)'
                    // decode failed: newPosition > limit: (353544 > 104176)
                    double r204 = r36 % r209
                    // decode failed: newPosition > limit: (126208 > 104176)
                    r25403 = r41311
                    int r8 = r5 % (-11294)
                    int r230 = r176 << (-36)
                    r7 = r6 ^ r165
                    // decode failed: newPosition < 0: (-706107888 < 0)
                    // decode failed: newPosition > limit: (265560 > 104176)
                    r27 = 6754854401765463834(0x5dbe104a26b6d71a, double:3.6660585125410944E143)
                    // decode failed: Unknown instruction: '0x0031: UNKNOWN(0x1D7A)'
                    // decode failed: Unknown instruction: '0x0032: UNKNOWN(0x9142)'
                    int r90 = r89 * (-49)
                    // decode failed: newPosition > limit: (381396 > 104176)
                    int r147 = r148 / (-105)
                    int r96 = (r214 > r226 ? 1 : (r214 == r226 ? 0 : -1))
                    double r8 = r8 - r12
                    // decode failed: newPosition < 0: (-623488548 < 0)
                    if (r1 >= r13) goto L270c
                    // decode failed: Unknown instruction: '0x0042: UNKNOWN(0x24F0)'
                    int r5 = (int) r11
                    // decode failed: newPosition > limit: (151736 > 104176)
                    // decode failed: newPosition > limit: (441616 > 104176)
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass6.AnonymousClass1.<clinit>():void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0003: SPUT r162
                java.lang.IllegalArgumentException: newPosition > limit: (453552 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x000B: SGET r106
                java.lang.IllegalArgumentException: newPosition > limit: (424344 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0003: SPUT r162
                java.lang.IllegalArgumentException: newPosition > limit: (453552 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x000B: SGET r106
                java.lang.IllegalArgumentException: newPosition > limit: (424344 > 104176)
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
            AnonymousClass1(com.Mode.toolbox.MainActivity.AnonymousClass6 r52, android.app.ProgressDialog r53) {
                /*
                    r51 = this;
                    r202 = r201 ^ 53
                    throw r231
                    // decode failed: newPosition > limit: (453552 > 104176)
                    if (r2 <= r2) goto LB_6772
                    int r38 = r183 - r94
                    return
                    int r14 = (int) r3
                    // decode failed: newPosition > limit: (424344 > 104176)
                    int r12 = r12 / r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass6.AnonymousClass1.<init>(com.Mode.toolbox.MainActivity$6, android.app.ProgressDialog):void");
            }

            /* renamed from: ۟ۢۥۦ۟, reason: not valid java name and contains not printable characters */
            public static native MainActivity m166(Object obj);

            /* renamed from: ۟ۤۤۡ۠, reason: not valid java name and contains not printable characters */
            public static native ProgressDialog m167(Object obj);

            /* renamed from: ۡۦۥ, reason: not valid java name and contains not printable characters */
            public static native short[] m168();

            /* renamed from: ۥۥۣۨ, reason: contains not printable characters */
            public static native AnonymousClass6 m169(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /* renamed from: com.Mode.toolbox.MainActivity$6$2, reason: invalid class name */
        class AnonymousClass2 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f36short = null;
            final /* synthetic */ AnonymousClass1 this$2;
            final /* synthetic */ String val$output;
            final /* synthetic */ boolean val$success;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0000: SGET r201
                java.lang.IllegalArgumentException: newPosition > limit: (380800 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0004: INVOKE_DIRECT_RANGE r29101, r29102, r29103, r29104, r29105, r29106, r29107, r29108, r29109, r29110, r29111, r29112, r29113, r29114, r29115, r29116, r29117, r29118, r29119, r29120, r29121, r29122, r29123, r29124, r29125, r29126, r29127, r29128, r29129, r29130, r29131, r29132, r29133
                java.lang.IllegalArgumentException: newPosition > limit: (723048 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x000E: IPUT r3, r2
                java.lang.IllegalArgumentException: newPosition > limit: (240390632 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0000: SGET r201
                java.lang.IllegalArgumentException: newPosition > limit: (380800 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0003: UNKNOWN(0x51F9)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0003: UNKNOWN(0x51F9)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0004: INVOKE_DIRECT_RANGE r29101, r29102, r29103, r29104, r29105, r29106, r29107, r29108, r29109, r29110, r29111, r29112, r29113, r29114, r29115, r29116, r29117, r29118, r29119, r29120, r29121, r29122, r29123, r29124, r29125, r29126, r29127, r29128, r29129, r29130, r29131, r29132, r29133
                java.lang.IllegalArgumentException: newPosition > limit: (723048 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
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
            /*  JADX ERROR: Failed to decode insn: 0x0007: UNKNOWN(0x8873)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0007: UNKNOWN(0x8873)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x000E: IPUT r3, r2
                java.lang.IllegalArgumentException: newPosition > limit: (240390632 > 104176)
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
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0017: UNKNOWN(0x82F9)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0017: UNKNOWN(0x82F9)'
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
                    // decode failed: newPosition > limit: (380800 > 104176)
                    int r5 = r5 << r4
                    // decode failed: Unknown instruction: '0x0003: UNKNOWN(0x51F9)'
                    // decode failed: newPosition > limit: (723048 > 104176)
                    // decode failed: Unknown instruction: '0x0007: UNKNOWN(0x8873)'
                    r20394 = r39836
                    com.Mode.toolbox.C0002.ۨ۟ۥ = r169
                    int r14 = r14 >> r10
                    // decode failed: newPosition > limit: (240390632 > 104176)
                    r213 = r54 & (-96)
                    if (r7 == r12) goto LB_44a4
                    throw r91
                    float r235 = r1 / r172
                    // decode failed: Unknown instruction: '0x0017: UNKNOWN(0x82F9)'
                    long r6 = r6 - r7
                    long r155 = r214 ^ r193
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass6.AnonymousClass2.<clinit>():void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0000: INVOKE_STATIC_RANGE r55424, r55425, r55426, r55427, r55428, r55429, r55430, r55431, r55432, r55433, r55434, r55435, r55436, r55437, r55438, r55439, r55440, r55441, r55442, r55443, r55444, r55445, r55446, r55447, r55448, r55449, r55450, r55451, r55452, r55453, r55454, r55455, r55456, r55457, r55458, r55459, r55460, r55461, r55462, r55463, r55464, r55465, r55466, r55467, r55468, r55469, r55470, r55471, r55472, r55473, r55474, r55475, r55476, r55477, r55478, r55479, r55480, r55481, r55482, r55483, r55484, r55485, r55486, r55487, r55488, r55489, r55490, r55491, r55492, r55493, r55494, r55495, r55496, r55497, r55498, r55499, r55500, r55501, r55502, r55503, r55504, r55505, r55506, r55507, r55508, r55509, r55510, r55511, r55512, r55513, r55514, r55515, r55516, r55517, r55518, r55519, r55520, r55521, r55522, r55523, r55524, r55525, r55526, r55527, r55528, r55529, r55530, r55531, r55532, r55533, r55534, r55535, r55536, r55537
                java.lang.IllegalArgumentException: newPosition > limit: (173808 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0005: SPUT r56
                java.lang.IllegalArgumentException: newPosition > limit: (122832 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0007: SGET r150
                java.lang.IllegalArgumentException: newPosition > limit: (132480 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0000: INVOKE_STATIC_RANGE r55424, r55425, r55426, r55427, r55428, r55429, r55430, r55431, r55432, r55433, r55434, r55435, r55436, r55437, r55438, r55439, r55440, r55441, r55442, r55443, r55444, r55445, r55446, r55447, r55448, r55449, r55450, r55451, r55452, r55453, r55454, r55455, r55456, r55457, r55458, r55459, r55460, r55461, r55462, r55463, r55464, r55465, r55466, r55467, r55468, r55469, r55470, r55471, r55472, r55473, r55474, r55475, r55476, r55477, r55478, r55479, r55480, r55481, r55482, r55483, r55484, r55485, r55486, r55487, r55488, r55489, r55490, r55491, r55492, r55493, r55494, r55495, r55496, r55497, r55498, r55499, r55500, r55501, r55502, r55503, r55504, r55505, r55506, r55507, r55508, r55509, r55510, r55511, r55512, r55513, r55514, r55515, r55516, r55517, r55518, r55519, r55520, r55521, r55522, r55523, r55524, r55525, r55526, r55527, r55528, r55529, r55530, r55531, r55532, r55533, r55534, r55535, r55536, r55537
                java.lang.IllegalArgumentException: newPosition > limit: (173808 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:439)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0005: SPUT r56
                java.lang.IllegalArgumentException: newPosition > limit: (122832 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0007: SGET r150
                java.lang.IllegalArgumentException: newPosition > limit: (132480 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0010: UNKNOWN(0xDC00)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0010: UNKNOWN(0xDC00)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0011: UNKNOWN(0x95F7)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0011: UNKNOWN(0x95F7)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            AnonymousClass2(com.Mode.toolbox.MainActivity.AnonymousClass6.AnonymousClass1 r52, java.lang.String r53, boolean r54) {
                /*
                    r51 = this;
                    // decode failed: newPosition > limit: (173808 > 104176)
                    int r250 = (-85) - r9
                    // decode failed: newPosition > limit: (122832 > 104176)
                    // decode failed: newPosition > limit: (132480 > 104176)
                    long r11 = r11 >>> r0
                    int r113 = r94 >> r109
                    r238 = r782
                    r6[r69] = r247
                    // decode failed: Unknown instruction: '0x0010: UNKNOWN(0xDC00)'
                    // decode failed: Unknown instruction: '0x0011: UNKNOWN(0x95F7)'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass6.AnonymousClass2.<init>(com.Mode.toolbox.MainActivity$6$1, java.lang.String, boolean):void");
            }

            /* renamed from: ۟۠ۤ۟ۡ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass6 m170(Object obj);

            /* renamed from: ۟۠ۤۢ, reason: not valid java name and contains not printable characters */
            public static native String m171(Object obj);

            /* renamed from: ۟ۢۥ۟, reason: not valid java name and contains not printable characters */
            public static native int m172(Object obj);

            /* renamed from: ۣ۟ۢ۟ۨ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass1 m173(Object obj);

            /* renamed from: ۟ۤۦۥۢ, reason: not valid java name and contains not printable characters */
            public static native short[] m174();

            /* renamed from: ۣۤۢۢ, reason: not valid java name and contains not printable characters */
            public static native MainActivity m175(Object obj);

            /* renamed from: ۣۤ۟ۤ, reason: not valid java name and contains not printable characters */
            public static native boolean m176(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.6.<clinit>():void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
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
            	... 7 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.6.<clinit>():void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass6.<clinit>():void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0001: IPUT r5, r12
            java.lang.IllegalArgumentException: newPosition > limit: (414120 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0001: IPUT r5, r12
            java.lang.IllegalArgumentException: newPosition > limit: (414120 > 104176)
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
        AnonymousClass6(com.Mode.toolbox.MainActivity r52) {
            /*
                r51 = this;
                long r14 = (long) r5
                // decode failed: newPosition > limit: (414120 > 104176)
                int r215 = r3 << 16
                long r69 = r42 + r238
                r12 = -6788(0xffffffffffffe57c, float:NaN)
                int r2 = r2 * r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass6.<init>(com.Mode.toolbox.MainActivity):void");
        }

        /* renamed from: ۟ۦۨ۟ۢ, reason: not valid java name and contains not printable characters */
        public static native short[] m163();

        /* renamed from: ۠ۢۨ۠, reason: not valid java name and contains not printable characters */
        public static native MainActivity m164(Object obj);

        /* renamed from: ۡۦۧ, reason: not valid java name and contains not printable characters */
        public static native int m165(Object obj);

        @Override // android.view.View.OnClickListener
        public native void onClick(View view);
    }

    /* renamed from: com.Mode.toolbox.MainActivity$7, reason: invalid class name */
    class AnonymousClass7 implements View.OnClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f37short = null;
        final /* synthetic */ MainActivity this$0;

        /* renamed from: com.Mode.toolbox.MainActivity$7$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f38short = null;
            final /* synthetic */ AnonymousClass7 this$1;
            final /* synthetic */ ProgressDialog val$dialog;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0004: SGET r40
                java.lang.IllegalArgumentException: newPosition > limit: (398096 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x000F: INVOKE_SUPER r2, r5, r10, r11
                java.lang.IllegalArgumentException: newPosition > limit: (495032 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0004: SGET r40
                java.lang.IllegalArgumentException: newPosition > limit: (398096 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x000F: INVOKE_SUPER r2, r5, r10, r11
                java.lang.IllegalArgumentException: newPosition > limit: (495032 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:446)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            static {
                /*
                    int r12 = r12 >> r12
                    r229 = r235 & r220
                    int r3 = r3 >> r2
                    // decode failed: newPosition > limit: (398096 > 104176)
                    boolean r233 = r143[r205]
                    int r156 = (r194 > r15 ? 1 : (r194 == r15 ? 0 : -1))
                    r7 = 7
                    int r46 = r167 + r48
                    int r129 = r48 / (-70)
                    // decode failed: newPosition > limit: (495032 > 104176)
                    float r1 = r1 % r8
                    return
                    int r125 = 49 - r61
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass7.AnonymousClass1.<clinit>():void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0000: SGET r22
                java.lang.IllegalArgumentException: newPosition > limit: (429424 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0000: SGET r22
                java.lang.IllegalArgumentException: newPosition > limit: (429424 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0004: UNKNOWN(0x2DE6)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0004: UNKNOWN(0x2DE6)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0008: UNKNOWN(0xC4F3)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0008: UNKNOWN(0xC4F3)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x000B: UNKNOWN(0xEAEB)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000B: UNKNOWN(0xEAEB)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x000D: UNKNOWN(0x7FF6)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000D: UNKNOWN(0x7FF6)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            AnonymousClass1(com.Mode.toolbox.MainActivity.AnonymousClass7 r52, android.app.ProgressDialog r53) {
                /*
                    r51 = this;
                    // decode failed: newPosition > limit: (429424 > 104176)
                    r227 = r17491
                    // decode failed: Unknown instruction: '0x0004: UNKNOWN(0x2DE6)'
                    r4 = r4 | r12
                    r218 = 1310916608(0x4e230000, float:6.8367155E8)
                    // decode failed: Unknown instruction: '0x0008: UNKNOWN(0xC4F3)'
                    r95[r71] = r214
                    // decode failed: Unknown instruction: '0x000B: UNKNOWN(0xEAEB)'
                    r218 = move-result
                    // decode failed: Unknown instruction: '0x000D: UNKNOWN(0x7FF6)'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass7.AnonymousClass1.<init>(com.Mode.toolbox.MainActivity$7, android.app.ProgressDialog):void");
            }

            /* renamed from: ۟ۤۦۢ۟, reason: not valid java name and contains not printable characters */
            public static native MainActivity m180(Object obj);

            /* renamed from: ۣ۠۟ۤ, reason: not valid java name and contains not printable characters */
            public static native ProgressDialog m181(Object obj);

            /* renamed from: ۣۡ۟ۥ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass7 m182(Object obj);

            /* renamed from: ۤۡۦۣ, reason: not valid java name and contains not printable characters */
            public static native short[] m183();

            @Override // java.lang.Runnable
            public native void run();
        }

        /* renamed from: com.Mode.toolbox.MainActivity$7$2, reason: invalid class name */
        class AnonymousClass2 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f39short = null;
            final /* synthetic */ AnonymousClass1 this$2;
            final /* synthetic */ boolean val$success;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0001: SPUT r30
                java.lang.IllegalArgumentException: newPosition > limit: (145240 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0014: SPUT r99
                java.lang.IllegalArgumentException: newPosition > limit: (187376 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0016: SPUT r183
                java.lang.IllegalArgumentException: newPosition > limit: (148260 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x001B: INVOKE_INTERFACE_RANGE r2057, r2058, r2059, r2060, r2061, r2062, r2063, r2064, r2065, r2066, r2067, r2068, r2069, r2070, r2071, r2072, r2073, r2074, r2075, r2076, r2077, r2078, r2079, r2080, r2081, r2082, r2083, r2084, r2085, r2086, r2087, r2088, r2089, r2090, r2091, r2092, r2093, r2094, r2095, r2096, r2097, r2098
                java.lang.IllegalArgumentException: newPosition > limit: (248520 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0020: SGET r35
                java.lang.IllegalArgumentException: newPosition > limit: (340032 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0024: IGET r14, r14
                java.lang.IllegalArgumentException: newPosition > limit: (141356 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:184)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0037: SPUT r112
                java.lang.IllegalArgumentException: newPosition > limit: (422456 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0001: SPUT r30
                java.lang.IllegalArgumentException: newPosition > limit: (145240 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0014: SPUT r99
                java.lang.IllegalArgumentException: newPosition > limit: (187376 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0016: SPUT r183
                java.lang.IllegalArgumentException: newPosition > limit: (148260 > 104176)
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
                */
            /*  JADX ERROR: Failed to decode insn: 0x001B: INVOKE_INTERFACE_RANGE r2057, r2058, r2059, r2060, r2061, r2062, r2063, r2064, r2065, r2066, r2067, r2068, r2069, r2070, r2071, r2072, r2073, r2074, r2075, r2076, r2077, r2078, r2079, r2080, r2081, r2082, r2083, r2084, r2085, r2086, r2087, r2088, r2089, r2090, r2091, r2092, r2093, r2094, r2095, r2096, r2097, r2098
                java.lang.IllegalArgumentException: newPosition > limit: (248520 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:459)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0020: SGET r35
                java.lang.IllegalArgumentException: newPosition > limit: (340032 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0024: IGET r14, r14
                java.lang.IllegalArgumentException: newPosition > limit: (141356 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:184)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:370)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0028: UNKNOWN(0x40EF)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0028: UNKNOWN(0x40EF)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x002F: UNKNOWN(0xE442)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x002F: UNKNOWN(0xE442)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0035: UNKNOWN(0x3473)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0035: UNKNOWN(0x3473)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0037: SPUT r112
                java.lang.IllegalArgumentException: newPosition > limit: (422456 > 104176)
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
            static {
                /*
                    int r15 = r15 >>> r8
                    // decode failed: newPosition > limit: (145240 > 104176)
                    int r167 = r65 + r94
                    float r15 = r15 + r15
                    r142 = r222[r0]
                    int r168 = r82 + r221
                    int r1 = (int) r10
                    r1 = r2
                    r1 = r3
                    r25049 = r32369
                    long r10 = -r11
                    int r59 = r201 >>> r89
                    char r12 = (char) r13
                    // decode failed: newPosition > limit: (187376 > 104176)
                    // decode failed: newPosition > limit: (148260 > 104176)
                    super/*java.lang.reflect.Method*/.invoke(r47248, r47249)
                    // decode failed: newPosition > limit: (248520 > 104176)
                    goto L4d8e
                    // decode failed: newPosition > limit: (340032 > 104176)
                    long r12 = r12 | r12
                    monitor-exit(r218)
                    // decode failed: newPosition > limit: (141356 > 104176)
                    long r44 = r154 >> r148
                    // decode failed: Unknown instruction: '0x0028: UNKNOWN(0x40EF)'
                    double r7 = (double) r12
                    float r15 = -r14
                    float r1 = (float) r2
                    return
                    r216 = r35305
                    // decode failed: Unknown instruction: '0x002F: UNKNOWN(0xE442)'
                    if (r10 == r14) goto L4f5f
                    float r1 = r1 + r4
                    long r41 = r214 - r63
                    // decode failed: Unknown instruction: '0x0035: UNKNOWN(0x3473)'
                    monitor-exit(r59)
                    // decode failed: newPosition > limit: (422456 > 104176)
                    r44 = -5922514984968912896(0xadcf000000000000, double:-4.869820717575345E-88)
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass7.AnonymousClass2.<clinit>():void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0003: SPUT r13
                java.lang.IllegalArgumentException: newPosition > limit: (492560 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x3BE6)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x3BE6)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0003: SPUT r13
                java.lang.IllegalArgumentException: newPosition > limit: (492560 > 104176)
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
            AnonymousClass2(com.Mode.toolbox.MainActivity.AnonymousClass7.AnonymousClass1 r52, boolean r53) {
                /*
                    r51 = this;
                    // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x3BE6)'
                    int r2 = r10 / 16098
                    // decode failed: newPosition > limit: (492560 > 104176)
                    switch(r192) {
                    // error: 0x0005: SWITCH (r192 I:??)no payload
                    if (r216 <= 0) goto LB_4d32
                    r14 = r2
                    r248 = 30355(0x7693, float:4.2536E-41)
                    java.lang.String r130 = "DNS_POOL"
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass7.AnonymousClass2.<init>(com.Mode.toolbox.MainActivity$7$1, boolean):void");
            }

            /* renamed from: ۟۟ۥۣ۟, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass7 m184(Object obj);

            /* renamed from: ۟۠ۧ۠۟, reason: not valid java name and contains not printable characters */
            public static native MainActivity m185(Object obj);

            /* renamed from: ۟ۧۡۦۧ, reason: not valid java name and contains not printable characters */
            public static native boolean m186(Object obj);

            /* renamed from: ۡ۠ۥۨ, reason: not valid java name and contains not printable characters */
            public static native short[] m187();

            /* renamed from: ۤۦۣ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass1 m188(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.7.<clinit>():void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
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
            	... 7 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.7.<clinit>():void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass7.<clinit>():void");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0006: UNKNOWN(0x76E6)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0006: UNKNOWN(0x76E6)'
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
        AnonymousClass7(com.Mode.toolbox.MainActivity r52) {
            /*
                r51 = this;
                int r112 = r21 << (-126)
                int r231 = r211 * (-20)
                r9 = r12 & 29216(0x7220, float:4.094E-41)
                // decode failed: Unknown instruction: '0x0006: UNKNOWN(0x76E6)'
                r175 = -637272064(0xffffffffda040000, float:-9.288674E15)
                android.widget.TextView r233 = com.Mode.toolbox.BackupActivity.backupSizeText
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass7.<init>(com.Mode.toolbox.MainActivity):void");
        }

        /* renamed from: ۣ۟۟ۨۢ, reason: not valid java name and contains not printable characters */
        public static native int m177(Object obj);

        /* renamed from: ۤۡۥۣ, reason: not valid java name and contains not printable characters */
        public static native short[] m178();

        /* renamed from: ۨۦۦۡ, reason: not valid java name and contains not printable characters */
        public static native MainActivity m179(Object obj);

        @Override // android.view.View.OnClickListener
        public native void onClick(View view);
    }

    /* renamed from: com.Mode.toolbox.MainActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f40short = null;
        final /* synthetic */ MainActivity val$activity;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.8.<clinit>():void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
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
            	... 7 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.8.<clinit>():void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass8.<clinit>():void");
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.8.<init>(com.Mode.toolbox.MainActivity, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
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
            	... 7 more
            */
        AnonymousClass8(com.Mode.toolbox.MainActivity r1, android.content.SharedPreferences r2) {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.8.<init>(com.Mode.toolbox.MainActivity, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass8.<init>(com.Mode.toolbox.MainActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۣۡۨۤ, reason: not valid java name and contains not printable characters */
        public static native short[] m189();

        /* renamed from: ۢۧ, reason: not valid java name and contains not printable characters */
        public static native MainActivity m190(Object obj);

        /* renamed from: ۦۡۡ۠, reason: contains not printable characters */
        public static native SharedPreferences m191(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.MainActivity$9, reason: invalid class name */
    class AnonymousClass9 implements DialogInterface.OnClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f41short = null;
        final /* synthetic */ MainActivity this$0;

        /* renamed from: com.Mode.toolbox.MainActivity$9$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f42short = null;
            final /* synthetic */ AnonymousClass9 this$1;
            final /* synthetic */ ProgressDialog val$dialog;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0005: SGET r65
                java.lang.IllegalArgumentException: newPosition > limit: (168300 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0007: IPUT r12, r11
                java.lang.IllegalArgumentException: newPosition > limit: (385720 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0011: INVOKE_SUPER_RANGE r30794, r30795, r30796, r30797, r30798, r30799, r30800, r30801, r30802, r30803, r30804, r30805, r30806, r30807, r30808, r30809, r30810, r30811, r30812, r30813, r30814, r30815, r30816, r30817, r30818, r30819, r30820, r30821, r30822, r30823, r30824, r30825, r30826, r30827, r30828, r30829, r30830, r30831, r30832, r30833, r30834, r30835, r30836, r30837, r30838, r30839, r30840, r30841, r30842, r30843, r30844, r30845, r30846, r30847, r30848, r30849, r30850, r30851, r30852, r30853, r30854, r30855, r30856, r30857, r30858, r30859, r30860, r30861, r30862, r30863, r30864, r30865, r30866, r30867, r30868, r30869, r30870, r30871, r30872, r30873, r30874, r30875, r30876, r30877, r30878, r30879, r30880, r30881, r30882, r30883, r30884, r30885, r30886, r30887, r30888, r30889, r30890, r30891, r30892, r30893, r30894, r30895, r30896, r30897, r30898, r30899, r30900, r30901, r30902, r30903, r30904, r30905, r30906, r30907, r30908, r30909, r30910, r30911, r30912, r30913, r30914, r30915, r30916, r30917, r30918, r30919, r30920, r30921, r30922, r30923, r30924, r30925, r30926, r30927, r30928, r30929, r30930, r30931, r30932, r30933, r30934, r30935, r30936, r30937, r30938, r30939, r30940, r30941, r30942, r30943, r30944, r30945, r30946, r30947, r30948, r30949, r30950, r30951, r30952, r30953, r30954, r30955, r30956, r30957, r30958, r30959, r30960, r30961, r30962, r30963, r30964, r30965, r30966, r30967, r30968, r30969, r30970, r30971, r30972, r30973, r30974, r30975, r30976, r30977, r30978, r30979, r30980, r30981, r30982, r30983, r30984, r30985, r30986, r30987, r30988, r30989, r30990, r30991, r30992, r30993, r30994, r30995, r30996, r30997, r30998, r30999, r31000, r31001, r31002, r31003, r31004, r31005, r31006, r31007, r31008, r31009, r31010
                java.lang.IllegalArgumentException: newPosition > limit: (484928 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0016: SGET r78
                java.lang.IllegalArgumentException: newPosition > limit: (138056 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x001A: INVOKE_CUSTOM_RANGE r54350, r54351, r54352, r54353, r54354, r54355, r54356, r54357, r54358, r54359, r54360, r54361, r54362, r54363, r54364, r54365, r54366, r54367, r54368, r54369, r54370, r54371, r54372, r54373, r54374, r54375, r54376, r54377, r54378, r54379, r54380, r54381, r54382, r54383, r54384, r54385, r54386, r54387, r54388, r54389, r54390, r54391, r54392, r54393, r54394, r54395, r54396, r54397, r54398, r54399, r54400, r54401, r54402, r54403, r54404, r54405, r54406, r54407, r54408, r54409, r54410, r54411, r54412, r54413, r54414, r54415, r54416, r54417, r54418, r54419, r54420, r54421, r54422, r54423, r54424, r54425, r54426, r54427, r54428, r54429, r54430, r54431, r54432, r54433, r54434, r54435, r54436, r54437, r54438, r54439, r54440, r54441, r54442
                java.lang.IllegalArgumentException: newPosition > limit: (247996 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:207)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x001D: FILLED_NEW_ARRAY r9, r5, r11, r14, r5
                java.lang.IllegalArgumentException: newPosition > limit: (127492 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0029: IPUT r2, r6
                java.lang.IllegalArgumentException: newPosition > limit: (286424 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0033: IGET r6, r5
                java.lang.IllegalArgumentException: newPosition > limit: (182832 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0035: INVOKE_STATIC_RANGE r57538, r57539, r57540, r57541, r57542, r57543, r57544, r57545, r57546, r57547, r57548, r57549, r57550, r57551, r57552, r57553, r57554, r57555, r57556, r57557, r57558, r57559, r57560, r57561, r57562, r57563, r57564, r57565, r57566, r57567, r57568, r57569, r57570, r57571, r57572, r57573, r57574, r57575, r57576, r57577, r57578, r57579, r57580, r57581, r57582, r57583, r57584, r57585, r57586, r57587, r57588, r57589, r57590, r57591, r57592, r57593, r57594, r57595, r57596, r57597, r57598, r57599, r57600, r57601, r57602, r57603, r57604, r57605, r57606, r57607, r57608, r57609, r57610, r57611, r57612, r57613, r57614, r57615, r57616, r57617, r57618, r57619, r57620, r57621, r57622, r57623, r57624, r57625, r57626, r57627, r57628, r57629, r57630, r57631, r57632, r57633, r57634, r57635, r57636, r57637, r57638, r57639, r57640, r57641, r57642, r57643, r57644, r57645, r57646, r57647, r57648, r57649, r57650, r57651, r57652, r57653, r57654, r57655, r57656, r57657, r57658, r57659, r57660, r57661, r57662, r57663, r57664, r57665, r57666, r57667, r57668, r57669, r57670, r57671, r57672, r57673, r57674, r57675, r57676, r57677, r57678, r57679, r57680, r57681, r57682, r57683, r57684, r57685, r57686, r57687, r57688, r57689
                java.lang.IllegalArgumentException: newPosition > limit: (376496 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x003B: IPUT r7, r8
                java.lang.IllegalArgumentException: newPosition > limit: (152560 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0043: SGET r64
                java.lang.IllegalArgumentException: newPosition < 0: (-1793407364 < 0)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0045: SGET r213
                java.lang.IllegalArgumentException: newPosition > limit: (435400 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x004B: SPUT r218
                java.lang.IllegalArgumentException: newPosition > limit: (261696 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0066: IPUT r3, r5
                java.lang.IllegalArgumentException: newPosition > limit: (349424 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x008B: IPUT r8, r11
                java.lang.IllegalArgumentException: newPosition > limit: (374304 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0091: CONST_CLASS r203
                java.lang.IllegalArgumentException: newPosition > limit: (237464 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0093: IGET r2, r0
                java.lang.IllegalArgumentException: newPosition > limit: (334432 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0097: SPUT r63
                java.lang.IllegalArgumentException: newPosition > limit: (324568 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x009B: IGET r4, r14
                java.lang.IllegalArgumentException: newPosition > limit: (186344 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00A6: FILLED_NEW_ARRAY_RANGE r54754, r54755, r54756, r54757, r54758, r54759, r54760, r54761, r54762, r54763, r54764, r54765, r54766, r54767, r54768, r54769, r54770, r54771, r54772, r54773, r54774, r54775, r54776, r54777, r54778, r54779, r54780, r54781, r54782
                java.lang.IllegalArgumentException: newPosition < 0: (-288762344 < 0)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00AD: IGET r14, r0
                java.lang.IllegalArgumentException: newPosition > limit: (243456 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00B1: IGET r11, r4
                java.lang.IllegalArgumentException: newPosition > limit: (226416 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00B4: SPUT r192
                java.lang.IllegalArgumentException: newPosition > limit: (297472 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00B6: INVOKE_SUPER r10, r6, r14, r8, r5, r54759, r54760, r54761, r54762, r54763, r54764, r54765, r54766
                java.lang.IllegalArgumentException: newPosition > limit: (231960 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00B9: IGET r4, r9
                java.lang.IllegalArgumentException: newPosition > limit: (111816 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00C0: INVOKE_DIRECT_RANGE r11045, r11046, r11047, r11048, r11049, r11050, r11051, r11052, r11053, r11054, r11055, r11056, r11057, r11058, r11059, r11060, r11061, r11062, r11063, r11064, r11065, r11066, r11067, r11068, r11069, r11070
                java.lang.IllegalArgumentException: newPosition > limit: (277520 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00CC: IGET r2, r14
                java.lang.IllegalArgumentException: newPosition > limit: (398240032 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00DE: SGET r71
                java.lang.IllegalArgumentException: newPosition > limit: (349472 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00E3: NEW_ARRAY r12, r0
                java.lang.IllegalArgumentException: newPosition > limit: (172832 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00F8: IGET r1, r15
                java.lang.IllegalArgumentException: newPosition > limit: (115272 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00FA: INSTANCE_OF r8, r7
                java.lang.IllegalArgumentException: newPosition > limit: (262168 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x00FE: IPUT r10, r11
                java.lang.IllegalArgumentException: newPosition > limit: (407320 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x011A: SPUT r120
                java.lang.IllegalArgumentException: newPosition > limit: (123580 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x012E: IPUT r5, r14
                java.lang.IllegalArgumentException: newPosition > limit: (127848 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0141: SGET r72
                java.lang.IllegalArgumentException: newPosition > limit: (158136 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0145: CONST_CLASS r252
                java.lang.IllegalArgumentException: newPosition < 0: (-23841904 < 0)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x014D: IGET r15, r0
                java.lang.IllegalArgumentException: newPosition > limit: (171024 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0152: SGET r11
                java.lang.IllegalArgumentException: newPosition > limit: (157480 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0161: IPUT r15, r9
                java.lang.IllegalArgumentException: newPosition > limit: (219104 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0164: NEW_ARRAY r2, r2
                java.lang.IllegalArgumentException: newPosition > limit: (165036 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0166: IGET r5, r8
                java.lang.IllegalArgumentException: newPosition > limit: (237984 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0001: UNKNOWN(0x5FF1)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0001: UNKNOWN(0x5FF1)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0005: SGET r65
                java.lang.IllegalArgumentException: newPosition > limit: (168300 > 104176)
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
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0007: IPUT r12, r11
                java.lang.IllegalArgumentException: newPosition > limit: (385720 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0010: UNKNOWN(0x8942)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0010: UNKNOWN(0x8942)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0011: INVOKE_SUPER_RANGE r30794, r30795, r30796, r30797, r30798, r30799, r30800, r30801, r30802, r30803, r30804, r30805, r30806, r30807, r30808, r30809, r30810, r30811, r30812, r30813, r30814, r30815, r30816, r30817, r30818, r30819, r30820, r30821, r30822, r30823, r30824, r30825, r30826, r30827, r30828, r30829, r30830, r30831, r30832, r30833, r30834, r30835, r30836, r30837, r30838, r30839, r30840, r30841, r30842, r30843, r30844, r30845, r30846, r30847, r30848, r30849, r30850, r30851, r30852, r30853, r30854, r30855, r30856, r30857, r30858, r30859, r30860, r30861, r30862, r30863, r30864, r30865, r30866, r30867, r30868, r30869, r30870, r30871, r30872, r30873, r30874, r30875, r30876, r30877, r30878, r30879, r30880, r30881, r30882, r30883, r30884, r30885, r30886, r30887, r30888, r30889, r30890, r30891, r30892, r30893, r30894, r30895, r30896, r30897, r30898, r30899, r30900, r30901, r30902, r30903, r30904, r30905, r30906, r30907, r30908, r30909, r30910, r30911, r30912, r30913, r30914, r30915, r30916, r30917, r30918, r30919, r30920, r30921, r30922, r30923, r30924, r30925, r30926, r30927, r30928, r30929, r30930, r30931, r30932, r30933, r30934, r30935, r30936, r30937, r30938, r30939, r30940, r30941, r30942, r30943, r30944, r30945, r30946, r30947, r30948, r30949, r30950, r30951, r30952, r30953, r30954, r30955, r30956, r30957, r30958, r30959, r30960, r30961, r30962, r30963, r30964, r30965, r30966, r30967, r30968, r30969, r30970, r30971, r30972, r30973, r30974, r30975, r30976, r30977, r30978, r30979, r30980, r30981, r30982, r30983, r30984, r30985, r30986, r30987, r30988, r30989, r30990, r30991, r30992, r30993, r30994, r30995, r30996, r30997, r30998, r30999, r31000, r31001, r31002, r31003, r31004, r31005, r31006, r31007, r31008, r31009, r31010
                java.lang.IllegalArgumentException: newPosition > limit: (484928 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0016: SGET r78
                java.lang.IllegalArgumentException: newPosition > limit: (138056 > 104176)
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
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x001A: INVOKE_CUSTOM_RANGE r54350, r54351, r54352, r54353, r54354, r54355, r54356, r54357, r54358, r54359, r54360, r54361, r54362, r54363, r54364, r54365, r54366, r54367, r54368, r54369, r54370, r54371, r54372, r54373, r54374, r54375, r54376, r54377, r54378, r54379, r54380, r54381, r54382, r54383, r54384, r54385, r54386, r54387, r54388, r54389, r54390, r54391, r54392, r54393, r54394, r54395, r54396, r54397, r54398, r54399, r54400, r54401, r54402, r54403, r54404, r54405, r54406, r54407, r54408, r54409, r54410, r54411, r54412, r54413, r54414, r54415, r54416, r54417, r54418, r54419, r54420, r54421, r54422, r54423, r54424, r54425, r54426, r54427, r54428, r54429, r54430, r54431, r54432, r54433, r54434, r54435, r54436, r54437, r54438, r54439, r54440, r54441, r54442
                jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: newPosition > limit: (247996 > 104176)
                	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:597)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:465)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                Caused by: java.lang.IllegalArgumentException: newPosition > limit: (247996 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:207)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
                	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
                	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
                	... 8 more
                */
            /*  JADX ERROR: Failed to decode insn: 0x001D: FILLED_NEW_ARRAY r9, r5, r11, r14, r5
                java.lang.IllegalArgumentException: newPosition > limit: (127492 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:484)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0029: IPUT r2, r6
                java.lang.IllegalArgumentException: newPosition > limit: (286424 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0033: IGET r6, r5
                java.lang.IllegalArgumentException: newPosition > limit: (182832 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0035: INVOKE_STATIC_RANGE r57538, r57539, r57540, r57541, r57542, r57543, r57544, r57545, r57546, r57547, r57548, r57549, r57550, r57551, r57552, r57553, r57554, r57555, r57556, r57557, r57558, r57559, r57560, r57561, r57562, r57563, r57564, r57565, r57566, r57567, r57568, r57569, r57570, r57571, r57572, r57573, r57574, r57575, r57576, r57577, r57578, r57579, r57580, r57581, r57582, r57583, r57584, r57585, r57586, r57587, r57588, r57589, r57590, r57591, r57592, r57593, r57594, r57595, r57596, r57597, r57598, r57599, r57600, r57601, r57602, r57603, r57604, r57605, r57606, r57607, r57608, r57609, r57610, r57611, r57612, r57613, r57614, r57615, r57616, r57617, r57618, r57619, r57620, r57621, r57622, r57623, r57624, r57625, r57626, r57627, r57628, r57629, r57630, r57631, r57632, r57633, r57634, r57635, r57636, r57637, r57638, r57639, r57640, r57641, r57642, r57643, r57644, r57645, r57646, r57647, r57648, r57649, r57650, r57651, r57652, r57653, r57654, r57655, r57656, r57657, r57658, r57659, r57660, r57661, r57662, r57663, r57664, r57665, r57666, r57667, r57668, r57669, r57670, r57671, r57672, r57673, r57674, r57675, r57676, r57677, r57678, r57679, r57680, r57681, r57682, r57683, r57684, r57685, r57686, r57687, r57688, r57689
                java.lang.IllegalArgumentException: newPosition > limit: (376496 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:439)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x003B: IPUT r7, r8
                java.lang.IllegalArgumentException: newPosition > limit: (152560 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x003D: UNKNOWN(0xDAF1)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x003D: UNKNOWN(0xDAF1)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x003E: UNKNOWN(0x1200)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x003E: UNKNOWN(0x1200)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x003F: UNKNOWN(0x6FF8)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x003F: UNKNOWN(0x6FF8)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0043: SGET r64
                java.lang.IllegalArgumentException: newPosition < 0: (-1793407364 < 0)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
                	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
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
            /*  JADX ERROR: Failed to decode insn: 0x0045: SGET r213
                java.lang.IllegalArgumentException: newPosition > limit: (435400 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x004B: SPUT r218
                java.lang.IllegalArgumentException: newPosition > limit: (261696 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0065: UNKNOWN(0x943E)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0065: UNKNOWN(0x943E)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0066: IPUT r3, r5
                java.lang.IllegalArgumentException: newPosition > limit: (349424 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0068: UNKNOWN(0xA8F6)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0068: UNKNOWN(0xA8F6)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x006D: CONST_METHOD_TYPE r38
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x006D: CONST_METHOD_TYPE r38'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0071: UNKNOWN(0x0079)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0071: UNKNOWN(0x0079)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0072: UNKNOWN(0x07ED)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0072: UNKNOWN(0x07ED)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x007F: UNKNOWN(0xE2F8)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x007F: UNKNOWN(0xE2F8)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0080: UNKNOWN(0x13F7)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0080: UNKNOWN(0x13F7)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0083: UNKNOWN(0x4EF6)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0083: UNKNOWN(0x4EF6)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0085: UNKNOWN(0x79F5)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0085: UNKNOWN(0x79F5)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0089: UNKNOWN(0x2F3E)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0089: UNKNOWN(0x2F3E)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x008A: UNKNOWN(0x6040)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x008A: UNKNOWN(0x6040)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x008B: IPUT r8, r11
                java.lang.IllegalArgumentException: newPosition > limit: (374304 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0090: UNKNOWN(0x74F2)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0090: UNKNOWN(0x74F2)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0091: CONST_CLASS r203
                java.lang.IllegalArgumentException: newPosition > limit: (237464 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0093: IGET r2, r0
                java.lang.IllegalArgumentException: newPosition > limit: (334432 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0097: SPUT r63
                java.lang.IllegalArgumentException: newPosition > limit: (324568 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x009B: IGET r4, r14
                java.lang.IllegalArgumentException: newPosition > limit: (186344 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x009D: UNKNOWN(0xA6EF)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x009D: UNKNOWN(0xA6EF)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00A6: FILLED_NEW_ARRAY_RANGE r54754, r54755, r54756, r54757, r54758, r54759, r54760, r54761, r54762, r54763, r54764, r54765, r54766, r54767, r54768, r54769, r54770, r54771, r54772, r54773, r54774, r54775, r54776, r54777, r54778, r54779, r54780, r54781, r54782
                java.lang.IllegalArgumentException: newPosition < 0: (-288762344 < 0)
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
                */
            /*  JADX ERROR: Failed to decode insn: 0x00AD: IGET r14, r0
                java.lang.IllegalArgumentException: newPosition > limit: (243456 > 104176)
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
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00B1: IGET r11, r4
                java.lang.IllegalArgumentException: newPosition > limit: (226416 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x00B3: UNKNOWN(0x287A)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00B3: UNKNOWN(0x287A)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00B4: SPUT r192
                java.lang.IllegalArgumentException: newPosition > limit: (297472 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x00B6: INVOKE_SUPER r10, r6, r14, r8, r5, r54759, r54760, r54761, r54762, r54763, r54764, r54765, r54766
                java.lang.IllegalArgumentException: newPosition > limit: (231960 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:446)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00B9: IGET r4, r9
                java.lang.IllegalArgumentException: newPosition > limit: (111816 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x00C0: INVOKE_DIRECT_RANGE r11045, r11046, r11047, r11048, r11049, r11050, r11051, r11052, r11053, r11054, r11055, r11056, r11057, r11058, r11059, r11060, r11061, r11062, r11063, r11064, r11065, r11066, r11067, r11068, r11069, r11070
                java.lang.IllegalArgumentException: newPosition > limit: (277520 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x00CC: IGET r2, r14
                java.lang.IllegalArgumentException: newPosition > limit: (398240032 > 104176)
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
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00CE: UNKNOWN(0xA9EF)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00CE: UNKNOWN(0xA9EF)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00CF: CONST_METHOD_HANDLE r101
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00CF: CONST_METHOD_HANDLE r101'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00D6: UNKNOWN(0x19EB)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00D6: UNKNOWN(0x19EB)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00DE: SGET r71
                java.lang.IllegalArgumentException: newPosition > limit: (349472 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x00E0: UNKNOWN(0xDFEC)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00E0: UNKNOWN(0xDFEC)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00E1: UNKNOWN(0xA443)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00E1: UNKNOWN(0xA443)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00E3: NEW_ARRAY r12, r0
                java.lang.IllegalArgumentException: newPosition > limit: (172832 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.instructions.InsnDecoder.makeNewArray(InsnDecoder.java:524)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:476)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00EB: UNKNOWN(0x3A73)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00EB: UNKNOWN(0x3A73)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00F8: IGET r1, r15
                java.lang.IllegalArgumentException: newPosition > limit: (115272 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x00FA: INSTANCE_OF r8, r7
                java.lang.IllegalArgumentException: newPosition > limit: (262168 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:357)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x00FE: IPUT r10, r11
                java.lang.IllegalArgumentException: newPosition > limit: (407320 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0102: UNKNOWN(0x0AE3)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0102: UNKNOWN(0x0AE3)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x010A: UNKNOWN(0x9342)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x010A: UNKNOWN(0x9342)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0112: CONST_METHOD_TYPE r54
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0112: CONST_METHOD_TYPE r54'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0114: UNKNOWN(0x433E)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0114: UNKNOWN(0x433E)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0117: UNKNOWN(0xFFE5)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0117: UNKNOWN(0xFFE5)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x011A: SPUT r120
                java.lang.IllegalArgumentException: newPosition > limit: (123580 > 104176)
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
                */
            /*  JADX ERROR: Failed to decode insn: 0x0127: UNKNOWN(0xB2E6)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0127: UNKNOWN(0xB2E6)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x012E: IPUT r5, r14
                java.lang.IllegalArgumentException: newPosition > limit: (127848 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0135: UNKNOWN(0x3600)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0135: UNKNOWN(0x3600)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x013D: UNKNOWN(0xD7F5)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x013D: UNKNOWN(0xD7F5)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0141: SGET r72
                java.lang.IllegalArgumentException: newPosition > limit: (158136 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0145: CONST_CLASS r252
                java.lang.IllegalArgumentException: newPosition < 0: (-23841904 < 0)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x014D: IGET r15, r0
                java.lang.IllegalArgumentException: newPosition > limit: (171024 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x014F: UNKNOWN(0x2CE4)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x014F: UNKNOWN(0x2CE4)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0152: SGET r11
                java.lang.IllegalArgumentException: newPosition > limit: (157480 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x015F: UNKNOWN(0x46EC)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x015F: UNKNOWN(0x46EC)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0160: UNKNOWN(0x49E3)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0160: UNKNOWN(0x49E3)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0161: IPUT r15, r9
                java.lang.IllegalArgumentException: newPosition > limit: (219104 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0164: NEW_ARRAY r2, r2
                java.lang.IllegalArgumentException: newPosition > limit: (165036 > 104176)
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
                */
            /*  JADX ERROR: Failed to decode insn: 0x0166: IGET r5, r8
                java.lang.IllegalArgumentException: newPosition > limit: (237984 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x016C: UNKNOWN(0xF6EA)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x016C: UNKNOWN(0xF6EA)'
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
                    Method dump skipped, instructions count: 367
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass9.AnonymousClass1.<clinit>():void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0004: SPUT r227
                java.lang.IllegalArgumentException: newPosition > limit: (256704 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0006: IGET r13, r13
                java.lang.IllegalArgumentException: newPosition > limit: (515416 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0xC0F3)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0xC0F3)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0004: SPUT r227
                java.lang.IllegalArgumentException: newPosition > limit: (256704 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0006: IGET r13, r13
                java.lang.IllegalArgumentException: newPosition > limit: (515416 > 104176)
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
            AnonymousClass1(com.Mode.toolbox.MainActivity.AnonymousClass9 r52, android.app.ProgressDialog r53) {
                /*
                    r51 = this;
                    // decode failed: Unknown instruction: '0x0000: UNKNOWN(0xC0F3)'
                    long r150 = r117 ^ r131
                    long r14 = r14 >>> r3
                    // decode failed: newPosition > limit: (256704 > 104176)
                    // decode failed: newPosition > limit: (515416 > 104176)
                    if (r13 > r9) goto LB_4515
                    float r12 = r12 + r7
                    int r9 = (int) r10
                    float r163 = r84 / r57
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass9.AnonymousClass1.<init>(com.Mode.toolbox.MainActivity$9, android.app.ProgressDialog):void");
            }

            /* renamed from: ۟ۥۦ, reason: not valid java name and contains not printable characters */
            public static native MainActivity m195(Object obj);

            /* renamed from: ۟ۦۥۨۧ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass9 m196(Object obj);

            /* renamed from: ۠ۧۦۡ, reason: not valid java name and contains not printable characters */
            public static native ProgressDialog m197(Object obj);

            /* renamed from: ۧۨ۠۟, reason: not valid java name and contains not printable characters */
            public static native short[] m198();

            @Override // java.lang.Runnable
            public native void run();
        }

        /* renamed from: com.Mode.toolbox.MainActivity$9$2, reason: invalid class name */
        class AnonymousClass2 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f43short = null;
            final /* synthetic */ AnonymousClass1 this$2;
            final /* synthetic */ int val$count;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0007: SGET r190
                java.lang.IllegalArgumentException: newPosition > limit: (232072 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0013: INSTANCE_OF r0, r3
                java.lang.IllegalArgumentException: newPosition > limit: (15525120 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x001D: NEW_INSTANCE r234
                java.lang.IllegalArgumentException: newPosition > limit: (190888 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0007: SGET r190
                java.lang.IllegalArgumentException: newPosition > limit: (232072 > 104176)
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
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0013: INSTANCE_OF r0, r3
                java.lang.IllegalArgumentException: newPosition > limit: (15525120 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:357)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x001A: UNKNOWN(0xF53F)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001A: UNKNOWN(0xF53F)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x001D: NEW_INSTANCE r234
                java.lang.IllegalArgumentException: newPosition > limit: (190888 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:470)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            static {
                /*
                    int r15 = (-30817) - r8
                    float r173 = r143 / r29
                    if (r7 >= r2) goto L4f4b
                    return r92
                    // decode failed: newPosition > limit: (232072 > 104176)
                    int r10 = (r155 > r142 ? 1 : (r155 == r142 ? 0 : -1))
                    long r15 = r15 / r0
                    r3 = r3 | r0
                    r131[r51] = r135
                    r107 = 831736646(0x31934b46, float:4.2868207E-9)
                    int r6 = r6 + r9
                    // decode failed: newPosition > limit: (15525120 > 104176)
                    monitor-enter(r178)
                    r183[r191] = r69
                    if (r184 > 0) goto L42
                    // decode failed: Unknown instruction: '0x001A: UNKNOWN(0xF53F)'
                    long r12 = r230 | r252
                    // decode failed: newPosition > limit: (190888 > 104176)
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass9.AnonymousClass2.<clinit>():void");
            }

            /*  JADX ERROR: Method load error
                jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.9.2.<init>(com.Mode.toolbox.MainActivity$9$1, int):void, file: /workspace/xh/fix_3950620.dex
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
                	at jadx.plugins.input.dex.insns.DexInsnData.toString(DexInsnData.java:251)
                	at java.base/java.lang.String.valueOf(String.java:4530)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	... 2 more
                */
            AnonymousClass2(com.Mode.toolbox.MainActivity.AnonymousClass9.AnonymousClass1 r1, int r2) {
                /*
                // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.9.2.<init>(com.Mode.toolbox.MainActivity$9$1, int):void, file: /workspace/xh/fix_3950620.dex
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass9.AnonymousClass2.<init>(com.Mode.toolbox.MainActivity$9$1, int):void");
            }

            /* renamed from: ۣۣ۟ۥ۟, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass1 m199(Object obj);

            /* renamed from: ۟ۧۦۨۦ, reason: not valid java name and contains not printable characters */
            public static native int m200(Object obj);

            /* renamed from: ۣۤۤ۠, reason: not valid java name and contains not printable characters */
            public static native MainActivity m201(Object obj);

            /* renamed from: ۧۨۥۧ, reason: not valid java name and contains not printable characters */
            public static native short[] m202();

            /* renamed from: ۣۨۨۤ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass9 m203(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0006: IPUT r15, r2
            java.lang.IllegalArgumentException: newPosition > limit: (412304 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0012: CONST_CLASS r52
            java.lang.IllegalArgumentException: newPosition > limit: (173516 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0014: SGET r140
            java.lang.IllegalArgumentException: newPosition > limit: (179184 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0006: IPUT r15, r2
            java.lang.IllegalArgumentException: newPosition > limit: (412304 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x000E: UNKNOWN(0xCAEC)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000E: UNKNOWN(0xCAEC)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0012: CONST_CLASS r52
            java.lang.IllegalArgumentException: newPosition > limit: (173516 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:86)
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
        /*  JADX ERROR: Failed to decode insn: 0x0014: SGET r140
            java.lang.IllegalArgumentException: newPosition > limit: (179184 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0017: UNKNOWN(0x37E8)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0017: UNKNOWN(0x37E8)'
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
                return r100
                long r15 = (long) r0
                r12 = r12 | r7
                r152 = 1568989423(0x5d84e0ef, double:7.751837726E-315)
                // decode failed: newPosition > limit: (412304 > 104176)
                long r195 = r131 | r80
                if (r118 < 0) goto LB_4047
                r210[r252] = r227
                // decode failed: Unknown instruction: '0x000E: UNKNOWN(0xCAEC)'
                float r162 = r200 / r30
                long r11 = r11 & r5
                // decode failed: newPosition > limit: (173516 > 104176)
                // decode failed: newPosition > limit: (179184 > 104176)
                double r6 = r6 / r9
                // decode failed: Unknown instruction: '0x0017: UNKNOWN(0x37E8)'
                r29 = move-exception
                if (r172 > 0) goto LB_780
                r53[r0] = r239
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass9.<clinit>():void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0001: INVOKE_VIRTUAL_RANGE r11132, r11133, r11134, r11135, r11136, r11137, r11138, r11139, r11140, r11141, r11142, r11143, r11144, r11145, r11146, r11147, r11148, r11149, r11150, r11151, r11152, r11153, r11154, r11155, r11156, r11157, r11158, r11159, r11160, r11161, r11162, r11163, r11164, r11165, r11166, r11167, r11168, r11169, r11170, r11171, r11172, r11173, r11174, r11175, r11176, r11177, r11178, r11179, r11180, r11181, r11182, r11183, r11184, r11185, r11186, r11187, r11188, r11189, r11190, r11191, r11192, r11193, r11194, r11195, r11196, r11197, r11198, r11199, r11200, r11201, r11202, r11203, r11204, r11205, r11206, r11207, r11208, r11209, r11210, r11211, r11212, r11213, r11214, r11215, r11216, r11217, r11218, r11219, r11220, r11221, r11222, r11223, r11224, r11225, r11226, r11227, r11228, r11229, r11230, r11231, r11232, r11233, r11234, r11235, r11236, r11237, r11238, r11239, r11240, r11241, r11242, r11243, r11244, r11245, r11246, r11247, r11248, r11249, r11250, r11251, r11252, r11253, r11254, r11255, r11256, r11257, r11258, r11259, r11260, r11261, r11262, r11263, r11264, r11265, r11266, r11267, r11268, r11269, r11270, r11271, r11272, r11273, r11274, r11275, r11276, r11277, r11278, r11279, r11280, r11281, r11282, r11283, r11284, r11285, r11286, r11287, r11288, r11289, r11290, r11291, r11292, r11293, r11294, r11295, r11296, r11297, r11298, r11299, r11300, r11301, r11302, r11303, r11304, r11305, r11306, r11307, r11308, r11309, r11310, r11311, r11312, r11313, r11314, r11315, r11316, r11317, r11318, r11319, r11320, r11321, r11322, r11323, r11324, r11325, r11326, r11327, r11328, r11329, r11330, r11331, r11332, r11333, r11334, r11335, r11336, r11337, r11338, r11339, r11340, r11341, r11342, r11343, r11344, r11345, r11346, r11347, r11348, r11349, r11350, r11351, r11352, r11353, r11354, r11355, r11356, r11357, r11358, r11359, r11360, r11361, r11362, r11363, r11364, r11365, r11366, r11367, r11368, r11369, r11370
            java.lang.IllegalArgumentException: newPosition > limit: (342288 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0xE6E9)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0xE6E9)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0001: INVOKE_VIRTUAL_RANGE r11132, r11133, r11134, r11135, r11136, r11137, r11138, r11139, r11140, r11141, r11142, r11143, r11144, r11145, r11146, r11147, r11148, r11149, r11150, r11151, r11152, r11153, r11154, r11155, r11156, r11157, r11158, r11159, r11160, r11161, r11162, r11163, r11164, r11165, r11166, r11167, r11168, r11169, r11170, r11171, r11172, r11173, r11174, r11175, r11176, r11177, r11178, r11179, r11180, r11181, r11182, r11183, r11184, r11185, r11186, r11187, r11188, r11189, r11190, r11191, r11192, r11193, r11194, r11195, r11196, r11197, r11198, r11199, r11200, r11201, r11202, r11203, r11204, r11205, r11206, r11207, r11208, r11209, r11210, r11211, r11212, r11213, r11214, r11215, r11216, r11217, r11218, r11219, r11220, r11221, r11222, r11223, r11224, r11225, r11226, r11227, r11228, r11229, r11230, r11231, r11232, r11233, r11234, r11235, r11236, r11237, r11238, r11239, r11240, r11241, r11242, r11243, r11244, r11245, r11246, r11247, r11248, r11249, r11250, r11251, r11252, r11253, r11254, r11255, r11256, r11257, r11258, r11259, r11260, r11261, r11262, r11263, r11264, r11265, r11266, r11267, r11268, r11269, r11270, r11271, r11272, r11273, r11274, r11275, r11276, r11277, r11278, r11279, r11280, r11281, r11282, r11283, r11284, r11285, r11286, r11287, r11288, r11289, r11290, r11291, r11292, r11293, r11294, r11295, r11296, r11297, r11298, r11299, r11300, r11301, r11302, r11303, r11304, r11305, r11306, r11307, r11308, r11309, r11310, r11311, r11312, r11313, r11314, r11315, r11316, r11317, r11318, r11319, r11320, r11321, r11322, r11323, r11324, r11325, r11326, r11327, r11328, r11329, r11330, r11331, r11332, r11333, r11334, r11335, r11336, r11337, r11338, r11339, r11340, r11341, r11342, r11343, r11344, r11345, r11346, r11347, r11348, r11349, r11350, r11351, r11352, r11353, r11354, r11355, r11356, r11357, r11358, r11359, r11360, r11361, r11362, r11363, r11364, r11365, r11366, r11367, r11368, r11369, r11370
            java.lang.IllegalArgumentException: newPosition > limit: (342288 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
            	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
            	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:463)
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
        AnonymousClass9(com.Mode.toolbox.MainActivity r52) {
            /*
                r51 = this;
                // decode failed: Unknown instruction: '0x0000: UNKNOWN(0xE6E9)'
                // decode failed: newPosition > limit: (342288 > 104176)
                goto LB_35
                r8 = r14 ^ 7687(0x1e07, float:1.0772E-41)
                long r10 = r10 + r6
                goto LB_314
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.AnonymousClass9.<init>(com.Mode.toolbox.MainActivity):void");
        }

        /* renamed from: ۣ۟ۦ۠ۢ, reason: not valid java name and contains not printable characters */
        public static native int m192(Object obj);

        /* renamed from: ۤۥۥ, reason: not valid java name and contains not printable characters */
        public static native short[] m193();

        /* renamed from: ۥۣۧۤ, reason: contains not printable characters */
        public static native MainActivity m194(Object obj);

        @Override // android.content.DialogInterface.OnClickListener
        public native void onClick(DialogInterface dialogInterface, int i);
    }

    public class MyHandler extends Handler {
        private final WeakReference mOuter;

        /* renamed from: com.Mode.toolbox.MainActivity$MyHandler$1, reason: invalid class name */
        class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ MyHandler this$0;
            final /* synthetic */ TextView val$textView;

            /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
                jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x0000: MONITOR_ENTER (r227 I:??[OBJECT, ARRAY]), expected to be less than 52
                	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:79)
                	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
                */
            static {
                /*
                    monitor-enter(r227)
                    long r43 = r241 & r71
                    if (r75 <= 0) goto LB_44a2
                    r12 = move-result
                    monitor-exit(r99)
                    int r162 = r54 >>> 0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.MyHandler.AnonymousClass1.<clinit>():void");
            }

            /*  JADX ERROR: Dependency scan failed at insn: 0x0002: IGET r1, r13
                java.lang.IllegalArgumentException: newPosition > limit: (240664 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0004: INVOKE_VIRTUAL_RANGE r23075, r23076, r23077, r23078, r23079, r23080, r23081, r23082, r23083, r23084, r23085, r23086, r23087, r23088, r23089, r23090, r23091, r23092, r23093, r23094, r23095, r23096, r23097, r23098, r23099, r23100, r23101, r23102, r23103, r23104, r23105, r23106
                java.lang.IllegalArgumentException: newPosition > limit: (392296 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x000B: SGET r193
                java.lang.IllegalArgumentException: newPosition > limit: (381584 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0002: IGET r1, r13
                java.lang.IllegalArgumentException: newPosition > limit: (240664 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0004: INVOKE_VIRTUAL_RANGE r23075, r23076, r23077, r23078, r23079, r23080, r23081, r23082, r23083, r23084, r23085, r23086, r23087, r23088, r23089, r23090, r23091, r23092, r23093, r23094, r23095, r23096, r23097, r23098, r23099, r23100, r23101, r23102, r23103, r23104, r23105, r23106
                java.lang.IllegalArgumentException: newPosition > limit: (392296 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
                	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
                	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:463)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0009: UNKNOWN(0x6C41)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0009: UNKNOWN(0x6C41)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x000B: SGET r193
                java.lang.IllegalArgumentException: newPosition > limit: (381584 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x000D: UNKNOWN(0xE979)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000D: UNKNOWN(0xE979)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            AnonymousClass1(com.Mode.toolbox.MainActivity.MyHandler r52, android.widget.TextView r53) {
                /*
                    r51 = this;
                    long r231 = r253 << r77
                    // decode failed: newPosition > limit: (240664 > 104176)
                    // decode failed: newPosition > limit: (392296 > 104176)
                    if (r31 < 0) goto L728a
                    // decode failed: Unknown instruction: '0x0009: UNKNOWN(0x6C41)'
                    long r9 = r9 | r10
                    // decode failed: newPosition > limit: (381584 > 104176)
                    // decode failed: Unknown instruction: '0x000D: UNKNOWN(0xE979)'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.MyHandler.AnonymousClass1.<init>(com.Mode.toolbox.MainActivity$MyHandler, android.widget.TextView):void");
            }

            /*  JADX ERROR: Method load error
                jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.MyHandler.1.rfytK():void, file: /workspace/xh/fix_3950620.dex
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
                	at jadx.plugins.input.dex.insns.DexInsnData.toString(DexInsnData.java:251)
                	at java.base/java.lang.String.valueOf(String.java:4530)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	... 2 more
                */
            private static /* synthetic */ void rfytK() {
                /*
                // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MainActivity.MyHandler.1.rfytK():void, file: /workspace/xh/fix_3950620.dex
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.MyHandler.AnonymousClass1.rfytK():void");
            }

            /* renamed from: ۤ۟ۤ۟, reason: not valid java name and contains not printable characters */
            public static native TextView m205(Object obj);

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public native boolean onPreDraw();
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0000: INVOKE_DIRECT_RANGE r11737, r11738, r11739, r11740, r11741, r11742, r11743, r11744, r11745, r11746, r11747, r11748, r11749, r11750, r11751, r11752, r11753, r11754, r11755, r11756, r11757, r11758, r11759, r11760, r11761, r11762, r11763, r11764, r11765, r11766, r11767, r11768, r11769, r11770, r11771, r11772, r11773, r11774, r11775, r11776, r11777, r11778, r11779, r11780, r11781, r11782, r11783, r11784, r11785, r11786, r11787, r11788, r11789, r11790, r11791, r11792, r11793, r11794, r11795, r11796, r11797, r11798, r11799, r11800, r11801, r11802, r11803, r11804, r11805, r11806, r11807, r11808, r11809, r11810, r11811, r11812, r11813, r11814, r11815, r11816, r11817, r11818, r11819, r11820, r11821, r11822, r11823, r11824
            java.lang.IllegalArgumentException: newPosition > limit: (340536 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0004: NEW_ARRAY r9, r2
            java.lang.IllegalArgumentException: newPosition > limit: (160128 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: INVOKE_DIRECT_RANGE r11737, r11738, r11739, r11740, r11741, r11742, r11743, r11744, r11745, r11746, r11747, r11748, r11749, r11750, r11751, r11752, r11753, r11754, r11755, r11756, r11757, r11758, r11759, r11760, r11761, r11762, r11763, r11764, r11765, r11766, r11767, r11768, r11769, r11770, r11771, r11772, r11773, r11774, r11775, r11776, r11777, r11778, r11779, r11780, r11781, r11782, r11783, r11784, r11785, r11786, r11787, r11788, r11789, r11790, r11791, r11792, r11793, r11794, r11795, r11796, r11797, r11798, r11799, r11800, r11801, r11802, r11803, r11804, r11805, r11806, r11807, r11808, r11809, r11810, r11811, r11812, r11813, r11814, r11815, r11816, r11817, r11818, r11819, r11820, r11821, r11822, r11823, r11824
            java.lang.IllegalArgumentException: newPosition > limit: (340536 > 104176)
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
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0003: UNKNOWN(0xF8F4)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0003: UNKNOWN(0xF8F4)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0004: NEW_ARRAY r9, r2
            java.lang.IllegalArgumentException: newPosition > limit: (160128 > 104176)
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
        static {
            /*
                // decode failed: newPosition > limit: (340536 > 104176)
                // decode failed: Unknown instruction: '0x0003: UNKNOWN(0xF8F4)'
                // decode failed: newPosition > limit: (160128 > 104176)
                int r12 = r12 - r13
                int r200 = r238 >> r88
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.MyHandler.<clinit>():void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0002: SPUT r227
            java.lang.IllegalArgumentException: newPosition > limit: (202276 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x000B: SPUT r146
            java.lang.IllegalArgumentException: newPosition > limit: (109104 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0002: SPUT r227
            java.lang.IllegalArgumentException: newPosition > limit: (202276 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0009: UNKNOWN(0x87F1)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0009: UNKNOWN(0x87F1)'
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
        /*  JADX ERROR: Failed to decode insn: 0x000B: SPUT r146
            java.lang.IllegalArgumentException: newPosition > limit: (109104 > 104176)
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
        public MyHandler(com.Mode.toolbox.MainActivity r53) {
            /*
                r52 = this;
                double r127 = r129 + r254
                // decode failed: newPosition > limit: (202276 > 104176)
                switch(r50) {
                // error: 0x0004: SWITCH (r50 I:??)no payload
                int r8 = (-7914) - r6
                // decode failed: Unknown instruction: '0x0009: UNKNOWN(0x87F1)'
                long r9 = r9 % r7
                // decode failed: newPosition > limit: (109104 > 104176)
                int r6 = r6 >> r8
                long r226 = r0 - r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.MyHandler.<init>(com.Mode.toolbox.MainActivity):void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x000A: IGET r0, r14
            java.lang.IllegalArgumentException: newPosition > limit: (244552 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0012: IGET r3, r4
            java.lang.IllegalArgumentException: newPosition > limit: (306752 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0014: SPUT r152
            java.lang.IllegalArgumentException: newPosition > limit: (346352 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x001E: SPUT r129
            java.lang.IllegalArgumentException: newPosition > limit: (213920 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0025: IPUT r11, r2
            java.lang.IllegalArgumentException: newPosition > limit: (120088996 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x002F: SGET r18
            java.lang.IllegalArgumentException: newPosition > limit: (285160 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0036: SPUT r165
            java.lang.IllegalArgumentException: newPosition > limit: (198824 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x003B: INVOKE_SUPER r9, r1
            java.lang.IllegalArgumentException: newPosition > limit: (421168 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0007: UNKNOWN(0x1AEC)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0007: UNKNOWN(0x1AEC)'
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
        /*  JADX ERROR: Failed to decode insn: 0x000A: IGET r0, r14
            java.lang.IllegalArgumentException: newPosition > limit: (244552 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x000F: UNKNOWN(0x0BE6)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000F: UNKNOWN(0x0BE6)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0012: IGET r3, r4
            java.lang.IllegalArgumentException: newPosition > limit: (306752 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0014: SPUT r152
            java.lang.IllegalArgumentException: newPosition > limit: (346352 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x001E: SPUT r129
            java.lang.IllegalArgumentException: newPosition > limit: (213920 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0025: IPUT r11, r2
            java.lang.IllegalArgumentException: newPosition > limit: (120088996 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:195)
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
        /*  JADX ERROR: Failed to decode insn: 0x0027: UNKNOWN(0xE4EA)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0027: UNKNOWN(0xE4EA)'
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
        /*  JADX ERROR: Failed to decode insn: 0x002F: SGET r18
            java.lang.IllegalArgumentException: newPosition > limit: (285160 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0031: UNKNOWN(0xEDEC)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0031: UNKNOWN(0xEDEC)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0036: SPUT r165
            java.lang.IllegalArgumentException: newPosition > limit: (198824 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x003B: INVOKE_SUPER r9, r1
            java.lang.IllegalArgumentException: newPosition > limit: (421168 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
            	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
            	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:446)
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
        private static /* synthetic */ void zCgyC() {
            /*
                int r1 = r1 * r1
                r128 = r146[r13]
                r11 = r9 | 17483(0x444b, float:2.4499E-41)
                long r23 = r254 * r116
                // decode failed: Unknown instruction: '0x0007: UNKNOWN(0x1AEC)'
                r67[r37] = r179
                // decode failed: newPosition > limit: (244552 > 104176)
                int r6 = r6 >>> r2
                int r10 = r14 * (-23305)
                // decode failed: Unknown instruction: '0x000F: UNKNOWN(0x0BE6)'
                float r147 = r131 * r211
                // decode failed: newPosition > limit: (306752 > 104176)
                // decode failed: newPosition > limit: (346352 > 104176)
                int r40 = r244 % r186
                int r33 = r202 >> r29
                double r9 = (double) r6
                float r11 = r11 - r10
                boolean r205 = r188[r107]
                // decode failed: newPosition > limit: (213920 > 104176)
                double r12 = (double) r14
                long r154 = r78 & r66
                long r216 = r85 >> r75
                // decode failed: newPosition > limit: (120088996 > 104176)
                // decode failed: Unknown instruction: '0x0027: UNKNOWN(0xE4EA)'
                long r189 = r60 % r128
                double r52 = r238 + r180
                r196[r61] = r192
                long r10 = r10 * r6
                // decode failed: newPosition > limit: (285160 > 104176)
                // decode failed: Unknown instruction: '0x0031: UNKNOWN(0xEDEC)'
                int r59 = r82 * r57
                double r0 = r0 / r4
                float r13 = (float) r5
                // decode failed: newPosition > limit: (198824 > 104176)
                double r14 = (double) r6
                int r114 = (-87) - r47
                // decode failed: newPosition > limit: (421168 > 104176)
                int r5 = 2040 - r3
                int r12 = r12 - r3
                r173 = 16347(0x3fdb, double:8.0765E-320)
                int r0 = r1 * (-17602)
                double r5 = r5 - r11
                int r13 = r13 % r13
                r166 = r16383
                int r0 = r11 % 6573
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.MyHandler.zCgyC():void");
        }

        /* renamed from: ۣۣ۟ۧۡ, reason: not valid java name and contains not printable characters */
        public static native WeakReference m204(Object obj);

        @Override // android.os.Handler
        public native void handleMessage(Message message);
    }

    /*  JADX ERROR: Failed to decode insn: 0x0003: UNKNOWN(0x7EEB)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0003: UNKNOWN(0x7EEB)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0004: UNKNOWN(0x6973)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0004: UNKNOWN(0x6973)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0005: UNKNOWN(0x26EF)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0005: UNKNOWN(0x26EF)'
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
    /* renamed from: $r8$lambda$U07WkLPee8nkB-Nd9eu5GpbLohA, reason: not valid java name */
    public static /* synthetic */ void m117$r8$lambda$U07WkLPee8nkBNd9eu5GpbLohA(com.Mode.toolbox.MainActivity r51, int r52, int r53) {
        /*
            double r58 = r68 + r228
            long r6 = ~r2
            // decode failed: Unknown instruction: '0x0003: UNKNOWN(0x7EEB)'
            // decode failed: Unknown instruction: '0x0004: UNKNOWN(0x6973)'
            // decode failed: Unknown instruction: '0x0005: UNKNOWN(0x26EF)'
            int r183 = (r64 > r104 ? 1 : (r64 == r104 ? 0 : -1))
            int r211 = r122 >>> 72
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.m117$r8$lambda$U07WkLPee8nkBNd9eu5GpbLohA(com.Mode.toolbox.MainActivity, int, int):void");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0000: FILLED_NEW_ARRAY 
        java.lang.IllegalArgumentException: newPosition > limit: (244264 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0003: SPUT r28
        java.lang.IllegalArgumentException: newPosition > limit: (521744 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0005: NEW_ARRAY r14, r4
        java.lang.IllegalArgumentException: newPosition > limit: (223932 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x000C: INVOKE_POLYMORPHIC_RANGE r37708, r37709, r37710, r37711, r37712, r37713, r37714, r37715, r37716, r37717, r37718, r37719, r37720, r37721, r37722, r37723, r37724, r37725, r37726, r37727, r37728, r37729, r37730, r37731, r37732, r37733, r37734, r37735, r37736, r37737, r37738, r37739, r37740, r37741, r37742, r37743, r37744, r37745, r37746, r37747, r37748, r37749, r37750, r37751, r37752, r37753, r37754, r37755, r37756, r37757, r37758, r37759, r37760, r37761, r37762, r37763, r37764, r37765, r37766, r37767, r37768, r37769, r37770, r37771, r37772, r37773, r37774, r37775, r37776, r37777, r37778, r37779, r37780, r37781, r37782, r37783, r37784, r37785, r37786, r37787, r37788, r37789, r37790, r37791, r37792, r37793, r37794, r37795, r37796, r37797, r37798, r37799, r37800, r37801, r37802, r37803, r37804, r37805, r37806, r37807, r37808, r37809, r37810, r37811, r37812, r37813, r37814, r37815, r37816, r37817, r37818, r37819, r37820, r37821, r37822, r37823, r37824, r37825, r37826, r37827, r37828, r37829, r37830, r37831, r37832, r37833, r37834, r37835, r37836, r37837, r37838, r37839, r37840, r37841, r37842, r37843, r37844, r37845, r37846, r37847, r37848, r37849, r37850, r37851, r37852, r37853, r37854, r37855, r37856, r37857, r37858, r37859, r37860, r37861, r37862, r37863, r37864, r37865, r37866, r37867, r37868, r37869, r37870, r37871, r37872, r37873, r37874, r37875, r37876, r37877, r37878, r37879, r37880, r37881, r37882, r37883, r37884, r37885, r37886, r37887, r37888, r37889, r37890, r37891, r37892, r37893, r37894, r37895, r37896, r37897, r37898, r37899, r37900, r37901, r37902, r37903, r37904, r37905, r37906, r37907, r37908, r37909, r37910, r37911, r37912, r37913, r37914, r37915, r37916, r37917, r37918, r37919, r37920, r37921, r37922, r37923, r37924, r37925, r37926, r37927, r37928, r37929, r37930, r37931, r37932, r37933, r37934, r37935, r37936, r37937, r37938, r37939, r37940, r37941, r37942, r37943, r37944, r37945, r37946, r37947, r37948, r37949, r37950, r37951, r37952, r37953, r37954
        java.lang.IllegalArgumentException: newPosition > limit: (296832 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0021: SPUT r90
        java.lang.IllegalArgumentException: newPosition > limit: (152544 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0027: SPUT r242
        java.lang.IllegalArgumentException: newPosition > limit: (217400 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0032: SPUT r206
        java.lang.IllegalArgumentException: newPosition > limit: (416488 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0038: IPUT r9, r3
        java.lang.IllegalArgumentException: newPosition > limit: (163596 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x003A: INVOKE_POLYMORPHIC_RANGE r42012, r42013, r42014, r42015, r42016, r42017, r42018, r42019, r42020, r42021, r42022, r42023, r42024, r42025, r42026, r42027, r42028, r42029, r42030, r42031, r42032, r42033, r42034, r42035, r42036, r42037, r42038, r42039, r42040, r42041, r42042, r42043, r42044, r42045, r42046, r42047, r42048, r42049, r42050, r42051, r42052, r42053, r42054, r42055, r42056, r42057, r42058, r42059, r42060, r42061, r42062, r42063, r42064, r42065, r42066, r42067, r42068, r42069, r42070, r42071, r42072, r42073, r42074, r42075, r42076, r42077, r42078, r42079, r42080, r42081, r42082, r42083, r42084, r42085, r42086, r42087, r42088, r42089, r42090, r42091, r42092, r42093, r42094, r42095, r42096, r42097, r42098, r42099, r42100, r42101, r42102, r42103, r42104, r42105, r42106, r42107, r42108, r42109, r42110, r42111, r42112, r42113, r42114, r42115, r42116, r42117, r42118, r42119, r42120, r42121, r42122, r42123, r42124, r42125, r42126, r42127, r42128, r42129, r42130, r42131, r42132, r42133, r42134, r42135, r42136, r42137, r42138, r42139, r42140, r42141, r42142, r42143, r42144, r42145, r42146, r42147, r42148, r42149, r42150, r42151, r42152, r42153, r42154, r42155, r42156, r42157, r42158, r42159, r42160, r42161, r42162, r42163, r42164, r42165, r42166, r42167, r42168, r42169, r42170, r42171, r42172, r42173, r42174, r42175, r42176, r42177, r42178
        java.lang.IllegalArgumentException: newPosition > limit: (419400 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x003F: INVOKE_DIRECT r4, r5
        java.lang.IllegalArgumentException: newPosition > limit: (218096 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0044: IPUT r7, r2
        java.lang.IllegalArgumentException: newPosition > limit: (223392 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0050: SGET r107
        java.lang.IllegalArgumentException: newPosition > limit: (346160 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0054: INVOKE_SUPER r7, r15, r1, r1, r14, r42017, r42018, r42019, r42020
        java.lang.IllegalArgumentException: newPosition > limit: (395712 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x005C: IGET r3, r5
        java.lang.IllegalArgumentException: newPosition > limit: (164264 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0063: INVOKE_STATIC r0, r7, r6, r4, r13, r42017, r42018, r42019
        java.lang.IllegalArgumentException: newPosition > limit: (225664 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0068: SPUT r165
        java.lang.IllegalArgumentException: newPosition < 0: (-378877104 < 0)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x006D: SPUT r153
        java.lang.IllegalArgumentException: newPosition > limit: (522056 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x006F: SPUT r98
        java.lang.IllegalArgumentException: newPosition > limit: (427848 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0072: IGET r12, r0
        java.lang.IllegalArgumentException: newPosition > limit: (228464 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x007C: INVOKE_POLYMORPHIC r7, r7, r3, r1, r4, r42017, r42018, r42019, r42020, r42021, r42022, r42023
        java.lang.IllegalArgumentException: newPosition > limit: (383992 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0082: SPUT r57
        java.lang.IllegalArgumentException: newPosition > limit: (409064 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0086: SGET r22
        java.lang.IllegalArgumentException: newPosition > limit: (231216 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x008A: IGET r5, r15
        java.lang.IllegalArgumentException: newPosition > limit: (157632 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x008C: IGET r14, r12
        java.lang.IllegalArgumentException: newPosition > limit: (399768 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00A3: INVOKE_POLYMORPHIC_RANGE r17610, r17611, r17612, r17613, r17614, r17615, r17616, r17617, r17618, r17619, r17620, r17621, r17622, r17623, r17624, r17625, r17626, r17627, r17628, r17629, r17630, r17631, r17632, r17633, r17634, r17635, r17636, r17637, r17638, r17639, r17640, r17641, r17642, r17643, r17644, r17645, r17646, r17647, r17648, r17649, r17650, r17651, r17652, r17653, r17654, r17655, r17656, r17657, r17658, r17659, r17660, r17661, r17662, r17663, r17664, r17665, r17666, r17667, r17668, r17669, r17670, r17671, r17672, r17673, r17674, r17675, r17676, r17677, r17678, r17679, r17680, r17681, r17682, r17683, r17684, r17685, r17686, r17687, r17688, r17689, r17690, r17691, r17692, r17693, r17694, r17695, r17696, r17697, r17698, r17699, r17700, r17701, r17702, r17703, r17704, r17705, r17706, r17707, r17708, r17709, r17710, r17711, r17712, r17713, r17714, r17715, r17716, r17717, r17718, r17719, r17720, r17721, r17722, r17723, r17724, r17725, r17726, r17727, r17728, r17729, r17730, r17731, r17732, r17733, r17734, r17735, r17736, r17737, r17738, r17739, r17740, r17741, r17742, r17743, r17744, r17745, r17746, r17747, r17748, r17749, r17750, r17751, r17752, r17753, r17754, r17755, r17756, r17757, r17758, r17759, r17760, r17761, r17762, r17763
        java.lang.IllegalArgumentException: newPosition > limit: (210800 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00A7: NEW_ARRAY r6, r2
        java.lang.IllegalArgumentException: newPosition > limit: (263984 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00AA: SPUT r22
        java.lang.IllegalArgumentException: newPosition > limit: (411968 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00BD: IPUT r0, r15
        java.lang.IllegalArgumentException: newPosition > limit: (109656 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00CA: IPUT r7, r13
        java.lang.IllegalArgumentException: newPosition > limit: (425536 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00D7: IPUT r7, r15
        java.lang.IllegalArgumentException: newPosition > limit: (380320 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00DC: IPUT r6, r6
        java.lang.IllegalArgumentException: newPosition > limit: (452872 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00DE: IPUT r9, r5
        java.lang.IllegalArgumentException: newPosition > limit: (367824 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00E0: FILLED_NEW_ARRAY r1, r6
        java.lang.IllegalArgumentException: newPosition > limit: (130456 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00E3: INVOKE_INTERFACE r10, r12
        java.lang.IllegalArgumentException: newPosition > limit: (520904 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00FA: SPUT r48
        java.lang.IllegalArgumentException: newPosition > limit: (118176000 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x00FC: SPUT r132
        java.lang.IllegalArgumentException: newPosition > limit: (313304 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0104: IGET r9, r11
        java.lang.IllegalArgumentException: newPosition > limit: (530192 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0113: SPUT r208
        java.lang.IllegalArgumentException: newPosition > limit: (198552 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0117: INVOKE_STATIC r7, r15, r5, r4, r3, r17615, r17616, r17617, r17618, r17619, r17620, r17621, r17622, r17623
        java.lang.IllegalArgumentException: newPosition > limit: (511128 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x012C: IGET r7, r10
        java.lang.IllegalArgumentException: newPosition > limit: (334360 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0137: FILLED_NEW_ARRAY r6, r10, r8, r5, r3, r17615, r17616, r17617, r17618, r17619, r17620, r17621, r17622, r17623
        java.lang.IllegalArgumentException: newPosition > limit: (178576 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0144: IPUT r7, r2
        java.lang.IllegalArgumentException: newPosition > limit: (155812 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x014C: INVOKE_INTERFACE r11, r10, r15, r9, r7, r17615, r17616, r17617, r17618, r17619
        java.lang.IllegalArgumentException: newPosition > limit: (353824 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0157: IGET r15, r9
        java.lang.IllegalArgumentException: newPosition > limit: (234136 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0172: FILLED_NEW_ARRAY r9, r13, r15, r3
        java.lang.IllegalArgumentException: newPosition < 0: (-1687253028 < 0)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x017B: INVOKE_INTERFACE_RANGE r485, r486, r487, r488, r489, r490, r491, r492, r493, r494, r495, r496, r497, r498, r499, r500, r501, r502, r503, r504, r505, r506, r507, r508, r509, r510, r511, r512, r513, r514, r515, r516, r517, r518, r519, r520, r521, r522, r523, r524, r525, r526, r527, r528, r529, r530, r531, r532, r533, r534, r535, r536, r537, r538, r539, r540, r541, r542, r543, r544, r545, r546, r547, r548, r549, r550, r551, r552, r553, r554, r555, r556, r557, r558, r559, r560, r561, r562, r563, r564, r565, r566, r567, r568, r569, r570, r571, r572, r573, r574, r575, r576, r577, r578, r579, r580, r581, r582, r583, r584, r585, r586, r587, r588, r589, r590, r591, r592, r593, r594, r595, r596, r597, r598, r599, r600, r601, r602, r603, r604, r605, r606, r607, r608, r609, r610, r611, r612, r613, r614, r615, r616, r617, r618, r619, r620, r621, r622, r623, r624, r625, r626, r627, r628, r629, r630, r631, r632, r633, r634, r635, r636, r637, r638, r639, r640, r641, r642, r643, r644, r645, r646, r647, r648, r649, r650, r651, r652, r653, r654, r655, r656, r657, r658, r659, r660, r661, r662, r663, r664, r665, r666, r667, r668, r669, r670, r671, r672, r673, r674, r675, r676, r677, r678, r679, r680, r681, r682, r683, r684, r685, r686, r687, r688, r689, r690, r691
        java.lang.IllegalArgumentException: newPosition > limit: (125552 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x017E: SPUT r117
        java.lang.IllegalArgumentException: newPosition > limit: (267776 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0182: IGET r0, r11
        java.lang.IllegalArgumentException: newPosition > limit: (146088 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0194: IGET r5, r8
        java.lang.IllegalArgumentException: newPosition > limit: (167008 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x019D: IPUT r6, r14
        java.lang.IllegalArgumentException: newPosition > limit: (182072 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01A8: IPUT r14, r13
        java.lang.IllegalArgumentException: newPosition > limit: (360408 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01BC: INVOKE_VIRTUAL r2, r9, r2, r4, r10, r490, r491, r492, r493, r494, r495, r496
        java.lang.IllegalArgumentException: newPosition > limit: (494256 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01C0: INVOKE_CUSTOM r3, r15, r13, r0, r10, r490, r491, r492, r493, r494, r495, r496, r497, r498, r499
        java.lang.IllegalArgumentException: newPosition > limit: (229152 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:207)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01C6: IPUT r7, r10
        java.lang.IllegalArgumentException: newPosition > limit: (241192 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01CC: INVOKE_INTERFACE 
        java.lang.IllegalArgumentException: newPosition > limit: (274144 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01DD: SGET r215
        java.lang.IllegalArgumentException: newPosition > limit: (378120 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01E4: INVOKE_DIRECT r10, r9, r4, r5, r14, r490, r491, r492, r493, r494, r495, r496, r497
        java.lang.IllegalArgumentException: newPosition > limit: (413792 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01ED: IGET r1, r5
        java.lang.IllegalArgumentException: newPosition > limit: (361656 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01F0: INVOKE_SUPER_RANGE r63809, r63810, r63811, r63812, r63813, r63814, r63815, r63816, r63817, r63818, r63819, r63820, r63821, r63822, r63823, r63824, r63825, r63826, r63827, r63828, r63829, r63830, r63831, r63832, r63833, r63834, r63835, r63836, r63837, r63838, r63839, r63840, r63841, r63842
        java.lang.IllegalArgumentException: newPosition > limit: (310644 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01F5: IPUT r3, r11
        java.lang.IllegalArgumentException: newPosition > limit: (160416 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x01FA: SPUT r248
        java.lang.IllegalArgumentException: newPosition > limit: (515216 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0205: IPUT r15, r3
        java.lang.IllegalArgumentException: newPosition > limit: (200064 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0217: SPUT r238
        java.lang.IllegalArgumentException: newPosition > limit: (419608 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x021A: FILLED_NEW_ARRAY r9, r2, r10, r13, r0, r63814, r63815, r63816, r63817, r63818, r63819, r63820, r63821, r63822
        java.lang.IllegalArgumentException: newPosition > limit: (441475688 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0220: IGET r14, r5
        java.lang.IllegalArgumentException: newPosition > limit: (456616 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x023B: CONST_CLASS r53
        java.lang.IllegalArgumentException: newPosition > limit: (120060 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x024C: INVOKE_SUPER r13, r7, r7
        java.lang.IllegalArgumentException: newPosition > limit: (334792 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0000: FILLED_NEW_ARRAY 
        java.lang.IllegalArgumentException: newPosition > limit: (244264 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:484)
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
    /*  JADX ERROR: Failed to decode insn: 0x0003: SPUT r28
        java.lang.IllegalArgumentException: newPosition > limit: (521744 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0005: NEW_ARRAY r14, r4
        java.lang.IllegalArgumentException: newPosition > limit: (223932 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x000A: UNKNOWN(0x64ED)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000A: UNKNOWN(0x64ED)'
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
    /*  JADX ERROR: Failed to decode insn: 0x000C: INVOKE_POLYMORPHIC_RANGE r37708, r37709, r37710, r37711, r37712, r37713, r37714, r37715, r37716, r37717, r37718, r37719, r37720, r37721, r37722, r37723, r37724, r37725, r37726, r37727, r37728, r37729, r37730, r37731, r37732, r37733, r37734, r37735, r37736, r37737, r37738, r37739, r37740, r37741, r37742, r37743, r37744, r37745, r37746, r37747, r37748, r37749, r37750, r37751, r37752, r37753, r37754, r37755, r37756, r37757, r37758, r37759, r37760, r37761, r37762, r37763, r37764, r37765, r37766, r37767, r37768, r37769, r37770, r37771, r37772, r37773, r37774, r37775, r37776, r37777, r37778, r37779, r37780, r37781, r37782, r37783, r37784, r37785, r37786, r37787, r37788, r37789, r37790, r37791, r37792, r37793, r37794, r37795, r37796, r37797, r37798, r37799, r37800, r37801, r37802, r37803, r37804, r37805, r37806, r37807, r37808, r37809, r37810, r37811, r37812, r37813, r37814, r37815, r37816, r37817, r37818, r37819, r37820, r37821, r37822, r37823, r37824, r37825, r37826, r37827, r37828, r37829, r37830, r37831, r37832, r37833, r37834, r37835, r37836, r37837, r37838, r37839, r37840, r37841, r37842, r37843, r37844, r37845, r37846, r37847, r37848, r37849, r37850, r37851, r37852, r37853, r37854, r37855, r37856, r37857, r37858, r37859, r37860, r37861, r37862, r37863, r37864, r37865, r37866, r37867, r37868, r37869, r37870, r37871, r37872, r37873, r37874, r37875, r37876, r37877, r37878, r37879, r37880, r37881, r37882, r37883, r37884, r37885, r37886, r37887, r37888, r37889, r37890, r37891, r37892, r37893, r37894, r37895, r37896, r37897, r37898, r37899, r37900, r37901, r37902, r37903, r37904, r37905, r37906, r37907, r37908, r37909, r37910, r37911, r37912, r37913, r37914, r37915, r37916, r37917, r37918, r37919, r37920, r37921, r37922, r37923, r37924, r37925, r37926, r37927, r37928, r37929, r37930, r37931, r37932, r37933, r37934, r37935, r37936, r37937, r37938, r37939, r37940, r37941, r37942, r37943, r37944, r37945, r37946, r37947, r37948, r37949, r37950, r37951, r37952, r37953, r37954
        java.lang.IllegalArgumentException: newPosition > limit: (296832 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0012: CONST_STRING r147
        java.lang.IllegalArgumentException: newPosition < 0: (-2136449325 < 0)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:177)
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
    /*  JADX ERROR: Failed to decode insn: 0x001D: UNKNOWN(0xD6F8)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001D: UNKNOWN(0xD6F8)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0021: SPUT r90
        java.lang.IllegalArgumentException: newPosition > limit: (152544 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0024: UNKNOWN(0x7B73)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0024: UNKNOWN(0x7B73)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0027: SPUT r242
        java.lang.IllegalArgumentException: newPosition > limit: (217400 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0029: UNKNOWN(0x3CE9)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0029: UNKNOWN(0x3CE9)'
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
    /*  JADX ERROR: Failed to decode insn: 0x002A: UNKNOWN(0xBF42)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x002A: UNKNOWN(0xBF42)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0032: SPUT r206
        java.lang.IllegalArgumentException: newPosition > limit: (416488 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0038: IPUT r9, r3
        java.lang.IllegalArgumentException: newPosition > limit: (163596 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
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
    /*  JADX ERROR: Failed to decode insn: 0x003A: INVOKE_POLYMORPHIC_RANGE r42012, r42013, r42014, r42015, r42016, r42017, r42018, r42019, r42020, r42021, r42022, r42023, r42024, r42025, r42026, r42027, r42028, r42029, r42030, r42031, r42032, r42033, r42034, r42035, r42036, r42037, r42038, r42039, r42040, r42041, r42042, r42043, r42044, r42045, r42046, r42047, r42048, r42049, r42050, r42051, r42052, r42053, r42054, r42055, r42056, r42057, r42058, r42059, r42060, r42061, r42062, r42063, r42064, r42065, r42066, r42067, r42068, r42069, r42070, r42071, r42072, r42073, r42074, r42075, r42076, r42077, r42078, r42079, r42080, r42081, r42082, r42083, r42084, r42085, r42086, r42087, r42088, r42089, r42090, r42091, r42092, r42093, r42094, r42095, r42096, r42097, r42098, r42099, r42100, r42101, r42102, r42103, r42104, r42105, r42106, r42107, r42108, r42109, r42110, r42111, r42112, r42113, r42114, r42115, r42116, r42117, r42118, r42119, r42120, r42121, r42122, r42123, r42124, r42125, r42126, r42127, r42128, r42129, r42130, r42131, r42132, r42133, r42134, r42135, r42136, r42137, r42138, r42139, r42140, r42141, r42142, r42143, r42144, r42145, r42146, r42147, r42148, r42149, r42150, r42151, r42152, r42153, r42154, r42155, r42156, r42157, r42158, r42159, r42160, r42161, r42162, r42163, r42164, r42165, r42166, r42167, r42168, r42169, r42170, r42171, r42172, r42173, r42174, r42175, r42176, r42177, r42178
        java.lang.IllegalArgumentException: newPosition > limit: (419400 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokePolymorphic(InsnDecoder.java:605)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:467)
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
    /*  JADX ERROR: Failed to decode insn: 0x003F: INVOKE_DIRECT r4, r5
        java.lang.IllegalArgumentException: newPosition > limit: (218096 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:442)
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
    /*  JADX ERROR: Failed to decode insn: 0x0044: IPUT r7, r2
        java.lang.IllegalArgumentException: newPosition > limit: (223392 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0046: CONST_METHOD_HANDLE r28
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0046: CONST_METHOD_HANDLE r28'
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
    /*  JADX ERROR: Failed to decode insn: 0x0048: UNKNOWN(0x5EF1)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0048: UNKNOWN(0x5EF1)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0050: SGET r107
        java.lang.IllegalArgumentException: newPosition > limit: (346160 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0053: UNKNOWN(0xF2F3)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0053: UNKNOWN(0xF2F3)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0054: INVOKE_SUPER r7, r15, r1, r1, r14, r42017, r42018, r42019, r42020
        java.lang.IllegalArgumentException: newPosition > limit: (395712 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:446)
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
    /*  JADX ERROR: Failed to decode insn: 0x0059: UNKNOWN(0x3EE4)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0059: UNKNOWN(0x3EE4)'
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
    /*  JADX ERROR: Failed to decode insn: 0x005C: IGET r3, r5
        java.lang.IllegalArgumentException: newPosition > limit: (164264 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0061: UNKNOWN(0xD93F)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0061: UNKNOWN(0xD93F)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0062: UNKNOWN(0x2C40)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0062: UNKNOWN(0x2C40)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0063: INVOKE_STATIC r0, r7, r6, r4, r13, r42017, r42018, r42019
        java.lang.IllegalArgumentException: newPosition > limit: (225664 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:436)
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
    /*  JADX ERROR: Failed to decode insn: 0x0068: SPUT r165
        java.lang.IllegalArgumentException: newPosition < 0: (-378877104 < 0)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
        	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:390)
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
    /*  JADX ERROR: Failed to decode insn: 0x006D: SPUT r153
        java.lang.IllegalArgumentException: newPosition > limit: (522056 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x006F: SPUT r98
        java.lang.IllegalArgumentException: newPosition > limit: (427848 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0072: IGET r12, r0
        java.lang.IllegalArgumentException: newPosition > limit: (228464 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x007C: INVOKE_POLYMORPHIC r7, r7, r3, r1, r4, r42017, r42018, r42019, r42020, r42021, r42022, r42023
        java.lang.IllegalArgumentException: newPosition > limit: (383992 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokePolymorphic(InsnDecoder.java:605)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:454)
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
    /*  JADX ERROR: Failed to decode insn: 0x0082: SPUT r57
        java.lang.IllegalArgumentException: newPosition > limit: (409064 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0086: SGET r22
        java.lang.IllegalArgumentException: newPosition > limit: (231216 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0089: UNKNOWN(0x4279)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0089: UNKNOWN(0x4279)'
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
    /*  JADX ERROR: Failed to decode insn: 0x008A: IGET r5, r15
        java.lang.IllegalArgumentException: newPosition > limit: (157632 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x008C: IGET r14, r12
        java.lang.IllegalArgumentException: newPosition > limit: (399768 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x00A3: INVOKE_POLYMORPHIC_RANGE r17610, r17611, r17612, r17613, r17614, r17615, r17616, r17617, r17618, r17619, r17620, r17621, r17622, r17623, r17624, r17625, r17626, r17627, r17628, r17629, r17630, r17631, r17632, r17633, r17634, r17635, r17636, r17637, r17638, r17639, r17640, r17641, r17642, r17643, r17644, r17645, r17646, r17647, r17648, r17649, r17650, r17651, r17652, r17653, r17654, r17655, r17656, r17657, r17658, r17659, r17660, r17661, r17662, r17663, r17664, r17665, r17666, r17667, r17668, r17669, r17670, r17671, r17672, r17673, r17674, r17675, r17676, r17677, r17678, r17679, r17680, r17681, r17682, r17683, r17684, r17685, r17686, r17687, r17688, r17689, r17690, r17691, r17692, r17693, r17694, r17695, r17696, r17697, r17698, r17699, r17700, r17701, r17702, r17703, r17704, r17705, r17706, r17707, r17708, r17709, r17710, r17711, r17712, r17713, r17714, r17715, r17716, r17717, r17718, r17719, r17720, r17721, r17722, r17723, r17724, r17725, r17726, r17727, r17728, r17729, r17730, r17731, r17732, r17733, r17734, r17735, r17736, r17737, r17738, r17739, r17740, r17741, r17742, r17743, r17744, r17745, r17746, r17747, r17748, r17749, r17750, r17751, r17752, r17753, r17754, r17755, r17756, r17757, r17758, r17759, r17760, r17761, r17762, r17763
        java.lang.IllegalArgumentException: newPosition > limit: (210800 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x00A7: NEW_ARRAY r6, r2
        java.lang.IllegalArgumentException: newPosition > limit: (263984 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x00AA: SPUT r22
        java.lang.IllegalArgumentException: newPosition > limit: (411968 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x00AC: UNKNOWN(0x86F2)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00AC: UNKNOWN(0x86F2)'
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
    /*  JADX ERROR: Failed to decode insn: 0x00BC: UNKNOWN(0x46F4)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00BC: UNKNOWN(0x46F4)'
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
    /*  JADX ERROR: Failed to decode insn: 0x00BD: IPUT r0, r15
        java.lang.IllegalArgumentException: newPosition > limit: (109656 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x00C5: UNKNOWN(0x6AF9)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00C5: UNKNOWN(0x6AF9)'
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
    /*  JADX ERROR: Failed to decode insn: 0x00CA: IPUT r7, r13
        java.lang.IllegalArgumentException: newPosition > limit: (425536 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x00CC: UNKNOWN(0x1441)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00CC: UNKNOWN(0x1441)'
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
    /*  JADX ERROR: Failed to decode insn: 0x00CF: UNKNOWN(0xC03E)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x00CF: UNKNOWN(0xC03E)'
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
    /*  JADX ERROR: Failed to decode insn: 0x00D7: IPUT r7, r15
        java.lang.IllegalArgumentException: newPosition > limit: (380320 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x00DC: IPUT r6, r6
        java.lang.IllegalArgumentException: newPosition > limit: (452872 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x00DE: IPUT r9, r5
        java.lang.IllegalArgumentException: newPosition > limit: (367824 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x00E0: FILLED_NEW_ARRAY r1, r6
        java.lang.IllegalArgumentException: newPosition > limit: (130456 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:484)
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
    /*  JADX ERROR: Failed to decode insn: 0x00E3: INVOKE_INTERFACE r10, r12
        java.lang.IllegalArgumentException: newPosition > limit: (520904 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:444)
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
    /*  JADX ERROR: Failed to decode insn: 0x00FA: SPUT r48
        java.lang.IllegalArgumentException: newPosition > limit: (118176000 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.sections.SectionReader.fillFieldData(SectionReader.java:194)
        	at jadx.plugins.input.dex.sections.SectionReader.getFieldRef(SectionReader.java:183)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsField(DexInsnData.java:131)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:390)
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
    /*  JADX ERROR: Failed to decode insn: 0x00FC: SPUT r132
        java.lang.IllegalArgumentException: newPosition > limit: (313304 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0101: UNKNOWN(0xD4E5)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0101: UNKNOWN(0xD4E5)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0102: UNKNOWN(0xC373)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0102: UNKNOWN(0xC373)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0103: UNKNOWN(0x8AF5)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0103: UNKNOWN(0x8AF5)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0104: IGET r9, r11
        java.lang.IllegalArgumentException: newPosition > limit: (530192 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x010C: UNKNOWN(0x08F9)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x010C: UNKNOWN(0x08F9)'
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
    /*  JADX ERROR: Failed to decode insn: 0x010F: UNKNOWN(0xFAF7)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x010F: UNKNOWN(0xFAF7)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0110: UNKNOWN(0x7A40)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0110: UNKNOWN(0x7A40)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0113: SPUT r208
        java.lang.IllegalArgumentException: newPosition > limit: (198552 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0117: INVOKE_STATIC r7, r15, r5, r4, r3, r17615, r17616, r17617, r17618, r17619, r17620, r17621, r17622, r17623
        java.lang.IllegalArgumentException: newPosition > limit: (511128 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:436)
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
    /*  JADX ERROR: Failed to decode insn: 0x011D: UNKNOWN(0x6D42)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x011D: UNKNOWN(0x6D42)'
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
    /*  JADX ERROR: Failed to decode insn: 0x012B: UNKNOWN(0xBEE4)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x012B: UNKNOWN(0xBEE4)'
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
    /*  JADX ERROR: Failed to decode insn: 0x012C: IGET r7, r10
        java.lang.IllegalArgumentException: newPosition > limit: (334360 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0132: UNKNOWN(0x5CF8)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0132: UNKNOWN(0x5CF8)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0137: FILLED_NEW_ARRAY r6, r10, r8, r5, r3, r17615, r17616, r17617, r17618, r17619, r17620, r17621, r17622, r17623
        java.lang.IllegalArgumentException: newPosition > limit: (178576 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:484)
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
    /*  JADX ERROR: Failed to decode insn: 0x0144: IPUT r7, r2
        java.lang.IllegalArgumentException: newPosition > limit: (155812 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
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
    /*  JADX ERROR: Failed to decode insn: 0x0146: UNKNOWN(0x9D43)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0146: UNKNOWN(0x9D43)'
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
    /*  JADX ERROR: Failed to decode insn: 0x014C: INVOKE_INTERFACE r11, r10, r15, r9, r7, r17615, r17616, r17617, r17618, r17619
        java.lang.IllegalArgumentException: newPosition > limit: (353824 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:444)
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
    /*  JADX ERROR: Failed to decode insn: 0x014F: UNKNOWN(0x1000)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x014F: UNKNOWN(0x1000)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0150: UNKNOWN(0x57EC)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0150: UNKNOWN(0x57EC)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0156: UNKNOWN(0x4441)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0156: UNKNOWN(0x4441)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0157: IGET r15, r9
        java.lang.IllegalArgumentException: newPosition > limit: (234136 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0172: FILLED_NEW_ARRAY r9, r13, r15, r3
        java.lang.IllegalArgumentException: newPosition < 0: (-1687253028 < 0)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:484)
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
    /*  JADX ERROR: Failed to decode insn: 0x017A: UNKNOWN(0xDDF3)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x017A: UNKNOWN(0xDDF3)'
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
    /*  JADX ERROR: Failed to decode insn: 0x017B: INVOKE_INTERFACE_RANGE r485, r486, r487, r488, r489, r490, r491, r492, r493, r494, r495, r496, r497, r498, r499, r500, r501, r502, r503, r504, r505, r506, r507, r508, r509, r510, r511, r512, r513, r514, r515, r516, r517, r518, r519, r520, r521, r522, r523, r524, r525, r526, r527, r528, r529, r530, r531, r532, r533, r534, r535, r536, r537, r538, r539, r540, r541, r542, r543, r544, r545, r546, r547, r548, r549, r550, r551, r552, r553, r554, r555, r556, r557, r558, r559, r560, r561, r562, r563, r564, r565, r566, r567, r568, r569, r570, r571, r572, r573, r574, r575, r576, r577, r578, r579, r580, r581, r582, r583, r584, r585, r586, r587, r588, r589, r590, r591, r592, r593, r594, r595, r596, r597, r598, r599, r600, r601, r602, r603, r604, r605, r606, r607, r608, r609, r610, r611, r612, r613, r614, r615, r616, r617, r618, r619, r620, r621, r622, r623, r624, r625, r626, r627, r628, r629, r630, r631, r632, r633, r634, r635, r636, r637, r638, r639, r640, r641, r642, r643, r644, r645, r646, r647, r648, r649, r650, r651, r652, r653, r654, r655, r656, r657, r658, r659, r660, r661, r662, r663, r664, r665, r666, r667, r668, r669, r670, r671, r672, r673, r674, r675, r676, r677, r678, r679, r680, r681, r682, r683, r684, r685, r686, r687, r688, r689, r690, r691
        java.lang.IllegalArgumentException: newPosition > limit: (125552 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:459)
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
    /*  JADX ERROR: Failed to decode insn: 0x017E: SPUT r117
        java.lang.IllegalArgumentException: newPosition > limit: (267776 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0182: IGET r0, r11
        java.lang.IllegalArgumentException: newPosition > limit: (146088 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0192: UNKNOWN(0xB7E7)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0192: UNKNOWN(0xB7E7)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0193: UNKNOWN(0xA9ED)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0193: UNKNOWN(0xA9ED)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0194: IGET r5, r8
        java.lang.IllegalArgumentException: newPosition > limit: (167008 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x019A: UNKNOWN(0x2DEF)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x019A: UNKNOWN(0x2DEF)'
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
    /*  JADX ERROR: Failed to decode insn: 0x019D: IPUT r6, r14
        java.lang.IllegalArgumentException: newPosition > limit: (182072 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
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
    /*  JADX ERROR: Failed to decode insn: 0x01A0: UNKNOWN(0x7DE6)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x01A0: UNKNOWN(0x7DE6)'
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
    /*  JADX ERROR: Failed to decode insn: 0x01A1: UNKNOWN(0x27F1)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x01A1: UNKNOWN(0x27F1)'
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
    /*  JADX ERROR: Failed to decode insn: 0x01A8: IPUT r14, r13
        java.lang.IllegalArgumentException: newPosition > limit: (360408 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x01B8: CONST_METHOD_TYPE r13
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x01B8: CONST_METHOD_TYPE r13'
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
    /*  JADX ERROR: Failed to decode insn: 0x01BC: INVOKE_VIRTUAL r2, r9, r2, r4, r10, r490, r491, r492, r493, r494, r495, r496
        java.lang.IllegalArgumentException: newPosition > limit: (494256 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x01C0: INVOKE_CUSTOM r3, r15, r13, r0, r10, r490, r491, r492, r493, r494, r495, r496, r497, r498, r499
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: newPosition > limit: (229152 > 104176)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:597)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:450)
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
        Caused by: java.lang.IllegalArgumentException: newPosition > limit: (229152 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:207)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
        	... 12 more
        */
    /*  JADX ERROR: Failed to decode insn: 0x01C5: UNKNOWN(0xFCE6)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x01C5: UNKNOWN(0xFCE6)'
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
    /*  JADX ERROR: Failed to decode insn: 0x01C6: IPUT r7, r10
        java.lang.IllegalArgumentException: newPosition > limit: (241192 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x01CC: INVOKE_INTERFACE 
        java.lang.IllegalArgumentException: newPosition > limit: (274144 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:444)
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
    /*  JADX ERROR: Failed to decode insn: 0x01D1: UNKNOWN(0x34E9)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x01D1: UNKNOWN(0x34E9)'
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
    /*  JADX ERROR: Failed to decode insn: 0x01DD: SGET r215
        java.lang.IllegalArgumentException: newPosition > limit: (378120 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x01E2: UNKNOWN(0x2743)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x01E2: UNKNOWN(0x2743)'
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
    /*  JADX ERROR: Failed to decode insn: 0x01E4: INVOKE_DIRECT r10, r9, r4, r5, r14, r490, r491, r492, r493, r494, r495, r496, r497
        java.lang.IllegalArgumentException: newPosition > limit: (413792 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:442)
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
    /*  JADX ERROR: Failed to decode insn: 0x01E7: CONST_METHOD_HANDLE r254
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x01E7: CONST_METHOD_HANDLE r254'
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
    /*  JADX ERROR: Failed to decode insn: 0x01ED: IGET r1, r5
        java.lang.IllegalArgumentException: newPosition > limit: (361656 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x01F0: INVOKE_SUPER_RANGE r63809, r63810, r63811, r63812, r63813, r63814, r63815, r63816, r63817, r63818, r63819, r63820, r63821, r63822, r63823, r63824, r63825, r63826, r63827, r63828, r63829, r63830, r63831, r63832, r63833, r63834, r63835, r63836, r63837, r63838, r63839, r63840, r63841, r63842
        java.lang.IllegalArgumentException: newPosition > limit: (310644 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:461)
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
    /*  JADX ERROR: Failed to decode insn: 0x01F5: IPUT r3, r11
        java.lang.IllegalArgumentException: newPosition > limit: (160416 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x01FA: SPUT r248
        java.lang.IllegalArgumentException: newPosition > limit: (515216 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0202: UNKNOWN(0xE4E6)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0202: UNKNOWN(0xE4E6)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0205: IPUT r15, r3
        java.lang.IllegalArgumentException: newPosition > limit: (200064 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0207: UNKNOWN(0x94F9)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0207: UNKNOWN(0x94F9)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0208: UNKNOWN(0xB2E6)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0208: UNKNOWN(0xB2E6)'
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
    /*  JADX ERROR: Failed to decode insn: 0x020C: UNKNOWN(0xEE3F)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x020C: UNKNOWN(0xEE3F)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0216: UNKNOWN(0x1DF7)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0216: UNKNOWN(0x1DF7)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0217: SPUT r238
        java.lang.IllegalArgumentException: newPosition > limit: (419608 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x021A: FILLED_NEW_ARRAY r9, r2, r10, r13, r0, r63814, r63815, r63816, r63817, r63818, r63819, r63820, r63821, r63822
        java.lang.IllegalArgumentException: newPosition > limit: (441475688 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:556)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:484)
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
    /*  JADX ERROR: Failed to decode insn: 0x021E: UNKNOWN(0x49E5)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x021E: UNKNOWN(0x49E5)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0220: IGET r14, r5
        java.lang.IllegalArgumentException: newPosition > limit: (456616 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0224: UNKNOWN(0xE643)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0224: UNKNOWN(0xE643)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0239: UNKNOWN(0xFDF3)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0239: UNKNOWN(0xFDF3)'
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
    /*  JADX ERROR: Failed to decode insn: 0x023B: CONST_CLASS r53
        java.lang.IllegalArgumentException: newPosition > limit: (120060 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:86)
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
    /*  JADX ERROR: Failed to decode insn: 0x024C: INVOKE_SUPER r13, r7, r7
        java.lang.IllegalArgumentException: newPosition > limit: (334792 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:258)
        	at jadx.plugins.input.dex.sections.DexMethodRef.load(DexMethodRef.java:28)
        	at jadx.core.dex.info.MethodInfo.fromRef(MethodInfo.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:637)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:446)
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
    /*  JADX ERROR: Failed to decode insn: 0x024F: UNKNOWN(0x4B41)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x024F: UNKNOWN(0x4B41)'
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
    static {
        /*
            Method dump skipped, instructions count: 599
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.<clinit>():void");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0008: SPUT r202
        java.lang.IllegalArgumentException: newPosition > limit: (418456 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0011: INSTANCE_OF r5, r15
        java.lang.IllegalArgumentException: newPosition > limit: (1871698372 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0008: SPUT r202
        java.lang.IllegalArgumentException: newPosition > limit: (418456 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x000A: UNKNOWN(0x3AF0)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000A: UNKNOWN(0x3AF0)'
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
    /*  JADX ERROR: Failed to decode insn: 0x000D: UNKNOWN(0x497A)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000D: UNKNOWN(0x497A)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0011: INSTANCE_OF r5, r15
        java.lang.IllegalArgumentException: newPosition > limit: (1871698372 > 104176)
        	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
        	at java.base/java.nio.Buffer.position(Buffer.java:326)
        	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:357)
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
    public MainActivity() {
        /*
            r52 = this;
            switch(r210) {
            // error: 0x0000: SWITCH (r210 I:??)no payload
            r170 = 24386(0x5f42, float:3.4172E-41)
            int r3 = r3 << r15
            int r5 = r5 >> r11
            int r3 = r3 % r3
            // decode failed: newPosition > limit: (418456 > 104176)
            // decode failed: Unknown instruction: '0x000A: UNKNOWN(0x3AF0)'
            long r6 = r6 + r2
            long r9 = r9 | r6
            // decode failed: Unknown instruction: '0x000D: UNKNOWN(0x497A)'
            long r128 = r15 / r19
            long r1 = ~r8
            // decode failed: newPosition > limit: (1871698372 > 104176)
            r236 = r49 | 117(0x75, float:1.64E-43)
            return r218
            double r0 = r0 + r14
            boolean r80 = r123[r0]
            r189 = 131125(0x20035, float:1.83745E-40)
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.<init>():void");
    }

    private native void NoPermission();

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x0000: ARITH (r205 I:int) = (r119 I:int) / (25 int), expected to be less than 53
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:79)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    static /* synthetic */ void access$000(com.Mode.toolbox.MainActivity r51, int r52) {
        /*
            int r205 = r119 / 25
            int r47 = r249 * r91
            char r7 = (char) r15
            r74 = r59106
            r99 = r53[r0]
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.access$000(com.Mode.toolbox.MainActivity, int):void");
    }

    private native void checkShizukuPermission();

    private native void exec(String str);

    private native void onClearSelected(int i);

    private native void onRequestPermissionsResult(int i, int i2);

    private native void updateShizukuStatus();

    private native void updateShizukuTextView();

    /* renamed from: ۟۟ۧۥۦ, reason: not valid java name and contains not printable characters */
    public static native Shizuku.OnRequestPermissionResultListener m118(Object obj);

    /* renamed from: ۟ۡۤۤۧ, reason: not valid java name and contains not printable characters */
    public static native void m119(Object obj);

    /* renamed from: ۟ۦۢۤۡ, reason: not valid java name and contains not printable characters */
    public static native SharedPreferences m120(Object obj);

    /* renamed from: ۟ۦۢۨ۠, reason: not valid java name and contains not printable characters */
    public static native boolean m121(Object obj);

    /* renamed from: ۠۠ۨۧ, reason: not valid java name and contains not printable characters */
    public static native TextView m122(Object obj);

    /* renamed from: ۣۡ۟ۤ, reason: not valid java name and contains not printable characters */
    public static native void m123(Object obj);

    /* renamed from: ۡ۠ۡ۟, reason: not valid java name and contains not printable characters */
    public static native short[] m124();

    /* renamed from: ۣۣۡۢ, reason: not valid java name and contains not printable characters */
    public static native void m125(Object obj, Object obj2);

    /* renamed from: ۡۥۣۡ, reason: not valid java name and contains not printable characters */
    public static native void m126(Object obj, int i);

    /* renamed from: ۢۡۧ۠, reason: not valid java name and contains not printable characters */
    public static native void m127(Object obj);

    /* renamed from: ۣۣ۠ۥ, reason: not valid java name and contains not printable characters */
    public static native void m128(Object obj);

    /* renamed from: ۣۤ۠۠, reason: not valid java name and contains not printable characters */
    public static native boolean m129(Object obj);

    /* renamed from: ۥۦۨۤ, reason: contains not printable characters */
    public static native int m130(Object obj);

    /* renamed from: ۨۡۡۤ, reason: not valid java name and contains not printable characters */
    public static native void m131(Object obj);

    /* renamed from: ۨۡۦۢ, reason: not valid java name and contains not printable characters */
    public static native void m132(Object obj, int i, int i2);

    /* renamed from: ۣۨ۠ۢ, reason: not valid java name and contains not printable characters */
    public static native boolean m133(Object obj);

    /* renamed from: 复制脚本, reason: contains not printable characters */
    private native void m134();

    public native void AboutMe(View view);

    public native void BackupRestore(View view);

    public native void BalancMode(View view);

    public native void BatteryMode(View view);

    public native void ClearBackground(View view);

    public native void MoreFeatures(View view);

    public native void OneClickClear(View view);

    public native void PerfMode(View view);

    public native void ShizukuExec(String str);

    public native void Terminal(View view);

    public native void Tool(View view);

    public native void Troubleshooting(View view);

    /*  JADX ERROR: Dependency scan failed at insn: 0x0000: IGET r15, r8
        java.lang.IllegalArgumentException: newPosition > limit: (277808 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0000: IGET r15, r8
        java.lang.IllegalArgumentException: newPosition > limit: (277808 > 104176)
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
    /* renamed from: lambda$exec$0$com-Mode-toolbox-MainActivity, reason: not valid java name */
    /* synthetic */ void m135lambda$exec$0$comModetoolboxMainActivity(java.lang.String r52) {
        /*
            r51 = this;
            // decode failed: newPosition > limit: (277808 > 104176)
            int r12 = r129 % (-21)
            long r15 = (long) r15
            float r211 = r136 + r173
            int r1 = (int) r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MainActivity.m135lambda$exec$0$comModetoolboxMainActivity(java.lang.String):void");
    }

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    @Override // android.app.Activity
    protected native void onDestroy();

    @Override // android.app.Activity
    protected native void onResume();

    public native void openLink(View view);
}
