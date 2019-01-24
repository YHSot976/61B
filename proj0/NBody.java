public class NBody{

    public static double readRadius(String file){
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] ps = new Planet[5];
        for(int i = 0; i < 5; i++){
            ps[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                               in.readDouble(), in.readDouble(), in.readString());

        }
        return ps;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        double time = 0;
        while(time < T){
            double[] xForces = new double[5];
            double[] yForces = new double[5];

            for(int i = 0; i < 5; i++){
                xForces[i] =  planets[i].calcNetForceExertedByX(planets);
                yForces[i] =  planets[i].calcNetForceExertedByY(planets);
            }

            for(int i = 0; i < 5; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            for(Planet p : planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }

}
