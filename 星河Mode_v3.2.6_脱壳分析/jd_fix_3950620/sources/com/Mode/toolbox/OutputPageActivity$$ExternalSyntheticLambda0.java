package com.Mode.toolbox;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException: Cannot invoke "java.util.List.forEach(java.util.function.Consumer)" because "blocks" is null
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:1029)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:245)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:160)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:65)
    */
/* loaded from: /workspace/xh/fix_3950620.dex */
public final /* synthetic */ class OutputPageActivity$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ OutputPageActivity f$0;
    public final /* synthetic */ String f$1;

    /*  JADX ERROR: Dependency scan failed at insn: 0x0000: SGET r188
        java.lang.IllegalArgumentException: newPosition > limit: (340320 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0000: SGET r188
        java.lang.IllegalArgumentException: newPosition > limit: (340320 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0003: UNKNOWN(0xB4E8)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0003: UNKNOWN(0xB4E8)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0005: UNKNOWN(0x7BE4)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0005: UNKNOWN(0x7BE4)'
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
            // decode failed: newPosition > limit: (340320 > 104176)
            long r6 = r6 + r2
            // decode failed: Unknown instruction: '0x0003: UNKNOWN(0xB4E8)'
            int r12 = r12 + r10
            // decode failed: Unknown instruction: '0x0005: UNKNOWN(0x7BE4)'
            r143 = -1929445376(0xffffffff8cff0000, float:-3.928897E-31)
            r0[r0] = r19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity$$ExternalSyntheticLambda0.<clinit>():void");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0001: IPUT r2, r7
        java.lang.IllegalArgumentException: newPosition > limit: (514272 > 104176)
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x0003: SPUT r134
        java.lang.IllegalArgumentException: newPosition < 0: (-383460576 < 0)
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
    /*  JADX ERROR: Failed to decode insn: 0x0001: IPUT r2, r7
        java.lang.IllegalArgumentException: newPosition > limit: (514272 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0003: SPUT r134
        java.lang.IllegalArgumentException: newPosition < 0: (-383460576 < 0)
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
    /*  JADX ERROR: Failed to decode insn: 0x000C: UNKNOWN(0xBB3F)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000C: UNKNOWN(0xBB3F)'
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
    public /* synthetic */ OutputPageActivity$$ExternalSyntheticLambda0(com.Mode.toolbox.OutputPageActivity r52, java.lang.String r53) {
        /*
            r51 = this;
            float r15 = r15 * r5
            // decode failed: newPosition > limit: (514272 > 104176)
            // decode failed: newPosition < 0: (-383460576 < 0)
            long r1 = r1 >>> r0
            int r8 = (int) r2
            float r3 = (float) r2
            r100[r51] = r157
            short r231 = r164[r117]
            // decode failed: Unknown instruction: '0x000C: UNKNOWN(0xBB3F)'
            long r9 = r9 - r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity$$ExternalSyntheticLambda0.<init>(com.Mode.toolbox.OutputPageActivity, java.lang.String):void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.OutputPageActivity$$ExternalSyntheticLambda0.RQOMYE():void, file: /workspace/xh/fix_3950620.dex
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
    private static /* synthetic */ void RQOMYE() {
        /*
        // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 in method: com.Mode.toolbox.OutputPageActivity$$ExternalSyntheticLambda0.RQOMYE():void, file: /workspace/xh/fix_3950620.dex
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.OutputPageActivity$$ExternalSyntheticLambda0.RQOMYE():void");
    }

    /* renamed from: ۣۨۧۤ, reason: not valid java name and contains not printable characters */
    public static native void m305(Object obj, Object obj2);

    @Override // java.lang.Runnable
    public final native void run();
}
