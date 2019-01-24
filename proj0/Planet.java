public class Planet{

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double r;
        r = java.lang.Math.sqrt((p.xxPos - this.xxPos) * (p.xxPos - this.xxPos)
            + (p.yyPos - this.yyPos) * (p.yyPos - this.yyPos));
        return r;
    }

    public double calcForceExertedBy(Planet p){
        double G = 6.67e-11;
        double F;
        F = G * this.mass * p.mass / this.calcDistance(p) / this.calcDistance(p);
        return F;
    }

    public double calcForceExertedByX(Planet p){
        double Fx;
        Fx = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) /
             this.calcDistance(p);
        return Fx;
    }

    public double calcForceExertedByY(Planet p){
        double Fy;
        Fy = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) /
             this.calcDistance(p);
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] ps){
        double Fx = 0;
        for(Planet p : ps){
            if(this.equals(p)){
                continue;
            }
            Fx = Fx + this.calcForceExertedByX(p);
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Planet[] ps){
        double Fy = 0;
        for(Planet p : ps){
            if(this.equals(p)){
                continue;
            }
            Fy = Fy + this.calcForceExertedByY(p);
        }
        return Fy;
    }

    public void update(double dt, double fX, double fY){
        xxVel = (fX / mass) * dt + xxVel;
        yyVel = (fY / mass) * dt + yyVel;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }

    public void draw(){
        String imgtodraw = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgtodraw);
    }

}
