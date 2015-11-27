package com.test;

/**
 * Created by zhangfan on 2015/3/19.
 */
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
public class ClipBo {
    public static void main(String[] args) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = new StringSelection("123456");
        cb.setContents(t, null);

        //以上代码是把123456复制了一下。


    }
}

