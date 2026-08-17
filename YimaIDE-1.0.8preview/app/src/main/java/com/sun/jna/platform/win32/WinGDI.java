package com.sun.jna.platform.win32;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface WinGDI {
    public static final int ALTERNATE = 1;
    public static final int BI_BITFIELDS = 3;
    public static final int BI_JPEG = 4;
    public static final int BI_PNG = 5;
    public static final int BI_RGB = 0;
    public static final int BI_RLE4 = 2;
    public static final int BI_RLE8 = 1;
    public static final int COMPLEXREGION = 3;
    public static final int DIB_PAL_COLORS = 1;
    public static final int DIB_RGB_COLORS = 0;
    public static final int ERROR = 0;
    public static final WinNT.HANDLE HGDI_ERROR = new WinNT.HANDLE(Pointer.createConstant(-1));
    public static final int NULLREGION = 1;
    public static final int PFD_DOUBLEBUFFER = 1;
    public static final int PFD_DRAW_TO_BITMAP = 8;
    public static final int PFD_DRAW_TO_WINDOW = 4;
    public static final int PFD_GENERIC_ACCELERATED = 4096;
    public static final int PFD_GENERIC_FORMAT = 64;
    public static final int PFD_MAIN_PLANE = 0;
    public static final int PFD_NEED_PALETTE = 128;
    public static final int PFD_NEED_SYSTEM_PALETTE = 256;
    public static final int PFD_OVERLAY_PLANE = 1;
    public static final int PFD_STEREO = 2;
    public static final int PFD_SUPPORT_DIRECTDRAW = 8192;
    public static final int PFD_SUPPORT_GDI = 16;
    public static final int PFD_SUPPORT_OPENGL = 32;
    public static final int PFD_SWAP_COPY = 1024;
    public static final int PFD_SWAP_EXCHANGE = 512;
    public static final int PFD_SWAP_LAYER_BUFFERS = 2048;
    public static final int PFD_TYPE_COLORINDEX = 1;
    public static final int PFD_TYPE_RGBA = 0;
    public static final int PFD_UNDERLAY_PLANE = -1;
    public static final int RDH_RECTANGLES = 1;
    public static final int RGN_AND = 1;
    public static final int RGN_COPY = 5;
    public static final int RGN_DIFF = 4;
    public static final int RGN_OR = 2;
    public static final int RGN_XOR = 3;
    public static final int SIMPLEREGION = 2;
    public static final int WINDING = 2;

    @Structure.FieldOrder({"bmType", "bmWidth", "bmHeight", "bmWidthBytes", "bmPlanes", "bmBitsPixel", "bmBits"})
    public static class BITMAP extends Structure {
        public Pointer bmBits;
        public short bmBitsPixel;
        public NativeLong bmHeight;
        public short bmPlanes;
        public NativeLong bmType;
        public NativeLong bmWidth;
        public NativeLong bmWidthBytes;
    }

    @Structure.FieldOrder({"biSize", "biWidth", "biHeight", "biPlanes", "biBitCount", "biCompression", "biSizeImage", "biXPelsPerMeter", "biYPelsPerMeter", "biClrUsed", "biClrImportant"})
    public static class BITMAPINFOHEADER extends Structure {
        public short biBitCount;
        public int biClrImportant;
        public int biClrUsed;
        public int biCompression;
        public int biHeight;
        public short biPlanes;
        public int biSize = size();
        public int biSizeImage;
        public int biWidth;
        public int biXPelsPerMeter;
        public int biYPelsPerMeter;
    }

    @Structure.FieldOrder({"dsBm", "dsBmih", "dsBitfields", "dshSection", "dsOffset"})
    public static class DIBSECTION extends Structure {
        public int[] dsBitfields = new int[3];
        public BITMAP dsBm;
        public BITMAPINFOHEADER dsBmih;
        public int dsOffset;
        public WinNT.HANDLE dshSection;
    }

    @Structure.FieldOrder({"fIcon", "xHotspot", "yHotspot", "hbmMask", "hbmColor"})
    public static class ICONINFO extends Structure {
        public boolean fIcon;
        public WinDef.HBITMAP hbmColor;
        public WinDef.HBITMAP hbmMask;
        public int xHotspot;
        public int yHotspot;
    }

    @Structure.FieldOrder({"rgbBlue", "rgbGreen", "rgbRed", "rgbReserved"})
    public static class RGBQUAD extends Structure {
        public byte rgbBlue;
        public byte rgbGreen;
        public byte rgbRed;
        public byte rgbReserved = 0;
    }

    @Structure.FieldOrder({"dwSize", "iType", "nCount", "nRgnSize", "rcBound"})
    public static class RGNDATAHEADER extends Structure {
        public int dwSize = size();
        public int iType = 1;
        public int nCount;
        public int nRgnSize;
        public WinDef.RECT rcBound;
    }

    @Structure.FieldOrder({"nSize", "nVersion", "dwFlags", "iPixelType", "cColorBits", "cRedBits", "cRedShift", "cGreenBits", "cGreenShift", "cBlueBits", "cBlueShift", "cAlphaBits", "cAlphaShift", "cAccumBits", "cAccumRedBits", "cAccumGreenBits", "cAccumBlueBits", "cAccumAlphaBits", "cDepthBits", "cStencilBits", "cAuxBuffers", "iLayerType", "bReserved", "dwLayerMask", "dwVisibleMask", "dwDamageMask"})
    public static class PIXELFORMATDESCRIPTOR extends Structure {
        public byte bReserved;
        public byte cAccumAlphaBits;
        public byte cAccumBits;
        public byte cAccumBlueBits;
        public byte cAccumGreenBits;
        public byte cAccumRedBits;
        public byte cAlphaBits;
        public byte cAlphaShift;
        public byte cAuxBuffers;
        public byte cBlueBits;
        public byte cBlueShift;
        public byte cColorBits;
        public byte cDepthBits;
        public byte cGreenBits;
        public byte cGreenShift;
        public byte cRedBits;
        public byte cRedShift;
        public byte cStencilBits;
        public int dwDamageMask;
        public int dwFlags;
        public int dwLayerMask;
        public int dwVisibleMask;
        public byte iLayerType;
        public byte iPixelType;
        public short nSize;
        public short nVersion;

        public static class ByReference extends PIXELFORMATDESCRIPTOR implements Structure.ByReference {
        }

        public PIXELFORMATDESCRIPTOR() {
            this.nSize = (short) size();
        }

        public PIXELFORMATDESCRIPTOR(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"rdh", "Buffer"})
    public static class RGNDATA extends Structure {
        public byte[] Buffer;
        public RGNDATAHEADER rdh;

        public RGNDATA(int i) {
            this.Buffer = new byte[i];
            allocateMemory();
        }

        public RGNDATA() {
            this(1);
        }
    }

    @Structure.FieldOrder({"bmiHeader", "bmiColors"})
    public static class BITMAPINFO extends Structure {
        public RGBQUAD[] bmiColors;
        public BITMAPINFOHEADER bmiHeader;

        public BITMAPINFO(int i) {
            this.bmiHeader = new BITMAPINFOHEADER();
            this.bmiColors = new RGBQUAD[1];
            this.bmiColors = new RGBQUAD[i];
        }

        public BITMAPINFO() {
            this(1);
        }
    }
}
