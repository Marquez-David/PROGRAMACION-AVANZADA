/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museo;

/**
 *
 * @author Darks
 */
public class Persona extends Thread{
    
    private Museo museo;
    private String idPersona;
    private String decisionEntrada;
    private String[] entradas= {"Este", "Oeste"};
    
    public Persona(Museo museo, String idPersona){
        this.museo = museo;
        this.idPersona = idPersona;
    }
    
    @Override
    public void run(){
        decidirEntrada();
        if("Este".equals(decisionEntrada)){
            museo.entrarMuseoEste(this);
        }else{
            museo.entrarMuseoOeste(this);
        }
        
        museo.salirMuseo(this);
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void decidirEntrada(){
        decisionEntrada = entradas[(int)Math.random()];
        //System.out.println("decision: " + decisionEntrada);
    }
 
}
