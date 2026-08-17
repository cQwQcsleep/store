package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2611sd extends AbstractC2526rd {
    public int A;
    public int B;
    public X7 C;
    public int D;
    public X7 E;
    public C2052m20 F;
    public C2052m20 G;
    public H4 H;
    public int I;
    public final int c;
    public int d;
    public final Ag0 e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int[] j;
    public C0805Rp k;
    public C0805Rp l;
    public YO m;
    public YO n;
    public int o;
    public X7 p;
    public int q;
    public int r;
    public int s;
    public int t;
    public X7 u;
    public L2 v;
    public L2 w;
    public L2 x;
    public L2 y;
    public C2253oP z;

    public C2611sd() {
        super(null);
        this.c = 0;
        this.e = new Ag0(this);
        this.I = 0;
    }

    public final byte[] a(byte[] bArr, boolean z) {
        G4 g4 = new G4();
        g4.a(this.H);
        for (C0805Rp c0805Rp = this.k; c0805Rp != null; c0805Rp = (C0805Rp) c0805Rp.b) {
            g4.a(c0805Rp.m);
        }
        for (YO yo = this.m; yo != null; yo = (YO) yo.b) {
            g4.a(yo.L);
            g4.a(null);
        }
        for (C2052m20 c2052m20 = this.F; c2052m20 != null; c2052m20 = (C2052m20) c2052m20.a) {
            g4.a(c2052m20.j);
        }
        int i = g4.a;
        H4[] h4Arr = new H4[i];
        System.arraycopy(g4.b, 0, h4Arr, 0, i);
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = 0;
        this.B = 0;
        this.C = null;
        this.D = 0;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = z ? 3 : 0;
        new C1586gd(bArr, false).a(this, h4Arr, (z ? 8 : 0) | Fcntl.S_IRUSR);
        return b();
    }

    /* JADX WARN: Code duplicated, block: B:114:0x0248  */
    /* JADX WARN: Code duplicated, block: B:117:0x025b  */
    /* JADX WARN: Code duplicated, block: B:118:0x0267  */
    /* JADX WARN: Code duplicated, block: B:121:0x0271  */
    /* JADX WARN: Code duplicated, block: B:124:0x028a  */
    /* JADX WARN: Code duplicated, block: B:125:0x0296  */
    /* JADX WARN: Code duplicated, block: B:128:0x029c  */
    /* JADX WARN: Code duplicated, block: B:129:0x02a9  */
    /* JADX WARN: Code duplicated, block: B:132:0x02af  */
    /* JADX WARN: Code duplicated, block: B:135:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:138:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:141:0x02db  */
    /* JADX WARN: Code duplicated, block: B:142:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:144:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:146:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:147:0x02fc  */
    /* JADX WARN: Code duplicated, block: B:149:0x0300  */
    /* JADX WARN: Code duplicated, block: B:152:0x0306  */
    /* JADX WARN: Code duplicated, block: B:154:0x030a  */
    /* JADX WARN: Code duplicated, block: B:155:0x030d  */
    /* JADX WARN: Code duplicated, block: B:158:0x0316  */
    /* JADX WARN: Code duplicated, block: B:159:0x0319  */
    /* JADX WARN: Code duplicated, block: B:162:0x0345  */
    /* JADX WARN: Code duplicated, block: B:163:0x0357  */
    /* JADX WARN: Code duplicated, block: B:166:0x035d  */
    /* JADX WARN: Code duplicated, block: B:168:0x0369  */
    /* JADX WARN: Code duplicated, block: B:171:0x0371  */
    /* JADX WARN: Code duplicated, block: B:174:0x0380  */
    /* JADX WARN: Code duplicated, block: B:177:0x0395  */
    /* JADX WARN: Code duplicated, block: B:180:0x03ae  */
    /* JADX WARN: Code duplicated, block: B:183:0x03b5  */
    /* JADX WARN: Code duplicated, block: B:184:0x03c2  */
    /* JADX WARN: Code duplicated, block: B:187:0x03cc  */
    /* JADX WARN: Code duplicated, block: B:189:0x03f5  */
    /* JADX WARN: Code duplicated, block: B:191:0x03fc A[LOOP:25: B:190:0x03fa->B:191:0x03fc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:193:0x0416  */
    /* JADX WARN: Code duplicated, block: B:198:0x0441  */
    /* JADX WARN: Code duplicated, block: B:200:0x0444 A[LOOP:7: B:199:0x0442->B:200:0x0444, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:203:0x0457 A[LOOP:8: B:202:0x0455->B:203:0x0457, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:207:0x047d  */
    /* JADX WARN: Code duplicated, block: B:209:0x04af  */
    /* JADX WARN: Code duplicated, block: B:210:0x04b2  */
    /* JADX WARN: Code duplicated, block: B:214:0x04d0 A[LOOP:9: B:212:0x04cc->B:214:0x04d0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:217:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:219:0x04eb  */
    /* JADX WARN: Code duplicated, block: B:220:0x04ee  */
    /* JADX WARN: Code duplicated, block: B:222:0x04f1  */
    /* JADX WARN: Code duplicated, block: B:223:0x04f4  */
    /* JADX WARN: Code duplicated, block: B:226:0x050c  */
    /* JADX WARN: Code duplicated, block: B:227:0x050f  */
    /* JADX WARN: Code duplicated, block: B:230:0x0518 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:234:0x0520  */
    /* JADX WARN: Code duplicated, block: B:237:0x0526  */
    /* JADX WARN: Code duplicated, block: B:240:0x052c  */
    /* JADX WARN: Code duplicated, block: B:243:0x0532  */
    /* JADX WARN: Code duplicated, block: B:246:0x0538  */
    /* JADX WARN: Code duplicated, block: B:249:0x053e  */
    /* JADX WARN: Code duplicated, block: B:252:0x0544  */
    /* JADX WARN: Code duplicated, block: B:254:0x0547 A[LOOP:11: B:253:0x0545->B:254:0x0547, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:258:0x0554  */
    /* JADX WARN: Code duplicated, block: B:259:0x056c  */
    /* JADX WARN: Code duplicated, block: B:262:0x0598  */
    /* JADX WARN: Code duplicated, block: B:264:0x059e A[LOOP:12: B:263:0x059c->B:264:0x059e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:268:0x05df  */
    /* JADX WARN: Code duplicated, block: B:270:0x05e3  */
    /* JADX WARN: Code duplicated, block: B:271:0x05e6  */
    /* JADX WARN: Code duplicated, block: B:274:0x05f3  */
    /* JADX WARN: Code duplicated, block: B:275:0x05f6  */
    /* JADX WARN: Code duplicated, block: B:277:0x05f9  */
    /* JADX WARN: Code duplicated, block: B:279:0x05fe  */
    /* JADX WARN: Code duplicated, block: B:282:0x0617  */
    /* JADX WARN: Code duplicated, block: B:283:0x0630  */
    /* JADX WARN: Code duplicated, block: B:285:0x0636  */
    /* JADX WARN: Code duplicated, block: B:286:0x0639  */
    /* JADX WARN: Code duplicated, block: B:289:0x063e  */
    /* JADX WARN: Code duplicated, block: B:292:0x0648 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:294:0x064d  */
    /* JADX WARN: Code duplicated, block: B:297:0x0653  */
    /* JADX WARN: Code duplicated, block: B:300:0x0659  */
    /* JADX WARN: Code duplicated, block: B:303:0x065f  */
    /* JADX WARN: Code duplicated, block: B:306:0x0665  */
    /* JADX WARN: Code duplicated, block: B:309:0x066b  */
    /* JADX WARN: Code duplicated, block: B:312:0x0671  */
    /* JADX WARN: Code duplicated, block: B:315:0x0677  */
    /* JADX WARN: Code duplicated, block: B:318:0x067d  */
    /* JADX WARN: Code duplicated, block: B:321:0x0683  */
    /* JADX WARN: Code duplicated, block: B:324:0x0689  */
    /* JADX WARN: Code duplicated, block: B:327:0x068f  */
    /* JADX WARN: Code duplicated, block: B:329:0x0692 A[LOOP:14: B:328:0x0690->B:329:0x0692, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:333:0x06a1  */
    /* JADX WARN: Code duplicated, block: B:335:0x06a8 A[LOOP:15: B:334:0x06a6->B:335:0x06a8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:338:0x06b8  */
    /* JADX WARN: Code duplicated, block: B:339:0x06c0  */
    /* JADX WARN: Code duplicated, block: B:342:0x06c5  */
    /* JADX WARN: Code duplicated, block: B:345:0x06d0  */
    /* JADX WARN: Code duplicated, block: B:348:0x06db  */
    /* JADX WARN: Code duplicated, block: B:351:0x06e6  */
    /* JADX WARN: Code duplicated, block: B:354:0x06f1  */
    /* JADX WARN: Code duplicated, block: B:357:0x072e A[LOOP:16: B:356:0x072c->B:357:0x072e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:360:0x0738 A[LOOP:17: B:359:0x0736->B:360:0x0738, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:363:0x075f  */
    /* JADX WARN: Code duplicated, block: B:365:0x0767  */
    /* JADX WARN: Code duplicated, block: B:366:0x076a  */
    /* JADX WARN: Code duplicated, block: B:368:0x0791  */
    /* JADX WARN: Code duplicated, block: B:371:0x0797  */
    /* JADX WARN: Code duplicated, block: B:374:0x07c1  */
    /* JADX WARN: Code duplicated, block: B:377:0x07eb  */
    /* JADX WARN: Code duplicated, block: B:380:0x0815  */
    /* JADX WARN: Code duplicated, block: B:383:0x0822  */
    /* JADX WARN: Code duplicated, block: B:384:0x082c  */
    /* JADX WARN: Code duplicated, block: B:387:0x0836  */
    /* JADX WARN: Code duplicated, block: B:389:0x0857 A[LOOP:18: B:388:0x0855->B:389:0x0857, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:392:0x0885  */
    /* JADX WARN: Code duplicated, block: B:394:0x0891  */
    /* JADX WARN: Code duplicated, block: B:398:0x0899  */
    /* JADX WARN: Code duplicated, block: B:400:0x08a5  */
    /* JADX WARN: Code duplicated, block: B:404:0x08ad  */
    /* JADX WARN: Code duplicated, block: B:405:0x08cc  */
    /* JADX WARN: Code duplicated, block: B:408:0x08d2  */
    /* JADX WARN: Code duplicated, block: B:409:0x08fb  */
    /* JADX WARN: Code duplicated, block: B:412:0x0903  */
    /* JADX WARN: Code duplicated, block: B:414:0x0909 A[LOOP:19: B:413:0x0907->B:414:0x0909, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:419:0x094b  */
    /* JADX WARN: Code duplicated, block: B:422:0x0977  */
    /* JADX WARN: Code duplicated, block: B:425:0x099a  */
    /* JADX WARN: Code duplicated, block: B:430:0x09b9  */
    /* JADX WARN: Code duplicated, block: B:431:0x09d0  */
    /* JADX WARN: Code duplicated, block: B:434:0x09d5  */
    /* JADX WARN: Code duplicated, block: B:437:0x09ee  */
    /* JADX WARN: Code duplicated, block: B:438:0x0a09  */
    /* JADX WARN: Code duplicated, block: B:441:0x0a10  */
    /* JADX WARN: Code duplicated, block: B:444:0x0a3e  */
    /* JADX WARN: Code duplicated, block: B:447:0x0a68  */
    /* JADX WARN: Code duplicated, block: B:450:0x0a6f  */
    /* JADX WARN: Code duplicated, block: B:453:0x0a89  */
    /* JADX WARN: Code duplicated, block: B:456:0x0ab5  */
    /* JADX WARN: Code duplicated, block: B:459:0x0ae3  */
    /* JADX WARN: Code duplicated, block: B:461:0x0ae7  */
    /* JADX WARN: Code duplicated, block: B:463:0x0b04  */
    /* JADX WARN: Code duplicated, block: B:465:0x0b13  */
    /* JADX WARN: Code duplicated, block: B:466:0x0b16  */
    /* JADX WARN: Code duplicated, block: B:469:0x0b1b  */
    /* JADX WARN: Code duplicated, block: B:472:0x0b21  */
    /* JADX WARN: Code duplicated, block: B:475:0x0b27  */
    /* JADX WARN: Code duplicated, block: B:478:0x0b2d  */
    /* JADX WARN: Code duplicated, block: B:481:0x0b33  */
    /* JADX WARN: Code duplicated, block: B:483:0x0b36 A[LOOP:22: B:482:0x0b34->B:483:0x0b36, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:487:0x0b64  */
    /* JADX WARN: Code duplicated, block: B:489:0x0b6a A[LOOP:23: B:488:0x0b68->B:489:0x0b6a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:493:0x0b92  */
    /* JADX WARN: Code duplicated, block: B:495:0x0b98 A[LOOP:20: B:494:0x0b96->B:495:0x0b98, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:498:0x0bba  */
    /* JADX WARN: Code duplicated, block: B:500:0x0bbf A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:501:0x0bc0  */
    /* JADX WARN: Code duplicated, block: B:524:0x05c4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:538:0x0b88 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:387:0x0836, please report this as an issue */
    public final byte[] b() {
        String str;
        int i;
        int i2;
        String str2;
        int i3;
        int i4;
        X7 x7;
        L2 l2;
        L2 l3;
        L2 l4;
        L2 l5;
        Ag0 ag0;
        int i5;
        C2253oP c2253oP;
        String str3;
        X7 x8;
        X7 x9;
        String str4;
        C2052m20 c2052m20;
        int i6;
        int i7;
        String str5;
        String str6;
        String str7;
        String str8;
        int i8;
        int iA;
        H4 h4;
        Ag0 ag1;
        int i9;
        H4 h5;
        int i10;
        Ag0 ag2;
        int i11;
        int i12;
        X7 x10;
        int i13;
        int i14;
        int i15;
        C0805Rp c0805Rp;
        X7 x11;
        YO yo;
        boolean z;
        boolean z2;
        int i16;
        X7 x12;
        int i17;
        Ag0 ag3;
        C2253oP c2253oP2;
        C2052m20 c2052m21;
        int i18;
        H4 h6;
        H4 h7;
        Ag0 ag4;
        int i19;
        H4 h8;
        byte[] bArr;
        Ag0 ag5;
        boolean z3;
        boolean z4;
        boolean z5;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        H4 h9;
        int i26;
        String str9;
        boolean z6;
        String str10;
        String str11;
        H4 h10;
        String str12;
        Ag0 ag6;
        L2[] l2Arr;
        int length;
        L2[] l2Arr2;
        int length2;
        int i27;
        C0523Gs c0523Gs;
        int i28;
        int iB;
        X7 x13;
        int i29;
        X7 x14;
        X7 x15;
        X7 x16;
        L2 l6;
        L2 l7;
        C0523Gs c0523Gs2;
        C0523Gs c0523Gs3;
        int i30;
        L2 l8;
        L2 l9;
        Ag0 ag7;
        String str13;
        int i31;
        boolean z7;
        int i32;
        int i33;
        int i34;
        H4 h11;
        String str14;
        X7 x17;
        H4 h12;
        Ag0 ag8;
        int i35;
        int i36;
        H4 h13;
        Ag0 ag9;
        int i37;
        int i38;
        int i39;
        int i40;
        Ag0 ag10;
        int i41;
        int i42;
        String str15;
        int iB2;
        int i43;
        int i44;
        int i45 = 2;
        int i46 = (this.i * 2) + 24;
        C0805Rp c0805Rp2 = this.k;
        int i47 = 0;
        while (true) {
            str = "ConstantValue";
            if (c0805Rp2 == null) {
                break;
            }
            i47++;
            if (c0805Rp2.h != 0) {
                c0805Rp2.c.a("ConstantValue");
                i44 = 16;
            } else {
                i44 = 8;
            }
            int iA2 = L2.a(c0805Rp2.i, c0805Rp2.j, c0805Rp2.k, c0805Rp2.l) + H4.a(c0805Rp2.c, c0805Rp2.d, c0805Rp2.g) + i44;
            H4 h14 = c0805Rp2.m;
            if (h14 != null) {
                Ag0 ag11 = c0805Rp2.c;
                C2611sd c2611sd = ag11.a;
                int i48 = 0;
                while (h14 != null) {
                    ag11.a(h14.a);
                    i48 += h14.a().b + 6;
                    h14 = h14.c;
                }
                iA2 += i48;
            }
            i46 += iA2;
            c0805Rp2 = (C0805Rp) c0805Rp2.b;
        }
        YO yo2 = this.m;
        int i49 = 0;
        while (true) {
            String str16 = "MethodParameters";
            String str17 = "AnnotationDefault";
            int i50 = i45;
            String str18 = "Code";
            String str19 = "RuntimeInvisibleTypeAnnotations";
            int i51 = i46;
            String str20 = "RuntimeVisibleTypeAnnotations";
            int i52 = i49;
            if (yo2 == null) {
                int i53 = i47;
                String str21 = str;
                X7 x18 = this.p;
                String str22 = "InnerClasses";
                if (x18 != null) {
                    i = x18.b + 8 + i51;
                    this.e.a("InnerClasses");
                    i2 = 1;
                } else {
                    i = i51;
                    i2 = 0;
                }
                int iB3 = i;
                if (this.q != 0) {
                    i2++;
                    iB3 += 10;
                    this.e.a("EnclosingMethod");
                }
                int i54 = i2;
                if ((this.f & 4096) != 0) {
                    str2 = "EnclosingMethod";
                    if ((this.d & 65535) < 49) {
                        i3 = i54 + 1;
                        iB3 += 6;
                        this.e.a("Synthetic");
                    }
                    i4 = i3;
                    if (this.s != 0) {
                        iB3 += 8;
                        i4++;
                        this.e.a("Signature");
                    }
                    if (this.t != 0) {
                        i4++;
                        iB3 += 8;
                        this.e.a("SourceFile");
                    }
                    x7 = this.u;
                    if (x7 != null) {
                        i4++;
                        iB3 = x7.b + 6 + iB3;
                        this.e.a("SourceDebugExtension");
                    }
                    if ((this.f & 131072) != 0) {
                        i4++;
                        iB3 += 6;
                        this.e.a("Deprecated");
                    }
                    l2 = this.v;
                    if (l2 != null) {
                        i4++;
                        iB3 = l2.b("RuntimeVisibleAnnotations") + iB3;
                    }
                    l3 = this.w;
                    if (l3 != null) {
                        i4++;
                        iB3 = l3.b("RuntimeInvisibleAnnotations") + iB3;
                    }
                    l4 = this.x;
                    if (l4 != null) {
                        i4++;
                        iB3 = l4.b("RuntimeVisibleTypeAnnotations") + iB3;
                    }
                    l5 = this.y;
                    if (l5 != null) {
                        i4++;
                        iB3 = l5.b("RuntimeInvisibleTypeAnnotations") + iB3;
                    }
                    ag0 = this.e;
                    if (ag0.j != null) {
                        ag0.a("BootstrapMethods");
                        i5 = ag0.j.b + 8;
                    } else {
                        i5 = 0;
                    }
                    if (i5 > 0) {
                        i4++;
                        ag10 = this.e;
                        if (ag10.j != null) {
                            ag10.a("BootstrapMethods");
                            i41 = ag10.j.b + 8;
                        } else {
                            i41 = 0;
                        }
                        iB3 = i41 + iB3;
                    }
                    c2253oP = this.z;
                    if (c2253oP == null) {
                        if (c2253oP.p > 0) {
                            i38 = 1;
                        } else {
                            i38 = 0;
                        }
                        int i55 = i38 + 1;
                        if (c2253oP.r > 0) {
                            i39 = 1;
                        } else {
                            i39 = 0;
                        }
                        i4 = i55 + i39 + i4;
                        c2253oP.b.a("Module");
                        i40 = c2253oP.g.b + 22 + c2253oP.i.b + c2253oP.k.b + c2253oP.m.b + c2253oP.o.b;
                        if (c2253oP.p > 0) {
                            c2253oP.b.a("ModulePackages");
                            i40 = c2253oP.q.b + 8 + i40;
                        }
                        if (c2253oP.r > 0) {
                            c2253oP.b.a("ModuleMainClass");
                            i40 += 8;
                        }
                        iB3 += i40;
                    }
                    str3 = "NestHost";
                    if (this.A != 0) {
                        i4++;
                        iB3 += 8;
                        this.e.a("NestHost");
                    }
                    x8 = this.C;
                    if (x8 != null) {
                        i4++;
                        iB3 = x8.b + 8 + iB3;
                        this.e.a("NestMembers");
                    }
                    x9 = this.E;
                    if (x9 != null) {
                        i4++;
                        iB3 = x9.b + 8 + iB3;
                        this.e.a("PermittedSubclasses");
                    }
                    if ((this.f & 65536) == 0) {
                        str4 = "PermittedSubclasses";
                        if (this.F != null) {
                            str5 = "InnerClasses";
                            str6 = "NestHost";
                            str7 = "MethodParameters";
                            str8 = "AnnotationDefault";
                            i8 = 0;
                            i6 = 0;
                        }
                        h5 = this.H;
                        if (h5 != null) {
                            i36 = 0;
                            while (h5 != null) {
                                i36++;
                                h5 = h5.c;
                            }
                            i4 = i36 + i4;
                            h13 = this.H;
                            ag9 = this.e;
                            h13.getClass();
                            C2611sd c2611sd2 = ag9.a;
                            i37 = 0;
                            while (h13 != null) {
                                ag9.a(h13.a);
                                i37 += h13.a().b + 6;
                                h13 = h13.c;
                            }
                            iB3 = i37 + iB3;
                        }
                        i10 = i4;
                        ag2 = this.e;
                        i11 = iB3 + ag2.h.b;
                        i12 = ag2.g;
                        if (i12 <= 65535) {
                            throw new C2184nd(i12, ag2.d);
                        }
                        x10 = new X7(i11);
                        x10.c(-889275714).c(this.d);
                        Ag0 ag12 = this.e;
                        X7 x7D = x10.d(ag12.g);
                        X7 x19 = ag12.h;
                        i13 = i8;
                        x7D.a(x19.a, 0, x19.b);
                        if ((this.d & 65535) < 49) {
                            i14 = 4096;
                        } else {
                            i14 = 0;
                        }
                        x10.d((~i14) & this.f).d(this.g).d(this.h);
                        x10.d(this.i);
                        for (i15 = 0; i15 < this.i; i15++) {
                            x10.d(this.j[i15]);
                        }
                        x10.d(i53);
                        c0805Rp = this.k;
                        while (c0805Rp != null) {
                            if (c0805Rp.c.c < 49) {
                                z7 = true;
                            } else {
                                z7 = false;
                            }
                            if (z7) {
                                i32 = 4096;
                            } else {
                                i32 = 0;
                            }
                            x10.d((~i32) & c0805Rp.d).d(c0805Rp.e).d(c0805Rp.f);
                            if (c0805Rp.h != 0) {
                                i33 = 1;
                            } else {
                                i33 = 0;
                            }
                            i34 = c0805Rp.d;
                            boolean z8 = z7;
                            if ((i34 & 4096) != 0 && z8) {
                                i33++;
                            }
                            if (c0805Rp.g != 0) {
                                i33++;
                            }
                            if ((i34 & 131072) != 0) {
                                i33++;
                            }
                            if (c0805Rp.i != null) {
                                i33++;
                            }
                            if (c0805Rp.j != null) {
                                i33++;
                            }
                            if (c0805Rp.k != null) {
                                i33++;
                            }
                            if (c0805Rp.l != null) {
                                i33++;
                            }
                            h11 = c0805Rp.m;
                            if (h11 != null) {
                                i35 = 0;
                                while (h11 != null) {
                                    i35++;
                                    h11 = h11.c;
                                }
                                i33 += i35;
                            }
                            x10.d(i33);
                            if (c0805Rp.h != 0) {
                                str14 = str21;
                                x10.d(c0805Rp.c.a(str14)).c(i50).d(c0805Rp.h);
                            } else {
                                str14 = str21;
                            }
                            str21 = str14;
                            H4.a(c0805Rp.c, c0805Rp.d, c0805Rp.g, x10);
                            x17 = x10;
                            L2.a(c0805Rp.c, c0805Rp.i, c0805Rp.j, c0805Rp.k, c0805Rp.l, x17);
                            h12 = c0805Rp.m;
                            if (h12 != null) {
                                ag8 = c0805Rp.c;
                                C2611sd c2611sd3 = ag8.a;
                                while (h12 != null) {
                                    X7 x7A = h12.a();
                                    x17.d(ag8.a(h12.a)).c(x7A.b);
                                    x17.a(x7A.a, 0, x7A.b);
                                    h12 = h12.c;
                                    ag8 = ag8;
                                    i10 = i10;
                                }
                            }
                            c0805Rp = (C0805Rp) c0805Rp.b;
                            x10 = x17;
                            i10 = i10;
                            i50 = 2;
                        }
                        int i56 = i10;
                        x11 = x10;
                        x11.d(i52);
                        yo = this.m;
                        z = false;
                        z2 = false;
                        while (yo != null) {
                            if (yo.t > 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            boolean z9 = z | z3;
                            z4 = z2 | yo.X;
                            if (yo.c.c < 49) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            if (z5) {
                                i20 = 4096;
                            } else {
                                i20 = 0;
                            }
                            x11.d(yo.d & (~i20)).d(yo.e).d(yo.g);
                            i21 = yo.Z;
                            if (i21 != 0) {
                                x11.a(yo.c.b.b, i21, yo.a0);
                                str12 = str19;
                                str9 = str18;
                                z6 = z4;
                                str11 = str7;
                                str10 = str8;
                                str20 = str20;
                            } else {
                                if (yo.k.b > 0) {
                                    i22 = 1;
                                } else {
                                    i22 = 0;
                                }
                                if (yo.x > 0) {
                                    i22++;
                                }
                                i23 = yo.d;
                                i24 = i22;
                                if ((i23 & 4096) == 0 && z5) {
                                    i25 = i24 + 1;
                                } else {
                                    i25 = i24;
                                }
                                if (yo.z != 0) {
                                    i25++;
                                }
                                if ((i23 & 131072) != 0) {
                                    i25++;
                                }
                                if (yo.A != null) {
                                    i25++;
                                }
                                if (yo.B != null) {
                                    i25++;
                                }
                                if (yo.D != null) {
                                    i25++;
                                }
                                if (yo.F != null) {
                                    i25++;
                                }
                                if (yo.G != null) {
                                    i25++;
                                }
                                if (yo.H != null) {
                                    i25++;
                                }
                                if (yo.I != null) {
                                    i25++;
                                }
                                if (yo.K != null) {
                                    i25++;
                                }
                                h9 = yo.L;
                                if (h9 != null) {
                                    i31 = 0;
                                    while (h9 != null) {
                                        i31++;
                                        h9 = h9.c;
                                    }
                                    i25 += i31;
                                }
                                x11.d(i25);
                                i26 = yo.k.b;
                                if (i26 > 0) {
                                    int i57 = i26 + 10;
                                    i28 = 0;
                                    for (c0523Gs = yo.l; c0523Gs != null; c0523Gs = c0523Gs.f) {
                                        i28++;
                                    }
                                    iB = (i28 * 8) + 2 + i57;
                                    x13 = yo.u;
                                    if (x13 != null) {
                                        iB += x13.b + 8;
                                        i29 = 1;
                                    } else {
                                        i29 = 0;
                                    }
                                    x14 = yo.o;
                                    if (x14 != null) {
                                        iB += x14.b + 8;
                                        i29++;
                                    }
                                    x15 = yo.q;
                                    if (x15 != null) {
                                        iB += x15.b + 8;
                                        i29++;
                                    }
                                    x16 = yo.s;
                                    if (x16 != null) {
                                        iB += x16.b + 8;
                                        i29++;
                                    }
                                    l6 = yo.v;
                                    if (l6 != null) {
                                        iB += l6.b(str20);
                                        i29++;
                                    }
                                    l7 = yo.w;
                                    if (l7 != null) {
                                        iB += l7.b(str19);
                                        i29++;
                                    }
                                    X7 x7C = x11.d(yo.c.a(str18)).c(iB).d(yo.i).d(yo.j).c(yo.k.b);
                                    X7 x20 = yo.k;
                                    str9 = str18;
                                    z6 = z4;
                                    x7C.a(x20.a, 0, x20.b);
                                    c0523Gs2 = yo.l;
                                    i30 = 0;
                                    for (c0523Gs3 = c0523Gs2; c0523Gs3 != null; c0523Gs3 = c0523Gs3.f) {
                                        i30++;
                                    }
                                    x11.d(i30);
                                    while (c0523Gs2 != null) {
                                        x11.d(c0523Gs2.a.e).d(c0523Gs2.b.e).d(c0523Gs2.c.e).d(c0523Gs2.d);
                                        c0523Gs2 = c0523Gs2.f;
                                    }
                                    x11.d(i29);
                                    if (yo.u != null) {
                                        ag7 = yo.c;
                                        if (ag7.c >= 50) {
                                            str13 = "StackMapTable";
                                        } else {
                                            str13 = "StackMap";
                                        }
                                        X7 x7D2 = x11.d(ag7.a(str13)).c(yo.u.b + 2).d(yo.t);
                                        X7 x21 = yo.u;
                                        x7D2.a(x21.a, 0, x21.b);
                                    }
                                    if (yo.o != null) {
                                        X7 x7D3 = x11.d(yo.c.a("LineNumberTable")).c(yo.o.b + 2).d(yo.n);
                                        X7 x22 = yo.o;
                                        x7D3.a(x22.a, 0, x22.b);
                                    }
                                    if (yo.q != null) {
                                        X7 x7D4 = x11.d(yo.c.a("LocalVariableTable")).c(yo.q.b + 2).d(yo.p);
                                        X7 x23 = yo.q;
                                        x7D4.a(x23.a, 0, x23.b);
                                    }
                                    if (yo.s != null) {
                                        X7 x7D5 = x11.d(yo.c.a("LocalVariableTypeTable")).c(yo.s.b + 2).d(yo.r);
                                        X7 x24 = yo.s;
                                        x7D5.a(x24.a, 0, x24.b);
                                    }
                                    l8 = yo.v;
                                    if (l8 != null) {
                                        l8.a(yo.c.a(str20), x11);
                                    }
                                    l9 = yo.w;
                                    if (l9 != null) {
                                        l9.a(yo.c.a(str19), x11);
                                    }
                                } else {
                                    str9 = str18;
                                    z6 = z4;
                                }
                                if (yo.x > 0) {
                                    x11.d(yo.c.a("Exceptions")).c((yo.x * 2) + 2).d(yo.x);
                                    for (int i58 : yo.y) {
                                        x11.d(i58);
                                    }
                                }
                                H4.a(yo.c, yo.d, yo.z, x11);
                                L2.a(yo.c, yo.A, yo.B, yo.G, yo.H, x11);
                                if (yo.D != null) {
                                    int iA3 = yo.c.a("RuntimeVisibleParameterAnnotations");
                                    l2Arr2 = yo.D;
                                    length2 = yo.C;
                                    if (length2 == 0) {
                                        length2 = l2Arr2.length;
                                    }
                                    L2.a(iA3, l2Arr2, length2, x11);
                                }
                                if (yo.F != null) {
                                    int iA4 = yo.c.a("RuntimeInvisibleParameterAnnotations");
                                    l2Arr = yo.F;
                                    length = yo.E;
                                    if (length == 0) {
                                        length = l2Arr.length;
                                    }
                                    L2.a(iA4, l2Arr, length, x11);
                                }
                                if (yo.I != null) {
                                    str10 = str8;
                                    X7 x7C2 = x11.d(yo.c.a(str10)).c(yo.I.b);
                                    X7 x25 = yo.I;
                                    x7C2.a(x25.a, 0, x25.b);
                                } else {
                                    str10 = str8;
                                }
                                if (yo.K != null) {
                                    str11 = str7;
                                    X7 x7B = x11.d(yo.c.a(str11)).c(yo.K.b + 1).b(yo.J);
                                    X7 x26 = yo.K;
                                    x7B.a(x26.a, 0, x26.b);
                                } else {
                                    str11 = str7;
                                }
                                h10 = yo.L;
                                if (h10 != null) {
                                    ag6 = yo.c;
                                    C2611sd c2611sd4 = ag6.a;
                                    while (h10 != null) {
                                        X7 x7A2 = h10.a();
                                        x11.d(ag6.a(h10.a)).c(x7A2.b);
                                        x11.a(x7A2.a, 0, x7A2.b);
                                        h10 = h10.c;
                                        str19 = str19;
                                    }
                                }
                                str12 = str19;
                            }
                            yo = (YO) yo.b;
                            str8 = str10;
                            z = z9;
                            str18 = str9;
                            z2 = z6;
                            str20 = str20;
                            str19 = str12;
                            str7 = str11;
                        }
                        x11.d(i56);
                        if (this.p != null) {
                            X7 x7D6 = x11.d(this.e.a(str5)).c(this.p.b + 2).d(this.o);
                            X7 x27 = this.p;
                            x7D6.a(x27.a, 0, x27.b);
                        }
                        if (this.q != 0) {
                            x11.d(this.e.a(str2)).c(4).d(this.q).d(this.r);
                        }
                        if ((this.f & 4096) != 0 && (this.d & 65535) < 49) {
                            x11.d(this.e.a("Synthetic")).c(0);
                        }
                        if (this.s != 0) {
                            i16 = 2;
                            x11.d(this.e.a("Signature")).c(2).d(this.s);
                        } else {
                            i16 = 2;
                        }
                        if (this.t != 0) {
                            x11.d(this.e.a("SourceFile")).c(i16).d(this.t);
                        }
                        x12 = this.u;
                        if (x12 != null) {
                            int i59 = x12.b;
                            i17 = 0;
                            x11.d(this.e.a("SourceDebugExtension")).c(i59).a(this.u.a, 0, i59);
                        } else {
                            i17 = 0;
                        }
                        if ((this.f & 131072) != 0) {
                            x11.d(this.e.a("Deprecated")).c(i17);
                        }
                        L2.a(this.e, this.v, this.w, this.x, this.y, x11);
                        ag3 = this.e;
                        if (ag3.j != null) {
                            X7 x7D7 = x11.d(ag3.a("BootstrapMethods")).c(ag3.j.b + 2).d(ag3.i);
                            X7 x28 = ag3.j;
                            x7D7.a(x28.a, 0, x28.b);
                        }
                        c2253oP2 = this.z;
                        if (c2253oP2 != null) {
                            c2253oP2.a(x11);
                        }
                        if (this.A != 0) {
                            x11.d(this.e.a(str6)).c(2).d(this.A);
                        }
                        if (this.C != null) {
                            X7 x7D8 = x11.d(this.e.a("NestMembers")).c(this.C.b + 2).d(this.B);
                            X7 x29 = this.C;
                            x7D8.a(x29.a, 0, x29.b);
                        }
                        if (this.E != null) {
                            X7 x7D9 = x11.d(this.e.a(str4)).c(this.E.b + 2).d(this.D);
                            X7 x30 = this.E;
                            x7D9.a(x30.a, 0, x30.b);
                        }
                        if ((this.f & 65536) == 0 || this.F != null) {
                            x11.d(this.e.a("Record")).c(i6 + 2).d(i13);
                            for (c2052m21 = this.F; c2052m21 != null; c2052m21 = (C2052m20) c2052m21.a) {
                                x11.d(c2052m21.c).d(c2052m21.d);
                                if (c2052m21.e != 0) {
                                    i18 = 1;
                                } else {
                                    i18 = 0;
                                }
                                if (c2052m21.f != null) {
                                    i18++;
                                }
                                if (c2052m21.g != null) {
                                    i18++;
                                }
                                if (c2052m21.h != null) {
                                    i18++;
                                }
                                if (c2052m21.i != null) {
                                    i18++;
                                }
                                h6 = c2052m21.j;
                                if (h6 != null) {
                                    i19 = 0;
                                    while (h6 != null) {
                                        i19++;
                                        h6 = h6.c;
                                    }
                                    i18 += i19;
                                }
                                x11.d(i18);
                                H4.a(c2052m21.b, 0, c2052m21.e, x11);
                                L2.a(c2052m21.b, c2052m21.f, c2052m21.g, c2052m21.h, c2052m21.i, x11);
                                h7 = c2052m21.j;
                                if (h7 != null) {
                                    ag4 = c2052m21.b;
                                    C2611sd c2611sd5 = ag4.a;
                                    while (h7 != null) {
                                        X7 x7A3 = h7.a();
                                        x11.d(ag4.a(h7.a)).c(x7A3.b);
                                        x11.a(x7A3.a, 0, x7A3.b);
                                        h7 = h7.c;
                                    }
                                }
                            }
                        }
                        h8 = this.H;
                        if (h8 != null) {
                            ag5 = this.e;
                            C2611sd c2611sd6 = ag5.a;
                            while (h8 != null) {
                                X7 x7A4 = h8.a();
                                x11.d(ag5.a(h8.a)).c(x7A4.b);
                                x11.a(x7A4.a, 0, x7A4.b);
                                h8 = h8.c;
                            }
                        }
                        bArr = x11.a;
                        if (z2) {
                            return a(bArr, z);
                        }
                        return bArr;
                    }
                    str4 = "PermittedSubclasses";
                    c2052m20 = this.F;
                    i6 = 0;
                    i7 = 0;
                    while (c2052m20 != null) {
                        i7++;
                        String str23 = str3;
                        String str24 = str22;
                        String str25 = str16;
                        String str26 = str17;
                        iA = L2.a(c2052m20.f, c2052m20.g, c2052m20.h, c2052m20.i) + H4.a(c2052m20.b, 0, c2052m20.e) + 6;
                        h4 = c2052m20.j;
                        if (h4 != null) {
                            ag1 = c2052m20.b;
                            C2611sd c2611sd7 = ag1.a;
                            i9 = 0;
                            while (h4 != null) {
                                ag1.a(h4.a);
                                i9 += h4.a().b + 6;
                                h4 = h4.c;
                                iA = iA;
                            }
                            iA = i9 + iA;
                        }
                        i6 += iA;
                        c2052m20 = (C2052m20) c2052m20.a;
                        str3 = str23;
                        str22 = str24;
                        str16 = str25;
                        str17 = str26;
                    }
                    str5 = str22;
                    str6 = str3;
                    str7 = str16;
                    str8 = str17;
                    i4++;
                    iB3 = i6 + 8 + iB3;
                    this.e.a("Record");
                    i8 = i7;
                    h5 = this.H;
                    if (h5 != null) {
                        i36 = 0;
                        while (h5 != null) {
                            i36++;
                            h5 = h5.c;
                        }
                        i4 = i36 + i4;
                        h13 = this.H;
                        ag9 = this.e;
                        h13.getClass();
                        C2611sd c2611sd8 = ag9.a;
                        i37 = 0;
                        while (h13 != null) {
                            ag9.a(h13.a);
                            i37 += h13.a().b + 6;
                            h13 = h13.c;
                        }
                        iB3 = i37 + iB3;
                    }
                    i10 = i4;
                    ag2 = this.e;
                    i11 = iB3 + ag2.h.b;
                    i12 = ag2.g;
                    if (i12 <= 65535) {
                        throw new C2184nd(i12, ag2.d);
                    }
                    x10 = new X7(i11);
                    x10.c(-889275714).c(this.d);
                    Ag0 ag13 = this.e;
                    X7 x7D10 = x10.d(ag13.g);
                    X7 x110 = ag13.h;
                    i13 = i8;
                    x7D10.a(x110.a, 0, x110.b);
                    if ((this.d & 65535) < 49) {
                        i14 = 4096;
                    } else {
                        i14 = 0;
                    }
                    x10.d((~i14) & this.f).d(this.g).d(this.h);
                    x10.d(this.i);
                    while (i15 < this.i) {
                        x10.d(this.j[i15]);
                    }
                    x10.d(i53);
                    c0805Rp = this.k;
                    while (c0805Rp != null) {
                        if (c0805Rp.c.c < 49) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        if (z7) {
                            i32 = 4096;
                        } else {
                            i32 = 0;
                        }
                        x10.d((~i32) & c0805Rp.d).d(c0805Rp.e).d(c0805Rp.f);
                        if (c0805Rp.h != 0) {
                            i33 = 1;
                        } else {
                            i33 = 0;
                        }
                        i34 = c0805Rp.d;
                        boolean z10 = z7;
                        if ((i34 & 4096) != 0) {
                            i33++;
                        }
                        if (c0805Rp.g != 0) {
                            i33++;
                        }
                        if ((i34 & 131072) != 0) {
                            i33++;
                        }
                        if (c0805Rp.i != null) {
                            i33++;
                        }
                        if (c0805Rp.j != null) {
                            i33++;
                        }
                        if (c0805Rp.k != null) {
                            i33++;
                        }
                        if (c0805Rp.l != null) {
                            i33++;
                        }
                        h11 = c0805Rp.m;
                        if (h11 != null) {
                            i35 = 0;
                            while (h11 != null) {
                                i35++;
                                h11 = h11.c;
                            }
                            i33 += i35;
                        }
                        x10.d(i33);
                        if (c0805Rp.h != 0) {
                            str14 = str21;
                            x10.d(c0805Rp.c.a(str14)).c(i50).d(c0805Rp.h);
                        } else {
                            str14 = str21;
                        }
                        str21 = str14;
                        H4.a(c0805Rp.c, c0805Rp.d, c0805Rp.g, x10);
                        x17 = x10;
                        L2.a(c0805Rp.c, c0805Rp.i, c0805Rp.j, c0805Rp.k, c0805Rp.l, x17);
                        h12 = c0805Rp.m;
                        if (h12 != null) {
                            ag8 = c0805Rp.c;
                            C2611sd c2611sd9 = ag8.a;
                            while (h12 != null) {
                                X7 x7A5 = h12.a();
                                x17.d(ag8.a(h12.a)).c(x7A5.b);
                                x17.a(x7A5.a, 0, x7A5.b);
                                h12 = h12.c;
                                ag8 = ag8;
                                i10 = i10;
                            }
                        }
                        c0805Rp = (C0805Rp) c0805Rp.b;
                        x10 = x17;
                        i10 = i10;
                        i50 = 2;
                    }
                    int i510 = i10;
                    x11 = x10;
                    x11.d(i52);
                    yo = this.m;
                    z = false;
                    z2 = false;
                    while (yo != null) {
                        if (yo.t > 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z11 = z | z3;
                        z4 = z2 | yo.X;
                        if (yo.c.c < 49) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (z5) {
                            i20 = 4096;
                        } else {
                            i20 = 0;
                        }
                        x11.d(yo.d & (~i20)).d(yo.e).d(yo.g);
                        i21 = yo.Z;
                        if (i21 != 0) {
                            x11.a(yo.c.b.b, i21, yo.a0);
                            str12 = str19;
                            str9 = str18;
                            z6 = z4;
                            str11 = str7;
                            str10 = str8;
                            str20 = str20;
                        } else {
                            if (yo.k.b > 0) {
                                i22 = 1;
                            } else {
                                i22 = 0;
                            }
                            if (yo.x > 0) {
                                i22++;
                            }
                            i23 = yo.d;
                            i24 = i22;
                            if ((i23 & 4096) == 0) {
                                i25 = i24;
                            } else {
                                i25 = i24;
                            }
                            if (yo.z != 0) {
                                i25++;
                            }
                            if ((i23 & 131072) != 0) {
                                i25++;
                            }
                            if (yo.A != null) {
                                i25++;
                            }
                            if (yo.B != null) {
                                i25++;
                            }
                            if (yo.D != null) {
                                i25++;
                            }
                            if (yo.F != null) {
                                i25++;
                            }
                            if (yo.G != null) {
                                i25++;
                            }
                            if (yo.H != null) {
                                i25++;
                            }
                            if (yo.I != null) {
                                i25++;
                            }
                            if (yo.K != null) {
                                i25++;
                            }
                            h9 = yo.L;
                            if (h9 != null) {
                                i31 = 0;
                                while (h9 != null) {
                                    i31++;
                                    h9 = h9.c;
                                }
                                i25 += i31;
                            }
                            x11.d(i25);
                            i26 = yo.k.b;
                            if (i26 > 0) {
                                int i511 = i26 + 10;
                                i28 = 0;
                                while (c0523Gs != null) {
                                    i28++;
                                }
                                iB = (i28 * 8) + 2 + i511;
                                x13 = yo.u;
                                if (x13 != null) {
                                    iB += x13.b + 8;
                                    i29 = 1;
                                } else {
                                    i29 = 0;
                                }
                                x14 = yo.o;
                                if (x14 != null) {
                                    iB += x14.b + 8;
                                    i29++;
                                }
                                x15 = yo.q;
                                if (x15 != null) {
                                    iB += x15.b + 8;
                                    i29++;
                                }
                                x16 = yo.s;
                                if (x16 != null) {
                                    iB += x16.b + 8;
                                    i29++;
                                }
                                l6 = yo.v;
                                if (l6 != null) {
                                    iB += l6.b(str20);
                                    i29++;
                                }
                                l7 = yo.w;
                                if (l7 != null) {
                                    iB += l7.b(str19);
                                    i29++;
                                }
                                X7 x7C3 = x11.d(yo.c.a(str18)).c(iB).d(yo.i).d(yo.j).c(yo.k.b);
                                X7 x210 = yo.k;
                                str9 = str18;
                                z6 = z4;
                                x7C3.a(x210.a, 0, x210.b);
                                c0523Gs2 = yo.l;
                                i30 = 0;
                                while (c0523Gs3 != null) {
                                    i30++;
                                }
                                x11.d(i30);
                                while (c0523Gs2 != null) {
                                    x11.d(c0523Gs2.a.e).d(c0523Gs2.b.e).d(c0523Gs2.c.e).d(c0523Gs2.d);
                                    c0523Gs2 = c0523Gs2.f;
                                }
                                x11.d(i29);
                                if (yo.u != null) {
                                    ag7 = yo.c;
                                    if (ag7.c >= 50) {
                                        str13 = "StackMapTable";
                                    } else {
                                        str13 = "StackMap";
                                    }
                                    X7 x7D11 = x11.d(ag7.a(str13)).c(yo.u.b + 2).d(yo.t);
                                    X7 x211 = yo.u;
                                    x7D11.a(x211.a, 0, x211.b);
                                }
                                if (yo.o != null) {
                                    X7 x7D12 = x11.d(yo.c.a("LineNumberTable")).c(yo.o.b + 2).d(yo.n);
                                    X7 x212 = yo.o;
                                    x7D12.a(x212.a, 0, x212.b);
                                }
                                if (yo.q != null) {
                                    X7 x7D13 = x11.d(yo.c.a("LocalVariableTable")).c(yo.q.b + 2).d(yo.p);
                                    X7 x213 = yo.q;
                                    x7D13.a(x213.a, 0, x213.b);
                                }
                                if (yo.s != null) {
                                    X7 x7D14 = x11.d(yo.c.a("LocalVariableTypeTable")).c(yo.s.b + 2).d(yo.r);
                                    X7 x214 = yo.s;
                                    x7D14.a(x214.a, 0, x214.b);
                                }
                                l8 = yo.v;
                                if (l8 != null) {
                                    l8.a(yo.c.a(str20), x11);
                                }
                                l9 = yo.w;
                                if (l9 != null) {
                                    l9.a(yo.c.a(str19), x11);
                                }
                            } else {
                                str9 = str18;
                                z6 = z4;
                            }
                            if (yo.x > 0) {
                                x11.d(yo.c.a("Exceptions")).c((yo.x * 2) + 2).d(yo.x);
                                while (i27 < r7) {
                                    x11.d(i58);
                                }
                            }
                            H4.a(yo.c, yo.d, yo.z, x11);
                            L2.a(yo.c, yo.A, yo.B, yo.G, yo.H, x11);
                            if (yo.D != null) {
                                int iA5 = yo.c.a("RuntimeVisibleParameterAnnotations");
                                l2Arr2 = yo.D;
                                length2 = yo.C;
                                if (length2 == 0) {
                                    length2 = l2Arr2.length;
                                }
                                L2.a(iA5, l2Arr2, length2, x11);
                            }
                            if (yo.F != null) {
                                int iA6 = yo.c.a("RuntimeInvisibleParameterAnnotations");
                                l2Arr = yo.F;
                                length = yo.E;
                                if (length == 0) {
                                    length = l2Arr.length;
                                }
                                L2.a(iA6, l2Arr, length, x11);
                            }
                            if (yo.I != null) {
                                str10 = str8;
                                X7 x7C4 = x11.d(yo.c.a(str10)).c(yo.I.b);
                                X7 x215 = yo.I;
                                x7C4.a(x215.a, 0, x215.b);
                            } else {
                                str10 = str8;
                            }
                            if (yo.K != null) {
                                str11 = str7;
                                X7 x7B2 = x11.d(yo.c.a(str11)).c(yo.K.b + 1).b(yo.J);
                                X7 x216 = yo.K;
                                x7B2.a(x216.a, 0, x216.b);
                            } else {
                                str11 = str7;
                            }
                            h10 = yo.L;
                            if (h10 != null) {
                                ag6 = yo.c;
                                C2611sd c2611sd10 = ag6.a;
                                while (h10 != null) {
                                    X7 x7A6 = h10.a();
                                    x11.d(ag6.a(h10.a)).c(x7A6.b);
                                    x11.a(x7A6.a, 0, x7A6.b);
                                    h10 = h10.c;
                                    str19 = str19;
                                }
                            }
                            str12 = str19;
                        }
                        yo = (YO) yo.b;
                        str8 = str10;
                        z = z11;
                        str18 = str9;
                        z2 = z6;
                        str20 = str20;
                        str19 = str12;
                        str7 = str11;
                    }
                    x11.d(i510);
                    if (this.p != null) {
                        X7 x7D15 = x11.d(this.e.a(str5)).c(this.p.b + 2).d(this.o);
                        X7 x217 = this.p;
                        x7D15.a(x217.a, 0, x217.b);
                    }
                    if (this.q != 0) {
                        x11.d(this.e.a(str2)).c(4).d(this.q).d(this.r);
                    }
                    if ((this.f & 4096) != 0) {
                        x11.d(this.e.a("Synthetic")).c(0);
                    }
                    if (this.s != 0) {
                        i16 = 2;
                        x11.d(this.e.a("Signature")).c(2).d(this.s);
                    } else {
                        i16 = 2;
                    }
                    if (this.t != 0) {
                        x11.d(this.e.a("SourceFile")).c(i16).d(this.t);
                    }
                    x12 = this.u;
                    if (x12 != null) {
                        int i512 = x12.b;
                        i17 = 0;
                        x11.d(this.e.a("SourceDebugExtension")).c(i512).a(this.u.a, 0, i512);
                    } else {
                        i17 = 0;
                    }
                    if ((this.f & 131072) != 0) {
                        x11.d(this.e.a("Deprecated")).c(i17);
                    }
                    L2.a(this.e, this.v, this.w, this.x, this.y, x11);
                    ag3 = this.e;
                    if (ag3.j != null) {
                        X7 x7D16 = x11.d(ag3.a("BootstrapMethods")).c(ag3.j.b + 2).d(ag3.i);
                        X7 x218 = ag3.j;
                        x7D16.a(x218.a, 0, x218.b);
                    }
                    c2253oP2 = this.z;
                    if (c2253oP2 != null) {
                        c2253oP2.a(x11);
                    }
                    if (this.A != 0) {
                        x11.d(this.e.a(str6)).c(2).d(this.A);
                    }
                    if (this.C != null) {
                        X7 x7D17 = x11.d(this.e.a("NestMembers")).c(this.C.b + 2).d(this.B);
                        X7 x219 = this.C;
                        x7D17.a(x219.a, 0, x219.b);
                    }
                    if (this.E != null) {
                        X7 x7D18 = x11.d(this.e.a(str4)).c(this.E.b + 2).d(this.D);
                        X7 x31 = this.E;
                        x7D18.a(x31.a, 0, x31.b);
                    }
                    if ((this.f & 65536) == 0) {
                        x11.d(this.e.a("Record")).c(i6 + 2).d(i13);
                        while (c2052m21 != null) {
                            x11.d(c2052m21.c).d(c2052m21.d);
                            if (c2052m21.e != 0) {
                                i18 = 1;
                            } else {
                                i18 = 0;
                            }
                            if (c2052m21.f != null) {
                                i18++;
                            }
                            if (c2052m21.g != null) {
                                i18++;
                            }
                            if (c2052m21.h != null) {
                                i18++;
                            }
                            if (c2052m21.i != null) {
                                i18++;
                            }
                            h6 = c2052m21.j;
                            if (h6 != null) {
                                i19 = 0;
                                while (h6 != null) {
                                    i19++;
                                    h6 = h6.c;
                                }
                                i18 += i19;
                            }
                            x11.d(i18);
                            H4.a(c2052m21.b, 0, c2052m21.e, x11);
                            L2.a(c2052m21.b, c2052m21.f, c2052m21.g, c2052m21.h, c2052m21.i, x11);
                            h7 = c2052m21.j;
                            if (h7 != null) {
                                ag4 = c2052m21.b;
                                C2611sd c2611sd11 = ag4.a;
                                while (h7 != null) {
                                    X7 x7A7 = h7.a();
                                    x11.d(ag4.a(h7.a)).c(x7A7.b);
                                    x11.a(x7A7.a, 0, x7A7.b);
                                    h7 = h7.c;
                                }
                            }
                        }
                    } else {
                        x11.d(this.e.a("Record")).c(i6 + 2).d(i13);
                        while (c2052m21 != null) {
                            x11.d(c2052m21.c).d(c2052m21.d);
                            if (c2052m21.e != 0) {
                                i18 = 1;
                            } else {
                                i18 = 0;
                            }
                            if (c2052m21.f != null) {
                                i18++;
                            }
                            if (c2052m21.g != null) {
                                i18++;
                            }
                            if (c2052m21.h != null) {
                                i18++;
                            }
                            if (c2052m21.i != null) {
                                i18++;
                            }
                            h6 = c2052m21.j;
                            if (h6 != null) {
                                i19 = 0;
                                while (h6 != null) {
                                    i19++;
                                    h6 = h6.c;
                                }
                                i18 += i19;
                            }
                            x11.d(i18);
                            H4.a(c2052m21.b, 0, c2052m21.e, x11);
                            L2.a(c2052m21.b, c2052m21.f, c2052m21.g, c2052m21.h, c2052m21.i, x11);
                            h7 = c2052m21.j;
                            if (h7 != null) {
                                ag4 = c2052m21.b;
                                C2611sd c2611sd12 = ag4.a;
                                while (h7 != null) {
                                    X7 x7A8 = h7.a();
                                    x11.d(ag4.a(h7.a)).c(x7A8.b);
                                    x11.a(x7A8.a, 0, x7A8.b);
                                    h7 = h7.c;
                                }
                            }
                        }
                    }
                    h8 = this.H;
                    if (h8 != null) {
                        ag5 = this.e;
                        C2611sd c2611sd13 = ag5.a;
                        while (h8 != null) {
                            X7 x7A9 = h8.a();
                            x11.d(ag5.a(h8.a)).c(x7A9.b);
                            x11.a(x7A9.a, 0, x7A9.b);
                            h8 = h8.c;
                        }
                    }
                    bArr = x11.a;
                    if (z2) {
                        return a(bArr, z);
                    }
                    return bArr;
                }
                str2 = "EnclosingMethod";
                i3 = i54;
                i4 = i3;
                if (this.s != 0) {
                    iB3 += 8;
                    i4++;
                    this.e.a("Signature");
                }
                if (this.t != 0) {
                    i4++;
                    iB3 += 8;
                    this.e.a("SourceFile");
                }
                x7 = this.u;
                if (x7 != null) {
                    i4++;
                    iB3 = x7.b + 6 + iB3;
                    this.e.a("SourceDebugExtension");
                }
                if ((this.f & 131072) != 0) {
                    i4++;
                    iB3 += 6;
                    this.e.a("Deprecated");
                }
                l2 = this.v;
                if (l2 != null) {
                    i4++;
                    iB3 = l2.b("RuntimeVisibleAnnotations") + iB3;
                }
                l3 = this.w;
                if (l3 != null) {
                    i4++;
                    iB3 = l3.b("RuntimeInvisibleAnnotations") + iB3;
                }
                l4 = this.x;
                if (l4 != null) {
                    i4++;
                    iB3 = l4.b("RuntimeVisibleTypeAnnotations") + iB3;
                }
                l5 = this.y;
                if (l5 != null) {
                    i4++;
                    iB3 = l5.b("RuntimeInvisibleTypeAnnotations") + iB3;
                }
                ag0 = this.e;
                if (ag0.j != null) {
                    ag0.a("BootstrapMethods");
                    i5 = ag0.j.b + 8;
                } else {
                    i5 = 0;
                }
                if (i5 > 0) {
                    i4++;
                    ag10 = this.e;
                    if (ag10.j != null) {
                        ag10.a("BootstrapMethods");
                        i41 = ag10.j.b + 8;
                    } else {
                        i41 = 0;
                    }
                    iB3 = i41 + iB3;
                }
                c2253oP = this.z;
                if (c2253oP == null) {
                    if (c2253oP.p > 0) {
                        i38 = 1;
                    } else {
                        i38 = 0;
                    }
                    int i513 = i38 + 1;
                    if (c2253oP.r > 0) {
                        i39 = 1;
                    } else {
                        i39 = 0;
                    }
                    i4 = i513 + i39 + i4;
                    c2253oP.b.a("Module");
                    i40 = c2253oP.g.b + 22 + c2253oP.i.b + c2253oP.k.b + c2253oP.m.b + c2253oP.o.b;
                    if (c2253oP.p > 0) {
                        c2253oP.b.a("ModulePackages");
                        i40 = c2253oP.q.b + 8 + i40;
                    }
                    if (c2253oP.r > 0) {
                        c2253oP.b.a("ModuleMainClass");
                        i40 += 8;
                    }
                    iB3 += i40;
                }
                str3 = "NestHost";
                if (this.A != 0) {
                    i4++;
                    iB3 += 8;
                    this.e.a("NestHost");
                }
                x8 = this.C;
                if (x8 != null) {
                    i4++;
                    iB3 = x8.b + 8 + iB3;
                    this.e.a("NestMembers");
                }
                x9 = this.E;
                if (x9 != null) {
                    i4++;
                    iB3 = x9.b + 8 + iB3;
                    this.e.a("PermittedSubclasses");
                }
                if ((this.f & 65536) == 0) {
                    str4 = "PermittedSubclasses";
                    if (this.F != null) {
                        str5 = "InnerClasses";
                        str6 = "NestHost";
                        str7 = "MethodParameters";
                        str8 = "AnnotationDefault";
                        i8 = 0;
                        i6 = 0;
                    }
                    h5 = this.H;
                    if (h5 != null) {
                        i36 = 0;
                        while (h5 != null) {
                            i36++;
                            h5 = h5.c;
                        }
                        i4 = i36 + i4;
                        h13 = this.H;
                        ag9 = this.e;
                        h13.getClass();
                        C2611sd c2611sd14 = ag9.a;
                        i37 = 0;
                        while (h13 != null) {
                            ag9.a(h13.a);
                            i37 += h13.a().b + 6;
                            h13 = h13.c;
                        }
                        iB3 = i37 + iB3;
                    }
                    i10 = i4;
                    ag2 = this.e;
                    i11 = iB3 + ag2.h.b;
                    i12 = ag2.g;
                    if (i12 <= 65535) {
                        throw new C2184nd(i12, ag2.d);
                    }
                    x10 = new X7(i11);
                    x10.c(-889275714).c(this.d);
                    Ag0 ag14 = this.e;
                    X7 x7D19 = x10.d(ag14.g);
                    X7 x111 = ag14.h;
                    i13 = i8;
                    x7D19.a(x111.a, 0, x111.b);
                    if ((this.d & 65535) < 49) {
                        i14 = 4096;
                    } else {
                        i14 = 0;
                    }
                    x10.d((~i14) & this.f).d(this.g).d(this.h);
                    x10.d(this.i);
                    while (i15 < this.i) {
                        x10.d(this.j[i15]);
                    }
                    x10.d(i53);
                    c0805Rp = this.k;
                    while (c0805Rp != null) {
                        if (c0805Rp.c.c < 49) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        if (z7) {
                            i32 = 4096;
                        } else {
                            i32 = 0;
                        }
                        x10.d((~i32) & c0805Rp.d).d(c0805Rp.e).d(c0805Rp.f);
                        if (c0805Rp.h != 0) {
                            i33 = 1;
                        } else {
                            i33 = 0;
                        }
                        i34 = c0805Rp.d;
                        boolean z12 = z7;
                        if ((i34 & 4096) != 0) {
                            i33++;
                        }
                        if (c0805Rp.g != 0) {
                            i33++;
                        }
                        if ((i34 & 131072) != 0) {
                            i33++;
                        }
                        if (c0805Rp.i != null) {
                            i33++;
                        }
                        if (c0805Rp.j != null) {
                            i33++;
                        }
                        if (c0805Rp.k != null) {
                            i33++;
                        }
                        if (c0805Rp.l != null) {
                            i33++;
                        }
                        h11 = c0805Rp.m;
                        if (h11 != null) {
                            i35 = 0;
                            while (h11 != null) {
                                i35++;
                                h11 = h11.c;
                            }
                            i33 += i35;
                        }
                        x10.d(i33);
                        if (c0805Rp.h != 0) {
                            str14 = str21;
                            x10.d(c0805Rp.c.a(str14)).c(i50).d(c0805Rp.h);
                        } else {
                            str14 = str21;
                        }
                        str21 = str14;
                        H4.a(c0805Rp.c, c0805Rp.d, c0805Rp.g, x10);
                        x17 = x10;
                        L2.a(c0805Rp.c, c0805Rp.i, c0805Rp.j, c0805Rp.k, c0805Rp.l, x17);
                        h12 = c0805Rp.m;
                        if (h12 != null) {
                            ag8 = c0805Rp.c;
                            C2611sd c2611sd15 = ag8.a;
                            while (h12 != null) {
                                X7 x7A10 = h12.a();
                                x17.d(ag8.a(h12.a)).c(x7A10.b);
                                x17.a(x7A10.a, 0, x7A10.b);
                                h12 = h12.c;
                                ag8 = ag8;
                                i10 = i10;
                            }
                        }
                        c0805Rp = (C0805Rp) c0805Rp.b;
                        x10 = x17;
                        i10 = i10;
                        i50 = 2;
                    }
                    int i514 = i10;
                    x11 = x10;
                    x11.d(i52);
                    yo = this.m;
                    z = false;
                    z2 = false;
                    while (yo != null) {
                        if (yo.t > 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z13 = z | z3;
                        z4 = z2 | yo.X;
                        if (yo.c.c < 49) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (z5) {
                            i20 = 4096;
                        } else {
                            i20 = 0;
                        }
                        x11.d(yo.d & (~i20)).d(yo.e).d(yo.g);
                        i21 = yo.Z;
                        if (i21 != 0) {
                            x11.a(yo.c.b.b, i21, yo.a0);
                            str12 = str19;
                            str9 = str18;
                            z6 = z4;
                            str11 = str7;
                            str10 = str8;
                            str20 = str20;
                        } else {
                            if (yo.k.b > 0) {
                                i22 = 1;
                            } else {
                                i22 = 0;
                            }
                            if (yo.x > 0) {
                                i22++;
                            }
                            i23 = yo.d;
                            i24 = i22;
                            if ((i23 & 4096) == 0) {
                                i25 = i24;
                            } else {
                                i25 = i24;
                            }
                            if (yo.z != 0) {
                                i25++;
                            }
                            if ((i23 & 131072) != 0) {
                                i25++;
                            }
                            if (yo.A != null) {
                                i25++;
                            }
                            if (yo.B != null) {
                                i25++;
                            }
                            if (yo.D != null) {
                                i25++;
                            }
                            if (yo.F != null) {
                                i25++;
                            }
                            if (yo.G != null) {
                                i25++;
                            }
                            if (yo.H != null) {
                                i25++;
                            }
                            if (yo.I != null) {
                                i25++;
                            }
                            if (yo.K != null) {
                                i25++;
                            }
                            h9 = yo.L;
                            if (h9 != null) {
                                i31 = 0;
                                while (h9 != null) {
                                    i31++;
                                    h9 = h9.c;
                                }
                                i25 += i31;
                            }
                            x11.d(i25);
                            i26 = yo.k.b;
                            if (i26 > 0) {
                                int i515 = i26 + 10;
                                i28 = 0;
                                while (c0523Gs != null) {
                                    i28++;
                                }
                                iB = (i28 * 8) + 2 + i515;
                                x13 = yo.u;
                                if (x13 != null) {
                                    iB += x13.b + 8;
                                    i29 = 1;
                                } else {
                                    i29 = 0;
                                }
                                x14 = yo.o;
                                if (x14 != null) {
                                    iB += x14.b + 8;
                                    i29++;
                                }
                                x15 = yo.q;
                                if (x15 != null) {
                                    iB += x15.b + 8;
                                    i29++;
                                }
                                x16 = yo.s;
                                if (x16 != null) {
                                    iB += x16.b + 8;
                                    i29++;
                                }
                                l6 = yo.v;
                                if (l6 != null) {
                                    iB += l6.b(str20);
                                    i29++;
                                }
                                l7 = yo.w;
                                if (l7 != null) {
                                    iB += l7.b(str19);
                                    i29++;
                                }
                                X7 x7C5 = x11.d(yo.c.a(str18)).c(iB).d(yo.i).d(yo.j).c(yo.k.b);
                                X7 x2110 = yo.k;
                                str9 = str18;
                                z6 = z4;
                                x7C5.a(x2110.a, 0, x2110.b);
                                c0523Gs2 = yo.l;
                                i30 = 0;
                                while (c0523Gs3 != null) {
                                    i30++;
                                }
                                x11.d(i30);
                                while (c0523Gs2 != null) {
                                    x11.d(c0523Gs2.a.e).d(c0523Gs2.b.e).d(c0523Gs2.c.e).d(c0523Gs2.d);
                                    c0523Gs2 = c0523Gs2.f;
                                }
                                x11.d(i29);
                                if (yo.u != null) {
                                    ag7 = yo.c;
                                    if (ag7.c >= 50) {
                                        str13 = "StackMapTable";
                                    } else {
                                        str13 = "StackMap";
                                    }
                                    X7 x7D110 = x11.d(ag7.a(str13)).c(yo.u.b + 2).d(yo.t);
                                    X7 x2111 = yo.u;
                                    x7D110.a(x2111.a, 0, x2111.b);
                                }
                                if (yo.o != null) {
                                    X7 x7D111 = x11.d(yo.c.a("LineNumberTable")).c(yo.o.b + 2).d(yo.n);
                                    X7 x2112 = yo.o;
                                    x7D111.a(x2112.a, 0, x2112.b);
                                }
                                if (yo.q != null) {
                                    X7 x7D112 = x11.d(yo.c.a("LocalVariableTable")).c(yo.q.b + 2).d(yo.p);
                                    X7 x2113 = yo.q;
                                    x7D112.a(x2113.a, 0, x2113.b);
                                }
                                if (yo.s != null) {
                                    X7 x7D113 = x11.d(yo.c.a("LocalVariableTypeTable")).c(yo.s.b + 2).d(yo.r);
                                    X7 x2114 = yo.s;
                                    x7D113.a(x2114.a, 0, x2114.b);
                                }
                                l8 = yo.v;
                                if (l8 != null) {
                                    l8.a(yo.c.a(str20), x11);
                                }
                                l9 = yo.w;
                                if (l9 != null) {
                                    l9.a(yo.c.a(str19), x11);
                                }
                            } else {
                                str9 = str18;
                                z6 = z4;
                            }
                            if (yo.x > 0) {
                                x11.d(yo.c.a("Exceptions")).c((yo.x * 2) + 2).d(yo.x);
                                while (i27 < r7) {
                                    x11.d(i58);
                                }
                            }
                            H4.a(yo.c, yo.d, yo.z, x11);
                            L2.a(yo.c, yo.A, yo.B, yo.G, yo.H, x11);
                            if (yo.D != null) {
                                int iA7 = yo.c.a("RuntimeVisibleParameterAnnotations");
                                l2Arr2 = yo.D;
                                length2 = yo.C;
                                if (length2 == 0) {
                                    length2 = l2Arr2.length;
                                }
                                L2.a(iA7, l2Arr2, length2, x11);
                            }
                            if (yo.F != null) {
                                int iA8 = yo.c.a("RuntimeInvisibleParameterAnnotations");
                                l2Arr = yo.F;
                                length = yo.E;
                                if (length == 0) {
                                    length = l2Arr.length;
                                }
                                L2.a(iA8, l2Arr, length, x11);
                            }
                            if (yo.I != null) {
                                str10 = str8;
                                X7 x7C6 = x11.d(yo.c.a(str10)).c(yo.I.b);
                                X7 x2115 = yo.I;
                                x7C6.a(x2115.a, 0, x2115.b);
                            } else {
                                str10 = str8;
                            }
                            if (yo.K != null) {
                                str11 = str7;
                                X7 x7B3 = x11.d(yo.c.a(str11)).c(yo.K.b + 1).b(yo.J);
                                X7 x2116 = yo.K;
                                x7B3.a(x2116.a, 0, x2116.b);
                            } else {
                                str11 = str7;
                            }
                            h10 = yo.L;
                            if (h10 != null) {
                                ag6 = yo.c;
                                C2611sd c2611sd16 = ag6.a;
                                while (h10 != null) {
                                    X7 x7A11 = h10.a();
                                    x11.d(ag6.a(h10.a)).c(x7A11.b);
                                    x11.a(x7A11.a, 0, x7A11.b);
                                    h10 = h10.c;
                                    str19 = str19;
                                }
                            }
                            str12 = str19;
                        }
                        yo = (YO) yo.b;
                        str8 = str10;
                        z = z13;
                        str18 = str9;
                        z2 = z6;
                        str20 = str20;
                        str19 = str12;
                        str7 = str11;
                    }
                    x11.d(i514);
                    if (this.p != null) {
                        X7 x7D114 = x11.d(this.e.a(str5)).c(this.p.b + 2).d(this.o);
                        X7 x2117 = this.p;
                        x7D114.a(x2117.a, 0, x2117.b);
                    }
                    if (this.q != 0) {
                        x11.d(this.e.a(str2)).c(4).d(this.q).d(this.r);
                    }
                    if ((this.f & 4096) != 0) {
                        x11.d(this.e.a("Synthetic")).c(0);
                    }
                    if (this.s != 0) {
                        i16 = 2;
                        x11.d(this.e.a("Signature")).c(2).d(this.s);
                    } else {
                        i16 = 2;
                    }
                    if (this.t != 0) {
                        x11.d(this.e.a("SourceFile")).c(i16).d(this.t);
                    }
                    x12 = this.u;
                    if (x12 != null) {
                        int i516 = x12.b;
                        i17 = 0;
                        x11.d(this.e.a("SourceDebugExtension")).c(i516).a(this.u.a, 0, i516);
                    } else {
                        i17 = 0;
                    }
                    if ((this.f & 131072) != 0) {
                        x11.d(this.e.a("Deprecated")).c(i17);
                    }
                    L2.a(this.e, this.v, this.w, this.x, this.y, x11);
                    ag3 = this.e;
                    if (ag3.j != null) {
                        X7 x7D115 = x11.d(ag3.a("BootstrapMethods")).c(ag3.j.b + 2).d(ag3.i);
                        X7 x2118 = ag3.j;
                        x7D115.a(x2118.a, 0, x2118.b);
                    }
                    c2253oP2 = this.z;
                    if (c2253oP2 != null) {
                        c2253oP2.a(x11);
                    }
                    if (this.A != 0) {
                        x11.d(this.e.a(str6)).c(2).d(this.A);
                    }
                    if (this.C != null) {
                        X7 x7D116 = x11.d(this.e.a("NestMembers")).c(this.C.b + 2).d(this.B);
                        X7 x2119 = this.C;
                        x7D116.a(x2119.a, 0, x2119.b);
                    }
                    if (this.E != null) {
                        X7 x7D117 = x11.d(this.e.a(str4)).c(this.E.b + 2).d(this.D);
                        X7 x32 = this.E;
                        x7D117.a(x32.a, 0, x32.b);
                    }
                    if ((this.f & 65536) == 0) {
                        x11.d(this.e.a("Record")).c(i6 + 2).d(i13);
                        while (c2052m21 != null) {
                            x11.d(c2052m21.c).d(c2052m21.d);
                            if (c2052m21.e != 0) {
                                i18 = 1;
                            } else {
                                i18 = 0;
                            }
                            if (c2052m21.f != null) {
                                i18++;
                            }
                            if (c2052m21.g != null) {
                                i18++;
                            }
                            if (c2052m21.h != null) {
                                i18++;
                            }
                            if (c2052m21.i != null) {
                                i18++;
                            }
                            h6 = c2052m21.j;
                            if (h6 != null) {
                                i19 = 0;
                                while (h6 != null) {
                                    i19++;
                                    h6 = h6.c;
                                }
                                i18 += i19;
                            }
                            x11.d(i18);
                            H4.a(c2052m21.b, 0, c2052m21.e, x11);
                            L2.a(c2052m21.b, c2052m21.f, c2052m21.g, c2052m21.h, c2052m21.i, x11);
                            h7 = c2052m21.j;
                            if (h7 != null) {
                                ag4 = c2052m21.b;
                                C2611sd c2611sd17 = ag4.a;
                                while (h7 != null) {
                                    X7 x7A12 = h7.a();
                                    x11.d(ag4.a(h7.a)).c(x7A12.b);
                                    x11.a(x7A12.a, 0, x7A12.b);
                                    h7 = h7.c;
                                }
                            }
                        }
                    } else {
                        x11.d(this.e.a("Record")).c(i6 + 2).d(i13);
                        while (c2052m21 != null) {
                            x11.d(c2052m21.c).d(c2052m21.d);
                            if (c2052m21.e != 0) {
                                i18 = 1;
                            } else {
                                i18 = 0;
                            }
                            if (c2052m21.f != null) {
                                i18++;
                            }
                            if (c2052m21.g != null) {
                                i18++;
                            }
                            if (c2052m21.h != null) {
                                i18++;
                            }
                            if (c2052m21.i != null) {
                                i18++;
                            }
                            h6 = c2052m21.j;
                            if (h6 != null) {
                                i19 = 0;
                                while (h6 != null) {
                                    i19++;
                                    h6 = h6.c;
                                }
                                i18 += i19;
                            }
                            x11.d(i18);
                            H4.a(c2052m21.b, 0, c2052m21.e, x11);
                            L2.a(c2052m21.b, c2052m21.f, c2052m21.g, c2052m21.h, c2052m21.i, x11);
                            h7 = c2052m21.j;
                            if (h7 != null) {
                                ag4 = c2052m21.b;
                                C2611sd c2611sd18 = ag4.a;
                                while (h7 != null) {
                                    X7 x7A13 = h7.a();
                                    x11.d(ag4.a(h7.a)).c(x7A13.b);
                                    x11.a(x7A13.a, 0, x7A13.b);
                                    h7 = h7.c;
                                }
                            }
                        }
                    }
                    h8 = this.H;
                    if (h8 != null) {
                        ag5 = this.e;
                        C2611sd c2611sd19 = ag5.a;
                        while (h8 != null) {
                            X7 x7A14 = h8.a();
                            x11.d(ag5.a(h8.a)).c(x7A14.b);
                            x11.a(x7A14.a, 0, x7A14.b);
                            h8 = h8.c;
                        }
                    }
                    bArr = x11.a;
                    if (z2) {
                        return a(bArr, z);
                    }
                    return bArr;
                }
                str4 = "PermittedSubclasses";
                c2052m20 = this.F;
                i6 = 0;
                i7 = 0;
                while (c2052m20 != null) {
                    i7++;
                    String str27 = str3;
                    String str28 = str22;
                    String str29 = str16;
                    String str210 = str17;
                    iA = L2.a(c2052m20.f, c2052m20.g, c2052m20.h, c2052m20.i) + H4.a(c2052m20.b, 0, c2052m20.e) + 6;
                    h4 = c2052m20.j;
                    if (h4 != null) {
                        ag1 = c2052m20.b;
                        C2611sd c2611sd20 = ag1.a;
                        i9 = 0;
                        while (h4 != null) {
                            ag1.a(h4.a);
                            i9 += h4.a().b + 6;
                            h4 = h4.c;
                            iA = iA;
                        }
                        iA = i9 + iA;
                    }
                    i6 += iA;
                    c2052m20 = (C2052m20) c2052m20.a;
                    str3 = str27;
                    str22 = str28;
                    str16 = str29;
                    str17 = str210;
                }
                str5 = str22;
                str6 = str3;
                str7 = str16;
                str8 = str17;
                i4++;
                iB3 = i6 + 8 + iB3;
                this.e.a("Record");
                i8 = i7;
                h5 = this.H;
                if (h5 != null) {
                    i36 = 0;
                    while (h5 != null) {
                        i36++;
                        h5 = h5.c;
                    }
                    i4 = i36 + i4;
                    h13 = this.H;
                    ag9 = this.e;
                    h13.getClass();
                    C2611sd c2611sd110 = ag9.a;
                    i37 = 0;
                    while (h13 != null) {
                        ag9.a(h13.a);
                        i37 += h13.a().b + 6;
                        h13 = h13.c;
                    }
                    iB3 = i37 + iB3;
                }
                i10 = i4;
                ag2 = this.e;
                i11 = iB3 + ag2.h.b;
                i12 = ag2.g;
                if (i12 <= 65535) {
                    throw new C2184nd(i12, ag2.d);
                }
                x10 = new X7(i11);
                x10.c(-889275714).c(this.d);
                Ag0 ag15 = this.e;
                X7 x7D118 = x10.d(ag15.g);
                X7 x112 = ag15.h;
                i13 = i8;
                x7D118.a(x112.a, 0, x112.b);
                if ((this.d & 65535) < 49) {
                    i14 = 4096;
                } else {
                    i14 = 0;
                }
                x10.d((~i14) & this.f).d(this.g).d(this.h);
                x10.d(this.i);
                while (i15 < this.i) {
                    x10.d(this.j[i15]);
                }
                x10.d(i53);
                c0805Rp = this.k;
                while (c0805Rp != null) {
                    if (c0805Rp.c.c < 49) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    if (z7) {
                        i32 = 4096;
                    } else {
                        i32 = 0;
                    }
                    x10.d((~i32) & c0805Rp.d).d(c0805Rp.e).d(c0805Rp.f);
                    if (c0805Rp.h != 0) {
                        i33 = 1;
                    } else {
                        i33 = 0;
                    }
                    i34 = c0805Rp.d;
                    boolean z14 = z7;
                    if ((i34 & 4096) != 0) {
                        i33++;
                    }
                    if (c0805Rp.g != 0) {
                        i33++;
                    }
                    if ((i34 & 131072) != 0) {
                        i33++;
                    }
                    if (c0805Rp.i != null) {
                        i33++;
                    }
                    if (c0805Rp.j != null) {
                        i33++;
                    }
                    if (c0805Rp.k != null) {
                        i33++;
                    }
                    if (c0805Rp.l != null) {
                        i33++;
                    }
                    h11 = c0805Rp.m;
                    if (h11 != null) {
                        i35 = 0;
                        while (h11 != null) {
                            i35++;
                            h11 = h11.c;
                        }
                        i33 += i35;
                    }
                    x10.d(i33);
                    if (c0805Rp.h != 0) {
                        str14 = str21;
                        x10.d(c0805Rp.c.a(str14)).c(i50).d(c0805Rp.h);
                    } else {
                        str14 = str21;
                    }
                    str21 = str14;
                    H4.a(c0805Rp.c, c0805Rp.d, c0805Rp.g, x10);
                    x17 = x10;
                    L2.a(c0805Rp.c, c0805Rp.i, c0805Rp.j, c0805Rp.k, c0805Rp.l, x17);
                    h12 = c0805Rp.m;
                    if (h12 != null) {
                        ag8 = c0805Rp.c;
                        C2611sd c2611sd111 = ag8.a;
                        while (h12 != null) {
                            X7 x7A15 = h12.a();
                            x17.d(ag8.a(h12.a)).c(x7A15.b);
                            x17.a(x7A15.a, 0, x7A15.b);
                            h12 = h12.c;
                            ag8 = ag8;
                            i10 = i10;
                        }
                    }
                    c0805Rp = (C0805Rp) c0805Rp.b;
                    x10 = x17;
                    i10 = i10;
                    i50 = 2;
                }
                int i517 = i10;
                x11 = x10;
                x11.d(i52);
                yo = this.m;
                z = false;
                z2 = false;
                while (yo != null) {
                    if (yo.t > 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z15 = z | z3;
                    z4 = z2 | yo.X;
                    if (yo.c.c < 49) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (z5) {
                        i20 = 4096;
                    } else {
                        i20 = 0;
                    }
                    x11.d(yo.d & (~i20)).d(yo.e).d(yo.g);
                    i21 = yo.Z;
                    if (i21 != 0) {
                        x11.a(yo.c.b.b, i21, yo.a0);
                        str12 = str19;
                        str9 = str18;
                        z6 = z4;
                        str11 = str7;
                        str10 = str8;
                        str20 = str20;
                    } else {
                        if (yo.k.b > 0) {
                            i22 = 1;
                        } else {
                            i22 = 0;
                        }
                        if (yo.x > 0) {
                            i22++;
                        }
                        i23 = yo.d;
                        i24 = i22;
                        if ((i23 & 4096) == 0) {
                            i25 = i24;
                        } else {
                            i25 = i24;
                        }
                        if (yo.z != 0) {
                            i25++;
                        }
                        if ((i23 & 131072) != 0) {
                            i25++;
                        }
                        if (yo.A != null) {
                            i25++;
                        }
                        if (yo.B != null) {
                            i25++;
                        }
                        if (yo.D != null) {
                            i25++;
                        }
                        if (yo.F != null) {
                            i25++;
                        }
                        if (yo.G != null) {
                            i25++;
                        }
                        if (yo.H != null) {
                            i25++;
                        }
                        if (yo.I != null) {
                            i25++;
                        }
                        if (yo.K != null) {
                            i25++;
                        }
                        h9 = yo.L;
                        if (h9 != null) {
                            i31 = 0;
                            while (h9 != null) {
                                i31++;
                                h9 = h9.c;
                            }
                            i25 += i31;
                        }
                        x11.d(i25);
                        i26 = yo.k.b;
                        if (i26 > 0) {
                            int i518 = i26 + 10;
                            i28 = 0;
                            while (c0523Gs != null) {
                                i28++;
                            }
                            iB = (i28 * 8) + 2 + i518;
                            x13 = yo.u;
                            if (x13 != null) {
                                iB += x13.b + 8;
                                i29 = 1;
                            } else {
                                i29 = 0;
                            }
                            x14 = yo.o;
                            if (x14 != null) {
                                iB += x14.b + 8;
                                i29++;
                            }
                            x15 = yo.q;
                            if (x15 != null) {
                                iB += x15.b + 8;
                                i29++;
                            }
                            x16 = yo.s;
                            if (x16 != null) {
                                iB += x16.b + 8;
                                i29++;
                            }
                            l6 = yo.v;
                            if (l6 != null) {
                                iB += l6.b(str20);
                                i29++;
                            }
                            l7 = yo.w;
                            if (l7 != null) {
                                iB += l7.b(str19);
                                i29++;
                            }
                            X7 x7C7 = x11.d(yo.c.a(str18)).c(iB).d(yo.i).d(yo.j).c(yo.k.b);
                            X7 x21110 = yo.k;
                            str9 = str18;
                            z6 = z4;
                            x7C7.a(x21110.a, 0, x21110.b);
                            c0523Gs2 = yo.l;
                            i30 = 0;
                            while (c0523Gs3 != null) {
                                i30++;
                            }
                            x11.d(i30);
                            while (c0523Gs2 != null) {
                                x11.d(c0523Gs2.a.e).d(c0523Gs2.b.e).d(c0523Gs2.c.e).d(c0523Gs2.d);
                                c0523Gs2 = c0523Gs2.f;
                            }
                            x11.d(i29);
                            if (yo.u != null) {
                                ag7 = yo.c;
                                if (ag7.c >= 50) {
                                    str13 = "StackMapTable";
                                } else {
                                    str13 = "StackMap";
                                }
                                X7 x7D119 = x11.d(ag7.a(str13)).c(yo.u.b + 2).d(yo.t);
                                X7 x21111 = yo.u;
                                x7D119.a(x21111.a, 0, x21111.b);
                            }
                            if (yo.o != null) {
                                X7 x7D1110 = x11.d(yo.c.a("LineNumberTable")).c(yo.o.b + 2).d(yo.n);
                                X7 x21112 = yo.o;
                                x7D1110.a(x21112.a, 0, x21112.b);
                            }
                            if (yo.q != null) {
                                X7 x7D1111 = x11.d(yo.c.a("LocalVariableTable")).c(yo.q.b + 2).d(yo.p);
                                X7 x21113 = yo.q;
                                x7D1111.a(x21113.a, 0, x21113.b);
                            }
                            if (yo.s != null) {
                                X7 x7D1112 = x11.d(yo.c.a("LocalVariableTypeTable")).c(yo.s.b + 2).d(yo.r);
                                X7 x21114 = yo.s;
                                x7D1112.a(x21114.a, 0, x21114.b);
                            }
                            l8 = yo.v;
                            if (l8 != null) {
                                l8.a(yo.c.a(str20), x11);
                            }
                            l9 = yo.w;
                            if (l9 != null) {
                                l9.a(yo.c.a(str19), x11);
                            }
                        } else {
                            str9 = str18;
                            z6 = z4;
                        }
                        if (yo.x > 0) {
                            x11.d(yo.c.a("Exceptions")).c((yo.x * 2) + 2).d(yo.x);
                            while (i27 < r7) {
                                x11.d(i58);
                            }
                        }
                        H4.a(yo.c, yo.d, yo.z, x11);
                        L2.a(yo.c, yo.A, yo.B, yo.G, yo.H, x11);
                        if (yo.D != null) {
                            int iA9 = yo.c.a("RuntimeVisibleParameterAnnotations");
                            l2Arr2 = yo.D;
                            length2 = yo.C;
                            if (length2 == 0) {
                                length2 = l2Arr2.length;
                            }
                            L2.a(iA9, l2Arr2, length2, x11);
                        }
                        if (yo.F != null) {
                            int iA10 = yo.c.a("RuntimeInvisibleParameterAnnotations");
                            l2Arr = yo.F;
                            length = yo.E;
                            if (length == 0) {
                                length = l2Arr.length;
                            }
                            L2.a(iA10, l2Arr, length, x11);
                        }
                        if (yo.I != null) {
                            str10 = str8;
                            X7 x7C8 = x11.d(yo.c.a(str10)).c(yo.I.b);
                            X7 x21115 = yo.I;
                            x7C8.a(x21115.a, 0, x21115.b);
                        } else {
                            str10 = str8;
                        }
                        if (yo.K != null) {
                            str11 = str7;
                            X7 x7B4 = x11.d(yo.c.a(str11)).c(yo.K.b + 1).b(yo.J);
                            X7 x21116 = yo.K;
                            x7B4.a(x21116.a, 0, x21116.b);
                        } else {
                            str11 = str7;
                        }
                        h10 = yo.L;
                        if (h10 != null) {
                            ag6 = yo.c;
                            C2611sd c2611sd112 = ag6.a;
                            while (h10 != null) {
                                X7 x7A16 = h10.a();
                                x11.d(ag6.a(h10.a)).c(x7A16.b);
                                x11.a(x7A16.a, 0, x7A16.b);
                                h10 = h10.c;
                                str19 = str19;
                            }
                        }
                        str12 = str19;
                    }
                    yo = (YO) yo.b;
                    str8 = str10;
                    z = z15;
                    str18 = str9;
                    z2 = z6;
                    str20 = str20;
                    str19 = str12;
                    str7 = str11;
                }
                x11.d(i517);
                if (this.p != null) {
                    X7 x7D1113 = x11.d(this.e.a(str5)).c(this.p.b + 2).d(this.o);
                    X7 x21117 = this.p;
                    x7D1113.a(x21117.a, 0, x21117.b);
                }
                if (this.q != 0) {
                    x11.d(this.e.a(str2)).c(4).d(this.q).d(this.r);
                }
                if ((this.f & 4096) != 0) {
                    x11.d(this.e.a("Synthetic")).c(0);
                }
                if (this.s != 0) {
                    i16 = 2;
                    x11.d(this.e.a("Signature")).c(2).d(this.s);
                } else {
                    i16 = 2;
                }
                if (this.t != 0) {
                    x11.d(this.e.a("SourceFile")).c(i16).d(this.t);
                }
                x12 = this.u;
                if (x12 != null) {
                    int i519 = x12.b;
                    i17 = 0;
                    x11.d(this.e.a("SourceDebugExtension")).c(i519).a(this.u.a, 0, i519);
                } else {
                    i17 = 0;
                }
                if ((this.f & 131072) != 0) {
                    x11.d(this.e.a("Deprecated")).c(i17);
                }
                L2.a(this.e, this.v, this.w, this.x, this.y, x11);
                ag3 = this.e;
                if (ag3.j != null) {
                    X7 x7D1114 = x11.d(ag3.a("BootstrapMethods")).c(ag3.j.b + 2).d(ag3.i);
                    X7 x21118 = ag3.j;
                    x7D1114.a(x21118.a, 0, x21118.b);
                }
                c2253oP2 = this.z;
                if (c2253oP2 != null) {
                    c2253oP2.a(x11);
                }
                if (this.A != 0) {
                    x11.d(this.e.a(str6)).c(2).d(this.A);
                }
                if (this.C != null) {
                    X7 x7D1115 = x11.d(this.e.a("NestMembers")).c(this.C.b + 2).d(this.B);
                    X7 x21119 = this.C;
                    x7D1115.a(x21119.a, 0, x21119.b);
                }
                if (this.E != null) {
                    X7 x7D1116 = x11.d(this.e.a(str4)).c(this.E.b + 2).d(this.D);
                    X7 x33 = this.E;
                    x7D1116.a(x33.a, 0, x33.b);
                }
                if ((this.f & 65536) == 0) {
                    x11.d(this.e.a("Record")).c(i6 + 2).d(i13);
                    while (c2052m21 != null) {
                        x11.d(c2052m21.c).d(c2052m21.d);
                        if (c2052m21.e != 0) {
                            i18 = 1;
                        } else {
                            i18 = 0;
                        }
                        if (c2052m21.f != null) {
                            i18++;
                        }
                        if (c2052m21.g != null) {
                            i18++;
                        }
                        if (c2052m21.h != null) {
                            i18++;
                        }
                        if (c2052m21.i != null) {
                            i18++;
                        }
                        h6 = c2052m21.j;
                        if (h6 != null) {
                            i19 = 0;
                            while (h6 != null) {
                                i19++;
                                h6 = h6.c;
                            }
                            i18 += i19;
                        }
                        x11.d(i18);
                        H4.a(c2052m21.b, 0, c2052m21.e, x11);
                        L2.a(c2052m21.b, c2052m21.f, c2052m21.g, c2052m21.h, c2052m21.i, x11);
                        h7 = c2052m21.j;
                        if (h7 != null) {
                            ag4 = c2052m21.b;
                            C2611sd c2611sd113 = ag4.a;
                            while (h7 != null) {
                                X7 x7A17 = h7.a();
                                x11.d(ag4.a(h7.a)).c(x7A17.b);
                                x11.a(x7A17.a, 0, x7A17.b);
                                h7 = h7.c;
                            }
                        }
                    }
                } else {
                    x11.d(this.e.a("Record")).c(i6 + 2).d(i13);
                    while (c2052m21 != null) {
                        x11.d(c2052m21.c).d(c2052m21.d);
                        if (c2052m21.e != 0) {
                            i18 = 1;
                        } else {
                            i18 = 0;
                        }
                        if (c2052m21.f != null) {
                            i18++;
                        }
                        if (c2052m21.g != null) {
                            i18++;
                        }
                        if (c2052m21.h != null) {
                            i18++;
                        }
                        if (c2052m21.i != null) {
                            i18++;
                        }
                        h6 = c2052m21.j;
                        if (h6 != null) {
                            i19 = 0;
                            while (h6 != null) {
                                i19++;
                                h6 = h6.c;
                            }
                            i18 += i19;
                        }
                        x11.d(i18);
                        H4.a(c2052m21.b, 0, c2052m21.e, x11);
                        L2.a(c2052m21.b, c2052m21.f, c2052m21.g, c2052m21.h, c2052m21.i, x11);
                        h7 = c2052m21.j;
                        if (h7 != null) {
                            ag4 = c2052m21.b;
                            C2611sd c2611sd114 = ag4.a;
                            while (h7 != null) {
                                X7 x7A18 = h7.a();
                                x11.d(ag4.a(h7.a)).c(x7A18.b);
                                x11.a(x7A18.a, 0, x7A18.b);
                                h7 = h7.c;
                            }
                        }
                    }
                }
                h8 = this.H;
                if (h8 != null) {
                    ag5 = this.e;
                    C2611sd c2611sd115 = ag5.a;
                    while (h8 != null) {
                        X7 x7A19 = h8.a();
                        x11.d(ag5.a(h8.a)).c(x7A19.b);
                        x11.a(x7A19.a, 0, x7A19.b);
                        h8 = h8.c;
                    }
                }
                bArr = x11.a;
                if (z2) {
                    return a(bArr, z);
                }
                return bArr;
            }
            int i60 = i52 + 1;
            if (yo2.Z != 0) {
                i43 = yo2.a0 + 6;
                i42 = i47;
                str15 = str;
            } else {
                int i61 = yo2.k.b;
                if (i61 > 0) {
                    str15 = str;
                    Ag0 ag16 = yo2.c;
                    i42 = i47;
                    if (i61 > 65535) {
                        throw new VO(i61, ag16.d, yo2.f, yo2.h);
                    }
                    ag16.a("Code");
                    int i62 = yo2.k.b + 16;
                    int i63 = 0;
                    for (C0523Gs c0523Gs4 = yo2.l; c0523Gs4 != null; c0523Gs4 = c0523Gs4.f) {
                        i63++;
                    }
                    iB2 = (i63 * 8) + 2 + i62 + 8;
                    if (yo2.u != null) {
                        Ag0 ag17 = yo2.c;
                        ag17.a(ag17.c >= 50 ? "StackMapTable" : "StackMap");
                        iB2 += yo2.u.b + 8;
                    }
                    if (yo2.o != null) {
                        yo2.c.a("LineNumberTable");
                        iB2 += yo2.o.b + 8;
                    }
                    if (yo2.q != null) {
                        yo2.c.a("LocalVariableTable");
                        iB2 += yo2.q.b + 8;
                    }
                    if (yo2.s != null) {
                        yo2.c.a("LocalVariableTypeTable");
                        iB2 += yo2.s.b + 8;
                    }
                    L2 l10 = yo2.v;
                    if (l10 != null) {
                        iB2 += l10.b("RuntimeVisibleTypeAnnotations");
                    }
                    L2 l11 = yo2.w;
                    if (l11 != null) {
                        iB2 += l11.b("RuntimeInvisibleTypeAnnotations");
                    }
                } else {
                    i42 = i47;
                    str15 = str;
                    iB2 = 8;
                }
                if (yo2.x > 0) {
                    yo2.c.a("Exceptions");
                    iB2 += (yo2.x * 2) + 8;
                }
                int iA11 = L2.a(yo2.A, yo2.B, yo2.G, yo2.H) + H4.a(yo2.c, yo2.d, yo2.z) + iB2;
                L2[] l2Arr3 = yo2.D;
                if (l2Arr3 != null) {
                    int length3 = yo2.C;
                    if (length3 == 0) {
                        length3 = l2Arr3.length;
                    }
                    int iB4 = (length3 * 2) + 7;
                    for (int i64 = 0; i64 < length3; i64++) {
                        L2 l12 = l2Arr3[i64];
                        iB4 += l12 == null ? 0 : l12.b("RuntimeVisibleParameterAnnotations") - 8;
                    }
                    iA11 += iB4;
                }
                L2[] l2Arr4 = yo2.F;
                if (l2Arr4 != null) {
                    int length4 = yo2.E;
                    if (length4 == 0) {
                        length4 = l2Arr4.length;
                    }
                    int iB5 = (length4 * 2) + 7;
                    for (int i65 = 0; i65 < length4; i65++) {
                        L2 l13 = l2Arr4[i65];
                        iB5 += l13 == null ? 0 : l13.b("RuntimeInvisibleParameterAnnotations") - 8;
                    }
                    iA11 += iB5;
                }
                if (yo2.I != null) {
                    yo2.c.a("AnnotationDefault");
                    iA11 += yo2.I.b + 6;
                }
                if (yo2.K != null) {
                    yo2.c.a("MethodParameters");
                    i43 = yo2.K.b + 7 + iA11;
                } else {
                    i43 = iA11;
                }
                H4 h15 = yo2.L;
                if (h15 != null) {
                    Ag0 ag18 = yo2.c;
                    C2611sd c2611sd21 = ag18.a;
                    int i66 = 0;
                    while (h15 != null) {
                        ag18.a(h15.a);
                        i66 += h15.a().b + 6;
                        h15 = h15.c;
                    }
                    i43 += i66;
                }
            }
            i46 = i51 + i43;
            yo2 = (YO) yo2.b;
            i45 = i50;
            i49 = i60;
            str = str15;
            i47 = i42;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void c(String str) {
        if (this.E == null) {
            this.E = new X7();
        }
        this.D++;
        this.E.d(this.e.a(7, str).a);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.d = i;
        this.f = i2;
        Ag0 ag0 = this.e;
        int i3 = i & 65535;
        ag0.c = i3;
        ag0.d = str;
        this.g = ag0.a(7, str).a;
        if (str2 != null) {
            this.s = this.e.a(str2);
        }
        this.h = str3 == null ? 0 : this.e.a(7, str3).a;
        if (strArr.length > 0) {
            int length = strArr.length;
            this.i = length;
            this.j = new int[length];
            for (int i4 = 0; i4 < this.i; i4++) {
                this.j[i4] = this.e.a(7, strArr[i4]).a;
            }
        }
        if (this.I != 1 || i3 < 51) {
            return;
        }
        this.I = 2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(String str, String str2) {
        if (str != null) {
            this.t = this.e.a(str);
        }
        if (str2 != null) {
            this.u = new X7().a(0, Integer.MAX_VALUE, str2);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC2167nP a(int i, String str, String str2) {
        Ag0 ag0 = this.e;
        C2253oP c2253oP = new C2253oP(ag0, ag0.a(19, str).a, i, str2 == null ? 0 : this.e.a(str2));
        this.z = c2253oP;
        return c2253oP;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(String str) {
        this.A = this.e.a(7, str).a;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(String str, String str2, String str3) {
        this.q = this.e.a(7, str).a;
        if (str2 == null || str3 == null) {
            return;
        }
        this.r = this.e.a(str2, str3);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final J2 a(String str, boolean z) {
        Ag0 ag0 = this.e;
        if (z) {
            L2 l2A = L2.a(ag0, str, this.v);
            this.v = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, str, this.w);
        this.w = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        Ag0 ag0 = this.e;
        if (z) {
            L2 l2A = L2.a(ag0, i, c3052xj0, str, this.x);
            this.x = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, i, c3052xj0, str, this.y);
        this.y = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(H4 h4) {
        h4.c = this.H;
        this.H = h4;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(int i, String str, String str2, String str3) {
        if (this.p == null) {
            this.p = new X7();
        }
        C3130yg0 c3130yg0A = this.e.a(7, str);
        if (c3130yg0A.g == 0) {
            this.o++;
            this.p.d(c3130yg0A.a);
            this.p.d(str2 == null ? 0 : this.e.a(7, str2).a);
            this.p.d(str3 != null ? this.e.a(str3) : 0);
            this.p.d(i);
            c3130yg0A.g = this.o;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC0779Qp a(int i, String str, String str2, String str3, Object obj) {
        C0805Rp c0805Rp = new C0805Rp(this.e, i, str, str2, str3, obj);
        if (this.k == null) {
            this.k = c0805Rp;
        } else {
            this.l.b = c0805Rp;
        }
        this.l = c0805Rp;
        return c0805Rp;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final XO a(int i, String str, String str2, String str3, String[] strArr) {
        YO yo = new YO(this.e, i, str, str2, str3, strArr, this.I);
        if (this.m == null) {
            this.m = yo;
        } else {
            this.n.b = yo;
        }
        this.n = yo;
        return yo;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a() {
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC1966l20 b(String str, String str2, String str3) {
        C2052m20 c2052m20 = new C2052m20(this.e, str, str2, str3);
        if (this.F == null) {
            this.F = c2052m20;
        } else {
            this.G.a = c2052m20;
        }
        this.G = c2052m20;
        return c2052m20;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void b(String str) {
        if (this.C == null) {
            this.C = new X7();
        }
        this.B++;
        this.C.d(this.e.a(7, str).a);
    }
}
