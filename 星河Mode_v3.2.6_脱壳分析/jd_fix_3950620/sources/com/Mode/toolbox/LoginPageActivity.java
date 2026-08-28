package com.Mode.toolbox;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

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
public class LoginPageActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f25short = null;
    private int currentImageIndex;
    private ImageView iconImageView;
    private int[] imageResources;
    private boolean isPasswordVisible;
    private EditText passwordEditText;
    private SharedPreferences prefs;
    private ScrollView rootScrollView;
    private ObjectAnimator rotationAnimator;
    private ImageView showPasswordToggle;

    /* renamed from: com.Mode.toolbox.LoginPageActivity$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ LoginPageActivity this$0;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0007: IGET r15, r6
            java.lang.IllegalArgumentException: newPosition > limit: (317592 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0007: IGET r15, r6
            java.lang.IllegalArgumentException: newPosition > limit: (317592 > 104176)
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
        static {
            /*
                r137[r255] = r242
                r144 = 993660039(0x3b3a0c87, double:4.90933289E-315)
                goto LB_5a51
                // decode failed: newPosition > limit: (317592 > 104176)
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.LoginPageActivity.AnonymousClass1.<clinit>():void");
        }

        /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
            jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x0000: ARITH (r179 I:int) = (r178 I:int) - (r104 I:int), expected to be less than 53
            	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:79)
            	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
            */
        AnonymousClass1(com.Mode.toolbox.LoginPageActivity r52) {
            /*
                r51 = this;
                int r179 = r178 - r104
                int r7 = ~r3
                int r160 = r180 + r57
                double r2 = (double) r4
                double r7 = (double) r3
                long r8 = (long) r14
                r6 = r8 | (-12308(0xffffffffffffcfec, float:NaN))
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.LoginPageActivity.AnonymousClass1.<init>(com.Mode.toolbox.LoginPageActivity):void");
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0010: NEW_INSTANCE r92
            java.lang.IllegalArgumentException: newPosition < 0: (-439763392 < 0)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0012: SPUT r32
            java.lang.IllegalArgumentException: newPosition > limit: (197776 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0016: INVOKE_DIRECT_RANGE r14969, r14970, r14971, r14972, r14973, r14974, r14975, r14976, r14977, r14978, r14979, r14980, r14981, r14982, r14983, r14984, r14985, r14986, r14987, r14988, r14989, r14990, r14991, r14992, r14993, r14994, r14995, r14996, r14997, r14998, r14999, r15000, r15001, r15002, r15003, r15004, r15005, r15006, r15007, r15008, r15009, r15010, r15011, r15012, r15013, r15014, r15015, r15016, r15017, r15018, r15019, r15020, r15021, r15022, r15023, r15024, r15025, r15026, r15027, r15028, r15029, r15030, r15031, r15032, r15033, r15034, r15035, r15036, r15037, r15038, r15039, r15040, r15041, r15042, r15043, r15044, r15045, r15046, r15047, r15048, r15049, r15050, r15051, r15052, r15053, r15054, r15055, r15056, r15057, r15058, r15059, r15060, r15061, r15062, r15063
            java.lang.IllegalArgumentException: newPosition > limit: (430232 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x002C: INVOKE_STATIC r10, r3, r5
            java.lang.IllegalArgumentException: newPosition > limit: (449880 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0031: INVOKE_STATIC r8, r6, r0, r6, r13, r14974, r14975, r14976, r14977, r14978
            java.lang.IllegalArgumentException: newPosition > limit: (393344 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x003C: IPUT r6, r11
            java.lang.IllegalArgumentException: newPosition > limit: (198376 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0041: INVOKE_DIRECT 
            java.lang.IllegalArgumentException: newPosition > limit: (263536 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x000A: UNKNOWN(0x2DED)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000A: UNKNOWN(0x2DED)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0010: NEW_INSTANCE r92
            java.lang.IllegalArgumentException: newPosition < 0: (-439763392 < 0)
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
        /*  JADX ERROR: Failed to decode insn: 0x0012: SPUT r32
            java.lang.IllegalArgumentException: newPosition > limit: (197776 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0015: UNKNOWN(0x98F4)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0015: UNKNOWN(0x98F4)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0016: INVOKE_DIRECT_RANGE r14969, r14970, r14971, r14972, r14973, r14974, r14975, r14976, r14977, r14978, r14979, r14980, r14981, r14982, r14983, r14984, r14985, r14986, r14987, r14988, r14989, r14990, r14991, r14992, r14993, r14994, r14995, r14996, r14997, r14998, r14999, r15000, r15001, r15002, r15003, r15004, r15005, r15006, r15007, r15008, r15009, r15010, r15011, r15012, r15013, r15014, r15015, r15016, r15017, r15018, r15019, r15020, r15021, r15022, r15023, r15024, r15025, r15026, r15027, r15028, r15029, r15030, r15031, r15032, r15033, r15034, r15035, r15036, r15037, r15038, r15039, r15040, r15041, r15042, r15043, r15044, r15045, r15046, r15047, r15048, r15049, r15050, r15051, r15052, r15053, r15054, r15055, r15056, r15057, r15058, r15059, r15060, r15061, r15062, r15063
            java.lang.IllegalArgumentException: newPosition > limit: (430232 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x001E: UNKNOWN(0x3EE7)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001E: UNKNOWN(0x3EE7)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0027: UNKNOWN(0xDBEF)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0027: UNKNOWN(0xDBEF)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0028: UNKNOWN(0x4CEF)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0028: UNKNOWN(0x4CEF)'
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
        /*  JADX ERROR: Failed to decode insn: 0x002C: INVOKE_STATIC r10, r3, r5
            java.lang.IllegalArgumentException: newPosition > limit: (449880 > 104176)
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
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0031: INVOKE_STATIC r8, r6, r0, r6, r13, r14974, r14975, r14976, r14977, r14978
            java.lang.IllegalArgumentException: newPosition > limit: (393344 > 104176)
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
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x003C: IPUT r6, r11
            java.lang.IllegalArgumentException: newPosition > limit: (198376 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0040: UNKNOWN(0x6CEF)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0040: UNKNOWN(0x6CEF)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0041: INVOKE_DIRECT 
            java.lang.IllegalArgumentException: newPosition > limit: (263536 > 104176)
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
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0046: CONST_METHOD_TYPE r91
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0046: CONST_METHOD_TYPE r91'
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
        /*  JADX ERROR: Failed to decode insn: 0x0049: CONST_METHOD_HANDLE r80
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0049: CONST_METHOD_HANDLE r80'
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
        private static /* synthetic */ void piPtD() {
            /*
                int r6 = r6 >>> r2
                long r14 = r14 + r9
                int r39 = r129 + r197
                int r37 = (r138 > r231 ? 1 : (r138 == r231 ? 0 : -1))
                monitor-enter(r155)
                r1 = r11
                r34 = r114[r226]
                // decode failed: Unknown instruction: '0x000A: UNKNOWN(0x2DED)'
                r10[r77] = r108
                if (r7 < r0) goto LB_1135
                int r13 = r13 - r15
                // decode failed: newPosition < 0: (-439763392 < 0)
                // decode failed: newPosition > limit: (197776 > 104176)
                int r0 = ~r2
                // decode failed: Unknown instruction: '0x0015: UNKNOWN(0x98F4)'
                // decode failed: newPosition > limit: (430232 > 104176)
                double r42 = r103 / r172
                int r25 = r185 % r173
                short r11 = (short) r11
                // decode failed: Unknown instruction: '0x001E: UNKNOWN(0x3EE7)'
                int r25 = r164 + r52
                long r12 = (long) r6
                if (r0 >= r13) goto L7be
                r30[r214] = r206
                int r8 = (int) r2
                // decode failed: Unknown instruction: '0x0027: UNKNOWN(0xDBEF)'
                // decode failed: Unknown instruction: '0x0028: UNKNOWN(0x4CEF)'
                float r9 = (float) r10
                int r175 = r238 + r11
                // decode failed: newPosition > limit: (449880 > 104176)
                float r72 = r66 % r213
                // decode failed: newPosition > limit: (393344 > 104176)
                if (r61 < 0) goto L79c9
                double r0 = (double) r10
                r5 = r6 | (-1384(0xfffffffffffffa98, float:NaN))
                int r3 = r15.length
                int r11 = r1 + (-30547)
                // decode failed: newPosition > limit: (198376 > 104176)
                int r54 = (r152 > r104 ? 1 : (r152 == r104 ? 0 : -1))
                // decode failed: Unknown instruction: '0x0040: UNKNOWN(0x6CEF)'
                // decode failed: newPosition > limit: (263536 > 104176)
                r152 = r218 ^ r89
                // decode failed: Unknown instruction: '0x0046: CONST_METHOD_TYPE r91'
                long r5 = r5 + r1
                // decode failed: Unknown instruction: '0x0049: CONST_METHOD_HANDLE r80'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.LoginPageActivity.AnonymousClass1.piPtD():void");
        }

        /* renamed from: ۟۠۟ۢۦ, reason: not valid java name and contains not printable characters */
        public static native int m115(Object obj);

        /* renamed from: ۣ۠ۦۣ, reason: not valid java name and contains not printable characters */
        public static native LoginPageActivity m116(Object obj);

        @Override // android.view.View.OnClickListener
        public native void onClick(View view);
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.LoginPageActivity.<clinit>():void, file: /workspace/xh/fix_3950620.dex
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        Caused by: java.lang.ArrayIndexOutOfBoundsException
        */
    static {
        /*
        // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.LoginPageActivity.<clinit>():void, file: /workspace/xh/fix_3950620.dex
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.LoginPageActivity.<clinit>():void");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0001: SGET r72
        java.lang.IllegalArgumentException: newPosition > limit: (430360 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0004: CONST_CLASS r103
        java.lang.IllegalArgumentException: newPosition > limit: (198828 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x1CF7)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x1CF7)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0001: SGET r72
        java.lang.IllegalArgumentException: newPosition > limit: (430360 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0003: UNKNOWN(0x9DF7)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0003: UNKNOWN(0x9DF7)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0004: CONST_CLASS r103
        java.lang.IllegalArgumentException: newPosition > limit: (198828 > 104176)
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
    public LoginPageActivity() {
        /*
            r52 = this;
            // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x1CF7)'
            // decode failed: newPosition > limit: (430360 > 104176)
            // decode failed: Unknown instruction: '0x0003: UNKNOWN(0x9DF7)'
            // decode failed: newPosition > limit: (198828 > 104176)
            int r27 = r24 / (-119)
            float r35 = r201 / r17
            int r7 = (int) r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.LoginPageActivity.<init>():void");
    }

    private native boolean checkPassword(String str);

    private native void copyDeviceKeyToClipboard();

    private native Drawable createButtonBackground();

    private native Drawable createRoundedBackground(int i, float f);

    private native String generateRandomKey();

    private native String getDeviceKey();

    private native void initImageResources();

    private native String loadPassword();

    private native void nextImage();

    private native void restoreCurrentImage();

    private native void savePassword(String str);

    private native void showToast(String str);

    private native void startIconRotation();

    private native void startImageRotation();

    private native void startMainActivity();

    private native void stopIconRotation();

    private native void togglePasswordVisibility();

    /* renamed from: ۣ۟۟ۢ۟, reason: not valid java name and contains not printable characters */
    public static native String m88(Object obj);

    /* renamed from: ۟۟ۢۥ, reason: not valid java name and contains not printable characters */
    public static native Drawable m89(Object obj, int i, float f);

    /* renamed from: ۟۟ۤۢۥ, reason: not valid java name and contains not printable characters */
    public static native void m90(Object obj);

    /* renamed from: ۟۟ۤۦ۠, reason: not valid java name and contains not printable characters */
    public static native void m91(Object obj);

    /* renamed from: ۟۠ۨ۟ۡ, reason: not valid java name and contains not printable characters */
    public static native String m92(Object obj);

    /* renamed from: ۟ۡۢ۟۟, reason: not valid java name and contains not printable characters */
    public static native void m93(Object obj);

    /* renamed from: ۟ۡۤۤۦ, reason: not valid java name and contains not printable characters */
    public static native void m94(Object obj);

    /* renamed from: ۣ۟ۢ۟ۢ, reason: not valid java name and contains not printable characters */
    public static native Drawable m95(Object obj);

    /* renamed from: ۟ۢ۠۠ۡ, reason: not valid java name and contains not printable characters */
    public static native ImageView m96(Object obj);

    /* renamed from: ۣۣ۟ۡۥ, reason: not valid java name and contains not printable characters */
    public static native EditText m97(Object obj);

    /* renamed from: ۣ۟ۤۢۢ, reason: not valid java name and contains not printable characters */
    public static native int m98(Object obj);

    /* renamed from: ۟ۥۢۡۥ, reason: not valid java name and contains not printable characters */
    public static native ImageView m99(Object obj);

    /* renamed from: ۟ۦۤ۟ۨ, reason: not valid java name and contains not printable characters */
    public static native void m100(Object obj);

    /* renamed from: ۟ۦۨ۟ۦ, reason: not valid java name and contains not printable characters */
    public static native void m101(Object obj);

    /* renamed from: ۟ۧۦۡۢ, reason: not valid java name and contains not printable characters */
    public static native String m102(Object obj);

    /* renamed from: ۟ۨۤۤ, reason: not valid java name and contains not printable characters */
    public static native boolean m103(Object obj, Object obj2);

    /* renamed from: ۣ۠۠ۥ, reason: not valid java name and contains not printable characters */
    public static native void m104(Object obj, Object obj2);

    /* renamed from: ۠ۨ۠ۡ, reason: not valid java name and contains not printable characters */
    public static native void m105(Object obj, Object obj2);

    /* renamed from: ۣۡۢ۟, reason: not valid java name and contains not printable characters */
    public static native int m106(Object obj);

    /* renamed from: ۡۦۣۢ, reason: not valid java name and contains not printable characters */
    public static native ObjectAnimator m107(Object obj);

    /* renamed from: ۢ۠ۡ, reason: not valid java name and contains not printable characters */
    public static native void m108(Object obj);

    /* renamed from: ۤۨۦۡ, reason: not valid java name and contains not printable characters */
    public static native short[] m109();

    /* renamed from: ۥۣ۠ۦ, reason: contains not printable characters */
    public static native SharedPreferences m110(Object obj);

    /* renamed from: ۥۨۤۡ, reason: contains not printable characters */
    public static native int[] m111(Object obj);

    /* renamed from: ۦۢۦ۟, reason: contains not printable characters */
    public static native void m112(Object obj);

    /* renamed from: ۦۧ۠۟, reason: contains not printable characters */
    public static native void m113(Object obj);

    /* renamed from: ۨۤۤ۟, reason: not valid java name and contains not printable characters */
    public static native boolean m114(Object obj);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    @Override // android.view.View.OnLongClickListener
    public native boolean onLongClick(View view);

    @Override // android.app.Activity
    protected native void onResume();
}
