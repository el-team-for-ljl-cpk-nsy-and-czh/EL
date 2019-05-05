import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Routine {
    //构造函数;
    public Routine(int[] startPoint,int[] endPoint,double a,double b,double c){
        this.startX = startPoint[0];
        this.startY = startPoint[1];
        this.endX = endPoint[0];
        ArrayList<Integer> xPoints = new ArrayList<Integer>();
        ArrayList<Integer> yPoints = new ArrayList<Integer>();
        for(int i = startX; i <= endX; i++){
            xPoints.add(i);
            int y =(int) (a*i*i + b*i + c);
            yPoints.add(y);
        }
        this.xPoints = getArray(xPoints);
        this.yPoints = getArray(yPoints);
    }//y=ax*x+bx+c是轨迹方程
    //变量；
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int[] xPoints;
    private int[] yPoints;

    //方法；
    public int[] getxPoints(){
        return xPoints;
    }
    public int[] getyPoints(){
        return yPoints;
    }
    public void revealRoutine(JFrame frame){
        int[] x = this.getxPoints();
        int[] y = this.getyPoints();
        RoutinePanel dpRoutine = new RoutinePanel();
        frame.getContentPane().add(dpRoutine);
        frame.repaint();
    }//画出运动轨迹（灰色）；
    private static int[] getArray(ArrayList<Integer> list){
        int[] result = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
}

class RoutinePanel extends JPanel{
    public void paintComponent(Graphics g){
        g.setColor(Color.gray);
        g.drawPolyline(x,y,x.length);
    }
}