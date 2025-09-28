/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class Epee extends Objet {
    private int ptAtt;
    private int dura;

    public int getPtAtt() {
        return ptAtt;
    }

    public void setPtAtt(int ptAtt) {
        this.ptAtt = ptAtt;
    }

    public int getDura() {
        return dura;
    }

    public void setDura(int dura) {
        this.dura = dura;
    }
    
    
    
    public Epee(String n, Point2D p, int a, int m, int d){
        super(n, p,a);
        ptAtt=m;
        dura=d;
    }
    
    public Epee(Epee e){
        super((Objet)e);
        ptAtt=e.ptAtt;
        dura=e.dura;
    }
    
    public Epee(){
        super("Epee", new Point2D(0,0),1);
        ptAtt=1;
        dura=1;
    }
}
