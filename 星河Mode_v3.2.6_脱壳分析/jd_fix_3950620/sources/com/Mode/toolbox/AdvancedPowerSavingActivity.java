package com.Mode.toolbox;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

/* loaded from: /workspace/xh/fix_3950620.dex */
public class AdvancedPowerSavingActivity extends Activity {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f4short = null;
    private SharedPreferences prefs;

    /* renamed from: com.Mode.toolbox.AdvancedPowerSavingActivity$1, reason: invalid class name */
    class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f5short = null;
        final /* synthetic */ AdvancedPowerSavingActivity val$activity;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0000: IPUT r15, r9
            java.lang.IllegalArgumentException: newPosition > limit: (170976 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: IPUT r15, r9
            java.lang.IllegalArgumentException: newPosition > limit: (170976 > 104176)
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
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x000A: CONST_STRING r134
            java.lang.IllegalArgumentException: newPosition < 0: (-305578676 < 0)
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
        AnonymousClass1(com.Mode.toolbox.AdvancedPowerSavingActivity r53, android.content.SharedPreferences r54) {
            /*
                r52 = this;
                // decode failed: newPosition > limit: (170976 > 104176)
                int r10 = r10 - r3
                double r9 = -r10
                long r11 = r11 / r12
                double r241 = r192 / r233
                float r137 = r220 * r141
                r10 = r8
                // decode failed: newPosition < 0: (-305578676 < 0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.AdvancedPowerSavingActivity.AnonymousClass1.<init>(com.Mode.toolbox.AdvancedPowerSavingActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۟ۢۢ۟, reason: not valid java name and contains not printable characters */
        public static native short[] m12();

        /* renamed from: ۡۥۣۤ, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m13(Object obj);

        /* renamed from: ۣۧۤۡ, reason: not valid java name and contains not printable characters */
        public static native AdvancedPowerSavingActivity m14(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.AdvancedPowerSavingActivity$2, reason: invalid class name */
    class AnonymousClass2 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f6short = null;
        final /* synthetic */ AdvancedPowerSavingActivity val$activity;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0006: INVOKE_DIRECT_RANGE r49548, r49549, r49550, r49551, r49552, r49553, r49554, r49555, r49556, r49557, r49558, r49559, r49560, r49561, r49562, r49563, r49564, r49565, r49566, r49567, r49568, r49569, r49570, r49571, r49572, r49573, r49574, r49575, r49576, r49577, r49578, r49579, r49580, r49581, r49582, r49583, r49584, r49585, r49586, r49587, r49588, r49589, r49590, r49591, r49592, r49593, r49594, r49595, r49596, r49597, r49598, r49599, r49600, r49601, r49602, r49603, r49604, r49605, r49606, r49607, r49608, r49609, r49610, r49611, r49612, r49613, r49614, r49615, r49616, r49617, r49618, r49619, r49620, r49621, r49622, r49623, r49624, r49625, r49626, r49627, r49628, r49629, r49630
            java.lang.IllegalArgumentException: newPosition > limit: (314120 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0006: INVOKE_DIRECT_RANGE r49548, r49549, r49550, r49551, r49552, r49553, r49554, r49555, r49556, r49557, r49558, r49559, r49560, r49561, r49562, r49563, r49564, r49565, r49566, r49567, r49568, r49569, r49570, r49571, r49572, r49573, r49574, r49575, r49576, r49577, r49578, r49579, r49580, r49581, r49582, r49583, r49584, r49585, r49586, r49587, r49588, r49589, r49590, r49591, r49592, r49593, r49594, r49595, r49596, r49597, r49598, r49599, r49600, r49601, r49602, r49603, r49604, r49605, r49606, r49607, r49608, r49609, r49610, r49611, r49612, r49613, r49614, r49615, r49616, r49617, r49618, r49619, r49620, r49621, r49622, r49623, r49624, r49625, r49626, r49627, r49628, r49629, r49630
            java.lang.IllegalArgumentException: newPosition > limit: (314120 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0009: UNKNOWN(0x74E3)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0009: UNKNOWN(0x74E3)'
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
        AnonymousClass2(com.Mode.toolbox.AdvancedPowerSavingActivity r53, android.content.SharedPreferences r54) {
            /*
                r52 = this;
                if (r6 > 0) goto L41fe
                int r13 = -r9
                double r3 = r3 % r9
                if (r14 == r8) goto LB_2ae8
                // decode failed: newPosition > limit: (314120 > 104176)
                // decode failed: Unknown instruction: '0x0009: UNKNOWN(0x74E3)'
                r27 = r15 | r44
                int r1 = r0 + 10172
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.AdvancedPowerSavingActivity.AnonymousClass2.<init>(com.Mode.toolbox.AdvancedPowerSavingActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۟۟ۦۧۡ, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m15(Object obj);

        /* renamed from: ۣ۟ۨۡ, reason: not valid java name and contains not printable characters */
        public static native AdvancedPowerSavingActivity m16(Object obj);

        /* renamed from: ۢ۟ۤۢ, reason: not valid java name and contains not printable characters */
        public static native short[] m17();

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.AdvancedPowerSavingActivity$4, reason: invalid class name */
    class AnonymousClass4 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f8short = null;
        final /* synthetic */ AdvancedPowerSavingActivity val$activity;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0004: SPUT r110
            java.lang.IllegalArgumentException: newPosition > limit: (357696 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0004: SPUT r110
            java.lang.IllegalArgumentException: newPosition > limit: (357696 > 104176)
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
        AnonymousClass4(com.Mode.toolbox.AdvancedPowerSavingActivity r53, android.content.SharedPreferences r54) {
            /*
                r52 = this;
                int r20 = r167 % r82
                int r9 = r13 * 9387
                // decode failed: newPosition > limit: (357696 > 104176)
                char r9 = (char) r4
                double r99 = r203 + r213
                int r57 = (r171 > r153 ? 1 : (r171 == r153 ? 0 : -1))
                r37677 = r45497
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.AdvancedPowerSavingActivity.AnonymousClass4.<init>(com.Mode.toolbox.AdvancedPowerSavingActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۣ۟ۤۦ, reason: not valid java name and contains not printable characters */
        public static native AdvancedPowerSavingActivity m21(Object obj);

        /* renamed from: ۠ۢۡۦ, reason: not valid java name and contains not printable characters */
        public static native short[] m22();

        /* renamed from: ۤۦۧ۟, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m23(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.AdvancedPowerSavingActivity$5, reason: invalid class name */
    class AnonymousClass5 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f9short = null;
        final /* synthetic */ AdvancedPowerSavingActivity val$activity;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0002: IGET r10, r4
            java.lang.IllegalArgumentException: newPosition > limit: (437928 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x000C: IGET r12, r9
            java.lang.IllegalArgumentException: newPosition > limit: (283256 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0002: IGET r10, r4
            java.lang.IllegalArgumentException: newPosition > limit: (437928 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x000C: IGET r12, r9
            java.lang.IllegalArgumentException: newPosition > limit: (283256 > 104176)
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
        AnonymousClass5(com.Mode.toolbox.AdvancedPowerSavingActivity r53, android.content.SharedPreferences r54) {
            /*
                r52 = this;
                int r7 = r11 + 22072
                // decode failed: newPosition > limit: (437928 > 104176)
                double r31 = r186 % r21
                double r15 = r15 / r13
                int r15 = -r10
                double r15 = (double) r7
                r232 = r205 & (-9)
                int r10 = r10 % r2
                // decode failed: newPosition > limit: (283256 > 104176)
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.AdvancedPowerSavingActivity.AnonymousClass5.<init>(com.Mode.toolbox.AdvancedPowerSavingActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۣ۟۟ۢ۟, reason: not valid java name and contains not printable characters */
        public static native short[] m24();

        /* renamed from: ۦۢۨ۠, reason: contains not printable characters */
        public static native SharedPreferences m25(Object obj);

        /* renamed from: ۧ۠ۨۢ, reason: not valid java name and contains not printable characters */
        public static native AdvancedPowerSavingActivity m26(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.AdvancedPowerSavingActivity$6, reason: invalid class name */
    class AnonymousClass6 implements DialogInterface.OnClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f10short = null;
        final /* synthetic */ AdvancedPowerSavingActivity this$0;
        final /* synthetic */ SharedPreferences val$prefs;

        /* renamed from: com.Mode.toolbox.AdvancedPowerSavingActivity$6$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f11short = null;
            final /* synthetic */ AnonymousClass6 this$1;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0001: SPUT r14
                java.lang.IllegalArgumentException: newPosition > limit: (429648 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0007: INVOKE_INTERFACE_RANGE r14297, r14298, r14299, r14300, r14301, r14302, r14303, r14304, r14305, r14306, r14307, r14308, r14309, r14310, r14311, r14312, r14313, r14314, r14315, r14316, r14317, r14318, r14319, r14320, r14321, r14322, r14323, r14324, r14325, r14326, r14327, r14328, r14329, r14330, r14331, r14332, r14333, r14334, r14335, r14336, r14337, r14338, r14339, r14340, r14341, r14342, r14343, r14344, r14345, r14346, r14347, r14348, r14349, r14350, r14351, r14352, r14353, r14354, r14355, r14356, r14357, r14358, r14359, r14360, r14361, r14362, r14363, r14364, r14365, r14366, r14367, r14368, r14369, r14370, r14371, r14372, r14373, r14374, r14375, r14376, r14377, r14378, r14379, r14380, r14381, r14382, r14383, r14384, r14385, r14386, r14387, r14388, r14389, r14390, r14391, r14392, r14393, r14394, r14395, r14396, r14397, r14398, r14399, r14400, r14401, r14402, r14403, r14404, r14405, r14406, r14407, r14408, r14409, r14410, r14411, r14412, r14413, r14414, r14415, r14416, r14417, r14418, r14419, r14420, r14421, r14422, r14423, r14424, r14425, r14426, r14427, r14428, r14429, r14430, r14431, r14432, r14433, r14434, r14435, r14436, r14437, r14438, r14439, r14440, r14441, r14442, r14443, r14444, r14445, r14446, r14447, r14448, r14449, r14450, r14451, r14452, r14453, r14454, r14455, r14456, r14457, r14458, r14459, r14460, r14461, r14462, r14463, r14464, r14465, r14466, r14467, r14468, r14469, r14470, r14471, r14472, r14473, r14474, r14475, r14476, r14477, r14478, r14479, r14480, r14481, r14482, r14483, r14484, r14485, r14486, r14487, r14488, r14489, r14490, r14491, r14492, r14493, r14494, r14495, r14496, r14497, r14498, r14499, r14500, r14501, r14502, r14503, r14504, r14505, r14506, r14507, r14508, r14509, r14510, r14511, r14512, r14513, r14514, r14515, r14516, r14517, r14518, r14519, r14520, r14521, r14522, r14523, r14524, r14525, r14526, r14527, r14528, r14529, r14530, r14531, r14532, r14533, r14534, r14535, r14536, r14537, r14538, r14539, r14540, r14541, r14542, r14543, r14544, r14545, r14546, r14547, r14548, r14549, r14550
                java.lang.IllegalArgumentException: newPosition > limit: (678288 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0001: SPUT r14
                java.lang.IllegalArgumentException: newPosition > limit: (429648 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0006: UNKNOWN(0x59F9)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0006: UNKNOWN(0x59F9)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0007: INVOKE_INTERFACE_RANGE r14297, r14298, r14299, r14300, r14301, r14302, r14303, r14304, r14305, r14306, r14307, r14308, r14309, r14310, r14311, r14312, r14313, r14314, r14315, r14316, r14317, r14318, r14319, r14320, r14321, r14322, r14323, r14324, r14325, r14326, r14327, r14328, r14329, r14330, r14331, r14332, r14333, r14334, r14335, r14336, r14337, r14338, r14339, r14340, r14341, r14342, r14343, r14344, r14345, r14346, r14347, r14348, r14349, r14350, r14351, r14352, r14353, r14354, r14355, r14356, r14357, r14358, r14359, r14360, r14361, r14362, r14363, r14364, r14365, r14366, r14367, r14368, r14369, r14370, r14371, r14372, r14373, r14374, r14375, r14376, r14377, r14378, r14379, r14380, r14381, r14382, r14383, r14384, r14385, r14386, r14387, r14388, r14389, r14390, r14391, r14392, r14393, r14394, r14395, r14396, r14397, r14398, r14399, r14400, r14401, r14402, r14403, r14404, r14405, r14406, r14407, r14408, r14409, r14410, r14411, r14412, r14413, r14414, r14415, r14416, r14417, r14418, r14419, r14420, r14421, r14422, r14423, r14424, r14425, r14426, r14427, r14428, r14429, r14430, r14431, r14432, r14433, r14434, r14435, r14436, r14437, r14438, r14439, r14440, r14441, r14442, r14443, r14444, r14445, r14446, r14447, r14448, r14449, r14450, r14451, r14452, r14453, r14454, r14455, r14456, r14457, r14458, r14459, r14460, r14461, r14462, r14463, r14464, r14465, r14466, r14467, r14468, r14469, r14470, r14471, r14472, r14473, r14474, r14475, r14476, r14477, r14478, r14479, r14480, r14481, r14482, r14483, r14484, r14485, r14486, r14487, r14488, r14489, r14490, r14491, r14492, r14493, r14494, r14495, r14496, r14497, r14498, r14499, r14500, r14501, r14502, r14503, r14504, r14505, r14506, r14507, r14508, r14509, r14510, r14511, r14512, r14513, r14514, r14515, r14516, r14517, r14518, r14519, r14520, r14521, r14522, r14523, r14524, r14525, r14526, r14527, r14528, r14529, r14530, r14531, r14532, r14533, r14534, r14535, r14536, r14537, r14538, r14539, r14540, r14541, r14542, r14543, r14544, r14545, r14546, r14547, r14548, r14549, r14550
                java.lang.IllegalArgumentException: newPosition > limit: (678288 > 104176)
                	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
                	at java.base/java.nio.Buffer.position(Buffer.java:326)
                	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
                	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
                	at jadx.plugins.input.dex.sections.SectionReader.loadMethodRef(SectionReader.java:264)
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
            AnonymousClass1(com.Mode.toolbox.AdvancedPowerSavingActivity.AnonymousClass6 r52) {
                /*
                    r51 = this;
                    int r0 = r0 % r1
                    // decode failed: newPosition > limit: (429648 > 104176)
                    float r248 = r202 * r192
                    long r11 = r11 & r5
                    // decode failed: Unknown instruction: '0x0006: UNKNOWN(0x59F9)'
                    // decode failed: newPosition > limit: (678288 > 104176)
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.AdvancedPowerSavingActivity.AnonymousClass6.AnonymousClass1.<init>(com.Mode.toolbox.AdvancedPowerSavingActivity$6):void");
            }

            /* renamed from: ۠۠ۥۨ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass6 m30(Object obj);

            /* renamed from: ۥۢ۟۟, reason: contains not printable characters */
            public static native SharedPreferences m31(Object obj);

            /* renamed from: ۦۤ۟ۤ, reason: contains not printable characters */
            public static native short[] m32();

            /* renamed from: ۧۧ۟ۤ, reason: not valid java name and contains not printable characters */
            public static native AdvancedPowerSavingActivity m33(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0003: CHECK_CAST r18
            java.lang.IllegalArgumentException: newPosition > limit: (189480 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x000B: SGET r213
            java.lang.IllegalArgumentException: newPosition > limit: (134416 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0002: UNKNOWN(0xAE79)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0002: UNKNOWN(0xAE79)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0003: CHECK_CAST r18
            java.lang.IllegalArgumentException: newPosition > limit: (189480 > 104176)
            	at java.base/java.nio.Buffer.createPositionException(Buffer.java:351)
            	at java.base/java.nio.Buffer.position(Buffer.java:326)
            	at java.base/java.nio.ByteBuffer.position(ByteBuffer.java:1561)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:163)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsType(DexInsnData.java:126)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:363)
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
        /*  JADX ERROR: Failed to decode insn: 0x0008: UNKNOWN(0xB9E6)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0008: UNKNOWN(0xB9E6)'
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
        /*  JADX ERROR: Failed to decode insn: 0x000B: SGET r213
            java.lang.IllegalArgumentException: newPosition > limit: (134416 > 104176)
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
        AnonymousClass6(com.Mode.toolbox.AdvancedPowerSavingActivity r52, android.content.SharedPreferences r53) {
            /*
                r51 = this;
                com.Mode.toolbox.OutputPageActivity$MyHandler r29 = com.Mode.toolbox.MainActivity.AnonymousClass9.AnonymousClass1.<init>
                // decode failed: Unknown instruction: '0x0002: UNKNOWN(0xAE79)'
                // decode failed: newPosition > limit: (189480 > 104176)
                r30 = r17 ^ r57
                float r5 = r5 - r3
                // decode failed: Unknown instruction: '0x0008: UNKNOWN(0xB9E6)'
                long r83 = r156 >> r143
                // decode failed: newPosition > limit: (134416 > 104176)
                return r131
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.AdvancedPowerSavingActivity.AnonymousClass6.<init>(com.Mode.toolbox.AdvancedPowerSavingActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۟ۥۣۢ۠, reason: not valid java name and contains not printable characters */
        public static native AdvancedPowerSavingActivity m27(Object obj);

        /* renamed from: ۦۡۨ, reason: contains not printable characters */
        public static native short[] m28();

        /* renamed from: ۧۤۧ۟, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m29(Object obj);

        @Override // android.content.DialogInterface.OnClickListener
        public native void onClick(DialogInterface dialogInterface, int i);
    }

    /* renamed from: ۟ۤۨۧۧ, reason: not valid java name and contains not printable characters */
    public static native short[] m9();

    /* renamed from: ۟ۦۦۢۥ, reason: not valid java name and contains not printable characters */
    public static native SharedPreferences m10(Object obj);

    /* renamed from: ۢۤۨۥ, reason: not valid java name and contains not printable characters */
    public static native int m11(Object obj);

    public native void Back(View view);

    public native void Intelligentoptimizationapplication(View view);

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);
}
