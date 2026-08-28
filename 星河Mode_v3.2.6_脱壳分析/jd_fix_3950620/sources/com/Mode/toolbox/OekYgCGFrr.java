package com.Mode.toolbox;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException: Cannot invoke "java.util.List.forEach(java.util.function.Consumer)" because "blocks" is null
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:1029)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:245)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:160)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:65)
    */
/* loaded from: /workspace/xh/fix_3950620.dex */
public class OekYgCGFrr extends Activity {
    private final Runnable checkRunnable;
    private final Handler handler;
    private boolean isDestroyed;
    private boolean isNightMode;
    private boolean isRequesting;
    private int rootBackgroundColor;

    /*  JADX ERROR: Failed to decode insn: 0x0005: UNKNOWN(0xADE3)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0005: UNKNOWN(0xADE3)'
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
            r137 = -1831193857568993127(0xe6964ae37cb91099, double:-1.5155726839245897E186)
            // decode failed: Unknown instruction: '0x0005: UNKNOWN(0xADE3)'
            if (r14 <= r8) goto LB_5a34
            float r4 = r4 - r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.<clinit>():void");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0004: IPUT r6, r14
        java.lang.IllegalArgumentException: newPosition > limit: (104200 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0006: INVOKE_CUSTOM_RANGE r25299, r25300, r25301, r25302, r25303, r25304, r25305, r25306, r25307, r25308, r25309, r25310, r25311, r25312, r25313, r25314, r25315, r25316, r25317, r25318, r25319, r25320, r25321, r25322, r25323, r25324, r25325, r25326, r25327, r25328, r25329, r25330, r25331, r25332, r25333, r25334, r25335, r25336, r25337, r25338, r25339, r25340, r25341, r25342, r25343, r25344, r25345, r25346, r25347, r25348, r25349, r25350, r25351, r25352, r25353, r25354, r25355, r25356, r25357, r25358, r25359, r25360, r25361, r25362, r25363, r25364, r25365, r25366, r25367, r25368, r25369, r25370, r25371, r25372, r25373, r25374, r25375, r25376, r25377, r25378, r25379, r25380, r25381, r25382, r25383, r25384, r25385, r25386, r25387, r25388, r25389, r25390, r25391, r25392, r25393, r25394, r25395, r25396, r25397, r25398, r25399, r25400, r25401, r25402, r25403, r25404, r25405, r25406, r25407, r25408, r25409, r25410, r25411, r25412, r25413, r25414, r25415, r25416, r25417
        java.lang.IllegalArgumentException: newPosition > limit: (182580 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x000F: IPUT r3, r13
        java.lang.IllegalArgumentException: newPosition > limit: (441080 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0011: IPUT r13, r13
        java.lang.IllegalArgumentException: newPosition > limit: (303528 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0003: UNKNOWN(0xE5F9)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0003: UNKNOWN(0xE5F9)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0004: IPUT r6, r14
        java.lang.IllegalArgumentException: newPosition > limit: (104200 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0006: INVOKE_CUSTOM_RANGE r25299, r25300, r25301, r25302, r25303, r25304, r25305, r25306, r25307, r25308, r25309, r25310, r25311, r25312, r25313, r25314, r25315, r25316, r25317, r25318, r25319, r25320, r25321, r25322, r25323, r25324, r25325, r25326, r25327, r25328, r25329, r25330, r25331, r25332, r25333, r25334, r25335, r25336, r25337, r25338, r25339, r25340, r25341, r25342, r25343, r25344, r25345, r25346, r25347, r25348, r25349, r25350, r25351, r25352, r25353, r25354, r25355, r25356, r25357, r25358, r25359, r25360, r25361, r25362, r25363, r25364, r25365, r25366, r25367, r25368, r25369, r25370, r25371, r25372, r25373, r25374, r25375, r25376, r25377, r25378, r25379, r25380, r25381, r25382, r25383, r25384, r25385, r25386, r25387, r25388, r25389, r25390, r25391, r25392, r25393, r25394, r25395, r25396, r25397, r25398, r25399, r25400, r25401, r25402, r25403, r25404, r25405, r25406, r25407, r25408, r25409, r25410, r25411, r25412, r25413, r25414, r25415, r25416, r25417
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: newPosition > limit: (182580 > 104176)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:597)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:465)
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
        Caused by: java.lang.IllegalArgumentException: newPosition > limit: (182580 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x000F: IPUT r3, r13
        java.lang.IllegalArgumentException: newPosition > limit: (441080 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0011: IPUT r13, r13
        java.lang.IllegalArgumentException: newPosition > limit: (303528 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0013: CONST_STRING r53
        java.lang.IllegalArgumentException: newPosition > limit: (1715726444 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0017: CONST_STRING r28
        java.lang.IllegalArgumentException: newPosition < 0: (-585835000 < 0)
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
    public OekYgCGFrr() {
        /*
            r3 = this;
            goto LB_3ccc1223
            // decode failed: Unknown instruction: '0x0003: UNKNOWN(0xE5F9)'
            // decode failed: newPosition > limit: (104200 > 104176)
            // decode failed: 'invoke-custom' instruction processing error: newPosition > limit: (182580 > 104176)
            int r133 = r37 >>> r22
            int r136 = (r209 > r3 ? 1 : (r209 == r3 ? 0 : -1))
            int r8 = r13 + 12042
            // decode failed: newPosition > limit: (441080 > 104176)
            // decode failed: newPosition > limit: (303528 > 104176)
            // decode failed: newPosition > limit: (1715726444 > 104176)
            r5 = r5 ^ r14
            long r9 = r9 ^ r11
            // decode failed: newPosition < 0: (-585835000 < 0)
            boolean r8 = r13 instanceof char
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.<init>():void");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0000: IPUT r5, r8
        java.lang.IllegalArgumentException: newPosition > limit: (310976 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0008: SGET r137
        java.lang.IllegalArgumentException: newPosition > limit: (169400 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x000F: INVOKE_SUPER_RANGE r52141, r52142, r52143, r52144, r52145, r52146, r52147, r52148, r52149, r52150, r52151, r52152, r52153, r52154, r52155, r52156, r52157
        java.lang.IllegalArgumentException: newPosition > limit: (509328 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x001D: INVOKE_VIRTUAL_RANGE r25709, r25710, r25711, r25712, r25713, r25714, r25715, r25716, r25717, r25718, r25719, r25720, r25721, r25722, r25723, r25724, r25725, r25726, r25727, r25728, r25729, r25730, r25731, r25732, r25733, r25734, r25735, r25736, r25737, r25738, r25739, r25740
        java.lang.IllegalArgumentException: newPosition > limit: (437600 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0025: SPUT r113
        java.lang.IllegalArgumentException: newPosition > limit: (435912 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0027: INVOKE_POLYMORPHIC_RANGE r62478, r62479, r62480, r62481, r62482, r62483, r62484, r62485, r62486, r62487, r62488, r62489, r62490, r62491, r62492, r62493, r62494, r62495, r62496, r62497, r62498, r62499, r62500, r62501, r62502, r62503, r62504, r62505, r62506, r62507, r62508, r62509, r62510, r62511, r62512, r62513, r62514, r62515, r62516, r62517, r62518, r62519, r62520, r62521, r62522, r62523, r62524, r62525, r62526, r62527, r62528, r62529, r62530, r62531, r62532, r62533, r62534, r62535, r62536, r62537, r62538, r62539, r62540, r62541, r62542, r62543, r62544, r62545, r62546, r62547, r62548, r62549, r62550, r62551, r62552, r62553, r62554, r62555, r62556, r62557, r62558, r62559, r62560, r62561, r62562, r62563, r62564, r62565, r62566, r62567, r62568, r62569, r62570, r62571, r62572, r62573, r62574, r62575, r62576, r62577, r62578, r62579, r62580, r62581, r62582, r62583, r62584, r62585, r62586, r62587, r62588, r62589, r62590, r62591, r62592, r62593, r62594, r62595, r62596, r62597, r62598, r62599, r62600, r62601, r62602, r62603, r62604, r62605, r62606, r62607, r62608, r62609, r62610, r62611, r62612, r62613, r62614, r62615, r62616, r62617, r62618, r62619, r62620, r62621, r62622, r62623, r62624, r62625, r62626, r62627, r62628, r62629, r62630, r62631, r62632, r62633, r62634, r62635, r62636, r62637, r62638, r62639, r62640, r62641, r62642, r62643, r62644, r62645, r62646, r62647, r62648, r62649, r62650, r62651, r62652, r62653, r62654, r62655, r62656, r62657, r62658, r62659, r62660, r62661, r62662, r62663, r62664, r62665, r62666, r62667, r62668, r62669, r62670, r62671, r62672, r62673, r62674, r62675, r62676, r62677, r62678, r62679, r62680, r62681, r62682, r62683, r62684, r62685, r62686, r62687, r62688, r62689, r62690, r62691, r62692, r62693, r62694, r62695, r62696, r62697, r62698, r62699, r62700, r62701, r62702, r62703, r62704, r62705, r62706
        java.lang.IllegalArgumentException: newPosition > limit: (111360 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x002B: INVOKE_POLYMORPHIC_RANGE r37401, r37402, r37403, r37404, r37405, r37406, r37407, r37408, r37409, r37410, r37411, r37412, r37413, r37414, r37415, r37416, r37417, r37418, r37419, r37420, r37421, r37422, r37423, r37424, r37425, r37426, r37427, r37428, r37429, r37430, r37431, r37432, r37433
        java.lang.IllegalArgumentException: newPosition > limit: (536364 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0036: INVOKE_DIRECT r12, r6, r7, r12, r2, r37406, r37407, r37408, r37409, r37410
        java.lang.IllegalArgumentException: newPosition > limit: (330784 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x003B: CONST_CLASS r245
        java.lang.IllegalArgumentException: newPosition > limit: (223568 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0045: INVOKE_VIRTUAL_RANGE r18813, r18814, r18815, r18816, r18817, r18818, r18819, r18820, r18821, r18822, r18823, r18824, r18825, r18826, r18827, r18828, r18829, r18830, r18831, r18832, r18833, r18834, r18835, r18836, r18837, r18838, r18839, r18840, r18841, r18842, r18843, r18844, r18845, r18846, r18847, r18848, r18849, r18850, r18851, r18852, r18853, r18854, r18855, r18856, r18857, r18858, r18859, r18860, r18861, r18862, r18863, r18864, r18865, r18866, r18867, r18868, r18869, r18870, r18871, r18872, r18873, r18874, r18875, r18876, r18877, r18878, r18879, r18880, r18881, r18882, r18883, r18884, r18885, r18886, r18887, r18888, r18889, r18890, r18891, r18892, r18893, r18894, r18895, r18896, r18897, r18898, r18899, r18900, r18901, r18902, r18903, r18904, r18905, r18906, r18907, r18908, r18909, r18910, r18911, r18912, r18913, r18914, r18915, r18916, r18917, r18918, r18919, r18920, r18921, r18922, r18923, r18924, r18925, r18926, r18927, r18928, r18929, r18930, r18931, r18932, r18933, r18934, r18935, r18936, r18937, r18938, r18939, r18940, r18941, r18942, r18943, r18944, r18945, r18946, r18947, r18948, r18949, r18950, r18951, r18952, r18953, r18954, r18955, r18956, r18957, r18958, r18959, r18960, r18961, r18962, r18963, r18964, r18965, r18966, r18967, r18968, r18969, r18970, r18971, r18972, r18973, r18974, r18975, r18976, r18977, r18978, r18979, r18980, r18981, r18982, r18983, r18984, r18985, r18986, r18987, r18988, r18989
        java.lang.IllegalArgumentException: newPosition > limit: (300336 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0000: IPUT r5, r8
        java.lang.IllegalArgumentException: newPosition > limit: (310976 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0008: SGET r137
        java.lang.IllegalArgumentException: newPosition > limit: (169400 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x000F: INVOKE_SUPER_RANGE r52141, r52142, r52143, r52144, r52145, r52146, r52147, r52148, r52149, r52150, r52151, r52152, r52153, r52154, r52155, r52156, r52157
        java.lang.IllegalArgumentException: newPosition > limit: (509328 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0015: UNKNOWN(0x51E7)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0015: UNKNOWN(0x51E7)'
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
    /*  JADX ERROR: Failed to decode insn: 0x001D: INVOKE_VIRTUAL_RANGE r25709, r25710, r25711, r25712, r25713, r25714, r25715, r25716, r25717, r25718, r25719, r25720, r25721, r25722, r25723, r25724, r25725, r25726, r25727, r25728, r25729, r25730, r25731, r25732, r25733, r25734, r25735, r25736, r25737, r25738, r25739, r25740
        java.lang.IllegalArgumentException: newPosition > limit: (437600 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0025: SPUT r113
        java.lang.IllegalArgumentException: newPosition > limit: (435912 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0027: INVOKE_POLYMORPHIC_RANGE r62478, r62479, r62480, r62481, r62482, r62483, r62484, r62485, r62486, r62487, r62488, r62489, r62490, r62491, r62492, r62493, r62494, r62495, r62496, r62497, r62498, r62499, r62500, r62501, r62502, r62503, r62504, r62505, r62506, r62507, r62508, r62509, r62510, r62511, r62512, r62513, r62514, r62515, r62516, r62517, r62518, r62519, r62520, r62521, r62522, r62523, r62524, r62525, r62526, r62527, r62528, r62529, r62530, r62531, r62532, r62533, r62534, r62535, r62536, r62537, r62538, r62539, r62540, r62541, r62542, r62543, r62544, r62545, r62546, r62547, r62548, r62549, r62550, r62551, r62552, r62553, r62554, r62555, r62556, r62557, r62558, r62559, r62560, r62561, r62562, r62563, r62564, r62565, r62566, r62567, r62568, r62569, r62570, r62571, r62572, r62573, r62574, r62575, r62576, r62577, r62578, r62579, r62580, r62581, r62582, r62583, r62584, r62585, r62586, r62587, r62588, r62589, r62590, r62591, r62592, r62593, r62594, r62595, r62596, r62597, r62598, r62599, r62600, r62601, r62602, r62603, r62604, r62605, r62606, r62607, r62608, r62609, r62610, r62611, r62612, r62613, r62614, r62615, r62616, r62617, r62618, r62619, r62620, r62621, r62622, r62623, r62624, r62625, r62626, r62627, r62628, r62629, r62630, r62631, r62632, r62633, r62634, r62635, r62636, r62637, r62638, r62639, r62640, r62641, r62642, r62643, r62644, r62645, r62646, r62647, r62648, r62649, r62650, r62651, r62652, r62653, r62654, r62655, r62656, r62657, r62658, r62659, r62660, r62661, r62662, r62663, r62664, r62665, r62666, r62667, r62668, r62669, r62670, r62671, r62672, r62673, r62674, r62675, r62676, r62677, r62678, r62679, r62680, r62681, r62682, r62683, r62684, r62685, r62686, r62687, r62688, r62689, r62690, r62691, r62692, r62693, r62694, r62695, r62696, r62697, r62698, r62699, r62700, r62701, r62702, r62703, r62704, r62705, r62706
        java.lang.IllegalArgumentException: newPosition > limit: (111360 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x002B: INVOKE_POLYMORPHIC_RANGE r37401, r37402, r37403, r37404, r37405, r37406, r37407, r37408, r37409, r37410, r37411, r37412, r37413, r37414, r37415, r37416, r37417, r37418, r37419, r37420, r37421, r37422, r37423, r37424, r37425, r37426, r37427, r37428, r37429, r37430, r37431, r37432, r37433
        java.lang.IllegalArgumentException: newPosition > limit: (536364 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0033: CONST_STRING r207
        java.lang.IllegalArgumentException: newPosition < 0: (-1877397896 < 0)
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
    /*  JADX ERROR: Failed to decode insn: 0x0036: INVOKE_DIRECT r12, r6, r7, r12, r2, r37406, r37407, r37408, r37409, r37410
        java.lang.IllegalArgumentException: newPosition > limit: (330784 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0039: UNKNOWN(0x98F5)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0039: UNKNOWN(0x98F5)'
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
    /*  JADX ERROR: Failed to decode insn: 0x003B: CONST_CLASS r245
        java.lang.IllegalArgumentException: newPosition > limit: (223568 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0045: INVOKE_VIRTUAL_RANGE r18813, r18814, r18815, r18816, r18817, r18818, r18819, r18820, r18821, r18822, r18823, r18824, r18825, r18826, r18827, r18828, r18829, r18830, r18831, r18832, r18833, r18834, r18835, r18836, r18837, r18838, r18839, r18840, r18841, r18842, r18843, r18844, r18845, r18846, r18847, r18848, r18849, r18850, r18851, r18852, r18853, r18854, r18855, r18856, r18857, r18858, r18859, r18860, r18861, r18862, r18863, r18864, r18865, r18866, r18867, r18868, r18869, r18870, r18871, r18872, r18873, r18874, r18875, r18876, r18877, r18878, r18879, r18880, r18881, r18882, r18883, r18884, r18885, r18886, r18887, r18888, r18889, r18890, r18891, r18892, r18893, r18894, r18895, r18896, r18897, r18898, r18899, r18900, r18901, r18902, r18903, r18904, r18905, r18906, r18907, r18908, r18909, r18910, r18911, r18912, r18913, r18914, r18915, r18916, r18917, r18918, r18919, r18920, r18921, r18922, r18923, r18924, r18925, r18926, r18927, r18928, r18929, r18930, r18931, r18932, r18933, r18934, r18935, r18936, r18937, r18938, r18939, r18940, r18941, r18942, r18943, r18944, r18945, r18946, r18947, r18948, r18949, r18950, r18951, r18952, r18953, r18954, r18955, r18956, r18957, r18958, r18959, r18960, r18961, r18962, r18963, r18964, r18965, r18966, r18967, r18968, r18969, r18970, r18971, r18972, r18973, r18974, r18975, r18976, r18977, r18978, r18979, r18980, r18981, r18982, r18983, r18984, r18985, r18986, r18987, r18988, r18989
        java.lang.IllegalArgumentException: newPosition > limit: (300336 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    private static /* synthetic */ void Idqe() {
        /*
            // decode failed: newPosition > limit: (310976 > 104176)
            int r19 = r156 / (-70)
            r14 = r10 | (-27113(0xffffffffffff9617, float:NaN))
            float r147 = r192 / r46
            // decode failed: newPosition > limit: (169400 > 104176)
            byte r6 = (byte) r10
            int r47 = (r143 > r119 ? 1 : (r143 == r119 ? 0 : -1))
            r67[r184] = r183
            // decode failed: newPosition > limit: (509328 > 104176)
            r228 = r245 & r224
            int r8 = ~r2
            // decode failed: Unknown instruction: '0x0015: UNKNOWN(0x51E7)'
            int r253 = 112 - r239
            long r13 = (long) r11
            float r30 = r159 * r125
            int r10 = r14 * 21219
            // decode failed: newPosition > limit: (437600 > 104176)
            long r14 = r14 * r14
            long r2 = (long) r9
            long r11 = r11 << r4
            float r93 = r162 % r244
            // decode failed: newPosition > limit: (435912 > 104176)
            // decode failed: newPosition > limit: (111360 > 104176)
            // decode failed: newPosition > limit: (536364 > 104176)
            r116 = r7 ^ r154
            int r2 = r9 * (-4572)
            // decode failed: newPosition < 0: (-1877397896 < 0)
            // decode failed: newPosition > limit: (330784 > 104176)
            // decode failed: Unknown instruction: '0x0039: UNKNOWN(0x98F5)'
            long r11 = (long) r1
            // decode failed: newPosition > limit: (223568 > 104176)
            int r41 = r188 - r125
            long r32 = r196 % r125
            float r158 = r214 + r159
            if (r20 <= 0) goto LB_7358
            // decode failed: newPosition > limit: (300336 > 104176)
            double r155 = r150 + r196
            int r183 = (r0 > r0 ? 1 : (r0 == r0 ? 0 : -1))
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.Idqe():void");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x000F: SGET r91
        java.lang.IllegalArgumentException: newPosition > limit: (391104 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0024: INVOKE_POLYMORPHIC_RANGE r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140
        java.lang.IllegalArgumentException: newPosition > limit: (570684 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x002D: INVOKE_DIRECT r6, r7, r12, r7, r3, r72, r73, r74, r75
        java.lang.IllegalArgumentException: newPosition > limit: (134792 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x000B: UNKNOWN(0xD3F4)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000B: UNKNOWN(0xD3F4)'
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
    /*  JADX ERROR: Failed to decode insn: 0x000C: UNKNOWN(0x4342)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000C: UNKNOWN(0x4342)'
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
    /*  JADX ERROR: Failed to decode insn: 0x000F: SGET r91
        java.lang.IllegalArgumentException: newPosition > limit: (391104 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0021: UNKNOWN(0x5341)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0021: UNKNOWN(0x5341)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0024: INVOKE_POLYMORPHIC_RANGE r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140
        java.lang.IllegalArgumentException: newPosition > limit: (570684 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x002B: CONST_STRING r2
        java.lang.IllegalArgumentException: newPosition > limit: (242692 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x002D: INVOKE_DIRECT r6, r7, r12, r7, r3, r72, r73, r74, r75
        java.lang.IllegalArgumentException: newPosition > limit: (134792 > 104176)
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
    public static /* synthetic */ int access$000(com.Mode.toolbox.OekYgCGFrr r4, int r5) {
        /*
            r60866 = r10854
            r235[r164] = r139
            double r2 = r2 * r15
            r13620 = r57395
            int r246 = r30 >> 46
            // decode failed: Unknown instruction: '0x000B: UNKNOWN(0xD3F4)'
            // decode failed: Unknown instruction: '0x000C: UNKNOWN(0x4342)'
            r16 = r46 & (-19)
            // decode failed: newPosition > limit: (391104 > 104176)
            long r9 = r9 >>> r8
            r139[r192] = r69
            char r147 = r169[r89]
            float r209 = r43 * r182
            if (r2 <= r15) goto LB_6c79
            if (r14 == r8) goto L38c1
            r2 = 25229(0x628d, float:3.5353E-41)
            goto LB_1b51
            long r4 = r4 << r15
            // decode failed: Unknown instruction: '0x0021: UNKNOWN(0x5341)'
            if (r9 > 0) goto LB_1547
            // decode failed: newPosition > limit: (570684 > 104176)
            r12 = r225 ^ r182
            long r8 = r8 >>> r15
            // decode failed: newPosition > limit: (242692 > 104176)
            // decode failed: newPosition > limit: (134792 > 104176)
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.access$000(com.Mode.toolbox.OekYgCGFrr, int):int");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.OekYgCGFrr.access$100(com.Mode.toolbox.OekYgCGFrr):boolean, file: /workspace/xh/fix_3950620.dex
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        Caused by: java.lang.ArrayIndexOutOfBoundsException
        */
    public static /* synthetic */ boolean access$100(com.Mode.toolbox.OekYgCGFrr r0) {
        /*
        // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.OekYgCGFrr.access$100(com.Mode.toolbox.OekYgCGFrr):boolean, file: /workspace/xh/fix_3950620.dex
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.access$100(com.Mode.toolbox.OekYgCGFrr):boolean");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0005: SPUT r23
        java.lang.IllegalArgumentException: newPosition > limit: (470280 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x000D: INVOKE_POLYMORPHIC_RANGE r64503, r64504, r64505, r64506, r64507, r64508, r64509, r64510, r64511, r64512, r64513, r64514, r64515, r64516, r64517, r64518, r64519, r64520, r64521, r64522, r64523, r64524, r64525, r64526, r64527, r64528, r64529, r64530, r64531, r64532, r64533, r64534, r64535, r64536, r64537, r64538, r64539, r64540, r64541, r64542, r64543, r64544, r64545, r64546, r64547, r64548, r64549, r64550, r64551, r64552, r64553, r64554, r64555, r64556, r64557, r64558, r64559, r64560, r64561, r64562, r64563, r64564, r64565, r64566, r64567, r64568, r64569, r64570, r64571, r64572, r64573, r64574, r64575, r64576, r64577, r64578, r64579, r64580, r64581, r64582, r64583, r64584, r64585, r64586, r64587
        java.lang.IllegalArgumentException: newPosition > limit: (330960 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0005: SPUT r23
        java.lang.IllegalArgumentException: newPosition > limit: (470280 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x000D: INVOKE_POLYMORPHIC_RANGE r64503, r64504, r64505, r64506, r64507, r64508, r64509, r64510, r64511, r64512, r64513, r64514, r64515, r64516, r64517, r64518, r64519, r64520, r64521, r64522, r64523, r64524, r64525, r64526, r64527, r64528, r64529, r64530, r64531, r64532, r64533, r64534, r64535, r64536, r64537, r64538, r64539, r64540, r64541, r64542, r64543, r64544, r64545, r64546, r64547, r64548, r64549, r64550, r64551, r64552, r64553, r64554, r64555, r64556, r64557, r64558, r64559, r64560, r64561, r64562, r64563, r64564, r64565, r64566, r64567, r64568, r64569, r64570, r64571, r64572, r64573, r64574, r64575, r64576, r64577, r64578, r64579, r64580, r64581, r64582, r64583, r64584, r64585, r64586, r64587
        java.lang.IllegalArgumentException: newPosition > limit: (330960 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0025: UNKNOWN(0x6873)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0025: UNKNOWN(0x6873)'
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
    public static /* synthetic */ boolean access$200(com.Mode.toolbox.OekYgCGFrr r4) {
        /*
            long r1 = r1 << r4
            double r10 = (double) r10
            float r4 = -r5
            int r11 = r189 / r163
            // decode failed: newPosition > limit: (470280 > 104176)
            int r14 = r9.length
            long r1 = r1 << r2
            short r2 = (short) r10
            long r79 = r119 >> r67
            short r12 = (short) r11
            // decode failed: newPosition > limit: (330960 > 104176)
            float r6 = (float) r8
            r73 = move-exception
            int r5 = r5 % r15
            r23[r170] = r136
            r161 = r40948
            int r3 = r1 % 6050
            float r8 = -r13
            r22 = move-result
            r2 = r126 | 11
            r152 = r72 & 73
            int r11 = r12 * (-30440)
            goto LB_4b
            double r142 = r89 * r246
            // decode failed: Unknown instruction: '0x0025: UNKNOWN(0x6873)'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.access$200(com.Mode.toolbox.OekYgCGFrr):boolean");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x000A: INVOKE_STATIC_RANGE r2033, r2034, r2035, r2036, r2037, r2038, r2039, r2040, r2041, r2042, r2043, r2044, r2045, r2046, r2047, r2048, r2049, r2050, r2051, r2052, r2053, r2054, r2055, r2056, r2057, r2058, r2059, r2060, r2061, r2062, r2063, r2064, r2065, r2066, r2067, r2068, r2069, r2070, r2071, r2072, r2073, r2074, r2075, r2076, r2077, r2078, r2079, r2080, r2081, r2082, r2083, r2084, r2085, r2086, r2087, r2088, r2089, r2090, r2091, r2092, r2093, r2094, r2095, r2096, r2097, r2098, r2099, r2100, r2101, r2102, r2103, r2104, r2105, r2106, r2107, r2108, r2109, r2110, r2111, r2112, r2113, r2114, r2115, r2116, r2117, r2118, r2119, r2120, r2121, r2122, r2123, r2124, r2125, r2126, r2127, r2128, r2129, r2130, r2131, r2132, r2133, r2134, r2135, r2136, r2137, r2138, r2139, r2140, r2141, r2142, r2143, r2144, r2145, r2146, r2147, r2148, r2149, r2150, r2151, r2152, r2153, r2154, r2155, r2156, r2157, r2158, r2159, r2160, r2161, r2162, r2163, r2164, r2165, r2166, r2167, r2168, r2169, r2170, r2171, r2172, r2173, r2174, r2175, r2176, r2177, r2178, r2179, r2180, r2181, r2182, r2183, r2184, r2185, r2186, r2187, r2188, r2189, r2190, r2191, r2192, r2193, r2194, r2195, r2196, r2197, r2198, r2199, r2200, r2201, r2202, r2203, r2204, r2205, r2206, r2207, r2208, r2209, r2210, r2211, r2212
        java.lang.IllegalArgumentException: newPosition > limit: (188448 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x000F: INVOKE_POLYMORPHIC r11, r1, r2, r10, r1, r2038, r2039, r2040, r2041
        java.lang.IllegalArgumentException: newPosition > limit: (346256 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0018: SPUT r122
        java.lang.IllegalArgumentException: newPosition > limit: (395464 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x001B: INVOKE_DIRECT r14, r13, r0, r1
        java.lang.IllegalArgumentException: newPosition > limit: (704136 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0020: INVOKE_CUSTOM r4, r8, r1, r3, r1, r2038, r2039, r2040, r2041, r2042, r2043, r2044
        java.lang.IllegalArgumentException: newPosition > limit: (186244 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x000A: INVOKE_STATIC_RANGE r2033, r2034, r2035, r2036, r2037, r2038, r2039, r2040, r2041, r2042, r2043, r2044, r2045, r2046, r2047, r2048, r2049, r2050, r2051, r2052, r2053, r2054, r2055, r2056, r2057, r2058, r2059, r2060, r2061, r2062, r2063, r2064, r2065, r2066, r2067, r2068, r2069, r2070, r2071, r2072, r2073, r2074, r2075, r2076, r2077, r2078, r2079, r2080, r2081, r2082, r2083, r2084, r2085, r2086, r2087, r2088, r2089, r2090, r2091, r2092, r2093, r2094, r2095, r2096, r2097, r2098, r2099, r2100, r2101, r2102, r2103, r2104, r2105, r2106, r2107, r2108, r2109, r2110, r2111, r2112, r2113, r2114, r2115, r2116, r2117, r2118, r2119, r2120, r2121, r2122, r2123, r2124, r2125, r2126, r2127, r2128, r2129, r2130, r2131, r2132, r2133, r2134, r2135, r2136, r2137, r2138, r2139, r2140, r2141, r2142, r2143, r2144, r2145, r2146, r2147, r2148, r2149, r2150, r2151, r2152, r2153, r2154, r2155, r2156, r2157, r2158, r2159, r2160, r2161, r2162, r2163, r2164, r2165, r2166, r2167, r2168, r2169, r2170, r2171, r2172, r2173, r2174, r2175, r2176, r2177, r2178, r2179, r2180, r2181, r2182, r2183, r2184, r2185, r2186, r2187, r2188, r2189, r2190, r2191, r2192, r2193, r2194, r2195, r2196, r2197, r2198, r2199, r2200, r2201, r2202, r2203, r2204, r2205, r2206, r2207, r2208, r2209, r2210, r2211, r2212
        java.lang.IllegalArgumentException: newPosition > limit: (188448 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x000F: INVOKE_POLYMORPHIC r11, r1, r2, r10, r1, r2038, r2039, r2040, r2041
        java.lang.IllegalArgumentException: newPosition > limit: (346256 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0016: UNKNOWN(0x7773)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0016: UNKNOWN(0x7773)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0017: UNKNOWN(0xDAEA)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0017: UNKNOWN(0xDAEA)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0018: SPUT r122
        java.lang.IllegalArgumentException: newPosition > limit: (395464 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x001B: INVOKE_DIRECT r14, r13, r0, r1
        java.lang.IllegalArgumentException: newPosition > limit: (704136 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x001E: UNKNOWN(0xC4E3)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001E: UNKNOWN(0xC4E3)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0020: INVOKE_CUSTOM r4, r8, r1, r3, r1, r2038, r2039, r2040, r2041, r2042, r2043, r2044
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: newPosition > limit: (186244 > 104176)
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
        Caused by: java.lang.IllegalArgumentException: newPosition > limit: (186244 > 104176)
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
    public static /* synthetic */ boolean access$300(com.Mode.toolbox.OekYgCGFrr r4) {
        /*
            r192 = move-exception
            int r231 = r56 - r202
            r15 = r15 ^ r10
            long r8 = r154 - r40
            int r2 = r2 >>> r7
            switch(r93) {
            // error: 0x0007: SWITCH (r93 I:??)no payload
            // decode failed: newPosition > limit: (188448 > 104176)
            long r152 = r104 ^ r131
            // decode failed: newPosition > limit: (346256 > 104176)
            r14.m249(r0)
            // decode failed: Unknown instruction: '0x0016: UNKNOWN(0x7773)'
            // decode failed: Unknown instruction: '0x0017: UNKNOWN(0xDAEA)'
            // decode failed: newPosition > limit: (395464 > 104176)
            long r5 = r5 + r7
            // decode failed: newPosition > limit: (704136 > 104176)
            // decode failed: Unknown instruction: '0x001E: UNKNOWN(0xC4E3)'
            double r9 = (double) r6
            // decode failed: 'invoke-custom' instruction processing error: newPosition > limit: (186244 > 104176)
            long r122 = r144 << r17
            r13 = -2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.access$300(com.Mode.toolbox.OekYgCGFrr):boolean");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0009: NEW_INSTANCE r102
        java.lang.IllegalArgumentException: newPosition < 0: (-522546752 < 0)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0013: SGET r36
        java.lang.IllegalArgumentException: newPosition > limit: (411680 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x001E: INVOKE_STATIC_RANGE r14690, r14691, r14692, r14693, r14694, r14695, r14696, r14697, r14698, r14699, r14700, r14701, r14702, r14703, r14704, r14705, r14706, r14707, r14708, r14709, r14710, r14711, r14712, r14713, r14714, r14715, r14716, r14717, r14718, r14719, r14720, r14721, r14722, r14723, r14724, r14725, r14726, r14727, r14728, r14729, r14730, r14731, r14732, r14733, r14734, r14735, r14736, r14737, r14738, r14739, r14740, r14741, r14742, r14743, r14744, r14745, r14746, r14747, r14748, r14749, r14750, r14751, r14752, r14753, r14754, r14755, r14756, r14757, r14758, r14759, r14760, r14761, r14762, r14763, r14764, r14765, r14766, r14767, r14768, r14769, r14770, r14771, r14772, r14773, r14774, r14775, r14776, r14777, r14778, r14779, r14780, r14781, r14782, r14783, r14784, r14785, r14786, r14787, r14788, r14789, r14790, r14791, r14792, r14793, r14794, r14795, r14796, r14797, r14798, r14799, r14800, r14801, r14802, r14803, r14804, r14805, r14806, r14807, r14808, r14809, r14810, r14811, r14812, r14813, r14814, r14815, r14816, r14817, r14818, r14819, r14820, r14821, r14822, r14823, r14824, r14825, r14826, r14827, r14828, r14829, r14830, r14831, r14832, r14833, r14834, r14835, r14836, r14837, r14838, r14839, r14840, r14841, r14842, r14843, r14844, r14845, r14846, r14847, r14848, r14849, r14850, r14851, r14852, r14853, r14854, r14855, r14856, r14857, r14858, r14859, r14860, r14861, r14862, r14863, r14864, r14865, r14866, r14867, r14868, r14869, r14870, r14871, r14872, r14873, r14874, r14875, r14876, r14877, r14878, r14879, r14880, r14881, r14882, r14883, r14884, r14885, r14886, r14887, r14888, r14889, r14890, r14891, r14892, r14893, r14894, r14895, r14896, r14897, r14898, r14899, r14900, r14901, r14902, r14903, r14904, r14905, r14906, r14907, r14908, r14909, r14910, r14911, r14912, r14913, r14914, r14915, r14916, r14917, r14918, r14919, r14920, r14921, r14922, r14923, r14924, r14925, r14926, r14927, r14928, r14929, r14930, r14931, r14932
        java.lang.IllegalArgumentException: newPosition > limit: (195496 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0023: CONST_CLASS r156
        java.lang.IllegalArgumentException: newPosition > limit: (166108 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0027: NEW_ARRAY r15, r7
        java.lang.IllegalArgumentException: newPosition > limit: (251576 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x783F)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x783F)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0009: NEW_INSTANCE r102
        java.lang.IllegalArgumentException: newPosition < 0: (-522546752 < 0)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x000D: UNKNOWN(0x41E8)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000D: UNKNOWN(0x41E8)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0013: SGET r36
        java.lang.IllegalArgumentException: newPosition > limit: (411680 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0015: UNKNOWN(0xA2ED)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0015: UNKNOWN(0xA2ED)'
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
    /*  JADX ERROR: Failed to decode insn: 0x001B: CONST_METHOD_HANDLE r134
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001B: CONST_METHOD_HANDLE r134'
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
    /*  JADX ERROR: Failed to decode insn: 0x001E: INVOKE_STATIC_RANGE r14690, r14691, r14692, r14693, r14694, r14695, r14696, r14697, r14698, r14699, r14700, r14701, r14702, r14703, r14704, r14705, r14706, r14707, r14708, r14709, r14710, r14711, r14712, r14713, r14714, r14715, r14716, r14717, r14718, r14719, r14720, r14721, r14722, r14723, r14724, r14725, r14726, r14727, r14728, r14729, r14730, r14731, r14732, r14733, r14734, r14735, r14736, r14737, r14738, r14739, r14740, r14741, r14742, r14743, r14744, r14745, r14746, r14747, r14748, r14749, r14750, r14751, r14752, r14753, r14754, r14755, r14756, r14757, r14758, r14759, r14760, r14761, r14762, r14763, r14764, r14765, r14766, r14767, r14768, r14769, r14770, r14771, r14772, r14773, r14774, r14775, r14776, r14777, r14778, r14779, r14780, r14781, r14782, r14783, r14784, r14785, r14786, r14787, r14788, r14789, r14790, r14791, r14792, r14793, r14794, r14795, r14796, r14797, r14798, r14799, r14800, r14801, r14802, r14803, r14804, r14805, r14806, r14807, r14808, r14809, r14810, r14811, r14812, r14813, r14814, r14815, r14816, r14817, r14818, r14819, r14820, r14821, r14822, r14823, r14824, r14825, r14826, r14827, r14828, r14829, r14830, r14831, r14832, r14833, r14834, r14835, r14836, r14837, r14838, r14839, r14840, r14841, r14842, r14843, r14844, r14845, r14846, r14847, r14848, r14849, r14850, r14851, r14852, r14853, r14854, r14855, r14856, r14857, r14858, r14859, r14860, r14861, r14862, r14863, r14864, r14865, r14866, r14867, r14868, r14869, r14870, r14871, r14872, r14873, r14874, r14875, r14876, r14877, r14878, r14879, r14880, r14881, r14882, r14883, r14884, r14885, r14886, r14887, r14888, r14889, r14890, r14891, r14892, r14893, r14894, r14895, r14896, r14897, r14898, r14899, r14900, r14901, r14902, r14903, r14904, r14905, r14906, r14907, r14908, r14909, r14910, r14911, r14912, r14913, r14914, r14915, r14916, r14917, r14918, r14919, r14920, r14921, r14922, r14923, r14924, r14925, r14926, r14927, r14928, r14929, r14930, r14931, r14932
        java.lang.IllegalArgumentException: newPosition > limit: (195496 > 104176)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0022: UNKNOWN(0x56F9)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0022: UNKNOWN(0x56F9)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0023: CONST_CLASS r156
        java.lang.IllegalArgumentException: newPosition > limit: (166108 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0027: NEW_ARRAY r15, r7
        java.lang.IllegalArgumentException: newPosition > limit: (251576 > 104176)
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
    public static /* synthetic */ boolean access$302(com.Mode.toolbox.OekYgCGFrr r4, boolean r5) {
        /*
            // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x783F)'
            switch(r178) {
            // error: 0x0001: SWITCH (r178 I:??)no payload
            if (r7 < r11) goto LB_3952
            long r15 = r15 % r7
            double r80 = r13 + r64
            // decode failed: newPosition < 0: (-522546752 < 0)
            double r202 = r217 * r113
            // decode failed: Unknown instruction: '0x000D: UNKNOWN(0x41E8)'
            r171 = move-result
            int r135 = r53 * r223
            int r52 = r15 >>> (-47)
            // decode failed: newPosition > limit: (411680 > 104176)
            // decode failed: Unknown instruction: '0x0015: UNKNOWN(0xA2ED)'
            int r134 = (r234 > r213 ? 1 : (r234 == r213 ? 0 : -1))
            r13 = r11
            int r9 = r2 + (-6499)
            // decode failed: Unknown instruction: '0x001B: CONST_METHOD_HANDLE r134'
            goto L54
            // decode failed: newPosition > limit: (195496 > 104176)
            r144 = move-exception
            // decode failed: Unknown instruction: '0x0022: UNKNOWN(0x56F9)'
            // decode failed: newPosition > limit: (166108 > 104176)
            double r132 = r178 / r84
            // decode failed: newPosition > limit: (251576 > 104176)
            int r245 = r58 * r95
            int r15 = r13 + 3802
            r235[r17] = r139
            float r7 = r7 % r5
            int r1 = r1 >> r5
            r112 = -25209(0xffffffffffff9d87, double:NaN)
            com.Mode.toolbox.AboutMeActivity r2 = r3.this$0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.access$302(com.Mode.toolbox.OekYgCGFrr, boolean):boolean");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x000B: IPUT r14, r4
        java.lang.IllegalArgumentException: newPosition > limit: (338400 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0022: IPUT r7, r3
        java.lang.IllegalArgumentException: newPosition > limit: (345360 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x000B: IPUT r14, r4
        java.lang.IllegalArgumentException: newPosition > limit: (338400 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x000F: CONST_STRING r129
        java.lang.IllegalArgumentException: newPosition > limit: (1492988124 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0016: UNKNOWN(0x66F6)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0016: UNKNOWN(0x66F6)'
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
    /*  JADX ERROR: Failed to decode insn: 0x001B: UNKNOWN(0x3E41)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001B: UNKNOWN(0x3E41)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0022: IPUT r7, r3
        java.lang.IllegalArgumentException: newPosition > limit: (345360 > 104176)
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
    public static /* synthetic */ android.os.Handler access$400(com.Mode.toolbox.OekYgCGFrr r4) {
        /*
            long r5 = r5 % r2
            long r13 = r13 - r4
            int r255 = (-40) - r167
            long r210 = r67 | r113
            if (r161 > 0) goto L7ab1
            r3 = r3 | r4
            r229 = -19004(0xffffffffffffb5c4, double:NaN)
            // decode failed: newPosition > limit: (338400 > 104176)
            r9 = r6 | 3443(0xd73, float:4.825E-42)
            // decode failed: newPosition > limit: (1492988124 > 104176)
            int r117 = r90 - r151
            r44 = r10[r135]
            // decode failed: Unknown instruction: '0x0016: UNKNOWN(0x66F6)'
            if (r15 > r7) goto L6ee9
            float r102 = r198 / r117
            // decode failed: Unknown instruction: '0x001B: UNKNOWN(0x3E41)'
            r28 = r177 ^ 34
            int r14 = r1 + 20995
            int r172 = r69 * 70
            // decode failed: newPosition > limit: (345360 > 104176)
            r23819 = r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.access$400(com.Mode.toolbox.OekYgCGFrr):android.os.Handler");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.OekYgCGFrr.access$500(com.Mode.toolbox.OekYgCGFrr):boolean, file: /workspace/xh/fix_3950620.dex
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        Caused by: java.lang.ArrayIndexOutOfBoundsException
        */
    public static /* synthetic */ boolean access$500(com.Mode.toolbox.OekYgCGFrr r0) {
        /*
        // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.OekYgCGFrr.access$500(com.Mode.toolbox.OekYgCGFrr):boolean, file: /workspace/xh/fix_3950620.dex
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.access$500(com.Mode.toolbox.OekYgCGFrr):boolean");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.OekYgCGFrr.access$600(com.Mode.toolbox.OekYgCGFrr):java.lang.Runnable, file: /workspace/xh/fix_3950620.dex
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        Caused by: java.lang.ArrayIndexOutOfBoundsException
        */
    public static /* synthetic */ java.lang.Runnable access$600(com.Mode.toolbox.OekYgCGFrr r0) {
        /*
        // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.OekYgCGFrr.access$600(com.Mode.toolbox.OekYgCGFrr):java.lang.Runnable, file: /workspace/xh/fix_3950620.dex
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.access$600(com.Mode.toolbox.OekYgCGFrr):java.lang.Runnable");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.OekYgCGFrr.access$700(com.Mode.toolbox.OekYgCGFrr):void, file: /workspace/xh/fix_3950620.dex
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        Caused by: java.lang.ArrayIndexOutOfBoundsException
        */
    public static /* synthetic */ void access$700(com.Mode.toolbox.OekYgCGFrr r0) {
        /*
        // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.OekYgCGFrr.access$700(com.Mode.toolbox.OekYgCGFrr):void, file: /workspace/xh/fix_3950620.dex
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OekYgCGFrr.access$700(com.Mode.toolbox.OekYgCGFrr):void");
    }

    private native void clearAllPopupIdCache();

    private native int dp(int i);

    private native String getAppVersion();

    private native Drawable getApplicationIcon();

    private native boolean hasSelfType2Window();

    private native boolean isAllNeededPopupsClosed();

    private native void launchNextActivity();

    private native void resolveSystemThemeColor();

    private native void setupImmersiveWindow();

    private native void startConfigCheckLoop();

    @Override // android.app.Activity
    public native void onCreate(Bundle bundle);

    @Override // android.app.Activity
    public native void onDestroy();
}
