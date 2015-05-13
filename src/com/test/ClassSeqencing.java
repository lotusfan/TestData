package com.test;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by zhangfan on 2015/4/15.
 */
public class ClassSeqencing {

    private int id;
    private Timestamp timestamp;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public static void main(String[] args) {
        List<ClassSeqencing> list = new ArrayList<ClassSeqencing>();
        for (int i = 0; i < 5; i++) {
            ClassSeqencing classSeqencing = new ClassSeqencing();
            classSeqencing.setId(i);
            classSeqencing.setTimestamp(new Timestamp(System.currentTimeMillis() + 1000));
            list.add(classSeqencing);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(list, new Comparator<ClassSeqencing>() {
            @Override
            public int compare(ClassSeqencing o1, ClassSeqencing o2) {

                System.out.println(o1.getTimestamp().getTime());
                System.out.println(o2.getTimestamp().getTime());
                System.out.println("-----------------");

               /* if (o1.getTimestamp().before(o2.getTimestamp())) {
                    System.out.println("before");
                    return -1;
                } else if (o1.getTimestamp().after(o2.getTimestamp())) {
                    System.out.println("after");
                    return 1;
                } else {
                    return 0;
                }
*/
                if(o2.getTimestamp().after(o1.getTimestamp())) return 1;
                return -1;
            }
        });
        for (ClassSeqencing seqencing : list) {
            System.out.println(seqencing.getId() + "----------" + seqencing.getTimestamp().getTime());
        }

    }

}
