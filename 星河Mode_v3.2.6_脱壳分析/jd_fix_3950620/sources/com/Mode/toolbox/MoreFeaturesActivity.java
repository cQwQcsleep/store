package com.Mode.toolbox;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;

/* loaded from: /workspace/xh/fix_3950620.dex */
public class MoreFeaturesActivity extends Activity {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f44short = null;
    private LinearLayout ColorOSContent;
    private boolean ColorOSExpanded;
    private LinearLayout CommonContent;
    private boolean CommonExpanded;
    private LinearLayout OriginOSContent;
    private boolean OriginOSExpanded;
    private ImageButton ToggleColorOS;
    private ImageButton ToggleCommon;
    private ImageButton ToggleOriginOS;
    private ImageButton ToggleXiaomi;
    private LinearLayout XiaomiContent;
    private boolean XiaomiExpanded;
    private SharedPreferences prefs;

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$1, reason: invalid class name */
    class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f45short = null;
        final /* synthetic */ MoreFeaturesActivity val$activity;
        final /* synthetic */ int val$mode;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.1.<init>(com.Mode.toolbox.MoreFeaturesActivity, int, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
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
        AnonymousClass1(com.Mode.toolbox.MoreFeaturesActivity r1, int r2, android.content.SharedPreferences r3) {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.1.<init>(com.Mode.toolbox.MoreFeaturesActivity, int, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass1.<init>(com.Mode.toolbox.MoreFeaturesActivity, int, android.content.SharedPreferences):void");
        }

        /* renamed from: ۣ۟ۤۧۦ, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m217(Object obj);

        /* renamed from: ۟ۧ۠ۦۨ, reason: not valid java name and contains not printable characters */
        public static native int m218(Object obj);

        /* renamed from: ۧۧ۟۟, reason: not valid java name and contains not printable characters */
        public static native short[] m219();

        /* renamed from: ۧۧۢ۟, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m220(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$10, reason: invalid class name */
    public class AnonymousClass10 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f46short = null;
        final /* synthetic */ MoreFeaturesActivity val$activity;
        final /* synthetic */ Switch val$otherSwitch;
        final /* synthetic */ SharedPreferences val$prefs;

        /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$10$1, reason: invalid class name */
        public class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f47short = null;
            final /* synthetic */ MoreFeaturesActivity val$activity;
            final /* synthetic */ Switch val$otherSwitch;
            final /* synthetic */ SharedPreferences val$prefs;

            /*  JADX ERROR: Dependency scan failed at insn: 0x000B: IPUT r4, r12
                java.lang.IllegalArgumentException: newPosition > limit: (352776 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0002: CONST_METHOD_TYPE r76
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0002: CONST_METHOD_TYPE r76'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x000B: IPUT r4, r12
                java.lang.IllegalArgumentException: newPosition > limit: (352776 > 104176)
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
            AnonymousClass1(com.Mode.toolbox.MoreFeaturesActivity r53, android.widget.Switch r54, android.content.SharedPreferences r55) {
                /*
                    r52 = this;
                    long r223 = r54 | r209
                    // decode failed: Unknown instruction: '0x0002: CONST_METHOD_TYPE r76'
                    int r248 = r238 % 109
                    int r11 = (int) r7
                    r244 = r4876
                    if (r255 != 0) goto LB_1c1d
                    // decode failed: newPosition > limit: (352776 > 104176)
                    double r132 = r87 * r173
                    int r9 = (-24789) - r15
                    long r11 = r11 / r13
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass10.AnonymousClass1.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.content.SharedPreferences):void");
            }

            /* renamed from: ۣ۟ۢ۟ۨ, reason: not valid java name and contains not printable characters */
            public static native Switch m225(Object obj);

            /* renamed from: ۟ۤۨۧۤ, reason: not valid java name and contains not printable characters */
            public static native SharedPreferences m226(Object obj);

            /* renamed from: ۣ۠ۢۨ, reason: not valid java name and contains not printable characters */
            public static native short[] m227();

            /* renamed from: ۠ۨۦۧ, reason: not valid java name and contains not printable characters */
            public static native MoreFeaturesActivity m228(Object obj);

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0003: INSTANCE_OF r0, r5
            java.lang.IllegalArgumentException: newPosition > limit: (170300 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x000C: IGET r12, r4
            java.lang.IllegalArgumentException: newPosition > limit: (177940 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x000E: IGET r1, r8
            java.lang.IllegalArgumentException: newPosition > limit: (360936 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0003: INSTANCE_OF r0, r5
            java.lang.IllegalArgumentException: newPosition > limit: (170300 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x000C: IGET r12, r4
            java.lang.IllegalArgumentException: newPosition > limit: (177940 > 104176)
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
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x000E: IGET r1, r8
            java.lang.IllegalArgumentException: newPosition > limit: (360936 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass10(com.Mode.toolbox.MoreFeaturesActivity r53, android.widget.Switch r54, android.content.SharedPreferences r55) {
            /*
                r52 = this;
                int r7 = r7 >>> r0
                int r53 = r86 - r118
                // decode failed: newPosition > limit: (170300 > 104176)
                float r13 = r13 + r12
                r77 = r243 & (-4)
                r10[r30] = r119
                long r15 = r34 >> r202
                // decode failed: newPosition > limit: (177940 > 104176)
                // decode failed: newPosition > limit: (360936 > 104176)
                double r6 = r6 * r4
                int r10 = (int) r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass10.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.content.SharedPreferences):void");
        }

        /* renamed from: ۟ۧ۟ۧۨ, reason: not valid java name and contains not printable characters */
        public static native Switch m221(Object obj);

        /* renamed from: ۣۤ۟ۤ, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m222(Object obj);

        /* renamed from: ۤۨ۠, reason: not valid java name and contains not printable characters */
        public static native short[] m223();

        /* renamed from: ۥۨۥۢ, reason: contains not printable characters */
        public static native SharedPreferences m224(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$11, reason: invalid class name */
    public class AnonymousClass11 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f48short = null;
        final /* synthetic */ MoreFeaturesActivity val$activity;
        final /* synthetic */ Switch val$otherSwitch;
        final /* synthetic */ SharedPreferences val$prefs;

        /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$11$1, reason: invalid class name */
        public class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f49short = null;
            final /* synthetic */ MoreFeaturesActivity val$activity;
            final /* synthetic */ Switch val$otherSwitch;
            final /* synthetic */ SharedPreferences val$prefs;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0002: SGET r206
                java.lang.IllegalArgumentException: newPosition > limit: (1936618460 > 104176)
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
            /*  JADX ERROR: Dependency scan failed at insn: 0x0004: IGET r14, r7
                java.lang.IllegalArgumentException: newPosition < 0: (-859488024 < 0)
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
            /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0xCEE4)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0xCEE4)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0001: UNKNOWN(0x30E3)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0001: UNKNOWN(0x30E3)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0002: SGET r206
                java.lang.IllegalArgumentException: newPosition > limit: (1936618460 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0004: IGET r14, r7
                java.lang.IllegalArgumentException: newPosition < 0: (-859488024 < 0)
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
            AnonymousClass1(com.Mode.toolbox.MoreFeaturesActivity r53, android.widget.Switch r54, android.content.SharedPreferences r55) {
                /*
                    r52 = this;
                    // decode failed: Unknown instruction: '0x0000: UNKNOWN(0xCEE4)'
                    // decode failed: Unknown instruction: '0x0001: UNKNOWN(0x30E3)'
                    // decode failed: newPosition > limit: (1936618460 > 104176)
                    // decode failed: newPosition < 0: (-859488024 < 0)
                    long r6 = -r7
                    r8 = r8 | r3
                    r166 = 2692310497250103542(0x255d0221fda5d0f6, double:1.0462238333015552E-128)
                    int r205 = (r77 > r143 ? 1 : (r77 == r143 ? 0 : -1))
                    double r5 = r5 / r7
                    r188[r20] = r128
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass11.AnonymousClass1.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.content.SharedPreferences):void");
            }

            /* renamed from: ۣ۟ۢۨۥ, reason: not valid java name and contains not printable characters */
            public static native MoreFeaturesActivity m233(Object obj);

            /* renamed from: ۠۟ۥۢ, reason: not valid java name and contains not printable characters */
            public static native SharedPreferences m234(Object obj);

            /* renamed from: ۡۦ۟۠, reason: not valid java name and contains not printable characters */
            public static native short[] m235();

            /* renamed from: ۦۣ۠ۢ, reason: contains not printable characters */
            public static native Switch m236(Object obj);

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0001: IPUT r15, r6
            java.lang.IllegalArgumentException: newPosition > limit: (426896 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x000D: SGET r218
            java.lang.IllegalArgumentException: newPosition > limit: (358296 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0010: SPUT r20
            java.lang.IllegalArgumentException: newPosition > limit: (478216 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0001: IPUT r15, r6
            java.lang.IllegalArgumentException: newPosition > limit: (426896 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x000D: SGET r218
            java.lang.IllegalArgumentException: newPosition > limit: (358296 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0010: SPUT r20
            java.lang.IllegalArgumentException: newPosition > limit: (478216 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass11(com.Mode.toolbox.MoreFeaturesActivity r53, android.widget.Switch r54, android.content.SharedPreferences r55) {
            /*
                r52 = this;
                float r2 = r2 * r11
                // decode failed: newPosition > limit: (426896 > 104176)
                float r1 = (float) r13
                long r10 = r10 | r8
                int r229 = (r54 > r94 ? 1 : (r54 == r94 ? 0 : -1))
                if (r5 == r12) goto LB_2db4
                int r232 = r176 >>> r115
                r76 = r12 ^ r105
                // decode failed: newPosition > limit: (358296 > 104176)
                int r1 = r1 << r0
                // decode failed: newPosition > limit: (478216 > 104176)
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass11.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.content.SharedPreferences):void");
        }

        /* renamed from: ۣ۟۟ۥۧ, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m229(Object obj);

        /* renamed from: ۟ۦۣۡۡ, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m230(Object obj);

        /* renamed from: ۣۢ۟ۦ, reason: not valid java name and contains not printable characters */
        public static native Switch m231(Object obj);

        /* renamed from: ۨۥ۠ۨ, reason: not valid java name and contains not printable characters */
        public static native short[] m232();

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$12, reason: invalid class name */
    public class AnonymousClass12 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f50short = null;
        final /* synthetic */ MoreFeaturesActivity val$activity;
        final /* synthetic */ Switch val$otherSwitch1;
        final /* synthetic */ Switch val$otherSwitch2;
        final /* synthetic */ SharedPreferences val$prefs;

        /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$12$1, reason: invalid class name */
        public class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f51short = null;
            final /* synthetic */ MoreFeaturesActivity val$activity;
            final /* synthetic */ Switch val$otherSwitch1;
            final /* synthetic */ Switch val$otherSwitch2;
            final /* synthetic */ SharedPreferences val$prefs;

            /*  JADX ERROR: Method load error
                jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.12.1.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.widget.Switch, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
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
            AnonymousClass1(com.Mode.toolbox.MoreFeaturesActivity r1, android.widget.Switch r2, android.widget.Switch r3, android.content.SharedPreferences r4) {
                /*
                // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.12.1.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.widget.Switch, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass12.AnonymousClass1.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.widget.Switch, android.content.SharedPreferences):void");
            }

            /* renamed from: ۟ۡۥۢۥ, reason: not valid java name and contains not printable characters */
            public static native short[] m242();

            /* renamed from: ۟ۥۤۧۡ, reason: not valid java name and contains not printable characters */
            public static native Switch m243(Object obj);

            /* renamed from: ۟ۦۧۦۥ, reason: not valid java name and contains not printable characters */
            public static native MoreFeaturesActivity m244(Object obj);

            /* renamed from: ۠ۢ۟۟, reason: not valid java name and contains not printable characters */
            public static native Switch m245(Object obj);

            /* renamed from: ۤ۟ۨۡ, reason: not valid java name and contains not printable characters */
            public static native SharedPreferences m246(Object obj);

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
        }

        /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$12$2, reason: invalid class name */
        public class AnonymousClass2 implements CompoundButton.OnCheckedChangeListener {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f52short = null;
            final /* synthetic */ MoreFeaturesActivity val$activity;
            final /* synthetic */ Switch val$otherSwitch1;
            final /* synthetic */ Switch val$otherSwitch2;
            final /* synthetic */ SharedPreferences val$prefs;

            /*  JADX ERROR: Dependency scan failed at insn: 0x000A: SPUT r114
                java.lang.IllegalArgumentException: newPosition < 0: (-1243871120 < 0)
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
            /*  JADX ERROR: Failed to decode insn: 0x000A: SPUT r114
                java.lang.IllegalArgumentException: newPosition < 0: (-1243871120 < 0)
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
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0014: UNKNOWN(0x18E3)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0014: UNKNOWN(0x18E3)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            AnonymousClass2(com.Mode.toolbox.MoreFeaturesActivity r52, android.widget.Switch r53, android.widget.Switch r54, android.content.SharedPreferences r55) {
                /*
                    r51 = this;
                    int r139 = r41 / 127
                    goto LB_7248bb7b
                    monitor-exit(r170)
                    int r2 = r14 + 1595
                    double r95 = r132 % r88
                    // decode failed: newPosition < 0: (-1243871120 < 0)
                    r196 = r19 | r228
                    double r10 = -r13
                    if (r12 < r2) goto L8fb
                    int r183 = r6 >> r92
                    long r3 = r3 | r14
                    // decode failed: Unknown instruction: '0x0014: UNKNOWN(0x18E3)'
                    r7 = r7 ^ r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass12.AnonymousClass2.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.widget.Switch, android.content.SharedPreferences):void");
            }

            /* renamed from: ۟ۥۨۨۨ, reason: not valid java name and contains not printable characters */
            public static native Switch m247(Object obj);

            /* renamed from: ۟ۦۡۡۥ, reason: not valid java name and contains not printable characters */
            public static native SharedPreferences m248(Object obj);

            /* renamed from: ۦۣ۟ۤ, reason: contains not printable characters */
            public static native Switch m249(Object obj);

            /* renamed from: ۧۥۡۧ, reason: not valid java name and contains not printable characters */
            public static native short[] m250();

            /* renamed from: ۣۨۧۤ, reason: not valid java name and contains not printable characters */
            public static native MoreFeaturesActivity m251(Object obj);

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.12.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.widget.Switch, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
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
        AnonymousClass12(com.Mode.toolbox.MoreFeaturesActivity r1, android.widget.Switch r2, android.widget.Switch r3, android.content.SharedPreferences r4) {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.12.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.widget.Switch, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass12.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.Switch, android.widget.Switch, android.content.SharedPreferences):void");
        }

        /* renamed from: ۣ۟ۧۥۣ, reason: not valid java name and contains not printable characters */
        public static native Switch m237(Object obj);

        /* renamed from: ۟ۥۥۥۧ, reason: not valid java name and contains not printable characters */
        public static native Switch m238(Object obj);

        /* renamed from: ۣ۠ۨۢ, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m239(Object obj);

        /* renamed from: ۥۣ۠۟, reason: contains not printable characters */
        public static native MoreFeaturesActivity m240(Object obj);

        /* renamed from: ۦۤۥۤ, reason: contains not printable characters */
        public static native short[] m241();

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$13, reason: invalid class name */
    class AnonymousClass13 implements SeekBar.OnSeekBarChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f53short = null;
        final /* synthetic */ MoreFeaturesActivity this$0;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0000: IPUT r3, r11
            java.lang.IllegalArgumentException: newPosition > limit: (301216 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: IPUT r3, r11
            java.lang.IllegalArgumentException: newPosition > limit: (301216 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0006: UNKNOWN(0x4679)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0006: UNKNOWN(0x4679)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0007: UNKNOWN(0x7242)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0007: UNKNOWN(0x7242)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass13(com.Mode.toolbox.MoreFeaturesActivity r52, android.content.SharedPreferences r53) {
            /*
                r51 = this;
                // decode failed: newPosition > limit: (301216 > 104176)
                r213 = r131[r144]
                r112 = r32380
                // decode failed: Unknown instruction: '0x0006: UNKNOWN(0x4679)'
                // decode failed: Unknown instruction: '0x0007: UNKNOWN(0x7242)'
                r2 = r216 ^ r150
                long r3 = r3 << r12
                float r146 = r186 - r99
                android.widget.TextView r221 = com.Mode.toolbox.BackupActivity.backupSizeText
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass13.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۟ۧۢۥۢ, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m252(Object obj);

        /* renamed from: ۡۢۧۡ, reason: not valid java name and contains not printable characters */
        public static native int m253(Object obj);

        /* renamed from: ۤ۟ۡ۠, reason: not valid java name and contains not printable characters */
        public static native short[] m254();

        /* renamed from: ۥۣۤۡ, reason: contains not printable characters */
        public static native MoreFeaturesActivity m255(Object obj);

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public native void onProgressChanged(SeekBar seekBar, int i, boolean z);

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public native void onStartTrackingTouch(SeekBar seekBar);

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public native void onStopTrackingTouch(SeekBar seekBar);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$2, reason: invalid class name */
    class AnonymousClass2 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f54short = null;
        final /* synthetic */ MoreFeaturesActivity val$activity;
        final /* synthetic */ int val$mode;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0007: SGET r79
            java.lang.IllegalArgumentException: newPosition > limit: (529416 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0004: UNKNOWN(0xE8E5)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0004: UNKNOWN(0xE8E5)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0007: SGET r79
            java.lang.IllegalArgumentException: newPosition > limit: (529416 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0009: UNKNOWN(0xF1E8)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0009: UNKNOWN(0xF1E8)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass2(com.Mode.toolbox.MoreFeaturesActivity r53, int r54, android.content.SharedPreferences r55) {
            /*
                r52 = this;
                r230 = r81 ^ r209
                r135 = r8 ^ r205
                // decode failed: Unknown instruction: '0x0004: UNKNOWN(0xE8E5)'
                int r105 = r214 >> 9
                // decode failed: newPosition > limit: (529416 > 104176)
                // decode failed: Unknown instruction: '0x0009: UNKNOWN(0xF1E8)'
                if (r211 == 0) goto LB_27db
                switch(r63) {
                // error: 0x000c: SWITCH (r63 I:??)no payload
                float r2 = r2 / r7
                int r66 = r134 >>> r39
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass2.<init>(com.Mode.toolbox.MoreFeaturesActivity, int, android.content.SharedPreferences):void");
        }

        /* renamed from: ۟ۦۣۢۧ, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m256(Object obj);

        /* renamed from: ۟ۦۨۥ۟, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m257(Object obj);

        /* renamed from: ۡۥ۠ۦ, reason: not valid java name and contains not printable characters */
        public static native int m258(Object obj);

        /* renamed from: ۣۧ۠۠, reason: not valid java name and contains not printable characters */
        public static native short[] m259();

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements DialogInterface.OnClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f55short = null;
        final /* synthetic */ MoreFeaturesActivity this$0;
        final /* synthetic */ EditText val$editText;
        final /* synthetic */ boolean val$isReset;

        /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$3$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f56short = null;
            final /* synthetic */ AnonymousClass3 this$1;
            final /* synthetic */ String val$temperature;

            /*  JADX ERROR: Dependency scan failed at insn: 0x0006: IGET r6, r12
                java.lang.IllegalArgumentException: newPosition > limit: (136716 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0006: IGET r6, r12
                java.lang.IllegalArgumentException: newPosition > limit: (136716 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0008: UNKNOWN(0xECF0)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0008: UNKNOWN(0xECF0)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            public AnonymousClass1(com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass3 r52, java.lang.String r53) {
                /*
                    r51 = this;
                    float r4 = r4 * r11
                    if (r132 <= 0) goto L69d6
                    int r15 = r12 + (-13225)
                    long r9 = ~r3
                    // decode failed: newPosition > limit: (136716 > 104176)
                    // decode failed: Unknown instruction: '0x0008: UNKNOWN(0xECF0)'
                    int r10 = r10 + r10
                    int r12 = (int) r8
                    int r229 = (r56 > r106 ? 1 : (r56 == r106 ? 0 : -1))
                    if (r5 < r8) goto L41
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass3.AnonymousClass1.<init>(com.Mode.toolbox.MoreFeaturesActivity$3, java.lang.String):void");
            }

            /* renamed from: ۟ۤۦ۠ۨ, reason: not valid java name and contains not printable characters */
            public static native short[] m264();

            /* renamed from: ۢۢۥۣ, reason: not valid java name and contains not printable characters */
            public static native MoreFeaturesActivity m265(Object obj);

            /* renamed from: ۣۤ۠ۧ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass3 m266(Object obj);

            /* renamed from: ۧۧۡۧ, reason: not valid java name and contains not printable characters */
            public static native String m267(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$3$2, reason: invalid class name */
        public class AnonymousClass2 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f57short = null;
            final /* synthetic */ AnonymousClass1 this$2;

            /*  JADX ERROR: Failed to decode insn: 0x0001: UNKNOWN(0xC67A)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0001: UNKNOWN(0xC67A)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0005: UNKNOWN(0xC2F3)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0005: UNKNOWN(0xC2F3)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0006: UNKNOWN(0x84F2)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0006: UNKNOWN(0x84F2)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            public AnonymousClass2(com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass3.AnonymousClass1 r52) {
                /*
                    r51 = this;
                    float r14 = -r6
                    // decode failed: Unknown instruction: '0x0001: UNKNOWN(0xC67A)'
                    r26076 = r65130
                    // decode failed: Unknown instruction: '0x0005: UNKNOWN(0xC2F3)'
                    // decode failed: Unknown instruction: '0x0006: UNKNOWN(0x84F2)'
                    int r9 = r183 * r102
                    long r38 = r52 ^ r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass3.AnonymousClass2.<init>(com.Mode.toolbox.MoreFeaturesActivity$3$1):void");
            }

            /* renamed from: ۟ۡۡۢۨ, reason: not valid java name and contains not printable characters */
            public static native String m268(Object obj);

            /* renamed from: ۟ۤ۟ۥۥ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass1 m269(Object obj);

            /* renamed from: ۟ۧۡ۟ۦ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass3 m270(Object obj);

            /* renamed from: ۡۡۦۦ, reason: not valid java name and contains not printable characters */
            public static native MoreFeaturesActivity m271(Object obj);

            /* renamed from: ۨ۟ۨۤ, reason: not valid java name and contains not printable characters */
            public static native short[] m272();

            @Override // java.lang.Runnable
            public native void run();
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.3.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.EditText, boolean):void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
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
        public AnonymousClass3(com.Mode.toolbox.MoreFeaturesActivity r1, android.widget.EditText r2, boolean r3) {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.3.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.EditText, boolean):void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass3.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.EditText, boolean):void");
        }

        /* renamed from: ۟ۦۣۢۧ, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m260(Object obj);

        /* renamed from: ۤۢ۟ۢ, reason: not valid java name and contains not printable characters */
        public static native boolean m261(Object obj);

        /* renamed from: ۦۣۦۢ, reason: contains not printable characters */
        public static native EditText m262(Object obj);

        /* renamed from: ۨۧۦۥ, reason: not valid java name and contains not printable characters */
        public static native short[] m263();

        @Override // android.content.DialogInterface.OnClickListener
        public native void onClick(DialogInterface dialogInterface, int i);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements DialogInterface.OnClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f58short = null;
        final /* synthetic */ MoreFeaturesActivity this$0;
        final /* synthetic */ EditText val$editText;
        final /* synthetic */ boolean val$isReset;

        /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$4$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f59short = null;
            final /* synthetic */ AnonymousClass4 this$1;
            final /* synthetic */ String val$level;

            /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
                jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x0000: APUT (r73 I:??[OBJECT, ARRAY][]), (r159 I:??[int, short, byte, char]), (r1 I:??[OBJECT, ARRAY]), expected to be less than 54
                	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:79)
                	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
                */
            public AnonymousClass1(com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass4 r52, java.lang.String r53) {
                /*
                    r51 = this;
                    r73[r159] = r1
                    int r4 = r4 >>> r12
                    r2 = r8
                    double r7 = r7 % r15
                    float r9 = r9 + r0
                    long r4 = -r0
                    int r111 = r213 * (-7)
                    r24 = 6201019(0x5e9ebb, float:8.689478E-39)
                    int r2 = (int) r4
                    r3 = r3 & r12
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass4.AnonymousClass1.<init>(com.Mode.toolbox.MoreFeaturesActivity$4, java.lang.String):void");
            }

            /* renamed from: ۟۟۠ۧۨ, reason: not valid java name and contains not printable characters */
            public static native String m277(Object obj);

            /* renamed from: ۟۠ۧۧۥ, reason: not valid java name and contains not printable characters */
            public static native MoreFeaturesActivity m278(Object obj);

            /* renamed from: ۟ۥ۟۟, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass4 m279(Object obj);

            /* renamed from: ۟ۦۦۥۢ, reason: not valid java name and contains not printable characters */
            public static native short[] m280();

            @Override // java.lang.Runnable
            public native void run();
        }

        /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$4$2, reason: invalid class name */
        public class AnonymousClass2 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f60short = null;
            final /* synthetic */ AnonymousClass1 this$2;

            /*  JADX ERROR: Method load error
                jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.4.2.<init>(com.Mode.toolbox.MoreFeaturesActivity$4$1):void, file: /workspace/xh/fix_3950620.dex
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
            public AnonymousClass2(com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass4.AnonymousClass1 r1) {
                /*
                // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.4.2.<init>(com.Mode.toolbox.MoreFeaturesActivity$4$1):void, file: /workspace/xh/fix_3950620.dex
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass4.AnonymousClass2.<init>(com.Mode.toolbox.MoreFeaturesActivity$4$1):void");
            }

            /* renamed from: ۣ۟۟۟ۢ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass4 m281(Object obj);

            /* renamed from: ۠ۢۦۨ, reason: not valid java name and contains not printable characters */
            public static native short[] m282();

            /* renamed from: ۡۥ۟, reason: not valid java name and contains not printable characters */
            public static native MoreFeaturesActivity m283(Object obj);

            /* renamed from: ۢ۟ۥ۠, reason: not valid java name and contains not printable characters */
            public static native String m284(Object obj);

            /* renamed from: ۥۢۤۦ, reason: contains not printable characters */
            public static native AnonymousClass1 m285(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x0003: INVOKE_DIRECT_RANGE r64385, r64386, r64387, r64388, r64389, r64390, r64391, r64392, r64393, r64394, r64395, r64396, r64397, r64398, r64399, r64400, r64401, r64402, r64403, r64404, r64405, r64406, r64407, r64408, r64409, r64410, r64411, r64412, r64413, r64414, r64415, r64416, r64417, r64418, r64419, r64420, r64421, r64422, r64423, r64424, r64425, r64426, r64427
            java.lang.IllegalArgumentException: newPosition > limit: (129104 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0006: INVOKE_DIRECT r14, r4, r15, r14
            java.lang.IllegalArgumentException: newPosition > limit: (486432 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x77F8)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x77F8)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0003: INVOKE_DIRECT_RANGE r64385, r64386, r64387, r64388, r64389, r64390, r64391, r64392, r64393, r64394, r64395, r64396, r64397, r64398, r64399, r64400, r64401, r64402, r64403, r64404, r64405, r64406, r64407, r64408, r64409, r64410, r64411, r64412, r64413, r64414, r64415, r64416, r64417, r64418, r64419, r64420, r64421, r64422, r64423, r64424, r64425, r64426, r64427
            java.lang.IllegalArgumentException: newPosition > limit: (129104 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0006: INVOKE_DIRECT r14, r4, r15, r14
            java.lang.IllegalArgumentException: newPosition > limit: (486432 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        public AnonymousClass4(com.Mode.toolbox.MoreFeaturesActivity r52, android.widget.EditText r53, boolean r54) {
            /*
                r51 = this;
                // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x77F8)'
                float r6 = r95 - r37
                // decode failed: newPosition > limit: (129104 > 104176)
                // decode failed: newPosition > limit: (486432 > 104176)
                r12 = r5 & (-1061(0xfffffffffffffbdb, float:NaN))
                long r205 = r199 & r218
                if (r122 > 0) goto L632f
                r198 = r0 & 126(0x7e, float:1.77E-43)
                r11 = r11 | r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass4.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.widget.EditText, boolean):void");
        }

        /* renamed from: ۟ۡۦۨ۠, reason: not valid java name and contains not printable characters */
        public static native boolean m273(Object obj);

        /* renamed from: ۟ۤ۠۟ۧ, reason: not valid java name and contains not printable characters */
        public static native short[] m274();

        /* renamed from: ۟ۤۥۦۨ, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m275(Object obj);

        /* renamed from: ۟ۧ۟۠ۡ, reason: not valid java name and contains not printable characters */
        public static native EditText m276(Object obj);

        @Override // android.content.DialogInterface.OnClickListener
        public native void onClick(DialogInterface dialogInterface, int i);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$6, reason: invalid class name */
    class AnonymousClass6 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f61short = null;
        final /* synthetic */ MoreFeaturesActivity val$activity;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Failed to decode insn: 0x0001: UNKNOWN(0xE3E3)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0001: UNKNOWN(0xE3E3)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x000C: UNKNOWN(0x86E7)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000C: UNKNOWN(0x86E7)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass6(com.Mode.toolbox.MoreFeaturesActivity r53, android.content.SharedPreferences r54) {
            /*
                r52 = this;
                int r2 = r2 + r7
                // decode failed: Unknown instruction: '0x0001: UNKNOWN(0xE3E3)'
                r2044 = r51399
                r1 = r1 | r4
                long r1 = r1 >> r1
                r27298 = r7364
                int r13 = r7 + (-25216)
                // decode failed: Unknown instruction: '0x000C: UNKNOWN(0x86E7)'
                com.Mode.toolbox.AdvancedPowerSavingActivity.AnonymousClass2.m15(r0)
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass6.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۟ۤۡۥ۠, reason: not valid java name and contains not printable characters */
        public static native short[] m286();

        /* renamed from: ۟ۦۥۤ۟, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m287(Object obj);

        /* renamed from: ۤۧۧۢ, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m288(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$7, reason: invalid class name */
    class AnonymousClass7 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f62short = null;
        final /* synthetic */ MoreFeaturesActivity val$activity;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0008: CONST_CLASS r119
            java.lang.IllegalArgumentException: newPosition < 0: (-1508559828 < 0)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x43EE)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x43EE)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0008: CONST_CLASS r119
            java.lang.IllegalArgumentException: newPosition < 0: (-1508559828 < 0)
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
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass7(com.Mode.toolbox.MoreFeaturesActivity r53, android.content.SharedPreferences r54) {
            /*
                r52 = this;
                // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x43EE)'
                r54 = -31482(0xffffffffffff8506, float:NaN)
                if (r15 >= r0) goto LB_335a
                if (r7 <= r13) goto L525f
                float r5 = (float) r12
                // decode failed: newPosition < 0: (-1508559828 < 0)
                long r242 = r175 / r74
                int r0 = r0 / r6
                int r103 = r52 >>> 0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass7.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۟۟۠۠, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m289(Object obj);

        /* renamed from: ۟ۡۤۡۢ, reason: not valid java name and contains not printable characters */
        public static native short[] m290();

        /* renamed from: ۟ۧ۟ۥۣ, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m291(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$8, reason: invalid class name */
    class AnonymousClass8 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f63short = null;
        final /* synthetic */ MoreFeaturesActivity val$activity;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0003: SGET r53
            java.lang.IllegalArgumentException: newPosition > limit: (248296 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0005: SPUT r61
            java.lang.IllegalArgumentException: newPosition > limit: (343304 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0003: SGET r53
            java.lang.IllegalArgumentException: newPosition > limit: (248296 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0005: SPUT r61
            java.lang.IllegalArgumentException: newPosition > limit: (343304 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x000B: UNKNOWN(0x3D41)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000B: UNKNOWN(0x3D41)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass8(com.Mode.toolbox.MoreFeaturesActivity r53, android.content.SharedPreferences r54) {
            /*
                r52 = this;
                return
                r9.<init> = r10
                // decode failed: newPosition > limit: (248296 > 104176)
                // decode failed: newPosition > limit: (343304 > 104176)
                int r2 = r2 >>> r4
                byte r1 = (byte) r0
                char r34 = r122[r32]
                // decode failed: Unknown instruction: '0x000B: UNKNOWN(0x3D41)'
                r8 = r15 | (-28092(0xffffffffffff9244, float:NaN))
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass8.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۟ۥۣۦ۟, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m292(Object obj);

        /* renamed from: ۟ۦۣۤۧ, reason: not valid java name and contains not printable characters */
        public static native short[] m293();

        /* renamed from: ۦۨۦۣ, reason: contains not printable characters */
        public static native SharedPreferences m294(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.MoreFeaturesActivity$9, reason: invalid class name */
    class AnonymousClass9 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f64short = null;
        final /* synthetic */ MoreFeaturesActivity val$activity;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.9.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
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
        AnonymousClass9(com.Mode.toolbox.MoreFeaturesActivity r1, android.content.SharedPreferences r2) {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.MoreFeaturesActivity.9.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.content.SharedPreferences):void, file: /workspace/xh/fix_3950620.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.MoreFeaturesActivity.AnonymousClass9.<init>(com.Mode.toolbox.MoreFeaturesActivity, android.content.SharedPreferences):void");
        }

        /* renamed from: ۣ۠۟۠, reason: not valid java name and contains not printable characters */
        public static native MoreFeaturesActivity m295(Object obj);

        /* renamed from: ۣ۠۠ۧ, reason: not valid java name and contains not printable characters */
        public static native short[] m296();

        /* renamed from: ۧ۟۠۠, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m297(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: ۣ۟ۦۡۢ, reason: not valid java name and contains not printable characters */
    public static native ImageButton m206(Object obj);

    /* renamed from: ۟ۤۦ۟ۧ, reason: not valid java name and contains not printable characters */
    public static native ImageButton m207(Object obj);

    /* renamed from: ۟ۥۣۤۢ, reason: not valid java name and contains not printable characters */
    public static native int m208(Object obj);

    /* renamed from: ۟ۥۥۨۢ, reason: not valid java name and contains not printable characters */
    public static native LinearLayout m209(Object obj);

    /* renamed from: ۟ۥۦۣۥ, reason: not valid java name and contains not printable characters */
    public static native ImageButton m210(Object obj);

    /* renamed from: ۟ۦۣۢۧ, reason: not valid java name and contains not printable characters */
    public static native SharedPreferences m211(Object obj);

    /* renamed from: ۟ۧ۟ۢۨ, reason: not valid java name and contains not printable characters */
    public static native short[] m212();

    /* renamed from: ۟ۧۧۧۨ, reason: not valid java name and contains not printable characters */
    public static native ImageButton m213(Object obj);

    /* renamed from: ۥۢۦۢ, reason: contains not printable characters */
    public static native LinearLayout m214(Object obj);

    /* renamed from: ۧۥۨۢ, reason: not valid java name and contains not printable characters */
    public static native LinearLayout m215(Object obj);

    /* renamed from: ۧۧۧ۠, reason: not valid java name and contains not printable characters */
    public static native LinearLayout m216(Object obj);

    public native void AdvancedPowerSaving(View view);

    public native void Back(View view);

    public native void ColorOS(View view);

    public native void Common(View view);

    public native void GameDeveloper(View view);

    public native void Modifybattery(View view);

    public native void Modifytemperature(View view);

    public native void Moreoptimization(View view);

    public native void OriginOS(View view);

    public native boolean ShizukuExec(String str);

    public native void Xiaomi(View view);

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    public native void onMorePageClick(View view);
}
