package com.Mode.toolbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/* loaded from: /workspace/xh/fix_3950620.dex */
public class BackupActivity extends Activity {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f12short = null;
    private TextView backupNoText;
    private TextView backupSizeText;

    /* renamed from: com.Mode.toolbox.BackupActivity$2, reason: invalid class name */
    class AnonymousClass2 implements DialogInterface.OnClickListener {

        /* renamed from: short, reason: not valid java name */
        private static final short[] f17short = null;
        final /* synthetic */ BackupActivity this$0;

        /* renamed from: com.Mode.toolbox.BackupActivity$2$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f18short = null;
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ ProgressDialog val$dialog;

            /*  JADX ERROR: Failed to decode insn: 0x000A: UNKNOWN(0xDE43)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000A: UNKNOWN(0xDE43)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            AnonymousClass1(com.Mode.toolbox.BackupActivity.AnonymousClass2 r52, android.app.ProgressDialog r53) {
                /*
                    r51 = this;
                    double r13 = (double) r4
                    r6 = 0
                    r186 = r63[r182]
                    long r12 = r12 & r11
                    int r10 = (-9698) - r9
                    double r124 = r238 / r98
                    r109 = move-result
                    // decode failed: Unknown instruction: '0x000A: UNKNOWN(0xDE43)'
                    int r235 = r222 >> r229
                    double r7 = -r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.BackupActivity.AnonymousClass2.AnonymousClass1.<init>(com.Mode.toolbox.BackupActivity$2, android.app.ProgressDialog):void");
            }

            /* renamed from: ۣۣ۟ۡۢ, reason: not valid java name and contains not printable characters */
            public static native ProgressDialog m63(Object obj);

            /* renamed from: ۟ۥۧۢۡ, reason: not valid java name and contains not printable characters */
            public static native short[] m64();

            /* renamed from: ۟ۧۡۤ۠, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass2 m65(Object obj);

            /* renamed from: ۧ۠ۢ۠, reason: not valid java name and contains not printable characters */
            public static native BackupActivity m66(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /* renamed from: com.Mode.toolbox.BackupActivity$2$2, reason: invalid class name and collision with other inner class name */
        class RunnableC00012 implements Runnable {

            /* renamed from: short, reason: not valid java name */
            private static final short[] f19short = null;
            final /* synthetic */ AnonymousClass1 this$2;
            final /* synthetic */ String val$output;
            final /* synthetic */ boolean val$success;

            /*  JADX ERROR: Dependency scan failed at insn: 0x000C: IPUT r1, r8
                java.lang.IllegalArgumentException: newPosition > limit: (404816 > 104176)
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
            /*  JADX ERROR: Failed to decode insn: 0x0005: UNKNOWN(0x3279)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0005: UNKNOWN(0x3279)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0006: UNKNOWN(0x4B40)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0006: UNKNOWN(0x4B40)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x0008: UNKNOWN(0x76E7)
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0008: UNKNOWN(0x76E7)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:508)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:51)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:86)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
                */
            /*  JADX ERROR: Failed to decode insn: 0x000C: IPUT r1, r8
                java.lang.IllegalArgumentException: newPosition > limit: (404816 > 104176)
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
            RunnableC00012(com.Mode.toolbox.BackupActivity.AnonymousClass2.AnonymousClass1 r52, java.lang.String r53, boolean r54) {
                /*
                    r51 = this;
                    r20 = 1132987781(0x43880585, float:272.04312)
                    int r93 = (r146 > r133 ? 1 : (r146 == r133 ? 0 : -1))
                    // decode failed: Unknown instruction: '0x0005: UNKNOWN(0x3279)'
                    // decode failed: Unknown instruction: '0x0006: UNKNOWN(0x4B40)'
                    int r2 = (int) r3
                    // decode failed: Unknown instruction: '0x0008: UNKNOWN(0x76E7)'
                    r63 = r16416
                    long r10 = r10 * r9
                    // decode failed: newPosition > limit: (404816 > 104176)
                    int r12 = -r9
                    r12130 = r26813
                */
                throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.BackupActivity.AnonymousClass2.RunnableC00012.<init>(com.Mode.toolbox.BackupActivity$2$1, java.lang.String, boolean):void");
            }

            /* renamed from: ۟۠ۡ۠ۦ, reason: not valid java name and contains not printable characters */
            public static native String m67(Object obj);

            /* renamed from: ۟ۧۡۡۤ, reason: not valid java name and contains not printable characters */
            public static native short[] m68();

            /* renamed from: ۠ۢۢ۠, reason: not valid java name and contains not printable characters */
            public static native BackupActivity m69(Object obj);

            /* renamed from: ۣۢۥۣ, reason: not valid java name and contains not printable characters */
            public static native AnonymousClass2 m70(Object obj);

            /* renamed from: ۣۥ۟, reason: not valid java name and contains not printable characters */
            public static native boolean m71(Object obj);

            /* renamed from: ۦۨۤۧ, reason: contains not printable characters */
            public static native AnonymousClass1 m72(Object obj);

            @Override // java.lang.Runnable
            public native void run();
        }

        /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
            jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x0000: CONST (r179 I:??[long, double]) = (-6378437571215364593(0xa77b3cd2c9b3ee0f, double:-1.6876812177950323E-118) ??[long, double]), expected to be less than 53
            	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:79)
            	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
            */
        AnonymousClass2(com.Mode.toolbox.BackupActivity r52) {
            /*
                r51 = this;
                r179 = -6378437571215364593(0xa77b3cd2c9b3ee0f, double:-1.6876812177950323E-118)
                char r10 = r6.

                long r11 = r11 / r8
                long r3 = ~r11
                int r13 = r13 << r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.Mode.toolbox.BackupActivity.AnonymousClass2.<init>(com.Mode.toolbox.BackupActivity):void");
        }

        /* renamed from: ۟ۦۥ۠ۡ, reason: not valid java name and contains not printable characters */
        public static native BackupActivity m60(Object obj);

        /* renamed from: ۡ۠ۧۢ, reason: not valid java name and contains not printable characters */
        public static native int m61(Object obj);

        /* renamed from: ۢ۟ۧۥ, reason: not valid java name and contains not printable characters */
        public static native short[] m62();

        @Override // android.content.DialogInterface.OnClickListener
        public native void onClick(DialogInterface dialogInterface, int i);
    }

    private native boolean checkBackupFilesExist();

    private native String getFormattedFileSize();

    private native void showBackupStatus();

    /* renamed from: ۣ۟ۢۢۢ, reason: not valid java name and contains not printable characters */
    public static native boolean m34(Object obj);

    /* renamed from: ۣ۟ۧۡۥ, reason: not valid java name and contains not printable characters */
    public static native TextView m35(Object obj);

    /* renamed from: ۟ۥۣ۟ۥ, reason: not valid java name and contains not printable characters */
    public static native short[] m36();

    /* renamed from: ۟ۦۦۢ, reason: not valid java name and contains not printable characters */
    public static native int m37(Object obj);

    /* renamed from: ۣ۠ۡۡ, reason: not valid java name and contains not printable characters */
    public static native void m38(Object obj);

    /* renamed from: ۢۤۨۧ, reason: not valid java name and contains not printable characters */
    public static native TextView m39(Object obj);

    /* renamed from: ۣۦۡ, reason: not valid java name and contains not printable characters */
    public static native String m40(Object obj);

    public native void Back(View view);

    public native void onBackupClick(View view);

    public native void onCleanClick(View view);

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    public native void onExportClick(View view);

    public native void onImportClick(View view);

    public native void onRestoreClick(View view);

    @Override // android.app.Activity
    protected native void onResume();
}
