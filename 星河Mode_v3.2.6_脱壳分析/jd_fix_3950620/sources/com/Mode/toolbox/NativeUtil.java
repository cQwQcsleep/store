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
public class NativeUtil {
    /*  JADX ERROR: Dependency scan failed at insn: 0x0000: INVOKE_SUPER_RANGE r51040, r51041, r51042, r51043, r51044, r51045, r51046, r51047, r51048, r51049, r51050, r51051, r51052, r51053, r51054, r51055, r51056, r51057, r51058, r51059, r51060, r51061, r51062, r51063, r51064, r51065, r51066, r51067, r51068, r51069, r51070, r51071, r51072, r51073, r51074, r51075, r51076, r51077, r51078, r51079, r51080, r51081, r51082, r51083, r51084, r51085, r51086, r51087, r51088, r51089
        java.lang.IllegalArgumentException: newPosition > limit: (454840 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0000: INVOKE_SUPER_RANGE r51040, r51041, r51042, r51043, r51044, r51045, r51046, r51047, r51048, r51049, r51050, r51051, r51052, r51053, r51054, r51055, r51056, r51057, r51058, r51059, r51060, r51061, r51062, r51063, r51064, r51065, r51066, r51067, r51068, r51069, r51070, r51071, r51072, r51073, r51074, r51075, r51076, r51077, r51078, r51079, r51080, r51081, r51082, r51083, r51084, r51085, r51086, r51087, r51088, r51089
        java.lang.IllegalArgumentException: newPosition > limit: (454840 > 104176)
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
    /*  JADX ERROR: Failed to decode insn: 0x0007: UNKNOWN(0x9B41)
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0007: UNKNOWN(0x9B41)'
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
            // decode failed: newPosition > limit: (454840 > 104176)
            r129 = r54 & r152
            r57[r199] = r188
            // decode failed: Unknown instruction: '0x0007: UNKNOWN(0x9B41)'
            r1.<init>()
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.NativeUtil.<clinit>():void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x0000: IF  (r207 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> ?, expected to be less than 1
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:79)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    public NativeUtil() {
        /*
            r0 = this;
            if (r207 == 0) goto LB_595
            long r169 = r44 >>> r161
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.NativeUtil.<init>():void");
    }

    public static native void classesInit0(int i);

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.NativeUtil.jIewehs():void, file: /workspace/xh/fix_3950620.dex
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:168)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        Caused by: java.lang.ArrayIndexOutOfBoundsException
        */
    private static /* synthetic */ void jIewehs() {
        /*
        // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: null in method: com.Mode.toolbox.NativeUtil.jIewehs():void, file: /workspace/xh/fix_3950620.dex
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.NativeUtil.jIewehs():void");
    }
}
