/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author aluno
 */
public class Logic {
    int[][] grid = new int[43][43];
    private int index;
    private long startTime, currentTime;
    ArrayList<Car> c;
    Random r;
    boolean[] chance = new boolean[]{false,false,false,false,false,true,true,true};
    TrafLight tf;
    
    public Logic(){
        this.c = new ArrayList();
        r = new Random();
        startTime = System.currentTimeMillis();
        tf = new TrafLight();
        for(int i=0;i<43;i++){
            for(int j=0;j<43;j++){
                if(i>=0&&i<=16&&j>=0&&j<=16) grid[i][j]=0;
                else if(i>=0&&i<=16&&j>=26&&j<=42) grid[i][j]=0;
                else if(i>=26&&i<=42&&j>=0&&j<=16) grid[i][j]=0;
                else if(i>=26&&i<=42&&j>=26&&j<=42) grid[i][j]=0;
                else if((j>=17&&j<=25)||(i>=17&&i<=25)) grid[i][j]=1;
                if((i==8||i==2||i==1||i==4||i==5||i==7||i==10||i==11||i==13||i==14||i==28||i==29||i==31||i==32||i==34||i==35||i==37||i==38||i==40||i==41)&&j==21) grid[i][j]=3;
                else if((j==8||j==2||j==1||j==4||j==5||j==7||j==10||j==11||j==13||j==14||j==28||j==29||j==31||j==32||j==34||j==35||j==37||j==38||j==40||j==41)&&i==21) grid[i][j]=3;
                else if((i==16||i==26)&&(j>=22&&j<=25)) grid[i][j] = tf.getStatusH();
                else if((j==16||j==26)&&(i>=22&&i<=25)) grid[i][j] = tf.getStatusV();
            }
        }
    }
    public void update(){
        currentTime = System.currentTimeMillis();
        if(currentTime-startTime>=1000.0){
            index = r.nextInt(8);
            if(chance[index]) c.add(new Car());
            startTime = currentTime;
        }
        for(int i=0;i<43;i++){
            for(int j=0;j<43;j++){
                if((j>=17&&j<=25)||(i>=17&&i<=25)) grid[i][j]=1;
                if((i==8||i==2||i==1||i==4||i==5||i==7||i==10||i==11||i==13||i==14||i==28||i==29||i==31||i==32||i==34||i==35||i==37||i==38||i==40||i==41)&&j==21) grid[i][j]=3;
                else if((j==8||j==2||j==1||j==4||j==5||j==7||j==10||j==11||j==13||j==14||j==28||j==29||j==31||j==32||j==34||j==35||j==37||j==38||j==40||j==41)&&i==21) grid[i][j]=3;
                else if((i==16&&j>=22&&j<=25)||(i==26&&j>=17&&j<=20)) grid[i][j] = tf.getStatusH();
                else if((j==16&&i>=17&&i<=20)||(j==26&&i>=22&&i<=25)) grid[i][j] = tf.getStatusV();
            }
        }
        verifyMove();
    }
    public void verifyMove(){
        for(Car car:c){
            if(car.getProgress()<=2){
                grid[car.getX1()][car.getY1()]=2;
                grid[car.getX2()][car.getY2()]=2;
            }
            else if((car.getProgress()==39&&car.turn())||car.getProgress()==41){
                grid[car.getX1()][car.getY1()]=2;
                grid[car.getX2()][car.getY2()]=2;
                c.remove(car);
            }
            else{
                car.move();
                
            }
        }
    }
}