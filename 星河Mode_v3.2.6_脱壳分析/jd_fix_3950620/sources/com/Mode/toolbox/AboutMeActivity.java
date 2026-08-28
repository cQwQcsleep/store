package com.Mode.toolbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.PopupMenu;

/* loaded from: /workspace/xh/fix_3950620.dex */
public class AboutMeActivity extends Activity {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f0short = null;
    private SharedPreferences prefs;

    /* renamed from: com.Mode.toolbox.AboutMeActivity$1, reason: invalid class name */
    class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f1short = null;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0007: INVOKE_STATIC_RANGE r27199, r27200, r27201, r27202, r27203, r27204, r27205, r27206, r27207, r27208, r27209, r27210, r27211, r27212, r27213, r27214, r27215, r27216, r27217, r27218, r27219, r27220, r27221, r27222, r27223, r27224, r27225, r27226, r27227, r27228, r27229, r27230, r27231, r27232, r27233, r27234, r27235, r27236, r27237, r27238, r27239, r27240, r27241, r27242, r27243, r27244, r27245, r27246, r27247, r27248, r27249, r27250, r27251, r27252, r27253, r27254, r27255, r27256, r27257, r27258, r27259, r27260, r27261, r27262, r27263, r27264, r27265, r27266, r27267, r27268, r27269, r27270, r27271, r27272, r27273, r27274, r27275, r27276, r27277, r27278, r27279, r27280, r27281, r27282, r27283, r27284, r27285, r27286, r27287, r27288, r27289, r27290, r27291, r27292, r27293, r27294, r27295, r27296, r27297, r27298, r27299, r27300, r27301, r27302, r27303, r27304, r27305, r27306, r27307, r27308, r27309, r27310, r27311, r27312, r27313, r27314, r27315, r27316, r27317, r27318, r27319, r27320
            java.lang.IllegalArgumentException: newPosition > limit: (370344 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x8442)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x8442)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0001: UNKNOWN(0x50E5)
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0001: UNKNOWN(0x50E5)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0007: INVOKE_STATIC_RANGE r27199, r27200, r27201, r27202, r27203, r27204, r27205, r27206, r27207, r27208, r27209, r27210, r27211, r27212, r27213, r27214, r27215, r27216, r27217, r27218, r27219, r27220, r27221, r27222, r27223, r27224, r27225, r27226, r27227, r27228, r27229, r27230, r27231, r27232, r27233, r27234, r27235, r27236, r27237, r27238, r27239, r27240, r27241, r27242, r27243, r27244, r27245, r27246, r27247, r27248, r27249, r27250, r27251, r27252, r27253, r27254, r27255, r27256, r27257, r27258, r27259, r27260, r27261, r27262, r27263, r27264, r27265, r27266, r27267, r27268, r27269, r27270, r27271, r27272, r27273, r27274, r27275, r27276, r27277, r27278, r27279, r27280, r27281, r27282, r27283, r27284, r27285, r27286, r27287, r27288, r27289, r27290, r27291, r27292, r27293, r27294, r27295, r27296, r27297, r27298, r27299, r27300, r27301, r27302, r27303, r27304, r27305, r27306, r27307, r27308, r27309, r27310, r27311, r27312, r27313, r27314, r27315, r27316, r27317, r27318, r27319, r27320
            java.lang.IllegalArgumentException: newPosition > limit: (370344 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass1(android.content.SharedPreferences r52) {
            /*
                r51 = this;
                // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x8442)'
                // decode failed: Unknown instruction: '0x0001: UNKNOWN(0x50E5)'
                if (r10 > r15) goto LB_202c
                int r151 = r98 >> r247
                double r3 = (double) r12
                // decode failed: newPosition > limit: (370344 > 104176)
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.AboutMeActivity.AnonymousClass1.<init>(android.content.SharedPreferences):void");
        }

        /* renamed from: ۟ۢ۠ۢۤ, reason: not valid java name and contains not printable characters */
        public static native short[] m3();

        /* renamed from: ۟ۧۦۨۤ, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m4(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.AboutMeActivity$2, reason: invalid class name */
    class AnonymousClass2 implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f2short = null;
        final /* synthetic */ SharedPreferences val$prefs;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0008: INVOKE_SUPER_RANGE r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140, r141, r142, r143, r144, r145, r146, r147, r148, r149, r150, r151, r152, r153, r154, r155, r156, r157, r158, r159, r160, r161, r162, r163, r164, r165, r166, r167, r168, r169, r170, r171, r172
            java.lang.IllegalArgumentException: newPosition > limit: (170848 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0008: INVOKE_SUPER_RANGE r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140, r141, r142, r143, r144, r145, r146, r147, r148, r149, r150, r151, r152, r153, r154, r155, r156, r157, r158, r159, r160, r161, r162, r163, r164, r165, r166, r167, r168, r169, r170, r171, r172
            java.lang.IllegalArgumentException: newPosition > limit: (170848 > 104176)
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
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        AnonymousClass2(android.content.SharedPreferences r52) {
            /*
                r51 = this;
                r10 = r15 | 13213(0x339d, float:1.8515E-41)
                r129[r247] = r26
                long r4 = (long) r13
                r47 = r40208
                double r7 = (double) r11
                // decode failed: newPosition > limit: (170848 > 104176)
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.AboutMeActivity.AnonymousClass2.<init>(android.content.SharedPreferences):void");
        }

        /* renamed from: ۟ۥۡۦۥ, reason: not valid java name and contains not printable characters */
        public static native short[] m5();

        /* renamed from: ۡۤ۠ۡ, reason: not valid java name and contains not printable characters */
        public static native SharedPreferences m6(Object obj);

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public native void onCheckedChanged(CompoundButton compoundButton, boolean z);
    }

    /* renamed from: com.Mode.toolbox.AboutMeActivity$3, reason: invalid class name */
    class AnonymousClass3 implements PopupMenu.OnMenuItemClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f3short = null;
        final /* synthetic */ AboutMeActivity this$0;

        /*  JADX ERROR: Dependency scan failed at insn: 0x0002: INVOKE_SUPER r13, r7, r7, r5, r4
            java.lang.IllegalArgumentException: newPosition > limit: (529688 > 104176)
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
        /*  JADX ERROR: Dependency scan failed at insn: 0x0005: IGET r14, r11
            java.lang.IllegalArgumentException: newPosition > limit: (284760 > 104176)
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
        /*  JADX ERROR: Failed to decode insn: 0x0002: INVOKE_SUPER r13, r7, r7, r5, r4
            java.lang.IllegalArgumentException: newPosition > limit: (529688 > 104176)
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
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0005: IGET r14, r11
            java.lang.IllegalArgumentException: newPosition > limit: (284760 > 104176)
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
        AnonymousClass3(com.Mode.toolbox.AboutMeActivity r52) {
            /*
                r51 = this;
                int r7 = r7 * (-2263)
                // decode failed: newPosition > limit: (529688 > 104176)
                // decode failed: newPosition > limit: (284760 > 104176)
                r3 = r10 | 26498(0x6782, float:3.7132E-41)
                r55 = 52
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.AboutMeActivity.AnonymousClass3.<init>(com.Mode.toolbox.AboutMeActivity):void");
        }

        /* renamed from: ۟ۦۡۦۢ, reason: not valid java name and contains not printable characters */
        public static native short[] m7();

        /* renamed from: ۣۤۢ, reason: not valid java name and contains not printable characters */
        public static native AboutMeActivity m8(Object obj);

        @Override // android.widget.PopupMenu.OnMenuItemClickListener
        public native boolean onMenuItemClick(MenuItem menuItem);
    }

    /* renamed from: ۟۟ۦۨ, reason: not valid java name and contains not printable characters */
    public static native short[] m0();

    /* renamed from: ۣۣ۟ۦۣ, reason: not valid java name and contains not printable characters */
    public static native int m1(Object obj);

    /* renamed from: ۢۤ۟ۥ, reason: not valid java name and contains not printable characters */
    public static native SharedPreferences m2(Object obj);

    public native void Back(View view);

    public native void Developer(View view);

    public native void DeviceInfo(View view);

    public native void Ding(View view);

    public native void Ignorebatteryoptimization(View view);

    public native void JoinUs(View view);

    public native void KeBai(View view);

    public native void Mo11Chen(View view);

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    @Override // android.app.Activity
    protected native void onResume();

    public native void reward(View view);

    public native void updateBatteryStatus();
}
