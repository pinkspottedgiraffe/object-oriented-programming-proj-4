package app;

import java.util.concurrent.ThreadLocalRandom;

enum movement{
    IMPRESSIONISM,EXPRESSIONISM,NATURALISM;
}

enum condition{
    BAD,GOOD,EXCELLENT;
}

enum technique{
    OIL,AQUARELLE,TEMPERA;
}

enum material{
    IRON,STONE,WOOD;
}
public class App {

    static void auction(artifacts ar[], movement mov, condition cond, int arsize){
            for(int i=0;i<arsize;i++){
                ar[i].getInfo();
                int k=ar[i].evaluate(mov,cond);
                if(k==1){
                    System.out.println("the artifact is accepted ");
                }
                else if(k==0){
                    System.out.println("the artifact is rejected ");
                }
            }
        }
    public static void main(String[] args) {
        int N=Integer.parseInt(args[0]);
        String value1=args[1];
        String value2=args[2];
        
        movement movvalue;
        condition condvalue;
        
        if(value1.equals("IMPRESSIONISM")==true){
            movvalue=movement.IMPRESSIONISM;
        }
        else if(value1.equals("EXPRESSIONISM")==true){
            movvalue=movement.IMPRESSIONISM;
        }
        else{
            movvalue=movement.NATURALISM;
        }
        
        System.out.println("the movement value is " + movvalue);
        
        if(value2.equals("BAD")==true){
            condvalue=condition.BAD;
        }
        else if(value2.equals("GOOD")==true){
            condvalue=condition.GOOD;
        }
        else{
            condvalue=condition.EXCELLENT;
        }
        
        System.out.println("the value of the condition is " + condvalue );
        
        int array_size=N;
        
        artifacts[] array=new artifacts[array_size];
        for(int i=0;i<array_size;i++){
            int randomnum=ThreadLocalRandom.current().nextInt(0, 2);
            if(randomnum==1){
                movement mov=movement.values()[ThreadLocalRandom.current().nextInt(0, 3)];
                condition cond=condition.values()[ThreadLocalRandom.current().nextInt(0,3)];
                int creat=ThreadLocalRandom.current().nextInt(0, 101);
                int y=ThreadLocalRandom.current().nextInt(1800, 2019);
                int le=ThreadLocalRandom.current().nextInt(10, 200);
                int wi=ThreadLocalRandom.current().nextInt(10, 200);
                technique tec=technique.values()[ThreadLocalRandom.current().nextInt(0, 3)];
                array[i]=new paintings(mov,cond,creat,y,i,le,wi,tec); 
            }
            else{
                movement move=movement.values()[ThreadLocalRandom.current().nextInt(0, 3)];
                condition condi=condition.values()[ThreadLocalRandom.current().nextInt(0,3)];
                int crea=ThreadLocalRandom.current().nextInt(0, 101);
                int ye=ThreadLocalRandom.current().nextInt(1800, 2019);
                int volu=ThreadLocalRandom.current().nextInt(10, 200);
                material mat=material.values()[ThreadLocalRandom.current().nextInt(0,3)];
                array[i]=new sculptures(move,condi,crea,ye,i,volu,mat);
            }
        }
        auction(array,movvalue,condvalue,array_size);
    }
    
}

abstract class artifacts{
    int index;
    int creator;
    int year;
    artifacts(int creat,int y,int i){
        creator=creat;
        year=y;
        index=i;
        System.out.println("Creating an instance of artifacts ");
    }
    void getInfo(){
        System.out.println("the artifact was created in " + year + " from the creator Creator" + creator);
    }
    void getIndex(){
        System.out.println("the index is "+ index);
    }
    abstract int evaluate(movement mo,condition con);
}

abstract class masterpiece extends artifacts{
    movement m;
    condition c;
    masterpiece(movement mov,condition cond,int creat,int y,int i){
        super(creat,y,i);
        m=mov;
        c=cond;
        System.out.println("Creating an instance of masterpiece ");
    }
    void getInfo(){
        super.getIndex();
        super.getInfo();
        System.out.println("the masterpiece belongs in the "+ m + " movement");
        System.out.println("the masterpiece is in " + c + " condition");
    }
    abstract int evaluate(movement mo,condition con);
}

class paintings extends masterpiece{
    int length;
    int width;
    technique t;
    paintings(movement mov,condition cond,int creat,int y,int i,int le,int wi,technique tec){
        super(mov,cond,creat,y,i);
        length=le;
        width=wi;
        t=tec;
        System.out.println("Creating an instance of paintings ");
    }
    void getInfo(){
        super.getInfo();
        System.out.println("the painting is " + length + " cm in length ");
        System.out.println("the painting is " + width + " cm in width ");
        System.out.println("the painting has " + length*width + " cm^2 surface area");
        System.out.println("the painting is made using the " + t + " technique ");
    }
    int evaluate(movement mo,condition con){
        int h=c.ordinal();
        int g=con.ordinal();
        if((mo==m) && (g<=h)){
            System.out.println("the movement and the condition are correct ");
            return 1;
        }
        else{
            System.out.println("the movement or the condition are incorrect ");
            return 0;
        }   
    }
    int evaluate(movement mo){
        return evaluate(mo,condition.GOOD);
    }
}

class sculptures extends masterpiece{
    int volume;
    material mater;
    sculptures(movement mov,condition cond,int creat,int y,int i,int vol,material ma){
        super(mov,cond,creat,y,i);
        volume=vol;
        mater=ma;
        System.out.println("Creating an instance of sculptures ");
    }
    int evaluate(movement mo,condition con){
        if(mo==m && con==c){
            System.out.println("the movement and the condition are correct ");
            return 1;
        }
        else{
            System.out.println("the movement or the condition are incorrect ");
            return 0;
        }
    }
    int evaluate(movement mo){
        return evaluate(mo,condition.EXCELLENT);
    }
    void getInfo(){
        super.getInfo();
        System.out.println("the sculpture has " + volume + " cm^3 volume");
        System.out.println("the sculpture is made from " + mater );
    }
}