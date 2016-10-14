/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

/**
 *
 * @author Xenahort
 */
    public class Pair {

        private int lugar;
        private int cubre;

        public Pair(int lugarr, int cubree) {
            super();
            this.lugar = lugarr;
            this.cubre = cubree;
        }

        public int getLugar() {
            return lugar;
        }

        public void setLugar(int first) {
            this.lugar = first;
        }

        public int getCubre() {
            return cubre;
        }

        public void setCubre(int second) {
            this.cubre = second;
        }
        
        public void incrementaCubre(){
            cubre=cubre+1;
        }
    }