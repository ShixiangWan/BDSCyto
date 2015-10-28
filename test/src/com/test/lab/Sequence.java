package com.test.lab;
/*
 * Sequence.java
 * 这是提取蛋白质序列特征的类
 */
import java.util.Vector;
/**
 * sequence class
 */
public class Sequence {

    /**
     * sequence of a protein
     */
    private String sequence;
    /**
     * feature tables
     */
    @SuppressWarnings("rawtypes")
	private Vector feature = new Vector();
    /**
     * AminoAcids list, used of calc composition
     */
    private String AminoAcids="ACDEFGHIKLMNPQRSTVWY";
    /**
     * Creates a new instance of Sequence, the initial sequence is the protein sequence in this
     * @param sequence input sequence
     */
    public Sequence(String sequence) {
        this.setSequence(sequence);
    }
    /**
     * return the length of a sequence
     * @return int
     */
    public int getSequenceLength(){
        return sequence.length();
    }
    /**
     * return the sequence in UPPER CASE
     * @return Sequence in String is returned
     */
    public String getSequence(){
        return this.sequence;
    }
    /**
     * Reset the sequence
     * @param sequence Seauence in String, no space or other character allowed here
     */
    public void setSequence(String sequence){
        this.sequence = sequence;
        sequence.toUpperCase();
    }
    /**
     * caculat the composition, 20 features will push back to the Vector of features
     */
    @SuppressWarnings("unchecked")
	public void composition() {
        double[] composition = new double[20];
        for(int i=0;i<sequence.length();i++){
            int index = AminoAcids.indexOf(sequence.charAt(i));
            if(index>=0) composition[index]++;
        }
        for(int i=0;i<20;i++){
            feature.add(composition[i]/sequence.length()*100.00);
            //System.out.println(composition[i]);
        }
    }
    /**
     * as Title
     * @param s the string where we are looking for matched patterns
     * @param pattern the pattern in two chars, the combination of AB and BA are considered as the
     * same pattern. if they are identical, the total number will divided by 2
     * @return the number of occurance of the specified pattern will be returned
     */
    private int findNumberOfOccurance(String pattern, String s) {
        char a=pattern.charAt(0);
        char b=pattern.charAt(1);
        int numberOfOccurance=0;
        //looking for pattern ab
        int position= s.indexOf(String.valueOf(a)+String.valueOf(b));
        while(position>=0){
            position = s.indexOf(String.valueOf(a)+String.valueOf(b), position+1);
            numberOfOccurance++;
        }
        //looking for pattern ba
        position= s.indexOf(String.valueOf(b)+String.valueOf(a));
        while(position>=0){
            position = s.indexOf(String.valueOf(b)+String.valueOf(a), position+1);
            numberOfOccurance++;
        }
        //numberOfOccurance should be divided 2 if a and b equal
        if(a==b) numberOfOccurance/=2;
        //System.out.println("number of occurance is "+numberOfOccurance);
        return numberOfOccurance;
    }
    /**
     * as Title
     * @param p returned features
     * @param transeq as name
     * @param classLabel as name
     * @param current_nG the total number of occurance of this classlabel
     */
    private void calcPvalue(double[] p,char transeq[],char classLabel,double current_nG) {
        int np=0;
        for(int i=0;i<sequence.length();i++){
            if(transeq[i]==classLabel){
                np++;
                if(np == 1)
                    p[0]=i+1;
                if(np == (int) (0.25*current_nG))
                    p[1]=i+1;
                if(np == (int) (0.50*current_nG))
                    p[2]=i+1;
                if(np == (int) (0.75*current_nG))
                    p[3]=i+1;
                if(np == current_nG)
                    p[4]=i+1;
            }
        }
    }
    /**
     * templete will be used to calc the features except compositions
     * @param classString the length of the class is not fixed. pvodied their list of strings as many as
     * you want
     */
    @SuppressWarnings("unchecked")
	public void templete(String... classString){
        char tranSeq[] = sequence.toCharArray();
        int numberOfClass = classString.length;

        // mark tranSeq and count G1,G2,G3 and calculate Vanderwaal(1)~(3)
        double[] nG = new double[numberOfClass];
        for(int i=0;i<sequence.length();i++){
            for(int j=0;j<numberOfClass;j++){
                if( classString[j].indexOf(sequence.charAt(i)) >=0 ){
                    tranSeq[i]= Integer.toString(j).charAt(0);
                    nG[j]++;
                    break;
                }
            }
        }

        for(int i=0;i<numberOfClass;i++)
            feature.add(nG[i]*1.0/sequence.length()*100.0);

        //for(double k:nG)         System.out.println("k is "+k);
        //System.out.println(feature.toString());
        //System.out.println(tranSeq);

        // count F1,F2,F3 and calculate Vanderwaal(4)~(6)
        double[] nF = new double[200];
        int counterOfnF=0;
        for(int i=0;i<numberOfClass-1;i++){
            for(int j=i+1;j<numberOfClass;j++){
                nF[counterOfnF++]=findNumberOfOccurance(
                        String.valueOf(i)+String.valueOf(j),
                        String.copyValueOf(tranSeq));
            }
        }

        for(int i=0;i<counterOfnF;i++)
            feature.add(nF[i]*1.0/(sequence.length()-1)*100.0);

        //for(double k:nF)         System.out.println("k is "+k);
        //System.out.println(feature.toString());


        // count p1,p2,p3 and calculate Vanderwaal(7)~(21)
        for(int i=0;i<numberOfClass;i++){
            double[] pi= new double[5];
            this.calcPvalue(pi, tranSeq, Integer.toString(i).charAt(0), nG[i]);
            for(int j=0;j<5;j++)
                feature.add(pi[j]*1.0/sequence.length()*100.0);
        }
        //System.out.println(feature.toString());

    }
    /**
     * perform the main calc here
     */
    public void performCalcuation() {
        if(sequence.length()<0){
            System.out.println("Empty Sequence.");
            return;
        }
        this.composition();

        /**   calc_Hydrophobic();
         * string sP="RKEDQN";
         * string sN="GASTPHY";
         * string sH="CVLIMFW";
         */
        this.templete("RKEDQN","GASTPHY","CVLIMFW");

        /**  calc_Vanderwaal()
         * string sP="GASCTPD";
         * string sN="NVEQIL";
         * string sH="MHKFRYW";
         */
        this.templete("GASCTPD","NVEQIL","MHKFRYW");

        /**  calc_Polarity1()
         * string sP="LIFWCMVY";
         * string sN="PATGS";
         * string sH="HQRKNED";
         */
        this.templete("LIFWCMVY","PATGS","HQRKNED");

        /**  calc_Polarizability1()
         * string sP="GASDT";
         * string sN="CPNVEQIL";
         * string sH="KMHFRYW";
         */
        this.templete("GASDT","CPNVEQIL","KMHFRYW");

        /** calc_Charge()
         * string sP="KR";
         * string sN="ANCQGHILMFPSTWYV";
         * string sH="DE";
         */
        this.templete("KR","ANCQGHILMFPSTWYV","DE");

        /**  calc_Surfacetension()
         * string sP="GQDNAHR";
         * string sN="KTSEC";
         * string sH="ILMFPWYV";
         */
        this.templete("GQDNAHR","KTSEC","ILMFPWYV");

        /**  calc_Secondarystructure()
         * string sP="EALMQKRH";
         * string sN="VIYCWFT";
         * string sH="GNPSD";
         */
         this.templete("EALMQKRH","VIYCWFT","GNPSD");

        /**  calc_Solventaccessibility()
         * string sP="ALFCGIVW";
         * string sN="RKQEND";
         * string sH="MPSTHY";
         */
         this.templete("ALFCGIVW","RKQEND","MPSTHY");
    }
    /**
     * return the feature vector
     * @return return the feature vector
     */
    @SuppressWarnings("rawtypes")
	public Vector getFeature(){
        return feature;
    }

    @SuppressWarnings("rawtypes")
	public String run(){
        performCalcuation();
        Vector results=getFeature();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i< results.size();i++)
            sb.append(results.get(i).toString()+",");
        return sb.toString();
    }

}