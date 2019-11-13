public enum WashingMachineWashingTypes {
    DoubleRins, Intense, Quick, Spin;

    int getTiming(){
        if(this == DoubleRins){
            return 120;
        }
        if(this == Intense){
            return 80;
        }
        if(this == Quick){
            return  60;
        }
        else{
            return 30;
        }
    }
}
