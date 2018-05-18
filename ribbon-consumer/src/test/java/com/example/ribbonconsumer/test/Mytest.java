package com.example.ribbonconsumer.test;

public class Mytest {
    public static void main(String[] args) {
//        Mytest m = new Mytest();
//        int aaa = m.aaa();
//        System.out.println(aaa);
        Mytest m = new Mytest();
        try {
            boolean aaa = m.aaa();
            System.out.println("main"+aaa);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public int aaa(){
//        //person p = new person("liu");
//        int i =0;
//        try {
//            throw new Exception("sdf");
//        }catch (Exception e){
//            //p = new person("dong");
//            i=1;
//            return i;
//        }finally {
//            //p = new person("ling");
//            i=2;
//            return i;
//        }
//    }

    public boolean aaa() throws Exception{
        boolean ret = true;
        try{
            bbb();
        }catch (Exception e){
            System.out.println("aaaa");
            ret = false;
            throw e;
        }finally {
            System.out.println("finally aaaa");
            return ret;
        }

    }

    public boolean bbb(){
        boolean ret = true;
        try{
            ccc();
        }catch (Exception e){
            System.out.println("bbbb");
            ret = false;
            return false;
            //throw e;
        }finally {
            System.out.println("finally bbb");
            return ret;
        }

    }

    public void ccc() {
        boolean ret = true;
        try{
            int i = 4/0;
        }catch (Exception e){
            System.out.println("ccc");
            ret = false;
            throw e;
        }finally {
            System.out.println("finally ccc");
            //return ret;
        }

    }

    class person{
        String name;

        public person(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return "person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
