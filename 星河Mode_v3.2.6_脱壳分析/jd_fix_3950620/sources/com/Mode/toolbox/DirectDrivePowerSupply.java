package com.Mode.toolbox;

import android.service.quicksettings.TileService;

/* loaded from: /workspace/xh/fix_3950620.dex */
public class DirectDrivePowerSupply extends TileService {

    /* renamed from: short, reason: not valid java name */
    private static final short[] f23short = null;

    private native void updateTile(boolean z);

    /* renamed from: ۟ۤۢۦۣ, reason: not valid java name and contains not printable characters */
    public static native void m85(Object obj, boolean z);

    /* renamed from: ۟ۧۦۥ۠, reason: not valid java name and contains not printable characters */
    public static native short[] m86();

    @Override // android.service.quicksettings.TileService
    public native void onClick();

    @Override // android.service.quicksettings.TileService
    public native void onStartListening();
}
