package io.utils;

public class Rect {

    public int left;
    public int top;
    public int right;
    public int bottom;

    public Rect() {
        super();
    }

    public Rect(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public int width() {
        return right - left;
    }

    public int height() {
        return bottom - top;
    }

    public int centerX() {
        return (left + right) / 2;
    }

    public int centerY() {
        return (top + bottom) / 2;
    }

    public boolean contains(int x, int y) {
        if (x >= left && x <= right && y >= top && y <= bottom) {
            return true;
        }
        return false;
    }

    public boolean contains(int left, int right, int top, int bottom) {
        if (this.left <= left && this.right >= right && this.top <= top && this.bottom >= bottom) {
            return true;
        }
        return false;
    }

    public boolean contains(Rect rect) {
        if (rect.left >= left && rect.right <= right && rect.top >= top && rect.bottom <= bottom) {
            return true;
        }
        return false;
    }

    public void set(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public String toString() {
        return "left:"+left+"right:"+right+"top:"+top+"bottom:"+bottom;
    }
}
