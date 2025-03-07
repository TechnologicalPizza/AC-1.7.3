package io.github.ryuu.adventurecraft.overrides;

import org.lwjgl.opengl.GL11;

public class ps {
    private ib[] j;

    private tz[] k;

    private final int l;

    private final int m;

    public float a;

    public float b;

    public float c;

    public float d;

    public float e;

    public float f;

    private boolean n;

    private int o;

    public boolean g;

    public boolean h;

    public boolean i;

    private final int tWidth;

    private final int tHeight;

    public ps(int i, int j) {
        this(i, j, 64, 32);
    }

    public ps(int i, int j, int w, int h) {
        this.n = false;
        this.o = 0;
        this.g = false;
        this.h = true;
        this.i = false;
        this.l = i;
        this.m = j;
        this.tWidth = w;
        this.tHeight = h;
    }

    public void a(float f, float f1, float f2, int i, int j, int k) {
        a(f, f1, f2, i, j, k, 0.0F);
    }

    public void a(float f, float f1, float f2, int i, int j, int k, float f3) {
        this.j = new ib[8];
        this.k = new tz[6];
        float f4 = f + i;
        float f5 = f1 + j;
        float f6 = f2 + k;
        f -= f3;
        f1 -= f3;
        f2 -= f3;
        f4 += f3;
        f5 += f3;
        f6 += f3;
        if (this.g) {
            float f7 = f4;
            f4 = f;
            f = f7;
        }
        ib positiontexturevertex = new ib(f, f1, f2, 0.0F, 0.0F);
        ib positiontexturevertex1 = new ib(f4, f1, f2, 0.0F, 8.0F);
        ib positiontexturevertex2 = new ib(f4, f5, f2, 8.0F, 8.0F);
        ib positiontexturevertex3 = new ib(f, f5, f2, 8.0F, 0.0F);
        ib positiontexturevertex4 = new ib(f, f1, f6, 0.0F, 0.0F);
        ib positiontexturevertex5 = new ib(f4, f1, f6, 0.0F, 8.0F);
        ib positiontexturevertex6 = new ib(f4, f5, f6, 8.0F, 8.0F);
        ib positiontexturevertex7 = new ib(f, f5, f6, 8.0F, 0.0F);
        this.j[0] = positiontexturevertex;
        this.j[1] = positiontexturevertex1;
        this.j[2] = positiontexturevertex2;
        this.j[3] = positiontexturevertex3;
        this.j[4] = positiontexturevertex4;
        this.j[5] = positiontexturevertex5;
        this.j[6] = positiontexturevertex6;
        this.j[7] = positiontexturevertex7;
        this.k[0] = new tz(new ib[]{positiontexturevertex5, positiontexturevertex1, positiontexturevertex2, positiontexturevertex6}, this.l + k + i, this.m + k, this.l + k + i + k, this.m + k + j, this.tWidth, this.tHeight);
        this.k[1] = new tz(new ib[]{positiontexturevertex, positiontexturevertex4, positiontexturevertex7, positiontexturevertex3}, this.l + 0, this.m + k, this.l + k, this.m + k + j, this.tWidth, this.tHeight);
        this.k[2] = new tz(new ib[]{positiontexturevertex5, positiontexturevertex4, positiontexturevertex, positiontexturevertex1}, this.l + k, this.m + 0, this.l + k + i, this.m + k, this.tWidth, this.tHeight);
        this.k[3] = new tz(new ib[]{positiontexturevertex2, positiontexturevertex3, positiontexturevertex7, positiontexturevertex6}, this.l + k + i, this.m + 0, this.l + k + i + i, this.m + k, this.tWidth, this.tHeight);
        this.k[4] = new tz(new ib[]{positiontexturevertex1, positiontexturevertex, positiontexturevertex3, positiontexturevertex2}, this.l + k, this.m + k, this.l + k + i, this.m + k + j, this.tWidth, this.tHeight);
        this.k[5] = new tz(new ib[]{positiontexturevertex4, positiontexturevertex5, positiontexturevertex6, positiontexturevertex7}, this.l + k + i + k, this.m + k, this.l + k + i + k + i, this.m + k + j, this.tWidth, this.tHeight);
        if (this.g)
            for (int l = 0; l < this.k.length; l++)
                this.k[l].a();
    }

    public void addBoxInverted(float f, float f1, float f2, int i, int j, int k, float f3) {
        this.j = new ib[8];
        this.k = new tz[6];
        float f4 = f + i;
        float f5 = f1 + j;
        float f6 = f2 + k;
        f -= f3;
        f1 -= f3;
        f2 -= f3;
        f4 += f3;
        f5 += f3;
        f6 += f3;
        if (this.g) {
            float f7 = f4;
            f4 = f;
            f = f7;
        }
        ib positiontexturevertex = new ib(f, f1, f2, 0.0F, 0.0F);
        ib positiontexturevertex1 = new ib(f4, f1, f2, 0.0F, 8.0F);
        ib positiontexturevertex2 = new ib(f4, f5, f2, 8.0F, 8.0F);
        ib positiontexturevertex3 = new ib(f, f5, f2, 8.0F, 0.0F);
        ib positiontexturevertex4 = new ib(f, f1, f6, 0.0F, 0.0F);
        ib positiontexturevertex5 = new ib(f4, f1, f6, 0.0F, 8.0F);
        ib positiontexturevertex6 = new ib(f4, f5, f6, 8.0F, 8.0F);
        ib positiontexturevertex7 = new ib(f, f5, f6, 8.0F, 0.0F);
        this.j[0] = positiontexturevertex;
        this.j[1] = positiontexturevertex1;
        this.j[2] = positiontexturevertex2;
        this.j[3] = positiontexturevertex3;
        this.j[4] = positiontexturevertex4;
        this.j[5] = positiontexturevertex5;
        this.j[6] = positiontexturevertex6;
        this.j[7] = positiontexturevertex7;
        this.k[0] = new tz(new ib[]{positiontexturevertex5, positiontexturevertex1, positiontexturevertex2, positiontexturevertex6}, this.l + k + i + k, this.m + k + j, this.l + k + i + 0, this.m + k, this.tWidth, this.tHeight);
        this.k[1] = new tz(new ib[]{positiontexturevertex, positiontexturevertex4, positiontexturevertex7, positiontexturevertex3}, this.l + k, this.m + k + j, this.l + 0, this.m + k, this.tWidth, this.tHeight);
        this.k[2] = new tz(new ib[]{positiontexturevertex5, positiontexturevertex4, positiontexturevertex, positiontexturevertex1}, this.l + k + i + i, this.m + 0, this.l + k + i, this.m + k, this.tWidth, this.tHeight);
        this.k[3] = new tz(new ib[]{positiontexturevertex2, positiontexturevertex3, positiontexturevertex7, positiontexturevertex6}, this.l + k + i, this.m + 0, this.l + k, this.m + k, this.tWidth, this.tHeight);
        this.k[4] = new tz(new ib[]{positiontexturevertex1, positiontexturevertex, positiontexturevertex3, positiontexturevertex2}, this.l + k + i + k + i, this.m + k + j, this.l + k + i + k, this.m + k, this.tWidth, this.tHeight);
        this.k[5] = new tz(new ib[]{positiontexturevertex4, positiontexturevertex5, positiontexturevertex6, positiontexturevertex7}, this.l + k + i, this.m + k + j, this.l + k, this.m + k, this.tWidth, this.tHeight);
        if (this.g)
            for (int l = 0; l < this.k.length; l++)
                this.k[l].a();
    }

    public void a(float f, float f1, float f2) {
        this.a = f;
        this.b = f1;
        this.c = f2;
    }

    public void a(float f) {
        if (this.i)
            return;
        if (!this.h)
            return;
        if (!this.n)
            d(f);
        if (this.d != 0.0F || this.e != 0.0F || this.f != 0.0F) {
            GL11.glPushMatrix();
            GL11.glTranslatef(this.a * f, this.b * f, this.c * f);
            if (this.f != 0.0F)
                GL11.glRotatef(this.f * 57.29578F, 0.0F, 0.0F, 1.0F);
            if (this.e != 0.0F)
                GL11.glRotatef(this.e * 57.29578F, 0.0F, 1.0F, 0.0F);
            if (this.d != 0.0F)
                GL11.glRotatef(this.d * 57.29578F, 1.0F, 0.0F, 0.0F);
            GL11.glCallList(this.o);
            GL11.glPopMatrix();
        } else if (this.a != 0.0F || this.b != 0.0F || this.c != 0.0F) {
            GL11.glTranslatef(this.a * f, this.b * f, this.c * f);
            GL11.glCallList(this.o);
            GL11.glTranslatef(-this.a * f, -this.b * f, -this.c * f);
        } else {
            GL11.glCallList(this.o);
        }
    }

    public void b(float f) {
        if (this.i)
            return;
        if (!this.h)
            return;
        if (!this.n)
            d(f);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.a * f, this.b * f, this.c * f);
        if (this.e != 0.0F)
            GL11.glRotatef(this.e * 57.29578F, 0.0F, 1.0F, 0.0F);
        if (this.d != 0.0F)
            GL11.glRotatef(this.d * 57.29578F, 1.0F, 0.0F, 0.0F);
        if (this.f != 0.0F)
            GL11.glRotatef(this.f * 57.29578F, 0.0F, 0.0F, 1.0F);
        GL11.glCallList(this.o);
        GL11.glPopMatrix();
    }

    public void c(float f) {
        if (this.i)
            return;
        if (!this.h)
            return;
        if (!this.n)
            d(f);
        if (this.d != 0.0F || this.e != 0.0F || this.f != 0.0F) {
            GL11.glTranslatef(this.a * f, this.b * f, this.c * f);
            if (this.f != 0.0F)
                GL11.glRotatef(this.f * 57.29578F, 0.0F, 0.0F, 1.0F);
            if (this.e != 0.0F)
                GL11.glRotatef(this.e * 57.29578F, 0.0F, 1.0F, 0.0F);
            if (this.d != 0.0F)
                GL11.glRotatef(this.d * 57.29578F, 1.0F, 0.0F, 0.0F);
        } else if (this.a != 0.0F || this.b != 0.0F || this.c != 0.0F) {
            GL11.glTranslatef(this.a * f, this.b * f, this.c * f);
        }
    }

    private void d(float f) {
        this.o = ge.a(1);
        GL11.glNewList(this.o, 4864);
        nw tessellator = nw.a;
        for (int i = 0; i < this.k.length; i++)
            this.k[i].a(tessellator, f);
        GL11.glEndList();
        this.n = true;
    }
}
