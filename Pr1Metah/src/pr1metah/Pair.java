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

        public Pair(int lugar, int cubre) {
            super();
            this.lugar = lugar;
            this.cubre = cubre;
        }

        public int getLugar() {
            return lugar;
        }

        public void setLugar(int lugar) {
            this.lugar = lugar;
        }

        public int getCubre() {
            return cubre;
        }

        public void setCubre(int cubre) {
            this.cubre = cubre;
        }
        
        public void aumentaCubre(){
            ++cubre;
        }
    }