public enum DishwasherProgramTypes {
    Glasses, Plates, Pans, Mixed;

    int getProgramDuration() {
        if (this == Glasses) {
            return 60;
        }

        if (this == Plates) {
            return 90;
        }

        if (this == Pans) {
            return 120;
        }

        if (this == Mixed) {
            return 100;
        }

        else return 0;
    }
}