import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Planet {
    //变量：
    final double FORCELIMIT = 1.75;//影响到地球的最小力为forceLimit;
    final double CAVENDISH = 667;//凯文迪许常量，即公式中的G；
    final double MASSOFEARTH = 5.965;//地球的质量，在下面的参数中，mass表示质量与地球质量的比值；
    protected String name;
    protected int[] center_position;
    protected int raidus;

    //方法：
    public int[] getCenterPosition() {
        return center_position;
    }
    public int getRaidus(){
        return raidus;
    }
    public static void paintPlanet(Planet p){
        int width = 2 * p.getRaidus();
        int leftDistance = p.getCenterPosition()[0]-p.getRaidus();
        int upDistance = p.getCenterPosition()[1]-p.getRaidus();

    }
}

class Earth extends Planet {
    //构造函数：
    public Earth (int[] position,String n,int r,JFrame frame){
        this.center_position = position;
        this.name = n;
        this.raidus = r;
        EarthPanel dpEarth = new EarthPanel();
        frame.getContentPane().add(dpEarth);
        int x = position[0];
        int y = position[1];
        frame.repaint();
    }

    //变量：

    //方法：
    /*判断地球与行星b的距离，安全返回true,危险返回false */
    public boolean checkRocheLimit(Barrier b){
        double safe_distance = b.getRocheLimit();
        double distance = getDistance(b);
        if(distance > safe_distance){
            return true;
        }
        else{
            return  false;
        }
    }
    /*判断地球是否可被行星b的引力影响，可以则返回true,否则返回false*/
    public boolean checkEffectDistance(Barrier b){
        double standard = b.getEffectDistance();
        double distance = getDistance(b);
        if(distance <= standard){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkEndPlace(int[] endPosition){
        if(this.getCenterPosition() .equals(endPosition)){
            return true;
        }
        else{
            return false;
        }
    }//判断是否到达终点；
    public void move(Routine r,JFrame frame){
        int[] xPoints = r.getxPoints();
        int[] yPoints = r.getyPoints();
        EarthPanel dpEarth = new EarthPanel();
        frame.getContentPane().add(dpEarth);
        for(int i = 0; i < xPoints.length; i++){
            int x = xPoints[i];
            int y = yPoints[i];
            frame.repaint();
            try{
                Thread.sleep(50);
            }catch(Exception ex){ }//可以放缓，控制move的速度；
        }
    }//地球以特定轨道运动；
    public Routine changeRoutine(Barrier b,Routine oriRountine){
        int[] positionOfB = b.getCenterPosition();
        int[] xPoints = oriRountine.getxPoints();
        int[] yPoints = oriRountine.getyPoints();
        double standard = b.getEffectDistance();
        boolean doCross = false;
        ArrayList<Integer> pointsToChange = new ArrayList<Integer>();
        for(int i =0; i < xPoints.length; i++){
            if(Math.sqrt(Math.pow(positionOfB[0]-xPoints[i],2)+Math.pow(positionOfB[1]-yPoints[i],2))<=standard){
                doCross = true;
                pointsToChange.add(i);
            }
        }
        if(doCross){

        }
        else{
            return oriRountine;
        }
    }
    /*计算地球与另一行星的距离*/
    private double getDistance(Barrier b){
        int[] position1 = this.getCenterPosition();
        int[] position2 = b.getCenterPosition();
        double distance = Math.sqrt((position1[0]-position2[0])*(position1[0]-position2[0])
                +(position1[1]-position2[1])*(position1[1]-position2[1]));
        return distance;
    }

class Barrier extends Planet{
    //构造函数：
    public Barrier(int[] position,String n,int b,double m,JFrame frame){
        this.center_position = position;
        this.name = n;
        this.raidus =b;
        this.mass = m;
        drawPlanet(frame,position,b,(int)this.effect_distance);
    }

    //变量：
    private double mass;
    private double Roche_limit = 2.44 * raidus; //洛希极限，两行星距离小于该值时小行星会消散；
    /*effect_distance表示行星引力可影响的最大半径*/
    private double effect_distance =Math.sqrt((MASSOFEARTH*CAVENDISH*mass*MASSOFEARTH)/FORCELIMIT);

    //方法：
    public double getRocheLimit(){
        return Roche_limit;
    }
    public double getEffectDistance(){
        return effect_distance;
    }
    private static void drawPlanet(JFrame frame,int[] position,int raidus,int effectDistance){
        int x1 = position[0] - raidus;
        int x2 = position[1] - raidus;
        DrawPanelOfBarrier dpBarrier = new DrawPanelOfBarrier();
        frame.getContentPane().add(dpBarrier);
        frame.repaint();
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setVisible(true);
        int[] position = {120,150};
        Barrier mine = new Barrier(position,"Earth",50,10,frame);
    }
}

class DrawPanelOfBarrier extends JPanel{
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x1,x2,2*raidus,2*raidus);
        g.setColor(Color.red);
        g.drawOval(x1,x2,(int)2.44*raidus*2,(int)2.44*raidus*2);
        g.setColor(Color.yellow);
        g.drawOval(x1,x2,2*effectDistance,2*effectDistance);
    }
}

class EarthPanel extends JPanel{
    public void paintComponent(Graphics g){
        g.fillOval(x-raidus,y-raidus,2*raidus,2*raidus);
    }
}