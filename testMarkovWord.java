package WordGramClassStarterProgram;


/**
 * Write a description of testMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class testMarkovWord {
public void newText(){
    FileResource fr = new FileResource();
    String s = fr.asString();
    int pos=9091;
    //while(true){
        pos = s.indexOf("sense of his",pos);
        System.out.println(pos);
        System.out.println(s.substring(pos,pos+20));
        //if(pos==-1)
        //break;
        //pos+=12;
    ///}
}
}
