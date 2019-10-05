package WordGramClassStarterProgram;


/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(3);
        markovWord.setTraining(st);
        runModel(markovWord, st, 200 ,621);
        MarkovWord markovWord2 = new MarkovWord(5);
        markovWord.setTraining(st);
        runModel(markovWord2, st, 200 ,844);
        //markovWord.testIndexOf();
    } 
    
    public void runEfficientMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(3);
        markovWord.setTraining(st);
        runModel(markovWord, st, 50 ,371);
        //markovWord.testIndexOf();
        markovWord.testHashMap();
        EfficientMarkovWord markovWord2 = new EfficientMarkovWord(2);
        markovWord2.setTraining(st);
        runModel(markovWord2, st, 50 ,65);
        //markovWord.testIndexOf();
        markovWord2.testHashMap();
    } 
    
    public void compareMethods(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        long current1 = System.nanoTime();
        System.out.println(current1);
        MarkovWord markovWord = new MarkovWord(2);
        markovWord.setTraining(st);
        runModel(markovWord, st, 100 ,42);
        long current2 = System.nanoTime();
        System.out.println(current2-current1);
        //EfficientMarkovWord EmarkovWord = new EfficientMarkovWord(2);
        //EmarkovWord.setTraining(st);
        //runModel(EmarkovWord, st, 50 ,42);
        long current3 = System.nanoTime();
        System.out.println(current3-current2);
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
