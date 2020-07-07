package recogida_paquetes;

public class Recogida_paquetes {

    public static void main(String[] args) {

        Muelle muelle = new Muelle(20);

        Furgoneta F1 = new Furgoneta(muelle);
        Furgoneta F2 = new Furgoneta(muelle);
        Furgoneta F3 = new Furgoneta(muelle);
        Furgoneta F4 = new Furgoneta(muelle);
        
        Operario O1 = new Operario(muelle);
        Operario O2 = new Operario(muelle);
        Operario O3 = new Operario(muelle);

        F1.start();
        F2.start();
        F3.start();
        F4.start();
        
        O1.start();
        O2.start();
        O3.start();

    }

}
