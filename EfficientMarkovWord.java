package WordGramClassStarterProgram;


/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;
    public EfficientMarkovWord(int num) {
        myRandom = new Random();
        myOrder = num;
        map = new HashMap<WordGram,ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public void buildMap(){
        int index=0;
        //System.out.println(myText.length());
        WordGram wb = new WordGram(myText,0,myOrder);
        while(index < myText.length){
            ArrayList<String> list = new ArrayList<String>();
            int start = indexOf(myText,wb,index);
            if(start==-1){
                break;
            }
            if(start + myOrder > myText.length-1){
                map.put(wb,list);
                break;
            }
            String next = myText[start+myOrder];
            if(map.containsKey(wb)){
                list = map.get(wb);
                list.add(next);
                map.put(wb,list);
            }
            else{
                list.add(next);
                map.put(wb,list);
            }
            wb = wb.shiftAdd(next);
            index = start + 1;
        }
            
    }
    
    public void testHashMap(){
        //buildMap();
        System.out.println("map size"+map.size());
        int max=0,maxi=-1;
        for(WordGram wg :map.keySet()){
           // System.out.println(wg+" "+map.get(wg));
            if(map.get(wg).size()>max){
                max = map.get(wg).size();
            }
        }
        System.out.println("max size of a map is"+max);
        for(WordGram wg :map.keySet()){
            if(map.get(wg).size()==max){
                System.out.println(wg+" "+map.get(wg));
            }
        }
    }
    public String getRandomText(int numWords){
        buildMap();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram wb = new WordGram(myText,index,myOrder);
        for(int i=0 ; i<wb.length() ;i++){
            String key = wb.wordAt(i);
            sb.append(key);
            sb.append(" ");
        }
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(wb);
            //System.out.println(wb+" "+follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            //System.out.println(index);
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            wb = wb.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words,WordGram target,int start){
        for(int i=start;i<words.length;i++){
            if(words[i].equals(target.wordAt(0))){
                if(helpCompare(words,i,target)){
                    return i;
                }
            }
        }
        return -1;
    }
    
    private boolean helpCompare(String[] array1,int start,WordGram target){
        for(int i=start;i<(start+target.length());i++){
                //System.out.println(array1[i]+"--"+target.wordAt(i-start));
                //if(target.wordAt(0).equals("sense")){
                 //  System.out.println(i);
                //}
            if(!array1[i].equals(target.wordAt(i-start))){
                return false;
            }
        }
        return true;
    }
    
    public void testIndexOf(){
        WordGram wb = new WordGram(myText,1512,3);
        
        System.out.println(indexOf(myText,wb,1513));
        //1512 10546 22854
        for(int i=0;i<3;i++){
        System.out.println("1512-"+myText[1512+i]);
        System.out.println("10546-"+myText[10546+i]);
        System.out.println("22855-"+myText[22855+i]);
        }
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);
    }
}
